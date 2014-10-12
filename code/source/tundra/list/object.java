package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-10-12 12:02:22 EST
// -----( ON-HOST: 172.16.189.176

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class object

{
	// ---( internal utility methods )---

	final static object _instance = new object();

	static object _newInstance() { return new object(); }

	static object _cast(Object o) { return (object)o; }

	// ---( server methods )---




	public static final void append (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(append)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] object:0:optional $item
		// [i] field:0:optional $class
		// [o] object:1:required $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String className = IDataUtil.getString(cursor, "$class");
		  append(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		  tundra.exception.raise(ex);
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
		// [i] object:1:optional $list
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		
		  IDataUtil.put(cursor, "$list", compact(list));
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
		// [i] object:1:optional $list.x
		// [i] object:1:optional $list.y
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list.x");
		  Object[] items = IDataUtil.getObjectArray(cursor, "$list.y");
		
		  IDataUtil.put(cursor, "$list", concatenate(list, items));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void difference (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(difference)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list.x
		// [i] object:1:optional $list.y
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] x = IDataUtil.getObjectArray(cursor, "$list.x");
		  Object[] y = IDataUtil.getObjectArray(cursor, "$list.y");
		
		  if (x != null) IDataUtil.put(cursor, "$list", difference(x, y));
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
		// [i] object:1:optional $list
		// [i] field:0:required $index
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  String index = IDataUtil.getString(cursor, "$index");
		  IDataUtil.put(cursor, "$list", drop(list, index));
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
		// [i] object:1:optional $list
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $item.input
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  String service = IDataUtil.getString(cursor, "$service");
		  IData scope = IDataUtil.getIData(cursor, "$pipeline");
		  String input = IDataUtil.getString(cursor, "$item.input");
		
		  boolean scoped = scope != null;
		
		  // invoke the service for each item in the list, passing 
		  // $item and $index variables on each invocation
		  each(list, service, scoped ? scope : pipeline, input);
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
		// [i] object:1:optional $list.x
		// [i] object:1:optional $list.y
		// [o] field:0:required $equal?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] x = IDataUtil.getObjectArray(cursor, "$list.x");
		  Object[] y = IDataUtil.getObjectArray(cursor, "$list.y");
		
		  IDataUtil.put(cursor, "$equal?", "" + equal(x, y));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void filter (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(filter)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] field:0:optional $condition
		// [i] record:0:optional $scope
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  String condition = IDataUtil.getString(cursor, "$condition");
		  IData scope = IDataUtil.getIData(cursor, "$scope");
		
		  IDataUtil.put(cursor, "$list", filter(list, condition, scope == null? pipeline : scope));
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
		// [i] object:1:optional $list
		// [o] object:0:optional $item
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  if (list != null && list.length > 0) IDataUtil.put(cursor, "$item", first(list));
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
		// [i] object:1:optional $list
		// [i] field:0:optional $index
		// [i] field:0:optional $iteration
		// [o] object:0:optional $item
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  String index = IDataUtil.getString(cursor, "$index");
		  String iteration = IDataUtil.getString(cursor, "$iteration");
		
		  if (index != null || iteration != null) IDataUtil.put(cursor, "$item", get(list, index, iteration));
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
		// [i] object:1:optional $list
		// [i] object:0:optional $item
		// [i] field:0:required $count
		// [i] field:0:optional $class
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String className = IDataUtil.getString(cursor, "$class");
		  grow(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void include (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(include)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] object:0:optional $item
		// [o] field:0:required $include?
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  Object item = IDataUtil.get(cursor, "$item");
		
		  IDataUtil.put(cursor, "$include?", "" + include(list, item));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void insert (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(insert)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] object:0:optional $item
		// [i] field:0:required $index
		// [i] field:0:optional $class
		// [o] object:1:required $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String className = IDataUtil.getString(cursor, "$class");
		  insert(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void instance (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(instance)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] field:0:optional $class
		// [o] field:0:optional $instance?
		IDataCursor cursor = pipeline.getCursor();
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  String klass = IDataUtil.getString(cursor, "$class");
		  if (list != null && klass != null) IDataUtil.put(cursor, "$instance?", "" + tundra.object.instance(list, klass));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void intersection (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(intersection)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list.x
		// [i] object:1:optional $list.y
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] x = IDataUtil.getObjectArray(cursor, "$list.x");
		  Object[] y = IDataUtil.getObjectArray(cursor, "$list.y");
		
		  if (x != null && y != null) IDataUtil.put(cursor, "$list", intersection(x, y));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void join (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(join)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] field:0:optional $separator
		// [o] field:0:required $result
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  String separator = IDataUtil.getString(cursor, "$separator");
		
		  IDataUtil.put(cursor, "$result", join(list, separator));
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
		// [i] object:1:optional $list
		// [o] object:0:optional $item
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  if (list != null && list.length > 0) IDataUtil.put(cursor, "$item", last(list));
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
		// [i] object:1:optional $list
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] array = IDataUtil.getObjectArray(cursor, "$list");
		
		  IDataUtil.put(cursor, "$length", "" + length(array));
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
		// [i] object:1:optional $list
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $item.input
		// [i] field:0:optional $item.output
		// [o] object:1:optional $list
		map(pipeline, Object.class);
		// --- <<IS-END>> ---

                
	}



	public static final void prepend (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(prepend)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] object:0:optional $item
		// [i] field:0:optional $class
		// [o] object:1:required $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String className = IDataUtil.getString(cursor, "$class");
		  prepend(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		  tundra.exception.raise(ex);
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
		// [i] object:1:optional $list
		// [i] object:0:optional $item
		// [i] field:0:required $index
		// [i] field:0:optional $class
		// [o] object:1:required $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String className = IDataUtil.getString(cursor, "$class");
		  put(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void resize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(resize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] object:0:optional $item
		// [i] field:0:required $length
		// [i] field:0:optional $class
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  String className = IDataUtil.getString(cursor, "$class");
		  resize(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		  tundra.exception.raise(ex);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void reverse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reverse)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		
		  IDataUtil.put(cursor, "$list", reverse(list));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void shrink (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(shrink)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] field:0:required $count
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  int count = Integer.parseInt(IDataUtil.getString(cursor, "$count"));
		
		  list = shrink(list, count);
		
		  if (list != null) IDataUtil.put(cursor, "$list", list);
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void slice (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(slice)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] field:0:optional $index
		// [i] field:0:optional $length
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		  String index = IDataUtil.getString(cursor, "$index");
		  String length = IDataUtil.getString(cursor, "$length");
		
		  IDataUtil.put(cursor, "$list", slice(list, index, length));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void sort (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sort)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		
		  IDataUtil.put(cursor, "$list", sort(list));
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
		// [i] object:1:optional $list
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		
		  IDataUtil.put(cursor, "$list", squeeze(list));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void unique (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unique)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		  Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		
		  IDataUtil.put(cursor, "$list", unique(list));
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns a new array, with the given element inserted at the end
	public static <T> T[] append(T[] array, T item, Class<T> klass) {
	  return insert(array, item, -1, klass);
	}
	
	// returns a new array, with the given element inserted at the end
	public static <T> void append(IData pipeline, Class<T> klass) {
	  IDataCursor cursor = pipeline.getCursor();
	
	  try {
	    T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	    T item = (T)IDataUtil.get(cursor, "$item");
	
	    list = append(list == null ? null : java.util.Arrays.copyOf(list, list.length, (Class<T[]>)java.lang.reflect.Array.newInstance(klass, 0).getClass()), item, klass);
	
	    if (list != null) IDataUtil.put(cursor, "$list", list);
	  } finally {
	    cursor.destroy();
	  }
	}
	
	// returns a new array with all null elements removed
	public static <T> T[] compact(T[] array) {
	  if (array == null) return array;
	  
	  java.util.List<T> list = new java.util.ArrayList<T>(array.length);
	
	  for (int i = 0; i < array.length; i++) {
	    if (array[i] != null) list.add(array[i]);
	  }
	  
	  return list.toArray(java.util.Arrays.copyOf(array, 0));
	}
	
	// returns a new array which contains all the elements from the given arrays
	public static <T> T[] concatenate(T[] array, T[] items) {
	  if (array == null) return items;
	  if (items == null) return array;
	
	  java.util.List<T> list = new java.util.ArrayList<T>(array.length + items.length);
	  
	  java.util.Collections.addAll(list, array);
	  java.util.Collections.addAll(list, items);
	  
	  return list.toArray(java.util.Arrays.copyOf(array, 0));
	}
	
	// returns only the items in x that are not also in y
	public static <T> T[] difference(T[] x, T[] y) {
	  if (x == null || y == null) return x;
	
	  java.util.List<T> d = new java.util.ArrayList<T>(x.length);
	  d.addAll(java.util.Arrays.asList(x));
	  d.removeAll(java.util.Arrays.asList(y));
	  
	  return d.toArray(java.util.Arrays.copyOf(x, 0));
	}
	
	// removes the element at the given index from the given list
	public static <T> T[] drop(T[] array, String index) {
	  return drop(array, Integer.parseInt(index));
	}
	
	// removes the element at the given index from the given list
	public static <T> T[] drop(T[] array, int index) {
	  if (array != null) {
	    // support reverse/tail indexing
	    if (index < 0) index += array.length;
	    if (index < 0 || array.length <= index) throw new ArrayIndexOutOfBoundsException(index);
	
	    T[] head = slice(array, 0, index);
	    T[] tail = slice(array, index + 1, array.length - index);
	
	    array = concatenate(head, tail);      
	  }
	
	  return array;
	}
	
	// invokes the given service for each element in the array
	public static <T> void each(T[] array, String service, IData pipeline, String input) throws ServiceException {
	  map(array, service, pipeline, input, null);
	}
	
	// returns true if the given arrays are equal
	public static <T> boolean equal(T[] x, T[] y) {
	  boolean result = true;
	
	  if (x != null && y != null) {
	    result = (x.length == y.length);
	
	    if (result) {
	      for (int i = 0; i < x.length; i++) {
	        result = result && tundra.object.equal(x[i], y[i]);
	        if (!result) break;
	      }
	    }
	  } else {
	    result = (x == null && y == null);
	  }
	
	  return result;
	}
	
	// filters the given array by evaluating the conditional statement against the 
	// given pipeline and each item in the array
	public static <T> T[] filter(T[] array, String condition, IData pipeline) throws ServiceException {
	  if (array == null || array.length == 0 || condition == null || condition.equals("")) return array;
	  if (pipeline == null) pipeline = IDataFactory.create();
	
	  java.util.ArrayList<T> list = new java.util.ArrayList<T>(array.length);
	
	  for (int i = 0; i < array.length; i++) {
	    IDataCursor cursor = pipeline.getCursor();
	    IDataUtil.put(cursor, "$item", array[i]);
	
	    if (tundra.condition.evaluate(condition, pipeline)) list.add(array[i]);
	    
	    IDataUtil.remove(cursor, "$item");
	    cursor.destroy();
	  }
	
	  return list.toArray(java.util.Arrays.copyOf(array, 0));  
	}
	
	// returns a new array which is a one-dimensional recursive flattening of the given array
	public static <T> T[] flatten(T[] array) {
	  if (array == null || array.length == 0) return array;
	
	  java.util.ArrayList<T> list = new java.util.ArrayList<T>(array.length);
	
	  for (int i = 0; i < array.length; i++) {
	    if (array[i] != null && array[i].getClass().isArray()) {
	      list.ensureCapacity(list.size() + ((T[])array[i]).length);
	      list.addAll(java.util.Arrays.asList(flatten((T[])array[i])));
	    } else {
	      list.add(array[i]);
	    }
	  }
	  
	  return list.toArray(java.util.Arrays.copyOf(array, 0));  
	}
	
	// returns the element from the given array at the given index (supports ruby-style reverse indexing)
	public static <T> T get(T[] array, String index, String iteration) {
	  int i = 0;
	  if (index != null) {
	    i = Integer.parseInt(index);
	  } else {
	    i = Integer.parseInt(iteration) - 1;
	  }
	  return get(array, i);
	}
	
	// returns the element from the given array at the given index (supports ruby-style reverse indexing)
	public static <T> T get(T[] array, String index) {
	  return get(array, index, null);
	}
	
	// returns the element from the given array at the given index (supports ruby-style reverse indexing)
	public static <T> T get(T[] array, int index) {
	  T item = null;
	  
	  if (array != null) {
	    // support reverse/tail indexing
	    if (index < 0) index += array.length;
	    
	    item = array[index];
	  }
	  
	  return item;
	}
	
	// returns the first element from the given array
	public static <T> T first(T[] array) {
	  return get(array, 0);
	}
	
	// returns the last element from the given array
	public static <T> T last(T[] array) {
	  return get(array, -1);
	}
	
	// resizes the given array (or instantiates a new array, if null) to the desired length, 
	// and pads with the given item
	public static <T> T[] resize(T[] array, int newLength, T item, Class<T> klass) {
	  if (array == null) array = (T[])java.lang.reflect.Array.newInstance(klass, 0);
	  return resize(array, newLength, item);
	}
	
	// resizes the given array to the desired length, and pads with the given item
	public static <T> T[] resize(T[] array, int newLength, T item) {
	  if (newLength < 0) newLength = array.length + newLength;
	  if (newLength < 0) newLength = 0;
	
	  int originalLength = array.length;
	  if (newLength == originalLength) return array;
	
	  array = java.util.Arrays.copyOf(array, newLength);
	  if (item != null) {
	    for (int i = originalLength; i < newLength; i++) array[i] = item;
	  }
	  return array;
	}
	
	// resizes the given array to the desired length, and pads with nulls
	public static <T> T[] resize(T[] array, int newLength) {
	  return resize(array, newLength, null);
	}
	
	// resizes the given array to the desired length, and pads with the given item
	public static <T> void resize(IData pipeline, Class<T> klass) {
	  IDataCursor cursor = pipeline.getCursor();
	
	  try {
	    T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	    int length = Integer.parseInt(IDataUtil.getString(cursor, "$length"));
	    T item = (T)IDataUtil.get(cursor, "$item");
	
	    list = resize(list, length, item, klass);
	
	    if (list != null) IDataUtil.put(cursor, "$list", list);
	  } finally {
	    cursor.destroy();
	  }
	}
	
	// grows the size of the given array by the given count, and pads with the given item
	public static <T> T[] grow(T[] array, int count, T item, Class<T> klass) {
	  return resize(array, array == null ? count : array.length + count, item, klass);
	}
	
	// grows the given array to the desired length, and pads with the given item
	public static <T> void grow(IData pipeline, Class<T> klass) {
	  IDataCursor cursor = pipeline.getCursor();
	
	  try {
	    T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	    int count = Integer.parseInt(IDataUtil.getString(cursor, "$count"));
	    T item = (T)IDataUtil.get(cursor, "$item");
	
	    list = grow(list, count, item, klass);
	
	    if (list != null) IDataUtil.put(cursor, "$list", list);
	  } finally {
	    cursor.destroy();
	  }
	}
	
	// shrinks the size of the given array by the given count
	public static <T> T[] shrink(T[] array, int count) {
	  if (array != null) {
	    int length = array.length - count;
	    array = resize(array, length < 0 ? 0 : length);
	  }
	  return array;
	}
	
	// returns true if the given item is found in the given array
	public static <T> boolean include(T[] array, T item) {
	  boolean found = false;
	
	  if (array != null) {
	    for (int i = 0; i < array.length; i++) {
	      found = tundra.object.equal(array[i], item);
	      if (found) break;
	    }
	  }
	
	  return found;
	}
	
	// returns a new array with the given item inserted at the given index
	public static <T> T[] insert(T[] array, T item, String index, Class<T> klass) {
	  if (index == null) index = "-1";
	  return insert(array, item, Integer.parseInt(index), klass);
	}
	
	// returns a new array with the given item inserted at the given index
	public static <T> T[] insert(T[] array, T item, int index, Class<T> klass) {
	  if (array == null) array = (T[])java.lang.reflect.Array.newInstance(klass, 0);
	
	  java.util.ArrayList<T> list = new java.util.ArrayList<T>(java.util.Arrays.asList(array));
	
	  int capacity = 0;
	  if (index < 0) index += list.size() + 1;
	  if (index < 0) {
	    capacity = (index * -1) + list.size();
	    index = 0;
	  } else {
	    capacity = index;
	  }
	
	  list.ensureCapacity(capacity);
	  if (capacity >= list.size()) {
	    // fill the list with nulls if it needs to be extended
	    for (int i = list.size(); i < capacity; i++) {
	      list.add(i, null);
	    }
	  }
	  list.add(index, item);
	  
	  return list.toArray(array);
	}
	
	// returns a new array, with the given element inserted at the given index
	public static <T> void insert(IData pipeline, Class<T> klass) {
	  IDataCursor cursor = pipeline.getCursor();
	
	  try {
	    T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	    T item = (T)IDataUtil.get(cursor, "$item");
	    String index = IDataUtil.getString(cursor, "$index");
	
	    IDataUtil.put(cursor, "$list", insert(list == null ? null : java.util.Arrays.copyOf(list, list.length, (Class<T[]>)java.lang.reflect.Array.newInstance(klass, 0).getClass()), item, index, klass));
	  } finally {
	    cursor.destroy();
	  }
	}
	
	// returns only the items in x that are also in y
	public static <T> T[] intersection(T[] x, T[] y) {
	  if (x == null || y == null) return null;
	
	  java.util.List<T> d = new java.util.ArrayList<T>(x.length);
	  d.addAll(java.util.Arrays.asList(x));
	  d.retainAll(java.util.Arrays.asList(y));
	  
	  return d.toArray(java.util.Arrays.copyOf(x, 0));
	}
	
	// returns a string created by concatenating each element of the given array, separated by the given separator string
	public static <T> String join(T[] array, String separator) {
	  StringBuilder builder = new StringBuilder();
	  
	  if (array != null) {
	    for (int i = 0; i < array.length; i++) {
	      T value = array[i];
	      if (value != null) builder.append(value.toString());
	      if (separator != null && i < array.length - 1) builder.append(separator);
	    }
	  }
	  
	  return builder.toString();
	}
	
	// returns the length of the given array
	public static <T> int length(T[] array) {
	  return (array == null? 0 : array.length);
	}
	
	// maps the given array to a new array by invoking a service for each element and collecting the output
	public static <T> void map(IData pipeline, Class<T> klass) throws ServiceException {
	  IDataCursor cursor = pipeline.getCursor();
	
	  try {
	    Object[] list = IDataUtil.getObjectArray(cursor, "$list");
	    String service = IDataUtil.getString(cursor, "$service");
	    IData scope = IDataUtil.getIData(cursor, "$pipeline");
	    String input = IDataUtil.getString(cursor, "$item.input");
	    String output = IDataUtil.getString(cursor, "$item.output");
	
	    boolean scoped = scope != null;
	
	    // invoke the service for each item in the list, passing $item and $index variables on each invocation
	    // and collect the returned $item's into a new list
	    IDataUtil.put(cursor, "$list", map(list == null ? null : java.util.Arrays.copyOf(list, list.length, (Class<T[]>)java.lang.reflect.Array.newInstance(klass, 0).getClass()), service, scoped ? scope : pipeline, input, output));
	  } finally {
	    cursor.destroy();
	  }
	}
	
	// maps the given array to a new array by invoking a service for each element and collecting the output
	public static <T> T[] map(T[] array, String service, IData pipeline, String input, String output) throws ServiceException {
	  if (array == null || array.length == 0 || service == null) return array;
	  if (pipeline == null) pipeline = IDataFactory.create();
	  if (input == null) input = "$item";
	  if (output == null) output = input;
	   
	  java.util.List<T> list = new java.util.ArrayList<T>(array.length);
	  IDataCursor cursor = null;
	  
	  for (int i = 0; i < array.length; i++) {
	    // add $item, $index, $iteration and $length variables to the input pipeline
	    cursor = pipeline.getCursor();
	    IDataUtil.put(cursor, input, array[i]);
	    IDataUtil.put(cursor, "$index", "" + i);
	    IDataUtil.put(cursor, "$iteration", "" + (i + 1));
	    IDataUtil.put(cursor, "$length", "" + array.length);
	    cursor.destroy();
	
	    // invoke the iterator service
	    pipeline = tundra.service.invoke(service, pipeline);
	
	    // clean up the input pipeline
	    cursor = pipeline.getCursor();
	    T item = (T)IDataUtil.get(cursor, output);
	    IDataUtil.remove(cursor, input);
	    IDataUtil.remove(cursor, output);
	    IDataUtil.remove(cursor, "$index");
	    IDataUtil.remove(cursor, "$iteration");
	    IDataUtil.remove(cursor, "$length");
	    cursor.destroy();
	    
	    // collect the mapped items
	    list.add(item);
	  }
	  
	  return list.toArray(java.util.Arrays.copyOf(array, 0));
	}
	
	// returns a new array with a new element inserted at the beginning
	public static <T> T[] prepend(T[] array, T item, Class<T> klass) {
	  return insert(array, item, 0, klass);
	}
	
	// returns a new array, with the given element inserted at the end
	public static <T> void prepend(IData pipeline, Class<T> klass) {
	  IDataCursor cursor = pipeline.getCursor();
	
	  try {
	    T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	    T item = (T)IDataUtil.get(cursor, "$item");
	
	    list = prepend(list == null ? null : java.util.Arrays.copyOf(list, list.length, (Class<T[]>)java.lang.reflect.Array.newInstance(klass, 0).getClass()), item, klass);
	
	    if (list != null) IDataUtil.put(cursor, "$list", list);
	  } finally {
	    cursor.destroy();
	  }
	}
	
	// sets the element from the given array at the given index (supports ruby-style reverse indexing)
	public static <T> T[] put(T[] array, T item, String index, Class<T> klass) {
	  return put(array, item, Integer.parseInt(index), klass);
	}
	
	// sets the element from the given array at the given index (supports ruby-style reverse indexing)
	public static <T> T[] put(T[] array, T item, int index, Class<T> klass) {
	  if (array == null) array = (T[])java.lang.reflect.Array.newInstance(klass, 0);
	
	  // support reverse/tail indexing
	  if (index < 0) index += array.length;
	  int capacity = 0;
	  if (index < 0) {
	    capacity = (index * -1) + array.length;
	    index = 0;
	  } else {
	    capacity = index + 1;
	  }
	  if (capacity > array.length) array = java.util.Arrays.copyOf(array, capacity);
	
	  array[index] = item;
	
	  return array;
	}
	
	// sets the element from the given array at the given index (supports ruby-style reverse indexing)
	public static <T> void put(IData pipeline, Class<T> klass) {
	  IDataCursor cursor = pipeline.getCursor();
	
	  try {
	    T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	    T item = (T)IDataUtil.get(cursor, "$item");
	    String index = IDataUtil.getString(cursor, "$index");
	
	    IDataUtil.put(cursor, "$list", put(list == null ? null : java.util.Arrays.copyOf(list, list.length, (Class<T[]>)java.lang.reflect.Array.newInstance(klass, 0).getClass()), item, index, klass));
	  } finally {
	    cursor.destroy();
	  }
	}
	
	// returns a new array with all elements from the given array but in reverse order
	public static <T> T[] reverse(T[] array) {
	  if (array == null || array.length <= 1) return array;
	  
	  java.util.ArrayList<T> list = new java.util.ArrayList<T>(java.util.Arrays.asList(array));
	  java.util.Collections.reverse(list);
	  
	  return list.toArray(java.util.Arrays.copyOf(array, 0));  
	}
	
	// returns a new array which is a subset of elements from the given array
	public static <T> T[] slice(T[] array, String index, String length) {
	  return slice(array, index == null ? 0 : Integer.parseInt(index), length == null ? (array == null ? 0 : array.length) : Integer.parseInt(length));
	}
	
	// returns a new array which is a subset of elements from the given array
	public static <T> T[] slice(T[] array, int index, int length) {
	  if (array == null || array.length == 0) return array;
	  // support reverse/tail length
	  if (length < 0) length = array.length + length;
	  // support reverse/tail indexing
	  if (index < 0) index += array.length;
	  // don't slice past the end of the array
	  if ((length += index) > array.length) length = array.length;
	  
	  return java.util.Arrays.copyOfRange(array, index, length);
	}
	
	// returns a new array with all elements sorted
	public static <T> T[] sort(T[] array) {
	  if (array == null || array.length <= 1) return array;
	  
	  T[] copy = java.util.Arrays.copyOf(array, array.length);
	  java.util.Arrays.sort(copy);
	  return copy;
	}
	
	// returns a new array with all string items trimmed, all
	// empty string items removed, and all null items removed
	public static <T> T[] squeeze(T[] array) {
	  if (array == null || array.length == 0) return null;
	
	  java.util.List<T> list = new java.util.ArrayList<T>(array.length);
	
	  for (int i = 0; i < array.length; i++) {
	    if (array[i] != null && array[i] instanceof String) {
	      T item = (T)((String)array[i]).trim();
	      if (item.equals("")) {
	        array[i] = null;
	      } else {
	        array[i] = item;
	      }
	    }
	    if (array[i] != null) list.add(array[i]);
	  }
	
	  array = list.toArray(java.util.Arrays.copyOf(array, 0));
	  if (array.length == 0) array = null;
	  
	  return array;
	}
	
	// returns a new array with all duplicate elements removed
	public static <T> T[] unique(T[] array) {
	  if (array == null || array.length <= 1) return array;
	  
	  java.util.Set<T> set = new java.util.TreeSet<T>(java.util.Arrays.asList(array));
	  
	  return set.toArray(java.util.Arrays.copyOf(array, 0));
	}
	// --- <<IS-END-SHARED>> ---
}

