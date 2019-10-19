package nl.mvdr.adventofcode.adventofcode2017.day21;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.processing.Generated;

/**
 * Representation of a square two-dimensional image.
 *
 * @author Martijn van de Rijdt
 */
class Image {

    /**
     * The initial image pattern:
     * 
     * <pre>
     * .#.
     * ..#
     * ###
     * </pre>
     */
    static final Image INITIAL_IMAGE = new Image(new boolean[][] {
        { false,  true, false },
        { false, false,  true },
        {  true,  true,  true }
    });
    
    private final boolean[][] pixels;
    
    /**
     * Parses the given string into an image.
     * 
     * @param text textual representation of an image, where newlines have been represented by a forward slash;
     *     for example: "../.#" or ".#./..#/###"
     * @return image
     */
    static Image parse(String text) {
        String[] rows = text.split("/");
        int size = rows.length;
        
        boolean[][] result = new boolean[size][size];
        for (int x = 0; x != size; x++) {
            for (int y = 0; y != size; y++) {
                result[x][y] = rows[x].charAt(y) == '#';
            }
        }

        return new Image(result);
    }
    
    /**
     * Constructor.
     * 
     * @param pixels square two-dimensional array of pixels, each of which can be on ({@code true}) or off ({@code false})
     */
    private Image(boolean[][] pixels) {
        super();
        this.pixels = pixels;
    }
    
    private int size() {
        return pixels.length;
    }
    
    /**
     * Whether this image can be flipped and/or rotated to match the given other image.
     * 
     * @param other other image to compare to
     * @return whether there is a match
     */
    boolean matches(Image other) {
        return this.size() == other.size()
                && (this.matchesModuloRotation(other) || this.flip().matchesModuloRotation(other));
    }
    
    /**
     * Determines whether this image can be rotated to match the given other image.
     * 
     * @param other other image to compare to
     * @return whether there is a match
     */
    private boolean matchesModuloRotation(Image other) {
        boolean result = false;
        Image image = this;
        for (int i = 0; !result && i != 4; i++) {
            result = image.equals(other);
            image = image.rotate();
        }
        return result;
    }

    /** @return a flipped version of this image */
    private Image flip() {
        int size = size();
        
        boolean[][] result = new boolean[size][size];
        for (int x = 0; x != size; x++) {
            for (int y = 0; y != size; y++) {
                result[x][y] = pixels[size - 1 - x][y];
            }
        }
        return new Image(result);
    }
    
    /** @return a 90 degrees rotated version of this image */
    private Image rotate() {
        int size = size();
        
        boolean[][] result = new boolean[size][size];
        for (int x = 0; x != size; x++) {
            for (int y = 0; y != size; y++) {
                result[x][y] = pixels[size - 1 - y][x];
            }
        }
        return new Image(result);
    }
    
    /** @return the number of pixels which are on */
    long countOnPixels() {
        return Stream.of(pixels)
                .flatMap(row -> IntStream.range(0, row.length).mapToObj(idx -> Boolean.valueOf(row[idx])))
                .filter(Boolean::booleanValue)
                .count();
    }

    @Override
    @Generated("Elipse")
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(pixels);
        return result;
    }

    @Override
    @Generated("Elipse")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Image other = (Image) obj;
        return Arrays.deepEquals(pixels, other.pixels);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Image:");
        for (int x = 0; x != size(); x++) {
            builder.append('\n');
            for (int y = 0; y != size(); y++) {
                if (pixels[x][y]) {
                    builder.append('#');
                } else {
                    builder.append('.');
                }
            }
        }
        return builder.toString();
    }
}
