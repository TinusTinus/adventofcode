package nl.mvdr.adventofcode.adventofcode2016.day11;

sealed interface Item permits Elevator, Microchip, RadioisotopeThermoelectricGenerator {

    static final String MICROCHIP_SUFFIX = "-compatible microchip";
    static final String GENERATOR_SUFFIX = " generator";

    static Item parse(String stringRepresentation) {
        Item result;
        
        if (stringRepresentation.endsWith(MICROCHIP_SUFFIX)) {
            var radioisotopeName = stringRepresentation.substring(2, stringRepresentation.length() - MICROCHIP_SUFFIX.length());
            var radioisotope = new Radioisotope(radioisotopeName);
            result = new Microchip(radioisotope);
        } else if (stringRepresentation.endsWith(GENERATOR_SUFFIX)) {
            var radioisotopeName = stringRepresentation.substring(2, stringRepresentation.length() - GENERATOR_SUFFIX.length());
            var radioisotope = new Radioisotope(radioisotopeName);
            result = new RadioisotopeThermoelectricGenerator(radioisotope);
        } else {
            throw new IllegalArgumentException("Unable to parse item: " + stringRepresentation);
        }
        
        return result;
    }
    
}
