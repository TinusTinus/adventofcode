package nl.mvdr.adventofcode.adventofcode2019.day06;

import java.util.Optional;

/**
 * Representation of a celestial object.
 *
 * @author Martijn van de Rijdt
 */
class CelestialObject {

    /**
     * The celestial object which this object orbits. May be empty if this is the
     * Universal Center of Mass, or if the collection of celestial objects is strill
     * under construction.
     */
    private Optional<CelestialObject> orbitedObject;

    /**
     * Constructor for an object, which is either the Universal Center of Mass, or
     * is orbitining an as-of-yet unknown other object.
     * 
     * In the latter case, the orbited object should later be set using
     * {@link #setOrbitedObject(CelestialObject)}.
     */
    CelestialObject() {
        this.orbitedObject = Optional.empty();
    }

    /**
     * @param object the other celestial object which this object orbits
     */
    void setOrbitedObject(CelestialObject object) {
        this.orbitedObject.ifPresent(o -> {
            throw new IllegalStateException("Orbit already set.");
        });
        this.orbitedObject = Optional.of(object);
    }
    
    /** @return total number of orbits */
    int computeTotalOrbits() {
        int result;
        if (this.orbitedObject.isPresent()) {
            result = this.orbitedObject.get().computeTotalOrbits() + 1;
        } else {
            result = 0;
        }
        return result;
    }
}
