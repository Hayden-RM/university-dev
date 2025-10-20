/**
 * COMP711 : Software Assignment - Unlimited Register Machine
 * Hayden Richard-Marsters : 21152003
 *
 * Zero(n) : Sets contents of register n to 0
 *
 **/

package myGrammarURM;

public final class Zero extends Instruction {
    private final int n1;

    public Zero(int n1) {
        this.n1 = n1;
    }

    @Override
    public int execute(URMachine m, int pc0){
        m.set(n1, 0);
        return pc0 + 1;
    }

    @Override
    public void validate(int N){
        if (n1 < 1) throw new IllegalArgumentException("zero(n): n must start at 1");
    }

    @Override
    public String toString(){
        return "zero(" + n1 + ")";
    }
}
