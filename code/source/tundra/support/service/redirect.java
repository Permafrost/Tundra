package tundra.support.service;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-08-01 10:56:42 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.server.service.RedirectServiceManager;
// --- <<IS-END-IMPORTS>> ---

public final class redirect

{
	// ---( internal utility methods )---

	final static redirect _instance = new redirect();

	static redirect _newInstance() { return new redirect(); }

	static redirect _cast(Object o) { return (redirect)o; }

	// ---( server methods )---




	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		RedirectServiceManager.getInstance().clear();
		// --- <<IS-END>> ---


	}



	public static final void reflect (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reflect)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $service.redirect.manager
		// [o] - object:0:required supervisor.started?
		// [o] - record:1:optional workers
		// [o] -- object:0:required started?
		// [o] -- record:0:required redirect
		// [o] --- field:0:required source
		// [o] --- field:0:optional target
		// [o] --- record:0:optional signature
		// [o] ---- record:0:optional input
		// [o] ---- record:0:optional output
		// [o] --- record:0:optional pipeline
		// [o] ---- record:0:optional input
		// [o] ---- record:0:optional output
		// [o] - object:0:required workers.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$service.redirect.manager", RedirectServiceManager.getInstance().getIData());
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
		RedirectServiceManager.getInstance().start();
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		RedirectServiceManager.getInstance().stop();
		// --- <<IS-END>> ---

                
	}
}

