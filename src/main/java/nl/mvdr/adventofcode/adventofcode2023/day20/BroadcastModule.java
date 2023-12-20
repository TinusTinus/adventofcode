package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.List;

/**
 * There is a single broadcast module (named broadcaster).
 * When it receives a pulse, it sends the same pulse to all of its destination modules.
 *
 * @author Martijn van de Rijdt
 */
record BroadcastModule(List<String> destinations) implements Module {

    @Override
    public String name() {
        return "broadcaster";
    }
    
    @Override
    public HandlePulseResult handlePulse(PulseType pulseType) {
        return new HandlePulseResult(this, createOutgoingPulses(pulseType));
    }
}
