package nl.mvdr.adventofcode.adventofcode2024.day25;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;

public class Part1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public long solve(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toCollection(ArrayList::new));
        
        Set<Lock> locks = new HashSet<>();
        Set<Key> keys = new HashSet<>();
        
        while (!lines.isEmpty()) {
            if (lines.getFirst().startsWith("#")) {
                var lock = Lock.parse(lines.subList(0, 7));
                LOGGER.debug("Parsed a lock: {}", lock);
                locks.add(lock);
            } else {
                var key = Key.parse(lines.subList(0, 7));
                LOGGER.debug("Parsed a key: {}", key);
                keys.add(key);
            }
            
            lines = lines.subList(Math.min(8, lines.size()), lines.size());
        }
        
        return keys.stream()
                .flatMap(key -> locks.stream()
                        .filter(lock -> key.fits(lock)))
                .count();
    }
    
    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day25-2024.txt");

        LOGGER.info(result); // 59345 is too high!
    }
}
 