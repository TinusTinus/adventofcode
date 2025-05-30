package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.z3.Context;
import com.microsoft.z3.Status;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/24">Never Tell Me The Odds</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NeverTellMeTheOddsPart2 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(NeverTellMeTheOddsPart2.class);

    @Override
    @SuppressWarnings("unchecked")
    public String solve(Stream<String> lines) {
        // We know that, for each hailstone, some timestamp t must exist so that:
        //   hailstone.position + t * hailstone.velocity = rock.position + t * rock.velocity
        // By using multiple hailstones, we can set up a system of equations,
        // and then solve for the rock's position.
        // Let's use Z3 to solve this system of equations.
        try (var context = new Context()) {
            var rock = Rock.create(context);
            var solver = context.mkSolver();
            lines.map(Hailstone::parse)
                    .map(hailstone -> rock.createEquation(hailstone, context))
                    .forEach(solver::add);
            LOGGER.debug("{}", solver);
            if (solver.check() != Status.SATISFIABLE) {
                throw new IllegalStateException("Failed to solve: " + solver);
            }
            var model = solver.getModel();
            var result = model.evaluate(rock.sumLocationCoordinates(context), false);
            return result.toString();
        }
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
 