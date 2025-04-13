package nl.mvdr.adventofcode.adventofcode2016.day15;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.microsoft.z3.Context;
import com.microsoft.z3.Status;

import nl.mvdr.adventofcode.solver.LinesSolver;

class TimingIsEverythingSolver implements LinesSolver<String> {

    private final boolean addExtraDisc;
    
    TimingIsEverythingSolver(boolean addExtraDisc) {
        this.addExtraDisc = addExtraDisc;
    }
    
    @Override
    public String solve(Stream<String> lines) {
        var discs = lines.map(Disc::parse)
                .collect(Collectors.toCollection(ArrayList::new));
        if (addExtraDisc) {
            discs.add(new Disc(discs.getLast().number() + 1, 11, 0));
        }
        
        return solve(discs);
    }

    private String solve(ArrayList<Disc> discs) {
        try (var context = new Context()) {
            var time = context.mkIntConst("time");
            
            var solver = context.mkSolver();
            
            solver.add(context.mkLt(context.mkInt(-1), time));
            discs.stream()
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
}
