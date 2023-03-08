package nl.mvdr.adventofcode.adventofcode2022.day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Representation of a directory on the file system.
 *
 * @param parent the parent directory; empty (only) for the root directory
 * @param subdirectories the subdirectories; keys in this map are the names of the subdirectories
 * @param files the files contained (directly) in this directory
 * @author Martijn van de Rijdt
 */
record Directory(Optional<Directory> parent, Map<String, Directory> subdirectories, List<File> files) {
    
    /**
     * Convenience constructor for creating a new directory.
     * 
     * @param parent the parent directory; empty (only) for the root directory
     */
    private Directory(Optional<Directory> parent) {
        this(parent, new HashMap<>(), new ArrayList<>());
    }
    
    /**
     * Parses the puzzle input into a directory structure.
     * 
     * @param lines puzzle input
     * @return the root directory
     */
    static Directory parse(List<String> lines) {
        // Create the root directory.
        Directory currentDirectory = new Directory(Optional.empty());
        
        for (String line : lines) {
            if (line.startsWith("$ cd")) {
                // Change directory.
                var parts = line.split(" ");
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Unable to parse command: " + line);
                }
                var argument = parts[2];
                currentDirectory = switch(argument) {
                    case "/" -> currentDirectory.root();
                    case ".." -> currentDirectory.parent().orElseThrow();
                    default -> Objects.requireNonNull(currentDirectory.subdirectories.get(argument), "No such subdirectory: " + argument);
                };
            } else if (line.startsWith("$ ls")) {
                // List directory contents.
                // Do nothing: the next lines will contain files and directories.
            } else if (line.startsWith("dir")) {
                // New subdirectory.
                var parts = line.split(" ");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Unable to parse directory listing: " + line);
                }
                var name = parts[1];
                Directory subdirectory = new Directory(Optional.of(currentDirectory));
                currentDirectory.subdirectories.put(name, subdirectory);
            } else {
                // New file.
                var file = File.parse(line);
                currentDirectory.files.add(file);
            }
        }
        
        return currentDirectory.root();
    }
    
    /**
     * @return the root directory
     */
    private Directory root() {
        return parent.map(Directory::root).orElse(this);
    }
    
    /**
     * @return total size of this directory
     */
    // Note that this method could be made (much) more efficient by introducing some sort of caching.
    int totalSize() {
        int filesSize = files.stream()
                .mapToInt(File::size)
                .sum();
        
        int subdirectoriesSize = subdirectories.values()
                .stream()
                .mapToInt(Directory::totalSize)
                .sum();
        
        return filesSize + subdirectoriesSize;
    }
    
    /**
     * Method for computing part 1 of the puzzle.
     * 
     * @return the total sizes of directories with a total size of at most 100000
     */
    int sum() {
        int result = subdirectories.values()
                .stream()
                .mapToInt(Directory::sum)
                .sum();
        
        int size = totalSize();
        if (size <= 100_000) {
            result = result + size;
        }
        
        return result;
    }
    
    /**
     * Method for computing part 2 of the puzzle.
     * 
     * @return the minimum subdirectory total size over the given lower bound
     */
    int min(int lowerBound) {
        int result = subdirectories.values()
                .stream()
                .mapToInt(subdirectory -> subdirectory.min(lowerBound))
                .min()
                .orElse(Integer.MAX_VALUE);
        
        int size = totalSize();
        
        if (lowerBound <= size && size < result) {
            result = size;
        }
        
        return result;
    }
}
