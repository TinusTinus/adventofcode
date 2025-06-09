package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.Optional;

record SwapPositionsOperation(int x, int y) implements ScramblerOperation {

    private static final String PREFIX = "swap position ";
    private static final String INFIX = " with position ";
    
    static Optional<SwapPositionsOperation> parse(String line) {
        Optional<SwapPositionsOperation> result;
        if (line.startsWith(PREFIX)) {
            var parameters = line.substring(PREFIX.length()).split(INFIX);
            var x = Integer.parseInt(parameters[0]);
            var y = Integer.parseInt(parameters[1]);
            result = Optional.of(new SwapPositionsOperation(x, y));
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
