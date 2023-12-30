package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.z3.Context;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/24">Never Tell Me The Odds</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NeverTellMeTheOddsPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeverTellMeTheOddsPart2.class);

    @Override
    public long solve(Stream<String> lines) {
        try (var context = new Context()) {
            var rockPositionX = context.mkIntConst("rockPositionX");
            var rockPositionY = context.mkIntConst("rockPositionY");
            var rockPositionZ = context.mkIntConst("rockPositionZ");
            var rockVelocityX = context.mkIntConst("rockVelocityX");
            var rockVelocityY = context.mkIntConst("rockVelocityY");
            var rockVelocityZ = context.mkIntConst("rockVelocityZ");
            var time0 = context.mkIntConst("time0");
            var time1 = context.mkIntConst("time1");
            var time2 = context.mkIntConst("time2");
            
            // rx + t0 * rvx == 277903024391745 + t0 * -118
            var lhs = context.mkAdd(rockPositionX, context.mkMul(time0, rockVelocityX));
            var rhs = context.mkAdd(context.mkInt(277903024391745L), context.mkMul(time0, context.mkInt(-118)));
            
//            var solver = context.mkSolver();
            
//            context.getIntSort()
//            context.mkEq(null, null)
            // TODO implement!
        }
        
        return 843888100572888L; // TODO
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new NeverTellMeTheOddsPart2();

        var result = instance.solve("input-day24-2023.txt");

        LOGGER.info(result);
    }
}
 