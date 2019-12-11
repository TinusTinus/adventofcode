package nl.mvdr.adventofcode.adventofcode2019.day11;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SpacePolicePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpacePolicePart2Test extends SolverTest<SpacePolicePart1> {

    private final Logger LOGGER = LoggerFactory.getLogger(SpacePolicePart2Test.class);
    
    /** Constructor. */
    public SpacePolicePart2Test() {
        super(SpacePolicePart1.class);
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        SpacePolicePart2 spacePolicePart2 = new SpacePolicePart2();
        
        String result = spacePolicePart2.solve("input-day11-2019.txt");
        
        // Inspect the log for the result; should be eight capital letters represented as ASCII art.
        LOGGER.info(result);
    }
}
