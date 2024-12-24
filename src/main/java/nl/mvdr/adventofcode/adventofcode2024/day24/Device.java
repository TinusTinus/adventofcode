package nl.mvdr.adventofcode.adventofcode2024.day24;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

record Device(Map<Wire, Boolean> values, Set<LogicGate> gates) {
    
    static Device parse(List<String> lines) {
        var emptyLineIndex = lines.indexOf("");
        
        var values = lines.subList(0, emptyLineIndex)
                .stream()
                .map(line -> line.split(": "))
                .collect(Collectors.toMap(parts -> new Wire(parts[0]), parts -> Boolean.valueOf("0".equals(parts[1]))));
        
        var gates = lines.subList(emptyLineIndex + 1, lines.size())
                .stream()
                .map(LogicGate::parse)
                .collect(Collectors.toSet());
        
        return new Device(values, gates);
    }
    
    /// Takes a step towards resolving the value of each output wire.
    private Device step() {
        Map<Wire, Boolean> updatedValues = new HashMap<>(values);
        
        Set<LogicGate> updatedGates = new HashSet<>(gates);
        
        gates.stream()
                .filter(gate -> values.containsKey(gate.lhs()))
                .filter(gate -> values.containsKey(gate.rhs()))
                .peek(updatedGates::remove)
                .forEach(gate -> updatedValues.put(gate.output(), Boolean.valueOf(
                        gate.type().apply(
                                values.get(gate.lhs()).booleanValue(),
                                values.get(gate.rhs()).booleanValue()))));
                        
        return new Device(updatedValues, updatedGates);
    }
    
    long resolveZ() {
        long result;
        if (gates.stream().anyMatch(gate -> gate.output().isZWire())) {
            result = step().resolveZ();
        } else {
            result = values.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().isZWire())
                    .peek(System.out::println) // TODO remove
                    .filter(entry -> entry.getValue().booleanValue())
                    .mapToLong(entry -> (long)Math.pow(2, entry.getKey().getZindex()))
                    .sum();
        }
        return result;
    }
}
