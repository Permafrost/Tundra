package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-07-16 15:13:27 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.EnumSet;
import java.util.Locale;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.transform.TransformerMode;
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
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$list", IDataHelper.blankify(list, recurse), false);
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
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] input = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    if (input != null) {
		        IData[] output = IDataHelper.compact(input, recurse);
		        if (output == null) output = new IData[0];
		        IDataHelper.put(cursor, "$list", output);
		    }
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
		// [o] field:0:required $list.length
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



	public static final void flip (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(flip)>> ---
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    IDataHelper.put(cursor, "$list", IDataHelper.flip(list), false);
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
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    IData criteria = IDataHelper.get(cursor, "$group", IData.class);
		    String[] keys = IDataHelper.get(cursor, "$keys", String[].class);
		
		    if (list != null) {
		        if (keys != null) {
		            IDataHelper.put(cursor, "$list.grouped", IDataHelper.group(list, keys));
		        } else {
		            IDataHelper.put(cursor, "$list.groups", IDataHelper.group(list, criteria));
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
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		
		    IDataHelper.put(cursor, "$keys", IDataHelper.getKeys(list, pattern));
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



	public static final void lowercase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(lowercase)>> ---
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [i] field:0:optional $recurse? {"false","true"}
		// [i] field:0:optional $mode {"values","keys","keys and values"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    Locale locale = IDataHelper.get(cursor, "$locale", Locale.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);
		
		    IDataHelper.put(cursor, "$list", IDataHelper.lowercase(list, locale, mode, recurse), false);
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
		// [i] record:1:optional $list
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $item.input
		// [i] field:0:optional $item.output
		// [o] record:1:optional $list
		tundra.list.object.map(pipeline, IData.class);
		// --- <<IS-END>> ---

                
	}



	public static final void merge (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(merge)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:0:required $document
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$document", IDataHelper.merge(list, recurse));
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
		// [i] record:1:optional $list
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    IDataHelper.put(cursor, "$list", IDataHelper.normalize(list), false);
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
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    IDataHelper.put(cursor, "$list", IDataHelper.nullify(list, recurse), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void partition (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(partition)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:1:optional $partitions
		// [i] - field:0:required name
		// [i] - field:0:required condition
		// [i] - record:0:optional scope
		// [i] record:0:optional $scope
		// [o] record:0:optional $results
		// [o] - record:1:optional remainder
		// [o] - field:0:required remainder.length
		tundra.list.object.partition(pipeline);
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
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String[] keys = IDataHelper.get(cursor, "$keys", String[].class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    String delimiter = IDataHelper.get(cursor, "$delimiter", String.class);
		
		    if (list != null) {
		        if (keys != null) {
		            IDataHelper.put(cursor, "$document", IDataHelper.pivot(list, delimiter, keys));
		        } else {
		            IDataHelper.put(cursor, "$document", IDataHelper.pivot(list, delimiter, key));
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
		// [o] field:0:required $list.length
		tundra.list.object.reject(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void remove (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(remove)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {"false","true"}
		// [i] field:0:optional $occurrence.first? {"false","true"}
		// [i] field:0:optional $recurse? {"false","true"}
		// [i] field:0:optional $mode {"values","keys","keys and values"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, false);
		    boolean firstOccurrence = IDataHelper.getOrDefault(cursor, "$occurrence.first?", Boolean.class, false);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);
		
		    IDataHelper.put(cursor, "$list", IDataHelper.remove(list, pattern, literalPattern, firstOccurrence, mode, recurse), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void replace (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(replace)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {"false","true"}
		// [i] field:0:optional $replacement
		// [i] field:0:optional $replacement.literal? {"false","true"}
		// [i] field:0:optional $occurrence.first? {"false","true"}
		// [i] field:0:optional $recurse? {"false","true"}
		// [i] field:0:optional $mode {"values","keys","keys and values"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, false);
		    String replacement = IDataHelper.get(cursor, "$replacement", String.class);
		    boolean literalReplacement = IDataHelper.getOrDefault(cursor, "$replacement.literal?", Boolean.class, false);
		    boolean firstOccurrence = IDataHelper.getOrDefault(cursor, "$occurrence.first?", Boolean.class, false);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);
		
		    IDataHelper.put(cursor, "$list", IDataHelper.replace(list, pattern, literalPattern, replacement, literalReplacement, firstOccurrence, mode, recurse), false);
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
		// [i] - field:0:optional type {"string","integer","decimal","datetime","duration"}
		// [i] - field:0:optional pattern
		// [i] - field:0:optional descending? {"false","true"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    IData[] criteria = IDataHelper.get(cursor, "$criteria", IData[].class);
		
		    // silently support $key, $keys and $ascending? for backwards compatibility
		    String[] keys = IDataHelper.get(cursor, "$keys", String[].class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    boolean ascending = IDataHelper.getOrDefault(cursor, "$ascending?", Boolean.class, true);
		
		    if (list != null) {
		        if (criteria == null) {
		            if (keys == null) {
		                IDataHelper.put(cursor, "$list", IDataHelper.sort(list, key, ascending));
		            } else {
		                IDataHelper.put(cursor, "$list", IDataHelper.sort(list, keys, ascending));
		            }
		        } else {
		            IDataHelper.put(cursor, "$list", IDataHelper.sort(list, criteria));
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
		// [i] field:0:optional $recurse? {"false","true"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] input = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		
		    if (input != null) {
		        IData[] output = IDataHelper.squeeze(input, recurse);
		        if (output == null) output = new IData[0];
		        IDataHelper.put(cursor, "$list", output);
		    }
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
		// [i] field:0:optional $mode {"local","global","all"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    IData scope = IDataHelper.getOrDefault(cursor, "$pipeline", IData.class, pipeline);
		    String defaultValue = IDataHelper.get(cursor, "$default", String.class);
		    EnumSet<SubstitutionType> mode = SubstitutionType.normalize(IDataHelper.get(cursor, "$mode", String.class));
		
		    if (list != null) IDataHelper.put(cursor, "$list", SubstitutionHelper.substitute(list, defaultValue, true, mode, scope));
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



	public static final void trim (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(trim)>> ---
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $recurse? {"false","true"}
		// [i] field:0:optional $mode {"values","keys","keys and values"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);
		
		    IDataHelper.put(cursor, "$list", IDataHelper.trim(list, mode, recurse), false);
		} finally {
		    cursor.destroy();
		}
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
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String[] keys = IDataHelper.get(cursor, "$keys", String[].class);
		
		    if (list != null) IDataHelper.put(cursor, "$list", IDataHelper.unique(list, keys));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void uppercase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(uppercase)>> ---
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [i] field:0:optional $recurse? {"false","true"}
		// [i] field:0:optional $mode {"values","keys","keys and values"}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    Locale locale = IDataHelper.get(cursor, "$locale", Locale.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);
		
		    IDataHelper.put(cursor, "$list", IDataHelper.uppercase(list, locale, mode, recurse), false);
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
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    Object defaultObject = IDataHelper.get(cursor, "$default.object");
		    if (defaultObject == null) defaultObject = IDataHelper.get(cursor, "$default.string", String.class);
		
		    Object[] values = IDataHelper.getValues(list, key, defaultObject);
		
		    IDataHelper.put(cursor, "$values", values, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}
}

