package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of a claim.
 *
 * @author Martijn van de Rijdt
 */
class Claim {
    private final int id;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    
    private Claim(int id, int x, int y, int width, int height) {
        super();
        
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**
     * Parses the string representation of a claim.
     * 
     * @param claimString claim string, for example: "#1 @ 1,3: 4x4"
     * @return claim
     */
    static Claim parse(String claimString) {
        Pattern pattern = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
        Matcher matcher = pattern.matcher(claimString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid claim: " + claimString);
        }
        
        int id = Integer.valueOf(matcher.group(1));
        int x = Integer.valueOf(matcher.group(2));
        int y = Integer.valueOf(matcher.group(3));
        int width = Integer.valueOf(matcher.group(4));
        int height = Integer.valueOf(matcher.group(5));
        
        return new Claim(id, x, y, width, height);
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("#");
        builder.append(id);
        builder.append(" @ ");
        builder.append(x);
        builder.append(",");
        builder.append(y);
        builder.append(": ");
        builder.append(width);
        builder.append("x");
        builder.append(height);
        return builder.toString();
    }
}
