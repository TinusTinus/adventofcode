package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test case for {@link Path}.
 *
 * @author Martijn van de Rijdt
 */
public class PathTest {
    
    /**
     * Test method for {@link Path#parse(String)}.
     */
    @Test
    public void testParse() {
        var pathString = "10R5L5R10L4R5L5";
        
        var path = Path.parse(pathString);
        
        var expectedPath = new Path(
                List.of(
                        new MovementInstruction(10),
                        TurnInstruction.RIGHT,
                        new MovementInstruction(5),
                        TurnInstruction.LEFT,
                        new MovementInstruction(5),
                        TurnInstruction.RIGHT,
                        new MovementInstruction(10),
                        TurnInstruction.LEFT,
                        new MovementInstruction(4),
                        TurnInstruction.RIGHT,
                        new MovementInstruction(5),
                        TurnInstruction.LEFT,
                        new MovementInstruction(5)
                        )
                );
        Assertions.assertEquals(expectedPath, path);
                
    }
}
