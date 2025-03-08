package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.multiset.HashMultiSet;

record State(Floor elevator, MultiSet<Pair> pairs) {
    
    private static final String MICROCHIP_SUFFIX = "-compatible microchip";
    private static final String GENERATOR_SUFFIX = " generator";
    
    static State parse(Stream<String> lines, boolean extraObjects) {
        
        Map<String, Floor> microchips = new HashMap<>();
        Map<String, Floor> generators = new HashMap<>();
        
        lines.forEach(line -> {
            var parts = line.split(" contains ");
            
            var floor = Floor.of(parts[0]);
            
            var contents = parts[1].substring(0, parts[1].length() - 1);
            
            if (!"nothing relevant".equals(contents)) {
                Stream.of(contents.split(", and"))
                        .flatMap(string -> Stream.of(string.split(" and ")))
                        .flatMap(string -> Stream.of(string.split(", ")))
                        .forEach(itemString -> {
                            if (itemString.endsWith(MICROCHIP_SUFFIX)) {
                                String radioisotope = itemString.substring(0, itemString.length() - MICROCHIP_SUFFIX.length());
                                microchips.put(radioisotope, floor);
                            } else if (itemString.endsWith(GENERATOR_SUFFIX)) {
                                String radioisotope = itemString.substring(0, itemString.length() - GENERATOR_SUFFIX.length());
                                generators.put(radioisotope, floor);
                            } else {
                                throw new IllegalArgumentException("Unable to parse: " + itemString);
                            }
                        });
            }
        });
        
        if (extraObjects) {
            microchips.put("elerium", Floor.FIRST);
            generators.put("elerium", Floor.FIRST);
            microchips.put("dilithium", Floor.FIRST);
            generators.put("dilithium", Floor.FIRST);
        }
        
        if (!generators.keySet().equals(microchips.keySet())) {
            throw new IllegalArgumentException("Invalid input");
        }
        
        MultiSet<Pair> pairs = microchips.entrySet()
                .stream()
                .map(entry -> new Pair(generators.get(entry.getKey()), entry.getValue()))
                .collect(Collectors.toCollection(HashMultiSet::new));
        return new State(Floor.FIRST, pairs);
    }

    State endState() {
        MultiSet<Pair> endPairs = Collections.nCopies(pairs.size(), new Pair(Floor.FOURTH, Floor.FOURTH))
                .stream()
                .collect(Collectors.toCollection(HashMultiSet::new));
        return new State(Floor.FOURTH, endPairs);
    }
    
    Stream<State> takeElevator() {
        return Stream.of(Floor.values())
                .filter(floor -> Math.abs(floor.getFloorNumber() - elevator.getFloorNumber()) == 1)
                .flatMap(this::takeElevator)
                .filter(State::isValid);
    }
    
    private Stream<State> takeElevator(Floor targetFloor) {
        return Stream.concat(takeElevatorAndSingleItem(targetFloor), takeElevatorAndTwoItems(targetFloor));
    }
    
    private Stream<State> takeElevatorAndSingleItem(Floor targetFloor) {
        return Stream.concat(takeElevatorAndMicrochip(targetFloor), takeElevatorAndGenerator(targetFloor));
    }
    
    private Stream<State> takeElevatorAndMicrochip(Floor targetFloor) {
        return pairs.stream()
                .filter(pair -> pair.microchip() == elevator)
                .map(pair -> moveMicrochip(targetFloor, pair));
    }
    
    private State moveMicrochip(Floor targetFloor, Pair microchipPair) {
        MultiSet<Pair> newPairs = new HashMultiSet<>(pairs);
        newPairs.remove(microchipPair);
        newPairs.add(microchipPair.moveMicrochip(targetFloor));
        return new State(targetFloor, newPairs);
    }
    
    private Stream<State> takeElevatorAndGenerator(Floor targetFloor) {
        return pairs.uniqueSet()
                .stream()
                .filter(pair -> pair.generator() == elevator)
                .map(pair -> moveGenerator(targetFloor, pair));
    }
    
    private State moveGenerator(Floor targetFloor, Pair generatorPair) {
        MultiSet<Pair> newPairs = new HashMultiSet<>(pairs);
        newPairs.remove(generatorPair);
        newPairs.add(generatorPair.moveGenerator(targetFloor));
        return new State(targetFloor, newPairs);
    }
    
    private Stream<State> takeElevatorAndTwoItems(Floor targetFloor) {
        return Stream.of(); // TODO
    }
    
    private boolean isValid() {
        return pairs.uniqueSet()
                .stream()
                .allMatch(pair -> 
                    // Either the corresponding microchip and generator are on the same floor,
                    pair.generator() == pair.microchip()
                        // or there are no non-corresponding generators on the microchip's floor.
                        || pairs.uniqueSet().stream().noneMatch(otherPair -> otherPair != pair && otherPair.generator() == pair.microchip()));
    }
}
