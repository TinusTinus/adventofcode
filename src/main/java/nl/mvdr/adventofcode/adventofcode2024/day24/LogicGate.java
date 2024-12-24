package nl.mvdr.adventofcode.adventofcode2024.day24;

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
    
    @Override
    public final String toString() {
        return lhs + " " + type + " " + rhs + " -> " + output;
    }
}
