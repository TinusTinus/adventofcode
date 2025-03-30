package nl.mvdr.adventofcode.adventofcode2016.day13;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

record MazeLayout(int officeDesignersFavoriteNumber) {

    static MazeLayout parse(Stream<String> lines) {
        var firstLine = lines.findFirst().orElseThrow();
        var officeDesignersFavoriteNumber = Integer.parseInt(firstLine);
        return new MazeLayout(officeDesignersFavoriteNumber);
    }
    
    /// Returns whether the given coordinate is an open space. If the result is false, it is a wall.
    boolean isOpenSpace(Point point) {
        var x = point.x();
        var y = point.y();
        
        var sum = x*x + 3*x + 2*x*y + y + y*y + officeDesignersFavoriteNumber;
        
        var oneBits = Integer.toBinaryString(sum)
                .chars()
                .filter(c -> c == '1')
                .count();
        
        return 0 <= x && 0 <= y && oneBits % 2 == 0;
    }
}
