package tundra.mime;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-06-06 16:28:27.644
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import javax.activation.MimeTypeParseException;
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
		    IData type = IDataUtil.getIData(cursor, "$type");
		    if (type != null) IDataUtil.put(cursor, "$string", MIMETypeHelper.emit(type));
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
		    String string1 = IDataUtil.getString(cursor, "$string.x");
		    String string2 = IDataUtil.getString(cursor, "$string.y");

		    IDataUtil.put(cursor, "$equal?", BooleanHelper.emit(MIMETypeHelper.equal(string1, string2)));
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
		    String string = IDataUtil.getString(cursor, "$string");
		    if (string != null) IDataUtil.put(cursor, "$string", MIMETypeHelper.normalize(string));
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
		    String string = IDataUtil.getString(cursor, "$string");
		    if (string != null) IDataUtil.put(cursor, "$type", MIMETypeHelper.parse(string));
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
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataUtil.getString(cursor, "$string");
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));

		    IDataUtil.put(cursor, "$valid?", BooleanHelper.emit(MIMETypeHelper.validate(string, raise)));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

