package nl.mvdr.adventofcode.adventofcode2018.day02;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link InventoryManagementSystemPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class InventoryManagementSystemPart1Test extends SolverTest<InventoryManagementSystemPart1> {
	/** Constructor. */
	public InventoryManagementSystemPart1Test() {
		super(InventoryManagementSystemPart1.class);
	}
	
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("12", "example-day02-2018.txt"),
                Arguments.of("6696", "input-day02-2018.txt"));
    }
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAbcdef2() {
		String id = "abcdef";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 2);
		
		Assertions.assertFalse(result, "contains no letters that appear exactly two or three times");
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAbcdef3() {
		String id = "abcdef";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 3);
		
		Assertions.assertFalse(result, "contains no letters that appear exactly two or three times");
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsBababc2() {
		String id = "bababc";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 2);
		
		Assertions.assertTrue(result, "contains two a and three b");
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsBababc3() {
		String id = "bababc";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 3);
		
		Assertions.assertTrue(result, "contains two a and three b");
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAbbcde2() {
		String id = "abbcde";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 2);
		
		Assertions.assertTrue(result, "contains two b");
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAbbcde3() {
		String id = "abbcde";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 3);
		
		Assertions.assertFalse(result, "no letter appears exactly three times");
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAbcccd2() {
		String id = "abcccd";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 2);
		
		Assertions.assertFalse(result, "no letter appears exactly two times");
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAbcccd3() {
		String id = "abcccd";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 3);
		
		Assertions.assertTrue(result, "contains three c");
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAabcdd2() {
		String id = "aabcdd";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 2);
		
		Assertions.assertTrue(result, "contains two a and two d");
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAabcdd3() {
		String id = "aabcdd";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 3);
		
		Assertions.assertFalse(result);
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAbcdee2() {
		String id = "abcdee";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 2);
		
		Assertions.assertTrue(result, "contains two e");
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAbcdee3() {
		String id = "abcdee";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 3);
		
		Assertions.assertFalse(result);
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAbabab2() {
		String id = "ababab";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 2);
		
		Assertions.assertFalse(result);
	}
	
	/** Test method for {@link InventoryManagementSystemPart1#containsExactlyNOfAnyLetter(String, int)}. */
	@Test
	public void testContainsAbabab3() {
		String id = "ababab";
		
		boolean result = InventoryManagementSystemPart1.containsExactlyNOfAnyLetter(id, 3);
		
		Assertions.assertTrue(result, "contains three a and three b");
    }
}
