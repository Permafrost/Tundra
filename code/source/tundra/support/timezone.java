package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-09-28 20:36:26 EST
// -----( ON-HOST: 172.16.189.176

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class timezone

{
	// ---( internal utility methods )---

	final static timezone _instance = new timezone();

	static timezone _newInstance() { return new timezone(); }

	static timezone _cast(Object o) { return (timezone)o; }

	// ---( server methods )---




	public static final void _ (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(_)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	protected static java.util.SortedSet<String> ZONES = new java.util.TreeSet(java.util.Arrays.asList(java.util.TimeZone.getAvailableIDs()));
	protected static java.util.regex.Pattern OFFSET_HHMM_PATTERN = java.util.regex.Pattern.compile("([\\+-])?(\\d?\\d):(\\d\\d)");
	protected static java.util.regex.Pattern OFFSET_XML_PATTERN = java.util.regex.Pattern.compile("-?P(\\d+|T\\d+).+");
	protected static java.util.regex.Pattern OFFSET_RAW_PATTERN = java.util.regex.Pattern.compile("[\\+-]?\\d+");
	
	// returns the time zone associated with the given ID
	public static java.util.TimeZone get(String id) {
	  if (id == null) return null;
	
	  if (id.equals("Z")) {
	    id = "UTC";
	  } else {
	    java.util.regex.Matcher matcher = OFFSET_HHMM_PATTERN.matcher(id);
	    if (matcher.matches()) {
	      String sign = matcher.group(1);
	      String hours = matcher.group(2);
	      String minutes = matcher.group(3);
	
	      int offset = Integer.parseInt(hours) * 60 * 60 * 1000 + Integer.parseInt(minutes) * 60 * 1000;
	      if (sign != null && sign.equals("-")) offset = offset * -1;
	
	      String candidate = get(offset);
	      if (candidate != null) id = candidate;      
	    } else {
	      matcher = OFFSET_XML_PATTERN.matcher(id);
	      if (matcher.matches()) {
	        try {
	          String candidate = get(Integer.parseInt(tundra.duration.format(id, "xml", "milliseconds")));
	          if (candidate != null) id = candidate;
	        } catch (NumberFormatException ex) {
	          // ignore
	        }
	      } else {
	        matcher = OFFSET_RAW_PATTERN.matcher(id);
	        if (matcher.matches()) {
	          // try parsing the id as a raw millisecond offset
	          try {
	            String candidate = get(Integer.parseInt(id));
	            if (candidate != null) id = candidate;
	          } catch (NumberFormatException ex) {
	            // ignore
	          }        
	        }
	      }
	    }
	  }
	  
	  java.util.TimeZone timezone = null;
	  if (ZONES.contains(id)) {
	    timezone = java.util.TimeZone.getTimeZone(id);
	  }
	  return timezone;
	}
	
	// returns the first matching timezone id for the given raw millisecond timezone offset 
	protected static String get(int offset) {
	  String id = null;
	  String[] candidates = java.util.TimeZone.getAvailableIDs(offset);
	  if (candidates != null && candidates.length > 0) id = candidates[0]; // default to the first candidate timezone ID        
	  return id;
	}
	
	// returns the default time zone
	public static java.util.TimeZone self() {
	  return java.util.TimeZone.getDefault();
	}
	
	// returns all known time zones
	public static java.util.TimeZone[] list() {
	  String[] id = java.util.TimeZone.getAvailableIDs();
	  java.util.TimeZone[] zones = new java.util.TimeZone[id.length];
	
	  for (int i = 0; i < id.length; i++) {
	    zones[i] = get(id[i]);
	  }
	
	  return zones;
	}
	
	// converts the given calendar to the given time zone
	public static java.util.Calendar convert(java.util.Calendar input, String zone) {
	  java.util.TimeZone timezone = get(zone);
	  if (timezone == null) throw new IllegalArgumentException("Unknown time zone specified: " + zone);
	
	  return convert(input, timezone);
	}
	
	// converts the given calendar to the given time zone
	public static java.util.Calendar convert(java.util.Calendar input, java.util.TimeZone zone) {
	  if (input == null || zone == null) return input;
	
	  java.util.Calendar output = java.util.Calendar.getInstance(zone);
	  output.setTimeInMillis(input.getTimeInMillis());
	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

