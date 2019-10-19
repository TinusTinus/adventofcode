package nl.mvdr.adventofcode.adventofcode2017.day21;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.annotation.processing.Generated;

/**
 * Representation of a two-dimensional image.
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
     * Constructor.
     * 
     * @param pixels two-dimensional array of pixels, each of which can be on ({@code true}) or off ({@code false})
     */
    private Image(boolean[][] pixels) {
        super();
        this.pixels = pixels;
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
}
