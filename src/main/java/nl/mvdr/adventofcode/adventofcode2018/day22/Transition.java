package nl.mvdr.adventofcode.adventofcode2018.day22;

/**
 * Possible transition from a {@link State} to another {@link State}.
 * 
 * @author Martijn van de Rijdt
 */
class Transition {
    /** Source state. */
    private final State source;
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
    Transition(State source, State nextState, int cost) {
        super();
        this.source = source;
        this.nextState = nextState;
        this.cost = cost;
    }

    State getSource() {
        return source;
    }
    
    State getNextState() {
        return nextState;
    }

    int getCost() {
        return cost;
    }
}
