package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-12-17 19:38:45 EST
// -----( ON-HOST: 172.16.189.176

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




	public static final void append (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(append)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:0:optional $item
		// [o] record:1:required $list
		tundra.list.object.append(pipeline, IData.class);
		// --- <<IS-END>> ---

                
	}



	public static final void compact (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compact)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		  if (list != null) IDataUtil.put(cursor, "$list", compact(list, recurse));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void concatenate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(concatenate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list.x
		// [i] record:1:optional $list.y
		// [o] record:1:optional $list
		tundra.list.object.concatenate(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void drop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(drop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:required $index
		// [o] record:1:optional $list
		tundra.list.object.drop(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void each (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(each)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $item.input
		tundra.list.object.each(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void equal (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(equal)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list.x
		// [i] record:1:optional $list.y
		// [o] field:0:required $equal?
		tundra.list.object.equal(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void filter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(filter)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $condition
		// [i] record:0:optional $scope
		// [o] record:1:optional $list
		tundra.list.object.filter(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void first (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(first)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [o] record:0:optional $item
		tundra.list.object.first(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $index
		// [i] field:0:optional $iteration
		// [o] record:0:optional $item
		tundra.list.object.get(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void group (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(group)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:1:optional $keys
		// [o] record:1:optional $list.grouped
		// [o] - record:0:required group
		// [o] - record:1:required items
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		  String[] keys = IDataUtil.getStringArray(cursor, "$keys");
		
		  if (list != null && list.length > 0) IDataUtil.put(cursor, "$list.grouped", group(list, keys));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void grow (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(grow)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:0:optional $item
		// [i] field:0:required $count
		// [o] record:1:required $list
		tundra.list.object.grow(pipeline, IData.class);
		// --- <<IS-END>> ---

                
	}



	public static final void include (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(include)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:0:optional $item
		// [o] field:0:required $include?
		tundra.list.object.include(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void insert (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(insert)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:0:optional $item
		// [i] field:0:required $index
		// [o] record:1:required $list
		tundra.list.object.insert(pipeline, IData.class);
		// --- <<IS-END>> ---

                
	}



	public static final void keys (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(keys)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $pattern
		// [o] field:1:required $keys
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		
		  IDataUtil.put(cursor, "$keys", keys(list, pattern));
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
		// [i] record:1:optional $list
		// [o] record:0:optional $item
		tundra.list.object.last(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void length (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(length)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [o] field:0:required $length
		tundra.list.object.length(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void map (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(map)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $item.input
		// [i] field:0:optional $item.output
		// [o] record:1:optional $list
		tundra.list.object.map(pipeline, IData.class);
		// --- <<IS-END>> ---

                
	}



	public static final void pivot (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(pivot)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:1:optional $keys
		// [i] field:0:optional $delimiter
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		  String[] keys = IDataUtil.getStringArray(cursor, "$keys");
		  String key = IDataUtil.getString(cursor, "$key");
		  String delimiter = IDataUtil.getString(cursor, "$delimiter");
		
		  if (list != null) {
		    if (keys != null) {
		      IDataUtil.put(cursor, "$document", pivot(list, delimiter, keys));
		    } else {
		      IDataUtil.put(cursor, "$document", pivot(list, delimiter, key));
		    }
		  }
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void prepend (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(prepend)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:0:optional $item
		// [o] record:1:required $list
		tundra.list.object.prepend(pipeline, IData.class);
		// --- <<IS-END>> ---

                
	}



	public static final void put (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(put)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:0:optional $item
		// [i] field:0:required $index
		// [o] record:1:required $list
		tundra.list.object.put(pipeline, IData.class);
		// --- <<IS-END>> ---

                
	}



	public static final void resize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(resize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:0:optional $item
		// [i] field:0:required $length
		// [o] record:1:required $list
		tundra.list.object.resize(pipeline, IData.class);
		// --- <<IS-END>> ---

                
	}



	public static final void reverse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reverse)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [o] record:1:optional $list
		tundra.list.object.reverse(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void shrink (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(shrink)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:required $count
		// [o] record:1:optional $list
		tundra.list.object.shrink(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void slice (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(slice)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $index
		// [i] field:0:optional $length
		// [o] record:1:optional $list
		tundra.list.object.slice(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void sort (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sort)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:1:optional $criteria
		// [i] - field:0:required key
		// [i] - field:0:optional type {&quot;string&quot;,&quot;integer&quot;,&quot;decimal&quot;,&quot;datetime&quot;,&quot;duration&quot;}
		// [i] - field:0:optional pattern
		// [i] - field:0:optional descending? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		  IData[] criteria = IDataUtil.getIDataArray(cursor, "$criteria");
		
		  // silently support $key, $keys and $ascending? for backwards compatibility
		  String[] keys = IDataUtil.getStringArray(cursor, "$keys");
		  String key = IDataUtil.getString(cursor, "$key");
		  String sAscending = IDataUtil.getString(cursor, "$ascending?");
		  boolean ascending = true;
		  if (sAscending != null) ascending = tundra.bool.parse(sAscending);
		
		  if (list != null) {
		    if (criteria == null) {
		      if (keys == null) {
		        IDataUtil.put(cursor, "$list", sort(list, key, ascending));
		      } else {
		        IDataUtil.put(cursor, "$list", sort(list, keys, ascending));
		      }
		    } else {
		      IDataUtil.put(cursor, "$list", sort(list, criteria));
		    }
		  }
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void squeeze (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(squeeze)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		  boolean recurse = Boolean.parseBoolean(IDataUtil.getString(cursor, "$recurse?"));
		
		  if (list != null) {
		    list = squeeze(list, recurse);
		    if (list == null) list = new IData[0];
		    IDataUtil.put(cursor, "$list", list);
		  }
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
		// [i] record:1:optional $list
		// [i] field:0:optional $key
		// [i] object:0:optional $default.object
		// [i] field:0:optional $default.string
		// [o] object:1:optional $values
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		  String key = IDataUtil.getString(cursor, "$key");
		  Object defaultObject = IDataUtil.get(cursor, "$default.object");
		  if (defaultObject == null) defaultObject = IDataUtil.getString(cursor, "$default.string");
		
		  Object[] values = values(list, key, defaultObject);
		
		  if (values != null) IDataUtil.put(cursor, "$values", values);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns a new IData[] with all null values removed
	public static IData[] compact(IData[] array, boolean recurse) {
	  if (array == null || array.length == 0) return array;
	
	  // take a copy of the array, to make sure it's really an IData[] and not some subclass that won't
	  // be able to store different IData implementations
	  array = (IData[])java.util.Arrays.copyOf(array, array.length, (new IData[0]).getClass());
	
	  for (int i = 0; i < array.length; i++) {
	    if (array[i] != null) array[i] = tundra.document.compact(array[i], recurse);
	  }
	
	  return tundra.list.object.compact(array);
	}
	
	// returns the union set of keys present in every item in the given
	// IData[] document list
	public static String[] keys(IData[] input) {
	  return keys(input, null);
	}
	
	// returns the union set of keys present in every item in the given
	// IData[] document list
	public static String[] keys(IData[] input, String patternString) {
	  java.util.regex.Pattern pattern = null;
	  if (patternString != null) pattern = java.util.regex.Pattern.compile(patternString);
	  
	  java.util.Set<String> keys = new java.util.LinkedHashSet<String>();
	
	  if (input != null) {
	    for (IData document : input) {
	      if (document != null) {
	        IDataCursor cursor = document.getCursor();
	        while(cursor.next()) {
	          String key = cursor.getKey();
	          
	          if (pattern == null) {
	            keys.add(key);
	          } else {
	            java.util.regex.Matcher matcher = pattern.matcher(key);
	            if (matcher.matches()) keys.add(key);
	          }
	        }
	        cursor.destroy();     
	      }
	    }
	  }
	
	  return keys.toArray(new String[0]);
	}
	
	// returns an IData document where the keys are the values associated with
	// given pivot key from the given IData[] document list, and the values are
	// the IData[] document list items associated with each pivot key
	public static IData pivot(IData[] array, String pivotKey) {
	  return pivot(array, null, pivotKey);
	}
	
	// returns an IData document where the keys are the values associated with
	// given pivot key from the given IData[] document list, and the values are
	// the IData[] document list items associated with each pivot key
	public static IData pivot(IData[] array, String delimiter, String ... pivotKeys) {
	  if (array == null || pivotKeys == null || pivotKeys.length == 0) return null;
	  if (delimiter == null) delimiter = ":";
	
	  IData output = IDataFactory.create();
	  IDataCursor oc = output.getCursor();
	
	  outer:
	  for (IData item : array) {
	    if (item != null) {
	      StringBuilder buffer = new StringBuilder();
	      for (int i = 0; i < pivotKeys.length; i++) {
	        Object value = tundra.support.document.get(item, pivotKeys[i]);
	        if (value == null) {
	          continue outer;
	        } else {
	          buffer.append(value.toString());
	        }
	        if (i < (pivotKeys.length - 1)) buffer.append(delimiter);
	      }
	      String key = buffer.toString();
	      if (IDataUtil.get(oc, key) == null) IDataUtil.put(oc, key, item);
	    }
	  }
	
	  oc.destroy();
	
	  return output;
	}
	
	// returns a new array with all elements sorted in ascending order by
	// the values associated with the given key
	public static IData[] sort(IData[] array, String key) {
	  return sort(array, key, true);
	}
	
	// returns a new array with all elements sorted either in ascending or
	// descending order by the values associated with the given key
	public static IData[] sort(IData[] array, String key, boolean ascending) {
	  String[] keys = null;
	  if (key != null) {
	    keys = new String[1];
	    keys[0] = key;
	  }
	
	  return sort(array, keys, ascending);
	}
	
	// returns a new array with all elements sorted in ascending order by the 
	// values associated with the given keys
	public static IData[] sort(IData[] array, String[] keys) {
	  return sort(array, keys, true);
	}
	
	// returns a new array with all elements sorted by the values associated with 
	// the given keys
	public static IData[] sort(IData[] array, String[] keys, boolean ascending) {
	  if (array == null || array.length < 2 || keys == null || keys.length == 0) return array;
	
	  IData[] sortKeys = new IData[keys.length];
	  for (int i = 0; i < keys.length; i++) {
	    sortKeys[i] = IDataFactory.create();
	    IDataCursor cursor = sortKeys[i].getCursor();
	    IDataUtil.put(cursor, "key", keys[i]);
	    IDataUtil.put(cursor, "descending?", "" + !ascending);
	    cursor.destroy();
	  }
	
	  return sort(array, sortKeys);
	}
	
	// returns a new array with all elements sorted by the values associated with 
	// the given keys
	public static IData[] sort(IData[] array, IData[] criteria) {
	  if (array == null || array.length < 2 || criteria == null || criteria.length == 0) return array;
	
	  array = java.util.Arrays.copyOf(array, array.length);
	  java.util.Arrays.sort(array, new IDataComparator(criteria));
	
	  return array;
	}
	
	// returns a new IData[] with all empty and null items removed
	public static IData[] squeeze(IData[] array, boolean recurse) {
	  if (array == null || array.length == 0) return array;
	
	  // take a copy of the array, to make sure it's really an IData[] and not some subclass that won't
	  // be able to store different IData implementations
	  array = (IData[])java.util.Arrays.copyOf(array, array.length, (new IData[0]).getClass());
	
	  java.util.List<IData> list = new java.util.ArrayList<IData>(array.length);
	
	  for (int i = 0; i < array.length; i++) {
	    if (array[i] != null) array[i] = tundra.document.squeeze(array[i], recurse);
	    if (array[i] != null) list.add(array[i]);
	  }
	
	  array = list.toArray(new IData[0]);
	  if (array.length == 0) array = null;
	
	  return array;
	}
	
	// compares two IData objects using the values associated with the given list
	// of keys
	public static class IDataComparator implements java.util.Comparator<IData> {
	  protected IData[] criteria;
	
	  public IDataComparator(IData[] criteria) {
	    this.criteria = criteria;
	  }
	
	  protected static int normalize(int result, boolean descending) {
	    if (descending) {
	      if (result < 0) {
	        result = 1;
	      } else if (result > 0) {
	        result = -1;
	      }
	    }
	    return result;
	  }
	
	  public int compare(IData a, IData b) {
	    int result = 0;
	    for (IData item : criteria) {
	      IDataCursor cursor = item.getCursor();
	      String key = IDataUtil.getString(cursor, "key");
	      String type = IDataUtil.getString(cursor, "type");
	      String pattern = IDataUtil.getString(cursor, "pattern");
	      boolean descending = tundra.bool.parse(IDataUtil.getString(cursor, "descending?"));
	      cursor.destroy();
	
	      Object value_a = tundra.support.document.get(a, key);
	      Object value_b = tundra.support.document.get(b, key);
	
	      if (value_a == null) {
	        if (value_b != null) {
	          result = normalize(-1, descending);
	          break;
	        }
	      } else if (value_b == null) {
	        if (value_a != null) {
	          result = normalize(1, descending);
	          break;
	        }
	      } else {
	        if (type != null) {
	          value_a = value_a.toString();
	          value_b = value_b.toString();
	          if (type.equals("integer")) {
	            value_a = tundra.integer.parse((String)value_a);
	            value_b = tundra.integer.parse((String)value_b);
	          } else if (type.equals("decimal")) {
	            value_a = tundra.decimal.parse((String)value_a);
	            value_b = tundra.decimal.parse((String)value_b);
	          } else if (type.equals("datetime")) {
	            value_a = tundra.datetime.parse((String)value_a, pattern);
	            value_b = tundra.datetime.parse((String)value_b, pattern);
	          } else if (type.equals("duration")) {
	            value_a = tundra.integer.parse(tundra.duration.format((String)value_a, pattern, "milliseconds"));
	            value_b = tundra.integer.parse(tundra.duration.format((String)value_b, pattern, "milliseconds"));
	          }
	        }
	
	        if (value_a instanceof Comparable && value_b instanceof Comparable) {
	          result = normalize(((Comparable)value_a).compareTo((Comparable)value_b), descending);
	          if (result != 0) break;
	        }
	      }
	    }
	    return result;
	  }
	}
	
	// groups the given list by the given keys
	public static IData[] group(IData[] list, String[] keys) {
	  if (list == null || list.length == 0) return list;
	
	  if (keys == null || keys.length == 0) {
	    IData[] result = new IData[1];
	    result[0] = IDataFactory.create();
	    IDataCursor cursor = result[0].getCursor();
	    IDataUtil.put(cursor, "group", IDataFactory.create());
	    IDataUtil.put(cursor, "items", list);
	    cursor.destroy();
	    return result;
	  } else {
	    java.util.Map<CompoundKey, java.util.List<IData>> groups = new java.util.TreeMap<CompoundKey, java.util.List<IData>>();
	
	    for (int i = 0; i < list.length; i++) {
	      if (list[i] != null) {
	        CompoundKey key = new CompoundKey(keys, list[i]);
	        java.util.List<IData> array = groups.get(key);
	        if (array == null) array = new java.util.LinkedList<IData>();
	        array.add(list[i]);
	        groups.put(key, array);
	      }
	    }
	
	    java.util.List<IData> result = new java.util.ArrayList<IData>(groups.size());
	    java.util.Iterator<CompoundKey> iterator = groups.keySet().iterator();
	
	    while(iterator.hasNext()) {
	      CompoundKey key = iterator.next();
	      java.util.List<IData> items = groups.get(key);
	
	      IData group = IDataFactory.create();
	      IDataCursor cursor = group.getCursor();
	      IDataUtil.put(cursor, "group", key.getIData());
	      IDataUtil.put(cursor, "items", (IData[])items.toArray(new IData[0]));
	      cursor.destroy();
	
	      result.add(group);
	    }
	    return (IData[])result.toArray(new IData[0]);
	  }
	}
	
	public static class CompoundKey extends java.util.LinkedHashMap<String, Comparable> implements Comparable<CompoundKey>, com.wm.util.coder.IDataCodable {
	  public CompoundKey() {
	    super();
	  }
	
	  public CompoundKey(int initialCapacity) {
	    super(initialCapacity);
	  }
	
	  public CompoundKey(int initialCapacity, float loadFactor) {
	    super(initialCapacity, loadFactor);
	  }
	
	  public CompoundKey(int initialCapacity, float loadFactor, boolean accessOrder) {
	    super(initialCapacity, loadFactor, accessOrder);
	  }
	
	  public CompoundKey(java.util.Map<? extends String,? extends Comparable> map) {
	    super(map);
	  }
	
	  public CompoundKey(String[] keys, IData document) {
	    super(keys.length);
	    
	    // seed with key value pairs
	    for (int i = 0; i < keys.length; i++) {
	      Object value = tundra.support.document.get(document, keys[i]);
	      if (value != null && value instanceof Comparable) {
	        this.put(keys[i], (Comparable)value);
	      } else {
	        this.put(keys[i], null);
	      }
	    }    
	  }
	
	  public int compareTo(CompoundKey other) {
	    if (other == null) return 1;
	
	    int result = 0;
	    
	    java.util.Iterator<String> iterator = this.keySet().iterator();
	
	    while(iterator.hasNext()) {
	      String key = iterator.next();
	      Comparable thisValue = this.get(key);
	      Comparable otherValue = other.get(key);
	
	      if (thisValue == null) {
	        if (otherValue != null) result = -1;
	      } else {
	        if (otherValue == null) {
	          result = 1;
	        } else {
	          result = thisValue.compareTo(otherValue);
	        }
	      }
	      if (result != 0) break;
	    }
	    return result;
	  }
	
	  public boolean equals(Object other) {
	    boolean result = false;
	
	    if (other != null && other instanceof CompoundKey) {
	      result = this.compareTo((CompoundKey)other) == 0;
	    }
	    return result;
	  }
	
	  public IData getIData() {
	    IData output = IDataFactory.create();
	    java.util.Iterator<String> iterator = this.keySet().iterator();
	    while(iterator.hasNext()) {
	      String key = iterator.next();
	      Object value = this.get(key);
	      tundra.support.document.put(output, key, value);
	    }
	    return output;
	  }
	
	  public void setIData(IData input) {
	    if (input == null) return;
	
	    this.clear();
	
	    IDataCursor cursor = input.getCursor();
	    while(cursor.next()) {
	      String key = cursor.getKey();
	      Object value = cursor.getValue();
	
	      if (value != null && value instanceof Comparable) this.put(key, (Comparable)value);
	    }
	    cursor.destroy();
	  }
	}
	
	// returns the values associated with the given key from each item in the given IData[] document list
	public static Object[] values(IData[] input, String key, Object defaultValue) {
	  if (input == null || key == null) return null;
	
	  java.util.Set<Class<?>> classes = new java.util.LinkedHashSet<Class<?>>();
	  java.util.List list = new java.util.ArrayList(input.length);
	  
	  for (int i = 0; i < input.length; i++) {
	    Object value = tundra.support.document.get(input[i], key, defaultValue);
	    if (value != null) classes.add(value.getClass());
	    list.add(value);
	  }
	  
	  Class<?> nearestAncestor = tundra.support.object.nearestAncestor(classes);
	  if (nearestAncestor == null) nearestAncestor = Object.class;
	  
	  return list.toArray((Object[])java.lang.reflect.Array.newInstance(nearestAncestor, 0));
	}
	// --- <<IS-END-SHARED>> ---
}

