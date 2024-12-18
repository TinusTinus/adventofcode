package nl.mvdr.adventofcode.adventofcode2024.day17;

import java.util.List;
import java.util.stream.Stream;

record Program(int initialA, int initialB, int initialC, List<Integer> program) {
    
    static Program parse(List<String> lines) {
        var a = getRegisterValue(lines.get(0));
        var b = getRegisterValue(lines.get(1));
        var c = getRegisterValue(lines.get(2));
        
        var program = getProgram(lines.get(4));
        
        return new Program(a, b, c, program);
    }

    private static int getRegisterValue(String line) {
        var registerValueString = line.substring(line.indexOf(": ") + 2);
        return Integer.parseInt(registerValueString);
    }
    
    private static List<Integer> getProgram(String line) {
        var programString = line.substring(9);
        return Stream.of(programString.split(","))
                .map(Integer::valueOf)
                .toList();
    }
    
    private static int divide(int a, int operandValue) {
        var numerator = a;
        var denominator = (int)Math.pow(2, operandValue);
        return numerator / denominator;
    }
    
    String execute() {
        var a = initialA;
        var b = initialB;
        var c = initialC;
        
        var instructionPointer = 0;
        
        var output = new StringBuilder();
        
        while (0 <= instructionPointer && instructionPointer < program.size()) {
            var opcode = program.get(instructionPointer).intValue();
            var instruction = Instruction.fromOpcode(opcode);
            
            var operand = program.get(instructionPointer + 1).intValue();
            int operandValue = switch(instruction.getOperandType()) {
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
                a = divide(a, operandValue);
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
                    instructionPointer = operandValue;
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
                b = divide(a, operandValue);
                instructionPointer += 2;
            } else if (instruction == Instruction.CDV) {
                c = divide(a, operandValue);
                instructionPointer += 2;
            }
        }
        
        return output.toString();
    }
    
    boolean outputs(String expectedOutput) {
        return expectedOutput.equals(execute()); // TODO implement this more efficiently!
    }
    
    Program withInitialA(int a) {
        return new Program(a, initialB, initialC, program);
    }
}
