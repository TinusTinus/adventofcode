package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Spin}.
 *
 * @author Martijn van de Rijdt
 */
public class SpinTest {
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample3() {
        Spin spin = new Spin(3);
        List<Program> dancers = List.of(new Program('a'), new Program('b'), new Program('c'), new Program('d'), new Program('e'));
        
        List<Program> result = spin.perform(dancers);
        
        Assertions.assertEquals(List.of(new Program('c'), new Program('d'), new Program('e'), new Program('a'), new Program('b')), result);
    }
    
    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample1() {
        Spin spin = new Spin(1);
        List<Program> dancers = List.of(new Program('a'), new Program('b'), new Program('c'), new Program('d'), new Program('e'));
        
        List<Program> result = spin.perform(dancers);
        
        Assertions.assertEquals(List.of(new Program('e'), new Program('a'), new Program('b'), new Program('c'), new Program('d')), result);
    }

}
