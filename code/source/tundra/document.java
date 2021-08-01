package tundra;

// -----( IS Java Code Template v1.2
// -----( CREATED: 2021-08-01 13:18:25 AEST
// -----( ON-HOST: -

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.app.b2b.server.Package;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.Locale;
import permafrost.tundra.content.ValidationHelper;
import permafrost.tundra.content.ValidationResult;
import permafrost.tundra.data.CaseInsensitiveIData;
import permafrost.tundra.data.IDataHelper;
import permafrost.tundra.data.IDataJSONParser;
import permafrost.tundra.data.IDataXMLParser;
import permafrost.tundra.data.IDataYAMLParser;
import permafrost.tundra.data.ImmutableIData;
import permafrost.tundra.data.transform.string.Blankifier;
import permafrost.tundra.data.transform.string.Capitalizer;
import permafrost.tundra.data.transform.string.Condenser;
import permafrost.tundra.data.transform.string.Legalizer;
import permafrost.tundra.data.transform.string.Lowercaser;
import permafrost.tundra.data.transform.string.Nullifier;
import permafrost.tundra.data.transform.string.Prefixer;
import permafrost.tundra.data.transform.string.Replacer;
import permafrost.tundra.data.transform.string.Squeezer;
import permafrost.tundra.data.transform.string.Stringifier;
import permafrost.tundra.data.transform.string.Suffixer;
import permafrost.tundra.data.transform.string.Trimmer;
import permafrost.tundra.data.transform.string.Unprefixer;
import permafrost.tundra.data.transform.string.Unsuffixer;
import permafrost.tundra.data.transform.string.Uppercaser;
import permafrost.tundra.data.transform.Transformer;
import permafrost.tundra.data.transform.TransformerMode;
import permafrost.tundra.flow.ConditionEvaluator;
import permafrost.tundra.flow.variable.SubstitutionHelper;
import permafrost.tundra.flow.variable.SubstitutionType;
import permafrost.tundra.io.InputStreamHelper;
import permafrost.tundra.lang.BooleanHelper;
import permafrost.tundra.lang.CharsetHelper;
import permafrost.tundra.lang.ClassHelper;
import permafrost.tundra.lang.ExceptionHelper;
import permafrost.tundra.lang.LocaleHelper;
import permafrost.tundra.lang.ObjectConvertMode;
import permafrost.tundra.lang.ObjectHelper;
import permafrost.tundra.lang.Sanitization;
import permafrost.tundra.math.IntegerHelper;
import permafrost.tundra.server.PackageHelper;
import permafrost.tundra.server.ServerLogHelper;
import permafrost.tundra.server.ServerLogLevel;
import permafrost.tundra.server.ServiceHelper;
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




	public static final void amend (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(amend)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] record:0:optional $document.namespace
		// [i] record:1:optional $amendments
		// [i] - field:0:required key
		// [i] - field:0:optional value
		// [i] - field:0:optional action {&quot;merge&quot;,&quot;create&quot;,&quot;update&quot;,&quot;delete&quot;}
		// [i] - field:0:optional condition
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IData namespace = IDataHelper.get(cursor, "$document.namespace", IData.class);
		    IData[] amendments = IDataHelper.get(cursor, "$amendments", IData[].class);

		    if (document != null) IDataHelper.put(cursor, "$document", IDataHelper.amend(document, namespace, amendments, pipeline));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void blankify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(blankify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    IDataHelper.put(cursor, "$document", Transformer.transform(document, new Blankifier(recurse)), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $capitalize {&quot;all words&quot;,&quot;first word&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean firstWordOnly = IDataHelper.getOrDefault(cursor, "$capitalize", String.class, "all words").equals("first word");
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$document", Transformer.transform(document, new Capitalizer(mode, firstWordOnly, recurse)), false);
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
		// [i] record:0:optional $document
		// [i] field:1:optional $preserve
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String[] keys = IDataHelper.get(cursor, "$preserve", String[].class);

		    IDataHelper.clear(document, keys);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData input = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    if (input != null) {
		        IData output = IDataHelper.compact(input, recurse);
		        if (output == null) output = IDataFactory.create();
		        IDataHelper.put(cursor, "$document", output);
		    }
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
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData input = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    if (input != null) {
		        IData output = Transformer.transform(input, new Condenser(mode, recurse));
		        if (output == null) output = IDataFactory.create();
		        IDataHelper.put(cursor, "$document", output);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void copy (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(copy)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:required $key.source
		// [i] field:0:required $key.target
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String source = IDataHelper.get(cursor, "$key.source", String.class);
		    String target = IDataHelper.get(cursor, "$key.target", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);

		    IDataHelper.put(cursor, "$document", IDataHelper.copy(document, source, target, literal), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void denormalize (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(denormalize)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IDataHelper.put(cursor, "$document", IDataHelper.denormalize(document), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $key
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);

		    IDataHelper.put(cursor, "$document", IDataHelper.drop(document, key, literal), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void duplicate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(duplicate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $duplicate
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    IDataHelper.put(cursor, "$duplicate", IDataHelper.duplicate(document, recurse), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $key.input
		// [i] field:0:optional $value.input
		// [i] field:0:optional $value.class
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    String keyInput = IDataHelper.get(cursor, "$key.input", String.class);
		    String valueInput = IDataHelper.get(cursor, "$value.input", String.class);
		    Class valueClass = IDataHelper.get(cursor, "$value.class", Class.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    boolean scoped = scope != null;

		    IDataHelper.each(document, service, scoped ? scope: pipeline, keyInput, valueInput, valueClass, recurse);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void emit (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(emit)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $content.class {&quot;xml&quot;,&quot;json&quot;,&quot;yaml&quot;}
		// [i] field:0:optional $content.encoding
		// [i] field:0:optional $content.mode {&quot;stream&quot;,&quot;bytes&quot;,&quot;string&quot;}
		// [o] object:0:optional $content
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String contentClass = IDataHelper.get(cursor, "$content.class", String.class);
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");
		    ObjectConvertMode mode = IDataHelper.first(cursor, ObjectConvertMode.class, "$content.mode", "$mode");

		    if (document != null) {
		        Object content;

		        if (contentClass == null || contentClass.equals("xml")) {
		            content = ObjectHelper.convert(new IDataXMLParser().emit(document, charset), charset, mode);
		        } else if (contentClass.equals("json")) {
		            content = ObjectHelper.convert(new IDataJSONParser().emit(document, charset), charset, mode);
		        } else if (contentClass.equals("yaml")) {
		            content = ObjectHelper.convert(new IDataYAMLParser().emit(document, charset), charset, mode);
		        } else {
		            throw new IllegalArgumentException(MessageFormat.format("$content.class must be either \"xml\", \"json\", or \"yaml\": {0}", contentClass));
		        }

		        IDataHelper.put(cursor, "$content", content);
		    }
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void empty (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(empty)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [o] record:0:required $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IDataHelper.put(cursor, "$document", IDataFactory.create());
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
		// [i] record:0:optional $document.x
		// [i] record:0:optional $document.y
		// [o] field:0:required $equal?
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData x = IDataHelper.get(cursor, "$document.x", IData.class);
		    IData y = IDataHelper.get(cursor, "$document.y", IData.class);

		    IDataHelper.put(cursor, "$equal?", ObjectHelper.equal(x, y), String.class);
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
		// [i] record:0:optional $document
		// [o] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);

		    if (document != null) {
		        IDataCursor documentCursor = document.getCursor();
		        if (documentCursor.first()) {
		            IDataHelper.put(cursor, "$key", documentCursor.getKey());
		            IDataHelper.put(cursor, "$value", documentCursor.getValue());
		        }
		        documentCursor.destroy();
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void flatten (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(flatten)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:1:optional $keys
		// [i] field:0:optional $nulls? {&quot;false&quot;,&quot;true&quot;}
		// [o] object:1:optional $values
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String[] keys = IDataHelper.get(cursor, "$keys", String[].class);
		    boolean includeNulls = IDataHelper.getOrDefault(cursor, "$nulls?", Boolean.class, false);

		    // support $key for backwards compatibility
		    if (keys == null) {
		        String key = IDataHelper.get(cursor, "$key", String.class);
		        if (key != null) keys = new String[] { key };
		    }

		    Object[] values = IDataHelper.flatten(document, includeNulls, keys);

		    IDataHelper.put(cursor, "$values", values, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void flip (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(flip)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IDataHelper.put(cursor, "$document", IDataHelper.flip(document), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void freeze (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(freeze)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    if (document != null) IDataHelper.put(cursor, "$document", new ImmutableIData(document));
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
		// [i] record:0:optional $document
		// [i] field:0:optional $key
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] object:0:optional $value.default
		// [i] object:1:optional $value.list.default
		// [o] object:0:optional $value
		// [o] object:1:optional $value.list
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    Object defaultValue = IDataHelper.get(cursor, "$value.default", "$value.list.default", "$default.object", "$default.string");
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);

		    Object value = IDataHelper.get(pipeline, document, key, defaultValue, literal);

		    IDataHelper.put(cursor, "$value", value, false);
		    IDataHelper.put(cursor, "$value.list", value, Object[].class, false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $separator.value
		// [i] field:0:optional $separator.item
		// [i] field:0:optional $separator.list
		// [i] field:0:optional $sanitization {&quot;remove nulls&quot;,&quot;remove nulls and blanks&quot;,&quot;convert nulls to blanks&quot;}
		// [o] field:0:optional $result
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String valueSeparator = IDataHelper.get(cursor, "$separator.value", String.class);
		    String listSeparator = IDataHelper.get(cursor, "$separator.list", String.class);
		    String itemSeparator = IDataHelper.get(cursor, "$separator.item", String.class);
		    Sanitization sanitization = IDataHelper.get(cursor, "$sanitization", Sanitization.class);

		    if (document != null) IDataHelper.put(cursor, "$result", IDataHelper.join(document, itemSeparator, listSeparator, valueSeparator, sanitization));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void keys (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(keys)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $pattern
		// [i] object:0:optional $value
		// [i] object:1:optional $value.list
		// [o] field:1:required $keys
		// [o] field:0:required $keys.length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    Object value = IDataHelper.get(cursor, "$value", "$value.list");

		    String[] keys;

		    if (pattern != null) {
		        keys = IDataHelper.getKeys(document, pattern);
		    } else if (value != null) {
		        keys = IDataHelper.getKeys(document, value);
		    } else {
		        keys = IDataHelper.getKeys(document);
		    }

		    IDataHelper.put(cursor, "$keys", keys);
		    IDataHelper.put(cursor, "$keys.length", keys.length, String.class);
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
		// [i] record:0:optional $document
		// [o] field:0:optional $key
		// [o] object:0:optional $value
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);

		    if (document != null) {
		        IDataCursor documentCursor = document.getCursor();
		        if (documentCursor.last()) {
		            IDataHelper.put(cursor, "$key", documentCursor.getKey());
		            IDataHelper.put(cursor, "$value", documentCursor.getValue());
		        }
		        documentCursor.destroy();
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void leaves (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(leaves)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $class
		// [o] object:1:optional $values
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    Class klass = IDataHelper.getOrDefault(cursor, "$class", Class.class, Object.class);

		    Object[] values = IDataHelper.getLeaves(document, klass, recurse);

		    IDataHelper.put(cursor, "$values", values, false, false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$document", Transformer.transform(document, new Legalizer(mode, recurse)), false);
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
		// [i] record:0:optional $document
		// [o] field:0:required $length
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IDataHelper.put(cursor, "$length", IDataHelper.size(document), String.class);
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
		IDataCursor cursor = pipeline.getCursor();

		try {
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    IData scope = IDataHelper.get(cursor, "$scope", IData.class);
		    boolean scoped = scope != null;

		    if (scoped) {
		        scope = IDataHelper.duplicate(scope);
		    } else {
		        scope = pipeline;
		    }

		    scope = IDataHelper.arrayify(scope, key);

		    if (scoped) IDataHelper.put(cursor, "$scope", scope);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void log (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(log)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $log.level {&quot;Fatal&quot;,&quot;Error&quot;,&quot;Warn&quot;,&quot;Info&quot;,&quot;Debug&quot;,&quot;Trace&quot;,&quot;Off&quot;}
		// [i] field:0:optional $log.message
		// [i] field:0:optional $log.prefix? {&quot;true&quot;,&quot;false&quot;}
		// [i] field:0:optional $log.name
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String message = IDataHelper.get(cursor, "$log.message", String.class);
		    ServerLogLevel level = IDataHelper.first(cursor, ServerLogLevel.class, "$log.level", "$level");
		    boolean addPrefix = IDataHelper.removeOrDefault(cursor, "$log.prefix?", Boolean.class, true);
		    String name = IDataHelper.remove(cursor, "$log.name", String.class);

		    if (name == null) {
		       // infer log name as the invoking service's package
		       Package invokingPackage = PackageHelper.self();
		       if (invokingPackage != null) name = invokingPackage.getName();
		    }

		    ServerLogHelper.log(name, level, message, document, addPrefix);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void lowercase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(lowercase)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    Locale locale = IDataHelper.get(cursor, "$locale", Locale.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$document", Transformer.transform(document, new Lowercaser(mode, locale, recurse)), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $service
		// [i] record:0:optional $pipeline
		// [i] field:0:optional $key.input
		// [i] field:0:optional $key.output
		// [i] field:0:optional $value.input
		// [i] field:0:optional $value.output
		// [i] field:0:optional $value.class
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String service = IDataHelper.get(cursor, "$service", String.class);
		    IData scope = IDataHelper.get(cursor, "$pipeline", IData.class);
		    String keyInput = IDataHelper.get(cursor, "$key.input", String.class);
		    String keyOutput = IDataHelper.get(cursor, "$key.output", String.class);
		    String valueInput = IDataHelper.get(cursor, "$value.input", String.class);
		    String valueOutput = IDataHelper.get(cursor, "$value.output", String.class);
		    Class valueClass = IDataHelper.get(cursor, "$value.class", Class.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    boolean scoped = scope != null;

		    IDataHelper.put(cursor, "$document", IDataHelper.map(document, service, scoped ? scope: pipeline, keyInput, keyOutput, valueInput, valueOutput, valueClass, recurse));
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
		// [i] record:0:optional $operands
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:required $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData operands = IDataHelper.get(cursor, "$operands", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    if (operands == null) {
		        // support $documents IData[] argument for backwards compatibility
		        IData[] documents = IDataHelper.get(cursor, "$documents", IData[].class);
		        IDataHelper.put(cursor, "$document", IDataHelper.merge(documents, recurse));
		    } else {
		        IDataHelper.put(cursor, "$document", IDataHelper.merge(operands, recurse));
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
		// [i] record:0:optional $document
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IDataHelper.put(cursor, "$document", IDataHelper.normalize(document), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    IDataHelper.put(cursor, "$document", Transformer.transform(document, new Nullifier(recurse)), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void parse (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(parse)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] object:0:optional $content
		// [i] field:0:optional $content.class {&quot;xml&quot;,&quot;json&quot;,&quot;yaml&quot;}
		// [i] field:0:optional $content.encoding
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    Object content = IDataHelper.get(cursor, "$content");
		    String contentClass = IDataHelper.get(cursor, "$content.class", String.class);
		    Charset charset = IDataHelper.first(cursor, Charset.class, "$content.encoding", "$encoding");

		    if (content != null) {
		        IData document;

		        if (contentClass == null || contentClass.equals("xml")) {
		            document = new IDataXMLParser().parse(InputStreamHelper.normalize(content, charset));
		        } else if (contentClass.equals("json")) {
		            document = new IDataJSONParser().parse(InputStreamHelper.normalize(content, charset));
		        } else if (contentClass.equals("yaml")) {
		            document = new IDataYAMLParser().parse(InputStreamHelper.normalize(content, charset));
		        } else {
		            throw new IllegalArgumentException(MessageFormat.format("$content.class must be either \"xml\", \"json\", or \"yaml\": {0}", contentClass));
		        }

		        IDataHelper.put(cursor, "$document", document);
		    }
		} catch(IOException ex) {
		    ExceptionHelper.raise(ex);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:1:optional $pivot
		// [o] - field:0:required key
		// [o] - object:0:optional value
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    IDataHelper.put(cursor, "$pivot", IDataHelper.pivot(document, recurse), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void prefix (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(prefix)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $prefix
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $force? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String prefix = IDataHelper.get(cursor, "$prefix", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    boolean force = IDataHelper.getOrDefault(cursor, "$force?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    if (prefix != null) IDataHelper.put(cursor, "$document", Transformer.transform(document, new Prefixer(mode, prefix, force, recurse)), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $key
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] object:0:optional $value
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String key = IDataHelper.get(cursor, "$key", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);
		    Object value = IDataHelper.get(cursor, "$value");

		    IDataHelper.put(cursor, "$document", IDataHelper.put(document, key, value, literal));
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
		// [i] record:0:optional $document
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $occurrence.first? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, false);
		    boolean firstOccurrence = IDataHelper.getOrDefault(cursor, "$occurrence.first?", Boolean.class, false);
		    String occurrenceMode = IDataHelper.get(cursor, "$occurrence.mode", String.class);
		    if ("first".equals(occurrenceMode)) firstOccurrence = true;
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$document", Transformer.transform(document, new Replacer(mode, PatternHelper.compile(pattern, literalPattern), "", firstOccurrence, recurse)), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void rename (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(rename)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:required $key.source
		// [i] field:0:required $key.target
		// [i] field:0:optional $key.literal? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String source = IDataHelper.get(cursor, "$key.source", String.class);
		    String target = IDataHelper.get(cursor, "$key.target", String.class);
		    boolean literal = IDataHelper.getOrDefault(cursor, "$key.literal?", Boolean.class, false);

		    IDataHelper.put(cursor, "$document", IDataHelper.rename(document, source, target, literal), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $pattern
		// [i] field:0:optional $pattern.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $replacement
		// [i] field:0:optional $replacement.literal? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $occurrence.first? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String pattern = IDataHelper.get(cursor, "$pattern", String.class);
		    boolean literalPattern = IDataHelper.getOrDefault(cursor, "$pattern.literal?", Boolean.class, false);
		    String replacement = IDataHelper.get(cursor, "$replacement", String.class);
		    boolean literalReplacement = IDataHelper.getOrDefault(cursor, "$replacement.literal?", Boolean.class, false);
		    boolean firstOccurrence = IDataHelper.getOrDefault(cursor, "$occurrence.first?", Boolean.class, false);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$document", Transformer.transform(document, new Replacer(mode, PatternHelper.compile(pattern, literalPattern), ReplacementHelper.quote(replacement, literalReplacement), firstOccurrence, recurse)), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    IDataHelper.put(cursor, "$document", IDataHelper.sort(document, recurse), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData input = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    if (input != null) {
		        IData output = Transformer.transform(input, new Squeezer(recurse));
		        if (output == null) output = IDataFactory.create();
		        IDataHelper.put(cursor, "$document", output);
		    }
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void stringify (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(stringify)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);

		    IDataHelper.put(cursor, "$document", Transformer.transform(document, new Stringifier(recurse)), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $substitution.default
		// [i] field:0:optional $substitution.mode {&quot;local&quot;,&quot;global&quot;,&quot;all&quot;}
		// [i] record:0:optional $pipeline
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String defaultValue = IDataHelper.first(cursor, String.class, "$substitution.default", "$default");
		    SubstitutionType mode = IDataHelper.first(cursor, SubstitutionType.class, "$substitution.mode", "$mode");
		    IData scope = IDataHelper.getOrDefault(cursor, "$pipeline", IData.class, pipeline);

		    IDataHelper.put(cursor, "$document", SubstitutionHelper.substitute(document, defaultValue, true, false, mode, scope), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void suffix (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(suffix)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $suffix
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $force? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String suffix = IDataHelper.get(cursor, "$suffix", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    boolean force = IDataHelper.getOrDefault(cursor, "$force?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    if (suffix != null) IDataHelper.put(cursor, "$document", Transformer.transform(document, new Suffixer(mode, suffix, force, recurse)), false);
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
		// [i] record:0:optional $document
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$document", Transformer.transform(document, new Trimmer(mode, recurse)), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void uncase (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(uncase)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    Locale locale = IDataHelper.getOrDefault(cursor, "$locale", Locale.class, Locale.getDefault());

		    if (document != null) IDataHelper.put(cursor, "$document", new CaseInsensitiveIData(IDataHelper.duplicate(document, true), locale));
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void unprefix (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unprefix)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $prefix
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String prefix = IDataHelper.get(cursor, "$prefix", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    if (prefix != null) IDataHelper.put(cursor, "$document", Transformer.transform(document, new Unprefixer(mode, prefix, recurse)), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void unsuffix (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(unsuffix)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $suffix
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $force? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String suffix = IDataHelper.get(cursor, "$suffix", String.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    if (suffix != null) IDataHelper.put(cursor, "$document", Transformer.transform(document, new Unsuffixer(mode, suffix, recurse)), false);
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
		// [i] record:0:optional $document
		// [i] record:0:optional $locale
		// [i] - field:0:required language
		// [i] - field:0:optional country
		// [i] - field:0:optional variant
		// [i] field:0:optional $recurse? {&quot;false&quot;,&quot;true&quot;}
		// [i] field:0:optional $mode {&quot;values&quot;,&quot;keys&quot;,&quot;keys and values&quot;}
		// [o] record:0:optional $document
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    Locale locale = IDataHelper.get(cursor, "$locale", Locale.class);
		    boolean recurse = IDataHelper.getOrDefault(cursor, "$recurse?", Boolean.class, false);
		    TransformerMode mode = IDataHelper.get(cursor, "$mode", TransformerMode.class);

		    IDataHelper.put(cursor, "$document", Transformer.transform(document, new Uppercaser(mode, locale, recurse)), false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}



	public static final void validate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(validate)>> ---
		// @subtype unknown
		// @sigtype java 3.5
		// [i] record:0:optional $document
		// [i] field:0:optional $content.schema
		// [i] field:0:optional $raise? {&quot;false&quot;,&quot;true&quot;}
		// [o] field:0:required $valid?
		// [o] field:0:optional $message
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    String contentSchema = IDataHelper.first(cursor, String.class, "$content.schema", "$schema");
		    boolean raise = IDataHelper.getOrDefault(cursor, "$raise?", Boolean.class, false);

		    ValidationResult result = ValidationHelper.validate(document, contentSchema);
		    result.raiseIfInvalid(raise);

		    IDataHelper.put(cursor, "$valid?", result.isValid(), String.class);
		    IDataHelper.put(cursor, "$message", result.getMessage(), false);
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
		// [i] record:0:optional $document
		// [o] object:1:optional $values
		IDataCursor cursor = pipeline.getCursor();

		try {
		    IData document = IDataHelper.get(cursor, "$document", IData.class);
		    IDataHelper.put(cursor, "$values", IDataHelper.getValues(document), false, false);
		} finally {
		    cursor.destroy();
		}
		// --- <<IS-END>> ---


	}
}

