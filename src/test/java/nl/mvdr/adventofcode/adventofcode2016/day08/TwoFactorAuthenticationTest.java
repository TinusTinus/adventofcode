package nl.mvdr.adventofcode.adventofcode2016.day08;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link TwoFactorAuthentication}.
 *
 * @author Martijn van de Rijdt
 */
public class TwoFactorAuthenticationTest extends SolverTest<TwoFactorAuthentication> {

    /** Constructor. */
    public TwoFactorAuthenticationTest() {
        super(TwoFactorAuthentication.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("0", "example-day08-2016-0.txt"));
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution(new TwoFactorAuthentication(7, 3), "6", "example-day08-2016-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() {
        assertSolution(new TwoFactorAuthentication(7, 3), "6", "example-day08-2016-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() {
        assertSolution(new TwoFactorAuthentication(7, 3), "6", "example-day08-2016-3.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4() {
        assertSolution(new TwoFactorAuthentication(7, 3), "6", "example-day08-2016-4.txt");
    }
}
