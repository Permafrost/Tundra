package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-06-26T09:07:02.541
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.server.UserHelper;
// --- <<IS-END-IMPORTS>> ---

public final class user

{
	// ---( internal utility methods )---

	final static user _instance = new user();

	static user _newInstance() { return new user(); }

	static user _cast(Object o) { return (user)o; }

	// ---( server methods )---




	public static final void current (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(current)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:optional $user
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDataHelper.put(cursor, "$user", UserHelper.getCurrentName(), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

