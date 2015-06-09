package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-01 18:32:42 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.lang.BooleanHelper;
// --- <<IS-END-IMPORTS>> ---

public final class decimal

{
	// ---( internal utility methods )---

	final static decimal _instance = new decimal();

	static decimal _newInstance() { return new decimal(); }

	static decimal _cast(Object o) { return (decimal)o; }

	// ---( server methods )---




	public static final void absolute (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(absolute)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $decimal
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String s = IDataUtil.getString(cursor, "$decimal");
		  if (s != null) IDataUtil.put(cursor, "$decimal", absolute(s));
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
		// [i] field:1:optional $decimals
		// [i] field:0:optional $decimal
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {"HALF_UP","CEILING","DOWN","FLOOR","HALF_DOWN","HALF_EVEN","UNNECESSARY","UP"}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		  String decimal = IDataUtil.getString(cursor, "$decimal");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  if (list != null && list.length > 0) IDataUtil.put(cursor, "$decimal", add(list, decimal, precision, rounding));
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
		// [i] field:1:optional $decimals
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {"HALF_UP","CEILING","DOWN","FLOOR","HALF_DOWN","HALF_EVEN","UNNECESSARY","UP"}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  String result = average(list, precision, rounding);

		  if (result != null) IDataUtil.put(cursor, "$decimal", result);
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
		// [i] field:0:optional $decimal.x
		// [i] field:0:optional $decimal.y
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {"HALF_UP","CEILING","DOWN","FLOOR","HALF_DOWN","HALF_EVEN","UNNECESSARY","UP"}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String x = IDataUtil.getString(cursor, "$decimal.x");
		  String y = IDataUtil.getString(cursor, "$decimal.y");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  if (x != null && y != null) IDataUtil.put(cursor, "$decimal", divide(x, y, precision, rounding));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $object
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		  Object object = IDataUtil.get(cursor, "$object");
		  if (object != null) IDataUtil.put(cursor, "$string", emit(object));
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
		// [i] field:1:optional $decimals
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {"HALF_UP","CEILING","DOWN","FLOOR","HALF_DOWN","HALF_EVEN","UNNECESSARY","UP"}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  String result = maximum(list, precision, rounding);

		  if (result != null) IDataUtil.put(cursor, "$decimal", result);
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
		// [i] field:1:optional $decimals
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {"HALF_UP","CEILING","DOWN","FLOOR","HALF_DOWN","HALF_EVEN","UNNECESSARY","UP"}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  String result = minimum(list, precision, rounding);

		  if (result != null) IDataUtil.put(cursor, "$decimal", result);
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
		// [i] field:1:optional $decimals
		// [i] field:0:optional $decimal
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {"HALF_UP","CEILING","DOWN","FLOOR","HALF_DOWN","HALF_EVEN","UNNECESSARY","UP"}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		  String decimal = IDataUtil.getString(cursor, "$decimal");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  if (list != null && list.length > 0) IDataUtil.put(cursor, "$decimal", multiply(list, decimal, precision, rounding));
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
		// [i] field:0:optional $decimal
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String s = IDataUtil.getString(cursor, "$decimal");
		  if (s != null) IDataUtil.put(cursor, "$decimal", negate(s));
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
		// [i] field:0:optional $class {"java.math.BigDecimal","java.math.BigInteger","java.lang.Double","java.lang.Float","java.lang.Integer","java.lang.Long"}
		// [o] object:0:optional $object
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  String className = IDataUtil.getString(cursor, "$class");

		  if (string != null) {
		    java.math.BigDecimal object = parse(string);
		    if (className == null || className.equals("java.math.BigDecimal")) {
		      IDataUtil.put(cursor, "$object", object);
		    } else if (className.equals("java.math.BigInteger")) {
		      IDataUtil.put(cursor, "$object", object.toBigInteger());
		    } else if (className.equals("java.lang.Double")) {
		      IDataUtil.put(cursor, "$object", object.doubleValue());
		    } else if (className.equals("java.lang.Float")) {
		      IDataUtil.put(cursor, "$object", object.floatValue());
		    } else if (className.equals("java.lang.Integer")) {
		      IDataUtil.put(cursor, "$object", object.intValue());
		    } else if (className.equals("java.lang.Long")) {
		      IDataUtil.put(cursor, "$object", object.longValue());
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
		// [i] field:0:optional $decimal
		// [i] field:0:optional $exponent
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {"HALF_UP","CEILING","DOWN","FLOOR","HALF_DOWN","HALF_EVEN","UNNECESSARY","UP"}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String decimal = IDataUtil.getString(cursor, "$decimal");
		  String exponent = IDataUtil.getString(cursor, "$exponent");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  if (decimal != null && exponent != null) IDataUtil.put(cursor, "$decimal", power(decimal, exponent, precision, rounding));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void round (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(round)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $decimal
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {"HALF_UP","CEILING","DOWN","FLOOR","HALF_DOWN","HALF_EVEN","UNNECESSARY","UP"}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String decimal = IDataUtil.getString(cursor, "$decimal");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  if (decimal != null) IDataUtil.put(cursor, "$decimal", round(decimal, precision, rounding));
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
		// [i] field:0:optional $decimal.x
		// [i] field:0:optional $decimal.y
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {"HALF_UP","CEILING","DOWN","FLOOR","HALF_DOWN","HALF_EVEN","UNNECESSARY","UP"}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String x = IDataUtil.getString(cursor, "$decimal.x");
		  String y = IDataUtil.getString(cursor, "$decimal.y");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  if (x != null && y != null) IDataUtil.put(cursor, "$decimal", subtract(x, y, precision, rounding));
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
		// [i] field:0:optional $decimal
		// [i] field:0:optional $raise? {"false","true"}
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String decimal = IDataUtil.getString(cursor, "$decimal");
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));

		    IDataUtil.put(cursor, "$valid?", "" + validate(decimal, raise));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	// returns the absolute value of the given decimal string
	public static String absolute(String s) {
	  return emit(parse(s).abs());
	}

	// adds the given list of decimal strings together
	public static String add(String[] list, String decimal, String precision, String rounding) {
	  java.math.BigDecimal result = java.math.BigDecimal.ZERO;

	  if (list != null) {
	    for (int i = 0; i < list.length; i++) {
	      result = result.add(parse(list[i]));
	    }
	  }

	  if (decimal != null) {
	    result = result.add(parse(decimal));
	  }

	  return emit(round(result, precision, rounding));
	}

	// adds the given list of decimal strings together
	public static String add(String[] list, String precision, String rounding) {
	  return add(list, null, precision, rounding);
	}

	// divides x by y
	public static String divide(String x, String y, String precision, String rounding) {
	  return emit(divide(parse(x), parse(y), precision, rounding));
	}

	// divides x by y
	public static java.math.BigDecimal divide(java.math.BigDecimal x, java.math.BigDecimal y, String precision, String rounding) {
	  java.math.BigDecimal result = java.math.BigDecimal.ZERO;
	  if (rounding == null) rounding = "HALF_UP";
	  if (precision == null) {
	    result = x.divide(y, java.math.RoundingMode.valueOf(rounding));
	  } else {
	    result = x.divide(y, Integer.parseInt(precision), java.math.RoundingMode.valueOf(rounding));
	  }
	  return result;
	}

	// multiplies the given list of decimal strings
	public static String multiply(String[] list, String decimal, String precision, String rounding) {
	  java.math.BigDecimal result = java.math.BigDecimal.ONE;

	  if (list != null) {
	    for (int i = 0; i < list.length; i++) {
	      result = result.multiply(parse(list[i]));
	    }
	  }

	  if (decimal != null) {
	    result = result.multiply(parse(decimal));
	  }

	  return emit(round(result, precision, rounding));
	}

	// multiplies the given list of decimal strings
	public static String multiply(String[] list, String precision, String rounding) {
	  return multiply(list, null, precision, rounding);
	}

	// returns -1 * the given decimal string
	public static String negate(String s) {
	  return emit(parse(s).negate());
	}

	// rounds the given decimal string to the given precision with the given rounding algorithm
	public static String round(String decimal, String precision, String rounding) {
	  return emit(round(parse(decimal), precision, rounding));
	}

	// rounds the given decimal to the given precision with the given rounding algorithm
	public static java.math.BigDecimal round(java.math.BigDecimal decimal, String precision, String rounding) {
	  if (rounding == null) rounding = "HALF_UP";
	  if (precision != null) {
	    decimal = decimal.setScale(Integer.parseInt(precision), java.math.RoundingMode.valueOf(rounding));
	  }
	  return decimal;
	}

	// returns the given decimal string raised to the power of the given exponent
	public static String power(String decimal, String exponent, String precision, String rounding) {
	  return emit(round(parse(decimal).pow(Integer.parseInt(exponent)), precision, rounding));
	}

	// subtracts y from x
	public static String subtract(String x, String y, String precision, String rounding) {
	  return emit(round(parse(x).subtract(parse(y)), precision, rounding));
	}

	// returns false or throws an exception if the given string cannot be parsed as a decimal value
	public static boolean validate(String decimal, boolean raise) throws ServiceException {
	  boolean valid = false;
	  try {
	    if (decimal != null) {
	      parse(decimal);
	      valid = true;
	    }
	  } catch(NumberFormatException ex) {
	    if (raise) tundra.exception.raise(ex);
	  }
	  return valid;
	}

	// returns true if the given string can be parsed as a decimal value
	public static boolean validate(String decimal) {
	  boolean result = false;

	  try {
	    result = validate(decimal, false);
	  } catch(ServiceException ex) {
	  }

	  return result;
	}

	// returns a string representation of the given object
	public static String emit(Object object) {
	  if (object == null) return null;

	  java.math.BigDecimal decimal = null;
	  if (object instanceof java.lang.Float) {
	    decimal = java.math.BigDecimal.valueOf((java.lang.Float)object);
	  } else if (object instanceof java.lang.Double) {
	    decimal = java.math.BigDecimal.valueOf((java.lang.Double)object);
	  } else if (object instanceof java.lang.Integer) {
	    decimal = java.math.BigDecimal.valueOf((java.lang.Integer)object);
	  } else if (object instanceof java.lang.Long) {
	    decimal = java.math.BigDecimal.valueOf((java.lang.Long)object);
	  } else if (object instanceof java.math.BigDecimal) {
	    decimal = (java.math.BigDecimal)object;
	  } else {
	    throw new IllegalArgumentException("Object class " + object.getClass().getName() + " is not supported");
	  }

	  return emit(decimal);
	}

	// returns a string representation of the given decimal value
	public static String emit(java.math.BigDecimal d) {
	  if (d == null) d = java.math.BigDecimal.ZERO;
	  return d.toString();
	}

	// returns the decimal value represented by the given string
	public static java.math.BigDecimal parse(String s) {
	  java.math.BigDecimal d = java.math.BigDecimal.ZERO;
	  if (s != null) d = new java.math.BigDecimal(s);
	  return d;
	}

	// returns the minimum value from the list of decimal strings
	public static String minimum(String[] list, String precision, String rounding) {
	  if (list == null || list.length == 0) return null;

	  java.math.BigDecimal result = null;

	  for (int i = 0; i < list.length; i++) {
	    java.math.BigDecimal n = parse(list[i]);
	    if (result == null) {
	      result = n;
	    } else {
	      result = result.min(n);
	    }
	  }

	  return emit(round(result, precision, rounding));
	}

	// returns the maximum value from the list of decimal strings
	public static String maximum(String[] list, String precision, String rounding) {
	  if (list == null || list.length == 0) return null;

	  java.math.BigDecimal result = null;

	  for (int i = 0; i < list.length; i++) {
	    java.math.BigDecimal n = parse(list[i]);
	    if (result == null) {
	      result = n;
	    } else {
	      result = result.max(n);
	    }
	  }

	  return emit(round(result, precision, rounding));
	}

	// returns the average value from the list of decimal strings
	public static String average(String[] list, String precision, String rounding) {
	  if (list == null || list.length == 0) return null;

	  java.math.BigDecimal total = java.math.BigDecimal.ZERO;

	  for (int i = 0; i < list.length; i++) {
	    total = total.add(parse(list[i]));
	  }

	  return emit(divide(total, new java.math.BigDecimal(list.length), precision, rounding));
	}
	// --- <<IS-END-SHARED>> ---
}

