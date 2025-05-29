package nl.mvdr.adventofcode.adventofcode2016.day17;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record State(String passcode, String path, Point location) {
    
    /// Constructor for the initial state
    State(String passcode) {
        this(passcode, "", new Point(0, 0));
    }
    
    Stream<State> move() {
        String hash = DigestUtils.md5Hex(passcode + path);
        
        return IntStream.range(0, 4)
                .filter(index -> doorIsOpen(hash.charAt(index)))
                .mapToObj(index -> Direction.values()[index])
                .map(this::move)
                .filter(Objects::nonNull);
    }

    private boolean doorIsOpen(char hashCharacter) {
        return List.of('b', 'c', 'd', 'e', 'f').contains(hashCharacter);
    }
    
    private State move(Direction direction) {
        var newLocation = direction.move(location);
        
        State result;
        if (0 <= newLocation.x() && newLocation.x() < 4
                && 0 <= newLocation.y() && newLocation.y() < 4) {
            result = new State(passcode, path + direction.name().charAt(0), newLocation);
        } else {
            result = null;
        }
        return result;
    }
}
