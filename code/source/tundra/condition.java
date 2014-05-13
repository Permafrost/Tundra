package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-05-13 20:46:39 EST
// -----( ON-HOST: 172.16.189.243

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
	// regular expressions to detect backwards-compatibility mode to support the previous ANTLR-based implementation of 
	// the evaluate function, which allowed use of the key words: null, true and false
	protected static final java.util.regex.Pattern nullPattern = java.util.regex.Pattern.compile("((=|==|!=|<>|>|>=|<|<=)\\s*null(\\s|$))|((^|\\s)null\\s*(=|==|!=|<>|>|>=|<|<=))");
	protected static final java.util.regex.Pattern truePattern = java.util.regex.Pattern.compile("((=|==|!=|<>|>|>=|<|<=)\\s*true(\\s|$))|((^|\\s)true\\s*(=|==|!=|<>|>|>=|<|<=))");
	protected static final java.util.regex.Pattern falsePattern = java.util.regex.Pattern.compile("((=|==|!=|<>|>|>=|<|<=)\\s*false(\\s|$))|((^|\\s)false\\s*(=|==|!=|<>|>|>=|<|<=))");
	
	// evaluates the given conditional statement against the given scope
	public static boolean evaluate(String condition, IData scope) throws ServiceException {
	  boolean result = true;
	
	  if (condition != null) {
	    java.util.regex.Matcher nullMatcher = nullPattern.matcher(condition);
	    java.util.regex.Matcher trueMatcher = truePattern.matcher(condition);
	    java.util.regex.Matcher falseMatcher = falsePattern.matcher(condition);
	
	    boolean nullFound = nullMatcher.find();
	    boolean trueFound = trueMatcher.find();
	    boolean falseFound = falseMatcher.find();
	
	    boolean backwardsCompatibilityRequired = nullFound || trueFound || falseFound;
	
	    if (scope == null) {
	      scope = IDataFactory.create();
	    } else {
	      if (backwardsCompatibilityRequired) scope = IDataUtil.clone(scope);
	    }
	    
	    if (backwardsCompatibilityRequired) {
	      IDataCursor cursor = scope.getCursor();
	      if (nullFound) IDataUtil.put(cursor, "null", null);
	      if (trueFound) IDataUtil.put(cursor, "true", "true");
	      if (falseFound) IDataUtil.put(cursor, "false", "false");
	      cursor.destroy();
	    }
	
	    try {
	      result = com.wm.lang.flow.ExpressionEvaluator.evalToBoolean(condition, scope);
	    } catch (com.wm.lang.flow.MalformedExpressionException ex) {
	      tundra.exception.raise(ex);   
	    } 
	  }
	
	  return result;
	}
	// --- <<IS-END-SHARED>> ---
}

