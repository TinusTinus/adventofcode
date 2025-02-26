package nl.mvdr.adventofcode.adventofcode2016.day10;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

record State(Map<MicrochipHolder, Set<Microchip>> microchips) {
    
    Bot findResponsibleBot(Microchip first, Microchip second, Map<Bot, Rule> rules) {
        return microchips.entrySet()
                .stream()
                .filter(entry -> entry.getKey() instanceof Bot)
                .filter(entry -> entry.getValue().equals(Set.of(first, second)))
                .findFirst()
                .map(Entry::getKey)
                .map(Bot.class::cast)
                .orElseGet(() -> apply(rules).findResponsibleBot(first, second, rules));
    }
    
    private State apply(Map<Bot, Rule> rules) {
        
        Map<MicrochipHolder, Set<Microchip>> newMicrochips = new HashMap<>();
        
        microchips.entrySet()
                .stream()
                .forEach(entry -> {
                    var holder = entry.getKey();
                    var heldMicrochips = entry.getValue();
                    
                    if (holder instanceof Bot bot && heldMicrochips.size() == 2) {
                        var lowValueChip = heldMicrochips.stream().min(Comparator.comparing(Microchip::value)).orElseThrow();
                        var highValueChip = heldMicrochips.stream().max(Comparator.comparing(Microchip::value)).orElseThrow();
                        var rule = rules.get(bot);
                        newMicrochips.computeIfAbsent(bot, h -> new HashSet<>());
                        newMicrochips.computeIfAbsent(rule.lowTarget(), h -> new HashSet<>()).add(lowValueChip);
                        newMicrochips.computeIfAbsent(rule.highTarget(), h -> new HashSet<>()).add(highValueChip);
                    } else {
                        newMicrochips.computeIfAbsent(holder, h -> new HashSet<>()).addAll(heldMicrochips);
                    }
                });
        
        return new State(newMicrochips);
    }
}
