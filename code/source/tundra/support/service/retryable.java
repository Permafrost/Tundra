package tundra.support.service;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-10-09 18:12:35 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.server.invoke.RetryableServiceProcessor;
// --- <<IS-END-IMPORTS>> ---

public final class retryable

{
	// ---( internal utility methods )---

	final static retryable _instance = new retryable();

	static retryable _newInstance() { return new retryable(); }

	static retryable _cast(Object o) { return (retryable)o; }

	// ---( server methods )---




	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		RetryableServiceProcessor.getInstance().start();
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		RetryableServiceProcessor.getInstance().stop();
		// --- <<IS-END>> ---

                
	}
}

