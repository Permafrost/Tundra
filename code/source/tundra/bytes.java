package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2018-12-01 21:04:47 EST
// -----( ON-HOST: 192.168.20.18

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
import permafrost.tundra.math.IntegerHelper;
// --- <<IS-END-IMPORTS>> ---

public final class bytes

{
	// ---( internal utility methods )---

	final static bytes _instance = new bytes();

	static bytes _newInstance() { return new bytes(); }

	static bytes _cast(Object o) { return (bytes)o; }

	// ---( server methods )---




	public static final void length (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(length)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $bytes
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    byte[] bytes = IDataHelper.get(cursor, "$bytes", byte[].class);
		
		    IDataHelper.put(cursor, "$length", bytes == null ? 0 : bytes.length, String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void normalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(normalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $object
		// [i] field:0:optional $encoding
		// [o] object:0:optional $bytes
		// [o] field:0:optional $encoding
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$object");
		    Charset charset = IDataHelper.getOrDefault(cursor, "$encoding", Charset.class, CharsetHelper.DEFAULT_CHARSET);
		
		    byte[] bytes = BytesHelper.normalize(object, charset);
		
		    IDataHelper.put(cursor, "$bytes", bytes, false);
		    if (bytes != null && object instanceof String) IDataHelper.put(cursor, "$encoding", charset.name());
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void random (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(random)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $bytes.random.length
		// [o] object:0:required $bytes.random
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    int length = IDataHelper.get(cursor, "$bytes.random.length", Integer.class);
		    byte[] bytes = new byte[length];
		    tundra.support.security.getRandom().nextBytes(bytes);
		    IDataHelper.put(cursor, "$bytes.random", bytes);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void transcode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(transcode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $bytes
		// [i] field:0:optional $encoding.input
		// [i] field:0:optional $encoding.output
		// [o] object:0:optional $bytes
		// [o] field:0:optional $encoding.input
		// [o] field:0:optional $encoding.output
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    byte[] bytes = IDataHelper.get(cursor, "$bytes", byte[].class);
		    Charset sourceCharset = IDataHelper.getOrDefault(cursor, "$encoding.input", Charset.class, CharsetHelper.DEFAULT_CHARSET);
		    Charset targetCharset = IDataHelper.getOrDefault(cursor, "$encoding.output", Charset.class, CharsetHelper.DEFAULT_CHARSET);
		
		    if (bytes != null) {
		        IDataHelper.put(cursor, "$bytes", BytesHelper.transcode(bytes, sourceCharset, targetCharset));
		        IDataHelper.put(cursor, "$encoding.input", sourceCharset.name());
		        IDataHelper.put(cursor, "$encoding.output", targetCharset.name());
		    }
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

