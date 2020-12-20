package nl.mvdr.adventofcode.adventofcode2017.day20;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point3D;

/**
 * A single particle.
 *
 * @author Martijn van de Rijdt
 */
class Particle {
    private final Point3D position;
    private final Point3D velocity;
    private final Point3D acceleration;
    
    /**
     * Parses the given text file into a list of particles.
     * 
     * @param lines puzzle input
     * @return list of particles
     */
    static List<Particle> parse(Stream<String> lines) {
        return lines
                // ignore empty lines (the last line in the file)
                .filter(line -> !line.isBlank())
                .map(Particle::parseParticle)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a single line of text into a particle.
     * 
     * @param text text representation of a particle, for example:
     *     "p=<-1027,-979,-188>, v=<7,60,66>, a=<9,1,-7>"
     * @return particle
     */
    private static Particle parseParticle(String text) {
        Pattern pattern = Pattern.compile("p=(<-?\\d+,-?\\d+,-?\\d+>), v=(<-?\\d+,-?\\d+,-?\\d+>), a=(<-?\\d+,-?\\d+,-?\\d+>)");
        
        Matcher matcher = pattern.matcher(text);
        
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Unable to parse: " + text);
        }
        
        Point3D position = Point3D.parse(matcher.group(1));
        Point3D velocity = Point3D.parse(matcher.group(2));
        Point3D acceleration = Point3D.parse(matcher.group(3));
        
        return new Particle(position, velocity, acceleration);
    }
    
    private Particle(Point3D position, Point3D velocity, Point3D acceleration) {
        super();
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
    
    /**
     * Performs a single tick for this particle.
     * 
     * @return updated particle
     */
    Particle tick() {
        Point3D newVelocity = this.velocity.add(this.acceleration);
        Point3D newPosition = this.position.add(newVelocity);
        return new Particle(newPosition, newVelocity, acceleration);
    }
    
    Point3D getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "p=" + position + ", v=" + velocity + ", a=" + acceleration;
    }
}
