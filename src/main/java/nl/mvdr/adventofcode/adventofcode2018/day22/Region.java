package nl.mvdr.adventofcode.adventofcode2018.day22;

/**
 * A region in the cave.
 * 
 * @author Martijn van de Rijdt
 */
class Region {
    
    /** The geologic index of this region. */
    private final int geologicIndex;
    /** The type of this region. */
    private final Type type;
    
    /**
     * Constructor.
     * 
     * @param geologicIndex geologic index of this region
     * @param depth depth of the cave
     */
    Region(int geologicIndex, int depth) {
        super();
        
        this.geologicIndex = geologicIndex;
        
        int erosionLevel = (geologicIndex + depth) % 20183;
        this.type = Type.getType(erosionLevel);
    }
    
    int getGeologicIndex() {
        return geologicIndex;
    }
    
    Type getType() {
        return type;
    }
}
