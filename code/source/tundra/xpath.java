package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-11-29T10:29:28.882
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.xml.dom.DocumentHelper;
import permafrost.tundra.xml.dom.Nodes;
import permafrost.tundra.xml.namespace.IDataNamespaceContext;
import permafrost.tundra.xml.xpath.XPathHelper;
// --- <<IS-END-IMPORTS>> ---

public final class xpath

{
	// ---( internal utility methods )---

	final static xpath _instance = new xpath();

	static xpath _newInstance() { return new xpath(); }

	static xpath _cast(Object o) { return (xpath)o; }

	// ---( server methods )---




	public static final void exists (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(exists)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $content.encoding
		// [i] record:0:optional $content.namespace
		// [i] - field:0:optional default
		// [i] field:0:required $expression
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    NamespaceContext namespace =  IDataHelper.first(cursor, IDataNamespaceContext.class, "$content.namespace", "$namespace");
		    String expression = IDataHelper.get(cursor, "$expression", String.class);

		    XPathExpression compiledExpression = XPathHelper.compile(expression, namespace);
		    Node node = null;

		    if (content instanceof Node) {
		        node = (Node)content;
		    } else if (content instanceof InputSource) {
		        node = DocumentHelper.parse((InputSource)content, namespace);
		    } else if (content != null) {
		        node = DocumentHelper.parse(InputStreamHelper.normalize(content, charset), charset, true, namespace);
		    }

		    IDataHelper.put(cursor, "$exists?", XPathHelper.exists(node, compiledExpression), String.class);
		} catch(XPathExpressionException ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] object:0:optional $content
		// [i] field:0:optional $content.encoding
		// [i] record:0:optional $content.namespace
		// [i] - field:0:optional default
		// [i] field:0:required $expression
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:1:optional $nodes
		// [o] - object:0:required node
		// [o] - field:0:required name.qualified
		// [o] - field:0:optional name.local
		// [o] - field:0:optional name.prefix
		// [o] - field:0:optional name.uri
		// [o] - field:0:required type
		// [o] - field:0:optional value
		// [o] - record:1:optional attributes
		// [o] -- object:0:required node
		// [o] -- field:0:required name.qualified
		// [o] -- field:0:optional name.local
		// [o] -- field:0:optional name.prefix
		// [o] -- field:0:optional name.uri
		// [o] -- field:0:required type
		// [o] -- field:0:optional value
		// [o] - record:1:optional elements
		// [o] -- object:0:required node
		// [o] -- field:0:required name.qualified
		// [o] -- field:0:optional name.local
		// [o] -- field:0:optional name.prefix
		// [o] -- field:0:optional name.uri
		// [o] -- field:0:required type
		// [o] -- field:0:optional value
		// [o] field:0:required $nodes.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    NamespaceContext namespace = IDataHelper.first(cursor, IDataNamespaceContext.class, "$content.namespace", "$namespace");
		    String expression = IDataHelper.get(cursor, "$expression", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    XPathExpression compiledExpression = XPathHelper.compile(expression, namespace);

		    Node node = null;
		    if (content instanceof Node) {
		        node = (Node)content;
		    } else if (content instanceof InputSource) {
		        node = DocumentHelper.parse((InputSource)content, namespace);
		    } else if (content != null) {
		        node = DocumentHelper.parse(InputStreamHelper.normalize(content, charset), charset, true, namespace);
		    }

		    Nodes nodes = XPathHelper.get(node, compiledExpression);

		    if (nodes != null) {
		        IDataHelper.put(cursor, "$nodes", nodes.reflect(namespace, recurse));
		        IDataHelper.put(cursor, "$nodes.length", nodes.size(), String.class);
		    } else {
		        IDataHelper.put(cursor, "$nodes.length", "0");
		    }
		} catch(XPathExpressionException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

