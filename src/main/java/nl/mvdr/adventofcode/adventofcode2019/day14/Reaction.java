package nl.mvdr.adventofcode.adventofcode2019.day14;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A chemical reaction.
 * 
 * Every reaction turns some quantities of specific input chemicals into some
 * quantity of an output chemical.
 *
 * @author Martijn van de Rijdt
 */
class Reaction {
    private final Set<Component> input;
    
    private final Component output;

    /**
     * Parses the text representation of a chemical reaction.
     * 
     * @param text textual representation of a reaction, for example: "7 A, 1 E => 1 FUEL"
     * @return reaction
     */
    static Reaction parse(String text) {
        String[] parts = text.split(" => ");
        
        Component output = Component.parse(parts[1]);
        
        Set<Component> input = Stream.of(parts[0].split(", "))
                .map(Component::parse)
                .collect(Collectors.toSet());
        
        return new Reaction(input, output);
    }
    
    /**
     * Constructor.
     * 
     * @param input input components
     * @param output output component
     */
    private Reaction(Set<Component> input, Component output) {
        super();
        this.input = input;
        this.output = output;
    }
    
    Set<Component> getInput() {
        return input;
    }
    
    Component getOutput() {
        return output;
    }
    
    @Override
    public String toString() {
        return input.stream().map(Object::toString).collect(Collectors.joining(", ")) + " => " + output;
    }
}
