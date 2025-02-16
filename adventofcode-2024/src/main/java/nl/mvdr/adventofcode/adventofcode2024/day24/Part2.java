package nl.mvdr.adventofcode.adventofcode2024.day24;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part2 implements LinesSolver<Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    public Void solve(Stream<String> lines) {
        var device = Device.parse(lines.toList());
        
        device = device.normalize();
        
        LOGGER.info("Normalized {}", device);
        
        // For those who know electronics:
        // the circuit should consist of a half-adder (for x00 and y00) and full adders (for pretty much everything else).
        // Inspect the log output to find the incorrect mappings.
        
        return null;
    }
    
    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day24-2024.txt");

        LOGGER.info(result);
    }
}
 