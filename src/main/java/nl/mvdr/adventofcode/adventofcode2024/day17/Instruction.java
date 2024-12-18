package nl.mvdr.adventofcode.adventofcode2024.day17;

import java.util.stream.Stream;

enum Instruction {
    
    ADV(0, OperandType.COMBO),
    BXL(1, OperandType.LITERAL),
    BST(2, OperandType.COMBO),
    JNZ(3, OperandType.LITERAL),
    BXC(4, OperandType.LITERAL),
    OUT(5, OperandType.COMBO),
    BDV(6, OperandType.COMBO),
    CDV(7, OperandType.COMBO);
    
    private final int opcode;
    private final OperandType operandType;

    static Instruction fromOpcode(int opcode) {
        return Stream.of(Instruction.values())
                .filter(instruction -> instruction.opcode == opcode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a valid opcode: " + opcode));
    }
    
    private Instruction(int opcode, OperandType operandType) {
        this.opcode = opcode;
        this.operandType = operandType;
    }
    
    OperandType getOperandType() {
        return operandType;
    }
}
