package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-07-07 10:37:06 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import permafrost.tundra.data.IDataHTMLParser;
import permafrost.tundra.html.HTMLHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.math.IntegerHelper;
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
		// [i] field:0:optional $depth
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataUtil.getIData(cursor, "$document");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		    int depth = IntegerHelper.parse(IDataUtil.getString(cursor, "$depth"), Integer.MAX_VALUE);
		    ObjectConvertMode mode = ObjectConvertMode.normalize(IDataUtil.getString(cursor, "$mode"));
		
		    IDataUtil.put(cursor, "$content", ObjectHelper.convert(IDataHTMLParser.getInstance().encodeToString(document, depth), charset, mode));
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

