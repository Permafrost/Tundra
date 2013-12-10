package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-12-10 11:52:24.282
// -----( ON-HOST: EBZDEVWAP37.ebiztest.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		  close(stream);
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
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object input = IDataUtil.get(cursor, "$input");
		  Object output = IDataUtil.get(cursor, "$output");
		  copy(input, output);
		} catch(java.io.IOException ex) {
		  tundra.exception.raise(ex);
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
		
		  IDataUtil.put(cursor, "$stream", normalize(object, encoding));
		} catch(java.io.UnsupportedEncodingException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// converts the given object, which must be either an input stream, byte array, or string, to an input stream, using the 
	// given encoding if the object was a string
	public static java.io.InputStream normalize(Object object, String encoding) throws java.io.UnsupportedEncodingException {
	  if (encoding == null) encoding = tundra.support.constant.DEFAULT_CHARACTER_ENCODING;
	  
	  java.io.InputStream stream = null;
	  
	  if (object != null) {
	    if (object instanceof java.io.BufferedInputStream) {
	      stream = (java.io.BufferedInputStream)object;
	    } else if (object instanceof java.io.InputStream) {
	      stream = new java.io.BufferedInputStream((java.io.InputStream)object, tundra.support.constant.DEFAULT_BUFFER_SIZE);
	    } else if (object instanceof byte[]) {
	      stream = new java.io.ByteArrayInputStream((byte[])object);
	    } else if (object instanceof String) {
	      stream = new java.io.ByteArrayInputStream(((String)object).getBytes(encoding));
	    } else {
	      throw new IllegalArgumentException("object must be a string, byte[] or java.io.InputStream: " + object.getClass().getName());
	    }
	  }
	
	  return stream;
	}
	
	// converts the given object, which must be either an input stream, byte array, or string, to an input stream
	public static java.io.InputStream normalize(Object object) throws java.io.UnsupportedEncodingException {
	  return normalize(object, tundra.support.constant.DEFAULT_CHARACTER_ENCODING);
	}
	
	// copies all the data from the reader to the writer, then closes both
	public static void copy(java.io.Reader reader, java.io.Writer writer) throws java.io.IOException {  
	  try {
	    if (reader == null || writer == null) return;
	    
	    char[] buffer = new char[tundra.support.constant.DEFAULT_BUFFER_SIZE];
	    int length = 0;
	    
	    if (!(reader instanceof java.io.BufferedReader)) reader = new java.io.BufferedReader(reader, tundra.support.constant.DEFAULT_BUFFER_SIZE);
	    if (!(writer instanceof java.io.BufferedWriter)) writer = new java.io.BufferedWriter(writer, tundra.support.constant.DEFAULT_BUFFER_SIZE);
	  
	    while((length = reader.read(buffer)) > 0) {
	      writer.write(buffer, 0, length);
	    }
	  } finally {
	    close(reader, writer);
	  }
	}
	
	// copies all data from the input stream to the output stream, and optionally closes both streams
	public static void copy(java.io.InputStream in, java.io.OutputStream out, boolean close) throws java.io.IOException {  
	  try {
	    if (in == null || out == null) return;
	  
	    byte[] buffer = new byte[tundra.support.constant.DEFAULT_BUFFER_SIZE];
	    int length = 0;
	    
	    if (!(in instanceof java.io.BufferedInputStream)) in = new java.io.BufferedInputStream(in, tundra.support.constant.DEFAULT_BUFFER_SIZE);
	    if (!(out instanceof java.io.BufferedOutputStream)) out = new java.io.BufferedOutputStream(out, tundra.support.constant.DEFAULT_BUFFER_SIZE);
	
	    while((length = in.read(buffer)) > 0) {
	      out.write(buffer, 0, length);
	    }
	  } finally {
	    if (close) close(in, out);
	  }
	}
	
	// copies all data from the input stream to the output stream, then closes both streams
	public static void copy(java.io.InputStream in, java.io.OutputStream out) throws java.io.IOException {  
	  copy(in, out, true);
	}
	
	public static void copy(Object input, Object output) throws java.io.IOException {
	  if (output != null && (!(output instanceof java.io.OutputStream)))
	    throw new IllegalArgumentException("output object must be an java.io.OutputStream: " + output.getClass().getName());
	
	  copy(normalize(input), (java.io.OutputStream)output);
	}
	
	// closes the given list of objects, which must be one of the following types: java.io.InputStream, 
	// java.io.OutputStream, java.io.Reader, or java.io.Writer
	public static void close(Object ... streams) {
	  if (streams == null) return;
	  
	  for (int i = 0; i < streams.length; i++) {
	    Object stream = streams[i];
	    try {
	      if (stream != null) {
	        if (stream instanceof java.io.InputStream) {
	          ((java.io.InputStream)stream).close();
	        } else if (stream instanceof java.io.OutputStream) {
	          ((java.io.OutputStream)stream).close();
	        } else if (stream instanceof java.io.Reader) {
	          ((java.io.Reader)stream).close();
	        } else  if (stream instanceof java.io.Writer) {
	          ((java.io.Writer)stream).close();
	        }
	      }
	    } catch (java.io.IOException ex) {}
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

