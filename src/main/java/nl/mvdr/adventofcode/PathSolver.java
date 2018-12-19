package nl.mvdr.adventofcode;

import java.io.IOException;
import java.io.UncheckedIOException;
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
			URL url = getClass().getClassLoader().getResource(inputfile);
			if (url == null) {
				throw new IllegalArgumentException("Unable to open file: " + inputfile);
			}
			Path inputFilePath = Paths.get(url.toURI());
			
			result = solve(inputFilePath);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Unable to open file: " + inputfile, e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		return result;
	}

	/**
	 * Solver method, which provides a {@link Path} to the input file.
	 * 
	 * Can be (for example) used in combination with {@link java.nio.file.Files#lines(Path)} to iterate over a stream of the text file's lines.
	 * 
	 * @param inputFilePath path of the input file 
	 * @return solution to the puzzle for the given input
	 * @throws IOException in case the file cannot be read
	 */
	String solve(Path inputFilePath) throws IOException;
}
