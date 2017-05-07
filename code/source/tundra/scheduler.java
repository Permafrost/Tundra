package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-07 14:55:32 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.server.SchedulerHelper;
// --- <<IS-END-IMPORTS>> ---

public final class scheduler

{
	// ---( internal utility methods )---

	final static scheduler _instance = new scheduler();

	static scheduler _newInstance() { return new scheduler(); }

	static scheduler _cast(Object o) { return (scheduler)o; }

	// ---( server methods )---




	public static final void restart (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(restart)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		SchedulerHelper.restart();
		// --- <<IS-END>> ---

                
	}



	public static final void self (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(self)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $scheduler.self.name
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$scheduler.self.name", SchedulerHelper.self());
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		SchedulerHelper.start();
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		SchedulerHelper.stop();
		// --- <<IS-END>> ---

                
	}
}

