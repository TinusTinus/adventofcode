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
public class SpaceStoichiometryPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceStoichiometryPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the minimum amount of ORE required to produce exactly 1 FUEL
     */
    @Override
    public int solve(Stream<String> lines) {
        Set<Reaction> reactions = lines.filter(Predicate.not(String::isBlank)).map(Reaction::parse)
                .collect(Collectors.toSet());

        LOGGER.debug("Reactions: {}", reactions);

        Map<String, Integer> requiredComponents = new HashMap<>();
        requiredComponents.put("FUEL", Integer.valueOf(1));
        
        Map<String, Integer> leftovers = new HashMap<>();
        
        Optional<Entry<String, Integer>> requiredComponent = requiredComponents.entrySet().stream().findFirst();
        while (requiredComponent.isPresent()) {
            String requiredChemical = requiredComponent.orElseThrow().getKey();
            int requiredQuantity = requiredComponent.orElseThrow().getValue().intValue();
            
            // See if we can use any leftovers to satisfy (part of) this requirement
            int leftover = leftovers.getOrDefault(requiredChemical, Integer.valueOf(0)).intValue();
            if (0 < leftover) {
                LOGGER.debug("Using leftover {}", requiredChemical);
                // Take from leftovers.
                leftovers.put(requiredChemical, Integer.valueOf(leftover - 1));
                requiredComponents.put(requiredChemical, Integer.valueOf(requiredQuantity - 1));
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
                
                LOGGER.debug("Remaining required chemicals: {}", requiredComponents);
                LOGGER.debug("Leftovers: {}", leftovers);
            }
            
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
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpaceStoichiometryPart1 instance = new SpaceStoichiometryPart1();

        String result = instance.solve("input-day14-2019.txt");

        LOGGER.info(result);
    }
}
 