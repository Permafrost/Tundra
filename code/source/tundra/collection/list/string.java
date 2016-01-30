package tundra.collection.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-01-30 15:32:40 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class string

{
	// ---( internal utility methods )---

	final static string _instance = new string();

	static string _newInstance() { return new string(); }

	static string _cast(Object o) { return (string)o; }

	// ---( server methods )---




	public static final void append (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(append)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $list
		// [i] field:0:optional $item
		// [o] object:0:required $list
		tundra.collection.list.object.append(pipeline, String.class);
		// --- <<IS-END>> ---

                
	}



	public static final void arrayify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(arrayify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $list
		// [o] field:1:optional $array
		tundra.collection.list.object.arrayify(pipeline, String.class);
		// --- <<IS-END>> ---

                
	}



	public static final void listify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(listify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $list
		// [o] field:1:optional $array
		tundra.collection.list.object.listify(pipeline, String.class);
		// --- <<IS-END>> ---

                
	}
}

