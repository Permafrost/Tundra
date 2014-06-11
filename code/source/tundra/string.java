package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2014-06-11 15:34:17.364
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
// --- <<IS-END-IMPORTS>> ---

public final class string

{
	// ---( internal utility methods )---

	final static string _instance = new string();

	static string _newInstance() { return new string(); }

	static string _cast(Object o) { return (string)o; }

	// ---( server methods )---




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
		  IDataUtil.put(cursor, "$length", "" + length(IDataUtil.getString(cursor, "$string")));
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
		  IDataUtil.put(cursor, "$lines", lines(string));
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
		tundra.object.listify(pipeline, String.class);
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
		  if (string != null) {
		    IDataUtil.put(cursor, "$string", string.toLowerCase(locale(document)));
		  }
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
		// [o] field:0:required $match?
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  String pattern = IDataUtil.getString(cursor, "$pattern");

		  IDataUtil.put(cursor, "$match?", "" + match(string, pattern));
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

		  IDataUtil.put(cursor, "$string", normalize(object, encoding));
		} catch(java.io.IOException ex) {
		  tundra.exception.raise(ex);
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
		// [i] field:0:required $string
		// [o] field:0:required $pattern
		IDataCursor cursor = pipeline.getCursor();

		try {
		  IDataUtil.put(cursor, "$pattern", quote(IDataUtil.getString(cursor, "$string")));
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
		// [o] field:0:optional $string
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		  String replacement = IDataUtil.getString(cursor, "$replacement");
		  boolean literal = Boolean.parseBoolean(IDataUtil.getString(cursor, "$literal?"));

		  IDataUtil.put(cursor, "$string", replace(string, pattern, replacement, literal));
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

		  if (string != null) IDataUtil.put(cursor, "$string", slice(string, index, length));
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
		// [o] field:1:optional $list
		IDataCursor cursor = pipeline.getCursor();

		try {
		  String string = IDataUtil.getString(cursor, "$string");
		  String pattern = IDataUtil.getString(cursor, "$pattern");
		  IDataUtil.put(cursor, "$list", split(string, pattern));
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
		  String string = IDataUtil.getString(cursor, "$string");
		  IDataUtil.put(cursor, "$string", squeeze(string));
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

		  IDataUtil.put(cursor, "$string", substitute(string, scope == null ? pipeline : scope, defaultValue));
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
		  IDataUtil.put(cursor, "$string", trim(IDataUtil.getString(cursor, "$string")));
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
		  if (string != null) {
		    IDataUtil.put(cursor, "$string", string.toUpperCase(locale(document)));
		  }
		} finally {
		  cursor.destroy();
		}
		// --- <<IS-END>> ---


	}

	// --- <<IS-START-SHARED>> ---
	// converts a byte array, input stream or string to a string
	public static String normalize(Object object, String encoding) throws java.io.IOException {
	  if (encoding == null) encoding = tundra.support.constant.DEFAULT_CHARACTER_ENCODING;

	  String string = null;

	  if (object != null) {
	    if (object instanceof String) {
	      string = (String)object;
	    } else if (object instanceof byte[]) {
	      string = new String((byte[])object, encoding);
	    } else if (object instanceof java.io.InputStream) {
	      java.io.Writer writer = new java.io.StringWriter();
	      tundra.stream.copy(new java.io.InputStreamReader((java.io.InputStream)object, encoding), writer);
	      string = writer.toString();
	    } else {
	      throw new IllegalArgumentException("object must be a string, byte[] or java.io.InputStream: " + object.getClass().getName());
	    }
	  }

	  return string;
	}

	// converts a byte array, input stream or string to a string
	public static String normalize(Object object) throws java.io.IOException {
	  return normalize(object, tundra.support.constant.DEFAULT_CHARACTER_ENCODING);
	}

	// returns a new java.util.Locale object for the given language, country and variant
	public static java.util.Locale locale(String language, String country, String variant) {
	  java.util.Locale locale = java.util.Locale.getDefault();

	  if (language != null) {
	    if (country == null) {
	      locale = new java.util.Locale(language);
	    } else if (variant == null) {
	      locale = new java.util.Locale(language, country);
	    } else {
	      locale = new java.util.Locale(language, country, variant);
	    }
	  }

	  return locale;
	}

	// converts an IData locale object to a java.util.Locale object
	public static java.util.Locale locale(IData document) {
	  String language = null, country = null, variant = null;

	  if (document != null) {
	    IDataCursor cursor = document.getCursor();
	    language = IDataUtil.getString(cursor, "language");
	    country = IDataUtil.getString(cursor, "country");
	    variant = IDataUtil.getString(cursor, "variant");
	    cursor.destroy();
	  }

	  return locale(language, country, variant);
	}

	// returns the given string with leading and trailing whitespace removed
	public static String trim(String input) {
	  String output = null;
	  if (input != null) output = input.trim();
	  return output;
	}

	// returns the length (number of characters) of the string
	public static int length(String input) {
	  int length = 0;
	  if (input != null) length = input.length();
	  return length;
	}


	// returns a substring starting at the given index for the given length
	public static String slice(String input, String index, String length) {
	  return slice(input, index == null ? 0 : Integer.parseInt(index), length == null ? (input == null ? 0 : input.length()) : Integer.parseInt(length));
	}

	// returns a substring starting at the given index for the given length
	public static String slice(String input, int index, int length) {
	  if (input == null || input.equals("")) return input;

	  String output = "";
	  int inputLength = input.length();

	  // support reverse indexing
	  if (index < 0) index += inputLength;

	  if (index < inputLength) {
	    if ((index + length) > inputLength) length = inputLength - index;

	    output = input.substring(index, index + length);
	  }

	  return output;
	}

	// returns true if the given regular expression is found in the given string
	public static boolean match(String input, String regex) {
	  boolean match = false;
	  if (input != null && regex != null) match = input.matches(regex);
	  return match;
	}

	// replaces all occurrences of the given regular expression in the given string with the given replacement
	public static String replace(String input, String regex, String replacement, boolean literal) {
	  String output = input;
	  if (input != null && regex != null && replacement != null) {
	    if (literal) replacement = java.util.regex.Matcher.quoteReplacement(replacement);
	    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
	    java.util.regex.Matcher matcher = pattern.matcher(input);
	    output = matcher.replaceAll(replacement);
	  }
	  return output;
	}

	// splits a string around each match of the given regular expression pattern
	public static String[] split(String input, String regex) {
	  String[] output = null;
	  if (input != null && regex != null) {
	    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
	    output = pattern.split(input);
	  }
	  return output;
	}

	// returns all the lines in the given string as an array
	public static String[] lines(String input) {
	  return split(input, "\n");
	}

	// replaces runs of whitespace characters with a single space
	public static String squeeze(String input) {
	  return replace(input, "\\s+", " ", false);
	}

	public static final java.util.regex.Pattern SUBSTITUTION_PATTERN = java.util.regex.Pattern.compile("%([^%]+)%");

	// performs variable substitution on the given string by replacing all occurrences of
	// substrings matching "%key%" with the associated value from the given scope
	public static String substitute(String input, IData scope) {
	  return substitute(input, scope, null);
	}

	// performs variable substitution on the given string by replacing all occurrences of
	// substrings matching "%key%" with the associated value from the given scope; if
	// the key has no value, the given defaultValue is used instead
	public static String substitute(String input, IData scope, String defaultValue) {
	  if (input == null || scope == null) return input;

	  java.util.regex.Matcher matcher = SUBSTITUTION_PATTERN.matcher(input);
	  StringBuffer output = new StringBuffer();

	  while(matcher.find()) {
	    String key = matcher.group(1);
	    Object value = tundra.support.document.get(scope, key);

	    if (value != null && value instanceof String) {
	      matcher.appendReplacement(output, matcher.quoteReplacement((String)value));
	    } else if (defaultValue != null) {
	      matcher.appendReplacement(output, matcher.quoteReplacement(defaultValue));
	    } else {
	      matcher.appendReplacement(output, matcher.quoteReplacement(matcher.group(0)));
	    }
	  }

	  matcher.appendTail(output);
	  return output.toString();
	}

	// returns a literal regular expression pattern for the given string
	public static String quote(String string) {
	  if (string == null) return null;
	  return java.util.regex.Pattern.quote(string);
	}
	// --- <<IS-END-SHARED>> ---
}

