package nl.mvdr.adventofcode.adventofcode2018.day20;

/**
 * Representation of a room on the map.
 * 
 * A representation of a room contains an indication whether it has a door on its East and South sides.
 * 
 * Note: doors are two-directional.
 * To find out whether there is a door on this room's North side, inspect whether a room directly North of this one exists and has a door on its South side.
 * To find out whether there is a door on this room's West side, inspect whether a room directly West of this one exists and has a door on its East side.
 * 
 * Note that this class is mutable. A new room initially has no doors; doors can be added during map construction.
 *
 * @author Martijn van de Rijdt
 */
class Room {
    /** Whether this room has a door on the East wall. */
    private boolean eastDoor;
    /** Whether this room has a door on the South wall. */
    private boolean southDoor;
    
    /**
     * Constructor.
     *
     * Creates a new room, without any doors.
     */
    Room() {
        super();
        this.eastDoor = false;
        this.southDoor = false;
    }
    
    /** @return whether this room has (is known to have) a door on its East side */
    boolean hasEastDoor() {
        return eastDoor;
    }
    
    /** @return whether this room has (is known to have) a door on its South side */
    boolean hasSouthDoor() {
        return southDoor;
    }

    /** Updates this room to indicate that it has a door on its East side. */
    void addEastDoor() {
        this.eastDoor = true;
    }
    
    /** Updates this room to indicate that it has a door on its South side. */
    void addSouthDoor() {
        this.southDoor = true;
    }
}
