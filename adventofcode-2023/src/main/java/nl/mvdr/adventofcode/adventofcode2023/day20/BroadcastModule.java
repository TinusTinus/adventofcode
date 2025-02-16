package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.List;
import java.util.Set;

/**
 * There is a single broadcast module (named broadcaster).
 * When it receives a pulse, it sends the same pulse to all of its destination modules.
 *
 * @author Martijn van de Rijdt
 */
record BroadcastModule(List<String> destinations) implements Module {

    static final String NAME = "broadcaster";

    @Override
    public BroadcastModule init(Set<Module> modules) {
        return this;
    }
    
    @Override
    public String name() {
        return NAME;
    }
    
    @Override
    public HandlePulseResult handlePulse(Pulse pulse) {
        return new HandlePulseResult(this, createOutgoingPulses(pulse.type()));
    }
}
