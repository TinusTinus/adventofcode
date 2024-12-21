package nl.mvdr.adventofcode.adventofcode2024.day21;

import nl.mvdr.adventofcode.point.Point;

enum DirectionalKeypadButton implements KeypadButton<DirectionalKeypadButton> {
    
    UP(1, 0),
    A(2, 0),
    LEFT(0, 1),
    DOWN(1, 1),
    RIGHT(2, 1);
    
    private final Point location;
    
    private DirectionalKeypadButton(int x, int y) {
        this.location = new Point(x, y);
    }
    
    @Override
    public Point getLocation() {
        return location;
    }
}
