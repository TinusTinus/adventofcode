package nl.mvdr.adventofcode.adventofcode2024.day05;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

record Update(List<Integer> pages) {
	
	static Update parse(String line) {
		var pages = Stream.of(line.split(","))
				.map(Integer::valueOf)
				.toList();
		if (pages.size() % 2 != 1) {
		    throw new IllegalArgumentException("Number of pages is expected to be odd but was " + pages.size());
		}
		return new Update(pages);
	}

	boolean satisfies(Collection<Rule> rules) {
	    return rules.stream().allMatch(this::satisfies);
	}
	
	private boolean satisfies(Rule rule) {
	    int lhsIndex = pages.indexOf(Integer.valueOf(rule.lhs()));
	    int rhsIndex = pages.indexOf(Integer.valueOf(rule.rhs()));
	    
	    return lhsIndex == -1 || rhsIndex == -1 || lhsIndex < rhsIndex;
	}
	
	int middlePage() {
	    return pages.get(pages.size() / 2).intValue();
	}
}
