package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.Optional;
import java.util.stream.Stream;

record SwapPositionsOperation(int x, int y) implements ScramblerOperation {

    private static final String PREFIX = "swap position ";
    private static final String INFIX = " with position ";
    
    static Optional<ScramblerOperation> parse(String line) {
        Optional<ScramblerOperation> result;
        if (line.startsWith(PREFIX)) {
            var parameters = Stream.of(line.substring(PREFIX.length()).split(INFIX))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (parameters.length != 2) {
                throw new IllegalArgumentException("Unable to parse: " + line);
            }
            result = Optional.of(new SwapPositionsOperation(parameters[0], parameters[1]));
        } else {
            result = Optional.empty();
        }
        return result;
    }
    
    @Override
    public String apply(String input) {
        String result;
        
        if (x <= y) {
            result = input.substring(0, x)
                    + input.charAt(y)
                    + input.substring(x + 1, y)
                    + input.charAt(x)
                    + input.substring(y + 1);
        } else {
            result = new SwapPositionsOperation(y, x).apply(input);
        }
        
        return result;
    }

}
