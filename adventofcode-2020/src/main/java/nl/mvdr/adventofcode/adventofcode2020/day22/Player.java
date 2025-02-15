package nl.mvdr.adventofcode.adventofcode2020.day22;

/**
 * Representation of a player in a game of Combat.
 *
 * @author Martijn van de Rijdt
 */
enum Player {
    /** Player 1: us. */
    ONE("Player 1"),
    
    /** Player 2: the small crab. */
    TWO("Player 2");
    
    private final String name;
    
    /**
     * Constructor.
     * 
     * @param name player name
     */
    Player(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
