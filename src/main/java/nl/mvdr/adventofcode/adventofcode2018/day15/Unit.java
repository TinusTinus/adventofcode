package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.util.Comparator;

/**
 * A unit in our little strategy game.
 *
 * @author Martijn van de Rijdt
 */
class Unit {
    /** Comparator for the <em>reading order</em>: top-to-bottom, then left-to-right. */
    static final Comparator<Unit> READING_ORDER = Comparator.comparing(Unit::getLocation);
    
    /** Maximum hit points. */
    private static final int MAX_HIT_POINTS = 200;
    
    /** Attack power. */
    static final int ATTACK_POWER = 3;

    /** This unit's race. */
    private final Race race;
    
    /** The unit's location. */
    private final Point location;

    /** Remaining hit points. Should be positive; otherwise this unit is no longer alive. */
    private final int hitPoints;

    /**
     * Constructor for a unit with full hit points.
     * 
     * @param race the unit's race
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
    Unit(Race race, int x, int y) {
        this(race, x, y, MAX_HIT_POINTS);
    }
    
    /**
     * Constructor.
     * 
     * @param race the unit's race
     * @param x horizontal coordinate
     * @param y vertical coordinate
     * @param hitPoints remaining hit points
     */
    Unit(Race race, int x, int y, int hitPoints) {
        this (race, new Point(x, y), hitPoints);
    }
    
    /**
     * Constructor.
     * 
     * @param race the unit's race
     * @param location the unit's location on the map
     * @param hitPoints remaining hit points
     */
    Unit(Race race, Point location, int hitPoints) {
        super();
        this.race = race;
        this.location = location;
        this.hitPoints = hitPoints;
    }
    
    Point getLocation() {
        return location;
    }
    
    Race getRace() {
        return race;
    }
    
    int getHitPoints() {
        return hitPoints;
    }

    @Override
    public String toString() {
        return race + "(" + hitPoints + ")";
    }
}
