package nl.mvdr.adventofcode.adventofcode2016.day03;

/**
 * Representation of a triangle (which may be impossible).
 *
 * @author Martijn van de Rijdt
 */
class Triangle {
    /** Length of the first side. */
    private final int a;
    /** Length of the second side. */
    private final int b;
    /** Length of the third side. */
    private final int c;
    
    static Triangle parse(String string) {
        String[] parts = string.trim().split(" +");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);
        int c = Integer.parseInt(parts[2]);
        return new Triangle(a, b, c);
    }
    
    private Triangle(int a, int b, int c) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    /** @return whether the sum of any two sides is larger than the remaining side */
    boolean isPossible() {
        return a < b + c
                && b < a + c
                && c < a + b;
    }
}
