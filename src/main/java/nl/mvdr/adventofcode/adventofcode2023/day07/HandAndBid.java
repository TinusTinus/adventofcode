package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.Comparator;

/**
 * A hand and the corresponding bid.
 * 
 * Values are comparable, where a stronger hand is greater than a weaker hand.
 * (Note that it is assumed that the puzzle input will not contain multiple copies of the same hand with different bids.)
 *
 * @author Martijn van de Rijdt
 */
record HandAndBid(Hand hand, int bid) implements Comparable<HandAndBid> {
    /**
     * Parses the textual representation of a hand and its corresponding bid.
     * 
     * @param text line from the puzzle input, for example: 32T3K 765
     * @return
     */
    static HandAndBid parse(String text) {
        var parts = text.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse as a hand and bid: " + text);
        }
        var hand = Hand.parse(parts[0]);
        var bid = Integer.parseInt(parts[1]);
        return new HandAndBid(hand, bid);
    }

    @Override
    public int compareTo(HandAndBid other) {
        return Comparator.comparing(HandAndBid::hand).compare(this, other);
    }
}
