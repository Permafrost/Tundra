package tundra.cache;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-06 14:45:43 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.concurrent.ConcurrentMap;
import permafrost.tundra.data.ConcurrentMapIData;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.math.IntegerHelper;
// --- <<IS-END-IMPORTS>> ---

public final class memory

{
	// ---( internal utility methods )---

	final static memory _instance = new memory();

	static memory _newInstance() { return new memory(); }

	static memory _cast(Object o) { return (memory)o; }

	// ---( server methods )---




	public static final void all (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(all)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $cache.name
		// [o] record:0:optional $cache
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		    IDataHelper.put(cursor, "$cache", getCache(name), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $cache.name
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		
		    ConcurrentMap<String, Object> cache = CACHES.remove(name);
		    if (cache != null) cache.clear();
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
		// [i] field:0:required $cache.name
		// [i] field:0:required $cache.key
		// [o] field:0:optional $cache.key.exists? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		    String key = IDataHelper.get(cursor, "$cache.key", String.class);
		
		    ConcurrentMap<String, Object> cache = getCache(name);
		    boolean exists = false;
		    if (cache != null) exists = cache.containsKey(key);
		
		    IDataHelper.put(cursor, "$cache.key.exists?", exists, String.class);
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
		// [i] field:0:required $cache.name
		// [i] field:0:required $cache.key
		// [o] object:0:optional $cache.value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		    String key = IDataHelper.get(cursor, "$cache.key", String.class);
		
		    ConcurrentMap<String, Object> cache = getCache(name);
		    if (cache != null) {
		        IDataHelper.put(cursor, "$cache.value", cache.get(key), false);
		    }
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
		// [i] field:0:required $cache.name
		// [i] field:0:required $cache.key
		// [i] field:0:optional $cache.key.absent? {"false","true"}
		// [i] object:0:required $cache.value
		// [o] object:0:required $cache.value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		    String key = IDataHelper.get(cursor, "$cache.key", String.class);
		    boolean absent = IDataHelper.get(cursor, "$cache.key.absent?", Boolean.class, false);
		    Object value = IDataHelper.get(cursor, "$cache.value", Object.class);
		
		    ConcurrentMap<String, Object> cache = getCache(name, true);
		
		    if (absent) {
		        Object oldValue = cache.putIfAbsent(key, value);
		        if (oldValue != null) value = oldValue;
		    } else {
		        cache.put(key, value);
		    }
		
		    IDataHelper.put(cursor, "$cache.value", value, false);
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
		// [i] field:0:required $cache.name
		// [i] field:0:required $cache.key
		// [i] object:0:optional $cache.value
		// [o] field:0:required $cache.key.removed? {"false","true"}
		// [o] object:0:optional $cache.value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		    String key = IDataHelper.get(cursor, "$cache.key", String.class);
		    Object value = IDataHelper.get(cursor, "$cache.value", Object.class);
		   
		    ConcurrentMap<String, Object> cache = getCache(name);
		
		    boolean removed = false;
		
		    if (cache != null) {
		        if (value == null) {
		            value = cache.remove(key);
		            removed = value != null;
		        } else {
		            removed = cache.remove(key, value);
		        }
		    }
		
		    IDataHelper.put(cursor, "$cache.key.removed?", BooleanHelper.emit(removed));
		    if (removed) IDataHelper.put(cursor, "$cache.value", value, false);
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
		// [i] field:0:required $cache.name
		// [i] field:0:required $cache.key
		// [i] object:0:optional $cache.value.old
		// [i] object:0:required $cache.value.new
		// [o] field:0:required $cache.value.replaced? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		    String key = IDataHelper.get(cursor, "$cache.key", String.class);
		    Object oldValue = IDataHelper.get(cursor, "$cache.value.old", Object.class);
		    Object newValue = IDataHelper.get(cursor, "$cache.value.new", Object.class);
		
		    ConcurrentMap<String, Object> cache = getCache(name);
		
		    boolean replaced = false;
		
		    if (cache != null) {
		        if (oldValue == null) {
		            oldValue = cache.replace(key, newValue);
		            replaced = oldValue != null;
		        } else {
		            replaced = cache.replace(key, oldValue, newValue);
		        }
		    }
		
		    IDataHelper.put(cursor, "$cache.value.replaced?", replaced, String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * The map containing the caches.
	 */
	private static final ConcurrentMap<String, ConcurrentMap<String, Object>> CACHES = new ConcurrentMapIData<String, ConcurrentMap<String, Object>>();
	
	
	/**
	 * Returns the cache with the given name.
	 * 
	 * @param cacheName The name of the cache to be returned.
	 * @returns         The cache with the given name.
	 */
	public static ConcurrentMap<String, Object> getCache(String cacheName) {
	    return getCache(cacheName, false);
	}
	
	/**
	 * Returns the cache with the given name.
	 * 
	 * @param cacheName The name of the cache to be returned.
	 * @returns         The cache with the given name.
	 */
	public static ConcurrentMap<String, Object> getCache(String cacheName, boolean createIfAbsent) {
	    ConcurrentMap<String, Object> cache = null;
	
	    if (CACHES.containsKey(cacheName)) {
	        cache = CACHES.get(cacheName);
	    } else if (createIfAbsent) {
	        cache = new ConcurrentMapIData<String, Object>();
	        ConcurrentMap<String, Object> oldCache = CACHES.putIfAbsent(cacheName, cache);
	        if (oldCache != null) cache = oldCache;
	    }
	
	    return cache;
	}
	// --- <<IS-END-SHARED>> ---
}

