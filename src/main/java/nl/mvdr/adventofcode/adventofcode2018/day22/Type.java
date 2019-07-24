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
}
