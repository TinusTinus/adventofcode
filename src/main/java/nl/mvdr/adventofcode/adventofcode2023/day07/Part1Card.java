package nl.mvdr.adventofcode.adventofcode2023.day07;

/**
 * A card as in part 1 of the puzzle, where 'J' is a Jack.
 * 
 * @author Martijn van de Rijdt
 */
enum Part1Card implements Card<Part1Card> {
    
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');
    
    private final char representation;
    
    @Override
    public char getRepresentation() {
        return representation;
    }
    
    /**
     * Constructor.
     * 
     * @param representation single-character representation of the card
     */
    Part1Card(char representation) {
        this.representation = representation;
    }
    
    @Override
    public String toString() {
        return "" + representation;
    }
}
