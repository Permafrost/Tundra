package tundra.assertion;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-03 12:57:59 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ObjectHelper;
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
		// [i] record:0:required $expected
		// [i] record:0:required $actual
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData expected = IDataHelper.get(cursor, "$expected", IData.class);
		    IData actual = IDataHelper.get(cursor, "$actual", IData.class);
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    equal(expected, actual, message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void unequal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unequal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:required $expected
		// [i] record:0:required $actual
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData expected = IDataHelper.get(cursor, "$expected", IData.class);
		    IData actual = IDataHelper.get(cursor, "$actual", IData.class);
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    unequal(expected, actual, message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// asserts that two documents are equal
	public static void equal(IData expected, IData actual, String message) {
	    if (!ObjectHelper.equal(expected, actual)) {
	        if (message == null) {
	            message = java.text.MessageFormat.format("Assertion failed: expected '{'{0}'}' is not equal to actual '{'{1}'}'", expected, actual);
	        } else {
	            message = java.text.MessageFormat.format("Assertion failed: {0} (expected '{'{1}'}' is not equal to actual '{'{2}'}')", message, expected, actual);
	        }
	        throw new AssertionError(message);
	    }
	}
	
	// asserts that two documents are not equal 
	public static void unequal(IData expected, IData actual, String message) {
	    if (ObjectHelper.equal(expected, actual)) {
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

