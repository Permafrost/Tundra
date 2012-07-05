package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-05 15:46:39.973
// -----( ON-HOST: 172.16.70.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class string

{
	// ---( internal utility methods )---

	final static string _instance = new string();

	static string _newInstance() { return new string(); }

	static string _cast(Object o) { return (string)o; }

	// ---( server methods )---




	public static final void lowercase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(lowercase)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  IData document = IDataUtil.getIData(cursor, "$locale");
		  if (string != null) {
		    IDataUtil.put(cursor, "$string", string.toLowerCase(locale(document)));
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
		// [i] object:0:optional $object
		// [i] field:0:optional $encoding
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object object = IDataUtil.get(cursor, "$object");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		
		  IDataUtil.put(cursor, "$string", normalize(object, encoding));
		} catch(java.io.IOException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void trim (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(trim)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IDataUtil.put(cursor, "$string", trim(IDataUtil.getString(cursor, "$string")));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void uppercase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(uppercase)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  IData document = IDataUtil.getIData(cursor, "$locale");
		  if (string != null) {
		    IDataUtil.put(cursor, "$string", string.toUpperCase(locale(document)));
		  }
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	public static String normalize(Object object, String encoding) throws java.io.IOException {
	  if (encoding == null) encoding = tundra.support.constant.DEFAULT_CHARACTER_ENCODING;
	  
	  String string = null;
	  
	  if (object != null) {
	    if (object instanceof String) {
	      string = (String)object;
	    } else if (object instanceof byte[]) {
	      string = new String((byte[])object, encoding);
	    } else if (object instanceof java.io.InputStream) {      
	      java.io.Writer writer = new java.io.StringWriter();
	      tundra.stream.copy(new java.io.InputStreamReader((java.io.InputStream)object, encoding), writer);
	      string = writer.toString();
	    } else {
	      throw new IllegalArgumentException("object must be a string, byte[] or java.io.InputStream: " + object.getClass().getName());
	    }
	  }
	
	  return string;
	}
	
	public static String normalize(Object object) throws java.io.IOException {
	  return normalize(object, tundra.support.constant.DEFAULT_CHARACTER_ENCODING);
	}
	
	public static java.util.Locale locale(String language, String country, String variant) {
	  java.util.Locale locale = java.util.Locale.getDefault();
	  
	  if (language != null) { 
	    if (country == null) {
	      locale = new java.util.Locale(language);
	    } else if (variant == null) {
	      locale = new java.util.Locale(language, country);
	    } else {
	      locale = new java.util.Locale(language, country, variant);
	    }
	  }
	  
	  return locale;
	}
	
	public static java.util.Locale locale(IData document) {
	  String language = null, country = null, variant = null;
	  
	  if (document != null) {
	    IDataCursor cursor = document.getCursor();    
	    language = IDataUtil.getString(cursor, "language");
	    country = IDataUtil.getString(cursor, "country");
	    variant = IDataUtil.getString(cursor, "variant");
	    cursor.destroy();
	  }
	  
	  return locale(language, country, variant);
	}
	
	public static String trim(String input) {
	  String output = null;
	  if (input != null) output = input.trim();
	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

