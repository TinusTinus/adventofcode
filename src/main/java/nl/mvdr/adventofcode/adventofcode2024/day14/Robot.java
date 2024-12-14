package nl.mvdr.adventofcode.adventofcode2024.day14;

import nl.mvdr.adventofcode.point.Point;

record Robot(Point position, Point velocity) {
    static Robot parse(String line) {
        var index = line.indexOf(" v=");
        if (!line.startsWith("p=") || index < 0) {
            throw new IllegalArgumentException("Unable to parse: " + line);
        }
        
        var position = Point.parse(line.substring(2, index));
        var velocity = Point.parse(line.substring(index + 3, line.length()));
        
        return new Robot(position, velocity);
    }
    
    Robot move(int seconds, int width, int height) {
        var newPosition = position.add(velocity.multiply(seconds));
        newPosition = new Point(
                Math.floorMod(newPosition.x(), width),
                Math.floorMod(newPosition.y(), height));
        return new Robot(newPosition, velocity);
    }
}
