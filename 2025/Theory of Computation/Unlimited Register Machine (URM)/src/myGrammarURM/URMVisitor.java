// Generated from /Users/hayden/Developer/IdeaProjects/URMProject/src/URM.g4 by ANTLR 4.13.2
package myGrammarURM;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link URMParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface URMVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link URMParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(URMParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link URMParser#config}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConfig(URMParser.ConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link URMParser#in}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn(URMParser.InContext ctx);
	/**
	 * Visit a parse tree produced by {@link URMParser#jump}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJump(URMParser.JumpContext ctx);
	/**
	 * Visit a parse tree produced by {@link URMParser#succ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSucc(URMParser.SuccContext ctx);
	/**
	 * Visit a parse tree produced by {@link URMParser#zero}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZero(URMParser.ZeroContext ctx);
	/**
	 * Visit a parse tree produced by {@link URMParser#transfer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransfer(URMParser.TransferContext ctx);
}