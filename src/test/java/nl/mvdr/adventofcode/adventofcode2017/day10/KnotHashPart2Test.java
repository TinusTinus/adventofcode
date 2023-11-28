package nl.mvdr.adventofcode.adventofcode2017.day10;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("a2582a3a0e66e6e86e3812dcb672a272", "example-day10-2017-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("33efeb34ea91902bb2f59c9920caa6cd", "example-day10-2017-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        testSolution("3efbe78a8d82f29979031a4aa0b16a9d", "example-day10-2017-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        testSolution("63960835bcdc130f0b66d7ff4f6a5a8e", "example-day10-2017-4.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("899124dac21012ebc32e2f4d11eaec55", "input-day10-2017.txt");
    }
}
