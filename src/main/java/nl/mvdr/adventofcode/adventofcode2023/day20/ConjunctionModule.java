package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Conjunction modules (prefix {@code &}) remember the type of the most recent
 * pulse received from each of their connected input modules; they initially
 * default to remembering a low pulse for each input. When a pulse is received,
 * the conjunction module first updates its memory for that input. Then, if it
 * remembers high pulses for all inputs, it sends a low pulse; otherwise, it
 * sends a high pulse.
 *
 * @author Martijn van de Rijdt
 */
record ConjunctionModule(String name, List<String> destinations, Map<String, PulseType> latestInputs)
        implements Module {

    static final String PREFIX = "&";
    
    /**
     * Convenience constructor for use when parsing the input.
     * 
     * This constructor does not initiate the map of latest inputs,
     * as the source modules may not yet be known during parsing.
     * These should be set later using the {@link #init(Set)} method.
     * 
     * @param name name of this conjunction module (excluding the ampersand)
     * @param destinations destinations of this conjunction module
     */
    ConjunctionModule(String name, List<String> destinations) {
        this(name, destinations, null);
    }
    
    /**
     * Initializes this conjunction module.
     * 
     * @param inputModules names of the modules which can send pulses to this one
     * @return initialized copy of this conjunction module
     */
    ConjunctionModule init(Set<String> inputModules) {
        var newLatestInputs = inputModules.stream()
                .collect(Collectors.toMap(Function.identity(), module -> PulseType.LOW));
        return new ConjunctionModule(name, destinations, newLatestInputs);
    }
    
    @Override
    public HandlePulseResult handlePulse(Pulse pulse) {
        Objects.requireNonNull(latestInputs, "Conjunction module " + name + " has not yet been initialized.");
        
        Map<String, PulseType> newLatestInputs = new HashMap<>(latestInputs);
        newLatestInputs.put(pulse.source(), pulse.type());
        
        var updatedModule = new ConjunctionModule(name, destinations, newLatestInputs);
        
        PulseType outgoingPulseType;
        if (newLatestInputs.values().stream().allMatch(PulseType.HIGH::equals)) {
            outgoingPulseType = PulseType.LOW;
        } else {
            outgoingPulseType = PulseType.HIGH;
        }
        var outgoingPulses = createOutgoingPulses(outgoingPulseType);
        
        return new HandlePulseResult(updatedModule, outgoingPulses);
    }
}
