package nl.mvdr.adventofcode

abstract class FunctionSolverTest<R>(solverFunction: (List<String>) -> R):
        SolverTest<FunctionSolver<R>>(FunctionSolver(solverFunction)) {
}