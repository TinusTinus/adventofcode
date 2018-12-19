package nl.mvdr.adventofcode.adventofcode2018.day01;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Solution to the day 1 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/1">Chronal Calibration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibration {

	/**
	 * Solver method.
	 * 
	 * @param filename filename of the input file (on the classpath)
	 * @return solution to the puzzle for the given input
	 */
	public String solve(String filename) {
		String result;
		try {
			URL url = getClass().getClassLoader().getResource(filename);
			if (url == null) {
				throw new IllegalArgumentException("Unable to open file: " + filename);
			}
			Path inputFilePath = Paths.get(url.toURI());
			
			int sum = Files.lines(inputFilePath)
					// ignore empty lines (the last line in the file)
					.filter(Objects::nonNull)
					.mapToInt(Integer::parseInt)
					.sum();
			
			result = "" + sum;
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Unable to open file: " + filename, e);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		return result;
	}

	/**
	 * Main method.
	 * 
	 * @param args commandline arguments; these are ignored
	 */
	public static void main(String[] args) {
		ChronalCalibration instance = new ChronalCalibration();
		
		String result = instance.solve("input-day01-2018.txt");
		
		System.out.println(result);
	}

}
