package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
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
    
    private final List<Integer> memory;
    private final int instructionPointer;
    private final int relativeBase;
    private final boolean done;
    private final IntSupplier input;
    private final IntConsumer output;

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
        IntSupplier dummyInput = () -> { throw new NoSuchElementException("No input available"); };
        IntConsumer dummyOutput = i -> LOGGER.warn("Unexpected output: {}", Integer.valueOf(i));
        
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
    public static Program parse(String programText, IntSupplier input, IntConsumer output) {
        List<Integer> integers = Stream.of(programText.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        return new Program(List.copyOf(integers), 0, 0, false, input, output);
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
    private Program(List<Integer> memory, int instructionPointer, int relativeBase, boolean done, IntSupplier input,
            IntConsumer output) {
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
    public Program set(int address, int value) {
        List<Integer> newIntegers = new ArrayList<>(memory);
        newIntegers.set(address, Integer.valueOf(value));
        return new Program(List.copyOf(newIntegers), instructionPointer, relativeBase, done, input, output);
    }
    
    /**
     * Returns a copy of this program, with the given input.
     * 
     * @param newInput new input
     * @return updated copy of the program
     */
    public Program withInput(IntSupplier newInput) {
        return new Program(memory, instructionPointer, relativeBase, done, newInput, output);
    }
    
    /**
     * Returns a copy of this program, with the given values used as its input.
     * 
     * @param inputValues input for the program
     * @return updated copy of the program
     */
    public Program withInput(int... inputValues) {
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
     * @see #withOutput(BlockingQueue)
     */
    public Program withInput(BlockingQueue<Integer> queue) {
        return withInput(() -> {
            int result;
            try {
                result = queue.take().intValue();
            } catch (InterruptedException e) {
                handle(e);
                result = 0;
            }
            return result;
        });
    }
    
    /**
     * Returns a copy of this program, with the given output callback.
     * 
     * @param newOutput new output callback
     * @return updated copy of the program
     */
    public Program withOutput(IntConsumer newOutput) {
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
     * @see #withInput(BlockingQueue)
     */
    public Program withOutput(BlockingQueue<Integer> queue) {
        return withOutput(value -> {
            try {
                queue.put(Integer.valueOf(value));
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
     * @return program state after termination
     */
    public Program execute() {
        LOGGER.debug("Initial program state: {}", this);
        Program result = this;
        while (!result.done) {
            int instructionPointerValue = result.memory.get(result.instructionPointer).intValue();
            
            int opcode = instructionPointerValue % 100;
            Instruction instruction = Instruction.of(opcode);
            
            instructionPointerValue = instructionPointerValue / 100;
            List<ParameterMode> parameterModes = new ArrayList<>(instruction.getParameterCount());
            for (int i = 0; i != instruction.getParameterCount(); i++) {
                parameterModes.add(ParameterMode.of(instructionPointerValue % 10));
                instructionPointerValue = instructionPointerValue / 10;
            }
            
            LOGGER.debug("Executing instruction {} {}", instruction, parameterModes);
            result = instruction.execute(result, parameterModes);
            LOGGER.debug("Updated program state: {}", result);
        }
        return result;
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
     * Performs the less-than operator.
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
    private Program perform(IntBinaryOperator operator, List<ParameterMode> parameterModes) {
        // validate modes
        if (parameterModes.size() != 3) {
            throw new IllegalArgumentException("Unexpected number of paramters: " + parameterModes);
        }
        if (parameterModes.get(2) != ParameterMode.POSITION) {
            throw new IllegalArgumentException("Unexpected parameter mode for write parameter: " + parameterModes.get(2));
        }
        
        LOGGER.debug("Performing {} {} ({}) {} ({}) {} ({})",
                memory.get(instructionPointer),
                memory.get(instructionPointer + 1),
                parameterModes.get(0),
                memory.get(instructionPointer + 2),
                parameterModes.get(1),
                memory.get(instructionPointer + 3),
                parameterModes.get(2));
        
        int value0 = getValue(instructionPointer + 1, parameterModes.get(0));
        int value1 = getValue(instructionPointer + 2, parameterModes.get(1));
        
        int resultValue = operator.applyAsInt(value0, value1);
        
        int resultAddress = memory.get(instructionPointer + 3).intValue();
        
        List<Integer> newMemory = setValue(resultAddress, parameterModes.get(2), resultValue);
        
        return new Program(List.copyOf(newMemory), instructionPointer + 4, relativeBase, false, input, output);
    }
    
    /**
     * Reads a single value from the input and stores it.
     * 
     * @param parameterModes parameter modes
     * @return updated program state
     */
    Program storeInput(List<ParameterMode> parameterModes) {
        // validate modes
        if (!List.of(ParameterMode.POSITION).equals(parameterModes)) {
            throw new IllegalArgumentException("Unexpected parameter modes: " + parameterModes);
        }
        
        Integer inputValue = Integer.valueOf(this.input.getAsInt());
        
        int resultAddress = memory.get(instructionPointer + 1).intValue();
        
        List<Integer> newMemory = setValue(resultAddress, parameterModes.get(0), inputValue.intValue());
        
        return new Program(List.copyOf(newMemory), instructionPointer + 2, relativeBase, false, input, output);
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
        
        Integer outputValue = Integer.valueOf(getValue(instructionPointer + 1, parameterModes.get(0)));
        
        LOGGER.debug("Output: {}", outputValue);
        output.accept(outputValue.intValue());
        
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
    private Program jumpIf(IntPredicate guard, List<ParameterMode> parameterModes) {
        // validate modes
        if (parameterModes.size() != 2) {
            throw new IllegalArgumentException("Unexpected number of paramters: " + parameterModes);
        }
        
        int value = getValue(instructionPointer + 1, parameterModes.get(0));
        
        int newInstructionPointer;
        if (guard.test(value)) {
            // Jump!
            newInstructionPointer = getValue(instructionPointer + 2, parameterModes.get(1));
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
            throw new IllegalArgumentException("Unexpected number of paramters: " + parameterModes);
        }
        
        int value = getValue(instructionPointer + 1, parameterModes.get(0));
        
        return new Program(memory, instructionPointer + 2, relativeBase + value, done, input, output);
    }
    
    /**
     * Converts the given address to an immediate address.
     * 
     * @param address address
     * @param mode parameter mode
     * @return immediate address
     */
    private int getAddress(int address, ParameterMode mode) {
        return switch(mode) {
            case IMMEDIATE -> address;
            case POSITION -> getValue(address);
            case RELATIVE -> getValue(address) + relativeBase;
            default -> throw new IllegalArgumentException("Unexpected mode: " + mode);
        };
    }
    
    /**
     * Reads a parameter value from memory.
     * 
     * @param address address from which to retrieve a parameter value
     * @param mode parameter mode
     * @return value
     */
    private int getValue(int address, ParameterMode mode) {
        return getValue(getAddress(address, mode));
    }
    
    /**
     * Reads a parameter value from memory (immediate mode).
     * 
     * @param address address to read
     * @return value
     */
    private int getValue(int address) {
        int result;
        if (address < memory.size()) {
            result = memory.get(address).intValue();
        } else {
            result = 0;
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
    private List<Integer> setValue(int address, ParameterMode mode, int value) {
        int resultAddress = switch(mode) {
            case POSITION -> address;
            case RELATIVE -> address + relativeBase;
            default -> throw new IllegalArgumentException("Unsupported parameter mode: " + mode);
        };
        
        List<Integer> result = new ArrayList<>(memory);
        for (int i = result.size(); i <= resultAddress; i++) {
            result.add(Integer.valueOf(i));
        }
        
        LOGGER.debug("Writing value {} to address {}", Integer.valueOf(value), Integer.valueOf(resultAddress));
        result.set(resultAddress, Integer.valueOf(value));
        return result;
    }
    
    public List<Integer> getMemory() {
        return memory;
    }
    
    @Override
    public String toString() {
        return "Program [memory=" + memory + ", instructionPointer=" + instructionPointer + ", done=" + done + "]";
    }
}
