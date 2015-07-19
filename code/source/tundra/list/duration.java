package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-19 19:12:50 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		    String[] list = IDataUtil.getStringArray(cursor, "$list");
		    String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		    String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		    String datetime = IDataUtil.getString(cursor, "$datetime");
		    String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		    
		    String[] output = DurationHelper.format(list, inPattern, outPattern, datetime, datetimePattern);
		
		    if (output != null) IDataUtil.put(cursor, "$list", output);
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
		    String[] list = IDataUtil.getStringArray(cursor, "$list");
		    String inPattern = IDataUtil.getString(cursor, "$pattern.input");
		    String outPattern = IDataUtil.getString(cursor, "$pattern.output");
		
		    IDataUtil.put(cursor, "$duration", DurationHelper.emit(DurationHelper.add(DurationHelper.parse(list, inPattern)), outPattern));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

