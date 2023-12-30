package nl.mvdr.adventofcode.adventofcode2023.day24;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntSort;

import nl.mvdr.adventofcode.point.Axis3D;

/**
 * A point or vector in three dimensions,
 * where each coordinate is represented by a Z3 integer expression.
 *
 * @author Martijn van de Rijdt
 */
record IntExpr3D(ArithExpr<IntSort> x, ArithExpr<IntSort> y, ArithExpr<IntSort> z) {
    
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
    
    /**
     * Gets the value for the given axis.
     * 
     * @param axis 3D axis
     * @return coordinate value
     */
    ArithExpr<IntSort> get(Axis3D axis) {
        return switch(axis) {
            case X -> x;
            case Y -> y;
            case Z -> z;
            default -> throw new IllegalArgumentException("Unexpected axis: " + axis);
        };
    }
}
