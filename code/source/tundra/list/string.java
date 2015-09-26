package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-09-26 21:48:11 EST
// -----( ON-HOST: 192.168.66.129

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import permafrost.tundra.flow.VariableSubstitutor;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.StringHelper;
// --- <<IS-END-IMPORTS>> ---

public final class string

{
	// ---( internal utility methods )---

	final static string _instance = new string();

	static string _newInstance() { return new string(); }

	static string _cast(Object o) { return (string)o; }

	// ---( server methods )---




	public static final void append (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(append)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $item
		// [o] field:1:required $list
		tundra.list.object.append(pipeline, String.class);
		// --- <<IS-END>> ---

                
	}



	public static final void blankify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(blankify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = StringHelper.blankify(IDataUtil.getStringArray(cursor, "$list"));
		    if (list != null) IDataUtil.put(cursor, "$list", list);
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
		// [i] field:1:optional $list
		// [i] field:0:optional $default
		// [i] field:0:optional $mode {&quot;missing&quot;,&quot;null&quot;}
		// [o] field:0:optional $item
		tundra.list.object.coalesce(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void compact (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compact)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [o] field:1:optional $list
		tundra.list.object.compact(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void concatenate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(concatenate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list.x
		// [i] field:1:optional $list.y
		// [o] field:1:optional $list
		tundra.list.object.concatenate(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void difference (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(difference)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list.x
		// [i] field:1:optional $list.y
		// [o] field:1:optional $list
		tundra.list.object.difference(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void drop (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(drop)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:required $index
		// [o] field:1:optional $list
		tundra.list.object.drop(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void each (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(each)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
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
		// [i] field:1:optional $list.x
		// [i] field:1:optional $list.y
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
		// [i] field:1:optional $list
		// [i] field:0:optional $condition
		// [i] record:0:optional $scope
		// [o] field:1:optional $list
		tundra.list.object.filter(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void find (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(find)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $pattern
		// [i] field:0:optional $literal? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $found.all?
		// [o] field:0:required $found.any?
		// [o] field:0:required $found.none?
		// [o] field:1:optional $found
		// [o] field:0:required $found.length
		// [o] field:1:optional $unfound
		// [o] field:0:required $unfound.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataUtil.getStringArray(cursor, "$list");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$literal?"));
		
		    String[][] output = find(list, pattern, literal);
		
		    if (output != null && output.length > 1) {
		        String[] found = output[0];
		        String[] unfound = output[1];
		
		        IDataUtil.put(cursor, "$found.all?", "" + (found.length == list.length));
		        IDataUtil.put(cursor, "$found.any?", "" + (found.length > 0));
		        IDataUtil.put(cursor, "$found.none?", "" + (found.length == 0));
		
		        IDataUtil.put(cursor, "$found", output[0]);
		        IDataUtil.put(cursor, "$found.length", "" + found.length);
		        IDataUtil.put(cursor, "$unfound", output[1]);
		        IDataUtil.put(cursor, "$unfound.length", "" + unfound.length);
		    } else {
		        IDataUtil.put(cursor, "$found.all?", "false");
		        IDataUtil.put(cursor, "$found.any?", "false");
		        IDataUtil.put(cursor, "$found.none?", "true");
		        IDataUtil.put(cursor, "$found.length", "0");
		        IDataUtil.put(cursor, "$unfound.length", "0");
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
		// [i] field:1:optional $list
		// [o] field:0:optional $item
		tundra.list.object.first(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void get (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(get)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $index
		// [i] field:0:optional $iteration
		// [o] field:0:optional $item
		tundra.list.object.get(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void grow (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(grow)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $item
		// [i] field:0:required $count
		// [o] field:1:optional $list
		tundra.list.object.grow(pipeline, String.class);
		// --- <<IS-END>> ---

                
	}



	public static final void include (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(include)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $item
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
		// [i] field:1:optional $list
		// [i] field:0:optional $item
		// [i] field:0:required $index
		// [o] field:1:required $list
		tundra.list.object.insert(pipeline, String.class);
		// --- <<IS-END>> ---

                
	}



	public static final void intersection (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(intersection)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list.x
		// [i] field:1:optional $list.y
		// [o] field:1:optional $list
		tundra.list.object.intersection(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void join (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(join)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $separator
		// [o] field:0:required $result
		tundra.list.object.join(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void last (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(last)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [o] field:0:optional $item
		tundra.list.object.last(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void length (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(length)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
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
		// [i] field:1:optional $list
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $item.input
		// [i] field:0:optional $item.output
		// [o] field:1:optional $list
		tundra.list.object.map(pipeline, String.class);
		// --- <<IS-END>> ---

                
	}



	public static final void match (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(match)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $pattern
		// [i] field:0:optional $literal? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $matched.all?
		// [o] field:0:required $matched.any?
		// [o] field:0:required $matched.none?
		// [o] field:1:optional $matched
		// [o] field:0:required $matched.length
		// [o] field:1:optional $unmatched
		// [o] field:0:required $unmatched.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataUtil.getStringArray(cursor, "$list");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$literal?"));
		
		    String[][] output = match(list, pattern, literal);
		
		    if (output != null && output.length > 1) {
		        String[] matched = output[0];
		        String[] unmatched = output[1];
		
		        IDataUtil.put(cursor, "$matched.all?", "" + (matched.length == list.length));
		        IDataUtil.put(cursor, "$matched.any?", "" + (matched.length > 0));
		        IDataUtil.put(cursor, "$matched.none?", "" + (matched.length == 0));
		
		        IDataUtil.put(cursor, "$matched", output[0]);
		        IDataUtil.put(cursor, "$matched.length", "" + matched.length);
		        IDataUtil.put(cursor, "$unmatched", output[1]);
		        IDataUtil.put(cursor, "$unmatched.length", "" + unmatched.length);
		    } else {
		        IDataUtil.put(cursor, "$matched.all?", "false");
		        IDataUtil.put(cursor, "$matched.any?", "false");
		        IDataUtil.put(cursor, "$matched.none?", "true");
		        IDataUtil.put(cursor, "$matched.length", "0");
		        IDataUtil.put(cursor, "$unmatched.length", "0");
		    }
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
		// [i] object:1:optional $objects
		// [i] field:0:optional $encoding
		// [o] field:1:optional $strings
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    Object[] objects = IDataUtil.getObjectArray(cursor, "$objects");
		    String encoding = IDataUtil.getString(cursor, "$encoding");
		
		    String[] strings = StringHelper.normalize(objects, encoding);
		
		    if (strings != null) IDataUtil.put(cursor, "$strings", strings);
		} catch(java.io.IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void pad (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(pad)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $length
		// [i] field:0:optional $character
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataUtil.getStringArray(cursor, "$list");
		    String length = IDataUtil.getString(cursor, "$length");
		    String character = IDataUtil.getString(cursor, "$character");
		
		    int len = length == null ? 0 : Integer.parseInt(length);
		    char c = (character == null || character.length() == 0) ? ' ' : character.charAt(0);
		
		    if (list != null) IDataUtil.put(cursor, "$list", StringHelper.pad(list, len, c));
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
		// [i] field:1:optional $list
		// [i] field:0:optional $item
		// [o] field:1:required $list
		tundra.list.object.prepend(pipeline, String.class);
		// --- <<IS-END>> ---

                
	}



	public static final void put (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(put)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $item
		// [i] field:0:required $index
		// [o] field:1:required $list
		tundra.list.object.put(pipeline, String.class);
		// --- <<IS-END>> ---

                
	}



	public static final void quote (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(quote)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [o] field:0:optional $pattern
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataUtil.getStringArray(cursor, "$list");
		    if (list != null) IDataUtil.put(cursor, "$pattern", StringHelper.quote(list));
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
		// [i] field:1:optional $list
		// [i] field:0:optional $condition
		// [i] record:0:optional $scope
		// [o] field:1:optional $list
		tundra.list.object.reject(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void resize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(resize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $item
		// [i] field:0:required $length
		// [o] field:1:optional $list
		tundra.list.object.resize(pipeline, String.class);
		// --- <<IS-END>> ---

                
	}



	public static final void reverse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(reverse)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [o] field:1:optional $list
		tundra.list.object.reverse(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void shrink (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(shrink)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:required $count
		// [o] field:1:optional $list
		tundra.list.object.shrink(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void slice (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(slice)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $index
		// [i] field:0:optional $length
		// [o] field:1:optional $list
		tundra.list.object.slice(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void sort (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sort)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [o] field:1:optional $list
		tundra.list.object.sort(pipeline);
		// --- <<IS-END>> ---

                
	}



	public static final void substitute (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(substitute)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $default
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataUtil.getStringArray(cursor, "$list");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    String defaultValue = IDataUtil.getString(cursor, "$default");
		
		    IDataUtil.put(cursor, "$list", VariableSubstitutor.substitute(list, defaultValue, scope == null ? pipeline : scope));
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
		// [i] field:1:optional $list
		// [o] field:1:optional $list
		tundra.list.object.unique(pipeline);
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	// returns the list of items that match and did not match the given regular 
	// expression or literal string pattern
	public static String[][] match(String[] input, String pattern, boolean literal) {
	    if (input == null) return null;
	
	    java.util.List<String> matched = new java.util.ArrayList<String>(input.length);
	    java.util.List<String> unmatched = new java.util.ArrayList<String>(input.length);
	
	    for (int i = 0; i < input.length; i++) {
	        if (StringHelper.match(input[i], pattern, literal)) {
	            matched.add(input[i]);
	        } else {
	            unmatched.add(input[i]);
	        }
	    }
	
	    String[][] output = new String[2][];
	    output[0] = matched.toArray(new String[0]);
	    output[1] = unmatched.toArray(new String[0]);
	
	    return output;
	}
	
	// returns the list of items that include and do not include the given regular 
	// expression or literal string pattern
	public static String[][] find(String[] input, String pattern, boolean literal) {
	    if (input == null) return null;
	
	    java.util.List<String> found = new java.util.ArrayList<String>(input.length);
	    java.util.List<String> unfound = new java.util.ArrayList<String>(input.length);
	
	    for (int i = 0; i < input.length; i++) {
	        if (StringHelper.find(input[i], pattern, literal)) {
	            found.add(input[i]);
	        } else {
	            unfound.add(input[i]);
	        }
	    }
	
	    String[][] output = new String[2][];
	    output[0] = found.toArray(new String[0]);
	    output[1] = unfound.toArray(new String[0]);
	
	    return output;
	}
	// --- <<IS-END-SHARED>> ---
}

