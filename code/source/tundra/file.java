package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-11-23 09:47:49.099
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class file

{
	// ---( internal utility methods )---

	final static file _instance = new file();

	static file _newInstance() { return new file(); }

	static file _cast(Object o) { return (file)o; }

	// ---( server methods )---




	public static final void create (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(create)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  create(IDataUtil.getString(cursor, "$file"));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void executable (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(executable)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [o] field:0:required $executable?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  IDataUtil.put(cursor, "$executable?", "" + executable(file));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void exists (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(exists)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  IDataUtil.put(cursor, "$exists?", "" + exists(file));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void length (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(length)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  IDataUtil.put(cursor, "$length", "" + length(file));
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
		// [i] field:0:required $file
		// [o] field:0:required $file
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$file", tundra.support.file.normalize(IDataUtil.getString(cursor, "$file")));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void process (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(process)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [i] field:0:optional $mode {&quot;read&quot;,&quot;append&quot;,&quot;write&quot;}
		// [i] field:0:required $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $service.input
		// [o] record:0:optional $pipeline
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  String mode = IDataUtil.getString(cursor, "$mode");
		  String service = IDataUtil.getString(cursor, "$service");
		  String input = IDataUtil.getString(cursor, "$service.input");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		  boolean scoped = scope != null;
		
		  scope = process(file, mode, service, input, scoped? scope : pipeline);
		
		  if (scoped) IDataUtil.put(cursor, "$pipeline", scope);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void read (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(read)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [i] field:0:required $mode {&quot;bytes&quot;,&quot;string&quot;}
		// [i] field:0:optional $encoding
		// [o] object:0:required $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  String mode = IDataUtil.getString(cursor, "$mode");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		
		  IDataUtil.put(cursor, "$content", read(file, mode, encoding));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void readable (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(readable)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [o] field:0:required $readable?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  IDataUtil.put(cursor, "$readable?", "" + readable(file));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void remove (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remove)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $file
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  remove(IDataUtil.getString(cursor, "$file"));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void rename (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(rename)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file.source
		// [i] field:0:required $file.target
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String source = IDataUtil.getString(cursor, "$file.source");
		  String target = IDataUtil.getString(cursor, "$file.target");
		  rename(source, target);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void touch (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(touch)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  touch(IDataUtil.getString(cursor, "$file"));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void type (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(type)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [o] field:0:required $type
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  IDataUtil.put(cursor, "$type", type(file));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void writable (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(writable)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [o] field:0:required $writable?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  IDataUtil.put(cursor, "$writable?", "" + writable(file));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void write (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(write)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $file
		// [i] field:0:optional $mode {&quot;append&quot;,&quot;write&quot;}
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [o] field:0:optional $file
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  String mode = IDataUtil.getString(cursor, "$mode");
		  Object content = IDataUtil.get(cursor, "$content");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		
		  file = write(file, mode, content, encoding);
		
		  IDataUtil.put(cursor, "$file", file);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns the mime type for the given file
	public static String type(java.io.File file) throws ServiceException {
	  String type = null;
	  if (file != null) type = com.wm.app.b2b.server.MimeTypes.getTypeFromName(file.getName());
	  if (type == null) type = "application/octet-stream";
	  return type;
	}
	
	// returns the mime type for the given filename
	public static String type(String filename) throws ServiceException {
	  return type(tundra.support.file.construct(filename));
	}
	
	// returns whether the given file exists
	public static boolean exists(java.io.File file) throws ServiceException {
	  return file == null ? false : file.exists() && file.isFile();
	}
	
	// returns whether the given file exists
	public static boolean exists(String filename) throws ServiceException {
	  return exists(tundra.support.file.construct(filename));
	}
	
	// returns whether the given file can be written to by this process
	public static boolean writable(java.io.File file) throws ServiceException {
	  return file == null ? false : file.canWrite();
	}
	
	// returns whether the given file can be written to by this process
	public static boolean writable(String filename) throws ServiceException {
	  return writable(tundra.support.file.construct(filename));
	}
	
	// returns whether the given file can be read by this process
	public static boolean readable(java.io.File file) throws ServiceException {
	  return file == null ? false : file.canRead();
	}
	
	// returns whether the given file can be read by this process
	public static boolean readable(String filename) throws ServiceException {
	  return readable(tundra.support.file.construct(filename));
	}
	
	// returns whether the given file can be executed by this process
	public static boolean executable(java.io.File file) throws ServiceException {
	  return file == null ? false : file.canExecute();
	}
	
	// returns whether the given file can be executed by this process
	public static boolean executable(String filename) throws ServiceException {
	  return executable(tundra.support.file.construct(filename));
	}
	
	// returns the length of the given file in bytes
	public static long length(java.io.File file) throws ServiceException {
	  return file == null ? 0 : file.length();
	}
	
	// returns the length of the given file in bytes
	public static long length(String filename) throws ServiceException {
	  return length(tundra.support.file.construct(filename));
	}
	
	// creates a new, empty file; if file is null, a temporary file is created
	public static java.io.File create(java.io.File file) throws ServiceException {
	  try {
	    if (file == null) {
	      file = java.io.File.createTempFile("tundra", null);
	    } else {
	      java.io.File parent = file.getParentFile();
	      if (parent != null) parent.mkdirs(); // automatically create directories if required
	      if (!file.createNewFile()) tundra.exception.raise("Unable to create file because it already exists: " + tundra.support.file.normalize(file));
	    }
	  } catch(java.io.IOException ex) {
	    tundra.exception.raise(ex);
	  }
	  return file;
	}
	
	// creates a new, empty file; if filename is null, a temporary file is created
	public static String create(String filename) throws ServiceException {
	  return tundra.support.file.normalize(create(tundra.support.file.construct(filename)));
	}
	
	// deletes a file
	public static void remove(java.io.File file) throws ServiceException {
	  if (file != null && exists(file) && !file.delete()) {
	    tundra.exception.raise("Unable to remove file: " + tundra.support.file.normalize(file));
	  }
	}
	
	// deletes a file
	public static void remove(String filename) throws ServiceException {
	  remove(tundra.support.file.construct(filename));
	}
	
	// update the modified time of the given file, or create it
	public static void touch(java.io.File file) throws ServiceException {
	  if (file.exists()) {
	    file.setLastModified((new java.util.Date()).getTime());
	  } else {
	    create(file);
	  }
	}
	
	// update the modified time of the given file, or create it
	public static void touch(String filename) throws ServiceException {
	  touch(tundra.support.file.construct(filename));
	}
	
	// renames a file
	public static void rename(java.io.File source, java.io.File target) throws ServiceException {
	  if (source != null && target != null) {
	    if (!exists(source) || exists(target) || !source.renameTo(target)) {
	      tundra.exception.raise("Unable to rename file " + tundra.support.file.normalize(source) + " to " + tundra.support.file.normalize(target));
	    }
	  }
	}
	
	// renames a file
	public static void rename(String source, String target) throws ServiceException {
	  rename(tundra.support.file.construct(source), tundra.support.file.construct(target));
	}
	
	// reads a file, returning the content as either a byte array or string
	public static Object read(java.io.File file, String mode, String encoding) throws ServiceException {
	  Object content = null;
	
	  if (file != null) {
	    try {
	      java.io.InputStream input = new java.io.FileInputStream(file);
	      java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream(tundra.support.constant.DEFAULT_BUFFER_SIZE);
	
	      tundra.stream.copy(input, output);
	
	      if (mode == null || mode.equals("bytes")) {
	        content = output.toByteArray();
	      } else {
	        content = output.toString(encoding == null? tundra.support.constant.DEFAULT_CHARACTER_ENCODING : encoding);
	      }
	    } catch (java.io.IOException ex) {
	      tundra.exception.raise(ex);
	    }
	  }
	
	  return content;
	}
	
	// reads a file, returning the content as either a byte array or string
	public static Object read(String filename, String mode, String encoding) throws ServiceException {
	  return read(tundra.support.file.construct(filename), mode, encoding);
	}
	
	// writes content (provided as a string, byte array or input stream) to a file; if the given file is null, a new temporary
	// file is automatically created
	public static java.io.File write(java.io.File file, String mode, Object content, String encoding) throws ServiceException {
	  try {
	    if (content != null) {
	      if (file == null || !exists(file)) file = create(file);
	      java.io.InputStream input = tundra.stream.normalize(content, encoding);
	      java.io.OutputStream output = new java.io.FileOutputStream(file, mode == null || mode.equals("append"));
	
	      tundra.stream.copy(input, output);
	    }
	  } catch (java.io.IOException ex) {
	    tundra.exception.raise(ex);
	  }
	  return file;
	}
	
	// writes content (provided as a string, byte array or input stream) to a file; if the given filename is null, a new 
	// temporary file is automatically created
	public static String write(String filename, String mode, Object content, String encoding) throws ServiceException {
	  return tundra.support.file.normalize(write(tundra.support.file.construct(filename), mode, content, encoding));
	}
	
	// opens a file for reading, appending, or writing, and processes it by calling the given service with the resulting $stream
	public static IData process(java.io.File file, String mode, String service, String input, IData pipeline) throws ServiceException {
	  if (file != null) {
	    if (input == null) input = "$stream";
	    Object stream = null;
	
	    try {
	      if (mode == null || mode.equals("read")) {
	        stream = new java.io.BufferedInputStream(new java.io.FileInputStream(file), tundra.support.constant.DEFAULT_BUFFER_SIZE);
	      } else {
	        if (!exists(file)) create(file);
	        stream = new java.io.BufferedOutputStream(new java.io.FileOutputStream(file, mode.equals("append")), tundra.support.constant.DEFAULT_BUFFER_SIZE);
	      }
	
	      IDataCursor cursor = pipeline.getCursor();
	      IDataUtil.put(cursor, input, stream);
	      cursor.destroy();
	
	      pipeline = tundra.service.invoke(service, pipeline);
	    } catch (java.io.IOException ex) {
	      tundra.exception.raise(ex);
	    } finally {
	      tundra.stream.close(stream);
	
	      IDataCursor cursor = pipeline.getCursor();
	      IDataUtil.remove(cursor, input);
	      cursor.destroy();
	    }
	  }
	  return pipeline;
	}
	
	// opens a file for reading, appending, or writing, and processes it by calling the given service with the resulting $stream
	public static IData process(String filename, String mode, String service, String input, IData pipeline) throws ServiceException {
	  return process(tundra.support.file.construct(filename), mode, service, input, pipeline);
	}
	// --- <<IS-END-SHARED>> ---
}

