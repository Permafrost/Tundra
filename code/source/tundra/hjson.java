package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-07 10:50:33 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataHjsonParser;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
// --- <<IS-END-IMPORTS>> ---

public final class hjson

{
	// ---( internal utility methods )---

	final static hjson _instance = new hjson();

	static hjson _newInstance() { return new hjson(); }

	static hjson _cast(Object o) { return (hjson)o; }

	// ---( server methods )---




	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] - object:1:optional recordWithNoID
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		    ObjectConvertMode mode = IDataHelper.get(cursor, "$mode", ObjectConvertMode.class);
		
		    if (document != null) {
		        IDataHelper.put(cursor, "$content", ObjectHelper.convert(IDataHjsonParser.getInstance().emit(document, charset), charset, mode), false);
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
		// [i] field:0:optional $encoding
		// [o] record:0:optional $document
		// [o] - object:1:optional recordWithNoID
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		
		    if (content != null) {
		        IDataHelper.put(cursor, "$document", IDataHjsonParser.getInstance().parse(InputStreamHelper.normalize(content, charset), charset), false);
		    }
		} catch (IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

