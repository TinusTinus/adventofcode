package nl.mvdr.adventofcode.adventofcode2024.day13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import com.microsoft.z3.Context;

import nl.mvdr.adventofcode.point.Point;

record ClawMachine(Map<Button, Point> buttonMovements, Point prize) {

    static Set<ClawMachine> parse(Stream<String> lines) {
        Set<ClawMachine> result = new HashSet<>();
        
        List<String> remainingLines = lines.toList();

        while (remainingLines.contains("")) {
            var index = remainingLines.indexOf("");
            result.add(parseMachine(remainingLines.subList(0, index)));
            remainingLines = remainingLines.subList(index +  1, remainingLines.size());
        }
        result.add(parseMachine(remainingLines));
        
        return result;
    }
    
    private static ClawMachine parseMachine(List<String> lines) {
        Map<Button, Point> buttonMovements = new HashMap<>();;
        Point prize = null;
        
        for (var line : lines) {
            if (line.startsWith("Button ")) {
                var button = Button.valueOf("" + line.charAt(7));
                
                var indexOfComma = line.indexOf(',');
                var x = Integer.parseInt(line.substring(12, indexOfComma));
                var y = Integer.parseInt(line.substring(indexOfComma + 4, line.length()));
                
                buttonMovements.put(button, new Point(x, y));
            } else if (line.startsWith("Prize: X=")) {
                var indexOfComma = line.indexOf(',');
                var x = Integer.parseInt(line.substring(9, indexOfComma));
                var y = Integer.parseInt(line.substring(indexOfComma + 4, line.length()));
                prize = new Point(x, y);
            } else if (!line.isEmpty()) {
                throw new IllegalArgumentException("Unable to parse line: " + line);
            }
        }
        
        return new ClawMachine(buttonMovements, prize);
    }
    
    int calculateTokens() {
        try (var context = new Context()) {
            var solver = context.mkSolver();
            
            var cost = context.mkIntConst("cost");
            var aPresses = context.mkIntConst("a");
            var bPresses = context.mkIntConst("b");

            var ax = context.mkMul(aPresses, context.mkInt(buttonMovements.get(Button.A).x()));
            var bx = context.mkMul(bPresses, context.mkInt(buttonMovements.get(Button.B).x()));
            solver.add(context.mkEq(context.mkInt(prize.x()), context.mkAdd(ax, bx)));
            
            var ay = context.mkMul(aPresses, context.mkInt(buttonMovements.get(Button.A).y()));
            var by = context.mkMul(bPresses, context.mkInt(buttonMovements.get(Button.B).y()));
            solver.add(context.mkEq(context.mkInt(prize.y()), context.mkAdd(ay, by)));
            
            var aPressCost = context.mkMul(aPresses, context.mkInt(Button.A.getCost()));
            var bPressCost = context.mkMul(bPresses, context.mkInt(Button.B.getCost()));
            solver.add(context.mkEq(cost, context.mkAdd(aPressCost, bPressCost)));
            
            solver.add(context.mkLt(aPresses, context.mkInt(100)));
            solver.add(context.mkLt(bPresses, context.mkInt(100)));
            
            return switch(solver.check()) {
                case SATISFIABLE -> Integer.parseInt(solver.getModel().eval(cost, false).toString());
                case UNSATISFIABLE -> 0;
                case UNKNOWN -> throw new IllegalStateException("Unable to solve for claw machine " + this);
            };
        }
    }
}
