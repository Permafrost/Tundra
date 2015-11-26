package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-11-26 18:44:52 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.time.DateTimeHelper;
import permafrost.tundra.time.DurationHelper;
// --- <<IS-END-IMPORTS>> ---

public final class datetime

{
	// ---( internal utility methods )---

	final static datetime _instance = new datetime();

	static datetime _newInstance() { return new datetime(); }

	static datetime _cast(Object o) { return (datetime)o; }

	// ---( server methods )---




	public static final void duration (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(duration)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $datetimes
		// [i] - field:0:optional start
		// [i] - field:0:optional end
		// [i] field:0:optional $datetime.pattern {&quot;datetime&quot;,&quot;datetime.db2&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:0:optional $duration.pattern {&quot;xml&quot;,&quot;milliseconds&quot;,&quot;seconds&quot;,&quot;minutes&quot;,&quot;hours&quot;,&quot;days&quot;,&quot;weeks&quot;,&quot;months&quot;,&quot;years&quot;}
		// [o] field:1:optional $durations
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] datetimes = IDataUtil.getIDataArray(cursor, "$datetimes");
		    String datetimePattern = IDataUtil.getString(cursor, "$datetime.pattern");
		    String durationPattern = IDataUtil.getString(cursor, "$duration.pattern");
		
		    if (datetimes != null) {
		        String[] durations = new String[datetimes.length];
		
		        for (int i = 0; i < datetimes.length; i++) {
		            if (datetimes[i] != null) {
		                IDataCursor dc = datetimes[i].getCursor();
		                String start = IDataUtil.getString(dc, "start");
		                String end = IDataUtil.getString(dc, "end");
		                dc.destroy();
		
		                durations[i] = DurationHelper.emit(DateTimeHelper.duration(DateTimeHelper.parse(start, datetimePattern), DateTimeHelper.parse(end, datetimePattern)), durationPattern);
		            }
		        }
		
		        IDataUtil.put(cursor, "$durations", durations);
		    }
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
		// [i] field:1:optional $list
		// [i] field:0:optional $pattern.input {&quot;datetime&quot;,&quot;datetime.db2&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] field:1:optional $patterns.input
		// [i] field:0:optional $pattern.output {&quot;datetime&quot;,&quot;datetime.db2&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
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
		            IDataUtil.put(cursor, "$list", DateTimeHelper.format(list, inPattern, inTimeZone, outPattern, outTimeZone));
		        } else {
		            IDataUtil.put(cursor, "$list", DateTimeHelper.format(list, inPatterns, inTimeZone, outPattern, outTimeZone));
		        }
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

