package nl.mvdr.adventofcode.adventofcode2019.day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.LinesSolver;

/**
 * Test cases for {@link Process}.
 *
 * @author Martijn van de Rijdt
 */
@SuppressWarnings("boxing")
public class ProcessTest {
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() throws IOException {
        testPerform(List.of(0, 3, 6, 9, 2, 5, 8, 1, 4, 7), "example-day22-2019-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() throws IOException {
        testPerform(List.of(3, 0, 7, 4, 1, 8, 5, 2, 9, 6), "example-day22-2019-1.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2() throws IOException {
        testPerform(List.of(6, 3, 0, 7, 4, 1, 8, 5, 2, 9), "example-day22-2019-2.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3() throws IOException {
        testPerform(List.of(9, 2, 5, 8, 1, 4, 7, 0, 3, 6), "example-day22-2019-3.txt");
    }
    
    /**
     * Performs a test case for {@link Process#performOnFactoryOrderDeck(int)}, for a deck of 10 cards.
     * 
     * @param expectedShuffledDeck expected result
     * @param inputPath filename of the test resource containing shuffle instructions
     * @throws IOException if the test resource could not be read
     */
    private void testPerform(List<Integer> expectedShuffledDeck, String inputPath) throws IOException {
        Process process;
        Path path = LinesSolver.toPath(getClass(), inputPath);
        try (Stream<String> lines = Files.lines(path)) {
            process = Process.parse(lines);
        }
        
        List<Integer> result = process.performOnFactoryOrderDeck(10);
        
        Assertions.assertEquals(expectedShuffledDeck, result);
    }
    
    /**
     * Test case for {@link Process#computePreviousIndex(long, long)}.
     * 
     * @throws IOException if the test resource could not be read
     */
    @Test
    public void testComputePreviousIndex0() throws IOException {
        testComputePreviousInstruction("example-day22-2019-0.txt");
    }
    
    /**
     * Test case for {@link Process#computePreviousIndex(long, long)}.
     * 
     * @throws IOException if the test resource could not be read
     */
    @Test
    public void testComputePreviousIndex1() throws IOException {
        testComputePreviousInstruction("example-day22-2019-1.txt");
    }
    
    /**
     * Test case for {@link Process#computePreviousIndex(long, long)}.
     * 
     * @throws IOException if the test resource could not be read
     */
    @Test
    public void testComputePreviousIndex2() throws IOException {
        testComputePreviousInstruction("example-day22-2019-2.txt");
    }
    
    /**
     * Test case for {@link Process#computePreviousIndex(long, long)}.
     * 
     * @throws IOException if the test resource could not be read
     */
    @Test
    public void testComputePreviousIndex3() throws IOException {
        testComputePreviousInstruction("example-day22-2019-3.txt");
    }
    
    /**
     * Tests {@link Process#computePreviousIndex(long, long)} for a deck of 10 cards.
     * 
     * Note that this method assumes that {@link Process#performOnFactoryOrderDeck(int)} works correctly.
     * 
     * @param inputPath filename of the test resource containing shuffle instructions
     * @throws IOException if the test resource could not be read
     */
    private void testComputePreviousInstruction(String inputPath) throws IOException {
        Process process;
        Path path = LinesSolver.toPath(getClass(), inputPath);
        try (Stream<String> lines = Files.lines(path)) {
            process = Process.parse(lines);
        }
        List<Integer> shuffledDeck = process.performOnFactoryOrderDeck(10);
        for (int i = 0; i != 10; i++) {
            
            long result = process.computePreviousIndex(i, 10L);
            
            Assertions.assertEquals(shuffledDeck.get(i), Math.toIntExact(result), "Incorrect result " + result + " for index " + i);
        }
    }
}
