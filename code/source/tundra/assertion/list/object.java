package tundra.assertion.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-06-22 14:07:44 EST
// -----( ON-HOST: 172.16.70.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		  Object[] expected = IDataUtil.getObjectArray(cursor, "$expected");
		  Object[] actual = IDataUtil.getObjectArray(cursor, "$actual");
		  String message = IDataUtil.getString(cursor, "$message");
		
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
		  Object object = IDataUtil.get(cursor, "$list");
		  String message = IDataUtil.getString(cursor, "$message");
		  
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
		  Object object = IDataUtil.get(cursor, "$list");
		  String className = IDataUtil.getString(cursor, "$class");
		  String message = IDataUtil.getString(cursor, "$message");
		
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
		  Object object = IDataUtil.get(cursor, "$list");
		  String message = IDataUtil.getString(cursor, "$message");
		
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
		  Object[] expected = IDataUtil.getObjectArray(cursor, "$expected");
		  Object[] actual = IDataUtil.getObjectArray(cursor, "$actual");
		  String message = IDataUtil.getString(cursor, "$message");
		
		  unequal(expected, actual, message);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// asserts that two documents are equal
	public static void equal(Object[] expected, Object[] actual, String message) {
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
	public static void unequal(Object[] expected, Object[] actual, String message) {
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

