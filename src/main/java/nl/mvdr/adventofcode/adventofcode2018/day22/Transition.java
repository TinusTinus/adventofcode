package nl.mvdr.adventofcode.adventofcode2018.day22;

/**
 * Possible transition from a {@link State} to another {@link State}.
 * 
 * @author Martijn van de Rijdt
 */
class Transition {
    /** Endpoint of the transition. */
    private final State nextState;
    /** The cost of this transition, in minutes. */
    private final int cost;
    
    /**
     * Constructor.
     * 
     * @param nextState endpoint
     * @param cost cost in minutes
     */
    Transition(State nextState, int cost) {
        super();
        this.nextState = nextState;
        this.cost = cost;
    }

    State getNextState() {
        return nextState;
    }

    int getCost() {
        return cost;
    }
}
