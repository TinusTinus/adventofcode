package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
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
        String programText = "1,9,10,3,2,3,11,0,99,30,40,50";

        Program program = Program.parse(programText);

        Assertions.assertEquals(List.of(1, 9, 10, 3, 2, 3, 11, 0, 99, 30, 40, 50), program.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute0() {
        Program program = Program.parse("1,9,10,3,2,3,11,0,99,30,40,50");

        Program result = program.execute();

        Assertions.assertEquals(List.of(3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute1() {
        Program program = Program.parse("1,0,0,0,99");

        Program result = program.execute();

        Assertions.assertEquals(List.of(2, 0, 0, 0, 99), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute2() {
        Program program = Program.parse("2,3,0,3,99");

        Program result = program.execute();

        Assertions.assertEquals(List.of(2, 3, 0, 6, 99), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute3() {
        Program program = Program.parse("2,4,4,5,99,0");

        Program result = program.execute();

        Assertions.assertEquals(List.of(2, 4, 4, 5, 99, 9801), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute4() {
        Program program = Program.parse("1,1,1,4,99,5,6,0,99");

        Program result = program.execute();

        Assertions.assertEquals(List.of(30, 1, 1, 4, 2, 5, 6, 0, 99), result.getMemory());
    }
    
    /** Test case for {@link Program#execute()} based on day 2, part 1. */
    @Test
    public void testDay2Part1() throws IOException {
        String programText;
        Path path = LinesSolver.toPath(getClass(), "input-day02-2019.txt");
        try (Stream<String> lines = Files.lines(path)) {
            programText = lines.findFirst().orElseThrow();
        }
        Program program = Program.parse(programText);
        program = program.set(1, 12);
        program = program.set(2, 2);
            
        Program result = program.execute();
            
        Assertions.assertEquals(9581917, result.getMemory().get(0).intValue());
    }
    
    /** Test case for {@link Program#execute()} based on day 2, part 2. */
    @Test
    public void testDay2Part2() throws IOException {
        String programText;
        Path path = LinesSolver.toPath(getClass(), "input-day02-2019.txt");
        try (Stream<String> lines = Files.lines(path)) {
            programText = lines.findFirst().orElseThrow();
        }
        Program program = Program.parse(programText);
        program = program.set(1, 25);
        program = program.set(2, 5);
            
        Program result = program.execute();
            
        Assertions.assertEquals(19690720, result.getMemory().get(0).intValue());
    }

    /**
     * Test case for {@link Program#execute()} including input / output handling.
     * 
     * The program 3,0,4,0,99 outputs whatever it gets as input, then halts.
     */
    @Test
    public void testExecuteInputOutput() {
        IntSupplier input = () -> 38;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,0,4,0,99", input, output);
        
        Program result = program.execute();
        
        Assertions.assertEquals(List.of(38), outputValues);
        Assertions.assertEquals(List.of(38, 0, 4, 0, 99), result.getMemory());
    }
    
    /**
     * Test case for {@link Program#execute()}, for a program with input handling, without providing any input.
     * 
     * The program 3,0,4,0,99 outputs whatever it gets as input, then halts.
     */
    @Test
    public void testExecuteNoInput() {
        Program program = Program.parse("3,0,4,0,99");
        
        Assertions.assertThrows(NoSuchElementException.class, program::execute);
    }
    
    /** Test case for {@link Program#execute()} including parameter modes. */
    @Test
    public void testExecuteParameterModes() {
        Program program = Program.parse("1002,4,3,4,33");
            
        Program result = program.execute();
            
        Assertions.assertEquals(List.of(1002, 4, 3, 4, 99), result.getMemory());
    }
    
    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecuteNegativeValues() {
        Program program = Program.parse("1101,100,-1,4,0");

        Program result = program.execute();

        Assertions.assertEquals(List.of(1101, 100, -1, 4, 99), result.getMemory());
    }
    
    /** Test case for {@link Program#execute()}. */
    @Test
    public void testDay5Part1() throws IOException {
        String programText;
        Path path = LinesSolver.toPath(getClass(), "input-day05-2019.txt");
        try (Stream<String> lines = Files.lines(path)) {
            programText = lines.findFirst().orElseThrow();
        }
        IntSupplier input = () -> 1;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse(programText, input, output);
        
        program.execute();
        
        boolean allTestsSuccessful = outputValues.stream()
                .limit(outputValues.size() - 1)
                .allMatch(outputValue -> outputValue.intValue() == 0);
        Assertions.assertTrue(allTestsSuccessful);
        Assertions.assertEquals(15508323, outputValues.get(outputValues.size() - 1).intValue());
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestEqualTo8PositionMode8() {
        IntSupplier input = () -> 8;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,9,8,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestEqualTo8PositionMode7() {
        IntSupplier input = () -> 7;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,9,8,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestEqualTo8PositionMode9() {
        IntSupplier input = () -> 9;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,9,8,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestLessThan8PositionMode8() {
        IntSupplier input = () -> 8;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,9,7,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestLessThan8PositionMode7() {
        IntSupplier input = () -> 7;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,9,7,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestLessThan8PositionMode9() {
        IntSupplier input = () -> 9;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,9,7,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestEqualTo8ImmediateMode8() {
        IntSupplier input = () -> 8;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1108,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestEqualTo8ImmediateMode7() {
        IntSupplier input = () -> 7;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1108,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestEqualTo8ImmediateMode9() {
        IntSupplier input = () -> 9;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1108,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestLessThan8ImmediateMode8() {
        IntSupplier input = () -> 8;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1107,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestLessThan8ImmediateMode7() {
        IntSupplier input = () -> 7;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1107,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     */
    @Test
    public void testExecuteTestLessThan8ImmediateMode9() {
        IntSupplier input = () -> 9;
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1107,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0), outputValues);
    }
}
