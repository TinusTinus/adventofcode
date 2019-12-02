package nl.mvdr.adventofcode.adventofcode2019.day02;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 2 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/2">1202 Program Alarm</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ProgramAlarmPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProgramAlarmPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return 100 * noun + verb, for the input noun and verb that cause the program to produce the output 19690720
     */
    @Override
    public int solve(Stream<String> lines) {
        Program initialProgram = Program.parse(lines.findFirst().orElseThrow());
        
        return IntStream.range(0, 100)
                .boxed()
                .flatMap(noun -> IntStream.range(0, 100).mapToObj(verb -> new NounAndVerb(noun.intValue(), verb)))
                .filter(nounAndVerb -> nounAndVerb.apply(initialProgram).execute().getMemory().get(0).intValue() == 19690720)
                .map(NounAndVerb::computeAnswer)
                .findAny()
                .get()
                .intValue();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ProgramAlarmPart2 instance = new ProgramAlarmPart2();

        String result = instance.solve("input-day02-2019.txt");

        LOGGER.info(result);
    }
}
