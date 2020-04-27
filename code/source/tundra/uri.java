package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2020-04-27T17:02:23.259
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataMap;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.net.uri.URIHelper;
import permafrost.tundra.org.springframework.web.util.UriTemplate;
// --- <<IS-END-IMPORTS>> ---

public final class uri

{
	// ---( internal utility methods )---

	final static uri _instance = new uri();

	static uri _newInstance() { return new uri(); }

	static uri _cast(Object o) { return (uri)o; }

	// ---( server methods )---




	public static final void decode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document.encoded
		// [i] field:0:optional $content.encoding
		// [o] record:0:optional $document.decoded
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document.encoded", IData.class);
		    Charset encoding = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");

		    if (document == null) {
		        document = IDataHelper.get(cursor, "$uri.encoded", IData.class);
		        if (document == null) {
		            String string = IDataHelper.get(cursor, "$string", String.class);
		            IDataHelper.put(cursor, "$string", URIHelper.decode(string, encoding), false);
		        } else {
		            IDataHelper.put(cursor, "$uri.decoded", URIHelper.decode(document, encoding), false);
		        }
		    } else {
		        IDataHelper.put(cursor, "$document.decoded", URIHelper.decode(document, encoding), false);
		    }
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
		// [i] record:0:optional $uri
		// [i] - field:0:optional scheme
		// [i] - field:0:optional body
		// [i] - record:0:optional authority
		// [i] -- field:0:optional registry
		// [i] -- record:0:optional server
		// [i] --- field:0:optional user
		// [i] --- field:0:optional password
		// [i] --- field:0:required host
		// [i] --- field:0:optional port
		// [i] - field:1:optional path
		// [i] - field:0:optional path.absolute? {"true","false"}
		// [i] - field:0:optional file
		// [i] - record:0:optional query
		// [i] - field:0:optional fragment
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData uri = IDataHelper.get(cursor, "$uri", IData.class);
		    IDataHelper.put(cursor, "$string", URIHelper.emit(uri), false);
		} catch(URISyntaxException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void encode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(encode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document.decoded
		// [i] field:0:optional $content.encoding
		// [o] record:0:optional $document.encoded
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document.decoded", IData.class);
		    Charset encoding = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");

		    if (document == null) {
		        document = IDataHelper.get(cursor, "$uri.decoded", IData.class);
		        if (document == null) {
		            String string = IDataHelper.get(cursor, "$string", String.class);
		            IDataHelper.put(cursor, "$string", URIHelper.encode(string, encoding), false);
		        } else {
		            IDataHelper.put(cursor, "$uri.encoded", URIHelper.encode(document, encoding), false);
		        }
		    } else {
		        IDataHelper.put(cursor, "$document.encoded", URIHelper.encode(document, encoding), false);
		    }
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
		    IDataHelper.put(cursor, "$string", URIHelper.normalize(string), false);
		} catch(URISyntaxException ex) {
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
		// [o] record:0:optional $uri
		// [o] - field:0:optional scheme
		// [o] - field:0:optional body
		// [o] - record:0:optional authority
		// [o] -- field:0:optional registry
		// [o] -- record:0:optional server
		// [o] --- field:0:optional user
		// [o] --- field:0:optional password
		// [o] --- field:0:required host
		// [o] --- field:0:optional port
		// [o] - field:1:optional path
		// [o] - field:0:optional path.absolute? {"true","false"}
		// [o] - field:0:optional file
		// [o] - record:0:optional query
		// [o] - field:0:optional fragment
		// [o] - field:0:required absolute?
		// [o] - field:0:required opaque?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$uri", URIHelper.parse(string), false);
		} catch(URISyntaxException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void substitute (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(substitute)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] record:0:optional $scope
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String uri = IDataHelper.get(cursor, "$string", String.class);
		    IData scope = IDataHelper.get(cursor, "$scope", IData.class);

		    IDataHelper.put(cursor, "$string", URIHelper.substitute(uri, scope == null ? pipeline : scope), false);
		} catch(URISyntaxException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

