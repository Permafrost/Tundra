package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2016-03-13 15:05:50 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.EnumSet;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.flow.variable.SubstitutionHelper;
import permafrost.tundra.flow.variable.SubstitutionType;
import permafrost.tundra.lang.BooleanHelper;
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



	public static final void blankify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(blankify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    IData[] list = IDataHelper.blankify(IDataUtil.getIDataArray(cursor, "$list"), recurse);
		    if (list != null) IDataUtil.put(cursor, "$list", list);
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
		// [i] record:1:optional $list
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    if (list != null) IDataUtil.put(cursor, "$list", IDataHelper.compact(list, recurse));
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
		// [o] record:1:optional $list
		tundra.list.object.concatenate(pipeline, IData.class);
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
		// [i] record:0:optional $operands
		// [o] field:0:required $equal?
		tundra.list.object.equal(pipeline, IData.class);
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
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		    IData criteria = IDataUtil.getIData(cursor, "$group");
		    String[] keys = IDataUtil.getStringArray(cursor, "$keys");
		
		    if (list != null) {
		        if (keys != null) {
		            IDataUtil.put(cursor, "$list.grouped", IDataHelper.group(list, keys));
		        } else {
		            IDataUtil.put(cursor, "$list.groups", IDataHelper.group(list, criteria));
		        }
		    }
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
		
		    IDataUtil.put(cursor, "$keys", IDataHelper.getKeys(list, pattern));
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



	public static final void normalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(normalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.normalize(IDataUtil.getIDataArray(cursor, "$list"));
		    if (list != null) IDataUtil.put(cursor, "$list", list);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void nullify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(nullify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    IData[] list = IDataHelper.nullify(IDataUtil.getIDataArray(cursor, "$list"), recurse);
		    if (list != null) IDataUtil.put(cursor, "$list", list);
		} finally {
		    cursor.destroy();
		}
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
		            IDataUtil.put(cursor, "$document", IDataHelper.pivot(list, delimiter, keys));
		        } else {
		            IDataUtil.put(cursor, "$document", IDataHelper.pivot(list, delimiter, key));
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



	public static final void reject (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reject)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $condition
		// [i] record:0:optional $scope
		// [o] record:1:optional $list
		tundra.list.object.reject(pipeline);
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
		    boolean ascending = BooleanHelper.parse(sAscending, true);
		
		    if (list != null) {
		        if (criteria == null) {
		            if (keys == null) {
		                IDataUtil.put(cursor, "$list", IDataHelper.sort(list, key, ascending));
		            } else {
		                IDataUtil.put(cursor, "$list", IDataHelper.sort(list, keys, ascending));
		            }
		        } else {
		            IDataUtil.put(cursor, "$list", IDataHelper.sort(list, criteria));
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
		    boolean recurse = BooleanHelper.parse(IDataUtil.getString(cursor, "$recurse?"));
		    IData[] list = IDataHelper.squeeze(IDataUtil.getIDataArray(cursor, "$list"), recurse);
		    IDataUtil.put(cursor, "$list", list == null ? new IData[0] : list);
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
		// [i] record:1:optional $list
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $default
		// [i] field:0:optional $mode {&quot;local&quot;,&quot;global&quot;,&quot;all&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    String defaultValue = IDataUtil.getString(cursor, "$default");
		    EnumSet<SubstitutionType> mode = SubstitutionType.normalize(IDataUtil.getString(cursor, "$mode"));
		
		    if (list != null) IDataUtil.put(cursor, "$list", SubstitutionHelper.substitute(list, defaultValue, scope == null ? pipeline : scope, true, mode));
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
		// [i] record:1:optional $list
		// [i] field:0:required $count
		// [o] record:1:optional $head
		// [o] record:1:optional $tail
		tundra.list.object.take(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void unique (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unique)>> ---
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:1:optional $keys
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		    String[] keys = IDataUtil.getStringArray(cursor, "$keys");
		
		    if (list != null) IDataUtil.put(cursor, "$list", IDataHelper.unique(list, keys));
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
		
		    Object[] values = IDataHelper.getValues(list, key, defaultObject);
		
		    if (values != null) IDataUtil.put(cursor, "$values", values);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

