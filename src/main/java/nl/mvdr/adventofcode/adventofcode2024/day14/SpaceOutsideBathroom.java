package nl.mvdr.adventofcode.adventofcode2024.day14;

import java.util.Set;
import java.util.stream.Collectors;

import nl.mvdr.adventofcode.point.Point;

record SpaceOutsideBathroom(int width, int height, Set<Robot> robots, int timePassedInSeconds) {

    SpaceOutsideBathroom(int width, int height, Set<Robot> robots) {
        this(width, height, robots, 0);
    }
    
    SpaceOutsideBathroom move(int seconds) {
        var newRobots = robots.stream()
                .map(robot -> robot.move(seconds, width, height))
                .collect(Collectors.toSet());
        return new SpaceOutsideBathroom(width, height, newRobots, timePassedInSeconds + seconds);
    }
    
    long safetyFactor() {
        long robotsInTopLeftQuadrant = robots.stream()
                .filter(robot -> robot.position().x() < width / 2)
                .filter(robot -> robot.position().y() < height / 2)
                .count();
        
        long robotsInTopRightQuadrant = robots.stream()
                .filter(robot -> width / 2 < robot.position().x())
                .filter(robot -> robot.position().y() < height / 2)
                .count();
        
        long robotsInBottomLeftQuadrant = robots.stream()
                .filter(robot -> robot.position().x() < width / 2)
                .filter(robot -> height / 2 < robot.position().y())
                .count();
        
        long robotsInBottomRightQuadrant = robots.stream()
                .filter(robot -> width / 2 < robot.position().x())
                .filter(robot -> height / 2 < robot.position().y())
                .count();
        
        return robotsInTopLeftQuadrant * robotsInTopRightQuadrant * robotsInBottomLeftQuadrant * robotsInBottomRightQuadrant;
    }
    
    @Override
    public final String toString() {
        var robotPositions = robots.stream()
                .map(Robot::position)
                .collect(Collectors.toSet());
        return "Space after " + timePassedInSeconds + " seconds:\n" + Point.visualize(robotPositions);
    }
}
