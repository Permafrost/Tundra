package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-03-25 14:09:29 GMT+10:00
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigDecimal;
import java.util.Date;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataMap;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.math.BigDecimalHelper;
import permafrost.tundra.time.DateTimeHelper;
import permafrost.tundra.time.DurationHelper;
import permafrost.tundra.time.DurationPattern;
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
		// [i] field:0:optional $pattern.input {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
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

		    String result = DurationHelper.emit(DurationHelper.add(DurationHelper.normalize(IDataHelper.getLeaves(operands), inPattern)), outPattern);

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
		// [i] field:0:optional $duration.first
		// [i] field:0:optional $duration.first.pattern {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $duration.second
		// [i] field:0:optional $duration.second.pattern {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] field:0:required $lesser?
		// [o] field:0:required $equal?
		// [o] field:0:required $greater?
		// [o] field:0:required $indeterminate?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String firstDuration = IDataHelper.first(cursor, String.class, "$duration.first", "$duration.x");
		    String firstPattern = IDataHelper.first(cursor, String.class, "$duration.first.pattern", "$pattern");
		    String secondDuration = IDataHelper.first(cursor, String.class, "$duration.second", "$duration.y");
		    String secondPattern = IDataHelper.first(cursor, String.class, "$duration.second.pattern", "$pattern");

		    int comparison = DurationHelper.compare(DurationHelper.parse(firstDuration, firstPattern), DurationHelper.parse(secondDuration, secondPattern));

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



	public static final void end (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(end)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $datetime.monotonic.start
		// [i] field:0:optional $duration.pattern {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] object:0:optional $datetime.monotonic.start
		// [o] object:0:optional $datetime.monotonic.end
		// [o] field:0:required $duration.measured
		IDataCursor cursor = pipeline.getCursor();

		try {
		    long end = System.nanoTime();
		    long start = IDataHelper.getOrDefault(cursor, "$datetime.monotonic.start", Long.class, end);
		    DurationPattern outputPattern = IDataHelper.get(cursor, "$duration.pattern", DurationPattern.class);

		    IDataHelper.put(cursor, "$datetime.monotonic.start", start);
		    IDataHelper.put(cursor, "$datetime.monotonic.end", end);
		    IDataHelper.put(cursor, "$duration.measured", DurationHelper.format(end - start, DurationPattern.NANOSECONDS, outputPattern));
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
		// [i] record:0:optional $duration.input
		// [i] field:0:optional $pattern.input {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:1:optional $patterns.input
		// [i] field:0:optional $pattern.output {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $datetime
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [o] record:0:optional $duration.output
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$duration.input", IData.class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String[] inPatterns = IDataHelper.get(cursor, "$patterns.input", String[].class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String datetimePattern = IDataHelper.get(cursor, "$datetime.pattern", String.class);

		    Date instant = null;
		    if (datetime != null) instant = DateTimeHelper.parse(datetime, datetimePattern).getTime();

		    if (document != null) {
		        if (inPatterns == null) {
		            document = DurationHelper.format(document, DurationPattern.normalize(inPattern), DurationPattern.normalize(outPattern), instant);
		        } else {
		            document = DurationHelper.format(document, DurationPattern.normalize(inPatterns), DurationPattern.normalize(outPattern), instant);
		        }
		        IDataHelper.put(cursor, "$duration.output", document);
		    } else {
		        String duration = IDataHelper.get(cursor, "$duration", String.class);
		        if (duration != null) {
		            if (inPatterns == null) {
		                duration = DurationHelper.format(duration, DurationPattern.normalize(inPattern), DurationPattern.normalize(outPattern), instant);
		            } else {
		                duration = DurationHelper.format(duration, DurationPattern.normalize(inPatterns), DurationPattern.normalize(outPattern), instant);
		            }
		            IDataHelper.put(cursor, "$duration", duration);
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
		// [i] field:0:optional $duration
		// [i] field:0:optional $pattern.input {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
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

		    duration = DurationHelper.emit(DurationHelper.multiply(DurationHelper.parse(duration, inPattern), factor, datetime, datetimePattern), outPattern);

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
		// [i] field:0:optional $pattern.input {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
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



	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] object:0:required $datetime.monotonic.start
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDataHelper.put(cursor, "$datetime.monotonic.start", System.nanoTime());
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
		// [i] field:0:optional $pattern.input {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","nanoseconds","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
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

