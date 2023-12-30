package nl.mvdr.adventofcode.adventofcode2023.day24;

import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;

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
    
    IntExpr3D createLhs(Context context, IntExpr time) {
        var lhsX = context.mkAdd(position.x(), context.mkMul(time, velocity.x()));
        var lhsY = context.mkAdd(position.y(), context.mkMul(time, velocity.y()));
        var lhsZ = context.mkAdd(position.z(), context.mkMul(time, velocity.z()));
        return new IntExpr3D(lhsX, lhsY, lhsZ);
    }
}
