package nl.mvdr.adventofcode.adventofcode2024.day21;

class DirectionalKeypadButtonPress {
    
    private final DirectionalKeypadButton button;
    
    DirectionalKeypadButtonPress(DirectionalKeypadButton button) {
        this.button = button;
    }
    
    DirectionalKeypadButton getButton() {
        return button;
    }
    
    // Equals and hashcode deliberately not overridden.
    // This class is intended to be used as edges in a graph,
    // so each distinct button press of the same button must be unique.
    
    @Override
    public String toString() {
        return button.toString();
    }
}
