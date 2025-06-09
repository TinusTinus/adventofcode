package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.function.Function;

interface ScramblerOperation extends Function<String, String> {

    static ScramblerOperation parse(String line) {
        return MoveOperation.parse(line)
                .orElseGet(() -> ReversePositionsOperation.parse(line)
                .orElseGet(() -> RotateBasedOnPositionOperation.parse(line)
                .orElseGet(() -> RotateOperation.parse(line)
                .orElseGet(() -> SwapLettersOperation.parse(line)
                .orElseGet(() -> SwapPositionsOperation.parse(line)
                .orElseThrow(() -> new IllegalArgumentException("Unable to parse: " + line)))))));
    }
    
    ScramblerOperation reverse();
}
