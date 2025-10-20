/**
 * COMP711 : Software Assignment - Unlimited Register Machine
 * Hayden Richard-Marsters : 21152003
 *
 * Transfer(m,n) : Replaces the content of register n with the content of register m.
 *
 **/

package myGrammarURM;

public final class Transfer extends Instruction {

    private final int m1, n1;

    public Transfer(int m1, int n1) {
        this.m1 = m1;
        this.n1 = n1;
    }

    @Override
    public int execute(URMachine m, int pc0){
        m.set(n1, m.get(m1));
        return pc0 + 1;

    }

    @Override
    public void validate(int N){
        if (m1 < 1 || n1 < 1) throw new IllegalArgumentException("transfer(m, n): m must start at 1");
    }

    @Override
    public String toString(){
        return "transfer(" + m1 + "," + n1 + ")";
    }

}
