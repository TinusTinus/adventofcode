package nl.mvdr.adventofcode.adventofcode2018.day18;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.LinesSolver;

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
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute01.txt"));
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick2() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute01.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute02.txt"));
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick3() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute02.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute03.txt"));
        Assertions.assertEquals(expected, result);
    }

    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick4() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute03.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute04.txt"));
        Assertions.assertEquals(expected, result);
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick5() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute04.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute05.txt"));
        Assertions.assertEquals(expected, result);
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick6() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute05.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute06.txt"));
        Assertions.assertEquals(expected, result);
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick7() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute06.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute07.txt"));
        Assertions.assertEquals(expected, result);
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick8() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute07.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute08.txt"));
        Assertions.assertEquals(expected, result);
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick9() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute08.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute09.txt"));
        Assertions.assertEquals(expected, result);
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testTick10() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute09.txt"));
        
        LumberCollectionArea result = area.tick(1);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute10.txt"));
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testMulitpleTicks() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018.txt"));
        
        LumberCollectionArea result = area.tick(10);
        
        LumberCollectionArea expected = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute10.txt"));
        Assertions.assertEquals(expected, result);
    }

    /**
     * Test case for {@link LumberCollectionArea#tick(int)} with negative input.
     * 
     * @thrown IOException unexpected
     */
    @Test
    public void testTickNegative() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018.txt"));
        
        Assertions.assertThrows(IllegalArgumentException.class, () -> area.tick(-1));
    }

    
    /**
     * Test case for {@link LumberCollectionArea#computeResourceValue()}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testResourceValue() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "example-day18-2018-minute10.txt"));
        
        long resourceValue = area.computeResourceValue();
        
        Assertions.assertEquals(1147L, resourceValue);
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testCycle0() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "input-day18-2018.txt"));
        
        LumberCollectionArea result = area.tick(512);
        
        LumberCollectionArea expected = area.tick(540);
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testCycle1() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "input-day18-2018.txt"));
        
        LumberCollectionArea result = area.tick(513);
        
        LumberCollectionArea expected = area.tick(541);
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testCycle2() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "input-day18-2018.txt"));
        
        LumberCollectionArea result = area.tick(514);
        
        LumberCollectionArea expected = area.tick(542);
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testCycle28() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "input-day18-2018.txt"));
        
        LumberCollectionArea result = area.tick(540);
        
        LumberCollectionArea expected = area.tick(568);
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testCycle29() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "input-day18-2018.txt"));
        
        LumberCollectionArea result = area.tick(541);
        
        LumberCollectionArea expected = area.tick(569);
        Assertions.assertEquals(expected, result);
    }
    
    /**
     * Test case for {@link LumberCollectionArea#tick(int)}.
     * 
     * @throws IOException unexpected
     */
    @Test
    public void testCycleTwice1() throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(LinesSolver.toPath(getClass(), "input-day18-2018.txt"));
        
        LumberCollectionArea result = area.tick(513);
        
        LumberCollectionArea expected = area.tick(569);
        Assertions.assertEquals(expected, result);
    }

}
