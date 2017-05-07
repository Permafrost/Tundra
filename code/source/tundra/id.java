package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-08 19:05:58 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.IdentityHelper;
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
		// [i] field:0:optional $mode {"string","base64"}
		// [o] field:0:required $id
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataHelper.put(cursor, "$id", IdentityHelper.generate(IDataHelper.get(cursor, "$mode", String.class)));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void normalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(normalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String input = IDataHelper.get(cursor, "$string", String.class);
		    String output = IdentityHelper.normalize(input);
		    IDataHelper.put(cursor, "$string", output, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

