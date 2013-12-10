package tundra.mime;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-12-10 12:43:51.419
// -----( ON-HOST: EBZDEVWAP37.ebiztest.qr.com.au

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class type

{
	// ---( internal utility methods )---

	final static type _instance = new type();

	static type _newInstance() { return new type(); }

	static type _cast(Object o) { return (type)o; }

	// ---( server methods )---




	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $type
		// [i] - field:0:required type
		// [i] - field:0:required subtype
		// [i] - record:0:optional parameters
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData type = IDataUtil.getIData(cursor, "$type");
		  if (type != null) IDataUtil.put(cursor, "$string", emit(type, true));
		} catch(javax.activation.MimeTypeParseException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void equal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(equal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string.x
		// [i] field:0:optional $string.y
		// [o] field:0:required $equal?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string1 = IDataUtil.getString(cursor, "$string.x");
		  String string2 = IDataUtil.getString(cursor, "$string.y");
		  IDataUtil.put(cursor, "$equal?", "" + equal(string1, string2));
		} catch(javax.activation.MimeTypeParseException ex) {
		  tundra.exception.raise(ex);
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
		  String string = IDataUtil.getString(cursor, "$string");
		  if (string != null) IDataUtil.put(cursor, "$string", normalize(string));
		} catch(javax.activation.MimeTypeParseException ex) {
		  tundra.exception.raise(ex);
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
		// [o] record:0:optional $type
		// [o] - field:0:required type
		// [o] - field:0:required subtype
		// [o] - record:0:optional parameters
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  if (string != null) IDataUtil.put(cursor, "$type", parse(string));
		} catch(javax.activation.MimeTypeParseException ex) {
		  tundra.exception.raise(ex);
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
		// [i] field:0:optional $string
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $valid?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  boolean raise = tundra.bool.parse(IDataUtil.getString(cursor, "$raise?"));
		
		  IDataUtil.put(cursor, "$valid?", "" + validate(string, raise));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns an IData representation of the given mime type string
	public static IData parse(String string) throws javax.activation.MimeTypeParseException {
	  if (string == null) return null;
	
	  javax.activation.MimeType type = new javax.activation.MimeType(string);
	
	  IData output = IDataFactory.create();
	  IDataCursor cursor = output.getCursor();
	  IDataUtil.put(cursor, "type", type.getPrimaryType());
	  IDataUtil.put(cursor, "subtype", type.getSubType());
	
	  javax.activation.MimeTypeParameterList list = type.getParameters();
	  if (list.size() > 0) {
	    IData parameters = IDataFactory.create();
	    IDataCursor pc = parameters.getCursor();
	    java.util.Enumeration names = list.getNames();
	    while(names.hasMoreElements()) {
	      String name = (String)names.nextElement();
	      String value = list.get(name);
	      IDataUtil.put(pc, name, value);
	    }
	    pc.destroy();
	    IDataUtil.put(cursor, "parameters", parameters);
	  }
	  cursor.destroy();
	
	  return output;
	}
	
	// returns a mime type string comprised of the components specified in the
	// IData document
	public static String emit(IData idata, boolean ignoreThisParameter) throws javax.activation.MimeTypeParseException {
	  if (idata == null) return null;
	
	  javax.activation.MimeType type = new javax.activation.MimeType();
	  
	  IDataCursor cursor = idata.getCursor();
	  type.setPrimaryType(IDataUtil.getString(cursor, "type"));
	  type.setSubType(IDataUtil.getString(cursor, "subtype"));
	
	  IData parameters = IDataUtil.getIData(cursor, "parameters");
	
	  if (parameters != null) {
	    IDataCursor pc = parameters.getCursor();
	    while (pc.next()) {
	      String key = pc.getKey();
	      String value = (String)pc.getValue();
	      type.setParameter(key, value);
	    }
	    pc.destroy();
	  }
	
	  return type.toString();
	}
	
	
	// normalizes a mime type by removing extraneous whitespace characters, and listing 
	// parameters in alphabetical order.
	public static String normalize(String string) throws javax.activation.MimeTypeParseException {
	  return emit(parse(string), true);
	}
	
	// returns true if the given mime type strings are considered equivalent
	// because their types and subtypes match (parameters are not considered
	// in the comparison)
	public static boolean equal(String s1, String s2) throws javax.activation.MimeTypeParseException {
	  if (s1 == null || s2 == null) return false;
	
	  javax.activation.MimeType t1 = new javax.activation.MimeType(s1);
	  javax.activation.MimeType t2 = new javax.activation.MimeType(s2);
	
	  return t1.match(t2);
	}
	
	// returns true if the given string is a valid mime type
	public static boolean validate(String string, boolean raise) throws ServiceException {
	  boolean valid = false;
	  if (string != null) {
	    try {
	      javax.activation.MimeType type = new javax.activation.MimeType(string);
	      valid = true;
	    } catch (javax.activation.MimeTypeParseException ex) {
	      if (raise) tundra.exception.raise(ex);
	    }
	  }
	  return valid;
	}
	
	// returns true if the given string is a valid mime type
	public static boolean validate(String string) throws ServiceException {
	  return validate(string, false);
	}
	// --- <<IS-END-SHARED>> ---
}

