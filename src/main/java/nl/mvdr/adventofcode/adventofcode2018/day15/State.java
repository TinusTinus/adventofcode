package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
    /** The number of rounds that have been completed. */
    private final int completedRounds;
    
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
        this.completedRounds = completedRounds;
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
        
        return totalHitPoints * completedRounds; 
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
            Set<Unit> targets = sortedUnits.stream()
                    .filter(target -> target.getRace() != unit.getRace())
                    .collect(Collectors.toSet());
            done = targets.isEmpty();
            
            if (!done) {
                // TODO do nothing, attack or move
                throw new IllegalStateException("Not implemented yet");
            }
        }
        
        int nextCompletedRounds = completedRounds;
        if (!done) {
            nextCompletedRounds++;
        }
        
        State result = new State(map, new HashSet<>(sortedUnits), nextCompletedRounds);
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
        builder.append(completedRounds);
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
                .filter(unit -> unit.getX() == x && unit.getY() == y)
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
                .filter(unit -> unit.getY() == y)
                .sorted(Comparator.comparing(Unit::getX))
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
