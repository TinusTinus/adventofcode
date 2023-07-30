package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.List;

/**
 * An actor who is capable of moving around and opening valves.
 *
 * @param currentPosition current position in the network
 * @param currentPath the path we are currently on to get to a specific closed valve; may be null, indicating that we are not (yet) on a path
 * @author Martijn van de Rijdt
 */
record Actor(Valve currentPosition, List<Valve> currentPath) {

}
