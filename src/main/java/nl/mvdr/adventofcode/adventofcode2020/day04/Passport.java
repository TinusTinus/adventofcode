package nl.mvdr.adventofcode.adventofcode2020.day04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Passport.
 *
 * @author Martijn van de Rijdt
 */
record Passport(Optional<String> birthYear,
        Optional<String> issueYear,
        Optional<String> expirationYear,
        Optional<String> height,
        Optional<String> hairColor,
        Optional<String> eyeColor,
        Optional<String> passportId,
        Optional<String> countryId) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Passport.class);
    
    /**
     * Parses a textual representation of a list of passports.
     * 
     * @param linesStream puzzle input
     * @return list of passports
     */
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
    
    /** @return whether this passport contains all required fields */
    boolean hasRequiredValues() {
        return birthYear.isPresent()
                && issueYear.isPresent()
                && expirationYear.isPresent()
                && height.isPresent()
                && hairColor.isPresent()
                && eyeColor.isPresent()
                && passportId.isPresent(); // ignore country id
    }
    
    /** @return whether this is a valid passport */
    boolean hasRequiredAndValidValues() {
        return validateBirthYear()
                && validateIssueYear()
                && validateExpirationYear()
                && validateHeight()
                && validateHairColor()
                && validateEyeColor()
                && validatePassportId()
                && validateCountryId();
    }
    
    /** @return whether the birth year is valid: four digits; at least 1920 and at most 2002 */
    private boolean validateBirthYear() {
        boolean result;
        try {
            int year = Integer.parseInt(birthYear.orElseThrow());
            result = 1920 <= year && year <= 2002;
        } catch (NoSuchElementException | NumberFormatException e) {
            LOGGER.debug("Invalid year", e);
            result = false;
        }
        return result;
    }

    /** @return whether the issue year is valid: four digits; at least 2010 and at most 2020 */
    private boolean validateIssueYear() {
        boolean result;
        try {
            int year = Integer.parseInt(issueYear.orElseThrow());
            result = 2010 <= year && year <= 2020;
        } catch (NoSuchElementException | NumberFormatException e) {
            LOGGER.debug("Invalid year", e);
            result = false;
        }
        return result;
    }
    
    /** @return whether the expiration year is valid: four digits; at least 2020 and at most 2030 */
    private boolean validateExpirationYear() {
        boolean result;
        try {
            int year = Integer.parseInt(expirationYear.orElseThrow());
            result = 2020 <= year && year <= 2030;
        } catch (NoSuchElementException | NumberFormatException e) {
            LOGGER.debug("Invalid year", e);
            result = false;
        }
        return result;
    }
    
    /**
     * @return whether the height is valid: a number followed by either cm or in:
     *         <ul>
     *         <li>If cm, the number must be at least 150 and at most 193.</li>
     *         <li>If in, the number must be at least 59 and at most 76.</li>
     *         </ul>
     */
    private boolean validateHeight() {
        boolean result;
        try {
            String heightString = height.orElseThrow();
            int number = Integer.parseInt(heightString.substring(0, heightString.length() - 2));
            String unit = heightString.substring(heightString.length() - 2);
            if ("cm".equals(unit)) {
                result = 150 <= number && number <= 193;
            } else if ("in".equals(unit)) {
                result = 59 <= number && number <= 76;
            } else {
                LOGGER.debug("Unknown unit: {}", unit);
                result = false;
            }
            
        } catch (NoSuchElementException | NumberFormatException | IndexOutOfBoundsException e) {
            LOGGER.debug("Invalid height", e);
            result = false;
        }
        return result;
    }
    
    /** @return whether the hair color is valid: a # followed by exactly six characters 0-9 or a-f */
    private boolean validateHairColor() {
        return hairColor.isPresent()
                && hairColor.orElseThrow().length() == 7
                && hairColor.orElseThrow().charAt(0) == '#'
                && hairColor.orElseThrow().substring(1).chars().allMatch(c -> ('0' <= c && c <= '9') || ('a' <= c && c <= 'f'));
    }
    
    /** @return whether the hair color is valid: exactly one of: amb blu brn gry grn hzl oth */
    private boolean validateEyeColor() {
        return List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
                .stream()
                .map(Optional::of)
                .anyMatch(eyeColor::equals);
    }
    
    /** @return whether the passport id is valid: a nine-digit number, including leading zeroes */
    private boolean validatePassportId() {
        return passportId.isPresent()
                && passportId.orElseThrow().length() == 9
                && passportId.orElseThrow().chars().allMatch(c -> '0' <= c && c <= '9');
    }
    
    /** @return whether the country id is valid: ignored, missing or not */
    private boolean validateCountryId() {
        return true;
    }
}
