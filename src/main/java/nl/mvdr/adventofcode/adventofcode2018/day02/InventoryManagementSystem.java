package nl.mvdr.adventofcode.adventofcode2018.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 2 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/1">Chronal Calibration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class InventoryManagementSystem implements PathSolver {
	
	@Override
	public String solve(Path inputFilePath) throws IOException {
		
		List<String> boxIds = Files.lines(inputFilePath)
				// ignore empty lines (the last line in the file)
				.filter(Objects::nonNull)
				.filter(line -> !line.isBlank())
				.collect(Collectors.toList());
		
		long boxIdsWith2OfAnyLetter = boxIds.stream()
				.filter(id -> containsExactlyNOfAnyLetter(id, 2L))
				.count();
		
		long boxIdsWith3OfAnyLetter = boxIds.stream()
				.filter(id -> containsExactlyNOfAnyLetter(id, 3L))
				.count();
		
		long checksum = boxIdsWith2OfAnyLetter * boxIdsWith3OfAnyLetter;
		
		return "" + checksum;
	}
	
	/**
	 * Given a box id, this method determines whether any of its letters occurs exactly the given number of times.
	 * 
	 * @param boxId box id (a sequence of letters)
	 * @param n expected number of times
	 * @return whether any of the letters in the box id occurs exactly the given number of times
	 */
	// default visibility for unit tests
	static boolean containsExactlyNOfAnyLetter(String boxId, long n) {
		// Count the number of occurences of each character.
		Map<Character, Long> characterCounts = boxId.chars()
				.mapToObj(c -> Character.valueOf((char) c))
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		
		return characterCounts.values().contains(Long.valueOf(n));
	}
	
	/**
	 * Main method.
	 * 
	 * @param args commandline arguments; these are ignored
	 */
	public static void main(String[] args) {
		InventoryManagementSystem instance = new InventoryManagementSystem();
		
		String result = instance.solve("input-day02-2018.txt");
		
		System.out.println(result);
	}
}
