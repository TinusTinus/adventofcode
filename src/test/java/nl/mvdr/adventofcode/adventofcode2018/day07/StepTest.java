package nl.mvdr.adventofcode.adventofcode2018.day07;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Unit test cases for {@link Step}.
 *
 * @author Martijn van de Rijdt
 */
public class StepTest {
    
    /**
     * Test case based on an example from the puzzle.
     *
     * @throws IOException unexpected
     */
    @Test
    public void testParseExampleInput() throws IOException {
        Path path = PathSolver.toPath(getClass(), "example-day07-2018.txt");
        
        List<Step> steps = Step.parse(path, 0);
        
        Assertions.assertEquals(6, steps.size());
        Assertions.assertEquals('A', steps.get(0).getId());
        Assertions.assertEquals(1, steps.get(0).getRemainingTime());
        Assertions.assertEquals(Set.of('C'), steps.get(0).getPrerequisites().stream().map(Step::getId).collect(Collectors.toSet()));
        Assertions.assertEquals('B', steps.get(1).getId());
        Assertions.assertEquals(2, steps.get(1).getRemainingTime());
        Assertions.assertEquals(Set.of('A'), steps.get(1).getPrerequisites().stream().map(Step::getId).collect(Collectors.toSet()));
        Assertions.assertEquals('C', steps.get(2).getId());
        Assertions.assertEquals(3, steps.get(2).getRemainingTime());
        Assertions.assertEquals(Set.of(), steps.get(2).getPrerequisites().stream().map(Step::getId).collect(Collectors.toSet()));
        Assertions.assertEquals('D', steps.get(3).getId());
        Assertions.assertEquals(4, steps.get(3).getRemainingTime());
        Assertions.assertEquals(Set.of('A'), steps.get(3).getPrerequisites().stream().map(Step::getId).collect(Collectors.toSet()));
        Assertions.assertEquals('E', steps.get(4).getId());
        Assertions.assertEquals(5, steps.get(4).getRemainingTime());
        Assertions.assertEquals(Set.of('B', 'D', 'F'), steps.get(4).getPrerequisites().stream().map(Step::getId).collect(Collectors.toSet()));
        Assertions.assertEquals('F', steps.get(5).getId());
        Assertions.assertEquals(6, steps.get(5).getRemainingTime());
        Assertions.assertEquals(Set.of('C'), steps.get(5).getPrerequisites().stream().map(Step::getId).collect(Collectors.toSet()));
    }
    
    /**
     * Test case based on the puzzle input.
     *
     * @throws IOException unexpected
     */
    @Test
    public void testParse() throws IOException {
        Path path = PathSolver.toPath(getClass(), "input-day07-2018.txt");
        
        List<Step> steps = Step.parse(path, 60);
        
        Assertions.assertEquals(26, steps.size());
        Assertions.assertEquals('A', steps.get(0).getId());
        Assertions.assertEquals(61, steps.get(0).getRemainingTime());
    }
}
