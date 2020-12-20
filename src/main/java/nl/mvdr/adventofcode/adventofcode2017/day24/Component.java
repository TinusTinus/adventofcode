package nl.mvdr.adventofcode.adventofcode2017.day24;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of a component.
 *
 * @author Martijn van de Rijdt
 */
class Component {
    private final int port0;
    private final int port1;

    /**
     * Parses the given text file into a set of components.
     * 
     * @param lines puzzle input
     * @return set of components
     */
    static Set<Component> parse(Stream<String> lines) {
        return lines.filter(line -> !"".equals(line))
                .map(Component::parseComponent)
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses the given text into a component.
     * 
     * @param text textual representation of a component, for example: "0/1"
     * @return component
     */
    private static Component parseComponent(String text) {
        String[] parts = text.split("/");
        int port0 = Integer.parseInt(parts[0]);
        int port1 = Integer.parseInt(parts[1]);
        return new Component(port0, port1);
    }
    
    private Component(int port0, int port1) {
        super();
        this.port0 = port0;
        this.port1 = port1;
    }
    
    int getPort0() {
        return port0;
    }
    
    int getPort1() {
        return port1;
    }

    /** @return strength of this component */
    int strength() {
        return port0 + port1;
    }
    
    @Override
    public String toString() {
        return port0 + "/" + port1;
    }
}
