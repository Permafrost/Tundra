package tundra.support.message.trigger;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-10-06 19:55:46 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.regex.Pattern;
import permafrost.tundra.server.invoke.TriggerRetryProcessor;
// --- <<IS-END-IMPORTS>> ---

public final class retry

{
	// ---( internal utility methods )---

	final static retry _instance = new retry();

	static retry _newInstance() { return new retry(); }

	static retry _cast(Object o) { return (retry)o; }

	// ---( server methods )---




	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $pattern.include
		// [i] field:0:optional $pattern.exclude
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String includePattern = IDataUtil.getString(cursor, "$pattern.include");
		    String excludePattern = IDataUtil.getString(cursor, "$pattern.exclude");
		
		    TriggerRetryProcessor processor = TriggerRetryProcessor.getInstance();
		    processor.setIncludePattern(includePattern == null ? null : Pattern.compile(includePattern));
		    processor.setExcludePattern(excludePattern == null ? null : Pattern.compile(excludePattern));
		    processor.start();
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		TriggerRetryProcessor.getInstance().stop();
		// --- <<IS-END>> ---

                
	}
}

