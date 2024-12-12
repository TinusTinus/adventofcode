package nl.mvdr.adventofcode.adventofcode2024.day12;

import nl.mvdr.adventofcode.point.Point;

import java.util.Set;

record Region(char plant, Set<Point> plots) {
    
	private int area() {
		return plots.size();
	}
	
	private int perimeter() {
		return 0; // TODO calculate perimeter
	}
	
	int cost() {
		return area() * perimeter();
	}
}
