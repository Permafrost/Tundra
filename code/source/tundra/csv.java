package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-04-29 14:24:19 EST
// -----( ON-HOST: PC62XKG2S.internal.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.data.IDataCSVParser;
import permafrost.tundra.io.StreamHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectHelper;
// --- <<IS-END-IMPORTS>> ---

public final class csv

{
	// ---( internal utility methods )---

	final static csv _instance = new csv();

	static csv _newInstance() { return new csv(); }

	static csv _cast(Object o) { return (csv)o; }

	// ---( server methods )---




	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] - record:1:optional recordWithNoID
		// [i] field:0:optional $delimiter
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String delimiter = IDataUtil.getString(cursor, "$delimiter");
		    String charset = IDataUtil.getString(cursor, "$encoding");
		    String mode = IDataUtil.getString(cursor, "$mode");
		
		    if (document != null) {
		        IDataUtil.put(cursor, "$content", ObjectHelper.convert(new IDataCSVParser(delimiter).emit(document, charset), mode));
		    }
		} catch (IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}    	
		// --- <<IS-END>> ---

                
	}



	public static final void parse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parse)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $delimiter
		// [i] field:0:optional $encoding
		// [o] record:0:optional $document
		// [o] - record:1:optional recordWithNoID
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    String delimiter = IDataUtil.getString(cursor, "$delimiter");
		    String charset = IDataUtil.getString(cursor, "$encoding");
		
		    if (content != null) {
		        IDataUtil.put(cursor, "$document", new IDataCSVParser(delimiter).parse(StreamHelper.normalize(content, charset)));
		    }
		} catch (IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		
		// --- <<IS-END>> ---

                
	}
}

