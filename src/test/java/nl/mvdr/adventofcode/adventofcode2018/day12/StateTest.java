package nl.mvdr.adventofcode.adventofcode2018.day12;

import org.junit.jupiter.api.Test;

/**
 * Test class for {@link State}.
 *
 * @author Martijn van de Rijdt
 */
public class StateTest {
    /** Test case for {@link State#parseInitial(String)}. */
    @Test
    public void testParse() {
        String text = "initial state: #..#.#..##......###...###";
        
        State state = State.parseInitial(text);
        
        // TODO check the result
    }
}
