package tundra.collection.map;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-10-05 15:30:06.392
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.collection.MapHelper;
import permafrost.tundra.lang.ExceptionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class document

{
	// ---( internal utility methods )---

	final static document _instance = new document();

	static document _newInstance() { return new document(); }

	static document _cast(Object o) { return (document)o; }

	// ---( server methods )---




	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $map
		// [i] field:0:required $key
		// [o] record:0:optional $value
		tundra.collection.map.object.get(pipeline, String.class, IData.class);
		// --- <<IS-END>> ---


	}



	public static final void mapify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(mapify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $sorted? {&quot;false&quot;,&quot;true&quot;}
		// [o] object:0:optional $map
		tundra.collection.map.object.mapify(pipeline, IData.class);
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * Returns a new Map representation of the given IData object.
	 *
	 * @param pipeline The pipeline containing the IData to be converted to a Map.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void mapify(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        IData document = IDataUtil.getIData(cursor, "$document");
	        if (document != null) IDataUtil.put(cursor, "$map", MapHelper.mapify(document, klass));
	    } finally {
	        cursor.destroy();
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

