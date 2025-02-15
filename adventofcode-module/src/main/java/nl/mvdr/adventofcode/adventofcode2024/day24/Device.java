package nl.mvdr.adventofcode.adventofcode2024.day24;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        LOGGER.debug("Resolving gate: {}", gate);
        
        Boolean lhsValue = values.get(gate.lhs());
        Boolean rhsValue = values.get(gate.rhs());
        
        var result = Boolean.valueOf(gate.type()
                .apply(
                        lhsValue.booleanValue(),
                        rhsValue.booleanValue()));
        
        LOGGER.debug("{} {} {} -> {}", lhsValue, gate.type(), rhsValue, result);
        
        return result;
    }
    
    private long getZValue() {
        return values.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isZWire())
                .filter(entry -> entry.getValue().booleanValue())
                .mapToLong(entry -> 1L << entry.getKey().getIndex())
                .sum();
    }
    
    long resolveZ() {
        LOGGER.debug("{}", this);
        
        long result;
        if (gates.stream().anyMatch(gate -> gate.output().isZWire())) {
            result = step().resolveZ();
        } else {
            result = getZValue();
        }
        return result;
    }
    
    Device normalize() {
        Device previousResult = this;
        Device result = alphabetizeGates();
        
        while (!result.equals(previousResult)) {
            previousResult = result;
            result = result.renameWire().alphabetizeGates();
        }
        
        return result;
    }

    private Device alphabetizeGates() {
        Set<LogicGate> updatedGates = gates.stream()
                .map(LogicGate::alphabetize)
                .collect(Collectors.toSet());
        
        return new Device(values, updatedGates);
    }

    private Device renameWire() {
        return gates.stream()
                .filter(gate -> gate.findOutputWireName().isPresent())
                .findFirst()
                .map(gate -> renameWire(gate.output().name(), gate.findOutputWireName().orElseThrow()))
                .orElse(this)
                .alphabetizeGates();
    }
    
    private Device renameWire(String currentName, String newName) {
        Map<Wire, Boolean> newValues = values.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey().name().equals(currentName) ? new Wire(newName) : entry.getKey(),
                        entry -> entry.getValue()));
        
        Set<LogicGate> newGates = gates.stream()
                .map(gate -> gate.renameWire(currentName, newName))
                .collect(Collectors.toSet());

        LOGGER.debug("Renamed wire {} to {}", currentName, newName);
        
        return new Device(newValues, newGates);
    }

    @Override
    public final String toString() {
        StringBuilder result = new StringBuilder("Device:\n");
        
        values.entrySet()
                .stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue().booleanValue() + "\n")
                .sorted()
                .forEach(result::append);
        
        result.append("\n");
        
        gates.stream()
            .map(LogicGate::toString)
            .sorted()
            .forEach(gate -> result.append(gate).append("\n"));

        return result.toString();
    }
}
