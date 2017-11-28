package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-11-28T16:47:05.297
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.nio.charset.Charset;
import java.io.IOException;
import javax.xml.namespace.NamespaceContext;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.html.HTMLHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.BytesHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.xml.dom.DocumentHelper;
import permafrost.tundra.xml.dom.NodeHelper;
import permafrost.tundra.xml.namespace.IDataNamespaceContext;
import permafrost.tundra.xml.XMLCanonicalizationAlgorithm;
import permafrost.tundra.xml.XMLCanonicalizationHelper;
import permafrost.tundra.xml.XMLHelper;
import permafrost.tundra.xml.XMLMinificationHelper;
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
		// [i] field:0:optional $algorithm {&quot;Canonical XML Version 1.0&quot;,&quot;Canonical XML Version 1.0 With Comments&quot;,&quot;Canonical XML Version 1.1&quot;,&quot;Canonical XML Version 1.1 With Comments&quot;,&quot;Exclusive Canonical XML Version 1.0&quot;,&quot;Exclusive Canonical XML Version 1.0 With Comments&quot;}
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content.canonical
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		    XMLCanonicalizationAlgorithm algorithm = XMLCanonicalizationAlgorithm.normalize(IDataHelper.get(cursor, "$algorithm", String.class));
		    ObjectConvertMode mode = IDataHelper.get(cursor, "$mode", ObjectConvertMode.class);

		    if (content != null) IDataHelper.put(cursor, "$content.canonical", ObjectHelper.convert(XMLCanonicalizationHelper.canonicalize(BytesHelper.normalize(content, charset), charset, algorithm), charset, mode));
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void decode (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(decode)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $xml.encoded
		// [i] - field:0:optional $value
		// [i] - field:1:optional $value.list
		// [i] - field:2:optional $value.table
		// [o] record:0:optional $xml.decoded
		// [o] - field:0:optional $value
		// [o] - field:1:optional $value.list
		// [o] - field:2:optional $value.table
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$xml.encoded", IData.class);

		    if (document == null) {
		        document = IDataHelper.get(cursor, "$document.encoded", IData.class);
		        if (document == null) {
		            String string = IDataHelper.get(cursor, "$string", String.class);
		            IDataHelper.put(cursor, "$string", HTMLHelper.decode(string), false);
		        } else {
		            IDataHelper.put(cursor, "$document.decoded", HTMLHelper.decode(document), false);
		        }
		    } else {
		        IDataHelper.put(cursor, "$xml.decoded", HTMLHelper.decode(document), false);
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
		// [i] object:0:optional $node
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		// [o] field:0:optional $encoding
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Node node = IDataHelper.get(cursor, "$node", Node.class);
		    Charset charset = IDataHelper.getOrDefault(cursor, "$encoding", Charset.class, CharsetHelper.DEFAULT_CHARSET);
		    ObjectConvertMode mode = IDataHelper.get(cursor, "$mode", ObjectConvertMode.class);

		    Object content = ObjectHelper.convert(NodeHelper.emit(node, charset), mode);

		    IDataHelper.put(cursor, "$content", content, false);
		    if (content != null && !(content instanceof String)) IDataHelper.put(cursor, "$encoding", charset.name());
		} catch(IOException ex) {
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
		// [i] record:0:optional $xml.decoded
		// [i] - field:0:optional $value
		// [i] - field:1:optional $value.list
		// [i] - field:2:optional $value.table
		// [o] record:0:optional $xml.encoded
		// [o] - field:0:optional $value
		// [o] - field:1:optional $value.list
		// [o] - field:2:optional $value.table
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$xml.decoded", IData.class);

		    if (document == null) {
		        document = IDataHelper.get(cursor, "$document.decoded", IData.class);
		        if (document == null) {
		            String string = IDataHelper.get(cursor, "$string", String.class);
		            IDataHelper.put(cursor, "$string", HTMLHelper.encode(string), false);
		        } else {
		            IDataHelper.put(cursor, "$document.encoded", HTMLHelper.encode(document), false);
		        }
		    } else {
		        IDataHelper.put(cursor, "$xml.encoded", HTMLHelper.encode(document), false);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void minify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(minify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content.minified
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);

		    ObjectConvertMode mode = IDataHelper.get(cursor, "$mode", ObjectConvertMode.class);

		    IDataHelper.put(cursor, "$content.minified", ObjectHelper.convert(XMLMinificationHelper.minify(InputStreamHelper.normalize(content, charset)), charset, mode), false);
		} catch(IOException ex) {
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
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [i] record:0:optional $namespace
		// [i] - field:0:optional default
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		    NamespaceContext namespace = IDataHelper.get(cursor, "$namespace", IDataNamespaceContext.class);

		    Node node = null;
		    if (content instanceof Node) {
		        node = (Node)content;
		    } else if (content instanceof InputSource) {
		        node = DocumentHelper.parse((InputSource)content, namespace);
		    } else if (content != null) {
		        node = DocumentHelper.parse(InputStreamHelper.normalize(content, charset), charset, true, namespace);
		    }

		    if (node != null) IDataHelper.put(cursor, "$document", NodeHelper.parse(node, namespace, true));
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
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $valid?
		// [o] field:1:optional $errors
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset contentCharset = IDataHelper.get(cursor, "$content.encoding", Charset.class);
		    Object schema = IDataHelper.get(cursor, "$schema");
		    Charset schemaCharset = IDataHelper.get(cursor, "$schema.encoding", Charset.class);
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);

		    String[] errors = XMLHelper.validate(InputStreamHelper.normalize(content, contentCharset), contentCharset, InputStreamHelper.normalize(schema, schemaCharset), schemaCharset, raise);
		    boolean valid = content != null && (errors == null || errors.length == 0);

		    IDataHelper.put(cursor, "$valid?", valid, String.class);
		    IDataHelper.put(cursor, "$errors", errors, false, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

