package tundra.support.test.document;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-09-13 11:15:28 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class sort

{
	// ---( internal utility methods )---

	final static sort _instance = new sort();

	static sort _newInstance() { return new sort(); }

	static sort _cast(Object o) { return (sort)o; }

	// ---( server methods )---




	public static final void make_idata_with_duplicate_keys (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(make_idata_with_duplicate_keys)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $document
		// [o] record:0:required $expected
		IDataCursor cursor = pipeline.getCursor();

		try {
		  IData document = IDataFactory.create();
		  IDataCursor dc = document.getCursor();
		  dc.insertAfter("z", "1");
		  dc.insertAfter("a", "2");
		  dc.insertAfter("z", "3");
		  dc.destroy();
		  IDataUtil.put(cursor, "$document", document);

		  IData expected = IDataFactory.create();
		  IDataCursor ec = expected.getCursor();
		  ec.insertAfter("a", "2");
		  ec.insertAfter("z", "1");
		  ec.insertAfter("z", "3");
		  ec.destroy();
		  IDataUtil.put(cursor, "$expected", expected);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

