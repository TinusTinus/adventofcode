package nl.mvdr.adventofcode.adventofcode2018.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test cases for {@link Note}.
 *
 * @author Martijn van de Rijdt
 */
public class NoteTest {
    /** Test case for {@link Note#parse(String)} and {@link Note#toString()}. */
    @Test
    public void testParseToString() {
        String input = "...## => #";
        
        Note note = Note.parse(input);
        String output = note.toString();
        
        Assertions.assertEquals(input, output);
    }
    
    /** Test case for {@link Note#parse(String)} with invalid input. */
    @Test
    public void testParseInvalid() {
        String input = "initial state: #..#.#..##......###...###";
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> Note.parse(input));
    }
}
