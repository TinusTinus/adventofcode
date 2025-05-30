package nl.mvdr.adventofcode.adventofcode2016.day18;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.solver.LongSolver;

public class LikeARogueSolver implements LongSolver {
    
    private final int rows;
    
    LikeARogueSolver(int rows) {
        this.rows = rows;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        Map<Point, Tile> topRow = Point.parse2DMap(lines.toList(), Tile::parse);
        
        Map<Point, Tile> map = new HashMap<>(topRow);
        
        int maxX = Point.maxX(map.keySet());
        
        IntStream.range(1, rows)
                .mapToObj(Integer::valueOf)
                .flatMap(y -> IntStream.range(0, maxX + 1).mapToObj(x -> new Point(x, y)))
                .forEach(point -> {
                    var leftIsTrap = map.get(new Point(point.x() - 1, point.y() - 1)) == Tile.TRAP;
                    var rightIsTrap = map.get(new Point(point.x() + 1, point.y() - 1)) == Tile.TRAP;
                    
                    Tile tile;
                    if (leftIsTrap != rightIsTrap) {
                        tile = Tile.TRAP;
                    } else {
                        tile = Tile.SAFE;
                    }
                    
                    map.put(point, tile);
                });
        
        return map.values()
                .stream()
                .filter(value -> value == Tile.SAFE)
                .count();
    }
}
