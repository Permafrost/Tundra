package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-11-22 15:27:50.091
// -----( ON-HOST: -

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




	public static final void validate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] object:0:optional $schema
		// [i] field:0:optional $raise? {&quot;true&quot;,&quot;false&quot;}
		// [o] field:0:required $valid?
		// [o] field:1:optional $errors
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object content = IDataUtil.get(cursor, "$content");
		  Object schema = IDataUtil.get(cursor, "$schema");
		  boolean raise = Boolean.parseBoolean(IDataUtil.getString(cursor, "$raise?"));
		
		  String[] errors = validate(tundra.stream.normalize(content), tundra.stream.normalize(schema), raise);
		  boolean valid = errors == null || errors.length == 0;
		
		  IDataUtil.put(cursor, "$valid?", "" + valid);
		  if (!valid) IDataUtil.put(cursor, "$errors", errors);
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
	// --- <<IS-END-SHARED>> ---
}

