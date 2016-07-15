package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-07-15 20:16:58 EST
// -----( ON-HOST: 192.168.66.129

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
		// [i] field:0:optional $algorithm {"Canonical XML Version 1.0","Canonical XML Version 1.0 With Comments","Canonical XML Version 1.1","Canonical XML Version 1.1 With Comments","Exclusive Canonical XML Version 1.0","Exclusive Canonical XML Version 1.0 With Comments"}
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content.canonical
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		    XMLCanonicalizationAlgorithm algorithm = XMLCanonicalizationAlgorithm.normalize(IDataUtil.getString(cursor, "$algorithm"));
		    ObjectConvertMode mode = ObjectConvertMode.normalize(IDataUtil.getString(cursor, "$mode"));
		
		    if (content != null) IDataUtil.put(cursor, "$content.canonical", ObjectHelper.convert(XMLCanonicalizationHelper.canonicalize(BytesHelper.normalize(content, charset), charset, algorithm), charset, mode));
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
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		tundra.html.decode(pipeline);
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
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Node node = (Node)IDataUtil.get(cursor, "$node");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		    ObjectConvertMode mode = ObjectConvertMode.normalize(IDataUtil.getString(cursor, "$mode"));
		
		    Object content = ObjectHelper.convert(NodeHelper.emit(node, charset), mode);
		
		    if (content != null) IDataUtil.put(cursor, "$content", content);
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
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		tundra.html.encode(pipeline);
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
		// [i] field:0:optional $mode {"stream","bytes","string"}
		// [o] object:0:optional $content.minified
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		
		    ObjectConvertMode mode = ObjectConvertMode.normalize(IDataUtil.getString(cursor, "$mode"));
		
		    content = ObjectHelper.convert(XMLMinificationHelper.minify(InputStreamHelper.normalize(content, charset)), charset, mode);
		
		    if (content != null) IDataUtil.put(cursor, "$content.minified", content);
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
		    Object content = IDataUtil.get(cursor, "$content");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		    NamespaceContext namespace = IDataNamespaceContext.of(IDataUtil.getIData(cursor, "$namespace"));
		
		    Node node = null;
		    if (content instanceof Node) {
		        node = (Node)content;
		    } else if (content instanceof InputSource) {
		        node = DocumentHelper.parse((InputSource)content, namespace);
		    } else if (content != null) {
		        node = DocumentHelper.parse(InputStreamHelper.normalize(content, charset), charset, true, namespace);
		    }
		
		    if (node != null) IDataUtil.put(cursor, "$document", NodeHelper.parse(node, namespace, true));
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
		    Charset contentCharset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$content.encoding"));
		    Object schema = IDataUtil.get(cursor, "$schema");
		    Charset schemaCharset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$schema.encoding"));
		    boolean raise = BooleanHelper.parse(IDataUtil.getString(cursor, "$raise?"));
		
		    String[] errors = XMLHelper.validate(InputStreamHelper.normalize(content, contentCharset), contentCharset, InputStreamHelper.normalize(schema, schemaCharset), schemaCharset, raise);
		    boolean valid = content != null && (errors == null || errors.length == 0);
		
		    IDataUtil.put(cursor, "$valid?", BooleanHelper.emit(valid));
		    if (!valid && errors != null) IDataUtil.put(cursor, "$errors", errors);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

