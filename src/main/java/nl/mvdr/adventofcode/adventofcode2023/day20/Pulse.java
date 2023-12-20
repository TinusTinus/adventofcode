package nl.mvdr.adventofcode.adventofcode2023.day20;

/**
 * A specific pulse which has been sent to a specific destination.
 *
 * @param type type of the pulse
 * @param source name of the pulse's source module
 * @param destination name of the pulse's destination module
 * @author Martijn van de Rijdt
 */
record Pulse(PulseType type, String source, String destination) {
    @Override
    public String toString() {
        return source + " -" + type + "-> " + destination;
    }
}
