package nl.mvdr.adventofcode.adventofcode2024.day12;

import nl.mvdr.adventofcode.point.Point;

import java.util.Set;

record Region(char plant, Set<Point> plots) {
    
	private int area() {
		return plots.size();
	}
	
	private int perimeter() {
		return (int)plots.stream()
				.flatMap(plot -> plot.neighbours().stream().filter(neighbour -> !plots.contains(neighbour)))
				.count(); // TODO clean up
	}
	
	int cost() {
		return area() * perimeter();
	}
}
