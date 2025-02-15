package nl.mvdr.adventofcode.adventofcode2019.day14;

import java.util.Comparator;
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
     * Computes the amount of ORE required to create the desired amount of FUEL.
     * 
     * @param required required amount of FUEL
     * @param reactions available alchemical reactions
     * @return required amount of ORE
     */
    static long computeRequiredOreForFuel(int requiredFuel, Set<Reaction> reactions) {
        Map<String, Long> requiredComponents = new HashMap<>();
        requiredComponents.put("FUEL", Long.valueOf(requiredFuel));
        
        Map<String, Long> leftovers = new HashMap<>();
        
        Optional<Entry<String, Long>> requiredComponent = requiredComponents.entrySet().stream().findFirst();
        while (requiredComponent.isPresent()) {
            String requiredChemical = requiredComponent.orElseThrow().getKey();
            long requiredQuantity = requiredComponent.orElseThrow().getValue().longValue();
            
            // See if we can use any leftovers to satisfy (part of) this requirement
            long leftover = leftovers.getOrDefault(requiredChemical, Long.valueOf(0L)).longValue();
            
            if (requiredQuantity <= leftover) {
                LOGGER.debug("Using leftover {} to satisfy entire requirement", requiredChemical);
                requiredComponents.remove(requiredChemical);
                leftovers.put(requiredChemical, Long.valueOf(leftover - requiredQuantity));
            } else if (0 < leftover) {
                LOGGER.debug("Using leftover {}", requiredChemical);
                requiredComponents.put(requiredChemical, Long.valueOf(requiredQuantity - leftover));
                leftovers.remove(requiredChemical);
            } else {
                // Find the reaction which produces this chemical. This must be unique.
                Reaction reaction = reactions.stream()
                        .filter(r -> requiredChemical.equals(r.getOutput().getChemical()))
                        .findFirst()
                        .orElseThrow();
                LOGGER.debug("Applying reaction: {}", reaction);
                
                long times = Math.max(requiredQuantity / reaction.getOutput().getQuantity(), 1L);
                
                for (Component input : reaction.getInput()) {
                    requiredComponents.merge(input.getChemical(),
                            Long.valueOf(times * input.getQuantity()),
                            (i, j) -> Long.valueOf(i.longValue() + j.longValue()));
                }
                
                if (requiredQuantity <= times * reaction.getOutput().getQuantity()) {
                    requiredComponents.remove(requiredChemical);
                } else {
                    requiredComponents.put(requiredChemical, Long.valueOf(requiredQuantity - times * reaction.getOutput().getQuantity()));
                }
                
                if (requiredQuantity < times * reaction.getOutput().getQuantity()) {
                    leftovers.merge(requiredChemical,
                            Long.valueOf(times * reaction.getOutput().getQuantity() - requiredQuantity),
                            (i, j) -> Long.valueOf(i.longValue() + j.longValue()));
                }
            }
            
            LOGGER.debug("Remaining required chemicals: {}", requiredComponents);
            LOGGER.debug("Leftovers: {}", leftovers);
            
            // Find the next required component.
            requiredComponent = requiredComponents.entrySet()
                    .stream()
                    .filter(entry -> 0 < entry.getValue().longValue())
                    .filter(entry -> !entry.getKey().equals("ORE"))
                    .sorted(Comparator.<Entry<String, Long>, Long>comparing(Entry::getValue).reversed())
                    .findFirst();
        }

        return requiredComponents.getOrDefault("ORE", Long.valueOf(0L)).longValue();
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
