/**
 * COMP711 : Software Assignment - Unlimited Register Machine
 * Hayden Richard-Marsters : 21152003
 *
 * URMachine:
 * - Stores register values (1-based externally, 0-based internally)
 * - Stores a list of instruction objects (program)
 * - Executes one step at a time or runs to completion with optional trace/step cap
 * - toString() reports the current registers and the next instruction (or HALT)
 **/

package myGrammarURM;

import java.util.*;

public final class URMachine {

    // Registers (grow-on-demand); program; and current program counter
    private final ArrayList<Long> regs = new ArrayList<>();
    private final ArrayList<Instruction> prog = new ArrayList<>();
    private int pc = 0;

    // Constructors
    // Default constructor
    public URMachine() {}

    // Initialise R1..Rn from given list of values, others remain implicitly 0
    public URMachine(List<Long> init) {
        if (init != null){
            for (int i = 0; i < init.size(); i++) set(i + 1, init.get(i));
        }
    }

    // Variable-length arguments
    public URMachine(Long... init){
        if (init != null){
            for (int i = 0; i < init.length; i++) set(i + 1, init[i]);
        }
    }

    // Registers - 1-based safe access
    public long get(int idx1) { ensure(idx1); return regs.get(idx1 - 1); }
    public void set(int idx1, long val) { ensure(idx1); regs.set(idx1 - 1, val); }
    private void ensure(int idx1) {while (regs.size() < idx1) regs.add(0L); }

    // Program loading
    public void add(Instruction inst){ prog.add(inst); }
    public int size(){ return prog.size(); }

    // Execute a single instruction at the current PC and advance PC
    public void execute(Instruction inst){
        pc = inst.execute(this, pc);
    }

    // Run a program to completion on the URM
    // halts when PC == size(), Trace prints PC (1-based), current registers, and next instruction
    public void run(){ run(Long.MAX_VALUE, true); }
    public void run(long maxSteps, boolean trace) {

        long steps = 0;

        while (pc >= 0 && pc < prog.size()) {

            try {

                // *** Added a delay to step through at human speeds ***
                Thread.sleep(250);
                if (trace) System.out.printf("PC=%d regs=%s next=%s%n",
                        pc + 1, regs, formatNext());

                // If step limit is given
                if (++steps > maxSteps)
                    throw new IllegalStateException("Step limit exceeded (likely non-terminating)");
                pc = prog.get(pc).execute(this, pc);

            }catch(Exception e){

                Thread.currentThread().interrupt();
                System.err.println("Execution was interrupted. ");
                return;
            }
        }
    }

    // Readable 'next instruction' helper
    private String formatNext(){
        if (pc < 0 || pc >= prog.size()) return "HALT";

        return (pc + 1) + ": " + prog.get(pc);
    }

    // Current register configuration and the next instruction to execute
    @Override
    public String toString(){
        return "CURRENT REGISTER CONFIG: regs =" + regs + " next = " + formatNext();
    }

}
