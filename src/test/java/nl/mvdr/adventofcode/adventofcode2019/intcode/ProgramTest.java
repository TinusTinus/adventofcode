package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
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

        Assertions.assertEquals(List.of(1L, 9L, 10L, 3L, 2L, 3L, 11L, 0L, 99L, 30L, 40L, 50L), program.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute0() {
        Program program = Program.parse("1,9,10,3,2,3,11,0,99,30,40,50");

        Program result = program.execute();

        Assertions.assertEquals(List.of(3500L, 9L, 10L, 70L, 2L, 3L, 11L, 0L, 99L, 30L, 40L, 50L), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute1() {
        Program program = Program.parse("1,0,0,0,99");

        Program result = program.execute();

        Assertions.assertEquals(List.of(2L, 0L, 0L, 0L, 99L), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute2() {
        Program program = Program.parse("2,3,0,3,99");

        Program result = program.execute();

        Assertions.assertEquals(List.of(2L, 3L, 0L, 6L, 99L), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute3() {
        Program program = Program.parse("2,4,4,5,99,0");

        Program result = program.execute();

        Assertions.assertEquals(List.of(2L, 4L, 4L, 5L, 99L, 9801L), result.getMemory());
    }

    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecute4() {
        Program program = Program.parse("1,1,1,4,99,5,6,0,99");

        Program result = program.execute();

        Assertions.assertEquals(List.of(30L, 1L, 1L, 4L, 2L, 5L, 6L, 0L, 99L), result.getMemory());
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
        program = program.set(1, 12L);
        program = program.set(2, 2L);
            
        Program result = program.execute();
            
        Assertions.assertEquals(9581917L, result.getMemory().get(0).longValue());
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
        program = program.set(1, 25L);
        program = program.set(2, 5L);
            
        Program result = program.execute();
            
        Assertions.assertEquals(19690720L, result.getMemory().get(0).longValue());
    }

    /**
     * Test case for {@link Program#execute()} including input / output handling.
     * 
     * The program 3,0,4,0,99 outputs whatever it gets as input, then halts.
     */
    @Test
    public void testExecuteInputOutput() {
        LongSupplier input = () -> 38L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,0,4,0,99", input, output);
        
        Program result = program.execute();
        
        Assertions.assertEquals(List.of(38L), outputValues);
        Assertions.assertEquals(List.of(38L, 0L, 4L, 0L, 99L), result.getMemory());
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
            
        Assertions.assertEquals(List.of(1002L, 4L, 3L, 4L, 99L), result.getMemory());
    }
    
    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecuteNegativeValues() {
        Program program = Program.parse("1101,100,-1,4,0");

        Program result = program.execute();

        Assertions.assertEquals(List.of(1101L, 100L, -1L, 4L, 99L), result.getMemory());
    }
    
    /** Test case for {@link Program#execute()}. */
    @Test
    public void testDay5Part1() throws IOException {
        String programText;
        Path path = LinesSolver.toPath(getClass(), "input-day05-2019.txt");
        try (Stream<String> lines = Files.lines(path)) {
            programText = lines.findFirst().orElseThrow();
        }
        LongSupplier input = () -> 1L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse(programText, input, output);
        
        program.execute();
        
        outputValues.stream()
                .limit(outputValues.size() - 1)
                .forEach(outputValue -> Assertions.assertEquals(0L, outputValue.longValue()));
        Assertions.assertEquals(15508323L, outputValues.get(outputValues.size() - 1).longValue());
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using position mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestEqualTo8PositionMode8() {
        LongSupplier input = () -> 8L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,9,8,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using position mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestEqualTo8PositionMode7() {
        LongSupplier input = () -> 7L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,9,8,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using position mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestEqualTo8PositionMode9() {
        LongSupplier input = () -> 9L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,9,8,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using position mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestLessThan8PositionMode8() {
        LongSupplier input = () -> 8L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,9,7,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using position mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestLessThan8PositionMode7() {
        LongSupplier input = () -> 7L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,9,7,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using position mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestLessThan8PositionMode9() {
        LongSupplier input = () -> 9L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,9,7,9,10,9,4,9,99,-1,8", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using immediate mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestEqualTo8ImmediateMode8() {
        LongSupplier input = () -> 8L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1108,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using immediate mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestEqualTo8ImmediateMode7() {
        LongSupplier input = () -> 7L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1108,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using immediate mode, consider whether the input is equal to 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestEqualTo8ImmediateMode9() {
        LongSupplier input = () -> 9L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1108,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestLessThan8ImmediateMode8() {
        LongSupplier input = () -> 8L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1107,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestLessThan8ImmediateMode7() {
        LongSupplier input = () -> 7L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1107,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteTestLessThan8ImmediateMode9() {
        LongSupplier input = () -> 9L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1107,-1,8,3,4,3,99", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Take an input, then output 0 if the input was zero or 1 if the input was non-zero,
     * using position mode.
     */
    @Test
    public void testExecuteTestJumpPositionMode0() {
        LongSupplier input = () -> 0L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Take an input, then output 0 if the input was zero or 1 if the input was non-zero,
     * using position mode.
     */
    @Test
    public void testExecuteTestJumpPositionMode1() {
        LongSupplier input = () -> 1L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1L), outputValues);
    }

    /**
     * Test case for {@link Program#execute()}.
     * 
     * Take an input, then output 0 if the input was zero or 1 if the input was non-zero,
     * using position mode.
     */
    @Test
    public void testExecuteTestJumpImmediateMode0() {
        LongSupplier input = () -> 0L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1105,-1,9,1101,0,0,12,4,12,99,1", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(0L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Take an input, then output 0 if the input was zero or 1 if the input was non-zero,
     * using immediate mode.
     */
    @Test
    public void testExecuteTestJumpImmediateMode1() {
        LongSupplier input = () -> 1L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse("3,3,1105,-1,9,1101,0,0,12,4,12,99,1", input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteJumpsAndTests7() {
        LongSupplier input = () -> 7L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse(
                "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,"
                + "1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99",
                input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(999L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteJumpsAndTests8() {
        LongSupplier input = () -> 8L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse(
                "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,"
                + "1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99",
                input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1000L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * Using immediate mode, consider whether the input is less than 8; output 1 (if it is) or 0 (if it is not).
     */
    @Test
    public void testExecuteJumpsAndTests9() {
        LongSupplier input = () -> 9L;
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValues::add;
        Program program = Program.parse(
                "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,"
                + "1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99",
                input, output);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1001L), outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * This program takes no input and produces a copy of itself as output.
     */
    @Test
    public void testExecuteQuine() {
        List<Long> outputValues = new ArrayList<>();
        Program program = Program.parse("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99")
                .withOutput(outputValues::add);
        
        program.execute();
        
        Assertions.assertEquals(
                List.of(109L, 1L, 204L, -1L, 1001L, 100L, 1L, 100L, 1008L, 100L, 16L, 101L, 1006L, 101L, 0L, 99L),
                outputValues);
    }
    
    /**
     * Test case for {@link Program#execute()}.
     * 
     * This program outputs a 16-digit number.
     */
    @Test
    public void testExecute16Digits() {
        List<Long> outputValues = new ArrayList<>();
        Program program = Program.parse("1102,34915192,34915192,7,4,7,99,0")
                .withOutput(outputValues::add);
        
        program.execute();
        
        Assertions.assertEquals(1, outputValues.size());
        Assertions.assertEquals(16, outputValues.get(0).toString().length());
    }
    
    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecuteLong() {
        List<Long> outputValues = new ArrayList<>();
        Program program = Program.parse("104,1125899906842624,99")
                .withOutput(outputValues::add);
        
        program.execute();
        
        Assertions.assertEquals(List.of(1125899906842624L), outputValues);
    }
    
    /** Test case for {@link Program#execute()}. */
    @Test
    public void testExecuteBoost() throws IOException {
        String programText;
        Path path = LinesSolver.toPath(getClass(), "input-day09-2019.txt");
        try (Stream<String> lines = Files.lines(path)) {
            programText = lines.findFirst().orElseThrow();
        }
        List<Long> outputValues = new ArrayList<>();
        Program program = Program.parse(programText, () -> 1L, outputValues::add);
        
        program.execute();
        
        Assertions.assertEquals(List.of(3638931938L), outputValues, "BOOST output: " + outputValues);
    }
}
