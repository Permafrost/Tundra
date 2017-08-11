package tundra.collection.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-08-11T10:33:49.333
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Collection;
import java.util.List;
import permafrost.tundra.collection.CollectionHelper;
import permafrost.tundra.collection.ListHelper;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ClassHelper;
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
		// [i] object:0:optional $list
		// [i] record:0:optional $items
		// [i] field:0:optional $class
		// [o] object:0:required $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    append(pipeline, klass == null ? Object.class : klass);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void arrayify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(arrayify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $list
		// [i] field:0:optional $class
		// [o] object:1:optional $array
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    arrayify(pipeline, klass == null ? Object.class : klass);
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
		// [i] object:0:optional $list
		// [i] field:0:optional $class
		// [o] object:0:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    clear(pipeline, klass == null ? Object.class : klass);
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
		// [i] object:0:optional $list
		// [i] field:0:optional $class
		// [o] object:0:optional $item
		// [o] field:0:required $item.exists?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    first(pipeline, klass == null ? Object.class : klass);
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
		// [i] object:0:optional $list
		// [i] field:0:required $index
		// [i] field:0:optional $index.base {&quot;0&quot;,&quot;1&quot;}
		// [i] field:0:optional $class
		// [o] object:0:optional $item
		// [o] field:0:required $item.exists?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    get(pipeline, klass == null ? Object.class : klass);
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
		// [i] object:0:optional $list
		// [i] record:0:optional $items
		// [i] field:0:required $index
		// [i] field:0:optional $index.base {&quot;0&quot;,&quot;1&quot;}
		// [i] field:0:optional $class
		// [o] object:0:required $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    insert(pipeline, klass == null ? Object.class : klass);
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
		// [i] object:0:optional $list
		// [i] field:0:optional $class
		// [o] object:0:optional $item
		// [o] field:0:required $item.exists?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    first(pipeline, klass == null ? Object.class : klass);
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
		// [i] object:0:optional $list
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Collection collection = IDataHelper.get(cursor, "$list", Collection.class);
		    IDataHelper.put(cursor, "$length", CollectionHelper.length(collection), String.class);
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
		// [i] object:1:optional $array
		// [i] field:0:optional $class
		// [o] object:0:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    listify(pipeline, klass == null ? Object.class : klass);
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
		// [i] object:0:optional $list
		// [i] record:0:optional $items
		// [i] field:0:optional $class
		// [o] object:0:required $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    prepend(pipeline, klass == null ? Object.class : klass);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void remove (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remove)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $list
		// [i] field:0:required $index
		// [i] field:0:optional $index.base {&quot;0&quot;,&quot;1&quot;}
		// [i] field:0:optional $class
		// [o] object:0:optional $item
		// [o] field:0:required $item.exists?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    remove(pipeline, klass == null ? Object.class : klass);
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
		// [i] object:0:optional $list
		// [o] object:0:optional $list.reverse
		IDataCursor cursor = pipeline.getCursor();

		try {
		    List list = IDataHelper.get(cursor, "$list", List.class);
		    IDataHelper.put(cursor, "$list.reverse", ListHelper.reverse(list), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void set (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(set)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $list
		// [i] object:0:optional $item.new
		// [i] field:0:required $index
		// [i] field:0:optional $index.base {&quot;0&quot;,&quot;1&quot;}
		// [i] field:0:optional $class
		// [o] object:0:optional $item.old
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    set(pipeline, klass == null ? Object.class : klass);
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
		// [i] object:0:optional $list
		// [i] field:0:required $count
		// [i] field:0:optional $class
		// [o] object:0:optional $list.head
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Class klass = IDataHelper.get(cursor, "$class", Class.class);
		    take(pipeline, klass == null ? Object.class : klass);
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
		// [i] object:0:optional $list
		// [o] object:0:optional $list.unique
		IDataCursor cursor = pipeline.getCursor();

		try {
		    List list = IDataHelper.get(cursor, "$list", List.class);
		    IDataHelper.put(cursor, "$list.unique", ListHelper.unique(list), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	/**
	 * Appends the given items to the given java.util.List.
	 *
	 * @param pipeline The pipeline containing the list and items to be appended.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void append(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);
	        IData items = IDataHelper.get(cursor, "$items", IData.class);

	        IDataHelper.put(cursor, "$list", ListHelper.append(list, IDataHelper.getLeaves(items, klass, false)), false);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Returns a new array representation of the given java.util.List object.
	 *
	 * @param pipeline The pipeline containing the list to be converted to an array.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void arrayify(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);
	        IDataHelper.put(cursor, "$array", CollectionHelper.arrayify(list, klass), false);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Prepends the given items to the given java.util.List.
	 *
	 * @param pipeline The pipeline containing the list and items to be prepended.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void clear(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);
	        IDataHelper.put(cursor, "$list", ListHelper.clear(list, klass), false);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Returns the first item from the given java.util.List.
	 *
	 * @param pipeline The pipeline containing the list.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void first(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();
	    int index = 0;

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);

	        boolean exists = ListHelper.exists(list, index);

	        if (exists) IDataHelper.put(cursor, "$item", ListHelper.get(list, index));
	        IDataHelper.put(cursor, "$item.exists?", exists, String.class);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Returns the item from the given java.util.List at the given index.
	 *
	 * @param pipeline The pipeline containing the list and index of the required item.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void get(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);
	        int indexBase = IDataHelper.getOrDefault(cursor, "$index.base", Integer.class, 0);
	        int index = IDataHelper.get(cursor, "$index", Integer.class) - indexBase;

	        boolean exists = ListHelper.exists(list, index);

	        if (exists) IDataHelper.put(cursor, "$item", ListHelper.get(list, index));
	        IDataHelper.put(cursor, "$item.exists?", exists, String.class);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Inserts the given items to the given java.util.List.
	 *
	 * @param pipeline The pipeline containing the list and items to be inserted.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void insert(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);
	        IData items = IDataHelper.get(cursor, "$items", IData.class);
	        int indexBase = IDataHelper.getOrDefault(cursor, "$index.base", Integer.class, 0);
	        int index = IDataHelper.get(cursor, "$index", Integer.class) - indexBase;

	        IDataHelper.put(cursor, "$list", ListHelper.insert(list, index, IDataHelper.getLeaves(items, klass, false)), false);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Returns the last item from the given java.util.List.
	 *
	 * @param pipeline The pipeline containing the list.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void last(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();
	    int index = -1;

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);

	        boolean exists = ListHelper.exists(list, index);

	        if (exists) IDataHelper.put(cursor, "$item", ListHelper.get(list, index));
	        IDataHelper.put(cursor, "$item.exists?", exists, String.class);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Returns a new java.util.List representation of the given array.
	 *
	 * @param pipeline The pipeline containing the array to be converted to a list.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void listify(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        T[] array = (T[])IDataHelper.get(cursor, "$array", ClassHelper.getArrayClass(klass));
	        IDataHelper.put(cursor, "$list", ListHelper.listify(array, klass), false);
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Prepends the given items to the given java.util.List.
	 *
	 * @param pipeline The pipeline containing the list and items to be prepended.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void prepend(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);
	        IData items = IDataHelper.get(cursor, "$items", IData.class);

	        IDataHelper.put(cursor, "$list", ListHelper.prepend(list, IDataHelper.getLeaves(items, klass, false)));
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Removes the item from the given java.util.List at the given index.
	 *
	 * @param pipeline The pipeline containing the list, and index.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void remove(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);
	        int indexBase = IDataHelper.getOrDefault(cursor, "$index.base", Integer.class, 0);
	        int index = IDataHelper.get(cursor, "$index", Integer.class) - indexBase;

	        boolean exists = ListHelper.exists(list, index);

	        if (exists) IDataHelper.put(cursor, "$item", ListHelper.remove(list, index));
	        IDataHelper.put(cursor, "$item.exists?", BooleanHelper.emit(exists));
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Sets the item in the given java.util.List at the given index.
	 *
	 * @param pipeline The pipeline containing the list, item, and index.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void set(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);

	        if (list != null) {
	            T item = IDataHelper.get(cursor, "$item.new", klass);
	            int indexBase = IDataHelper.getOrDefault(cursor, "$index.base", Integer.class, 0);
	            int index = IDataHelper.get(cursor, "$index", Integer.class) - indexBase;

	            IDataHelper.put(cursor, "$item.old", ListHelper.set(list, index, item));
	        }
	    } finally {
	        cursor.destroy();
	    }
	}

	/**
	 * Removes and returns a specified number of items from the head of the given java.util.List.
	 *
	 * @param pipeline The pipeline containing the list, and count.
	 * @param klass    The component type of the list.
	 * @param <T>      The component type of the list.
	 */
	public static <T> void take(IData pipeline, Class<T> klass) {
	    IDataCursor cursor = pipeline.getCursor();

	    try {
	        List<T> list = (List<T>)IDataHelper.get(cursor, "$list", List.class);
	        int count = IDataHelper.get(cursor, "$count", Integer.class);

	        IDataHelper.put(cursor, "$list.head", ListHelper.take(list, count), false);
	    } finally {
	        cursor.destroy();
	    }
	}
	// --- <<IS-END-SHARED>> ---
}

