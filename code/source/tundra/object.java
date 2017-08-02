package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-08-02 21:15:03 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataMap;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.math.IntegerHelper;
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $mode {"missing","null"}
		// [o] object:0:optional $object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    String mode = IDataHelper.get(cursor, "$mode", String.class);
		
		    if (operands == null) {
		        Object x = IDataHelper.get(cursor, "$object.x");
		        Object y = IDataHelper.get(cursor, "$object.y");
		
		        IDataMap map = new IDataMap();
		        map.put("$object.x", x);
		        map.put("$object.y", y);
		        operands = map;
		    }
		
		    Object result = ObjectHelper.coalesce(IDataHelper.getLeaves(operands));
		
		    if (result != null || (mode != null && mode.equals("null"))) {
		        IDataHelper.put(cursor, "$object", result);
		    }
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
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $object
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$object");
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		    ObjectConvertMode mode = IDataHelper.get(cursor, "$mode", ObjectConvertMode.class);
		
		    IDataHelper.put(cursor, "$object", ObjectHelper.convert(object, charset, mode), false);
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
		// [o] field:0:required $equal? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object x = IDataHelper.get(cursor, "$object.x");
		    Object y = IDataHelper.get(cursor, "$object.y");
		
		    IDataHelper.put(cursor, "$equal?", equal(x, y), String.class);
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
		// [o] field:0:required $instance? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$object");
		    String className = IDataHelper.get(cursor, "$class", String.class);
		
		    IDataHelper.put(cursor, "$instance?", instance(object, Class.forName(className)), String.class);
		} catch(ClassNotFoundException ex) {
		    IDataHelper.put(cursor, "$instance?", "false");
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
		
		try {
		    IDataHelper.put(cursor, "$nothing", null);
		} finally {
		    cursor.destroy();
		}
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
		// [o] field:0:optional $array? {"false","true"}
		// [o] field:0:optional $primitive? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		try {
		    Object object = IDataHelper.get(cursor, "$object");
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    if (object == null && key != null) object = IDataHelper.get(pipeline, key);
		
		    IDataHelper.put(cursor, "$id", System.identityHashCode(object), String.class);
		    if (object != null) {
		        Class klass = object.getClass();
		        IDataHelper.put(cursor, "$class", klass.getName());
		        IDataHelper.put(cursor, "$array?", klass.isArray(), String.class);
		        IDataHelper.put(cursor, "$primitive?", primitive(object), String.class);
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
		    Object object = IDataHelper.get(cursor, "$object");
		    IDataHelper.put(cursor, "$string", ObjectHelper.stringify(object), false);
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
	
	// is the given object an instance of the given class?
	public static boolean instance(Object object, Class klass) {
	  return object != null && klass != null && klass.isInstance(object);
	}
	
	// converts a string, byte array or stream to a string, byte array or stream
	public static Object convert(Object object, String encoding, String mode) throws java.io.IOException {
	    return ObjectHelper.convert(object, encoding, mode);
	}
	// --- <<IS-END-SHARED>> ---
}

