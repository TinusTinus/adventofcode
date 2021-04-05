package nl.mvdr.adventofcode.adventofcode2020.day23;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CrabCupsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CrabCupsPart1Test extends SolverTest<CrabCupsPart1> {

    /** Constructor. */
    public CrabCupsPart1Test() {
        super(CrabCupsPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0Turns() {
        assertSolution(new CrabCupsPart1(0), "25467389", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1Turn() {
        assertSolution(new CrabCupsPart1(1), "54673289", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2Turns() {
        assertSolution(new CrabCupsPart1(2), "32546789", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3Turns() {
        assertSolution(new CrabCupsPart1(3), "34672589", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4Turns() {
        assertSolution(new CrabCupsPart1(4), "32584679", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5Turns() {
        assertSolution(new CrabCupsPart1(5), "36792584", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6Turns() {
        assertSolution(new CrabCupsPart1(6), "93672584", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample7Turns() {
        assertSolution(new CrabCupsPart1(7), "92583674", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample8Turns() {
        assertSolution(new CrabCupsPart1(8), "58392674", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample9Turns() {
        assertSolution(new CrabCupsPart1(9), "83926574", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample10Turns() {
        assertSolution(new CrabCupsPart1(10), "92658374", "example-day23-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution("67384529", "example-day23-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("32897654", "input-day23-2020.txt");
    }
}
