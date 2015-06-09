package tundra.assertion;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-05-02 20:45:11 EST
// -----( ON-HOST: -

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
		// [i] field:0:optional _expected
		// [i] field:0:optional _actual
		// [i] field:0:optional _message
		tundra.assertion.object.equal(pipeline);
		// --- <<IS-END>> ---


	}



	public static final void unequal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unequal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional _expected
		// [i] field:0:optional _actual
		// [i] field:0:optional _message
		tundra.assertion.object.unequal(pipeline);
		// --- <<IS-END>> ---


	}
}

