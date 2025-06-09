package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public record ReversedRotateBasedOnPositionOperation(RotateBasedOnPositionOperation originalOperation) implements ScramblerOperation {

    @Override
    public Stream<String> apply(String input) {
        return IntStream.range(1, input.length())
                .mapToObj(rotationAmount -> new RotateOperation(RotationDirection.LEFT, rotationAmount))
                .flatMap(operation -> operation.apply(input))
                .filter(output -> originalOperation.apply(output).anyMatch(input::equals));
    }
    
    @Override
    public ScramblerOperation reverse() {
        return originalOperation;
    }
}
