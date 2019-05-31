package nl.mvdr.adventofcode.adventofcode2018.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of a tree.
 *
 * @author Martijn van de Rijdt
 */
class Tree {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Tree.class);
    
    private final List<Tree> children;
    
    private final List<Integer> metadataEntries;
    
    private final int endIndex;
    
    /**
     * Constructor.
     * 
     * @param children children of this tree
     * @param metadataEntries metadata entries for this node of the tree
     * @param endIndex end index in the original list of numbers from the input; this is an auxiliary value, only used during construction of the tree
     */
    private Tree(List<Tree> children, List<Integer> metadataEntries, int endIndex) {
        super();
        this.children = children;
        this.metadataEntries = metadataEntries;
        this.endIndex = endIndex;
    }

    /** @return sum of all metadata values within this tree */
    int sum() {
        int childrenSum = children.stream()
                .mapToInt(Tree::sum)
                .sum();
        int metadataSum = metadataEntries.stream()
                .mapToInt(Integer::valueOf)
                .sum();
        return childrenSum + metadataSum;
    }
    
    @Override
    public String toString() {
        return children + ", " + metadataEntries;
    }
    
    /**
     * Parses the input into a tree.
     * 
     * @param inputFilePath text file containing the textual representation of a tree
     * @return tree
     * @throws IOException exception when reading the file
     */
    static Tree parse(Path inputFilePath) throws IOException {
        // All of the input is on the first line of the text file.
        String line = Files.lines(inputFilePath).findFirst().get();
        
        // The input consists of a sequence of integers, separated by spaces.
        List<Integer> numbers = Stream.of(line.split(" "))
                .mapToInt(Integer::valueOf)
                .mapToObj(Integer::valueOf)
                .collect(Collectors.toList());
        
        return build(numbers, 0);
    }
    
    /**
     * Constructs a tree based on the given sequence of numbers.
     * 
     * @param numbers numbers from the input file
     * @param startIndex start index of this (sub)tree
     * @return tree
     */
    private static Tree build(List<Integer> numbers, int startIndex) {
        int numberOfChildren = numbers.get(startIndex);
        int numberOfMetadataEntries = numbers.get(startIndex + 1);
        
        int nextStartIndex = startIndex + 2;
        
        List<Tree> children = new ArrayList<>();
        while (children.size() < numberOfChildren) {
            Tree child = build(numbers, nextStartIndex);
            nextStartIndex = child.endIndex;
            children.add(child);
        }
        LOGGER.debug("Children: {}", children);
        
        int endIndex = nextStartIndex + numberOfMetadataEntries;
        List<Integer> metadataEntries = numbers.subList(nextStartIndex, endIndex);
        LOGGER.debug("Metadata entries: {}", metadataEntries);
        
        return new Tree(children, metadataEntries, endIndex);
    }
}
