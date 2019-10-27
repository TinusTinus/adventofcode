package nl.mvdr.adventofcode.adventofcode2017.day24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

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
     * @param inputFilePath path to the text file
     * @return set of components
     * @throws IOException in case the text file could not be read
     */
    static Set<Component> parse(Path inputFilePath) throws IOException {
        return Files.lines(inputFilePath)
                .filter(line -> !"".equals(line))
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
