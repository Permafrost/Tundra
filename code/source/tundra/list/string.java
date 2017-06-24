package tundra.list;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2017-06-24 09:51:43 EST
// -----( ON-HOST: 192.168.66.132

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.flow.variable.SubstitutionHelper;
import permafrost.tundra.flow.variable.SubstitutionType;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.StringHelper;
import permafrost.tundra.math.IntegerHelper;
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
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    IDataHelper.put(cursor, "$list", StringHelper.blankify(list), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---

                
	}



	public static final void capitalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(capitalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:1:optional $list
		// [i] field:0:optional $mode {"all words","first word"}
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    String mode = IDataHelper.get(cursor, "$mode", String.class);
		
		    if (list != null) IDataHelper.put(cursor, "$list", StringHelper.capitalize(list, mode == null ? false : mode.equalsIgnoreCase("first word")));
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
		// [i] field:0:optional $mode {"missing","null"}
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
		// [i] record:0:optional $operands
		// [o] field:1:optional $list
		tundra.list.object.concatenate(pipeline, String.class);
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
		// [i] record:0:optional $operands
		// [o] field:0:required $equal?
		tundra.list.object.equal(pipeline, String.class);
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
		// [i] field:1:optional $list
		// [i] field:0:optional $pattern
		// [i] field:0:optional $literal? {"false","true"}
		// [o] field:0:required $found.all? {"false","true"}
		// [o] field:0:required $found.any? {"false","true"}
		// [o] field:0:required $found.none? {"false","true"}
		// [o] field:1:optional $found
		// [o] field:0:required $found.length
		// [o] field:1:optional $unfound
		// [o] field:0:required $unfound.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$literal?", Boolean.class, false);
		
		    String[][] output = find(list, pattern, literal);
		
		    if (output != null && output.length > 1) {
		        String[] found = output[0];
		        String[] unfound = output[1];
		
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
		// [i] record:0:optional $operands
		// [o] field:1:optional $list
		tundra.list.object.intersection(pipeline, String.class);
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
		// [i] field:0:optional $default
		// [i] field:0:optional $sanitization {"remove nulls","remove nulls and blanks"}
		// [o] field:0:optional $result
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
		// [i] field:0:optional $literal? {"false","true"}
		// [o] field:0:required $matched.all? {"false","true"}
		// [o] field:0:required $matched.any? {"false","true"}
		// [o] field:0:required $matched.none? {"false","true"}
		// [o] field:1:optional $matched
		// [o] field:0:required $matched.length
		// [o] field:1:optional $unmatched
		// [o] field:0:required $unmatched.length
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$literal?", Boolean.class, false);
		
		    String[][] output = match(list, pattern, literal);
		
		    if (output != null && output.length > 1) {
		        String[] matched = output[0];
		        String[] unmatched = output[1];
		
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
		    Object[] objects = IDataHelper.get(cursor, "$objects", Object[].class);
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);
		
		    String[] strings = StringHelper.normalize(objects, charset);
		
		    IDataHelper.put(cursor, "$strings", strings, false);
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] field:1:optional $list
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    IDataHelper.put(cursor, "$list", StringHelper.nullify(list), false);
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
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    int length = IDataHelper.getOrDefault(cursor, "$length", Integer.class, 0);
		    String character = IDataHelper.get(cursor, "$character", String.class);
		
		    char c = (character == null || character.length() == 0) ? ' ' : character.charAt(0);
		
		    IDataHelper.put(cursor, "$list", StringHelper.pad(list, length, c), false);
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
		// [i] field:1:optional $list
		// [i] record:1:optional $partitions
		// [i] - field:0:required name
		// [i] - field:0:required condition
		// [i] - record:0:optional scope
		// [i] record:0:optional $scope
		// [o] record:0:optional $results
		// [o] - field:1:required remainder
		// [o] - field:0:required remainder.length
		tundra.list.object.partition(pipeline);
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
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    IDataHelper.put(cursor, "$pattern", StringHelper.quote(list), false);
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
		// [o] field:0:required $list.length
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
		// [i] field:0:optional $mode {"local","global","all"}
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();
		
		try {
		    String[] list = IDataHelper.get(cursor, "$list", String[].class);
		    IData scope = IDataHelper.getOrDefault(cursor, "$pipeline", IData.class, pipeline);
		    String defaultValue = IDataHelper.get(cursor, "$default", String.class);
		    EnumSet<SubstitutionType> mode = SubstitutionType.normalize(IDataHelper.get(cursor, "$mode", String.class));
		
		    IDataHelper.put(cursor, "$list", SubstitutionHelper.substitute(list, defaultValue, mode, scope));
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
		// [i] field:1:optional $list
		// [i] field:0:required $count
		// [o] field:1:optional $head
		// [o] field:1:optional $tail
		tundra.list.object.take(pipeline);
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
	/**
	 * Returns the list of items that include and do not include the given regular expression or literal string pattern.
	 * 
	 * @param input     The array of strings to be processed.
	 * @param pattern   The pattern to look for in the given strings.
	 * @param literal   Whether the pattern is literal or a regular expression.
	 * @return          A pair of arrays, the first containing the strings that contain the pattern, the second
	 *                  contains the strings that do not contain the pattern.
	 */
	public static String[][] find(String[] input, String pattern, boolean literal) {
	    if (input == null) return null;
	
	    List<String> found = new ArrayList<String>(input.length);
	    List<String> unfound = new ArrayList<String>(input.length);
	
	    for (int i = 0; i < input.length; i++) {
	        if (StringHelper.find(input[i], pattern, literal)) {
	            found.add(input[i]);
	        } else {
	            unfound.add(input[i]);
	        }
	    }
	
	    String[][] output = new String[2][];
	    output[0] = found.toArray(new String[found.size()]);
	    output[1] = unfound.toArray(new String[unfound.size()]);
	
	    return output;
	}
	
	/**
	 * Returns the list of items that match and did not match the given regular expression or literal string pattern.
	 *
	 * @param input     The array of strings to be processed.
	 * @param pattern   The pattern to match against the given strings.
	 * @param literal   Whether the pattern is literal or a regular expression.
	 * @return          A pair of arrays, the first containing the strings that match the pattern, the second
	 *                  contains the strings that do not match the pattern.
	 */
	public static String[][] match(String[] input, String pattern, boolean literal) {
	    if (input == null) return null;
	
	    List<String> matched = new ArrayList<String>(input.length);
	    List<String> unmatched = new ArrayList<String>(input.length);
	
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
	// --- <<IS-END-SHARED>> ---
}

