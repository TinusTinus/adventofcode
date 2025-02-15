package nl.mvdr.adventofcode.adventofcode2016.day08;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of an instruction.
 *
 * @author Martijn van de Rijdt
 */
class Instruction {
    
    private final Operation operation;
    private final int a;
    private final int b;

    static Instruction parse(String string) {
        Instruction result = null;
        for (OperationImpl operation : OperationImpl.values()) {
            Pattern pattern = Pattern.compile(operation.getRegex());
            Matcher matcher = pattern.matcher(string);
            
            if (matcher.matches()) {
                int a = Integer.parseInt(matcher.group(1));
                int b = Integer.parseInt(matcher.group(2));
                result = new Instruction(operation, a, b);
            }
        }
        Objects.requireNonNull(result, "Unable to parse: " + string);
        return result;
    }
    
    /**
     * Constructor.
     * 
     * @param operation operation
     * @param a first parameter
     * @param b second parameter
     */
    private Instruction(Operation operation, int a, int b) {
        super();
        this.operation = operation;
        this.a = a;
        this.b = b;
    }
    
    /**
     * Executes this instruction.
     * 
     * @param input screen before execution of the instruction
     * @return updated screen
     */
    Screen execute(Screen input) {
        return operation.execute(input, a, b);
    }
}
