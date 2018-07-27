package tundra.support;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2018-07-28 08:02:41 GMT+10:00
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.server.PackageHelper;
import permafrost.tundra.server.TestHelper;
// --- <<IS-END-IMPORTS>> ---

public final class test

{
	// ---( internal utility methods )---

	final static test _instance = new test();

	static test _newInstance() { return new test(); }

	static test _cast(Object o) { return (test)o; }

	// ---( server methods )---




	public static final void execute (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(execute)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $package
		// [i] field:0:optional $concurrency
		// [o] record:0:optional $result
		// [o] - field:0:required package
		// [o] - field:0:required passed?
		// [o] - record:0:required counts
		// [o] -- field:0:required total
		// [o] -- field:0:required passed
		// [o] -- field:0:required failed
		// [o] - record:1:optional cases
		// [o] -- field:0:required description
		// [o] -- field:0:required passed?
		// [o] -- field:0:optional message
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String packageName = IDataHelper.get(cursor, "$package", String.class, true);
		    int concurrency = IDataHelper.getOrDefault(cursor, "$concurrency", Integer.class, 1);
		    IDataHelper.put(cursor, "$result", TestHelper.execute(PackageHelper.getPackage(packageName), concurrency));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

