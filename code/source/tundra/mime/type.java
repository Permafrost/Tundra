package tundra.mime;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-12-15 15:09:04 GMT+10:00
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.mime.MIMETypeHelper;
// --- <<IS-END-IMPORTS>> ---

public final class type

{
	// ---( internal utility methods )---

	final static type _instance = new type();

	static type _newInstance() { return new type(); }

	static type _cast(Object o) { return (type)o; }

	// ---( server methods )---




	public static final void classify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(classify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $content.type
		// [o] field:0:optional $content.class {"csv","json","psv","tsv","xml","yaml"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    MimeType type = IDataHelper.get(cursor, "$content.type", MimeType.class);
		    IDataHelper.put(cursor, "$content.class", MIMETypeHelper.classify(type), false);
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
		// [i] record:0:optional $type
		// [i] - field:0:required type
		// [i] - field:0:required subtype
		// [i] - record:0:optional parameters
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData type = IDataHelper.get(cursor, "$type", IData.class);
		    IDataHelper.put(cursor, "$string", MIMETypeHelper.emit(type), false);
		} catch(MimeTypeParseException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void equal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(equal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string.x
		// [i] field:0:optional $string.y
		// [o] field:0:required $equal?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string1 = IDataHelper.get(cursor, "$string.x", String.class);
		    String string2 = IDataHelper.get(cursor, "$string.y", String.class);

		    IDataHelper.put(cursor, "$equal?", MIMETypeHelper.equal(string1, string2), String.class);
		} catch(MimeTypeParseException ex) {
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
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$string", MIMETypeHelper.normalize(string), false);
		} catch(MimeTypeParseException ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] field:0:optional $string
		// [o] record:0:optional $type
		// [o] - field:0:required type
		// [o] - field:0:required subtype
		// [o] - record:0:optional parameters
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$type", MIMETypeHelper.parse(string), false);
		} catch(MimeTypeParseException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void validate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:optional $raise? {"false","true"}
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);

		    IDataHelper.put(cursor, "$valid?", MIMETypeHelper.validate(string, raise), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

