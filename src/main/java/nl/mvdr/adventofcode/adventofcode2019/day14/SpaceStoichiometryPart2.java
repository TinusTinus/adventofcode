package nl.mvdr.adventofcode.adventofcode2019.day14;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 14 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/14">Space Stoichiometry</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceStoichiometryPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceStoichiometryPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return the maximum amount of FUEL that can be produced with 1 trillion ORE
     */
    @Override
    public int solve(Stream<String> lines) {
        Set<Reaction> reactions = lines.filter(Predicate.not(String::isBlank)).map(Reaction::parse)
                .collect(Collectors.toSet());
        LOGGER.debug("Reactions: {}", reactions);

        Map<String, Long> requiredComponents = new HashMap<>();
        requiredComponents.put("FUEL", Long.valueOf(1L));
        
        Map<String, Long> leftovers = new HashMap<>();
        leftovers.put("ORE", Long.valueOf(1000000000000L));
        
        Optional<Entry<String, Long>> requiredComponent = requiredComponents.entrySet().stream().findFirst();
        while (requiredComponent.isPresent()) {
            String requiredChemical = requiredComponent.orElseThrow().getKey();
            long requiredQuantity = requiredComponent.orElseThrow().getValue().longValue();
            
            // See if we can use any leftovers to satisfy (part of) this requirement
            long leftover = leftovers.getOrDefault(requiredChemical, Long.valueOf(0L)).longValue();
            if (0L < leftover) {
                LOGGER.debug("Using leftover {}", requiredChemical);
                // Take from leftovers.
                leftovers.put(requiredChemical, Long.valueOf(leftover - 1L));
                requiredComponents.put(requiredChemical, Long.valueOf(requiredQuantity - 1));
            } else {
                // Find the reaction which produces this chemical. This must be unique.
                Reaction reaction = reactions.stream()
                        .filter(r -> requiredChemical.equals(r.getOutput().getChemical()))
                        .findFirst()
                        .orElseThrow();
                LOGGER.debug("Applying reaction: {}", reaction);
                
                for (Component input : reaction.getInput()) {
                    requiredComponents.merge(input.getChemical(),
                            Long.valueOf(input.getQuantity()),
                            (i, j) -> Long.valueOf(i.longValue() + j.longValue()));
                }
                
                if (requiredQuantity <= reaction.getOutput().getQuantity()) {
                    requiredComponents.remove(requiredChemical);
                } else {
                    requiredComponents.put(requiredChemical, Long.valueOf(requiredQuantity - reaction.getOutput().getQuantity()));
                }
                
                if (requiredQuantity < reaction.getOutput().getQuantity()) {
                    leftovers.merge(requiredChemical,
                            Long.valueOf(reaction.getOutput().getQuantity() - requiredQuantity),
                            (i, j) -> Long.valueOf(i.intValue() + j.intValue()));
                }
                
                LOGGER.debug("Remaining required chemicals: {}", requiredComponents);
                LOGGER.debug("Leftovers: {}", leftovers);
            }
            
            // Find the next required component.
            requiredComponent = requiredComponents.entrySet()
                    .stream()
                    .filter(entry -> 0L < entry.getValue().longValue())
                    .findFirst();
        }

        return requiredComponents.get("ORE").intValue();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpaceStoichiometryPart2 instance = new SpaceStoichiometryPart2();

        String result = instance.solve("input-day14-2019.txt");

        LOGGER.info(result);
    }
}
 