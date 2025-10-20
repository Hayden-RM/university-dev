/**
 * COMP711 : Software Assignment - Unlimited Register Machine
 * Hayden Richard-Marsters : 21152003
 *
 * URMBuilder:
 * - Extends the generated BaseVisitor to construct an URMachine
 * - Applies optional config[..] (values >= 0)
 * - Builds Instruction objects
 * - Validates semantics that depend on N
 **/

package myGrammarURM;

import java.util.*;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public final class URMBuilder extends URMBaseVisitor<Void> {

    private final List<Long> configValues = new ArrayList<>();
    private final List<Instruction> program = new ArrayList<>();

    // Build machine from parsed program
    public URMachine build(URMParser.ProgramContext ctx) {
        configValues.clear();
        program.clear();
        visit(ctx);

        // Validate semantics that need to know N (e.g., jump q in 1...N)
        int N = program.size();
        for (Instruction ins : program) ins.validate(N);

        // Initialise machine (default registers are zero unless config provided)
        URMachine m = new URMachine();
        for (int i = 0; i < configValues.size(); i++) m.set(i + 1, configValues.get(i));

        // Load program
        for (Instruction ins : program) m.add(ins);
        return m;
    }

    // Visitor overrides
    @Override
    public Void visitProgram(URMParser.ProgramContext ctx) {
        if (ctx.config() != null) visit(ctx.config());
        for (URMParser.InContext s : ctx.in()) visit(s);
        return null;
    }

    @Override
    public Void visitConfig(URMParser.ConfigContext ctx) {
        for (TerminalNode t : ctx.INT()) configValues.add(parseNonNegative(t.getSymbol(), "config value"));
        return null;
    }

    @Override
    public Void visitSucc(URMParser.SuccContext ctx) {
        int n = oneBased(ctx.INT().getSymbol(), "succ register index");
        program.add(new Succ(n));
        return null;
    }


    @Override
    public Void visitZero(URMParser.ZeroContext ctx) {
        int n = oneBased(ctx.INT().getSymbol(), "zero register index");
        program.add(new Zero(n));
        return null;
    }

    @Override
    public Void visitTransfer(URMParser.TransferContext ctx) {
        int m = oneBased(ctx.INT(0).getSymbol(), "transfer source register index");
        int n = oneBased(ctx.INT(1).getSymbol(), "transfer target register index");
        program.add(new Transfer(m, n));
        return null;
    }

    @Override
    public Void visitJump(URMParser.JumpContext ctx) {
        int m = oneBased(ctx.INT(0).getSymbol(), "jump m register index");
        int n = oneBased(ctx.INT(1).getSymbol(), "jump n register index");
        int q = oneBased(ctx.INT(2).getSymbol(), "jump target q");
        program.add(new Jump(m, n, q));
        return null;
    }

    // Helpers
    // Parse value >= 0 (used for configs)
    private static long parseNonNegative(Token token, String name) {
        long v = Long.parseLong(token.getText());
        if (v < 0) throw new IllegalArgumentException(name + " must be >= 0 (got " + v + ") at line " + token.getLine());
        return v;
    }

    // Parse index >= 1 (used for register indices)
    private static int oneBased(Token token, String name) {
        int v = Integer.parseInt(token.getText());
        if (v < 1) throw new IllegalArgumentException(name + " must start with 1 (got " + v + ") at line " + token.getLine());
        return v;
    }

}
