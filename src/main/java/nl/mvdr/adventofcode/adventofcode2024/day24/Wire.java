package nl.mvdr.adventofcode.adventofcode2024.day24;

record Wire(String name) {

    boolean isZWire() {
        return name.startsWith("z");
    }
    
    int getZindex() {
        if (!isZWire()) {
            throw new UnsupportedOperationException("Not a z wire: " + name);
        }
        
        return Integer.parseInt(name.substring(1), 10);
    }
}
