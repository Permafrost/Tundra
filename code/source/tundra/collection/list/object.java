package tundra.collection.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-01-29 14:31:53 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.List;
import permafrost.tundra.collection.ListHelper;
import permafrost.tundra.lang.ExceptionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class object

{
	// ---( internal utility methods )---

	final static object _instance = new object();

	static object _newInstance() { return new object(); }

	static object _cast(Object o) { return (object)o; }

	// ---( server methods )---




	public static final void append (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(append)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $list
		// [i] object:0:optional $item
		// [i] field:0:optional $class
		// [o] object:0:required $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String className = IDataUtil.getString(cursor, "$class");
		    append(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void arrayify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(arrayify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $list
		// [i] field:0:optional $class
		// [o] object:1:optional $array
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String className = IDataUtil.getString(cursor, "$class");
		    arrayify(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * Adds the given item to the given java.util.List.
	 * 
	 * @param pipeline The pipeline containing the list and item to be inserted.
	 * @param klass    The class of the item being appended.
	 * @param <T>      The class of the item being appended.
	 */
	public static <T> void append(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();
	
	    try {
	        List<T> list = (List<T>)IDataUtil.get(cursor, "$list");
	        T item = (T)IDataUtil.get(cursor, "$item");
	
	        list = ListHelper.append(list, item);
	
	        if (list != null) IDataUtil.put(cursor, "$list", list);
	    } finally {
	        cursor.destroy();
	    }
	}
	
	/**
	 * Returns a new array, with the given element inserted at the end.
	 * 
	 * @param pipeline The pipeline containing the list to be converted to an array.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void arrayify(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();
	
	    try {
	        List<T> list = (List<T>)IDataUtil.get(cursor, "$list");
	
	        T[] array = ListHelper.arrayify(list, klass);
	
	        if (array != null) IDataUtil.put(cursor, "$array", array);
	    } finally {
	        cursor.destroy();
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

