package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-06-29 18:18:04 EST
// -----( ON-HOST: 172.16.189.136

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
		// [i] field:1:optional $keys
		// [i] field:0:optional $ascending? {&quot;true&quot;,&quot;false&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		  String[] keys = IDataUtil.getStringArray(cursor, "$keys");
		  // silently support $key for backwards compatibility
		  String key = IDataUtil.getString(cursor, "$key");
		  String sAscending = IDataUtil.getString(cursor, "$ascending?");
		
		  boolean ascending = true;
		  if (sAscending != null) ascending = tundra.bool.parse(sAscending);
		
		  if (list != null) {
		    if (keys == null) {
		      IDataUtil.put(cursor, "$list", sort(list, key, ascending));
		    } else {
		      IDataUtil.put(cursor, "$list", sort(list, keys, ascending));
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
	  if (array == null || keys == null || keys.length == 0) return array;
	
	  array = java.util.Arrays.copyOf(array, array.length);
	  java.util.Arrays.sort(array, new IDataComparator(keys));
	
	  return ascending ? array : tundra.list.object.reverse(array);
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
	  protected String[] keys;
	
	  public IDataComparator(String[] keys) {
	    this.keys = keys;
	  }
	
	  public int compare(IData a, IData b) {
	    int result = 0;
	    for (String key : keys) {
	      Object value_a = tundra.support.document.get(a, key);
	      Object value_b = tundra.support.document.get(b, key);
	
	      if (value_a == null) {
	        if (value_b != null) {
	          result = -1;
	          break;
	        }
	      } else if (value_b == null) {
	        if (value_a != null) {
	          result = 1;
	          break;
	        }
	      } else if (value_a instanceof Comparable && value_b instanceof Comparable) {
	        result = ((Comparable)value_a).compareTo((Comparable)value_b);
	        if (result != 0) break;
	      }
	    }
	    return result;
	  }
	}
	// --- <<IS-END-SHARED>> ---
}

