package nl.mvdr.adventofcode.adventofcode2020.day06;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for {@link Person}.
 *
 * @author Martijn van de Rijdt
 */
public class PersonTest {
    
    /** Test case for {@link Person#parse(String)}. */
    @Test
    public void testParse() {
        String text = "abcx";
        
        Person person = Person.parse(text);
        
        Assertions.assertEquals(Set.of(Character.valueOf('a'), Character.valueOf('b'), Character.valueOf('c'), Character.valueOf('x')), person.questions());
    }
}
