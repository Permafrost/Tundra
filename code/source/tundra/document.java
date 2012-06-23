package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-06-22 13:58:45 EST
// -----( ON-HOST: 172.16.70.129

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




	public static final void equal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(equal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document.x
		// [i] record:0:optional $document.y
		// [o] field:0:required $equal?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData x = IDataUtil.getIData(cursor, "$document.x");
		  IData y = IDataUtil.getIData(cursor, "$document.y");
		
		  IDataUtil.put(cursor, "$equal?", "" + equal(x, y));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns whether two documents are equal
	public static boolean equal(IData x, IData y) {
	  return tundra.object.equal(x, y);
	}
	// --- <<IS-END-SHARED>> ---
}

