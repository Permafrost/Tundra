package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-07-16 16:46:20 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.id.UUIDHelper;
import permafrost.tundra.lang.ObjectConvertMode;
// --- <<IS-END-IMPORTS>> ---

public final class uuid

{
	// ---( internal utility methods )---

	final static uuid _instance = new uuid();

	static uuid _newInstance() { return new uuid(); }

	static uuid _cast(Object o) { return (uuid)o; }

	// ---( server methods )---




	public static final void generate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(generate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $mode {"string","base64"}
		// [o] field:0:required $id
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$id", UUIDHelper.generate(IDataHelper.get(cursor, "$mode", ObjectConvertMode.class)));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

