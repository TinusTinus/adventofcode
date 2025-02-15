package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.List;
import java.util.Set;

/**
 * There is a module with a single button on it called, aptly, the button module.
 * When you push the button, a single low pulse is sent directly to the broadcaster module.
 *
 * @author Martijn van de Rijdt
 */
final class ButtonModule implements Module {
    /** Singleton instance. */
    static final ButtonModule INSTANCE = new ButtonModule();
    
    /** Private constructor to prevent singleton instantiation. */
    private ButtonModule() {
        super();
    }
    
    @Override
    public ButtonModule init(Set<Module> modules) {
        return this;
    }
    
    @Override
    public String name() {
        return "button";
    }

    @Override
    public List<String> destinations() {
        return List.of("broadcaster");
    }

    @Override
    public HandlePulseResult handlePulse(Pulse pulse) {
        throw new UnsupportedOperationException("The button cannot handle input.");
    }

    /**
     * Presses the button.
     * 
     * @return singleton list containing the outgoing pulse as a result of the button press
     */
    List<Pulse> press() {
        return createOutgoingPulses(PulseType.LOW);
    }
}
