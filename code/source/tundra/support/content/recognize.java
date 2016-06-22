package tundra.support.content;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-06-22 14:19:02.370
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import permafrost.tundra.configuration.ConfigurationManager;
import permafrost.tundra.content.format.Format;
import permafrost.tundra.content.format.Recognizer;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
// --- <<IS-END-IMPORTS>> ---

public final class recognize

{
	// ---( internal utility methods )---

	final static recognize _instance = new recognize();

	static recognize _newInstance() { return new recognize(); }

	static recognize _cast(Object o) { return (recognize)o; }

	// ---( server methods )---




	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		Recognizer.clear();
		// --- <<IS-END>> ---


	}



	public static final void list (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(list)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:1:optional $formats
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDataUtil.put(cursor, "$formats", Format.toIDataArray(Recognizer.list()));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void recognize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(recognize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] field:0:required $recognized?
		// [o] record:0:optional $recognized.format
		// [o] - field:0:required name
		// [o] - field:0:optional description
		// [o] - field:0:optional content.type
		// [o] - record:0:optional attributes
		// [o] - record:0:optional namespace
		// [o] -- field:0:optional default
		// [o] - field:0:optional schema.parse
		// [o] - field:0:optional schema.validate
		// [o] - field:0:required recognize
		// [o] - field:0:optional enabled?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Format format = Recognizer.recognize(pipeline);
		    boolean recognized = format != null;
		    IDataUtil.put(cursor, "$recognized?", BooleanHelper.emit(recognized));
		    if (recognized) IDataUtil.put(cursor, "$recognized.format", format.getIData());
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void refresh (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(refresh)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] configurations = ConfigurationManager.list(true, false);

		    List<Format> formats = new ArrayList<Format>();
		    List<Exception> exceptions = new ArrayList<Exception>();

		    for (IData configuration : configurations) {
		        Object value = IDataHelper.get(configuration, "configuration/tundra.content:recognize");

		        try {
		            if (value instanceof IData) {
		                formats.add(Format.of((IData)value));
		            } else if (value instanceof IData[]) {
		                formats.addAll(Format.of(Arrays.asList((IData[])value)));
		            }
		        } catch(Exception ex) {
		            exceptions.add(ex);
		        }
		    }

		    Recognizer.initialize(formats);

		    if (exceptions.size() > 0) ExceptionHelper.raise(exceptions);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

