package nl.mvdr.adventofcode.adventofcode2018.timetraveldevice;

/**
 * Exception indicating that live lock / an infinite loop has been detected.
 *
 * @author Martijn van de Rijdt
 */
@SuppressWarnings("serial")
public class LiveLockException extends RuntimeException {
    /**
     * Constructor.
     * 
     * @param message exception message
     */
    LiveLockException(String message) {
        super(message);
    }
}
