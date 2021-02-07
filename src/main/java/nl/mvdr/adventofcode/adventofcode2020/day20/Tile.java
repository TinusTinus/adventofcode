package nl.mvdr.adventofcode.adventofcode2020.day20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a tile.
 *
 * @author Martijn van de Rijdt
 */
record Tile(int id, List<String> imageLines) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Tile.class);
    
    /**
     * Reassembles the image based on the smaller images given in the puzzle input.
     * 
     * @param lines puzzle input
     * @return map containing the entire image
     */
    static Map<Point, Tile> reassembleImage(Stream<String> lines) {
        Set<Tile> tiles = parseTiles(lines);
        
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
        
        return image;
    }
    
    /**
     * Parses the puzzle input into a set of tiles.
     * 
     * @param inputStream puzzle input
     * @return tiles
     */
    private static Set<Tile> parseTiles(Stream<String> inputStream) {
        List<String> input = inputStream.collect(Collectors.toList());
        
        Set<Tile> result = new HashSet<>();
        int id = 0;
        List<String> imageLines = new ArrayList<>();
        
        for (String inputLine: input) {
            if (inputLine.startsWith("Tile ")) {
                // Start of a new tile.
                // This line contains the tile id, in the format: "Tile nnnn:"
                String idString = inputLine.substring(5, inputLine.length() - 1);
                id = Integer.parseInt(idString);
            } else if (inputLine.isEmpty()) {
                // End of a tile
                result.add(new Tile(id, List.copyOf(imageLines)));
                imageLines.clear();
            } else {
                imageLines.add(inputLine);
            }
        }
        
        if (!imageLines.isEmpty()) {
            // Add the final tile
            result.add(new Tile(id, List.copyOf(imageLines)));
        }
        return result;
    }
    
    /** @return all possible arrangements (through flipping and rotating) of this tile */
    private Set<Tile> arrangements() {
        Set<Tile> result = new HashSet<>();
        
        result.add(this);
        result.add(flipVertically());
        
        Tile flippedHorizontally = flipHorizontally();
        result.add(flippedHorizontally);
        result.add(flippedHorizontally.flipVertically());
        
        Tile rotated = rotateCounterClockwise();
        result.add(rotated);
        result.add(rotated.flipVertically());
        flippedHorizontally = rotated.flipHorizontally();
        result.add(flippedHorizontally);
        result.add(flippedHorizontally.flipVertically());
        
        
        return result;
    }
    
    /** @return copy of this tile, flipped vertically */
    private Tile flipVertically() {
        List<String> flippedImageLines = new ArrayList<>(imageLines);
        Collections.reverse(flippedImageLines);
        return new Tile(id, flippedImageLines);
    }
    
    /** @return copy of this tile, flipped horizontally */
    private Tile flipHorizontally() {
        List<String> flippedImageLines = imageLines.stream()
                .map(StringBuilder::new)
                .map(StringBuilder::reverse)
                .map(StringBuilder::toString)
                .collect(Collectors.toList());
        return new Tile(id, flippedImageLines);
    }
    
    /** @return copy of this tile, rotated 90 degrees counter-clockwise */
    private Tile rotateCounterClockwise() {
        int imageSize = imageLines.size();
        List<String> rotatedImageLines = IntStream.range(0, imageSize)
                .mapToObj(i -> imageLines.stream()
                        .map(imageLine -> "" + imageLine.charAt(imageSize - i - 1))
                        .collect(Collectors.joining()))
                .collect(Collectors.toList());
        return new Tile(id, rotatedImageLines);
    }

    /**
     * Determines whether two tiles can be placed next to each other.
     * 
     * @param otherTile other tile
     * @return whether this tile fits above the given other tile
     */
    private boolean fitsAbove(Tile otherTile) {
        return otherTile.imageLines.get(otherTile.imageLines.size() - 1).equals(this.imageLines.get(0));
    }

    /**
     * Determines whether two tiles can be placed next to each other.
     * 
     * @param otherTile other tile
     * @return whether this tile fits below the given other tile
     */
    private boolean fitsBelow(Tile otherTile) {
        return otherTile.fitsAbove(this);
    }
    
    /**
     * Determines whether two tiles can be placed next to each other.
     * 
     * @param otherTile other tile
     * @return whether this tile fits to the right of the given other tile
     */
    private boolean fitsRightOf(Tile otherTile) {
        int imageSize = imageLines.size();
        return IntStream.range(0, imageSize)
                .allMatch(i -> imageLines.get(i).charAt(0) == otherTile.imageLines.get(i).charAt(imageSize - 1));
    }
    
    /**
     * Determines whether two tiles can be placed next to each other.
     * 
     * @param otherTile other tile
     * @return whether this tile fits to the left of the given other tile
     */
    private boolean fitsLeftOf(Tile otherTile) {
        return otherTile.fitsRightOf(this);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tile ");
        builder.append(id);
        builder.append(":\n");
        imageLines.forEach(imageLine -> builder.append(imageLine).append("\n"));
        return builder.toString();
    }

}
