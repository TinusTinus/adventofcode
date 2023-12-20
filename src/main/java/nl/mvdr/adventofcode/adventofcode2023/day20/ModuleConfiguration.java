package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.LinkedList;
import java.util.List;
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
record ModuleConfiguration(Map<String, Module> modules, Queue<Pulse> pulseQueue) {
    
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
    
    /**
     * Presses the button once.
     * 
     * @param pulseCounter mutable(!) map of pulse counters; will be updated during this method
     * @return updated module configuration
     */
    private ModuleConfiguration pressButton(Map<PulseType, Long> pulseCounter) {
        if (!pulseQueue.isEmpty()) {
            throw new IllegalStateException("Still processing pulses.");
        }
        var newPulses = addPulses(ButtonModule.INSTANCE.press(), pulseCounter);
        return new ModuleConfiguration(modules, newPulses);
    }
    
    /**
     * Processes all pulses currently in the queue.
     * 
     * @param pulseCounter mutable(!) map of pulse counters; will be updated during this method
     * @return updated module configuration
     */
    private ModuleConfiguration handleQueue(Map<PulseType, Long> pulseCounter) {
        return this; // TODO
    }
    
    /**
     * Adds the given pulses to the queue.
     * 
     * @param pulses pulses to add to the back of the queue
     * @param pulseCounter mutable(!) map of pulse counters; will be updated during this method
     * @return new copy of the queue with the given pulses added to it
     */
    private Queue<Pulse> addPulses(List<Pulse> pulses, Map<PulseType, Long> pulseCounter) {
        count(pulses, pulseCounter);
        Queue<Pulse> result = new LinkedList<>(pulseQueue);
        result.addAll(pulses);
        return result;
    }

    /**
     * Counts the given (new) pulses.
     * 
     * @param pulses new pulses, which are about to be added to the back of the queue
     * @param pulseCounter mutable(!) map of pulse counters; will be updated during this method
     */
    private void count(List<Pulse> pulses, Map<PulseType, Long> pulseCounter) {
        pulses.forEach(pulse -> {
            var newCount = pulseCounter.get(pulse.type()).longValue() + 1L;
            pulseCounter.put(pulse.type(), Long.valueOf(newCount));
        });
    }
}
