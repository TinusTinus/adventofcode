package nl.mvdr.adventofcode.adventofcode2019.day23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 23 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/23">Category Six</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class CategorySix implements LongSolver {

    /**
     * {@inheritDoc}
     * 
     * @return y value
     */
    @Override
    public long solve(Stream<String> lines) {
        // Parse the input.
        Program program = Program.parse(lines.findFirst().orElseThrow());
        
        Nat nat = new Nat();
        
        // Input values, indexed by their address.
        Map<Long, Queue<Long>> inputs = new HashMap<>();
        
        List<Program> computers = LongStream.range(0L, 50L)
                // Make sure each program is provided with its address first.
                .mapToObj(address -> inputs.computeIfAbsent(Long.valueOf(address), _ -> new LinkedList<>(Set.of(Long.valueOf(address)))))
                .map(queue -> program.withQueueInput(queue))
                .map(computer -> computer.withOutput(new OutputHandler(inputs, nat)::handleOutput))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        
        return solve(nat, inputs, computers);
    }

    /**
     * Solver method.
     * 
     * @param nat the Not Always Transmitting Device
     * @param inputs input queues, indexed by their address
     * @param computers computers
     * @return y value
     */
    abstract long solve(Nat nat, Map<Long, Queue<Long>> inputs, List<Program> computers);
}
 