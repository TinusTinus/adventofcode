package nl.mvdr.adventofcode.adventofcode2016.day09;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 9 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/9">Explosives in Cyberspace</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class ExplosivesInCyberspace implements IntSolver {

    private final boolean recursiveExpansion;
    
    /**
     * Constructor.
     * 
     * @param recursiveExpansion whether to recursive perform expansion of compressed data
     */
    public ExplosivesInCyberspace(boolean recursiveExpansion) {
        super();
        this.recursiveExpansion = recursiveExpansion;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return length of the decompressed text
     */
    @Override
    public int solve(Stream<String> lines) {
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
                    if (recursiveExpansion) {
                        // Expand the sequence.
                        String sequence = matcher.group(3).substring(0, characters);
                        remainingText = "";
                        for (int i = 0; i != repeats; i++) {
                            remainingText += sequence;
                        }
                        remainingText += matcher.group(3).substring(characters);
                    } else {
                        result += characters * repeats;
                        remainingText = matcher.group(3).substring(characters);
                    }
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
}
