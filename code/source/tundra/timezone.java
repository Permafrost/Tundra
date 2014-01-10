package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-01-10 10:27:04.151
// -----( ON-HOST: EBZDEVWAP37.ebiztest.qr.com.au

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




	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $id
		// [i] field:0:optional $datetime
		// [o] record:0:optional $timezone
		// [o] - field:0:required id
		// [o] - field:0:required name
		// [o] - field:0:required description
		// [o] - field:0:required utc.offset
		// [o] - field:0:required dst.used?
		// [o] - field:0:required dst.active?
		// [o] - field:0:required dst.offset
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String id = IDataUtil.getString(cursor, "$id");
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		
		  IData timezone = get(id, datetime);
		
		  if (timezone != null) IDataUtil.put(cursor, "$timezone", timezone);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $datetime
		// [o] record:1:optional $timezones
		// [o] - field:0:required id
		// [o] - field:0:required name
		// [o] - field:0:required description
		// [o] - field:0:required utc.offset
		// [o] - field:0:required dst.used?
		// [o] - field:0:required dst.active?
		// [o] - field:0:required dst.offset
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  IData[] timezones = list(datetime);
		
		  if (timezones != null) IDataUtil.put(cursor, "$timezones", timezones);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void self (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(self)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $datetime
		// [o] record:0:optional $timezone
		// [o] - field:0:required id
		// [o] - field:0:required name
		// [o] - field:0:required description
		// [o] - field:0:required utc.offset
		// [o] - field:0:required dst.used?
		// [o] - field:0:required dst.active?
		// [o] - field:0:required dst.offset
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  IData timezone = getDefault(datetime);
		  if (timezone != null) IDataUtil.put(cursor, "$timezone", timezone);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns the time zone associated with the given ID in IData format
	public static IData get(String id, String datetime) {
	  return get(id, getInstant(datetime));
	}
	
	// returns the time zone associated with the given ID in IData format
	public static IData get(String id, java.util.Date instant) {
	  return toIData(java.util.TimeZone.getTimeZone(id), instant);
	}
	
	// returns the default time zone in IData format
	public static IData getDefault(String datetime) {
	  return getDefault(getInstant(datetime));
	}
	
	// returns the default time zone in IData format
	public static IData getDefault(java.util.Date instant) {
	  return toIData(java.util.TimeZone.getDefault(), instant);
	}
	
	// returns all known time zones in IData format
	public static IData[] list(String datetime) {
	  return list(getInstant(datetime));
	}
	
	// returns all known time zones in IData format
	public static IData[] list(java.util.Date instant) {
	  String[] id = java.util.TimeZone.getAvailableIDs();
	  IData[] zones = new IData[id.length];
	
	  for (int i = 0; i < id.length; i++) {
	    zones[i] = get(id[i], instant);
	  }
	
	  return zones;
	}
	
	// returns the given timezone in IData format
	protected static IData toIData(java.util.TimeZone timezone, java.util.Date instant) {
	  if (timezone == null) return null;
	
	  IData doc = IDataFactory.create();
	  IDataCursor cursor = doc.getCursor();
	
	  boolean dstActive = timezone.inDaylightTime(instant);
	
	  IDataUtil.put(cursor, "id", timezone.getID());
	  IDataUtil.put(cursor, "name", timezone.getDisplayName(dstActive, java.util.TimeZone.SHORT));
	  IDataUtil.put(cursor, "description", timezone.getDisplayName(dstActive, java.util.TimeZone.LONG));
	  IDataUtil.put(cursor, "utc.offset", tundra.duration.format("" + timezone.getOffset(instant.getTime()), "milliseconds", "xml"));
	  IDataUtil.put(cursor, "dst.used?", "" + timezone.useDaylightTime());
	  IDataUtil.put(cursor, "dst.active?", "" + dstActive);
	  IDataUtil.put(cursor, "dst.offset", tundra.duration.format("" + timezone.getDSTSavings(), "milliseconds", "xml"));
	
	  cursor.destroy();
	
	  return doc;
	}
	
	// converts a datetime string to java.util.Date object, or returns current date if null
	protected static java.util.Date getInstant(String datetime) {
	  java.util.Date instant = null;
	
	  if (datetime == null) {
	    instant = new java.util.Date();
	  } else {
	    instant = tundra.datetime.parse(datetime).getTime();
	  }
	
	  return instant;
	}
	// --- <<IS-END-SHARED>> ---
}

