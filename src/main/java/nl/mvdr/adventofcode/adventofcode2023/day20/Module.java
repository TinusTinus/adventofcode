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
     * @param pulseType type of the pulse
     * @return result
     */
    HandlePulseResult handlePulse(PulseType pulseType);
}
