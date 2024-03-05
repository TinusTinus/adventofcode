package nl.mvdr.adventofcode

abstract class FunctionSolverTest<R>(solverFunction: (Sequence<String>) -> R):
        SolverTest<FunctionSolver<R>>(FunctionSolver(solverFunction)) {
}