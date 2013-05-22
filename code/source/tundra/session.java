package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-10-21 19:42:59 EST
// -----( ON-HOST: 172.16.189.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		  IDataUtil.put(cursor, "$session", get());
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
		
		  put(key, value);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static IData get() {
	  com.wm.app.b2b.server.Session session = com.wm.app.b2b.server.Service.getSession();
	
	  IData document = IDataFactory.create();
	  IDataCursor cursor = document.getCursor();
	  IDataUtil.put(cursor, "id", session.getSessionID());
	  IDataUtil.put(cursor, "name", session.getName());
	  java.util.Calendar birth = java.util.Calendar.getInstance();
	  birth.setTime(session.getStart());
	  IDataUtil.put(cursor, "birth", tundra.datetime.emit(birth));
	  IDataUtil.put(cursor, "age", tundra.duration.format("" + session.getAge(), "milliseconds", "xml"));
	  IDataUtil.put(cursor, "timeout", tundra.duration.format("" + session.getTimeout(), "milliseconds", "xml"));
	  IDataUtil.put(cursor, "user", session.getUser().getName());
	  IDataUtil.put(cursor, "invocations", "" + session.getCalls());
	  IDataUtil.put(cursor, "state", session);
	  cursor.destroy();
	
	  return document;
	}
	
	public static void put(String key, Object value) {
	  com.wm.app.b2b.server.Service.getSession().put(key, value);
	}
	// --- <<IS-END-SHARED>> ---
}

