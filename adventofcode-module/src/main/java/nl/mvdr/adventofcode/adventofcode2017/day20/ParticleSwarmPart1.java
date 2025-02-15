package nl.mvdr.adventofcode.adventofcode2017.day20;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 20 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/20">Particle Swarm</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ParticleSwarmPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParticleSwarmPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the particle which will stay closest to position <0,0,0> in the long term
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Particle> particles = Particle.parse(lines);
        LOGGER.debug("Starting particles: {}", particles);
        
        // Note: if the number of ticks is too high, integer overflows may become an issue
        for (int tick = 0; tick != 1_000; tick++) {
            particles = particles.stream()
                    .map(Particle::tick)
                    .collect(Collectors.toList());
            
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Particles: {}", particles);
                LOGGER.debug("Closest particle: " + closestToOrigin(particles));
            }
        }
        
        return closestToOrigin(particles);
    }
    
    private int closestToOrigin(List<Particle> particles) {
        return IntStream.range(0, particles.size())
                .boxed()
                .min(Comparator.comparing(i -> Integer.valueOf(particles.get(i.intValue()).getPosition().manhattanDistanceToOrigin())))
                .orElseThrow()
                .intValue();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ParticleSwarmPart1 instance = new ParticleSwarmPart1();

        String result = instance.solve("input-day20-2017.txt");

        LOGGER.info(result);
    }
}
