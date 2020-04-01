package tundra.http;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-04-01T17:39:08.010
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.net.http.HTTPHelper;
// --- <<IS-END-IMPORTS>> ---

public final class response

{
	// ---( internal utility methods )---

	final static response _instance = new response();

	static response _newInstance() { return new response(); }

	static response _cast(Object o) { return (response)o; }

	// ---( server methods )---




	public static final void accept (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(accept)>> ---
		// @specification tundra.schema.http.response:handler
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData response = IDataHelper.get(cursor, "$response", IData.class);
		    IDataHelper.put(cursor, "$response", HTTPHelper.acceptResponse(response), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void handle (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(handle)>> ---
		// @specification tundra.schema.http.response:handler
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData response = IDataHelper.get(cursor, "$response", IData.class);
		    IDataHelper.put(cursor, "$response", HTTPHelper.handleResponse(response), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



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
		    int code = IDataHelper.get(cursor, "$code", Integer.class);
		    IDataHelper.put(cursor, "$message", HTTPHelper.getResponseStatusMessage(code));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

