package nl.mvdr.adventofcode.adventofcode2016.day21;

import java.util.Optional;
import java.util.stream.Stream;

record RotateOperation(RotationDirection direction, int x) implements ScramblerOperation {

    private static final String PREFIX = "rotate ";
    private static final String INFIX = " ";
    
    static Optional<ScramblerOperation> parse(String line) {
        Optional<ScramblerOperation> result;
        if (line.startsWith(PREFIX)) {
            var parts = line.substring(PREFIX.length()).split(INFIX);
            
            if (parts.length != 3) {
                throw new IllegalArgumentException("Unable to parse: " + line);
            }
            
            var direction = RotationDirection.parse(parts[0]);
            var x = Integer.parseInt(parts[1]);
            
            result = Optional.of(new RotateOperation(direction, x));
        } else {
            result = Optional.empty();
        }
        return result;
    }
    
    @Override
    public Stream<String> apply(String input) {
        Stream<String> result;
        
        if (direction == RotationDirection.LEFT) {
            result = Stream.of(input.substring(x) + input.substring(0, x));
        } else {
            result = new RotateOperation(RotationDirection.LEFT, input.length() - x).apply(input);
        }
        
        return result;
    }
    
    @Override
    public ScramblerOperation reverse() {
        return new RotateOperation(direction.reverse(), x);
    }
}
