package nl.mvdr.adventofcode.adventofcode2016.day09;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 9 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/9">Explosives in Cyberspace</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ExplosivesInCyberspacePart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExplosivesInCyberspacePart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of lit pixels
     */
    @Override
    public int solve(Stream<String> lines) {
        // TODO implement part 2
        
        String remainingText = lines.findFirst().orElseThrow();
        int result = 0;
        
        while (0 < remainingText.length()) {
            int index = remainingText.indexOf("(");
            if (index == -1) {
                // No more opening brackets.
                result += remainingText.length();
                remainingText = "";
            } else if (index == 0) {
                // remainingText starts with "("; this may be the start of a compressed sequence.
                Pattern pattern = Pattern.compile("\\((\\d*)x(\\d*)\\)(.*)");
                Matcher matcher = pattern.matcher(remainingText);
                if (matcher.matches()) {
                    // Start of a compressed sequence!
                    int characters = Integer.parseInt(matcher.group(1));
                    int repeats = Integer.parseInt(matcher.group(2));
                    result += characters * repeats;
                    remainingText = matcher.group(3).substring(characters);
                } else {
                    // Just an opening bracket, not the start of a compressed sequence.
                    result++;
                    remainingText = remainingText.substring(1);
                }
            } else {
                result += index;
                remainingText = remainingText.substring(index);
            }
        }
        
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ExplosivesInCyberspacePart2 instance = new ExplosivesInCyberspacePart2();

        String result = instance.solve("input-day09-2016.txt");

        LOGGER.info(result);
    }

}
