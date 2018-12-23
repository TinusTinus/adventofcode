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
 * Common ancestor for {@link SlicePart1} and {@link SlicePart2}.
 *
 * @author Martijn van de Rijdt
 */
abstract class Slice implements PathSolver{
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
        
        return solve(claims, claimedFabric);
    }
    
    /**
     * Solver method.
     * 
     * @param claims all claims from the input
     * @param claimedFabric map containing, for every square inch of fabric, the set of claims to that particular square inch of fabric
     * @return puzzle solution
     */
    protected abstract String solve(List<Claim> claims, Map<SquareInch, Set<Claim>> claimedFabric); 
}
