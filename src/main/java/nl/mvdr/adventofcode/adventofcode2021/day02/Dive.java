package nl.mvdr.adventofcode.adventofcode2021.day02;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to 2021's Advent of Code:
 * <a href="https://adventofcode.com/2021/day/2">Dive!</a>.
 *
 * @author Martijn van de Rijdt
 */
class Dive implements IntSolver {

    private final boolean applyAim;
    
    /**
     * Constructor

     * @param applyAim whether the rules from part 2 of the puzzle should be applied
     */
    public Dive(boolean applyAim) {
        super();
        this.applyAim = applyAim;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var commands = Command.parse(lines, applyAim);
        
        var submarine = Submarine.STARTING_POSITION;
        for (Command command : commands) {
            submarine = command.execute(submarine);
        }
        
        var location = submarine.location();
        return location.x() * submarine.location().y();
    }
}
 