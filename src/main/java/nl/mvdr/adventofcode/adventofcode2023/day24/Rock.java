package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.UUID;
import java.util.stream.Stream;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import com.microsoft.z3.IntSort;

import nl.mvdr.adventofcode.point.Axis3D;

/**
 * Representation of the rock.
 *
 * @author Martijn van de Rijdt
 */
record Rock(IntExpr3D location, IntExpr3D velocity) {
    /**
     * Creates a new rock.
     * 
     * @param context Z3 context
     * @return rock
     */
    static Rock create(Context context) {
        var position = IntExpr3D.mkConst(context, "rockPosition");
        var velocity = IntExpr3D.mkConst(context, "rockVelocity");
        return new Rock(position, velocity);
    }
    
    /**
     * Creates an equation of the following form, for each axis:
     * 
     * <pre>
     * rock location + time * rock velocity = hailstone position + time * hailstone velocity
     * </pre>
     * 
     * This means that, at some timestamp, the rock and the hailstone must be at the same position.
     * 
     * @param hailstone the hailstone to compare to
     * @param context Z3 context
     * @return equation
     */
    BoolExpr createEquation(Hailstone hailstone, Context context) {
        // Make an int constant for the timestamp at which this rock collides with the given hailstone.
        // The UUID is used to ensure that the timestamp constant name is unique for each hailstone.
        var time = context.mkIntConst("time" + UUID.randomUUID());
        
        return Stream.of(Axis3D.values())
            .map(axis -> createEquation(hailstone, context, time, axis))
            .reduce(context::mkAnd)
            .orElseThrow();
    }

    /**
     * Creates an equation of the following form, for the given axis:
     * 
     * <pre>
     * rock location + time * rock velocity = hailstone position + time * hailstone velocity
     * </pre>
     * 
     * @param hailstone the hailstone to compare to
     * @param context Z3 context
     * @param time time expression
     * @param axis axis
     * @return equation
     */
    private BoolExpr createEquation(Hailstone hailstone, Context context, IntExpr time, Axis3D axis) {
        var rockLocation = location.get(axis);
        var rockVelocity = velocity.get(axis);
        var lhs = createExpression(context, rockLocation, time, rockVelocity);
        
        var hailstoneLocation = context.mkInt(hailstone.location().get(axis));
        var hailstoneVelocity = context.mkInt(hailstone.velocity().get(axis));
        var rhs = createExpression(context, hailstoneLocation, time, hailstoneVelocity);
        
        return context.mkEq(lhs, rhs);
    }
    
    /**
     * Creates an expression for the location of an object at the given timestamp.
     * 
     * That is, this method returns the following expression:
     * <pre>
     * location + time * velocity
     * </pre>
     * 
     * @param context Z3 context
     * @param location coordinate value of an object's starting location (x, y, or z)
     * @param time the timestamp
     * @param velocity coordinate value (corresponding to {@code location}) of an object's velocity
     * @return coordinate value (corresponding to {@code location} and {@code velocity}) of the object at the given timestamp
     */
    private static ArithExpr<IntSort> createExpression(Context context, IntExpr location, IntExpr time, IntExpr velocity) {
        return context.mkAdd(location, context.mkMul(time, velocity));
    }
    
    /**
     * Returns an expression for the sum of the rock position's coordinate values.
     * 
     * @param context Z3 context
     * @return sum of the position's coordinate values
     */
    ArithExpr<IntSort> sumLocationCoordinates(Context context) {
        return location.addCoordinates(context);
    }
}
