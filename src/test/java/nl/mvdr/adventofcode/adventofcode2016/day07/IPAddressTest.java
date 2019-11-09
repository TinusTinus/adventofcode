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
    
    /** Test case for {@link IPAddress#supportsTransportLayerSnooping()}. */
    @Test
    public void testSupportsTLS0() {
        IPAddress address = IPAddress.parseIPAddress("abba[mnop]qrst");
        
        boolean result = address.supportsTransportLayerSnooping();
        
        Assertions.assertTrue(result);
    }
    
    /** Test case for {@link IPAddress#supportsTransportLayerSnooping()}. */
    @Test
    public void testSupportsTLS1() {
        IPAddress address = IPAddress.parseIPAddress("abcd[bddb]xyyx");
        
        boolean result = address.supportsTransportLayerSnooping();
        
        Assertions.assertFalse(result);
    }
    
    /** Test case for {@link IPAddress#supportsTransportLayerSnooping()}. */
    @Test
    public void testSupportsTLS2() {
        IPAddress address = IPAddress.parseIPAddress("aaaa[qwer]tyui");
        
        boolean result = address.supportsTransportLayerSnooping();
        
        Assertions.assertFalse(result);
    }
    
    /** Test case for {@link IPAddress#supportsTransportLayerSnooping()}. */
    @Test
    public void testSupportsTLS3() {
        IPAddress address = IPAddress.parseIPAddress("ioxxoj[asdfgh]zxcvbn");
        
        boolean result = address.supportsTransportLayerSnooping();
        
        Assertions.assertTrue(result);
    }
    
    /** Test case for {@link IPAddress#supportsSuperSecretListening()}. */
    @Test
    public void testSupportsSSL0() {
        IPAddress address = IPAddress.parseIPAddress("aba[bab]xyz");
        
        boolean result = address.supportsSuperSecretListening();
        
        Assertions.assertTrue(result);
    }
    
    /** Test case for {@link IPAddress#supportsSuperSecretListening()}. */
    @Test
    public void testSupportsSSL1() {
        IPAddress address = IPAddress.parseIPAddress("xyx[xyx]xyx");
        
        boolean result = address.supportsSuperSecretListening();
        
        Assertions.assertFalse(result);
    }
    
    /** Test case for {@link IPAddress#supportsSuperSecretListening()}. */
    @Test
    public void testSupportsSSL2() {
        IPAddress address = IPAddress.parseIPAddress("aaa[kek]eke");
        
        boolean result = address.supportsSuperSecretListening();
        
        Assertions.assertTrue(result);
    }
    
    /** Test case for {@link IPAddress#supportsSuperSecretListening()}. */
    @Test
    public void testSupportsSSL3() {
        IPAddress address = IPAddress.parseIPAddress("zazbz[bzb]cdb");
        
        boolean result = address.supportsSuperSecretListening();
        
        Assertions.assertTrue(result);
    }
}
