package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-07-18 11:28:05.323
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import com.wm.app.b2b.server.Package;
import permafrost.tundra.configuration.ConfigurationManager;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.server.PackageHelper;
// --- <<IS-END-IMPORTS>> ---

public final class configuration

{
	// ---( internal utility methods )---

	final static configuration _instance = new configuration();

	static configuration _newInstance() { return new configuration(); }

	static configuration _cast(Object o) { return (configuration)o; }

	// ---( server methods )---




	public static final void all (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(all)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $refresh? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:required $configurations
		IDataCursor cursor = pipeline.getCursor();

		try {
		    boolean refresh = BooleanHelper.parse(IDataUtil.getString(cursor, "$refresh?"));
		    IData configurations = ConfigurationManager.all(refresh);
		    if (configurations != null) IDataUtil.put(cursor, "$configurations", configurations);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $package
		// [i] field:0:optional $refresh? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $package
		// [o] record:0:required $configuration
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String packageName = IDataUtil.getString(cursor, "$package");
		    boolean refresh = BooleanHelper.parse(IDataUtil.getString(cursor, "$refresh?"));

		    if (packageName == null) {
		        // infer package name from invoking service
		        Package invokingPackage = PackageHelper.self();
		        if (invokingPackage != null) packageName = invokingPackage.getName();
		    }

		    IData configuration = ConfigurationManager.get(packageName, refresh);

		    IDataUtil.put(cursor, "$package", packageName);
		    IDataUtil.put(cursor, "$configuration", configuration);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $refresh? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:required $configurations
		// [o] - field:0:required package
		// [o] - record:0:required configuration
		IDataCursor cursor = pipeline.getCursor();

		try {
		    boolean refresh = BooleanHelper.parse(IDataUtil.getString(cursor, "$refresh?"));
		    IData[] configurations = ConfigurationManager.list(refresh);
		    if (configurations != null) IDataUtil.put(cursor, "$configurations", configurations);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

