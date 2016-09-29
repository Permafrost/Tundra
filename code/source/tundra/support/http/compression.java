package tundra.support.http;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-09-29 12:50:13 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.server.content.HTTPCompressionContentHandlerFactory;
// --- <<IS-END-IMPORTS>> ---

public final class compression

{
	// ---( internal utility methods )---

	final static compression _instance = new compression();

	static compression _newInstance() { return new compression(); }

	static compression _cast(Object o) { return (compression)o; }

	// ---( server methods )---




	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		HTTPCompressionContentHandlerFactory.register();
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		HTTPCompressionContentHandlerFactory.unregister();
		// --- <<IS-END>> ---

                
	}
}

