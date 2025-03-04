package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

record State(Map<Item, Floor> itemLocations) {
    
    static State parse(Stream<String> lines) {
        Map<Item, Floor> itemLocations = new HashMap<>();
        
        itemLocations.put(Elevator.INSTANCE, Floor.FIRST);
        
        lines.forEach(line -> {
            var parts = line.split(" contains ");
            
            var floor = Floor.of(parts[0]);
            
            var contents = parts[1].substring(0, parts[1].length() - 1);
            
            if (!"nothing relevant".equals(contents)) {
                Stream.of(contents.split(", and"))
                        .flatMap(string -> Stream.of(string.split(" and ")))
                        .flatMap(string -> Stream.of(string.split(", ")))
                        .map(Item::parse)
                        .forEach(item -> itemLocations.put(item, floor));
            }
        });
        
        return new State(itemLocations);
    }

    /// @return all possible valid states containing the items in this state
    Stream<State> allValidStates() {
        var emptyState = new State(Map.of());
        var result = Stream.of(emptyState);
        
        for (var item : itemLocations.keySet()) {
            result = result.flatMap(state -> state.add(item));
        }
        
        return result.filter(State::isValid);
    }
    
    private Stream<State> add(Item item) {
        return Stream.of(Floor.values())
                .map(floor -> add(item, floor));
    }
    
    private State add(Item item, Floor floor) {
        var newItemLocations = new HashMap<>(itemLocations);
        newItemLocations.put(item, floor);
        return new State(newItemLocations);
    }
    
    private boolean isValid() {
        return itemLocations.keySet()
                .stream()
                .filter(Microchip.class::isInstance)
                .map(Microchip.class::cast)
                .noneMatch(this::fries);
    }
    
    private boolean fries(Microchip chip) {
        return !isPowered(chip) && isNearOtherGenerator(chip) ;
    }

    private boolean isNearOtherGenerator(Microchip chip) {
        var chipFloor = itemLocations.get(chip);
        return itemLocations.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == chipFloor)
                .map(Entry::getKey)
                .filter(RadioisotopeThermoelectricGenerator.class::isInstance)
                .map(RadioisotopeThermoelectricGenerator.class::cast)
                .filter(generator -> !generator.radioisotope().equals(chip.radioisotope()))
                .findFirst()
                .isPresent();
    }

    private boolean isPowered(Microchip chip) {
        var chipFloor = itemLocations.get(chip);
        var generatorFloor = itemLocations.get(new RadioisotopeThermoelectricGenerator(chip.radioisotope()));
        return chipFloor == generatorFloor;
    }

    /// @return the desired end state, where all items have been gathered on the fourth floor
    State endState() {
        var endItemLocations = itemLocations.keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(), item -> Floor.FOURTH));
        return new State(endItemLocations);
    }
    
    /// @return possible transitions, by taking the elevator to another floor, taking one or two items
    Stream<State> takeElevator() {
        return Stream.of(Floor.values())
                .filter(floor -> floor != itemLocations.get(Elevator.INSTANCE))
                .flatMap(this::takeElevator)
                .filter(State::isValid);
    }
    
    private Stream<State> takeElevator(Floor targetFloor) {
        return Stream.concat(takeElevatorAndSingleItem(targetFloor), takeElevatorAndTwoItems(targetFloor));
    }
    
    private Stream<State> takeElevatorAndSingleItem(Floor targetFloor) {
        var currentFloor = itemLocations.get(Elevator.INSTANCE);
        return itemsFromFloor(currentFloor)
                .map(item -> moveItems(targetFloor, Elevator.INSTANCE, item));
    }
    
    private Stream<Item> itemsFromFloor(Floor floor) {
        return itemLocations.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == floor)
                .map(Entry::getKey)
                .filter(item -> item != Elevator.INSTANCE);
    }
    
    private State moveItems(Floor targetFloor, Item... items) {
        Map<Item, Floor> newItemLocations = new HashMap<>(itemLocations);
        Stream.of(items).forEach(item -> newItemLocations.put(item, targetFloor));
        return new State(newItemLocations);
    }
    
    private Stream<State> takeElevatorAndTwoItems(Floor targetFloor) {
        var currentFloor = itemLocations.get(Elevator.INSTANCE);
        return itemsFromFloor(currentFloor)
                .flatMap(item -> itemsFromFloor(currentFloor)
                        .filter(otherItem -> item != otherItem)
                        .map(otherItem -> moveItems(targetFloor, Elevator.INSTANCE, item, otherItem)));
    }
}
