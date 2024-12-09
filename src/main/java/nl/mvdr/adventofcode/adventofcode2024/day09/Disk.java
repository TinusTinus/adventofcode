package nl.mvdr.adventofcode.adventofcode2024.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import kotlin.ranges.IntRange;

record Disk(List<DiskElement> elements) {
    static Disk parse(String diskMap) {
        List<DiskElement> elements = new ArrayList<>();
        elements.add(new File(0, Character.getNumericValue(diskMap.charAt(0))));
        
        for (int i = 1; i != diskMap.length(); i++) {
            elements.add(new EmptySpace(Character.getNumericValue(diskMap.charAt(i))));
            i++;
            elements.add(new File(i / 2, Character.getNumericValue(diskMap.charAt(i))));
        }
        
        return new Disk(elements);
    }
    
    Disk filterEmptyElements() {
        return new Disk(elements.stream()
                .filter(Predicate.not(DiskElement::isEmpty))
                .toList());
    }
    
    Disk compact() {
        return this; // TODO implement
    }
    
    private int blocks() {
        return elements.stream()
                .mapToInt(DiskElement::blocks)
                .sum();
    }
    
    int checksum() {
        int result = 0;
        int blockIndex = 0;
        for (DiskElement element : elements) {
            if (element instanceof File file) {
                result = result + IntStream.range(blockIndex, blockIndex + file.blocks()).sum() * file.idNumber();
            }
            blockIndex += element.blocks();
        }
        return result;
    }
}
