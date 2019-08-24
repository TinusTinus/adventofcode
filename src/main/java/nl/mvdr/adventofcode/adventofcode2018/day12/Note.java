package nl.mvdr.adventofcode.adventofcode2018.day12;

/**
 * A note, containing a rule for determining whether a pot will have a plant in it during the next generation.
 *
 * @author Martijn van de Rijdt
 */
class Note {
    
    private final boolean farLeft;
    private final boolean left;
    private final boolean current;
    private final boolean right;
    private final boolean farRight;
    private final boolean next;
    
    /**
     * Parses the string representation of a note.
     * 
     * @param note string representation of a note, of the form LLCRR => N
     * @return note
     */
    static Note parse(String note) {
        boolean farLeft = toBoolean(note.charAt(0));
        boolean left = toBoolean(note.charAt(1));
        boolean current = toBoolean(note.charAt(2));
        boolean right = toBoolean(note.charAt(3));
        boolean farRight = toBoolean(note.charAt(4));
        
        boolean next = toBoolean(note.charAt(9));
        
        return new Note(farLeft, left, current, right, farRight, next);
    }
    
    /**
     * Converts a character representation of a pot to a boolean.
     * 
     * @param c character representation of a pot: # in case of a plant, . otherwise
     * @return whether this pot contains a plant
     */    
    private static boolean toBoolean(char c) {
        boolean result;
        if (c == PlantConstants.PLANT) {
            result = true;
        } else if (c == PlantConstants.NO_PLANT) {
            result = false;
        } else {
            throw new IllegalArgumentException("Unexpected character: " + c);
        }
        return result;
    }
    
    /**
     * Converts a boolean value to its character representation.
     * 
     * @param b boolean indicating whether a pot contains a plant
     * @return # in case of a plant, . otherwise
     */
    private static char toChar(boolean b) {
        char result;
        if (b) {
            result = PlantConstants.PLANT;
        } else {
            result = PlantConstants.NO_PLANT;
        }
        return result;
    }
    
    /**
     * Constructor.
     * 
     * @param farLeft precondition: whether the pot two spaces left of the current pot contains a plant (the first L in LLCRR => N)
     * @param left precondition: whether the pot directly left of the current pot contains a plant (the second L in LLCRR => N)
     * @param current precondition: whether the current pot contains a plant (C in LLCRR => N)
     * @param right precondition: whether the pot directly right of the current pot contains a plant (the first R in LLCRR => N)
     * @param farRight precondition: whether the pot two spaces right of the current pot contains a plant (the second R in LLCRR => N)
     * @param next the next value for the current pot, if the precondition applies (N in LLCRR => N)
     */
    private Note(boolean farLeft, boolean left, boolean current, boolean right, boolean farRight, boolean next) {
        super();
        
        this.farLeft = farLeft;
        this.left = left;
        this.current = current;
        this.right = right;
        this.farRight = farRight;
        
        this.next = next;
    }

    /**
     * Checks whether this note has the given precondition.
     * 
     * @param fl whether the pot two spaces left of the current pot contains a plant
     * @param l whether the pot directly left of the current pot contains a plant
     * @param c whether the current pot contains a plant
     * @param r whether the pot directly right of the current pot contains a plant
     * @param fr whether the pot two spaces right of the current pot contains a plant
     * @return whether this note matches the given precondition
     */
    boolean hasPrecondition(boolean fl, boolean l, boolean c, boolean r, boolean fr) {
        return this.farLeft == fl
                && this.left == l
                && this.current == c
                && this.right == r
                && this.farRight == fr;
    }
    
    /** @return whether the current pot will contain a plant in the next generation, if the precondition applies */
    public boolean isNext() {
        return next;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(toChar(farLeft));
        builder.append(toChar(left));
        builder.append(toChar(current));
        builder.append(toChar(right));
        builder.append(toChar(farRight));
        builder.append(" => ");
        builder.append(toChar(next));
        return builder.toString();
    }
}
