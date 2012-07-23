package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2012-07-23 15:03:32.028
// -----( ON-HOST: 172.16.70.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class document

{
	// ---( internal utility methods )---

	final static document _instance = new document();

	static document _newInstance() { return new document(); }

	static document _cast(Object o) { return (document)o; }

	// ---( server methods )---




	public static final void compact (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compact)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  IDataUtil.put(cursor, "$document", compact(document, true));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void copy (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(copy)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $copy
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		  IDataUtil.put(cursor, "$copy", copy(document, recurse));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void drop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(drop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $key
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String key = IDataUtil.getString(cursor, "$key");
		  IDataUtil.put(cursor, "$document", drop(document, key));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void each (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(each)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $key.input
		// [i] field:0:optional $value.input
		// [i] field:0:optional $value.class
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String service = IDataUtil.getString(cursor, "$service");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		  String keyInput = IDataUtil.getString(cursor, "$key.input");
		  String valueInput = IDataUtil.getString(cursor, "$value.input");
		  String valueClass = IDataUtil.getString(cursor, "$value.class");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		  boolean scoped = scope != null;
		
		  each(document, service, scoped ? scope: pipeline, keyInput, valueInput, valueClass == null? null : Class.forName(valueClass), recurse);
		} catch (ClassNotFoundException ex) {
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
		// [i] record:0:optional $document.x
		// [i] record:0:optional $document.y
		// [o] field:0:required $equal?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData x = IDataUtil.getIData(cursor, "$document.x");
		  IData y = IDataUtil.getIData(cursor, "$document.y");
		
		  IDataUtil.put(cursor, "$equal?", "" + equal(x, y));
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
		// [i] record:0:optional $document
		// [i] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String key = IDataUtil.getString(cursor, "$key");
		  IDataUtil.put(cursor, "$value", get(document, key));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void keys (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(keys)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] field:1:required $keys
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  IDataUtil.put(cursor, "$keys", keyset(document));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void length (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(length)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  IDataUtil.put(cursor, "$length", "" + size(document));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void map (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(map)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $key.input
		// [i] field:0:optional $key.output
		// [i] field:0:optional $value.input
		// [i] field:0:optional $value.output
		// [i] field:0:optional $value.class
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String service = IDataUtil.getString(cursor, "$service");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		  String keyInput = IDataUtil.getString(cursor, "$key.input");
		  String keyOutput = IDataUtil.getString(cursor, "$key.output");
		  String valueInput = IDataUtil.getString(cursor, "$value.input");
		  String valueOutput = IDataUtil.getString(cursor, "$value.output");
		  String valueClass = IDataUtil.getString(cursor, "$value.class");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		  boolean scoped = scope != null;
		
		  IDataUtil.put(cursor, "$document", map(document, service, scoped ? scope: pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass == null? null : Class.forName(valueClass), recurse));
		} catch (ClassNotFoundException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void merge (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(merge)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $documents
		// [o] record:0:required $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] documents = IDataUtil.getIDataArray(cursor, "$documents");
		  IDataUtil.put(cursor, "$document", merge(documents));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void put (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(put)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $key
		// [i] object:0:optional $value
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String key = IDataUtil.getString(cursor, "$key");
		  Object value = IDataUtil.get(cursor, "$value");
		
		  IDataUtil.put(cursor, "$document", put(document, key, value));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void values (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(values)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] object:1:required $values
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  IDataUtil.put(cursor, "$values", valueset(document));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns whether two documents are equal
	public static boolean equal(IData x, IData y) {
	  return tundra.object.equal(x, y);
	}
	
	// returns all the keys in the given document as a string list
	public static String[] keyset(IData input) {
	  java.util.List<String> keys = new java.util.ArrayList<String>();
	  if (input != null) {
	    IDataCursor cursor = input.getCursor();
	    while(cursor.next()) {
	      keys.add(cursor.getKey());
	    }
	    cursor.destroy();
	  }
	  return (String[])keys.toArray(new String[0]);
	}
	
	// returns all the values in the given document as a string list
	public static Object[] valueset(IData input) {
	  java.util.List<Object> values = new java.util.ArrayList<Object>();
	  if (input != null) {
	    IDataCursor cursor = input.getCursor();
	    while(cursor.next()) {
	      values.add(cursor.getValue());
	    }
	    cursor.destroy();
	  }
	  return (Object[])values.toArray(new Object[0]);
	}
	
	// merges multiple IData documents into a single document
	public static IData merge(IData[] input) {
	  IData output = IDataFactory.create();
	  if (input != null) {
	    for (int i = 0; i < input.length; i++) {
	      IDataUtil.merge(input[i], output);
	    }
	  }
	  return output;
	}
	
	// returns the number of top-level {key, value} pairs in the given IData document
	public static int size(IData input) {
	  int size = 0;
	  if (input != null) {
	    IDataCursor cursor = input.getCursor();
	    size = IDataUtil.size(cursor);
	    cursor.destroy();
	  }
	  return size;
	}
	
	// returns a clone of the given IData document
	public static IData copy(IData input, boolean recurse) throws ServiceException {
	  IData output = null;
	  if (input != null) {
	    IDataCursor cursor = input.getCursor();
	    try {
	      if (recurse) {
	        output = IDataUtil.deepClone(input);
	      } else {
	        output = IDataUtil.clone(input);
	      }
	    } catch (java.io.IOException ex) {
	      tundra.exception.raise(ex);
	    } finally {
	      cursor.destroy();
	    }
	  }
	  return output;
	}
	
	// removes the value with the given key from the given IData document
	public static IData drop(IData input, String key) {
	  if (input != null && key != null) {
	    IDataCursor cursor = input.getCursor();
	    IDataUtil.remove(cursor, key);
	    cursor.destroy();
	  }
	  return input;
	}
	
	// visits each element in the given IData document, calls the given service to convert the element, and creates a new IData document with the resulting elements
	public static IData map(IData input, String service, IData pipeline, String keyInput, String keyOutput, String valueInput, String valueOutput, Class valueClass, boolean recurse) throws ServiceException {
	  IData output = null;
	
	  if (input != null && service != null) {
	    IDataCursor ic = input.getCursor();
	    output = IDataFactory.create();
	    IDataCursor oc = output.getCursor();
	
	    if (keyInput == null) keyInput = "$key";
	    if (keyOutput == null) keyOutput = keyInput;
	    if (valueInput == null) valueInput = "$value";
	    if (valueOutput == null) valueOutput = valueInput;
	    if (pipeline == null) pipeline = IDataFactory.create();
	
	    try {
	      while(ic.next()) {
	        Tuple<Object> tuple = new Tuple<Object>(ic.getKey(), ic.getValue());
	
	        if (recurse && tuple.value != null) {
	          if (tuple.value instanceof IData) {
	            tuple.value = map((IData)tuple.value, service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass, recurse);
	          } else if (tuple.value instanceof IData[] || tuple.value instanceof com.wm.util.Table) {
	            IData[] iary = tuple.value instanceof IData[] ? (IData[])tuple.value : ((com.wm.util.Table)tuple.value).getValues();
	            IData[] oary = new IData[iary.length];
	            for (int i = 0; i < iary.length; i++) {
	              oary[i] = map(iary[i], service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass, recurse);
	            }
	            tuple.value = oary;
	          } else if (tuple.value instanceof String[][]) {
	            String[][] iary = (String[][])tuple.value;
	            String[][] oary = new String[iary.length][];
	            for (int i = 0; i < iary.length; i++) {
	              if (iary[i] != null) {
	                oary[i] = new String[iary[i].length];
	                for (int j = 0; j < iary[i].length; j++) {
	                  Tuple<String> t = map(new Tuple<String>(tuple.key, iary[i][j]), service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass);
	                  oary[i][j] = t.value;
	                }
	              }
	            }
	            tuple.value = oary;
	          } else if (tuple.value instanceof String[]) {
	            String[] iary = (String[])tuple.value;
	            String[] oary = new String[iary.length];
	            for (int i = 0; i < iary.length; i++) {
	              Tuple<String> t = map(new Tuple<String>(tuple.key, iary[i]), service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass);
	              oary[i] = t.value;
	            }
	            tuple.value = oary;
	          } else if (tuple.value instanceof Object[]) {
	            Object[] iary = (Object[])tuple.value;
	            Object[] oary = new Object[iary.length];
	            for (int i = 0; i < iary.length; i++) {
	              Tuple<Object> t = map(new Tuple<Object>(tuple.key, iary[i]), service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass);
	              oary[i] = t.value;
	            }
	            tuple.value = oary;
	          }
	        }
	        tuple = map(tuple, service, pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass);
	        IDataUtil.put(oc, tuple.key, tuple.value);
	      }
	    } finally {
	      ic.destroy();
	      oc.destroy();
	    }
	  } else {
	    output = copy(input, recurse);
	  }
	
	  return output;
	}
	
	// wrapper class for {key, value} tuples
	protected static class Tuple<T> { 
	  public String key;
	  public T value;
	  public Tuple(String key, T value) { 
	    this.key = key; 
	    this.value = value; 
	  }
	}
	
	// converts the given element by calling the given service, and returns the result
	protected static <T> Tuple<T> map(Tuple<T> tuple, String service, IData pipeline, String keyInput, String keyOutput, String valueInput, String valueOutput, Class valueClass) throws ServiceException {
	  if (tuple.value == null || valueClass == null || valueClass.isInstance(tuple.value)) {
	    IDataCursor cursor = pipeline.getCursor();
	    IDataUtil.put(cursor, keyInput, tuple.key);
	    IDataUtil.put(cursor, valueInput, tuple.value);
	    cursor.destroy();
	
	    pipeline = tundra.service.invoke(service, pipeline);
	
	    // clean up the input pipeline
	    cursor = pipeline.getCursor();
	    tuple.key = IDataUtil.getString(cursor, keyOutput);
	    tuple.value = (T)IDataUtil.get(cursor, valueOutput);
	    IDataUtil.remove(cursor, keyInput);
	    if (!keyInput.equals(keyOutput)) IDataUtil.remove(cursor, keyOutput);
	    IDataUtil.remove(cursor, valueInput);
	    if (!valueInput.equals(valueOutput)) IDataUtil.remove(cursor, valueOutput);
	    cursor.destroy();
	  }  
	  return tuple;
	}
	
	// invokes the given service for each {key, value} pair in the given IData document
	public static void each(IData input, String service, IData pipeline, String keyInput, String valueInput, Class valueClass, boolean recurse) throws ServiceException {
	  map(input, service, pipeline, keyInput, null, valueInput, null, valueClass, recurse);
	}
	
	// returns the value associated with the given key from the given IData document
	public static Object get(IData input, String key) {
	  Object value = null;
	  if (input != null && key != null) {
	    IDataCursor cursor = input.getCursor();
	    try {
	      value = IDataUtil.get(cursor, key);
	    } finally {
	      cursor.destroy();
	    }
	  }
	  return value;
	}
	
	// sets the value associated with the given key in the given IData document
	public static IData put(IData input, String key, Object value) {
	  if (key != null) {
	    if (input == null) input = IDataFactory.create();
	    IDataCursor cursor = input.getCursor();
	    try {
	      IDataUtil.put(cursor, key, value);
	    } finally {
	      cursor.destroy();
	    }
	  }
	  return input;
	}
	
	// removes all null values from the given IData document
	public static IData compact(IData input, boolean recurse) {
	  IData output = null;
	
	  if (input != null) {
	    output = IDataFactory.create();
	    IDataCursor ic = input.getCursor();
	    IDataCursor oc = output.getCursor();
	
	    try {
	      while(ic.next()) {
	        String key = ic.getKey();
	        Object value = ic.getValue();
	
	        if (value != null) {
	          if (recurse) {
	            if (value instanceof IData) {
	              value = compact((IData)value, recurse);
	            } else if (value instanceof IData[]) {
	              IData[] array = tundra.list.object.compact((IData[])value);
	              for (int i = 0; i < array.length; i++) {
	                array[i] = compact(array[i], recurse);
	              }
	              value = array;
	            } else if (value instanceof String[][]) {
	              String[][] array = (String[][])value;
	              for (int i = 0; i < array.length; i++) {
	                array[i] = tundra.list.object.compact(array[i]);
	              }
	              value = array;
	            } else if (value instanceof String[]) {
	              value = tundra.list.object.compact((String[])value);
	            } else if (value instanceof Object[]) {
	              value = tundra.list.object.compact((Object[])value);
	            }
	          }
	        }
	
	        if (value != null) IDataUtil.put(oc, key, value);
	      }
	    } finally {
	      ic.destroy();
	      oc.destroy();
	    }
	  }
	
	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

