package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-07 17:39:10 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.InputOutputHelper;
import permafrost.tundra.lang.CharsetHelper;
// --- <<IS-END-IMPORTS>> ---

public final class constant

{
	// ---( internal utility methods )---

	final static constant _instance = new constant();

	static constant _newInstance() { return new constant(); }

	static constant _cast(Object o) { return (constant)o; }

	// ---( server methods )---




	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $tundra.constants
		// [o] - field:0:required encoding.default
		// [o] - object:0:required buffer.length.default
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData constants = IDataFactory.create();
		    IDataCursor cc = constants.getCursor();
		    IDataHelper.put(cc, "encoding.default", CharsetHelper.DEFAULT_CHARSET_NAME);
		    IDataHelper.put(cc, "buffer.length.default", InputOutputHelper.DEFAULT_BUFFER_SIZE);
		    cc.destroy();
		
		    IDataHelper.put(cursor, "$tundra.constants", constants);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

