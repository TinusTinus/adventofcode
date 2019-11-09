package nl.mvdr.adventofcode.adventofcode2016.day07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link IPAddress}.
 *
 * @author Martijn van de Rijdt
 */
public class IPAddressTest {
    
    /** Test case for {@link IPAddress#parseIPAddress(String)} and {@link IPAddress#toString()}. */
    @Test
    public void testParseToString() {
        String input = "abba[mnop]qrst";
        
        IPAddress address = IPAddress.parseIPAddress(input);
        String result = address.toString();
        
        Assertions.assertEquals(input, result);
    }
    
    /** Test case for {@link IPAddress#parseIPAddress(String)} and {@link IPAddress#toString()}. */
    @Test
    public void testParseToStringLongAddress() {
        String input = "zeebynirxqrjbdqzjav[cawghcfvfeefkmx]xqcdkvawumyayfnq[qhhwzlwjvjpvyavtm]sbnvwssglfpyacfbua[wpbknuubmsjjbekkfy]icimffaoqghdpvsbx";
        
        IPAddress address = IPAddress.parseIPAddress(input);
        String result = address.toString();
        
        Assertions.assertEquals(input, result);
    }
}
