package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-01 18:51:22 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BytesHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.lang.StringHelper;
// --- <<IS-END-IMPORTS>> ---

public final class base64

{
	// ---( internal utility methods )---

	final static base64 _instance = new base64();

	static base64 _newInstance() { return new base64(); }

	static base64 _cast(Object o) { return (base64)o; }

	// ---( server methods )---




	public static final void decode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $base64
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object input = IDataHelper.get(cursor, "$base64");
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		    ObjectConvertMode mode = IDataHelper.get(cursor, "$mode", ObjectConvertMode.class);
		
		    Object output = ObjectHelper.convert(BytesHelper.base64Decode(StringHelper.normalize(input, charset)), charset, mode);
		
		    IDataHelper.put(cursor, "$content", output, false);
		} catch(IOException ex) {
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
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $base64
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object input = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		    ObjectConvertMode mode = IDataHelper.get(cursor, "$mode", ObjectConvertMode.class);
		
		    Object output = ObjectHelper.convert(BytesHelper.base64Encode(BytesHelper.normalize(input, charset)), charset, mode);
		
		    IDataHelper.put(cursor, "$base64", output, false);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

