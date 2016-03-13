package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-03-13 13:28:58 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.configuration.ConfigurationManager;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class configuration

{
	// ---( internal utility methods )---

	final static configuration _instance = new configuration();

	static configuration _newInstance() { return new configuration(); }

	static configuration _cast(Object o) { return (configuration)o; }

	// ---( server methods )---




	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $package
		// [i] field:0:optional $refresh? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $configuration
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String packageName = IDataUtil.getString(cursor, "$package");
		    boolean refresh = BooleanHelper.parse(IDataUtil.getString(cursor, "$refresh?"));
		
		    IData configuration = ConfigurationManager.get(packageName, refresh);
		
		    if (configuration != null) IDataUtil.put(cursor, "$configuration", configuration);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

