package tundra.support.http;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-10-07 21:17:10 EST
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




	public static final void shutdown (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(shutdown)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		HTTPCompressionContentHandlerFactory.unregister();
		// --- <<IS-END>> ---

                
	}



	public static final void startup (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(startup)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		HTTPCompressionContentHandlerFactory.register();
		// --- <<IS-END>> ---

                
	}
}

