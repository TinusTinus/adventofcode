package nl.mvdr.adventofcode.adventofcode2017.day14;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 12 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/14">Disk Defragmentation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DiskDefragmentationPart2 extends DiskDefragmentation {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiskDefragmentationPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return number of regions
     */
    @Override
    protected Integer solve(Set<Point> squares) {
        Set<Set<Point>> regions = new HashSet<>();
        
        squares.forEach(square -> {
            Set<Point> newRegion = new HashSet<>();
            newRegion.add(square);
            
            Set<Set<Point>> matchingRegions = new HashSet<>();
            square.neighbours().forEach(neighbour -> {
                regions.stream()
                    .filter(region -> region.contains(neighbour))
                    .forEach(matchingRegions::add);
            });
            regions.removeAll(matchingRegions);
            matchingRegions.forEach(newRegion::addAll);
            
            regions.add(newRegion);
        });
        
        return Integer.valueOf(regions.size());
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DiskDefragmentationPart2 instance = new DiskDefragmentationPart2();

        String result = instance.solve("input-day14-2017.txt");

        LOGGER.info(result);
    }
}
