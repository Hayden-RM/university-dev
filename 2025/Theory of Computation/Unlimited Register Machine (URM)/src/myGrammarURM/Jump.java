/**
 * COMP711 : Software Assignment - Unlimited Register Machine
 * Hayden Richard-Marsters : 21152003
 *
 *  Jump(m,n,q) : Compares the contents of register m and n. If they are equal,
 *  then the next instruction to execute is q. Otherwise, the next
 *  instruction is executed.
 *
 **/

package myGrammarURM;

public final class Jump extends Instruction{

    private final int m1, n1, q1;

    // Jump constructor
    public Jump(int m1, int n1, int q1) {
        this.m1 = m1;
        this.n1 = n1;
        this.q1 = q1;
    }

    @Override
    public int execute(URMachine m, int pc0) {
        return (m.get(m1) == m.get(n1)) ? (q1 -1) : (pc0 + 1);
    }

    @Override
    public void validate(int N) {
        if (m1 < 1 || n1 < 1)
            throw new IllegalArgumentException("jump(m,n,q): register indices start at 1");
        if (q1 < 1 || q1 > N)
            throw new IllegalArgumentException("jump target q must be 1..." + N + " (got " + q1 + ")");
    }

    @Override
    public String toString(){
        return "jump(" + m1 + "," + n1 + "," + q1 + ")";
    }


}
