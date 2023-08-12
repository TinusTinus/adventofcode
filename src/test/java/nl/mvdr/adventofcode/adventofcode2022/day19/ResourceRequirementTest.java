package nl.mvdr.adventofcode.adventofcode2022.day19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link ResourceRequirement}.
 *
 * @author Martijn van de Rijdt
 */
public class ResourceRequirementTest {
    
    /**
     * Test case for {@link ResourceRequirement#parse(String)}.
     */
    @Test
    public void testParseOneRequirement() {
        var text = "4 ore";
        
        var result = ResourceRequirement.parse(text);
        
        Assertions.assertEquals(4, result.requires(Resource.ORE));
        Assertions.assertEquals(0, result.requires(Resource.CLAY));
        Assertions.assertEquals(0, result.requires(Resource.OBSIDIAN));
        Assertions.assertEquals(0, result.requires(Resource.GEODE));
    }
    
    /**
     * Test case for {@link ResourceRequirement#parse(String)}.
     */
    @Test
    public void testParseTwoRequirements() {
        var text = "2 ore and 12 clay";
        
        var result = ResourceRequirement.parse(text);
        
        Assertions.assertEquals(2, result.requires(Resource.ORE));
        Assertions.assertEquals(12, result.requires(Resource.CLAY));
        Assertions.assertEquals(0, result.requires(Resource.OBSIDIAN));
        Assertions.assertEquals(0, result.requires(Resource.GEODE));
    }
}
