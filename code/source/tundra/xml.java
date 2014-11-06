package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-11-06 19:18:00 EST
// -----( ON-HOST: 172.16.189.176

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
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
		  Object content = IDataUtil.get(cursor, "$content");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String algorithm = IDataUtil.getString(cursor, "$algorithm");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  if (content != null) IDataUtil.put(cursor, "$content.canonical", canonicalize(content, encoding, algorithm, mode));
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
		  Object content = IDataUtil.get(cursor, "$content");
		  String contentEncoding = IDataUtil.getString(cursor, "$content.encoding");
		  Object schema = IDataUtil.get(cursor, "$schema");
		  String schemaEncoding = IDataUtil.getString(cursor, "$schema.encoding");
		  boolean raise = Boolean.parseBoolean(IDataUtil.getString(cursor, "$raise?"));
		
		  String[] errors = validate(tundra.stream.normalize(content, contentEncoding), tundra.stream.normalize(schema, schemaEncoding), raise);
		  boolean valid = content != null && (errors == null || errors.length == 0);
		
		  IDataUtil.put(cursor, "$valid?", "" + valid);
		  if (!valid && errors != null) IDataUtil.put(cursor, "$errors", errors);
		} catch (java.io.UnsupportedEncodingException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// validates the given content as XML, optionally against the given XML schema (XSD); throws an exception if
	// the content is malformed and raise is true
	public static String[] validate(java.io.InputStream content, java.io.InputStream schema, boolean raise) throws ServiceException {
	  if (content == null) return null;
	  
	  java.util.List<Throwable> errors = new java.util.ArrayList<Throwable>();
	  try {
	    javax.xml.parsers.SAXParserFactory factory = javax.xml.parsers.SAXParserFactory.newInstance();
	    factory.setNamespaceAware(true);
	    factory.setXIncludeAware(true);
	
	    if (schema != null) {
	      factory.setSchema(javax.xml.validation.SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new javax.xml.transform.stream.StreamSource(schema)));
	    }
	    javax.xml.parsers.SAXParser parser = factory.newSAXParser();
	    ErrorHandler handler = new ErrorHandler();
	    parser.parse(content, handler);
	    errors.addAll(handler.getErrors());
	  } catch (java.io.IOException ex) {
	    errors.add(ex);
	  } catch (javax.xml.parsers.ParserConfigurationException ex) {
	    errors.add(ex);
	  } catch (org.xml.sax.SAXParseException ex) {
	    errors.add(ex);
	  } catch (org.xml.sax.SAXException ex) {
	    errors.add(ex);
	  } finally {
	    tundra.stream.close(content, schema);
	  }
	
	  if (errors.size() > 0 && raise) {
	    tundra.exception.raise(errors);
	  }
	  
	  return format(errors);
	}
	
	public static class ErrorHandler extends org.xml.sax.helpers.DefaultHandler {
	  private java.util.List<Throwable> errors = new java.util.ArrayList<Throwable>();
	
	  public ErrorHandler() {
	    super();
	  }
	
	  // returns the list of formatted error messages encountered while parsing XML
	  public java.util.List<Throwable> getErrors() {
	    return errors;
	  }
	
	  // handles an XML error by appending it to the list of errors encountered while parsing
	  public void error(org.xml.sax.SAXParseException ex) throws org.xml.sax.SAXException {
	    append(ex);
	  }
	
	  // handles a fatal XML error by appending it to the list of errors encountered while parsing
	  public void fatalError(org.xml.sax.SAXParseException ex) throws org.xml.sax.SAXException {
	    append(ex);
	  }
	  
	  // formats the given exception as a string, and appends it to the list of errors encountered
	  // while parsing
	  protected void append(org.xml.sax.SAXParseException ex) {
	    errors.add(ex);
	  }
	}
	
	// formats the given exception as a string
	protected static String format(Throwable error) {
	  if (error == null) return null;
	  
	  StringBuilder builder = new StringBuilder();
	  builder.append(tundra.exception.message(error));
	  
	  if (error instanceof org.xml.sax.SAXParseException) {
	    org.xml.sax.SAXParseException ex = (org.xml.sax.SAXParseException)error;
	    builder.append(" (Line ").append("" + ex.getLineNumber()).append(", Column ").append("" + ex.getColumnNumber()).append(")");
	  }
	
	  return builder.toString();
	}
	
	// formats the given exception as a string
	protected static String[] format(java.util.List<Throwable> errors) {
	  if (errors == null) return null;
	  
	  return format((Throwable[])errors.toArray(new Throwable[0]));
	}
	
	// formats the given exception as a string
	protected static String[] format(Throwable[] errors) {
	  if (errors == null) return null;
	  
	  String[] strings = new String[errors.length];
	  for (int i = 0; i < errors.length; i++) {
	    strings[i] = format(errors[i]);
	  }
	  return strings;
	}
	
	protected static final java.util.Map<String, String> CANONICALIZATION_ALGORITHM_ALIASES = constructCanonicalizationAlgorithmAliases();
	
	protected static final java.util.Map<String, String> constructCanonicalizationAlgorithmAliases() {
	  java.util.Map<String, String> map = new java.util.TreeMap<String, String>();
	
	  map.put("Canonical XML Version 1.0", "http://www.w3.org/TR/2001/REC-xml-c14n-20010315");
	  map.put("Canonical XML Version 1.0 With Comments", "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments");
	  map.put("Inclusive Canonical XML Version 1.0", "http://www.w3.org/TR/2001/REC-xml-c14n-20010315");
	  map.put("Inclusive Canonical XML Version 1.0 With Comments", "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments");
	  map.put("Exclusive Canonical XML Version 1.0", "http://www.w3.org/2001/10/xml-exc-c14n#");
	  map.put("Exclusive Canonical XML Version 1.0 With Comments", "http://www.w3.org/2001/10/xml-exc-c14n#WithComments");
	  map.put("Canonical XML Version 1.1", "http://www.w3.org/2006/12/xml-c14n11");
	  map.put("Canonical XML Version 1.1 With Comments", "http://www.w3.org/2006/12/xml-c14n11#WithComments");
	  map.put("Inclusive Canonical XML Version 1.1", "http://www.w3.org/2006/12/xml-c14n11");
	  map.put("Inclusive Canonical XML Version 1.1 With Comments", "http://www.w3.org/2006/12/xml-c14n11#WithComments");
	
	  return map;
	}
	
	protected static String getAlgorithm(String alias) {
	  String algorithm = CANONICALIZATION_ALGORITHM_ALIASES.get(alias);
	  if (algorithm == null) algorithm = alias;
	  return algorithm;
	}
	
	// canonicalizes the given XML content using the given algorithm
	public static Object canonicalize(Object input, String encoding, String algorithm, String mode) throws ServiceException {
	  Object output = null;
	
	  try {
	    String inputString = tundra.string.normalize(input, encoding);
	    byte[] inputBytes = tundra.bytes.normalize(input, org.apache.xml.security.c14n.Canonicalizer.ENCODING);
	    org.apache.xml.security.Init.init();
	    org.apache.xml.security.c14n.Canonicalizer canonicalizer = org.apache.xml.security.c14n.Canonicalizer.getInstance(getAlgorithm(algorithm));
	    byte[] outputBytes = canonicalizer.canonicalize(inputBytes);
	    String outputString = tundra.string.normalize(outputBytes, org.apache.xml.security.c14n.Canonicalizer.ENCODING);
	    output = tundra.object.convert(outputString, encoding, mode);
	  } catch(org.apache.xml.security.exceptions.XMLSecurityException ex) {
	    tundra.exception.raise(ex);
	  } catch(javax.xml.parsers.ParserConfigurationException ex) {
	    tundra.exception.raise(ex);
	  } catch(org.xml.sax.SAXException ex) {
	    tundra.exception.raise(ex);
	  } catch(java.io.IOException ex) {
	    tundra.exception.raise(ex);
	  }
	
	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

