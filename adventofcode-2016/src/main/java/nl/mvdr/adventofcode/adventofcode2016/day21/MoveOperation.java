package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.Optional;
import java.util.stream.Stream;

record MoveOperation(int x, int y) implements ScramblerOperation {

    private static final String PREFIX = "move position ";
    private static final String INFIX = " to position ";
    
    static Optional<ScramblerOperation> parse(String line) {
        Optional<ScramblerOperation> result;
        if (line.startsWith(PREFIX)) {
            var parameters = Stream.of(line.substring(PREFIX.length()).split(INFIX))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (parameters.length != 2) {
                throw new IllegalArgumentException("Unable to parse: " + line);
            }
            result = Optional.of(new MoveOperation(parameters[0], parameters[1]));
        } else {
            result = Optional.empty();
        }
        return result;
    }
    
    @Override
    public Stream<String> apply(String input) {
        String result;
        
        if (x == y) {
            result = input;
        } else if (x < y) {
            result = input.substring(0, x)
                    + input.substring(x + 1, y + 1)
                    + input.charAt(x)
                    + input.substring(y + 1, input.length());
        } else {
            result = input.substring(0, y)
                    + input.charAt(x)
                    + input.substring(y, x)
                    + input.substring(x + 1, input.length());
        }
        
        return Stream.of(result);
    }
    
    @Override
    public ScramblerOperation reverse() {
        return new MoveOperation(y, x);
    }
}
