package tundra.support.exception;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-06-23 13:15:30 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.content.UnsupportedException;
// --- <<IS-END-IMPORTS>> ---

public final class unsupported

{
	// ---( internal utility methods )---

	final static unsupported _instance = new unsupported();

	static unsupported _newInstance() { return new unsupported(); }

	static unsupported _cast(Object o) { return (unsupported)o; }

	// ---( server methods )---




	public static final void raise (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(raise)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    throw new UnsupportedException(IDataUtil.getString(cursor, "$message"));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

