package nl.mvdr.adventofcode.adventofcode2024.day15;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Part2 extends WarehouseWoesSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part2.class);

    @Override
    protected Stream<String> manipulate(Stream<String> lines) {
        return lines.map(line -> line.replace(".", ".."))
                .map(line -> line.replace("#", "##"))
                .map(line -> line.replace("O", "[]"))
                .map(line -> line.replace("@", "@."));
    }
    
    public static void main(String[] args) {
        var instance = new Part2();

        var result = instance.solve("input-day15-2024.txt");

        LOGGER.info(result);
    }
}
 