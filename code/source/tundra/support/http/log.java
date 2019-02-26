package tundra.support.http;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-02-28 18:04:50 GMT+10:00
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.server.http.handler.Logger;
import permafrost.tundra.server.http.handler.Manager;
// --- <<IS-END-IMPORTS>> ---

public final class log

{
	// ---( internal utility methods )---

	final static log _instance = new log();

	static log _newInstance() { return new log(); }

	static log _cast(Object o) { return (log)o; }

	// ---( server methods )---




	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		Manager manager = Manager.getInstance();
		Logger logger = Logger.getInstance();

		manager.start();
		manager.register(logger);
		logger.start();
		// --- <<IS-END>> ---


	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		Manager manager = Manager.getInstance();
		Logger logger = Logger.getInstance();

		logger.stop();
		manager.unregister(logger);
		manager.stop();
		// --- <<IS-END>> ---


	}
}

