package nl.mvdr.adventofcode.adventofcode2024.day05;

record Rule(int lhs, int rhs) {

	static Rule parse(String line) {
		var parts = line.split("|");
		if (parts.length != 2) {
			throw new IllegalArgumentException("Unable to parse as rule: " + line);
		}
		int lhs = Integer.parseInt(parts[0]);
		int rhs = Integer.parseInt(parts[1]);
		return new Rule(lhs, rhs);
	}
	
	@Override
	public final String toString() {
		return lhs + "|" + rhs;
	}
}
