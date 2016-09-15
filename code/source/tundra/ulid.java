package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-09-15 16:50:20.969
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.id.ULID;
// --- <<IS-END-IMPORTS>> ---

public final class ulid

{
	// ---( internal utility methods )---

	final static ulid _instance = new ulid();

	static ulid _newInstance() { return new ulid(); }

	static ulid _cast(Object o) { return (ulid)o; }

	// ---( server methods )---




	public static final void generate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $id
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDataUtil.put(cursor, "$id", ULID.generate());
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

