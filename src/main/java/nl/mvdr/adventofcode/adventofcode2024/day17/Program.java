package nl.mvdr.adventofcode.adventofcode2024.day17;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

record Program(long initialA, long initialB, long initialC, List<Long> program) {

    private static final Logger LOGGER = LoggerFactory.getLogger(Program.class);
    
    static Program parse(List<String> lines) {
        var a = getRegisterValue(lines.get(0));
        var b = getRegisterValue(lines.get(1));
        var c = getRegisterValue(lines.get(2));
        
        var program = getProgram(lines.get(4));
        
        return new Program(a, b, c, program);
    }

    private static long getRegisterValue(String line) {
        var registerValueString = line.substring(line.indexOf(": ") + 2);
        return Long.parseLong(registerValueString);
    }
    
    private static List<Long> getProgram(String line) {
        var programString = line.substring(9);
        return Stream.of(programString.split(","))
                .map(Long::valueOf)
                .toList();
    }
    
    String execute() {
        return execute(Optional.empty());
    }
    
    private String execute(Optional<String> expectedOuput) {
        var a = initialA;
        var b = initialB;
        var c = initialC;
        
        var instructionPointer = 0;
        
        var output = new StringBuilder();
        
        while (0 <= instructionPointer && instructionPointer < program.size()
                && (expectedOuput.isEmpty() || expectedOuput.orElseThrow().startsWith(output.toString()))) {
            var opcode = program.get(instructionPointer).intValue();
            var instruction = Instruction.fromOpcode(opcode);
            
            var operand = program.get(instructionPointer + 1).intValue();
            long operandValue = switch(instruction.getOperandType()) {
                case LITERAL -> operand;
                case COMBO -> switch(operand) {
                    case 0, 1, 2, 3 -> operand;
                    case 4 -> a;
                    case 5 -> b;
                    case 6 -> c;
                    default -> throw new IllegalStateException("Not a valid combo operand: " + operand);
                };
            };
            
            if (instruction == Instruction.ADV) {
                a = a >> operandValue;
                instructionPointer += 2;
            } else if (instruction == Instruction.BXL) {
                b = b ^ operandValue;
                instructionPointer += 2;
            } else if (instruction == Instruction.BST) {
                b = operandValue % 8;
                instructionPointer += 2;
            } else if (instruction == Instruction.JNZ) {
                if (a == 0) {
                    instructionPointer += 2;
                } else {
                    instructionPointer = Math.toIntExact(operandValue);
                }
            } else if (instruction == Instruction.BXC) {
                b = b ^ c;
                instructionPointer += 2;
            } else if (instruction == Instruction.OUT) {
                if (!output.isEmpty()) {
                    output.append(",");
                }
                output.append(operandValue % 8);
                instructionPointer += 2;
            } else if (instruction == Instruction.BDV) {
                b = a >> operandValue;
                instructionPointer += 2;
            } else if (instruction == Instruction.CDV) {
                c = a >> operandValue;
                instructionPointer += 2;
            }
        }
        
        return output.toString();
    }
    
    boolean outputs(String expectedOutput) {
        String output = execute(Optional.of(expectedOutput));
//        LOGGER.info("Expected output: {}, initial value for A = {}: {}", expectedOutput, Long.valueOf(initialA), output); // TODO clean up logging
        return expectedOutput.equals(output);
    }
    
    Program withInitialA(long a) {
        return new Program(a, initialB, initialC, program);
    }
}
