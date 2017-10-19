package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-10-19T11:11:00.389
// -----( ON-HOST: EBZDEVWAP37.ebiztest.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataMap;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.math.BigDecimalHelper;
import permafrost.tundra.math.BigIntegerHelper;
import permafrost.tundra.math.DoubleHelper;
import permafrost.tundra.math.NormalDistributionEstimator;
import permafrost.tundra.math.PrecisionHelper;
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
		    BigDecimal decimal = IDataHelper.get(cursor, "$decimal", BigDecimal.class);
		    BigDecimal result = BigDecimalHelper.absolute(decimal);
		
		    IDataHelper.put(cursor, "$decimal", result, String.class, false);
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
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    // support $decimals and $decimal inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null) {
		        String[] list = IDataHelper.get(cursor, "$decimals", String[].class);
		        String decimal = IDataHelper.get(cursor, "$decimal", String.class);
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$decimals", list);
		        if (decimal != null) map.put("$decimal", decimal);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigDecimal[] decimals = BigDecimalHelper.normalize(IDataHelper.getLeaves(operands));
		    BigDecimal result = BigDecimalHelper.round(BigDecimalHelper.add(decimals), precision, rounding);
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$decimal", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$result", result, String.class, false);
		    }
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
		// [o] field:0:optional $average
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    // support $decimals input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null) {
		        String[] list = IDataHelper.get(cursor, "$decimals", String[].class);
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$decimals", list);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigDecimal result = BigDecimalHelper.average(precision, rounding, BigDecimalHelper.normalize(IDataHelper.getLeaves(operands)));
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$decimal", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$average", result, String.class, false);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void deviate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(deviate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $operands
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $sample.deviation
		// [o] field:0:optional $sample.variance
		// [o] field:0:optional $sample.mean
		// [o] field:0:optional $sample.count
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    Object[] leaves = IDataHelper.getLeaves(operands);
		
		    if (leaves != null && leaves.length > 0) {
		        double[] samples = DoubleHelper.normalize(BigDecimalHelper.normalize(leaves));
		
		        NormalDistributionEstimator estimator = new NormalDistributionEstimator(samples);
		
		        IDataHelper.put(cursor, "$sample.deviation", BigDecimalHelper.round(new BigDecimal(estimator.getStandardDeviation()), precision, rounding), String.class, false);
		        IDataHelper.put(cursor, "$sample.variance", BigDecimalHelper.round(new BigDecimal(estimator.getVariance()), precision, rounding), String.class, false);
		        IDataHelper.put(cursor, "$sample.mean", BigDecimalHelper.round(new BigDecimal(estimator.getMean()), precision, rounding), String.class, false);
		        IDataHelper.put(cursor, "$sample.count", estimator.getCount(), String.class, false);
		    }
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
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    BigDecimal dividend = IDataHelper.get(cursor, "$dividend", BigDecimal.class);
		    BigDecimal divisor = IDataHelper.get(cursor, "$divisor", BigDecimal.class);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    // support $decimal.x and $decimal.y inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (dividend == null && divisor == null) {
		        dividend = IDataHelper.get(cursor, "$decimal.x", BigDecimal.class);
		        divisor = IDataHelper.get(cursor, "$decimal.y", BigDecimal.class);
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigDecimal result = BigDecimalHelper.divide(dividend, divisor, precision, rounding);
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$decimal", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$result", result, String.class, false);
		    }
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
		    Object object = IDataHelper.get(cursor, "$object");
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		
		    IDataHelper.put(cursor, "$string", BigDecimalHelper.emit(BigDecimalHelper.normalize(object), pattern), false);
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
		// [i] record:0:optional $number.input
		// [i] - field:0:optional value
		// [i] - field:1:optional value.list
		// [i] - field:2:optional value.table
		// [i] field:0:optional $pattern.input
		// [i] field:1:optional $patterns.input
		// [i] field:0:optional $pattern.output
		// [o] record:0:optional $number.output
		// [o] - field:0:optional value
		// [o] - field:1:optional value.list
		// [o] - field:2:optional value.table
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$number.input", IData.class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String[] inPatterns = IDataHelper.get(cursor, "$patterns.input", String[].class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		
		    if (document != null) {
		        if (inPatterns == null) {
		            document = BigDecimalHelper.format(document, inPattern, outPattern);
		        } else {
		            document = BigDecimalHelper.format(document, inPatterns, outPattern);
		        }
		        IDataHelper.put(cursor, "$number.output", document);
		    } else {
		        String string = IDataHelper.get(cursor, "$decimal", String.class);
		        if (string != null) {
		            if (inPatterns == null) {
		                string = BigDecimalHelper.format(string, inPattern, outPattern);
		            } else {
		                string = BigDecimalHelper.format(string, inPatterns, outPattern);
		            }
		            IDataHelper.put(cursor, "$decimal", string);
		        }
		    }
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
		// [o] field:0:optional $maximum
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    // support $decimals input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null) {
		        String[] list = IDataHelper.get(cursor, "$decimals", String[].class);
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$decimals", list);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigDecimal[] decimals = BigDecimalHelper.normalize(IDataHelper.getLeaves(operands));
		    BigDecimal result = BigDecimalHelper.round(BigDecimalHelper.maximum(decimals), precision, rounding);
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$decimal", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$maximum", result, String.class, false);
		    }
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
		// [o] field:0:optional $minimum
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    // support $decimals input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null) {
		        String[] list = IDataHelper.get(cursor, "$decimals", String[].class);
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$decimals", list);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigDecimal[] decimals = BigDecimalHelper.normalize(IDataHelper.getLeaves(operands));
		    BigDecimal result = BigDecimalHelper.round(BigDecimalHelper.minimum(decimals), precision, rounding);
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$decimal", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$minimum", result, String.class, false);
		    }
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
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    // support $decimals and $decimal input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null) {
		        String[] list = IDataHelper.get(cursor, "$decimals", String[].class);
		        String decimal = IDataHelper.get(cursor, "$decimal", String.class);
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$decimals", list);
		        if (decimal != null) map.put("$decimal", decimal);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigDecimal[] decimals = BigDecimalHelper.normalize(IDataHelper.getLeaves(operands));
		    BigDecimal result = BigDecimalHelper.round(BigDecimalHelper.multiply(decimals), precision, rounding);
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$decimal", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$result", result, String.class, false);
		    }
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
		    BigDecimal decimal = IDataHelper.get(cursor, "$decimal", BigDecimal.class);
		    BigDecimal result = BigDecimalHelper.negate(decimal);
		    
		    IDataHelper.put(cursor, "$decimal", result, String.class, false);
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
		// [i] field:0:optional $class {&quot;java.math.BigDecimal&quot;,&quot;java.math.BigInteger&quot;,&quot;java.lang.Double&quot;,&quot;java.lang.Float&quot;,&quot;java.lang.Long&quot;,&quot;java.lang.Integer&quot;,&quot;java.lang.Short&quot;,&quot;java.lang.Byte&quot;}
		// [o] object:0:optional $object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String[] patterns = IDataHelper.get(cursor, "$patterns", String[].class);
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		
		    BigDecimal decimal = null;
		
		    if (patterns == null) {
		        decimal = BigDecimalHelper.parse(string, pattern);
		    } else {
		        decimal = BigDecimalHelper.parse(string, patterns);
		    }
		
		    if (decimal != null) {
		        Object object = null;
		
		        if (klass == null || klass.equals(BigDecimal.class)) {
		            object = decimal;
		        } else if (klass.equals(BigInteger.class)) {
		            object = decimal.toBigInteger();
		        } else if (klass.equals(Double.class)) {
		            object = decimal.doubleValue();
		        } else if (klass.equals(Float.class)) {
		            object = decimal.floatValue();
		        } else if (klass.equals(Long.class)) {
		            object = decimal.longValue();
		        } else if (klass.equals(Integer.class)) {
		            object = decimal.intValue();
		        } else if (klass.equals(Short.class)) {
		            object = decimal.shortValue();
		        } else if (klass.equals(Byte.class)) {
		            object = decimal.byteValue();
		        }
		
		        IDataHelper.put(cursor, "$object", object, false);
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
		// [i] field:0:optional $base
		// [i] field:0:optional $exponent
		// [i] field:0:optional $precision
		// [i] field:0:optional $rounding {&quot;HALF_UP&quot;,&quot;CEILING&quot;,&quot;DOWN&quot;,&quot;FLOOR&quot;,&quot;HALF_DOWN&quot;,&quot;HALF_EVEN&quot;,&quot;UNNECESSARY&quot;,&quot;UP&quot;}
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    BigDecimal base = IDataHelper.get(cursor, "$base", BigDecimal.class);
		    int exponent = IDataHelper.getOrDefault(cursor, "$exponent", Integer.class, 1);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    // support $decimal input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (base == null) {
		        base = IDataHelper.get(cursor, "$decimal", BigDecimal.class);
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigDecimal result = BigDecimalHelper.round(BigDecimalHelper.power(base, exponent), precision, rounding);
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$decimal", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$result", result, String.class, false);
		    }
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
		    BigDecimal decimal = IDataHelper.get(cursor, "$decimal", BigDecimal.class);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    BigDecimal result = BigDecimalHelper.round(decimal, precision, rounding);
		
		    IDataHelper.put(cursor, "$decimal", result, String.class, false);
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
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    BigDecimal minuend = IDataHelper.get(cursor, "$minuend", BigDecimal.class);
		    BigDecimal subtrahend = IDataHelper.get(cursor, "$subtrahend", BigDecimal.class);
		    int precision = IDataHelper.getOrDefault(cursor, "$precision", Integer.class, -1);
		    RoundingMode rounding = IDataHelper.get(cursor, "$rounding", RoundingMode.class);
		
		    // support $decimal.x and $decimal.y inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (minuend == null && subtrahend == null) {
		        minuend = IDataHelper.get(cursor, "$decimal.x", BigDecimal.class);
		        subtrahend = IDataHelper.get(cursor, "$decimal.y", BigDecimal.class);
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigDecimal result = BigDecimalHelper.round(BigDecimalHelper.subtract(minuend, subtrahend), precision, rounding);
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$decimal", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$result", result, String.class, false);
		    }
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
		    String decimal = IDataHelper.get(cursor, "$decimal", String.class);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$valid?", BigDecimalHelper.validate(decimal, raise), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

