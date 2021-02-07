package nl.mvdr.adventofcode.adventofcode2020.day20;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 20 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/18">Jurassic Jigsaw</a>.
 *
 * @author Martijn van de Rijdt
 */
public class JurassicJigsawPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(JurassicJigsawPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return product of the ids of the four corners
     */
    @Override
    public long solve(Stream<String> lines) {
        Set<Tile> tiles = Tile.parseTiles(lines);
        LOGGER.debug("Tiles: {}", tiles);
        
        Map<Point, Tile> image = new HashMap<>();
        
        Set<Tile> remainingTiles = new HashSet<>(tiles);

        // Place a tile
        image.put(Point.ORIGIN, remainingTiles.iterator().next());
        remainingTiles.remove(image.get(Point.ORIGIN));
        LOGGER.debug("Placing at {}: {}", Point.ORIGIN, image.get(Point.ORIGIN));
        
        // Keep a set containing points with tiles for which we have not yet attempted to find their neighbours.
        Set<Point> unexaminedTileLocations = new HashSet<>();
        unexaminedTileLocations.add(Point.ORIGIN);
        
        // Place the other tiles
        while (!remainingTiles.isEmpty()) {
            Point location = unexaminedTileLocations.iterator().next();
            unexaminedTileLocations.remove(location);
            
            Point aboveNeighbourLocation = location.aboveNeighbour();
            if (!image.containsKey(aboveNeighbourLocation)) {
                remainingTiles.stream()
                        .flatMap(tile -> tile.arrangements().stream())
                        .filter(tile -> tile.fitsAbove(image.get(location)))
                        .findFirst()
                        .ifPresent(neighbourTile -> {
                            LOGGER.debug("Placing at {} (above {}): {}", aboveNeighbourLocation, location, neighbourTile);
                            image.put(aboveNeighbourLocation, neighbourTile);
                            remainingTiles.removeIf(tile -> tile.id() == neighbourTile.id());
                            unexaminedTileLocations.add(aboveNeighbourLocation);
                        });
            }
            
            Point rightNeighbourLocation = location.rightNeighbour();
            if (!image.containsKey(rightNeighbourLocation)) {
                remainingTiles.stream()
                        .flatMap(tile -> tile.arrangements().stream())
                        .filter(tile -> tile.fitsRightOf(image.get(location)))
                        .findFirst()
                        .ifPresent(neighbourTile -> {
                            LOGGER.debug("Placing at {} (right of {}): {}", rightNeighbourLocation, location, neighbourTile);
                            image.put(rightNeighbourLocation, neighbourTile);
                            remainingTiles.removeIf(tile -> tile.id() == neighbourTile.id());
                            unexaminedTileLocations.add(rightNeighbourLocation);
                        });
            }
            
            Point belowNeighbourLocation = location.belowNeighbour();
            if (!image.containsKey(belowNeighbourLocation)) {
                remainingTiles.stream()
                        .flatMap(tile -> tile.arrangements().stream())
                        .filter(tile -> tile.fitsBelow(image.get(location)))
                        .findFirst()
                        .ifPresent(neighbourTile -> {
                            LOGGER.debug("Placing at {} (below {}): {}", belowNeighbourLocation, location, neighbourTile);
                            image.put(belowNeighbourLocation, neighbourTile);
                            remainingTiles.removeIf(tile -> tile.id() == neighbourTile.id());
                            unexaminedTileLocations.add(belowNeighbourLocation);
                        });
            }
            
            Point leftNeighbourLocation = location.leftNeighbour();
            if (!image.containsKey(leftNeighbourLocation)) {
                remainingTiles.stream()
                        .flatMap(tile -> tile.arrangements().stream())
                        .filter(tile -> tile.fitsLeftOf(image.get(location)))
                        .findFirst()
                        .ifPresent(neighbourTile -> {
                            LOGGER.debug("Placing at {} (left of {}): {}", leftNeighbourLocation, location, neighbourTile);
                            image.put(leftNeighbourLocation, neighbourTile);
                            remainingTiles.removeIf(tile -> tile.id() == neighbourTile.id());
                            unexaminedTileLocations.add(leftNeighbourLocation);
                        });
            }
        }
        
        LOGGER.debug("Image: {}", image);
        
        int minX = Point.minX(image.keySet());
        int maxX = Point.maxX(image.keySet());
        int minY = Point.minY(image.keySet());
        int maxY = Point.maxY(image.keySet());
        
        Tile upperLeft = image.get(new Point(minX, minY));
        Tile upperRight = image.get(new Point(maxX, minY));
        Tile lowerLeft = image.get(new Point(minX, maxY));
        Tile lowerRight = image.get(new Point(maxX, maxY));
        
        return (long)upperLeft.id() * upperRight.id() * lowerLeft.id() * lowerRight.id();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        JurassicJigsawPart1 instance = new JurassicJigsawPart1();

        String result = instance.solve("input-day20-2020.txt");

        LOGGER.info(result);
    }
}
 