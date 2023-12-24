package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/24">Never Tell Me The Odds</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NeverTellMeTheOddsPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeverTellMeTheOddsPart1.class);

    private final BigDecimal testAreaMin;
    private final BigDecimal testAreaMax;
    
    /**
     * Constructor.
     *     
     * @param testAreaMin minimum value for both coordinates for the test area
     * @param testAreaMax maximum value for both coordinates for the test area
     */
    NeverTellMeTheOddsPart1(long testAreaMin, long testAreaMax) {
        super();
        this.testAreaMin = new BigDecimal(testAreaMin).setScale(BigPoint2D.SCALE);
        this.testAreaMax = new BigDecimal(testAreaMax).setScale(BigPoint2D.SCALE);
    }
    
    /**
     * Constructor.
     */
    public NeverTellMeTheOddsPart1() {
        this(200000000000000L, 400000000000000L);
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var hailstones = lines.map(Hailstone::parse)
                .map(Hailstone::to2D)
                .toList();
        return hailstones.stream()
                .flatMap(hailstone -> hailstones.stream()
                        .filter(otherHailstone -> otherHailstone != hailstone)
                        .map(otherHailstone -> hailstone.findPathIntersection(otherHailstone)))
                .filter(Optional::isPresent)
                .map(Optional::orElseThrow)
                .filter(intersection -> testAreaMin.compareTo(intersection.x()) <= 0)
                .filter(intersection -> testAreaMin.compareTo(intersection.y()) <= 0)
                .filter(intersection -> intersection.x().compareTo(testAreaMax) <= 0)
                .filter(intersection -> intersection.y().compareTo(testAreaMax) <= 0)
                .count() / 2; // Divide by 2 because we inspected each pair twice
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new NeverTellMeTheOddsPart1();

        var result = instance.solve("input-day24-2023.txt");

        LOGGER.info(result);
    }
}
 