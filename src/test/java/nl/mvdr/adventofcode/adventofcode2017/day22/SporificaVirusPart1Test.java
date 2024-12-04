package nl.mvdr.adventofcode.adventofcode2017.day22;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SporificaVirusPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SporificaVirusPart1Test extends SolverTest<SporificaVirusPart1> {

    /** Constructor. */
    public SporificaVirusPart1Test() {
        super(SporificaVirusPart1.class);
    }
    
    /** Test case with 0 bursts. No nodes could possibly be infected in this case. */
    @Test
    public void testExample0Bursts() {
        assertSolution(new SporificaVirusPart1(0), "0", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1Burst() {
        assertSolution(new SporificaVirusPart1(1), "1", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2Bursts() {
        assertSolution(new SporificaVirusPart1(2), "1", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3Bursts() {
        assertSolution(new SporificaVirusPart1(3), "2", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4Bursts() {
        assertSolution(new SporificaVirusPart1(4), "3", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5Bursts() {
        assertSolution(new SporificaVirusPart1(5), "4", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6Bursts() {
        assertSolution(new SporificaVirusPart1(6), "5", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample7Bursts() {
        assertSolution(new SporificaVirusPart1(7), "5", "example-day22-2017.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample70Bursts() {
        assertSolution(new SporificaVirusPart1(70), "41", "example-day22-2017.txt");
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5587", "example-day22-2017.txt"));
    }
}
