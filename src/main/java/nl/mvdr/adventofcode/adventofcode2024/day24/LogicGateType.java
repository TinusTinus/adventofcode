package nl.mvdr.adventofcode.adventofcode2024.day24;

import java.util.stream.Stream;

enum LogicGateType {
    AND("AND") {
        @Override
        boolean apply(boolean lhs, boolean rhs) {
            return lhs && rhs;
        }
    },
    OR("OR") {
        @Override
        boolean apply(boolean lhs, boolean rhs) {
            return lhs || rhs;
        }
    },
    XOR("XOR") {
        @Override
        boolean apply(boolean lhs, boolean rhs) {
            return lhs ^ rhs;
        }
    };
    
    private final String stringRepresentation;
    
    private LogicGateType(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }
    
    abstract boolean apply(boolean lhs, boolean rhs);
    
    static LogicGateType parse(String text) {
        return Stream.of(values())
                .filter(value -> value.stringRepresentation.equals(text))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unable to parse as logic gate type: " + text));
    }
}
