package nl.mvdr.adventofcode.adventofcode2016.day16;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LinesSolver;

class DragonChecksumSolver implements LinesSolver<String> {

    private final int diskLength;
    
    DragonChecksumSolver(int diskLength) {
        this.diskLength = diskLength;
    }
    
    @Override
    public String solve(Stream<String> lines) {
        var input = lines.findFirst().orElseThrow();
        var curve = dragonCurve(input);
        return checksum(curve);
    }
    
    String dragonCurve(String input) {
        String result = dragonCurveStep(input);
        while (result.length() < diskLength) {
            result = dragonCurveStep(result);
        }
        result = result.substring(0, diskLength);
        return result;
    }
    
    static String dragonCurveStep(String input) {
        String b = new StringBuilder(input)
                .reverse()
                .toString()
                .replace('1', 't')
                .replace('0', '1')
                .replace('t', '0');
        return input + "0" + b;
    }
    
    static String checksum(String input) {
        String result = checksumStep(input);
        while (result.length() % 2 == 0) {
            result = checksumStep(result);
        }
        return result;
    }
    
    static String checksumStep(String input) {
        return IntStream.range(0, input.length() / 2)
                .mapToObj(i -> input.charAt(i * 2) == (input.charAt(i * 2 + 1)) ? "1" : "0")
                .collect(Collectors.joining());
    }
}
