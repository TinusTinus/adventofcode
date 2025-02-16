package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.List;

/**
 * Record containing the result of the {@link Module#handlePulse(PulseType)} method.
 *
 * @param updatedModule updated state of the module after handling the pulse
 * @param list of newly outgoing pulses
 * @author Martijn van de Rijdt
 */
record HandlePulseResult(Module updatedModule, List<Pulse> outgoingPulses) {

}
