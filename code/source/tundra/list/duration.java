package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-07 17:52:09 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.time.DurationHelper;
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
		// [i] field:0:optional $pattern.input {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","milliseconds","seconds","minutes","hours","days","weeks"}
		// [i] field:0:optional $datetime
		// [i] field:0:optional $datetime.pattern {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		    String datetime = IDataHelper.get(cursor, "$datetime", String.class);
		    String datetimePattern = IDataHelper.get(cursor, "$datetime.pattern", String.class);
		    
		    String[] output = DurationHelper.format(list, inPattern, outPattern, datetime, datetimePattern);
		
		    IDataHelper.put(cursor, "$list", output, false);
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
		// [i] field:0:optional $pattern.input {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		// [i] field:0:optional $pattern.output {"xml","milliseconds","seconds","minutes","hours","days","weeks"}
		// [o] field:0:required $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		
		    IDataHelper.put(cursor, "$duration", DurationHelper.emit(DurationHelper.add(DurationHelper.parse(list, inPattern)), outPattern));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

