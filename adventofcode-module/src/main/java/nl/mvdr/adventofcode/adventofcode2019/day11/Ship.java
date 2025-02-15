package nl.mvdr.adventofcode.adventofcode2019.day11;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.point.TurnDirection;

/**
 * Representation of the current state of the space ship,
 * as the hull painting robot is doing its job.
 * 
 * This class is mutable.
 * 
 * @author Martijn van de Rijdt
 */
class Ship {
    private Hull hull;
    private Robot robot;
    
    private boolean justPainted;
    
    /**
     * Constructor.
     * 
     * @param startOnWhitePanel whether to start on a white panel
     */
    Ship(boolean startOnWhitePanel) {
        super();
        this.hull = new Hull();
        this.robot = new Robot();
        this.justPainted = false;
        
        if (startOnWhitePanel) {
            this.hull = hull.paint(robot.getLocation(), Color.WHITE);
            // Note: this adds <0,0> to the areas that have been painted.
            // However this location is guaranteed to be the first to be painted anyway.
        }
    }
    
    /**
     * Returns an input value for the robot's Intcode program.
     * 
     * @return representation of the panel at the robot's current location
     */
    public long getColorCode() {
        Point location = robot.getLocation();
        Color color = hull.getColor(location);
        return color.getCode();
    }
    
    /**
     * Processes an output value from the robot's Intcode program.
     * 
     * The program is expected to alternatingly return a color to paint and a turn
     * direction to turn and move the robot.
     * 
     * @param value representation of either a turn direction or a color
     */
    public void process(long value) {
        if (justPainted) {
            // Turn
            TurnDirection turnDirection = TurnDirection.of(value);
            robot = robot.turnAndMove(turnDirection);
            justPainted = false;
        } else {
            // Paint
            Color color = Color.of(value);
            hull = hull.paint(robot.getLocation(), color);
            justPainted = true;
        }
    }
    
    Hull getHull() {
        return hull;
    }
}
