package nl.mvdr.adventofcode.adventofcode2016.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;

import nl.mvdr.adventofcode.solver.SolverTest;

class TwoFactorAuthenticationTest extends SolverTest<TwoFactorAuthentication> {

    public TwoFactorAuthenticationTest() {
        super(TwoFactorAuthentication.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("0", "example-day08-0.txt"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "example-day08-1.txt", "example-day08-2.txt", "example-day08-3.txt", "example-day08-4.txt" })
    void testExample(String inputFile) {
        assertSolution(new TwoFactorAuthentication(7, 3), "6", inputFile);
    }
}
