package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-09-13 05:26:15 AEST
// -----( ON-HOST: 192.168.20.9

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import javax.activation.MimeType;
import permafrost.tundra.content.ContentParser;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
// --- <<IS-END-IMPORTS>> ---

public final class content

{
	// ---( internal utility methods )---

	final static content _instance = new content();

	static content _newInstance() { return new content(); }

	static content _cast(Object o) { return (content)o; }

	// ---( server methods )---




	public static final void convert (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(convert)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;,&quot;base64&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.get(cursor, "$content.encoding", Charset.class);
		    ObjectConvertMode mode = IDataHelper.get(cursor, "$content.mode", ObjectConvertMode.class);
		
		    IDataHelper.put(cursor, "$content", ObjectHelper.convert(content, charset, mode), false);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] field:0:optional $content.type
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.schema
		// [i] record:0:optional $content.namespace
		// [i] field:0:optional $content.validate? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $content.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		// [o] field:0:optional $content.type
		// [o] field:0:optional $content.encoding
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    MimeType contentType = IDataHelper.get(cursor, "$content.type", MimeType.class);
		    Charset contentEncoding = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    String contentSchema = IDataHelper.first(cursor, String.class, "$content.schema", "$schema");
		    IData contentNamespace = IDataHelper.first(cursor, IData.class, "$content.namespace", "$namespace");
		    boolean validate = IDataHelper.firstOrDefault(cursor, Boolean.class, false, "$content.validate?", "$validate?");
		    ObjectConvertMode contentMode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");
		
		    ContentParser parser = new ContentParser(contentType, contentEncoding, contentSchema, contentNamespace, validate, pipeline);
		    InputStream content = parser.emit(document);
		    contentEncoding = parser.getCharset();
		
		    IDataHelper.put(cursor, "$content", ObjectHelper.convert(content, contentEncoding, contentMode), false);
		    IDataHelper.put(cursor, "$content.type", parser.getContentType(), false);
		    if (contentEncoding != null) IDataHelper.put(cursor, "$content.encoding", contentEncoding.displayName());
		} catch(IOException ex) {
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
		// [i] field:0:optional $content.type
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.schema
		// [i] record:0:optional $content.namespace
		// [i] field:0:optional $content.validate? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    MimeType contentType = IDataHelper.get(cursor, "$content.type", MimeType.class);
		    Charset contentEncoding = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    String contentSchema = IDataHelper.first(cursor, String.class, "$content.schema", "$schema");
		    IData contentNamespace = IDataHelper.first(cursor, IData.class, "$content.namespace", "$namespace");
		    boolean validate = IDataHelper.firstOrDefault(cursor, Boolean.class, false, "$content.validate?", "$validate?");
		
		    ContentParser parser = new ContentParser(contentType, contentEncoding, contentSchema, contentNamespace, validate, pipeline);
		    IData document = parser.parse(content);
		
		    IDataHelper.put(cursor, "$document", document, false);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

