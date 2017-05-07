package tundra.assertion;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-03 13:08:26 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
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
		// [i] object:0:required $expected
		// [i] object:0:required $actual
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object expected = IDataHelper.get(cursor, "$expected");
		    Object actual = IDataHelper.get(cursor, "$actual");
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
		// [i] object:0:optional $object
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$object");
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    exist(object, message);
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
		// [i] object:0:required $object
		// [i] field:0:required $class
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$object");
		    String className = IDataHelper.get(cursor, "$class", String.class);
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    instance(object, className, message);
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
		// [i] object:0:optional $object
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$object");
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    nothing(object, message);
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
		// [i] object:0:required $expected
		// [i] object:0:required $actual
		// [i] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object expected = IDataHelper.get(cursor, "$expected");
		    Object actual = IDataHelper.get(cursor, "$actual");
		    String message = IDataHelper.get(cursor, "$message", String.class);
		
		    unequal(expected, actual, message);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// asserts that two objects are equal
	public static void equal(Object expected, Object actual, String message) {
	    if (!((expected == null && actual == null) || (expected != null && actual != null && expected.equals(actual)))) {
	        if (message == null) {
	            message = java.text.MessageFormat.format("Assertion failed: expected '{'{0}'}' is not equal to actual '{'{1}'}'", expected, actual);
	        } else {
	            message = java.text.MessageFormat.format("Assertion failed: {0} (expected '{'{1}'}' is not equal to actual '{'{2}'}')", message, expected, actual);
	        }
	        throw new AssertionError(message);
	    }
	}
	
	// asserts that two objects are not equal 
	public static void unequal(Object expected, Object actual, String message) {
	    if ((expected == null && actual == null) || (expected != null && actual != null && expected.equals(actual))) {
	        if (message == null) {
	            message = java.text.MessageFormat.format("Assertion failed: expected '{'{0}'}' is equal to actual '{'{1}'}'", expected, actual);
	        } else {
	            message = java.text.MessageFormat.format("Assertion failed: {0} (expected '{'{1}'}' is equal to actual '{'{2}'}')", message, expected, actual);
	        }
	        throw new AssertionError(message);
	    }
	}
	
	// asserts that the given object is an instance of the given class
	public static void instance(Object object, String className, String message) {
	    if (message == null) {
	        message = java.text.MessageFormat.format("Assertion failed: object '{'{0}'}' is not an instance of class '{'{1}'}'", object, className);
	    } else {
	        message = java.text.MessageFormat.format("Assertion failed: {0} (object '{'{1}'}' is not an instance of class '{'{2}'}')", message, object, className);
	    }
	
	    try {
	        Class klass = Class.forName(className);
	        if (!klass.isInstance(object)) {
	            throw new AssertionError(message);
	        }
	    } catch (ClassNotFoundException ex) {
	        throw new AssertionError(message);
	    }
	}
	
	// asserts that the given object is null
	public static void nothing(Object object, String message) {
	    if (object != null) {
	        if (message == null) {
	            message = java.text.MessageFormat.format("Assertion failed: object is not null '{'{0}'}'", object);
	        } else {
	            message = java.text.MessageFormat.format("Assertion failed: {0} (object is not null '{'{1}'}')", message, object);
	        }
	        throw new AssertionError(message);
	    }
	}
	
	// asserts that the given object is not null
	public static void exist(Object object, String message) {
	    if (object == null) {
	        if (message == null) {
	            message = java.text.MessageFormat.format("Assertion failed: object is null '{'{0}'}'", object);
	        } else {
	            message = java.text.MessageFormat.format("Assertion failed: {0} (object is null '{'{1}'}')", message, object);
	        }
	        throw new AssertionError(message);
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

