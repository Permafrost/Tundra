package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-07 14:11:50 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.time.TimeZoneHelper;
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
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
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
		    String id = IDataHelper.get(cursor, "$id", String.class);
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String pattern = IDataHelper.get(cursor, "$datetime.pattern", String.class);
		
		    IData timezone = TimeZoneHelper.toIData(TimeZoneHelper.get(id), datetime, pattern);
		
		    IDataHelper.put(cursor, "$timezone", timezone, false);
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
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
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
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String pattern = IDataHelper.get(cursor, "$datetime.pattern", String.class);
		
		    IData[] timezones = TimeZoneHelper.toIDataArray(TimeZoneHelper.list(), datetime, pattern);
		
		    IDataHelper.put(cursor, "$timezones", timezones, false);
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
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
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
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String pattern = IDataHelper.get(cursor, "$datetime.pattern", String.class);
		
		    IData timezone = TimeZoneHelper.toIData(TimeZoneHelper.self(), datetime, pattern);
		
		    IDataHelper.put(cursor, "$timezone", timezone, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

