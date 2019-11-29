package nl.mvdr.adventofcode.adventofcode2016.day10;

/**
 * Representation of an output bin.
 *
 * @author Martijn van de Rijdt
 */
class Output extends MicrochipHolder {

    /**
     * Constructor.
     * 
     * @param number identification number of this output bin
     */
    Output(int number) {
        super(number);
    }
    
    /** {@inheritDoc} */
    @Override
    boolean canAct() {
        return false;
    }
}
