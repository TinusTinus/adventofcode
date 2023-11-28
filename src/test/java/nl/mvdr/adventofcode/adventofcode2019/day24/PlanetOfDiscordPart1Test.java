package nl.mvdr.adventofcode.adventofcode2019.day24;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PlanetOfDiscordPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PlanetOfDiscordPart1Test extends SolverTest<PlanetOfDiscordPart1> {

    /** Constructor. */
    public PlanetOfDiscordPart1Test() {
        super(PlanetOfDiscordPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        testSolution("2129920", "example-day24-2019-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        testSolution("2129920", "example-day24-2019-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        testSolution("2129920", "example-day24-2019-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        testSolution("2129920", "example-day24-2019-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        testSolution("2129920", "example-day24-2019-4.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5() {
        testSolution("2129920", "example-day24-2019-5.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("28778811", "input-day24-2019.txt"); 
    }
}
