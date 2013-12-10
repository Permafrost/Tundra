package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-12-11 08:53:50.725
// -----( ON-HOST: EBZDEVWAP37.ebiztest.qr.com.au

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




	public static final void format (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(format)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $datetime
		// [i] field:0:optional $pattern.input {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [i] field:0:optional $pattern.output {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;}
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$list");
		  String datetime = IDataUtil.getString(cursor, "$datetime");
		  String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		  String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		  
		  IDataUtil.put(cursor, "$list", format(list, inPattern, outPattern, datetime));
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
		// [i] field:1:optional $list
		// [o] field:0:required $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] list = IDataUtil.getStringArray(cursor, "$list");
		  IDataUtil.put(cursor, "$duration", sum(list));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// formats a list of duration strings to the desired pattern
	public static String[] format(String[] durations, String inPattern, String outPattern) {
	  return tundra.duration.format(durations, inPattern, outPattern);
	}
	
	// formats a list of duration strings to the desired pattern
	public static String[] format(String[] durations, String inPattern, String outPattern, String datetime) {
	  return tundra.duration.format(durations, inPattern, outPattern, datetime);
	}
	
	// returns the sum of the given durations
	public static String sum(String[] durations, String pattern) {
	  return tundra.duration.add(durations, pattern);
	}
	
	// returns the sum of the given durations
	public static String sum(String[] durations) {
	  return tundra.duration.add(durations);
	}
	// --- <<IS-END-SHARED>> ---
}

