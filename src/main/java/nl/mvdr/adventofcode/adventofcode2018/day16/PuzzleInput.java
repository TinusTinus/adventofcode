package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Container class for the puzzle input.
 *
 * @author Martijn van de Rijdt
 */
class PuzzleInput {
    
    /** Samples, from the first half of the puzzle input. */
    private final List<Sample> samples;
    
    /** Instructions, from the second half of the puzzle input. Not relevant for part 1. */
    private final List<OpcodeNumberInstruction> instructions;

    /**
     * Parses the puzzle input file.
     * 
     * @param input contents of the input text file
     * @return puzzle input
     */
    static PuzzleInput parse(Stream<String> input) {
        List<String> lines = input
                // ignore empty lines (the last line in the file)
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        
        Iterator<String> lineIterator = lines.iterator();
        
        List<Sample> samples = new ArrayList<>();
        List<OpcodeNumberInstruction> instructions = new ArrayList<>();
        
        while (lineIterator.hasNext()) {
            String line = lineIterator.next();
            
            if (line.startsWith("Before")) {
                Sample sample = Sample.parse(line, lineIterator.next(), lineIterator.next());
                samples.add(sample);
            } else {
                OpcodeNumberInstruction instruction = OpcodeNumberInstruction.parse(line);
                instructions.add(instruction);
            }
        }
        
        return new PuzzleInput(Collections.unmodifiableList(samples), Collections.unmodifiableList(instructions));
    }
    
    /**
     * Constructor.
     * 
     * Use {@link PuzzleInput#parse(Path)} to obtain an instance of this class.
     * 
     * @param samples samples
     * @param instructions instructions
     */
    private PuzzleInput(List<Sample> samples, List<OpcodeNumberInstruction> instructions) {
        super();
        this.samples = samples;
        this.instructions = instructions;
    }
    
    /** @return samples, from the first half of the puzzle input */
    List<Sample> getSamples() {
        return samples;
    }

    /** @return instructions, from the second half of the puzzle input */
    List<OpcodeNumberInstruction> getInstructions() {
        return instructions;
    }

    @Override
    public String toString() {
        return "PuzzleInput [samples=" + samples + ", instructions=" + instructions + "]";
    }
}
