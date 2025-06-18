package nl.mvdr.adventofcode.adventofcode2016.assembunny;

import java.util.Optional;
import java.util.stream.Stream;

public enum Register {
    A("a"),
    B("b"),
    C("c"),
    D("d");
    
    private final String stringRepresentation;
    
    private Register(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }
    
    static Optional<Register> of(String stringRepresentation) {
        return Stream.of(Register.values())
                .filter(register -> register.stringRepresentation.equals(stringRepresentation))
                .findFirst();
    }
}
