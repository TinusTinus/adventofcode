package nl.mvdr.adventofcode.adventofcode2024.day14;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part2 implements LinesSolver<Void> {

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
        
        for (int second = 0; second < 1_000; second++) {
            space = space.move(1);
            LOGGER.info("After {} seconds: {}", Integer.valueOf(second), space);
        }
        return null;
    }

    public static void main(String[] args) {
        var instance = new Part2();

        instance.solve("input-day14-2024.txt");
    }
}
 