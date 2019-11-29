package nl.mvdr.adventofcode.adventofcode2016.day10;

/**
 * Representation of an output bin.
 *
 * @author Martijn van de Rijdt
 */
class Input extends MicrochipHolder {

    /**
     * Constructor.
     * 
     * @param number identification number of this output bin
     */
    Input(int number) {
        super(number);
    }
    
    /** {@inheritDoc} */
    @Override
    boolean canAct() {
        return !getMicrochips().isEmpty();
    }
}
