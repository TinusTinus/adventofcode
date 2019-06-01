package nl.mvdr.adventofcode.adventofcode2018.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link State}.
 *
 * @author Martijn van de Rijdt
 */
public class StateTest {
    /** Test case for {@link State#parseInitial(String)} and {@link State#toString()}. */
    @Test
    public void testParseExample() {
        String text = "initial state: #..#.#..##......###...###";
        
        State state = State.parseInitial(text);
        String string = state.toString();
        
        Assertions.assertEquals("0: #..#.#..##......###...###", string);
    }
    
    /** Test case for {@link State#parseInitial(String)} and {@link State#toString()}. */
    @Test
    public void testParsePuzzleInput() {
        String text = "initial state: #.##.#.##..#.#...##...#......##..#..###..##..#.#.....##..###...#.#..#...######...#####..##....#..###";
        
        State state = State.parseInitial(text);
        String string = state.toString();
        
        Assertions.assertEquals("0: #.##.#.##..#.#...##...#......##..#..###..##..#.#.....##..###...#.#..#...######...#####..##....#..###", string);
    }
    
    /** Test case for {@link State#parseInitial(String)} and {@link State#toString()}. */
    @Test
    public void testParseNoPlants() {
        String text = "initial state: ..........";
        
        State state = State.parseInitial(text);
        String string = state.toString();
        
        Assertions.assertEquals("", string);
    }
    
    /** Test case for {@link State#parseInitial(String)} and {@link State#toString()}. */
    @Test
    public void testParseEmpty() {
        String text = "initial state: ";
        
        State state = State.parseInitial(text);
        String string = state.toString();
        
        Assertions.assertEquals("", string);
    }
}
