package nl.mvdr.adventofcode.adventofcode2023.day20;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The complete module configuration.
 *
 * @author Martijn van de Rijdt
 */
record Machine(Map<String, Module> modules, List<Pulse> pulseQueue) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Machine.class);
    
    /**
     * Parses a module configuration.
     * 
     * @param lines puzzle input
     * @return initialized module configuration
     */
    static Machine parse(Stream<String> lines) {
        // Read modules from the input
        var modulesFromInput = lines.map(Module::parse)
                .collect(Collectors.toSet());
        
        var outputModules = modulesFromInput.stream()
                .map(module -> module.destinations())
                .flatMap(List::stream)
                .distinct()
                .filter(moduleName -> !modulesFromInput.stream().anyMatch(module -> module.name().equals(moduleName)))
                .map(OutputModule::new)
                .collect(Collectors.toSet());
        
        Set<Module> uninitializedModules = new HashSet<>();
        uninitializedModules.addAll(modulesFromInput);
        uninitializedModules.addAll(outputModules);
        
        var modules = uninitializedModules.stream()
                .map(module -> module.init(uninitializedModules))
                .collect(Collectors.toMap(Module::name, Function.identity()));
        
        return new Machine(modules, List.of());
    }
    
    /**
     * Counts the given (new) pulses.
     * 
     * @param pulses new pulses, which are about to be added to the back of the queue
     * @param pulseCounter mutable(!) map of pulse counters; will be updated during this method; may be null
     */
    private static void count(List<Pulse> pulses, Map<PulseType, Long> pulseCounter) {
        if (pulseCounter != null) {
            pulses.forEach(pulse -> {
                var newCount = pulseCounter.get(pulse.type()).longValue() + 1L;
                pulseCounter.put(pulse.type(), Long.valueOf(newCount));
            });
        }
    }

    /**
     * Computest the total number of low pulses sent, multiplied by the total number of high pulses sent,
     * if the button is pressed the given number of times.
     * 
     * @param buttonPresses number of button presses
     * @return product
     */
    long countPulses(int buttonPresses) {
        var pulseCounter = Stream.of(PulseType.values())
                .collect(Collectors.toMap(Function.identity(), type -> Long.valueOf(0L)));
        pulseCounter = new EnumMap<>(pulseCounter);
        
        pressButtonAndHandle(buttonPresses, pulseCounter);
        
        return pulseCounter.values()
                .stream()
                .mapToLong(Long::longValue)
                .reduce(Math::multiplyExact)
                .orElseThrow();
    }
    
    /**
     * Presses the button once, then handles all of the resulting pulses.
     * 
     * @return updated module configuration
     */
    private Machine pressButtonAndHandle() {
        return pressButtonAndHandle(null);
    }
    
    /**
     * Presses the button the given number of times and handles all of the resulting pulses.
     * 
     * @param pulseCounter mutable(!) map of pulse counters; will be updated during this method; may be null
     * @return updated module configuration
     */
    private Machine pressButtonAndHandle(int times, Map<PulseType, Long> pulseCounter) {
        var result = this;
        for (var i = 0; i != times; i++) {
            result = result.pressButtonAndHandle(pulseCounter);
        }
        return result;
    }
    
    /**
     * Presses the button once, then handles all of the resulting pulses.
     * 
     * @param pulseCounter mutable(!) map of pulse counters; will be updated during this method; may be null
     * @return updated module configuration
     */
    private Machine pressButtonAndHandle(Map<PulseType, Long> pulseCounter) {
        return pressButton(pulseCounter).handlePulseQueue(pulseCounter);
    }
    
    /**
     * Presses the button once.
     * 
     * @param pulseCounter mutable(!) map of pulse counters; will be updated during this method; may be null
     * @return updated module configuration
     */
    private Machine pressButton(Map<PulseType, Long> pulseCounter) {
        if (!pulseQueue.isEmpty()) {
            throw new IllegalStateException("Still processing pulses.");
        }
        List<Pulse> newPulseQueue = ButtonModule.INSTANCE.press();
        count(newPulseQueue, pulseCounter);
        return new Machine(modules, newPulseQueue);
    }
    
    /**
     * Processes all pulses until there are none left in the queue.
     * 
     * @param pulseCounter mutable(!) map of pulse counters; will be updated during this method; may be null
     * @return updated module configuration
     */
    private Machine handlePulseQueue(Map<PulseType, Long> pulseCounter) {
        Machine result = this;
        while (!result.pulseQueue.isEmpty()) {
            result = result.handlePulse(pulseCounter);
        }
        return result;
    }
    
    /**
     * Processes the first pulse in the queue.
     * 
     * @param pulseCounter mutable(!) map of pulse counters; will be updated during this method; may be null
     * @return updated module configuration
     */
    private Machine handlePulse(Map<PulseType, Long> pulseCounter) {
        var pulse = pulseQueue.getFirst();
        LOGGER.debug("{}", pulse);
        
        List<Pulse> newPulseQueue = new ArrayList<>(pulseQueue.subList(1, pulseQueue.size()));
        
        var module = Objects.requireNonNull(modules.get(pulse.destination()), "Module not found: " + pulse.destination());
        
        var result = module.handlePulse(pulse);
        
        count(result.outgoingPulses(), pulseCounter);
        newPulseQueue.addAll(result.outgoingPulses());

        Map<String, Module> newModules;
        if (module.equals(result.updatedModule())) {
            newModules = modules;
        } else {
            // The module's state has changed
            newModules = new HashMap<>(modules);
            newModules.put(module.name(), result.updatedModule());
        }
        
        return new Machine(newModules, newPulseQueue);
    }

    /**
     * @return number of button presses until the machine turns on
     */
    long turnOn() {
        // From observing the input: there is a single conjunction module which outputs to rx.
        // Find this module.
        var secondToLastModule = modules.values()
                .stream()
                .filter(value -> value.destinations().contains("rx"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No source found for module rx"));
        
        // From observing the input: there are four conjunction modules which output to the above one.
        // Find these modules, and check how long it takes to get a high output from each one.
        // The puzzle answer is the lcm of these four values.
        return modules.values()
                .stream()
                .filter(value -> value.destinations().contains(secondToLastModule.name()))
                .map(Module::name)
                .mapToLong(this::buttonPressesUntilHighOutput)
                .reduce(ArithmeticUtils::lcm)
                .orElseThrow();
                
        // Note that this approach might not work for all possible inputs, but it works for mine.
        // From reading others' approaches this does appear to be a universally correct solution.
        // See https://www.reddit.com/r/adventofcode/comments/18mmfxb/2023_day_20_solutions/
    }
    
    /**
     * Keeps pressing the button until this machine has turned on.
     * @return number of button presses
     */
    private long buttonPressesUntilHighOutput(String moduleName) {
        var machine = this;
        var result = 0L;
        while (!machine.hasOutputHigh(moduleName)) {
            machine = machine.pressButtonAndHandle();
            result++;
        }
        return result;
    }
    
    /**
     * Checks whether the given conjunction module has output a high value.
     * 
     * @param moduleName module name
     * @return whether the module has output a high value yet
     */
    private boolean hasOutputHigh(String moduleName) {
        var module = (ConjunctionModule)modules.get(moduleName);
        return module.hasOutputHigh();
    }
}
