package nl.mvdr.adventofcode.adventofcode2018.day22;

/**
 * A region in the cave.
 * 
 * @author Martijn van de Rijdt
 */
class Region {
    
    // TODO computations lead to overflows; either use BigIntegers instead of longs, or see if we can use smaller numbers by using properties of modulo
    /** The geologic index of this region. */
    private final long geologicIndex;
    /** The type of this region. */
    private final Type type;
    
    /**
     * Constructor.
     * 
     * @param geologicIndex geologic index of this region
     * @param depth depth of the cave
     */
    Region(long geologicIndex, int depth) {
        super();
        
        this.geologicIndex = geologicIndex;
        
        long erosionLevel = (geologicIndex + depth) % 20183L;
        this.type = Type.getType(erosionLevel);
    }
    
    long getGeologicIndex() {
        return geologicIndex;
    }
    
    Type getType() {
        return type;
    }
}
