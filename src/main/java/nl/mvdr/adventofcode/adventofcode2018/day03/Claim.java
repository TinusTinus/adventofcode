package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of a claim.
 *
 * @author Martijn van de Rijdt
 */
class Claim {
    private static final Pattern PATTERN = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
    
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
        Matcher matcher = PATTERN.matcher(claimString);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid claim: " + claimString);
        }
        
        int id = Integer.parseInt(matcher.group(1));
        int x = Integer.parseInt(matcher.group(2));
        int y = Integer.parseInt(matcher.group(3));
        int width = Integer.parseInt(matcher.group(4));
        int height = Integer.parseInt(matcher.group(5));
        
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
    
    /** @return the claimed fabric */
    Set<SquareInch> getFabric() {
        Set<SquareInch> result = new HashSet<>();
        
        for (int i = x; i != x + width; i++) {
            for (int j = y; j != y + height; j++) {
                result.add(new SquareInch(i, j));
            }
        }
        
        return result;
    }
    
    int getId() {
        return id;
    }
}
