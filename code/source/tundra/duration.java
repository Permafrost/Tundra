package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-05-13 15:38:28 EST
// -----( ON-HOST: 172.16.70.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		// [o] field:0:required $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String x = IDataUtil.getString(cursor, "$duration.x");
		  String y = IDataUtil.getString(cursor, "$duration.y");
		  IDataUtil.put(cursor, "$duration", add(x, y));
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
		// [o] field:0:required $lesser?
		// [o] field:0:required $equal?
		// [o] field:0:required $greater?
		// [o] field:0:required $indeterminate?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String x = IDataUtil.getString(cursor, "$duration.x");
		  String y = IDataUtil.getString(cursor, "$duration.y");
		
		  int comparison = compare(x, y);
		
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
		// [i] field:0:optional $datetime
		// [i] field:0:optional $pattern.input {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [i] field:0:optional $pattern.output {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;english&quot;}
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String duration = IDataUtil.getString(cursor, "$duration");
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		  String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		  
		  IDataUtil.put(cursor, "$duration", format(duration, inPattern, outPattern, datetime));
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
		// [o] field:0:optional $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String x = IDataUtil.getString(cursor, "$duration.x");
		  String y = IDataUtil.getString(cursor, "$duration.y");
		  IDataUtil.put(cursor, "$duration", subtract(x, y));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void sum (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sum)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $durations
		// [o] field:0:required $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] durations = IDataUtil.getStringArray(cursor, "$durations");
		  IDataUtil.put(cursor, "$duration", add(durations));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static final String DEFAULT_DURATION_PATTERN = "xml";
	
	private static final long MILLISECONDS_PER_SECOND = 1000;
	private static final long MILLISECONDS_PER_MINUTE =   60 * MILLISECONDS_PER_SECOND;
	private static final long MILLISECONDS_PER_HOUR   =   60 * MILLISECONDS_PER_MINUTE;
	private static final long MILLISECONDS_PER_DAY    =   24 * MILLISECONDS_PER_HOUR;
	private static final long MILLISECONDS_PER_WEEK   =    7 * MILLISECONDS_PER_DAY;
	
	private static javax.xml.datatype.DatatypeFactory factory = null;
	
	// constructs a java xml duration factory
	private static javax.xml.datatype.DatatypeFactory factory() {
	  try {
	    if (factory == null) factory = javax.xml.datatype.DatatypeFactory.newInstance();
	  } catch (javax.xml.datatype.DatatypeConfigurationException ex) {
	    throw new RuntimeException(ex.getClass().getName() + ": " + ex.getMessage());
	  }
	  
	  return factory;
	}
	
	// formats a duration string to the desired pattern
	public static String format(String duration, String inPattern, String outPattern) {
	  return format(duration, inPattern, outPattern, null);
	}
	
	// formats a duration string to the desired pattern
	public static String format(String duration, String inPattern, String outPattern, String datetime) {
	  return emit(parse(duration, inPattern), outPattern, datetime);
	}
	
	// returns the sum of the given durations
	public static String add(String ... durations) {
	  javax.xml.datatype.Duration dz = factory().newDuration(0);
	  if (durations != null) {
	    for (int i = 0; i < durations.length; i++) {
	      javax.xml.datatype.Duration dx = parse(durations[i]);
	      if (dx != null) dz = dz.add(dx);
	    }
	  }
	  return emit(dz);
	}
	
	// subtracts one duration from another returning (x - y)
	public static String subtract(String x, String y) {
	  javax.xml.datatype.Duration dx = x == null? factory().newDuration(0) : parse(x);
	  javax.xml.datatype.Duration dy = y == null? factory().newDuration(0) : parse(y);
	  javax.xml.datatype.Duration dz = dx.subtract(dy);
	  return emit(dz);
	}
	
	// compares two durations, returning one of the following values:
	// - javax.xml.datatype.DatatypeConstants.LESSER if this Duration is shorter than duration parameter
	// - javax.xml.datatype.DatatypeConstants.EQUAL if this Duration is equal to duration parameter
	// - javax.xml.datatype.DatatypeConstants.GREATER if this Duration is longer than duration parameter
	// - javax.xml.datatype.DatatypeConstants.INDETERMINATE if a conclusive partial order relation cannot be determined
	public static int compare(String x, String y) {
	  if (x == null && y == null) return javax.xml.datatype.DatatypeConstants.EQUAL;
	  if (x == null || y == null) return javax.xml.datatype.DatatypeConstants.INDETERMINATE;
	  
	  return parse(x).compare(parse(y));
	}
	
	// returns a parsed xml duration string
	public static javax.xml.datatype.Duration parse(String duration) {
	  return parse(duration, null);
	}
	
	// returns a parsed duration string with the given pattern
	public static javax.xml.datatype.Duration parse(String input, String pattern) {
	  if (pattern == null) pattern = DEFAULT_DURATION_PATTERN;
	
	  java.math.BigInteger zero = new java.math.BigInteger("0");  
	  javax.xml.datatype.Duration output = null;
	  
	  if (input != null) {
	    if (pattern.equals("milliseconds")) {
	      output = factory().newDuration(Long.parseLong(input));
	    } else if (pattern.equals("seconds")) {
	      java.math.BigDecimal zero_ = new java.math.BigDecimal("0");
	      java.math.BigDecimal value = new java.math.BigDecimal(input);
	      output = factory().newDuration(value.compareTo(zero_) >= 0, null, null, null, null, null, value.abs());
	    } else if (pattern.equals("minutes")) {
	      java.math.BigInteger value = new java.math.BigInteger(input);
	      output = factory().newDuration(value.compareTo(zero) >= 0, null, null, null, null, value.abs(), null);
	    } else if (pattern.equals("hours")) {
	      java.math.BigInteger value = new java.math.BigInteger(input);
	      output = factory().newDuration(value.compareTo(zero) >= 0, null, null, null, value.abs(), null, null);
	    } else if (pattern.equals("days")) {
	      java.math.BigInteger value = new java.math.BigInteger(input);
	      output = factory().newDuration(value.compareTo(zero) >= 0, null, null, value.abs(), null, null, null);
	    } else if (pattern.equals("weeks")) {
	      // convert weeks to days by multiplying by 7
	      java.math.BigInteger value = (new java.math.BigInteger(input)).multiply(new java.math.BigInteger("7"));
	      output = factory().newDuration(value.compareTo(zero) >= 0, null, null, value.abs(), null, null, null);
	    } else if (pattern.equals("months")) {
	      java.math.BigInteger value = new java.math.BigInteger(input);
	      output = factory().newDuration(value.compareTo(zero) >= 0, null, value.abs(), null, null, null, null);
	    } else if (pattern.equals("years")) {
	      java.math.BigInteger value = new java.math.BigInteger(input);
	      output = factory().newDuration(value.compareTo(zero) >= 0, value.abs(), null, null, null, null, null);
	    } else if (pattern.equals("xml")) {
	      output = factory().newDuration(input);
	    } else {
	      throw new IllegalArgumentException("Unparseable pattern: " + pattern);
	    }
	  }
	
	  return output;
	}
	
	// returns an xml formatted duration string
	public static String emit(javax.xml.datatype.Duration input) {
	  return emit(input, null, null);
	}
	
	// returns an xml formatted duration string
	public static String emit(javax.xml.datatype.Duration input, String pattern) {
	  return emit(input, pattern, null);
	}
	
	// returns a formatted duration string for the given period
	private static String emit(javax.xml.datatype.Duration input, String pattern, String datetime) {
	  if (pattern == null) pattern = DEFAULT_DURATION_PATTERN;
	  
	  java.util.Date instant = null;
	  if (datetime == null) {
	    instant = new java.util.Date();
	  } else {
	    instant = javax.xml.bind.DatatypeConverter.parseDateTime(datetime).getTime();  
	  }
	  
	  String output = null;
	  
	  if (input != null) {
	    if (pattern.equals("milliseconds")) {
	      output = "" + input.getTimeInMillis(instant);
	    } else if (pattern.equals("seconds")) {
	      output = "" + (input.getTimeInMillis(instant) / MILLISECONDS_PER_SECOND);
	    } else if (pattern.equals("minutes")) {
	      output = "" + (input.getTimeInMillis(instant) / MILLISECONDS_PER_MINUTE);
	    } else if (pattern.equals("hours")) {
	      output = "" + (input.getTimeInMillis(instant) / MILLISECONDS_PER_HOUR);
	    } else if (pattern.equals("days")) {
	      output = "" + (input.getTimeInMillis(instant) / MILLISECONDS_PER_DAY);
	    } else if (pattern.equals("weeks")) {
	      output = "" + (input.getTimeInMillis(instant) / MILLISECONDS_PER_WEEK);
	    } else if (pattern.equals("xml")) {
	      output = input.toString();
	    } else if (pattern.equals("english")) {
	      String[] fields = new String[6];
	      fields[0] = format((java.math.BigInteger)input.getField(javax.xml.datatype.DatatypeConstants.YEARS), "year");
	      fields[1] = format((java.math.BigInteger)input.getField(javax.xml.datatype.DatatypeConstants.MONTHS), "month");
	      fields[2] = format((java.math.BigInteger)input.getField(javax.xml.datatype.DatatypeConstants.DAYS), "day");
	      fields[3] = format((java.math.BigInteger)input.getField(javax.xml.datatype.DatatypeConstants.HOURS), "hour");
	      fields[4] = format((java.math.BigInteger)input.getField(javax.xml.datatype.DatatypeConstants.MINUTES), "minute");
	      fields[5] = format((java.math.BigDecimal)input.getField(javax.xml.datatype.DatatypeConstants.SECONDS), "second");
	
	      StringBuffer buffer = new StringBuffer();
	      for (int i = 0; i < fields.length; i++) {
	       if (!fields[i].equals("")) {
	         if (buffer.length() > 0) buffer.append(", ");
	         buffer.append(fields[i]);
	       }
	      }
	      if (buffer.length() == 0) buffer.append("0 seconds");
	      
	      output = buffer.toString();
	    } else {
	      throw new IllegalArgumentException("Unparseable pattern: " + pattern);
	    }
	  }
	  
	  return output;
	}
	
	// returns an empty string if the given number is zero, otherwise
	// a string equal to the number and correctly pluralized units
	private static String format(java.math.BigInteger number, String unit) {
	  StringBuffer output = new StringBuffer();
	  if (number != null) {
	    java.math.BigInteger zero = new java.math.BigInteger("0");
	    if (number.compareTo(zero) != 0) {
	      output.append(number.toString());
	      output.append(" ");
	      output.append(pluralize(number, unit));
	    }
	  }
	  return output.toString();
	}
	
	// returns an empty string if the given number is zero, otherwise
	// a string equal to the number and correctly pluralized units
	private static String format(java.math.BigDecimal number, String unit) {
	  StringBuffer output = new StringBuffer();
	  if (number != null) {
	    java.math.BigDecimal zero = new java.math.BigDecimal("0");
	    if (number.compareTo(zero) != 0) {
	      output.append(number.toString());
	      output.append(" ");
	      output.append(pluralize(number, unit));
	    }
	  }
	  return output.toString();
	}
	
	// naive pluralization algorithm
	private static String pluralize(java.math.BigInteger number, String unit) {
	  java.math.BigInteger positiveOne = new java.math.BigInteger("1");
	  java.math.BigInteger negativeOne = new java.math.BigInteger("-1");
	
	  if (number.compareTo(positiveOne) != 0 && number.compareTo(negativeOne) != 0) unit = unit + "s";
	
	  return unit;
	}
	
	// naive pluralization algorithm
	private static String pluralize(java.math.BigDecimal number, String unit) {
	  java.math.BigDecimal positiveOne = new java.math.BigDecimal("1");
	  java.math.BigDecimal negativeOne = new java.math.BigDecimal("-1");
	
	  if (number.compareTo(positiveOne) != 0 && number.compareTo(negativeOne) != 0) unit = unit + "s";
	
	  return unit;
	}
	// --- <<IS-END-SHARED>> ---
}

