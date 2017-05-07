package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-05-06 15:51:46 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.CloseableHelper;
import permafrost.tundra.io.FileHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.io.OutputStreamHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.math.LongHelper;
import permafrost.tundra.server.ServiceHelper;
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
		// [i] field:0:optional $mode {"append","write"}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String source = IDataHelper.get(cursor, "$file.source", String.class);
		    String target = IDataHelper.get(cursor, "$file.target", String.class);
		    String mode = IDataHelper.get(cursor, "$mode", String.class);
		
		    FileHelper.copy(source, target, mode == null || mode.equalsIgnoreCase("append"));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] field:0:optional $file
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    IDataHelper.put(cursor, "$file", FileHelper.create(file));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    IDataHelper.put(cursor, "$executable?", FileHelper.isExecutable(file), String.class);
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
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    IDataHelper.put(cursor, "$exists?", FileHelper.exists(file), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void gzip (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(gzip)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [i] field:0:optional $file.gzip
		// [i] field:0:optional $replace? {"false","true"}
		// [o] field:0:required $file.gzip
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String source = IDataHelper.get(cursor, "$file", String.class);
		    String target = IDataHelper.get(cursor, "$file.gzip", String.class);
		    boolean replace = IDataHelper.getOrDefault(cursor, "$replace?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$file.gzip", FileHelper.gzip(source, target, replace));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    IDataHelper.put(cursor, "$length", FileHelper.length(file), String.class);
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
		// [i] field:0:optional $mode {"regular expression","wildcard"}
		// [o] field:0:required $match?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String mode = IDataHelper.get(cursor, "$mode", String.class);
		
		    IDataHelper.put(cursor, "$match?", FileHelper.match(file, pattern, mode == null || mode.equalsIgnoreCase("regular expression")  || mode.equalsIgnoreCase("regex")), String.class);
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
		    IDataHelper.put(cursor, "$file", FileHelper.normalize(IDataHelper.get(cursor, "$file", String.class)));
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
		// [i] field:0:optional $mode {"read","append","write"}
		// [i] field:0:required $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $service.input
		// [o] record:0:optional $pipeline
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    String mode = IDataHelper.get(cursor, "$mode", String.class);
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    String input = IDataHelper.get(cursor, "$service.input", String.class);
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    boolean scoped = scope != null;
		
		    scope = process(file, mode, service, input, scoped? scope : pipeline);
		
		    if (scoped) IDataHelper.put(cursor, "$pipeline", scope);
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
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [i] field:0:optional $encoding
		// [o] object:0:required $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		
		    ObjectConvertMode mode = IDataHelper.get(cursor, "$mode", ObjectConvertMode.class);
		
		    IDataHelper.put(cursor, "$content", ObjectHelper.convert(FileHelper.readToBytes(file), charset, mode));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    IDataHelper.put(cursor, "$readable?", FileHelper.isReadable(file), String.class);
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
		// [o] - field:0:optional type
		// [o] - field:0:optional length
		// [o] - field:0:optional modified
		// [o] - field:0:optional executable?
		// [o] - field:0:optional readable?
		// [o] - field:0:optional writable?
		// [o] - field:0:required uri
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    IDataHelper.put(cursor, "$file.properties", FileHelper.getPropertiesAsIData(file));
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
		    FileHelper.remove(IDataHelper.get(cursor, "$file", String.class));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    String source = IDataHelper.get(cursor, "$file.source", String.class);
		    String target = IDataHelper.get(cursor, "$file.target", String.class);
		
		    FileHelper.rename(source, target);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    FileHelper.touch(IDataHelper.get(cursor, "$file", String.class));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    IDataHelper.put(cursor, "$type", FileHelper.getMIMEType(file));
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
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    IDataHelper.put(cursor, "$writable?", FileHelper.isWritable(file), String.class);
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
		// [i] field:0:optional $mode {"append","write"}
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [o] field:0:required $file
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String file = IDataHelper.get(cursor, "$file", String.class);
		    String mode = IDataHelper.get(cursor, "$mode", String.class);
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		
		    file = FileHelper.writeFromStream(file, InputStreamHelper.normalize(content, charset), mode == null || mode.equalsIgnoreCase("append"));
		
		    IDataHelper.put(cursor, "$file", file);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void zip (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(zip)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $file
		// [i] field:0:optional $file.zip
		// [i] field:0:optional $replace? {"false","true"}
		// [o] field:0:required $file.zip
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String source = IDataHelper.get(cursor, "$file", String.class);
		    String target = IDataHelper.get(cursor, "$file.zip", String.class);
		    boolean replace = IDataHelper.get(cursor, "$replace?", Boolean.class);
		
		    IDataHelper.put(cursor, "$file.zip", FileHelper.zip(source, target, replace));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// opens a file for reading, appending, or writing, and processes it by calling the given service with the resulting $stream
	public static IData process(java.io.File file, String mode, String service, String input, IData pipeline) throws ServiceException {
	    if (file != null) {
	        if (input == null) input = "$stream";
	        Closeable stream = null;
	
	        try {
	            if (mode == null || mode.equals("read")) {
	                stream = InputStreamHelper.normalize(new java.io.FileInputStream(file));
	            } else {
	                if (!FileHelper.exists(file)) FileHelper.create(file);
	                stream = OutputStreamHelper.normalize(new java.io.FileOutputStream(file, mode.equalsIgnoreCase("append")));
	            }
	
	            IDataCursor cursor = pipeline.getCursor();
	            IDataUtil.put(cursor, input, stream);
	            cursor.destroy();
	
	            pipeline = ServiceHelper.invoke(service, pipeline);
	        } catch (IOException ex) {
	            ExceptionHelper.raise(ex);
	        } finally {
	            CloseableHelper.close(stream);
	
	            IDataCursor cursor = pipeline.getCursor();
	            IDataUtil.remove(cursor, input);
	            cursor.destroy();
	        }
	    }
	    return pipeline;
	}
	
	// opens a file for reading, appending, or writing, and processes it by calling the given service with the resulting $stream
	public static IData process(String filename, String mode, String service, String input, IData pipeline) throws ServiceException {
	    return process(FileHelper.construct(filename), mode, service, input, pipeline);
	}
	// --- <<IS-END-SHARED>> ---
}

