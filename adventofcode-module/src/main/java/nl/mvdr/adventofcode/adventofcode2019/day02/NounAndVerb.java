package nl.mvdr.adventofcode.adventofcode2019.day02;

import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Combination of a noun and a verb.
 *
 * @author Martijn van de Rijdt
 */
class NounAndVerb {
    private final int noun;
    private final int verb;
    
    /**
     * Constructor.
     * 
     * @param noun 0 &le; noun &le; 99
     * @param verb 0 &le; verb &le; 99
     */
    NounAndVerb(int noun, int verb) {
        super();
        this.noun = noun;
        this.verb = verb;
    }

    /**
     * Applies the noun and verb on the given program.
     * 
     * @param program input program
     * @return program after application of this noun and verb
     */
    Program apply(Program program) {
        return program.set(1, noun)
                .set(2, verb);
    }
    
    /** @return answer to part 2 of the puzzle */
    int computeAnswer() {
        return 100 * noun + verb;
    }
}
