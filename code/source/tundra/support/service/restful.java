package tundra.support.service;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-03-16T18:00:00.000
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.server.invoke.RestServiceProcessor;
// --- <<IS-END-IMPORTS>> ---

public final class restful

{
	// ---( internal utility methods )---

	final static restful _instance = new restful();

	static restful _newInstance() { return new restful(); }

	static restful _cast(Object o) { return (restful)o; }

	// ---( server methods )---




	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		RestServiceProcessor.getInstance().start();
		// --- <<IS-END>> ---


	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		RestServiceProcessor.getInstance().stop();
		// --- <<IS-END>> ---


	}
}

