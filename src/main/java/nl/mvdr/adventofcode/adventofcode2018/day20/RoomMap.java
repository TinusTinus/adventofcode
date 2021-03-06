package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.SingleSourcePaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

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
    private static final Point STARTING_POINT = Point.ORIGIN; // Arbitrary coordinates
    
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomMap.class);
    
    /** Rooms. */
    private final Map<Point, Room> rooms;
    
    /**
     * Map containing, for each room, the length of the shortest path from {@link #STARTING_POINT} to this room.
     * 
     * Computed once this map is marked as complete (using {@link #complete()}).
     */
    private Map<Point, Integer> pathLengths;
    
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
        
        LOGGER.debug("Generated {}", result);
        
        return result;
    }
    
    /** Constructor. */
    private RoomMap() {
        super();
        this.rooms = new HashMap<>();
    }
    
    private boolean isComplete() {
        return pathLengths != null;
    }
    
    /** Marks this map as complete. */
    void complete() {
        // Calculate the shortest paths.
        
        // Create a graph, with rooms as vertices, and edges where there are doors.
        Graph<Room, DefaultEdge> graph = new SimpleWeightedGraph<>(DefaultEdge.class);
        rooms.values().forEach(graph::addVertex);
        // Add edges for West - East doors
        rooms.entrySet().stream()
            .filter(entry -> entry.getValue().hasEastDoor())
            .forEach(entry -> graph.addEdge(entry.getValue(), rooms.get(entry.getKey().eastNeighbour())));
        // Add edges for North - South doors
        rooms.entrySet().stream()
            .filter(entry -> entry.getValue().hasSouthDoor())
            .forEach(entry -> graph.addEdge(entry.getValue(), rooms.get(entry.getKey().southNeighbour())));
        
        ShortestPathAlgorithm<Room, DefaultEdge> algorithm = new DijkstraShortestPath<>(graph);
        SingleSourcePaths<Room, DefaultEdge> paths = algorithm.getPaths(rooms.get(STARTING_POINT));
        
        pathLengths = new HashMap<>();
        rooms.keySet().forEach(point -> {
            Room room = rooms.get(point);
            double weight = paths.getWeight(room);
            int pahtLength = (int) Math.round(weight);
            pathLengths.put(point, Integer.valueOf(pahtLength));
        });
    }

    Map<Point, Room> getRooms() {
        return rooms;
    }
    
    /** @return shortest distance, in number of doors, to the room furthest from the starting point */
    int calculateShortestDistanceToFurthestRoom() {
        return pathLengths.values().stream()
                .mapToInt(Integer::valueOf)
                .max()
                .getAsInt();
    }

    /** @return how many rooms have a shortest path from the starting position that pass through at least 1000 doors */
    long calculateNumberOfRoomsOver1000DoorsAway() {
        return pathLengths.values().stream()
                .filter(value -> 1000 <= value.intValue())
                .count();
    }
    
    @Override
    public String toString() {
        int minX = Point.minX(rooms.keySet());
        int maxX = Point.maxX(rooms.keySet());
        int minY = Point.minY(rooms.keySet());
        int maxY = Point.maxY(rooms.keySet());
        
        StringBuilder builder = new StringBuilder("Map:\n");
        
        // Top wall
        builder.append(WALL);
        for (int x = minX; x != maxX + 1; x++) {
            if (isComplete()) {
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
            if (isComplete()) {
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
                } else if (isComplete()) {
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
                } else if (isComplete()) {
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
