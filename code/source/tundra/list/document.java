package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-08-01 14:19:26 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.transform.string.Blankifier;
import permafrost.tundra.data.transform.string.Capitalizer;
import permafrost.tundra.data.transform.string.Condenser;
import permafrost.tundra.data.transform.string.Lowercaser;
import permafrost.tundra.data.transform.string.Nullifier;
import permafrost.tundra.data.transform.string.Prefixer;
import permafrost.tundra.data.transform.string.Replacer;
import permafrost.tundra.data.transform.string.Squeezer;
import permafrost.tundra.data.transform.string.Suffixer;
import permafrost.tundra.data.transform.string.Trimmer;
import permafrost.tundra.data.transform.string.Unprefixer;
import permafrost.tundra.data.transform.string.Unsuffixer;
import permafrost.tundra.data.transform.string.Uppercaser;
import permafrost.tundra.data.transform.Transformer;
import permafrost.tundra.data.transform.TransformerMode;
import permafrost.tundra.flow.variable.SubstitutionHelper;
import permafrost.tundra.flow.variable.SubstitutionType;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.StringHelper;
import permafrost.tundra.util.regex.PatternHelper;
import permafrost.tundra.util.regex.ReplacementHelper;
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
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    IDataHelper.put(cursor, "$list", Transformer.transform(list, new Blankifier(recurse)), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void capitalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(capitalize)>> ---
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $capitalize {&quot;all words&quot;,&quot;first word&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean firstWordOnly = IDataHelper.getOrDefault(cursor, "$capitalize", String.class, "all words").equals("first word");
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$list", Transformer.transform(list, new Capitalizer(mode, firstWordOnly, recurse)), false);
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



	public static final void condense (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(condense)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] input = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    if (input != null) {
		        IData[] output = Transformer.transform(input, new Condenser(mode, recurse));
		        if (output == null) output = new IData[0];
		        IDataHelper.put(cursor, "$list", output);
		    }
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



	public static final void find (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(find)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:required $key
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $found.all? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $found.any? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $found.none? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $found
		// [o] field:0:required $found.length
		// [o] record:1:optional $unfound
		// [o] field:0:required $unfound.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    boolean literalKey = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, false);

		    IData[][] output = find(list, key, literalKey, pattern, literalPattern);

		    if (output != null && output.length > 1) {
		        IData[] found = output[0];
		        IData[] unfound = output[1];

		        IDataHelper.put(cursor, "$found.all?", found.length == list.length, String.class);
		        IDataHelper.put(cursor, "$found.any?", found.length > 0, String.class);
		        IDataHelper.put(cursor, "$found.none?", found.length == 0, String.class);

		        IDataHelper.put(cursor, "$found", found);
		        IDataHelper.put(cursor, "$found.length", found.length, String.class);
		        IDataHelper.put(cursor, "$unfound", unfound);
		        IDataHelper.put(cursor, "$unfound.length", unfound.length, String.class);
		    } else {
		        IDataHelper.put(cursor, "$found.all?", "false");
		        IDataHelper.put(cursor, "$found.any?", "false");
		        IDataHelper.put(cursor, "$found.none?", "true");
		        IDataHelper.put(cursor, "$found.length", "0");
		        IDataHelper.put(cursor, "$unfound.length", "0");
		    }
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
		// [o] field:0:required $list.groups.length
		// [o] field:0:required $list.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    IData criteria = IDataHelper.get(cursor, "$group", IData.class);
		    String[] keys = IDataHelper.get(cursor, "$keys", String[].class);

		    if (list != null) {
		        if (keys != null) {
		            IData[] groups = IDataHelper.group(list, keys);
		            IDataHelper.put(cursor, "$list.grouped", groups);
		            IDataHelper.put(cursor, "$list.groups.length", groups.length, String.class);
		        } else {
		            IData[] groups = IDataHelper.group(list, criteria);
		            IDataHelper.put(cursor, "$list.groups", groups);
		            IDataHelper.put(cursor, "$list.groups.length", groups.length, String.class);
		        }
		        IDataHelper.put(cursor, "$list.length", list.length, String.class);
		    } else {
		        IDataHelper.put(cursor, "$list.groups.length", 0, String.class);
		        IDataHelper.put(cursor, "$list.length", 0, String.class);
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
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    Locale locale = IDataHelper.get(cursor, "$locale", Locale.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$list", Transformer.transform(list, new Lowercaser(mode, locale, recurse)), false);
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



	public static final void match (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(match)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:required $key
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $matched.all? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $matched.any? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $matched.none? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $matched
		// [o] field:0:required $matched.length
		// [o] record:1:optional $unmatched
		// [o] field:0:required $unmatched.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    boolean literalKey = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, false);

		    IData[][] output = match(list, key, literalKey, pattern, literalPattern);

		    if (output != null && output.length > 1) {
		        IData[] matched = output[0];
		        IData[] unmatched = output[1];

		        IDataHelper.put(cursor, "$matched.all?", matched.length == list.length, String.class);
		        IDataHelper.put(cursor, "$matched.any?", matched.length > 0, String.class);
		        IDataHelper.put(cursor, "$matched.none?", matched.length == 0, String.class);

		        IDataHelper.put(cursor, "$matched", matched);
		        IDataHelper.put(cursor, "$matched.length", matched.length, String.class);
		        IDataHelper.put(cursor, "$unmatched", unmatched);
		        IDataHelper.put(cursor, "$unmatched.length", unmatched.length, String.class);
		    } else {
		        IDataHelper.put(cursor, "$matched.all?", "false");
		        IDataHelper.put(cursor, "$matched.any?", "false");
		        IDataHelper.put(cursor, "$matched.none?", "true");
		        IDataHelper.put(cursor, "$matched.length", "0");
		        IDataHelper.put(cursor, "$unmatched.length", "0");
		    }
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
		// [i] record:1:optional $list
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
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
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    IDataHelper.put(cursor, "$list", Transformer.transform(list, new Nullifier(recurse)), false);
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



	public static final void prefix (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(prefix)>> ---
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $prefix
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $force? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String prefix = IDataHelper.get(cursor, "$prefix", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    boolean force = IDataHelper.getOrDefault(cursor, "$force?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    if (prefix != null) IDataHelper.put(cursor, "$list", Transformer.transform(list, new Prefixer(mode, prefix, force, recurse)), false);
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
		// [i] field:0:optional $pattern.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $occurrence.first? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, false);
		    boolean firstOccurrence = IDataHelper.getOrDefault(cursor, "$occurrence.first?", Boolean.class, false);
		    String occurrenceMode = IDataHelper.get(cursor, "$occurrence.mode", String.class);
		    if ("first".equals(occurrenceMode)) firstOccurrence = true;
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$list", Transformer.transform(list, new Replacer(mode, PatternHelper.compile(pattern, literalPattern), "", firstOccurrence, recurse)), false);
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
		// [i] field:0:optional $pattern.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $replacement
		// [i] field:0:optional $replacement.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $occurrence.first? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
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

		    IDataHelper.put(cursor, "$list", Transformer.transform(list, new Replacer(mode, PatternHelper.compile(pattern, literalPattern), ReplacementHelper.quote(replacement, literalReplacement), firstOccurrence, recurse)), false);
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
		// [i] - field:0:optional type {&quot;string&quot;,&quot;integer&quot;,&quot;decimal&quot;,&quot;datetime&quot;,&quot;duration&quot;}
		// [i] - field:0:optional pattern
		// [i] - field:0:optional descending? {&quot;false&quot;,&quot;true&quot;}
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
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] input = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    if (input != null) {
		        IData[] output = Transformer.transform(input, new Squeezer(recurse));
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
		// [i] field:0:optional $substitution.default
		// [i] field:0:optional $substitution.mode {&quot;local&quot;,&quot;global&quot;,&quot;all&quot;}
		// [i] record:0:optional $pipeline
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String defaultValue = IDataHelper.first(cursor, String.class, "$substitution.default", "$default");
		    SubstitutionType mode = IDataHelper.first(cursor, SubstitutionType.class, "$substitution.mode", "$mode");
		    IData scope = IDataHelper.getOrDefault(cursor, "$pipeline", IData.class, pipeline);

		    if (list != null) IDataHelper.put(cursor, "$list", SubstitutionHelper.substitute(list, defaultValue, true, false, mode, scope));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void suffix (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(suffix)>> ---
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $suffix
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $force? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String suffix = IDataHelper.get(cursor, "$suffix", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    boolean force = IDataHelper.getOrDefault(cursor, "$force?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    if (suffix != null) IDataHelper.put(cursor, "$list", Transformer.transform(list, new Suffixer(mode, suffix, force, recurse)), false);
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
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$list", Transformer.transform(list, new Trimmer(mode, recurse)), false);
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



	public static final void unprefix (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unprefix)>> ---
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $prefix
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $force? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String prefix = IDataHelper.get(cursor, "$prefix", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    if (prefix != null) IDataHelper.put(cursor, "$list", Transformer.transform(list, new Unprefixer(mode, prefix, recurse)), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void unsuffix (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unsuffix)>> ---
		// @sigtype java 3.5
		// [i] record:1:optional $list
		// [i] field:0:optional $suffix
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String suffix = IDataHelper.get(cursor, "$suffix", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    if (suffix != null) IDataHelper.put(cursor, "$list", Transformer.transform(list, new Unsuffixer(mode, suffix, recurse)), false);
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
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    Locale locale = IDataHelper.get(cursor, "$locale", Locale.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$list", Transformer.transform(list, new Uppercaser(mode, locale, recurse)), false);
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

	// --- <<IS-START-SHARED>> ---
	/**
	 * Returns the list of items that include and do not include the given regular
	 * expression or literal string pattern.
	 *
	 * @param input          The IData[] document list to be processed.
	 * @param key            The key whose value will be used for filtering.
	 * @param literalKey     Whether the key is literal or fully-qualified.
	 * @param pattern        The pattern to look for in the given documents.
	 * @param literalPattern Whether the pattern is literal or a regular expression.
	 * @return               A pair of arrays, the first containing the IData documents
	 *                       that contain the pattern, the second contains the IData
	 *                       documents that do not contain the pattern.
	 */
	public static IData[][] find(IData[] input, String key, boolean literalKey, String pattern, boolean literalPattern) {
	    if (input == null) return null;

	    List<IData> found = new ArrayList<IData>(input.length);
	    List<IData> unfound = new ArrayList<IData>(input.length);

	    for (IData document : input) {
	        String value = IDataHelper.get(document, key, literalKey, String.class);
	        if (StringHelper.find(value, pattern, literalPattern)) {
	            found.add(document);
	        } else {
	            unfound.add(document);
	        }
	    }

	    IData[][] output = new IData[2][];
	    output[0] = found.toArray(new IData[0]);
	    output[1] = unfound.toArray(new IData[0]);

	    return output;
	}

	/**
	 * Returns the list of items that match and did not match the given regular
	 * expression or literal string pattern.
	 *
	 * @param input          The IData[] document list to be processed.
	 * @param key            The key whose value will be used for filtering.
	 * @param literalKey     Whether the key is literal or fully-qualified.
	 * @param pattern        The pattern to match against the given documents.
	 * @param literalPattern Whether the pattern is literal or a regular expression.
	 * @return               A pair of arrays, the first containing the IData documents
	 *                       that match the pattern, the second contains the IData
	 *                       documents that do not match the pattern.
	 */
	public static IData[][] match(IData[] input, String key, boolean literalKey, String pattern, boolean literalPattern) {
	    if (input == null) return null;

	    List<IData> matched = new ArrayList<IData>(input.length);
	    List<IData> unmatched = new ArrayList<IData>(input.length);

	    for (IData document : input) {
	        String value = IDataHelper.get(document, key, literalKey, String.class);
	        if (StringHelper.match(value, pattern, literalPattern)) {
	            matched.add(document);
	        } else {
	            unmatched.add(document);
	        }
	    }

	    IData[][] output = new IData[2][];
	    output[0] = matched.toArray(new IData[0]);
	    output[1] = unmatched.toArray(new IData[0]);

	    return output;
	}
	// --- <<IS-END-SHARED>> ---
}

