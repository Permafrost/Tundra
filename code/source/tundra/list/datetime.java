package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-09 14:52:48 AEST
// -----( ON-HOST: 192.168.66.129

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




	public static final void format (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(format)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $pattern.input {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:1:optional $patterns.input
		// [i] field:0:optional $pattern.output {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $timezone.input
		// [i] field:0:optional $timezone.output
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataUtil.getStringArray(cursor, "$list");
		    String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		    String[] inPatterns = IDataUtil.getStringArray(cursor, "$patterns.input");
		    String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		    String inTimeZone = IDataUtil.getString(cursor, "$timezone.input");
		    String outTimeZone = IDataUtil.getString(cursor, "$timezone.output");
		
		    if (list != null && list.length > 0) {
		        if (inPatterns == null) {
		            IDataUtil.put(cursor, "$list", format(list, inPattern, inTimeZone, outPattern, outTimeZone));
		        } else {
		            IDataUtil.put(cursor, "$list", format(list, inPatterns, inTimeZone, outPattern, outTimeZone));
		        }
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// formats a list datetime strings to the given pattern
	public static String[] format(String[] inputs, String inPattern, String inTimeZone, String outPattern, String outTimeZone) {
	    String[] outputs = null;
	    if (inputs != null) {
	        outputs = new String[inputs.length];
	        for (int i = 0; i < inputs.length; i++) {
	            outputs[i] = tundra.datetime.format(inputs[i], inPattern, inTimeZone, outPattern, outTimeZone);
	        }
	    }
	    return outputs;
	}
	
	// formats a list datetime strings to the given pattern
	public static String[] format(String[] inputs, String[] inPatterns, String inTimeZone, String outPattern, String outTimeZone) {
	    String[] outputs = null;
	    if (inputs != null) {
	        outputs = new String[inputs.length];
	        for (int i = 0; i < inputs.length; i++) {
	            outputs[i] = tundra.datetime.format(inputs[i], inPatterns, inTimeZone, outPattern, outTimeZone);
	        }
	    }
	    return outputs;
	}
	// --- <<IS-END-SHARED>> ---
}

