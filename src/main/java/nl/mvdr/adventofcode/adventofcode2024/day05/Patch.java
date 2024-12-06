package nl.mvdr.adventofcode.adventofcode2024.day05;

import java.util.List;
import java.util.stream.Stream;

record Patch(List<Integer> pages) {
	
	static Patch parse(String line) {
		var pages = Stream.of(line.split(","))
				.map(Integer::valueOf)
				.toList();
		return new Patch(pages);
	}
}
