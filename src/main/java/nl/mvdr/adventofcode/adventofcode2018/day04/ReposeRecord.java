package nl.mvdr.adventofcode.adventofcode2018.day04;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Common superclass for {@link ReposeRecordPart1} and {@link ReposeRecordPart2}.
 *
 * @author Martijn van de Rijdt
 * 
 */
abstract class ReposeRecord implements IntSolver {
    /** Regular expression for a record indicating the start of a shift. Group 1 is the guard id. */
    private static final Pattern PATTERN_BEGIN_SHIFT = Pattern.compile("\\[\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d\\] Guard #(\\d+) begins shift");
    /** Regular expression for a record indicating that a guard falls asleep. Group 1 is the minute of the midnight hour. */
    private static final Pattern PATTERN_FALL_ASLEEP = Pattern.compile("\\[\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:(\\d\\d)\\] falls asleep");
    /** Regular expression for a record indicating that a guard wakes up. Group 1 is the minute of the midnight hour. */
    private static final Pattern PATTERN_WAKE_UP = Pattern.compile("\\[\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:(\\d\\d)\\] wakes up");
    
    /** Comparator, used to determine which guard to pick. */
    private final Comparator<Guard> comparator;
    
    /**
     * Constructor.
     * 
     * @param comparator used to determine which guard to pick
     */
    protected ReposeRecord(Comparator<Guard> comparator) {
        super();
        
        this.comparator = comparator;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        List<String> records = lines
                // ignore empty lines (the last line in the file)
                .filter(Predicate.not(String::isBlank))
                // sort input in chronological order (which is equal to lexicographical order)
                .sorted()
                .collect(Collectors.toList());
        
        Map<Integer, Guard> guards = process(records);
        
        Guard guard = guards.values().stream()
                .max(comparator)
                .get();
        
        return guard.getId() * guard.computeMostAsleepMinute();
    }

    private Map<Integer, Guard> process(List<String> records) {
        Map<Integer, Guard> guards = new HashMap<>();
        
        Guard currentGuard = null;
        Iterator<String> iterator = records.iterator();
        while (iterator.hasNext()) {
            String record = iterator.next();
            Matcher matcher = PATTERN_BEGIN_SHIFT.matcher(record);
            if (matcher.matches()) {
                // Start of a new shift.
                int guardId = Integer.parseInt(matcher.group(1));
                guards.putIfAbsent(Integer.valueOf(guardId), new Guard(guardId));
                currentGuard = guards.get(Integer.valueOf(guardId));
            } else {
                matcher = PATTERN_FALL_ASLEEP.matcher(record);
                if (!matcher.matches()) {
                    throw new IllegalStateException("Unexpected record: " + record);
                }
                // The current guard fell asleep.
                int napStart = Integer.parseInt(matcher.group(1));
                
                // The next record must be of the current guard waking up.
                record = iterator.next();
                matcher = PATTERN_WAKE_UP.matcher(record);
                if (!matcher.matches()) {
                    throw new IllegalStateException("Unexpected record: " + record);
                }
                int napEnd = Integer.parseInt(matcher.group(1));
                Objects.requireNonNull(currentGuard).sleep(napStart, napEnd);
            }
        }
        return guards;
    }
}
