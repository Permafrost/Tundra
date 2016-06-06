package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-06-06 17:03:40.157
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDataUtil.put(cursor, "$thread", ThreadHelper.toIData(ThreadHelper.current()));
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
		// [o] field:0:required $threads.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Thread[] threads = ThreadHelper.list();
		    if (threads != null) {
		        IDataUtil.put(cursor, "$threads", ThreadHelper.toIDataArray(threads));
		        IDataUtil.put(cursor, "$threads.length", IntegerHelper.emit(threads.length));
		    } else {
		        IDataUtil.put(cursor, "$threads.length", "0");
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

