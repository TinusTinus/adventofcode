package nl.mvdr.adventofcode.adventofcode2020.day04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Passport.
 *
 * @author Martijn van de Rijdt
 */
// Passport properties:
// byr (Birth Year)
// iyr (Issue Year)
// eyr (Expiration Year)
// hgt (Height)
// hcl (Hair Color)
// ecl (Eye Color)
// pid (Passport ID)
// cid (Country ID)
record Passport(Optional<String> birthYear,
        Optional<String> issueYear,
        Optional<String> expirationYear,
        Optional<String> height,
        Optional<String> hairColor,
        Optional<String> eyeColor,
        Optional<String> passportId,
        Optional<String> countryId) {
    
    static List<Passport> parse(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toList());
        
        List<Passport> result = new ArrayList<>();
        
        Map<String, String> passportProperties = new HashMap<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                // End of a set of passport properties
                result.add(new Passport(passportProperties));
                passportProperties.clear();
            } else {
                String[] properties = line.split(" ");
                Stream.of(properties)
                        .map(property -> property.split(":"))
                        .forEach(property -> passportProperties.put(property[0], property[1]));
            }
        }
        if (!passportProperties.isEmpty()) {
            result.add(new Passport(passportProperties));
        }
        
        return result;
    }
    
    /**
     * Constructor, based on the properties in the given map.
     * 
     * @param passportProperties map containing passport properties
     */
    private Passport(Map<String, String> passportProperties) {
        this(Optional.ofNullable(passportProperties.get("byr")),
                Optional.ofNullable(passportProperties.get("iyr")),
                Optional.ofNullable(passportProperties.get("eyr")),
                Optional.ofNullable(passportProperties.get("hgt")),
                Optional.ofNullable(passportProperties.get("hcl")),
                Optional.ofNullable(passportProperties.get("ecl")),
                Optional.ofNullable(passportProperties.get("pid")),
                Optional.ofNullable(passportProperties.get("cid")));
    }
    
    /** @return whether this is a valid passport */
    boolean isValid() {
        return birthYear.isPresent()
                && issueYear.isPresent()
                && expirationYear.isPresent()
                && height.isPresent()
                && hairColor.isPresent()
                && eyeColor.isPresent()
                && passportId.isPresent();
    }

}
