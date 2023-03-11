package nl.mvdr.adventofcode.adventofcode2022.day09;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/09">Rope Bridge</a>.
 *
 * @author Martijn van de Rijdt
 */
class RopeBridge implements IntSolver {

    private final int length;

    /**
     * Constructor.
     * 
     * @param length the length of the rope, that is, the number of knots
     */
    RopeBridge(int length) {
        super();
        this.length = length;
    }

    @Override
    public int solve(Stream<String> lines) {
        var motions = lines.map(Motion::parse)
                .collect(Collectors.toList());
        Rope rope = new Rope(length);
        for (Motion motion : motions) {
            rope = motion.perform(rope);
        }
        return rope.visited().size();
    }
}
