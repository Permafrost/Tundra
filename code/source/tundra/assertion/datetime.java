package tundra.assertion;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-09-27T10:25:53.853
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.time.DateTimeHelper;
import java.text.MessageFormat;
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
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String expected = IDataHelper.get(cursor, "$expected", String.class);
		    String actual = IDataHelper.get(cursor, "$actual", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String message = IDataHelper.get(cursor, "$message", String.class);

		    if (DateTimeHelper.compare(expected, pattern, actual, pattern) != 0) {
		        if (message == null) {
		            message = MessageFormat.format("Assertion failed: expected '{'{0}'}' is not equal to actual '{'{1}'}'", expected, actual);
		        } else {
		            message = MessageFormat.format("Assertion failed: {0} (expected '{'{1}'}' is not equal to actual '{'{2}'}')", message, expected, actual);
		        }
		        throw new AssertionError(message);
		    }
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
		// [i] field:0:optional $pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String expected = IDataHelper.get(cursor, "$expected", String.class);
		    String actual = IDataHelper.get(cursor, "$actual", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String message = IDataHelper.get(cursor, "$message", String.class);

		    if (DateTimeHelper.compare(expected, pattern, actual, pattern) == 0) {
		        if (message == null) {
		            message = MessageFormat.format("Assertion failed: expected '{'{0}'}' is equal to actual '{'{1}'}'", expected, actual);
		        } else {
		            message = MessageFormat.format("Assertion failed: {0} (expected '{'{1}'}' is equal to actual '{'{2}'}')", message, expected, actual);
		        }
		        throw new AssertionError(message);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

