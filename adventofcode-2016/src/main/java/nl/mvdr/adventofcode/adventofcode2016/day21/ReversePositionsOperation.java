package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.Optional;
import java.util.stream.Stream;

record ReversePositionsOperation(int x, int y) implements ScramblerOperation {

    private static final String PREFIX = "reverse positions ";
    private static final String INFIX = " through ";
    
    static Optional<ScramblerOperation> parse(String line) {
        Optional<ScramblerOperation> result;
        if (line.startsWith(PREFIX)) {
            var parameters = Stream.of(line.substring(PREFIX.length()).split(INFIX))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (parameters.length != 2) {
                throw new IllegalArgumentException("Unable to parse: " + line);
            }
            result = Optional.of(new ReversePositionsOperation(parameters[0], parameters[1]));
        } else {
            result = Optional.empty();
        }
        return result;
    }
    
    @Override
    public Stream<String> apply(String input) {
        String result = input.substring(0, x)
                + new StringBuilder(input.substring(x, y + 1)).reverse().toString()
                + input.substring(y + 1, input.length());
        
        return Stream.of(result);
    }
    
    @Override
    public ScramblerOperation reverse() {
        return this;
    }
}
