package nl.mvdr.adventofcode.adventofcode2023.day04;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/4">Scratchcards</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ScratchcardsPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScratchcardsPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        MultiSet<Scratchcard> scratchcards = lines.map(Scratchcard::parse)
                .collect(Collectors.toCollection(HashMultiSet::new));
        
        var maxCardNumber = scratchcards.size();
        
        for (var cardId = 1; cardId != maxCardNumber; cardId++) {
            var card = Scratchcard.findCard(scratchcards, cardId);
            var wins = card.countWins();
            var occurrences = scratchcards.getCount(card);
            IntStream.range(cardId + 1, cardId + 1 + wins)
                    .mapToObj(id -> Scratchcard.findCard(scratchcards, id))
                    .forEach(c -> scratchcards.add(c, occurrences));
        }
        
        return scratchcards.size();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new ScratchcardsPart2();

        var result = instance.solve("input-day04-2023.txt");

        LOGGER.info(result);
    }
}
 