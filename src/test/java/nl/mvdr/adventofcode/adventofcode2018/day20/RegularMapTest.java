package nl.mvdr.adventofcode.adventofcode2018.day20;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link RegularMap}.
 *
 * @author Martijn van de Rijdt
 */
public class RegularMapTest extends SolverTest<RegularMap> {
    /** Constructor. */
    public RegularMapTest() {
        super(RegularMap.class);
    }

    /**
     * Test case based on an example from the puzzle.
     * 
     * In the first example ({@code ^WNE$}),
     * the furthest room would be the north-east corner 3 doors away.
     */
    @Test
    public void testExample0() {
        testSolution("Part 1: 3, part 2: 0", "example-day20-2018-0.txt");
    }
    
    /**
     * Test case based on an example from the puzzle.
     * 
     * In the second example ({@code ^ENWWW(NEEE|SSE(EE|N))$}),
     * the furthest room would be the south-east corner 10 doors away.
     */
    @Test
    public void testExample1() {
        testSolution("Part 1: 10, part 2: 0", "example-day20-2018-1.txt");
    }
    
    /**
     * Test case based on an example from the puzzle.
     * 
     * In the third example ({@code ^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$}),
     * the furthest room would be the north-east corner 18 doors away.
     */
    @Test
    public void testExample2() {
        testSolution("Part 1: 18, part 2: 0", "example-day20-2018-2.txt");
    }
    
    /**
     * Test case based on an example from the puzzle.
     * 
     * Regex: {@code ^ESSWWN(E|NNENN(EESS(WNSE|)SSS|WWWSSSSE(SW|NNNE)))$}.
     * Furthest room requires passing 23 doors.
     */
    @Test
    public void testExample3() {
        testSolution("Part 1: 23, part 2: 0", "example-day20-2018-3.txt");
    }
    
    /**
     * Test case based on an example from the puzzle.
     * 
     * Regex: {@code ^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$}.
     * Furthest room requires passing 31 doors.
     */
    @Test
    public void testExample4() {
        testSolution("Part 1: 31, part 2: 0", "example-day20-2018-4.txt");
    }
        
    /** Test case based on the accepted solution to the puzzle. */
    @Test
    public void testSolution() {
        testSolution("Part 1: 3502, part 2: 8000", "input-day20-2018.txt");
    }
}
