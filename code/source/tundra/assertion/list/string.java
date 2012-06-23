package tundra.assertion.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-06-22 14:08:15 EST
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

	// --- <<IS-START-SHARED>> ---
	// asserts that two documents are equal
	public static void equal(IData[] expected, IData[] actual, String message) {
	  if (!tundra.list.object.equal(expected, actual)) {
	    if (message == null) {
	      message = java.text.MessageFormat.format("Assertion failed: expected '{'{0}'}' is not equal to actual '{'{1}'}'", expected, actual);
	    } else {
	      message = java.text.MessageFormat.format("Assertion failed: {0} (expected '{'{1}'}' is not equal to actual '{'{2}'}')", message, expected, actual);
	    }
	    throw new AssertionError(message);
	  }
	}
	
	// asserts that two documents are not equal 
	public static void unequal(IData[] expected, IData[] actual, String message) {
	  if (tundra.list.object.equal(expected, actual)) {
	    if (message == null) {
	      message = java.text.MessageFormat.format("Assertion failed: expected '{'{0}'}' is equal to actual '{'{1}'}'", expected, actual);
	    } else {
	      message = java.text.MessageFormat.format("Assertion failed: {0} (expected '{'{1}'}' is equal to actual '{'{2}'}')", message, expected, actual);
	    }
	    throw new AssertionError(message);
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

