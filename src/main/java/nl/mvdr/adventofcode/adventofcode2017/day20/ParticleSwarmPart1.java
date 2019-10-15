package nl.mvdr.adventofcode.adventofcode2017.day20;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 20 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/20">Particle Swarm</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ParticleSwarmPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParticleSwarmPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the particle which will stay closest to position <0,0,0> in the long term
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        return null;
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
