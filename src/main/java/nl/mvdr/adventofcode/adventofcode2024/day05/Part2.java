package nl.mvdr.adventofcode.adventofcode2024.day05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

public class Part2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public long solve(Stream<String> linesStream) {
    	var lines = linesStream.toList();
    	var indexOfEmptyLine = lines.indexOf("");
    	if (indexOfEmptyLine < 0) {
    	    throw new IllegalArgumentException("Empty line not found in input: " + lines);
    	}
    	
    	var rules = lines.subList(0, indexOfEmptyLine)
    			.stream()
    			.map(Rule::parse)
    			.collect(Collectors.toSet());
    	
    	Comparator<Integer> pageComparator = pageComparator(rules);
    	
        return lines.subList(indexOfEmptyLine + 1, lines.size())
                .stream()
                .map(Update::parse)
                .filter(update -> !update.satisfies(rules))
                .map(update -> update.sort(pageComparator))
                .mapToInt(Update::middlePage)
                .sum();// TODO
    }
    
    private static Comparator<Integer> pageComparator(Set<Rule> rules) {
        
        // Find all page numbers in the rules
        Set<Integer> remainingPageNumbers = rules.stream()
                .flatMap(rule -> Stream.of(Integer.valueOf(rule.lhs()), Integer.valueOf(rule.rhs())))
                .collect(Collectors.toCollection(HashSet::new));
        
        List<Integer> sortedPages = new ArrayList<>();
        
        // The first page is the (only) one which never appears as the right-hand side of any rule.
        Integer firstPage = remainingPageNumbers.stream()
                .filter(page -> rules.stream().noneMatch(rule -> rule.rhs() == page.intValue()))
                .findFirst()
                .orElseThrow();
        remainingPageNumbers.remove(firstPage);
        sortedPages.add(firstPage);
        
        while (!remainingPageNumbers.isEmpty()) {
            Integer nextPage = remainingPageNumbers.stream()
                    .filter(page -> rules.stream().noneMatch(rule -> rule.rhs() == page.intValue() && !sortedPages.contains(Integer.valueOf(rule.lhs()))))
                    .findFirst()
                    .orElseThrow();
            remainingPageNumbers.remove(nextPage);
            sortedPages.add(nextPage);
        }
        
        return (lhs, rhs) -> sortedPages.indexOf(rhs) - sortedPages.indexOf(lhs);
    }

    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day05-2024.txt");

        LOGGER.info(result);
    }
}
 