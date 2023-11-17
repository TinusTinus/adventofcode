package nl.mvdr.adventofcode.adventofcode2022.day16;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/16">Proboscidea Volcanium</a>.
 *
 * @author Martijn van de Rijdt
 */
class ProboscideaVolcanium implements IntSolver {

    private final boolean helperElephant;
    
    /**
     * Constructor.
     * 
     * @param helperElephant whether one of the elephants can help out
     */
    ProboscideaVolcanium(boolean helperElephant) {
        this.helperElephant = helperElephant;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var network = Network.parse(lines.toList());
        var startState = new State(network, helperElephant);
        return startState.maxPressureReleased();
    }
}
 