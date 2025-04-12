package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Spin dance move.
 * 
 * Makes X programs move from the end to the front, but maintain their order otherwise.
 * (For example, s3 on abcde produces cdeab).
 *
 * @author Martijn van de Rijdt
 */
final class Spin implements DanceMove {
    /** The number of dancers to move. */
    private final int numberOfDancers;
    
    /**
     * Constructor.
     * 
     * @param numberOfDancers number of dancers to move
     */
    Spin(int numberOfDancers) {
        super();
        this.numberOfDancers = numberOfDancers;
    }
    
    @Override
    public List<Program> perform(List<Program> dancers) {
        LinkedList<Program> result = new LinkedList<>(dancers);
        
        IntStream.range(0, numberOfDancers).forEach(_ -> {
            Program dancer = result.pollLast();
            result.offerFirst(dancer);
        });
        
        return result;
    }
    
    @Override
    public String toString() {
        return "s" + numberOfDancers;
    }
}
