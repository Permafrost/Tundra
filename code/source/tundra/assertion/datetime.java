package tundra.assertion;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-21 15:24:16 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.time.DateTimeHelper;
// --- <<IS-END-IMPORTS>> ---

public final class datetime

{
	// ---( internal utility methods )---

	final static datetime _instance = new datetime();

	static datetime _newInstance() { return new datetime(); }

	static datetime _cast(Object o) { return (datetime)o; }

	// ---( server methods )---




	public static final void equal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(equal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $expected
		// [i] field:0:required $actual
		// [i] field:0:optional $pattern {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String expected = IDataUtil.getString(cursor, "$expected");
		    String actual = IDataUtil.getString(cursor, "$actual");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String message = IDataUtil.getString(cursor, "$message");
		
		    equal(expected, actual, pattern, message);
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
		// [i] field:0:required $expected
		// [i] field:0:required $actual
		// [i] field:0:optional $pattern {"datetime","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String expected = IDataUtil.getString(cursor, "$expected");
		    String actual = IDataUtil.getString(cursor, "$actual");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String message = IDataUtil.getString(cursor, "$message");
		
		    equal(expected, actual, pattern, message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// asserts that two datetime strings formatted according to the given pattern are equal
	public static void equal(String expected, String actual, String pattern, String message) {
	    tundra.assertion.object.equal(DateTimeHelper.parse(expected, pattern), DateTimeHelper.parse(actual, pattern), message);
	}
	
	// asserts that two datetime strings formatted according to the given pattern are not equal
	public static void unequal(String expected, String actual, String pattern, String message) {
	    tundra.assertion.object.unequal(DateTimeHelper.parse(expected, pattern), DateTimeHelper.parse(actual, pattern), message);
	}
	// --- <<IS-END-SHARED>> ---
}

