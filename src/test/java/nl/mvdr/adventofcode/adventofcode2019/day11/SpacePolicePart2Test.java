package nl.mvdr.adventofcode.adventofcode2019.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpacePolicePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SpacePolicePart2Test extends SolverTest<SpacePolicePart2> {

    /** Constructor. */
    public SpacePolicePart2Test() {
        super(SpacePolicePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("""
                        Hull:
                        .##...##...##..#....###...##....##.####
                        #..#.#..#.#..#.#....#..#.#..#....#.#...
                        #..#.#....#..#.#....#..#.#.......#.###.
                        ####.#.##.####.#....###..#.##....#.#...
                        #..#.#..#.#..#.#....#.#..#..#.#..#.#...
                        #..#..###.#..#.####.#..#..###..##..####
                             """, "input-day11-2019.txt")); // "AGALRGJE" represented as ASCII art
    }
}
