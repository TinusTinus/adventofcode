package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

record Scrambler(List<ScramblerOperation> operations) implements Function<String, String> {
    
    static Scrambler parse(Stream<String> lines) {
        var operations = lines.map(ScramblerOperation::parse)
                .toList();
        return new Scrambler(operations);
    }
    
    @Override
    public String apply(String input) {
        return operations.stream()
                .map(operation -> (Function<String, String>) operation)
                .reduce((firstOperation, secondOperation) -> firstOperation.andThen(secondOperation))
                .orElseThrow()
                .apply(input);
    }
    
    Scrambler reverse() {
        var reversedOperations = operations.reversed()
                .stream()
                .map(ScramblerOperation::reverse)
                .toList();
        return new Scrambler(reversedOperations);
    }
}
