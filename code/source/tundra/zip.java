package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-11-27T13:32:58.916
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.zip.ZipEntryWithData;
import permafrost.tundra.zip.ZipHelper;
// --- <<IS-END-IMPORTS>> ---

public final class zip

{
	// ---( internal utility methods )---

	final static zip _instance = new zip();

	static zip _newInstance() { return new zip(); }

	static zip _cast(Object o) { return (zip)o; }

	// ---( server methods )---




	public static final void compress (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compress)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $contents
		// [i] - field:0:required name
		// [i] - object:0:required content
		// [i] - field:0:optional encoding
		// [i] field:0:optional $content.mode {"stream","bytes","string","base64"}
		// [o] object:0:optional $contents.zip
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] contents = IDataHelper.get(cursor, "$contents", IData[].class);
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");

		    Object output = ObjectHelper.convert(ZipHelper.compress(ZipEntryWithData.valueOf(contents)), mode);

		    IDataHelper.put(cursor, "$contents.zip", output, false);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void decompress (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decompress)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $contents.zip
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.mode {"stream","bytes","string","base64"}
		// [o] record:1:optional $contents
		// [o] - field:0:required name
		// [o] - object:0:required content
		// [o] - object:0:required length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object input = IDataHelper.get(cursor, "$contents.zip");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");

		    ZipEntryWithData[] entries = ZipHelper.decompress(InputStreamHelper.normalize(input, charset));

		    IDataHelper.put(cursor, "$contents", ZipEntryWithData.toIDataArray(entries, charset, mode), false);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

