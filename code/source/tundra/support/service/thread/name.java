package tundra.support.service.thread;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-05-14 17:42:43 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.server.invoke.ThreadNameProcessor;
// --- <<IS-END-IMPORTS>> ---

public final class name

{
	// ---( internal utility methods )---

	final static name _instance = new name();

	static name _newInstance() { return new name(); }

	static name _cast(Object o) { return (name)o; }

	// ---( server methods )---




	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ThreadNameProcessor.getInstance().start();
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ThreadNameProcessor.getInstance().stop();
		// --- <<IS-END>> ---

                
	}
}

