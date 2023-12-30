package nl.mvdr.adventofcode.adventofcode2023.day24;

import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;

/**
 * A point or vector in three dimensions,
 * where each coordinate is represented by a Z3 {@link IntExpr}.
 *
 * @author Martijn van de Rijdt
 */
record IntExpr3D(IntExpr x, IntExpr y, IntExpr z) {
    
    /**
     * Creates a constant.
     * 
     * That is, an {@link IntExpr3D} where each value is an integer constant.
     * 
     * @param context context
     * @param name name prefix
     * @return constant expressions
     */
    static IntExpr3D mkConst(Context context, String name) {
        var x = context.mkIntConst(name + "X");
        var y = context.mkIntConst(name + "Y");
        var z = context.mkIntConst(name + "Z");
        return new IntExpr3D(x, y, z);
    }
}
