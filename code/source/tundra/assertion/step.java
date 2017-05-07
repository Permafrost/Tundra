package tundra.assertion;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-03 13:08:55 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
// --- <<IS-END-IMPORTS>> ---

public final class step

{
	// ---( internal utility methods )---

	final static step _instance = new step();

	static step _newInstance() { return new step(); }

	static step _cast(Object o) { return (step)o; }

	// ---( server methods )---




	public static final void unreached (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unreached)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		try {
		    String message  = IDataHelper.get(cursor, "$message", String.class);
		    unreached(message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// asserts that two objects are equal
	public static void unreached(String message) {
	    if (message == null) {
	        message = "Assertion failed: step reached";
	    } else {
	        message = java.text.MessageFormat.format("Assertion failed: {0} (step reached)", message);
	    }
	    throw new AssertionError(message);
	}
	// --- <<IS-END-SHARED>> ---
}

