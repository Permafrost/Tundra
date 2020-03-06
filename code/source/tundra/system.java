package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-03-06T17:44:47.883
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.server.ServerLogger;
import permafrost.tundra.server.SystemHelper;
// --- <<IS-END-IMPORTS>> ---

public final class system

{
	// ---( internal utility methods )---

	final static system _instance = new system();

	static system _newInstance() { return new system(); }

	static system _cast(Object o) { return (system)o; }

	// ---( server methods )---




	public static final void reflect (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reflect)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $refresh? {"false","true"}
		// [o] record:0:required $system
		// [o] - field:0:required version
		// [o] - record:0:required environment
		// [o] - record:0:required property
		// [o] - record:0:optional global
		// [o] - record:0:required directory
		// [o] -- field:0:required root
		// [o] -- field:0:required config
		// [o] -- field:0:required datastore
		// [o] -- field:0:required jobs
		// [o] -- field:0:required lib
		// [o] -- field:0:required logs
		// [o] -- field:0:required packages
		// [o] -- field:0:required recycle
		// [o] -- field:0:required replicate
		// [o] -- field:0:required replicate.inbound
		// [o] -- field:0:required replicate.outbound
		// [o] -- field:0:required replicate.salvage
		// [o] - record:0:required memory
		// [o] -- field:0:required used
		// [o] -- field:0:required free
		// [o] -- field:0:required total
		IDataCursor cursor = pipeline.getCursor();

		try {
		    boolean refresh = IDataHelper.getOrDefault(cursor, "$refresh?", Boolean.class, false);
		    IDataHelper.put(cursor, "$system", SystemHelper.reflect(refresh));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	public static void log(IData pipeline) throws ServiceException {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        String message = IDataHelper.first(cursor, String.class, "$log.message", "$message");
	        String level = IDataHelper.first(cursor, String.class, "$log.level", "$level");
	        IData context = IDataHelper.get(cursor, "$log.context", IData.class);

	        ServerLogger.log(level, message, context);
	    } finally {
	        cursor.destroy();
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

