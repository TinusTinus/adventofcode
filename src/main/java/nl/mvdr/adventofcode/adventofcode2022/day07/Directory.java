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
     * Parses the puzzle input into a directory structure.
     * 
     * @param lines puzzle input
     * @return the root directory
     */
    static Directory parse(List<String> lines) {
        // Create the root directory.
        Directory currentDirectory = new Directory(Optional.empty(), new HashMap<>(), new ArrayList<>());
        
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
                Directory subdirectory = new Directory(Optional.of(currentDirectory), new HashMap<>(), new ArrayList<>());
                currentDirectory.subdirectories.put(name, subdirectory);
            } else {
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
}
