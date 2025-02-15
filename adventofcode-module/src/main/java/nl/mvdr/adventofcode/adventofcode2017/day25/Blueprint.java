package nl.mvdr.adventofcode.adventofcode2017.day25;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Blueprint of a Turing machine.
 *
 * @author Martijn van de Rijdt
 */
class Blueprint {
    /** The number of steps after which to compute the diagnostic checksum. */
    private final int steps;
    
    /** Definition of the Turing machine itself. */
    private final TuringMachineDefinition turingMachineDefinition;
    
    /**
     * Parses a text file into a Turing machine.
     * 
     * @param linesStream puzzle input
     * @return Turing machine represented by this text file
     */
    static Blueprint parse(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toList());
        
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
        
        return new Blueprint(steps, new TuringMachineDefinition(initialState, states));
    }

    /**
     * Constructor.
     * 
     * @param steps number of steps after which to compute the diagnostic checksum
     * @param turingMachineDefinition definition of the Turing machine
     */
    private Blueprint(int steps, TuringMachineDefinition turingMachineDefinition) {
        super();
        this.steps = steps;
        this.turingMachineDefinition = turingMachineDefinition;
    }
    
    int getSteps() {
        return steps;
    }
    
    TuringMachineDefinition getTuringMachineDefinition() {
        return turingMachineDefinition;
    }
}
