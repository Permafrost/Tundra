package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-05-26 20:01:43 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import permafrost.tundra.io.CloseableHelper;
import permafrost.tundra.io.FileHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.io.OutputStreamHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
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
		    String source = IDataUtil.getString(cursor, "$file.source");
		    String target = IDataUtil.getString(cursor, "$file.target");
		    String mode = IDataUtil.getString(cursor, "$mode");
		
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
		    String file = IDataUtil.getString(cursor, "$file");
		    IDataUtil.put(cursor, "$file", FileHelper.create(file));
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
		    String file = IDataUtil.getString(cursor, "$file");
		    IDataUtil.put(cursor, "$executable?", "" + FileHelper.isExecutable(file));
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
		    IDataUtil.put(cursor, "$exists?", "" + FileHelper.exists(file));
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
		    IDataUtil.put(cursor, "$length", "" + FileHelper.length(file));
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
		    String file = IDataUtil.getString(cursor, "$file");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String mode = IDataUtil.getString(cursor, "$mode");
		
		    IDataUtil.put(cursor, "$match?", "" + FileHelper.match(file, pattern, mode == null || mode.equalsIgnoreCase("regular expression")  || mode.equalsIgnoreCase("regex")));
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
		    IDataUtil.put(cursor, "$file", FileHelper.normalize(IDataUtil.getString(cursor, "$file")));
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
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [i] field:0:optional $encoding
		// [o] object:0:required $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String file = IDataUtil.getString(cursor, "$file");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		
		    ObjectConvertMode mode = ObjectConvertMode.normalize(IDataUtil.getString(cursor, "$mode"));
		
		    IDataUtil.put(cursor, "$content", ObjectHelper.convert(FileHelper.readToBytes(file), charset, mode));
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
		    String file = IDataUtil.getString(cursor, "$file");
		    IDataUtil.put(cursor, "$readable?", "" + FileHelper.isReadable(file));
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
		    String file = IDataUtil.getString(cursor, "$file");
		    IDataUtil.put(cursor, "$file.properties", FileHelper.getPropertiesAsIData(file));
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
		    FileHelper.remove(IDataUtil.getString(cursor, "$file"));
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
		    String source = IDataUtil.getString(cursor, "$file.source");
		    String target = IDataUtil.getString(cursor, "$file.target");
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
		    FileHelper.touch(IDataUtil.getString(cursor, "$file"));
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
		    String file = IDataUtil.getString(cursor, "$file");
		    IDataUtil.put(cursor, "$type", FileHelper.getMIMEType(file));
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
		    IDataUtil.put(cursor, "$writable?", "" + FileHelper.isWritable(file));
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
		// [o] field:0:optional $file
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String file = IDataUtil.getString(cursor, "$file");
		    String mode = IDataUtil.getString(cursor, "$mode");
		    Object content = IDataUtil.get(cursor, "$content");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		
		    file = FileHelper.writeFromStream(file, InputStreamHelper.normalize(content, charset), mode == null || mode.equalsIgnoreCase("append"));
		
		    IDataUtil.put(cursor, "$file", file);
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
	
	            pipeline = tundra.service.invoke(service, pipeline);
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

