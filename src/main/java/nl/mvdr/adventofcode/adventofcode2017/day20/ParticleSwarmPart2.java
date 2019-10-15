package nl.mvdr.adventofcode.adventofcode2017.day20;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.point.Point3D;

/**
 * Solution to the day 20 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/20">Particle Swarm</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ParticleSwarmPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParticleSwarmPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the particle which will stay closest to position <0,0,0> in the long term
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Particle> particles = Particle.parse(inputFilePath);
        LOGGER.debug("Starting particles: {}", particles);
        
        // Note: if the number of ticks is too high, integer overflows may become an issue
        for (int tick = 0; tick != 1_000; tick++) {
            particles = particles.stream()
                    .map(Particle::tick)
                    .collect(Collectors.toList());
            
            removeCollisions(particles);
            
            LOGGER.debug("Particles: {}", particles);
        }
        
        return Integer.valueOf(particles.size());
    }
    
    private void removeCollisions(List<Particle> particles) {
        Map<Point3D, Integer> positionCounts = new HashMap<>();
        particles.stream()
            .map(Particle::getPosition)
            .forEach(position -> {
                int newCount = positionCounts.getOrDefault(position, Integer.valueOf(0)).intValue() + 1;
                positionCounts.put(position, Integer.valueOf(newCount));
            });
        positionCounts.entrySet().stream()
                .filter(entry -> 1 < entry.getValue().intValue())
                .map(Entry::getKey)
                .forEach(collisionPosition -> particles.removeIf(particle -> particle.getPosition().equals(collisionPosition)));
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ParticleSwarmPart2 instance = new ParticleSwarmPart2();

        String result = instance.solve("input-day20-2017.txt");

        LOGGER.info(result);
    }
}
