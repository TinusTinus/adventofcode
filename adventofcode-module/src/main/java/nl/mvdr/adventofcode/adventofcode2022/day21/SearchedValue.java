package nl.mvdr.adventofcode.adventofcode2022.day21;

/**
 * The value we're searching for.
 *
 * @author Martijn van de Rijdt
 */
class SearchedValue implements Value {
    /** Singleton instance. */
    static SearchedValue INSTANCE = new SearchedValue();
    
    /** Private constructor to prevent singleton instantiation. */
    private SearchedValue() {
        super();
    }
}
