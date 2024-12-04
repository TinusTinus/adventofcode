package nl.mvdr.adventofcode.adventofcode2017.day10;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link KnotHashPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class KnotHashPart2Test extends SolverTest<KnotHashPart2> {

    /** Constructor. */
    public KnotHashPart2Test() {
        super(KnotHashPart2.class);
    }

    /** Test case for {@link KnotHashPart2#inputLengths(String)}. */
    @SuppressWarnings("boxing")
    @Test
    public void testInputLengths() {
        String input = "1,2,3";
        KnotHashPart2 solver = new KnotHashPart2();
        
        List<Integer> lengths = solver.inputLengths(input);
        
        Assertions.assertEquals(List.of(49, 44, 50, 44, 51, 17, 31, 73, 47, 23), lengths);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("a2582a3a0e66e6e86e3812dcb672a272", "example-day10-2017-1.txt"),
                Arguments.of("33efeb34ea91902bb2f59c9920caa6cd", "example-day10-2017-2.txt"),
                Arguments.of("3efbe78a8d82f29979031a4aa0b16a9d", "example-day10-2017-3.txt"),
                Arguments.of("63960835bcdc130f0b66d7ff4f6a5a8e", "example-day10-2017-4.txt"));
    }
}
