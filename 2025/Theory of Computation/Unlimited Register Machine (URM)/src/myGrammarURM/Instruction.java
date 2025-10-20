/**
 * COMP711 : Software Assignment - Unlimited Register Machine
 * Hayden Richard-Marsters : 21152003
 **/

package myGrammarURM;

public abstract class Instruction {

    public abstract int execute(URMachine m, int pc0);

    public void validate(int programSize) {}
}
