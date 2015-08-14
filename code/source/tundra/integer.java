package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-08-14 14:30:48 EST
// -----( ON-HOST: 192.168.66.129

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
		    String string = IDataUtil.getString(cursor, "$integer");
		    String result = BigIntegerHelper.emit(BigIntegerHelper.absolute(BigIntegerHelper.parse(string)));
		    if (result != null) IDataUtil.put(cursor, "$integer", result);
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
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String[] list = IDataUtil.getStringArray(cursor, "$integers");
		    String integer = IDataUtil.getString(cursor, "$integer");
		
		    // support $integers and $integer inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null && (list != null || integer != null)) {
		        IDataMap map = new IDataMap();
		        if (list != null) map.put("$integers", list);
		        if (integer != null) map.put("$integer", integer);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    String result = BigIntegerHelper.emit(BigIntegerHelper.add(BigIntegerHelper.normalize(IDataHelper.getLeafValues(operands))));
		
		    if (result != null) {
		        if (backwardsCompatiblityRequired) {
		            IDataUtil.put(cursor, "$integer", result);
		        } else {
		            IDataUtil.put(cursor, "$result", result);
		        }
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
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String[] list = IDataUtil.getStringArray(cursor, "$integers");
		
		    // support $integers input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null && list != null) {
		        IDataMap map = new IDataMap();
		        map.put("$integers", list);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    String result = BigIntegerHelper.emit(BigIntegerHelper.average(BigIntegerHelper.normalize(IDataHelper.getLeafValues(operands))));
		
		    if (result != null) {
		        if (backwardsCompatiblityRequired) {
		            IDataUtil.put(cursor, "$integer", result);
		        } else {
		            IDataUtil.put(cursor, "$average", result);
		        }
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
		    String string = IDataUtil.getString(cursor, "$integer");
		    String result = BigIntegerHelper.emit(BigIntegerHelper.decrement(BigIntegerHelper.parse(string)));
		    IDataUtil.put(cursor, "$integer", result);
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
		    String dividend = IDataUtil.getString(cursor, "$dividend");
		    String divisor = IDataUtil.getString(cursor, "$divisor");
		
		    // support $integer.x and $integer.y inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (dividend == null && divisor == null) {
		        dividend = IDataUtil.getString(cursor, "$integer.x");
		        divisor = IDataUtil.getString(cursor, "$integer.y");
		        backwardsCompatiblityRequired = true;
		    }
		
		    String[] result = BigIntegerHelper.emit(BigIntegerHelper.divideAndRemainder(BigIntegerHelper.parse(dividend), BigIntegerHelper.parse(divisor)));
		
		    if (result != null && result.length == 2) {
		        if (backwardsCompatiblityRequired) {
		            IDataUtil.put(cursor, "$integer", result[0]);
		        } else {
		            IDataUtil.put(cursor, "$result", result[0]);
		            IDataUtil.put(cursor, "$remainder", result[1]);
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
		    Object object = IDataUtil.get(cursor, "$object");
		    String radix = IDataUtil.getString(cursor, "$radix");
		
		    String result = BigIntegerHelper.emit(BigIntegerHelper.normalize(object, Integer.parseInt(radix)));
		
		    if (result != null) IDataUtil.put(cursor, "$string", result);
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
		    String integer = IDataUtil.getString(cursor, "$integer");
		    String result = BigIntegerHelper.emit(BigIntegerHelper.increment(BigIntegerHelper.parse(integer)));
		    IDataUtil.put(cursor, "$integer", result);
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
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String[] list = IDataUtil.getStringArray(cursor, "$integers");
		
		    // support $integers input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null && list != null) {
		        IDataMap map = new IDataMap();
		        map.put("$integers", list);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    String result = BigIntegerHelper.emit(BigIntegerHelper.maximum(BigIntegerHelper.normalize(IDataHelper.getLeafValues(operands))));
		
		    if (result != null) {
		        if (backwardsCompatiblityRequired) {
		            IDataUtil.put(cursor, "$integer", result);
		        } else {
		            IDataUtil.put(cursor, "$maximum", result);
		        }
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
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String[] list = IDataUtil.getStringArray(cursor, "$integers");
		
		    // support $integers input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null && list != null) {
		        IDataMap map = new IDataMap();
		        map.put("$integers", list);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    String result = BigIntegerHelper.emit(BigIntegerHelper.minimum(BigIntegerHelper.normalize(IDataHelper.getLeafValues(operands))));
		
		    if (result != null) {
		        if (backwardsCompatiblityRequired) {
		            IDataUtil.put(cursor, "$integer", result);
		        } else {
		            IDataUtil.put(cursor, "$minimum", result);
		        }
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
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String[] list = IDataUtil.getStringArray(cursor, "$integers");
		    String integer = IDataUtil.getString(cursor, "$integer");
		
		    // support $integers input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (operands == null && (list != null || integer != null)) {
		        IDataMap map = new IDataMap();
		        map.put("$integers", list);
		        map.put("$integer", integer);
		        operands = map;
		        backwardsCompatiblityRequired = true;
		    }
		
		    String result = BigIntegerHelper.emit(BigIntegerHelper.multiply(BigIntegerHelper.normalize(IDataHelper.getLeafValues(operands))));
		
		    if (result != null) {
		        if (backwardsCompatiblityRequired) {
		            IDataUtil.put(cursor, "$integer", result);
		        } else {
		            IDataUtil.put(cursor, "$result", result);
		        }
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
		    String integer = IDataUtil.getString(cursor, "$integer");
		    String result = BigIntegerHelper.emit(BigIntegerHelper.negate(BigIntegerHelper.parse(integer)));
		    if (result != null) IDataUtil.put(cursor, "$integer", result);
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
		// [i] field:0:optional $class {&quot;java.math.BigInteger&quot;,&quot;java.math.BigDecimal&quot;,&quot;java.lang.Double&quot;,&quot;java.lang.Float&quot;,&quot;java.lang.Integer&quot;,&quot;java.lang.Long&quot;}
		// [i] field:0:optional $radix
		// [o] object:0:optional $object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String string = IDataUtil.getString(cursor, "$string");
		    String className = IDataUtil.getString(cursor, "$class");
		    String radix = IDataUtil.getString(cursor, "$radix");
		
		    BigInteger integer = BigIntegerHelper.parse(string, radix == null ? BigIntegerHelper.DEFAULT_RADIX : Integer.parseInt(radix));
		
		    if (integer != null) {
		        if (className == null || className.equals("java.math.BigInteger")) {
		            IDataUtil.put(cursor, "$object", integer);
		        } else if (className.equals("java.math.BigDecimal")) {
		            IDataUtil.put(cursor, "$object", new BigDecimal(integer));
		        } else if (className.equals("java.lang.Double")) {
		            IDataUtil.put(cursor, "$object", new BigDecimal(integer).doubleValue());
		        } else if (className.equals("java.lang.Float")) {
		            IDataUtil.put(cursor, "$object", new BigDecimal(integer).floatValue());
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
		// [i] field:0:optional $base
		// [i] field:0:optional $exponent
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String base = IDataUtil.getString(cursor, "$base");
		    String exponent = IDataUtil.getString(cursor, "$exponent");
		
		    // support $integer input for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (base == null) {
		        base = IDataUtil.getString(cursor, "$integer");
		        backwardsCompatiblityRequired = true;
		    }
		
		    String result = BigIntegerHelper.emit(BigIntegerHelper.power(BigIntegerHelper.parse(base), BigIntegerHelper.parse(exponent)));
		
		    if (result != null) {
		        if (backwardsCompatiblityRequired) {
		            IDataUtil.put(cursor, "$integer", result);
		        } else {
		            IDataUtil.put(cursor, "$result", result);
		        }
		    }
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
		    String dividend = IDataUtil.getString(cursor, "$dividend");
		    String divisor = IDataUtil.getString(cursor, "$divisor");
		
		    // support $integer.x and $integer.y inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (dividend == null && divisor == null) {
		        dividend = IDataUtil.getString(cursor, "$integer.x");
		        divisor = IDataUtil.getString(cursor, "$integer.y");
		        backwardsCompatiblityRequired = true;
		    }
		
		    String result = BigIntegerHelper.emit(BigIntegerHelper.remainder(BigIntegerHelper.parse(dividend), BigIntegerHelper.parse(divisor)));
		
		    if (result != null) {
		        if (backwardsCompatiblityRequired) {
		            IDataUtil.put(cursor, "$integer", result);
		        } else {
		            IDataUtil.put(cursor, "$remainder", result);
		        }
		    }
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
		    String minuend = IDataUtil.getString(cursor, "$minuend");
		    String subtrahend = IDataUtil.getString(cursor, "$subtrahend");
		
		    // support $integer.x and $integer.y inputs for backwards-compatibility
		    boolean backwardsCompatiblityRequired = false;
		    if (minuend == null && subtrahend == null) {
		        minuend = IDataUtil.getString(cursor, "$integer.x");
		        subtrahend = IDataUtil.getString(cursor, "$integer.y");
		        backwardsCompatiblityRequired = true;
		    }
		
		    String result = BigIntegerHelper.emit(BigIntegerHelper.subtract(BigIntegerHelper.parse(minuend), BigIntegerHelper.parse(subtrahend)));
		
		    if (result != null) {
		        if (backwardsCompatiblityRequired) {
		            IDataUtil.put(cursor, "$integer", result);
		        } else {
		            IDataUtil.put(cursor, "$remainder", result);
		        }
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
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String integer = IDataUtil.getString(cursor, "$integer");
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));
		
		    IDataUtil.put(cursor, "$valid?", BooleanHelper.emit(BigIntegerHelper.validate(integer, raise)));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

