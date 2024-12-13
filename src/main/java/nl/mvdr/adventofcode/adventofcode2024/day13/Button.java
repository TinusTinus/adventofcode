package nl.mvdr.adventofcode.adventofcode2024.day13;

enum Button {
    A(3),
    B(1);
    
    private final int cost;
    
    private Button(int cost) {
        this.cost = cost;
    }
    
    int getCost() {
        return cost;
    }
}
