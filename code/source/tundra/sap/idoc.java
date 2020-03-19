package tundra.sap;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-03-20T05:27:17.093
// -----( ON-HOST: PCLBKJP4M2.internal.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.sap.conn.idoc.IDocDocument;
import com.sap.conn.idoc.IDocDocumentIterator;
import com.sap.conn.idoc.IDocDocumentList;
import com.sap.conn.idoc.IDocException;
import com.sap.conn.idoc.IDocFactory;
import com.sap.conn.idoc.IDocRepository;
import com.sap.conn.idoc.jco.JCoIDoc;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.sap.IDocDocumentListHelper;
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
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $idoclist
		// [o] object:0:optional $idoclist
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDocDocumentList list = IDataHelper.get(cursor, "$idoclist", IDocDocumentList.class);
		    IDataHelper.put(cursor, "$idoclist", IDocDocumentListHelper.identify(list), false);
		} catch(Exception ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] object:0:optional $idoclist
		// [o] field:0:required $idoclist.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDocDocumentList list = IDataHelper.get(cursor, "$idoclist", IDocDocumentList.class);
		    IDataHelper.put(cursor, "$idoclist.length", list == null ? 0 : list.size(), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void partition (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(partition)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $idoclist
		// [i] field:0:optional $limit
		// [o] object:1:optional $idoclists
		// [o] field:0:required $idoclists.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDocDocumentList list = IDataHelper.get(cursor, "$idoclist", IDocDocumentList.class);
		    int limit = IDataHelper.getOrDefault(cursor, "$limit", Integer.class, 0);

		    IDocDocumentList[] table = IDocDocumentListHelper.partition(list, limit);

		    IDataHelper.put(cursor, "$idoclists", table, false);
		    IDataHelper.put(cursor, "$idoclists.length", table == null ? 0 : table.length, String.class);
		} catch(IDocException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

