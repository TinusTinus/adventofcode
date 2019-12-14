package nl.mvdr.adventofcode.adventofcode2019.day14;

/**
 * A component to a chemical reaction.
 *
 * @author Martijn van de Rijdt
 */
class Component {
    /** Name of the chemical. */
    private final String chemical;
    /** Quantity of the chemical. Must be a positive integer. */
    private final int quantity;
    
    /**
     * Parses the text representation of a component to a chemical reaction.
     * 
     * @param text textual representation of a component, for example: "1 FUEL"
     * @return component
     */
    static Component parse(String text) {
        String[] parts = text.split(" ");
        
        int quantity = Integer.parseInt(parts[0]);
        String chemical = parts[1];
        
        return new Component(chemical, quantity);
    }
    
    /**
     * Constructor.
     * 
     * @param chemical name of the chemical
     * @param quantity quantity of the chemical
     */
    private Component(String chemical, int quantity) {
        super();
        this.chemical = chemical;
        this.quantity = quantity;
    }
    
    String getChemical() {
        return chemical;
    }
    
    int getQuantity() {
        return quantity;
    }
    
    @Override
    public String toString() {
        return quantity + " " + chemical;
    }
}
