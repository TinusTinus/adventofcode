package nl.mvdr.adventofcode.solver

abstract class FunctionSolverTest<R>(solverFunction: (Sequence<String>) -> R):
        SolverTest<FunctionSolver<R>>(FunctionSolver(solverFunction)) {
}