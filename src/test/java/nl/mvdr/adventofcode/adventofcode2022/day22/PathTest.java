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
     * Test method for {@link Path#parse(String, WrapAroundStrategy)}.
     */
    @Test
    public void testParse() {
        var pathString = "10R5L5R10L4R5L5";
        var strategy = WrapAroundStrategy.MODULO;
        
        var path = Path.parse(pathString, strategy);
        
        var expectedPath = new Path(
                List.of(
                        new MovementInstruction(10, strategy),
                        TurnInstruction.RIGHT,
                        new MovementInstruction(5, strategy),
                        TurnInstruction.LEFT,
                        new MovementInstruction(5, strategy),
                        TurnInstruction.RIGHT,
                        new MovementInstruction(10, strategy),
                        TurnInstruction.LEFT,
                        new MovementInstruction(4, strategy),
                        TurnInstruction.RIGHT,
                        new MovementInstruction(5, strategy),
                        TurnInstruction.LEFT,
                        new MovementInstruction(5, strategy)
                        )
                );
        Assertions.assertEquals(expectedPath, path);
                
    }
}
