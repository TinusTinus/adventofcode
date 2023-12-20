package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * A module.
 *
 * @author Martijn van de Rijdt
 */
sealed interface Module permits ButtonModule, BroadcastModule, FlipFlopModule, ConjunctionModule {
    
    /**
     * Parses a module.
     * 
     * @param text textual representation of a module, such as "broadcaster -> a, b, c" or "%a -> b"
     * @return the (uninitialized!) module
     */
    static Module parse(String text) {
        var parts = text.split(" -> ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse: " + text);
        }
        
        var destinations = parseDestinations(parts[1]);
        Module result;
        if (BroadcastModule.NAME.equals(parts[0])) {
            result = new BroadcastModule(destinations);
        } else {
            var prefix = parts[0].substring(0, 1);
            var name = parts[0].substring(1);
            result = switch(prefix) {
                case FlipFlopModule.PREFIX -> new FlipFlopModule(name, destinations);
                case ConjunctionModule.PREFIX -> new ConjunctionModule(name, destinations);
                default -> throw new IllegalArgumentException("Unable to parse module with prefix " + prefix + ": " + text);
            };
        }
        
        return result;
    }
    
    /**
     * Parses a list of destinations.
     * 
     * @param text textual representation of destinations, such as "a, b, c" or "b"
     * @return destinations
     */
    private static List<String> parseDestinations(String text) {
        var parts = text.split(", ");
        return Arrays.asList(parts);
    }
    
    /**
     * Initializes this module.
     * 
     * @param modules the complete set of (uninitialized) modules
     * @return initialized module
     */
    Module init(Set<Module> modules);
    
    /**
     * @return this module's name
     */
    String name();
    
    /**
     * @return destination modules
     */
    List<String> destinations();
    
    /**
     * Handles a pulse.
     * 
     * @param pulse incoming pulse
     * @return result
     */
    HandlePulseResult handlePulse(Pulse pulse);
    
    /**
     * Creates outgoing pulses for this module: one for each destination.
     * 
     * @param type the outgoing pulse type
     * @return list of pulses
     */
    default List<Pulse> createOutgoingPulses(PulseType type) {
        return destinations().stream()
                .map(destination -> new Pulse(type, name(), destination))
                .toList();
    }
}
