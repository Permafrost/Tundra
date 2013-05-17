package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-05-17 17:14:13.822
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String expression = IDataUtil.getString(cursor, "$expression");
		  IData namespace = IDataUtil.getIData(cursor, "$namespace");
		  boolean result = false;
		
		  if (content != null) result = exists(tundra.stream.normalize(content, encoding), expression, namespace);
		  
		  IDataUtil.put(cursor, "$exists?", "" + result);
		} catch (java.io.IOException ex) {
		  tundra.exception.raise(ex);
		} catch (javax.xml.xpath.XPathExpressionException ex) {
		  tundra.exception.raise(ex);
		} catch (javax.xml.parsers.ParserConfigurationException ex) {
		  tundra.exception.raise(ex);
		} catch (org.xml.sax.SAXException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns true if the given XPath expression can be found in the given XML content
	public static boolean exists(java.io.InputStream content, String expression, IData namespace) throws javax.xml.xpath.XPathExpressionException, javax.xml.parsers.ParserConfigurationException, org.xml.sax.SAXException, java.io.IOException {
	  try {
	    return exists(new org.xml.sax.InputSource(content), expression, namespace);
	  } finally {
	    tundra.stream.close(content);
	  }
	}
	
	// returns true if the given XPath expression can be found in the given XML content
	public static boolean exists(org.xml.sax.InputSource content, String expression, IData namespace) throws javax.xml.xpath.XPathExpressionException, javax.xml.parsers.ParserConfigurationException, org.xml.sax.SAXException, java.io.IOException {
	  javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
	  javax.xml.namespace.NamespaceContext context = null;
	
	  if (namespace != null) {
	    factory.setNamespaceAware(namespace != null);
	    context = new IDataNamespaceContext(namespace);
	  }
	  
	  javax.xml.parsers.DocumentBuilder parser = factory.newDocumentBuilder();
	  return exists(parser.parse(content), expression, context);
	}
	
	// returns true if the given XPath expression can be found in the given XML content
	public static boolean exists(org.w3c.dom.Document content, String expression, javax.xml.namespace.NamespaceContext context) throws javax.xml.xpath.XPathExpressionException {
	  javax.xml.xpath.XPath xpath = javax.xml.xpath.XPathFactory.newInstance().newXPath();
	  if (context != null) xpath.setNamespaceContext(context);
	  return ((Boolean)xpath.evaluate(expression, content, javax.xml.xpath.XPathConstants.BOOLEAN)).booleanValue();
	}
	
	public static class IDataNamespaceContext implements javax.xml.namespace.NamespaceContext {
	  protected java.util.Map<String, String> prefixes = new java.util.TreeMap<String, String>();
	  protected java.util.Map<String, java.util.List<String>> uris = new java.util.TreeMap<String, java.util.List<String>>();
	
	  public IDataNamespaceContext(IData document) {
	    if (document != null) {
	      IDataCursor cursor = document.getCursor();
	      while(cursor.next()) {
	        String key = cursor.getKey();
	        String value = (String)cursor.getValue();
	        prefixes.put(key, value);
	
	        java.util.List<String> list = uris.get(value);
	        if (list == null) list = new java.util.ArrayList<String>();
	        list.add(key);
	        uris.put(value, list);
	      }
	      cursor.destroy();
	    }
	  }
	
	  public String getNamespaceURI(String prefix) {
	    String uri = null;
	    if (prefix == null) {
	      throw new IllegalArgumentException("prefix must not be null");
	    } else if (prefix.equals(javax.xml.XMLConstants.DEFAULT_NS_PREFIX)) {
	      uri = prefixes.get("default");
	    } else {
	      uri = prefixes.get(prefix);
	    }
	
	    if (uri == null) uri = javax.xml.XMLConstants.NULL_NS_URI;
	
	    return uri;
	  }
	
	  public String getPrefix(String uri) {
	    if (uri == null) throw new IllegalArgumentException("uri must not be null");
	
	    java.util.List<String> list = uris.get(uri);
	    if (list == null) {
	      return null;
	    } else {
	      return list.get(0);
	    }
	  }
	
	  public java.util.Iterator getPrefixes(String uri) {
	    if (uri == null) throw new IllegalArgumentException("uri must not be null");
	
	    java.util.List<String> list = uris.get(uri);
	    if (list == null) {
	      return null;
	    } else {
	      return list.iterator();
	    }
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

