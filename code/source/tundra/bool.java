package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-08 21:11:15 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.lang.BooleanHelper;
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
		
		    if (bool != null) IDataUtil.put(cursor, "$string", BooleanHelper.emit(BooleanHelper.parse(bool.toString()), trueValue, falseValue));
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
		// [i] field:0:optional $string
		// [i] field:0:optional $value.true.input
		// [i] field:0:optional $value.false.input
		// [i] field:0:optional $value.true.output
		// [i] field:0:optional $value.false.output
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String inString = IDataUtil.getString(cursor, "$string");
		    String inTrueValue = IDataUtil.getString(cursor, "$value.true.input");
		    String inFalseValue = IDataUtil.getString(cursor, "$value.false.input");
		    String outTrueValue = IDataUtil.getString(cursor, "$value.true.output");
		    String outFalseValue = IDataUtil.getString(cursor, "$value.false.output");
		
		    if (inString != null) IDataUtil.put(cursor,  "$string", BooleanHelper.format(inString, inTrueValue, inFalseValue, outTrueValue, outFalseValue));
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
		    String input = IDataUtil.getString(cursor, "$boolean");
		    if (input != null) IDataUtil.put(cursor, "$boolean", BooleanHelper.negate(input));
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
		    String input = IDataUtil.getString(cursor, "$boolean");
		    String defaultValue = IDataUtil.getString(cursor, "$default");
		    IDataUtil.put(cursor, "$boolean", BooleanHelper.normalize(input, defaultValue));
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
		    String input = IDataUtil.getString(cursor, "$string");
		    String trueValue = IDataUtil.getString(cursor, "$value.true");
		    String falseValue = IDataUtil.getString(cursor, "$value.false");
		
		    if (input != null) IDataUtil.put(cursor, "$boolean", BooleanHelper.parse(input, trueValue, falseValue));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

