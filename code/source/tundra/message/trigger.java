package tundra.message;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-11-27 11:50:16 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.message.format.Format;
import permafrost.tundra.message.format.Recognizer;
// --- <<IS-END-IMPORTS>> ---

public final class trigger

{
	// ---( internal utility methods )---

	final static trigger _instance = new trigger();

	static trigger _newInstance() { return new trigger(); }

	static trigger _cast(Object o) { return (trigger)o; }

	// ---( server methods )---




	public static final void recognize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(recognize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $message
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    while(cursor.next()) {
		        String key = cursor.getKey();
		        Object value = cursor.getValue();
		
		        if (value instanceof IData) {
		            Format format = Recognizer.getInstance().getByPublishableDocumentType(key);
		
		            if (format != null) {
		                IDataHelper.put(cursor, "$message", value);
		                IDataHelper.put(cursor, "$message.format", format, IData.class);
		                return;
		            }
		        }
		    }
		
		    ExceptionHelper.raise("published document was not recognized or could not be found in pipeline");
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

