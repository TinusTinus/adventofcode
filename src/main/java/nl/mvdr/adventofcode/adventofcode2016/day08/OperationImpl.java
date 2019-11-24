package nl.mvdr.adventofcode.adventofcode2016.day08;

/**
 * An operation on a screen.
 *
 * @author Martijn van de Rijdt
 */
enum OperationImpl implements Operation {
    
    RECT(Screen::rect),
    ROTATE_ROW(Screen::rotateRow),
    ROTATE_COLUMN(Screen::rotateColumn);
    
    private final Operation implementation;
    
    OperationImpl(Operation implementation) {
        this.implementation = implementation;
    }
    
    @Override
    public Screen execute(Screen screen, int a, int b) {
        return implementation.execute(screen, a, b);
    }
}
