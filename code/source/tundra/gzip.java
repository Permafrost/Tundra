package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-01 18:33:55 EST
// -----( ON-HOST: 172.16.167.128

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.io.StreamHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.zip.GzipHelper;
// --- <<IS-END-IMPORTS>> ---

public final class gzip

{
	// ---( internal utility methods )---

	final static gzip _instance = new gzip();

	static gzip _newInstance() { return new gzip(); }

	static gzip _cast(Object o) { return (gzip)o; }

	// ---( server methods )---




	public static final void compress (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compress)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string","base64"}
		// [o] object:0:optional $content.gzip
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object input = IDataUtil.get(cursor, "$content");
		  String charset = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		  
		  Object output = ObjectHelper.convert(GzipHelper.compress(StreamHelper.normalize(input, charset)), charset, mode);
		  
		  if (output != null) IDataUtil.put(cursor, "$content.gzip", output);
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
		// [i] object:0:optional $content.gzip
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string","base64"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object input = IDataUtil.get(cursor, "$content.gzip");
		  String charset = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		  
		  Object output = ObjectHelper.convert(GzipHelper.decompress(StreamHelper.normalize(input, charset)), charset, mode);
		
		  if (output != null) IDataUtil.put(cursor, "$content", output);
		} catch(IOException ex) {
		  ExceptionHelper.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

