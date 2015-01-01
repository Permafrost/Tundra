package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-01-01 19:30:32 EST
// -----( ON-HOST: 172.16.167.128

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class bool

{
	// ---( internal utility methods )---

	final static bool _instance = new bool();

	static bool _newInstance() { return new bool(); }

	static bool _cast(Object o) { return (bool)o; }

	// ---( server methods )---




	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $boolean
		// [i] field:0:optional $value.true
		// [i] field:0:optional $value.false
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object bool = IDataUtil.get(cursor, "$boolean");
		  String trueValue = IDataUtil.getString(cursor, "$value.true");
		  String falseValue = IDataUtil.getString(cursor, "$value.false");
		
		  if (bool	 != null) IDataUtil.put(cursor, "$string", emit(bool, trueValue, falseValue));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void negate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(negate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $boolean
		// [o] field:0:required $boolean
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String s = IDataUtil.getString(cursor, "$boolean");
		  IDataUtil.put(cursor, "$boolean", negate(s));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void normalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(normalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $boolean
		// [i] field:0:optional $default
		// [o] field:0:required $boolean
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String s = IDataUtil.getString(cursor, "$boolean");
		  String d = IDataUtil.getString(cursor, "$default");
		  IDataUtil.put(cursor, "$boolean", normalize(s, d));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void parse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parse)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] object:0:optional $boolean
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String input = IDataUtil.getString(cursor, "$string");
		  if (input != null) IDataUtil.put(cursor, "$boolean", parse(input));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// normalizes a boolean string to either "true" or "false", substituting
	// the given default if the string is null
	public static String normalize(String s, String def) {
	  return normalize(s == null ? def : s);
	}
	
	// normalizes a boolean string to either "true" or "false"
	public static String normalize(String s) {
	  return emit(parse(s));
	}
	
	// parses a string that can contain "true" (ignoring case) or "1" to 
	// represent true, and "false" (ignoring case) or "0" to represent
	// false
	public static boolean parse(Object input) {
	  boolean result = false;
	
	  if (input != null) {
	    if (input instanceof java.lang.Boolean) {
	      result = ((java.lang.Boolean)input).booleanValue();
	    } else {
	      result = (parse(input.toString()));
	    }
	  }
	  return result;
	}
	
	// parses a string that can contain "true" (ignoring case) or "1" to 
	// represent true, and "false" (ignoring case) or "0" to represent
	// false
	public static boolean parse(String input) {
	  if (input != null) {
	    // handle xs:boolean strings which can contain 0 or 1
	    if (input.equals("0")) {
	      input = "false";
	    } else if (input.equals("1")) {
	      input = "true";
	    }
	  }
	  return Boolean.parseBoolean(input);
	}
	
	
	// parses the given input object as a boolean, then returns the boolean value
	// as the appropriate trueValue or falseValue string
	public static String emit(Object input, String trueValue, String falseValue) {
	  return emit(parse(input), trueValue, falseValue);
	}
	
	// returns a boolean value as the appropriate trueValue or falseValue string
	public static String emit(boolean b, String trueValue, String falseValue) {
	  return b ? (trueValue == null ? emit(b) : trueValue) : (falseValue == null ? emit(b) : falseValue);
	}
	
	// returns a boolean value in its canonical string form: "true" or "false"
	public static String emit(boolean b) {
	  return "" + b;
	}
	
	// returns the negated boolean value of the given string
	public static String negate(String s) {
	  return emit(negate(parse(s)));
	}
	
	// returns the negated value of the given boolean
	public static boolean negate(boolean b) {
	  return !b;
	}
	// --- <<IS-END-SHARED>> ---
}

