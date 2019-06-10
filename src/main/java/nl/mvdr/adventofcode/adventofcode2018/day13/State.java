package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
     * @return new state
     * @throws CollisionException exception indicating that two mine carts have collided
     */
    State tick() throws CollisionException {
        List<MineCart> cartList = carts.stream()
                .sorted(Comparator.comparing(MineCart::getY).thenComparing(MineCart::getX))
                .collect(Collectors.toList());
        
        for (int i = 0; i != cartList.size(); i++) {
            MineCart cart = cartList.get(i);
            
            MineCart movedCart = move(cart);
            
            if (cartList.stream().anyMatch(otherCart -> otherCart.getX() == movedCart.getX() && otherCart.getY() == movedCart.getY())) {
                throw new CollisionException(movedCart.getX(), movedCart.getY());
            }
            
            cartList.set(i, movedCart);
        }
        
        return new State(map, new HashSet<>(cartList));
    }
    
    /**
     * Moves the given cart to its next position.
     * 
     * @param cart current position
     * @return new cart instance, containing the next position of the given cart
     */
    private MineCart move(MineCart cart) {
        // Determine the cart's next position, based on the direction it is currently facing.
        int nextX = cart.getX();
        int nextY = cart.getY();
        if (cart.getDirection() == Direction.UP) {
            nextY--;
        } else if (cart.getDirection() == Direction.DOWN) {
            nextY++;
        } else if (cart.getDirection() == Direction.LEFT) {
            nextX--;
        } else if (cart.getDirection() == Direction.RIGHT) {
            nextX++;
        } else {
            throw new IllegalStateException("Unexpected direction: " + cart.getDirection());
        }
        
        // Determine whether and how the cart should turn, based on the next section it travels onto.
        Direction nextDirection;
        Deque<Action> nextActions;
        
        TrackSection nextSection = map[nextX][nextY];
        
        if (nextSection == TrackSection.INTERSECTION) {
            nextActions = new LinkedList<>(cart.getActions());
            Action action = nextActions.removeFirst();
            nextActions.addLast(action);
            
            nextDirection = action.getNextDirection(cart.getDirection());
        } else {
            nextDirection = nextSection.getNextDirection(cart.getDirection());
            nextActions = cart.getActions();
        }
        
        return new MineCart(nextX, nextY, nextDirection, nextActions);
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
        
        LOGGER.debug("Parsed a {} x {} map with {} mine carts.", Integer.valueOf(width), Integer.valueOf(height), Integer.valueOf(carts.size()));
        
        return new State(map, carts);
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
                .map(direction -> new MineCart(x, y, direction));
    }
}
