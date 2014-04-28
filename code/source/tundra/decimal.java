package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-04-28 10:46:20.547
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		// [i] field:0:required $decimal
		// [o] field:0:required $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String s = IDataUtil.getString(cursor, "$decimal");
		  IDataUtil.put(cursor, "$decimal", absolute(s));
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
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:required $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  IDataUtil.put(cursor, "$decimal", add(list, precision, rounding));
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
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
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
		// [i] field:0:required $decimal.x
		// [i] field:0:required $decimal.y
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:required $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String x = IDataUtil.getString(cursor, "$decimal.x");
		  String y = IDataUtil.getString(cursor, "$decimal.y");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  IDataUtil.put(cursor, "$decimal", divide(x, y, precision, rounding));
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
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
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
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
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
		// [i] field:1:required $decimals
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:required $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  IDataUtil.put(cursor, "$decimal", multiply(list, precision, rounding));
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
		// [i] field:0:required $decimal
		// [o] field:0:required $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String s = IDataUtil.getString(cursor, "$decimal");
		  IDataUtil.put(cursor, "$decimal", negate(s));
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
		// [i] field:0:required $decimal
		// [i] field:0:required $exponent
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:required $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String decimal = IDataUtil.getString(cursor, "$decimal");
		  String exponent = IDataUtil.getString(cursor, "$exponent");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  IDataUtil.put(cursor, "$decimal", power(decimal, exponent, precision, rounding));
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
		// [i] field:0:required $decimal
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:required $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String decimal = IDataUtil.getString(cursor, "$decimal");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  IDataUtil.put(cursor, "$decimal", round(decimal, precision, rounding));
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
		// [i] field:0:required $decimal.x
		// [i] field:0:optional $decimal.y
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:required $decimal
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String x = IDataUtil.getString(cursor, "$decimal.x");
		  String y = IDataUtil.getString(cursor, "$decimal.y");
		  String precision = IDataUtil.getString(cursor, "$precision");
		  String rounding = IDataUtil.getString(cursor, "$rounding");

		  IDataUtil.put(cursor, "$decimal", subtract(x, y, precision, rounding));
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
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String decimal = IDataUtil.getString(cursor, "$decimal");
		  boolean raise = tundra.bool.parse(IDataUtil.getString(cursor, "$raise?"));
		  IDataUtil.put(cursor, "$valid?", "" + validate(decimal, raise));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	public static String absolute(String s) {
	  return emit(parse(s).abs());
	}

	public static String add(String[] list, String precision, String rounding) {
	  java.math.BigDecimal result = java.math.BigDecimal.ZERO;

	  if (list != null) {
	    for (int i = 0; i < list.length; i++) {
	      result = result.add(parse(list[i]));
	    }
	  }

	  return emit(round(result, precision, rounding));
	}

	public static String divide(String x, String y, String precision, String rounding) {
	  return emit(divide(parse(x), parse(y), precision, rounding));
	}

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

	public static String multiply(String[] list, String precision, String rounding) {
	  java.math.BigDecimal result = java.math.BigDecimal.ONE;

	  if (list != null) {
	    for (int i = 0; i < list.length; i++) {
	      result = result.multiply(parse(list[i]));
	    }
	  }

	  return emit(round(result, precision, rounding));
	}

	public static String negate(String s) {
	  return emit(parse(s).negate());
	}

	public static String round(String decimal, String precision, String rounding) {
	  return emit(round(parse(decimal), precision, rounding));
	}

	public static java.math.BigDecimal round(java.math.BigDecimal decimal, String precision, String rounding) {
	  if (rounding == null) rounding = "HALF_UP";
	  if (precision != null) {
	    decimal = decimal.setScale(Integer.parseInt(precision), java.math.RoundingMode.valueOf(rounding));
	  }
	  return decimal;
	}

	public static String power(String decimal, String exponent, String precision, String rounding) {
	  return emit(round(parse(decimal).pow(Integer.parseInt(exponent)), precision, rounding));
	}

	public static String subtract(String x, String y, String precision, String rounding) {
	  return emit(round(parse(x).subtract(parse(y)), precision, rounding));
	}

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

	public static boolean validate(String decimal) {
	  boolean result = false;

	  try {
	    result = validate(decimal, false);
	  } catch(ServiceException ex) {
	  }

	  return result;
	}

	public static String emit(java.math.BigDecimal d) {
	  if (d == null) d = java.math.BigDecimal.ZERO;
	  return d.toString();
	}

	public static java.math.BigDecimal parse(String s) {
	  java.math.BigDecimal d = java.math.BigDecimal.ZERO;
	  if (s != null) d = new java.math.BigDecimal(s);
	  return d;
	}

	public static String minimum(String[] list, String precision, String rounding) {
	  if (list == null) return null;

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

	public static String maximum(String[] list, String precision, String rounding) {
	  if (list == null) return null;

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

	public static String average(String[] list, String precision, String rounding) {
	  if (list == null) return null;

	  java.math.BigDecimal total = java.math.BigDecimal.ZERO;

	  for (int i = 0; i < list.length; i++) {
	    total = total.add(parse(list[i]));
	  }

	  return emit(divide(total, new java.math.BigDecimal(list.length), precision, rounding));
	}
	// --- <<IS-END-SHARED>> ---
}

