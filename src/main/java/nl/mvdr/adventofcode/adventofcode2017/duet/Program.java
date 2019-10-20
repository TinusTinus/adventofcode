package nl.mvdr.adventofcode.adventofcode2017.duet;

import java.util.List;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of an execution of a duet program.
 *
 * @author Martijn van de Rijdt
 */
public class Program {

    private static final Logger LOGGER = LoggerFactory.getLogger(Program.class);
    
    private final String name;
    
    private final List<Instruction> instructions;
    
    private final State state;

    /**
     * Convenience constructor, for use when no communication between processes is needed.
     * 
     * @param instructions list of instructions making up the program
     */
    public Program(List<Instruction> instructions) {
        this(instructions, new State(), "Duet");
    }
    
    /**
     * Constructor.
     * 
     * @param instructions list of instructions making up the program
     * @param state state of the program
     * @param name of this program
     */
    public Program(List<Instruction> instructions, State state, String name) {
        super();
        
        this.instructions = instructions;
        this.state = state;
        this.name = name;
    }
    
    /**
     * Checks whether the next instruction can be executed.
     * 
     * @return true if the next instruction can be executed; false if the program has terminated or is blocked
     */
    public boolean canProceed() {
        boolean result = 0 <= state.getInstructionPointer();
        result = result && state.getInstructionPointer() < instructions.size();
        result = result && instructions.get(state.getInstructionPointer()).canProceed(state);
        return result;
    }

    /**
     * Executes a single instruction.
     * 
     * @return program with updated state
     */
    private Program executeInstruction() {
        if (!canProceed()) {
            throw new IllegalStateException(name + ": unable to execute the next instruction");
        }
        
        Instruction instruction = instructions.get(state.getInstructionPointer());
        LOGGER.debug("{}: {} - {}", name, state, instruction);
        State updatedState = instruction.execute(state);
        return new Program(instructions, updatedState, name);
    }
    
    /**
     * Continues executing instructions until no longer possible.
     * 
     * @return program with updated state
     */
    public Program executeInstructions() {
        return executeInstructionsWhile(Program::canProceed);
    }
    
    /**
     * Continues executing instructions as long as the given condition applies.
     * 
     * @param condition condition
     * @return program with updated state
     */
    public Program executeInstructionsWhile(Predicate<Program> condition) {
        Program result = this;
        while (condition.test(result)) {
            result = result.executeInstruction();
        }
        return result;
    }
    
    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Program [instructions=" + instructions + ", state=" + state + "]";
    }
}
