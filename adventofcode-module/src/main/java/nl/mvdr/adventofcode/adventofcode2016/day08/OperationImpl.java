package nl.mvdr.adventofcode.adventofcode2016.day08;

/**
 * An operation on a screen.
 *
 * @author Martijn van de Rijdt
 */
enum OperationImpl implements Operation {
    
    RECT(Screen::rect, "rect (\\d*)x(\\d*)"),
    ROTATE_ROW(Screen::rotateRow, "rotate row y=(\\d*) by (\\d*)"),
    ROTATE_COLUMN(Screen::rotateColumn, "rotate column x=(\\d*) by (\\d*)");
    
    private final Operation implementation;
    
    private final String regex;
    
    OperationImpl(Operation implementation, String regex) {
        this.implementation = implementation;
        this.regex = regex;
    }
    
    @Override
    public Screen execute(Screen screen, int a, int b) {
        return implementation.execute(screen, a, b);
    }
    
    String getRegex() {
        return regex;
    }
}
