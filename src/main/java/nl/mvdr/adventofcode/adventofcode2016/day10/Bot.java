package nl.mvdr.adventofcode.adventofcode2016.day10;

/**
 * Representation of a robot.
 *
 * @author Martijn van de Rijdt
 */
class Bot extends MicrochipHolder {

    /**
     * Constructor.
     * 
     * @param number identification number of this robot
     */
    Bot(int number) {
        super(number);
    }
    
    /** {@inheritDoc} */
    @Override
    boolean canAct() {
        return getMicrochips().size() == 2;
    }

}
