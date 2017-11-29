package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-11-29T17:12:50.314
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
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
		// [i] - record:1:optional recordWithNoID
		// [i] field:0:optional $delimiter
		// [i] field:0:optional $header? {&quot;true&quot;,&quot;false&quot;}
		// [i] field:1:optional $columns
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String delimiter = IDataHelper.get(cursor, "$delimiter", String.class);
		    boolean hasHeader = IDataHelper.getOrDefault(cursor, "$header?", Boolean.class, true);
		    String[] columns = IDataHelper.get(cursor, "$columns", String[].class);
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		    ObjectConvertMode mode = IDataHelper.get(cursor, "$mode", ObjectConvertMode.class);

		    if (document != null) {
		        IDataCSVParser parser = new IDataCSVParser(delimiter, null, hasHeader, columns);
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
		// [i] field:0:optional $delimiter
		// [i] field:0:optional $header? {&quot;true&quot;,&quot;false&quot;}
		// [i] field:0:optional $encoding
		// [o] record:0:optional $document
		// [o] - record:1:optional recordWithNoID
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    String delimiter = IDataHelper.get(cursor, "$delimiter", String.class);
		    boolean hasHeader = IDataHelper.getOrDefault(cursor, "$header?", Boolean.class, true);
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);

		    if (content != null) {
		        IDataCSVParser parser = new IDataCSVParser(delimiter, null, hasHeader);
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

