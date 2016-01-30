package tundra.collection.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-01-30 15:29:49 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.List;
import permafrost.tundra.collection.ListHelper;
import permafrost.tundra.data.IDataHelper;
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
		// [i] record:0:optional $items
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



	public static final void listify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(listify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $array
		// [i] field:0:optional $class
		// [o] object:0:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String className = IDataUtil.getString(cursor, "$class");
		    listify(pipeline, className == null ? Object.class : Class.forName(className));
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
	        IData items = IDataUtil.getIData(cursor, "$items");
	
	        if (items == null) {
	            T item = (T)IDataUtil.get(cursor, "$item");
	            list = ListHelper.append(list, item);
	        } else {
	            list = ListHelper.append(list, (T[])IDataHelper.getLeafValues(items, klass));
	        }
	
	        if (list != null) IDataUtil.put(cursor, "$list", list);
	    } finally {
	        cursor.destroy();
	    }
	}
	
	/**
	 * Returns a new array representation of the given java.util.List object.
	 * 
	 * @param pipeline The pipeline containing the list to be converted to an array.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void arrayify(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();
	
	    try {
	        List<T> list = (List<T>)IDataUtil.get(cursor, "$list");
	        if (list != null) IDataUtil.put(cursor, "$array", ListHelper.arrayify(list, klass));
	    } finally {
	        cursor.destroy();
	    }
	}
	
	
	/**
	 * Returns a new java.util.List representation of the given array.
	 * 
	 * @param pipeline The pipeline containing the array to be converted to a list.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void listify(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();
	
	    try {
	        T[] array = (T[])IDataUtil.getObjectArray(cursor, "$array");
	        if (array != null) IDataUtil.put(cursor, "$list", ListHelper.listify(array, klass));
	    } finally {
	        cursor.destroy();
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

