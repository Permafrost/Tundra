package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-04 20:22:29 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.lang.ThreadHelper;
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
		    IDataUtil.put(cursor, "$thread", ThreadHelper.getCurrentThreadAsIData());
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
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] threads = ThreadHelper.listThreadsAsIDataArray();
		    if (threads != null) {
		        IDataUtil.put(cursor, "$threads", threads);
		        IDataUtil.put(cursor, "$threads.length", "" + threads.length);
		    } else {
		    	IDataUtil.put(cursor, "$threads.length", "0");
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

