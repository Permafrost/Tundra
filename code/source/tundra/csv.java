package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-08-18 10:53:39 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.csv.QuoteMode;
import permafrost.tundra.data.IDataCSVParser;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
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
		// [i] - object:1:optional recordWithNoID
		// [i] field:0:optional $content.delimiter.character
		// [i] field:0:optional $content.escape.character
		// [i] field:0:optional $content.quote.character
		// [i] field:0:optional $content.quote.mode {&quot;minimal&quot;,&quot;non-numeric&quot;,&quot;none&quot;,&quot;all&quot;}
		// [i] field:0:optional $content.header? {&quot;true&quot;,&quot;false&quot;}
		// [i] field:1:optional $content.headings
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String delimiterCharacter = IDataHelper.first(cursor, String.class, "$content.delimiter.character", "$delimiter");
		    String escapeCharacter = IDataHelper.get(cursor, "$content.escape.character", String.class);
		    String quoteCharacter = IDataHelper.get(cursor, "$content.quote.character", String.class);
		    QuoteMode quoteMode = IDataHelper.get(cursor, "$content.quote.mode", QuoteMode.class);
		    boolean hasHeader = IDataHelper.firstOrDefault(cursor, Boolean.class, true, "$content.header?", "$header?");
		    String[] columns = IDataHelper.first(cursor, String[].class, "$content.headings", "$columns");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");
		
		    if (document != null) {
		        IDataCSVParser parser = new IDataCSVParser(delimiterCharacter, escapeCharacter, quoteCharacter, quoteMode, (String)null, hasHeader, columns);
		        IDataHelper.put(cursor, "$content", ObjectHelper.convert(parser.emit(document, charset), charset, mode));
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
		// [i] field:0:optional $content.delimiter.character
		// [i] field:0:optional $content.escape.character
		// [i] field:0:optional $content.quote.character
		// [i] field:0:optional $content.quote.mode {&quot;minimal&quot;,&quot;non-numeric&quot;,&quot;none&quot;,&quot;all&quot;}
		// [i] field:0:optional $content.header? {&quot;true&quot;,&quot;false&quot;}
		// [i] field:0:optional $content.encoding
		// [o] record:0:optional $document
		// [o] - record:1:optional recordWithNoID
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    String delimiterCharacter = IDataHelper.first(cursor, String.class, "$content.delimiter.character", "$delimiter");
		    String escapeCharacter = IDataHelper.get(cursor, "$content.escape.character", String.class);
		    String quoteCharacter = IDataHelper.get(cursor, "$content.quote.character", String.class);
		    QuoteMode quoteMode = IDataHelper.get(cursor, "$content.quote.mode", QuoteMode.class);
		    boolean hasHeader = IDataHelper.firstOrDefault(cursor, Boolean.class, true, "$content.header?", "$header?");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		
		    if (content != null) {
		        IDataCSVParser parser = new IDataCSVParser(delimiterCharacter, escapeCharacter, quoteCharacter, quoteMode, (String)null, hasHeader, null);
		        IDataHelper.put(cursor, "$document", parser.parse(InputStreamHelper.normalize(content, charset)));
		    }
		} catch (IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

