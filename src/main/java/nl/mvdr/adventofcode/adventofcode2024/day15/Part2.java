package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2 extends WarehouseWoesSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    protected List<String> toList(Stream<String> linesStream) {
        return linesStream.map(line -> line.replace(".", ".."))
                .map(line -> line.replace("#", "##"))
                .map(line -> line.replace("O", "[]"))
                .toList();
    }
    
    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day15-2024.txt");

        LOGGER.info(result);
    }
}
 