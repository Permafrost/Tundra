package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-06-06 16:36:20.272
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.server.PackageHelper;
// --- <<IS-END-IMPORTS>> ---

public final class packages

{
	// ---( internal utility methods )---

	final static packages _instance = new packages();

	static packages _newInstance() { return new packages(); }

	static packages _cast(Object o) { return (packages)o; }

	// ---( server methods )---




	public static final void exists (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(exists)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional $name
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String packageName = IDataUtil.getString(cursor, "$name");
		    IDataUtil.put(cursor, "$exists?", BooleanHelper.emit(PackageHelper.exists(packageName)));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional $name
		// [o] record:0:optional $package
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
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String packageName = IDataUtil.getString(cursor, "$name");
		    IData document = PackageHelper.toIData(PackageHelper.getPackage(packageName));
		    boolean exists = document != null;
		    if (exists) IDataUtil.put(cursor, "$package", document);
		    IDataUtil.put(cursor, "$exists?", BooleanHelper.emit(exists));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @sigtype java 3.5
		// [i] field:0:optional $enabled? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:required $packages
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
		// [o] field:0:required $packages.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    boolean enabledOnly = BooleanHelper.parse(IDataUtil.getString(cursor, "$enabled?"));

		    IData[] list = PackageHelper.toIDataArray(PackageHelper.list(enabledOnly));

		    IDataUtil.put(cursor, "$packages", list);
		    IDataUtil.put(cursor, "$packages.length", IntegerHelper.emit(list.length));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void self (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(self)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:optional $package
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
		    boolean enabledOnly = BooleanHelper.parse(IDataUtil.getString(cursor, "$enabled?"));
		    IData self = PackageHelper.toIData(PackageHelper.self());
		    if (self != null) IDataUtil.put(cursor, "$package", self);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

