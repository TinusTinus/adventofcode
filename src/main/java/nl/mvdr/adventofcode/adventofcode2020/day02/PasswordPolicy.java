package nl.mvdr.adventofcode.adventofcode2020.day02;

import org.apache.commons.lang3.StringUtils;

/**
 * Representation of a password policy.
 *
 * @param minimum lowest number of times the given letter must appear for the password to be valid
 * @param maximum highest number of times the given letter must appear for the password to be valid
 * @param letter letter
 * @author Martijn van de Rijdt
 */
record PasswordPolicy(int minimum, int maximum, char letter) {
    
    /**
     * Determines whether the given password satisfies this policy,
     * according to the rules from part 1 of the puzzle.
     * 
     * @param password password to check
     * @return whether the password is valid
     */
    boolean isValidPart1(String password) {
        int matches = StringUtils.countMatches(password, letter);
        return minimum <= matches && matches <= maximum;
    }
    
    /**
     * Determines whether the given password satisfies this policy,
     * according to the rules from part 2 of the puzzle.
     * 
     * @param password password to check
     * @return whether the password is valid
     */
    boolean isValidPart2(String password) {
        return (password.charAt(minimum - 1) == letter && password.charAt(maximum - 1) != letter) || 
                (password.charAt(minimum - 1) != letter && password.charAt(maximum - 1) == letter);
    }
}
