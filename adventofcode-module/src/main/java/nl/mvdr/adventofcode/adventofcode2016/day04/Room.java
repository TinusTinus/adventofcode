package nl.mvdr.adventofcode.adventofcode2016.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Reprentation of a room.
 *
 * @author Martijn van de Rijdt
 */
class Room {
    
    private final String encryptedName;
    private final int sectorId;
    private final String checksum;

    /**
     * Parses a text file containing representations of room.
     * 
     * @param inputFilePath location of the text file
     * @return rooms
     * @throws IOException in case the file could not be read
     */
    static List<Room> parse(Path inputFilePath) throws IOException {
        return Files.lines(inputFilePath)
                .filter(Predicate.not(String::isEmpty))
                .map(Room::parseRoom)
                .collect(Collectors.toList());
    }
    
    /**
     * Parses a text representation of a room.
     * 
     * @param text text representation of a room, for example: aaaaa-bbb-z-y-x-123[abxyz]
     * @return room
     */
    static Room parseRoom(String text) {
        int lastDashIndex = text.lastIndexOf('-');
        int openingBracketIndex = text.indexOf('[');
        
        String encryptedName = text.substring(0, lastDashIndex);
        int sectorId = Integer.parseInt(text.substring(lastDashIndex + 1, openingBracketIndex));
        String checksum = text.substring(openingBracketIndex + 1, text.length() - 1);
        
        return new Room(encryptedName, sectorId, checksum);
    }
    
    /**
     * Constructor.
     * 
     * @param encryptedName encrypted name: lowercase letters separated by dashes
     * @param sectorId sector id
     * @param checksum checksum: the five most common letters in the encrypted name, in order, in case of a real room
     */
    private Room(String encryptedName, int sectorId, String checksum) {
        super();
        this.encryptedName = encryptedName;
        this.sectorId = sectorId;
        this.checksum = checksum;
    }
    
    /** @return whether this is a real room; if false, this is a decoy */
    boolean isReal() {
        return computeChecksum().equals(checksum);
    }

    /** @return checksum as computed based on this room's encrypted name */
    private String computeChecksum() {
        Map<Character, Long> counters = encryptedName.chars()
                // ignore the dashes
                .filter(c -> c != '-')
                .mapToObj(c -> Character.valueOf((char)c))
                // count occurrences of each letter
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        // comparator which sorts entries by descending number of occurrences, then alphabetical order
        Comparator<Entry<Character, Long>> comparator = 
                Comparator.<Entry<Character, Long>, Long>comparing(Entry::getValue)
                        .reversed()
                        .thenComparing(Entry::getKey);
        
        return counters.entrySet().stream()
                .sorted(comparator)
                .map(Entry::getKey)
                .map(Object::toString)
                .limit(5L)
                .collect(Collectors.joining());
    }
    
    int getSectorId() {
        return sectorId;
    }
    
    /** @return decrypted name */
    String decryptName() {
        return encryptedName.chars()
                .map(this::decrypt)
                .mapToObj(c -> "" + (char)c)
                .collect(Collectors.joining());
    }
    
    private int decrypt(int c) {
        int result;
        if (c == '-') {
            result = ' ';
        } else {
            result = (c - 'a' + sectorId) % 26 + 'a';
        }
        return result;
    }
    
    @Override
    public String toString() {
        return encryptedName + "-" + sectorId + "[" + checksum + "]";
    }
}
