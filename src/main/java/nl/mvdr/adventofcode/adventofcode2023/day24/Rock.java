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
     * rock position + time * rock velocity == hailstone position + time * hailstone velocity
     * </pre>
     * 
     * This means that, at some timestamp, the rock and the hailstone must be at the same position.
     * 
     * @param hailstone the hailstone to compare to
     * @param context context
     * @return equation
     */
    BoolExpr createEquation(Hailstone hailstone, Context context) {
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
     * rock position + time * rock velocity == hailstone position + time * hailstone velocity
     * </pre>
     * 
     * @param hailstone the hailstone to compare to
     * @param context context
     * @param time time expression
     * @param axis axis
     * @return equation
     */
    private BoolExpr createEquation(Hailstone hailstone, Context context, IntExpr time, Axis3D axis) {
        // position + time * velocity
        var lhs = context.mkAdd(location.get(axis), context.mkMul(time, velocity.get(axis)));
        var rhs = context.mkAdd(context.mkInt(hailstone.location().get(axis)), context.mkMul(time, context.mkInt(hailstone.velocity().get(axis))));
        return context.mkEq(lhs, rhs);
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
