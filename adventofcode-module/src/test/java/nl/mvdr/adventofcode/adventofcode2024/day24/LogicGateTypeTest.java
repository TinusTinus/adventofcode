package nl.mvdr.adventofcode.adventofcode2024.day24;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LogicGateTypeTest {
    
    @ParameterizedTest
    @CsvSource({
        "false, AND, false, false",
        "false, AND, true,  false",
        " true, AND, false, false",
        " true, AND, true,   true",
        "false,  OR, false, false",
        "false,  OR,  true,  true",
        " true,  OR, false,  true",
        " true,  OR,  true,  true",
        "false, XOR, false, false",
        "false, XOR,  true,  true",
        " true, XOR, false,  true",
        " true, XOR,  true, false"
    })
    void test(boolean lhs, LogicGateType type, boolean rhs, boolean expected) {
        var result = type.apply(lhs, rhs);
        
        Assertions.assertEquals(Boolean.valueOf(expected), Boolean.valueOf(result));
    }
}
