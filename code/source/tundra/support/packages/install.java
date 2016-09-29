package tundra.support.packages;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-09-29 14:15:08 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.server.invoke.PackageInstallProcessor;
// --- <<IS-END-IMPORTS>> ---

public final class install

{
	// ---( internal utility methods )---

	final static install _instance = new install();

	static install _newInstance() { return new install(); }

	static install _cast(Object o) { return (install)o; }

	// ---( server methods )---




	public static final void start (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(start)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $pattern.install
		// [i] field:0:optional $pattern.uninstall
		PackageInstallProcessor processor = PackageInstallProcessor.getInstance();
		processor.setInstallServicePattern(IDataHelper.get(pipeline, "$pattern.install", String.class));
		processor.setUninstallServicePattern(IDataHelper.get(pipeline, "$pattern.uninstall", String.class));
		processor.start();
		// --- <<IS-END>> ---

                
	}



	public static final void stop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		PackageInstallProcessor.getInstance().stop();
		// --- <<IS-END>> ---

                
	}
}

