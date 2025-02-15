package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Partner}.
 *
 * @author Martijn van de Rijdt
 */
public class PartnerTest {
    /** Test case based on the example from the puzzle. */
    @Test
    public void testPartner() {
        Partner exchange = new Partner('e', 'b');
        List<Program> dancers = List.of(new Program('e'), new Program('a'), new Program('b'), new Program('d'), new Program('c'));
        
        List<Program> result = exchange.perform(dancers);
        
        Assertions.assertEquals(List.of(new Program('b'), new Program('a'), new Program('e'), new Program('d'), new Program('c')), result);
    }
}
