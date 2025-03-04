package nl.mvdr.adventofcode.adventofcode2016.day11;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
                Stream.of(contents.split("/(, and|,| and /)"))
                        .map(Item::parse)
                        .forEach(item -> itemLocations.put(item, floor));
            }
        });
        
        return new State(itemLocations);
    }
    
    boolean isValid() {
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
        
}
