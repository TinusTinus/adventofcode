package nl.mvdr.adventofcode.adventofcode2022.day23;

import nl.mvdr.adventofcode.point.Point;

/**
 * Movement proposal from an elf.
 * 
 * @param currentPosition the elf's current position
 * @param proposedPosition the elf's proposed new position
 * @author Martijn van de Rijdt
 */
record Proposal(Point currentPosition, Point proposedPosition) {
    // No additional logic needed
}
