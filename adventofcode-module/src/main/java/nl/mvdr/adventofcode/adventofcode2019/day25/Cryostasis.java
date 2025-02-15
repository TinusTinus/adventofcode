package nl.mvdr.adventofcode.adventofcode2019.day25;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LinesSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.AsciiOutputDebugLogger;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 25 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/25">Tractor Beam</a>.
 * 
 * Note: this program is interactive. Provide keyboard input to play the text adventure.
 * 
 * @author Martijn van de Rijdt
 */
public class Cryostasis implements LinesSolver<Void> {

    /**
     * {@inheritDoc}
     * 
     * @return null
     */
    @Override
    public Void solve(Stream<String> lines) {
        Program.parse(lines.findFirst().orElseThrow())
                .withOutput(new AsciiOutputDebugLogger()::handleOutput)
                .withInput(this::getInput)
                .execute();
        
        return null;
    }
    
    /** @return input character from {@link System#in} */
    private long getInput() {
        try {
            // Ignore carriage returns
            int result = '\r';
            while (result == '\r') {
                result = System.in.read();
            }
            return result;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        Cryostasis instance = new Cryostasis();
        instance.solve("input-day25-2019.txt");
    }
}
 