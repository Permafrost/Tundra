package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-06-03 17:18:53 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Date;
import java.util.Calendar;
import permafrost.tundra.data.IDataHelper;
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
		// [i] field:0:optional $datetime.pattern
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String datetimePattern = IDataHelper.first(cursor, String.class, "$datetime.pattern", "$pattern");
		
		    String duration = IDataHelper.get(cursor, "$duration", String.class);
		    String durationPattern = IDataHelper.get(cursor, "$duration.pattern", String.class);
		
		    String result = DateTimeHelper.add(datetime, datetimePattern, duration, durationPattern);
		
		    IDataHelper.put(cursor, "$datetime", result, false);
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
		// [i] field:0:optional $datetime.first
		// [i] field:0:optional $datetime.first.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $datetime.second
		// [i] field:0:optional $datetime.second.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [o] field:0:required $before?
		// [o] field:0:required $equal?
		// [o] field:0:required $after?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String firstDateTime = IDataHelper.first(cursor, String.class, "$datetime.first", "$datetime.x");
		    String secondDateTime = IDataHelper.first(cursor, String.class, "$datetime.second", "$datetime.y");
		    String firstPattern = IDataHelper.first(cursor, String.class, "$datetime.first.pattern", "$pattern.x", "$pattern");
		    String secondPattern = IDataHelper.first(cursor, String.class, "$datetime.second.pattern", "$pattern.y", "$pattern");
		
		    int comparison = DateTimeHelper.compare(firstDateTime, firstPattern, secondDateTime, secondPattern);
		
		    IDataHelper.put(cursor, "$before?", comparison < 0, String.class);
		    IDataHelper.put(cursor, "$equal?", comparison == 0, String.class);
		    IDataHelper.put(cursor, "$after?", comparison > 0, String.class);
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
		// [i] field:0:optional $date.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $time
		// [i] field:0:optional $time.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $timezone.input
		// [i] field:0:optional $timezone.output
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String date = IDataHelper.get(cursor, "$date", String.class);
		    String time = IDataHelper.get(cursor, "$time", String.class);
		    String datePattern = IDataHelper.first(cursor, String.class, "$date.pattern", "$pattern.date");
		    
		String timePattern = IDataHelper.first(cursor, String.class, "$time.pattern", "$pattern.time");
		    String datetimePattern = IDataHelper.first(cursor, String.class, "$datetime.pattern", "$pattern.datetime");
		    String inTimeZone = IDataHelper.get(cursor, "$timezone.input", String.class);
		    String outTimeZone = IDataHelper.get(cursor, "$timezone.output", String.class);
		
		    String datetime = DateTimeHelper.emit(DateTimeHelper.concatenate(DateTimeHelper.parse(date, datePattern, inTimeZone), DateTimeHelper.parse(time, timePattern, inTimeZone)), datetimePattern, outTimeZone);
		
		    IDataHelper.put(cursor, "$datetime", datetime, false);
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
		// [i] field:0:optional $datetime.start.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $datetime.end
		// [i] field:0:optional $datetime.end.pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String start = IDataHelper.get(cursor, "$datetime.start", String.class);
		    String end = IDataHelper.get(cursor, "$datetime.end", String.class);
		    String startPattern = IDataHelper.first(cursor, String.class, "$datetime.start.pattern", "$datetime.pattern", "$pattern");
		    String endPattern = IDataHelper.first(cursor, String.class, "$datetime.end.pattern", "$datetime.pattern", "$pattern");
		    String durationPattern = IDataHelper.get(cursor, "$duration.pattern", String.class);
		
		    String duration = DurationHelper.emit(DateTimeHelper.duration(DateTimeHelper.parse(start, startPattern), DateTimeHelper.parse(end, endPattern)), durationPattern);
		
		    IDataHelper.put(cursor, "$duration", duration, false);
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
		// [o] field:0:required $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String datetimePattern = IDataHelper.get(cursor, "$datetime.pattern", String.class);
		    String duration = IDataHelper.get(cursor, "$duration", String.class);
		    String durationPattern = IDataHelper.get(cursor, "$duration.pattern", String.class);
		    String timezone = IDataHelper.get(cursor, "$timezone", String.class);
		
		    String datetime = DateTimeHelper.emit(DateTimeHelper.earlier(DurationHelper.parse(duration, durationPattern)), datetimePattern, timezone);
		
		    IDataHelper.put(cursor, "$datetime", datetime);
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
		    Date date = IDataHelper.get(cursor, "$datetime.object", Date.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String timezone = IDataHelper.get(cursor, "$timezone", String.class);
		
		    String datetime = DateTimeHelper.emit(date, pattern, timezone);
		
		    IDataHelper.put(cursor, "$datetime", datetime, false);
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
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String[] inPatterns = IDataHelper.get(cursor, "$patterns.input", String[].class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		    String inTimeZone = IDataHelper.get(cursor, "$timezone.input", String.class);
		    String outTimeZone = IDataHelper.get(cursor, "$timezone.output", String.class);
		
		    if (datetime != null) {
		        if (inPatterns == null) {
		            datetime = DateTimeHelper.format(datetime, inPattern, inTimeZone, outPattern, outTimeZone);
		        } else {
		            datetime = DateTimeHelper.format(datetime, inPatterns, inTimeZone, outPattern, outTimeZone);
		        }
		        IDataHelper.put(cursor, "$datetime", datetime);
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
		// [o] field:0:required $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String datetimePattern = IDataHelper.get(cursor, "$datetime.pattern", String.class);
		    String duration = IDataHelper.get(cursor, "$duration", String.class);
		    String durationPattern = IDataHelper.get(cursor, "$duration.pattern", String.class);
		    String timezone = IDataHelper.get(cursor, "$timezone", String.class);
		
		    String datetime = DateTimeHelper.emit(DateTimeHelper.later(DurationHelper.parse(duration, durationPattern)), datetimePattern, timezone);
		
		    IDataUtil.put(cursor, "$datetime", datetime);
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
		    String[] datetimes = IDataHelper.get(cursor, "$datetimes", String[].class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		
		    String datetime = DateTimeHelper.emit(DateTimeHelper.maximum(DateTimeHelper.parse(datetimes, pattern)), pattern);
		
		    IDataHelper.put(cursor, "$datetime", datetime, false);
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
		    String[] datetimes = IDataHelper.get(cursor, "$datetimes", String[].class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		
		    String datetime = DateTimeHelper.emit(DateTimeHelper.minimum(DateTimeHelper.parse(datetimes, pattern)), pattern);
			
		    IDataHelper.put(cursor, "$datetime", datetime, false);
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
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String timezone = IDataHelper.get(cursor, "$timezone", String.class);
		
		    IDataHelper.put(cursor, "$datetime", DateTimeHelper.now(pattern, timezone));
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
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String[] patterns = IDataHelper.get(cursor, "$patterns", String[].class);
		    String timezone = IDataHelper.get(cursor, "$timezone", String.class);
		
		    if (datetime != null) {
		        Calendar calendar;
		        if (patterns == null) {
		            calendar = DateTimeHelper.parse(datetime, pattern, timezone);
		        } else {
		            calendar = DateTimeHelper.parse(datetime, patterns, timezone);
		        }
		        IDataHelper.put(cursor, "$datetime.object", calendar.getTime());
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
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String datetimePattern = IDataHelper.first(cursor, String.class, "$datetime.pattern", "$pattern");
		    String duration = IDataHelper.get(cursor, "$duration", String.class);
		    String durationPattern = IDataHelper.get(cursor, "$duration.pattern", String.class);
		    
		    String result = DateTimeHelper.emit(DateTimeHelper.subtract(DateTimeHelper.parse(datetime, datetimePattern), DurationHelper.parse(duration, durationPattern)), datetimePattern);
		
		    IDataHelper.put(cursor, "$datetime", result, false);
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
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String timezone = IDataHelper.get(cursor, "$timezone", String.class);
		
		    String datetime = DateTimeHelper.today(pattern, timezone);
		
		    IDataUtil.put(cursor, "$datetime", datetime);
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
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String timezone = IDataHelper.get(cursor, "$timezone", String.class);
		
		    String datetime = DateTimeHelper.tomorrow(pattern, timezone);
		
		    IDataHelper.put(cursor, "$datetime", datetime);
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
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);
		
		    // attempt to parse the datetime string, if no exception is thrown then the string is valid
		    DateTimeHelper.parse(datetime, pattern);
		    valid = true;
		} catch (IllegalArgumentException ex) {
		    if (raise) ExceptionHelper.raise(ex);
		} finally {
		    IDataHelper.put(cursor, "$valid?", valid, String.class);
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
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String timezone = IDataHelper.get(cursor, "$timezone", String.class);
		
		    String datetime = DateTimeHelper.yesterday(pattern, timezone);
		
		    IDataHelper.put(cursor, "$datetime", datetime);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

