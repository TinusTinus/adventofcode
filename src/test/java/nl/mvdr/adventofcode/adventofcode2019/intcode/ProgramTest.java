package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.LinesSolver;

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

        Assertions.assertEquals(List.of(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50), program.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute0() {
        String input = "1,9,10,3,2,3,11,0,99,30,40,50";
        Program program = Program.parse(input);

        Program result = program.execute();

        Assertions.assertEquals(List.of(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute1() {
        String input = "1,0,0,0,99";
        Program program = Program.parse(input);

        Program result = program.execute();

        Assertions.assertEquals(List.of(2, 0, 0, 0, 99), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute2() {
        String input = "2,3,0,3,99";
        Program program = Program.parse(input);

        Program result = program.execute();

        Assertions.assertEquals(List.of(2, 3, 0, 6, 99), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute3() {
        String input = "2,4,4,5,99,0";
        Program program = Program.parse(input);

        Program result = program.execute();

        Assertions.assertEquals(List.of(2, 4, 4, 5, 99, 9801), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute4() {
        String input = "1,1,1,4,99,5,6,0,99";
        Program program = Program.parse(input);

        Program result = program.execute();

        Assertions.assertEquals(List.of(30, 1, 1, 4, 2, 5, 6, 0, 99), result.getMemory());
    }
    
    /** Test case for {@link Program#execute()} based on day 2, part 1. */
    @Test
    public void testDay2Part1() throws IOException {
        String input;
        Path path = LinesSolver.toPath(getClass(), "input-day02-2019.txt");
        try (Stream<String> lines = Files.lines(path)) {
            input = lines.findFirst().orElseThrow();
        }
        Program program = Program.parse(input);
        program = program.set(1, 12);
        program = program.set(2, 2);
            
        Program result = program.execute();
            
        Assertions.assertEquals(9581917, result.getMemory().get(0).intValue());
    }
    
    /** Test case for {@link Program#execute()} based on day 2, part 2. */
    @Test
    public void testDay2Part2() throws IOException {
        String input;
        Path path = LinesSolver.toPath(getClass(), "input-day02-2019.txt");
        try (Stream<String> lines = Files.lines(path)) {
            input = lines.findFirst().orElseThrow();
        }
        Program program = Program.parse(input);
        program = program.set(1, 25);
        program = program.set(2, 5);
            
        Program result = program.execute();
            
        Assertions.assertEquals(19690720, result.getMemory().get(0).intValue());
    }
}