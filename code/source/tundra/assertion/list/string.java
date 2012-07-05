package tundra.assertion.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-05 15:33:47.049
// -----( ON-HOST: 172.16.70.129

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




	public static final void equal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(equal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:required $expected
		// [i] field:1:required $actual
		// [i] field:0:optional $message
		tundra.assertion.list.object.equal(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void unequal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unequal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:required $expected
		// [i] field:1:required $actual
		// [i] field:0:optional $message
		tundra.assertion.list.object.unequal(pipeline);
		// --- <<IS-END>> ---

                
	}
}

