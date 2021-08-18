package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-08-18 10:56:24 AEST
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
import permafrost.tundra.data.transform.Transformer;
import permafrost.tundra.data.transform.TransformerMode;
import permafrost.tundra.data.transform.xml.namespace.Normalizer;
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
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [i] field:0:optional $algorithm {&quot;Canonical XML Version 1.0&quot;,&quot;Canonical XML Version 1.0 With Comments&quot;,&quot;Canonical XML Version 1.1&quot;,&quot;Canonical XML Version 1.1 With Comments&quot;,&quot;Exclusive Canonical XML Version 1.0&quot;,&quot;Exclusive Canonical XML Version 1.0 With Comments&quot;}
		// [o] object:0:optional $content.canonical
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");
		    XMLCanonicalizationAlgorithm algorithm = XMLCanonicalizationAlgorithm.normalize(IDataHelper.get(cursor, "$algorithm", String.class));
		
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
		// [i] record:0:optional $document.encoded
		// [o] record:0:optional $document.decoded
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document.encoded", IData.class);
		
		    if (document == null) {
		        String string = IDataHelper.get(cursor, "$string", String.class);
		        IDataHelper.put(cursor, "$string", XMLHelper.decode(string), false);
		    } else {
		        IDataHelper.put(cursor, "$document.decoded", XMLHelper.decode(document), false);
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
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		// [o] field:0:optional $content.encoding
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Node node = IDataHelper.get(cursor, "$node", Node.class);
		    Charset charset = IDataHelper.firstOrDefault(cursor, Charset.class, CharsetHelper.DEFAULT_CHARSET, "$content.encoding", "$encoding");
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");
		
		    Object content = ObjectHelper.convert(NodeHelper.emit(node, charset), mode);
		
		    IDataHelper.put(cursor, "$content", content, false);
		    if (content != null && !(content instanceof String)) IDataHelper.put(cursor, "$content.encoding", charset.name());
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
		// [i] record:0:optional $document.decoded
		// [i] field:0:optional $attribute.prefix
		// [o] record:0:optional $document.encoded
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData document = IDataHelper.get(cursor, "$document.decoded", IData.class);
		    String attributePrefix = IDataHelper.get(cursor, "$attribute.prefix", String.class);
		
		    if (document == null) {
		        String string = IDataHelper.get(cursor, "$string", String.class);
		        IDataHelper.put(cursor, "$string", XMLHelper.encode(string), false);
		    } else {
		        IDataHelper.put(cursor, "$document.encoded", XMLHelper.encode(document, attributePrefix), false);
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
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content.minified
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");
		
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
		// [i] field:0:optional $content.encoding
		// [i] record:0:optional $content.namespace
		// [i] - field:0:optional default
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    NamespaceContext namespace = IDataHelper.first(cursor, IDataNamespaceContext.class, "$content.namespace", "$namespace");
		
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

	// --- <<IS-START-SHARED>> ---
	public static class Namespace {
	    public static final void normalize(IData pipeline) throws ServiceException {
	        IDataCursor cursor = pipeline.getCursor();
	        try {
	            IData document = IDataHelper.get(cursor, "$document.operands", IData.class);
	            IData namespace = IDataHelper.get(cursor, "$document.namespace", IData.class);
	            boolean recurse = IDataHelper.getOrDefault(cursor, "$document.recurse?", Boolean.class, false);
	            TransformerMode mode = IDataHelper.get(cursor, "$document.mode", TransformerMode.class);
	
	            IDataNamespaceContext context = new IDataNamespaceContext(namespace);
	            IData normalizedDocument = Transformer.transform(document, new Normalizer(context, mode, recurse));
	
	            IDataHelper.put(cursor, "$document.normalized", normalizedDocument, false);
	        } finally {
	            cursor.destroy();
	        }
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

