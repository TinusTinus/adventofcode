package nl.mvdr.adventofcode.adventofcode2024.day05;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

	boolean satisfies(Set<Rule> rules) {
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
	
	Update sort(Set<Rule> rules) {
        Set<Integer> remainingPages = new HashSet<>(pages);

        // Sort these according to the rules
        List<Integer> sortedPages = new ArrayList<>();
        while (!remainingPages.isEmpty()) {
            Integer nextPage = remainingPages.stream()
                    .filter(page -> rules.stream().noneMatch(rule -> 
                        rule.rhs() == page.intValue() 
                            && !sortedPages.contains(Integer.valueOf(rule.lhs()))))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Unable to find next page. Sorted: " + sortedPages + ", remaining: " + remainingPages));
            remainingPages.remove(nextPage);
            sortedPages.add(nextPage);
        }
	    
	    return new Update(sortedPages);
	}
}
