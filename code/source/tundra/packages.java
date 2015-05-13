package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-13 20:47:43 EST
// -----( ON-HOST: 172.16.167.128

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.server.PackageHelper;
// --- <<IS-END-IMPORTS>> ---

public final class packages

{
	// ---( internal utility methods )---

	final static packages _instance = new packages();

	static packages _newInstance() { return new packages(); }

	static packages _cast(Object o) { return (packages)o; }

	// ---( server methods )---




	public static final void reflect (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reflect)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional $package
		// [o] record:0:optional $package.properties
		// [o] - field:0:required name
		// [o] - field:0:required version
		// [o] - field:0:required enabled?
		// [o] - field:0:required system?
		// [o] - record:1:required dependencies
		// [o] -- field:0:required package
		// [o] -- field:0:required version
		// [o] - field:0:required dependencies.length
		// [o] - record:0:required services
		// [o] -- field:1:required startup
		// [o] -- field:0:required startup.length
		// [o] -- field:1:required shutdown
		// [o] -- field:0:required shutdown.length
		// [o] -- field:1:required replication
		// [o] -- field:0:required replication.length
		// [o] -- field:1:required loaded
		// [o] -- field:0:required loaded.length
		// [o] - record:0:required directories
		// [o] -- field:0:required root
		// [o] -- field:0:required ns
		// [o] -- field:0:required pub
		// [o] -- field:0:required template
		// [o] -- field:0:required web
		// [o] -- field:0:required config
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String packageName = IDataUtil.getString(cursor, "$package");
		
		    IData properties = PackageHelper.getPackageAsIData(packageName);
		    if (properties != null) IDataUtil.put(cursor, "$package.properties", properties);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

