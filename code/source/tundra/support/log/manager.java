package tundra.support.log;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-09-02T05:47:46.241
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.server.ServerLogManager;
// --- <<IS-END-IMPORTS>> ---

public final class manager

{
	// ---( internal utility methods )---

	final static manager _instance = new manager();

	static manager _newInstance() { return new manager(); }

	static manager _cast(Object o) { return (manager)o; }

	// ---( server methods )---




	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ServerLogManager.getInstance().start();
		// --- <<IS-END>> ---


	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ServerLogManager.getInstance().stop();
		// --- <<IS-END>> ---


	}
}

