package nl.mvdr.adventofcode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Assertions;

/**
 * Abstract superclass for unit tests of implementations of {@link Solver}.
 *
 * @author Martijn van de Rijdt
 * @param <S> specific solver class; must have a default constructor
 */
public abstract class SolverTest<S extends Solver> {
	/** Specific solver class; must have a default constructor. */
	private final Class<S> solverClass;

	/**
	 * Constructor.
	 * 
	 * @param solverClass specific solver class; must have a default constructor
	 */
	protected SolverTest(Class<S> solverClass) {
		super();

		this.solverClass = solverClass;
	}

	/**
	 * Creates a new solver instance, using the default constructor.
	 * 
	 * @return new solver instance
	 */
	private S instantiateSolver() {
		S result;

		try {
			Constructor<S> constructor = this.solverClass.getConstructor();
			result = constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new IllegalStateException("Unable to instantiate solver: " + solverClass);
		}

		return result;
	}

	/**
	 * Invokes the solver and checks that the given solution matches the expected result.
	 * 
	 * @param expectedSolution expected solution
	 * @param inputfile input file on the (test) classpath
	 */
	public void assertSolution(String expectedSolution, String inputfile) {
		S solver = instantiateSolver();

		String result = solver.solve(inputfile);

		Assertions.assertEquals(expectedSolution, result);
	}
}
