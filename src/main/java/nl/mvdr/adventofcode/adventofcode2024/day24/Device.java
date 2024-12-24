package nl.mvdr.adventofcode.adventofcode2024.day24;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

record Device(Map<Wire, Boolean> values, Set<LogicGate> gates) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Device.class);
    
    static Device parse(List<String> lines) {
        var emptyLineIndex = lines.indexOf("");
        
        var values = lines.subList(0, emptyLineIndex)
                .stream()
                .map(line -> line.split(": "))
                .collect(Collectors.toMap(parts -> new Wire(parts[0]), parts -> Boolean.valueOf("1".equals(parts[1]))));
        
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
                .forEach(gate -> updatedValues.put(gate.output(), resolve(gate)));
        
        return new Device(updatedValues, updatedGates);
    }

    private Boolean resolve(LogicGate gate) {
        LOGGER.info("Resolving gate: {}", gate); // TODO clean up logging
        
        Boolean lhsValue = values.get(gate.lhs());
        Boolean rhsValue = values.get(gate.rhs());
        
        var result = Boolean.valueOf(gate.type()
                .apply(
                        lhsValue.booleanValue(),
                        rhsValue.booleanValue()));
        
        LOGGER.info("{} {} {} -> {}", lhsValue, gate.type(), rhsValue, result); // TODO clean up logging
        
        return result;
    }
    
    long resolveZ() {
        LOGGER.info("{}", this); // TODO clean up logging
        
        long result;
        if (gates.stream().anyMatch(gate -> gate.output().isZWire())) {
            result = step().resolveZ();
        } else {
            result = values.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().isZWire())
                    .sorted(Comparator.<Entry<Wire, Boolean>, String>comparing(entry -> entry.getKey().name()).reversed()) // TODO remove?
                    .peek(entry -> LOGGER.info("{}: {}", entry.getKey(), entry.getValue().booleanValue() ? "1" : "0")) // TODO clean up logging
                    .filter(entry -> entry.getValue().booleanValue())
                    .mapToLong(entry -> (long)Math.pow(2, entry.getKey().getZindex()))
                    .sum();
        }
        return result;
    }
    
    @Override
    public final String toString() {
        StringBuilder result = new StringBuilder("Device:\n");
        
        values.entrySet()
                .stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue().booleanValue() + "\n")
                .forEach(result::append);
        
        gates.forEach(gate -> result.append(gate).append("\n"));

        return result.toString();
    }
}
