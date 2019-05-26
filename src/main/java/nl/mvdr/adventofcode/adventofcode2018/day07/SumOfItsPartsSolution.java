package nl.mvdr.adventofcode.adventofcode2018.day07;

/**
 * Holder class for the solution to the "Sum of its Parts" puzzle.
 *
 * @author Martijn van de Rijdt
 */
class SumOfItsPartsSolution {
    /** Time spent working, in seconds. */
    private final int time;
    
    /** Concatenation of the step ids, in the order they were started. */
    private final String steps;
    
    /**
     * Constructor.
     * 
     * @param time time spent working, in seconds
     * @param steps concatenation of the step ids, in the order they were started
     */
    SumOfItsPartsSolution(int time, String steps) {
        super();
        this.time = time;
        this.steps = steps;
    }
    
    /**  @return concatenation of the step ids, in the order they were started */
    String getSteps() {
        return steps;
    }
    
    /**  @return time spent working, in seconds */
    int getTime() {
        return time;
    }
}
