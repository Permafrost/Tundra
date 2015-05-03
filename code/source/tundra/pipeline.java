package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-03 17:00:22 EST
// -----( ON-HOST: 172.16.167.128

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataXMLParser;
import permafrost.tundra.io.StreamHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectHelper;
// --- <<IS-END-IMPORTS>> ---

public final class pipeline

{
	// ---( internal utility methods )---

	final static pipeline _instance = new pipeline();

	static pipeline _newInstance() { return new pipeline(); }

	static pipeline _cast(Object o) { return (pipeline)o; }

	// ---( server methods )---




	public static final void capture (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(capture)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $pipeline
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$pipeline", IDataHelper.duplicate(pipeline, false));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $preserve
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String[] keys = IDataUtil.getStringArray(cursor, "$preserve");
		  IDataHelper.clear(pipeline, keys);
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
		// [i] field:0:required $key.source
		// [i] field:0:required $key.target
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String source = IDataUtil.getString(cursor, "$key.source");
		  String target = IDataUtil.getString(cursor, "$key.target");
		  IDataHelper.copy(pipeline, source, target);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void drop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(drop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $key
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String key = IDataUtil.getString(cursor, "$key");
		  IDataHelper.drop(pipeline, key);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		  IDataUtil.remove(cursor, "$encoding");
		  IDataUtil.remove(cursor, "$mode");
		
		  IDataUtil.put(cursor, "$content", ObjectHelper.convert(IDataXMLParser.getInstance().emit(pipeline, encoding), encoding, mode));
		} catch(IOException ex) {
		  ExceptionHelper.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void first (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(first)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		try {
		  if (cursor.first()) {
		    String key = cursor.getKey();
		    Object value = cursor.getValue();
		    IDataUtil.put(cursor, "$key", key);
		    IDataUtil.put(cursor, "$value", value);
		  }
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String key = IDataUtil.getString(cursor, "$key");
		  IDataUtil.put(cursor, "$value", IDataHelper.get(pipeline, key));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void last (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(last)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		try {
		  if (cursor.last()) {
		    String key = cursor.getKey();
		    Object value = cursor.getValue();
		    IDataUtil.put(cursor, "$key", key);
		    IDataUtil.put(cursor, "$value", value);
		  }
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
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$length", "" + IDataHelper.size(pipeline));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void merge (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(merge)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  merge(pipeline, IDataUtil.getIData(cursor, "$document"));
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
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData copy = IDataHelper.normalize(pipeline);
		  IDataHelper.clear(pipeline);
		  merge(pipeline, copy);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void parse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parse)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object content = IDataUtil.get(cursor, "$content");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		
		  merge(pipeline, IDataXMLParser.getInstance().parse(StreamHelper.normalize(content, encoding)));
		} catch(IOException ex) {
		  ExceptionHelper.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void put (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(put)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $key
		// [i] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String key = IDataUtil.getString(cursor, "$key");
		  Object value = IDataUtil.get(cursor, "$value");
		  IDataHelper.put(pipeline, key, value);
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
		// [i] field:0:required $key.source
		// [i] field:0:required $key.target
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String source = IDataUtil.getString(cursor, "$key.source");
		  String target = IDataUtil.getString(cursor, "$key.target");
		  IDataHelper.rename(pipeline, source, target);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void sort (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sort)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		sort(pipeline, false);
		// --- <<IS-END>> ---

                
	}



	public static final void substitute (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(substitute)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData copy = IDataHelper.substitute(pipeline, pipeline, true);
		  IDataHelper.clear(pipeline);
		  merge(pipeline, copy);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// merges the contents of the given document into the given pipeline
	public static void merge(IData target, IData source) {
	  if (target != null && source != null) IDataUtil.merge(source, target);
	}
	
	// sorts the elements in the pipeline by its keys in natural ascending order
	public static void sort(IData pipeline, boolean recurse) {
	  IData sorted = IDataHelper.sort(pipeline, recurse);
	  IDataHelper.clear(pipeline, null);
	  IDataUtil.append(sorted, pipeline);
	}
	// --- <<IS-END-SHARED>> ---
}

