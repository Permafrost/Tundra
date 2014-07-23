package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-07-23 21:09:05 EST
// -----( ON-HOST: 172.16.189.176

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




	public static final void copy (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(copy)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file.source
		// [i] field:0:required $file.target
		// [i] field:0:optional $mode {&quot;append&quot;,&quot;write&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String source = IDataUtil.getString(cursor, "$file.source");
		  String target = IDataUtil.getString(cursor, "$file.target");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  copy(source, target, mode);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



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



	public static final void match (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(match)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [i] field:0:required $pattern
		// [i] field:0:optional $mode {&quot;regex&quot;,&quot;wildcard&quot;}
		// [o] field:0:required $match?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		  String mode = IDataUtil.getString(cursor, "$mode");
		  boolean regex = (mode == null || mode.equalsIgnoreCase("regex"));
		
		  IDataUtil.put(cursor, "$match?", "" + tundra.support.file.match(file, pattern, regex));
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
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
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



	public static final void reflect (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reflect)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [o] record:0:required $file.properties
		// [o] - field:0:required exists?
		// [o] - field:0:optional parent
		// [o] - field:0:optional name
		// [o] - field:0:optional base
		// [o] - field:0:optional extension
		// [o] - field:0:required type
		// [o] - field:0:optional length
		// [o] - field:0:optional modified
		// [o] - field:0:optional executable?
		// [o] - field:0:optional readable?
		// [o] - field:0:optional writable?
		// [o] - field:0:required uri
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String file = IDataUtil.getString(cursor, "$file");
		  IDataUtil.put(cursor, "$file.properties", reflect(file));
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
	
	      if (mode == null || mode.equals("stream")) {
	        content = new java.io.ByteArrayInputStream(output.toByteArray());
	      } else if (mode.equals("bytes")) {
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
	
	// copies a source file to a target file
	public static void copy(java.io.File source, java.io.File target, String mode) throws ServiceException {
	  if (source != null && target != null) {
	    try {
	      java.io.InputStream input = new java.io.FileInputStream(source);
	      java.io.OutputStream output = new java.io.FileOutputStream(target, mode == null || mode.equals("append"));
	
	      tundra.stream.copy(input, output);
	    } catch (java.io.IOException ex) {
	      tundra.exception.raise(ex);
	    }
	  }
	}
	
	// copies a source file to a target file
	public static void copy(String source, String target, String mode) throws ServiceException {
	  copy(tundra.support.file.construct(source), tundra.support.file.construct(target), mode);
	}
	
	// returns only the name component of the given filename
	public static String name(String filename) throws ServiceException {
	  return name(tundra.support.file.construct(filename));
	}
	
	// returns only the name component of the given filename
	public static String name(java.io.File file) throws ServiceException {
	  String name = file.getName();
	  return file.equals("") ? null : name;
	}
	
	// returns the base and extension parts of the name component of the given filename
	public static String[] parts(String filename) throws ServiceException {
	  return parts(tundra.support.file.construct(filename));
	}
	
	// returns the base and extension parts of the name component of the given filename
	public static String[] parts(java.io.File file) throws ServiceException {
	  String[] parts = null;
	  String name = name(file);
	  if (name != null) parts = name.split("\\.(?=[^\\.]+$)");
	  return parts;
	}
	
	// returns only the parent directory containing the given filename
	public static String parent(String filename) throws ServiceException {
	  return parent(tundra.support.file.construct(filename));
	}
	
	// returns only the parent directory containing the given filename
	public static String parent(java.io.File file) throws ServiceException {
	  return tundra.support.file.normalize(file.getParent());
	}
	
	// returns the last modified datetime of the given file as an ISO8601 formatted datetime string
	public static String modified(String filename) throws ServiceException {
	  return modified(tundra.support.file.construct(filename));
	}
	
	// returns the last modified datetime of the given file as an ISO8601 formatted datetime string
	public static String modified(java.io.File file) throws ServiceException {
	  return tundra.datetime.emit(new java.util.Date(file.lastModified()));
	}
	
	// returns an IData document containing the properties of the given file
	public static IData reflect(String filename) throws ServiceException {
	  return reflect(tundra.support.file.construct(filename));
	}
	
	// returns an IData document containing the properties of the given file
	public static IData reflect(java.io.File file) throws ServiceException {
	  IData output = IDataFactory.create();
	  IDataCursor cursor = output.getCursor();
	
	  boolean isFile = file.isFile();
	  boolean exists = exists(file);
	
	  IDataUtil.put(cursor, "exists?", "" + exists);
	
	  String parent = parent(file);
	  if (parent != null) IDataUtil.put(cursor, "parent", parent);
	
	  String name = name(file);
	  if (name != null) IDataUtil.put(cursor, "name", name(file));
	
	  String[] parts = parts(file);
	  if (parts != null) {
	    if (parts.length > 0) IDataUtil.put(cursor, "base", parts[0]);
	    if (parts.length > 1) IDataUtil.put(cursor, "extension", parts[1]);
	  }
	
	  if (isFile) IDataUtil.put(cursor, "type", type(file));
	
	  IDataUtil.put(cursor, "length", "" + length(file));
	
	  if (exists) {
	    IDataUtil.put(cursor, "modified", modified(file));
	    IDataUtil.put(cursor, "executable?", "" + executable(file));
	    IDataUtil.put(cursor, "readable?", "" + readable(file));
	    IDataUtil.put(cursor, "writable?", "" + writable(file));
	  }
	
	  IDataUtil.put(cursor, "uri", tundra.support.file.normalize(file));
	
	  cursor.destroy();
	
	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

