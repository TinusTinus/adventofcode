package nl.mvdr.adventofcode.adventofcode2022.day02;

import java.util.function.Function;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 2 puzzle of 2022's Advent of Code:
 * <a href="https://adventofcode.com/2022/day/02">Rock Paper Scissors</a>.
 *
 * @author Martijn van de Rijdt
 */
class RockPaperScissors implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RockPaperScissors.class);

    private final Function<String, Round> roundParser;
    
    /**
     * Constructor.
     * 
     * @param roundParser how to parse a round
     */
    RockPaperScissors(Function<String, Round> roundParser) {
        super();
        this.roundParser = roundParser;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return score
     */
    @Override
    public int solve(Stream<String> lines) {
        Game game = Game.parse(lines, roundParser);
        LOGGER.debug("Game: {}", game);
        return game.score();
    }
}
 