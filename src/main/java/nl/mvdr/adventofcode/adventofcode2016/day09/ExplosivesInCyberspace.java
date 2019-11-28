package nl.mvdr.adventofcode.adventofcode2016.day09;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 9 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/9">Explosives in Cyberspace</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class ExplosivesInCyberspace implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExplosivesInCyberspace.class);
    
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
    public long solve(Stream<String> lines) {
        String remainingText = lines.findFirst().orElseThrow();
        long result = 0;
        
        Map<String, String> expansionCache = new HashMap<>();
        
        while (0 < remainingText.length()) {
            LOGGER.debug("Remaining text: {} characters", Integer.valueOf(remainingText.length()));
            
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
                        String expandedSequence = expansionCache.computeIfAbsent(sequence, s -> expand(s, repeats));
                        remainingText = expandedSequence + matcher.group(3).substring(characters);
                    } else {
                        // No need to actually expand the sequence: just count its length.
                        result += characters * repeats;
                        remainingText = matcher.group(3).substring(characters);
                    }
                } else {
                    // Just an opening bracket, not the start of a compressed sequence.
                    result++;
                    remainingText = remainingText.substring(1);
                }
            } else {
                // remainingText contain a "(", but not at its start.
                result += index;
                remainingText = remainingText.substring(index);
            }
        }
        
        return result;
    }
    
    private String expand(String sequence, int repeats) {
        return IntStream.range(0, repeats)
                .mapToObj(i -> sequence)
                .collect(Collectors.joining());
    }
}
