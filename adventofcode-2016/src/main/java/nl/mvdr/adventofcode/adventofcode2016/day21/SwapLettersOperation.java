package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.Optional;
import java.util.stream.Stream;

record SwapLettersOperation(char x, char y) implements ScramblerOperation {

    private static final String PREFIX = "swap letter ";
    private static final String INFIX = " with letter ";
    
    static Optional<ScramblerOperation> parse(String line) {
        Optional<ScramblerOperation> result;
        if (line.startsWith(PREFIX)) {
            var parameters = Stream.of(line.substring(PREFIX.length()).split(INFIX))
                    .peek(SwapLettersOperation::checkLength1)
                    .map(part -> Character.valueOf(part.charAt(0)))
                    .toList();
            if (parameters.size() != 2) {
                throw new IllegalArgumentException("Unable to parse: " + line);
            }
            result = Optional.of(new SwapLettersOperation(parameters.getFirst().charValue(), parameters.getLast().charValue()));
        } else {
            result = Optional.empty();
        }
        return result;
    }
    
    private static void checkLength1(String part) {
        if (part.length() != 1) {
            throw new IllegalArgumentException("Part contains more than 1 letter: " + part);
        }
    }
     
    @Override
    public Stream<String> apply(String input) {
        var xIndex = input.indexOf(x);
        var yIndex = input.indexOf(y);
        
        return new SwapPositionsOperation(xIndex, yIndex).apply(input);
    }

    @Override
    public ScramblerOperation reverse() {
        return this;
    }
}
