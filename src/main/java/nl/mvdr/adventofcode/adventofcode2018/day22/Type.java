package nl.mvdr.adventofcode.adventofcode2018.day22;

/**
 * Type of a region.
 * 
 * @author Martijn van de Rijdt
 */
enum Type {
    
    /** The region of the cave is rocky. */
    ROCKY(0, '.'),
    /** The region of the cave is narrow. */
    NARROW(2, '|'),
    /** The region of the cave is wet. */
    WET(1, '=');
    
    private final int riskLevel;
    
    private final char representation;
    
    Type(int riskLevel, char representation) {
        this.riskLevel = riskLevel;
        this.representation = representation;
    }
    
    int getRiskLevel() {
        return riskLevel;
    }
    
    char getRepresentation() {
        return representation;
    }
    
    /**
     * Determines the type of a region, based on its erosion level.
     * 
     * @param erosionLevel erosion level
     * @return region type
     */
    static Type getType(int erosionLevel) {
        int erosionLevelModulo3 = erosionLevel % 3;
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
