package nl.mvdr.adventofcode.adventofcode2018.day23;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.point.Point3D;

/**
 * Solution to the day 23 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/23">Experimental Emergency Teleportation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ExperimentalEmergencyTeleportationPart2 implements PathSolver<Integer> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExperimentalEmergencyTeleportationPart2.class);
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        
        Set<Nanobot> nanobots = Nanobot.parse(inputFilePath);
        
        Nanobot startOctohedron = generateStartOctahedron(nanobots);

        Map<Nanobot, Long> overlapCache = new HashMap<>();
        Comparator<Nanobot> comparator = Comparator.<Nanobot, Long>comparing(nanobot -> overlapCache.computeIfAbsent(nanobot, n -> Long.valueOf(-n.overlap(nanobots))))
                .thenComparingInt(nanobot -> nanobot.getPosition().manhattanDistanceToOrigin());
        
        Queue<Nanobot> pQ = new PriorityQueue<>(10, comparator);
        pQ.add(startOctohedron);
        while (0 < pQ.peek().getRadius()) {
            Nanobot n = pQ.poll();
            pQ.addAll(splitNanobot(n));
        }
        
        Nanobot n = pQ.poll();
        int result = n.getPosition().manhattanDistanceToOrigin();
        return Integer.valueOf(result);
    }
    
    /**
     * Generates an octahedron containing all of the given nanobots within its radius.
     * 
     * @param nanobots nanobots
     * @return octahedron, represented as another nanobot
     */
    private static Nanobot generateStartOctahedron(Set<Nanobot> nanobots) {
        int minX = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point3D::getX)
                .min()
                .getAsInt();
        int maxX = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point3D::getX)
                .max()
                .getAsInt();
        int minY = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point3D::getY)
                .min()
                .getAsInt();
        int maxY = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point3D::getY)
                .max()
                .getAsInt();
        int minZ = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point3D::getZ)
                .min()
                .getAsInt();
        int maxZ = nanobots.stream()
                .map(Nanobot::getPosition)
                .mapToInt(Point3D::getZ)
                .max()
                .getAsInt();
        
        Point3D location = new Point3D(minX + (maxX - minX) / 2, minY + (maxY - minY) / 2, minZ + (maxZ - minZ) / 2);
        int radius = 1;
        boolean containsAll = false;
        while (!containsAll) {
            radius *= 2;
            Nanobot newNanobot = new Nanobot(location, radius);
            containsAll = nanobots.stream()
                    .map(Nanobot::getPosition)
                    .allMatch(newNanobot::inRange);
        }
        return new Nanobot(location, radius);
    }
    
    /**
     * Splits the given octahedron into multiple smaller octahedrons.
     * 
     * @param src source
     * @return ocathedrons
     */
    private static List<Nanobot> splitNanobot(Nanobot src) {
        List<Nanobot> result = new ArrayList<>();
        int newR;
        int offset = 1;
        if (src.getRadius() == 1) {
            newR = 0;
            result.add(new Nanobot(src.getPosition(), newR));
        } else if (src.getRadius() == 2) {
            newR = 1;
        } else {
            newR = (int) Math.ceil((double)(src.getRadius() * 2) / 3);
            offset = src.getRadius() - newR;
        }
        
        src.getPosition().offsetOnAxes(offset).stream()
                .map(position -> new Nanobot(position, newR))
                .forEach(result::add);
        
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ExperimentalEmergencyTeleportationPart2 solver = new ExperimentalEmergencyTeleportationPart2();
        String solution = solver.solve("input-day23-2018.txt");
        LOGGER.info(solution);
    }
}
