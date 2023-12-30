package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.z3.Context;
import com.microsoft.z3.Status;

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
            
            var solver = context.mkSolver();
            IntStream.range(0, hailstones.size())
                    .mapToObj(i -> rock.createEquation(hailstones.get(i), context, "time" + i))
                    .forEach(solver::add);
            
            if (solver.check() == Status.SATISFIABLE) {
                var model = solver.getModel();
                System.out.println(model.evaluate(rock.sumLocationCoordinates(context), false));
                
                result = 0L; // TODO
            } else {
                throw new IllegalStateException("Failed to solve.");
            }
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
 