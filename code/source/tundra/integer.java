package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-08-02 13:07:53 EST
// -----( ON-HOST: 172.16.189.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class integer

{
	// ---( internal utility methods )---

	final static integer _instance = new integer();

	static integer _newInstance() { return new integer(); }

	static integer _cast(Object o) { return (integer)o; }

	// ---( server methods )---




	public static final void absolute (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(absolute)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $integer
		// [o] field:0:required $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String i = IDataUtil.getString(cursor, "$integer");
		  IDataUtil.put(cursor, "$integer", absolute(i));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void add (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(add)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $integers
		// [o] field:0:required $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$integers");
		  IDataUtil.put(cursor, "$integer", add(list));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void average (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(average)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $integers
		// [o] field:0:optional $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$integers");
		  String i = average(list);
		  if (i != null) IDataUtil.put(cursor, "$integer", i);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void decrement (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decrement)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $integer
		// [o] field:0:required $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String i = IDataUtil.getString(cursor, "$integer");
		  IDataUtil.put(cursor, "$integer", decrement(i));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void divide (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(divide)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $integer.x
		// [i] field:0:required $integer.y
		// [o] field:0:required $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String x = IDataUtil.getString(cursor, "$integer.x");
		  String y = IDataUtil.getString(cursor, "$integer.y");
		
		  IDataUtil.put(cursor, "$integer", divide(x, y));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void increment (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(increment)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $integer
		// [o] field:0:required $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String i = IDataUtil.getString(cursor, "$integer");
		  IDataUtil.put(cursor, "$integer", increment(i));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void maximum (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(maximum)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $integers
		// [o] field:0:optional $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$integers");
		  String i = maximum(list);
		  if (i != null) IDataUtil.put(cursor, "$integer", i);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void minimum (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(minimum)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $integers
		// [o] field:0:optional $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$integers");
		  String i = minimum(list);
		  if (i != null) IDataUtil.put(cursor, "$integer", i);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void multiply (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(multiply)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:required $integers
		// [o] field:0:required $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$integers");
		  IDataUtil.put(cursor, "$integer", multiply(list));
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
		// [i] field:0:required $integer
		// [o] field:0:required $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String i = IDataUtil.getString(cursor, "$integer");
		  IDataUtil.put(cursor, "$integer", negate(i));
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
		// [i] field:0:optional $class {&quot;java.math.BigInteger&quot;,&quot;java.lang.Integer&quot;,&quot;java.lang.Long&quot;}
		// [i] field:0:optional $radix
		// [o] object:0:optional $object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String s = IDataUtil.getString(cursor, "$string");
		  String className = IDataUtil.getString(cursor, "$class");
		  String radix = IDataUtil.getString(cursor, "$radix");
		
		  if (s != null) {
		    java.math.BigInteger integer = parse(s, radix);
		    if (className == null || className.equals("java.math.BigInteger")) {
		      IDataUtil.put(cursor, "$object", integer);
		    } else if (className.equals("java.lang.Integer")) {
		      IDataUtil.put(cursor, "$object", integer.intValue());
		    } else if (className.equals("java.lang.Long")) {
		      IDataUtil.put(cursor, "$object", integer.longValue());
		    }
		  }
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void power (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(power)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $integer
		// [i] field:0:required $exponent
		// [o] field:0:required $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String i = IDataUtil.getString(cursor, "$integer");
		  String e = IDataUtil.getString(cursor, "$exponent");
		
		  IDataUtil.put(cursor, "$integer", power(i, e));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void remainder (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remainder)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $integer.x
		// [i] field:0:required $integer.y
		// [o] field:0:required $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String x = IDataUtil.getString(cursor, "$integer.x");
		  String y = IDataUtil.getString(cursor, "$integer.y");
		
		  IDataUtil.put(cursor, "$integer", remainder(x, y));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void subtract (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(subtract)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $integer.x
		// [i] field:0:optional $integer.y
		// [o] field:0:required $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String x = IDataUtil.getString(cursor, "$integer.x");
		  String y = IDataUtil.getString(cursor, "$integer.y");
		
		  IDataUtil.put(cursor, "$integer", subtract(x, y));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void validate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $integer
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String i = IDataUtil.getString(cursor, "$integer");
		  boolean raise = tundra.bool.parse(IDataUtil.getString(cursor, "$raise?"));
		  IDataUtil.put(cursor, "$valid?", "" + validate(i, raise));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns the absolute value of the given integer string
	public static String absolute(String s) {
	  return emit(parse(s).abs());
	}
	
	// returns the result of adding the given list of integer strings
	public static String add(String ... s) {
	  java.math.BigInteger result = java.math.BigInteger.ZERO;
	  if (s != null) {
	    for (int i = 0; i < s.length; i++) {
	      result = result.add(parse(s[i]));
	    }
	  }
	  return emit(result);
	}
	
	// subtracts 1 from the given integer string
	public static String decrement(String s) {
	  return emit(parse(s).subtract(java.math.BigInteger.ONE));
	}
	
	// returns the result of dividing x by y
	public static String divide(String x, String y) {
	  return emit(parse(x).divide(parse(y)));
	}
	
	// adds 1 to the given integer string
	public static String increment(String s) {
	  return emit(parse(s).add(java.math.BigInteger.ONE));
	}
	
	// returns the result of multiplying the given list of integer strings
	public static String multiply(String ... s) {
	  java.math.BigInteger result = java.math.BigInteger.ONE;
	  if (s != null) {
	    for (int i = 0; i < s.length; i++) {
	      if (s[i] != null) result = result.multiply(parse(s[i]));
	    }
	  }
	  return emit(result);
	}
	
	// returns the s * -1
	public static String negate(String s) {
	  return emit(parse(s).negate());
	}
	
	// returns the value of i raised to the power of e
	public static String power(String i, String e) {
	  int exponent = Integer.parseInt(e);
	  return emit(parse(i).pow(exponent));
	}
	
	// returns the modulo remainder from dividing x by y
	public static String remainder(String x, String y) {
	  return emit(parse(x).remainder(parse(y)));
	}
	
	// returns the resulting value of subtracting y from x
	public static String subtract(String x, String y) {
	  return emit(parse(x).subtract(parse(y)));
	}
	
	// returns false or throws an exception if the given string cannot be parsed as an integer
	public static boolean validate(String s, boolean raise) throws ServiceException {
	  boolean valid = false;
	  try {
	    if (s != null) {
	      parse(s);
	      valid = true;
	    }
	  } catch(NumberFormatException ex) { 
	    if (raise) tundra.exception.raise(ex);
	  }
	  return valid;
	}
	
	// returns true if the given string can be parsed as an integer
	public static boolean validate(String s) throws ServiceException {
	  return validate(s, false);
	}
	
	// returns a string representation of the given BigInteger object
	public static String emit(java.math.BigInteger i) {
	  return emit(i, null);
	}
	
	// returns a string representation of the given BigInteger object
	public static String emit(java.math.BigInteger i, String radix) {
	  if (radix == null) radix = "10";
	  return emit(i, Integer.parseInt(radix));
	}
	
	// returns a string representation of the given BigInteger object
	public static String emit(java.math.BigInteger i, int radix) {
	  if (i == null) i = java.math.BigInteger.ZERO;
	  return i.toString(radix);
	}
	
	// returns a BigInteger object given an integer string
	public static java.math.BigInteger parse(String s) {
	  return parse(s, null);
	}
	
	// returns a BigInteger object given an integer string
	public static java.math.BigInteger parse(String s, String radix) {
	  if (radix == null) radix = "10";
	  return parse(s, Integer.parseInt(radix));
	}
	
	// returns a BigInteger object given an integer string
	public static java.math.BigInteger parse(String s, int radix) {
	  java.math.BigInteger i = java.math.BigInteger.ZERO;
	  if (s != null) i = new java.math.BigInteger(s, radix);
	  return i;
	}
	
	// returns the minimum value from the given list of integer strings
	public static String minimum(String ... s) {
	  if (s == null) return null;
	
	  java.math.BigInteger result = null;
	  for (int i = 0; i < s.length; i++) {
	    java.math.BigInteger n = parse(s[i]);
	    if (result == null) {
	      result = n;
	    } else {
	      result = result.min(n);
	    }
	  }
	  
	  return emit(result);
	}
	
	// returns the maximum value from the given list of integer strings
	public static String maximum(String ... s) {
	  if (s == null) return null;
	
	  java.math.BigInteger result = null;
	  for (int i = 0; i < s.length; i++) {
	    java.math.BigInteger n = parse(s[i]);
	    if (result == null) {
	      result = n;
	    } else {
	      result = result.max(n);
	    }
	  }
	  
	  return emit(result);
	}
	
	// returns the average value for the given list of integer strings
	public static String average(String ... s) {
	  if (s == null) return null;
	
	  java.math.BigInteger total = java.math.BigInteger.ZERO;
	  for (int i = 0; i < s.length; i++) {
	    total = total.add(parse(s[i]));
	  }
	  
	  return emit(total.divide(parse("" + s.length)));
	}
	// --- <<IS-END-SHARED>> ---
}

