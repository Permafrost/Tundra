package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-05-01 18:39:19 EST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.io.StreamHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.BytesHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.xml.XMLHelper;
// --- <<IS-END-IMPORTS>> ---

public final class xml

{
	// ---( internal utility methods )---

	final static xml _instance = new xml();

	static xml _newInstance() { return new xml(); }

	static xml _cast(Object o) { return (xml)o; }

	// ---( server methods )---




	public static final void canonicalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(canonicalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [i] field:0:optional $algorithm {"Canonical XML Version 1.0","Canonical XML Version 1.0 With Comments","Canonical XML Version 1.1","Canonical XML Version 1.1 With Comments","Exclusive Canonical XML Version 1.0","Exclusive Canonical XML Version 1.0 With Comments"}
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content.canonical
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    String encoding = IDataUtil.getString(cursor, "$encoding");
		    String algorithm = IDataUtil.getString(cursor, "$algorithm");
		    String mode = IDataUtil.getString(cursor, "$mode");

		    if (content != null) IDataUtil.put(cursor, "$content.canonical", ObjectHelper.convert(XMLHelper.canonicalize(BytesHelper.normalize(content, encoding), encoding, algorithm), mode));
		} catch(IOException ex) {
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
		// [i] object:0:optional $content
		// [i] field:0:optional $content.encoding
		// [i] object:0:optional $schema
		// [i] field:0:optional $schema.encoding
		// [i] field:0:optional $raise? {"false","true"}
		// [o] field:0:required $valid?
		// [o] field:1:optional $errors
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    String contentEncoding = IDataUtil.getString(cursor, "$content.encoding");
		    Object schema = IDataUtil.get(cursor, "$schema");
		    String schemaEncoding = IDataUtil.getString(cursor, "$schema.encoding");
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));

		    String[] errors = XMLHelper.validate(StreamHelper.normalize(content, contentEncoding), StreamHelper.normalize(schema, schemaEncoding), raise);
		    boolean valid = content != null && (errors == null || errors.length == 0);

		    IDataUtil.put(cursor, "$valid?", "" + valid);
		    if (!valid && errors != null) IDataUtil.put(cursor, "$errors", errors);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

