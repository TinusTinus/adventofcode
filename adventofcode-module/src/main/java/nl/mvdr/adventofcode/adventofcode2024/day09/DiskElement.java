package nl.mvdr.adventofcode.adventofcode2024.day09;

interface DiskElement {
    int blocks();
    
    default boolean isEmpty() {
        return blocks() == 0;
    }
}
