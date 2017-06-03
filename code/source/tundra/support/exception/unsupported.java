package tundra.support.exception;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-06-03 18:36:08 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.content.UnsupportedException;
import permafrost.tundra.data.IDataHelper;
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
		    throw new UnsupportedException(IDataHelper.get(cursor, "$message", String.class));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

