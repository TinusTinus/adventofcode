package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * Representation of a map.
 *
 * @author Martijn van de Rijdt
 */
class RoomMap {
    /** Character representation of a wall, for use in {@link #toString()}. */
    private static final char WALL = '#';
    /** Character representation of a door on the East of a room, for use in {@link #toString()}. */
    private static final char EAST_DOOR = '|';
    /** Character representation of a door on the Sast of a room, for use in {@link #toString()}. */
    private static final char SOUTH_DOOR = '-';
    /** Character representation of the starting room, for use in {@link #toString()}. */
    private static final char STARTING_ROOM = 'X';
    /** Character representation for a room, for use in {@link #toString()}. */
    private static final char ROOM = '.';
    /** Character representation for a space which may be a wall or a door, for use in {@link #toString()}. */
    private static final char UNKNOWN = '?';
    /** Newline for use in {@link #toString()}. */
    private static final char NEWLINE = '\n';
    
    /** The starting point. */
    private static final Point STARTING_POINT = new Point(0, 0); // Arbitrary coordinates
    
    /** Rooms. */
    private final Map<Point, Room> rooms;
    
    /** Whether the map has been completely constructed from the regular expression from the input. */
    private boolean complete;
    
    /**
     * Creates a map of rooms based on the given expression.
     * 
     * @param expression expression to be parsed
     * @return map of the rooms
     */
    static RoomMap createMap(RoomMapExpression expression) {
        RoomMap result = new RoomMap();
        
        result.rooms.put(STARTING_POINT, new Room());
        
        expression.apply(Set.of(STARTING_POINT), result);
        
        return result;
    }
    
    /** Constructor. */
    private RoomMap() {
        super();
        this.rooms = new HashMap<>();
        this.complete = false;
    }
    
    /** Marks this map as complete. */
    void complete() {
        this.complete = true;
    }

    Map<Point, Room> getRooms() {
        return rooms;
    }
    
    /** @return shortest distance, in number of doors, to the room furthest from the starting point */
    int calculateShortestDistanceToFurthestRoom() {
        // TODO implement
        return 0;
    }
    
    @Override
    public String toString() {
        int minX = rooms.keySet().stream()
                .mapToInt(Point::getX)
                .min()
                .orElse(0);
        int maxX = rooms.keySet().stream()
                .mapToInt(Point::getX)
                .max()
                .orElse(0);
        int minY = rooms.keySet().stream()
                .mapToInt(Point::getY)
                .min()
                .orElse(0);
        int maxY = rooms.keySet().stream()
                .mapToInt(Point::getY)
                .max()
                .orElse(0);
        
        StringBuilder builder = new StringBuilder("Map:\n");
        
        // Top wall
        builder.append(WALL);
        for (int x = minX; x != maxX + 1; x++) {
            if (complete) {
                builder.append(WALL);
            } else {
                builder.append(UNKNOWN);
            }
            builder.append(WALL);
        }
        builder.append(NEWLINE);
        
        // Rooms
        for (int y = minY; y != maxY + 1; y++) {
            // West wall
            if (complete) {
                builder.append(WALL);
            } else {
                builder.append(UNKNOWN);
            }
            
            for (int x = minX; x != maxX + 1; x++) {
                Point point = new Point(x, y);
                Room room = rooms.get(point);

                // Room itself
                if (STARTING_POINT.equals(point)) {
                    builder.append(STARTING_ROOM);
                } else {
                    builder.append(ROOM);
                }
                
                // East door / wall
                if (room == null) {
                    builder.append(WALL);
                } else if (room.hasEastDoor()) {
                    builder.append(EAST_DOOR);
                } else if (complete) {
                    builder.append(WALL);
                } else {
                    builder.append(UNKNOWN);
                }
            }
            builder.append(NEWLINE);
            
            // Southwest wall
            builder.append(WALL);
            
            for (int x = minX; x != maxX + 1; x++) {
                Room room = rooms.get(new Point(x, y));

                // South door / wall
                if (room == null) {
                    builder.append(WALL);
                } else if (room.hasSouthDoor()) {
                    builder.append(SOUTH_DOOR);
                } else if (complete) {
                    builder.append(WALL);
                } else {
                    builder.append(UNKNOWN);
                }
                
                // Southeast wall
                builder.append(WALL);
            }
            builder.append(NEWLINE);
        }
        
        return builder.toString();
    }


    
    
}
