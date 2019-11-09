package nl.mvdr.adventofcode.adventofcode2016.day04;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 3 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/3">Squares With Three Sides</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SecurityThroughObscurityPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityThroughObscurityPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the sector ID of the room where North Pole objects are stored
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Room> rooms = Room.parse(inputFilePath);
        
        int result = rooms.stream()
                .filter(Room::isReal)
                .filter(room -> "northpole object storage".equals(room.decryptName()))
                .mapToInt(Room::getSectorId)
                .findFirst()
                .getAsInt();
        
        return Integer.valueOf(result);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SecurityThroughObscurityPart2 instance = new SecurityThroughObscurityPart2();

        String result = instance.solve("input-day04-2016.txt");

        LOGGER.info(result);
    }
}
