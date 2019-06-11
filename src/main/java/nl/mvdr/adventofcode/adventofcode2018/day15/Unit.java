package nl.mvdr.adventofcode.adventofcode2018.day15;

import java.util.Comparator;

/**
 * A unit in our little strategy game.
 *
 * @author Martijn van de Rijdt
 */
class Unit {
    /** Comparator for the <em>reading order</em>: top-to-bottom, then left-to-right. */
    static final Comparator<Unit> READING_ORDER = Comparator.comparing(Unit::getY).thenComparing(Unit::getX);
    
    /** Maximum hit points. */
    private static final int MAX_HIT_POINTS = 200;
    
    /** Attack power. */
    static final int ATTACK_POWER = 3;

    /** This unit's race. */
    private final Race race;
    
    /** Horizontal coordinate. */
    private final int x;
    
    /** Vertical coordinate. */
    private final int y;
    
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
        super();
        this.race = race;
        this.x = x;
        this.y = y;
        this.hitPoints = hitPoints;
    }
    
    Race getRace() {
        return race;
    }
    
    int getX() {
        return x;
    }
    
    int getY() {
        return y;
    }
    
    int getHitPoints() {
        return hitPoints;
    }

    @Override
    public String toString() {
        return race + "(" + hitPoints + ")";
    }
}
