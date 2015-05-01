package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-04-30 09:34:55 EST
// -----( ON-HOST: PC62XKG2S.internal.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.data.IDataHTMLParser;
import permafrost.tundra.html.HTMLHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectHelper;
// --- <<IS-END-IMPORTS>> ---

public final class html

{
	// ---( internal utility methods )---

	final static html _instance = new html();

	static html _newInstance() { return new html(); }

	static html _cast(Object o) { return (html)o; }

	// ---( server methods )---




	public static final void decode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  if (string != null) IDataUtil.put(cursor, "$string", HTMLHelper.decode(string));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String charset = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  IDataUtil.put(cursor, "$content", ObjectHelper.convert(IDataHTMLParser.getInstance().emit(document, charset), charset, mode));
		} catch (IOException ex) {
		  ExceptionHelper.raise(ex);
		} finally {
		  cursor.destroy();
		}
		
		
		// --- <<IS-END>> ---

                
	}



	public static final void encode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(encode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  if (string != null) IDataUtil.put(cursor, "$string", HTMLHelper.encode(string));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

