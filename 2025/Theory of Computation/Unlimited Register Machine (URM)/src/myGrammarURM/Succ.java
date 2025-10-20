/**
 * COMP711 : Software Assignment - Unlimited Register Machine
 * Hayden Richard-Marsters : 21152003
 *
 * Succ(n) : Increments contents of register n by one.
 *
 **/

package myGrammarURM;

public final class Succ extends Instruction {

    private final int n1;

    public Succ(int n1) {
        this.n1 = n1;
    }

    @Override
    public int execute(URMachine m, int pc0) {
        m.set(n1, m.get(n1) + 1);
        return pc0  + 1;
    }

    @Override public void validate(int N){
        if (n1 < 1) throw new IllegalArgumentException("succ(n): n must start at 1");
    }

    @Override
    public String toString(){
        return "succ(" + n1 + ")";
    }
}
