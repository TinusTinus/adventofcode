package nl.mvdr.adventofcode.adventofcode2016.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Representation of an Internet Protocol address in Internet Protocol Version 7.
 *
 * @author Martijn van de Rijdt
 */
class IPAddress {
    
    private final List<String> supernetSequences;
    private final List<String> hypernetSequences;

    /**
     * Parses a string representation of an IPv7 IP address.
     * 
     * @param address string representation of an address, for example: abba[mnop]qrst
     * @return IP address
     */
    static IPAddress parse(String address) {
        List<String> supernetSequences = new ArrayList<>();
        List<String> hypernetSequences = new ArrayList<>();
        
        String remaining = address;
        int index = remaining.indexOf("[");
        while (index != -1) {
            supernetSequences.add(remaining.substring(0, index));
            remaining = remaining.substring(index + 1);
            
            index = remaining.indexOf("]");
            hypernetSequences.add(remaining.substring(0, index));
            remaining = remaining.substring(index + 1);
            
            index = remaining.indexOf("[");
        }
        
        supernetSequences.add(remaining);
        
        return new IPAddress(List.copyOf(supernetSequences), List.copyOf(hypernetSequences));
    }
    
    /**
     * Determines whether the given string contains an Autonomous Bridge Bypass Annotation.
     * 
     * An ABBA is any four-character sequence which consists of a pair of two
     * different characters followed by the reverse of that pair, such as xyyx or
     * abba.
     * 
     * @param string string
     * @return whether the given string contains an ABBA.
     */
    private static boolean containsAbba(String string) {
        return IntStream.range(0, string.length() - 3)
            // a = a
            .filter(i -> string.charAt(i) == string.charAt(i + 3))
            // b = b
            .filter(i -> string.charAt(i + 1) == string.charAt(i + 2))
            // a != b
            .anyMatch(i -> string.charAt(i) != string.charAt(i + 1));
    }
    
    /**
     * Returns all Area-Broadcast Accessors, or ABAs, contained in the given string.
     * 
     * An ABA is any three-character sequence which consists of the same character
     * twice with a different character between them, such as xyx or aba.
     * 
     * @param string string
     * @return ABAs
     */
    private static Stream<String> getAbas(String string) {
        return IntStream.range(0, string.length() - 2)
                // a = a
                .filter(i -> string.charAt(i) == string.charAt(i + 2))
                // a != b
                .filter(i -> string.charAt(i) != string.charAt(i + 1))
                .mapToObj(i -> string.substring(i, i + 3));
    }
    
    /**
     * Constructor.
     * 
     * @param supernetSequences supernet sequences
     * @param hypernetSequences hypernet sequences
     */
    private IPAddress(List<String> supernetSequences, List<String> hypernetSequences) {
        super();
        this.supernetSequences = supernetSequences;
        this.hypernetSequences = hypernetSequences;
    }
    
    /** @return whether this IP address supports TLS (transport-layer snooping) */
    boolean supportsTransportLayerSnooping() {
        return hypernetSequences.stream().noneMatch(IPAddress::containsAbba)
                && supernetSequences.stream().anyMatch(IPAddress::containsAbba);
    }
    
    /** @return whether this IP address supports SSL (super-secret listening) */
    boolean supportsSuperSecretListening() {
        return supernetSequences.stream()
                .flatMap(IPAddress::getAbas)
                .map(aba -> "" + aba.charAt(1) + aba.charAt(0) + aba.charAt(1))
                .anyMatch(bab -> hypernetSequences.stream().anyMatch(hypernetSequence -> hypernetSequence.contains(bab)));
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(supernetSequences.getFirst());
        for (int i = 0; i != hypernetSequences.size(); i++) {
            result.append("[");
            result.append(hypernetSequences.get(i));
            result.append("]");
            result.append(supernetSequences.get(i + 1));
        }
        return result.toString();
    }
}
