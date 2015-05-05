package tundra.http;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-05 13:20:24 AEST
// -----( ON-HOST: PC62XKG2S.internal.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.net.http.route.HTTPRouter;
// --- <<IS-END-IMPORTS>> ---

public final class route

{
	// ---( internal utility methods )---

	final static route _instance = new route();

	static route _newInstance() { return new route(); }

	static route _cast(Object o) { return (route)o; }

	// ---( server methods )---




	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		HTTPRouter.getInstance().clear();
		// --- <<IS-END>> ---

                
	}



	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:1:required $routes
		// [o] - field:0:required directive
		// [o] - record:1:required routes
		// [o] -- field:0:required method
		// [o] -- field:0:required uri
		// [o] -- field:0:required target
		// [o] -- field:0:optional description
		// [o] -- field:0:optional source
		// [o] - field:0:required routes.length
		// [o] field:0:required $routes.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = HTTPRouter.getInstance().getRoutes().toIDataArray();
		    IDataUtil.put(cursor, "$routes",list);
		    IDataUtil.put(cursor, "$routes.length", "" + list.length);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void refresh (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(refresh)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		HTTPRouter.getInstance().refresh();
		// --- <<IS-END>> ---

                
	}
}

