package nl.mvdr.adventofcode.adventofcode2024.day14;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    private final int width;
    private final int height;
    
    public Part1() {
        this(101, 103);
    }
    
    Part1(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var robots = lines.map(Robot::parse)
                .collect(Collectors.toSet());
        var space = new SpaceOutsideBathroom(width, height, robots);
        space = space.move(100);
        return space.safetyFactor();
    }

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day14-2024.txt");

        LOGGER.info(result);
    }
}
 