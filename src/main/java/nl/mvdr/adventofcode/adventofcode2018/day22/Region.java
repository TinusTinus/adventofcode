package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.math.BigInteger;

/**
 * A region in the cave.
 * 
 * @author Martijn van de Rijdt
 */
class Region {
    
    /** The geologic index of this region. */
    private final BigInteger geologicIndex;
    /** The type of this region. */
    private final Type type;
    
    /**
     * Constructor.
     * 
     * @param geologicIndex geologic index of this region
     * @param depth depth of the cave
     */
    Region(BigInteger geologicIndex, int depth) {
        super();
        
        this.geologicIndex = geologicIndex;
        
        BigInteger erosionLevel = geologicIndex.add(BigInteger.valueOf(depth)).mod(BigInteger.valueOf(20_183L));
        this.type = Type.getType(erosionLevel);
    }
    
    BigInteger getGeologicIndex() {
        return geologicIndex;
    }
    
    Type getType() {
        return type;
    }
}
