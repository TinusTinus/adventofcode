package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of an Intcode program, including its current state.
 *
 * @author Martijn van de Rijdt
 */
public class Program {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Program.class);
    
    private final List<Long> memory;
    private final int instructionPointer;
    private final int relativeBase;
    private final boolean done;
    private final LongSupplier input;
    private final LongConsumer output;

    /**
     * Creates a new program, without any support for input or output handling.
     * 
     * Note: input / output handling can be added later using {@code withInput()}
     * and {@code withOutput()} methods.
     * 
     * @param programText program text: a comma-separated list of integers
     * @return program
     */
    public static Program parse(String programText) {
        LongSupplier dummyInput = () -> { throw new NoSuchElementException("No input available"); };
        LongConsumer dummyOutput = i -> LOGGER.warn("Unexpected output: {}", Long.valueOf(i));
        
        return parse(programText, dummyInput, dummyOutput);
    }

    /**
     * Creates a new program.
     * 
     * @param programText program text: a comma-separated list of integers
     * @param input source of input
     * @param output target to send output
     * @return program
     */
    public static Program parse(String programText, LongSupplier input, LongConsumer output) {
        List<Long> integers = Stream.of(programText.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toUnmodifiableList());
        
        return new Program(integers, 0, 0, false, input, output);
    }
    
    /**
     * Constructor.
     * 
     * @param memory list of values making up this program and its memory
     * @param instructionPointer current value of the instruction pointer
     * @param relativeBase relative base
     * @param done whether program execution has halted
     * @param input source of input
     * @param output target to send output
     */
    private Program(List<Long> memory, int instructionPointer, int relativeBase, boolean done, LongSupplier input,
            LongConsumer output) {
        super();
        this.memory = memory;
        this.instructionPointer = instructionPointer;
        this.relativeBase = relativeBase;
        this.done = done;
        this.input = input;
        this.output = output;
    }
    
    /**
     * Updates the value at the given index.
     * 
     * @param address memory index to update
     * @param value value to write to index
     * @return copy of this program with the updated value
     */
    public Program set(int address, long value) {
        List<Long> newMemory = setValue(address, ParameterMode.POSITION, value);
        return new Program(newMemory, instructionPointer, relativeBase, done, input, output);
    }
    
    /**
     * Returns a copy of this program, with the given input.
     * 
     * @param newInput new input
     * @return updated copy of the program
     */
    public Program withInput(LongSupplier newInput) {
        return new Program(memory, instructionPointer, relativeBase, done, newInput, output);
    }
    
    /**
     * Returns a copy of this program, with the given values used as its input.
     * 
     * @param inputValues input for the program
     * @return updated copy of the program
     */
    public Program withInputValues(long... inputValues) {
        return withInput(Arrays.stream(inputValues).iterator()::next);
    }
    
    /**
     * Returns a copy of this program, using the given queue to get its input.
     * 
     * This may be particularly useful in combination with another {@link Program}
     * using the same queue as its output.
     * 
     * @param queue blocking queue, providing input for the program
     * @return updated copy of the program
     * @see #withBlockingQueueOutput(BlockingQueue)
     */
    public Program withBlockingQueueInput(BlockingQueue<Long> queue) {
        return withInput(() -> {
            long result;
            try {
                result = queue.take().longValue();
            } catch (InterruptedException e) {
                handle(e);
                result = 0;
            }
            return result;
        });
    }
    
    /**
     * Returns a copy of this program, using the given queue to get its input.
     * 
     * @param queue providing input for the program; in case the queue is empty, the value -1 is provided instead
     * @return updated copy of the program
     */
    public Program withQueueInput(Queue<Long> queue) {
        return withInput(() -> {
            long result;
            if (queue.isEmpty()) {
                result = -1L;
            } else {
                result = queue.poll().longValue();
            }
            return result;
        });
    }
    
    /**
     * Returns a copy of this program, using the given String as input, as ASCII values.
     * 
     * @param inputString input string
     * @return updated copy of the program
     */
    public Program withAsciiInput(String inputString) {
        Queue<Long> queue = new LinkedList<>();
        inputString.chars()
                .asLongStream()
                .boxed()
                .forEach(queue::offer);
        return withInput(queue::poll);
    }
    
    /**
     * Returns a copy of this program, with the given output callback.
     * 
     * @param newOutput new output callback
     * @return updated copy of the program
     */
    public Program withOutput(LongConsumer newOutput) {
        return new Program(memory, instructionPointer, relativeBase, done, input, newOutput);
    }
    
    /**
     * Returns a copy of this program, using the given queue to write its output to.
     * 
     * This may be particularly useful in combination with another {@link Program}
     * using the same queue as its input.
     * 
     * @param queue blocking queue, to which to write the program's output
     * @return updated copy of the program
     * @see #withBlockingQueueInput(BlockingQueue)
     */
    public Program withBlockingQueueOutput(BlockingQueue<Long> queue) {
        return withOutput(value -> {
            try {
                queue.put(Long.valueOf(value));
            } catch (InterruptedException e) {
                handle(e);
            }
        });
    }
    
    /**
     * Handles an {@link InterruptedException} by interrupting the current thread.
     * 
     * @param interruptedException exception
     */
    private void handle(InterruptedException interruptedException) {
        LOGGER.error("Unexpected interrupt.", interruptedException);
        Thread.currentThread().interrupt();
    }
    
    /**
     * Executes this program.
     * 
     * @return program state after the program has halted
     */
    public Program execute() {
        return executeUntil(_ -> false);
    }
    
    /**
     * Executes this program until it either halts or is about to perform an {@link Instruction#INPUT}.
     * 
     * @return program state
     */
    public Program executeUntilNextInput() {
        return executeUntil(p -> p.nextInstruction() == Instruction.INPUT);
    }
    
    /** @return the next instruction to be executed by this program */
    private Instruction nextInstruction() {
        long instructionPointerValue = memory.get(instructionPointer).longValue();
        int opcode = Math.toIntExact(instructionPointerValue % 100L);
        return Instruction.of(opcode);
    }
    
    /**
     * Executes this program until it halts, or the given condition applies.
     * 
     * @return program state after the program has halted
     */
    private Program executeUntil(Predicate<Program> guard) {
        LOGGER.trace("Initial program state: {}", this);
        Program result = this;
        while (!result.done && !guard.test(result)) {
            result = result.executeInstruction();
            LOGGER.trace("Updated program state: {}", result);
        }
        return result;
    }
    
    /**
     * Executes a single instruction.
     * 
     * @return updated program state
     */
    public Program executeInstruction() {
        if (done) {
            throw new IllegalStateException("This program has already halted.");
        }
        
        long instructionPointerValue = memory.get(instructionPointer).longValue();
        
        int opcode = Math.toIntExact(instructionPointerValue % 100L);
        Instruction instruction = Instruction.of(opcode);
        
        instructionPointerValue = instructionPointerValue / 100L;
        List<ParameterMode> parameterModes = new ArrayList<>(instruction.getParameterCount());
        for (int i = 0; i != instruction.getParameterCount(); i++) {
            parameterModes.add(ParameterMode.of(Math.toIntExact(instructionPointerValue % 10L)));
            instructionPointerValue = instructionPointerValue / 10L;
        }
        
        LOGGER.trace("Executing instruction {} {}", instruction, parameterModes);
        return instruction.execute(this, parameterModes);
    }

    
    /** @return updated program state after halting */
    Program halt(List<ParameterMode> parameterModes) {
        // validate modes
        if (!parameterModes.isEmpty()) {
            throw new IllegalArgumentException("No parameters expected, got: " + parameterModes);
        }
        
        return new Program(memory, instructionPointer, relativeBase, true, input, output);
    }
    
    /**
     * Performs addition.
     * 
     * @param parameterModes parameter modes
     * @return updated program state 
     */
    Program add(List<ParameterMode> parameterModes) {
        return perform((i, j) -> i + j, parameterModes);
    }
    
    /** 
     * Performs multiplication.
     * 
     * @param parameterModes parameter modes
     * @return updated program state 
     */
    Program multiply(List<ParameterMode> parameterModes) {
        return perform((i, j) -> i * j, parameterModes);
    }
    
    /** 
     * Performs the less-than operator.
     * 
     * @param parameterModes parameter modes
     * @return updated program state 
     */
    Program isLessThan(List<ParameterMode> parameterModes) {
        return perform((i, j) -> i < j ? 1 : 0, parameterModes);
    }
    
    /** 
     * Performs the equals operator.
     * 
     * @param parameterModes parameter modes
     * @return updated program state 
     */
    Program equals(List<ParameterMode> parameterModes) {
        return perform((i, j) -> i == j ? 1 : 0, parameterModes);
    }
    
    /**
     * Performs a binary integer operation.
     * 
     * @param operator operator to perform
     * @param parameterModes parameter modes
     * @return updated program state after performing the given operator
     */
    private Program perform(LongBinaryOperator operator, List<ParameterMode> parameterModes) {
        // validate modes
        if (parameterModes.size() != 3) {
            throw new IllegalArgumentException("Unexpected number of parameters: " + parameterModes);
        }
        
        LOGGER.trace("Performing {} {} ({}) {} ({}) {} ({})",
                memory.get(instructionPointer),
                memory.get(instructionPointer + 1),
                parameterModes.get(0),
                memory.get(instructionPointer + 2),
                parameterModes.get(1),
                memory.get(instructionPointer + 3),
                parameterModes.get(2));
        
        long value0 = getValue(instructionPointer + 1, parameterModes.get(0));
        long value1 = getValue(instructionPointer + 2, parameterModes.get(1));
        
        long resultValue = operator.applyAsLong(value0, value1);
        
        long resultAddress = memory.get(instructionPointer + 3).longValue();
        
        List<Long> newMemory = setValue(Math.toIntExact(resultAddress), parameterModes.get(2), resultValue);
        
        return new Program(newMemory, instructionPointer + 4, relativeBase, false, input, output);
    }
    
    /**
     * Reads a single value from the input and stores it.
     * 
     * @param parameterModes parameter modes
     * @return updated program state
     */
    Program storeInput(List<ParameterMode> parameterModes) {
        // validate modes
        if (parameterModes.size() != 1) {
            throw new IllegalArgumentException("Unexpected number of parameters: " + parameterModes);
        }
        
        long inputValue = this.input.getAsLong();
        
        long resultAddress = memory.get(instructionPointer + 1).longValue();
        
        List<Long> newMemory = setValue(Math.toIntExact(resultAddress), parameterModes.get(0), inputValue);
        
        return new Program(newMemory, instructionPointer + 2, relativeBase, false, input, output);
    }
    
    /**
     * Outputs a single value from memory.
     * 
     * @param parameterModes parameter modes
     * @return updated program state
     */
    Program output(List<ParameterMode> parameterModes) {
        // validate modes
        if (parameterModes.size() != 1) {
            throw new IllegalArgumentException("Unexpected parameter modes: " + parameterModes);
        }
        
        long outputValue = getValue(instructionPointer + 1, parameterModes.get(0));
        
        output.accept(outputValue);
        
        return new Program(memory, instructionPointer + 2, relativeBase, false, input, output);
    }
    
    /**
     * If the first parameter is non-zero, this instruction sets the instruction pointer to the
     * value from the second parameter. Otherwise, it does nothing.
     * 
     * @param parameterModes parameter modes
     * @return updated program state
     */
    Program jumpIfTrue(List<ParameterMode> parameterModes) {
        return jumpIf(i -> i != 0, parameterModes);
    }
    
    /**
     * If the first parameter is equal to zero, this instruction sets the instruction pointer to the
     * value from the second parameter. Otherwise, it does nothing.
     * 
     * @param parameterModes parameter modes
     * @return updated program state
     */
    Program jumpIfFalse(List<ParameterMode> parameterModes) {
        return jumpIf(i -> i == 0, parameterModes);
    }
    
    /**
     * If the given predicate applies to the first parameter, this instruction sets the instruction pointer to the
     * value from the second parameter. Otherwise, it does nothing.
     * 
     * @param guard test for the first parameter
     * @param parameterModes parameter modes
     * @return updated program state
     */
    private Program jumpIf(LongPredicate guard, List<ParameterMode> parameterModes) {
        // validate modes
        if (parameterModes.size() != 2) {
            throw new IllegalArgumentException("Unexpected number of parameters: " + parameterModes);
        }
        
        long value = getValue(instructionPointer + 1, parameterModes.get(0));
        
        int newInstructionPointer;
        if (guard.test(value)) {
            // Jump!
            newInstructionPointer = Math.toIntExact(getValue(instructionPointer + 2, parameterModes.get(1)));
        } else {
            // Do not jump. Just move on to the next instruction.
            newInstructionPointer = instructionPointer + 3;
        }
        
        return new Program(memory, newInstructionPointer, relativeBase, done, input, output);
    }
    
    /**
     * Adjusts the relative base by the value of its only parameter. The relative
     * base increases (or decreases, if the value is negative) by the value of the
     * parameter.
     * 
     * For example, if the relative base is 2000, then after the instruction 109,19,
     * the relative base would be 2019. If the next instruction were 204,-34, then
     * the value at address 1985 would be output.
     * 
     * @param parameterModes parameter modes
     * @return updated program state
     */
    Program adjustRelativeBase(List<ParameterMode> parameterModes) {
        // validate modes
        if (parameterModes.size() != 1) {
            throw new IllegalArgumentException("Unexpected number of parameters: " + parameterModes);
        }
        
        long value = getValue(instructionPointer + 1, parameterModes.get(0));
        
        return new Program(memory, instructionPointer + 2, relativeBase + Math.toIntExact(value), done, input, output);
    }
    
    /**
     * Reads a parameter value from memory.
     * 
     * @param address address from which to retrieve a parameter value
     * @param mode parameter mode
     * @return value
     */
    private long getValue(int address, ParameterMode mode) {
        long value = getValue(address);
        return switch (mode) {
            case IMMEDIATE -> value;
            case POSITION -> getValue(Math.toIntExact(value));
            case RELATIVE -> getValue(Math.toIntExact(value) + relativeBase);
            default -> throw new IllegalArgumentException("Unexpected mode: " + mode);
        };
    }
    
    /**
     * Reads a parameter value from memory (immediate mode).
     * 
     * @param address address to read
     * @return value
     */
    private long getValue(int address) {
        long result;
        if (address < memory.size()) {
            result = memory.get(address).longValue();
        } else {
            result = 0L;
        }
        return result;
    }
    
    /**
     * Sets the given value.
     * 
     * @param address address to write to
     * @param mode parameter mode; must not be {@link ParameterMode#IMMEDIATE}
     * @param value (immediate) value to write
     * @return updated copy of this program's memory
     */
    private List<Long> setValue(int address, ParameterMode mode, long value) {
        int resultAddress = switch(mode) {
            case POSITION -> address;
            case RELATIVE -> address + relativeBase;
            case IMMEDIATE -> throw new IllegalArgumentException("Unsupported parameter mode: " + mode);
        };
        
        List<Long> result = new ArrayList<>(memory);
        for (int i = result.size(); i <= resultAddress; i++) {
            result.add(Long.valueOf(0L));
        }
        
        result.set(resultAddress, Long.valueOf(value));
        return Collections.unmodifiableList(result);
    }
    
    public List<Long> getMemory() {
        return memory;
    }
    
    @Override
    public String toString() {
        return "Program [memory=" + memory + ", instructionPointer=" + instructionPointer + ", done=" + done + "]";
    }
}
