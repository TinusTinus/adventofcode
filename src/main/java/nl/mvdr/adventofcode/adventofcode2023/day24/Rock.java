package nl.mvdr.adventofcode.adventofcode2023.day24;

import com.microsoft.z3.Context;

/**
 * TODO javadoc
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
}
