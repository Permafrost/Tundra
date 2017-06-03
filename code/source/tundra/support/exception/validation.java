package tundra.support.exception;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-06-03 18:35:58 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.content.ValidationException;
import permafrost.tundra.data.IDataHelper;
// --- <<IS-END-IMPORTS>> ---

public final class validation

{
	// ---( internal utility methods )---

	final static validation _instance = new validation();

	static validation _newInstance() { return new validation(); }

	static validation _cast(Object o) { return (validation)o; }

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
		    throw new ValidationException(IDataHelper.get(cursor, "$message", String.class));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

