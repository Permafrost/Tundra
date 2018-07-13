package tundra.support.service;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2018-07-13 13:08:08 GMT+10:00
// -----( ON-HOST: -

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
		// [o] record:0:required $context
		// [o] - object:0:required sampling.started?
		// [o] - field:0:optional sampling.start
		// [o] - field:0:optional sampling.duration
		// [o] - record:1:required statistics
		// [o] -- field:0:required service
		// [o] -- object:0:required minimum
		// [o] -- field:0:required minimum.formatted
		// [o] -- object:0:required average
		// [o] -- field:0:required average.formatted
		// [o] -- object:0:required deviation.standard
		// [o] -- field:0:required deviation.standard.formatted
		// [o] -- object:0:required maximum
		// [o] -- field:0:required maximum.formatted
		// [o] -- object:0:required count
		// [o] -- field:0:required count.formatted
		// [o] - object:0:required statistics.length
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

