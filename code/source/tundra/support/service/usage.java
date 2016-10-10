package tundra.support.service;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-10-10 18:04:22 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.ImmutableIData;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.server.invoke.ServiceUsageProcessor;
import permafrost.tundra.time.DateTimeHelper;
import permafrost.tundra.time.DurationHelper;
import permafrost.tundra.time.DurationPattern;
// --- <<IS-END-IMPORTS>> ---

public final class usage

{
	// ---( internal utility methods )---

	final static usage _instance = new usage();

	static usage _newInstance() { return new usage(); }

	static usage _cast(Object o) { return (usage)o; }

	// ---( server methods )---




	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $context
		// [o] - field:0:required monitoring.started?
		// [o] - field:0:optional monitoring.start
		// [o] - field:0:optional monitoring.duration
		// [o] - object:0:required invocations.started
		// [o] - object:0:required invocations.errored
		// [o] - record:1:required invocations.current
		// [o] -- object:0:required thread.id
		// [o] -- field:0:required thread.name
		// [o] -- object:0:required thread.object
		// [o] -- field:0:required thread.start
		// [o] -- field:0:required thread.duration
		// [o] -- record:1:optional callstack
		// [o] --- field:0:required service
		// [o] --- field:0:required package
		// [o] --- record:0:optional pipeline
		// [o] --- object:0:required pipeline.length
		// [o] --- field:0:required start
		// [o] --- field:0:required duration
		// [o] --- field:0:required session
		// [o] --- field:0:required user
		// [o] -- object:0:required callstack.length
		// [o] - object:0:required invocations.current.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataUtil.put(cursor, "$context", ServiceUsageProcessor.getInstance().getIData());
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
		ServiceUsageProcessor.getInstance().start();
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ServiceUsageProcessor.getInstance().stop();
		// --- <<IS-END>> ---

                
	}
}

