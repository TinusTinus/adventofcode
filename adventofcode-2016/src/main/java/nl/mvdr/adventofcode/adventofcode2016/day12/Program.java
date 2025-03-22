package nl.mvdr.adventofcode.adventofcode2016.day12;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

record Program(List<Instruction> instructions) {

    private static final Logger LOGGER = LoggerFactory.getLogger(Program.class);
    
    static Program parse(Stream<String> input) {
        var instructions = input.map(Instruction::parse).toList();
        return new Program(instructions);
    }
    
    State execute() {
        var state = new State();
        while (state.instructionPointer() < instructions.size()) {
            LOGGER.debug("{}", state);
            state = instructions.get(state.instructionPointer()).execute(state);
        }
        return state;
    }
    
}
