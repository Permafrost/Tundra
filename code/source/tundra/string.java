package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2019-12-13T15:34:38.050
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;
import permafrost.tundra.flow.variable.SubstitutionHelper;
import permafrost.tundra.flow.variable.SubstitutionType;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataMap;
import permafrost.tundra.data.transform.string.Capitalizer;
import permafrost.tundra.data.transform.string.Condenser;
import permafrost.tundra.data.transform.string.Slicer;
import permafrost.tundra.data.transform.string.Splitter;
import permafrost.tundra.data.transform.string.Translator;
import permafrost.tundra.data.transform.string.Truncator;
import permafrost.tundra.data.transform.Transformer;
import permafrost.tundra.data.transform.TransformerMode;
import permafrost.tundra.lang.ArrayHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.LocaleHelper;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.lang.Sanitization;
import permafrost.tundra.lang.StringHelper;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.util.regex.PatternHelper;
// --- <<IS-END-IMPORTS>> ---

public final class string

{
	// ---( internal utility methods )---

	final static string _instance = new string();

	static string _newInstance() { return new string(); }

	static string _cast(Object o) { return (string)o; }

	// ---( server methods )---




	public static final void blankify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(blankify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] field:0:required $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String input = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$string", StringHelper.blankify(input));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void build (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(build)>> ---
		// @sigtype java 3.5
		// [i] object:0:optional $builder
		// [i] record:0:optional $operands
		// [i] field:0:optional $separator
		// [o] object:0:required $builder
		IDataCursor cursor = pipeline.getCursor();

		try {
		    StringBuilder builder = IDataHelper.get(cursor, "$builder", StringBuilder.class);
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    String separator = IDataHelper.get(cursor, "$separator", String.class);

		    IDataHelper.put(cursor, "$builder", StringHelper.build(builder, operands, separator), false);
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $capitalize {"all words","first word"}
		// [o] record:0:optional $results
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    String capitalize = IDataHelper.first(cursor, String.class, "$capitalize", "$mode");
		    boolean firstWordOnly = capitalize == null ? false : capitalize.equalsIgnoreCase("first word");

		    if (operands == null) {
		        String input = IDataHelper.get(cursor, "$string", String.class);
		        IDataHelper.put(cursor, "$string", StringHelper.capitalize(input, firstWordOnly), false);
		    } else {
		        IDataHelper.put(cursor, "$results", Transformer.transform(operands, new Capitalizer(TransformerMode.VALUES, firstWordOnly, true)), false);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void capture (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(capture)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:optional $pattern
		// [o] field:0:required $captured? {"false","true"}
		// [o] record:1:optional $captures
		// [o] - record:1:required groups
		// [o] -- field:0:required captured?
		// [o] -- field:0:optional index
		// [o] -- field:0:optional length
		// [o] -- field:0:optional content
		// [o] - field:0:required groups.length
		// [o] field:0:required $captures.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);

		    IData[] captures = StringHelper.capture(string, pattern);

		    if (captures != null && captures.length > 0) {
		        IDataHelper.put(cursor, "$captured?", "true");
		        IDataHelper.put(cursor, "$captures", captures);
		        IDataHelper.put(cursor, "$captures.length", captures.length, String.class);
		    } else {
		        IDataHelper.put(cursor, "$captured?", "false");
		        IDataHelper.put(cursor, "$captures.length", "0");
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void characters (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(characters)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] object:1:optional $characters
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$characters", StringHelper.characters(string), false);
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $mode {"missing","null"}
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    String mode = IDataHelper.get(cursor, "$mode", String.class);

		    if (operands == null) {
		        String x = IDataHelper.get(cursor, "$string.x", String.class);
		        String y = IDataHelper.get(cursor, "$string.y", String.class);

		        IDataMap map = new IDataMap();
		        map.put("$string.x", x);
		        map.put("$string.y", y);
		        operands = map;
		    }

		    String result = ObjectHelper.coalesce(IDataHelper.getLeaves(operands, String.class, false));

		    if (result != null || (mode != null && mode.equals("null"))) IDataHelper.put(cursor, "$string", result);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void compare (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(compare)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string.x
		// [i] field:0:optional $string.y
		// [i] field:0:optional $insensitive.case? {"false","true"}
		// [i] field:0:optional $insensitive.whitespace? {"false","true"}
		// [o] field:0:required $before? {"false","true"}
		// [o] field:0:required $equal? {"false","true"}
		// [o] field:0:required $after? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String x = IDataHelper.get(cursor, "$string.x", String.class);
		    String y = IDataHelper.get(cursor, "$string.y", String.class);
		    String insensitiveCase = IDataHelper.get(cursor, "$insensitive.case?", String.class);
		    if (insensitiveCase == null) insensitiveCase = IDataHelper.get(cursor, "$case.insensitive?", String.class);
		    boolean insensitiveWhitespace = IDataHelper.getOrDefault(cursor, "$insensitive.whitespace?", Boolean.class, false);

		    int comparison = StringHelper.compare(x, y, BooleanHelper.parse(insensitiveCase), insensitiveWhitespace);

		    IDataHelper.put(cursor, "$before?", comparison < 0, String.class);
		    IDataHelper.put(cursor, "$equal?", comparison == 0, String.class);
		    IDataHelper.put(cursor, "$after?", comparison > 0, String.class);
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
		// [i] field:0:optional $separator
		// [i] field:0:optional $sanitization {"remove nulls","remove nulls and blanks","convert nulls to blanks"}
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    String separator = IDataHelper.get(cursor, "$separator", String.class);
		    Sanitization sanitization = IDataHelper.get(cursor, "$sanitization", Sanitization.class);

		    String result = StringHelper.concatenate(operands, separator, sanitization);

		    IDataHelper.put(cursor, "$string", result, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void condense (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(condense)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $operands
		// [o] record:0:optional $results
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);

		    if (operands == null) {
		        String string = IDataHelper.get(cursor, "$string", String.class);
		        IDataHelper.put(cursor, "$string", StringHelper.condense(string), false);
		    } else {
		        IDataHelper.put(cursor, "$results", Transformer.transform(operands, new Condenser(true)), false);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void find (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(find)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {"false","true"}
		// [o] field:0:required $found? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, IDataHelper.getOrDefault(cursor, "$literal?", Boolean.class, false));

		    IDataHelper.put(cursor, "$found?", StringHelper.find(string, pattern, literalPattern), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void format (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(format)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $pattern
		// [i] field:1:optional $patterns
		// [i] record:0:optional $pipeline
		// [i] record:1:optional $list
		// [i] field:0:optional $separator
		// [i] record:1:optional $arguments
		// [i] - field:0:optional key
		// [i] - object:0:optional value
		// [i] - field:0:optional type {"string","integer","decimal","datetime"}
		// [i] - field:0:optional pattern {"datetime","datetime.db2","datetime.jdbc","date","date.jdbc","time","time.jdbc","milliseconds"}
		// [i] - field:0:optional blankify? {"false","true"}
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    String[] patterns = IDataHelper.get(cursor, "$patterns", String[].class);
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    IData[] list = IDataHelper.get(cursor, "$list", IData[].class);
		    String separator = IDataHelper.get(cursor, "$separator", String.class);
		    IData[] arguments = IDataHelper.get(cursor, "$arguments", IData[].class);
		    Locale locale = IDataHelper.getOrDefault(cursor, "$locale", Locale.class, Locale.getDefault());

		    if (patterns != null) pattern = ArrayHelper.join(patterns, null, Sanitization.REMOVE_NULLS);

		    String result = null;
		    if (pattern != null) {
		        if (list != null) {
		            result = StringHelper.format(locale, pattern, arguments, scope == null ? pipeline : scope, separator, list);
		        } else {
		            result = StringHelper.format(locale, pattern, arguments, pipeline, scope == null ? pipeline : scope);
		        }
		    }

		    IDataHelper.put(cursor, "$string", result, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void legalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(legalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String input = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$string", StringHelper.legalize(input), false);
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
		// [i] field:0:optional $string
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDataHelper.put(cursor, "$length", StringHelper.length(IDataHelper.get(cursor, "$string", String.class)), String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void lines (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(lines)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] field:1:optional $lines
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$lines", StringHelper.lines(string), false);
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
		// [i] field:0:required $key
		// [i] record:0:optional $scope
		// [o] record:0:optional $scope
		tundra.document.listify(pipeline);
		// --- <<IS-END>> ---


	}



	public static final void lowercase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(lowercase)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    Locale locale = IDataHelper.getOrDefault(cursor, "$locale", Locale.class, Locale.getDefault());

		    IDataHelper.put(cursor, "$string", StringHelper.lowercase(string, locale), false);

		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void match (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(match)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {"false","true"}
		// [o] field:0:required $match? {"false","true"}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, IDataHelper.getOrDefault(cursor, "$literal?", Boolean.class, false));

		    IDataHelper.put(cursor, "$match?", StringHelper.match(string, pattern, literalPattern), String.class);
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
		// [i] object:0:optional $object
		// [i] field:0:optional $encoding
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object object = IDataHelper.get(cursor, "$object");
		    Charset charset = IDataHelper.get(cursor, "$encoding", Charset.class);

		    IDataHelper.put(cursor, "$string", StringHelper.normalize(object, charset), false);
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
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String input = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$string", StringHelper.nullify(input));
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
		// [i] field:0:optional $string
		// [i] field:0:optional $length
		// [i] field:0:optional $character
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    int length = IDataHelper.getOrDefault(cursor, "$length", Integer.class, 0);
		    String character = IDataHelper.get(cursor, "$character", String.class);

		    char c = (character == null || character.length() == 0) ? ' ' : character.charAt(0);

		    if (string != null) {
		        IDataHelper.put(cursor, "$string", StringHelper.pad(string, length, c));
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void prefixed (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(prefixed)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:required $prefix
		// [o] field:0:required $prefixed?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    String prefix = IDataHelper.get(cursor, "$prefix", String.class);

		    boolean prefixed = string != null && string.startsWith(prefix);

		    IDataHelper.put(cursor, "$prefixed?", prefixed, String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void quote (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(quote)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] field:0:optional $pattern
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$pattern", PatternHelper.quote(string), false);
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
		// [i] field:0:optional $string
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {"false","true"}
		// [i] field:0:optional $occurrence.first? {"false","true"}
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, IDataHelper.getOrDefault(cursor, "$literal?", Boolean.class, false));
		    Boolean firstOccurrence = IDataHelper.getOrDefault(cursor, "$occurrence.first?", Boolean.class, false);

		    if (firstOccurrence == null) {
		        // support mode for backwards compatibility
		        String mode = IDataHelper.get(cursor, "$mode", String.class);
		        firstOccurrence = mode != null && mode.equals("first");
		    }

		    IDataHelper.put(cursor, "$string", StringHelper.remove(string, pattern, literalPattern, firstOccurrence), false);
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
		// [i] field:0:optional $string
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {"false","true"}
		// [i] field:0:optional $replacement
		// [i] field:0:optional $replacement.literal? {"false","true"}
		// [i] field:0:optional $occurrence.first? {"false","true"}
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, false);
		    String replacement = IDataHelper.get(cursor, "$replacement", String.class);
		    boolean literalReplacement = IDataHelper.getOrDefault(cursor, "$replacement.literal?", Boolean.class, IDataHelper.getOrDefault(cursor, "$literal?", Boolean.class, false));
		    Boolean firstOccurrence = IDataHelper.getOrDefault(cursor, "$occurrence.first?", Boolean.class, false);

		    if (firstOccurrence == null) {
		        // support mode for backwards compatibility
		        String mode = IDataHelper.get(cursor, "$mode", String.class);
		        firstOccurrence = mode != null && mode.equals("first");
		    }

		    IDataHelper.put(cursor, "$string", StringHelper.replace(string, pattern, literalPattern, replacement, literalReplacement, firstOccurrence.booleanValue()), false);
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
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String input = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$string", StringHelper.reverse(input), false);
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $index
		// [i] field:0:optional $length
		// [o] record:0:optional $results
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    int index = IDataHelper.getOrDefault(cursor, "$index", Integer.class, 0);
		    int length = IDataHelper.getOrDefault(cursor, "$length", Integer.class, index < 0 ? Integer.MIN_VALUE - index : Integer.MAX_VALUE - index);

		    if (operands == null) {
		        String string = IDataHelper.get(cursor, "$string", String.class);
		        IDataHelper.put(cursor, "$string", StringHelper.slice(string, index, length), false);
		    } else {
		        IDataHelper.put(cursor, "$results", Transformer.transform(operands, new Slicer(index, length)), false);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void split (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(split)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $operands
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {"false","true"}
		// [o] record:0:optional $results
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, IDataHelper.getOrDefault(cursor, "$literal?", Boolean.class, false));

		    if (operands == null) {
		        String string = IDataHelper.get(cursor, "$string", String.class);
		        IDataHelper.put(cursor, "$list", StringHelper.split(string, pattern, literal), false);
		    } else {
		        IDataHelper.put(cursor, "$results", Transformer.transform(operands, new Splitter(pattern, literal)), false);
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
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		tundra.string.condense(pipeline);
		// --- <<IS-END>> ---


	}



	public static final void substitute (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(substitute)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:optional $substitution.default
		// [i] field:0:optional $substitution.mode {"local","global","all"}
		// [i] record:0:optional $pipeline
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    String defaultValue = IDataHelper.first(cursor, String.class, "$substitution.default", "$default");
		    SubstitutionType mode = IDataHelper.first(cursor, SubstitutionType.class, "$substitution.mode", "$mode");
		    IData scope = IDataHelper.getOrDefault(cursor, "$pipeline", IData.class, pipeline);

		    IDataHelper.put(cursor, "$string", SubstitutionHelper.substitute(string, String.class, defaultValue, mode, scope), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void suffixed (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(suffixed)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:required $suffix
		// [o] field:0:required $suffixed?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    String suffix = IDataHelper.get(cursor, "$suffix", String.class);

		    boolean suffixed = string != null && string.endsWith(suffix);

		    IDataHelper.put(cursor, "$suffixed?", suffixed, String.class);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void translate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(translate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $operands
		// [i] record:0:optional $translations
		// [i] field:0:optional $reverse? {"false","true"}
		// [o] record:0:optional $results
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    IData translations = IDataHelper.get(cursor, "$translations", IData.class);
		    boolean reverse = IDataHelper.getOrDefault(cursor, "$reverse?", Boolean.class, false);

		    IDataHelper.put(cursor, "$results", Transformer.transform(operands, new Translator(translations, reverse)), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void trim (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(trim)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    IDataHelper.put(cursor, "$string", StringHelper.trim(string), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void truncate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(truncate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $operands
		// [i] field:0:required $length
		// [i] field:0:optional $ellipsis? {"false","true"}
		// [o] record:0:optional $results
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    int length = IDataHelper.get(cursor, "$length", Integer.class);
		    boolean ellipsis = IDataHelper.getOrDefault(cursor, "$ellipsis?", Boolean.class, false);

		    IDataHelper.put(cursor, "$results", Transformer.transform(operands, new Truncator(length, ellipsis)), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void uppercase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(uppercase)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    Locale locale = IDataHelper.getOrDefault(cursor, "$locale", Locale.class, Locale.getDefault());

		    IDataHelper.put(cursor, "$string", StringHelper.uppercase(string, locale), false);

		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void wrap (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(wrap)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] field:0:optional $string
		// [i] field:0:required $length
		// [o] field:0:optional $string
		// [o] field:1:optional $lines
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataHelper.get(cursor, "$string", String.class);
		    int length = IDataHelper.get(cursor, "$length", Integer.class);

		    String[] lines = StringHelper.wrap(string, length);

		    IDataHelper.put(cursor, "$string", ArrayHelper.join(lines, "\n"), false);
		    IDataHelper.put(cursor, "$lines", lines, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

