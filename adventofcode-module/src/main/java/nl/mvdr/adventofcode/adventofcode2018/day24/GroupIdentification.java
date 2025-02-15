package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.util.Comparator;
import java.util.Objects;

import javax.annotation.processing.Generated;

/**
 * Unique identification of a group.
 *
 * @author Martijn van de Rijdt
 */
public class GroupIdentification implements Comparable<GroupIdentification> {
    /** The army to which this group belongs. */
    private final Army army;
    /** Unique identification of this group within its army. */
    private final int id;

    /**
     * Constructor.
     * 
     * @param army army to which this group belongs
     * @param id unique identification of this group within its army
     */
    GroupIdentification(Army army, int id) {
        super();
        this.army = army;
        this.id = id;
    }
    
    Army getArmy() {
        return army;
    }
    
    int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return army + " group " + id;
    }

    @Override
    @Generated("Eclipse")
    public int hashCode() {
        return Objects.hash(army, Integer.valueOf(id));
    }

    @Override
    @Generated("Eclipse")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GroupIdentification other = (GroupIdentification) obj;
        return army == other.army && id == other.id;
    }
    
    @Override
    public int compareTo(GroupIdentification other) {
        return Comparator.comparing(GroupIdentification::getArmy)
                .thenComparing(GroupIdentification::getId)
                .compare(this, other);
    }
}