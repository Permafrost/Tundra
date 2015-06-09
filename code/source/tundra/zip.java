package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-01 18:39:45 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.io.StreamHelper;
import permafrost.tundra.lang.ExceptionHelper;
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
		// [i] field:0:optional $mode {"stream","bytes","string","base64"}
		// [o] object:0:optional $contents.zip
		IDataCursor cursor = pipeline.getCursor();

		try {
		  IData[] contents = IDataUtil.getIDataArray(cursor, "$contents");
		  String mode = IDataUtil.getString(cursor, "$mode");

		  Object output = ObjectHelper.convert(ZipHelper.compress(ZipEntryWithData.valueOf(contents)), mode);

		  if (output != null) IDataUtil.put(cursor, "$contents.zip", output);
		} catch(java.io.IOException ex) {
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
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string","base64"}
		// [o] record:1:optional $contents
		// [o] - field:0:required name
		// [o] - object:0:required content
		IDataCursor cursor = pipeline.getCursor();

		try {
		  Object input = IDataUtil.get(cursor, "$contents.zip");
		  String charset = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");

		  ZipEntryWithData[] entries = ZipHelper.decompress(StreamHelper.normalize(input, charset));

		  if (entries != null) IDataUtil.put(cursor, "$contents", ZipEntryWithData.toIDataArray(entries, charset, mode));
		} catch(IOException ex) {
		  ExceptionHelper.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

