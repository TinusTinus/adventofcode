package nl.mvdr.adventofcode.adventofcode2024.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

record Disk(List<DiskElement> elements) {
    
    static Disk parse(String diskMap) {
        List<DiskElement> elements = new ArrayList<>();
        elements.add(new File(0, Character.getNumericValue(diskMap.charAt(0))));
        
        for (int i = 1; i != diskMap.length(); i++) {
            elements.add(new EmptySpace(Character.getNumericValue(diskMap.charAt(i))));
            i++;
            elements.add(new File(i / 2, Character.getNumericValue(diskMap.charAt(i))));
        }
        
        // filter out any empty (that is, 0-block) elements
        elements.removeIf(DiskElement::isEmpty);
        
        return new Disk(elements);
    }
    
    Disk compactBlocks() {
        var result = this;
        while (result.findFirstEmptySpace().isPresent()) {
            var firstEmptySpace = result.findFirstEmptySpace().orElseThrow();
            var lastFile = result.findLastFile();
            var blocksToMove = Math.min(firstEmptySpace.blocks(), lastFile.blocks());
            
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

            // filter out any empty (that is, 0-block) elements
            newElements.removeIf(DiskElement::isEmpty);
            
            // remove any trailing empty space
            while (newElements.getLast() instanceof EmptySpace) {
                newElements.remove(newElements.size() - 1);
            }
            
            result = new Disk(newElements);
        }
        
        return result;
    }
    
    Disk compactFiles() {
        var result = this;
        
        var files = elements.reversed()
                .stream()
                .filter(File.class::isInstance)
                .map(File.class::cast)
                .toList();
        
        for (var file : files) {
            var optionalEmptySpace = result.elements()
                    .stream()
                    .filter(EmptySpace.class::isInstance)
                    .map(EmptySpace.class::cast)
                    .filter(emptySpace -> file.blocks() <= emptySpace.blocks())
                    .findFirst();
            if (optionalEmptySpace.isPresent() 
                    && result.elements().indexOf(optionalEmptySpace.orElseThrow()) < result.elements.indexOf(file)) {
                var emptySpace = optionalEmptySpace.orElseThrow();
                
                // We have found an empty space which can fit the file. Move the file here.
                List<DiskElement> newElements = new ArrayList<>();
                var elementIndex = 0;
                while (result.elements().get(elementIndex) != emptySpace) {
                    newElements.add(result.elements().get(elementIndex));
                    elementIndex++;
                }
                newElements.add(file);
                newElements.add(new EmptySpace(emptySpace.blocks() - file.blocks()));
                elementIndex++;
                while (elementIndex < result.elements().size()) {
                    if (result.elements().get(elementIndex) != file) {
                        newElements.add(result.elements().get(elementIndex));
                    } else {
                        newElements.add(new EmptySpace(file.blocks()));
                    }
                    elementIndex++;
                }
                
                // filter out any empty (that is, 0-block) elements
                newElements.removeIf(DiskElement::isEmpty);
                
                // remove any trailing empty space
                while (newElements.getLast() instanceof EmptySpace) {
                    newElements.remove(newElements.size() - 1);
                }
                result = new Disk(newElements);
            }
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
    
    long checksum() {
        long result = 0L;
        int blockIndex = 0;
        for (DiskElement element : elements) {
            if (element instanceof File file) {
                result = Math.addExact(result, LongStream.range(blockIndex, blockIndex + file.blocks()).sum() * file.idNumber());
            }
            blockIndex += element.blocks();
        }
        return result;
    }
}
