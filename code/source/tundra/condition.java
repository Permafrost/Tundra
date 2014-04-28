package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-04-28 10:55:04.297
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
// --- <<IS-END-IMPORTS>> ---

public final class condition

{
	// ---( internal utility methods )---

	final static condition _instance = new condition();

	static condition _newInstance() { return new condition(); }

	static condition _cast(Object o) { return (condition)o; }

	// ---( server methods )---




	public static final void evaluate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(evaluate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $condition
		// [i] record:0:optional $scope
		// [o] field:0:required $result?
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String condition = IDataUtil.getString(cursor, "$condition");
		  IData scope = IDataUtil.getIData(cursor, "$scope");

		  IDataUtil.put(cursor, "$result?", "" + evaluate(condition, scope == null? pipeline : scope));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	public static boolean evaluate(String condition, IData scope) {
	  if (condition == null || condition.trim().equals("")) return true;

	  ANTLRInputStream input = new ANTLRInputStream(condition);
	  ConditionLexer lexer = new RethrowLexer(input);
	  CommonTokenStream tokens = new CommonTokenStream(lexer);
	  ConditionParser parser = new ConditionParser(tokens);
	  parser.setErrorHandler(new BailErrorStrategy());
	  parser.setBuildParseTree(true);
	  ParseTree tree = parser.condition();

	  ParseTreeWalker walker = new ParseTreeWalker();
	  ConditionEvaluator eval = new ConditionEvaluator(scope);
	  walker.walk(eval, tree);

	  return eval.getValue(tree);
	}

	public static class ConditionEvaluator extends ConditionBaseListener {
	  ParseTreeProperty<Boolean> values = new ParseTreeProperty<Boolean>();
	  IData scope;

	  public ConditionEvaluator(IData scope) {
	    this.scope = scope;
	  }

	  @Override public void exitCondition(ConditionParser.ConditionContext ctx) {
	    setValue(ctx, getValue(ctx.expr()));
	  }

	  @Override public void exitOr(ConditionParser.OrContext ctx) {
	    Boolean left = getValue(ctx.expr(0));
	    Boolean right = getValue(ctx.expr(1));
	    setValue(ctx, left || right);
	  }

	  @Override public void exitParens(ConditionParser.ParensContext ctx) {
	    setValue(ctx, getValue(ctx.expr()));
	  }

	  @Override public void exitAnd(ConditionParser.AndContext ctx) {
	    Boolean left = getValue(ctx.expr(0));
	    Boolean right = getValue(ctx.expr(1));
	    setValue(ctx, left && right);
	  }

	  @Override public void exitNot(ConditionParser.NotContext ctx) {
	    Boolean expr = getValue(ctx.expr());
	    setValue(ctx, !expr);
	  }

	  @Override public void exitInequalNull(ConditionParser.InequalNullContext ctx) {
	    Object left = getVariable(ctx.ID().getText());
	    setValue(ctx, left != null);
	  }

	  @Override public void exitInequalString(ConditionParser.InequalStringContext ctx) {
	    setValue(ctx, !equalString(ctx.ID().getText(), ctx.STRING().getText()));
	  }

	  @Override public void exitEqualRegex(ConditionParser.EqualRegexContext ctx) {
	    setValue(ctx, equalRegex(ctx.ID().getText(), ctx.REGEX().getText()));
	  }

	  protected boolean equalRegex(String ID, String REGEX) {
	    Object value = getVariable(ID);
	    boolean result = false;
	    if (value != null && value instanceof String) {
	      result = ((String)value).matches(strip(REGEX));
	    }
	    return result;
	  }

	  @Override public void exitInequalNumber(ConditionParser.InequalNumberContext ctx) {
	    setValue(ctx, !equalNumber(ctx.ID().getText(), ctx.NUMBER().getText()));
	  }

	  @Override public void exitEqualBoolean(ConditionParser.EqualBooleanContext ctx) {
	    setValue(ctx, equalBoolean(ctx.ID().getText(), ctx.BOOLEAN().getText()));
	  }

	  protected boolean equalBoolean(String ID, String BOOLEAN) {
	    Object left = getVariable(ID);
	    Boolean right = new Boolean(BOOLEAN);

	    boolean result = left != null;
	    if (result) {
	      if (left instanceof Boolean) {
	        result = (Boolean)left == right;
	      } else if (left instanceof String) {
	        String value = (String)left;
	        result = value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
	        if (result) result = (new Boolean(value)).equals(right);
	      } else {
	        result = false;
	      }
	    }
	    return result;
	  }

	  @Override public void exitInequalRegex(ConditionParser.InequalRegexContext ctx) {
	    setValue(ctx, !equalRegex(ctx.ID().getText(), ctx.REGEX().getText()));
	  }

	  @Override public void exitInequalID(ConditionParser.InequalIDContext ctx) {
	    setValue(ctx, !equalID(ctx.ID(0).getText(), ctx.ID(1).getText()));
	  }

	  @Override public void exitEqualID(ConditionParser.EqualIDContext ctx) {
	    setValue(ctx, equalID(ctx.ID(0).getText(), ctx.ID(1).getText()));
	  }

	  protected boolean equalID(String ID1, String ID2) {
	    Object left = getVariable(ID1);
	    Object right = getVariable(ID2);
	    return (left == null && right == null) || (left != null && right != null && left.equals(right));
	  }

	  @Override public void exitEqualNumber(ConditionParser.EqualNumberContext ctx) {
	    setValue(ctx, equalNumber(ctx.ID().getText(), ctx.NUMBER().getText()));
	  }

	  protected boolean equalNumber(String ID, String NUMBER) {
	    Object left = getVariable(ID);
	    String right = NUMBER;

	    boolean result = left != null && tundra.decimal.validate(left.toString()) && tundra.decimal.validate(right);

	    if (result) {
	      java.math.BigDecimal x = new java.math.BigDecimal(left.toString());
	      java.math.BigDecimal y = new java.math.BigDecimal(right);
	      result = x.compareTo(y) == 0;
	    }

	    return result;
	  }

	  @Override public void exitInequalBoolean(ConditionParser.InequalBooleanContext ctx) {
	    setValue(ctx, !equalBoolean(ctx.ID().getText(), ctx.BOOLEAN().getText()));
	  }

	  @Override public void exitEqualString(ConditionParser.EqualStringContext ctx) {
	    setValue(ctx, equalString(ctx.ID().getText(), ctx.STRING().getText()));
	  }

	  protected boolean equalString(String ID, String STRING) {
	    Object left = getVariable(ID);
	    String right = strip(STRING);
	    return left != null && left.equals(right);
	  }

	  @Override public void exitEqualNull(ConditionParser.EqualNullContext ctx) {
	    Object left = getVariable(ctx.ID().getText());
	    setValue(ctx, left == null);
	  }

	  // return the value of the variable with the given ID from the scope IData
	  protected Object getVariable(String ID) {
	    return tundra.support.document.get(scope, strip(ID));
	  }

	  // remove first and last chars from string, ie. quotes
	  protected String strip(String s) {
	    if (s == null) return null;
	    int length = s.length();
	    if (length > 1) s = s.substring(1, length - 1);
	    return s;
	  }

	  public void setValue(ParseTree node, Boolean value) {
	    values.put(node, value);
	  }

	  public Boolean getValue(ParseTree node) {
	    return values.get(node);
	  }
	}

	public static class RethrowStrategy extends org.antlr.v4.runtime.DefaultErrorStrategy {
	  @Override public void recover(org.antlr.v4.runtime.Parser recognizer, org.antlr.v4.runtime.RecognitionException e) {
	    throw new RuntimeException(e);
	  }

	  @Override public void reportError(Parser recognizer, RecognitionException e) {
	    throw new RuntimeException(e);
	  }

	  @Override public org.antlr.v4.runtime.Token recoverInline(org.antlr.v4.runtime.Parser recognizer) throws org.antlr.v4.runtime.RecognitionException {
	    throw new RuntimeException(new org.antlr.v4.runtime.InputMismatchException(recognizer));
	  }

	  @Override public void sync(org.antlr.v4.runtime.Parser recognizer) { }

	  @Override public void reportUnwantedToken(@NotNull Parser recognizer) {
	    throw new RuntimeException(new org.antlr.v4.runtime.InputMismatchException(recognizer));
	  }
	}

	public static class RethrowLexer extends ConditionLexer {
	  public RethrowLexer(CharStream input) {
	    super(input);
	  }
	  @Override public void recover(LexerNoViableAltException e) {
	    throw new RuntimeException(e);
	  }
	}

	/**************************************************************/

	@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
	public static class ConditionLexer extends Lexer {
	  protected static final DFA[] _decisionToDFA;
	  protected static final PredictionContextCache _sharedContextCache =
	    new PredictionContextCache();
	  public static final int
	    T__1=1, T__0=2, ID=3, BOOLEAN=4, TRUE=5, FALSE=6, NULL=7, STRING=8, NUMBER=9,
	    REGEX=10, NOT=11, EQUAL=12, INEQUAL=13, AND=14, OR=15, WS=16;
	  public static String[] modeNames = {
	    "DEFAULT_MODE"
	  };

	  public static final String[] tokenNames = {
	    "<INVALID>",
	    "')'", "'('", "ID", "BOOLEAN", "TRUE", "FALSE", "NULL", "STRING", "NUMBER",
	    "REGEX", "NOT", "EQUAL", "INEQUAL", "AND", "OR", "WS"
	  };
	  public static final String[] ruleNames = {
	    "T__1", "T__0", "ID", "BOOLEAN", "TRUE", "FALSE", "NULL", "STRING", "ESC",
	    "UNICODE", "HEX", "NUMBER", "INT", "EXP", "REGEX", "NOT", "EQUAL", "INEQUAL",
	    "AND", "OR", "WS"
	  };


	  public ConditionLexer(CharStream input) {
	    super(input);
	    _interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	  }

	  @Override
	  public String getGrammarFileName() { return "Condition.g4"; }

	  @Override
	  public String[] getTokenNames() { return tokenNames; }

	  @Override
	  public String[] getRuleNames() { return ruleNames; }

	  @Override
	  public String[] getModeNames() { return modeNames; }

	  @Override
	  public ATN getATN() { return _ATN; }

	  @Override
	  public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
	    switch (ruleIndex) {
	    case 20: WS_action((RuleContext)_localctx, actionIndex); break;
	    }
	  }
	  private void WS_action(RuleContext _localctx, int actionIndex) {
	    switch (actionIndex) {
	    case 0: skip();  break;
	    }
	  }

	  public static final String _serializedATN =
	    "\2\4\22\u00C8\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b"+
	    "\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20"+
	    "\t\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2"+
	    "\3\2\3\3\3\3\3\4\3\4\7\4\64\n\4\f\4\16\4\67\13\4\3\4\3\4\3\5\3\5\5\5="+
	    "\n\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\5\bK\n\b\3\b\3\b"+
	    "\3\b\3\b\3\b\3\t\3\t\3\t\7\tU\n\t\f\t\16\tX\13\t\3\t\3\t\3\t\7\t]\n\t"+
	    "\f\t\16\t`\13\t\3\t\5\tc\n\t\3\n\3\n\3\n\5\nh\n\n\3\13\3\13\3\13\3\13"+
	    "\3\13\3\13\3\f\3\f\3\r\5\rs\n\r\3\r\3\r\3\r\3\r\5\ry\n\r\3\r\5\r|\n\r"+
	    "\3\r\3\r\3\r\3\r\5\r\u0082\n\r\3\r\5\r\u0085\n\r\3\16\3\16\3\16\7\16\u008A"+
	    "\n\16\f\16\16\16\u008D\13\16\5\16\u008F\n\16\3\17\3\17\5\17\u0093\n\17"+
	    "\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u009B\n\20\f\20\16\20\u009E\13\20"+
	    "\3\20\3\20\3\21\3\21\3\21\3\21\5\21\u00A6\n\21\3\22\3\22\3\22\5\22\u00AB"+
	    "\n\22\3\23\3\23\3\23\3\23\5\23\u00B1\n\23\3\24\3\24\3\24\3\24\3\24\3\24"+
	    "\5\24\u00B9\n\24\3\25\3\25\3\25\3\25\3\25\5\25\u00C0\n\25\3\26\6\26\u00C3"+
	    "\n\26\r\26\16\26\u00C4\3\26\3\26\6\65V^\u009C\27\3\3\1\5\4\1\7\5\1\t\6"+
	    "\1\13\7\1\r\b\1\17\t\1\21\n\1\23\2\1\25\2\1\27\2\1\31\13\1\33\2\1\35\2"+
	    "\1\37\f\1!\r\1#\16\1%\17\1\'\20\1)\21\1+\22\2\3\2#\3\'\'\4VVvv\4TTtt\4"+
	    "WWww\4GGgg\4HHhh\4CCcc\4NNnn\4UUuu\4GGgg\3&&\4PPpp\4WWww\4NNnn\4NNnn\4"+
	    "$$^^\3))\n$$\61\61^^ddhhppttvv\5\62;CHch\3\63;\3\62;\4GGgg\4--//\3\61"+
	    "\61\4PPpp\4QQqq\4VVvv\4CCcc\4PPpp\4FFff\4QQqq\4TTtt\5\13\f\17\17\"\"\u00DD"+
	    "\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
	    "\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\31\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2"+
	    "#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2\5/\3"+
	    "\2\2\2\7\61\3\2\2\2\t<\3\2\2\2\13>\3\2\2\2\rC\3\2\2\2\17J\3\2\2\2\21b"+
	    "\3\2\2\2\23d\3\2\2\2\25i\3\2\2\2\27o\3\2\2\2\31\u0084\3\2\2\2\33\u008E"+
	    "\3\2\2\2\35\u0090\3\2\2\2\37\u0096\3\2\2\2!\u00A5\3\2\2\2#\u00AA\3\2\2"+
	    "\2%\u00B0\3\2\2\2\'\u00B8\3\2\2\2)\u00BF\3\2\2\2+\u00C2\3\2\2\2-.\7+\2"+
	    "\2.\4\3\2\2\2/\60\7*\2\2\60\6\3\2\2\2\61\65\7\'\2\2\62\64\n\2\2\2\63\62"+
	    "\3\2\2\2\64\67\3\2\2\2\65\66\3\2\2\2\65\63\3\2\2\2\668\3\2\2\2\67\65\3"+
	    "\2\2\289\7\'\2\29\b\3\2\2\2:=\5\13\6\2;=\5\r\7\2<:\3\2\2\2<;\3\2\2\2="+
	    "\n\3\2\2\2>?\t\3\2\2?@\t\4\2\2@A\t\5\2\2AB\t\6\2\2B\f\3\2\2\2CD\t\7\2"+
	    "\2DE\t\b\2\2EF\t\t\2\2FG\t\n\2\2GH\t\13\2\2H\16\3\2\2\2IK\t\f\2\2JI\3"+
	    "\2\2\2JK\3\2\2\2KL\3\2\2\2LM\t\r\2\2MN\t\16\2\2NO\t\17\2\2OP\t\20\2\2"+
	    "P\20\3\2\2\2QV\7$\2\2RU\5\23\n\2SU\n\21\2\2TR\3\2\2\2TS\3\2\2\2UX\3\2"+
	    "\2\2VW\3\2\2\2VT\3\2\2\2WY\3\2\2\2XV\3\2\2\2Yc\7$\2\2Z^\7)\2\2[]\n\22"+
	    "\2\2\\[\3\2\2\2]`\3\2\2\2^_\3\2\2\2^\\\3\2\2\2_a\3\2\2\2`^\3\2\2\2ac\7"+
	    ")\2\2bQ\3\2\2\2bZ\3\2\2\2c\22\3\2\2\2dg\7^\2\2eh\t\23\2\2fh\5\25\13\2"+
	    "ge\3\2\2\2gf\3\2\2\2h\24\3\2\2\2ij\7w\2\2jk\5\27\f\2kl\5\27\f\2lm\5\27"+
	    "\f\2mn\5\27\f\2n\26\3\2\2\2op\t\24\2\2p\30\3\2\2\2qs\7/\2\2rq\3\2\2\2"+
	    "rs\3\2\2\2st\3\2\2\2tu\5\33\16\2uv\7\60\2\2vx\5\33\16\2wy\5\35\17\2xw"+
	    "\3\2\2\2xy\3\2\2\2y\u0085\3\2\2\2z|\7/\2\2{z\3\2\2\2{|\3\2\2\2|}\3\2\2"+
	    "\2}~\5\33\16\2~\177\5\35\17\2\177\u0085\3\2\2\2\u0080\u0082\7/\2\2\u0081"+
	    "\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\5\33"+
	    "\16\2\u0084r\3\2\2\2\u0084{\3\2\2\2\u0084\u0081\3\2\2\2\u0085\32\3\2\2"+
	    "\2\u0086\u008F\7\62\2\2\u0087\u008B\t\25\2\2\u0088\u008A\t\26\2\2\u0089"+
	    "\u0088\3\2\2\2\u008A\u008D\3\2\2\2\u008B\u0089\3\2\2\2\u008B\u008C\3\2"+
	    "\2\2\u008C\u008F\3\2\2\2\u008D\u008B\3\2\2\2\u008E\u0086\3\2\2\2\u008E"+
	    "\u0087\3\2\2\2\u008F\34\3\2\2\2\u0090\u0092\t\27\2\2\u0091\u0093\t\30"+
	    "\2\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
	    "\u0095\5\33\16\2\u0095\36\3\2\2\2\u0096\u009C\7\61\2\2\u0097\u0098\7^"+
	    "\2\2\u0098\u009B\7\61\2\2\u0099\u009B\n\31\2\2\u009A\u0097\3\2\2\2\u009A"+
	    "\u0099\3\2\2\2\u009B\u009E\3\2\2\2\u009C\u009D\3\2\2\2\u009C\u009A\3\2"+
	    "\2\2\u009D\u009F\3\2\2\2\u009E\u009C\3\2\2\2\u009F\u00A0\7\61\2\2\u00A0"+
	    " \3\2\2\2\u00A1\u00A6\7#\2\2\u00A2\u00A3\t\32\2\2\u00A3\u00A4\t\33\2\2"+
	    "\u00A4\u00A6\t\34\2\2\u00A5\u00A1\3\2\2\2\u00A5\u00A2\3\2\2\2\u00A6\""+
	    "\3\2\2\2\u00A7\u00AB\7?\2\2\u00A8\u00A9\7?\2\2\u00A9\u00AB\7?\2\2\u00AA"+
	    "\u00A7\3\2\2\2\u00AA\u00A8\3\2\2\2\u00AB$\3\2\2\2\u00AC\u00AD\7#\2\2\u00AD"+
	    "\u00B1\7?\2\2\u00AE\u00AF\7>\2\2\u00AF\u00B1\7@\2\2\u00B0\u00AC\3\2\2"+
	    "\2\u00B0\u00AE\3\2\2\2\u00B1&\3\2\2\2\u00B2\u00B9\7(\2\2\u00B3\u00B4\7"+
	    "(\2\2\u00B4\u00B9\7(\2\2\u00B5\u00B6\t\35\2\2\u00B6\u00B7\t\36\2\2\u00B7"+
	    "\u00B9\t\37\2\2\u00B8\u00B2\3\2\2\2\u00B8\u00B3\3\2\2\2\u00B8\u00B5\3"+
	    "\2\2\2\u00B9(\3\2\2\2\u00BA\u00C0\7~\2\2\u00BB\u00BC\7~\2\2\u00BC\u00C0"+
	    "\7~\2\2\u00BD\u00BE\t \2\2\u00BE\u00C0\t!\2\2\u00BF\u00BA\3\2\2\2\u00BF"+
	    "\u00BB\3\2\2\2\u00BF\u00BD\3\2\2\2\u00C0*\3\2\2\2\u00C1\u00C3\t\"\2\2"+
	    "\u00C2\u00C1\3\2\2\2\u00C3\u00C4\3\2\2\2\u00C4\u00C2\3\2\2\2\u00C4\u00C5"+
	    "\3\2\2\2\u00C5\u00C6\3\2\2\2\u00C6\u00C7\b\26\2\2\u00C7,\3\2\2\2\33\2"+
	    "\65<JTV^bgrx{\u0081\u0084\u008B\u008E\u0092\u009A\u009C\u00A5\u00AA\u00B0"+
	    "\u00B8\u00BF\u00C4";
	  public static final ATN _ATN =
	    ATNSimulator.deserialize(_serializedATN.toCharArray());
	  static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	  }
	}

	@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
	public static class ConditionParser extends Parser {
	  protected static final DFA[] _decisionToDFA;
	  protected static final PredictionContextCache _sharedContextCache =
	    new PredictionContextCache();
	  public static final int
	    T__1=1, T__0=2, ID=3, BOOLEAN=4, TRUE=5, FALSE=6, NULL=7, STRING=8, NUMBER=9,
	    REGEX=10, NOT=11, EQUAL=12, INEQUAL=13, AND=14, OR=15, WS=16;
	  public static final String[] tokenNames = {
	    "<INVALID>", "')'", "'('", "ID", "BOOLEAN", "TRUE", "FALSE", "NULL", "STRING",
	    "NUMBER", "REGEX", "NOT", "EQUAL", "INEQUAL", "AND", "OR", "WS"
	  };
	  public static final int
	    RULE_condition = 0, RULE_expr = 1;
	  public static final String[] ruleNames = {
	    "condition", "expr"
	  };

	  @Override
	  public String getGrammarFileName() { return "Condition.g4"; }

	  @Override
	  public String[] getTokenNames() { return tokenNames; }

	  @Override
	  public String[] getRuleNames() { return ruleNames; }

	  @Override
	  public ATN getATN() { return _ATN; }

	  public ConditionParser(TokenStream input) {
	    super(input);
	    _interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	  }
	  public static class ConditionContext extends ParserRuleContext {
	    public ExprContext expr() {
	      return getRuleContext(ExprContext.class,0);
	    }
	    public ConditionContext(ParserRuleContext parent, int invokingState) {
	      super(parent, invokingState);
	    }
	    @Override public int getRuleIndex() { return RULE_condition; }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterCondition(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitCondition(this);
	    }
	  }

	  public final ConditionContext condition() throws RecognitionException {
	    ConditionContext _localctx = new ConditionContext(_ctx, getState());
	    enterRule(_localctx, 0, RULE_condition);
	    try {
	      enterOuterAlt(_localctx, 1);
	      {
	      setState(4); expr(0);
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

	  public static class ExprContext extends ParserRuleContext {
	    public int _p;
	    public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
	    public ExprContext(ParserRuleContext parent, int invokingState, int _p) {
	      super(parent, invokingState);
	      this._p = _p;
	    }
	    @Override public int getRuleIndex() { return RULE_expr; }

	    public ExprContext() { }
	    public void copyFrom(ExprContext ctx) {
	      super.copyFrom(ctx);
	      this._p = ctx._p;
	    }
	  }
	  public static class InequalStringContext extends ExprContext {
	    public TerminalNode INEQUAL() { return getToken(ConditionParser.INEQUAL, 0); }
	    public TerminalNode ID() { return getToken(ConditionParser.ID, 0); }
	    public TerminalNode STRING() { return getToken(ConditionParser.STRING, 0); }
	    public InequalStringContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterInequalString(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitInequalString(this);
	    }
	  }
	  public static class EqualRegexContext extends ExprContext {
	    public TerminalNode REGEX() { return getToken(ConditionParser.REGEX, 0); }
	    public TerminalNode ID() { return getToken(ConditionParser.ID, 0); }
	    public TerminalNode EQUAL() { return getToken(ConditionParser.EQUAL, 0); }
	    public EqualRegexContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterEqualRegex(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitEqualRegex(this);
	    }
	  }
	  public static class OrContext extends ExprContext {
	    public ExprContext expr(int i) {
	      return getRuleContext(ExprContext.class,i);
	    }
	    public List<ExprContext> expr() {
	      return getRuleContexts(ExprContext.class);
	    }
	    public TerminalNode OR() { return getToken(ConditionParser.OR, 0); }
	    public OrContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterOr(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitOr(this);
	    }
	  }
	  public static class InequalNullContext extends ExprContext {
	    public TerminalNode INEQUAL() { return getToken(ConditionParser.INEQUAL, 0); }
	    public TerminalNode ID() { return getToken(ConditionParser.ID, 0); }
	    public TerminalNode NULL() { return getToken(ConditionParser.NULL, 0); }
	    public InequalNullContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterInequalNull(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitInequalNull(this);
	    }
	  }
	  public static class ParensContext extends ExprContext {
	    public ExprContext expr() {
	      return getRuleContext(ExprContext.class,0);
	    }
	    public ParensContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterParens(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitParens(this);
	    }
	  }
	  public static class InequalNumberContext extends ExprContext {
	    public TerminalNode INEQUAL() { return getToken(ConditionParser.INEQUAL, 0); }
	    public TerminalNode ID() { return getToken(ConditionParser.ID, 0); }
	    public TerminalNode NUMBER() { return getToken(ConditionParser.NUMBER, 0); }
	    public InequalNumberContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterInequalNumber(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitInequalNumber(this);
	    }
	  }
	  public static class EqualBooleanContext extends ExprContext {
	    public TerminalNode BOOLEAN() { return getToken(ConditionParser.BOOLEAN, 0); }
	    public TerminalNode ID() { return getToken(ConditionParser.ID, 0); }
	    public TerminalNode EQUAL() { return getToken(ConditionParser.EQUAL, 0); }
	    public EqualBooleanContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterEqualBoolean(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitEqualBoolean(this);
	    }
	  }
	  public static class AndContext extends ExprContext {
	    public TerminalNode AND() { return getToken(ConditionParser.AND, 0); }
	    public ExprContext expr(int i) {
	      return getRuleContext(ExprContext.class,i);
	    }
	    public List<ExprContext> expr() {
	      return getRuleContexts(ExprContext.class);
	    }
	    public AndContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterAnd(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitAnd(this);
	    }
	  }
	  public static class NotContext extends ExprContext {
	    public TerminalNode NOT() { return getToken(ConditionParser.NOT, 0); }
	    public ExprContext expr() {
	      return getRuleContext(ExprContext.class,0);
	    }
	    public NotContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterNot(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitNot(this);
	    }
	  }
	  public static class InequalRegexContext extends ExprContext {
	    public TerminalNode REGEX() { return getToken(ConditionParser.REGEX, 0); }
	    public TerminalNode INEQUAL() { return getToken(ConditionParser.INEQUAL, 0); }
	    public TerminalNode ID() { return getToken(ConditionParser.ID, 0); }
	    public InequalRegexContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterInequalRegex(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitInequalRegex(this);
	    }
	  }
	  public static class InequalIDContext extends ExprContext {
	    public TerminalNode INEQUAL() { return getToken(ConditionParser.INEQUAL, 0); }
	    public TerminalNode ID(int i) {
	      return getToken(ConditionParser.ID, i);
	    }
	    public List<TerminalNode> ID() { return getTokens(ConditionParser.ID); }
	    public InequalIDContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterInequalID(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitInequalID(this);
	    }
	  }
	  public static class EqualIDContext extends ExprContext {
	    public TerminalNode ID(int i) {
	      return getToken(ConditionParser.ID, i);
	    }
	    public List<TerminalNode> ID() { return getTokens(ConditionParser.ID); }
	    public TerminalNode EQUAL() { return getToken(ConditionParser.EQUAL, 0); }
	    public EqualIDContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterEqualID(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitEqualID(this);
	    }
	  }
	  public static class EqualNumberContext extends ExprContext {
	    public TerminalNode ID() { return getToken(ConditionParser.ID, 0); }
	    public TerminalNode EQUAL() { return getToken(ConditionParser.EQUAL, 0); }
	    public TerminalNode NUMBER() { return getToken(ConditionParser.NUMBER, 0); }
	    public EqualNumberContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterEqualNumber(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitEqualNumber(this);
	    }
	  }
	  public static class InequalBooleanContext extends ExprContext {
	    public TerminalNode INEQUAL() { return getToken(ConditionParser.INEQUAL, 0); }
	    public TerminalNode BOOLEAN() { return getToken(ConditionParser.BOOLEAN, 0); }
	    public TerminalNode ID() { return getToken(ConditionParser.ID, 0); }
	    public InequalBooleanContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterInequalBoolean(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitInequalBoolean(this);
	    }
	  }
	  public static class EqualStringContext extends ExprContext {
	    public TerminalNode ID() { return getToken(ConditionParser.ID, 0); }
	    public TerminalNode EQUAL() { return getToken(ConditionParser.EQUAL, 0); }
	    public TerminalNode STRING() { return getToken(ConditionParser.STRING, 0); }
	    public EqualStringContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterEqualString(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitEqualString(this);
	    }
	  }
	  public static class EqualNullContext extends ExprContext {
	    public TerminalNode ID() { return getToken(ConditionParser.ID, 0); }
	    public TerminalNode NULL() { return getToken(ConditionParser.NULL, 0); }
	    public TerminalNode EQUAL() { return getToken(ConditionParser.EQUAL, 0); }
	    public EqualNullContext(ExprContext ctx) { copyFrom(ctx); }
	    @Override
	    public void enterRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).enterEqualNull(this);
	    }
	    @Override
	    public void exitRule(ParseTreeListener listener) {
	      if ( listener instanceof ConditionListener ) ((ConditionListener)listener).exitEqualNull(this);
	    }
	  }

	  public final ExprContext expr(int _p) throws RecognitionException {
	    ParserRuleContext _parentctx = _ctx;
	    int _parentState = getState();
	    ExprContext _localctx = new ExprContext(_ctx, _parentState, _p);
	    ExprContext _prevctx = _localctx;
	    int _startState = 2;
	    enterRecursionRule(_localctx, RULE_expr);
	    try {
	      int _alt;
	      enterOuterAlt(_localctx, 1);
	      {
	      setState(49);
	      switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
	      case 1:
	        {
	        _localctx = new NotContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;

	        setState(7); match(NOT);
	        setState(8); expr(16);
	        }
	        break;

	      case 2:
	        {
	        _localctx = new EqualIDContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(9); match(ID);
	        setState(10); match(EQUAL);
	        setState(11); match(ID);
	        }
	        break;

	      case 3:
	        {
	        _localctx = new EqualBooleanContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(12); match(ID);
	        setState(13); match(EQUAL);
	        setState(14); match(BOOLEAN);
	        }
	        break;

	      case 4:
	        {
	        _localctx = new EqualStringContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(15); match(ID);
	        setState(16); match(EQUAL);
	        setState(17); match(STRING);
	        }
	        break;

	      case 5:
	        {
	        _localctx = new EqualNumberContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(18); match(ID);
	        setState(19); match(EQUAL);
	        setState(20); match(NUMBER);
	        }
	        break;

	      case 6:
	        {
	        _localctx = new EqualRegexContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(21); match(ID);
	        setState(22); match(EQUAL);
	        setState(23); match(REGEX);
	        }
	        break;

	      case 7:
	        {
	        _localctx = new EqualNullContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(24); match(ID);
	        setState(25); match(EQUAL);
	        setState(26); match(NULL);
	        }
	        break;

	      case 8:
	        {
	        _localctx = new InequalIDContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(27); match(ID);
	        setState(28); match(INEQUAL);
	        setState(29); match(ID);
	        }
	        break;

	      case 9:
	        {
	        _localctx = new InequalBooleanContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(30); match(ID);
	        setState(31); match(INEQUAL);
	        setState(32); match(BOOLEAN);
	        }
	        break;

	      case 10:
	        {
	        _localctx = new InequalStringContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(33); match(ID);
	        setState(34); match(INEQUAL);
	        setState(35); match(STRING);
	        }
	        break;

	      case 11:
	        {
	        _localctx = new InequalNumberContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(36); match(ID);
	        setState(37); match(INEQUAL);
	        setState(38); match(NUMBER);
	        }
	        break;

	      case 12:
	        {
	        _localctx = new InequalRegexContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(39); match(ID);
	        setState(40); match(INEQUAL);
	        setState(41); match(REGEX);
	        }
	        break;

	      case 13:
	        {
	        _localctx = new InequalNullContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(42); match(ID);
	        setState(43); match(INEQUAL);
	        setState(44); match(NULL);
	        }
	        break;

	      case 14:
	        {
	        _localctx = new ParensContext(_localctx);
	        _ctx = _localctx;
	        _prevctx = _localctx;
	        setState(45); match(2);
	        setState(46); expr(0);
	        setState(47); match(1);
	        }
	        break;
	      }
	      _ctx.stop = _input.LT(-1);
	      setState(59);
	      _errHandler.sync(this);
	      _alt = getInterpreter().adaptivePredict(_input,2,_ctx);
	      while ( _alt!=2 && _alt!=-1 ) {
	        if ( _alt==1 ) {
	          if ( _parseListeners!=null ) triggerExitRuleEvent();
	          _prevctx = _localctx;
	          {
	          setState(57);
	          switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
	          case 1:
	            {
	            _localctx = new AndContext(new ExprContext(_parentctx, _parentState, _p));
	            pushNewRecursionContext(_localctx, _startState, RULE_expr);
	            setState(51);
	            if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
	            setState(52); match(AND);
	            setState(53); expr(4);
	            }
	            break;

	          case 2:
	            {
	            _localctx = new OrContext(new ExprContext(_parentctx, _parentState, _p));
	            pushNewRecursionContext(_localctx, _startState, RULE_expr);
	            setState(54);
	            if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
	            setState(55); match(OR);
	            setState(56); expr(3);
	            }
	            break;
	          }
	          }
	        }
	        setState(61);
	        _errHandler.sync(this);
	        _alt = getInterpreter().adaptivePredict(_input,2,_ctx);
	      }
	      }
	    }
	    catch (RecognitionException re) {
	      _localctx.exception = re;
	      _errHandler.reportError(this, re);
	      _errHandler.recover(this, re);
	    }
	    finally {
	      unrollRecursionContexts(_parentctx);
	    }
	    return _localctx;
	  }

	  public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
	    switch (ruleIndex) {
	    case 1: return expr_sempred((ExprContext)_localctx, predIndex);
	    }
	    return true;
	  }
	  private boolean expr_sempred(ExprContext _localctx, int predIndex) {
	    switch (predIndex) {
	    case 0: return 3 >= _localctx._p;

	    case 1: return 2 >= _localctx._p;
	    }
	    return true;
	  }

	  public static final String _serializedATN =
	    "\2\3\22A\4\2\t\2\4\3\t\3\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
	    "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
	    "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\64"+
	    "\n\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3<\n\3\f\3\16\3?\13\3\3\3\2\4\2\4\2\2M"+
	    "\2\6\3\2\2\2\4\63\3\2\2\2\6\7\5\4\3\2\7\3\3\2\2\2\b\t\b\3\1\2\t\n\7\r"+
	    "\2\2\n\64\5\4\3\2\13\f\7\5\2\2\f\r\7\16\2\2\r\64\7\5\2\2\16\17\7\5\2\2"+
	    "\17\20\7\16\2\2\20\64\7\6\2\2\21\22\7\5\2\2\22\23\7\16\2\2\23\64\7\n\2"+
	    "\2\24\25\7\5\2\2\25\26\7\16\2\2\26\64\7\13\2\2\27\30\7\5\2\2\30\31\7\16"+
	    "\2\2\31\64\7\f\2\2\32\33\7\5\2\2\33\34\7\16\2\2\34\64\7\t\2\2\35\36\7"+
	    "\5\2\2\36\37\7\17\2\2\37\64\7\5\2\2 !\7\5\2\2!\"\7\17\2\2\"\64\7\6\2\2"+
	    "#$\7\5\2\2$%\7\17\2\2%\64\7\n\2\2&\'\7\5\2\2\'(\7\17\2\2(\64\7\13\2\2"+
	    ")*\7\5\2\2*+\7\17\2\2+\64\7\f\2\2,-\7\5\2\2-.\7\17\2\2.\64\7\t\2\2/\60"+
	    "\7\4\2\2\60\61\5\4\3\2\61\62\7\3\2\2\62\64\3\2\2\2\63\b\3\2\2\2\63\13"+
	    "\3\2\2\2\63\16\3\2\2\2\63\21\3\2\2\2\63\24\3\2\2\2\63\27\3\2\2\2\63\32"+
	    "\3\2\2\2\63\35\3\2\2\2\63 \3\2\2\2\63#\3\2\2\2\63&\3\2\2\2\63)\3\2\2\2"+
	    "\63,\3\2\2\2\63/\3\2\2\2\64=\3\2\2\2\65\66\6\3\2\3\66\67\7\20\2\2\67<"+
	    "\5\4\3\289\6\3\3\39:\7\21\2\2:<\5\4\3\2;\65\3\2\2\2;8\3\2\2\2<?\3\2\2"+
	    "\2=;\3\2\2\2=>\3\2\2\2>\5\3\2\2\2?=\3\2\2\2\5\63;=";
	  public static final ATN _ATN =
	    ATNSimulator.deserialize(_serializedATN.toCharArray());
	  static {
	    _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
	  }
	}

	public static interface ConditionListener extends ParseTreeListener {
	  void enterInequalString(ConditionParser.InequalStringContext ctx);
	  void exitInequalString(ConditionParser.InequalStringContext ctx);

	  void enterEqualRegex(ConditionParser.EqualRegexContext ctx);
	  void exitEqualRegex(ConditionParser.EqualRegexContext ctx);

	  void enterCondition(ConditionParser.ConditionContext ctx);
	  void exitCondition(ConditionParser.ConditionContext ctx);

	  void enterOr(ConditionParser.OrContext ctx);
	  void exitOr(ConditionParser.OrContext ctx);

	  void enterInequalNull(ConditionParser.InequalNullContext ctx);
	  void exitInequalNull(ConditionParser.InequalNullContext ctx);

	  void enterParens(ConditionParser.ParensContext ctx);
	  void exitParens(ConditionParser.ParensContext ctx);

	  void enterInequalNumber(ConditionParser.InequalNumberContext ctx);
	  void exitInequalNumber(ConditionParser.InequalNumberContext ctx);

	  void enterEqualBoolean(ConditionParser.EqualBooleanContext ctx);
	  void exitEqualBoolean(ConditionParser.EqualBooleanContext ctx);

	  void enterAnd(ConditionParser.AndContext ctx);
	  void exitAnd(ConditionParser.AndContext ctx);

	  void enterNot(ConditionParser.NotContext ctx);
	  void exitNot(ConditionParser.NotContext ctx);

	  void enterInequalRegex(ConditionParser.InequalRegexContext ctx);
	  void exitInequalRegex(ConditionParser.InequalRegexContext ctx);

	  void enterInequalID(ConditionParser.InequalIDContext ctx);
	  void exitInequalID(ConditionParser.InequalIDContext ctx);

	  void enterEqualID(ConditionParser.EqualIDContext ctx);
	  void exitEqualID(ConditionParser.EqualIDContext ctx);

	  void enterEqualNumber(ConditionParser.EqualNumberContext ctx);
	  void exitEqualNumber(ConditionParser.EqualNumberContext ctx);

	  void enterInequalBoolean(ConditionParser.InequalBooleanContext ctx);
	  void exitInequalBoolean(ConditionParser.InequalBooleanContext ctx);

	  void enterEqualString(ConditionParser.EqualStringContext ctx);
	  void exitEqualString(ConditionParser.EqualStringContext ctx);

	  void enterEqualNull(ConditionParser.EqualNullContext ctx);
	  void exitEqualNull(ConditionParser.EqualNullContext ctx);
	}

	public static class ConditionBaseListener implements ConditionListener {
	  @Override public void enterInequalString(ConditionParser.InequalStringContext ctx) { }
	  @Override public void exitInequalString(ConditionParser.InequalStringContext ctx) { }

	  @Override public void enterEqualRegex(ConditionParser.EqualRegexContext ctx) { }
	  @Override public void exitEqualRegex(ConditionParser.EqualRegexContext ctx) { }

	  @Override public void enterCondition(ConditionParser.ConditionContext ctx) { }
	  @Override public void exitCondition(ConditionParser.ConditionContext ctx) { }

	  @Override public void enterOr(ConditionParser.OrContext ctx) { }
	  @Override public void exitOr(ConditionParser.OrContext ctx) { }

	  @Override public void enterInequalNull(ConditionParser.InequalNullContext ctx) { }
	  @Override public void exitInequalNull(ConditionParser.InequalNullContext ctx) { }

	  @Override public void enterParens(ConditionParser.ParensContext ctx) { }
	  @Override public void exitParens(ConditionParser.ParensContext ctx) { }

	  @Override public void enterInequalNumber(ConditionParser.InequalNumberContext ctx) { }
	  @Override public void exitInequalNumber(ConditionParser.InequalNumberContext ctx) { }

	  @Override public void enterEqualBoolean(ConditionParser.EqualBooleanContext ctx) { }
	  @Override public void exitEqualBoolean(ConditionParser.EqualBooleanContext ctx) { }

	  @Override public void enterAnd(ConditionParser.AndContext ctx) { }
	  @Override public void exitAnd(ConditionParser.AndContext ctx) { }

	  @Override public void enterNot(ConditionParser.NotContext ctx) { }
	  @Override public void exitNot(ConditionParser.NotContext ctx) { }

	  @Override public void enterInequalRegex(ConditionParser.InequalRegexContext ctx) { }
	  @Override public void exitInequalRegex(ConditionParser.InequalRegexContext ctx) { }

	  @Override public void enterInequalID(ConditionParser.InequalIDContext ctx) { }
	  @Override public void exitInequalID(ConditionParser.InequalIDContext ctx) { }

	  @Override public void enterEqualID(ConditionParser.EqualIDContext ctx) { }
	  @Override public void exitEqualID(ConditionParser.EqualIDContext ctx) { }

	  @Override public void enterEqualNumber(ConditionParser.EqualNumberContext ctx) { }
	  @Override public void exitEqualNumber(ConditionParser.EqualNumberContext ctx) { }

	  @Override public void enterInequalBoolean(ConditionParser.InequalBooleanContext ctx) { }
	  @Override public void exitInequalBoolean(ConditionParser.InequalBooleanContext ctx) { }

	  @Override public void enterEqualString(ConditionParser.EqualStringContext ctx) { }
	  @Override public void exitEqualString(ConditionParser.EqualStringContext ctx) { }

	  @Override public void enterEqualNull(ConditionParser.EqualNullContext ctx) { }
	  @Override public void exitEqualNull(ConditionParser.EqualNullContext ctx) { }

	  @Override public void enterEveryRule(ParserRuleContext ctx) { }
	  @Override public void exitEveryRule(ParserRuleContext ctx) { }
	  @Override public void visitTerminal(TerminalNode node) { }
	  @Override public void visitErrorNode(ErrorNode node) { }
	}
	// --- <<IS-END-SHARED>> ---
}

