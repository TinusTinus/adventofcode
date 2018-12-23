package nl.mvdr.adventofcode.adventofcode2018.day04;

import java.util.Comparator;
import java.util.stream.IntStream;

/**
 * Information about a guard.
 *
 * Note that this class is mutable.
 *
 * @author Martijn van de Rijdt
 */
class Guard {
    /** Identification of this guard. */
    private final int id;
    
    /** The total number of minutes that this guard has been asleep during midnight hours. */
    private int minutesAsleep;
    
    /** Map of each minute of the midnight hour, to the number of times the guard has been asleep during that minute. */
    private int[] numberOfTimesAsleep;

    /** Constructor. */
    Guard(int id) {
        super();
        
        this.id = id;
        this.minutesAsleep = 0;
        
        this.numberOfTimesAsleep = new int[60];
    }
    
    /**
     * Records a nap during the midnight hour.
     * 
     * @param startMinute minute the guard fell asleep (inclusive boundary)
     * @param endMinute minute the guard woke up (exclusive boundary)
     */
    void sleep(int startMinute, int endMinute) {
        this.minutesAsleep += endMinute - startMinute;
        
        IntStream.range(startMinute, endMinute)
                .forEach(minute -> numberOfTimesAsleep[minute]++);
    }

    int getId() {
        return id;
    }
    
    int getMinutesAsleep() {
        return minutesAsleep;
    }
    
    /** @return the minute during the midnight hour, in which the guard has been asleep the most number of times */
    int computeMostAsleepMinute() {
        return IntStream.range(0, numberOfTimesAsleep.length)
            .mapToObj(Integer::valueOf)
            .max(Comparator.comparing(i -> numberOfTimesAsleep[i]))
            .get()
            .intValue();
    }
}
