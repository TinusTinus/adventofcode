package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.IntStream;
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
        
        var hailstones = lines.map(Hailstone::parse)
                .limit(3L) // three hailstones are enough to determine the solution
                .toList();
        
        long result;
        
        try (var context = new Context()) {
            var rock = Rock.create(context);
            
            var expression = IntStream.range(0, hailstones.size())
                    .mapToObj(i -> rock.createEquation(hailstones.get(i), context, "time" + i))
                    .reduce(context.mkTrue(), context::mkAnd);
            
            var solver = context.mkSolver();
            solver.add(expression);
            solver.check();
            
            var model = solver.getModel();
            System.out.println(model.evaluate(rock.position().x(), false));
            System.out.println(model.evaluate(rock.position().y(), false));
            System.out.println(model.evaluate(rock.position().z(), false));
            
            result = 0L; // TODO
        }
        
        return result;
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
 