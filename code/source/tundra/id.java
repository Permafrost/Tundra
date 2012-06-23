package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-05-12 17:03:21 EST
// -----( ON-HOST: 172.16.70.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class id

{
	// ---( internal utility methods )---

	final static id _instance = new id();

	static id _newInstance() { return new id(); }

	static id _cast(Object o) { return (id)o; }

	// ---( server methods )---




	public static final void generate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $id
		IDataCursor cursor = pipeline.getCursor();
		IDataUtil.put(cursor, "$id", "" + java.util.UUID.randomUUID());
		cursor.destroy();
		// --- <<IS-END>> ---

                
	}
}

