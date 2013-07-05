package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-06-08 15:27:48 EST
// -----( ON-HOST: 172.16.189.177

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



	public static final void decrement (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decrement)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $integer
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
		// [i] field:0:required $integer
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
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String i = IDataUtil.getString(cursor, "$integer");
		  IDataUtil.put(cursor, "$valid?", "" + validate(i));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static String absolute(String s) {
	  return emit(parse(s).abs());
	}
	
	public static String add(String ... s) {
	  java.math.BigInteger result = java.math.BigInteger.ZERO;
	  if (s != null) {
	    for (int i = 0; i < s.length; i++) {
	      result = result.add(parse(s[i]));
	    }
	  }
	  return emit(result);
	}
	
	public static String decrement(String s) {
	  return emit(parse(s).subtract(java.math.BigInteger.ONE));
	}
	
	public static String divide(String x, String y) {
	  return emit(parse(x).divide(parse(y)));
	}
	
	public static String increment(String s) {
	  return emit(parse(s).add(java.math.BigInteger.ONE));
	}
	
	public static String multiply(String ... s) {
	  java.math.BigInteger result = java.math.BigInteger.ONE;
	  if (s != null) {
	    for (int i = 0; i < s.length; i++) {
	      if (s[i] != null) result = result.multiply(parse(s[i]));
	    }
	  }
	  return emit(result);
	}
	
	public static String negate(String s) {
	  return emit(parse(s).negate());
	}
	
	public static String power(String i, String e) {
	  int exponent = Integer.parseInt(e);
	  return emit(parse(i).pow(exponent));
	}
	
	public static String remainder(String x, String y) {
	  return emit(parse(x).remainder(parse(y)));
	}
	
	public static String subtract(String x, String y) {
	  return emit(parse(x).subtract(parse(y)));
	}
	
	public static boolean validate(String s) {
	  boolean valid = false;
	  try {
	    if (s != null) {
	      parse(s);
	      valid = true;
	    }
	  } catch(NumberFormatException ex) { }
	  return valid;
	}
	
	public static String emit(java.math.BigInteger i) {
	  if (i == null) i = java.math.BigInteger.ZERO;
	  return i.toString();
	}
	
	public static java.math.BigInteger parse(String s) {
	  java.math.BigInteger i = java.math.BigInteger.ZERO;
	  if (s != null) i = new java.math.BigInteger(s);
	  return i;
	}
	// --- <<IS-END-SHARED>> ---
}
