package tundra.support.test.document;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-09 14:06:53 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class squeeze

{
	// ---( internal utility methods )---

	final static squeeze _instance = new squeeze();

	static squeeze _newInstance() { return new squeeze(); }

	static squeeze _cast(Object o) { return (squeeze)o; }

	// ---( server methods )---




	public static final void make_idata_with_squeezable_values (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(make_idata_with_squeezable_values)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $document
		// [o] record:0:required $expected
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataFactory.create();
		    IDataCursor dc = document.getCursor();
		    dc.insertAfter("a", "test   ");
		    dc.insertAfter("b", "   test   ");
		    dc.insertAfter("c", "   test");
		    dc.insertAfter("d", "   ");
		    dc.insertAfter("e", "");
		    dc.insertAfter("f", null);
		    dc.destroy();
		    IDataUtil.put(cursor, "$document", document);
		
		    IData expected = IDataFactory.create();
		    IDataCursor ec = expected.getCursor();
		    ec.insertAfter("a", "test");
		    ec.insertAfter("b", "test");
		    ec.insertAfter("c", "test");
		    ec.destroy();
		    IDataUtil.put(cursor, "$expected", expected);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

