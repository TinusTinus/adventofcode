package nl.mvdr.adventofcode.adventofcode2024.day17;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LinesSolver;

public class Part1 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public String solve(Stream<String> linesStream) {
        var lines = linesStream.toList();
        
        var a = getRegisterValue(lines.get(0));
        var b = getRegisterValue(lines.get(1));
        var c = getRegisterValue(lines.get(2));
        
        var program = getProgram(lines.get(4));
        
        var instructionPointer = 0;
        
        var output = new StringBuilder();
        
        while (0 <= instructionPointer && instructionPointer < program.size()) {
            var opcode = program.get(instructionPointer).intValue();
            var instruction = Instruction.fromOpcode(opcode);
            
            var operand = program.get(instructionPointer).intValue();
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
            
            LOGGER.info("Executing instruction {} with operand value {}", instruction, Integer.valueOf(operandValue)); // TODO debug
            
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

    public static void main(String[] args) {
        var instance = new Part1();

        var result = instance.solve("input-day17-2024.txt");

        LOGGER.info(result);
    }
}
 