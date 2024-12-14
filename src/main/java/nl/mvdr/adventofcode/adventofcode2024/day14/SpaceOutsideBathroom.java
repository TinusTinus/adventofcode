package nl.mvdr.adventofcode.adventofcode2024.day14;

import java.util.Set;
import java.util.stream.Collectors;

record SpaceOutsideBathroom(int width, int height, Set<Robot> robots) {

    SpaceOutsideBathroom move(int seconds) {
        var newRobots = robots.stream()
                .map(robot -> robot.move(seconds, width, height))
                .collect(Collectors.toSet());
        return new SpaceOutsideBathroom(width, height, newRobots);
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
}
