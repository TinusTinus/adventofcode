package nl.mvdr.adventofcode.adventofcode2022.day16;

/**
 * An actor who is capable of moving around and opening valves.
 *
 * @param currentTarget the valve that this actor is currently traveling towards; may be null if not yet determined
 * @param distance the distance to the current target; null if not applicable
 * @author Martijn van de Rijdt
 */
record Actor(Valve currentTarget, Integer distance) {

}
