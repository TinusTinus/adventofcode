package nl.mvdr.adventofcode.adventofcode2022.day19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link Blueprint}.
 *
 * @author Martijn van de Rijdt
 */
public class BlueprintTest {
    
    /**
     * Test case for {@link Blueprint#parse(String)}.
     */
    @Test
    public void testParse() {
        var text = "Blueprint 11:"
                + " Each ore robot costs 2 ore."
                + " Each clay robot costs 4 ore."
                + " Each obsidian robot costs 3 ore and 19 clay."
                + " Each geode robot costs 4 ore and 13 obsidian.";
        
        var result = Blueprint.parse(text);
        
        Assertions.assertEquals(11, result.id());
        Assertions.assertEquals(2, result.resourceRequirements().get(Resource.ORE).requires(Resource.ORE));
        Assertions.assertEquals(0, result.resourceRequirements().get(Resource.ORE).requires(Resource.CLAY));
        Assertions.assertEquals(0, result.resourceRequirements().get(Resource.ORE).requires(Resource.OBSIDIAN));
        Assertions.assertEquals(0, result.resourceRequirements().get(Resource.ORE).requires(Resource.GEODE));
        Assertions.assertEquals(4, result.resourceRequirements().get(Resource.CLAY).requires(Resource.ORE));
        Assertions.assertEquals(0, result.resourceRequirements().get(Resource.CLAY).requires(Resource.CLAY));
        Assertions.assertEquals(0, result.resourceRequirements().get(Resource.CLAY).requires(Resource.OBSIDIAN));
        Assertions.assertEquals(0, result.resourceRequirements().get(Resource.CLAY).requires(Resource.GEODE));
        Assertions.assertEquals(3, result.resourceRequirements().get(Resource.OBSIDIAN).requires(Resource.ORE));
        Assertions.assertEquals(19, result.resourceRequirements().get(Resource.OBSIDIAN).requires(Resource.CLAY));
        Assertions.assertEquals(0, result.resourceRequirements().get(Resource.OBSIDIAN).requires(Resource.OBSIDIAN));
        Assertions.assertEquals(0, result.resourceRequirements().get(Resource.OBSIDIAN).requires(Resource.GEODE));
        Assertions.assertEquals(4, result.resourceRequirements().get(Resource.GEODE).requires(Resource.ORE));
        Assertions.assertEquals(0, result.resourceRequirements().get(Resource.GEODE).requires(Resource.CLAY));
        Assertions.assertEquals(13, result.resourceRequirements().get(Resource.GEODE).requires(Resource.OBSIDIAN));
        Assertions.assertEquals(0, result.resourceRequirements().get(Resource.GEODE).requires(Resource.GEODE));
    }
    
    /**
     * Test case for {@link Blueprint#computeQualityLevel()}.
     */
    @Test
    public void testComputeQualityLevel1() {
        var text = "Blueprint 1:"
                + " Each ore robot costs 4 ore."
                + " Each clay robot costs 2 ore."
                + " Each obsidian robot costs 3 ore and 14 clay."
                + " Each geode robot costs 2 ore and 7 obsidian.";
        var blueprint = Blueprint.parse(text);
        
        var result = blueprint.computeQualityLevel();
        
        Assertions.assertEquals(9, result);
    }
    
    /**
     * Test case for {@link Blueprint#computeQualityLevel()}.
     */
    @Test
    public void testComputeQualityLevel2() {
        var text = "Blueprint 2:"
                + " Each ore robot costs 2 ore."
                + " Each clay robot costs 3 ore."
                + " Each obsidian robot costs 3 ore and 8 clay."
                + " Each geode robot costs 3 ore and 12 obsidian.";
        var blueprint = Blueprint.parse(text);
        
        var result = blueprint.computeQualityLevel();
        
        Assertions.assertEquals(24, result);
    }
}
