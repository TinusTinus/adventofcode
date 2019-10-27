package nl.mvdr.adventofcode.adventofcode2017.day25;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Blueprint of a Turing machine.
 *
 * @author Martijn van de Rijdt
 */
class TuringMachine {
    /** Name of the initial state. */
    private final String initialState;
    
    /** The number of steps after which to compute the diagnostic checksum. */
    private final int steps;
    
    /** States of the machine, indexed by name. */
    private final Map<String, State> states;

    /**
     * Parses a text file into a Turing machine.
     * 
     * @param inputFilePath path to the text file
     * @return Turing machine represented by this text file
     * @throws IOException in case the text file could not be read
     */
    static TuringMachine parse (Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath).collect(Collectors.toList());
        
        String initialStateLine = lines.get(0);
        String initialState = initialStateLine.substring("Begin in state ".length(), initialStateLine.length() - 1);
        
        String stepsLine = lines.get(1);
        int steps = Integer.parseInt(stepsLine.substring("Perform a diagnostic checksum after ".length(), stepsLine.length() - " steps.".length()));
        
        Map<String, State> states = new HashMap<>();
        for (int i = 3; i < lines.size(); i += 10) {
            String nameLine = lines.get(i);
            String name = nameLine.substring("In state ".length(), nameLine.length() - 1);
            
            State state = State.parse(lines.subList(i + 1, i + 9));
            
            states.put(name, state);
        }
        states = Map.copyOf(states);
        
        return new TuringMachine(initialState, steps, states);
    }
    
    /**
     * Constructor.
     * 
     * @param initialState name of the initial state
     * @param steps number of steps after which to compute the diagnostic checksum
     * @param states states of the machine, indexed by name
     */
    private TuringMachine(String initialState, int steps, Map<String, State> states) {
        super();
        this.initialState = initialState;
        this.steps = steps;
        this.states = states;
    }
    
    
}
