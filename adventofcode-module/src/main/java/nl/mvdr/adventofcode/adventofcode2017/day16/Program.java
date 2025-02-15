package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.util.Objects;

import javax.annotation.processing.Generated;

/**
 * A program performing a dance.
 *
 * @author Martijn van de Rijdt
 */
class Program {
    /** Name of this program. Should be a lowercase letter. */
    private final char name;
    
    /**
     * Constructor.
     * 
     * @param name single lowercase letter name of this program
     */
    Program(char name) {
        super();
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "" + name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Character.valueOf(name));
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
        Program other = (Program) obj;
        return name == other.name;
    }
    
    
}
