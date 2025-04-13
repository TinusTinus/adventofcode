package nl.mvdr.adventofcode.adventofcode2016.day15;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;

record Disc(int number, int positions, int startingPosition) {

    static Disc parse(String stringRepresentation) {
        var parts = stringRepresentation.split(" has ");
        if (parts.length != 2 || !parts[0].startsWith("Disc #")) {
            throw new IllegalArgumentException("Unable to parse: " + stringRepresentation);
        }
        
        var discNumber = Integer.parseInt(parts[0].substring(6));
        
        parts = parts[1].split(" positions; at time=0, it is at position ");
        if (parts.length != 2 || !parts[1].endsWith(".")) {
            throw new IllegalArgumentException("Unable to parse: " + stringRepresentation);
        }
        
        var positions = Integer.parseInt(parts[0]);
        var startingPosition = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
        
        return new Disc(discNumber, positions, startingPosition);
    }

    /// Creates the following equation:
    /// (startingPosition + number + time) % position  =  0
    BoolExpr createEquation(Context context, IntExpr time) {
        return context.mkEq(
                context.mkMod(
                        context.mkAdd(context.mkInt(startingPosition), context.mkInt(number), time),
                        context.mkInt(positions)),
                context.mkInt(0));
    }
}
