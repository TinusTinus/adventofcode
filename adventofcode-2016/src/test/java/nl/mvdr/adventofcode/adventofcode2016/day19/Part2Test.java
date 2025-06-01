package nl.mvdr.adventofcode.adventofcode2016.day19;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.SolverTest;

class Part2Test extends SolverTest<Part2> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Part2Test.class);
    
    Part2Test() {
        super(Part2.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(Arguments.of("2", "example-day19.txt"));
    }
    
    @ParameterizedTest
    @CsvSource( { "5, 2",
        "6, 3",
        "8, 7"
        } )
    void test(int startingElves, int expectedResult) {
        var solver = new Part2();
        
        var result = solver.solve(startingElves);
        
        assertEquals(expectedResult, result);
    }
    
    @Test
    void test() {
        var solver = new Part2();
        
        IntStream.range(1, 1_000)
                .forEach(startingElves -> LOGGER.debug("{} starting elves: elf {} wins", startingElves, solver.solve(startingElves)));
    }
}
