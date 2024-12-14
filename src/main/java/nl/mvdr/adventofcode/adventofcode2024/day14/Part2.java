package nl.mvdr.adventofcode.adventofcode2024.day14;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part2 implements LinesSolver<Void> {

    private static final int INITIALISATION_SECONDS = 0;
    private static final int SECONDS_PER_ITERATION = 1;
    private static final int ITERATIONS = 10_000;

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    private final int width;
    private final int height;
    
    public Part2() {
        this(101, 103);
    }
    
    Part2(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public Void solve(Stream<String> lines) {
        var robots = lines.map(Robot::parse)
                .collect(Collectors.toSet());
        var space = new SpaceOutsideBathroom(width, height, robots);
        
        space = space.move(INITIALISATION_SECONDS);
        LOGGER.info(space.toString());
        for (int i = 1; i < ITERATIONS; i++) {
            space = space.move(SECONDS_PER_ITERATION);
            LOGGER.info(space.toString());
        }
        // Observe log output to find the tree.
        // Note: in my case I quickly noticed a pattern in the log output,
        // which allowed me to pick better values for INITIALISATION_SECONDS and SECONDS_PER_ITERATION (66 and 101 respectively).
        // I assume these are different depending on the input.
        return null;
    }

    public static void main(String[] args) {
        var instance = new Part2();

        instance.solve("input-day14-2024.txt");
    }
}
 