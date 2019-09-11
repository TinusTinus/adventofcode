package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.util.List;

/**
 * Partner dance move.
 *
 * Makes the programs named A and B swap places.
 * 
 * @author Martijn van de Rijdt
 */
class Partner implements DanceMove {
    
    /** Name of the first program. */
    private final char a;
    /** Name of the second program. */
    private final char b;

    /**
     * Constructor.
     * 
     * @param a name of the first program
     * @param b name of the second program
     */
    Partner(char a, char b) {
        super();
        this.a = a;
        this.b = b;
    }
    
    @Override
    public List<Program> perform(List<Program> dancers) {
        int indexA = dancers.indexOf(new Program(a));
        int indexB = dancers.indexOf(new Program(b));
        
        Exchange exchange = new Exchange(indexA, indexB);
        
        return exchange.perform(dancers);
    }
    
    @Override
    public String toString() {
        return "p" + a + "/" + b;
    }
}
