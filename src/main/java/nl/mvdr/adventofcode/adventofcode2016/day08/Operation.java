package nl.mvdr.adventofcode.adventofcode2016.day08;

/**
 * Implementation of an operation on a screen.
 *
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
interface Operation {
    /**
     * Performs the operation.
     * 
     * @param screen the screen before executing of the operation
     * @param a first parameter
     * @param b second parameter
     * @return updated screen
     */
    Screen execute(Screen screen, int a, int b);
}
