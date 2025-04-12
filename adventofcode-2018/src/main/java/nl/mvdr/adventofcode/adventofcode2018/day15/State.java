package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * State of the game.
 *
 * @author Martijn van de Rijdt
 */
class State {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);

    /** Default attack power. */
    static final int DEFAULT_ATTACK_POWER = 3;
    
    /** The static map, consisting of walls and open areas. */
    private final Square[][] map;
    /** Currently alive units. */
    private final Set<Unit> units;
    /** The number of rounds. Note that the last round was not complete. */
    private final int rounds;
    /** Attack power per race. */
    private final Map<Race, Integer> attackPower;
    /** Number of elves that have died so far. */
    private final int elfDeaths;
    
    /**
     * Constructor.
     * 
     * @param map static map, consisting of walls and open areas
     * @param units currently alive units
     * @param completedRound number of completed rounds, where every unit got a turn
     * @param attackPower attack power
     * @param elfDeaths number of elves that have died so far
     */
    private State(Square[][] map, Set<Unit> units, int completedRounds, Map<Race, Integer> attackPower, int elfDeaths) { 
        super();
        this.map = map;
        this.units = units;
        this.rounds = completedRounds;
        this.attackPower = attackPower;
        this.elfDeaths = elfDeaths;
    }
    
    /**
     * If no targets remain, combat ends.
     * 
     * @return whether combat has ended; that is, whether either all elves or all goblins have died
     */
    private boolean isCombatDone() {
        return units.stream()
                .map(Unit::getRace)
                .distinct()
                .count() < 2;
    }
    
    /** @return number of elf deaths */
    int getElfDeaths() {
        return elfDeaths;
    }
    
    /**
     * Computes the outcome of this game state: the number of full rounds that were
     * completed (not counting the round in which combat ends) multiplied by the sum
     * of the hit points of all remaining units at the moment combat ends.
     * 
     * This is only relevant after combat has completed (see
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
     * Returns a copy of this state with the given elf attack power value.
     * 
     * @param elfAttackPower elf attack power
     * @return state
     */
    State withElfAttackPower(int elfAttackPower) {
        Map<Race, Integer> newAttackPower = Map.of(Race.GOBLIN, attackPower.get(Race.GOBLIN), Race.ELF, Integer.valueOf(elfAttackPower));
        
        return new State(map, units, rounds, newAttackPower, elfDeaths);
    }

    /**
     * Performs a single round of combat.
     * 
     * @param stopAtElfDeath whether to stop computation as soon as an elf dies
     * @return new state
     */
    private State performCombatRound(boolean stopAtElfDeath) {
        List<Unit> sortedUnits = units.stream()
                .sorted(Unit.READING_ORDER)
                .collect(Collectors.toList());
        int i = 0;
        boolean done = false;
        
        int newElfDeaths = 0;
        
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
                        .flatMap(location -> location.neighbours().stream())
                        // ... and which aren't already occupied by a wall...
                        .filter(point -> map[point.x()][point.y()] == Square.OPEN_AREA)
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
                    Map<Point, OptionalInt> pathLengths = new HashMap<>();
                    Map<Point, Set<Point>> firstSteps = new HashMap<>();
                    
                    int width = map.length;
                    int height = map[0].length;
                    for (int x = 0; x != width; x++) {
                        for (int y = 0; y != height; y++) {
                            Point point = new Point(x, y);
                            if (map[x][y] == Square.OPEN_AREA && otherUnits.stream().noneMatch(otherUnit -> otherUnit.getLocation().equals(point))) {
                                unvisited.add(point);
                                firstSteps.put(point, new HashSet<>());
                                pathLengths.put(point, OptionalInt.empty());
                            }
                        }
                    }
                    
                    pathLengths.put(unit.getLocation(), OptionalInt.of(0));
                    
                    Optional<Point> nextU = unvisited.stream()
                            .filter(p -> pathLengths.get(p).isPresent())
                            .min(Comparator.comparingInt(p -> pathLengths.get(p).getAsInt()));
                    while (nextU.isPresent()) {
                        Point u = nextU.get();
                        unvisited.remove(u);
                        
                        int altPathLength = pathLengths.get(u).getAsInt() + 1;
                        for (Point neighbour : u.neighbours()) {
                            if (unvisited.contains(neighbour)) {
                                if (pathLengths.get(neighbour).isPresent() && altPathLength < pathLengths.get(neighbour).getAsInt()) {
                                    // Shorter path found.
                                    LOGGER.trace("Shorter path found.");
                                    firstSteps.get(neighbour).clear();
                                }
                                
                                if (!pathLengths.get(neighbour).isPresent() || altPathLength <= pathLengths.get(neighbour).getAsInt()) {
                                    
                                    Set<Point> newFirstSteps = firstSteps.get(u);
                                    if (newFirstSteps.isEmpty()) {
                                        // this is the very start of a path
                                        newFirstSteps = Set.of(neighbour);
                                    }
                                    firstSteps.get(neighbour).addAll(newFirstSteps);
                                    
                                    pathLengths.put(neighbour, OptionalInt.of(altPathLength));
                                }
                            }
                        }
                        
                        LOGGER.trace("Remaining unvisited: {}", Integer.valueOf(unvisited.size()));
                        nextU = unvisited.stream()
                                .filter(p -> pathLengths.get(p).isPresent())
                                .min(Comparator.comparingInt(p -> pathLengths.get(p).getAsInt()));
                    }
                    
                    LOGGER.trace("First steps: {}", firstSteps);
                    
                    // If multiple squares are in range and tied for being reachable in the fewest
                    // steps, the square which is first in reading order is chosen.
                    Comparator<Point> comparator = Comparator.comparingInt(p -> pathLengths.get(p).getAsInt());
                    comparator = comparator.thenComparing(Comparator.naturalOrder());
                    Optional<Point> newLocation = adjacentPointsToTarget.stream()
                            .filter(p -> pathLengths.get(p).isPresent())
                            .min(comparator)
                            // Find the first step.
                            .map(firstSteps::get)
                            .orElse(Set.of())
                            .stream()
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
                        .filter(target -> movedUnit.getLocation().neighbours().contains(target.getLocation()))
                        // Otherwise, the adjacent target with the fewest hit points is selected;
                        // in a tie, the adjacent target with the fewest hit points which is first in reading order is selected.
                        .min(Comparator.comparing(Unit::getHitPoints).thenComparing(Unit::getLocation));
                if (selectedTarget.isPresent()) {
                    Unit target = selectedTarget.get();
                    int targetIndex = sortedUnits.indexOf(target);
                    LOGGER.debug("Unit {} at {} is attacking {} at {}.", movedUnit, movedUnit.getLocation(), target, target.getLocation());
                    
                    // The unit deals damage equal to its attack power to the selected target, reducing its hit points by that amount.
                    int remainingHitPoints = target.getHitPoints() - attackPower.get(unit.getRace()).intValue();
                    if (0 < remainingHitPoints) {
                        sortedUnits.set(targetIndex, new Unit(target.getRace(), target.getLocation(), remainingHitPoints));
                    } else {
                        // The selected target dies.
                        LOGGER.debug("Target {} dies.", target);
                        sortedUnits.remove(targetIndex);
                        if (targetIndex < i) { 
                            i--;
                        }
                        if (target.getRace() == Race.ELF) {
                            newElfDeaths++;
                            done = stopAtElfDeath;
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
        
        State result = new State(map, new HashSet<>(sortedUnits), nextRounds, attackPower, elfDeaths + newElfDeaths);
        LOGGER.debug("Next state:\n{}", result);
        return result;
    }
    
    /**
     * Resolves combat.
     * 
     * @param stopAtElfDeath whether to stop computation as soon as an elf dies
     * @return end state, after combat is done
     */
    State performCombat(boolean stopAtElfDeath) {
        State state = this;
        
        while(!state.isCombatDone() && (!stopAtElfDeath || 0 == state.getElfDeaths())) {
            state = state.performCombatRound(stopAtElfDeath);
        }
        
        return state;
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
                .filter(unit -> unit.getLocation().x() == x && unit.getLocation().y() == y)
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
                .filter(unit -> unit.getLocation().y() == y)
                .sorted(Unit.READING_ORDER)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses the input file into a state.
     * 
     * @param input contents of the input file
     * @return initial state
     */
    static State parse(Stream<String> input) {
        List<String> lines = input
                // ignore empty lines (the last line in the file)
                .filter(Predicate.not(String::isBlank))
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
                
                map[x][y] = unit.map(_ -> Square.OPEN_AREA)
                        .orElseGet(() -> Square.of(c));
            }
        }
        
        State result = new State(map, units, 0, Map.of(Race.GOBLIN, Integer.valueOf(DEFAULT_ATTACK_POWER), Race.ELF, Integer.valueOf(DEFAULT_ATTACK_POWER)), 0);
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
