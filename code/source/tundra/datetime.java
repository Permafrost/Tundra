package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-08-13 16:20:15 EST
// -----( ON-HOST: 172.16.189.132

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
		// [i] field:0:optional $datetime.pattern {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		  // support $pattern to be backwards compatible
		  if (datetimePattern == null) datetimePattern = IDataUtil.getString(cursor, "$pattern");
		
		  String duration = IDataUtil.getString(cursor, "$duration");
		  String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		
		  IDataUtil.put(cursor, "$datetime", add(datetime, datetimePattern, duration, durationPattern));
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
		// [i] field:0:optional $pattern {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [o] field:0:required $before?
		// [o] field:0:required $equal?
		// [o] field:0:required $after?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String dx = IDataUtil.getString(cursor, "$datetime.x");
		  String dy = IDataUtil.getString(cursor, "$datetime.y");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		
		  int comparison = compare(dx, dy, pattern);
		
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
		// [i] field:0:optional $datetime.pattern {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:0:optional $duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String start = IDataUtil.getString(cursor, "$datetime.start");
		  String end = IDataUtil.getString(cursor, "$datetime.end");
		  String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		  // support $pattern to be backwards compatible
		  if (datetimePattern == null) datetimePattern = IDataUtil.getString(cursor, "$pattern");
		  String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		
		  if (start != null && end != null) IDataUtil.put(cursor, "$duration", duration(start, end, datetimePattern, durationPattern));
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
		// [i] field:0:optional $datetime.pattern {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		  String duration = IDataUtil.getString(cursor, "$duration");
		  String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		
		  IDataUtil.put(cursor, "$datetime", earlier(datetimePattern, duration, durationPattern));
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
		// [i] field:0:optional $pattern {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  java.util.Date datetime = (java.util.Date)IDataUtil.get(cursor, "$datetime.object");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		
		  if (datetime != null) IDataUtil.put(cursor, "$datetime", emit(datetime, pattern));
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
		// [i] field:0:optional $pattern.input {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:1:optional $patterns.input
		// [i] field:0:optional $pattern.output {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		  String[] inPatterns = IDataUtil.getStringArray(cursor, "$patterns.input");
		  String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		
		  if (datetime != null) {
		    if (inPatterns == null) {
		      datetime = format(datetime, inPattern, outPattern);
		    } else {
		      datetime = format(datetime, inPatterns, outPattern);
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
		// [i] field:0:optional $datetime.pattern {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		  String duration = IDataUtil.getString(cursor, "$duration");
		  String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		
		  IDataUtil.put(cursor, "$datetime", later(datetimePattern, duration, durationPattern));
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
		// [i] field:0:optional $pattern {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [o] field:0:required $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		  IDataUtil.put(cursor, "$datetime", now(pattern));
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
		// [i] field:0:optional $pattern {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:1:optional $patterns
		// [o] object:0:optional $datetime.object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		  String[] patterns = IDataUtil.getStringArray(cursor, "$patterns");
		
		  if (datetime != null) {
		    java.util.Calendar calendar = null;
		    if (patterns == null) {
		      calendar = parse(datetime, pattern);
		    } else {
		      calendar = parse(datetime, patterns);
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
		// [i] field:0:optional $datetime.pattern {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:0:optional $duration
		// [i] field:0:optional $duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [o] field:0:optional $datetime
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		  // support $pattern to be backwards compatible
		  if (datetimePattern == null) datetimePattern = IDataUtil.getString(cursor, "$pattern");
		  String duration = IDataUtil.getString(cursor, "$duration");
		  String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		
		  IDataUtil.put(cursor, "$datetime", subtract(datetime, datetimePattern, duration, durationPattern));
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
		// [i] field:0:optional $pattern {&quot;datetime&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();
		
		boolean valid = true, raise = false;
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		  raise = tundra.bool.parse(IDataUtil.getString(cursor, "$raise?"));
		
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
	protected static final String DEFAULT_DATETIME_PATTERN = "datetime";
	
	protected static final java.util.Map<String, String> namedPatterns = constructNamedPatterns();
	
	protected static final java.util.Map<String, String> constructNamedPatterns() {
	  java.util.Map<String, String> map = new java.util.TreeMap<String, String>();
	
	  map.put("date", "yyyy-MM-dd");
	  map.put("date.jdbc", "yyyy-MM-dd");
	  map.put("date.xml", "yyyy-MM-dd");  
	  map.put("time", "HH:mm:ss.SSS");
	  map.put("time.jdbc", "HH:mm:ss");
	  map.put("time.xml", "HH:mm:ss.SSS");
	
	  return map;
	}
	
	// adds the given xml duration to the given xml datetime returning the result
	public static String add(String datetime, String duration) throws ServiceException {
	  return add(datetime, null, duration);
	}
	
	// adds the given xml duration to the given xml datetime returning the result
	public static String add(String datetime, String pattern, String duration) throws ServiceException {
	  return add(datetime, pattern, duration, null);
	}
	
	// adds the given xml duration to the given xml datetime returning the result
	public static String add(String datetime, String datetimePattern, String duration, String durationPattern) throws ServiceException {
	  return emit(add(parse(datetime, datetimePattern), tundra.duration.parse(duration, durationPattern)), datetimePattern);
	}
	
	// adds the given xml duration to the given xml datetime returning the result
	public static java.util.Calendar add(java.util.Calendar calendar, javax.xml.datatype.Duration duration) throws ServiceException {
	  if (calendar == null || duration == null) return calendar;
	
	  try {
	    java.util.GregorianCalendar gcal = new java.util.GregorianCalendar();
	    gcal.setTime(calendar.getTime());
	    gcal.setTimeZone(calendar.getTimeZone());
	    javax.xml.datatype.XMLGregorianCalendar xcal = javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
	    xcal.add(duration);
	
	    calendar = xcal.toGregorianCalendar();
	  } catch (javax.xml.datatype.DatatypeConfigurationException ex) {
	    tundra.exception.raise(ex);
	  }
	
	  return calendar;
	}
	
	// compares two xml datetime strings
	public static int compare(String dx, String dy) {
	  return compare(dx, dy, null);
	}
	
	// compares two xml datetime strings
	public static int compare(String dx, String dy, String pattern) {
	  return compare(parse(dx, pattern), parse(dy, pattern));
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
	  return duration(start, end, null);
	}
	
	// returns the xml duration between two given xml datetimes
	public static String duration(String start, String end, String datetimePattern) {
	  return duration(start, end, datetimePattern, null);
	}
	
	// returns the xml duration between two given xml datetimes
	public static String duration(String start, String end, String datetimePattern, String durationPattern) {
	  return tundra.duration.emit(duration(parse(start, datetimePattern), parse(end, datetimePattern)), durationPattern);
	}
	
	// returns the xml duration between two given xml datetimes
	public static javax.xml.datatype.Duration duration(java.util.Calendar start, java.util.Calendar end) {
	  if (start == null || end == null) return null;
	  return tundra.duration.parse("" + (end.getTimeInMillis() - start.getTimeInMillis()), "milliseconds");
	}
	
	// returns the given datetime as an xml datetime string
	public static String emit(java.util.Date input) {
	  return emit(input, null);
	}
	
	// returns the given datetime as a string adhering to the given pattern
	public static String emit(java.util.Date input, String pattern) {
	  if (input == null) return null;
	
	  java.util.Calendar calendar = java.util.Calendar.getInstance();
	  calendar.setTime(input);
	
	  return emit(calendar, pattern);
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
	    if (pattern.equals("datetime") || pattern.equals("datetime.xml")) {
	      output = javax.xml.bind.DatatypeConverter.printDateTime(input);
	    } else if (pattern.equals("datetime.jdbc")) {
	      output = (new java.sql.Timestamp(input.getTimeInMillis())).toString();
	    } else if (pattern.equals("milliseconds")) {
	      output = "" + input.getTimeInMillis();  
	    } else {
	      if (namedPatterns.containsKey(pattern)) pattern = namedPatterns.get(pattern);
	      java.text.DateFormat formatter = new java.text.SimpleDateFormat(pattern);
	      formatter.setLenient(false);
	      output = formatter.format(input.getTime());
	    }
	  }
	  
	  return output;
	}
	
	// reformats a datetime string according to the given patterns
	public static String format(String input, String inPattern, String outPattern) {
	  return emit(parse(input, inPattern), outPattern);
	}
	
	// reformats a datetime string according to the given patterns
	public static String format(String input, String[] inPatterns, String outPattern) {
	  return emit(parse(input, inPatterns), outPattern);
	}
	
	// returns the current datetime as an xml datetime string
	public static String now() {
	  return now("datetime");
	}
	
	// returns the current datetime as an datetime string formatted according to the given pattern
	public static String now(String pattern) {
	  return emit(java.util.Calendar.getInstance(), pattern);
	}
	
	// parses an xml datetime string and returns a java.util.Date object
	public static java.util.Calendar parse(String input) {
	  return parse(input, (String)null);
	}
	
	// parses a datetime string that adheres to the given pattern and returns a java.util.Date object
	public static java.util.Calendar parse(String input, String pattern) throws IllegalArgumentException {
	  if (pattern == null) pattern = DEFAULT_DATETIME_PATTERN;
	  
	  java.util.Calendar output = null;
	  
	  if (input != null) {
	    try {
	      if (pattern.equals("datetime") || pattern.equals("datetime.xml")) {
	        output = javax.xml.bind.DatatypeConverter.parseDateTime(input);
	      } else if (pattern.equals("datetime.jdbc")) {
	        output = java.util.Calendar.getInstance();
	        output.setTime(java.sql.Timestamp.valueOf(input));
	      } else if (pattern.equals("date") || pattern.equals("date.xml")) {
	        output = javax.xml.bind.DatatypeConverter.parseDate(input);
	      } else if (pattern.equals("time") || pattern.equals("time.xml")) {
	        output = javax.xml.bind.DatatypeConverter.parseTime(input);
	      } else if (pattern.equals("milliseconds")) {
	        output = java.util.Calendar.getInstance();
	        output.setTimeInMillis(Long.parseLong(input));
	      } else {
	        if (namedPatterns.containsKey(pattern)) pattern = namedPatterns.get(pattern);
	        
	        java.text.DateFormat formatter = new java.text.SimpleDateFormat(pattern);
	        formatter.setLenient(false);
	        output = java.util.Calendar.getInstance();
	        output.setTime(formatter.parse(input));
	      }
	    } catch (Exception ex) {
	      throw new IllegalArgumentException("Unparseable datetime: '" + input + "' does not conform to pattern '" + pattern + "'");
	    }
	  }
	  
	  return output;
	}
	
	// parses a datetime string that adheres to the given pattern and returns a java.util.Date object
	public static java.util.Calendar parse(String input, String... patterns) throws IllegalArgumentException {  
	  java.util.Calendar output = null;
	
	  if (patterns == null) patterns = new String[1];
	
	  java.util.List<Throwable> exceptions = new java.util.ArrayList<Throwable>();
	  
	  if (input != null) {
	    boolean parsed = false;
	    for (String pattern : patterns) {
	      try {
	        output = parse(input, pattern);
	        parsed = true;
	        break;
	      } catch (Exception ex) {
	        exceptions.add(ex);
	      }
	    }
	    if (!parsed) throw new IllegalArgumentException(tundra.exception.message(exceptions));
	  }
	  
	  return output;
	}
	
	// subtracts the given xml duration from the given xml datetime returning the result
	public static String subtract(String datetime, String duration) throws ServiceException {
	  return subtract(datetime, null, duration);
	}
	
	// subtracts the given xml duration from the given xml datetime returning the result
	public static String subtract(String datetime, String datetimePattern, String duration) throws ServiceException {
	  return subtract(datetime, datetimePattern, duration, null);
	}
	
	// subtracts the given xml duration from the given xml datetime returning the result
	public static String subtract(String datetime, String datetimePattern, String duration, String durationPattern) throws ServiceException {
	  return emit(subtract(parse(datetime, datetimePattern), tundra.duration.parse(duration, durationPattern)), datetimePattern);
	}
	
	// subtracts the given duration from the given datetime returning the result
	public static java.util.Calendar subtract(java.util.Calendar calendar, javax.xml.datatype.Duration duration) throws ServiceException {
	  if (duration != null) duration = duration.negate();
	  return add(calendar, duration);
	}
	
	// returns the current datetime minus the given XML duration
	public static String earlier(String duration) throws ServiceException {
	  return earlier(null, duration, null);
	}
	
	// returns the current datetime minus the given duration
	public static String earlier(String duration, String durationPattern) throws ServiceException {
	  return earlier(null, duration, durationPattern);
	}
	
	// returns the current datetime minus the given duration
	public static String earlier(String datetimePattern, String duration, String durationPattern) throws ServiceException {
	  return emit(earlier(tundra.duration.parse(duration, durationPattern)), datetimePattern);
	}
	
	// returns the current datetime minus the given duration
	public static java.util.Calendar earlier(javax.xml.datatype.Duration duration) throws ServiceException {
	  return subtract(java.util.Calendar.getInstance(), duration);
	}
	
	// returns the current datetime plus the given XML duration
	public static String later(String duration) throws ServiceException {
	  return later(null, duration, null);
	}
	
	// returns the current datetime plus the given duration
	public static String later(String duration, String durationPattern) throws ServiceException {
	  return later(null, duration, durationPattern);
	}
	
	// returns the current datetime plus the given duration
	public static String later(String datetimePattern, String duration, String durationPattern) throws ServiceException {
	  return emit(later(tundra.duration.parse(duration, durationPattern)), datetimePattern);
	}
	
	// returns the current datetime plus the given duration
	public static java.util.Calendar later(javax.xml.datatype.Duration duration) throws ServiceException {
	  return add(java.util.Calendar.getInstance(), duration);
	}
	// --- <<IS-END-SHARED>> ---
}

