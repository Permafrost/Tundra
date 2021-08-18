package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-08-18 10:39:51 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.Closeable;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.CloseableHelper;
import permafrost.tundra.io.InputOutputHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.io.TranscodingInputStream;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class stream

{
	// ---( internal utility methods )---

	final static stream _instance = new stream();

	static stream _newInstance() { return new stream(); }

	static stream _cast(Object o) { return (stream)o; }

	// ---( server methods )---




	public static final void close (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(close)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $stream
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Closeable closeable = IDataHelper.get(cursor, "$stream", Closeable.class);
		    CloseableHelper.close(closeable);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void copy (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(copy)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $stream.input
		// [i] object:0:optional $stream.output
		// [i] object:0:optional $stream.buffer.size
		// [i] field:0:optional $stream.close? {&quot;true&quot;,&quot;false&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    InputStream input = IDataHelper.first(cursor, InputStream.class, "$stream.input", "$input");
		    OutputStream output = IDataHelper.first(cursor, OutputStream.class, "$stream.output", "$output");
		    int bufferSize = IDataHelper.getOrDefault(cursor, "$stream.buffer.size", Integer.class, -1);
		    boolean close = IDataHelper.firstOrDefault(cursor, Boolean.class, true, "$stream.close?", "$close?");
		
		    InputOutputHelper.copy(input, output, close, bufferSize);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		// [o] object:0:optional $stream
		// [o] field:0:optional $encoding
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataHelper.get(cursor, "$object");
		    Charset charset = IDataHelper.getOrDefault(cursor, "$encoding", Charset.class, CharsetHelper.DEFAULT_CHARSET);
		
		    InputStream stream = InputStreamHelper.normalize(object, charset);
		
		    IDataHelper.put(cursor, "$stream", stream, false);
		    if (stream != null && object instanceof String) IDataHelper.put(cursor, "$encoding", charset.name());
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
		// [i] object:0:optional $stream
		// [i] field:0:optional $encoding.input
		// [i] field:0:optional $encoding.output
		// [o] object:0:optional $stream
		// [o] field:0:optional $encoding.input
		// [o] field:0:optional $encoding.output
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    InputStream inputStream = IDataHelper.get(cursor, "$stream", InputStream.class);
		    Charset sourceCharset = IDataHelper.getOrDefault(cursor, "$encoding.input", Charset.class, CharsetHelper.DEFAULT_CHARSET);
		
		    Charset targetCharset = IDataHelper.getOrDefault(cursor, "$encoding.output", Charset.class, CharsetHelper.DEFAULT_CHARSET);
		
		    if (inputStream != null) {
		        IDataHelper.put(cursor, "$stream", new TranscodingInputStream(inputStream, sourceCharset, targetCharset));
		        IDataHelper.put(cursor, "$encoding.input", sourceCharset.name());
		        IDataHelper.put(cursor, "$encoding.output", targetCharset.name());
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

