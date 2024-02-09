package nl.mvdr.adventofcode;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Abstract superclass for unit tests of implementations of {@link Solver}.
 *
 * @author Martijn van de Rijdt
 * @param <S> specific solver class; must have a default constructor
 */
public abstract class SolverTest<S extends Solver> {
    /** Supplies the solver. */
    private final Supplier<S> solverSupplier;

    /**
     * Constructor.
     *
     * @param solverSupplier supplier for obtaining a solver instance
     */
    private SolverTest(Supplier<S> solverSupplier) {
        super();
        this.solverSupplier = solverSupplier;
    }

    /**
     * Constructor.
     *
     * @param solver the solver to test
     */
    protected SolverTest(S solver) {
        this(() -> solver);
    }

    /**
     * Constructor.
     * 
     * @param solverClass specific solver class; must have a default constructor
     */
    protected SolverTest(Class<S> solverClass) {
        this(() -> instantiateSolver(solverClass));
    }

    /**
     * Creates a new solver instance, using the default constructor.
     *
     * @param <S> specific solver class; must have a default constructor
     * @param solverClass specific solver class
     * @return new solver instance
     */
    private static <S> S instantiateSolver(Class<S> solverClass) {
        try {
            return solverClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException
                 | SecurityException e) {
            throw new IllegalArgumentException("Unable to instantiate solver: " + solverClass, e);
        }
    }

    /**
     * Checks that the solver produces the expected solution for the given input.
     * 
     * @param expectedSolution expected solution
     * @param inputfile        input file on the (test) classpath
     */
    @ParameterizedTest
    @MethodSource
    public void testSolution(String expectedSolution, String inputfile) {
        S solver = solverSupplier.get();

        assertSolution(solver, expectedSolution, inputfile);
    }

    /**
     * Invokes the solver and checks that the given solution matches the expected
     * result.
     * 
     * @param solver           solver under test
     * @param expectedSolution expected solution
     * @param inputfile        input file on the (test) classpath
     */
    public void assertSolution(S solver, String expectedSolution, String inputfile) {
        String result = solver.solve(inputfile);

        Assertions.assertEquals(expectedSolution, result);
    }
}
