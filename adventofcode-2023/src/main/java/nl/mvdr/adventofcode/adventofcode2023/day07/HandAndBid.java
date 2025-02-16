package nl.mvdr.adventofcode.adventofcode2023.day07;

/**
 * A hand and the corresponding bid.
 * 
 * Values are comparable, where a stronger hand is greater than a weaker hand.
 * (Note that it is assumed that the puzzle input will not contain multiple copies of the same hand with different bids.)
 *
 * @param <C> the concrete card class
 * @author Martijn van de Rijdt
 */
record HandAndBid<C extends Card<C>>(Hand<C> hand, int bid) implements Comparable<HandAndBid<C>> {
    /**
     * Parses the textual representation of a hand and its corresponding bid.
     * 
     * @param <C> the concrete card class
     * @param text line from the puzzle input, for example: 32T3K 765
     * @param cardClass the concrete card class
     * @return hand and corresponding bid
     */
    static <C extends Enum<C> & Card<C>> HandAndBid<C> parse(String text, Class<C> cardClass) {
        var parts = text.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse as a hand and bid: " + text);
        }
        var hand = Hand.parse(parts[0], cardClass);
        var bid = Integer.parseInt(parts[1]);
        return new HandAndBid<>(hand, bid);
    }

    @Override
    public int compareTo(HandAndBid<C> other) {
        return this.hand().compareTo(other.hand());
    }
}
