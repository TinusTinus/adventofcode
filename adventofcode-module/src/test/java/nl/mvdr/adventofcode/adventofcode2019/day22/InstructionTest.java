package nl.mvdr.adventofcode.adventofcode2019.day22;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for {@link Instruction}.
 *
 * @author Martijn van de Rijdt
 */
@SuppressWarnings("boxing")
public class InstructionTest {
    
    /** Tests performing a "deal into a new stack" instruction. */
    @Test
    public void testDeal() {
        Instruction instruction = Instruction.parse("deal into new stack");
        List<Integer> deck = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toList());
        
        List<Integer> result = instruction.perform(deck);
        
        Assertions.assertEquals(List.of(9, 8, 7, 6, 5, 4, 3, 2, 1, 0), result);
    }
    
    /** Tests performing a "cut" instruction. */
    @Test
    public void testCut() {
        Instruction instruction = Instruction.parse("cut 3");
        List<Integer> deck = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toList());
        
        List<Integer> result = instruction.perform(deck);
        
        Assertions.assertEquals(List.of(3, 4, 5, 6, 7, 8, 9, 0, 1, 2), result);
    }
    
    /** Tests performing a "cut" instruction. */
    @Test
    public void testCutNegative() {
        Instruction instruction = Instruction.parse("cut -4");
        List<Integer> deck = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toList());
        
        List<Integer> result = instruction.perform(deck);
        
        Assertions.assertEquals(List.of(6, 7, 8, 9, 0, 1, 2, 3, 4, 5), result);
    }
    
    /** Tests performing a "deal with increment" instruction. */
    @Test
    public void testDealWithIncrement() {
        Instruction instruction = Instruction.parse("deal with increment 3");
        List<Integer> deck = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toList());
        
        List<Integer> result = instruction.perform(deck);
        
        Assertions.assertEquals(List.of(0, 7, 4, 1, 8, 5, 2, 9, 6, 3), result);
    }
    
    /** Tests {@link Instruction#computePreviousIndex(long, long)} for a "deal into a new stack" instruction. */
    @Test
    public void testComputePreviousInstructionDeal() {
        testComputePreviousInstruction("deal into new stack");
    }
    
    /** Tests {@link Instruction#computePreviousIndex(long, long)} for a "cut" instruction. */
    @Test
    public void testComputePreviousInstructionCut() {
        testComputePreviousInstruction("cut 3");
    }
    
    /** Tests {@link Instruction#computePreviousIndex(long, long)} for a "cut" instruction. */
    @Test
    public void testComputePreviousInstructionCutNegative() {
        testComputePreviousInstruction("cut -4");
    }

    /** Tests {@link Instruction#computePreviousIndex(long, long)} for a "deal into a new stack" instruction. */
    @Test
    public void testComputePreviousInstructionDealWithIncrement() {
        testComputePreviousInstruction("deal with increment 3");
    }

    /**
     * Tests {@link Instruction#computePreviousIndex(long, long)} for the given instruction.
     * 
     * Note that this method assumes that {@link Instruction#perform(List)} works correctly.
     * 
     * @param instructionText instruction to test
     */
    private void testComputePreviousInstruction(String instructionText) {
        Instruction instruction = Instruction.parse(instructionText);
        List<Integer> deck = IntStream.range(0, 10)
                .boxed()
                .collect(Collectors.toList());
        List<Integer> shuffledDeck = instruction.perform(deck);
        for (int i = 0; i != deck.size(); i++) {
            
            long result = instruction.computePreviousIndex(i, deck.size());
            
            Assertions.assertTrue(0L <= result, "Result " + result + " out of bounds for index " + i);
            Assertions.assertTrue(result < deck.size(), "Result " + result + " out of bounds for index " + i);
            Assertions.assertEquals(shuffledDeck.get(i), deck.get(Math.toIntExact(result)), "Incorrect result " + result + " for index " + i);
        }
    }
}
