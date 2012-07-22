package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-22 16:50:37.463
// -----( ON-HOST: 172.16.70.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class pipeline

{
	// ---( internal utility methods )---

	final static pipeline _instance = new pipeline();

	static pipeline _newInstance() { return new pipeline(); }

	static pipeline _cast(Object o) { return (pipeline)o; }

	// ---( server methods )---




	public static final void capture (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(capture)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $pipeline
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$pipeline", IDataUtil.clone(pipeline));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String key = IDataUtil.getString(cursor, "$key");
		  IDataUtil.put(cursor, "$value", tundra.document.get(pipeline, key));
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
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String key = IDataUtil.getString(cursor, "$key");
		  Object value = IDataUtil.get(cursor, "$value");
		
		  tundra.document.put(pipeline, key, value);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

