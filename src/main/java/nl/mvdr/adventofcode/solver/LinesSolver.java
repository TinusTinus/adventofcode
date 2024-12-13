package nl.mvdr.adventofcode.solver;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Convenience interface, where puzzle input is presented as a stream of strings.
 * 
 * @param <R> result type
 *
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
public interface LinesSolver<R> extends Solver {

    @Override
    default String solve(String inputfile) {
        R result;
        Path inputFilePath = toPath(getClass(), inputfile);
        try (Stream<String> lines = Files.lines(inputFilePath)) {
            result = solve(lines);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return "" + result;
    }

    /**
     * Helper method, to create a {@link Path} based on a filename.
     * 
     * @param clazz class to use to access the class loader
     * @param inputfile filename of the input file (on the classpath) 
     * @return path
     */
    public static Path toPath(Class<?> clazz, String inputfile) {
        URL url = clazz.getClassLoader().getResource(inputfile);
        if (url == null) {
            throw new IllegalArgumentException("Unable to open file: " + inputfile);
        }
        
        URI uri;
        try {
            uri = url.toURI();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to open file: " + inputfile, e);
        }
        
        return Paths.get(uri);
    }

    /**
     * Solver method.
     * 
     * Note that the last line from the input is usually empty (input files typically end with a newline).
     * 
     * @param lines stream of strings, each of which corresponds to a line from the input
     * @return solution to the puzzle for the given input
     */
    R solve(Stream<String> lines);
}
