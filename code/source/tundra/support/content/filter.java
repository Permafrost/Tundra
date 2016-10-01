package tundra.support.content;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-10-01 17:38:47 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.ArrayList;
import java.util.List;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.server.content.ChainedFilterContentHandlerFactory;
import permafrost.tundra.server.content.FilterContentHandlerFactory;
import permafrost.tundra.server.content.HTTPCompressionContentHandlerFactory;
import permafrost.tundra.server.content.LoggingContentHandlerFactory;
// --- <<IS-END-IMPORTS>> ---

public final class filter

{
	// ---( internal utility methods )---

	final static filter _instance = new filter();

	static filter _newInstance() { return new filter(); }

	static filter _cast(Object o) { return (filter)o; }

	// ---( server methods )---




	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ChainedFilterContentHandlerFactory.register(
		    HTTPCompressionContentHandlerFactory.getInstance(),
		    LoggingContentHandlerFactory.getInstance()
		);
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		ChainedFilterContentHandlerFactory.unregister();
		// --- <<IS-END>> ---

                
	}
}

