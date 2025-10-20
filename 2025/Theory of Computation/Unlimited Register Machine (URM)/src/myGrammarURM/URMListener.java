// Generated from /Users/hayden/Developer/IdeaProjects/URMProject/src/URM.g4 by ANTLR 4.13.2
package myGrammarURM;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link URMParser}.
 */
public interface URMListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link URMParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(URMParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link URMParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(URMParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link URMParser#config}.
	 * @param ctx the parse tree
	 */
	void enterConfig(URMParser.ConfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link URMParser#config}.
	 * @param ctx the parse tree
	 */
	void exitConfig(URMParser.ConfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link URMParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(URMParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link URMParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(URMParser.InContext ctx);
	/**
	 * Enter a parse tree produced by {@link URMParser#jump}.
	 * @param ctx the parse tree
	 */
	void enterJump(URMParser.JumpContext ctx);
	/**
	 * Exit a parse tree produced by {@link URMParser#jump}.
	 * @param ctx the parse tree
	 */
	void exitJump(URMParser.JumpContext ctx);
	/**
	 * Enter a parse tree produced by {@link URMParser#succ}.
	 * @param ctx the parse tree
	 */
	void enterSucc(URMParser.SuccContext ctx);
	/**
	 * Exit a parse tree produced by {@link URMParser#succ}.
	 * @param ctx the parse tree
	 */
	void exitSucc(URMParser.SuccContext ctx);
	/**
	 * Enter a parse tree produced by {@link URMParser#zero}.
	 * @param ctx the parse tree
	 */
	void enterZero(URMParser.ZeroContext ctx);
	/**
	 * Exit a parse tree produced by {@link URMParser#zero}.
	 * @param ctx the parse tree
	 */
	void exitZero(URMParser.ZeroContext ctx);
	/**
	 * Enter a parse tree produced by {@link URMParser#transfer}.
	 * @param ctx the parse tree
	 */
	void enterTransfer(URMParser.TransferContext ctx);
	/**
	 * Exit a parse tree produced by {@link URMParser#transfer}.
	 * @param ctx the parse tree
	 */
	void exitTransfer(URMParser.TransferContext ctx);
}