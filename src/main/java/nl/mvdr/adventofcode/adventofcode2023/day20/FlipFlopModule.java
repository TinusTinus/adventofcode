package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.List;

/**
 * Flip-flop modules (prefix {@code %}) are either on or off; they are initially
 * off. If a flip-flop module receives a high pulse, it is ignored and nothing
 * happens. However, if a flip-flop module receives a low pulse, it flips
 * between on and off. If it was off, it turns on and sends a high pulse. If it
 * was on, it turns off and sends a low pulse.
 *
 * @author Martijn van de Rijdt
 */
record FlipFlopModule(String name, List<String> destinations, FlipFlopState state) implements Module {

    static final String PREFIX = "%";
    
    @Override
    public HandlePulseResult handlePulse(Pulse pulse) {
        return switch(pulse.type()) {
            case HIGH -> new HandlePulseResult(this, List.of());
            case LOW -> {
                var newState = switch(state) {
                    case OFF -> FlipFlopState.ON;
                    case ON -> FlipFlopState.OFF;
                    default -> throw new IllegalStateException("Unexpected flip-flop state: " + state);
                };
                var newModule = new FlipFlopModule(name, destinations, newState);
                var outgoingPulseType = switch(state) {
                    case OFF -> PulseType.HIGH;
                    case ON -> PulseType.LOW;
                    default -> throw new IllegalStateException("Unexpected flip-flop state: " + state);
                };
                yield new HandlePulseResult(newModule, createOutgoingPulses(outgoingPulseType));
            }
            default -> throw new IllegalArgumentException("Unexpected pulse type: " + pulse.type());
        };
    }

}
