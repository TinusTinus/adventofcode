package nl.mvdr.adventofcode.adventofcode2024.day24;

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
    
    long resolveZ() {
        return 0; // TODO implement
    }
}
