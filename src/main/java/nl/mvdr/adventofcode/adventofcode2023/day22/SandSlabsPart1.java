package nl.mvdr.adventofcode.adventofcode2023.day22;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/22">Sand Slabs</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SandSlabsPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SandSlabsPart1.class);

    @Override
    public long solve(Stream<String> lines) {
        var bricks = lines.map(Brick::parse).collect(Collectors.toSet());
        var settledBricks = Brick.settle(bricks);
        
        LOGGER.debug("Bricks have settled: {}", settledBricks);
        
        // For each brick which is not resting on the ground: determine which other bricks it is resting on.
        // (Note that bricks already resting on the ground can never fall.)
        var supportingBricks = settledBricks.stream()
                .filter(Predicate.not(Brick::isOnTheGround))
                .collect(Collectors.toMap(Function.identity(), brick -> brick.supportingBrickSet(settledBricks)));
        
        return settledBricks.parallelStream()
                .filter(brick -> canBeDisintegrated(brick, supportingBricks))
                .count();
    }
    
    /**
     * Counts how many bricks would fall if the given brick was disintegrated.
     * 
     * @param brick brick to be disintegrated
     * @param supportingBricks for each brick, not resting on the ground, which other bricks it is resting on
     * @return number of bricks which would fall
     */
    private static boolean canBeDisintegrated(Brick brick, Map<Brick, Set<Brick>> supportingBricks) {
        return !supportingBricks.values().contains(Set.of(brick));
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new SandSlabsPart1();

        var result = instance.solve("input-day22-2023.txt");

        LOGGER.info(result);
    }
}
 