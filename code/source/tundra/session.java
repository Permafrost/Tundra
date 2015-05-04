package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-04 14:19:14 AEST
// -----( ON-HOST: PC62XKG2S.internal.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.server.SessionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class session

{
	// ---( internal utility methods )---

	final static session _instance = new session();

	static session _newInstance() { return new session(); }

	static session _cast(Object o) { return (session)o; }

	// ---( server methods )---




	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $session
		// [o] - field:0:required id
		// [o] - field:0:required name
		// [o] - field:0:required birth
		// [o] - field:0:required age
		// [o] - field:0:required timeout
		// [o] - field:0:required user
		// [o] - field:0:required invocations
		// [o] - record:0:required state
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IDataUtil.put(cursor, "$session", SessionHelper.getCurrentSessionAsIData());
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void put (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(put)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $key
		// [i] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String key = IDataUtil.getString(cursor, "$key");
		    Object value = IDataUtil.get(cursor, "$value");
		
		    SessionHelper.put(key, value);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void remove (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remove)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String key = IDataUtil.getString(cursor, "$key");
		    Object value = SessionHelper.remove(key);
		
		    if (value != null) IDataUtil.put(cursor, "$value", value);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

