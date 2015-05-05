package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-05 08:51:15 AEST
// -----( ON-HOST: PC62XKG2S.internal.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.Closeable;
import java.io.IOException;
import permafrost.tundra.io.StreamHelper;
import permafrost.tundra.lang.BooleanHelper;
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
		  Object stream = IDataUtil.get(cursor, "$stream");
		  if (stream instanceof Closeable) StreamHelper.close((Closeable)stream);
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
		// [i] object:0:optional $input
		// [i] object:0:optional $output
		// [i] field:0:optional $close? {"true","false"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object input = IDataUtil.get(cursor, "$input");
		    Object output = IDataUtil.get(cursor, "$output");
		    boolean close = BooleanHelper.parse(IDataUtil.getString(cursor, "$close?"), true);
		    StreamHelper.copy(input, output, close);
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
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object object = IDataUtil.get(cursor, "$object");
		    String encoding = IDataUtil.getString(cursor, "$encoding");
		
		    IDataUtil.put(cursor, "$stream", StreamHelper.normalize(object, encoding));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

