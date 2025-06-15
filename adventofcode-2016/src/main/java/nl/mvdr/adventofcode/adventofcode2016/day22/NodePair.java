package nl.mvdr.adventofcode.adventofcode2016.day22;

record NodePair(Node a, Node b) {
    boolean isViable() {
        return !a.isEmpty()
                && a != b
                && a.used() <= b.avail();
    }
}
