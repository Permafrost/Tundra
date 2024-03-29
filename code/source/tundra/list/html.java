package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-09-18 15:04:48 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import permafrost.tundra.data.IDataHTMLParser;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataMap;
import permafrost.tundra.html.HTMLHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
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
		// [i] field:1:optional $list
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    IDataHelper.put(cursor, "$list", HTMLHelper.decode(list), false);
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
		// [i] record:1:optional $document.list
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.first(cursor, IData[].class, "$document.list", "$list");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");
		
		    if (list != null) {
		        IDataMap map = new IDataMap();
		        map.put("recordWithNoID", list);
		        IDataHelper.put(cursor, "$content", ObjectHelper.convert(new IDataHTMLParser().emit(map, charset), charset, mode));
		    }
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
		// [i] field:1:optional $list
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    IDataHelper.put(cursor, "$list", HTMLHelper.encode(list), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

