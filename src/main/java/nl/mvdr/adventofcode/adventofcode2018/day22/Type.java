package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.math.BigInteger;

/**
 * Type of a region.
 * 
 * @author Martijn van de Rijdt
 */
enum Type {
    
    /** The region of the cave is rocky. */
    ROCKY(0),
    /** The region of the cave is narrow. */
    NARROW(1),
    /** The region of the cave is wet. */
    WET(2);
    
    private final int riskLevel;
    
    Type(int riskLevel) {
        this.riskLevel = riskLevel;
    }
    
    int getRiskLevel() {
        return riskLevel;
    }
    
    /**
     * Determines the type of a region, based on its erosion level.
     * 
     * @param erosionLevel erosion level
     * @return region type
     */
    static Type getType(BigInteger erosionLevel) {
        int erosionLevelModulo3 = erosionLevel.mod(BigInteger.valueOf(3L)).intValue();
        Type result;
        if (erosionLevelModulo3 == 0) {
            result = ROCKY;
        } else if (erosionLevelModulo3 == 1) {
            result = WET;
        } else if (erosionLevelModulo3 == 2) {
            result = NARROW;
        } else {
            // Should not occur: BigInteger.mod returns a nonnegative value.
            throw new IllegalStateException("Unexpected modulo value: " + erosionLevelModulo3);
        }
        return result;
    }
}
