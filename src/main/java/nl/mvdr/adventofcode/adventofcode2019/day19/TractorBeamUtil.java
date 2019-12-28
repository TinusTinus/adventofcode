package nl.mvdr.adventofcode.adventofcode2019.day19;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;
import nl.mvdr.adventofcode.point.Point;

/**
 * Utility class for tractor beam computations.
 *
 * @author Martijn van de Rijdt
 */
class TractorBeamUtil {
    private TractorBeamUtil() {
        // private constructor to prevent utility class instantiation
    }
    
    /**
     * Determines whether the drone is being affected by the tractor beam.
     * 
     * @param drone location of the drone
     * @param program Intcode program from the puzzle input
     * @return whether the drone is being pulled
     */
    static boolean isBeingPulled(Point drone, Program program) {
        Queue<Long> inputQueue = new LinkedList<>();
        inputQueue.offer(Long.valueOf(drone.getX()));
        inputQueue.offer(Long.valueOf(drone.getY()));
        List<Long> outputList = new ArrayList<>(1);
        
        program.withInput(inputQueue::poll)
                .withOutput(outputList::add)
                .execute();
        
        if (outputList.size() != 1) {
            throw new IllegalStateException("Unexpected output received from intcode program: " + outputList);
        }
        
        return outputList.get(0).longValue() == 1L;
    }
}
