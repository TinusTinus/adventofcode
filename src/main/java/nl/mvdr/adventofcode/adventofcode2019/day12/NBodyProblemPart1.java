package nl.mvdr.adventofcode.adventofcode2019.day12;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Point3D;

/**
 * Solution to the day 12 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/12">The N-Body Problem</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NBodyProblemPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NBodyProblemPart1.class);
    
    private final int steps;
    
    /**
     * Constructor.
     * 
     * @param steps number of simulation steps to run
     */
    NBodyProblemPart1(int steps) {
        super();
        this.steps = steps;
    }
    
    /** Constructor. */
    public NBodyProblemPart1() {
        this(1_000);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return total energy in the system after simulating the moons for 1000 steps
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Moon> system = lines.filter(Predicate.not(String::isBlank))
                .map(Moon::parse)
                .collect(Collectors.toList());
        LOGGER.debug("System: {}", system);
        
        for (int i = 0; i != steps; i++) {
            Map<Moon, Point3D> newVelocities = new HashMap<>();
            for (Moon moon : system) {
                newVelocities.put(moon, moon.getVelocity());
            }
            
            for (Moon moon0 : system) {
                for (Moon moon1 : system) {
                    if (moon0.getLocation().getX() < moon1.getLocation().getX()) {
                        newVelocities.merge(moon0, new Point3D(1, 0, 0), Point3D::add);
                        newVelocities.merge(moon1, new Point3D(-1, 0, 0), Point3D::add);
                    } else if (moon0.getLocation().getX() < moon1.getLocation().getX()) {
                        newVelocities.merge(moon0, new Point3D(-1, 0, 0), Point3D::add);
                        newVelocities.merge(moon1, new Point3D(1, 0, 0), Point3D::add);
                    }
                    
                    if (moon0.getLocation().getY() < moon1.getLocation().getY()) {
                        newVelocities.merge(moon0, new Point3D(0, 1, 0), Point3D::add);
                        newVelocities.merge(moon1, new Point3D(0, -1, 0), Point3D::add);
                    } else if (moon0.getLocation().getY() < moon1.getLocation().getY()) {
                        newVelocities.merge(moon0, new Point3D(0, -1, 0), Point3D::add);
                        newVelocities.merge(moon1, new Point3D(0, 1, 0), Point3D::add);
                    }
                    
                    if (moon0.getLocation().getZ() < moon1.getLocation().getZ()) {
                        newVelocities.merge(moon0, new Point3D(0, 0, 1), Point3D::add);
                        newVelocities.merge(moon1, new Point3D(0, 0, -1), Point3D::add);
                    } else if (moon0.getLocation().getZ() < moon1.getLocation().getZ()) {
                        newVelocities.merge(moon0, new Point3D(0, 0, -1), Point3D::add);
                        newVelocities.merge(moon1, new Point3D(0, 0, 1), Point3D::add);
                    }
                }
            }
            
            system = system.stream()
                    .map(moon -> moon.updateVelocity(newVelocities.get(moon)))
                    .collect(Collectors.toList());
            
            LOGGER.debug("Updated system: {}", system);
        }
        
        return system.stream()
                .mapToInt(Moon::computeTotalEnergy)
                .sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        NBodyProblemPart1 instance = new NBodyProblemPart1();

        String result = instance.solve("input-day12-2019.txt");

        LOGGER.info(result);
    }
}
