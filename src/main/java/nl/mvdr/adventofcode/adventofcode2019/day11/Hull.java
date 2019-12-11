package nl.mvdr.adventofcode.adventofcode2019.day11;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import nl.mvdr.adventofcode.point.Point;

/**
 * The hull of the spaceship.
 *
 * @author Martijn van de Rijdt
 */
class Hull {
    /**
     * Panels of the spaceship's hull which are white.
     * 
     * All other panels are black.
     */
    private final Set<Point> whitePanels;
    
    /** Panels which have been painted over at least once. */
    private final Set<Point> paintedPanels;
    
    /** Constructor for the initial state of the hull, where it is entirely black. */
    Hull() {
        this(Set.of(), Set.of());
    }
    
    /**
     * Constructor.
     * 
     * @param whitePanels panels that are currently white
     * @param paintedPanels panels that have been painted over at least once
     */
    private Hull(Set<Point> whitePanels, Set<Point> paintedPanels) {
        super();
        this.whitePanels = whitePanels;
        this.paintedPanels = paintedPanels;
    }
    
    /**
     * Gets the color of the given panel.
     * 
     * @param panel panel
     * @return panel's color
     */
    Color getColor(Point panel) {
        Color result;
        if (whitePanels.contains(panel)) {
            result = Color.WHITE;
        } else {
            result = Color.BLACK;
        }
        return result;
    }
    
    /**
     * Paints the given point.
     * 
     * @param point panel to paint
     * @param color to paint the panel
     * @return updated state of the hull
     */
    Hull paint(Point point, Color color) {
        Set<Point> newWhitePanels = new HashSet<>(whitePanels);
        if (color == Color.WHITE) {
            newWhitePanels.add(point);
        } else if (color == Color.BLACK) {
            newWhitePanels.remove(point);
        } else {
            throw new IllegalArgumentException("Unexpected color: " + color);
        }
        newWhitePanels = Collections.unmodifiableSet(newWhitePanels);
        
        Set<Point> newPaintedPanels = new HashSet<>(paintedPanels);
        newPaintedPanels.add(point);
        newPaintedPanels = Collections.unmodifiableSet(newPaintedPanels);
        
        return new Hull(newWhitePanels, newPaintedPanels);
    }
    
    /** @return the number of panels which have been painted at least once */
    int numberOfPaintedPanels() {
        return paintedPanels.size();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int minX = whitePanels.stream()
                .mapToInt(Point::getX)
                .min()
                .getAsInt();
        int maxX = whitePanels.stream()
                .mapToInt(Point::getX)
                .max()
                .getAsInt();
        int minY = whitePanels.stream()
                .mapToInt(Point::getY)
                .min()
                .getAsInt();
        int maxY = whitePanels.stream()
                .mapToInt(Point::getY)
                .max()
                .getAsInt();
        
        for (int y = minY; y != maxY + 1; y++) {
            for (int x = minX; x != maxX + 1; x++) {
                if (whitePanels.contains(new Point(x, y))) {
                    builder.append("#");
                } else {
                    builder.append(".");
                }
            }
            builder.append("\n");
        }
        
        return builder.toString();
    }
}
