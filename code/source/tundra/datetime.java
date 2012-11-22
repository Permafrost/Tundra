package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-11-22 15:42:13.784
// -----( ON-HOST: TNFDEVWAP103.test.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		// [i] field:0:optional $duration
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String duration = IDataUtil.getString(cursor, "$duration");
		  IDataUtil.put(cursor, "$datetime", add(datetime, duration));
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
		// [o] field:0:required $before?
		// [o] field:0:required $equal?
		// [o] field:0:required $after?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String dx = IDataUtil.getString(cursor, "$datetime.x");
		  String dy = IDataUtil.getString(cursor, "$datetime.y");
		
		  int comparison = compare(dx, dy);
		
		  IDataUtil.put(cursor, "$before?", "" + (comparison < 0));
		  IDataUtil.put(cursor, "$equal?", "" + (comparison == 0));
		  IDataUtil.put(cursor, "$after?", "" + (comparison > 0));
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
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String date = IDataUtil.getString(cursor, "$date");
		  String time = IDataUtil.getString(cursor, "$time");
		
		  if (date != null && time != null) IDataUtil.put(cursor, "$datetime", concatenate(date, time));
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
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String start = IDataUtil.getString(cursor, "$datetime.start");
		  String end = IDataUtil.getString(cursor, "$datetime.end");
		
		  IDataUtil.put(cursor, "$duration", duration(start, end));
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
		// [i] field:0:optional $pattern.input {&quot;datetime&quot;,&quot;date&quot;,&quot;time&quot;,&quot;milliseconds&quot;}
		// [i] field:0:optional $pattern.output {&quot;datetime&quot;,&quot;date&quot;,&quot;time&quot;,&quot;milliseconds&quot;}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		  String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		
		  IDataUtil.put(cursor, "$datetime", format(datetime, inPattern, outPattern));
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
		// [o] field:0:required $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$datetime", now());
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
		// [i] field:0:optional $duration
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String duration = IDataUtil.getString(cursor, "$duration");
		  IDataUtil.put(cursor, "$datetime", subtract(datetime, duration));
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
		// [i] field:0:optional $pattern {&quot;date.jdbc&quot;,&quot;date.xml&quot;,&quot;datetime.jdbc&quot;,&quot;datetime.xml&quot;,&quot;datetime.xcbl&quot;,&quot;datetime.sapiens&quot;,&quot;datetime.epoch.milliseconds&quot;,&quot;time.jdbc&quot;,&quot;time.xml&quot;}
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();
		
		boolean valid = true, raise = false;
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		  raise = Boolean.parseBoolean(IDataUtil.getString(cursor, "$raise?"));
		
		  // attempt to parse the datetime string, if no exception is thrown then the string is valid
		  parse(datetime, pattern);
		} catch (IllegalArgumentException ex) {
		  if (raise) tundra.exception.raise(ex);
		  valid = false;
		} finally {
		  IDataUtil.put(cursor, "$valid?", "" + valid);
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static final String DEFAULT_DATETIME_PATTERN = "datetime";
	
	// adds the given xml duration to the given xml datetime returning the result
	public static String add(String datetime, String duration) {
	  return emit(add(parse(datetime), tundra.duration.parse(duration)));
	}
	
	// adds the given xml duration to the given xml datetime returning the result
	public static java.util.Calendar add(java.util.Calendar calendar, javax.xml.datatype.Duration duration) {
	  if (calendar == null || duration == null) return calendar;
	  
	  java.util.Calendar clone = (java.util.Calendar)calendar.clone();
	  duration.addTo(clone);
	  return clone;
	}
	
	// compares two xml datetime strings
	public static int compare(String dx, String dy) {
	  return compare(parse(dx), parse(dy));
	}
	
	// compares two datetimes
	public static int compare(java.util.Calendar cx, java.util.Calendar cy) {
	  if (cx == null && cy == null) return 0;
	  if (cx == null && cy != null) return -1;
	  if (cx != null && cy == null) return 1;
	  
	  return cx.compareTo(cy);
	}
	
	// concatenates a xml date and xml time together to form an xml datetime
	public static String concatenate(String date, String time) {
	  return emit(concatenate(parse(date, "date"), parse(time, "time")));
	}
	
	// adds two dates together
	public static java.util.Calendar concatenate(java.util.Calendar date, java.util.Calendar time) {
	  java.util.Calendar localDateTime = new java.util.GregorianCalendar();
	  localDateTime.setTimeInMillis(date.getTimeInMillis() + time.getTimeInMillis());
	
	  return localDateTime;
	}
	
	// returns the xml duration between two given xml datetimes
	public static String duration(String start, String end) {
	  return tundra.duration.emit(duration(parse(start), parse(end)));
	}
	
	// returns the xml duration between two given xml datetimes
	public static javax.xml.datatype.Duration duration(java.util.Calendar start, java.util.Calendar end) {
	  if (start == null || end == null) return null;
	  return tundra.duration.parse("" + (end.getTimeInMillis() - start.getTimeInMillis()), "milliseconds");
	}
	
	// returns the given datetime as an xml datetime string
	public static String emit(java.util.Calendar input) {
	  return emit(input, null);
	}
	
	// returns the given datetime formatted as a string adhering to the given pattern
	public static String emit(java.util.Calendar input, String pattern) {
	  if (pattern == null) pattern = DEFAULT_DATETIME_PATTERN;
	  String output = null;
	  
	  if (input != null) {
	    if (pattern.equals("datetime")) {
	      output = javax.xml.bind.DatatypeConverter.printDateTime(input);
	    } else if (pattern.equals("date")) {
	      output = javax.xml.bind.DatatypeConverter.printDate(input);
	    } else if (pattern.equals("time"))    {
	      output = javax.xml.bind.DatatypeConverter.printTime(input);
	    } else if (pattern.equals("milliseconds")) {
	      output = "" + input.getTimeInMillis();  
	    } else {
	      java.text.DateFormat formatter = new java.text.SimpleDateFormat(pattern);
	      formatter.setLenient(false);
	      output = formatter.format(input.getTime());
	    }
	  }
	  
	  return output;
	}
	
	// formats a datetime string to the given pattern
	public static String format(String input, String inPattern, String outPattern) {
	  return emit(parse(input, inPattern), outPattern);
	}
	
	// returns the current datetime as an xml datetime string
	public static String now() {
	  return emit(java.util.Calendar.getInstance());
	}
	
	// parses an xml datetime string and returns a java.util.Date object
	public static java.util.Calendar parse(String input) {
	  return parse(input, null);
	}
	
	// parses a datetime string that adhears to the given pattern and returns a java.util.Date object
	public static java.util.Calendar parse(String input, String pattern) throws IllegalArgumentException {
	  if (pattern == null) pattern = DEFAULT_DATETIME_PATTERN;
	  
	  java.util.Calendar output = null;
	  
	  if (input != null) {
	    if (pattern.equals("datetime")) {
	      output = javax.xml.bind.DatatypeConverter.parseDateTime(input);
	    } else if (pattern.equals("date")) {
	      output = javax.xml.bind.DatatypeConverter.parseDate(input);
	    } else if (pattern.equals("time")) {
	      output = javax.xml.bind.DatatypeConverter.parseTime(input);
	    } else if (pattern.equals("milliseconds")) {
	      output = java.util.Calendar.getInstance();
	      output.setTimeInMillis(Long.parseLong(input));
	    } else {
	      java.text.DateFormat formatter = new java.text.SimpleDateFormat(pattern);
	      formatter.setLenient(false);
	      output = java.util.Calendar.getInstance();
	      try {
	        output.setTime(formatter.parse(input));
	      } catch (java.text.ParseException ex) {
	        throw new IllegalArgumentException(tundra.exception.message(ex));
	      }
	    }
	  }
	  
	  return output;
	}
	
	// subtracts the given xml duration from the given xml datetime returning the result
	public static String subtract(String datetime, String duration) {
	  return emit(subtract(parse(datetime), tundra.duration.parse(duration)));
	}
	
	// subtracts the given duration from the given datetime returning the result
	public static java.util.Calendar subtract(java.util.Calendar calendar, javax.xml.datatype.Duration duration) {
	  if (duration != null) duration = duration.negate();
	  return add(calendar, duration);
	}
	// --- <<IS-END-SHARED>> ---
}

