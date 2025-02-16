package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Conjunction modules (prefix {@code &}) remember the type of the most recent
 * pulse received from each of their connected input modules; they initially
 * default to remembering a low pulse for each input. When a pulse is received,
 * the conjunction module first updates its memory for that input. Then, if it
 * remembers high pulses for all inputs, it sends a low pulse; otherwise, it
 * sends a high pulse.
 *
 * @param name the name of this module
 * @param destinations the names of the modules to which this module sends its output pulses
 * @parma latestInputs the latest value received for each of this module's inputs
 * @param hasOutputHigh whether this module has, at any point, output a high pulse
 * @author Martijn van de Rijdt
 */
record ConjunctionModule(String name, List<String> destinations, Map<String, PulseType> latestInputs, boolean hasOutputHigh)
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
        this(name, destinations, null, false);
    }
    
    @Override
    public ConjunctionModule init(Set<Module> modules) {
        var newLatestInputs = modules.stream()
                .filter(module -> module.destinations().contains(name))
                .collect(Collectors.toMap(Module::name, module -> PulseType.LOW));
        return new ConjunctionModule(name, destinations, newLatestInputs, hasOutputHigh);
    }
    
    @Override
    public HandlePulseResult handlePulse(Pulse pulse) {
        Objects.requireNonNull(latestInputs, "Conjunction module " + name + " has not yet been initialized.");
        
        Map<String, PulseType> newLatestInputs = new HashMap<>(latestInputs);
        newLatestInputs.put(pulse.source(), pulse.type());
        
        PulseType outgoingPulseType;
        if (newLatestInputs.values().stream().allMatch(PulseType.HIGH::equals)) {
            outgoingPulseType = PulseType.LOW;
        } else {
            outgoingPulseType = PulseType.HIGH;
        }
        
        var newHasOutputHigh = outgoingPulseType == PulseType.HIGH || hasOutputHigh;
        var updatedModule = new ConjunctionModule(name, destinations, newLatestInputs, newHasOutputHigh);
        
        var outgoingPulses = createOutgoingPulses(outgoingPulseType);
        
        return new HandlePulseResult(updatedModule, outgoingPulses);
    }
}
