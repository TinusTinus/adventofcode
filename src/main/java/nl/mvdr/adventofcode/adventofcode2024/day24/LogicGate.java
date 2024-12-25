package nl.mvdr.adventofcode.adventofcode2024.day24;

import java.util.Optional;

record LogicGate(Wire lhs, LogicGateType type, Wire rhs, Wire output) {
    static LogicGate parse(String line) {
        var parts = line.split(" ");
        
        if (parts.length != 5 || !parts[3].equals("->")) {
            throw new IllegalArgumentException("Unable to parse as a logic gate: " + line);
        }
        
        var lhs = new Wire(parts[0]);
        var type = LogicGateType.parse(parts[1]);
        var rhs = new Wire(parts[2]);
        var output = new Wire(parts[4]);
        
        return new LogicGate(lhs, type, rhs, output);
    }
    
    /// Swaps input wires if necessary to make sure they are in lexicographical order.
    /// Note that this does not change its functionality: all logic gate type operations are symmetrical.
    LogicGate alphabetize() {
        LogicGate result;
        if (rhs.name().compareTo(lhs.name()) < 0) {
            result = new LogicGate(rhs, type, lhs, output);
        } else {
            result = this;
        }
        return result;
    }
    
    LogicGate renameWire(String currentName, String newName) {
        var newLhs = lhs.name().equals(currentName) ? new Wire(newName) : lhs;
        var newRhs = rhs.name().equals(currentName) ? new Wire(newName) : rhs;        
        var newOutput = output.name().equals(currentName) ? new Wire(newName) : output;
        return new LogicGate(newLhs, type, newRhs, newOutput);
    }
    
    /// Attempts to decide a better, meaningful name for the output wire.
    @SuppressWarnings("boxing")
    Optional<String> findOutputWireName() {
        Optional<String> result;
        
        if (output.isXInputWire() || output.isYInputWire()) {
            throw new IllegalStateException("Unexpected output wire in gate: " + this);
        } else if (output.isCWire() || output.isZWire()) {
            // do not rename
            result = Optional.empty();
        } else if (lhs.isXInputWire() && rhs.isYInputWire() && lhs.getIndex() == rhs.getIndex()) {
            result = Optional.of(String.format("x%sy%02d", type, lhs.getIndex()));
        } else if (lhs.isCWire() && rhs.name().matches("xXORy\\d\\d") && lhs.getIndex() == rhs.getIndex() && type == LogicGateType.AND) {
            result = Optional.of(String.format("temp%02d", lhs.getIndex()));
        } else if (lhs.isCWire() && rhs.name().matches("temp\\d\\d") && lhs.getIndex() == rhs.getIndex() && type == LogicGateType.OR) {
            result = Optional.of(String.format("c%02d", lhs.getIndex() + 1));
        } else {
            result = Optional.empty();
        }
        
        result = result.filter(newName -> !output.name().equals(newName));
        
        return result;
    }
    
    @Override
    public final String toString() {
        return lhs + " " + type + " " + rhs + " -> " + output;
    }
}
