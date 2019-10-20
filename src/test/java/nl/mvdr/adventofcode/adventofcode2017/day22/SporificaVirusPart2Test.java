package nl.mvdr.adventofcode.adventofcode2017.day22;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SporificaVirusPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SporificaVirusPart2Test extends SolverTest<SporificaVirusPart2> {

    /** Constructor. */
    public SporificaVirusPart2Test() {
        super(SporificaVirusPart2.class);
    }
    
    /** Test case with 0 bursts. No nodes could possibly be infected in this case. */
    @Test
    public void testExample0Bursts() {
        assertSolution(new SporificaVirusPart2(0), "0", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1Burst() {
        assertSolution(new SporificaVirusPart2(1), "0", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2Bursts() {
        assertSolution(new SporificaVirusPart2(2), "0", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3Bursts() {
        assertSolution(new SporificaVirusPart2(3), "0", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4Bursts() {
        assertSolution(new SporificaVirusPart2(4), "0", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5Bursts() {
        assertSolution(new SporificaVirusPart2(5), "0", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6Bursts() {
        assertSolution(new SporificaVirusPart2(6), "0", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample7Bursts() {
        assertSolution(new SporificaVirusPart2(7), "1", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample70Bursts() {
        assertSolution(new SporificaVirusPart2(100), "26", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample10MillionBursts() {
        assertSolution("2511944", "example-day22-2017.txt");
    }
    
    /** Test case with 0 bursts. No nodes could possibly be infected in this case. */
    @Test
    public void test0BurstsActualInput() {
        assertSolution(new SporificaVirusPart2(0), "0", "input-day22-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day22-2017.txt");
    }
}
