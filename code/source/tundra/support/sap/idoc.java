package tundra.support.sap;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-11-29T17:26:26.626
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.sap.conn.idoc.IDocDocument;
import com.sap.conn.idoc.IDocDocumentIterator;
import com.sap.conn.idoc.IDocDocumentList;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ExceptionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class idoc

{
	// ---( internal utility methods )---

	final static idoc _instance = new idoc();

	static idoc _newInstance() { return new idoc(); }

	static idoc _cast(Object o) { return (idoc)o; }

	// ---( server methods )---




	public static final void identify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(identify)>> ---
		// @sigtype java 3.5
		// [i] object:0:optional $list
		// [o] object:0:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDocDocumentList list = IDataHelper.get(cursor, "$list", IDocDocumentList.class);
		    IDataHelper.put(cursor, "$list", identify(list), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	public static IDocDocumentList identify(IDocDocumentList list) throws ServiceException {
	    try {
	        if (list != null) {
	            int index = 0;
	            IDocDocumentIterator iterator = list.iterator();
	            while (iterator.hasNext()) {
	                iterator.next().setIDocNumber(Integer.toString(index++));
	            }
	        }
	    } catch(Exception ex) {
	        ExceptionHelper.raise(ex);
	    }
	    return list;
	}
	// --- <<IS-END-SHARED>> ---
}

