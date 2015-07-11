package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-11 14:51:54 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectHelper;
// --- <<IS-END-IMPORTS>> ---

public final class object

{
	// ---( internal utility methods )---

	final static object _instance = new object();

	static object _newInstance() { return new object(); }

	static object _cast(Object o) { return (object)o; }

	// ---( server methods )---




	public static final void coalesce (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(coalesce)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $object.x
		// [i] object:0:optional $object.y
		// [i] field:0:optional $mode {"missing","null"}
		// [o] object:0:optional $object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object x = IDataUtil.get(cursor, "$object.x");
		    Object y = IDataUtil.get(cursor, "$object.y");
		    String mode = IDataUtil.getString(cursor, "$mode");
		
		    Object result = tundra.object.coalesce(x, y);
		
		    if (result != null || (mode != null && mode.equals("null"))) IDataUtil.put(cursor, "$object", result);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void convert (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(convert)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $object
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [i] field:0:optional $encoding
		// [o] object:0:optional $object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataUtil.get(cursor, "$object");
		    String charset = IDataUtil.getString(cursor, "$encoding");
		    String mode = IDataUtil.getString(cursor, "$mode");
		
		    IDataUtil.put(cursor, "$object", ObjectHelper.convert(object, charset, mode));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



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
		// [o] field:0:required $instance?
		IDataCursor cursor = pipeline.getCursor();
		try {
		    Object object = IDataUtil.get(cursor, "$object");
		    String klass = IDataUtil.getString(cursor, "$class");
		    IDataUtil.put(cursor, "$instance?", "" + (object != null && klass != null && instance(object, klass)));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void listify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(listify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $key
		// [i] record:0:optional $scope
		// [o] record:0:optional $scope
		tundra.document.listify(pipeline);
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
		// [i] field:0:optional $key
		// [o] field:0:required $id
		// [o] field:0:optional $class
		// [o] field:0:optional $array?
		// [o] field:0:optional $primitive?
		IDataCursor cursor = pipeline.getCursor();
		try {
		    Object object = IDataUtil.get(cursor, "$object");
		    String key = IDataUtil.getString(cursor, "$key");
		    if (object == null && key != null) object = IDataHelper.get(pipeline, key);
		
		    IDataUtil.put(cursor, "$id", "" + System.identityHashCode(object));
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
		    if (object != null) IDataUtil.put(cursor, "$string", ObjectHelper.stringify(object));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns the first non-null item from the given list
	public static <T> T coalesce(T x, T y) {
	    return x != null ? x : y;
	}
	
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
	
	// returns true if the given object is an instance of the given class
	public static boolean instance(Object object, String className) throws ServiceException {
	    boolean instance = false;
	    try {
	        instance = className != null && instance(object, Class.forName(className));
	    } catch(ClassNotFoundException ex) {
	        ExceptionHelper.raise(ex);
	    }
	    return instance;
	}
	
	// returns true if the given object is an instance of the given class
	public static boolean instance(Object object, Class klass) {
	    return object != null && klass != null && klass.isInstance(object);
	}
	
	// converts a string, byte array or stream to a string, byte array or stream
	public static Object convert(Object object, String encoding, String mode) throws java.io.IOException {
	    return ObjectHelper.convert(object, encoding, mode);
	}
	// --- <<IS-END-SHARED>> ---
}

