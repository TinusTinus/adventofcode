package nl.mvdr.adventofcode;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Convenience interface.
 *
 * @author Martijn van de Rijdt
 */
public interface PathSolver extends Solver {

    @Override
    default String solve(String inputfile) {
        String result;
        try {
            Path inputFilePath = toPath(getClass(), inputfile);
            result = solve(inputFilePath);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return result;
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
    String solve(Path inputFilePath) throws IOException;
}
