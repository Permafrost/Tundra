package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2023-08-23 04:50:11 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.transform.bool.Formatter;
import permafrost.tundra.data.transform.Transformer;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.util.RandomHelper;
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
		    Object bool = IDataHelper.get(cursor, "$boolean");
		    String trueValue = IDataHelper.get(cursor, "$value.true", String.class);
		    String falseValue = IDataHelper.get(cursor, "$value.false", String.class);
		
		    if (bool != null) IDataHelper.put(cursor, "$string", BooleanHelper.emit(BooleanHelper.parse(bool.toString()), trueValue, falseValue));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void format (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(format)>> ---
		// @sigtype java 3.5
		// [i] record:0:optional $format.operands
		// [i] field:0:optional $value.true.input
		// [i] field:0:optional $value.false.input
		// [i] field:0:optional $value.true.output
		// [i] field:0:optional $value.false.output
		// [o] record:0:optional $format.results
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$format.operands", IData.class);
		    String inTrueValue = IDataHelper.get(cursor, "$value.true.input", String.class);
		    String inFalseValue = IDataHelper.get(cursor, "$value.false.input", String.class);
		    String outTrueValue = IDataHelper.get(cursor, "$value.true.output", String.class);
		    String outFalseValue = IDataHelper.get(cursor, "$value.false.output", String.class);
		
		    if (operands == null) {
		        String inString = IDataHelper.get(cursor, "$string", String.class);
		        if (inString != null) {
		            IDataHelper.put(cursor,  "$string", BooleanHelper.format(inString, inTrueValue, inFalseValue, outTrueValue, outFalseValue));
		        }
		    } else {
		        IDataHelper.put(cursor, "$format.results", Transformer.transform(operands, new Formatter(inTrueValue, inFalseValue, outTrueValue, outFalseValue)), false);
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
		// [i] field:0:optional $boolean
		// [o] field:0:optional $boolean
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String input = IDataHelper.get(cursor, "$boolean", String.class);
		    if (input != null) IDataHelper.put(cursor, "$boolean", BooleanHelper.negate(input));
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
		    String input = IDataHelper.get(cursor, "$boolean", String.class);
		    String defaultValue = IDataHelper.get(cursor, "$default", String.class);
		    IDataHelper.put(cursor, "$boolean", BooleanHelper.normalize(input, defaultValue));
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
		// [i] field:0:optional $value.true
		// [i] field:0:optional $value.false
		// [o] object:0:optional $boolean
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String input = IDataHelper.get(cursor, "$string", String.class);
		    String trueValue = IDataHelper.get(cursor, "$value.true", String.class);
		    String falseValue = IDataHelper.get(cursor, "$value.false", String.class);
		
		    if (input != null) IDataHelper.put(cursor, "$boolean", BooleanHelper.parse(input, trueValue, falseValue));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void random (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(random)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] object:0:required $boolean.random
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$boolean.random", RandomHelper.getInstance().nextBoolean());
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

