package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The complete module configuration.
 *
 * @author Martijn van de Rijdt
 */
record ModuleConfiguration(Map<String, Module> modules, Queue<Pulse> pulses) {
    
    /**
     * Parses a module configuration.
     * 
     * @param lines puzzle input
     * @return initialized module configuration
     */
    static ModuleConfiguration parse(Stream<String> lines) {
        // Read modules from the input
        var uninitializedModules = lines.map(Module::parse)
                .collect(Collectors.toSet());
        
        var modules = uninitializedModules.stream()
                .map(module -> module.init(uninitializedModules))
                .collect(Collectors.toMap(Module::name, Function.identity()));
        
        Queue<Pulse> pulses = new LinkedList<>();
        
        return new ModuleConfiguration(modules, pulses);
    }
}
