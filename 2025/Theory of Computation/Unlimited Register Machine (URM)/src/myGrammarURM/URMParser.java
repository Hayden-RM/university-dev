// Generated from /Users/hayden/Developer/IdeaProjects/URMProject/src/URM.g4 by ANTLR 4.13.2
/**
 * COMP711 : Software Assignment - Unlimited Register Machine
 * Hayden Richard-Marsters : 21152003
 **/

package myGrammarURM;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class URMParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, CONFIG=4, JUMP=5, SUCC=6, ZERO=7, TRANSFER=8, 
		INT=9, LINE_COMMENT=10, WS=11;
	public static final int
		RULE_program = 0, RULE_config = 1, RULE_in = 2, RULE_jump = 3, RULE_succ = 4, 
		RULE_zero = 5, RULE_transfer = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "config", "in", "jump", "succ", "zero", "transfer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "']'", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "CONFIG", "JUMP", "SUCC", "ZERO", "TRANSFER", 
			"INT", "LINE_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "URM.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public URMParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(URMParser.EOF, 0); }
		public ConfigContext config() {
			return getRuleContext(ConfigContext.class,0);
		}
		public List<InContext> in() {
			return getRuleContexts(InContext.class);
		}
		public InContext in(int i) {
			return getRuleContext(InContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof URMVisitor ) return ((URMVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONFIG) {
				{
				setState(14);
				config();
				}
			}

			setState(18); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(17);
				in();
				}
				}
				setState(20); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 480L) != 0) );
			setState(22);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConfigContext extends ParserRuleContext {
		public TerminalNode CONFIG() { return getToken(URMParser.CONFIG, 0); }
		public List<TerminalNode> INT() { return getTokens(URMParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(URMParser.INT, i);
		}
		public ConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_config; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).enterConfig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).exitConfig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof URMVisitor ) return ((URMVisitor<? extends T>)visitor).visitConfig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConfigContext config() throws RecognitionException {
		ConfigContext _localctx = new ConfigContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_config);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(CONFIG);
			setState(25);
			match(INT);
			setState(26);
			match(T__0);
			setState(27);
			match(INT);
			setState(28);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InContext extends ParserRuleContext {
		public JumpContext jump() {
			return getRuleContext(JumpContext.class,0);
		}
		public SuccContext succ() {
			return getRuleContext(SuccContext.class,0);
		}
		public ZeroContext zero() {
			return getRuleContext(ZeroContext.class,0);
		}
		public TransferContext transfer() {
			return getRuleContext(TransferContext.class,0);
		}
		public InContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_in; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).enterIn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).exitIn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof URMVisitor ) return ((URMVisitor<? extends T>)visitor).visitIn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InContext in() throws RecognitionException {
		InContext _localctx = new InContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_in);
		try {
			setState(34);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case JUMP:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				jump();
				}
				break;
			case SUCC:
				enterOuterAlt(_localctx, 2);
				{
				setState(31);
				succ();
				}
				break;
			case ZERO:
				enterOuterAlt(_localctx, 3);
				{
				setState(32);
				zero();
				}
				break;
			case TRANSFER:
				enterOuterAlt(_localctx, 4);
				{
				setState(33);
				transfer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JumpContext extends ParserRuleContext {
		public TerminalNode JUMP() { return getToken(URMParser.JUMP, 0); }
		public List<TerminalNode> INT() { return getTokens(URMParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(URMParser.INT, i);
		}
		public JumpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jump; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).enterJump(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).exitJump(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof URMVisitor ) return ((URMVisitor<? extends T>)visitor).visitJump(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JumpContext jump() throws RecognitionException {
		JumpContext _localctx = new JumpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_jump);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(JUMP);
			setState(37);
			match(INT);
			setState(38);
			match(T__0);
			setState(39);
			match(INT);
			setState(40);
			match(T__0);
			setState(41);
			match(INT);
			setState(42);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SuccContext extends ParserRuleContext {
		public TerminalNode SUCC() { return getToken(URMParser.SUCC, 0); }
		public TerminalNode INT() { return getToken(URMParser.INT, 0); }
		public SuccContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_succ; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).enterSucc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).exitSucc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof URMVisitor ) return ((URMVisitor<? extends T>)visitor).visitSucc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuccContext succ() throws RecognitionException {
		SuccContext _localctx = new SuccContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_succ);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(SUCC);
			setState(45);
			match(INT);
			setState(46);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ZeroContext extends ParserRuleContext {
		public TerminalNode ZERO() { return getToken(URMParser.ZERO, 0); }
		public TerminalNode INT() { return getToken(URMParser.INT, 0); }
		public ZeroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_zero; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).enterZero(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).exitZero(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof URMVisitor ) return ((URMVisitor<? extends T>)visitor).visitZero(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ZeroContext zero() throws RecognitionException {
		ZeroContext _localctx = new ZeroContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_zero);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(ZERO);
			setState(49);
			match(INT);
			setState(50);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TransferContext extends ParserRuleContext {
		public TerminalNode TRANSFER() { return getToken(URMParser.TRANSFER, 0); }
		public List<TerminalNode> INT() { return getTokens(URMParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(URMParser.INT, i);
		}
		public TransferContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transfer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).enterTransfer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof URMListener ) ((URMListener)listener).exitTransfer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof URMVisitor ) return ((URMVisitor<? extends T>)visitor).visitTransfer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransferContext transfer() throws RecognitionException {
		TransferContext _localctx = new TransferContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_transfer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(TRANSFER);
			setState(53);
			match(INT);
			setState(54);
			match(T__0);
			setState(55);
			match(INT);
			setState(56);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u000b;\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000\u0003\u0000\u0010"+
		"\b\u0000\u0001\u0000\u0004\u0000\u0013\b\u0000\u000b\u0000\f\u0000\u0014"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002#\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0000\u0000\u0007\u0000\u0002\u0004\u0006\b\n\f\u0000\u0000"+
		"8\u0000\u000f\u0001\u0000\u0000\u0000\u0002\u0018\u0001\u0000\u0000\u0000"+
		"\u0004\"\u0001\u0000\u0000\u0000\u0006$\u0001\u0000\u0000\u0000\b,\u0001"+
		"\u0000\u0000\u0000\n0\u0001\u0000\u0000\u0000\f4\u0001\u0000\u0000\u0000"+
		"\u000e\u0010\u0003\u0002\u0001\u0000\u000f\u000e\u0001\u0000\u0000\u0000"+
		"\u000f\u0010\u0001\u0000\u0000\u0000\u0010\u0012\u0001\u0000\u0000\u0000"+
		"\u0011\u0013\u0003\u0004\u0002\u0000\u0012\u0011\u0001\u0000\u0000\u0000"+
		"\u0013\u0014\u0001\u0000\u0000\u0000\u0014\u0012\u0001\u0000\u0000\u0000"+
		"\u0014\u0015\u0001\u0000\u0000\u0000\u0015\u0016\u0001\u0000\u0000\u0000"+
		"\u0016\u0017\u0005\u0000\u0000\u0001\u0017\u0001\u0001\u0000\u0000\u0000"+
		"\u0018\u0019\u0005\u0004\u0000\u0000\u0019\u001a\u0005\t\u0000\u0000\u001a"+
		"\u001b\u0005\u0001\u0000\u0000\u001b\u001c\u0005\t\u0000\u0000\u001c\u001d"+
		"\u0005\u0002\u0000\u0000\u001d\u0003\u0001\u0000\u0000\u0000\u001e#\u0003"+
		"\u0006\u0003\u0000\u001f#\u0003\b\u0004\u0000 #\u0003\n\u0005\u0000!#"+
		"\u0003\f\u0006\u0000\"\u001e\u0001\u0000\u0000\u0000\"\u001f\u0001\u0000"+
		"\u0000\u0000\" \u0001\u0000\u0000\u0000\"!\u0001\u0000\u0000\u0000#\u0005"+
		"\u0001\u0000\u0000\u0000$%\u0005\u0005\u0000\u0000%&\u0005\t\u0000\u0000"+
		"&\'\u0005\u0001\u0000\u0000\'(\u0005\t\u0000\u0000()\u0005\u0001\u0000"+
		"\u0000)*\u0005\t\u0000\u0000*+\u0005\u0003\u0000\u0000+\u0007\u0001\u0000"+
		"\u0000\u0000,-\u0005\u0006\u0000\u0000-.\u0005\t\u0000\u0000./\u0005\u0003"+
		"\u0000\u0000/\t\u0001\u0000\u0000\u000001\u0005\u0007\u0000\u000012\u0005"+
		"\t\u0000\u000023\u0005\u0003\u0000\u00003\u000b\u0001\u0000\u0000\u0000"+
		"45\u0005\b\u0000\u000056\u0005\t\u0000\u000067\u0005\u0001\u0000\u0000"+
		"78\u0005\t\u0000\u000089\u0005\u0003\u0000\u00009\r\u0001\u0000\u0000"+
		"\u0000\u0003\u000f\u0014\"";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}