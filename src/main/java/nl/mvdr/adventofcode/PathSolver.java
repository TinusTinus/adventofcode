package nl.mvdr.adventofcode;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;

/**
 * Convenience interface.
 * 
 * @param <R> result type
 *
 * @author Martijn van de Rijdt
 * 
 * @deprecated use {@link LinesSolver}, {@link IntSolver} or {@link LongSolver} instead,
 *     as they require less I/O fiddling
 */
@FunctionalInterface
@Deprecated // TODO remove
public interface PathSolver<R> extends Solver {

    @Override
    default String solve(String inputfile) {
        R result;
        try {
            Path inputFilePath = LinesSolver.toPath(getClass(), inputfile);
            result = solve(inputFilePath);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return "" + result;
    }

    /**
     * Solver method, which provides a {@link Path} to the input file.
     * 
     * Can be (for example) used in combination with
     * {@link java.nio.file.Files#lines(Path)} to iterate over a stream of the text
     * file's lines.
     * 
     * @param inputFilePath path of the input file
     * @return solution to the puzzle for the given input
     * @throws IOException in case the file cannot be read
     */
    R solve(Path inputFilePath) throws IOException;
}
