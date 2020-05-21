package nl.mvdr.adventofcode.adventofcode2019.day23;

import java.util.OptionalLong;

/**
 * Not Always Transmitting Device.
 *
 * @author Martijn van de Rijdt
 */
class Nat {
    /** Address of the NAT. */
    static final long ADDRESS = 255L;
    
    /** X value of the latest packet retained by the NAT. */
    private OptionalLong x;
    
    /** Y value of the latest packet retained by the NAT. */
    private OptionalLong y;
    
    /** Constructor. */
    Nat() {
        super();
        this.x = OptionalLong.empty();
        this.y = OptionalLong.empty();
    }
    
    /**
     * Handles a packet by storing it in memory.
     * 
     * @param xValue the packet's x value
     * @param yValue the packet's y value
     */
    void handlePacket(long xValue, long yValue) {
        this.x = OptionalLong.of(xValue);
        this.y = OptionalLong.of(yValue);
    }
    
    /** @return whether the NAT has handled a packet */
    boolean containsValue() {
        return y.isPresent();
    }
    
    /** @return latest x value */
    long getX() {
        return x.orElseThrow();
    }
    
    /** @return latest y value */
    long getY() {
        return y.orElseThrow();
    }
}
