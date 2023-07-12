package nl.mvdr.adventofcode.adventofcode2022.day13;

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
        var pairs = PacketPair.parse(lines.toList());
        var pairPackets = pairs.stream()
                .flatMap(pair -> Stream.of(pair.leftPacket(), pair.rightPacket()));
        var divider2 = PacketValue.parse("[[2]]");
        var divider6 = PacketValue.parse("[[6]]");
        var packets = Stream.concat(pairPackets, Stream.of(divider2, divider6))
                .sorted()
                .toList();
        var divider2Index = packets.indexOf(divider2) + 1;
        var divider6Index = packets.indexOf(divider6) + 1;
        return divider2Index * divider6Index;
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
 