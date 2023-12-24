package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test cases for {@link Hailstone2D}.
 *
 * @author Martijn van de Rijdt
 */
public class Hailstone2DTest {
    
    /**
     * Test case for {@link Hailstone2D#findPathIntersection(Hailstone2D)}.
     */
    @ParameterizedTest
    @MethodSource
    public void testFindPathIntersection(String stone0String, String stone1String, Optional<BigPoint2D> expectedResult) {
        var stone0 = Hailstone.parse(stone0String).to2D();
        var stone1 = Hailstone.parse(stone1String).to2D();
        
        var result = stone0.findPathIntersection(stone1);
        var resultReversed = stone1.findPathIntersection(stone0);
                
        Assertions.assertEquals(result, resultReversed);
        
        Assertions.assertEquals(expectedResult, result); // TODO take precision into account
    }
    
    /**
     * @return arguments for {@link #testFindPathIntersection(Hailstone2D, Hailstone2D, Optional)}
     */
    private static Stream<Arguments> testFindPathIntersection() {
        return Stream.of(
                Arguments.of("18, 19, 22 @ -1, -1, -2",
                        "18, 19, 22 @ -1, -1, -2",
                        Optional.empty()), // equal
                Arguments.of("19, 13, 30 @ -2, 1, -2",
                        "18, 19, 22 @ -1, -1, -2",
                        Optional.of(new BigPoint2D(14.333, 15.333))),
                Arguments.of("19, 13, 30 @ -2, 1, -2",
                        "20, 25, 34 @ -2, -2, -4",
                        Optional.of(new BigPoint2D(11.667, 16.667))),
                Arguments.of("19, 13, 30 @ -2, 1, -2",
                        "12, 31, 28 @ -1, -2, -1",
                        Optional.of(new BigPoint2D(6.2, 19.4))),
                Arguments.of("19, 13, 30 @ -2, 1, -2",
                        "20, 19, 15 @ 1, -5, -3",
                        Optional.empty()), // crosses in the past
                Arguments.of("18, 19, 22 @ -1, -1, -2",
                        "20, 25, 34 @ -2, -2, -4",
                        Optional.empty()), // parallel
                Arguments.of("18, 19, 22 @ -1, -1, -2",
                        "12, 31, 28 @ -1, -2, -1",
                        Optional.of(new BigPoint2D(-6, -5))),
                Arguments.of("18, 19, 22 @ -1, -1, -2",
                        "20, 19, 15 @ 1, -5, -3",
                        Optional.empty()), // crosses in the past
                Arguments.of("20, 25, 34 @ -2, -2, -4",
                        "12, 31, 28 @ -1, -2, -1",
                        Optional.of(new BigPoint2D(-2, 3))),
                Arguments.of("20, 25, 34 @ -2, -2, -4",
                        "20, 19, 15 @ 1, -5, -3",
                        Optional.empty()), // crosses in the past
                Arguments.of("12, 31, 28 @ -1, -2, -1",
                        "20, 19, 15 @ 1, -5, -3",
                        Optional.empty())); // crosses in the past
    }
}
