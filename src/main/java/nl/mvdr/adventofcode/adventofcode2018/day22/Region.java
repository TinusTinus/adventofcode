package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.math.BigInteger;

/**
 * A region in the cave.
 * 
 * @author Martijn van de Rijdt
 */
class Region {
    
    /** The type of this region. */
    private final Type type;
    /** Erosion level. */
    private final BigInteger erosionLevel;
    
    /**
     * Constructor.
     * 
     * @param geologicIndex geologic index of this region
     * @param depth depth of the cave
     */
    Region(BigInteger geologicIndex, int depth) {
        super();
        
        this.erosionLevel = geologicIndex.add(BigInteger.valueOf(depth)).mod(BigInteger.valueOf(20_183L));
        this.type = Type.getType(erosionLevel);
    }
    
    Type getType() {
        return type;
    }
    
    BigInteger getErosionLevel() {
        return erosionLevel;
    }

    @Override
    public String toString() {
        return "Region [type=" + type + ", erosionLevel=" + erosionLevel + "]";
    }
}
