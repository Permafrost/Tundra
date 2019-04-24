package tundra.collection.map;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-04-24 14:48:35 GMT+10:00
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class document

{
	// ---( internal utility methods )---

	final static document _instance = new document();

	static document _newInstance() { return new document(); }

	static document _cast(Object o) { return (document)o; }

	// ---( server methods )---




	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		tundra.collection.map.object.clear(pipeline);
		// --- <<IS-END>> ---


	}



	public static final void create (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(create)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $sorted? {"false","true"}
		// [o] object:0:optional $map
		tundra.collection.map.object.create(pipeline, String.class, IData.class);
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
		tundra.collection.map.object.documentify(pipeline);
		// --- <<IS-END>> ---


	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] field:0:required $key
		// [o] field:0:required $key.exists? {"false","true"}
		// [o] record:0:optional $value
		tundra.collection.map.object.get(pipeline, String.class, IData.class);
		// --- <<IS-END>> ---


	}



	public static final void keys (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(keys)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [o] field:1:optional $keys
		tundra.collection.map.object.keys(pipeline);
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
		tundra.collection.map.object.length(pipeline);
		// --- <<IS-END>> ---


	}



	public static final void mapify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(mapify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $sorted? {"false","true"}
		// [o] object:0:optional $map
		tundra.collection.map.object.mapify(pipeline, IData.class);
		// --- <<IS-END>> ---


	}



	public static final void put (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(put)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] field:0:required $key
		// [i] field:0:optional $key.absent? {"false","true"}
		// [i] record:0:required $value
		// [o] object:0:required $map
		// [o] record:0:required $value
		tundra.collection.map.object.put(pipeline);
		// --- <<IS-END>> ---


	}



	public static final void remove (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remove)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] field:0:required $key
		// [i] record:0:optional $value
		// [o] field:0:required $key.removed? {"false","true"}
		// [o] record:0:optional $value
		tundra.collection.map.object.remove(pipeline);
		// --- <<IS-END>> ---


	}



	public static final void replace (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(replace)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] field:0:required $key
		// [i] record:0:optional $value.old
		// [i] record:0:required $value.new
		// [o] field:0:required $value.replaced? {"false","true"}
		tundra.collection.map.object.replace(pipeline);
		// --- <<IS-END>> ---


	}



	public static final void values (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(values)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [o] record:1:optional $values
		tundra.collection.map.object.values(pipeline);
		// --- <<IS-END>> ---


	}
}

