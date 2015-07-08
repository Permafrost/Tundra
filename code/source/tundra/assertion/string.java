package tundra.assertion;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-08 21:05:37 AEST
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




	public static final void equal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(equal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $expected
		// [i] field:0:required $actual
		// [i] field:0:optional $message
		tundra.assertion.object.equal(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void unequal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unequal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $expected
		// [i] field:0:required $actual
		// [i] field:0:optional $message
		tundra.assertion.object.unequal(pipeline);
		// --- <<IS-END>> ---

                
	}
}

