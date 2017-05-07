package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-07 14:22:27 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import javax.xml.datatype.Duration;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ThreadHelper;
import permafrost.tundra.math.IntegerHelper;
// --- <<IS-END-IMPORTS>> ---

public final class thread

{
	// ---( internal utility methods )---

	final static thread _instance = new thread();

	static thread _newInstance() { return new thread(); }

	static thread _cast(Object o) { return (thread)o; }

	// ---( server methods )---




	public static final void current (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(current)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] recref:0:required $thread tundra.support.schema:thread
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$thread", ThreadHelper.toIData(ThreadHelper.current()));
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
		// [o] recref:1:required $threads tundra.support.schema:thread
		// [o] field:0:required $threads.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Thread[] threads = ThreadHelper.list();
		    if (threads != null) {
		        IDataHelper.put(cursor, "$threads", ThreadHelper.toIDataArray(threads));
		        IDataHelper.put(cursor, "$threads.length", threads.length, String.class);
		    } else {
		        IDataHelper.put(cursor, "$threads.length", "0");
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void sleep (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sleep)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $duration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Duration duration = IDataHelper.get(cursor, "$duration", Duration.class);
		    ThreadHelper.sleep(duration);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

