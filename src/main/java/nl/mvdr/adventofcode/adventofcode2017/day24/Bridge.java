package nl.mvdr.adventofcode.adventofcode2017.day24;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.Generated;

/**
 * Components forming a bridge together.
 *
 * @author Martijn van de Rijdt
 */
class Bridge {
    /** Components making up this bridge. */
    private final List<Component> components;
    /** The required type of port for attaching the next component. */
    private final int requiredPort;

    /**
     * Creates a collection of all valid bridges with the given components.
     * 
     * @param components components
     * @return all valid bridges
     */
    static Set<Bridge> validBridges(Set<Component> components) {
        Set<Bridge> result = new HashSet<>();
        Set<Bridge> next = Set.of(new Bridge(List.of(), 0));
        while(result.addAll(next)) {
            next = next.parallelStream()
                    .flatMap(bridge -> components.stream().map(bridge::attach))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());
        }
        return result;
    }
    
    /**
     * Constructor.
     * 
     * @param components components
     * @param requiredPort required type of port for attaching the next component
     */
    private Bridge(List<Component> components, int requiredPort) {
        super();
        this.components = components;
        this.requiredPort = requiredPort;
    }
    
    /**
     * Attempts to attach the given component to this bridge.
     * 
     * @param component component to attach
     * @return a valid new bridge with the component attached, or empty in case this component cannot be attached 
     */
    Optional<Bridge> attach(Component component) {
        Optional<Bridge> result;
        if (components.contains(component)) {
            // Unable to use the same component twice
            result = Optional.empty();
        } else if (component.getPort0() == requiredPort) {
            result = Optional.of(new Bridge(append(component), component.getPort1()));
        } else if (component.getPort1() == requiredPort) {
            result = Optional.of(new Bridge(append(component), component.getPort0()));
        } else {
            // No fitting port
            result = Optional.empty();
        }
        return result;
    }

    /**
     * Creates a new list, consisting of this bridge's components, plus the given component.
     * 
     * @param component component to append
     * @return the given component, appended to this bridge's list of components
     */
    private List<Component> append(Component component) {
        List<Component> result = new ArrayList<>(components);
        result.add(component);
        return List.copyOf(result);
    }
    
    /** @return strength of this bridge */
    int strength() {
        return components.stream()
                .mapToInt(Component::strength)
                .sum();
    }
    
    @Override
    public String toString() {
        return components.stream()
                .map(Component::toString)
                .collect(Collectors.joining("--"));
    }

    @Override
    public int hashCode() {
        return Objects.hash(components);
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
        Bridge other = (Bridge) obj;
        return Objects.equals(components, other.components);
    }
}
