package nl.mvdr.adventofcode.adventofcode2022.day13;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/13">Distress Signal</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DistressSignalPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistressSignalPart2.class);
    
    @Override
    public int solve(Stream<String> lines) {
        var pairPackets = PacketPair.parse(lines.toList())
                .stream()
                .flatMap(pair -> Stream.of(pair.leftPacket(), pair.rightPacket()));
        var dividers = Stream.of("[[2]]", "[[6]]")
                .map(PacketValue::parse)
                .toList();
        var sortedPackets = Stream.concat(pairPackets, dividers.stream())
                .sorted()
                .toList();
        return dividers.stream()
                .mapToInt(sortedPackets::indexOf)
                .map(index -> index + 1)
                .reduce((i, j) -> i * j)
                .orElseThrow();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new DistressSignalPart2();

        var result = instance.solve("input-day13-2022.txt");

        LOGGER.info(result);
    }
}
 