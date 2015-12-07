package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2015-12-08 08:22:05.368
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.IOException;
import permafrost.tundra.flow.VariableSubstitutor;
import permafrost.tundra.lang.ArrayHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.LocaleHelper;
import permafrost.tundra.lang.StringHelper;
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
		    String input = IDataUtil.getString(cursor, "$string");
		    IDataUtil.put(cursor, "$string", StringHelper.blankify(input));
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
		// [i] field:0:optional $string
		// [i] field:0:optional $mode {&quot;all words&quot;,&quot;first word&quot;}
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String input = IDataUtil.getString(cursor, "$string");
		    String mode = IDataUtil.getString(cursor, "$mode");

		    if (input != null) IDataUtil.put(cursor, "$string", StringHelper.capitalize(input, mode == null ? false : mode.equalsIgnoreCase("first word")));
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
		// [o] field:0:required $captured?
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
		    String string = IDataUtil.getString(cursor, "$string");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$literal?"));

		    IData[] captures = StringHelper.capture(string, pattern);

		    if (captures != null && captures.length > 0) {
		        IDataUtil.put(cursor, "$captured?", "true");
		        IDataUtil.put(cursor, "$captures", captures);
		        IDataUtil.put(cursor, "$captures.length", "" + captures.length);
		    } else {
		        IDataUtil.put(cursor, "$captured?", "false");
		        IDataUtil.put(cursor, "$captures.length", "0");
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
		    String string = IDataUtil.getString(cursor, "$string");
		    if (string != null) IDataUtil.put(cursor, "$characters", StringHelper.characters(string));
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
		// [i] field:0:optional $string.x
		// [i] field:0:optional $string.y
		// [i] field:0:optional $mode {&quot;missing&quot;,&quot;null&quot;}
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String x = IDataUtil.getString(cursor, "$string.x");
		    String y = IDataUtil.getString(cursor, "$string.y");
		    String mode = IDataUtil.getString(cursor, "$mode");

		    String result = tundra.object.coalesce(x, y);

		    if (result != null || (mode != null && mode.equals("null"))) IDataUtil.put(cursor, "$string", result);
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
		// [i] field:0:optional $insensitive.case? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $insensitive.whitespace? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $before?
		// [o] field:0:required $equal?
		// [o] field:0:required $after?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String x = IDataUtil.getString(cursor, "$string.x");
		    String y = IDataUtil.getString(cursor, "$string.y");
		    String insensitiveCase = IDataUtil.getString(cursor, "$insensitive.case?");
		    if (insensitiveCase == null) insensitiveCase = IDataUtil.getString(cursor, "$case.insensitive?");
		    String insensitiveWhitespace = IDataUtil.getString(cursor, "$insensitive.whitespace?");

		    int comparison = StringHelper.compare(x, y, BooleanHelper.parse(insensitiveCase), BooleanHelper.parse(insensitiveWhitespace));

		    IDataUtil.put(cursor, "$before?", BooleanHelper.emit(comparison < 0));
		    IDataUtil.put(cursor, "$equal?", BooleanHelper.emit(comparison == 0));
		    IDataUtil.put(cursor, "$after?", BooleanHelper.emit(comparison > 0));
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
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData operands = IDataUtil.getIData(cursor, "$operands");
		    String separator = IDataUtil.getString(cursor, "$separator");

		    String result = StringHelper.concatenate(operands, separator);

		    if (result != null) IDataUtil.put(cursor, "$string", result);
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
		// [i] field:0:optional $literal? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $found?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataUtil.getString(cursor, "$string");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$literal?"));

		    IDataUtil.put(cursor, "$found?", "" + StringHelper.find(string, pattern, literal));
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
		// [i] - field:0:optional type {&quot;string&quot;,&quot;integer&quot;,&quot;decimal&quot;,&quot;datetime&quot;}
		// [i] - field:0:optional pattern {&quot;datetime&quot;,&quot;datetime.db2&quot;,&quot;datetime.jdbc&quot;,&quot;date&quot;,&quot;date.jdbc&quot;,&quot;time&quot;,&quot;time.jdbc&quot;,&quot;milliseconds&quot;}
		// [i] - field:0:optional blankify? {&quot;false&quot;,&quot;true&quot;}
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String[] patterns = IDataUtil.getStringArray(cursor, "$patterns");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");
		    IData[] list = IDataUtil.getIDataArray(cursor, "$list");
		    String separator = IDataUtil.getString(cursor, "$separator");
		    IData[] arguments = IDataUtil.getIDataArray(cursor, "$arguments");
		    IData locale = IDataUtil.getIData(cursor, "$locale");

		    if (patterns != null) pattern = ArrayHelper.join(patterns, null, false);

		    String result = null;
		    if (pattern != null) {
		        if (list != null) {
		            result = StringHelper.format(LocaleHelper.toLocale(locale), pattern, arguments, scope == null ? pipeline : scope, separator, list);
		        } else {
		            result = StringHelper.format(LocaleHelper.toLocale(locale), pattern, arguments, pipeline, scope == null ? pipeline : scope);
		        }
		    }

		    if (result != null) IDataUtil.put(cursor, "$string", result);
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
		    IDataUtil.put(cursor, "$length", "" + StringHelper.length(IDataUtil.getString(cursor, "$string")));
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
		    String string = IDataUtil.getString(cursor, "$string");
		    if (string != null) IDataUtil.put(cursor, "$lines", StringHelper.lines(string));
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
		    String string = IDataUtil.getString(cursor, "$string");
		    IData document = IDataUtil.getIData(cursor, "$locale");

		    if (string != null) IDataUtil.put(cursor, "$string", string.toLowerCase(LocaleHelper.toLocale(document)));

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
		// [i] field:0:optional $literal? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $match?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataUtil.getString(cursor, "$string");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$literal?"));

		    IDataUtil.put(cursor, "$match?", "" + StringHelper.match(string, pattern, literal));
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
		    Object object = IDataUtil.get(cursor, "$object");
		    String encoding = IDataUtil.getString(cursor, "$encoding");

		    if (object != null) IDataUtil.put(cursor, "$string", StringHelper.normalize(object, encoding));
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
		    String input = IDataUtil.getString(cursor, "$string");
		    IDataUtil.put(cursor, "$string", StringHelper.nullify(input));
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
		    String string = IDataUtil.getString(cursor, "$string");
		    String length = IDataUtil.getString(cursor, "$length");
		    String character = IDataUtil.getString(cursor, "$character");

		    int len = length == null ? 0 : Integer.parseInt(length);
		    char c = (character == null || character.length() == 0) ? ' ' : character.charAt(0);

		    if (string != null) IDataUtil.put(cursor, "$string", StringHelper.pad(string, len, c));
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
		    String string = IDataUtil.getString(cursor, "$string");
		    if (string != null) IDataUtil.put(cursor, "$pattern", StringHelper.quote(string));
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
		// [i] field:0:optional $mode {&quot;all&quot;,&quot;first&quot;}
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataUtil.getString(cursor, "$string");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String mode = IDataUtil.getString(cursor, "$mode");

		    if (string != null) IDataUtil.put(cursor, "$string", StringHelper.remove(string, pattern, (!(mode == null || mode.equals("all")))));
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
		// [i] field:0:optional $replacement
		// [i] field:0:optional $literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;all&quot;,&quot;first&quot;}
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataUtil.getString(cursor, "$string");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    String replacement = IDataUtil.getString(cursor, "$replacement");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$literal?"));
		    String mode = IDataUtil.getString(cursor, "$mode");

		    if (string != null) IDataUtil.put(cursor, "$string", StringHelper.replace(string, pattern, replacement, literal, (!(mode == null || mode.equals("all")))));
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
		// [i] field:0:optional $string
		// [i] field:0:optional $index
		// [i] field:0:optional $length
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataUtil.getString(cursor, "$string");
		    String index = IDataUtil.getString(cursor, "$index");
		    String length = IDataUtil.getString(cursor, "$length");

		    int ix = index == null ? 0 : Integer.parseInt(index);
		    int len = length == null ? (string == null ? 0 : (ix < 0 ? -string.length() : string.length())) : Integer.parseInt(length);

		    if (string != null) IDataUtil.put(cursor, "$string", StringHelper.slice(string, ix, len));
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
		// [i] field:0:optional $string
		// [i] field:0:optional $pattern
		// [i] field:0:optional $literal? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataUtil.getString(cursor, "$string");
		    String pattern = IDataUtil.getString(cursor, "$pattern");
		    boolean literal = BooleanHelper.parse(IDataUtil.getString(cursor, "$literal?"));

		    IDataUtil.put(cursor, "$list", StringHelper.split(string, pattern, literal));
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
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = StringHelper.squeeze(IDataUtil.getString(cursor, "$string"));
		    if (string != null) IDataUtil.put(cursor, "$string", string);
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
		// [i] field:0:optional $string
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $default
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String string = IDataUtil.getString(cursor, "$string");
		    String defaultValue = IDataUtil.getString(cursor, "$default");
		    IData scope = IDataUtil.getIData(cursor, "$pipeline");

		    IDataUtil.put(cursor, "$string", VariableSubstitutor.substitute(string, defaultValue, scope == null ? pipeline : scope));
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
		    String string = StringHelper.trim(IDataUtil.getString(cursor, "$string"));
		    if (string != null) IDataUtil.put(cursor, "$string", string);
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
		    String string = IDataUtil.getString(cursor, "$string");
		    IData document = IDataUtil.getIData(cursor, "$locale");

		    if (string != null) IDataUtil.put(cursor, "$string", string.toUpperCase(LocaleHelper.toLocale(document)));

		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

