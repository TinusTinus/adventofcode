package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * State of the game.
 *
 * @author Martijn van de Rijdt
 */
class State {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);
    
    /** The static map, consisting of walls and open areas. */
    private final Square[][] map;
    /** Currently alive units. */
    private final Set<Unit> units;
    /** The number of rounds. Note that the last round was not complete. */
    private final int rounds;
    
    /**
     * Constructor.
     * 
     * @param map static map, consisting of walls and open areas
     * @param units currently alive units
     */
    private State(Square[][] map, Set<Unit> units, int completedRounds) { 
        super();
        this.map = map;
        this.units = units;
        this.rounds = completedRounds;
    }
    
    /**
     * If no targets remain, combat ends.
     * 
     * @return whether combat has ended; that is, whether either all elves or all goblins have died
     */
    boolean isCombatDone() {
        return units.stream()
                .map(Unit::getRace)
                .distinct()
                .count() < 2;
    }
    
    /**
     * Computes the outcome of this game state: the number of full rounds that were
     * completed (not counting the round in which combat ends) multiplied by the sum
     * of the hit points of all remaining units at the moment combat ends.
     * 
     * This is only relevant after combat has comleted (see
     * {@link #isCombatDone()}).
     * 
     * @return outcome
     */
    int getOutcome() {
        int totalHitPoints = units.stream()
                .mapToInt(Unit::getHitPoints)
                .sum();
        
        return totalHitPoints * rounds; 
    }

    /**
     * Performs a single round of combat.
     * 
     * @return new state
     */
    State performCombatRound() {
        List<Unit> sortedUnits = units.stream()
                .sorted(Unit.READING_ORDER)
                .collect(Collectors.toList());
        int i = 0;
        boolean done = false;
        
        while (!done && i < sortedUnits.size()) {
            Unit unit = sortedUnits.get(i);
            
            // Each unit begins its turn by identifying all possible targets (enemy units).
            Set<Unit> targets = sortedUnits.stream()
                    .filter(target -> target.getRace() != unit.getRace())
                    .collect(Collectors.toSet());
            // If no targets remain, combat ends.
            done = targets.isEmpty();
            
            if (!done) {
                
                Set<Unit> otherUnits = sortedUnits.stream()
                        .filter(u -> u != unit)
                        .collect(Collectors.toSet());
                
                // Then, the unit identifies all of the open squares (.) that are in range of
                // each target. 
                Set<Point> adjacentPointsToTarget = targets.stream()
                        .map(Unit::getLocation)
                        // These are the squares which are adjacent to any target...
                        .flatMap(location -> location.adjacent().stream())
                        // ... and which aren't already occupied by a wall...
                        .filter(point -> map[point.getX()][point.getY()] == Square.OPEN_AREA)
                        // ... or another unit.
                        .filter(point -> otherUnits.stream().noneMatch(otherUnit -> otherUnit.getLocation().equals(point)))
                        .collect(Collectors.toSet());
                
                Unit movedUnit;
                if (adjacentPointsToTarget.contains(unit.getLocation())) {
                    // The unit is already in range of a target.
                    // Do not move.
                    movedUnit = unit;
                    LOGGER.debug("Unit {} stays at {}: target(s) already in range.", unit, unit.getLocation());
                } else {
                    // Try to move towards a closest target to compute all shortest paths for all points.
                    
                    // Use Dijkstra's shortest path algorithm.
                    Set<Point> unvisited = new HashSet<>();
                    Map<Point, Set<List<Point>>> shortestPaths = new HashMap<>();
                    int width = map.length;
                    int height = map[0].length;
                    for (int x = 0; x != width; x++) {
                        for (int y = 0; y != height; y++) {
                            Point point = new Point(x, y);
                            if (map[x][y] == Square.OPEN_AREA && otherUnits.stream().noneMatch(otherUnit -> otherUnit.getLocation().equals(point))) {
                                unvisited.add(point);
                                shortestPaths.put(point, new HashSet<>());
                            }
                        }
                    }
                    
                    shortestPaths.get(unit.getLocation()).add(List.of());
                    
                    Optional<Point> nextU = unvisited.stream()
                            .filter(p -> !shortestPaths.get(p).isEmpty())
                            .min(Comparator.comparing(p -> shortestPaths.get(p).iterator().next().size()));
                    while (nextU.isPresent()) {
                        Point u = nextU.get();
                        unvisited.remove(u);
                        
                        int altPathLength = shortestPaths.get(u).iterator().next().size() + 1;
                        for (Point neighbour : u.adjacent()) {
                            if (unvisited.contains(neighbour)) {
                                Set<List<Point>> paths = shortestPaths.get(neighbour);
                                OptionalInt pathLength;
                                if (paths.isEmpty()) {
                                    pathLength = OptionalInt.empty();
                                } else {
                                    pathLength = OptionalInt.of(paths.iterator().next().size());
                                }
                                if (pathLength.isPresent() && altPathLength < pathLength.getAsInt()) {
                                    // Shorter path found.
                                    paths.clear();
                                }
                                if (!pathLength.isPresent() || altPathLength <= pathLength.getAsInt()) {
                                    shortestPaths.get(u).stream()
                                        .map(ArrayList::new)
                                        .peek(list -> list.add(neighbour))
                                        .forEach(paths::add);
                                }
                            }
                        }
                        
                        nextU = unvisited.stream()
                                .filter(p -> !shortestPaths.get(p).isEmpty())
                                .min(Comparator.comparing(p -> shortestPaths.get(p).iterator().next().size()));
                    }
                    
                    LOGGER.trace("Shortest paths: {}", shortestPaths);
                    
                    // If multiple squares are in range and tied for being reachable in the fewest
                    // steps, the square which is first in reading order is chosen.
                    Comparator<Point> comparator = Comparator.comparing(p -> shortestPaths.get(p).iterator().next().size());
                    comparator = comparator.thenComparing(Comparator.naturalOrder());
                    Optional<Point> newLocation = adjacentPointsToTarget.stream()
                            .filter(p -> !shortestPaths.get(p).isEmpty())
                            .min(comparator)
                            .map(shortestPaths::get)
                            .orElse(Set.of())
                            .stream()
                            // Find the first step.
                            .map(path -> path.get(0))
                            // If multiple steps would put the unit equally closer to its destination,
                            // the unit chooses the step which is first in reading order.
                            .min(Comparator.naturalOrder());
                    
                    if (newLocation.isPresent()) {
                        // Take this step.
                        movedUnit = new Unit(unit.getRace(), newLocation.get(), unit.getHitPoints());
                        sortedUnits.set(i, movedUnit);
                        LOGGER.debug("Unit {} moved from {} to {}.", unit, unit.getLocation(), movedUnit.getLocation());
                    } else {
                        // Do not move.
                        movedUnit = unit;
                        LOGGER.debug("Unit {} stays at {}: no path to target.", unit, unit.getLocation());
                    }
                }
                
                // After moving (or if the unit began its turn in range of a target), the unit attacks.
                Optional<Unit> selectedTarget = targets.stream()
                        // To attack, the unit first determines all of the targets that are in range of it by being immediately adjacent to it.
                        .filter(target -> movedUnit.getLocation().adjacent().contains(target.getLocation()))
                        // Otherwise, the adjacent target with the fewest hit points is selected;
                        // in a tie, the adjacent target with the fewest hit points which is first in reading order is selected.
                        .min(Comparator.comparing(Unit::getHitPoints).thenComparing(Unit::getLocation));
                if (selectedTarget.isPresent()) {
                    Unit target = selectedTarget.get();
                    int targetIndex = sortedUnits.indexOf(target);
                    LOGGER.debug("Unit {} at {} is attacking {} at {}.", movedUnit, movedUnit.getLocation(), target, target.getLocation());
                    
                    // The unit deals damage equal to its attack power to the selected target, reducing its hit points by that amount.
                    int remainingHitPoints = target.getHitPoints() - Unit.ATTACK_POWER;
                    if (0 < remainingHitPoints) {
                        sortedUnits.set(targetIndex, new Unit(target.getRace(), target.getLocation(), remainingHitPoints));
                    } else {
                        // The selected target dies.
                        LOGGER.debug("Target {} dies.", target);
                        sortedUnits.remove(targetIndex);
                        if (targetIndex < i) { 
                            i--;
                        }
                    }
                } else {
                    LOGGER.debug("Unit {} at {} cannot attack a target.", movedUnit, movedUnit.getLocation());
                }
                i++;
            }
        }
        
        int nextRounds = rounds;
        if (!done) {
            nextRounds++;
        }
        
        State result = new State(map, new HashSet<>(sortedUnits), nextRounds);
        LOGGER.debug("Next state:\n{}", result);
        return result;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Note: the output is multi-line
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("After ");
        builder.append(rounds);
        builder.append(" round(s):\n");
        
        int width = map.length;
        int height = map[0].length;
        
        for (int y = 0; y != height; y++) {
            for (int x = 0; x != width; x++) {
                Set<Unit> unitsHere = unitsAt(x, y);
                if (unitsHere.isEmpty()) {
                    builder.append(map[x][y]);
                } else if (1 < unitsHere.size()) {
                    // should not occur but hey
                    builder.append('X');
                } else {
                    builder.append(unitsHere.iterator().next().getRace());
                }
            }
            builder.append("    ");
            builder.append(unitsAt(y).stream()
                    .map(Unit::toString)
                    .collect(Collectors.joining(", ")));
            builder.append("\n");
        }
        
        return builder.toString();
    }
    
    /**
     * Returns all units at the given coordinates.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @return units at x,y
     */
    private Set<Unit> unitsAt(int x, int y) {
        return units.stream()
                .filter(unit -> unit.getLocation().getX() == x && unit.getLocation().getY() == y)
                .collect(Collectors.toSet());
    }
    
    /**
     * Returns all units at the given y coordinate, sorted by their x coordinate.
     * 
     * @param y y coordinate
     * @return units at y
     */
    private List<Unit> unitsAt(int y) {
        return units.stream()
                .filter(unit -> unit.getLocation().getY() == y)
                .sorted(Unit.READING_ORDER)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses the input file into a state.
     * 
     * @param inputFilePath path of the input file
     * @return initial state
     * @throws IOException if the input could not be read
     */
    static State parse(Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
        
        int height = lines.size();
        int width = lines.get(0).length();
        
        Square[][] map = new Square[width][height];
        Set<Unit> units = new HashSet<>();
        
        for (int y = 0; y != height; y++) {
            String line = lines.get(y);
            for (int x = 0; x != width; x++) {
                char c = line.charAt(x);
                
                Optional<Unit> unit = parseUnit(x, y, c);
                
                unit.ifPresent(units::add);
                
                map[x][y] = unit.map(u -> Square.OPEN_AREA)
                        .orElseGet(() -> Square.of(c));
            }
        }
        
        State result = new State(map, units, 0);
        LOGGER.debug("Parsed state:\n{}", result);
        return result;
    }
    
    /**
     * Creates a new unit with the given info.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param c character representation of the unit's race
     * @return new unit with full hit points, or empty if c does not correspond to a {@link Race} value
     */
    private static Optional<Unit> parseUnit(int x, int y, char c) {
        return Race.of(c)
                .map(race -> new Unit(race, x, y));
    }
}
