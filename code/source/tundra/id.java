package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-01 18:34:24 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		  IDataUtil.put(cursor, "$id", IdentityHelper.generate(IDataUtil.getString(cursor, "$mode")));
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
		  String input = IDataUtil.getString(cursor, "$string");
		  String output = IdentityHelper.normalize(input);
		  if (output != null) IDataUtil.put(cursor, "$string", output);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

