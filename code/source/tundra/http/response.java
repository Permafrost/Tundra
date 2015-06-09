package tundra.http;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-05 07:55:44 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.net.http.HTTPHelper;
// --- <<IS-END-IMPORTS>> ---

public final class response

{
	// ---( internal utility methods )---

	final static response _instance = new response();

	static response _newInstance() { return new response(); }

	static response _cast(Object o) { return (response)o; }

	// ---( server methods )---




	public static final void status (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(status)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $code
		// [o] field:0:required $message
		IDataCursor cursor = pipeline.getCursor();

		try {
		    int code = Integer.parseInt(IDataUtil.getString(cursor, "$code"));
		    IDataUtil.put(cursor, "$message", HTTPHelper.getResponseStatusMessage(code));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

