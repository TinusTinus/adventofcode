package nl.mvdr.adventofcode.adventofcode2018.day05;

/**
 * Helper class.
 *
 * @author Martijn van de Rijdt
 * 
 */
class Polymers {
    /** Private constructor to prevent singleton instantiation. */
    private Polymers() {
        super();
    }
    
    /**
     * Reduces the given polymer by reacting as much as possible.
     * 
     * @param polymer polymer
     * @return polymer after reaction
     */
    static String react(String polymer) {
        String result = polymer;
        
        boolean done = false;
        while (!done) {
            int i = 1;
            while (i != result.length() && !canReact(result.charAt(i - 1), result.charAt(i))) {
                i++;
            }
            
            if (i == result.length()) {
                done = true;
            } else {
                // A reaction is possible.
                result = result.substring(0, i - 1) + result.substring(i + 1);
            }
        }
        
        return result;
    }
    
    private static boolean canReact(char c0, char c1) {
        // must have the same type
        return Character.toLowerCase(c0) == Character.toLowerCase(c1)
                // and opposing polarities
                && Character.isUpperCase(c0) != Character.isUpperCase(c1);
    }
}
