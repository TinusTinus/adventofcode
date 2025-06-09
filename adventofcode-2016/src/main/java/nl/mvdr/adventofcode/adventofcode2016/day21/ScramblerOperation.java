package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.function.Function;

interface ScramblerOperation extends Function<String, String> {

    static ScramblerOperation parse(String line) {
        return MoveOperation.parse(line)
                .orElse(ReversePositionsOperation.parse(line)
                .orElse(RotateBasedOnPositionOperation.parse(line)
                .orElse(RotateOperation.parse(line)
                .orElse(SwapLettersOperation.parse(line)
                .orElse(SwapPositionsOperation.parse(line)
                .orElseThrow(() -> new IllegalArgumentException("Unable to parse: " + line)))))));
    }
}
