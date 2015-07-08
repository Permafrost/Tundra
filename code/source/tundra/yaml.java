package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-07-09 08:30:58 AEST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.data.IDataYAMLParser;
import permafrost.tundra.io.StreamHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectHelper;
// --- <<IS-END-IMPORTS>> ---

public final class yaml

{
	// ---( internal utility methods )---

	final static yaml _instance = new yaml();

	static yaml _newInstance() { return new yaml(); }

	static yaml _cast(Object o) { return (yaml)o; }

	// ---( server methods )---




	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] - record:1:optional recordWithNoID
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataUtil.getIData(cursor, "$document");
		    String charset = IDataUtil.getString(cursor, "$encoding");
		    String mode = IDataUtil.getString(cursor, "$mode");
		
		    if (document != null) IDataUtil.put(cursor, "$content", ObjectHelper.convert(IDataYAMLParser.getInstance().emit(document, charset), charset, mode));
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
		// [o] - record:1:optional recordWithNoID
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    String charset = IDataUtil.getString(cursor, "$encoding");
		
		    if (content != null) {
		        IDataUtil.put(cursor, "$document", IDataYAMLParser.getInstance().parse(StreamHelper.normalize(content, charset)));
		    }
		} catch (IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

