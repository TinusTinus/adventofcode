package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Exchange}.
 *
 * @author Martijn van de Rijdt
 */
public class ExchangeTest {
    /** Test case based on the example from the puzzle. */
    @Test
    public void testExample() {
        Exchange exchange = new Exchange(3, 4);
        List<Program> dancers = List.of(new Program('e'), new Program('a'), new Program('b'), new Program('c'), new Program('d'));
        
        List<Program> result = exchange.perform(dancers);
        
        Assertions.assertEquals(List.of(new Program('e'), new Program('a'), new Program('b'), new Program('d'), new Program('c')), result);
    }
}
