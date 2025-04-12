package nl.mvdr.adventofcode.adventofcode2016.day14;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

public class Part1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Part1.class);

    private final MessageDigest md5Algorithm;
    
    public Part1() {
        try {
            this.md5Algorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var salt = lines.findFirst().orElseThrow();
        
        System.out.println(md5(salt + 13));
        
        return 0; // TODO
    }
    
    private String md5(String input) {
        var hashBytes = md5Algorithm.digest(input.getBytes());
        return DatatypeConverter.printHexBinary(hashBytes);
    }


    public static void main(String[] args) {
        Part1 instance = new Part1();

        String result = instance.solve("input-day14.txt");

        LOGGER.info(result);
    }
}
