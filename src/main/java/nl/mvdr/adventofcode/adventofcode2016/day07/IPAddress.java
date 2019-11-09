package nl.mvdr.adventofcode.adventofcode2016.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Representation of an Internet Protocol address in Internet Protocol Version 7.
 *
 * @author Martijn van de Rijdt
 */
class IPAddress {
    
    private final List<String> supernetSequences;
    private final List<String> hypernetSequences;

    /**
     * Parses a text file containing IPv7 IP addresses.
     * 
     * @param inputFilePath path to the text file
     * @return collection of addresses
     * @throws IOException in case the file could not be read
     */
    static Set<IPAddress> parse(Path inputFilePath) throws IOException {
        return Files.lines(inputFilePath)
                .filter(Predicate.not(String::isEmpty))
                .map(IPAddress::parseIPAddress)
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a string representation of an IPv7 IP address.
     * 
     * @param address string representation of an address, for example: abba[mnop]qrst
     * @return IP address
     */
    static IPAddress parseIPAddress(String address) {
        List<String> nonHypernetSequences = new ArrayList<>();
        List<String> hypernetSequences = new ArrayList<>();
        
        String remaining = address;
        int index = remaining.indexOf("[");
        while (index != -1) {
            nonHypernetSequences.add(remaining.substring(0, index));
            remaining = remaining.substring(index + 1);
            
            index = remaining.indexOf("]");
            hypernetSequences.add(remaining.substring(0, index));
            remaining = remaining.substring(index + 1);
            
            index = remaining.indexOf("[");
        }
        
        nonHypernetSequences.add(remaining);
        
        return new IPAddress(List.copyOf(nonHypernetSequences), List.copyOf(hypernetSequences));
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
    private static Set<String> getAbas(String string) {
        return IntStream.range(0, string.length() - 2)
                // a = a
                .filter(i -> string.charAt(i) == string.charAt(i + 2))
                // a != b
                .filter(i -> string.charAt(i) != string.charAt(i + 1))
                .mapToObj(i -> string.substring(i, i + 3))
                .collect(Collectors.toSet());
    }
    
    /**
     * Constructor.
     * 
     * @param nonHypernetSequences non-hypernet sequences
     * @param hypernetSequences hypernet sequences
     */
    private IPAddress(List<String> nonHypernetSequences, List<String> hypernetSequences) {
        super();
        this.supernetSequences = nonHypernetSequences;
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
                .map(IPAddress::getAbas)
                .flatMap(Set::stream)
                .map(aba -> "" + aba.charAt(1) + aba.charAt(0) + aba.charAt(1))
                .anyMatch(bab -> hypernetSequences.stream().anyMatch(hypernetSequence -> hypernetSequence.contains(bab)));
    }
    
    @Override
    public String toString() {
        String result = supernetSequences.get(0);
        for (int i = 0; i != hypernetSequences.size(); i++) {
            result = result + "[";
            result = result + hypernetSequences.get(i);
            result = result + "]";
            result = result + supernetSequences.get(i + 1);
        }
        return result;
    }
}