package nl.mvdr.adventofcode.adventofcode2019.day12;

import java.util.Objects;

import javax.annotation.processing.Generated;

import nl.mvdr.adventofcode.point.Axis3D;

/**
 * Holder class for a moon and a three-dimensional axis.
 * 
 * This is simply intended to serve as the key in a map.
 *
 * @author Martijn van de Rijdt
 */
class MoonAndAxis {
    private final Moon moon;
    private final Axis3D axis;
    
    /**
     * Constructor.
     * 
     * @param moon initial state of a moon
     * @param axis axis in three dimensions
     */
    MoonAndAxis(Moon moon, Axis3D axis) {
        super();
        this.moon = moon;
        this.axis = axis;
    }

    @Override
    @Generated("Eclipse")
    public int hashCode() {
        return Objects.hash(axis, moon);
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
        MoonAndAxis other = (MoonAndAxis) obj;
        return axis == other.axis && Objects.equals(moon, other.moon);
    }
}
