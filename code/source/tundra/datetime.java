package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-02-17 22:01:11 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.time.DateTimeHelper;
import permafrost.tundra.time.DurationHelper;
// --- <<IS-END-IMPORTS>> ---

public final class datetime

{
	// ---( internal utility methods )---

	final static datetime _instance = new datetime();

	static datetime _newInstance() { return new datetime(); }

	static datetime _cast(Object o) { return (datetime)o; }

	// ---( server methods )---




	public static final void add (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(add)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional $datetime
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String datetime = IDataUtil.getString(cursor, "$datetime");
		    String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		    // support $pattern to be backwards compatible
		    if (datetimePattern == null) datetimePattern = IDataUtil.getString(cursor, "$pattern");
		
		    String duration = IDataUtil.getString(cursor, "$duration");
		    String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		
		    IDataUtil.put(cursor, "$datetime", DateTimeHelper.add(datetime, datetimePattern, duration, durationPattern));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void compare (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compare)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional $datetime.x
		// [i] field:0:optional $datetime.y
		// [i] field:0:optional $pattern.x {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $pattern.y {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [o] field:0:required $before?
		// [o] field:0:required $equal?
		// [o] field:0:required $after?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String date1 = IDataUtil.getString(cursor, "$datetime.x");
		    String date2 = IDataUtil.getString(cursor, "$datetime.y");
		    String pattern1 = IDataUtil.getString(cursor, "$pattern.x");
		    String pattern2 = IDataUtil.getString(cursor, "$pattern.y");
		
		    // support $pattern for backwards compatibility
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    if (pattern1 == null) pattern1 = pattern;
		    if (pattern2 == null) pattern2 = pattern;
		
		    int comparison = DateTimeHelper.compare(date1, pattern1, date2, pattern2);
		
		    IDataUtil.put(cursor, "$before?", BooleanHelper.emit(comparison < 0));
		    IDataUtil.put(cursor, "$equal?", BooleanHelper.emit(comparison == 0));
		    IDataUtil.put(cursor, "$after?", BooleanHelper.emit(comparison > 0));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void concatenate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(concatenate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $date
		// [i] field:0:optional $time
		// [i] field:0:optional $pattern.date {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $pattern.time {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $pattern.datetime {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $timezone.input
		// [i] field:0:optional $timezone.output
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String date = IDataUtil.getString(cursor, "$date");
		    String time = IDataUtil.getString(cursor, "$time");
		    String datePattern = IDataUtil.getString(cursor, "$pattern.date");
		    
		String timePattern = IDataUtil.getString(cursor, "$pattern.time");
		    String datetimePattern = IDataUtil.getString(cursor, "$pattern.datetime");
		    String inTimeZone = IDataUtil.getString(cursor, "$timezone.input");
		    String outTimeZone = IDataUtil.getString(cursor, "$timezone.output");
		
		    String datetime = DateTimeHelper.emit(DateTimeHelper.concatenate(DateTimeHelper.parse(date, datePattern, inTimeZone), DateTimeHelper.parse(time, timePattern, inTimeZone)), datetimePattern, outTimeZone);
		
		    if (datetime != null) IDataUtil.put(cursor, "$datetime", datetime);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void duration (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(duration)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $datetime.start
		// [i] field:0:optional $datetime.end
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String start = IDataUtil.getString(cursor, "$datetime.start");
		    String end = IDataUtil.getString(cursor, "$datetime.end");
		    String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		    // support $pattern to be backwards compatible
		    if (datetimePattern == null) datetimePattern = IDataUtil.getString(cursor, "$pattern");
		    String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		
		    String duration = DurationHelper.emit(DateTimeHelper.duration(DateTimeHelper.parse(start, datetimePattern), DateTimeHelper.parse(end, datetimePattern)), durationPattern);
		
		    if (duration != null) IDataUtil.put(cursor, "$duration", duration);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void earlier (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(earlier)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $timezone
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		    String duration = IDataUtil.getString(cursor, "$duration");
		    String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		    String timezone = IDataUtil.getString(cursor, "$timezone");
		
		    IDataUtil.put(cursor, "$datetime", DateTimeHelper.emit(DateTimeHelper.earlier(DurationHelper.parse(duration, durationPattern)), datetimePattern, timezone));
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
		// [i] object:0:optional $datetime.object
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $timezone
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    java.util.Date datetime = (java.util.Date)IDataUtil.get(cursor, "$datetime.object");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String timezone = IDataUtil.getString(cursor, "$timezone");
		
		    if (datetime != null) IDataUtil.put(cursor, "$datetime", DateTimeHelper.emit(datetime, pattern, timezone));
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
		// [i] field:0:optional $datetime
		// [i] field:0:optional $pattern.input {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:1:optional $patterns.input
		// [i] field:0:optional $pattern.output {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $timezone.input
		// [i] field:0:optional $timezone.output
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String datetime = IDataUtil.getString(cursor, "$datetime");
		    String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		    String[] inPatterns = IDataUtil.getStringArray(cursor, "$patterns.input");
		    String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		    String inTimeZone = IDataUtil.getString(cursor, "$timezone.input");
		    String outTimeZone = IDataUtil.getString(cursor, "$timezone.output");
		
		    if (datetime != null) {
		        if (inPatterns == null) {
		            datetime = DateTimeHelper.format(datetime, inPattern, inTimeZone, outPattern, outTimeZone);
		        } else {
		            datetime = DateTimeHelper.format(datetime, inPatterns, inTimeZone, outPattern, outTimeZone);
		        }
		        IDataUtil.put(cursor, "$datetime", datetime);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void later (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(later)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $timezone
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		    String duration = IDataUtil.getString(cursor, "$duration");
		    String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		    String timezone = IDataUtil.getString(cursor, "$timezone");
		
		    IDataUtil.put(cursor, "$datetime", DateTimeHelper.emit(DateTimeHelper.later(DurationHelper.parse(duration, durationPattern)), datetimePattern, timezone));
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
		// [i] field:1:optional $datetimes
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] datetimes = IDataUtil.getStringArray(cursor, "$datetimes");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		
		    String datetime = DateTimeHelper.emit(DateTimeHelper.maximum(DateTimeHelper.parse(datetimes, pattern)), pattern);
		
		    if (datetime != null) IDataUtil.put(cursor, "$datetime", datetime);
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
		// [i] field:1:optional $datetimes
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] datetimes = IDataUtil.getStringArray(cursor, "$datetimes");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		
		    String datetime = DateTimeHelper.emit(DateTimeHelper.minimum(DateTimeHelper.parse(datetimes, pattern)), pattern);
			
		    if (datetime != null) IDataUtil.put(cursor, "$datetime", datetime);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void now (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(now)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $timezone
		// [o] field:0:required $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String timezone = IDataUtil.getString(cursor, "$timezone");
		    IDataUtil.put(cursor, "$datetime", DateTimeHelper.now(pattern, timezone));
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
		// [i] field:0:optional $datetime
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:1:optional $patterns
		// [i] field:0:optional $timezone
		// [o] object:0:optional $datetime.object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String datetime = IDataUtil.getString(cursor, "$datetime");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String[] patterns = IDataUtil.getStringArray(cursor, "$patterns");
		    String timezone = IDataUtil.getString(cursor, "$timezone");
		
		    if (datetime != null) {
		        java.util.Calendar calendar = null;
		        if (patterns == null) {
		            calendar = DateTimeHelper.parse(datetime, pattern, timezone);
		        } else {
		            calendar = DateTimeHelper.parse(datetime, patterns, timezone);
		        }
		        IDataUtil.put(cursor, "$datetime.object", calendar.getTime());
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
		// @sigtype java 3.5
		// [i] field:0:optional $datetime
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String datetime = IDataUtil.getString(cursor, "$datetime");
		    String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		    // support $pattern to be backwards compatible
		    if (datetimePattern == null) datetimePattern = IDataUtil.getString(cursor, "$pattern");
		    String duration = IDataUtil.getString(cursor, "$duration");
		    String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		    
		    String result = DateTimeHelper.emit(DateTimeHelper.subtract(DateTimeHelper.parse(datetime, datetimePattern), DurationHelper.parse(duration, durationPattern)), datetimePattern);
		
		    if (result != null) IDataUtil.put(cursor, "$datetime", result);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void today (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(today)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $timezone
		// [o] field:0:required $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String timezone = IDataUtil.getString(cursor, "$timezone");
		    IDataUtil.put(cursor, "$datetime", DateTimeHelper.today(pattern, timezone));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void tomorrow (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(tomorrow)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $timezone
		// [o] field:0:required $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String timezone = IDataUtil.getString(cursor, "$timezone");
		    IDataUtil.put(cursor, "$datetime", DateTimeHelper.tomorrow(pattern, timezone));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void validate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validate)>> ---
		// @sigtype java 3.5
		// [i] field:0:required $datetime
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $raise? {"false","true"}
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();
		
		boolean valid = false, raise = false;
		try {
		    String datetime = IDataUtil.getString(cursor, "$datetime");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));
		
		    // attempt to parse the datetime string, if no exception is thrown then the string is valid
		    DateTimeHelper.parse(datetime, pattern);
		    valid = true;
		} catch (IllegalArgumentException ex) {
		    if (raise) ExceptionHelper.raise(ex);
		} finally {
		    IDataUtil.put(cursor, "$valid?", BooleanHelper.emit(valid));
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void yesterday (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(yesterday)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $timezone
		// [o] field:0:required $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String timezone = IDataUtil.getString(cursor, "$timezone");
		    IDataUtil.put(cursor, "$datetime", DateTimeHelper.yesterday(pattern, timezone));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

