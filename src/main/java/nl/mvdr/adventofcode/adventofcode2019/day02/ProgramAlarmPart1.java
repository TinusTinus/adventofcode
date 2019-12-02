package nl.mvdr.adventofcode.adventofcode2019.day02;

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
public class ProgramAlarmPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProgramAlarmPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return value left at position 0 after the program halts
     */
    @Override
    public int solve(Stream<String> lines) {
        Program program = Program.parse(lines.findFirst().orElseThrow());
        program = program.execute();
        return program.getIntegers().get(0).intValue();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ProgramAlarmPart1 instance = new ProgramAlarmPart1();

        String result = instance.solve("input-day02-2019.txt");

        LOGGER.info(result);
    }
}
