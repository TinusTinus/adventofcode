package nl.mvdr.adventofcode.adventofcode2016.day25;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.adventofcode2016.assembunny.Program;
import nl.mvdr.adventofcode.adventofcode2016.assembunny.Register;
import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    @Override
    public int solve(Stream<String> linesStream) {
        var lines = linesStream.toList();
        return IntStream.iterate(1, i -> i + 1)
                .filter(inputValue -> isValidInputLine(inputValue, lines))
                .findFirst()
                .orElseThrow();
    }
    
    private boolean isValidInputLine(int inputValue, List<String> programLines) {
        var startingRegisterValues = Map.of(
                Register.A, inputValue,
                Register.B, 0,
                Register.C, 0,
                Register.D, 0);
        var outputChecker = new OutputChecker();
        var program = Program.parse(programLines.stream(), startingRegisterValues, outputChecker::check);
        program.executeWhile(() -> !outputChecker.isDone());
        return outputChecker.isSuccessful();
    }
    
    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day25.txt");

        LOGGER.info(result);
    }
}
