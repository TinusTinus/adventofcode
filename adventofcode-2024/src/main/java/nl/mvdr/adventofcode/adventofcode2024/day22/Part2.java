package nl.mvdr.adventofcode.adventofcode2024.day22;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public long solve(Stream<String> lines) {
        var secretNumberSequences = lines.mapToLong(Long::parseLong)
                .mapToObj(SecretNumber::new)
                .map(SecretNumber::generateAll)
                .collect(Collectors.toSet());
        
        return secretNumberSequences.stream()
                .flatMap(sequence -> sequence.stream()
                        .map(SecretNumber::priceChanges))
                .filter(priceChangeSequence -> priceChangeSequence.size() == 4)
                .distinct()
                .mapToLong(priceChangeSequence -> secretNumberSequences.stream()
                        .parallel()
                        .mapToLong(secretNumberSequence -> secretNumberSequence.stream()
                                .filter(secretNumber -> secretNumber.priceChanges().equals(priceChangeSequence))
                                .mapToLong(SecretNumber::price)
                                .findFirst()
                                .orElse(0L))
                        .sum())
                .max()
                .orElseThrow();
    }
    
    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day22-2024.txt");

        LOGGER.info(result);
    }
}
 