package nl.mvdr.adventofcode.adventofcode2020.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PassportProcessingPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PassportProcessingPart1Test extends SolverTest<PassportProcessingPart1> {

    /** Constructor. */
    public PassportProcessingPart1Test() {
        super(PassportProcessingPart1.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * The first passport is valid - all eight fields are present. The second
     * passport is invalid - it is missing hgt (the Height field).
     * 
     * The third passport is interesting; the only missing field is cid, so it looks
     * like data from North Pole Credentials, not a passport at all! Surely, nobody
     * would mind if you made the system temporarily ignore missing cid fields.
     * Treat this "passport" as valid.
     * 
     * The fourth passport is missing two fields, cid and byr. Missing cid is fine,
     * but missing any other field is not, so this passport is invalid.
     * 
     * According to the above rules, your improved system would report 2 valid
     * passports.
     */
    @Test
    public void testExample() {
        testSolution("2", "example-day04-2020-0.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("239", "input-day04-2020.txt");
    }
}
