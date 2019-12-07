package nl.mvdr.adventofcode.adventofcode2019.day06;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

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
     * Parses puzzle input into a celestial map.
     * 
     * @param lines lines from the puzzle input, where each line represents a direct orbit
     * @return map of celestial objects, indexed by their names
     */
    static Map<String, CelestialObject> parse(Stream<String> lines) {
        Map<String, CelestialObject> objects = new HashMap<>();
        
        lines.filter(Predicate.not(String::isBlank))
                .forEach(line -> {
                    String[] parts = line.split("\\)");
                    CelestialObject lhs = objects.computeIfAbsent(parts[0], name -> new CelestialObject());
                    CelestialObject rhs = objects.computeIfAbsent(parts[1], name -> new CelestialObject());
                    rhs.setOrbitedObject(lhs);
                });
        
        return objects;
    }
    
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
    
    Optional<CelestialObject> getOrbitedObject() {
        return orbitedObject;
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
