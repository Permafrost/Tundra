package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-08-11 12:52:22 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigDecimal;
import java.math.RoundingMode;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataMap;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.math.BigDecimalHelper;
import permafrost.tundra.math.BigIntegerHelper;
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
		    String string = IDataUtil.getString(cursor, "$decimal");
		    if (string != null) IDataUtil.put(cursor, "$decimal", BigDecimalHelper.emit(BigDecimalHelper.absolute(BigDecimalHelper.parse(string))));
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		    String decimal = IDataUtil.getString(cursor, "$decimal");
		    String precision = IDataUtil.getString(cursor, "$precision");
		    String rounding = IDataUtil.getString(cursor, "$rounding");
		
		    // support $decimals and $decimal inputs for backwards-compatibility
		    if (operands == null && (list != null || decimal != null)) {
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$decimals", list);
		        if (decimal != null) map.put("$decimal", decimal);
		        operands = map;
		    }
		
		    String result = BigDecimalHelper.emit(BigDecimalHelper.round(BigDecimalHelper.add(BigDecimalHelper.normalize(IDataHelper.getLeafValues(operands))), precision, rounding));
		
		    if (result != null) IDataUtil.put(cursor, "$decimal", result);
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		    String precision = IDataUtil.getString(cursor, "$precision");
		    String rounding = IDataUtil.getString(cursor, "$rounding");
		
		    // support $decimals input for backwards-compatibility
		    if (operands == null && list != null) {
		        IDataMap map = new IDataMap();
		        map.put("$decimals", list);
		        operands = map;
		    }
		
		    String result = BigDecimalHelper.emit(BigDecimalHelper.average(precision, rounding, BigDecimalHelper.normalize(IDataHelper.getLeafValues(operands))));
		
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
		// [i] field:0:optional $dividend
		// [i] field:0:optional $divisor
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String dividend = IDataUtil.getString(cursor, "$dividend");
		    String divisor = IDataUtil.getString(cursor, "$divisor");
		
		    // support $decimal.x and $decimal.y inputs for backwards-compatibility
		    if (dividend == null && divisor == null) {
		        dividend = IDataUtil.getString(cursor, "$decimal.x");
		        divisor = IDataUtil.getString(cursor, "$decimal.y");
		    }
		
		    String precision = IDataUtil.getString(cursor, "$precision");
		    String rounding = IDataUtil.getString(cursor, "$rounding");
		
		    String result = BigDecimalHelper.emit(BigDecimalHelper.divide(BigDecimalHelper.parse(dividend), BigDecimalHelper.parse(divisor), precision, rounding));
		
		    if (result != null) IDataUtil.put(cursor, "$decimal", result);
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
		// [i] field:0:optional $pattern
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataUtil.get(cursor, "$object");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		
		    String result = BigDecimalHelper.emit(BigDecimalHelper.normalize(object), pattern);
		
		    if (result != null) IDataUtil.put(cursor, "$string", result);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void format (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(format)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $decimal
		// [i] field:0:optional $pattern.input {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:1:optional $patterns.input
		// [i] field:0:optional $pattern.output {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String string = IDataUtil.getString(cursor, "$decimal");
		    String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		    String[] inPatterns = IDataUtil.getStringArray(cursor, "$patterns.input");
		    String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		
		    String result = null;
		
		    if (inPatterns == null) {
		        result = BigDecimalHelper.format(string, inPattern, outPattern);
		    } else {
		        result = BigDecimalHelper.format(string, inPatterns, outPattern);
		    }
		
		    if (result != null) IDataUtil.put(cursor, "$decimal", result);
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		    String precision = IDataUtil.getString(cursor, "$precision");
		    String rounding = IDataUtil.getString(cursor, "$rounding");
		
		    // support $decimals input for backwards-compatibility
		    if (operands == null && list != null) {
		        IDataMap map = new IDataMap();
		        map.put("$decimals", list);
		        operands = map;
		    }
		
		    String result = BigDecimalHelper.emit(BigDecimalHelper.round(BigDecimalHelper.maximum(BigDecimalHelper.normalize(IDataHelper.getLeafValues(operands))), precision, rounding));
		
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		    String precision = IDataUtil.getString(cursor, "$precision");
		    String rounding = IDataUtil.getString(cursor, "$rounding");
		
		    // support $decimals input for backwards-compatibility
		    if (operands == null && list != null) {
		        IDataMap map = new IDataMap();
		        map.put("$decimals", list);
		        operands = map;
		    }
		
		    String result = BigDecimalHelper.emit(BigDecimalHelper.round(BigDecimalHelper.minimum(BigDecimalHelper.normalize(IDataHelper.getLeafValues(operands))), precision, rounding));
		
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String[] list = IDataUtil.getStringArray(cursor, "$decimals");
		    String decimal = IDataUtil.getString(cursor, "$decimal");
		    String precision = IDataUtil.getString(cursor, "$precision");
		    String rounding = IDataUtil.getString(cursor, "$rounding");
		
		    // support $decimals input for backwards-compatibility
		    if (operands == null && list != null) {
		        IDataMap map = new IDataMap();
		        map.put("$decimals", list);
		        operands = map;
		    }
		
		    String result = BigDecimalHelper.emit(BigDecimalHelper.round(BigDecimalHelper.multiply(BigDecimalHelper.normalize(IDataHelper.getLeafValues(operands))), precision, rounding));
		
		    if (result != null) IDataUtil.put(cursor, "$decimal", result);
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
		    String string = IDataUtil.getString(cursor, "$decimal");
		    if (string != null) IDataUtil.put(cursor, "$decimal", BigDecimalHelper.emit(BigDecimalHelper.negate(BigDecimalHelper.parse(string))));
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
		// [i] field:0:optional $pattern
		// [i] field:1:optional $patterns
		// [i] field:0:optional $class {&quot;java.math.BigDecimal&quot;,&quot;java.math.BigInteger&quot;,&quot;java.lang.Double&quot;,&quot;java.lang.Float&quot;,&quot;java.lang.Integer&quot;,&quot;java.lang.Long&quot;}
		// [o] object:0:optional $object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String string = IDataUtil.getString(cursor, "$string");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String[] patterns = IDataUtil.getStringArray(cursor, "$patterns");
		    String className = IDataUtil.getString(cursor, "$class");
		
		    BigDecimal decimal = null;
		
		    if (patterns == null) {
		        decimal = BigDecimalHelper.parse(string, pattern);
		    } else {
		        decimal = BigDecimalHelper.parse(string, patterns);
		    }
		
		    if (decimal != null) {
		        Object object = null;
		
		        if (className == null || className.equals("java.math.BigDecimal")) {
		            object = decimal;
		        } else if (className.equals("java.math.BigInteger")) {
		            object = decimal.toBigInteger();
		        } else if (className.equals("java.lang.Double")) {
		            object = decimal.doubleValue();
		        } else if (className.equals("java.lang.Float")) {
		            object = decimal.floatValue();
		        } else if (className.equals("java.lang.Integer")) {
		            object = decimal.intValue();
		        } else if (className.equals("java.lang.Long")) {
		            object = decimal.longValue();
		        }
		
		        if (object != null) IDataUtil.put(cursor, "$object", object);
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
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String decimal = IDataUtil.getString(cursor, "$decimal");
		    String exponent = IDataUtil.getString(cursor, "$exponent");
		    String precision = IDataUtil.getString(cursor, "$precision");
		    String rounding = IDataUtil.getString(cursor, "$rounding");
		
		    String result = BigDecimalHelper.emit(BigDecimalHelper.round(BigDecimalHelper.power(BigDecimalHelper.parse(decimal), BigIntegerHelper.parse(exponent)), precision, rounding));
		
		    if (result != null) IDataUtil.put(cursor, "$decimal", result);
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
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String decimal = IDataUtil.getString(cursor, "$decimal");
		    String precision = IDataUtil.getString(cursor, "$precision");
		    String rounding = IDataUtil.getString(cursor, "$rounding");
		
		    String result = BigDecimalHelper.emit(BigDecimalHelper.round(BigDecimalHelper.parse(decimal), precision, rounding));
		
		    if (result != null) IDataUtil.put(cursor, "$decimal", result);
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
		// [i] field:0:optional $minuend
		// [i] field:0:optional $subtrahend
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $decimal
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String minuend = IDataUtil.getString(cursor, "$minuend");
		    String subtrahend = IDataUtil.getString(cursor, "$subtrahend");
		
		    // support $decimal.x and $decimal.y inputs for backwards-compatibility
		    if (minuend == null && subtrahend == null) {
		        minuend = IDataUtil.getString(cursor, "$decimal.x");
		        subtrahend = IDataUtil.getString(cursor, "$decimal.y");
		    }
		
		    String precision = IDataUtil.getString(cursor, "$precision");
		    String rounding = IDataUtil.getString(cursor, "$rounding");
		
		    String result = BigDecimalHelper.emit(BigDecimalHelper.round(BigDecimalHelper.subtract(BigDecimalHelper.parse(minuend), BigDecimalHelper.parse(subtrahend)), precision, rounding));
		
		    if (result != null) IDataUtil.put(cursor, "$decimal", result);
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
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));
		
		    IDataUtil.put(cursor, "$valid?", BooleanHelper.emit(BigDecimalHelper.validate(decimal, raise)));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

