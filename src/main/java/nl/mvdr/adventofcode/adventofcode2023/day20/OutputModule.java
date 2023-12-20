package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An output module, which processes all incoming pulses and does not produce any output itself.
 *
 * @author Martijn van de Rijdt
 */
record OutputModule(String name, boolean hasReceivedLowPulse) implements Module {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutputModule.class);
    
    /**
     * Convenience constructor.
     * 
     * @param name name of this module
     */
    OutputModule(String name) {
        this(name, false);
    }
    
    @Override
    public Module init(Set<Module> modules) {
        return this;
    }

    @Override
    public List<String> destinations() {
        return List.of();
    }

    @Override
    public HandlePulseResult handlePulse(Pulse pulse) {
        LOGGER.debug("Output module {} received pulse {}", name, pulse);
        
        OutputModule newModule;
        if (pulse.type() == PulseType.LOW) {
            newModule = new OutputModule(name, true);
        } else {
            newModule = this;
        }
        return new HandlePulseResult(newModule, List.of());
    }
}
