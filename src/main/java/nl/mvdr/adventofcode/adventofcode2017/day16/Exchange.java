package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.util.ArrayList;
import java.util.List;

/**
 * Exchange dance move.
 * 
 * Makes the programs at positions A and B swap places.
 *
 * @author Martijn van de Rijdt
 */
class Exchange implements DanceMove {
    /** First program. */
    private final int a;
    /** Second program. */
    private final int b;
    
    /**
     * Constructor.
     * 
     * @param a position of the first program
     * @param b position of the second program
     */
    Exchange(int a, int b) {
        super();
        this.a = a;
        this.b = b;
    }
    
    @Override
    public List<Program> perform(List<Program> dancers) {
        List<Program> result = new ArrayList<>(dancers);
        
        result.set(a, dancers.get(b));
        result.set(b, dancers.get(a));
        
        return result;
    }
    
    @Override
    public String toString() {
        return "x" + a + "/" + b;
    }

}
