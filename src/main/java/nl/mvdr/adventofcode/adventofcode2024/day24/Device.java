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
                .peek(gate -> LOGGER.info("Resolving gate: {}", gate))
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
                    .sorted(Comparator.<Entry<Wire, Boolean>, String>comparing(entry -> entry.getKey().name()).reversed()) // TODO remove?
                    .peek(entry -> LOGGER.info("{}: {}", entry.getKey(), entry.getValue().booleanValue() ? "1" : "0")) // TODO clean up logging
                    .filter(entry -> entry.getValue().booleanValue())
                    .mapToLong(entry -> (long)Math.pow(2, entry.getKey().getZindex()))
                    .sum();
        }
        return result;
    }
}
