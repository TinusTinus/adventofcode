package nl.mvdr.adventofcode.adventofcode2020.day02;

/**
 * An entry in the list of passwords.
 *
 * @author Martijn van de Rijdt
 */
class PasswordEntry {
    
    private final PasswordPolicy policy;
    private final String password;
    
    /**
     * Parses a textual representation of a password entry.
     * 
     * @param line line from the puzzle input
     * @return password entry
     */
    static PasswordEntry parse(String line) {
        String[] parts = line.split(": ");
        String password = parts[1];
        
        String[] policyParts = parts[0].split(" ");
        char letter = policyParts[1].charAt(0);
        
        String[] boundsParts = policyParts[0].split("-");
        int minimum = Integer.parseInt(boundsParts[0]);
        int maximum = Integer.parseInt(boundsParts[1]);
        
        PasswordPolicy policy = new PasswordPolicy(minimum, maximum, letter);
        
        return new PasswordEntry(policy, password);
    }
    
    /**
     * Constructor.
     * 
     * @param policy password policy
     * @param password password value
     */
    private PasswordEntry(PasswordPolicy policy, String password) {
        super();
        this.policy = policy;
        this.password = password;
    }
    
    /**
     * @return whether this entry's password is valid
     */
    boolean isValid() {
        return policy.isValid(password);
    }

}
