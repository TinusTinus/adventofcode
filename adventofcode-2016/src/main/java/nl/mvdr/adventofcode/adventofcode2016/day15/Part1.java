package nl.mvdr.adventofcode.adventofcode2016.day15;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.microsoft.z3.Context;
import com.microsoft.z3.Status;

import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part1 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public String solve(Stream<String> lines) {
        try (var context = new Context()) {
            var time = context.mkIntConst("time");
            
            var solver = context.mkSolver();
            
            solver.add(context.mkLt(context.mkInt(-1), time));
            lines.map(Disc::parse)
                    .map(disc -> disc.createEquation(context, time))
                    .forEach(solver::add);
            
            if (solver.check() != Status.SATISFIABLE) {
                throw new IllegalStateException("Failed to solve: " + solver);
            }
            
            var model = solver.getModel();
            var result = model.evaluate(time, false);
            return result.toString();
        }
    }

    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day15.txt");

        LOGGER.info(result);
    }
}
