package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-07-22 20:20:48 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ArrayHelper;
import permafrost.tundra.lang.ExceptionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class exception

{
	// ---( internal utility methods )---

	final static exception _instance = new exception();

	static exception _newInstance() { return new exception(); }

	static exception _cast(Object o) { return (exception)o; }

	// ---( server methods )---




	public static final void raise (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(raise)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $exceptions
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object[] exceptions = ArrayHelper.compact(IDataHelper.get(cursor, "$exceptions", Object[].class));
		
		    if (exceptions != null && exceptions.length > 0) {
		        ExceptionHelper.raise((Throwable[])ArrayHelper.normalize(exceptions));
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

