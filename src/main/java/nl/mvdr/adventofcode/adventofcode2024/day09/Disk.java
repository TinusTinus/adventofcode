package nl.mvdr.adventofcode.adventofcode2024.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

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
        var result = this.filterEmptyElements();
        while (result.findFirstEmptySpace().isPresent()) {
            var firstEmptySpace = result.findFirstEmptySpace().orElseThrow();
            var lastFile = result.findLastFile();
            var blocksToMove = Math.min(firstEmptySpace.blocks(), lastFile.blocks());
            
            System.out.println("First empty space: " + firstEmptySpace); // TODO remove
            System.out.println("Last file: " + lastFile); // TODO remove
            System.out.println("Blocks to move: " + blocksToMove); // TODO remove
            
            List<DiskElement> newElements = new ArrayList<>();
            var elementIndex = 0;
            while (result.elements().get(elementIndex) != firstEmptySpace) {
                newElements.add(result.elements().get(elementIndex));
                elementIndex++;
            }
            newElements.add(new File(lastFile.idNumber(), blocksToMove));
            newElements.add(new EmptySpace(firstEmptySpace.blocks() - blocksToMove));
            elementIndex++;
            while (result.elements().get(elementIndex) != lastFile) {
                newElements.add(result.elements().get(elementIndex));
                elementIndex++;
            }
            newElements.add(new File(lastFile.idNumber(), lastFile.blocks() - blocksToMove));
            result = new Disk(newElements);
            
            result = result.filterEmptyElements();
            System.out.println(result); // TODO remove
        }
        
        return result;
    }
    
    private Optional<EmptySpace> findFirstEmptySpace() {
        return elements().stream()
                .filter(EmptySpace.class::isInstance)
                .map(EmptySpace.class::cast)
                .findFirst();
    }
    
    private File findLastFile() {
        return elements.reversed()
                .stream()
                .filter(File.class::isInstance)
                .map(File.class::cast)
                .findFirst()
                .orElseThrow();
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
