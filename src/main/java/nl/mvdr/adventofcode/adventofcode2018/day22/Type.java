package nl.mvdr.adventofcode.adventofcode2018.day22;

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
            throw new IllegalArgumentException("Unexpected erosion level: " + erosionLevel);
        }
        return result;
    }
}
