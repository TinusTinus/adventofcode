package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.List;

/**
 * A module.
 *
 * @author Martijn van de Rijdt
 */
interface Module {
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
     * Creates outgoing pulses for this module.
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
