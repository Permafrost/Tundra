package tundra.collection.map;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-09-30 05:45:20 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import permafrost.tundra.collection.ConcurrentMapHelper;
import permafrost.tundra.collection.MapHelper;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.math.IntegerHelper;
// --- <<IS-END-IMPORTS>> ---

public final class object

{
	// ---( internal utility methods )---

	final static object _instance = new object();

	static object _newInstance() { return new object(); }

	static object _cast(Object o) { return (object)o; }

	// ---( server methods )---




	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Map map = IDataHelper.get(cursor, "$map", Map.class);
		    MapHelper.clear(map);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void create (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(create)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $key.class
		// [i] field:0:optional $value.class
		// [i] field:0:optional $sorted? {&quot;false&quot;,&quot;true&quot;}
		// [o] object:0:optional $map
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class keyClass = IDataHelper.get(cursor, "$key.class", Class.class);
		    Class valueClass = IDataHelper.get(cursor, "$value.class", Class.class);

		    create(pipeline, keyClass == null ? Object.class : keyClass, valueClass == null ? Object.class : valueClass);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void documentify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(documentify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Map map = IDataHelper.get(cursor, "$map", Map.class);
		    IDataHelper.put(cursor, "$document", MapHelper.documentify(map), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] object:0:required $key
		// [o] field:0:required $key.exists? {&quot;false&quot;,&quot;true&quot;}
		// [o] object:0:optional $value
		get(pipeline, Object.class, Object.class);
		// --- <<IS-END>> ---


	}



	public static final void keys (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(keys)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [o] object:1:optional $keys
		// [o] field:0:required $keys.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Map map = IDataHelper.get(cursor, "$map", Map.class);

		    Object[] keys = MapHelper.keys(map);

		    IDataHelper.put(cursor, "$keys", keys, false);
		    IDataHelper.put(cursor, "$keys.length", keys == null ? 0 : keys.length, String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void length (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(length)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Map map = IDataHelper.get(cursor, "$map", Map.class);
		    IDataHelper.put(cursor, "$length", MapHelper.length(map), String.class);
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
		    Class valueClass = IDataHelper.get(cursor, "$value.class", Class.class);
		    mapify(pipeline, valueClass == null ? Object.class : valueClass);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void put (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(put)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] object:0:required $key
		// [i] field:0:optional $key.absent? {&quot;false&quot;,&quot;true&quot;}
		// [i] object:0:required $value
		// [o] object:0:required $map
		// [o] object:0:required $value
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Map map = IDataHelper.get(cursor, "$map", Map.class);
		    Object key = IDataHelper.get(cursor, "$key");
		    boolean keyAbsent = IDataHelper.getOrDefault(cursor, "$key.absent?", Boolean.class, false);
		    Object value = IDataHelper.get(cursor, "$value");

		    if (map == null) map = ConcurrentMapHelper.create();

		    if (keyAbsent) {
		        Object existingValue = ConcurrentMapHelper.putIfAbsent((ConcurrentMap)map, key, value);
		        if (existingValue != null) value = existingValue;
		    } else {
		        MapHelper.put(map, key, value);
		    }

		    IDataHelper.put(cursor, "$map", map);
		    IDataHelper.put(cursor, "$value", value);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void remove (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remove)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] object:0:required $key
		// [i] object:0:optional $value
		// [o] field:0:required $key.removed? {&quot;false&quot;,&quot;true&quot;}
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Map map = IDataHelper.get(cursor, "$map", Map.class);
		    Object key = IDataHelper.get(cursor, "$key");
		    Object value = IDataHelper.get(cursor, "$value");

		    boolean removed = true;

		    if (value == null) {
		        value = MapHelper.remove(map, key);
		    } else {
		        removed = ConcurrentMapHelper.remove((ConcurrentMap)map, key, value);
		    }

		    IDataHelper.put(cursor, "$key.removed?", removed, String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void replace (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(replace)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] object:0:required $key
		// [i] object:0:optional $value.old
		// [i] object:0:required $value.new
		// [o] field:0:required $value.replaced? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    ConcurrentMap map = IDataHelper.get(cursor, "$map", ConcurrentMap.class);
		    Object key = IDataHelper.get(cursor, "$key");
		    Object oldValue = IDataHelper.get(cursor, "$value.old");
		    Object newValue = IDataHelper.get(cursor, "$value.new");

		    if (oldValue == null) {
		        IDataHelper.put(cursor, "$value.replaced?", ConcurrentMapHelper.replace(map, key, newValue) != null, String.class);
		    } else {
		        IDataHelper.put(cursor, "$value.replaced?", ConcurrentMapHelper.replace(map, key, oldValue, newValue), String.class);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void values (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(values)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [o] object:1:optional $values
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Map map = IDataHelper.get(cursor, "$map", Map.class);
		    IDataHelper.put(cursor, "$values", MapHelper.values(map), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * Returns a new Map representation of the given IData object.
	 *
	 * @param pipeline   The pipeline containing the arguments to the method.
	 * @param keyClass   The class of keys stored in the Map.
	 * @param valueClass The class of values stored in the Map.
	 * @param <K>        The class of keys stored in the Map.
	 * @param <V>        The class of values stored in the Map.
	 */
	public static <K, V> void create(IData pipeline, Class<K> keyClass, Class<V> valueClass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        boolean sorted = IDataHelper.get(cursor, "$sorted?", Boolean.class);
	        IDataHelper.put(cursor, "$map", ConcurrentMapHelper.create(sorted));
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Returns the value associated with the given key in the given Map.
	 *
	 * @param pipeline   The pipeline containing the arguments to the method.
	 * @param keyClass   The class of keys stored in the Map.
	 * @param valueClass The class of values stored in the Map.
	 * @param <K>        The class of keys stored in the Map.
	 * @param <V>        The class of values stored in the Map.
	 */
	public static <K, V> void get(IData pipeline, Class<K> keyClass, Class<V> valueClass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        Map map = IDataHelper.get(cursor, "$map", Map.class);
	        K key = IDataHelper.get(cursor, "$key", keyClass);

	        V value = (V)MapHelper.get(map, key);

	        IDataHelper.put(cursor, "$key.exists?", value != null, String.class);
	        IDataHelper.put(cursor, "$value", value, false);
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
	        IData document = IDataHelper.get(cursor, "$document", IData.class);
	        boolean sorted = IDataHelper.getOrDefault(cursor, "$sorted?", Boolean.class, false);

	        IDataHelper.put(cursor, "$map", ConcurrentMapHelper.mapify(document, sorted, valueClass), false);
	    } finally {
	        cursor.destroy();
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

