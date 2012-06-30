package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-06-30 15:27:05.652
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
		// [i] object:0:optional $object.x
		// [i] object:0:optional $object.y
		// [o] field:0:required $equal?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object x = IDataUtil.get(cursor, "$object.x");
		  Object y = IDataUtil.get(cursor, "$object.y");
		
		  IDataUtil.put(cursor, "$equal?", "" + equal(x, y));
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
		// [i] object:0:optional $object
		// [i] field:0:optional $class
		// [o] field:0:optional $instance?
		IDataCursor cursor = pipeline.getCursor();
		try {
		  Object object = IDataUtil.get(cursor, "$object");
		  String klass = IDataUtil.getString(cursor, "$class");
		  if (object != null && klass != null) {
		    IDataUtil.put(cursor, "$instance?", "" + Class.forName(klass).isInstance(object));
		  }
		} catch (ClassNotFoundException e) {
		  tundra.exception.raise(e);
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
		// [o] object:0:required $nothing
		IDataCursor cursor = pipeline.getCursor();
		IDataUtil.put(cursor, "$nothing", null);
		cursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void reflect (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reflect)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $object
		// [o] field:0:optional $class
		// [o] field:0:optional $array?
		// [o] field:0:optional $primitive?
		IDataCursor cursor = pipeline.getCursor();
		try {
		  Object object = IDataUtil.get(cursor, "$object");
		  if (object != null) {
		    Class klass = object.getClass();
		    IDataUtil.put(cursor, "$class", klass.getName());
		    IDataUtil.put(cursor, "$array?", "" + klass.isArray());
		    IDataUtil.put(cursor, "$primitive?", "" + primitive(object));
		  }
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void stringify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stringify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $object
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object object = IDataUtil.get(cursor, "$object");
		  if (object != null) IDataUtil.put(cursor, "$string", object.toString());
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns true if the two objects are equal
	public static boolean equal(Object x, Object y) {
	  boolean result = true;
	  if (x != null && y != null) {
	    if (x instanceof IData && y instanceof IData) {
	      result = IDataUtil.equals((IData)x, (IData)y);
	    } else {
	      result = x.equals(y);
	    }
	  } else {
	    result = (x == null && y == null);
	  }
	
	  return result;
	}
	
	// is the given object a primitive or an array of primitives?
	public static boolean primitive(Object object) {
	  if (object == null) return false;
	
	  Class klass = object.getClass();
	  return klass.isPrimitive() || (klass.isArray() && !(object instanceof Object[]));
	}
	// --- <<IS-END-SHARED>> ---
}

