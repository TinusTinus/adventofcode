package nl.mvdr.adventofcode.adventofcode2016.day17;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

class Part2Test extends SolverTest<Part2> {
    
    Part2Test() {
        super(Part2.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(Arguments.of("370", "example-day17-1.txt"),
                Arguments.of("492", "example-day17-2.txt"),
                Arguments.of("830", "example-day17-3.txt"));
    }
    
    @Test
    void testNoValidPath() {
        var solver = new Part1();
        
        assertThrows(IllegalStateException.class, () -> solver.solve("example-day17-0.txt"));
    }
}
