package nl.mvdr.adventofcode.adventofcode2018.day22;

/**
 * A region in the cave.
 * 
 * @author Martijn van de Rijdt
 */
class Region {
    
    /** The type of this region. */
    private final Type type;
    /** Erosion level. */
    private final int erosionLevel;
    
    /**
     * Constructor.
     * 
     * @param geologicIndex geologic index of this region
     * @param depth depth of the cave
     */
    Region(int geologicIndex, int depth) {
        super();
        
        this.erosionLevel = (geologicIndex + depth) % 20_183;
        this.type = Type.getType(erosionLevel);
    }
    
    Type getType() {
        return type;
    }
    
    int getErosionLevel() {
        return erosionLevel;
    }

    @Override
    public String toString() {
        return "Region [type=" + type + ", erosionLevel=" + erosionLevel + "]";
    }
}
