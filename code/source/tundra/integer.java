package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-10-19T11:14:32.340
// -----( ON-HOST: EBZDEVWAP37.ebiztest.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigDecimal;
import java.math.BigInteger;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataMap;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.math.BigIntegerHelper;
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
		// [i] field:0:optional $integer
		// [o] field:0:optional $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String string = IDataHelper.get(cursor, "$integer", String.class);
		    BigInteger result = BigIntegerHelper.absolute(BigIntegerHelper.parse(string));
		    IDataHelper.put(cursor, "$integer", result, String.class, false);
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
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		
		    // support $integers and $integer inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null) {
		        String[] list = IDataHelper.get(cursor, "$integers", String[].class);
		        String integer = IDataHelper.get(cursor, "$integer", String.class);
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$integers", list);
		        if (integer != null) map.put("$integer", integer);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigInteger result = BigIntegerHelper.add(BigIntegerHelper.normalize(IDataHelper.getLeaves(operands)));
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$integer", result, String.class, false);
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
		// [o] field:0:optional $average
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		
		    // support $integers input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null) {
		        String[] list = IDataHelper.get(cursor, "$integers", String[].class);
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$integers", list);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigInteger result = BigIntegerHelper.average(BigIntegerHelper.normalize(IDataHelper.getLeaves(operands)));
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$integer", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$average", result, String.class, false);
		    }
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
		    BigInteger integer = IDataHelper.get(cursor, "$integer", BigInteger.class);
		    IDataHelper.put(cursor, "$integer", BigIntegerHelper.decrement(integer), String.class);
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
		// [o] field:0:optional $result
		// [o] field:0:optional $remainder
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    BigInteger dividend = IDataHelper.get(cursor, "$dividend", BigInteger.class);
		    BigInteger divisor = IDataHelper.get(cursor, "$divisor", BigInteger.class);
		
		    // support $integer.x and $integer.y inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (dividend == null && divisor == null) {
		        dividend = IDataHelper.get(cursor, "$integer.x", BigInteger.class);
		        divisor = IDataHelper.get(cursor, "$integer.y", BigInteger.class);
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigInteger[] result = BigIntegerHelper.divideAndRemainder(dividend, divisor);
		
		    if (result != null && result.length == 2) {
		        if (backwardsCompatiblityRequired) {
		            IDataHelper.put(cursor, "$integer", result[0], String.class);
		        } else {
		            IDataHelper.put(cursor, "$result", result[0], String.class);
		            IDataHelper.put(cursor, "$remainder", result[1], String.class);
		        }
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
		// [i] field:0:optional $radix
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$object");
		    int radix = IDataHelper.getOrDefault(cursor, "$radix", Integer.class, BigIntegerHelper.DEFAULT_RADIX);
		
		    if (object != null) {
		        IDataHelper.put(cursor, "$string", BigIntegerHelper.emit(BigIntegerHelper.normalize(object), radix));
		    }
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
		tundra.decimal.format(pipeline);
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
		    BigInteger integer = IDataHelper.get(cursor, "$integer", BigInteger.class);
		    IDataHelper.put(cursor, "$integer", BigIntegerHelper.increment(integer), String.class);
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
		// [o] field:0:optional $maximum
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		
		    // support $integers input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null) {
		        String[] list = IDataHelper.get(cursor, "$integers", String[].class);
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$integers", list);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigInteger result = BigIntegerHelper.maximum(BigIntegerHelper.normalize(IDataHelper.getLeaves(operands)));
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$integer", result, String.class, false);
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
		// [o] field:0:optional $minimum
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		
		    // support $integers input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null) {
		        String[] list = IDataHelper.get(cursor, "$integers", String[].class);
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$integers", list);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigInteger result = BigIntegerHelper.minimum(BigIntegerHelper.normalize(IDataHelper.getLeaves(operands)));
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$integer", result, String.class, false);
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
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		
		    // support $integers and $integer input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null) {
		        String[] list = IDataHelper.get(cursor, "$integers", String[].class);
		        String integer = IDataHelper.get(cursor, "$integer", String.class);
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$integers", list);
		        if (integer != null) map.put("$integer", integer);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigInteger result = BigIntegerHelper.multiply(BigIntegerHelper.normalize(IDataHelper.getLeaves(operands)));
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$integer", result, String.class, false);
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
		// [i] field:0:optional $integer
		// [o] field:0:optional $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    BigInteger integer = IDataHelper.get(cursor, "$integer", BigInteger.class);
		    IDataHelper.put(cursor, "$integer", BigIntegerHelper.negate(integer), String.class, false);
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
		// [i] field:0:optional $class {&quot;java.math.BigInteger&quot;,&quot;java.math.BigDecimal&quot;,&quot;java.lang.Double&quot;,&quot;java.lang.Float&quot;,&quot;java.lang.Long&quot;,&quot;java.lang.Integer&quot;,&quot;java.lang.Short&quot;,&quot;java.lang.Byte&quot;}
		// [i] field:0:optional $radix
		// [o] object:0:optional $object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    int radix = IDataHelper.getOrDefault(cursor, "$radix", Integer.class, BigIntegerHelper.DEFAULT_RADIX);
		
		    BigInteger integer = BigIntegerHelper.parse(string, radix);
		
		    if (integer != null) {
		        if (klass == null || klass.equals(BigInteger.class)) {
		            IDataHelper.put(cursor, "$object", integer);
		        } else if (klass.equals(BigDecimal.class)) {
		            IDataHelper.put(cursor, "$object", new BigDecimal(integer));
		        } else if (klass.equals(Double.class)) {
		            IDataHelper.put(cursor, "$object", new BigDecimal(integer).doubleValue());
		        } else if (klass.equals(Float.class)) {
		            IDataHelper.put(cursor, "$object", new BigDecimal(integer).floatValue());
		        } else if (klass.equals(Long.class)) {
		            IDataHelper.put(cursor, "$object", integer.longValue());
		        } else if (klass.equals(Integer.class)) {
		            IDataHelper.put(cursor, "$object", integer.intValue());
		        } else if (klass.equals(Short.class)) {
		            IDataHelper.put(cursor, "$object", integer.shortValue());
		        } else if (klass.equals(Byte.class)) {
		            IDataHelper.put(cursor, "$object", integer.byteValue());
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
		// [i] field:0:optional $base
		// [i] field:0:optional $exponent
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    BigInteger base = IDataHelper.get(cursor, "$base", BigInteger.class);
		    int exponent = IDataHelper.getOrDefault(cursor, "$exponent", Integer.class, 1);
		
		    // support $integer input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (base == null) {
		        base = IDataHelper.get(cursor, "$integer", BigInteger.class);
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigInteger result = BigIntegerHelper.power(base, exponent);
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$integer", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$result", result, String.class, false);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void rebase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(rebase)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:optional $radix.input
		// [i] field:0:optional $radix.output
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    int inputRadix = IDataHelper.getOrDefault(cursor, "$radix.input", Integer.class, BigIntegerHelper.DEFAULT_RADIX);
		
		    int outputRadix = IDataHelper.getOrDefault(cursor, "$radix.output", Integer.class, BigIntegerHelper.DEFAULT_RADIX);
		
		    IDataHelper.put(cursor, "$string", BigIntegerHelper.rebase(string, inputRadix, outputRadix), false);
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
		// [i] field:0:optional $dividend
		// [i] field:0:optional $divisor
		// [o] field:0:optional $remainder
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    BigInteger dividend = IDataHelper.get(cursor, "$dividend", BigInteger.class);
		    BigInteger divisor = IDataHelper.get(cursor, "$divisor", BigInteger.class);
		
		    // support $integer.x and $integer.y inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (dividend == null && divisor == null) {
		        dividend = IDataHelper.get(cursor, "$integer.x", BigInteger.class);
		        divisor = IDataHelper.get(cursor, "$integer.y", BigInteger.class);
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigInteger result = BigIntegerHelper.remainder(dividend, divisor);
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$integer", result, String.class, false);
		    } else {
		        IDataHelper.put(cursor, "$remainder", result, String.class, false);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void shift (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(shift)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $integer
		// [i] field:0:optional $distance
		// [o] field:0:optional $integer
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    BigInteger integer = IDataHelper.get(cursor, "$integer", BigInteger.class);
		    int distance = IDataHelper.getOrDefault(cursor, "$distance", Integer.class, 0);
		
		    IDataHelper.put(cursor, "$integer", BigIntegerHelper.shift(integer, distance), String.class, false);
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
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    BigInteger minuend = IDataHelper.get(cursor, "$minuend", BigInteger.class);
		    BigInteger subtrahend = IDataHelper.get(cursor, "$subtrahend", BigInteger.class);
		
		    // support $integer.x and $integer.y inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (minuend == null && subtrahend == null) {
		        minuend = IDataHelper.get(cursor, "$integer.x", BigInteger.class);
		        subtrahend = IDataHelper.get(cursor, "$integer.y", BigInteger.class);
		        backwardsCompatiblityRequired = true;
		    }
		
		    BigInteger result = BigIntegerHelper.subtract(minuend, subtrahend);
		
		    if (backwardsCompatiblityRequired) {
		        IDataHelper.put(cursor, "$integer", result, String.class, false);
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
		// [i] field:0:optional $integer
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $valid? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String integer = IDataHelper.get(cursor, "$integer", String.class);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$valid?", BigIntegerHelper.validate(integer, raise), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

