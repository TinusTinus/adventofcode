package nl.mvdr.adventofcode.adventofcode2016.day17;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

class Part1Test extends SolverTest<Part1> {
    
    Part1Test() {
        super(Part1.class);
    }
    
    static Stream<Arguments> testSolution() {
        return Stream.of(Arguments.of("DDRRRD", "example-day17-1.txt"),
                Arguments.of("DDUDRLRRUDRD", "example-day17-2.txt"),
                Arguments.of("DRURDRUDDLLDLUURRDULRLDUUDDDRR", "example-day17-3.txt"));
    }
    
    @Test
    void testNoValidPath() {
        var solver = new Part1();
        
        assertThrows(IllegalStateException.class, () -> solver.solve("example-day17-0.txt"));
    }
}
