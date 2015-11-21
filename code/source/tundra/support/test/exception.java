package tundra.support.test;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-11-20 18:21:15 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class exception

{
	// ---( internal utility methods )---

	final static exception _instance = new exception();

	static exception _newInstance() { return new exception(); }

	static exception _cast(Object o) { return (exception)o; }

	// ---( server methods )---




	public static final void create (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(create)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $message
		// [o] object:0:required $exception
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String message = IDataUtil.getString(cursor, "$message");
		    if (message == null) message = "";
		
		    IDataUtil.put(cursor, "$exception", new Exception(message));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

