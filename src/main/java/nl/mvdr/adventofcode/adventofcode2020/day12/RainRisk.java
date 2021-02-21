package nl.mvdr.adventofcode.adventofcode2020.day12;

import java.util.List;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 12 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/12">Rain Risk</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class RainRisk implements IntSolver {

    private final boolean part1;
    
    /**
     * Constructor.
     * 
     * @param part1 whether the rules from part 1 of the puzzle apply
     */
    RainRisk(boolean part1) {
        super();
        this.part1 = part1;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return Manhattan distance between the start and end position of the ship
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Instruction> instructions = Instruction.parse(lines, part1);
        
        Ship ship = Ship.STARTING_POSITION;
        
        for (Instruction instruction : instructions) {
            ship = instruction.execute(ship);
        }
        
        return ship.location().manhattanDistanceToOrigin();
    }
}
 