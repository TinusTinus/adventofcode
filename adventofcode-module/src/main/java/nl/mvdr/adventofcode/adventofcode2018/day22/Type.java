package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.util.Set;

/**
 * Type of a region.
 * 
 * @author Martijn van de Rijdt
 */
enum Type {
    
    /** The region of the cave is rocky. */
    ROCKY(0, Set.of(Equipment.CLIMBING_GEAR, Equipment.TORCH), '.'),
    /** The region of the cave is narrow. */
    NARROW(2, Set.of(Equipment.TORCH, Equipment.NEITHER), '|'),
    /** The region of the cave is wet. */
    WET(1, Set.of(Equipment.CLIMBING_GEAR, Equipment.NEITHER), '=');
    
    /** Risk level of regions of this type. */
    private final int riskLevel;
    
    /** Tools which can be used in regions of this type. */
    private final Set<Equipment> usableEquipment;
    
    /** Single-character representation of regions of this type. */
    private final char representation;
    
    Type(int riskLevel, Set<Equipment> usableEquipment, char representation) {
        this.riskLevel = riskLevel;
        this.usableEquipment = usableEquipment;
        this.representation = representation;
    }
    
    int getRiskLevel() {
        return riskLevel;
    }
    
    char getRepresentation() {
        return representation;
    }
    
    Set<Equipment> getUsableEquipment() {
        return usableEquipment;
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
