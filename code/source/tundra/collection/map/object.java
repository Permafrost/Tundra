package tundra.collection.map;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-11-30 08:18:09 GMT+10:00
// -----( ON-HOST: PCO62XKG2S.internal.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import permafrost.tundra.collection.ConcurrentMapHelper;
import permafrost.tundra.collection.MapHelper;
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
		    Map map = (Map)IDataUtil.get(cursor, "$map");
		    if (map != null) MapHelper.clear(map);
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
		    String keyClass = IDataUtil.getString(cursor, "$key.class");
		    String valueClass = IDataUtil.getString(cursor, "$value.class");
		    create(pipeline, keyClass == null ? Object.class : Class.forName(keyClass), valueClass == null ? Object.class : Class.forName(valueClass));
		} catch (ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] object:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Map map = (Map)IDataUtil.get(cursor, "$map");
		    Object key = IDataUtil.get(cursor, "$key");
		
		    if (map != null) IDataUtil.put(cursor, "$value", MapHelper.get(map, key));
		} finally {
		    cursor.destroy();
		}
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
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Map map = (Map)IDataUtil.get(cursor, "$map");
		    if (map != null) IDataUtil.put(cursor, "$keys", MapHelper.keys(map));
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
		    Map map = (Map)IDataUtil.get(cursor, "$map");
		    if (map != null) IDataUtil.put(cursor, "$length", IntegerHelper.emit(MapHelper.length(map)));
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



	public static final void put (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(put)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] object:0:optional $key
		// [i] object:0:optional $value
		// [i] field:0:optional $mode {&quot;always&quot;,&quot;absent&quot;}
		// [o] object:0:optional $value.existing
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Map map = (Map)IDataUtil.get(cursor, "$map");
		    Object key = IDataUtil.get(cursor, "$key");
		    Object value = IDataUtil.get(cursor, "$value");
		    String mode = IDataUtil.getString(cursor, "$mode");
		
		    if (map != null && key != null) {
		        if (mode == null || mode.equals("always")) {
		            MapHelper.put(map, key, value);
		        } else {
		            Object existingValue = ConcurrentMapHelper.putIfAbsent((ConcurrentMap)map, key, value);
		            if (existingValue != null) IDataUtil.put(cursor, "$value.existing", existingValue);
		        }
		    }
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
		// [i] object:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Map map = (Map)IDataUtil.get(cursor, "$map");
		    Object key = IDataUtil.get(cursor, "$key");
		
		    if (map != null) IDataUtil.put(cursor, "$length", MapHelper.remove(map, key));
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
		    Map map = (Map)IDataUtil.get(cursor, "$map");
		    if (map != null) IDataUtil.put(cursor, "$values", MapHelper.values(map));
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
	        boolean sorted = BooleanHelper.parse(IDataUtil.get(cursor, "$sorted?"));
	        IDataUtil.put(cursor, "$map", ConcurrentMapHelper.create(sorted));
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

