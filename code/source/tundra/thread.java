package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-07-01T07:04:48.105
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.math.BigDecimal;
import javax.xml.datatype.Duration;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ThreadHelper;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.time.DurationHelper;
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
		// [o] recref:0:required $thread tundra.schema:thread
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDataHelper.put(cursor, "$thread", ThreadHelper.toIData(ThreadHelper.current()));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $thread.id
		// [o] field:0:required $thread.exists?
		// [o] recref:0:optional $thread tundra.schema:thread
		IDataCursor cursor = pipeline.getCursor();

		try {
		    int identity = IDataHelper.get(cursor, "$thread.id", Integer.class);
		    Thread thread = ThreadHelper.get(identity);
		    IDataHelper.put(cursor, "$thread.exists?", thread != null, String.class);
		    IDataHelper.put(cursor, "$thread", ThreadHelper.toIData(thread), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void interrupt (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(interrupt)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $thread.id
		IDataCursor cursor = pipeline.getCursor();

		try {
		    int identity = IDataHelper.get(cursor, "$thread.id", Integer.class);
		    ThreadHelper.interrupt(ThreadHelper.get(identity));
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
		// [o] recref:1:required $threads tundra.schema:thread
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



	public static final void prioritize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(prioritize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $thread.priority
		// [o] field:0:required $thread.priority.previous
		IDataCursor cursor = pipeline.getCursor();

		try {
		    BigDecimal newPriority = IDataHelper.get(cursor, "$thread.priority", BigDecimal.class);

		    Thread currentThread = Thread.currentThread();
		    int previousPriority = currentThread.getPriority();
		    currentThread.setPriority(ThreadHelper.normalizePriority(newPriority.intValue()));

		    IDataHelper.put(cursor, "$thread.priority.previous", previousPriority, String.class);
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
		// [i] field:0:optional $duration.pattern {"xml","milliseconds","seconds","minutes","hours","days","weeks","months","years"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String duration = IDataHelper.get(cursor, "$duration", String.class);
		    String durationPattern = IDataHelper.get(cursor, "$duration.pattern", String.class);

		    ThreadHelper.sleep(DurationHelper.parse(duration, durationPattern));
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
		IDataCursor cursor = pipeline.getCursor();

		try {
		    int identity = IDataHelper.get(cursor, "$thread.id", Integer.class);
		    ThreadHelper.stop(ThreadHelper.get(identity));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

