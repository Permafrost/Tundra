package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-02-22 15:51:19.390
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataMap;
import permafrost.tundra.flow.ConditionEvaluator;
import permafrost.tundra.lang.ArraySplitter;
import permafrost.tundra.lang.ArrayHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.math.IntegerHelper;
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
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void coalesce (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(coalesce)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] object:0:optional $default
		// [i] field:0:optional $mode {&quot;missing&quot;,&quot;null&quot;}
		// [o] object:0:optional $item
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		    Object defaultValue = IDataUtil.get(cursor, "$default");
		    String mode = IDataUtil.getString(cursor, "$mode");

		    Object result = ArrayHelper.coalesce(list, defaultValue);

		    if (result != null || (mode != null && mode.equals("null"))) IDataUtil.put(cursor, "$item", result);
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
		    if (list != null && list.length > 0) IDataUtil.put(cursor, "$list", ArrayHelper.compact(list));
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $class
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String className = IDataUtil.getString(cursor, "$class");
		    concatenate(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
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
		    Object[] listX = IDataUtil.getObjectArray(cursor, "$list.x");
		    Object[] listY = IDataUtil.getObjectArray(cursor, "$list.y");

		    if (listX != null) IDataUtil.put(cursor, "$list", ArrayHelper.difference(listX, listY));
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
		    int index = IntegerHelper.parse(IDataUtil.getString(cursor, "$index"));

		    if (list != null && list.length > 0) IDataUtil.put(cursor, "$list", ArrayHelper.drop(list, index));
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $class
		// [o] field:0:required $equal?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String className = IDataUtil.getString(cursor, "$class");
		    equal(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
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

		    if (list != null) IDataUtil.put(cursor, "$list", filter(list, condition, scope == null? pipeline : scope));
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
		    if (list != null && list.length > 0) IDataUtil.put(cursor, "$item", ArrayHelper.get(list, 0));
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
		    int index = IntegerHelper.parse(IDataUtil.getString(cursor, "$index"), IntegerHelper.parse(IDataUtil.getString(cursor, "$iteration"), 1) - 1);
		    if (list != null && list.length > 0) IDataUtil.put(cursor, "$item", ArrayHelper.get(list, index));
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
		    ExceptionHelper.raise(ex);
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

		    IDataUtil.put(cursor, "$include?", BooleanHelper.emit(ArrayHelper.include(list, item)));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void index (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(index)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] field:0:optional $index.start
		// [i] field:0:optional $index.step
		// [o] field:1:optional $indexes
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		    String indexStart = IDataUtil.getString(cursor, "$index.start");
		    String indexStep = IDataUtil.getString(cursor, "$index.step");

		    if (list != null) IDataUtil.put(cursor, "$indexes", ArrayHelper.index(list, indexStart == null ? 0 : Integer.parseInt(indexStart), indexStep == null ? 1 : Integer.parseInt(indexStep)));
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
		    ExceptionHelper.raise(ex);
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
		    if (list != null && klass != null) IDataUtil.put(cursor, "$instance?", BooleanHelper.emit(tundra.object.instance(list, klass)));
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $class
		// [o] object:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String className = IDataUtil.getString(cursor, "$class");
		    intersection(pipeline, className == null ? Object.class : Class.forName(className));
		} catch (ClassNotFoundException ex) {
		    ExceptionHelper.raise(ex);
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

		    IDataUtil.put(cursor, "$result", ArrayHelper.join(list, separator, false));
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
		    if (list != null && list.length > 0) IDataUtil.put(cursor, "$item", ArrayHelper.get(list, -1));
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
		    IDataUtil.put(cursor, "$length", "" + ArrayHelper.length(array));
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
		    ExceptionHelper.raise(ex);
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
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void reject (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reject)>> ---
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

		    IDataUtil.put(cursor, "$list", reject(list, condition, scope == null? pipeline : scope));
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
		    ExceptionHelper.raise(ex);
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
		    if (list != null) IDataUtil.put(cursor, "$list", ArrayHelper.reverse(list));
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

		    list = ArrayHelper.shrink(list, count);

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

		    if (list != null) {
		        int index = IntegerHelper.parse(IDataUtil.getString(cursor, "$index"));
		        int length = IntegerHelper.parse(IDataUtil.getString(cursor, "$length"), list.length);

		        IDataUtil.put(cursor, "$list", ArrayHelper.slice(list, index, length));
		    }
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
		    if (list != null) IDataUtil.put(cursor, "$list", ArrayHelper.sort(list));
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
		    if (list != null) IDataUtil.put(cursor, "$list", ArrayHelper.squeeze(list));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void take (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(take)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:1:optional $list
		// [i] field:0:required $count
		// [o] object:1:optional $head
		// [o] object:1:optional $tail
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object[] list = IDataUtil.getObjectArray(cursor, "$list");
		    int count = IntegerHelper.parse(IDataUtil.getString(cursor, "$count"));

		    if (list != null) {
		        ArraySplitter splitter = new ArraySplitter(list, count);
		        IDataUtil.put(cursor, "$head", splitter.getHead());
		        IDataUtil.put(cursor, "$tail", splitter.getTail());
		    }
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
		    if (list != null) IDataUtil.put(cursor, "$list", ArrayHelper.unique(list));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * Returns a new array, with the given element inserted at the end.
	 *
	 * @param pipeline The pipeline containing the array and item to be inserted.
	 * @param klass    The class of the item being appended.
	 * @param <T>      The class of the item being appended.
	 */
	public static <T> void append(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	        T item = (T)IDataUtil.get(cursor, "$item");

	        list = ArrayHelper.append(list, item, klass, false);

	        if (list != null) IDataUtil.put(cursor, "$list", list);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Returns a new array that is the concatenation of the given arrays.
	 *
	 * @param pipeline The pipeline containing the arrays to be concatenated.
	 * @param klass    The component class of the arrays.
	 * @param <T>      The component class of the arrays.
	 */
	public static <T> void concatenate(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        IData operands = IDataUtil.getIData(cursor, "$operands");

	        // support $list.x and $list.y inputs for backwards-compatibility
	        if (operands == null) {
	            Object[] listX = IDataUtil.getObjectArray(cursor, "$list.x");
	            Object[] listY = IDataUtil.getObjectArray(cursor, "$list.y");

	            IDataMap map = new IDataMap();
	            if (listX != null) map.put("$list.x", listX);
	            if (listY != null) map.put("$list.y", listY);
	            operands = map;
	        }

	        if (IDataHelper.size(operands) > 0) IDataUtil.put(cursor, "$list", ArrayHelper.concatenate(operands, klass));
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Invokes the given service for each element in the array.
	 *
	 * @param array     The array to iterate over.
	 * @param service   The service to invoke for each item in the array.
	 * @param pipeline  The input pipeline used when invoking the service.
	 * @param input     The variable name used for the item in the input pipeline.
	 * @param <T>       The array component class.
	 * @throws ServiceException If an error invoking the service occurs.
	 */
	public static <T> void each(T[] array, String service, IData pipeline, String input) throws ServiceException {
	    map(array, service, pipeline, input, null);
	}

	/**
	 * Returns whether the given arrays are considered equal.
	 *
	 * @param pipeline The pipeline containing the arrays to be compared.
	 * @param klass    The component class of the arrays.
	 * @param <T>      The component class of the arrays.
	 */
	public static <T> void equal(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        IData operands = IDataUtil.getIData(cursor, "$operands");

	        // support $list.x and $list.y inputs for backwards-compatibility
	        if (operands == null) {
	            Object[] listX = IDataUtil.getObjectArray(cursor, "$list.x");
	            Object[] listY = IDataUtil.getObjectArray(cursor, "$list.y");

	            IDataMap map = new IDataMap();
	            if (listX != null) map.put("$list.x", listX);
	            if (listY != null) map.put("$list.y", listY);
	            operands = map;
	        }

	        IDataUtil.put(cursor, "$equal?", BooleanHelper.emit(ArrayHelper.equal(operands, klass)));
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Filters the given list to only include items where the given condition evaluates to true.
	 *
	 * @param array     The array to be filtered.
	 * @param condition The filter condition.
	 * @param pipeline  The pipeline against which the condition is evaluated.
	 * @param <T>       The array component class.
	 * @return          A new array containing only the items from the given array where the condition was true.
	 * @throws ServiceException If an error occurs evaluating the condition.
	 */
	public static <T> T[] filter(T[] array, String condition, IData pipeline) throws ServiceException {
	    if (array == null || array.length == 0 || condition == null || condition.equals("")) return array;
	    if (pipeline == null) pipeline = IDataFactory.create();

	    List<T> list = new ArrayList<T>(array.length);

	    for (int i = 0; i < array.length; i++) {
	        IDataCursor cursor = pipeline.getCursor();
	        IDataUtil.put(cursor, "$item", array[i]);

	        if (ConditionEvaluator.evaluate(condition, pipeline)) list.add(array[i]);

	        IDataUtil.remove(cursor, "$item");
	        cursor.destroy();
	    }

	    return list.toArray(Arrays.copyOf(array, list.size()));
	}

	/**
	 * Grows the given array to the desired length, and pads with the given item.
	 *
	 * @param pipeline  The pipeline containing $list, $count, and $item variables.
	 * @param klass     The array component class.
	 * @param <T>       The array component class.
	 */
	public static <T> void grow(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	        int count = IntegerHelper.parse(IDataUtil.getString(cursor, "$count"));
	        T item = (T)IDataUtil.get(cursor, "$item");

	        list = ArrayHelper.grow(list, count, item, klass);

	        if (list != null) IDataUtil.put(cursor, "$list", list);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Returns a new array, with the given element inserted at the given index.
	 *
	 * @param pipeline  The pipeline containing the $list, $item, $index variables.
	 * @param klass     The array component class.
	 * @param <T>       The array component class.
	 */
	public static <T> void insert(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	        T item = (T)IDataUtil.get(cursor, "$item");
	        int index = IntegerHelper.parse(IDataUtil.getString(cursor, "$index"));

	        IDataUtil.put(cursor, "$list", ArrayHelper.insert(list, item, index, klass, false));
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Returns a new array which is the set intersection of the given arrays.
	 *
	 * @param pipeline  The pipeline containing the arrays to be intersected.
	 * @param klass     The array component class.
	 * @param <T>       The array component class.
	 */
	public static <T> void intersection(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        IData operands = IDataUtil.getIData(cursor, "$operands");

	        // support $list.x and $list.y inputs for backwards-compatibility
	        if (operands == null) {
	            Object[] listX = IDataUtil.getObjectArray(cursor, "$list.x");
	            Object[] listY = IDataUtil.getObjectArray(cursor, "$list.y");

	            IDataMap map = new IDataMap();
	            if (listX != null) map.put("$list.x", listX);
	            if (listY != null) map.put("$list.y", listY);
	            operands = map;
	        }

	        if (IDataHelper.size(operands) > 0) IDataUtil.put(cursor, "$list", ArrayHelper.intersect(operands, klass));
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Maps the given array to a new array by invoking a service for each element and collecting the output.
	 *
	 * @param pipeline The pipeline containing the $list, $service, $pipeline, $item.input, and $item.output variables.
	 * @param klass    The array component class.
	 * @param <T>      The array component class.
	 * @throws ServiceException If an error occurs invoking the service.
	 */
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

	        list = map(list == null ? null : Arrays.copyOf(list, list.length, (Class<T[]>)Array.newInstance(klass, 0).getClass()), service, scoped ? scope : pipeline, input, output);

	        if (list != null) IDataUtil.put(cursor, "$list", list);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Maps the given array to a new array by invoking a service for each element and collecting the output.
	 *
	 * @param array     The array to be iterated over.
	 * @param service   The service to be invoked for each item in the array.
	 * @param pipeline  The input pipeline used when invoking the array.
	 * @param input     The variable name used when passing the array item in the input pipeline.
	 * @param output    The variable name of the mapped item returned by the invoked service.
	 * @param <T>       The array component class.
	 * @return          A new array containing each item from the given array after being transformed by the given service.
	 * @throws ServiceException If an error occurs invoking the service.
	 */
	public static <T> T[] map(T[] array, String service, IData pipeline, String input, String output) throws ServiceException {
	    if (array == null || array.length == 0 || service == null) return array;
	    if (pipeline == null) pipeline = IDataFactory.create();
	    if (input == null) input = "$item";
	    if (output == null) output = input;

	    List<T> list = new ArrayList<T>(array.length);
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

	    return list.toArray(Arrays.copyOf(array, list.size()));
	}

	/**
	 * Returns a new array, with the given element inserted at the beginning.
	 *
	 * @param pipeline  The pipeline containing the $list and $item variables.
	 * @param klass     The array component class.
	 * @param <T>       The array component class.
	 */
	public static <T> void prepend(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	        T item = (T)IDataUtil.get(cursor, "$item");

	        list = ArrayHelper.prepend(list, item, klass, false);

	        if (list != null) IDataUtil.put(cursor, "$list", list);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Sets the element from the given array at the given index (supports ruby-style reverse indexing).
	 *
	 * @param pipeline  The pipeline containing the $list, $item, and $index variables.
	 * @param klass     The array component class.
	 * @param <T>       The array component class.
	 */
	public static <T> void put(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	        T item = (T)IDataUtil.get(cursor, "$item");
	        int index = IntegerHelper.parse(IDataUtil.getString(cursor, "$index"));

	        IDataUtil.put(cursor, "$list", ArrayHelper.put(list == null ? null : Arrays.copyOf(list, list.length, (Class<T[]>)Array.newInstance(klass, 0).getClass()), item, index, klass));
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Filters the given list to not include items where the given condition evaluates to true.
	 *
	 * @param array     The array to be filtered.
	 * @param condition The filter condition.
	 * @param pipeline  The pipeline against which the condition is evaluated.
	 * @param <T>       The array component class.
	 * @return          A new array containing only the items from the given array where the condition was false.
	 * @throws ServiceException If an error occurs evaluating the condition.
	 */
	public static <T> T[] reject(T[] array, String condition, IData pipeline) throws ServiceException {
	    if (array == null || array.length == 0 || condition == null || condition.equals("")) return array;
	    if (pipeline == null) pipeline = IDataFactory.create();

	    List<T> list = new ArrayList<T>(array.length);

	    for (int i = 0; i < array.length; i++) {
	        IDataCursor cursor = pipeline.getCursor();
	        IDataUtil.put(cursor, "$item", array[i]);

	        if (!ConditionEvaluator.evaluate(condition, pipeline)) list.add(array[i]);

	        IDataUtil.remove(cursor, "$item");
	        cursor.destroy();
	    }

	    return list.toArray(Arrays.copyOf(array, list.size()));
	}

	/**
	 * Resizes the given array to the desired length, and pads with the given item.
	 *
	 * @param pipeline The pipeline containing $list, $length, and $item variables.
	 * @param klass    The array component class.
	 * @param <T>      The array component class.
	 */
	public static <T> void resize(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        T[] list = (T[])IDataUtil.getObjectArray(cursor, "$list");
	        int length = IntegerHelper.parse(IDataUtil.getString(cursor, "$length"));
	        T item = (T)IDataUtil.get(cursor, "$item");

	        list = ArrayHelper.resize(list, length, item, klass);

	        if (list != null) IDataUtil.put(cursor, "$list", list);
	    } finally {
	        cursor.destroy();
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

