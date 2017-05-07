package tundra.assertion.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-03 13:03:10 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ArrayHelper;
import permafrost.tundra.lang.ObjectHelper;
// --- <<IS-END-IMPORTS>> ---

public final class object

{
	// ---( internal utility methods )---

	final static object _instance = new object();

	static object _newInstance() { return new object(); }

	static object _cast(Object o) { return (object)o; }

	// ---( server methods )---




	public static final void equal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(equal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:required $expected
		// [i] object:1:required $actual
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object[] expected = IDataHelper.get(cursor, "$expected", Object[].class);
		    Object[] actual = IDataHelper.get(cursor, "$actual", Object[].class);
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    equal(expected, actual, message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void exists (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(exists)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$list");
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    tundra.assertion.object.exist(object, message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void instance (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(instance)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:required $list
		// [i] field:0:required $class
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$list");
		    String className = IDataHelper.get(cursor, "$class", String.class);
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    tundra.assertion.object.instance(object, className, message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void nothing (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(nothing)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$list");
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    tundra.assertion.object.nothing(object, message);
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
		// [i] object:1:required $expected
		// [i] object:1:required $actual
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object[] expected = IDataHelper.get(cursor, "$expected", Object[].class);
		    Object[] actual = IDataHelper.get(cursor, "$actual", Object[].class);
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    unequal(expected, actual, message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// asserts that two documents are equal
	public static void equal(Object[] expected, Object[] actual, String message) {
	    if (!ArrayHelper.equal(expected, actual)) {
	        if (message == null) {
	            message = java.text.MessageFormat.format("Assertion failed: expected '{'{0}'}' is not equal to actual '{'{1}'}'", ObjectHelper.stringify(expected), ObjectHelper.stringify(actual));
	        } else {
	            message = java.text.MessageFormat.format("Assertion failed: {0} (expected '{'{1}'}' is not equal to actual '{'{2}'}')", message, ObjectHelper.stringify(expected), ObjectHelper.stringify(actual));
	        }
	        throw new AssertionError(message);
	    }
	}
	
	// asserts that two documents are not equal 
	public static void unequal(Object[] expected, Object[] actual, String message) {
	    if (ArrayHelper.equal(expected, actual)) {
	        if (message == null) {
	            message = java.text.MessageFormat.format("Assertion failed: expected '{'{0}'}' is equal to actual '{'{1}'}'", ObjectHelper.stringify(expected), ObjectHelper.stringify(actual));
	        } else {
	            message = java.text.MessageFormat.format("Assertion failed: {0} (expected '{'{1}'}' is equal to actual '{'{2}'}')", message, ObjectHelper.stringify(expected), ObjectHelper.stringify(actual));
	        }
	        throw new AssertionError(message);
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

