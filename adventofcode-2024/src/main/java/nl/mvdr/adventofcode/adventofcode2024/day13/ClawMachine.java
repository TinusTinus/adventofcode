package nl.mvdr.adventofcode.adventofcode2024.day13;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import com.microsoft.z3.Context;

record ClawMachine(long ax, long ay, long bx, long by, long prizeX, long prizeY) {

    private static final long OFFSET = 10000000000000L;

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
        if (lines.size() != 3) {
            throw new IllegalArgumentException();
        }
        return parseMachine(lines.get(0), lines.get(1), lines.get(2));
    }
    
    private static ClawMachine parseMachine(String aLine, String bLine, String prizeLine) {
        var indexOfComma = aLine.indexOf(',');
        var ax = Long.parseLong(aLine.substring(12, indexOfComma));
        var ay = Long.parseLong(aLine.substring(indexOfComma + 4, aLine.length()));
        
        indexOfComma = bLine.indexOf(',');
        var bx = Long.parseLong(bLine.substring(12, indexOfComma));
        var by = Long.parseLong(bLine.substring(indexOfComma + 4, bLine.length()));
        
        indexOfComma = prizeLine.indexOf(',');
        var prizeX = Long.parseLong(prizeLine.substring(9, indexOfComma));
        var prizeY = Long.parseLong(prizeLine.substring(indexOfComma + 4, prizeLine.length()));
        
        return new ClawMachine(ax, ay, bx, by, prizeX, prizeY);
    }
    
    @SuppressWarnings("unchecked")
    long calculateTokens() {
        try (var context = new Context()) {
            var solver = context.mkSolver();
            
            var cost = context.mkIntConst("cost");
            var aPresses = context.mkIntConst("a");
            var bPresses = context.mkIntConst("b");

            var axTotal = context.mkMul(aPresses, context.mkInt(ax));
            var bxTotal = context.mkMul(bPresses, context.mkInt(bx));
            solver.add(context.mkEq(context.mkInt(prizeX), context.mkAdd(axTotal, bxTotal)));
            
            var ayTotal = context.mkMul(aPresses, context.mkInt(ay));
            var byTotal = context.mkMul(bPresses, context.mkInt(by));
            solver.add(context.mkEq(context.mkInt(prizeY), context.mkAdd(ayTotal, byTotal)));
            
            var aPressCost = context.mkMul(aPresses, context.mkInt(3));
            var bPressCost = context.mkMul(bPresses, context.mkInt(1));
            solver.add(context.mkEq(cost, context.mkAdd(aPressCost, bPressCost)));
            
            return switch(solver.check()) {
                case SATISFIABLE -> Long.parseLong(solver.getModel().eval(cost, false).toString());
                case UNSATISFIABLE -> 0L;
                case UNKNOWN -> throw new IllegalStateException("Unable to solve for claw machine " + this);
            };
        }
    }
    
    ClawMachine movePrize() {
        return new ClawMachine(ax, ay, bx, by, prizeX + OFFSET, prizeY + OFFSET);
    }
}
