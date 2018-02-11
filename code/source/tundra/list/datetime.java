package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2018-01-31 21:17:24 EST
// -----( ON-HOST: 192.168.79.128

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.TimeZone;
import permafrost.tundra.data.IDataHelper;
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
		    IData[] datetimes = IDataHelper.get(cursor, "$datetimes", IData[].class);
		    String datetimePattern = IDataHelper.get(cursor, "$datetime.pattern", String.class);
		    String durationPattern = IDataHelper.get(cursor, "$duration.pattern", String.class);
		
		    if (datetimes != null) {
		        String[] durations = new String[datetimes.length];
		
		        for (int i = 0; i < datetimes.length; i++) {
		            if (datetimes[i] != null) {
		                IDataCursor dc = datetimes[i].getCursor();
		                String start = IDataHelper.get(dc, "start", String.class);
		                String end = IDataHelper.get(dc, "end", String.class);
		                dc.destroy();
		
		                durations[i] = DurationHelper.emit(DateTimeHelper.duration(DateTimeHelper.parse(start, datetimePattern), DateTimeHelper.parse(end, datetimePattern)), durationPattern);
		            }
		        }
		
		        IDataHelper.put(cursor, "$durations", durations, false);
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
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    String inPattern = IDataHelper.get(cursor, "$pattern.input", String.class);
		    String[] inPatterns = IDataHelper.get(cursor, "$patterns.input", String[].class);
		    String outPattern = IDataHelper.get(cursor, "$pattern.output", String.class);
		    TimeZone inTimeZone = IDataHelper.get(cursor, "$timezone.input", TimeZone.class);
		    TimeZone outTimeZone = IDataHelper.get(cursor, "$timezone.output", TimeZone.class);
		
		    if (list != null && list.length > 0) {
		        if (inPatterns == null) {
		            IDataHelper.put(cursor, "$list", DateTimeHelper.format(list, inPattern, inTimeZone, outPattern, outTimeZone));
		        } else {
		            IDataHelper.put(cursor, "$list", DateTimeHelper.format(list, inPatterns, inTimeZone, outPattern, outTimeZone));
		        }
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

