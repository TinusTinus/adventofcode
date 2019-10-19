package nl.mvdr.adventofcode.adventofcode2017.day21;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.processing.Generated;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Image.class);
    
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
    
    /**
     * Enhances this image, according to the given enhancement rules.
     * 
     * @param iterations the number of times to enhance
     * @param rules rules to apply
     * @return enhanced image
     */
    Image enhance(int iterations, Set<EnhancementRule> rules) {
        Image result = this;
        for (int i = 0; i != iterations; i++) {
            LOGGER.debug("After {} enhancements: {}", Integer.valueOf(i), result);
            result = result.enhance(rules);
        }
        LOGGER.debug("Enhanced {}", result);
        return result;
    }
    
    /**
     * Performs a single enhancement of this image, according to the given
     * enhancement rules.
     * 
     * @param rules rules to apply
     * @return enhanced image
     */
    private Image enhance(Set<EnhancementRule> rules) {
        int size = size();

        Image result;
        if (size < 2) {
            throw new IllegalStateException("Too small to enhance: " + size + " x " + size + " image.");
        } else if (size == 2 || size == 3) {
            return applyRule(rules);
        } else {
            result = enhanceSubImages(rules);
        }

        return result;
    }
    
    /**
     * Applies one of the given rules to enhance this image.
     * 
     * Note that this image must be either 2x2 or 3x3, and match one of the given rules.
     * 
     * @param rules enhancement rules
     * @return enhanced image
     */
    private Image applyRule(Set<EnhancementRule> rules) {
        return rules.stream()
                .filter(this::applies)
                .map(EnhancementRule::getOutput)
                .findFirst()
                .orElseThrow();
    }
    
    /**
     * Determines whether the given rule can be applied to this image.
     * 
     * @param rule rule to check
     * @return whether the rule's input matches this image
     */
    private boolean applies(EnhancementRule rule) {
        return rule.getInput().matches(this);
    }

    /**
     * Enhances this image, by dividing it up into 2x2 or 3x3 subimages and enhancing each of them.
     * 
     * @param rules rules to apply
     * @return enhanced image
     */
    private Image enhanceSubImages(Set<EnhancementRule> rules) {
        int size = size();
        int squareSize;
        if (size % 2 == 0) {
            squareSize = 2;
        } else if (size % 3 == 0) {
            squareSize = 3;
        } else {
            throw new IllegalStateException("Unable to divide a " + size + " x " + size + " image up into squares.");
        }

        Image[][] squares = new Image[size / squareSize][size / squareSize];
        for (int x = 0; x != size / squareSize; x++) {
            for (int y = 0; y != size / squareSize; y++) {
                Image square = subImage(x * squareSize, y * squareSize, squareSize);
                squares[x][y] = square.applyRule(rules);
            }
        }

        int newSize = (size / squareSize) * (squareSize + 1);
        boolean[][] result = new boolean[newSize][newSize];

        // TODO populate result
        
        return new Image(result);
    }
    

    
    private Image subImage(int x, int y, int size) {
        boolean[][] result = new boolean[size][size];
        for (int xOffset = 0; xOffset != size; xOffset++) {
            for (int yOffset = 0; yOffset != size; yOffset++) {
                result[xOffset][yOffset] = pixels[x + xOffset][y + yOffset];
            }
        }
        return new Image(result);
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
            builder.append("\n    ");
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
