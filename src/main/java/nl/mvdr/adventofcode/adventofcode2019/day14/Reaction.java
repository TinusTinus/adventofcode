package nl.mvdr.adventofcode.adventofcode2019.day14;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A chemical reaction.
 * 
 * Every reaction turns some quantities of specific input chemicals into some
 * quantity of an output chemical.
 *
 * @author Martijn van de Rijdt
 */
class Reaction {

    private static final Logger LOGGER = LoggerFactory.getLogger(Reaction.class);
    
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
     * Computes the amount of ORE required to create 1 FUEL.
     * 
     * @param reactions available alchemical reactions
     * @return required amount of ORE
     */
    static int computeRequiredOreForFuel(Set<Reaction> reactions) {
        return computeRequiredOreForFuel(reactions, new HashMap<>());
    }

    /**
     * Computes the amount of ORE required to create 1 FUEL.
     * 
     * @param reactions available alchemical reactions
     * @param leftover any available leftovers from a previous invocation of this method
     * @return required amount of ORE
     */
    static int computeRequiredOreForFuel(Set<Reaction> reactions, Map<String, Integer> leftovers) {
        Map<String, Integer> requiredComponents = new HashMap<>();
        requiredComponents.put("FUEL", Integer.valueOf(1));
        
        Optional<Entry<String, Integer>> requiredComponent = requiredComponents.entrySet().stream().findFirst();
        while (requiredComponent.isPresent()) {
            String requiredChemical = requiredComponent.orElseThrow().getKey();
            int requiredQuantity = requiredComponent.orElseThrow().getValue().intValue();
            
            // See if we can use any leftovers to satisfy (part of) this requirement
            int leftover = leftovers.getOrDefault(requiredChemical, Integer.valueOf(0)).intValue();
            
            if (requiredQuantity <= leftover) {
                LOGGER.debug("Using leftover {} to satisfy entire requirement", requiredChemical);
                requiredComponents.remove(requiredChemical);
                leftovers.put(requiredChemical, Integer.valueOf(leftover - requiredQuantity));
            } else if (0 < leftover) {
                LOGGER.debug("Using leftover {}", requiredChemical);
                requiredComponents.put(requiredChemical, Integer.valueOf(requiredQuantity - leftover));
                leftovers.remove(requiredChemical);
            } else {
                // Find the reaction which produces this chemical. This must be unique.
                Reaction reaction = reactions.stream()
                        .filter(r -> requiredChemical.equals(r.getOutput().getChemical()))
                        .findFirst()
                        .orElseThrow();
                LOGGER.debug("Applying reaction: {}", reaction);
                
                for (Component input : reaction.getInput()) {
                    requiredComponents.merge(input.getChemical(),
                            Integer.valueOf(input.getQuantity()),
                            (i, j) -> Integer.valueOf(i.intValue() + j.intValue()));
                }
                
                if (requiredQuantity <= reaction.getOutput().getQuantity()) {
                    requiredComponents.remove(requiredChemical);
                } else {
                    requiredComponents.put(requiredChemical, Integer.valueOf(requiredQuantity - reaction.getOutput().getQuantity()));
                }
                
                if (requiredQuantity < reaction.getOutput().getQuantity()) {
                    leftovers.merge(requiredChemical,
                            Integer.valueOf(reaction.getOutput().getQuantity() - requiredQuantity),
                            (i, j) -> Integer.valueOf(i.intValue() + j.intValue()));
                }
            }
            
            LOGGER.debug("Remaining required chemicals: {}", requiredComponents);
            LOGGER.debug("Leftovers: {}", leftovers);
            
            // Find the next required component.
            requiredComponent = requiredComponents.entrySet()
                    .stream()
                    .filter(entry -> 0 < entry.getValue().intValue())
                    .filter(entry -> !entry.getKey().equals("ORE"))
                    .findFirst();
        }

        return requiredComponents.get("ORE").intValue();
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
