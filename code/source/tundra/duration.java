package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-19 17:22:41 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.math.DecimalHelper;
import permafrost.tundra.time.DurationHelper;
// --- <<IS-END-IMPORTS>> ---

public final class duration

{
	// ---( internal utility methods )---

	final static duration _instance = new duration();

	static duration _newInstance() { return new duration(); }

	static duration _cast(Object o) { return (duration)o; }

	// ---( server methods )---




	public static final void add (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(add)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $duration.x
		// [i] field:0:optional $duration.y
		// [i] field:0:optional $pattern.input {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] field:0:required $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String dx = IDataUtil.getString(cursor, "$duration.x");
		    String dy = IDataUtil.getString(cursor, "$duration.y");
		    String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		    String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		
		    String result = DurationHelper.emit(DurationHelper.add(DurationHelper.parse(dx, inPattern), DurationHelper.parse(dy, inPattern)), outPattern);
		    
		    if (result != null) IDataUtil.put(cursor, "$duration", result);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void compare (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compare)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $duration.x
		// [i] field:0:optional $duration.y
		// [i] field:0:optional $pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] field:0:required $lesser?
		// [o] field:0:required $equal?
		// [o] field:0:required $greater?
		// [o] field:0:required $indeterminate?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String x = IDataUtil.getString(cursor, "$duration.x");
		    String y = IDataUtil.getString(cursor, "$duration.y");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		
		    int comparison = DurationHelper.compare(DurationHelper.parse(x, pattern), DurationHelper.parse(y, pattern));
		
		    boolean lesser        = comparison == javax.xml.datatype.DatatypeConstants.LESSER;
		    boolean equal         = comparison == javax.xml.datatype.DatatypeConstants.EQUAL;
		    boolean greater       = comparison == javax.xml.datatype.DatatypeConstants.GREATER;
		    boolean indeterminate = comparison == javax.xml.datatype.DatatypeConstants.INDETERMINATE;
		
		    IDataUtil.put(cursor, "$lesser?",        "" + lesser);
		    IDataUtil.put(cursor, "$equal?",         "" + equal);
		    IDataUtil.put(cursor, "$greater?",       "" + greater);
		    IDataUtil.put(cursor, "$indeterminate?", "" + indeterminate);
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
		// [i] field:0:optional $duration
		// [i] field:0:optional $pattern.input {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","milliseconds","seconds","minutes","hours","days","weeks"}
		// [i] field:0:optional $datetime
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String duration = IDataUtil.getString(cursor, "$duration");
		    String datetime = IDataUtil.getString(cursor, "$datetime");
		    String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		    String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		    String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		
		    duration = DurationHelper.format(duration, inPattern, outPattern, datetime, datetimePattern);
		
		    if (duration != null) IDataUtil.put(cursor, "$duration", duration);
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
		// [i] field:0:optional $duration
		// [i] field:0:optional $pattern.input {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $datetime
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $factor
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String duration = IDataUtil.getString(cursor, "$duration");
		    String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		    String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		    String datetime = IDataUtil.getString(cursor, "$datetime");
		    String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		    String factor = IDataUtil.getString(cursor, "$factor");
		
		    duration = DurationHelper.emit(DurationHelper.multiply(DurationHelper.parse(duration, inPattern), DecimalHelper.parse(factor), datetime, datetimePattern));
		
		    if (duration != null) IDataUtil.put(cursor, "$duration", duration);
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
		// [i] field:0:optional $duration
		// [i] field:0:optional $pattern.input {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String duration = IDataUtil.getString(cursor, "$duration");
		    String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		    String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		
		    duration = DurationHelper.emit(DurationHelper.negate(DurationHelper.parse(duration, inPattern)), outPattern);
		
		    if (duration != null) IDataUtil.put(cursor, "$duration", duration);
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
		// [i] field:0:optional $duration.x
		// [i] field:0:optional $duration.y
		// [i] field:0:optional $pattern.input {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String x = IDataUtil.getString(cursor, "$duration.x");
		    String y = IDataUtil.getString(cursor, "$duration.y");
		    String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		    String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		
		    String result = DurationHelper.emit(DurationHelper.subtract(DurationHelper.parse(x, inPattern), DurationHelper.parse(y, inPattern)), outPattern);
		
		    if (result != null) IDataUtil.put(cursor, "$duration", result);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

