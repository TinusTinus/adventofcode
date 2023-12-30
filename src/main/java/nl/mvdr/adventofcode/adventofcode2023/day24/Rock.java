package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.Stream;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;

import nl.mvdr.adventofcode.point.Axis3D;

/**
 * Representation of the rock.
 *
 * @author Martijn van de Rijdt
 */
record Rock(IntExpr3D position, IntExpr3D velocity) {
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
     * @param hailstone the hailstone to compare to
     * @param context context
     * @param timeExprName name for the time expression; must be unique per hailstone 
     * @return equation
     */
    BoolExpr createEquation(Hailstone hailstone, Context context, String timeExprName) {
        var time = context.mkIntConst(timeExprName);
        return Stream.of(Axis3D.values())
            .map(axis -> createEquation(hailstone, context, time, axis))
            .reduce(context.mkTrue(), context::mkAnd);
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
        var lhs = context.mkAdd(position.get(axis), context.mkMul(time, velocity.get(axis)));
        var rhs = context.mkAdd(context.mkInt(hailstone.location().get(axis)), context.mkMul(time, context.mkInt(hailstone.velocity().get(axis))));
        return context.mkEq(lhs, rhs);
    }
}
