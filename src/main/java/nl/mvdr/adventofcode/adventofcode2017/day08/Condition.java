package nl.mvdr.adventofcode.adventofcode2017.day08;

import java.util.Map;
import java.util.function.Predicate;

/**
 * The condition part of an instruction.
 *
 * @author Martijn van de Rijdt
 */
class Condition implements Predicate<Map<String, Integer>> {
    
    private final String registerName;
    private final ConditionOperator operator;
    private final int value;
    
    /**
     * Constructor.
     * 
     * @param registerName the register to modify 
     * @param operator whether to increase or decrease that register's value
     * @param value the amount by which to increase or decrease it
     */
    Condition(String registerName, ConditionOperator operator, int value) {
        super();
        this.registerName = registerName;
        this.operator = operator;
        this.value = value;
    }
    
    @Override
    public boolean test(Map<String, Integer> registers) {
        int registerValue = registers.getOrDefault(registerName, Integer.valueOf(0)).intValue();
        return operator.test(Integer.valueOf(registerValue), Integer.valueOf(value));
    }
    
    @Override
    public String toString() {
        return registerName + " " + operator + " " + value;
    }
}
