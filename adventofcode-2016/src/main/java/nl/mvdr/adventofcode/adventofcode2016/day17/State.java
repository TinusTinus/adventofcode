package nl.mvdr.adventofcode.adventofcode2016.day17;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

record State(String passcode, String path, Point location) {
    
    private static final Point VAULT = new Point(3, 3);
    
    /// Constructor for the initial state
    State(String passcode) {
        this(passcode, "", new Point(0, 0));
    }
    
    String shortestPathToVault() {
        var states = Set.of(this);
        
        while (!states.stream().anyMatch(State::isAtVault) && !states.isEmpty()) {
            states = states.stream()
                    .flatMap(State::move)
                    .collect(Collectors.toSet());
        }
        
        return states.stream()
                .filter(State::isAtVault)
                .reduce((_, _) -> { throw new IllegalStateException("Multiple shortest paths to vault found!"); })
                .orElseThrow(() -> new IllegalStateException("No path to vault found!"))
                .path();
    }
    
    int longestPathToVaultLength() {
        int result;
        if (isAtVault()) {
            result = path.length();
        } else {
            result = move()
                    .mapToInt(State::longestPathToVaultLength)
                    .max()
                    .orElse(0);
        }
        return result;
    }
    
    private boolean isAtVault() {
        return VAULT.equals(location);
    }
    
    private Stream<State> move() {
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
            // Facing a wall.
            result = null;
        }
        return result;
    }
}
