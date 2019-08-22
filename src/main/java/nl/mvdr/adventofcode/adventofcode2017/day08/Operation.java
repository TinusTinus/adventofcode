package nl.mvdr.adventofcode.adventofcode2017.day08;

import java.util.Map;
import java.util.function.Consumer;

/**
 * The operation part of an instruction.
 *
 * @author Martijn van de Rijdt
 */
class Operation implements Consumer<Map<String, Integer>> {
    
    private final String registerName;
    private final Operator operator;
    private final int amount;
    
    /**
     * Constructor.
     * 
     * @param registerName the register to modify 
     * @param operator whether to increase or decrease that register's value
     * @param amount the amount by which to increase or decrease it
     */
    Operation(String registerName, Operator operator, int amount) {
        super();
        this.registerName = registerName;
        this.operator = operator;
        this.amount = amount;
    }

    @Override
    public void accept(Map<String, Integer> registers) {
        int registerValue = registers.getOrDefault(registerName, Integer.valueOf(0)).intValue();
        int newValue = operator.applyAsInt(registerValue, amount);
        registers.put(registerName, Integer.valueOf(newValue));
    }
    
    @Override
    public String toString() {
        return registerName + " " + operator + " " + amount;
    }
}
