package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 3 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">No Matter How You Slice It</a>.
 *
 * @author Martijn van de Rijdt
 */
public class Slice implements PathSolver {

    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<Claim> claims = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .map(Claim::parse)
                .collect(Collectors.toList());
        
        Map<SquareInch, Set<Claim>> claimedFabric = new HashMap<>();
        
        for (Claim claim : claims) {
            for (SquareInch squareInch : claim.getFabric()) {
                claimedFabric.putIfAbsent(squareInch, new HashSet<>());
                claimedFabric.get(squareInch).add(claim);
            }
        }
        
        return "" + claimedFabric.values().stream()
                .filter(cs -> 2 <= cs.size())
                .count();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        Slice instance = new Slice();

        String result = instance.solve("input-day03-2018.txt");

        System.out.println(result);
    }
}
