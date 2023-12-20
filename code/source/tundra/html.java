package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2023-12-21 05:41:17 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import permafrost.tundra.data.IDataHelper;
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
		// [i] record:0:optional $document.encoded
		// [o] record:0:optional $document.decoded
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document.encoded", IData.class);
		
		    if (document == null) {
		        String string = IDataHelper.get(cursor, "$string", String.class);
		        IDataHelper.put(cursor, "$string", HTMLHelper.decode(string), false);
		    } else {
		        IDataHelper.put(cursor, "$document.decoded", HTMLHelper.decode(document), false);
		    }
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
		// [i] field:0:optional $content.orientation {&quot;portrait&quot;,&quot;landscape&quot;}
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean portraitOrientation = "portrait".equals(IDataHelper.getOrDefault(cursor, "$content.orientation", String.class, "portrait"));
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");
		    if (document != null) {
		        IDataHelper.put(cursor, "$content", ObjectHelper.convert(new IDataHTMLParser(true, portraitOrientation).emit(document, charset), charset, mode), false);
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
		// [i] record:0:optional $document.decoded
		// [o] record:0:optional $document.encoded
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document.decoded", IData.class);
		    if (document == null) {
		        String string = IDataHelper.get(cursor, "$string", String.class);
		        IDataHelper.put(cursor, "$string", HTMLHelper.encode(string), false);
		    } else {
		        IDataHelper.put(cursor, "$document.encoded", HTMLHelper.encode(document), false);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

