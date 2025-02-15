package nl.mvdr.adventofcode.adventofcode2020.day02;

import org.apache.commons.lang3.StringUtils;

/**
 * Representation of a password policy.
 *
 * @param i first number in the password policy spec
 * @param j second number in the password policy spec
 * @param letter letter
 * @author Martijn van de Rijdt
 */
record PasswordPolicy(int i, int j, char letter) {
    
    /**
     * Determines whether the given password satisfies this policy,
     * according to the rules from part 1 of the puzzle.
     * 
     * @param password password to check
     * @return whether the password is valid
     */
    boolean isValidPart1(String password) {
        int matches = StringUtils.countMatches(password, letter);
        return i <= matches && matches <= j;
    }
    
    /**
     * Determines whether the given password satisfies this policy,
     * according to the rules from part 2 of the puzzle.
     * 
     * @param password password to check
     * @return whether the password is valid
     */
    boolean isValidPart2(String password) {
        return (password.charAt(i - 1) == letter && password.charAt(j - 1) != letter) || 
                (password.charAt(i - 1) != letter && password.charAt(j - 1) == letter);
    }
}
