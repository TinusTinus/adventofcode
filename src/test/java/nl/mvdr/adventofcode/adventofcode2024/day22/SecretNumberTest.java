package nl.mvdr.adventofcode.adventofcode2024.day22;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SecretNumberTest {
    
    @ParameterizedTest
    @CsvSource( { "123,15887950",
        "15887950,16495136",
        "16495136,527345",
        "527345,704524",
        "704524,1553684",
        "1553684,12683156",
        "12683156,11100544",
        "11100544,12249484",
        "12249484,7753432",
        "7753432,5908254"
    } )
    void testNextNumber(long input, long expected) {
        var result = SecretNumber.nextSecretNumber(input);
        
        Assertions.assertEquals(expected, result);
    }
    
    @ParameterizedTest
    @CsvSource( { "1,8685429",
            "10,4700978",
            "100,15273692",
            "2024,8667524"
    } )
    void testGetSecretNumber(long input, long expected) {
        var result = SecretNumber.getSecretNumber(input, 2_000);
        
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    void testMix() {
        var value = 15;
        var secretNumber = 42;
        
        var mixed = SecretNumber.mix(secretNumber, value);
        
        Assertions.assertEquals(37, mixed);
    }
    
    @Test
    void testPrune() {
        var secretNumber = 100000000;
        
        var mixed = SecretNumber.prune(secretNumber);
        
        Assertions.assertEquals(16113920, mixed);
    }
    
    @ParameterizedTest
    @CsvSource({
        "123, 3",
        "15887950, 0",
        "16495136, 6"
    })
    void testPrice(long number, long expectedPrice) {
        var secretNumber = new SecretNumber(number);
        
        var price = secretNumber.price();
        
        Assertions.assertEquals(expectedPrice, price);
    }
}
