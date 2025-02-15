package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LinesSolver;

/**
 * Common ancestor for {@link SlicePart1} and {@link SlicePart2}.
 *
 * @param <R> result type
 * 
 * @author Martijn van de Rijdt
 */
abstract class Slice<R> implements LinesSolver<R>{
    @Override
    public R solve(Stream<String> lines) {
        List<Claim> claims = lines
                // ignore empty lines (the last line in the file)
                .filter(Predicate.not(String::isBlank))
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
    protected abstract R solve(List<Claim> claims, Map<SquareInch, Set<Claim>> claimedFabric); 
}
