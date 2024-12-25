package nl.mvdr.adventofcode.adventofcode2024.day24;

record Wire(String name) {

    boolean isXInputWire() {
        return name.matches("x\\d\\d");
    }
    
    boolean isYInputWire() {
        return name.matches("y\\d\\d");
    }
    
    boolean isZWire() {
        return name.matches("z\\d\\d");
    }
    
    boolean isCWire() {
        return name.matches("c\\d\\d");
    }
    
    int getIndex() {
        return Integer.parseInt(name.substring(name.length() - 2, name.length()), 10);
    }
    
    @Override
    public final String toString() {
        return name;
    }
}
