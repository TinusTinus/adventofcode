package nl.mvdr.adventofcode.adventofcode2024.day02;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;

class RedNosedReportsSolver implements LongSolver {

    private final boolean problemDampener;
    
    public RedNosedReportsSolver(boolean problemDampener) {
        this.problemDampener = problemDampener;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        return lines.map(Report::parse)
                .filter(report -> report.isSafe(problemDampener))
                .count();
    }
}
 