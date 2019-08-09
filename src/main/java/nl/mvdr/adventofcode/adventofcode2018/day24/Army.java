package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.util.Set;

/**
 * Representation of an army.
 *
 * @author Martijn van de Rijdt
 */
class Army {
    /** Name of the army. */
    private final String name;
    
    /** Groups of units that make up this army. */
    private final Set<Group> groups;

    private Army(String name, Set<Group> groups) {
        super();
        this.name = name;
        this.groups = groups;
    }
    
}
