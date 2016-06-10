package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-06-10 09:29:59.620
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
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
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
		// [i] field:0:optional $encoding
		// [i] field:0:required $expression
		// [i] record:0:optional $namespace
		// [i] - field:0:optional default
		// [o] field:0:required $exists?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		    String expression = IDataUtil.getString(cursor, "$expression");
		    NamespaceContext namespace =  IDataNamespaceContext.of(IDataUtil.getIData(cursor, "$namespace"));

		    XPathExpression compiledExpression = XPathHelper.compile(expression, namespace);
		    Node node = null;

		    if (content instanceof Node) {
		        node = (Node)content;
		    } else if (content instanceof InputSource) {
		        node = DocumentHelper.parse((InputSource)content, namespace);
		    } else if (content != null) {
		        node = DocumentHelper.parse(InputStreamHelper.normalize(content, charset), charset, true, namespace);
		    }

		    IDataUtil.put(cursor, "$exists?", BooleanHelper.emit(XPathHelper.exists(node, compiledExpression)));
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
		// [i] field:0:optional $encoding
		// [i] field:0:required $expression
		// [i] record:0:optional $namespace
		// [i] - field:0:optional default
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $nodes
		// [o] - object:0:required node
		// [o] - field:0:required name.qualified
		// [o] - field:0:optional name.local
		// [o] - field:0:optional name.prefix
		// [o] - field:0:optional name.uri
		// [o] - field:0:required type
		// [o] - field:0:optional value
		// [o] field:0:required $nodes.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataUtil.get(cursor, "$content");
		    Charset charset = CharsetHelper.normalize(IDataUtil.getString(cursor, "$encoding"));
		    String expression = IDataUtil.getString(cursor, "$expression");
		    NamespaceContext namespace = IDataNamespaceContext.of(IDataUtil.getIData(cursor, "$namespace"));
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));

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
		        IDataUtil.put(cursor, "$nodes", nodes.reflect(recurse));
		        IDataUtil.put(cursor, "$nodes.length", IntegerHelper.emit(nodes.size()));
		    } else {
		        IDataUtil.put(cursor, "$nodes.length", "0");
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

