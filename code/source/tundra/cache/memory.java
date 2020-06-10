package tundra.cache;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-06-11T06:49:30.169
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Calendar;
import javax.xml.datatype.Duration;
import permafrost.tundra.cache.memory.CacheManager;
import permafrost.tundra.cache.memory.CacheManager.ExpiringValue;
import permafrost.tundra.data.MapIData;
import permafrost.tundra.data.IDataHelper;
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

		    MapIData<String, Object> cache = CacheManager.getInstance().all(name);

		    IDataHelper.put(cursor, "$cache", cache, false);
		    IDataHelper.put(cursor, "$cache.length", cache.size());
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
		    CacheManager.getInstance().clear(name);
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
		// [o] field:0:required $cache.key.exists? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		    String key = IDataHelper.get(cursor, "$cache.key", String.class);

		    boolean exists = CacheManager.getInstance().exists(name, key);

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
		// [o] field:0:optional $cache.expiry.datetime
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		    String key = IDataHelper.get(cursor, "$cache.key", String.class);

		    ExpiringValue value = CacheManager.getInstance().get(name, key);

		    if (value != null) {
		        IDataHelper.put(cursor, "$cache.value", value.getValue());
		        IDataHelper.put(cursor, "$cache.expiry.datetime", value.getExpiry(), String.class, false);
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
		// [i] field:0:optional $cache.expiry.duration
		// [i] field:0:optional $cache.expiry.datetime
		// [o] field:0:required $cache.updated?
		// [o] object:0:required $cache.value
		// [o] object:0:optional $cache.value.previous
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		    String key = IDataHelper.get(cursor, "$cache.key", String.class);
		    boolean absent = IDataHelper.getOrDefault(cursor, "$cache.key.absent?", Boolean.class, false);
		    Object value = IDataHelper.get(cursor, "$cache.value", Object.class);
		    Duration expiryDuration = IDataHelper.get(cursor, "$cache.expiry.duration", Duration.class);
		    Calendar expiryDateTime = IDataHelper.get(cursor, "$cache.expiry.datetime", Calendar.class);

		    ExpiringValue previousValue;

		    if (expiryDuration != null) {
		        previousValue = CacheManager.getInstance().put(name, key, value, expiryDuration, absent);
		    } else {
		        previousValue = CacheManager.getInstance().put(name, key, value, expiryDateTime, absent);
		    }

		    if (absent && previousValue != null) {
		        IDataHelper.put(cursor, "$cache.updated?", "false");
		        IDataHelper.put(cursor, "$cache.value", previousValue.getValue());
		    } else {
		        IDataHelper.put(cursor, "$cache.updated?", "true");
		        IDataHelper.put(cursor, "$cache.value", value);
		    }
		    if (previousValue != null) {
		        IDataHelper.put(cursor, "$cache.value.previous", previousValue.getValue());
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

		    value = CacheManager.getInstance().remove(name, key, value);

		    IDataHelper.put(cursor, "$cache.key.removed?", value != null, String.class);
		    IDataHelper.put(cursor, "$cache.value", value, false);
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
		// [i] field:0:optional $cache.expiry.duration
		// [i] field:0:optional $cache.expiry.datetime
		// [o] field:0:required $cache.value.replaced? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String name = IDataHelper.get(cursor, "$cache.name", String.class);
		    String key = IDataHelper.get(cursor, "$cache.key", String.class);
		    Object oldValue = IDataHelper.get(cursor, "$cache.value.old", Object.class);
		    Object newValue = IDataHelper.get(cursor, "$cache.value.new", Object.class);
		    Duration expiryDuration = IDataHelper.get(cursor, "$cache.expiry.duration", Duration.class);
		    Calendar expiryDateTime = IDataHelper.get(cursor, "$cache.expiry.datetime", Calendar.class);

		    boolean replaced;
		    if (expiryDuration != null) {
		        replaced = CacheManager.getInstance().replace(name, key, oldValue, newValue, expiryDuration);
		    } else {
		        replaced = CacheManager.getInstance().replace(name, key, oldValue, newValue, expiryDateTime);
		    }

		    IDataHelper.put(cursor, "$cache.value.replaced?", replaced, String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

