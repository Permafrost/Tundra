package tundra.support.service;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2018-07-08 13:16:16 EST
// -----( ON-HOST: 192.168.20.13

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.server.invoke.ServiceStatisticsProcessor;
// --- <<IS-END-IMPORTS>> ---

public final class statistics

{
	// ---( internal utility methods )---

	final static statistics _instance = new statistics();

	static statistics _newInstance() { return new statistics(); }

	static statistics _cast(Object o) { return (statistics)o; }

	// ---( server methods )---




	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$context", ServiceStatisticsProcessor.getInstance().getIData());
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
		ServiceStatisticsProcessor.getInstance().start();
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ServiceStatisticsProcessor.getInstance().stop();
		// --- <<IS-END>> ---

                
	}
}

