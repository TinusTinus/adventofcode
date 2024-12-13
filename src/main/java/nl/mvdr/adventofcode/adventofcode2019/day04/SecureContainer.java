package nl.mvdr.adventofcode.adventofcode2019.day04;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to the day 4 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/4">Secure Container</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class SecureContainer implements LongSolver {

    /**
     * {@inheritDoc}
     * 
     * @return how many different passwords within the range meet the criteria
     */
    @Override
    public long solve(Stream<String> lines) {
        String password = lines.findFirst().orElseThrow();
        String[] parts = password.split("-");
        int lowerBound = Integer.parseInt(parts[0]);
        int upperBound = Integer.parseInt(parts[1]);
        
        return IntStream.range(lowerBound, upperBound)
                .parallel()
                .filter(this::validatePassword)
                .count();
    }
    
    /**
     * Validates the given password.
     * 
     * @param password password to validate; must be a 6-digit integer
     * @return whether the password matches the given criteria
     */
    abstract boolean validatePassword(int password);
}
