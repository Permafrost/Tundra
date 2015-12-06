package tundra.assertion.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-12-07 08:05:28.059
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class document

{
	// ---( internal utility methods )---

	final static document _instance = new document();

	static document _newInstance() { return new document(); }

	static document _cast(Object o) { return (document)o; }

	// ---( server methods )---




	public static final void equal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(equal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:required $expected
		// [i] record:1:required $actual
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
		// [i] record:1:required $expected
		// [i] record:1:required $actual
		// [i] field:0:optional $message
		tundra.assertion.list.object.unequal(pipeline);
		// --- <<IS-END>> ---


	}
}

