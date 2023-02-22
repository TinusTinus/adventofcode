package nl.mvdr.adventofcode.adventofcode2022.day02;

/**
 * Outcome of a round of Rock Paper Scissors.
 *
 * @author Martijn van de Rijdt
 */
enum Outcome {
    WIN(6),
    DRAW(3),
    LOSS(0);

    final int score;

    /**
     * Constructor.
     * 
     * @param score score for this outcome
     */
    Outcome(int score) {
        this.score = score;
    }
    
    /**
     * @return score for this outcome
     */
    int getScore() {
        return score;
    }
}
