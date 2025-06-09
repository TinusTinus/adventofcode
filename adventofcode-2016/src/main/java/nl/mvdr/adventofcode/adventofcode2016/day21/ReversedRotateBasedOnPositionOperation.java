package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.stream.IntStream;

public record ReversedRotateBasedOnPositionOperation(RotateBasedOnPositionOperation originalOperation) implements ScramblerOperation {

    @Override
    public String apply(String input) {
        return IntStream.range(1, input.length())
                .mapToObj(rotationAmount -> new RotateOperation(RotationDirection.LEFT, rotationAmount))
                .map(operation -> operation.apply(input))
                .filter(output -> originalOperation.apply(output).equals(input))
                .reduce((_, _) -> { throw new IllegalStateException("Multiple possible correct answers found for " + this); })
                .orElseThrow();
    }
    
    @Override
    public ScramblerOperation reverse() {
        return originalOperation;
    }
    
}
