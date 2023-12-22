package nl.mvdr.adventofcode.adventofcode2023.day22;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/22">Sand Slabs</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SandSlabsPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SandSlabsPart2.class);

    @Override
    public int solve(Stream<String> lines) {
        var bricks = lines.map(Brick::parse).collect(Collectors.toSet());
        var settledBricks = Brick.settle(bricks);
        
        LOGGER.debug("Bricks have settled: {}", settledBricks);

        // For each brick which is not resting on the ground: determine which other bricks it is resting on.
        // (Note that bricks already resting on the ground can never fall.)
        var supportingBricks = settledBricks.stream()
                .filter(Predicate.not(Brick::isOnTheGround))
                .collect(Collectors.toMap(Function.identity(), brick -> brick.supportingBrickSet(settledBricks)));
        
        return settledBricks.stream()
                .mapToInt(brick -> SandSlabsPart2.countFallingBricks(brick, supportingBricks))
                .sum();
    }
    
    /**
     * Counts how many bricks would fall if the given brick was disintegrated.
     * 
     * @param brick brick to be disintegrated
     * @param supportingBricks for each brick, not resting on the ground, which other bricks it is resting on
     * @return number of bricks which would fall
     */
    private static int countFallingBricks(Brick brick, Map<Brick, Set<Brick>> supportingBricks) {
        Set<Brick> removedBricks = new HashSet<>();
        removedBricks.add(brick);
        
        Set<Brick> remainingBricks = new HashSet<>(supportingBricks.keySet());
        
        var done = false;
        while (!done) {
            var fallingBricks = remainingBricks.stream()
                    .filter(remainingBrick -> supportingBricks.get(remainingBrick).stream().allMatch(removedBricks::contains))
                    .collect(Collectors.toSet());
            remainingBricks.removeAll(fallingBricks);
            done = !removedBricks.addAll(fallingBricks);
        }
        
        return removedBricks.size() - 1; // do not count the disintegrated brick
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new SandSlabsPart2();

        var result = instance.solve("input-day22-2023.txt");

        LOGGER.info(result);
    }
}
 