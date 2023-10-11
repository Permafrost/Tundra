package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2023-08-17 12:32:10 EST
// -----( ON-HOST: PCL39264Q2.internal.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
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
		// [i] field:0:optional $package.name
		// [o] field:0:required $package.exists? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    boolean backwardsCompatible = false;
		    String packageName = IDataHelper.get(cursor, "$package.name", String.class);
		    if (packageName == null) {
		        packageName = IDataHelper.get(cursor, "$name", String.class);
		        backwardsCompatible = packageName != null;
		    }
		    IDataHelper.put(cursor, "$package.exists?", PackageHelper.exists(packageName), String.class);
		    if (backwardsCompatible) {
		        IDataHelper.put(cursor, "$exists?", PackageHelper.exists(packageName), String.class);
		    }
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
		// [o] - field:0:optional description
		// [o] - field:0:required version
		// [o] - field:0:required enabled? {&quot;false&quot;,&quot;true&quot;}
		// [o] - field:0:required system? {&quot;false&quot;,&quot;true&quot;}
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
		// [o] - record:0:required references
		// [o] -- field:1:required packages
		// [o] -- field:0:required packages.length
		// [o] -- record:1:required nodes
		// [o] --- field:0:required package
		// [o] --- field:0:required node
		// [o] -- field:0:required nodes.length
		// [o] -- field:1:required unresolved
		// [o] -- field:0:required unresolved.length
		// [o] field:0:required $exists? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String packageName = IDataHelper.get(cursor, "$name", String.class);
		
		    IData document = PackageHelper.toIData(PackageHelper.getPackage(packageName));
		
		    IDataHelper.put(cursor, "$package", document, false);
		    IDataHelper.put(cursor, "$exists?", document != null, String.class);
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
		// [o] record:1:optional $packages
		// [o] - field:0:required name
		// [o] - field:0:optional description
		// [o] - field:0:required version
		// [o] - field:0:required enabled? {&quot;false&quot;,&quot;true&quot;}
		// [o] - field:0:required system? {&quot;false&quot;,&quot;true&quot;}
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
		// [o] - record:0:required references
		// [o] -- field:1:required packages
		// [o] -- field:0:required packages.length
		// [o] -- record:1:required nodes
		// [o] --- field:0:required package
		// [o] --- field:0:required node
		// [o] -- field:0:required nodes.length
		// [o] -- field:1:required unresolved
		// [o] -- field:0:required unresolved.length
		// [o] field:0:required $packages.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    boolean enabledOnly = IDataHelper.getOrDefault(cursor, "$enabled?", Boolean.class, false);
		
		    IData[] list = PackageHelper.toIDataArray(PackageHelper.list(enabledOnly));
		
		    IDataHelper.put(cursor, "$packages", list);
		    IDataHelper.put(cursor, "$packages.length", list.length, String.class);
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
		// [o] - field:0:optional description
		// [o] - field:0:required version
		// [o] - field:0:required enabled? {&quot;false&quot;,&quot;true&quot;}
		// [o] - field:0:required system? {&quot;false&quot;,&quot;true&quot;}
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
		// [o] - record:0:required references
		// [o] -- field:1:required packages
		// [o] -- field:0:required packages.length
		// [o] -- record:1:required nodes
		// [o] --- field:0:required package
		// [o] --- field:0:required node
		// [o] -- field:0:required nodes.length
		// [o] -- field:1:required unresolved
		// [o] -- field:0:required unresolved.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    boolean enabledOnly = IDataHelper.getOrDefault(cursor, "$enabled?", Boolean.class, false);
		    IDataHelper.put(cursor, "$package", PackageHelper.toIData(PackageHelper.self()), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

