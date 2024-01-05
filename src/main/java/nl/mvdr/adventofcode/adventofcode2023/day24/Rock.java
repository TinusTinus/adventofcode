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
     * @param context context
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
        var lhs = makeExpression(context, location.get(axis), time, velocity.get(axis));
        var rhs = makeExpression(context, context.mkInt(hailstone.location().get(axis)), time, context.mkInt(hailstone.velocity().get(axis)));
        return context.mkEq(lhs, rhs);
    }
    
    /**
     * Creates an expression of the following form:
     * 
     * <pre>
     * location + time * velocity
     * </pre>
     * 
     * @param context Z3 context
     * @param location a single coordinate of an object's location
     * @param time the timestamp
     * @param velocity a single coordinate of an object's velocity (same coordinate as the one given for {@code location})
     * @return <pre>location + time * velocity</pre>
     */
    private static ArithExpr<IntSort> makeExpression(Context context, IntExpr location, IntExpr time, IntExpr velocity) {
        return context.mkAdd(location, context.mkMul(time, velocity));
    }
    
    /**
     * Returns an expression for the sum of the rock position's coordinate values.
     * 
     * @param context context
     * @return sum of the position's coordinate values
     */
    ArithExpr<IntSort> sumLocationCoordinates(Context context) {
        return location.addCoordinates(context);
    }
}
