package nl.mvdr.adventofcode.adventofcode2020.day05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link BoardingPass}.
 *
 * @author Martijn van de Rijdt
 */
public class BoardingPassTest {
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testFBFBBFFRLR() {
        String text = "FBFBBFFRLR";
        
        BoardingPass pass = BoardingPass.parse(text);
        
        Assertions.assertEquals(44, pass.row());
        Assertions.assertEquals(5, pass.column());
        Assertions.assertEquals(357, pass.seatId());
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testBFFFBBFRRR() {
        String text = "BFFFBBFRRR";
        
        BoardingPass pass = BoardingPass.parse(text);
        
        Assertions.assertEquals(70, pass.row());
        Assertions.assertEquals(7, pass.column());
        Assertions.assertEquals(567, pass.seatId());
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testFFFBBBFRRR() {
        String text = "FFFBBBFRRR";
        
        BoardingPass pass = BoardingPass.parse(text);
        
        Assertions.assertEquals(14, pass.row());
        Assertions.assertEquals(7, pass.column());
        Assertions.assertEquals(119, pass.seatId());
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testBBFFBBFRLL() {
        String text = "BBFFBBFRLL";
        
        BoardingPass pass = BoardingPass.parse(text);
        
        Assertions.assertEquals(102, pass.row());
        Assertions.assertEquals(4, pass.column());
        Assertions.assertEquals(820, pass.seatId());
    }
}
