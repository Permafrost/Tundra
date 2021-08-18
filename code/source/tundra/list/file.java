package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-08-18 10:31:23 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.FileHelper;
import permafrost.tundra.lang.ExceptionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class file

{
	// ---( internal utility methods )---

	final static file _instance = new file();

	static file _newInstance() { return new file(); }

	static file _cast(Object o) { return (file)o; }

	// ---( server methods )---




	public static final void remove (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remove)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $files
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] files = IDataHelper.get(cursor, "$files", String[].class);
		
		    List<Throwable> exceptions = new ArrayList<Throwable>();
		
		    for (String file : files) {
		        try {
		            FileHelper.remove(FileHelper.construct(file));
		        } catch(IOException ex) {
		            exceptions.add(ex);
		        }
		    }
		
		    if (exceptions.size() > 0) ExceptionHelper.raise(exceptions);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

