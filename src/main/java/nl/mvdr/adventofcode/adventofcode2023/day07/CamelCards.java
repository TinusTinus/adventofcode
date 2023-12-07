package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/7">Camel Cards</a>.
 *
 * @author Martijn van de Rijdt
 */
class CamelCards<C extends Card<C>> implements IntSolver {

    private final Class<C> cardClass;
    
    /**
     * Constructor.
     * 
     * @param cardClass concrete card class (either {@link Part1Card} or {@link Part2Card})
     */
    CamelCards(Class<C> cardClass) {
        this.cardClass = cardClass;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var sorted = lines.map(line -> HandAndBid.parse(line, cardClass))
                // Sort weak-to-strong
                .sorted()
                .toList();
        
        return IntStream.range(0, sorted.size())
                // rank * bid
                .map(i -> (i + 1) * sorted.get(i).bid())
                .sum();
    }
}
 