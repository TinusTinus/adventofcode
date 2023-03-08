package nl.mvdr.adventofcode.adventofcode2022.day07;

/**
 * Representation of a file on the file system.
 *
 * @author Martijn van de Rijdt
 */
record File(String name, int size) {

    /**
     * Parses a line from the puzzle input as a file.
     * 
     * @param input line from the puzzle input, for example: "8504156 c.dat"
     * @return file
     */
    static File parse(String input) {
        var parts = input.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse file listing: " + input);
        }
        
        int size = Integer.parseInt(parts[0]);
        String name = parts[1];
        
        return new File(name, size);
    }
}
