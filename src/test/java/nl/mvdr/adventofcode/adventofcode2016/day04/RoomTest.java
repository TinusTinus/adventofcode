package nl.mvdr.adventofcode.adventofcode2016.day04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link Room}.
 *
 * @author Martijn van de Rijdt
 */
public class RoomTest {
    /** Test case for {@link Room#isReal()}. */
    @Test
    public void testIsReal0() {
        Room room = Room.parseRoom("aaaaa-bbb-z-y-x-123[abxyz]");
        
        boolean real = room.isReal();
        
        Assertions.assertTrue(real);
    }
    
    /** Test case for {@link Room#isReal()}. */
    @Test
    public void testIsReal1() {
        Room room = Room.parseRoom("a-b-c-d-e-f-g-h-987[abcde]");
        
        boolean real = room.isReal();
        
        Assertions.assertTrue(real);
    }

    
    /** Test case for {@link Room#isReal()}. */
    @Test
    public void testIsReal2() {
        Room room = Room.parseRoom("not-a-real-room-404[oarel]");
        
        boolean real = room.isReal();
        
        Assertions.assertTrue(real);
    }

    
    /** Test case for {@link Room#isReal()}. */
    @Test
    public void testIsNotReal() {
        Room room = Room.parseRoom("totally-real-room-200[decoy]");
        
        boolean real = room.isReal();
        
        Assertions.assertFalse(real);
    }

    /** Test case for {@link Room#decryptName()}. */
    @Test
    public void testDecryptName() {
        Room room = Room.parseRoom("qzmt-zixmtkozy-ivhz-343[whatever]");
        
        String decryptedName = room.decryptName();
        
        Assertions.assertEquals("very encrypted name", decryptedName);
    }
}
