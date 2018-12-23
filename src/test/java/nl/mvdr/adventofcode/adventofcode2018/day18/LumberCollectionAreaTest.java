package nl.mvdr.adventofcode.adventofcode2018.day18;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Unit tests for {@link LumberCollectionArea}.
 *
 * @author Martijn van de Rijdt
 */
public class LumberCollectionAreaTest {
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick1() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute01.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick2() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute01.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute02.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick3() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute02.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute03.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }

    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick4() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute03.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute04.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick5() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute04.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute05.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick6() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute05.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute06.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick7() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute06.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute07.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick8() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute07.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute08.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick9() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute08.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute09.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick10() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute09.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute10.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testMulitpleTicks() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018.txt"));
        
        LumberCollectionArea result = area.tick(10);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute10.txt"));
        Assertions.assertEquals(expected.getAcres(), result.getAcres());
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)} with negative input.
     * 
     * @thrown IOException unexpected
     */
    @Test
    public void testTickNegative() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018.txt"));
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> area.tick(-1));
    }

    
    /**
     * Test case for {@link LumberCollectionArea#computeResourceValue()}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testResourceValue() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(PathSolver.toPath(getClass(), "example-day18-2018-minute10.txt"));
        
        long resourceValue = area.computeResourceValue();
        
        Assertions.assertEquals(1147L, resourceValue);
    }

}
