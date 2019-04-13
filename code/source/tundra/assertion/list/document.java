package tundra.assertion.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-04-13 19:31:14 EST
// -----( ON-HOST: 192.168.20.19

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
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
		// [i] field:0:optional $strict? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] expected = IDataHelper.get(cursor, "$expected", IData[].class);
		    IData[] actual = IDataHelper.get(cursor, "$actual", IData[].class);
		    String message = IDataHelper.get(cursor, "$message", String.class);
		    boolean strict = IDataHelper.getOrDefault(cursor, "$strict?", Boolean.class, false);
		
		    equal(expected, actual, message, strict);
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
		// [i] record:1:required $expected
		// [i] record:1:required $actual
		// [i] field:0:optional $message
		// [i] field:0:optional $strict? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] expected = IDataHelper.get(cursor, "$expected", IData[].class);
		    IData[] actual = IDataHelper.get(cursor, "$actual", IData[].class);
		    String message = IDataHelper.get(cursor, "$message", String.class);
		    boolean strict = IDataHelper.getOrDefault(cursor, "$strict?", Boolean.class, false);
		
		    unequal(expected, actual, message, strict);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// asserts that two documents are equal
	public static void equal(IData[] expected, IData[] actual, String message, boolean strict) {
	    if (!strict) {
	        expected = IDataHelper.sort(expected, true, false);
	        actual = IDataHelper.sort(actual, true, false);
	    }
	
	    tundra.assertion.list.object.equal(expected, actual, message);
	}
	
	// asserts that two documents are equal
	public static void unequal(IData[] expected, IData[] actual, String message, boolean strict) {
	    if (!strict) {
	        expected = IDataHelper.sort(expected, true, false);
	        actual = IDataHelper.sort(actual, true, false);
	    }
	
	    tundra.assertion.list.object.unequal(expected, actual, message);
	}
	// --- <<IS-END-SHARED>> ---
}

