package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public record Program(List<Instruction> instructions) {

    private static final Logger LOGGER = LoggerFactory.getLogger(Program.class);
    
    public static Program parse(Stream<String> input) {
        var instructions = input.map(Instruction::parse).toList();
        return new Program(instructions);
    }
    
    public State execute(State startState) {
        State state = startState;
        while (state.instructionPointer() < instructions.size()) {
            LOGGER.debug("{}", state);
            state = instructions.get(state.instructionPointer()).execute(state);
        }
        return state;
    }
    
}
