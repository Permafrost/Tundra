package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-08-03 16:01:30 EST
// -----( ON-HOST: 172.16.189.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class json

{
	// ---( internal utility methods )---

	final static json _instance = new json();

	static json _newInstance() { return new json(); }

	static json _cast(Object o) { return (json)o; }

	// ---( server methods )---




	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] - object:1:optional recordWithNoID
		// [i] field:0:optional $encoding
		// [i] field:0:optional $mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		  String mode = IDataUtil.getString(cursor, "$mode");
		
		  if (document != null) IDataUtil.put(cursor, "$content", emit(document, mode, encoding));
		} catch (java.io.IOException ex) {
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
		// [i] object:0:optional $content
		// [i] field:0:optional $encoding
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object content = IDataUtil.get(cursor, "$content");
		  String encoding = IDataUtil.getString(cursor, "$encoding");
		
		  if (content != null) {
		    Object output = parse(tundra.stream.normalize(content, encoding), encoding);
		    if (output != null) {
		      if (!(output instanceof IData)) {
		        // wrap in an outer IData document containing recordWithNoID item
		        IData document = IDataFactory.create();
		        IDataCursor dc = document.getCursor();
		        IDataUtil.put(dc, "recordWithNoID", output);
		        dc.destroy();
		        output = document;
		      }
		      IDataUtil.put(cursor, "$document", output);
		    }
		  }
		} catch (java.io.UnsupportedEncodingException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// parses JSON content to an appropriate webMethods compatible represention
	public static Object parse(java.io.InputStream in) {
	  return parse(in, null);
	}
	
	// parses JSON content to an appropriate webMethods compatible represention
	public static Object parse(java.io.InputStream in, String encoding) {
	  if (in == null) return null;
	
	  javax.json.JsonReader reader = getReader(in, encoding);
	  javax.json.JsonStructure structure = reader.read();
	  reader.close();
	
	  return fromJsonValue(structure);
	}
	
	// returns a JSON reader that uses the specified encoding
	protected static javax.json.JsonReader getReader(java.io.InputStream in, String encoding) {
	  javax.json.JsonReaderFactory factory = javax.json.Json.createReaderFactory(null);
	  javax.json.JsonReader reader = null;
	
	  if (encoding != null) {
	    reader = factory.createReader(in, java.nio.charset.Charset.forName(encoding));
	  } else {
	    reader = factory.createReader(in);
	  }
	
	  return reader;
	}
	
	// converts an JSON value to an appropriate webMethods compatible represention
	protected static Object fromJsonValue(javax.json.JsonValue input) {
	  Object output = null;
	
	  if (input != null) {
	    javax.json.JsonValue.ValueType type = input.getValueType();
	
	    if (type == javax.json.JsonValue.ValueType.OBJECT) {
	      output = fromJsonObject((javax.json.JsonObject)input);
	    } else if (type == javax.json.JsonValue.ValueType.ARRAY) {
	      output = fromJsonArray((javax.json.JsonArray)input);
	    } else if (type == javax.json.JsonValue.ValueType.NULL) {
	      // do nothing
	    } else if (type == javax.json.JsonValue.ValueType.TRUE) {
	      output = Boolean.TRUE;
	    } else if (type == javax.json.JsonValue.ValueType.FALSE) {
	      output = Boolean.FALSE;
	    } else if (type == javax.json.JsonValue.ValueType.NUMBER) {
	      output = fromJsonNumber((javax.json.JsonNumber)input);
	    } else {
	      output = input.toString();
	    }
	  }
	
	  return output;
	}
	
	protected static final java.math.BigInteger MIN_VALUE_INT = new java.math.BigInteger((new Integer(Integer.MIN_VALUE)).toString());
	protected static final java.math.BigInteger MAX_VALUE_INT = new java.math.BigInteger((new Integer(Integer.MAX_VALUE)).toString());
	protected static final java.math.BigInteger MIN_VALUE_LONG = new java.math.BigInteger((new Long(Long.MIN_VALUE)).toString());
	protected static final java.math.BigInteger MAX_VALUE_LONG = new java.math.BigInteger((new Long(Long.MAX_VALUE)).toString());
	protected static final java.math.BigDecimal MIN_VALUE_FLOAT = new java.math.BigDecimal(-Float.MAX_VALUE);
	protected static final java.math.BigDecimal MAX_VALUE_FLOAT = new java.math.BigDecimal(Float.MAX_VALUE);
	protected static final java.math.BigDecimal MIN_VALUE_DOUBLE = new java.math.BigDecimal(-Double.MAX_VALUE);
	protected static final java.math.BigDecimal MAX_VALUE_DOUBLE = new java.math.BigDecimal(Double.MAX_VALUE);
	
	// converts a JSON number to an appropriate webMethods compatible represention
	protected static Object fromJsonNumber(javax.json.JsonNumber input) {
	  Object output = null;
	  if (input.isIntegral()) {
	    java.math.BigInteger integer = input.bigIntegerValue();
	    if (integer.compareTo(MIN_VALUE_LONG) < 0 || integer.compareTo(MAX_VALUE_LONG) > 0) {
	      output = integer;
	    } else if (integer.compareTo(MIN_VALUE_INT) < 0 || integer.compareTo(MAX_VALUE_INT) > 0) {
	      output = integer.longValue();
	    } else {
	      output = integer.intValue();
	    }
	  } else {
	    java.math.BigDecimal decimal = input.bigDecimalValue();
	    if (decimal.compareTo(MIN_VALUE_DOUBLE) < 0 || decimal.compareTo(MAX_VALUE_DOUBLE) > 0) {
	      output = decimal;
	    } else if (decimal.compareTo(MIN_VALUE_FLOAT) < 0 || decimal.compareTo(MAX_VALUE_FLOAT) > 0) {
	      output = decimal.doubleValue();
	    } else {
	      output = decimal.floatValue();
	    }    
	  }
	  return output;
	}
	
	// converts a JSON object to an IData document
	protected static IData fromJsonObject(javax.json.JsonObject input) {
	  if (input == null) return null;
	
	  java.util.Iterator<String> iterator = input.keySet().iterator();
	
	  IData output = IDataFactory.create();
	  IDataCursor cursor = output.getCursor();
	
	  while(iterator.hasNext()) {
	    String key = iterator.next();
	    javax.json.JsonValue value = input.get(key);
	    IDataUtil.put(cursor, key, fromJsonValue(value));
	  }
	  
	  cursor.destroy();
	
	  return output;
	}
	
	// converts a JSON array to an Object array
	protected static Object[] fromJsonArray(javax.json.JsonArray input) {
	  if (input == null) return null;
	
	  java.util.List output = new java.util.ArrayList(input.size());
	  java.util.Iterator<javax.json.JsonValue> iterator = input.iterator();
	
	  Class arrayClass = null;
	
	  while(iterator.hasNext()) {
	    javax.json.JsonValue item = iterator.next();
	    Object object = fromJsonValue(item);
	    output.add(object);
	
	    if (object != null) {
	      Class itemClass = object.getClass();
	      if (arrayClass == null) {
	        arrayClass = itemClass;
	      } else if (arrayClass != itemClass) {
	        // must be a heterogeneous array, so default to Object[]
	        arrayClass = Object.class;
	      }
	    }
	  }
	
	  if (arrayClass == null) arrayClass = Object.class;
	
	  return output.toArray((Object[])java.lang.reflect.Array.newInstance(arrayClass, 0));
	}
	
	// serializes an IData document to a JSON representation
	public static Object emit(IData input, String mode, String encoding) throws java.io.IOException {
	  java.io.StringWriter stringWriter = new java.io.StringWriter();
	  javax.json.JsonWriter writer = javax.json.Json.createWriter(stringWriter);
	
	  IDataCursor cursor = input.getCursor();
	  Object[] array = IDataUtil.getObjectArray(cursor, "recordWithNoID");
	  cursor.destroy();
	
	  if (array != null) {
	    writer.write(toJsonArray(array));
	  } else {
	    writer.write(toJsonObject(input));
	  }
	
	  writer.close();
	
	  Object content = stringWriter.toString();
	
	  if (mode == null || mode.equals("stream")) {
	    content = tundra.stream.normalize(content, encoding);
	  } else if (mode.equals("bytes")) {
	    content = tundra.bytes.normalize(content, encoding);
	  }
	
	  return content;
	}
	
	// converts an IData document to a JSON object
	protected static javax.json.JsonObject toJsonObject(IData input) {
	  javax.json.JsonObjectBuilder builder = javax.json.Json.createObjectBuilder();
	
	  if (input != null) {
	    IDataCursor cursor = input.getCursor();
	
	    while(cursor.next()) {
	      String key = cursor.getKey();
	      Object value = cursor.getValue();
	
	      if (value == null) {
	        builder.addNull(key);
	      } else if (value instanceof IData) {
	        builder.add(key, toJsonObject((IData)value));   
	      } else if (value instanceof com.wm.util.Table) {
	        value = ((com.wm.util.Table)value).getValues();
	        builder.add(key, toJsonArray((IData[])value));
	      } else if (value instanceof Object[]) {
	        builder.add(key, toJsonArray((Object[])value));
	      } else if (value instanceof Boolean) {
	        builder.add(key, ((Boolean)value).booleanValue());
	      } else if (value instanceof Integer) {
	        builder.add(key, ((Integer)value).intValue());
	      } else if (value instanceof Long) {
	        builder.add(key, ((Long)value).longValue());
	      } else if (value instanceof java.math.BigInteger) {
	        builder.add(key, (java.math.BigInteger)value);
	      } else if (value instanceof Float) {
	        builder.add(key, ((Float)value).floatValue());
	      } else if (value instanceof Double) {
	        builder.add(key, ((Double)value).doubleValue());
	      } else if (value instanceof java.math.BigDecimal) {
	        builder.add(key, (java.math.BigDecimal)value);
	      } else {
	        builder.add(key, value.toString());
	      }
	    }
	  }
	
	  return builder.build();
	}
	
	// converts an Object[] to a JSON array
	protected static javax.json.JsonArray toJsonArray(Object[] input) {
	  javax.json.JsonArrayBuilder builder = javax.json.Json.createArrayBuilder();
	
	  if (input != null) {
	    for (int i = 0; i < input.length; i++) {
	      Object value = input[i];
	      if (value == null) {
	        builder.addNull();
	      } else if (value instanceof IData) {
	        builder.add(toJsonObject((IData)value));   
	      } else if (value instanceof com.wm.util.Table) {
	        value = ((com.wm.util.Table)value).getValues();
	        builder.add(toJsonArray((IData[])value));
	      } else if (value instanceof Object[]) {
	        builder.add(toJsonArray((Object[])value));
	      } else if (value instanceof Boolean) {
	        builder.add(((Boolean)value).booleanValue());
	      } else if (value instanceof Integer) {
	        builder.add(((Integer)value).intValue());
	      } else if (value instanceof Long) {
	        builder.add(((Long)value).longValue());
	      } else if (value instanceof java.math.BigInteger) {
	        builder.add((java.math.BigInteger)value);
	      } else if (value instanceof Float) {
	        builder.add(((Float)value).floatValue());
	      } else if (value instanceof Double) {
	        builder.add(((Double)value).doubleValue());
	      } else if (value instanceof java.math.BigDecimal) {
	        builder.add((java.math.BigDecimal)value);
	      } else {
	        builder.add(value.toString());
	      }
	    }
	  }
	
	  return builder.build();
	}
	// --- <<IS-END-SHARED>> ---
}

