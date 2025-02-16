package nl.mvdr.adventofcode.adventofcode2023.day03;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.mvdr.adventofcode.point.Point;

/**
 * Schematic of the engine.
 *
 * @author Martijn van de Rijdt
 */
record EngineSchematic(Set<SchematicNumber> numbers, Set<Symbol> symbols) {
    
    /**
     * Parses puzzle input into an engine schematic.
     * 
     * @param lines puzzle input
     * @return engine schematic
     */
    static EngineSchematic parse(List<String> lines) {
        Set<SchematicNumber> numbers = new HashSet<>();
        Set<Symbol> symbols = new HashSet<>();
        for (var lineNumber = 0; lineNumber != lines.size(); lineNumber++) {
            var line = lines.get(lineNumber);
            var i = 0;
            while (i < line.length()) {
                if (Character.isDigit(line.charAt(i))) {
                    // Found the start of a new number.
                    var numberString = "";
                    Set<Point> location = new HashSet<>();
                    while (i < line.length() && Character.isDigit(line.charAt(i))) {
                        numberString = numberString + line.charAt(i);
                        location.add(new Point(i, lineNumber));
                        i++;
                    }
                    numbers.add(new SchematicNumber(Integer.parseInt(numberString), location));
                }
                else {
                    if (line.charAt(i) != '.') {
                        symbols.add(new Symbol(line.charAt(i), new Point(i, lineNumber)));
                    }
                    i++;
                }
            }
        }
        return new EngineSchematic(numbers, symbols);
    }
    
    /**
     * @return sum of the part numbers on this schematic
     */
    int sumPartNumbers() {
        return numbers.stream()
                .filter(number -> number.isPartNumber(symbols))
                .mapToInt(SchematicNumber::value)
                .sum();
    }
    
    /**
     * @return sum of the gear ratios on this schematic
     */
    int sumGearRatios() {
        return symbols.stream()
                .mapToInt(symbol -> symbol.gearRatio(numbers))
                .sum();
    }
}
