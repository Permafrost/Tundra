package tundra.collection.map;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-10-05 15:28:32.969
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Map;
import permafrost.tundra.collection.ConcurrentMapHelper;
import permafrost.tundra.collection.MapHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class object

{
	// ---( internal utility methods )---

	final static object _instance = new object();

	static object _newInstance() { return new object(); }

	static object _cast(Object o) { return (object)o; }

	// ---( server methods )---




	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] object:0:required $key
		// [i] field:0:optional $key.class
		// [i] field:0:optional $value.class
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String keyClass = IDataUtil.getString(cursor, "$key.class");
		    String valueClass = IDataUtil.getString(cursor, "$value.class");
		    get(pipeline, keyClass == null ? String.class : Class.forName(keyClass), valueClass == null ? Object.class : Class.forName(valueClass));
		} catch (ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void mapify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(mapify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $value.class
		// [i] field:0:optional $sorted? {&quot;false&quot;,&quot;true&quot;}
		// [o] object:0:optional $map
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String className = IDataUtil.getString(cursor, "$value.class");
		    mapify(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * Returns the value associated with a key from a given Map.
	 *
	 * @param pipeline   The pipeline containing the arguments to the method.
	 * @param klass      The component type of the list.
	 * @param keyClass   The class of keys stored in the Map.
	 * @param valueClass The class of values stored in the Map.
	 * @param <K>        The class of keys stored in the Map.
	 * @param <V>        The class of values stored in the Map.
	 */
	public static <K, V> void get(IData pipeline, Class<K> keyClass, Class<V> valueClass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        Map<K, V> map = (Map<K, V>)IDataUtil.get(cursor, "$map");
	        K key = (K)IDataUtil.get(cursor, "$key");
	        if (map != null) IDataUtil.put(cursor, "$value", MapHelper.get(map, key));
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Returns a new Map representation of the given IData object.
	 *
	 * @param pipeline   The pipeline containing the arguments to the method.
	 * @param valueClass The class of values stored in the Map.
	 * @param <V>        The class of values stored in the Map.
	 */
	public static <V> void mapify(IData pipeline, Class<V> valueClass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        IData document = IDataUtil.getIData(cursor, "$document");
	        boolean sorted = BooleanHelper.parse(IDataUtil.get(cursor, "$sorted?"));
	        if (document != null) IDataUtil.put(cursor, "$map", ConcurrentMapHelper.mapify(document, sorted, valueClass));
	    } finally {
	        cursor.destroy();
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

