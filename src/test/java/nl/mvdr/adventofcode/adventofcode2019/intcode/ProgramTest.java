package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Program}.
 *
 * @author Martijn van de Rijdt
 */
@SuppressWarnings("boxing")
public class ProgramTest {
    /** Test case for {@link Program#parse(String)}. */
    @Test
    public void testParse() {
        String input = "1,9,10,3,2,3,11,0,99,30,40,50";

        Program program = Program.parse(input);

        Assertions.assertEquals(List.of(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50), program.getIntegers());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute0() {
        String input = "1,9,10,3,2,3,11,0,99,30,40,50";
        Program program = Program.parse(input);

        Program result = program.execute();

        Assertions.assertEquals(List.of(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50), result.getIntegers());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute1() {
        String input = "1,0,0,0,99";
        Program program = Program.parse(input);

        Program result = program.execute();

        Assertions.assertEquals(List.of(2, 0, 0, 0, 99), result.getIntegers());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute2() {
        String input = "2,3,0,3,99";
        Program program = Program.parse(input);

        Program result = program.execute();

        Assertions.assertEquals(List.of(2, 3, 0, 6, 99), result.getIntegers());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute3() {
        String input = "2,4,4,5,99,0";
        Program program = Program.parse(input);

        Program result = program.execute();

        Assertions.assertEquals(List.of(2, 4, 4, 5, 99, 9801), result.getIntegers());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute4() {
        String input = "1,1,1,4,99,5,6,0,99";
        Program program = Program.parse(input);

        Program result = program.execute();

        Assertions.assertEquals(List.of(30, 1, 1, 4, 2, 5, 6, 0, 99), result.getIntegers());
    }
}
