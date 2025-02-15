package nl.mvdr.adventofcode.adventofcode2017.day08;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An instruction.
 *
 * @author Martijn van de Rijdt
 */
class Instruction implements Consumer<Map<String, Integer>> {

    private final Operation operation;
    private final Condition condition;

    /**
     * Parses puzzle input.
     * 
     * @param lines puzzle input
     * @return list of instructions
     */
    static List<Instruction> parse(Stream<String> lines) {
        return lines
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line
                .map(Instruction::parseLine)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a single line from the input into an instruction.
     * 
     * @param line input text
     * @return instruction
     */
    private static Instruction parseLine(String line) { 
        String[] parts = line.split(" ");
        
        String registerToModify = parts[0];
        Operator operator = Operator.parse(parts[1]);
        int amount = Integer.parseInt(parts[2]);
        // parts[3] should be "if"
        String conditionRegister = parts[4];
        ConditionOperator conditionOperator = ConditionOperator.parse(parts[5]);
        int conditionValue = Integer.parseInt(parts[6]);
        
        Operation operation = new Operation(registerToModify, operator, amount);
        Condition condition = new Condition(conditionRegister, conditionOperator, conditionValue);
        
        return new Instruction(operation, condition);
    }
    
    /**
     * Constructor.
     * 
     * @param operation the operation
     * @param condition the condition
     */
    private Instruction(Operation operation, Condition condition) {
        super();
        this.operation = operation;
        this.condition = condition;
    }
    
    @Override
    public void accept(Map<String, Integer> registers) {
        if (condition.test(registers)) {
            operation.accept(registers);
        }
    }
    
    @Override
    public String toString() {
        return operation + " if " + condition;
    }
}
