package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-03-07 08:49:19 GMT+10:00
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.data.IDataExcelParser;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import java.io.IOException;
// --- <<IS-END-IMPORTS>> ---

public final class excel

{
	// ---( internal utility methods )---

	final static excel _instance = new excel();

	static excel _newInstance() { return new excel(); }

	static excel _cast(Object o) { return (excel)o; }

	// ---( server methods )---




	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $content.extension {"xlsx","xls"}
		// [i] field:0:optional $content.mode {"stream","bytes"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String extension = IDataHelper.firstOrDefault(cursor, String.class, "xlsx", "$content.extension", "$extension");
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");

		    if (document != null) {
		        IDataExcelParser parser = new IDataExcelParser(extension.equals("xlsx"));
		        IDataHelper.put(cursor, "$content", ObjectHelper.convert(parser.emit(document), mode));
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
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    if (content != null) IDataHelper.put(cursor, "$document", new IDataExcelParser().parse(InputStreamHelper.normalize(content)));
		} catch (IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

