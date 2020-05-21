package nl.mvdr.adventofcode.adventofcode2019.day24;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.LinesSolver;

/**
 * Unit test cases for {@link Layout}.
 *
 * @author Martijn van de Rijdt
 */
public class LayoutTest {
    /** Test case for {@link Layout#tick()}. */
    @Test
    public void test1stMinute() {
        Layout layout = parse("example-day24-2019-0.txt");
        
        Layout result = layout.tick();
        
        Layout expected = parse("example-day24-2019-1.txt");
        Assertions.assertEquals(expected, result);
    }
    
    /** Test case for {@link Layout#tick()}. */
    @Test
    public void test2ndMinute() {
        Layout layout = parse("example-day24-2019-1.txt");
        
        Layout result = layout.tick();
        
        Layout expected = parse("example-day24-2019-2.txt");
        Assertions.assertEquals(expected, result);
    }
    
    /** Test case for {@link Layout#tick()}. */
    @Test
    public void test3rdMinute() {
        Layout layout = parse("example-day24-2019-2.txt");
        
        Layout result = layout.tick();
        
        Layout expected = parse("example-day24-2019-3.txt");
        Assertions.assertEquals(expected, result);
    }
    
    /** Test case for {@link Layout#tick()}. */
    @Test
    public void test4thMinute() {
        Layout layout = parse("example-day24-2019-3.txt");
        
        Layout result = layout.tick();
        
        Layout expected = parse("example-day24-2019-4.txt");
        Assertions.assertEquals(expected, result);
    }
    
    /** Test case for {@link Layout#biodiversity()}. */
    @Test
    public void testBioDiversity() {
        Layout layout = parse("example-day24-2019-5.txt");
        
        int biodiversity = layout.biodiversity();
        
        Assertions.assertEquals(2129920, biodiversity);
    }
    
    /**
     * Reads a layout from a test resource.
     * 
     * @param filename filename of a test resource
     * @return layout
     */
    private Layout parse(String filename) {
        Layout result;
        Path path = LinesSolver.toPath(getClass(), filename);
        try (Stream<String> lines = Files.lines(path)) {
            result = Layout.parse(false, lines);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return result;
    }
}
