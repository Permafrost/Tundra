package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2013-07-25 20:34:40 EST
// -----( ON-HOST: 172.16.189.223

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




	public static final void amend (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(amend)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] record:1:optional $amendments
		// [i] - field:0:required key
		// [i] - field:0:optional value
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  IData[] amendments = IDataUtil.getIDataArray(cursor, "$amendments");
		
		  if (document != null) IDataUtil.put(cursor, "$document", amend(document, amendments, pipeline));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void clear (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(clear)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:1:optional $preserve
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String[] keys = IDataUtil.getStringArray(cursor, "$preserve");
		
		  clear(document, keys);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



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
		// [i] field:0:required $key.source
		// [i] field:0:required $key.target
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String source = IDataUtil.getString(cursor, "$key.source");
		  String target = IDataUtil.getString(cursor, "$key.target");
		  IDataUtil.put(cursor, "$document", copy(document, source, target));
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



	public static final void duplicate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(duplicate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $duplicate
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		  IDataUtil.put(cursor, "$duplicate", duplicate(document, recurse));
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



	public static final void first (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(first)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  if (document != null) {
		    IDataCursor dc = document.getCursor();
		    if (dc.first()) {
		      String key = dc.getKey();
		      Object value = dc.getValue();
		      IDataUtil.put(cursor, "$key", key);
		      IDataUtil.put(cursor, "$value", value);
		    }
		    dc.destroy();
		  }
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
		  IDataUtil.put(cursor, "$value", tundra.support.document.get(document, key));
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



	public static final void last (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(last)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  if (document != null) {
		    IDataCursor dc = document.getCursor();
		    if (dc.last()) {
		      String key = dc.getKey();
		      Object value = dc.getValue();
		      IDataUtil.put(cursor, "$key", key);
		      IDataUtil.put(cursor, "$value", value);
		    }
		    dc.destroy();
		  }
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



	public static final void listify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(listify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:required $key
		// [i] record:0:optional $scope
		// [o] record:0:optional $scope
		tundra.object.listify(pipeline, IData.class);
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



	public static final void normalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(normalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  IDataUtil.put(cursor, "$document", normalize(document, true));
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
		
		  IDataUtil.put(cursor, "$document", tundra.support.document.put(document, key, value));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void rename (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(rename)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:required $key.source
		// [i] field:0:required $key.target
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  String source = IDataUtil.getString(cursor, "$key.source");
		  String target = IDataUtil.getString(cursor, "$key.target");
		  IDataUtil.put(cursor, "$document", rename(document, source, target));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void substitute (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(substitute)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] record:0:optional $pipeline
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData document = IDataUtil.getIData(cursor, "$document");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		
		  IDataUtil.put(cursor, "$document", substitute(document, scope == null ? pipeline : scope, true));
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
	// amends the given IData document with the {key,value} pairs specified in the
	// amendments IData
	public static IData amend(IData document, IData[] amendments, IData scope) {
	  if (amendments == null) return document;
	
	  for (int i = 0; i < amendments.length; i++) {
	    if (amendments[i] != null) {
	      IData amendment = substitute(amendments[i], scope, true);
	      IDataCursor cursor = amendment.getCursor();
	      String key = IDataUtil.getString(cursor, "key");
	      Object value = IDataUtil.get(cursor, "value");
	      cursor.destroy();
	      document = tundra.support.document.put(document, key, value);
	    }
	  }
	
	  return document;
	}
	
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
	public static IData duplicate(IData input, boolean recurse) throws ServiceException {
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
	
	    if (tundra.support.document.Key.isFullyQualified(key)) drop(input, tundra.support.document.Key.parse(key));
	  }
	  return input;
	}
	
	// removes the value with the given key from the given IData document
	protected static IData drop(IData input, java.util.Queue<tundra.support.document.Key> keys) {
	  if (input != null && keys != null && keys.size() > 0) {
	    IDataCursor cursor = input.getCursor();
	    tundra.support.document.Key key = keys.remove();
	
	    if (keys.size() > 0) {
	      if (key.hasIndex()) {
	        IData[] array = IDataUtil.getIDataArray(cursor, key.toString());
	        drop(tundra.list.object.get(array, key.getIndex()), keys);
	      } else {
	        drop(IDataUtil.getIData(cursor, key.toString()), keys);
	      }
	    } else {
	      if (key.hasIndex()) {
	        Object[] array = IDataUtil.getObjectArray(cursor, key.toString());
	        IDataUtil.put(cursor, key.toString(), tundra.list.object.drop(array, key.getIndex()));
	      } else {
	        IDataUtil.remove(cursor, key.toString());
	      }
	    }
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
	    output = duplicate(input, recurse);
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
	
	// renames a key from source to target within the given IData document
	public static IData rename(IData input, String source, String target) {
	  return drop(copy(input, source, target), source);
	}
	
	// copies a value from source key to target key within the given IData document
	public static IData copy(IData input, String source, String target) {
	  if (source.equals(target)) return input;
	  return tundra.support.document.put(input, target, tundra.support.document.get(input, source));
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
	
	// returns a new IData document, where all IDatas are implemented with the same class, and all
	// fully qualified keys are replaced with a nested structure
	public static IData normalize(IData input, boolean recurse) {
	  if (input == null) return null;
	
	  IData output = IDataFactory.create();
	  IDataCursor ic = input.getCursor();
	
	  try {
	    while(ic.next()) {
	      String key = ic.getKey();
	      Object value = ic.getValue();
	
	      if (value != null) {
	        if (value instanceof com.wm.util.coder.IDataCodable) {
	          value = ((com.wm.util.coder.IDataCodable)value).getIData();
	        } else if (value instanceof com.wm.util.coder.ValuesCodable) {
	          value = ((com.wm.util.coder.ValuesCodable)value).getValues();
	        } else if (value instanceof com.wm.util.coder.IDataCodable[]) {
	          value = normalize((com.wm.util.coder.IDataCodable[])value, recurse);
	        } else if (value instanceof com.wm.util.coder.ValuesCodable[]) {
	          value = normalize((com.wm.util.coder.ValuesCodable[])value, recurse);
	        } else if (value instanceof IData[]) {
	          value = normalize((IData[])value, recurse);
	        } else if (value instanceof com.wm.util.Table) {
	          value = normalize(((com.wm.util.Table)value).getValues(), recurse);
	        } else if (recurse && value instanceof IData) {
	          value = normalize((IData)value, recurse);
	        }
	      }
	      tundra.support.document.put(output, key, value);
	    }
	  } finally {
	    ic.destroy();
	  }
	
	  return output;
	}
	
	// normalizes an IData[], where all IDatas are implemented with the same class, and all
	// fully qualified keys are replaced with a nested structure
	protected static IData[] normalize(IData[] input, boolean recurse) {
	  if (input == null) return null;
	
	  IData[] output = new IData[input.length];
	  for (int i = 0; i < input.length; i++) {
	    output[i] = normalize(input[i], recurse);
	  }
	
	  return output;
	}
	
	// converts an IDataCodable[] to an IData[] and normalizes, where all IDatas are implemented 
	// with the same class, and all fully qualified keys are replaced with a nested structure
	protected static IData[] normalize(com.wm.util.coder.IDataCodable[] input, boolean recurse) {
	  if (input == null) return null;
	
	  IData[] output = new IData[input.length];
	  for (int i = 0; i < input.length; i++) {
	    output[i] = normalize(input[i].getIData(), recurse);
	  }
	  
	  return output;
	}
	
	// converts a ValuesCodable[] to an IData[] and normalizes, where all IDatas are implemented 
	// with the same class, and all fully qualified keys are replaced with a nested structure
	protected static IData[] normalize(com.wm.util.coder.ValuesCodable[] input, boolean recurse) {
	  if (input == null) return null;
	
	  IData[] output = new IData[input.length];
	  for (int i = 0; i < input.length; i++) {
	    output[i] = normalize(input[i].getValues(), recurse);
	  }
	  
	  return output;
	}
	
	// removes all keys except those specified from the given IData document
	public static void clear(IData document, String[] keys) {
	  if (document == null) return;
	
	  IData saved = null;
	  if (keys != null) {
	    saved = IDataFactory.create();
	    for (int i = 0; i < keys.length; i++) {
	      Object value = tundra.support.document.get(document, keys[i]);
	      if (value != null) tundra.support.document.put(saved, keys[i], value);
	    }
	  }
	
	  IDataCursor cursor = document.getCursor();
	  cursor.first();
	  while(cursor.delete());
	  cursor.destroy();
	
	  if (keys != null) IDataUtil.merge(saved, document);
	}
	
	// performs variable substitution on all elements of the given IData input document
	public static IData substitute(IData input, IData scope, boolean recurse) {
	  if (input == null) return null;
	  if (scope == null) scope = input;
	
	  IData output = IDataFactory.create();
	
	  IDataCursor ic = input.getCursor();
	  IDataCursor oc = output.getCursor();
	
	  try {
	    while(ic.next()) {
	      String key = ic.getKey();
	      Object value = ic.getValue();
	      if (value != null) {
	        if (recurse && value instanceof IData) {
	          value = substitute((IData)value, scope, recurse);
	        } else if (recurse && (value instanceof IData[] || value instanceof com.wm.util.Table)) {
	          IData[] iary = value instanceof IData[] ? (IData[])value : ((com.wm.util.Table)value).getValues();
	          IData[] oary = new IData[iary.length];
	          for (int i = 0; i < iary.length; i++) oary[i] = substitute(iary[i], scope, recurse);
	          value = oary;
	        } else if (value instanceof String) {
	          value = tundra.string.substitute((String)value, scope);
	        } else if (value instanceof String[]) {
	          value = tundra.list.string.substitute((String[])value, scope);
	        } else if (value instanceof String[][]) {
	          value = tundra.list.string.substitute((String[][])value, scope);
	        }
	      }
	      IDataUtil.put(oc, key, value);
	    }
	  } finally {
	    ic.destroy();
	    oc.destroy();
	  }
	
	  return output;
	}
	// --- <<IS-END-SHARED>> ---
}

