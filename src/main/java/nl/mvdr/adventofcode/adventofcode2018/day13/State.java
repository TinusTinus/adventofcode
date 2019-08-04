package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * Current state of the map and the carts on it.
 *
 * @author Martijn van de Rijdt
 */
class State {
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(State.class);
    
    /** The map. */
    private final TrackSection[][] map;
    
    /** All mine carts. */
    private final Set<MineCart> carts;
    
    /**
     * Constructor.
     * 
     * @param map map
     * @param carts mine carts
     */
    private State(TrackSection[][] map, Set<MineCart> carts) {
        super();
        
        this.map = map;
        this.carts = carts;
    }
    
    /**
     * Performs a single tick.
     * 
     * @param throwOnCollision whether this method should throw a {@link CollisionException} as soon as a collision occurs
     * @return new state
     * @throws CollisionException exception indicating that two mine carts have collided
     */
    State tick(boolean throwOnCollision) throws CollisionException {
        List<MineCart> cartList = carts.stream()
                .sorted(Comparator.comparing(MineCart::getLocation))
                .collect(Collectors.toList());
        
        int i = 0;
        while (i < cartList.size()) {
            MineCart cart = cartList.get(i);
            
            MineCart movedCart = move(cart);
            
            Set<MineCart> collidingCarts = cartsAt(cartList, movedCart.getLocation());
            if (collidingCarts.isEmpty()) {
                // no collision
                cartList.set(i, movedCart);
                i++;
            } else if (throwOnCollision) {
                throw new CollisionException(movedCart.getLocation());
            } else {
                LOGGER.debug("Collision detected at {}", movedCart.getLocation());
                // remove the colliding carts from play
                cartList.remove(i);
                
                for (MineCart collidingCart : collidingCarts) {
                    int index = cartList.indexOf(collidingCart);
                    cartList.remove(index);
                    if (index < i) {
                        i--;
                    }
                }
            }
        }
        
        State result = new State(map, new HashSet<>(cartList));
        LOGGER.debug("State after tick:\n{}", result);
        return result;
    }
    
    /**
     * Moves the given cart to its next position.
     * 
     * @param cart current position
     * @return new cart instance, containing the next position of the given cart
     */
    private MineCart move(MineCart cart) {
        // Determine the cart's next position, based on the direction it is currently facing.
        Point nextLocation = cart.getDirection().nextLocation(cart.getLocation());
        
        // Determine whether and how the cart should turn, based on the next section it travels onto.
        Direction nextDirection;
        Deque<Action> nextActions;
        
        TrackSection nextSection = map[nextLocation.getX()][nextLocation.getY()];
        
        if (nextSection == TrackSection.INTERSECTION) {
            nextActions = new LinkedList<>(cart.getActions());
            Action action = nextActions.removeFirst();
            nextActions.addLast(action);
            
            nextDirection = action.getNextDirection(cart.getDirection());
        } else {
            nextDirection = nextSection.getNextDirection(cart.getDirection());
            nextActions = cart.getActions();
        }
        
        return new MineCart(nextLocation, nextDirection, nextActions);
    }
    
    Set<MineCart> getCarts() {
        return carts;
    }
    
    /**
     * Parses the input file into a state.
     * 
     * @param inputFilePath path of the input file
     * @return initial state
     * @throws IOException if the input could not be read
     */
    static State parse(Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
        
        int height = lines.size();
        int width = lines.get(0).length();
        
        TrackSection[][] map = new TrackSection[width][height];
        Set<MineCart> carts = new HashSet<>();
        
        for (int y = 0; y != height; y++) {
            String line = lines.get(y);
            for (int x = 0; x != width; x++) {
                char c = line.charAt(x);
                
                Optional<MineCart> cart = parseMineCart(x, y, c);
                
                cart.ifPresent(carts::add);
                
                map[x][y] = cart.map(MineCart::getDirection)
                        .map(Direction::getStraightPath)
                        .orElseGet(() -> TrackSection.of(c));
            }
        }
        
        State result = new State(map, carts);
        LOGGER.debug("Parsed state:\n{}", result);
        return result;
    }
    
    /**
     * Parses the given character as a mine cart.
     * 
     * @param x x coordinate
     * @param y y coordinate
     * @param c character representation of the mine cart's direction
     * @return mine cart, or empty in case the given character does not match a {@link Direction} value
     */
    private static Optional<MineCart> parseMineCart(int x, int y, char c) {
        return Direction.of(c)
                .map(direction -> new MineCart(new Point(x, y), direction));
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        int width = map.length;
        int height = map[0].length;
        
        for (int y = 0; y != height; y++) {
            for (int x = 0; x != width; x++) {
                Set<MineCart> cartsHere = cartsAt(carts, new Point(x, y));
                if (cartsHere.isEmpty()) {
                    builder.append(map[x][y]);
                } else if (1 < cartsHere.size()) {
                    // should not occur but hey
                    builder.append('X');
                } else {
                    builder.append(cartsHere.iterator().next().getDirection());
                }
            }
            builder.append("\n");
        }
        
        return builder.toString();
    }
    
    /**
     * Returns all mine carts from the given collection at the given coordinates.
     * 
     * @param carts carts
     * @param location location
     * @return mine carts at the given location
     */
    private static Set<MineCart> cartsAt(Collection<MineCart> carts, Point location) {
        return carts.stream()
                .filter(cart -> cart.getLocation().equals(location))
                .collect(Collectors.toSet());
    }
}
