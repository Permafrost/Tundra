package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-06 16:26:52 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigDecimal;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataMap;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.math.BigDecimalHelper;
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $pattern.input {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] field:0:required $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		
		    // support $duration.x and $duration.y inputs for backwards-compatibility
		    if (operands == null) {
		        String dx = IDataHelper.get(cursor, "$duration.x", String.class);
		        String dy = IDataHelper.get(cursor, "$duration.y", String.class);
		
		        IDataMap map = new IDataMap();
		        if (dx != null) map.put("$duration.x", dx);
		        if (dy != null) map.put("$duration.y", dy);
		        operands = map;
		    }
		
		    String result = DurationHelper.emit(DurationHelper.add(DurationHelper.normalize(IDataHelper.getLeafValues(operands), inPattern)), outPattern);
		    
		    IDataHelper.put(cursor, "$duration", result, false);
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
		    String x = IDataHelper.get(cursor, "$duration.x", String.class);
		    String y = IDataHelper.get(cursor, "$duration.y", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		
		    int comparison = DurationHelper.compare(DurationHelper.parse(x, pattern), DurationHelper.parse(y, pattern));
		
		    boolean lesser        = comparison == javax.xml.datatype.DatatypeConstants.LESSER;
		    boolean equal         = comparison == javax.xml.datatype.DatatypeConstants.EQUAL;
		    boolean greater       = comparison == javax.xml.datatype.DatatypeConstants.GREATER;
		    boolean indeterminate = comparison == javax.xml.datatype.DatatypeConstants.INDETERMINATE;
		
		    IDataHelper.put(cursor, "$lesser?", lesser, String.class);
		    IDataHelper.put(cursor, "$equal?", equal, String.class);
		    IDataHelper.put(cursor, "$greater?", greater, String.class);
		    IDataHelper.put(cursor, "$indeterminate?", indeterminate, String.class);
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
		    String duration = IDataHelper.get(cursor, "$duration", String.class);
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		    String datetimePattern = IDataHelper.get(cursor, "$datetime.pattern", String.class);
		
		    duration = DurationHelper.format(duration, inPattern, outPattern, datetime, datetimePattern);
		
		    IDataHelper.put(cursor, "$duration", duration, false);
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
		    String duration = IDataHelper.get(cursor, "$duration", String.class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String datetimePattern = IDataHelper.get(cursor, "$datetime.pattern", String.class);
		    BigDecimal factor = IDataHelper.get(cursor, "$factor", BigDecimal.class);
		
		    duration = DurationHelper.emit(DurationHelper.multiply(DurationHelper.parse(duration, inPattern), factor, datetime, datetimePattern));
		
		    IDataHelper.put(cursor, "$duration", duration, false);
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
		    String duration = IDataHelper.get(cursor, "$duration", String.class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		
		    duration = DurationHelper.emit(DurationHelper.negate(DurationHelper.parse(duration, inPattern)), outPattern);
		
		    IDataHelper.put(cursor, "$duration", duration, false);
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
		    String x = IDataHelper.get(cursor, "$duration.x", String.class);
		    String y = IDataHelper.get(cursor, "$duration.y", String.class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		
		    String result = DurationHelper.emit(DurationHelper.subtract(DurationHelper.parse(x, inPattern), DurationHelper.parse(y, inPattern)), outPattern);
		
		    IDataHelper.put(cursor, "$duration", result, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

