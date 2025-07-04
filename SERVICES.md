### tundra:log

Writes the given message to the server log, or a given named log,
optionally prefixed with the current user and call stack, and
suffixed with a given `IData` document that contains further context
serialized as a minified [JSON] string.

#### Inputs:

* `$log.level` is an optional logging level used when writing the
  pipeline to the server log.
* `$log.message` is an optional message to be written to the server
  log.
* `$log.context` is an optional object that provides context to the
  log statement. If an `IData` document is provided, it is logged as a
  serialized [JSON] string.
* `$log.prefix?` is a boolean indicating whether to prefix the log
  statement with the current user and callstack. Defaults to `true`.
* `$log.name` is the optional logical name of the log to write to.
  The logical log name can be mapped to a physical log target file
  via the Tundra package configuration section `feature/log/target`.
  If the logical log name cannot be found in this configuration
  section, the default physical log target is `./logs/server.log`.
  Defaults to the calling service's package name, if not specified.

---

### tundra:test

Runs all `*test*:should*` services in the given package, returning the
test results, where a test case passes if no exceptions are thrown.

Supports optional `:setup` and `:teardown` sibling services, which are
invoked before and after each test case service respectively.

This service is designed to either be invoked directly, or via a web
browser. When invoked via a browser, a basic HTML test report is
displayed:

    http://server:port/invoke/tundra/test?$package=Tundra

#### Inputs:

* `$package` is the name of the package which contains the test cases
  to be executed.
* `$concurrency` is an optional number of threads to use when
  executing the test cases. Defaults to 1.

#### Outputs:

* `$result` is a document containing the test results from executing
  all the `*test*:should*` services in the given `$package`.
  * `package` is the name of package the test results relate to.
  * `passed?` is a boolean indicating if all test cases executed
    passed.
  * `counts` is a document containing the test result counts.
    * `total` is the total number of test case services executed.
    * `passed` is the number of test case services which passed (did
      not throw an exception).
    * `failed` is the number of test case services which failed (threw
      an exception).
  * `cases` is a list of the test case services which were executed.
    * `description` is the fully-qualified name of the test case
      service.
    * `passed?` is a boolean indicating if the test case service passed
      (did not throw an exception).
    * `message` is an optional description returned if the test case
      service failed, which describes why the test case failed.

---

### tundra.assertion.datetime:equal

Throws an assertion error if the expected and actual datetimes are not
equal.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

#### Inputs:

* `$expected` is the expected datetime string value.
* `$actual` is the actual datetime string value. If this value is not
  equal to the expected value, an assertion error will be thrown.
* `$pattern` is an optional datetime string pattern the above values
  conform to, which defaults to an ISO8601/XML datetime pattern. Can
  either be a [java.text.SimpleDateFormat] pattern, or one of the
  named patterns.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.datetime:unequal

Throws an assertion error if the expected and actual datetimes are equal.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

#### Inputs:

* `$expected` is the expected datetime string value.
* `$actual` is the actual datetime string value. If this value is equal
  to the expected value, an assertion error will be thrown.
* `$pattern` is an optional datetime string pattern the above values
  conform to, which defaults to an ISO8601/XML datetime pattern. Can
  either be a [java.text.SimpleDateFormat] pattern, or one of the
  named patterns:
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.document:equal

Throws an assertion error if the expected and actual documents are
not equal.

#### Inputs:

* `$expected` is the expected `IData` document.
* `$actual` is the actual `IData` document. If this document is not equal
  to the expected document, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.
* `$strict?` is an optional boolean indicating if key position is also
  required for document equality. Defaults to `false`.

---

### tundra.assertion.document:unequal

Throws an assertion error if the expected and actual documents are
equal.

#### Inputs:

* `$expected` is the expected `IData` document.
* `$actual` is the actual `IData` document. If this document is equal to
  the expected document, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.
* `$strict?` is an optional boolean indicating if key position is also
  required for document equality. Defaults to `false`.

---

### tundra.assertion.exception:raised

Throws an assertion error if the given service, when invoked, does
not throw an exception matching the given criteria.

#### Inputs:

* `$service` is the fully-qualified name of the service to be
  invoked that is expected to throw an exception.
* `$pipeline` is an optional `IData` document which, if specified,
  contains the input arguments for the invocation of `$service`; in
  other words, the invocation is scoped to this `IData` document. If
  not specified, the invocation is unscoped, and hence the service
  will operate directly against the pipeline itself.
* `$exception` is an `IData` document containing optional criteria
  which a thrown exception will be expected meet.
  * `class` is the name of a Java class that the thrown exception
    must be an instance of.
  * `message`
    * `pattern` is a [regular expression pattern] or literal string
      that must match the thrown exception's message.
    * `literal?` is a boolean indicating if the `pattern` string
      should be treated as a literal string. If `false`, `pattern` is
      treated as a [regular expression pattern]. If `true`, `pattern`
      is treated as a literal string. Defaults to `false`, if not
      specified.
* `$message` is an optional custom message to be used as the
  asertion error message if the assertion fails.

---

### tundra.assertion.list.document:equal

Throws an assertion error if the expected and actual document lists are not
equal.

#### Inputs:

* `$expected` is the expected `IData` document list.
* `$actual` is the actual `IData` document list. If this document list is
  not equal to the expected document list, an assertion error will be
  thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.
* `$strict?` is an optional boolean indicating if key position is also
  required for document equality. Defaults to `false`.

---

### tundra.assertion.list.document:unequal

Throws an assertion error if the expected and actual document lists are
equal.

#### Inputs:

* `$expected` is the expected `IData` document list.
* `$actual` is the actual `IData` document list. If this document list is
  equal to the expected document list, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.
* `$strict?` is an optional boolean indicating if key position is also
  required for document equality. Defaults to `false`.

---

### tundra.assertion.list.object:equal

Throws an assertion error if the expected and actual lists are not equal.

#### Inputs:

* `$expected` is the expected java.lang.Object list.
* `$actual` is the actual java.lang.Object list. If this list is not
  equal to the expected list, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.list.object:exists

Throws an assertion error if the given list is null.

#### Inputs:

* `$list` is the java.lang.Object list to assert existence of.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.list.object:instance

Throws an assertion error if the given list is not an instance of the given
class.

#### Inputs:

* `$list` is the java.lang.Object list to be tested as an instance of the
  specified class.
* `$class` is the Java array class name the given list is asserted to be
  an instance of. Note that [Java array class names] are different and
  distinct to normal Java object class names. For example, an array of
  `java.lang.String` objects has the class name `[Ljava.lang.String;`.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.list.object:nothing

Throws an assertion error if the given list is not null.

#### Inputs:

* `$list` is the java.lang.Object list expected to be null.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.list.object:unequal

Throws an assertion error if the expected and actual lists are equal.

#### Inputs:

* `$expected` is the expected object list.
* `$actual` is the actual object list. If this list is equal to the
  expected list, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.list.string:equal

Throws an assertion error if the expected and actual string lists are not
equal.

#### Inputs:

* `$expected` is the expected string list.
* `$actual` is the actual string list. If this list is not equal to the
  expected list, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.list.string:unequal

Throws an assertion error if the expected and actual string lists are
equal.

#### Inputs:

* `$expected` is the expected string list.
* `$actual` is the actual string list. If this list is equal to the
  expected list, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.object:equal

Throws an assertion error if the expected and actual objects are not equal.

#### Inputs:

* `$expected` is the expected object value.
* `$actual` is the actual object value. If this value is not equal to the
  expected value, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.object:exists

Throws an assertion error if the given object is null.

#### Inputs:

* `$object` is the object expected to not be null.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.object:instance

Throws an assertion error if the given object is not an instance of the
given class.

#### Inputs:

* `$object` is the object to be tested as an instance of the specified
  class.
* `$class` is the Java class name the given object is asserted to be an
  instance of.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.object:nothing

Throws an assertion error if the given object is not null

#### Inputs:

* `$object` is the object that is expected to be null.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.object:unequal

Throws an assertion error if the expected and actual objects are equal.

#### Inputs:

* `$expected` is the expected object.
* `$actual` is the actual object. If this object is equal to the expected
  object, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.step:unreached

Throws an assertion error if this service is executed.

#### Inputs:

* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.string:equal

Throws an assertion error if the expected and actual strings are not equal.

#### Inputs:

* `$expected` is the expected string value.
* `$actual` is the actual string value. If this value is not equal to the
  expected value, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.assertion.string:unequal

Throws an assertion error if the expected and actual strings are equal.

#### Inputs:

* `$expected` is the expected string value.
* `$actual` is the actual string value. If this value is equal to the
  expected value, an assertion error will be thrown.
* `$message` is an optional custom message to be used as the assertion
  error message if the assertion fails.

---

### tundra.base64:decode

[Base64] decodes the given string, byte array, or input stream.

#### Inputs:

* `$base64` is either a [Base64] encoded string, byte array, or input
  stream to be decoded.
* `$content.encoding` is the optional character set used to decode
  the text data when `$base64` is provided as a byte array or input
  stream. Not used when `$base64` is provided as a string. Defaults
  to [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or
  `string`, which determines the type of object `$content` is
  returned as. Defaults to `stream`.

#### Outputs:

* `$content` is the [Base64] decoded data as a string, byte array, or
  input stream, as determined by the `$content.mode` specified.

---

### tundra.base64:encode

[Base64] encodes the given string, byte array, or input stream.

#### Inputs:

* `$content` is either a string, byte array, or input stream
  containing data to be [Base64] encoded.
* `$content.encoding` is the optional character set used to encode
  the text data when `$content` is provided as a string. Defaults to
  [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or
  `string` which determines the type of object `$base64` is returned
  as. Defaults to `stream`.

#### Outputs:

* `$base64` is the [Base64] encoded data as a string, byte array, or
  input stream, as determined by the `$content.mode` specified.

---

### tundra.bool:emit

Converts the given `$boolean` value to a string using the appropriate
string values specified for `true` and `false`.

#### Inputs:

* `$boolean` is the value to be converted, and can be either a string
  parseable as a boolean value, or a `java.lang.Boolean` object.
* `$value.true` is an optional string value returned if `$boolean` is
  `true`. Defaults to "true".
* `$value.false` is an optional string value returned if `$boolean` is
  `false`. Defaults to "false".

#### Outputs:

* `$string` is the converted boolean value.

---

### tundra.bool:format

Formats the given boolean string using the appropriate boolean value
specified for `true` and `false`.

#### Inputs:

* `$format.operands`  is an `IData` document containing arbitrarily
  specified boolean strings to be formatted.
* `$value.true.input` is an optional string value used to determine
  if `$string` represents a boolean value of `true`.
* `$value.false.input` is an optional string value used to determine
  if `$string` represents a boolean value of `false`.
* `$value.true.output` is an optional string value returned if
  `$string` represents the boolean value `true`. Defaults to "true".
* `$value.false.output` is an optional string value returned if
  `$string` represents the boolean value `false`. Defaults to
  "false".

#### Outputs:

* `$format.results` is an `IData` document containing the reformatted
  boolean strings.

---

### tundra.bool:negate

Returns the negated canonical string form for the given `$boolean` string:
either "true" or "false".

#### Inputs:

* `$boolean` is the value to be negated.

#### Outputs:

* `$boolean` is the negated input value.

---

### tundra.bool:normalize

Returns the canonical string form for the given `$boolean` string: either
"true" or "false".

If `$boolean` is null and `$default` is not null, then `$default`'s boolean
value will be returned.

If `$boolean` is null and `$default` is null, then "false" will be
returned.

#### Inputs:

* `$boolean` is the value to be normalized.
* `$default` is the value to use if `$boolean` is null.

#### Outputs:

* `$boolean` is the normalized input value, guaranteed to be either the
  string "true" or "false".

---

### tundra.bool:parse

Parses the given string as a boolean value.

#### Inputs:

* `$string` is an optional string to be parsed.

#### Outputs:

* `$boolean` is the resulting `java.lang.Boolean` object from parsing
  the given `$string`.

---

### tundra.bool:random

Returns a cryptographically strong randomly generated boolean using
an instance of the [java.security.SecureRandom] class.

#### Outputs:

* `$boolean.random` is the randomly generated boolean provided as
  a [java.lang.Boolean] object.

---

### tundra.bytes:length

Returns the length of the given byte array.

#### Inputs:

* `$bytes` must be a byte array.

#### Outputs:

* `$length` is the length of the given byte array.

---

### tundra.bytes:normalize

Converts a string, byte array or input stream to a byte array.

#### Inputs:

* `$content` is the string, byte array or input stream to be
  normalized.
* `$content.encoding` is the optional character set to use to encode
  `$content` when it is a string. Defaults to [UTF-8].

#### Outputs:

* `$bytes` is a byte array representation of the `$content` data.
* `$content.encoding` is the character set used to encode `$content`.

---

### tundra.bytes:random

Returns cryptographically strong randomly generated bytes using an
instance of the [java.security.SecureRandom] class.

#### Inputs:

* `$bytes.random.length` is the desired number of bytes to be
  returned.

#### Outputs:

* `$bytes.random` is the randomly generated bytes of the specified
  length.

---

### tundra.bytes:transcode

Converts the given bytes containing text data from one encoding or
character set to another.

#### Inputs:

* `$bytes` is a `byte[]` to be transcoded.
* `$content.encoding.input` is the optional character set used to
  decode the given `$bytes`. Defaults to [UTF-8].
* `$content.encoding.output` is the optional character set used to
  encode the text data decoded from the given `$bytes`. Defaults to
  [UTF-8].

#### Outputs:

* `$bytes` is the resulting transcoded `byte[]`.
* `$content.encoding.input` is the character set used to decode the
  input data.
* `$content.encoding.output` is the character set used to encode the
  output data.

---

### tundra.cache.memory:all

Returns the cache with the given name.

#### Inputs:

* `$cache.name` is the name of the cache to be returned.

#### Outputs:

* `$cache` is the cache with the given name, if it exists.
* `$cache.length` is the number of items in `$cache`.

---

### tundra.cache.memory:clear

Removes all entries from the cache with the given name.

#### Inputs:

* `$cache.name` is the name of the cache to be cleared.

---

### tundra.cache.memory:exists

Returns `true` if the cache with the given name contains the given
key.

#### Inputs:

* `$cache.name` is the name of the cache to be checked.
* `$cache.key` is the key whose existence is to be checked.

#### Outputs:

* `$cache.key.exists?` is `true` if the given key exists in the cache
  with the given name.

---

### tundra.cache.memory:get

Returns the value associated with the given key from the cache with
the given name.

#### Inputs:

* `$cache.name` is the name of the cache to be checked.
* `$cache.key` is the key whose value is to be returned.

#### Outputs:

* `$cache.value` is the value associated with the given key from the
  cache with the given name, if it exists.
* `$cache.expiry.datetime` is the datetime when this cache entry
  expires, if applicable.

---

### tundra.cache.memory:put

Sets the value associated with the given key in the cache with
the given name.

#### Inputs:

* `$cache.name` is the name of the cache to be updated.
* `$cache.key` is the key whose associated value is to be set.
* `$cache.key.absent?` is an optional boolean which when `true` will
  only associate the given value with the given key if the key does
  not already exist in the cache. Defaults to `false`.
* `$cache.value` is the value to be set.
* `$cache.expiry.duration` is an optional duration specifying when
  from now that this cache entry expires. If neither an expiry
  duration or datetime is specified, the cache entry never expires.
* `$cache.expiry.datetime` is an optional datetime specifing when
  this cache entry expires. If neither an expiry duration or datetime
  is specified, the cache entry never expires.

#### Outputs:

* `$cache.updated?` is `true` if the `$cache.key` is now associated
  with the given `$cache.value`, or `false` if the given
  `$cache.key.absent?` was `true` and `$cache.key` could not be
  associated with the given `$cache.value` because it was already
  associated with another value.
* `$cache.value` is the value associated with the given key in the
  cache with the given name. If `$cache.key.absent?` was `true` and
  the key already existed, this is the value already associated with
  the key.
* `$cache.value.previous` is the value that was previously already
  associated with the given key in the cache with the given name, if
  any.

---

### tundra.cache.memory:remove

Removes the given key and its associated value from the cache with
the given name.

#### Inputs:

* `$cache.name` is the name of the cache to be updated.
* `$cache.key` is the key to be removed.
* `$cache.value` is the optional value associated with the key. If
  specified, the key is only removed from the cache if it is already
  associated with this value.

#### Outputs:

* `$cache.key.removed?` is `true` if the key value pair was removed
  from the cache with the given name.
* `$cache.value` was the value associated with the given key at the
  time of its removal.

---

### tundra.cache.memory:replace

Replaces the value associated with the given key in the cache with
the given name.

#### Inputs:

* `$cache.name` is the name of the cache to be updated.
* `$cache.key` is the key whose associated value is to be replaced.
* `$cache.value.old` is the optional value which is expected to
  already be associated with the key. If specified, the key's
  associated value is only replaced with the given new value if its
  current value is equal to this value. If not specified, the key's
  associated value will always be replaced with the given new value.
* `$cache.value.new` is the new value to be associated with the key.
* `$cache.expiry.duration` is an optional duration specifying when
  from now that this cache entry expires. If neither an expiry
  duration or datetime is specified, the cache entry never expires.
* `$cache.expiry.datetime` is an optional datetime specifing when
  this cache entry expires. If neither an expiry duration or datetime
  is specified, the cache entry never expires.

#### Outputs:

* `$cache.value.replaced?` is `true` if the key exists and it's value
  was replaced with the given new value in the cache with the given
  name.

---

### tundra.collection.list.document:append

Appends arbitrarily specified items to the end of a [java.util.List]
object.

#### Inputs:

* `$list` is a [java.util.List] object to append items to. If not
  specified, a new [java.util.List] object is automatically created.
* `$items` is an `IData` document containing arbitrarily specified
  top-level `IData` documents to be appended to the list. The values
  from the given document will be appended in the order they appear.

#### Outputs:

* `$list` is the resulting [java.util.List] object with the given
  items appended to the end.

---

### tundra.collection.list.document:arrayify

Converts a [java.util.List] object to an array.

#### Inputs:

* `$list` is a [java.util.List] object to be converted.

#### Outputs:

* `$array` is an array representation of the given `$list`.
* `$array.length` is the number of items in the returned `$array`.

---

### tundra.collection.list.document:clear

Removes all items from the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$list` is the given [java.util.List] object with all items removed.

---

### tundra.collection.list.document:first

Returns the first item from a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to retrieve the first item
  from.
* `$class` is an optional Java class name that the list and items
  are required to be instances of.

#### Outputs:

* `$item` is the first value in the list, if it exists.
* `$item.exists?` is a boolean indicating whether at least one
  item existed in the list.

---

### tundra.collection.list.document:get

Returns the item at the specified index of a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to retrieve an item from.
* `$index` is the index of the item to be returned.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.

#### Outputs:

* `$item` is the value at the specified index, if it exists.
* `$item.exists?` is a boolean indicating whether an item at the
  specified index exists in the given list.

---

### tundra.collection.list.document:insert

Inserts arbitrarily specified items at the specified index of a
[java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to insert items into. If not
  specified, a new [java.util.List] object is automatically created.
* `$items` is an `IData` document containing arbitrarily specified
  top-level `IData` documents to be inserted into the list. The values
  from the given document will be inserted in the order they appear.
* `$index` is the index at which the given items will be inserted.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.

#### Outputs:

* `$list` is the resulting [java.util.List] object with the given
  items inserted at the specified index.

---

### tundra.collection.list.document:last

Returns the last item from a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to retrieve the last item
  from.
* `$class` is an optional Java class name that the list and items
  are required to be instances of.

#### Outputs:

* `$item` is the last value in the list, if it exists.
* `$item.exists?` is a boolean indicating whether at least one
  item existed in the list.

---

### tundra.collection.list.document:length

Returns the number of items in the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$length` is the number of items in the given a [java.util.List] object.

---

### tundra.collection.list.document:listify

Converts an `IData[]` object to a [java.util.List] object.

#### Inputs:

* `$array` is an `IData[]` object to be converted.

#### Outputs:

* `$list` is a [java.util.List] representation of the given `$array`.

---

### tundra.collection.list.document:prepend

Prepends arbitrarily specified items to the end of a [java.util.List]
object.

#### Inputs:

* `$list` is a [java.util.List] object to prepend items to. If not
  specified, a new [java.util.List] object is automatically created.
* `$items` is an `IData` document containing arbitrarily specified
  top-level `IData` documents to be prepended to the list. The values
  from the given document will be prepended in the order they appear.

#### Outputs:

* `$list` is the resulting [java.util.List] object with the given
  items prepended to the end.

---

### tundra.collection.list.document:remove

Removes and returns the value of the item at the specified index
from a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.
* `$index` is the index of the item to be removed.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.

#### Outputs:

* `$item` is the value that was removed at the specified index.
* `$item.exists?` is a boolean indicating whether an item at the
  specified index existed in the given list.

---

### tundra.collection.list.document:reverse

Returns a new [java.util.List] object which is a reverse of the
given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$list.reverse` is a new [java.util.List] object which is a
  reverse of the given [java.util.List] object.

---

### tundra.collection.list.document:set

Sets the value of at the specified index in a [java.util.List]
object.

#### Inputs:

* `$list` is a [java.util.List] object to set the value in.
* `$item.new` is the new value to be set.
* `$index` is the index of the item to be set.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.

#### Outputs:

* `$item.old` is the previous value at the specified index.

---

### tundra.collection.list.document:take

Removes and returns a specified number of items from the head
of the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.
* `$count` is the number of items to take from the head of the list.

#### Outputs:

* `$list.head` is a new [java.util.List] object containing the given
  count of items removed from the head of the given list.

---

### tundra.collection.list.document:unique

Returns a new [java.util.List] object which contains only the
unique items from the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$list.unique` is a new [java.util.List] object which contains
  only the unique items from the given [java.util.List] object.

---

### tundra.collection.list.object:append

Appends arbitrarily specified items to the end of a [java.util.List]
object.

#### Inputs:

* `$list` is a [java.util.List] object to append items to. If not
  specified, a new [java.util.List] object is automatically created.
* `$items` is an `IData` document containing arbitrarily specified
  top-level objects to be appended to the list. The values from the
  given document will be appended in the order they appear.
* `$class` is an optional Java class name that the list items
  to be appended are required to be instances of.

#### Outputs:

* `$list` is the resulting [java.util.List] object with the given
  items appended to the end.

---

### tundra.collection.list.object:arrayify

Converts a [java.util.List] object to an array.

#### Inputs:

* `$list` is a [java.util.List] object to be converted.
* `$class` is an optional Java class name for the type of item
  stored in the list and resulting array.

#### Outputs:

* `$array` is an array representation of the given `$list.`
* `$array.length` is the number of items in the returned `$array`.

---

### tundra.collection.list.object:clear

Removes all items from the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.
* `$class` is an optional Java class name that list items are
  required to be instances of.

#### Outputs:

* `$list` is the given [java.util.List] object with all items removed.

---

### tundra.collection.list.object:first

Returns the first item from a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to retrieve the first item
  from.
* `$class` is an optional Java class name that the list items
  are required to be instances of.

#### Outputs:

* `$item` is the first value in the list, if it exists.
* `$item.exists?` is a boolean indicating whether at least one
  item existed in the list.

---

### tundra.collection.list.object:get

Returns the item at the specified index of a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to retrieve an item from.
* `$index` is the index of the item to be returned.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.
* `$class` is an optional Java class name that the list items
  are required to be instances of.

#### Outputs:

* `$item` is the value at the specified index, if it exists.
* `$item.exists?` is a boolean indicating whether an item at the
  specified index exists in the given list.

---

### tundra.collection.list.object:insert

Inserts arbitrarily specified items at the specified index of a
[java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to insert items into. If not
  specified, a new [java.util.List] object is automatically created.
* `$items` is an `IData` document containing arbitrarily specified
  top-level objects to be inserted into the list. The values from the
  given document will be inserted in the order they appear.
* `$index` is the index at which the given items will be inserted.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.
* `$class` is an optional Java class name that the list items
  to be inserted are required to be instances of.

#### Outputs:

* `$list` is the resulting [java.util.List] object with the given
  items inserted at the specified index.

---

### tundra.collection.list.object:last

Returns the last item from a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to retrieve the last item
  from.
* `$class` is an optional Java class name that the list items
  are required to be instances of.

#### Outputs:

* `$item` is the last value in the list, if it exists.
* `$item.exists?` is a boolean indicating whether at least one
  item existed in the list.

---

### tundra.collection.list.object:length

Returns the number of items in the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$length` is the number of items in the given a [java.util.List] object.

---

### tundra.collection.list.object:listify

Converts an array to a [java.util.List] object.

#### Inputs:

* `$array` is an array to be converted to a [java.util.List].
* `$class` is an optional Java class name for the type of item
  stored in the array and resulting list.

#### Outputs:

* `$list` is a [java.util.List] representation of the given `$array`.

---

### tundra.collection.list.object:prepend

Prepends arbitrarily specified items to the end of a [java.util.List]
object.

#### Inputs:

* `$list` is a [java.util.List] object to prepend items to. If not
  specified, a new [java.util.List] object is automatically created.
* `$items` is an `IData` document containing arbitrarily specified
  top-level objects to be prepended to the list. The values from the
  given document will be prepended in the order they appear.
* `$class` is an optional Java class name that the list items
  to be prepended are required to be instances of.

#### Outputs:

* `$list` is the resulting [java.util.List] object with the given
  items prepended to the start of the list.

---

### tundra.collection.list.object:remove

Removes and returns the value of the item at the specified index
from a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.
* `$index` is the index of the item to be removed.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.
* `$class` is an optional Java class name that the list items
  are required to be instances of.

#### Outputs:

* `$item` is the value that was removed at the specified index.
* `$item.exists?` is a boolean indicating whether an item at the
  specified index existed in the given list.

---

### tundra.collection.list.object:reverse

Returns a new [java.util.List] object which is a reverse of the
given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$list.reverse` is a new [java.util.List] object which is a
  reverse of the given [java.util.List] object.

---

### tundra.collection.list.object:set

Sets the value of at the specified index in a [java.util.List]
object.

#### Inputs:

* `$list` is a [java.util.List] object to set the value in.
* `$item.new` is the new value to be set.
* `$index` is the index of the item to be set.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.
* `$class` is an optional Java class name that the list items
  are required to be instances of.

#### Outputs:

* `$item.old` is the previous value at the specified index.

---

### tundra.collection.list.object:take

Removes and returns a specified number of items from the head
of the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.
* `$count` is the number of items to take from the head of the list.
* `$class` is an optional Java class name that the list items
  are required to be instances of.

#### Outputs:

* `$list.head` is a new [java.util.List] object containing the given
  count of items removed from the head of the given list.

---

### tundra.collection.list.object:unique

Returns a new [java.util.List] object which contains only the
unique items from the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$list.unique` is a new [java.util.List] object which contains
  only the unique items from the given [java.util.List] object.

---

### tundra.collection.list.string:append

Appends arbitrarily specified items to the end of a [java.util.List]
object.

#### Inputs:

* `$list` is a [java.util.List] object to append items to. If not
  specified, a new [java.util.List] object is automatically created.
* `$items` is an `IData` document containing arbitrarily specified
  top-level strings to be appended to the list. The values from the
  given document will be appended in the order they appear.

#### Outputs:

* `$list` is the resulting [java.util.List] object with the given
  items appended to the end.

---

### tundra.collection.list.string:arrayify

Converts a [java.util.List] object to an array.

#### Inputs:

* `$list` is a [java.util.List] object to be converted.

#### Outputs:

* `$array` is an array representation of the given `$list`.
* `$array.length` is the number of items in the returned `$array`.

---

### tundra.collection.list.string:clear

Removes all items from the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$list` is the given [java.util.List] object with all items removed.

---

### tundra.collection.list.string:first

Returns the first item from a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to retrieve the first item
  from.

#### Outputs:

* `$item` is the first value in the list, if it exists.
* `$item.exists?` is a boolean indicating whether at least one
  item existed in the list.

---

### tundra.collection.list.string:get

Returns the item at the specified index of a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to retrieve an item from.
* `$index` is the index of the item to be returned.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.

#### Outputs:

* `$item` is the value at the specified index, if it exists.
* `$item.exists?` is a boolean indicating whether an item at the
  specified index exists in the given list.

---

### tundra.collection.list.string:insert

Inserts arbitrarily specified items at the specified index of a
[java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to insert items into. If not
  specified, a new [java.util.List] object is automatically created.
* `$items` is an `IData` document containing arbitrarily specified
  top-level strings to be inserted into the list. The values from the
  given document will be inserted in the order they appear.
* `$index` is the index at which the given items will be inserted.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.

#### Outputs:

* `$list` is the resulting [java.util.List] object with the given
  items inserted at the specified index.

---

### tundra.collection.list.string:last

Returns the last item from a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object to retrieve the last item
  from.

#### Outputs:

* `$item` is the last value in the list, if it exists.
* `$item.exists?` is a boolean indicating whether at least one
  item existed in the list.

---

### tundra.collection.list.string:length

Returns the number of items in the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$length` is the number of items in the given a [java.util.List] object.

---

### tundra.collection.list.string:listify

Converts a `String[]` object to a [java.util.List] object.

#### Inputs:

* `$array` is a `String[]` object to be converted.

#### Outputs:

* `$list` is a [java.util.List] representation of the given `$array`.

---

### tundra.collection.list.string:prepend

Prepends arbitrarily specified items to the end of a [java.util.List]
object.

#### Inputs:

* `$list` is a [java.util.List] object to prepend items to. If not
  specified, a new [java.util.List] object is automatically created.
* `$items` is an `IData` document containing arbitrarily specified
  top-level strings to be prepended to the list. The values from the
  given document will be prepended in the order they appear.

#### Outputs:

* `$list` is the resulting [java.util.List] object with the given
  items prepended to the end.

---

### tundra.collection.list.string:remove

Removes and returns the value of the item at the specified index
from a [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.
* `$index` is the index of the item to be removed.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.

#### Outputs:

* `$item` is the value that was removed at the specified index.
* `$item.exists?` is a boolean indicating whether an item at the
  specified index existed in the given list.

---

### tundra.collection.list.string:reverse

Returns a new [java.util.List] object which is a reverse of the
given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$list.reverse` is a new [java.util.List] object which is a
  reverse of the given [java.util.List] object.

---

### tundra.collection.list.string:set

Sets the value of at the specified index in a [java.util.List]
object.

#### Inputs:

* `$list` is a [java.util.List] object to set the value in.
* `$item.new` is the new value to be set.
* `$index` is the index of the item to be set.
* `$index.base` specifies whether the index is zero-based or one-
  based. Defaults to zero-based if not specified.

#### Outputs:

* `$item.old` is the previous value at the specified index.

---

### tundra.collection.list.string:take

Removes and returns a specified number of items from the head
of the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.
* `$count` is the number of items to take from the head of the list.

#### Outputs:

* `$list.head` is a new [java.util.List] object containing the given
  count of items removed from the head of the given list.

---

### tundra.collection.list.string:unique

Returns a new [java.util.List] object which contains only the
unique items from the given [java.util.List] object.

#### Inputs:

* `$list` is a [java.util.List] object.

#### Outputs:

* `$list.unique` is a new [java.util.List] object which contains
  only the unique items from the given [java.util.List] object.

---

### tundra.collection.map.document:clear

Removes all of the mappings from the given map.

#### Inputs:

* `$map` is a [java.util.Map] object from which all key value mappings
  will be removed.

---

### tundra.collection.map.document:create

Returns a newly constructed [java.util.Map] object with String
keys and `IData` values.

#### Inputs:

* `$sorted?` is an optional boolean which when `true` indicates that
  the resulting [java.util.Map] should maintain its keys sorted
  in natural ascending order. Defaults to `false`.

#### Outputs:

* `$map` is a newly constructed [java.util.Map] object.

---

### tundra.collection.map.document:documentify

Returns an `IData` document representation of the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$document` is an `IData` document representation of the given `$map`.

---

### tundra.collection.map.document:get

Returns the value associated with the given key in the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key whose associated value is to be returned.

#### Outputs:

* `$key.exists?` is `true` if the given key exists in the given map.
* `$value` is the value associated with the given key in the given
  [java.util.Map].

---

### tundra.collection.map.document:keys

Returns the list of keys stored in the given [java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$keys` is the list of keys currently stored in the given map.
* `$keys.length` is the number of items in the returned `$keys` array.

---

### tundra.collection.map.document:length

Returns the number of mappings stored in the given [java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$length` is the number of mappings currently stored in the given map.

---

### tundra.collection.map.document:mapify

Copies the top-level elements from the given `IData` document to a
newly created thread-safe [java.util.Map] object, which provides
either O(1) time cost for an unsorted map, or O(log(n)) time cost
for a sorted map, for the contains, get, put, and remove operations.

Therefore the resulting [java.util.Map] is more efficient than the
default `IData` implementations, such as `com.wm.data.ISMemDataImpl` or
`com.wm.data.BasicData`, which are order preserving linked lists with
a time cost is O(n) for the same operations.

Note that the resulting [java.util.Map] requires keys to be `String`
objects and values to be `IData` objects.

#### Inputs:

* `$document` is an `IData` document whose top-level elements will be
  used to seed the returned [java.util.Map] object. All values
  are required to be `IData` objects.
* `$sorted?` is an optional boolean which when `true` indicates that
  the resulting [java.util.Map] should maintain its keys sorted in
  natural ascending order. Defaults to `false`.

#### Outputs:

* `$map` is a new [java.util.Map] object containing all the top-level
  elements from the given `$document`.

---

### tundra.collection.map.document:put

Associates the given value with the given key in the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key to be associated with the given value.
* `$key.absent?` is an optional boolean which when `true` will only
  associate the given value with the given key if the key does
  not already exist in the map. Defaults to `false`.
* `$value` is the value to be associated with the given key.

#### Outputs:

* `$map` is the given map, or a newly created map if none was given.
* `$value` is the value associated with the given key. If `$key.absent?`
  was `true` and the key already existed, this is the value already
  associated with the key.

---

### tundra.collection.map.document:remove

Removes the mapping associated the given key from the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key to be removed.
* `$value` is the optional value associated with the key. If
  specified, the key is only removed from the map if it is
  associated with this value.

#### Outputs:

* `$key.removed?` is `true` if the key value pair was removed from the
  map.
* `$value` was the value associated with the given key at the time of
  its removal.

---

### tundra.collection.map.document:replace

Replaces the value associated with the given key in the given map.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key whose associated value is to be replaced.
* `$value.ol`d is the optional value which is expected to already be
  associated with the key. If specified, the key's associated value
  is only replaced with the given new value if its current value is
  equal to this value. If not specified, the key's associated value
  will always be replaced with the given new value.
* `$value.new` is the new value to be associated with the key.

#### Outputs:

* `$value.replaced?` is `true` if the key exists and it's value was
  replaced with the given new value.

---

### tundra.collection.map.document:values

Returns the list of values stored in the given [java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$values` is the list of values currently stored in the given map.
* `$values.length` is the number of items in the returned `$values`
  array.

---

### tundra.collection.map.object:clear

Removes all of the mappings from the given map.

#### Inputs:

* `$map` is a [java.util.Map] object from which all key value mappings
  will be removed.

---

### tundra.collection.map.object:create

Returns a newly constructed [java.util.Map] object.

#### Inputs:

* `$key.class` is the name of the Java class that keys in the
  resulting map will use. Defaults to `java.lang.Object`.
* `$value.class` is the name of the Java class that values in the
  resulting map will use. Defaults to `java.lang.Object`.
* `$sorted?` is an optional boolean which when `true` indicates that
  the resulting [java.util.Map] should maintain its keys sorted
  in natural ascending order. Defaults to `false`.

#### Outputs:

* `$map` is a newly constructed [java.util.Map] object.

---

### tundra.collection.map.object:documentify

Returns an `IData` document representation of the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$document` is an `IData` document representation of the given `$map`.

---

### tundra.collection.map.object:get

Returns the value associated with the given key in the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key whose associated value is to be returned.

#### Outputs:

* `$key.exists?` is `true` if the given key exists in the given map.
* `$value` is the value associated with the given key in the given
  [java.util.Map].

---

### tundra.collection.map.object:keys

Returns the list of keys stored in the given [java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$keys` is the list of keys currently stored in the given map.
* `$keys.length` is the number of items in the returned `$keys` array.

---

### tundra.collection.map.object:length

Returns the number of mappings stored in the given [java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$length` is the number of mappings currently stored in the given map.

---

### tundra.collection.map.object:mapify

Copies the top-level elements from the given `IData` document to a
newly created thread-safe [java.util.Map] object, which provides
either O(1) time cost for an unsorted map, or O(log(n)) time cost
for a sorted map, for the contains, get, put, and remove operations.

Therefore the resulting [java.util.Map] is more efficient than the
default `IData` implementations, such as `com.wm.data.ISMemDataImpl` or
`com.wm.data.BasicData`, which are order preserving linked lists with
a time cost is O(n) for the same operations.

Note that the resulting [java.util.Map] requires keys to be `String`
objects.

#### Inputs:

* `$document` is an `IData` document whose top-level elements will be
  used to seed the returned [java.util.Map] object.
* `$value.class` is the optional class name identifying the class
  that values in the resulting [java.util.Map] are required to be
  instances of. Defaults to `Object`, if not specified.
* `$sorted?` is an optional boolean which when `true` indicates that
  the resulting [java.util.Map] should maintain its keys sorted in
  natural ascending order. Defaults to `false`.

#### Outputs:

* `$map` is a new [java.util.Map] object containing all the top-level
  elements from the given `$document`.

---

### tundra.collection.map.object:put

Associates the given value with the given key in the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key to be associated with the given value.
* `$key.absent?` is an optional boolean which when `true` will only
  associate the given value with the given key if the key does
  not already exist in the map. Defaults to `false`.
* `$value` is the value to be associated with the given key.

#### Outputs:

* `$map` is the given map, or a newly created map if none was given.
* `$value` is the value associated with the given key. If `$key.absent?`
  was `true` and the key already existed, this is the value already
  associated with the key.

---

### tundra.collection.map.object:remove

Removes the mapping associated the given key from the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key to be removed.
* `$value` is the optional value associated with the key. If
  specified, the key is only removed from the map if it is
  associated with this value.

#### Outputs:

* `$key.removed?` is `true` if the key value pair was removed from the
  map.
* `$value` was the value associated with the given key at the time of
  its removal.

---

### tundra.collection.map.object:replace

Replaces the value associated with the given key in the given map.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key whose associated value is to be replaced.
* `$value.old` is the optional value which is expected to already be
  associated with the key. If specified, the key's associated value
  is only replaced with the given new value if its current value is
  equal to this value. If not specified, the key's associated value
  will always be replaced with the given new value.
* `$value.new` is the new value to be associated with the key.

#### Outputs:

* `$value.replaced?` is `true` if the key exists and it's value was
  replaced with the given new value.

---

### tundra.collection.map.object:values

Returns the list of values stored in the given [java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$values` is the list of values currently stored in the given map.
* `$values.length` is the number of items in the returned `$values`
  array.

---

### tundra.collection.map.string:clear

Removes all of the mappings from the given map.

#### Inputs:

* `$map` is a [java.util.Map] object from which all key value mappings
  will be removed.

---

### tundra.collection.map.string:create

Returns a newly constructed [java.util.Map] object with String
keys and String values.

#### Inputs:

* `$sorted?` is an optional boolean which when `true` indicates that
  the resulting [java.util.Map] should maintain its keys sorted
  in natural ascending order. Defaults to `false`.

#### Outputs:

* `$map` is a newly constructed [java.util.Map] object.

---

### tundra.collection.map.string:documentify

Returns an `IData` document representation of the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$document` is an `IData` document representation of the given `$map`.

---

### tundra.collection.map.string:get

Returns the value associated with the given key in the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key whose associated value is to be returned.

#### Outputs:

* `$key.exists?` is `true` if the given key exists in the given map.
* `$value` is the value associated with the given key in the given
  [java.util.Map].

---

### tundra.collection.map.string:keys

Returns the list of keys stored in the given [java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$keys` is the list of keys currently stored in the given map.
* `$keys.length` is the number of items in the returned `$keys` array.

---

### tundra.collection.map.string:length

Returns the number of mappings stored in the given [java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$length` is the number of mappings currently stored in the given map.

---

### tundra.collection.map.string:mapify

Copies the top-level elements from the given `IData` document to a
newly created thread-safe [java.util.Map] object, which provides
either O(1) time cost for an unsorted map, or O(log(n)) time cost
for a sorted map, for the contains, get, put, and remove operations.

Therefore the resulting [java.util.Map] is more efficient than the
default `IData` implementations, such as `com.wm.data.ISMemDataImpl` or
`com.wm.data.BasicData`, which are order preserving linked lists with
a time cost is O(n) for the same operations.

Note that the resulting [java.util.Map] requires both keys and
values to be String objects.

#### Inputs:

* `$document` is an `IData` document whose top-level elements will be
  used to seed the returned [java.util.Map] object. All values
  are required to be `String` objects.
* `$sorted?` is an optional boolean which when `true` indicates that
  the resulting [java.util.Map] should maintain its keys sorted in
  natural ascending order. Defaults to `false`.

#### Outputs:

* `$map` is a new [java.util.Map] object containing all the top-level
  elements from the given `$document`.

---

### tundra.collection.map.string:put

Associates the given value with the given key in the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key to be associated with the given value.
* `$key.absent?` is an optional boolean which when `true` will only
  associate the given value with the given key if the key does
  not already exist in the map. Defaults to `false`.
* `$value` is the value to be associated with the given key.

#### Outputs:

* `$map` is the given map, or a newly created map if none was given.
* `$value` is the value associated with the given key. If `$key.absent?`
  was `true` and the key already existed, this is the value already
  associated with the key.

---

### tundra.collection.map.string:remove

Removes the mapping associated the given key from the given
[java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key to be removed.
* `$value` is the optional value associated with the key. If
  specified, the key is only removed from the map if it is
  associated with this value.

#### Outputs:

* `$key.removed?` is `true` if the key value pair was removed from the
  map.
* `$value` was the value associated with the given key at the time of
  its removal.

---

### tundra.collection.map.string:replace

Replaces the value associated with the given key in the given map.

#### Inputs:

* `$map` is a [java.util.Map] object.
* `$key` is the key whose associated value is to be replaced.
* `$value.old` is the optional value which is expected to already be
  associated with the key. If specified, the key's associated value
  is only replaced with the given new value if its current value is
  equal to this value. If not specified, the key's associated value
  will always be replaced with the given new value.
* `$value.new` is the new value to be associated with the key.

#### Outputs:

* `$value.replaced?` is `true` if the key exists and it's value was
  replaced with the given new value.

---

### tundra.collection.map.string:values

Returns the list of values stored in the given [java.util.Map] object.

#### Inputs:

* `$map` is a [java.util.Map] object.

#### Outputs:

* `$values` is the list of values currently stored in the given map.
* `$values.length` is the number of items in the returned `$values`
  array.

---

### tundra.condition:choose

Evaluates the given condition against the pipeline (or optional
scope `IData` document), and returns the appropriate result.

Also supports resolving [XPath] expressions against
[org.w3c.dom.Node] objects in the top level of the given scope or
pipeline.

#### Inputs:

* `$condition` is the conditional statement to be evaluated.
  Conditional statements have the same form as when they are used in
  a flow branch step:

      condition = value comparison_op value [logical_op condition]
                | [!]value                  [logical_op condition]

  Where:
  * `value` is a fully-qualified percent delimited `IData` document key,
    such as `%a/b/c[0]%`, or a literal (double- or single-quoted)
    string, number, (forward slash delimited) regular expression, or
    `$null`.
  * `comparison_op` is one of the following comparison operators:
    * `=`
    * `==`
    * `!=`
    * `<>`
    * `>`
    * `>=`
    * `<`
    * `<=`
  * `logical_op` is one of the following logical operators:
    * `and`
    * `&&`
    * `or`
    * `||`

  Examples:
  * `%a/b/c[0]% == "xyz"`
  * `%num% == /\d\d/`
  * `%num% == 10`
  * `%something% == $null`
  * `%total% == %count%`
  * `%inString1% == "abc" and (%inNum1% < 100 or %inNum2% > 1000)`

  Refer to the Conditional Expressions section in the Integration
  Server Developer User's Guide for further details.
* `$scope` is an optional `IData` document containing the variables
  against which `$condition` will be evaluated. If not specified, the
  `$condition` will be evaluated against the pipeline.
* `$namespace` is an optional list of namespace prefixes and the URIs
  they map to, used when evaluating [XPath] expressions against
  [org.w3c.dom.Node] objects.
  * `default` is the URI for the default namespace, if applicable.
* `$result.true` is the result to be returned when `$condition`
  evaluates to `true`. If not specified or null, no `$result` is
  returned when the condition evaluates to `true`.
* `$result.false` is the result to be returned when `$condition`
  evaluates to `false`. If not specified or null, no `$result` is
  returned when the condition evaluates to `false`.

#### Outputs:

* `$result?` is the given `$result.true` when `$condition` evaluates to
  `true`, or `$result.false` when `$condition` evaluates to `false`.
  If no `$condition` was specified, this is treated as equivalent to
  evaluating to `true`.

---

### tundra.condition:evaluate

Evaluates the given condition against the pipeline (or optional
scope `IData` document).

Also supports resolving [XPath] expressions against
[org.w3c.dom.Node] objects in the top level of the given scope or
pipeline.

#### Inputs:

* `$condition` is the conditional statement to be evaluated.
  Conditional statements have the same form as when they are used in
  a flow branch step:

      condition = value comparison_op value [logical_op condition]
                | [!]value                  [logical_op condition]

  Where:
  * `value` is a fully-qualified percent delimited `IData` document key,
    such as `%a/b/c[0]%`, or a literal (double- or single-quoted)
    string, number, (forward slash delimited) regular expression, or
    `$null`.
  * `comparison_op` is one of the following comparison operators:
    * `=`
    * `==`
    * `!=`
    * `<>`
    * `>`
    * `>=`
    * `<`
    * `<=`
  * `logical_op` is one of the following logical operators:
    * `and`
    * `&&`
    * `or`
    * `||`

  Examples:
  * `%a/b/c[0]% == "xyz"`
  * `%num% == /\d\d/`
  * `%num% == 10`
  * `%something% == $null`
  * `%total% == %count%`
  * `%inString1% == "abc" and (%inNum1% < 100 or %inNum2% > 1000)`

  Refer to the Conditional Expressions section in the Integration
  Server Developer User's Guide for further details.
* `$scope` is an optional `IData` document containing the variables
  against which `$condition` will be evaluated. If not specified, the
  `$condition` will be evaluated against the pipeline.
* `$namespace` is an optional list of namespace prefixes and the URIs
  they map to, used when evaluating [XPath] expressions against
  [org.w3c.dom.Node] objects.
  * `default` is the URI for the default namespace, if applicable.

#### Outputs:

* `$result?` is the boolean result of the evaluation. If no `$condition`
  was specified, `true` will be returned.

---

### tundra.configuration:all

Returns all configuration settings for all configured packages.

#### Inputs:

* `$refresh?` is an optional boolean which when `true` will force a
  refresh of the cached configurations from disk. Defaults to
  `false`.

#### Outputs:

* `$configurations` is an `IData` document containing the configuration
  settings for each configured package.

---

### tundra.configuration:clear

Removes all cached configurations from the in-memory cache.

---

### tundra.configuration:get

Returns the configuration settings associated with the given package.

This service supports the tiering of configuration settings across a
package-specific file and a server-specific file, where both files
are merged with the server-specific file taking precedence. This
approach allows for settings which are not environment-specific or
for default settings to be stored within the package itself, and for
other environment-specific settings or overridden settings to be
stored in a configuration file specific to an Integration Server
instance. Note that if this is not required, a single file can still
be used.

Package-specific settings are loaded from the following file:

    ./packages/<package>/config/package.<format>

Server-specific settings are loaded from the following file:

    ./config/packages/<package>.<format>

Where `<package>` is the name of the package in question, and
`<format>` is one of the following supported file extensions:

File Extension | File Format
-------------- | --------------------------------------------
hjson          | [Hjson]
json           | [JSON]
properties     | Java properties
values         | IData or Values encoded XML
xml            | Aribtrary or IData or Values encoded XML
yaml           | [YAML]

Package configurations are cached lazily in memory: read first from
disk on the initial invocation, and then returned from an in-memory
cache thereafter.

Prior to a configuration being cached in memory, variable
substitution `%key%` substrings found in configuration values are
automatically substituted as follows:

* Keys prefixed with `$system` are resolved against the `$system`
  document returned by `tundra.system:reflect`. This approach allows
  for server-specific global configuration values stored as
  environment variables, java system properties, or global
  variables to be included in the returned package configurations.
* All other keys are resolved against the configuration itself. This
  approach allows for values to be built using other values in the
  configuration, which can be used to reduce repetition of values.

Note that because the variable substitution occurs when the
configuration is first read from disk and cached (to optimise
performance), changes to environment variables, java system
properties, or global variables will not be reflected in the
returned package configurations until those configurations are
refreshed from disk.

#### Inputs:

* `$package` is the optional name of the package whose configuration
  is to be returned. If not specified, the package is automatically
  inferred from the invoking service.
* `$refresh?` is an optional boolean which when `true` will force a
  refresh of the cached configuration from disk. Defaults to `false`.

#### Outputs:

* `$package` is the name of the package whose configuration was
  returned.
* `$configuration` is an `IData` document containing the configuration
  settings for the given package.

---

### tundra.configuration:list

Returns all configuration settings for all configured packages.

#### Inputs:

* `$refresh?` is an optional boolean which when `true` will force a
  refresh of the cached configurations from disk. Defaults to
  `false`.

#### Outputs:

* `$configurations` is an `IData[]` document list containing the
  configuration settings for each configured package.
  * `package` is the name of the package the associated configuration
    relates to.
  * `configuration` is an `IData` document containing the configuration
    settings for the associated package.
  * `configuration.length` is the number of top-level elements in the
    `configuration` document.
* `$configurations.length` is the number of items in the returned
  `$configurations` `IData[]` document list.

---

### tundra.configuration:peek

Returns the configuration settings associated with the given package
from disk.

This service does not update the cached configuration, if any, and
therefore can be used to inspect and verify changes staged on disk
before refreshing the cached configuration using
`tundra.configuration:get`.

#### Inputs:

* `$package` is the name of the package whose configuration is to be
  read from disk and returned.

#### Outputs:

* `$configuration` is an `IData` document containing the on disk
  configuration settings for the given package.

---

### tundra.configuration:refresh

Refreshes all cached configurations from disk.

---

### tundra.content:amend

Edits the given content with the list of key value pairs specified in
`$amendments` by first parsing the content, replacing the values
associated with the given keys with those in `$amendments`, and then
emitting or serializing the amended content.

#### Inputs:

* `$content` is a string, byte array, or input stream containing the
  content to be amended.
* `$amendments` is an `IData` document list containing all the edits
  to be made to the given `$content`.
  * `key` is a fully-qualified key identifying the value in the
    parsed `$content` to be edited.
  * `value` is the value to be assigned to the item identified by
    key, and can include percent-delimited variable substitution
    strings which will be substituted prior to being inserted into
    the parsed `$content`.
  * `action` is an optional choice of merge, create, update, or
    delete. Defaults to merge if not specified.
    * `merge` will create the key and associate it with the given
      value if the key does not already exist, or update the the
      associated value if the key already exists.
    * `create` will only create the key and associate it with the
      given value if the key does not already exist.
    * `update` will only update the associated value if the key
      already exists.
    * `delete` will remove the key and previously associated value
      from the document. No value is required to be specified when
      using this action.
  * `condition` is an optional `Tundra/tundra.condition:evaluate`
    conditional statement, which is evaluated against the pipeline
    and only if the condition evaluates to true will the associated
    amended value be applied. If not specified, the amended value
    will always be applied.
* `$content.type` is the MIME media type that describes the format
  of the given `$content`.
* `$content.encoding.input` is an optional character set used to
  decode the text data if `$content` is provided as a byte array or
  input stream. Defaults to [UTF-8].
* `$content.encoding.output` is an optional character set used to
  encode the amended text data if `$content.mode.output` is a byte
  array or input stream. Defaults to [UTF-8].
* `$content.schema` is the fully-qualified name of the document
  reference or flat file schema to use when parsing `$content`.
* `$content.namespace` is a list of namespace prefixes and the URIs
  they map to, used when parsing and emitting [XML] content with
  elements in one or more namespaces.
* `$content.validate?` is an optional boolean which when `true` will
  validate the document content against the given `$content.schema`,
  and throw an exception if the content is invalid. Defaults to
  `false`.
* `$content.mode.output` is an optional choice of stream, bytes, or
  string which specifies the type of object `$content` is returned
  as. Defaults to stream.

#### Outputs:

* `$content` is the resulting amended content.

---

### tundra.content:convert

Converts a `String`, `byte[]`, or `java.io.InputStream` object to a
`String`, `byte[]`, or `java.io.InputStream` object.

#### Inputs:

* `$content` is a `String`, `byte[]`, or `java.io.InputStream` object.
  If not specified, this service does nothing.
* `$content.encoding` is the character set to use when converting from
  or to a `String`. Defaults to [UTF-8].
* `$content.mode` determines the type of object to convert the given
  `$content` to. Defaults to `stream`.
  * `stream` returns a `java.io.InputStream` object.
  * `bytes` returns a `byte[]` object.
  * `string` returns a `String` object.
  * `base64` returns a base64-encoded `String` object.

#### Outputs:

* `$content` is the converted content object.

---

### tundra.content:deliver

Delivers arbitrary content specified as a string, byte array, input
stream, [org.w3c.dom.Node] object, or `IData` document to the given
destination [URI].

Additional delivery protocols can be implemented by creating a
service named for the [URI] scheme in the folder
`Tundra/tundra.content.deliver`. Services in this folder should
implement the `Tundra/tundra.schema.content.deliver:handler`
specification.

#### Inputs:

* `$destination` is a [URI] identifying the location where the given
  `$content` should be delivered. If not specified, no delivery will
  be attempted. Supports the following delivery protocols / [URI]
  schemes:
  * `file`: writes the given content to the file specified by the
    destination [URI]. The following parameters can be specified in
    the query string:
    * `buffer` is the optional size in bytes of the in memory buffer
      used when writing the content to the file.
    * `filemode` is the optional choice of:
      * `append` will append the given content to the file if it
        already exists, or create it if it does not already exist.
      * `create` will throw an exception if the file already exists.
      * `write` will overwrite the file with the given content if it
        already exists.

    The following additional override options can be provided via the
    `$pipeline` document:
    * `$filename`: the name of the file to be written. This value
      will override the value provided in the destination [URI].

  * `ftp`: uploads the given content to the FTP server, directory and
    file specified by the destination [URI]. An example FTP [URI] is
    as follows:

        ftp://aladdin:opensesame@example.com:21/path/file?append=true&active=true&ascii=true&rename=false

    The following additional override options can be provided via
    the `$pipeline` document:
    * `$user`: the username used to log in to the FTP server.
      Defaults to the username specified in the authority section of
      the [URI].
    * `$password`: the password used to log in to the FTP server.
      Defaults to the password specified in the authority section of
      the [URI].
    * `$active`: a boolean which when `true` indicates that the
      connection to the FTP server should be in active mode.
      Defaults to `false` (passive mode).
    * `$append`: a boolean which when `true` will append the given
      content to the file, rather than overwrite it, if the file
      already exists. Defaults to `false` (overwriting).
    * `$ascii`: a boolean which when `true` indicates that the file
      transfer should be made in ascii mode. Defaults to `false`
      (binary mode).
    * `$rename` is an optional boolean which if `true` will first
      write the content to the given file name with `.tmp` suffix
      and then rename it to its specified name once written.
      Defaults to `false`, where the content is written directly to
      the file name specified in the destination [URI]. Note if the
      append option is `true`, this option has no effect.
    * `$timeout`: an optional [XML] duration string which specifies
      how long the client waits for a response from the server
      before timing out and terminating the request with an error.
      Defaults to `PT60S`.

  * `ftps`: refer to `ftp`

  * `http`: transmits the given content to the destination [URI]. The
    following additional override options can be provided via the
    `$pipeline` document:
    * `$method`: get / put / post / delete / head / trace / options
    * `$headers/*`: additional HTTP headers as required
    * `$authority/user`: the username to log on to the remote web
      server. Defaults to the username specified in the authority
      section of the [URI].
    * `$authority/password`: the password to log on to the remote web
      server. Defaults to the password specified in the authority
      section of the [URI].
    * `$timeout` is an optional [XML] duration string which specifies
      how long the client waits for a response from the server
      before timing out and terminating the request with an error.
      Defaults to `PT60S`.

  * `https`: refer to `http`

  * `jms`: sends the given content as a [javax.jms.BytesMessage] to
    the specified [JMS] alias and queue or topic. The following
    additional override options can be provided via the `$pipeline`
    document:
    * `$headers/*`: additional properties to be added to the [JMS]
      message header, which can be used for filtering by [JMS]
      subscribers.

    The following example will deliver the given content as a [JMS]
    bytes message to the JMS alias DEFAULT_IS_JMS_CONNECTION, [JMS]
    topic JMS::Temporary::Topic, with a time to live of 1 day, and
    with the default priority of 4:

        jms://DEFAULT_IS_JMS_CONNECTION?topic=JMS::Temporary::Topic&lifetime=P1D

    The following example will deliver the given content as a [JMS]
    bytes message to the [JMS] alias DEFAULT_IS_JMS_CONNECTION,
    [JMS] queue JMS::Temporary::Queue, with no expiry, and with the
    specified priority of 1:

        jms://DEFAULT_IS_JMS_CONNECTION?queue=JMS::Temporary::Queue&priority=1

  * `mailto`: sends an email, with the given content as an attachment
    if specified. An example mailto [URI] is as follows:

        mailto:bob@example.com?cc=jane@example.com&subject=Example&body=Example&body-content-type=text/plain&attachment=message.xml&compression=gzip

    The following mailto query parameters are supported:
    * `to`: optional comma-delimited list of email addressed to send
      the email to.
    * `cc`: optional comma-delimited list of email addresses to carbon
      copy the email to.
    * `bcc`: optional comma-delimited list of email addresses to blind
      carbon copy the email to.
    * `subject`: optional email subject line.
    * `body`: optional email body text, can be provided as plain text,
      or to send an HTML email email can be provided as a HTML string
      along with a `content-type` of `text/html`.
    * `body-content-type`: the MIME type of the email body content,
      such as `text/plain` for plain text or `text/html` for HTML.
      Defaults to `text/plain`.
    * `attachment`: the filename to use when attaching the content to
      the email. Defaults to `attachment.dat` if not specified.
    * `compression`: an optional choice of whether to compress the
      attachment content before attaching it to the email:
      * `none` - the attachment will not be compressed and will be
        attached as is to the email. This is the default.
      * `gzip`: the attachment is gzip compressed and attached with
        the given attachment name suffixed with the gzip extension
        `.gz`.
      * `zip`: the attachment is zip compressed and attached with the
        given attachment name suffixed with the zip extension `.zip`.

    The following additional override options can be provided via the
    `$pipeline` document:
    * `$attachment`: the attached file's name
    * `$compression`: an optional choice of whether to compress the
      attachment content before attaching it to the email:
      * `none` - the attachment will not be compressed and will be
        attached as is to the email. This is the default.
      * `gzip`: the attachment is gzip compressed and attached with
        the given attachment name suffixed with the gzip extension
        `.gz`.
      * `zip`: the attachment is zip compressed and attached with the
        given attachment name suffixed with the zip extension `.zip`.
    * `$from`: email address to send the email from
    * `$subject`: the subject line text
    * `$body`: the main text of the email
    * `$body.content.type`: the MIME type used for the email body text
      such as `text/plain` for plain text, or `text/html` for HTML for
      example. Defaults to `text/plain` if not specified.
    * `$smtp`: an SMTP [URI] specifying the SMTP server to use (for
      example, smtp://user:password@host:port), defaults to the SMTP
      server configured in the Integration Server setting
      `watt.server.smtpServer`.

  * `sap+idoc`: sends an IDoc [XML] message to an SAP system. Note
    that this delivery scheme requires the webMethods SAP adapter be
    installed. Both opaque and non-opaque URIs are allowed: opaque
    URIs are useful if the SAP Adapter alias contains characters not
    permitted in a normal domain name, such as underscores.

    An example opaque `sap+idoc` [URI] is as follows, where `sap_r3`
    is the SAP Adapter alias name, and the user and password are
    provided as query string parameters:

        sap+idoc:sap_r3?user=aladdin&password=opensesame&client=200&language=en&queue=xyz&limit=999

    An example non-opaque `sap+idoc` [URI] is as follows, where
    `sappr3` is the SAP Adapter alias name, and the user and password
    are provided in the authority section of the [URI]:

        sap+idoc://aladdin:opensesame@sappr3?client=200&language=en&queue=xyz&limit=999

    The following additional override options can be provided via the
    `$pipeline` document, and if specified will override the relevant
    parts of the destination [URI]:
    * `$user`: the username used for the SAP session. Defaults to the
      SAP Adapter alias username.
    * `$password`: the password used for the SAP session. Defaults to
      the SAP Adapter alias password.
    * `$client`: the SAP client used for the SAP session. Defaults to
      the SAP Adapter alias client.
    * `$language`: the language used for the SAP session. Defaults to
      the SAP Adapter alias language.
    * `$queue`: the optional name of the SAP system inbound queue,
      required when using queued remote function calls (qRFC).
    * `$limit`: the maximum number of IDocs to send per transaction
      (TID), used to partition the sending of a batch of IDocs across
      multiple smaller TIDs. Defaults to no limit.

  * `sftp`: uploads the given content to the [SFTP] server, directory
    and file specified by the destination [URI]. Note that [SFTP]
    delivery is only supported on Integration Server versions 9.0 and
    higher. An example [SFTP] [URI] is as follows:

        sftp://useralias/path/file?append=false&rename=true

    Where:
    * `useralias` identifies the Integration Server [SFTP] User Alias
      to be used for connecting to the destination [SFTP] server.
    * `path` is the absolute path to the directory where the file
      should be written, unless it starts with `.` to indicate a
      relative directory path, such as:

          sftp://useralias/./path/file

    * `file` is the literal name of the remote file the content will
      be written to.
    * `append` is an optional query string boolean which if `true`
      will append the content to the given file if it already exists.
      Defaults to `false` if not specified, which overwrites the
      given file if it already exists.
    * `rename` is an optional query string boolean which if `true`
      will first write the content to the given file name with `.tmp`
      suffix and then rename it to its specified name once written.
      Defaults to `false`, where the content is written directly to
      the file name specified in the destination [URI]. Note if the
      append option is `true`, this option has no effect.

* `$content` is a string, byte array, input stream, `IData` document,
  or [org.w3c.dom.Node] object containing data to be delivered to the
  `$destination` [URI]. If `$content` is provided as an `IData`
  document, it will be serialized using an emitter determined in order
  of precedence by `$content.schema` and `$content.type`. If
  `$content.schema` is specified, the type of reference determines the
  emitter to use: a document reference will use the [XML] emitter, a
  flat file schema reference will use the Flat File emitter. If
  `$content.schema` is not specified, `$content.type` is used to
  determine the most appropriate emitter for the MIME media type in
  question. If neither `$content.schema`, nor `$content.type` are
  specified, `$content` is serialized as [XML] by default.
* `$content.type` is an optional MIME media type that describes the
  format of the given content.
* `$content.encoding` is an optional character set to use when
  `$content` is provided as a string or `IData` document which is used
  to encode the text data for delivery. Defaults to [UTF-8].
* `$content.schema` is an optional fully-qualified name of the
  document reference or flat file schema used to serialize `$content`
  when provided as an `IData` document.
* `$content.namespace` is an optional list of namespace prefixes and
  the URIs they map to, used when emitting and `IData` document as
  [XML] content with elements in one or more namespaces.
* `$pipeline` is an optional `IData` document for providing arbitrary
  variables to the delivery implementation service. Variables
  provided will take precedence and override their corresponding
  values in the `$destination` [URI] where applicable. See the
  `$destination` description above for transport-specific options
  which can be provided via this `IData` document.

#### Outputs:

* `$message` is an optional response message, useful for logging,
  that may be returned by specific delivery protocols.
* `$response` is an optional response content returned by the
  delivery (for example, the HTTP response body).
* `$response.type` is an optional MIME media type describing the type
  of `$response` returned.
* `$response.context` is an optional document containing additional
  context for the returned response. For example, an HTTP delivery
  could return the response status and headers to provide additional
  context to the delivery result.

---

### tundra.content.deliver:file

The file protocol handler for the `Tundra/tundra.content:deliver`
service, which delivers arbitrary content to a `file:` [URI]
destination.

Implements the `Tundra/tundra.schema.content.deliver:handler`
specification.

---

### tundra.content.deliver:ftp

The FTP protocol handler for the `Tundra/tundra.content:deliver`
service, which delivers arbitrary content to a `ftp:` [URI]
destination.

Implements the `Tundra/tundra.schema.content.deliver:handler`
specification.

---

### tundra.content.deliver:ftps

The FTPS protocol handler for the `Tundra/tundra.content:deliver`
service, which delivers arbitrary content to a `ftps:` [URI]
destination.

Implements the `Tundra/tundra.schema.content.deliver:handler`
specification.

---

### tundra.content.deliver:http

The HTTP protocol handler for the `Tundra/tundra.content:deliver`
service, which delivers arbitrary content to an `http:` [URI]
destination.

Implements the `Tundra/tundra.schema.content.deliver:handler`
specification.

---

### tundra.content.deliver:https

The HTTPS protocol handler for the `Tundra/tundra.content:deliver`
service, which delivers arbitrary content to an `https:` [URI]
destination.

Implements the `Tundra/tundra.schema.content.deliver:handler`
specification.

---

### tundra.content.deliver:jms

The JMS protocol handler for the `Tundra/tundra.content:deliver`
service, which delivers arbitrary content to an `jms:` [URI]
destination.

Implements the `Tundra/tundra.schema.content.deliver:handler`
specification.

---

### tundra.content.deliver:mailto

The email protocol handler for the `Tundra/tundra.content:deliver`
service, which delivers arbitrary content to a `mailto:` [URI]
destination.

Implements the `Tundra/tundra.schema.content.deliver:handler`
specification.

---

### tundra.content.deliver:message

The message protocol handler for the `Tundra/tundra.content:deliver`
service, which routes arbitrary content to either the webMethods
messaging subsystem via `pub.publish:publish`, a JMS destination, or
a direct service invocation, as determined by the message format
specified in the given `message:` [URI] destination.

Refer to `Tundra/tundra.message:route` for message routing
implementation details.

Implements the `Tundra/tundra.schema.content.deliver:handler`
specification.

---

### tundra.content.deliver:sap_idoc

The SAP IDoc protocol handler for the `Tundra/tundra.content:deliver`
service, which delivers IDoc XML content to an `sap+idoc:` [URI]
destination.

Implements the `Tundra/tundra.schema.content.deliver:handler`
specification.

---

### tundra.content.deliver:sap_idoc

The SFTP protocol handler for the `Tundra/tundra.content:deliver`
service, which delivers arbitrary content to an `sftp:` [URI]
destination.

Implements the `Tundra/tundra.schema.content.deliver:handler`
specification.

---

### tundra.content:discard

Receives arbitrary (XML or flat file) content and then discards it (does
nothing with it). This is the Tundra equivalent of Unix's [/dev/null],
which is useful for successfully receiving messages that do not need to be
saved or processed.

This service is intended to be invoked by clients via HTTP or FTP.

---

### tundra.content:emit

Emits or serializes an `IData` document to a string, byte array, or
input stream.

Supports serializing to the following formats: [CSV], Flat File,
[HJSON], [JSON], [PSV], [TSV], [XLS], [XLSX], [XML], or [YAML].

#### Inputs:

* `$document` is the `IData` document to be serialized as a string,
  byte array, or input stream.
* `$content.type` is the MIME media type that describes the format of
  the resulting serialized `$content`.
* `$content.encoding` is an optional character set to use when
  encoding the resulting text data to a byte array or input stream.
  Defaults to [UTF-8].
* `$content.schema` is the fully-qualified name of the document
  reference or flat file schema to use when validating and
  serializing `$document`.
* `$content.namespace` is a list of namespace prefixes and the URIs
  they map to, used when emitting [XML] content with elements in one
  or more namespaces.
* `$content.validate?` is an optional boolean flag which when `true`
  will validate the document content against the given `$schema`, and
  throw an exception if the content is invalid. Defaults to `false`.
* `$content.mode` is an optional choice of `stream`, `bytes`,
  `string` which specifies the type of object `$content` is returned
  as. Defaults to `stream`.

#### Outputs:

* `$content` is the resulting serialization of `$document`.
* `$content.type` is the MIME media type that describes the format of
  the resulting serialized `$content`.
* `$content.encoding` is the character set that was used for
  serialization.

---

### tundra.content:parse

Parses or deserializes content specified as a string, byte array, or
input stream into an `IData` document.

Supports deserializing from the following formats: [CSV], Flat File,
[HJSON], [JSON], [PSV], [TSV], [XLS], [XLSX], [XML], or [YAML].

#### Inputs:

* `$content` is a string, byte array, [org.w3c.dom.Node] object (node
  in webMethods parlance), or input stream containing content to be
  parsed.
* `$content.type` is the MIME media type that describes the format of
  the given `$content`.
* `$content.encoding` is an optional character set to use when
  `$content` is provided as a byte array or input stream to decode
  the contained text data. Defaults to [UTF-8].
* `$content.schema` is the fully-qualified name of the document
  reference or flat file schema to be used when parsing and
  validating `$content`.
* `$content.namespace` is a list of namespace prefixes and the URIs
  they map to, used when parsing [XML] content with elements in one
  or more namespaces.
* `$content.validate?` is an optional boolean flag which when `true`
  will validate the parsed content against the given `$schema`, and
  throw an exception if the content is invalid. Defaults to `false`.

#### Outputs:

* `$document` is the resulting `IData` document representing the
  parsed `$content`.

---

### tundra.content:reject

Receives arbitrary (XML or flat file) content and then rejects it by always
returning an error to the client.

This service is intended to be invoked by clients via HTTP or FTP.

---

### tundra.content:retrieve

Retrieves arbitrary content from the given `$source` URI, and calls
the given content processing service to process it.

Additional retrieval protocols can be implemented by creating a
service named for the URI scheme in the folder
`tundra.content.retrieve`. Services in this folder must implement the
`tundra.schema.content.retrieve:handler` specification.

#### Inputs:

* `$source` is a URI identifying the location from which content is
  to be retrieved. Supports the following retrieval protocols / URI
  schemes:
  * `file`: processes each file matching the given `$source` URI with
    the given processing `$service`. The file component of the URI
    can include wildcards or globs (such as `*.txt` or `*.j?r`) for
    matching multiple files at once.

    The following example would process all `*.txt` files in the
    specified directory:

        file:////server:port/directory/*.txt

    If only files modified or updated within a specific time range
    are required to be retrieved, this can configured by adding a
    query string parameter called `updated` to the URI and specifying
    a duration range that is resolved against the current time:

        file:////server:port/directory/*.txt?updated=-PT10M..-PT5M
        file:////server:port/directory/*.txt?updated=-PT10M..
        file:////server:port/directory/*.txt?updated=..-PT5M

    In the first example above, only files updated between the last
    10 minutes and the last 5 minutes will be retrieved. In the
    second example, an endless range is specified, where all files
    updated in the last 10 minutes or newer will be retrieved. In
    the third example, a beginless range is specified, where all
    files updated 5 minutes ago or older will be retrieved.

    To ensure each file processed is not locked or being written to
    by another process, the file is first moved to a working
    directory. The name of this directory can be configured by adding
    a query string parameter called `working` to the URI, for
    example:

        file:////server:port/directory/*.txt?working=temp

    In this example, files are first moved to a subdirectory named
    `temp`. If not specified, the working directory defaults to a
    subdirectory named `working`.

    After successful processing, the file is then moved to an archive
    directory. The name of this directory can be configured by adding
    a query string parameter called `archive` to the URI, for
    example:

        file:////server:port/directory/*.txt?archive=backup

    In this example, files are moved to a subdirectory named `backup`
    after being successfully processed. If not specified, the archive
    directory defaults to a subdirectory named `archive`.

    Optionally, archived files older than a given age can be cleaned
    up automatically by the retrieve process by specifying a query
    string parameter called `purge` with an XML duration value
    representing the age an archived file must be before being
    purged, for example:

        file:////server:port/directory/*.txt?purge=P14D

    In this example, any files in the archive directory older than 14
    days will be automatically deleted by the retrieve process. If
    the query string parameter `purge` is not specified, archived
    files will not be automatically cleaned up.

  * `ftp`: processes each file matching the given `$source` URI with
    the given processing `$service`. The file component of the URI
    can include wildcards or globs (such as `*.txt`) for matching
    multiple files at once.

    The following example would process all `*.txt` files in the
    specified directory using active FTP with an ASCII transfer mode
    and a server timeout of 5 minutes:

        ftp://user:password@server:port/directory/*.txt?active=true&ascii=true&timeout=PT5M

    The following additional settings can be specified using query
    string parameters:
    * `active`: optional boolean which when `true` uses active FTP.
      Defaults to passive FTP.
    * `ascii`: optional boolean which when `true` sets the FTP
      transfer  mode to ASCII. Defaults to binary transfer mode.
    * `timeout`: optional XML duration specifying the time to wait
      for a response from the FTP server before timing out and
      terminating the request. Defaults to PT1M (1 minute).

    By default, files will first be renamed to include the suffix
    `.tmp` on the FTP server prior to being retrieved, to ensure
    no other processes have locked the file or are currently writing
    to it. To disable this feature, set the query string parameter
    `rename` to `false`:

        ftp://user:password@server:port/directory/*.txt?rename=false

  * `ftps`: refer to `ftp`.

  * `jms`: retrieves message content from the specified JMS alias
    and queue or topic.

    The following example will retrieve message content from the JMS
    alias `DEFAULT_IS_JMS_CONNECTION` topic `JMS::Temporary::Topic`:

        jms://DEFAULT_IS_JMS_CONNECTION?topic=JMS::Temporary::Topic

    The following example will retrive message content from the JMS
    alias `DEFAULT_IS_JMS_CONNECTION` queue `JMS::Temporary::Queue`:

        jms://DEFAULT_IS_JMS_CONNECTION?queue=JMS::Temporary::Queue

  * `sftp`: processes each file matching the given `$source` URI with
    the given processing `$service`. The file component of the URI
    can include wildcards or globs (such as `*.txt`) for matching
    multiple files at once. Note that SFTP retrieval is only
    supported on Integration Server versions 9.0 and higher.

    The following example would process all `*.txt` files in the
    specified directory using SFTP:

        sftp://useralias/path/*.txt

    Where:
    * `useralias` identifies the Integration Server SFTP User Alias
      to be used for connecting to the source SFTP server.
    * `path` is the absolute path to the directory where the files to
      be processed exist, unless it starts with "." to indicate a
      relative directory path, such as:

        sftp://useralias/./path/*.txt

    If only files modified or updated within a specific time range
    are required to be retrieved, this can configured by adding a
    query string parameter called `updated` to the URI and specifying
    a duration range that is resolved against the current time:

        sftp://useralias/./path/*.txt?updated=-PT10M..-PT5M
        sftp://useralias/./path/*.txt?updated=-PT10M..
        sftp://useralias/./path/*.txt?updated=..-PT5M

    In the first example above, only files updated between the last
    10 minutes and the last 5 minutes will be retrieved. In the
    second example, an endless range is specified, where all files
    updated in the last 10 minutes or newer will be retrieved. In
    the third example, a beginless range is specified, where all
    files updated 5 minutes ago or older will be retrieved.

    By default, files will first be renamed to include the suffix
    `.tmp` on the SFTP server prior to being retrieved, to ensure
    no other processes have locked the file and are currently writing
    to it. To disable this feature, set the query string parameter
    `rename` to `false`:

        sftp://useralias/./path/*.txt?rename=false

* `$service` is the fully-qualified name of the content processing
  service, which implements the
  `tundra.schema.content.retrieve:processor` specification, invoked
  to process each item of content retrieved from the `$source` URI.
* `$service.input` is the optional variable name for the retrieved
  content in the input pipeline of `$service`. Defaults to `$content`
  if not specified.
* `$limit` is an optional maximum number of content matches to be
  processed in a single execution. Defaults to 1000 if not specified.

---

### tundra.content.retrieve:file

The `file` protocol handler for the `Tundra/tundra.content:retrieve`
service, which retrieves file content for files matching the given
`$source` URI and calls the given `$service` content processing service
to process each file.

Implements the `Tundra/tundra.schema.content.retrieve:handler`
specification.

---

### tundra.content.retrieve:ftp

The `FTP` protocol handler for the `Tundra/tundra.content:retrieve`
service, which retrieves file content for files matching the given
`$source` URI and calls the given `$service` content processing service
to process each file.

Implements the `Tundra/tundra.schema.content.retrieve:handler`
specification.

---

### tundra.content.retrieve:ftps

The `FTPS` protocol handler for the `Tundra/tundra.content:retrieve`
service, which retrieves file content for files matching the given
`$source` URI and calls the given `$service` content processing service
to process each file.

Implements the `Tundra/tundra.schema.content.retrieve:handler`
specification.

---

### tundra.content.retrieve:jms

The JMS protocol handler for the `Tundra/tundra.content:retrieve`
service, which retrieves message content from the given `$source` URI
and calls the given `$service` content processing service to process
each message.

Implements the `Tundra/tundra.schema.content.retrieve:handler`
specification.

---

### tundra.content.retrieve:sftp

The `SFTP` protocol handler for the `Tundra/tundra.content:retrieve`
service, which retrieves file content for files matching the given
`$source` URI and calls the given `$service` content processing service
to process each file.

Implements the `Tundra/tundra.schema.content.retrieve:handler`
specification.

---

### tundra.content:split

One-to-many (1:N) conversion of content in one format to another
format. Calls the given splitting service, passing the parsed
`$content` as an input, and emitting the split list of `$contents`
as output.

#### Inputs:

* `$service` is the fully-qualified name of the splitting service,
  which accepts a single `IData` document and returns an `IData`
  document list, called to split the parsed `$content`.
* `$service.input` is an optional variable name to use in the input
  pipeline of the call to `$service` for the parsed `$content` IData
  document. Defaults to `$document`.
* `$service.output` is an optional variable name used to extract the
  output `IData` document list from the output pipeline of the call to
  `$service`. Defaults to `$documents`.
* `$pipeline` is an optional `IData` document containing arbitrary
  variables to be included in the input pipeline of the invocation of
  `$service`.
* `$content` is a `String`, `byte[]`, or `java.io.InputStream` object
  containing content to be split.
* `$content.type.input` is the MIME media type that describes the
  format of the given `$content`.
* `$content.type.output` is the MIME media type that describes the
  format of the resulting serialized split content, if all split
  content formats are alike. Alternatively, it is permissible for the
  resulting list returned by `$service` to contain unlike documents
  (documents whose MIME types are different), and in this case
  `$service` is required to return a string list `$content.types`,
  where each item in `$content.types` has a value appropriate (for
  example, "application/json" for [JSON] content) for serializing the
  corresponding indexed item in the returned document list.
* `$content.encoding.input` is an optional character set used to
  decode the text data if `$content` is provided as a `byte[]` or
  `java.io.InputStream` object. Defaults to [UTF-8].
* `$content.encoding.output` is an optional character set used to
  encode the split content if the specified `$content.mode.output`
  is `bytes` or `stream`. Defaults to [UTF-8].
* `$content.schema.input` is the fully-qualified name of the document
  reference or flat file schema to use when parsing `$content`.
* `$content.schema.output` is the fully-qualified name of the document
  reference or flat file schema to use when serializing the split
  documents. Alternatively, it is permissible for the resulting list
  returned by `$service` to contain unlike documents (documents whose
  formats are different), and in this case `$service` is required to
  return a string list `$schemas`, where each item in `$schemas` has a
  value appropriate (document reference for [XML], flat file schema
  for Flat Files) for serializing the corresponding indexed item in
  the returned document list.
* `$content.namespace.input` is a list of namespace prefixes and the
  URIs they map to, used when parsing [XML] content with elements in
  one or more namespaces.
* `$content.namespace.output` is a list of namespace prefixes and the
  URIs they map to, used when emitting [XML] content with elements in
  one or more namespaces. Alternatively, it is permissible for the
  resulting list returned by `$service` to contain unlike documents
  (documents whose MIME types are different), and in this case
  `$service` is required to return an `IData` document list
  `$namespaces`, where each item in `$namespaces` declares the
  namespace prefixes and URIs appropriate for serializing the
  corresponding indexed item in the returned document list.
* `$content.validate.input?` is an optional boolean flag which when
  `true` will validate the input content against the given
  `$schema.input`, and throw an exception if the content is invalid.
  Defaults to `false`.
* `$content.validate.output?` is an optional boolean flag which when
  `true` will validate each output content against the appropriate
  schema, and throw an exception if the content is invalid. Defaults
  to `false`.
* `$content.mode.output` determines the type of object each item in
  `$contents` is returned as. Defaults to `stream`.
  * `stream` returns a `java.io.InputStream` object.
  * `bytes` returns a `byte[]` object.
  * `string` returns a `String` object.

#### Outputs:

* `$contents` is the resulting list of split content as a string, byte
  array, or input stream, depending on the `$mode.output` chosen.
* `$content.types` is the optional list of MIME media types returned
  by `$service` if the `$contents` list contains unlike media types.
* `$schemas` is the optional list of fully-qualified document
  references (for XML) or flat file schemas (for flat files) returned
  by `$service` if the `$contents` list contains unlike formats.

---

### tundra.content:transfer

Transfers content retrieved from the given source location directly
to the given destination location.

Uses `tundra.content:retrieve` to retrieve content from a source
location, and `tundra.content:deliver` to deliver the content to a
destination location.

For example, you can transfer files from an SFTP server to a CIFS
file server with the following inputs:

    $source = sftp://example_user_alias/inbox/*.xml
    $destination = file://example_file_server/outbox/

In this example, the source file names are appended to the
destination location prior to delivery, because the destination
location provided is a directory.

#### Inputs:

* `$source` is a URI identifying the location from which content is
  to be retrieved.

  Refer to the `tundra.content:retrieve` service documentation for
  details on the protocols supported.

* `$destination` is a URI identifying the location where the given
  `$content` should be delivered. If a file component is provided in
  the URI, this will be used as the destination file name. Otherwise,
  if no file component is provided in the URI, the source file name
  will be appended to the destination URI prior to delivery so that
  the transferred content retains its file name.

  If an opaque URI with the scheme `service` is provided that has the
  name of a service in its body, this service will be invoked and is
  expected to return a dynamically determined `$destination` to
  deliver to.

  Refer to the `tundra.content:deliver` service documentation for
  details on the protocols supported.

* `$limit` is an optional maximum number of content matches to be
  processed in a single execution. Defaults to 1000 if not specified.

---

### tundra.content:translate

Performs a one-to-one (1:1) translation of content from one format to
another format, via the given translation service.

#### Inputs:

* `$service` is the fully-qualified name of the translation service,
  which accepts a single `IData` document and returns a single IData
  document, called to translate the parsed `$content`.
* `$service.input` is an optional variable name to use in the input
  pipeline of the call to `$service` for the parsed `$content` IData
  document. Defaults to `$document`.
* `$service.output` is an optional variable name used to extract the
  output `IData` document from the output pipeline of the call to
  `$service`. Defaults to `$translation`.
* `$pipeline` is an optional `IData` document used as the input
  pipeline for the invocation of `$service`.
* `$content` is a `String`, `byte[]`, or `java.io.InputStream` object
  containing the content to be translated to another format.
* `$content.type.input` is the MIME media type that describes the
  format of the given `$content`.
* `$content.type.output` is the MIME media type that describes the
  format of the resulting serialized translated content.
* `$content.encoding.input` is an optional character set used to
  decode the text data if `$content` is provided as a `byte[]` or
  `java.io.InputStream` object. Defaults to [UTF-8].
* `$content.encoding.output` is an optional character set used to
  encode the translated text data if the `$content.mode.output`
  specified is a `byte[]` or `java.io.InputStream` object. Defaults to
  [UTF-8].
* `$content.schema.input` is the fully-qualified name of the document
  reference or flat file schema to use when parsing `$content`.
* `$content.schema.output` is the fully-qualified name of the document
  reference or flat file schema to use when serializing the translated
  content.
* `$content.namespace.input` is a list of namespace prefixes and the
  URIs they map to, used when parsing [XML] content with elements in
  one or more namespaces.
* `$content.namespace.output` is a list of namespace prefixes and the
  URIs they map to, used when emitting [XML] content with elements in
  one or more namespaces.
* `$content.validate.input?` is an optional boolean flag which when
  `true` will validate the input content against the given
  `$content.schema.input`, and throw an exception if the content is
  invalid. Defaults to `false`.
* `$content.validate.output?` is an optional boolean flag which when
  `true` will validate the output content against the given
  `$content.schema.output`, and throw an exception if the content is
  invalid. Defaults to `false`.
* `$content.mode.output` determines the type of object `$translation`
  is returned as. Defaults to `stream`.
  * `stream` returns a `java.io.InputStream` object.
  * `bytes` returns a `byte[]` object.
  * `string` returns a `String` object.

#### Outputs:

* `$translation` is the translated content returned as a `String`,
  `byte[]`, or `java.io.InputStream`, depending on the given
  `$content.mode.output` value.

---

### tundra.csv:emit

Serializes an `IData` document containing a list of records as a [CSV]
formatted string, byte array, or input stream.

#### Inputs:

* `$document` is the `IData` document containing a list of records to be
  serialized as a [CSV] string, byte array, or input stream.
  * `recordWithNoID` is the `IData[]` document list of records to be
    serialized, or a `String[]` string list of values to be serialized.
* `$content.delimiter.character` is the character used to delimit
  values in the given `$content`. Defaults to `,` (comma).
* `$content.escape.character` is the character used to escape special
  characters such as the delimiter in values. Defaults to `null`,
  which disables escaping.
* `$content.quote.character` is the character used to quote values
  that contain special characters such as the delimiter or quote.
  Defaults to `"` (double quote).
* `$content.quote.mode` is an optional choice of value quoting mode.
  Defaults to the `minimal` mode.
  * `all`: quotes all values
  * `minimal`: quotes values which contain special characters such as
    the delimiter or quote character.
  * `non-numeric`: quotes all non-numeric values.
  * `none`: does not quote any values.
* `$content.header?` is an optional boolean indicating if the first
  row emitted should be a header row containing column heading.
  Defaults to `true`.
* `$content.headings` is an optional list of column headings to use in
  the header row, if applicable. If not specified, the IData keys
  are used as the column headings.
* `$content.encoding` is an optional character set to use when
  encoding the resulting text data to a byte array or input stream.
  Defaults to [UTF-8].
* `$content.mode` is an optional choice of stream, bytes, or string
  which specifies the type of object `$content` is returned as.
  Defaults to stream.

#### Outputs:

* `$content` is the resulting serialization of the records in `$document`
  as [CSV] content.

---

### tundra.csv:parse

Parses [CSV] content specified as a string, byte array, or input
stream into an IData document containing a list of records.

#### Inputs:

* `$content` is a string, byte array, or input stream containing [CSV]
  content to be parsed.
* `$content.delimiter.character` is the character used to delimit
  values in the given `$content`. Defaults to `,` (comma).
* `$content.escape.character` is the character used to escape special
  characters such as the delimiter in values. Defaults to `null`,
  which disables escaping.
* `$content.quote.character` is the character used to quote values
  that contain special characters such as the delimiter or quote.
  To disable quoting completely, set this to the literal string
  `$null` (not including the backticks). Defaults to `"` (double
  quote).
* `$content.quote.mode` is an optional choice of value quoting mode.
  Defaults to the `minimal` mode.
  * `all`: quotes all values
  * `minimal`: quotes values which contain special characters such as
    the delimiter or quote character.
  * `non-numeric`: quotes all non-numeric values.
  * `none`: does not quote any values.
* `$content.header?` is an optional boolean indicating if the first
  row should be read as a header row containing column names. If
  `false`, columns will be named for their position with a one-based
  index. Defaults to `true`.
* `$content.encoding` is an optional character set to use when
  `$content` is provided as a byte array or input stream to decode the
  contained text data. Defaults to [UTF-8].

#### Outputs:

* `$document` is the resulting `IData` document containing a list of
  records representing the parsed `$content`.
  * `recordWithNoID` is the resulting `IData[]` document list of records.

---

### tundra.datetime:add

Adds a duration of time to the given arbitrarily specified datetimes,
formatted according to the given patterns.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime.input` is an `IData` document containing arbitrarily
  specified datetime strings to add the duration to.
* `$datetime.pattern` is an optional datetime pattern that all the
  `$datetime.input` datetime strings conform to. Defaults to an
  [ISO8601] XML datetime.
* `$duration` is the duration to be added to all the datetime strings
  specified in the given `$datetime.input`.
* `$duration.pattern` is an optional duration pattern that the given
  `$duration` conforms to. Defaults to an [ISO8601] XML duration.

#### Outputs:

* `$datetime.output` is an `IData` document containing the given
  datetime strings with the given `$duration` added.

---

### tundra.datetime:compare

Compares two datetime strings, formatted according to the given
patterns, indicating their position in time relative to one another.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime.first` is the datetime string to be compared to
  `$datetime.second`.
* `$datetime.first.pattern` is an optional datetime pattern that
  `$datetime.first` conforms to. Defaults to an [ISO8601] XML datetime.
* `$datetime.second` is the datetime string to be compared to
  `$datetime.first`.
* `$datetime.second.pattern` is an optional datetime pattern that
  `$datetime.second` conforms to. Defaults to an [ISO8601] XML datetime.

#### Outputs:

* `$before?` is a boolean flag indicating if `$datetime.first` is an
  earlier instant in time than `$datetime.second`.
* `$equal?` is a boolean flag indicating if `$datetime.first` and
  `$datetime.second` represent the same instant in time.
* `$after?` is a boolean flag indicating if `$datetime.first` is a later
  instant in time than `$datetime.second`.

---

### tundra.datetime:concatenate

Concatenates individual date and time strings into a single datetime
string.

#### Inputs:

* `$date` is a date string.
* `$date.pattern` is an optional date pattern that `$date` conforms to,
  that will be used to parse the date string. Defaults to an
  [ISO8601] XML datetime.
* `$time` is a time string.
* `$time.pattern` is an optional time pattern that `$time` conforms to,
  that will be used to parse the time string. Defaults to an
  [ISO8601] XML datetime.
* `$datetime.pattern` is an optional datetime pattern that will be
  used to format the returned `$datetime` string. Defaults to an
  [ISO8601] XML datetime.
* `$timezone.input` is an optional [java.util.TimeZone] ID, or a
  (+|-)HH:mm time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or Z for UTC, or local for the default localhost
  time zone identifying the time zone the input `$time` will
  be parsed with. If specified, this time zone will override any
  zone specified in the `$time` string itself.
* `$timezone.output` is an optional [java.util.TimeZone] ID, or a
  (+|-)HH:mm time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or Z for UTC, or local for the default localhost
  time zone identifying the time zone the returned `$datetime` will
  be formatted with.

#### Outputs:

* `$datetime` is a datetime string that concatenates the inputs.

---

### tundra.datetime:duration

Returns the duration between two datetimes.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime.start` is the datetime string representing the starting
  instant for calculating the duration of time.
* `$datetime.start.pattern` is an optional datetime pattern that
  `$datetime.start` conforms to. Defaults to an [ISO8601] XML datetime.
* `$datetime.end` is the datetime string representing the ending
  instant for calculating the duration of time.
* `$datetime.end.pattern` is an optional datetime pattern that
  `$datetime.end` conforms to. Defaults to an [ISO8601] XML datetime.
* `$duration.pattern` is an optional duration pattern that the output
  `$duration` will be formatted as. Defaults to an [ISO8601] XML
  duration.

#### Outputs:

* `$duration` is the duration of time between `$datetime.start` and
  `$datetime.end`, formatted according to the given `$duration.pattern`.

---

### tundra.datetime:earlier

Subtracts a duration of time from the current datetime, formatted according
to the given pattern.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime.pattern` is an optional datetime pattern that `$datetime`
  is formatted as. Defaults to an [ISO8601] XML datetime.
* `$duration` is the duration to be subtracted from the current datetime.
* `$duration.pattern` is an optional duration pattern that `$duration`
  conforms to. Defaults to an [ISO8601] XML duration.
* `$timezone` is an optional [java.util.TimeZone] ID, or a
  `(+|-)HH:mm` time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or `Z` for UTC, or `local` for the default localhost time
  zone identifying the time zone the returned `$datetime` will be
  formatted with.

#### Outputs:

* `$datetime` is the current datetime minus the given `$duration` in the
  given `$timezone`.

---

### tundra.datetime:emit

Returns the given object as a string formatted according to the
given datetime pattern.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime.object` is either a [java.lang.Number], [java.util.Date],
  or [java.util.Calendar] object to be formatted as a datetime
  string. When a [java.lang.Number] is specified, it is interpreted
  as a milliseconds since epoch value.
* `$pattern` is an optional datetime pattern that will be used to
  format the resulting `$datetime` string. Defaults to an [ISO8601] XML
  datetime.
* `$timezone` is an optional [java.util.TimeZone] ID, or a `(+|-)HH:mm`
  time zone offset, or an XML duration string representing a time
  zone offset, or a raw millisecond time zone offset, or `Z` for UTC,
  or `local` for the default localhost time zone identifying the time
  zone the returned `$datetime` will be formatted with.

#### Outputs:

* `$datetime` is the given object formatted as a string according to
  the given `$pattern` in the given `$timezone`.

---

### tundra.datetime:format

Formats arbitrarily specified datetime strings that conforms to the
input pattern, according to the output pattern.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime.input` is an `IData` document containing arbitrarily
  specified datetime strings to be reformatted to a different
  pattern.
* `$pattern.input `is an optional datetime pattern that the datetime
  strings conform to, that will be used to parse the datetime
  strings. Defaults to an [ISO8601] XML datetime.
* `$patterns.input` is an optional list of datetime patterns that
  the input datetime strings might conform to, useful when the exact
  pattern is not known. A parse is attempted for each pattern until
  the first successful parse, or until all patterns have been tried
  in which case an unparseable datetime exception will be thrown.
* `$pattern.output` is an optional datetime pattern that will be used
  to format the resulting datetime strings. Defaults to an [ISO8601]
  XML datetime.
* `$timezone.input` is an optional [java.util.TimeZone] ID, or a
  (+|-)HH:mm time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or `Z` for UTC, or `local` for the default localhost time zone
  identifying the time zone the input datetime strings will be
  parsed with. If specified, this time zone will override any zone
  specified in the datetime string itself.
* `$timezone.output` is an optional [java.util.TimeZone] ID, or a
  (+|-)HH:mm time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or `Z` for UTC, or `local` for the default localhost
  time zone identifying the time zone the returned datetime strings
  will be formatted with.

#### Outputs:

* `$datetime.output` is an `IData` document containing the datetime
  strings reformatted according to the given `$pattern.output` in the
  given `$timezone.output`.

---

### tundra.datetime:later

Adds a duration of time to the current datetime, formatted according
to the given pattern.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime.pattern` is an optional datetime pattern that `$datetime`
  is formatted as. Defaults to an [ISO8601] XML datetime.
* `$duration` is the duration to be added to the current datetime.
* `$duration.pattern` is an optional duration pattern that `$duration`
  conforms to. Defaults to an [ISO8601] XML duration.
* `$timezone` is an optional [java.util.TimeZone] ID, or a
  `(+|-)HH:mm` time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or `Z` for UTC, or `local` for the default localhost time
  zone identifying the time zone the returned `$datetime` will be
  formatted with.

#### Outputs:

* `$datetime` is the current datetime plus the given `$duration` in
  the given `$timezone`.

---

### tundra.datetime:maximum

Returns the largest datetime from the given list of datetime strings.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using [java.text.SimpleDateFormat]
compatible patterns.

#### Inputs:

* `$datetimes` is a list of datetime strings.
* `$pattern` is an optional datetime pattern that each string in the
  `$datetimes` list conforms to, and will be used to parse the datetime
  strings. Defaults to an [ISO8601] XML datetime.

#### Outputs:

* `$datetime` is the largest datetime from the given `$datetimes` list.

---

### tundra.datetime:minimum

Returns the smallest datetime from the given list of datetime strings.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using [java.text.SimpleDateFormat]
compatible patterns.

#### Inputs:

* `$datetimes` is a list of datetime strings.
* `$pattern` is an optional datetime pattern that each string in the
  `$datetimes` list conforms to, and will be used to parse the datetime
  strings. Defaults to an [ISO8601] XML datetime.

#### Outputs:

* `$datetime` is the smallest datetime from the given `$datetimes` list.

---

### tundra.datetime:now

Returns the current datetime formatted according to the given pattern.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$pattern` is an optional datetime pattern that will be used to format
  the resulting `$datetime` string. Defaults to an [ISO8601] XML
  datetime.
* `$timezone` is an optional [java.util.TimeZone] ID, or a
  `(+|-)HH:mm` time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or `Z` for UTC, or `local` for the default localhost time
  zone identifying the time zone the returned `$datetime` will be
  formatted with.

#### Outputs:

* `$datetime` is the current datetime formatted as a string according to
  the given `$pattern` in the given `$timezone`.

---

### tundra.datetime:parse

Parses the given datetime string according to the given pattern, and
returns the resulting [java.util.Date] object.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime` is the datetime string to be parsed.
* `$pattern` is an optional datetime pattern that `$datetime` conforms
  to, and will be used to parse the datetime string. Defaults to an
  [ISO8601] XML datetime.
* `$patterns` is an optional list of datetime patterns that `$datetime` might
  conform to, useful when the exact pattern is not known. A parse is
  attempted for each pattern until the first successful parse, or until
  all patterns have been tried in which case an unparseable datetime
  exception will be thrown.
* `$timezone.input` is an optional [java.util.TimeZone] ID, or a
  `(+|-)HH:mm` time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or `Z` for UTC, or `local` for the default localhost
  time zone identifying the time zone the input `$datetime` will
  be parsed with. If specified, this time zone will override any
  zone specified in the `$datetime` string itself.

#### Outputs:

* `$datetime.object` is a [java.util.Date] object representing the same
  instant in time as the given `$datetime` string.

---

### tundra.datetime:subtract

Subtracts a duration of time from the given arbitrarily specified
datetimes, formatted according to the given patterns.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime.input` is an `IData` document containing arbitrarily
  specified datetime strings to subtract the duration from.
* `$datetime.pattern` is an optional datetime pattern that all the
  `$datetime.input` datetime strings conform to. Defaults to an
  [ISO8601] XML datetime.
* `$duration` is the duration to be subtracted from all the datetime
  strings specified in the given `$datetime.input`.
* `$duration.pattern` is an optional duration pattern that the given
  `$duration` conforms to. Defaults to an [ISO8601] XML duration.

#### Outputs:

* `$datetime.output` is an `IData` document containing the given
  datetime strings with the given `$duration` subtracted.

---

### tundra.datetime:timezone

Returns information about the time zone that the given datetime
string is associated with.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime` is the datetime string whose time zone is to be returned.
* `$pattern` is an optional datetime pattern that `$datetime` conforms
  to, and will be used to parse the datetime string. Defaults to an
  [ISO8601] XML datetime.

#### Outputs:

* `$timezone` is an `IData` document describing the time zone
  associated with the given datetime string.
  * `id` is the [java.util.TimeZone] ID associated with the
    time zone.
  * `name` is a short name associated with the time zone.
  * `description` is a long name associated with the time zone.
  * `utc.offset` is the historically correct XML duration added
    to UTC time to get local time in this time zone for the
    given `$datetime` instant. If daylight savings time is in
    effect for the given `$datetime` instant, then this value was
    adjusted to include the daylight savings offset.
  * `dst.used?` is a boolean indicating if daylight savings
    time is used by this time zone.
  * `dst.active?` is a boolean indicating if daylight savings
    time is in effect for the given `$datetime` instant.
  * `dst.offset` is the XML duration added to the UTC offset
    when daylight savings time is in effect.

---

### tundra.datetime:today

Returns the current date at midnight formatted according to the
given pattern.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$pattern` is an optional datetime pattern that will be used to format
  the resulting `$datetime` string. Defaults to an [ISO8601] XML
  datetime.
* `$timezone` is an optional [java.util.TimeZone] ID, or a
  (+|-)HH:mm time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or Z for UTC, or local for the default localhost time
  zone identifying the time zone the returned `$datetime` will be
  formatted with.

#### Outputs:

* `$datetime` is the current date formatted as a string according to
  the given `$pattern` in the given `$timezone`.

---

### tundra.datetime:tomorrow

Returns the current date at midnight plus one day formatted
according to the given pattern.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$pattern` is an optional datetime pattern that will be used to format
  the resulting `$datetime` string. Defaults to an [ISO8601] XML
  datetime.
* `$timezone` is an optional [java.util.TimeZone] ID, or a
  (+|-)HH:mm time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or Z for UTC, or local for the default localhost time
  zone identifying the time zone the returned `$datetime` will be
  formatted with.

#### Outputs:

* `$datetime` is the current date plus one day formatted as a string
  according to the given `$pattern` in the given `$timezone`.

---

### tundra.datetime:validate

Returns `true` if the given datetime string conforms to the given pattern.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetime` is the datetime string to be validated.
* `$pattern` is an optional datetime pattern that `$datetime` is
  required to conform to. Defaults to an [ISO8601] XML datetime.
* `$raise?` is an optional boolean flag indicating if an exception
  should be thrown if `$datetime` is found not to conform to `$pattern`.
  Defaults to `false`.

#### Outputs:

* `$valid?` is a boolean flag indicating the given `$datetime` conforms
  to, and can be parsed by, the given `$pattern`.

---

### tundra.datetime:yesterday

Returns the current date at midnight minus one day formatted
according to the given pattern.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$pattern` is an optional datetime pattern that will be used to format
  the resulting `$datetime` string. Defaults to an [ISO8601] XML
  datetime.
* `$timezone` is an optional [java.util.TimeZone] ID, or a
  (+|-)HH:mm time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or Z for UTC, or local for the default localhost time
  zone identifying the time zone the returned `$datetime` will be
  formatted with.

#### Outputs:

* `$datetime` is the current date minus one day formatted as a string
  according to the given `$pattern` in the given `$timezone`.

---

### tundra.datetime:within

Returns whether the given datetime is within a given inclusive range.

The range can be specified as either a datetime range provided as a
start and end datetime, or as a duration range and epoch datetime.

When a duration range is provided, it can be specified as either a
start and end duration, or as a duration range string in the
following format:

    start..end

For example, the following duration range string specifies a range
from -10 minutes to -10 seconds:

    -PT10M..-PT10S

#### Inputs:

* `$datetime` is the datetime to check if its within the given range.
* `$datetime.pattern` is the optional pattern to use to parse the
  given `$datetime`.
* `$datetime.range.start` is an optional datetime range start.
* `$datetime.range.end` is an optional datetime range end.
* `$duration.range` is an optional duration range string.
* `$duration.range.start` is an optional duration range start.
* `$duration.range.end` is an optional duration range end.
* `$duration.range.epoch` is an optional datetime against which the
  given duration range is resolved to absolute time. Defaults to
  the current time.

#### Outputs:

* `$datetime.within.range?` is `true` if the given `$datetime` is
  within the specified inclusive range.

---

### tundra.decimal:absolute

Returns the absolute value of the given decimal.

#### Inputs:

* `$decimal` is a signed decimal value.

#### Outputs:

* `$decimal` is the given decimal value unsigned.

---

### tundra.decimal:add

Adds the given decimals, returning the result optionally rounded
to the given precision (number of decimal places) using the
given [rounding algorithm].

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as a decimal number using the [java.math.BigDecimal grammar].
* `$precision` is an optional number of decimal places to be
  preserved in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$result` is the result of adding the given decimal values
  and rounding to the given precision using the nominated
  [rounding algorithm].

---

### tundra.decimal:average

Returns the average value of the given list of decimals, optionally
rounded to the given precision (number of decimal places) using the
given [rounding algorithm].

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as a decimal number using the [java.math.BigDecimal grammar].
* `$precision` is an optional number of decimal places to be
  preserved in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$average` is the result of averaging the given list of decimals
  and rounding to the given precision using the nominated
  [rounding algorithm].

---

### tundra.decimal:deviate

Returns the standard deviation and related statistics for the given
list of decimals, optionally rounded to the given precision (number
of decimal places) using the given [rounding algorithm].

Note that only double-precision decimal numbers are supported by
this service.

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as a decimal number using the [java.math.BigDecimal grammar].
* `$precision` is an optional number of decimal places to be
  preserved in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$sample.deviation` is the standard deviation of the given list of
  decimals rounded to the given precision using the nominated
  [rounding algorithm].
* `$sample.variance` is the statistical variance of the given list of
  decimals rounded to the given precision using the nominated
  [rounding algorithm].
* `$sample.mean` is the statistical average of the given list of
  decimals rounded to the given precision using the nominated
  [rounding algorithm].
* `$sample.count` is the number of samples used for calculating the
  above values.

---

### tundra.decimal:divide

Divides one decimal by another, returning the result optionally
rounded to the given precision (number of decimal places) using
the given [rounding algorithm].

#### Inputs:

* `$dividend` is the numerator decimal for the division.
* `$divisor` is the denominator decimal for the division.
* `$precision` is an optional number of decimal places to be
  preserved in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$result` is the result of dividing the given values and rounding
  to the given precision using the nominated [rounding algorithm].

---

### tundra.decimal:emit

Returns an string representation of the given decimal object.

#### Inputs:

* `$object` is either a `java.math.BigDecimal`, `java.math.BigInteger`,
  `java.lang.Double`, `java.lang.Float`, `java.lang.Integer`, or
  `java.lang.Long` object.
* `$pattern` is an optional [java.text.DecimalFormat] pattern that will
  be used to format the resulting decimal string. If not specified,
  defaults to [java.math.BigDecimal grammar].

#### Outputs:

* `$string` is the resulting string representation of the decimal.

---

### tundra.decimal:format

Formats arbitrarily specified number strings that conform to the
input pattern, according to the output pattern.

#### Inputs:

* `$decimal.input` is an `IData` document containing arbitrarily
  specified string representations of numbers.
* `$pattern.input` is an optional [java.text.DecimalFormat] pattern
  that the given strings conform to, and if specified will be used
  to parse the strings. If not specified, defaults to
  [java.math.BigDecimal grammar].
* `$patterns.input` is an optional list of [java.text.DecimalFormat]
  patterns that the given strings might conform to, which is useful
  when the exact pattern is not known. A parse is attempted for each
  pattern until the first successful parse, or until all patterns
  have been tried in which case an unparseable number exception
  will be thrown.
* `$pattern.output` is an optional [java.text.DecimalFormat] pattern
  that will be used to format the resulting number strings. If not
  specified, defaults to [java.math.BigDecimal grammar].

#### Outputs:

* `$decimal.output` contains the resulting formatted number strings.

---

### tundra.decimal:maximum

Returns the maximum value from the given decimal values, optionally
rounded to the given precision (number of decimal places) using the
given [rounding algorithm].

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as a decimal number using the [java.math.BigDecimal grammar].
* `$precision` is an optional number of decimal places to be
  preserved in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$maximum` is the largest value from the given decimal values,
  rounded to the given precision using the nominated
  [rounding algorithm].

---

### tundra.decimal:minimum

Returns the minimum value from the given decimal values, optionally
rounded to the given precision (number of decimal places) using the
given [rounding algorithm].

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as a decimal number using the [java.math.BigDecimal grammar].
* `$precision` is an optional number of decimal places to be
  preserved in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$minimum` is the smallest value from the given decimal values,
  rounded to the given precision using the nominated
  [rounding algorithm].

---

### tundra.decimal:multiply

Multiplies the given decimals, returning the result optionally
rounded to the given precision (number of decimal places) using
the given [rounding algorithm].

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as a decimal number using the [java.math.BigDecimal grammar].
* `$precision` is an optional number of decimal places to be
  preserved in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$result` is result of multiplying the given decimal values
  and rounding to the given precision using the nominated
  [rounding algorithm].

---

### tundra.decimal:negate

Returns the negative value of the given decimal (-x).

#### Inputs:

* `$decimal` is a decimal value.

#### Outputs:

* `$decimal` is the given decimal value multiplied
  by minus one.

---

### tundra.decimal:parse

Returns an decimal object by parsing the given string.

#### Inputs:

* `$string` is a string representation of a decimal number, formatted
  according to [java.math.BigDecimal grammar].
* `$pattern` is an optional [java.text.DecimalFormat] pattern that the
  given string conforms to, and if specified will be used to parse the
  string. If not specified, defaults to [java.math.BigDecimal grammar].
* `$patterns` is an optional list of [java.text.DecimalFormat] patterns
  that the given string might conform to, which is useful when the exact
  pattern is not known. A parse is attempted for each pattern until the
  first successful parse, or until all patterns have been tried in which
  case an unparseable decimal exception will be thrown.
* `$class` is an optional Java class name that determines the type of
  object returned, a choice of either java.math.BigDecimal,
  `java.math.BigInteger`, `java.lang.Double`, `java.lang.Float`,
  `java.lang.Long`, `java.lang.Integer`, `java.lang.Short`, or `java.lang.Byte`.
  Defaults to `java.math.BigDecimal`, if not specified.


#### Outputs:

* `$object` is the resulting Java object representing the parsed decimal.

---

### tundra.decimal:power

Raises the given decimal to the power of the given exponent (d^e),
optionally rounded to the given precision (number of decimal places)
using the given [rounding algorithm].

#### Inputs:

* `$base` is a decimal value to be raised to the power of the given
  exponent.
* `$exponent` is the value to raise the base by. If not specified,
  defaults to `1`.
* `$precision` is an optional number of decimal places to be
  preserved in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$result` is result of raising the given base by the
  given exponent, and rounding to the given precision
  using the nominated [rounding algorithm].

---

### tundra.decimal:random

Returns a cryptographically strong randomly generated decimal using
an instance of the [java.security.SecureRandom] class.

#### Inputs:

* `$decimal.random.class` is a choice of either [java.lang.Double],
  or [java.lang.Float], which determines the class of the output
  returned. Defaults to [java.lang.Double].

#### Outputs:

* `$decimal.random` is the randomly generated decimal provided as
  an instance of the specified class.

---

### tundra.decimal:round

Rounds the given decimal to given precision (number of decimal
places) using the given [rounding algorithm].

#### Inputs:

* `$decimal` is a decimal value to be rounded.
* `$precision` is an optional number of decimal places to be
  preserved in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$decimal` is the given decimal value rounded to the given
  precision using the nominated [rounding algorithm].

---

### tundra.decimal:subtract

Subtracts one decimal from another, returning the result optionally
rounded to the given precision (number of decimal places) using the
given [rounding algorithm].

#### Inputs:

* `$minuend` is the decimal value to be subtracted from.
* `$subtrahend` is the decimal value to subtract.
* `$precision` is an optional number of decimal places to be
  preserved in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$result` is result of subtracting the given values and rounding
  to the given precision using the nominated [rounding algorithm].

---

### tundra.decimal:validate

Returns `true` if the given string can be parsed as a decimal.

#### Inputs:

* `$decimal` is a string to be validated as a decimal.
* `$raise?` is an optional boolean indicating if an exception
  should be thrown if the given string is not a valid
  decimal. Defaults to `false`.

#### Outputs:

* `$valid?` is a boolean indicating if the given string is
  a valid decimal number.

---

### tundra.directory:compact

Deletes all empty child directories recursively from the given
directory, and optionally deletes the given directory itself if
also empty after compacting.

#### Inputs:

* `$directory` is a relative or absolute path or file: [URI] to be
  compacted.
* `$delete?` is an optional boolean flag which when `true` will delete
  the given `$directory` itself if it is emtpy after compaction.
  Defaults to `false`.
* `$raise?` is an optional boolean flag which when `true` will throw
  an exception if the deletion of any directories fails. Defaults
  to `false`.

---

### tundra.directory:create

Creates a new directory.

#### Inputs:

* `$directory` is a string specifying a relative or absolute path
  or file: [URI]; all directories on this path will be created.
* `$raise?` is an optional boolean flag indicating if an exception
  should be thrown if the directory already exists. Defaults to
  `false`.

---

### tundra.directory:exists

Returns `true` if the given directory exists.

#### Inputs:

* `$directory` is a string specifying a relative or absolute path
  or file: [URI].

#### Outputs:

* `$exists?` is `true` if `$directory` exists and is a directory.

---

### tundra.directory:gzip

Compresses all files matching the specified criteria from the given
directory using the gzip algorithm, and optionally recursively from
all child directories.

#### Inputs:

* `$directory` is the directory in which files will be compressed,
  specified as either a relative or absolute file path or file
  [URI].
* `$duration` is an optional duration of time representing the age of
  files to be compressed. For example, a duration of `P1D` will
  compress all files that were last modified 24 hours ago or earlier.
* `$duration.pattern` is an optional pattern describing the type of
  duration specified by the `$duration` string. Defaults to an
  [ISO8601] XML string.
* `$filter.inclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  include files with matching names for compression.
* `$filter.exclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  exclude files with matching names from compression.
* `$filter.type` is an optional choice if either 'regular expression',
  'wildcard', or 'literal', which determines the type of filter
  patterns provided. Defaults to 'regular expression'.
* `$recurse?` is an optional boolean flag indicating if files in child
  directories should also compressed. Defaults to `false`.
* `$replace?` is an optional boolean flag indicating if files should
  be deleted once they are compressed. Defaults to `false`.

#### Outputs:

* `$count` is the number of files compressed by this service.

---

### tundra.directory:join

Returns a new file path [URI], given a parent path and a child path
or file name.

#### Inputs:

* `$parent` is a string specifying a relative or absolute parent path.
* `$children` is a string list specifying relative child paths or file
  names.

#### Outputs:

* `$path` is the path of the joined parent and children items.
* `$uri` is a `file:` [URI] representing the path of the joined parent
  and children items.

---

### tundra.directory:list

Lists a directory, optionally filtering based on the given patterns,
which can be either a regular expression (for example, `\w+\.\w+`)
or a wildcard expression (for example, `*.txt`), depending on the
selected mode.

#### Inputs:

* `$directory` is a relative or absolute path or `file:` [URI] to be
  listed.
* `$filter.inclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  include objects with matching names in the resulting directory
  listing.
* `$filter.exclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  exclude objects with matching names from the resulting directory
  listing.
* `$filter.type` is an optional choice if either 'regular expression',
  'wildcard', or 'literal', which determines the type of filter
  patterns provided. Defaults to 'regular expression'.
* `$recurse?` is an optional boolean flag indicating if subdirectories
  should also be listed recursively. Defaults to `false`.

#### Outputs:

* `$directories` is a list of all subdirectories, optionally filtered.
* `$directories.length` is the number of items in the `$directories`
  list.
* `$files` is a list of all files in the given `$directory`, optionally
  filtered.
* `$files.length` is the number of items in the `$files` list.

---

### tundra.directory:ls

Returns a sorted directory listing with no additional processing.

This is useful for when performance takes priority over ease of use.
For example, when the directory contains hundreds of thousands or
more files.

#### Inputs:

* `$directory` is a relative or absolute path or file: [URI] to be
  listed.

#### Outputs:

* `$list` is a sorted list of the names of all items in the directory.
* `$list.length` is the number of items in the returned `$list`.

---

### tundra.directory:normalize

Returns the canonical file: URI that represents the given directory.

#### Inputs:

* `$directory` is a relative or absolute path, or file: [URI], to be
  normalized.

#### Outputs:

* `$directory` is the equivalent canonical file: [URI] representing
  the directory.

---

### tundra.directory:purge

Deletes all files that match the given criteria from the given
directory, and optionally recursively from all child directories.

#### Inputs:

* `$directory` is the directory from which files will be deleted,
  specified as either a relative or absolute file path or file
  [URI].
* `$duration` is an optional duration of time representing the age of
  files to be deleted. For example, a duration of P1D will delete
  all files that were last modified 24 hours ago or earlier.
* `$duration.pattern` is an optional pattern describing the type of
  duration specified by the `$duration` string. Defaults to an
  [ISO8601] XML string.
* `$filter.inclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  include files with matching names for deletion.
* `$filter.exclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  exclude files with matching names from deletion.
* `$filter.type` is an optional choice if either 'regular expression',
  'wildcard', or 'literal', which determines the type of filter
  patterns provided. Defaults to 'regular expression'.
* `$recurse?` is an optional boolean flag indicating if files in child
  directories should also deleted. Defaults to `false`.

#### Outputs:

* `$count` is the number of files deleted by this service.

---

### tundra.directory:reflect

Returns useful details about the given directory.

#### Inputs:

* `$directory` is the name of the directory to return details about,
  specified as either a relative or absolute file path or file: [URI].

#### Outputs:

* `$directory.properties` is an `IData` document containing the following
  details about the given `$directory`:
  * `exists?` is a boolean flag indicating if the given directory exists.
  * `parent` is the canonical file: [URI] that represents the parent
    directory that contains the given directory.
  * `name` is the name component not including the path of the given
    directory.
  * `modified` is the last modified datetime of the given directory.
  * `uri` is the canonical file: [URI] that represents the given
    directory.

---

### tundra.directory:remove

Deletes the given directory, and optionally all child files and
directories recursively if desired.

#### Inputs:

* `$directory` is a relative or absolute path or file: [URI] to be
  deleted.
* `$recurse?` is a boolean flag indicating that all child files and
  directories should be recursively deleted also. If `false`, and
  the directory is not empty, an exception will be thrown. Defaults
  to `false`.

---

### tundra.directory:rename

Renames the source directory to the target directory name. If the
target already exists, an exception will be thrown.

#### Inputs:

* `$directory.source` is a relative or absolute path or file: [URI]
  to be renamed.
* `$directory.target` is a relative or absolute path of file: [URI]
  to rename `$directory.source` to.

---

### tundra.directory:size

Returns the total size in bytes of all files in the given directory.

#### Inputs:

* `$directory` is a relative or absolute path, or file: [URI], to
  calculate the total size of.
* `$recurse?` is an optional boolean flag which when `true` will include
  the size of all child directories in the calculation. Defaults to
  `false`.

#### Outputs:

* `$size` is the total size in bytes of all files in the given
  directory.

---

### tundra.directory:squeeze

Reduces the total size in bytes of the given directory to the given
size by progressively deleting files in order of least recently
modified.

#### Inputs:

* `$directory` is a relative or absolute path, or `file:` [URI], in which
  files will be deleted to reduce the total size of the directory to
  the required size.
* `$size.required` is the required size in bytes of the given directory.
  If the directory's size is less that this size, no files will be
  deleted. If the directory's size is greater than this size, files
  will be progressively deleted in order of least recently modified
  until the directory size is equal to or less than the required size.
* `$filter.inclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  include files with matching names for deletion.
* `$filter.exclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  exclude files with matching names from deletion.
* `$filter.type` is an optional choice if either 'regular expression',
  'wildcard', or 'literal', which determines the type of filter
  patterns provided. Defaults to 'regular expression'.
* `$recurse?` is an optional boolean flag which when `true` will include
  all child directories in the squeezing process. Defaults to `false`.

#### Outputs:

* `$size.squeezed` is the resulting size in bytes of the directory
  after it has been squeezed.

---

### tundra.directory:tar

Adds files in the given directory into a new [tar] archive, and
optionally compressed with [gzip].

#### Inputs:

* `$directory` is the directory containing files to be archived,
  specified as either a relative or absolute file path or file
  [URI].
* `$filter.inclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  include files with matching names in the archive.
* `$filter.exclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  exclude files with matching names from the archive.
* `$filter.type` is an optional choice if either 'regular expression',
  'wildcard', or 'literal', which determines the type of filter
  patterns provided. Defaults to 'regular expression'.
* `$path.parent?` is an optional boolean flag indicating if the
  archive file name paths should include the given `$directory` name
  as the top-level path name. Defaults to `false`.
* `$recurse?` is an optional boolean flag indicating if files in child
  directories should also be archived. Defaults to `false`.
* `$gzip?` is an optional boolean flag indicating if the resulting
  [tar] archive should also be compressed with [gzip].
* `$mode` is an optional choice of 'stream', 'bytes', or
  'string', which determines the type of object returned by
  this service. If the 'string' mode is chosen, the resulting
  zipped data is base64-encoded. Defaults to 'stream'.

#### Outputs:

* `$directory.tar` is the resulting [tar] archive containing the
  files from the given `$directory`, optionally compressed with [gzip].

---

### tundra.directory:zip

Compresses files in the given directory into a [zip] archive.

#### Inputs:

* `$directory` is the directory containing files to be compressed,
  specified as either a relative or absolute file path or file
  [URI].
* `$filter.inclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  include files with matching names in the archive.
* `$filter.exclusions` is an optional list of regular expression
  patterns, wildcard file glob patterns, or literals used to
  exclude files with matching names from the archive.
* `$filter.type` is an optional choice if either 'regular expression',
  'wildcard', or 'literal', which determines the type of filter
  patterns provided. Defaults to 'regular expression'.
* `$path.parent?` is an optional boolean flag indicating if the
  archive file name paths should include the given `$directory` name
  as the top-level path name. Defaults to `false`.
* `$recurse?` is an optional boolean flag indicating if files in child
  directories should also be archived. Defaults to `false`.
* `$mode` is an optional choice of 'stream', 'bytes', or
  'string', which determines the type of object returned by
  this service. If the 'string' mode is chosen, the resulting
  zipped data is base64-encoded. Defaults to 'stream'.

#### Outputs:

* `$directory.zip` is the resulting [zip] archive containing the
  compressed files from the given `$directory`.

---

### tundra.document:amend

Edits the given `IData` `$document` with the list of key value pairs
specified in `$amendments`.

#### Inputs:

* `$document` is the `IData` document to be amended.
* `$document.namespace` is an optional `IData` document containing
  the namespace declarations used in the given `$document`.
* `$amendments` is an `IData` document list containing all the edits
  to be made to the given `$document`.
  * `key` is a fully-qualified (for example, `a/b/c[0]`) key
    identifying the value in `$document` to be edited.
  * `value` is the value to be assigned to the item identified by
    `key`, and can include percent-delimited variable substitution
    strings which will be substituted prior to being inserted into the
    `$document`.
  * `action` is an optional choice of `merge`, `create`, `update`, or
    `delete`. Defaults to `merge` if not specified.
    * `merge` will create the key and associate it with the given
      value if the key does not already exist, or update the the
      associated value if the key already exists.
    * `create` will only create the key and associate it with the
      given value if the key does not already exist.
    * `update` will only update the associated value if the key
      already exists.
    * `delete` will remove the key and previously associated value
      from the document. No value is required to be specified when
      using this action.
  * `condition` is an optional `Tundra/tundra.condition:evaluate`
    conditional statement, which is evaluated against the pipeline and
    only if the condition evaluates to `true` will the associated
    amended `value` be applied. If not specified, the amended `value`
    will always be applied.

#### Outputs:

* `$document` is the resulting edited `IData` document.

---

### tundra.document:blankify

Converts all null values in the given `IData` document to empty strings.

#### Inputs:

* `$document` is an `IData` document whose null values are to be converted
  to empty strings.
* `$recurse` is an optional boolean indicating if embedded `IData` documents
  and `IData[]` document lists should also have their null values converted
  to empty strings. Defaults to `false`.

#### Outputs:

* `$document` is the given `IData` document with all null values converted to
  empty strings.

---

### tundra.document:capitalize

Capitalizes the first character in either the first word or all
words in strings in the given `IData` document.

#### Inputs:

* `$document` is an `IData` document whose string values are to be
  capitalized.
* `$capitalize` is an optional choice that determines whether all
  words or only the first word is capitalized. Defaults to
  capitalizing all words, if not specified:
  * `all words`
  * `first word`
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have string
  values capitalized. Defaults to `false`.
* `$mode` is an optional choice for what should be capitalized: either
  the values, the keys, or both the keys and the values. Defaults to
  values only.

#### Outputs:

* `$document` is the given `IData` document with all string values
  capitalized.

---

### tundra.document:clear

Removes all elements from the given `IData` document, except for
any keys specified in the preserve list.

#### Inputs:

* `$document` is an `IData` document whose keys are to be removed.
* `$preserve` is list of keys which will not be removed from
  the given `IData` document. Keys can be simple or fully
  qualified, such as `a/b/c[0]/d`.

#### Outputs:

* `$document` is the given `IData` document with all keys removed,
  except for those specified in `$preserve`.

---

### tundra.document:compact

Removes all null values from the given `IData` document.

#### Inputs:

* `$document` is an `IData` document from which null values are
  to be removed.
* `$recurse?` is an optional boolean indicating if embedded
  `IData` documents and `IData[]` document lists should also
  be compacted. Defaults to `false`.

#### Outputs:

* `$document` is the given `IData` document with all null values
  removed.

---

### tundra.document:condense

Replaces runs of one or more whitespace characters (space, tab,
carriage return, line feed) with a single space character, then
trims leading and trailing whitespace.

#### Inputs:

* `$document` is an `IData` document to be condensed.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also be condensed.
  Defaults to `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  values.

#### Outputs:

* `$document` is the resulting `IData` document with `String`, `String[]`,
  and `String[][]` values condensed into single lines.

---

### tundra.document:copy

Copies the value associated with the source key to the target key in the
given `IData` document.

#### Inputs:

* `$document` is an `IData` document in which to copy the value associated
  with the source key to the target key.
* `$key.source` is a key identifying the value to be copied, and can be
  simple or fully qualified, such as `a/b/c[0]/d`.
* `$key.target` is the key to which the source key value is copied, and can
  be simple or fully qualified, such as `a/b/c[0]/d`.
* `$key.literal?` is an optional boolean flag which if `true` will treat the
  given keys literally rather than as potentially fully-qualified keys.
  Defaults to `false` if not specified.

#### Outputs:

* `$document` is the given `IData` document where the value associated with
  `$key.source` has been copied to `$key.target`.

---

### tundra.document:deliver

Serializes the given `IData` document, and delivers it to the given
destination URI.

Additional delivery protocols can be implemented by creating a
service named for the URI scheme in the folder
`tundra.content.deliver`. Services in this folder should implement
the `tundra.schema.content.deliver:handler` specification.

#### Inputs:

* `$destination` is the delivery destination [URI] to which the
  serialized document content will be delivered. If not specified, no
  delivery will be attempted. The supported delivery protocols
  ([URI] schemes) are as per `Tundra/tundra.content:deliver`. Please
  refer to this service's documentation for further details.
* `$document` is the `IData` document to be serialized and delivered
  to the given destination URI.
* `$content.type` is an optional MIME media type that describes the
  format of the given content.
* `$content.encoding` is an optional character set to use when
  serializing `$document` to text data for delivery. Defaults to
  [UTF-8].
* `$content.schema` is an optional fully-qualified name of the
  document reference or flat file schema used to serialize
  `$document`.
* `$content.namespace` is an optional list of namespace prefixes and
  the URIs they map to, used when serializing `$document` to [XML]
  content with elements in one or more namespaces.
* `$pipeline` is an optional `IData` document for providing arbitrary
  variables to the delivery implementation service. Variables
  provided will take precedence and override their corresponding
  values in the `$destination` [URI] where applicable. See the
  `$destination` description above for transport-specific options
  which can be provided via this `IData` document.

#### Outputs:

* `$message` is an optional response message, useful for logging,
  that may be returned by specific delivery protocols.
* `$response` is an optional response content returned by the
  delivery (for example, the HTTP response body).
* `$response.type` is an optional MIME media type describing the type
  of `$response` returned.
* `$response.context` is an optional document containing additional
  context for the returned response. For example, an HTTP delivery
  could return the response status and headers to provide additional
  context to the delivery result.

---

### tundra.document:denormalize

Returns a new `IData` document where all nested child elements in the
given `IData` document are replaced with non-nested elements whose
keys are fully-qualified.

For example, if the `IData` document contains the following key value
pairs (using [JSON] notation to represent the pipeline):

    {
      "a": {
        "b": {
          "c": "example 1",
          "d": "example 2"
        }
      },
      "e": "example 3",
      "f": ["example 4", "example 5"]
    }

This is denormalized to the following:

    {
      "a/b/c": "example 1",
      "a/b/d": "example 2",
      "e": "example 3",
      "f[0]": "example 4",
      "f[1]": "example 5"
    }

#### Inputs:

* `$document` is an `IData` document to be denormalized.

#### Outputs:

* `$document` is the resulting denormalized `IData` document.

---

### tundra.document:drop

Removes the element with the given key from the given `IData` document.

#### Inputs:

* `$document` is an `IData` document from which to remove the element
  identified by `$key`.
* `$key` is a key identifying the element in `$document` to be removed,
  and can be simple or fully qualified, such as `a/b/c[0]/d`.
* `$key.literal?` is an optional boolean flag which if `true` will treat
  the given key literally rather than as a potentially fully-qualified
  key. Defaults to `false` if not specified.

#### Outputs:

* `$document` is the given `IData` document where the element
  associated with `$key` has been removed.

---

### tundra.document:duplicate

Returns an optionally recursive clone of the the given IData
document.

#### Inputs:

* `$document` is an `IData` document to be cloned.
* `$recurse?` is an optional boolean indicating if embedded
  `IData` documents and `IData[]` document lists should also
  be cloned. If not cloned, the resulting `IData` document
  will reference the same `IData` and `IData[]` objects as
  the input document. Defaults to `false`.

#### Outputs:

* `$duplicate` is the cloned input `IData` document.

---

### tundra.document:each

Iterates over all elements in the given `IData` document, invoking
the given service for each key value pair.

#### Inputs:

* `$document` is an `IData` document whose elements are to be
  iterated over.
* `$service` is the fully-qualifed name of the service to
  be called to process each element .
* `$pipeline` is an optional input pipeline for providing
  arbitrary input arguments to `$service`.
* `$key.input` is the optional argument name used when
  passing each iteration's key in the input pipeline of
  each invocation of `$service`. Defaults to `$key`.
* `$value.input` is the optional argument name used when
  passing each iteration's value in the input pipeline of
  each invocation of `$service`. Defaults to `$value`.
* `$value.class` is an optional Java class name, which when
  specified restricts the elements iterated to only those
  whose value are an instance of the given class. When
  not specified, all elements in the `$document` are iterated
  over.
* `$recurse` is an optional boolean indicating if embedded
  `IData` documents and `IData[]` document lists should also
  be iterated over. Defaults to `false`.

---

### tundra.document:emit

Emits or serializes the given `IData` document as an [IData XML]
string, byte array, or input stream.

#### Inputs:

* `$document` is an `IData` document to be serialized.
* `$content.class` is an optional choice of the format to use: either
  `xml`, `json`, or `yaml`. When `xml` is chosen, the [IData XML] schema is
  used. Defaults to `xml`.
* `$content.encoding` is an optional character set used to encode the
  serialized document when returned as a byte array or input
  stream. Defaults to [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or `string`,
  which determines the type of object returned by this service.
  Defaults to `stream`.

#### Outputs:

* `$content` is the resulting serialized document.

---

### tundra.document:empty

Returns a newly created empty IData document.

#### Outputs:

* `$document` is a new empty IData document.

---

### tundra.document:equal

Returns `true` if the given documents are equal (contain the same set
of keys and values).

#### Inputs:

* `$document.x` is an `IData` document to be compared to `$document.y`.
* `$document.y` is an `IData` document to be compared to `$document.x`.

#### Outputs:

* `$equal?` is a boolean indicating if the given documents contain
  the same set of keys and values.

---

### tundra.document:first

Returns the first key value pair from the given `IData` document.

#### Inputs:

* `$document` is an `IData` document from which to fetch the first
  element.

#### Outputs:

* `$key` is the key of the first element in the given IData
  document.
* `$value` is the value of the first element in the given IData
  document.

---

### tundra.document:flatten

Returns the values associated with the given keys from the given
`IData` document flattened to a one-dimensional array.

For example, given the following `IData` document (represented in
[JSON] form):

    {
      "a": [
        {
          "b": [
            {
              "c": "1"
            },
            {
              "c": "2"
            },
            {
              "c": "3"
            }
          ]
        },
        {
          "b": [
            {
              "c": "4"
            },
            {
              "c": "5"
            }
          ]
        }
      ]
    }

A flattening of `a/b/c` would return the following (represented in
[JSON] form):

    ["1", "2", "3", "4", "5"]

A flattening of `a/b[0]/c` would return the following (represented
in [JSON] form):

    ["1", "2", "3"]

And a flattening of `a/b[0]/c[0]` would return the following
(represented in [JSON] form):

    ["1"]

#### Inputs:

* `$document` is an `IData` document from which to collect and flatten
  the values associated with the given `$keys`.
* `$keys` is a list of fully-qualified keys identifying the values in
  the given document to be flattened. If the keys index multiple
  nested arrays all associated values in those arrays will be
  flattened into the returned one-dimensional array.
* `$nulls?` is an optional boolean flag which when `true` will include
  missing or null values as nulls in the resulting array. Defaults
  to `false`.

#### Outputs:

* `$values` is a one-dimensional array containing the flattened values
  associated with the given `$keys` in the given `IData` document.

---

### tundra.document:flip

Flips the given `IData` document so that the keys become the values
and the values become the keys. Note that only top-level elements
with String values are flipped; other elements are not included.

For example, an `IData` document with the following structure
represented as [JSON]:

    {
        "band": "Joy Division",
        "album": "Unknown Pleasures"
    }

Would be flipped as follows:

    {
        "Joy Division": "band",
        "Unknown Pleasures": "album"
    }

#### Inputs:

* `$document` is an `IData` document to be flipped.

#### Outputs:

* `$document` is the given `IData` document flipped so that the keys
  are now the values and the values are now the keys.

---

### tundra.document:freeze

Returns a read-only version of the given `IData` document, such
that future modification attempts are ignored. A frozen `IData`
document cannot be unfrozen.

#### Inputs:

* `$document` is an `IData` document to be frozen.

#### Outputs:

* `$document` is the given `IData` document frozen such that no
  further modifications are possible.

---

### tundra.document:get

Returns the value associated with the given key from the given IData
document.

Also supports resolving [XPath] expressions against [org.w3c.dom.Node]
objects in the top level of the given `IData` document.

#### Inputs:

* `$document` is an `IData` document from which to fetch the value
  associated with the given `$key`.
* `$key` is the key identifying the value in the given document to
  be returned, and can be simple or fully-qualified, such as
  `a/b/c[0]/d`.
* `$key.literal?` is an optional boolean flag which if `true` will treat
  the given key literally rather than as a potentially fully-qualified
  key. Defaults to `false` if not specified.
* `$value.default` is an optional value to be returned when either
  the given `$key` does not exist or its associated value is null.
* `$value.list.default` is an optional list value to be returned when
  either the given `$key` does not exit or its associated value is
  null.

#### Outputs:

* `$value` is the value associated with the given `$key` in the given
  `IData` document, or the given `$value.default` or `$value.list.default`
  if specified and the associated value for `$key` either does not
  exist or is null.
* `$value.list` is the value associated with the given `$key` in the
  given `IData` document if it is a list, or the given
  `$value.list.default` if specified and the associated value for `$key`
  either does not exist or is null.

---

### tundra.document:join

Converts the given `IData` document to a human-readable string
representation, useful for logging or display to end users.

#### Inputs:

* `$document` is an `IData` document to be converted to a string.
* `$separator.value` is an optional string used to separate the key
  from the value of each item in the `IData` document. Defaults to the
  string ": ", if not specified.
* `$separator.item` is an optional string used to separate each key
  value item in the `IData` document. Defaults to ", ", if not
  specified.
* `$separator.list` is an optional string used to separate each list
  item in the `IData` document. Defaults to ", ", if not specified.
* `$nulls?` is an optional boolean flag which if `true` will include all
  `null` values in the resulting string representation, otherwise these
  are excluded. Defaults to `false`, if not specified.
* `$sanitization` is an optional choice of whether to remove nulls,
  nulls and blanks (strings that only contain whitespace
  characters), or convert nulls to blanks. If not specified, no
  sanitization is performed prior to the join.

#### Outputs:

* `$result` is the resulting string representation of the given `IData`
  document.

---

### tundra.document:keys

Returns the top-level keys in the given `IData` document that either
match the given regular expression pattern if specified, or are
associated with the given value if specified, or all top-level keys
if neither are specified.

#### Inputs:

* `$document` is an `IData` document from which all top-level keys are
  to be fetched.
* `$pattern` is an optional [regular expression pattern] that is used
  to filter the list of keys returned.
* `$value` is an optional value whose associated keys are to be
  returned.
* `$value.list` is an optional list value whose associated keys are to
  be returned.

#### Outputs:

* `$keys` is the list of the top-level keys in the given `IData`
  document that match the given regular expression `$pattern` if
  specified, or are associated with the given `$value` or `$value.list`
  if specified, or all top-level keys if no filtering was specified.
* `$keys.length` is the number of items returned in the `$keys` list.

---

### tundra.document:last

Returns the last key value pair from the given `IData` document.

#### Inputs:

* `$document` is an `IData` document from which to fetch the last
  element.

#### Outputs:

* `$key` is the key of the last element in the given IData
  document.
* `$value` is the value of the last element in the given IData
  document.

---

### tundra.document:leaves

Returns all the leaf values in the given `IData` document.

For example, an `IData` document with the following structure
represented as [JSON]:

    {
        "band": {
            "name": "Joy Division",
            "members": [
                {
                    "name": "Ian Curtis",
                    "role": "Singer"
                },
                {
                    "name": "Bernard Sumner",
                    "role": "Guitarist"
                },
                {
                    "name": "Peter Hook",
                    "role": "Bassist"
                },
                {
                    "name": "Stephen Morris",
                    "role": "Drummer"
                }
            ],
            "songs": [
                "She's Lost Control",
                "Love Will Tear Us Apart",
                "Ceremony"
            ]
        }
    }

If recursing, the following list of leaf values would be returned:

    [
        "Joy Division",
        "Ian Curtis",
        "Singer",
        "Bernard Sumner",
        "Guitarist",
        "Peter Hook",
        "Bassist",
        "Stephen Morris",
        "Drummer",
        "She's Lost Control",
        "Love Will Tear Us Apart",
        "Ceremony"
    ]

#### Inputs:

* `$document` is an `IData` document from which all leaf
  values are to be fetched.
* `$recurse?` is an optional boolean indicating if child `IData`
  documents, `IData[]` document lists, `Object[][]` object tables, and
  `Object[]` object lists should be recursed when collecting leaves.
  Defaults to `false`.
* `$class` is an optional Java class names that the returned leaf
  values are required to be instances of. If not specified, defaults
  to `java.lang.Object`.

#### Outputs:

* `$values` is the list of all leaf values in the given `IData` document
  that are instances of the given class, or all leaf values if no
  class was specified.

---

### tundra.document:legalize

Converts illegal characters to underscores to create legal Java
identifiers in either the values, the keys, or both the keys and
values in the given `IData` document.

#### Inputs:

* `$document` is an `IData` document in which the keys and/or values are
  converted to legal Java identifiers.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have their keys
  and/or values converted to legal Java identifiers. Defaults to
  `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  converting values only.

#### Outputs:

* `$document` is the given `IData` document with its keys and/or values
  converted to to legal Java identifiers.

---

### tundra.document:length

Returns the number of top-level key value pairs in the given
IData document.

#### Inputs:

* `$document` is an `IData` document.

#### Outputs:

* `$length` is the number of top-level keys in the given IData
  document.

---

### tundra.document:listify

Converts the value or values identified by the given key to a new
list containing the original value or values as its items, unless
the original value is already a list in which case it remains
unmodified.

#### Inputs:

* `$key` is a simple or fully-qualified (such as `a/b/c[0]/d`) key
  identifying the value to be converted to a list.
* `$scope` is an optional `IData` document that, if specified, is used
  to resolve the given `$key` against. If not specified, `$key` is
  resolved against the pipeline.

#### Outputs:

* `$scope` is returned if an input `$scope` was provided, where the value
  identified by `$key` within it has been converted to a list. If
  no input `$scope` was specified, the value identified by `$key` in the
  pipeline is instead converted to a list. If `$key` does not identify
  any value, this service does nothing.

---

### tundra.document:log

Writes the given `IData` document to the server log, or a given named
log, as a minified [JSON] string, optionally prefixed with the
invoking user, call stack, and message.

#### Inputs:

* `$document` is the `IData` document to be logged to the server log.
* `$log.level` is an optional logging level used when writing the
  pipeline to the server log.
* `$log.message` is an optional message to be written to the server
  log.
* `$log.prefix?` is a boolean indicating whether to prefix the log
  statement with the current user and callstack. Defaults to `true`.
* `$log.name` is the optional logical name of the log to write to.
  The logical log name can be mapped to a physical log target file
  via the Tundra package configuration section `feature/log/target`.
  If the logical log name cannot be found in this configuration
  section, the default physical log target is `./logs/server.log`.
  Defaults to the calling service's package name, if not specified.

---

### tundra.document:lowercase

Converts all string values in the given `IData` document to lowercase.

#### Inputs:

* `$document` is an `IData` document whose string values are to be
  converted to lowercase.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have string
  values converted to lowercase. Defaults to `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  trimming values only.

#### Outputs:

* `$document` is the given `IData` document with all string values
  converted to lowercase.

---

### tundra.document:map

Constructs a new `IData` document by invoking the given service for
each key value pair in the given `IData` document, and inserting
the key value pair returned by the service in the resulting IData
document.

This is an implementation of a higher-order [map function] for
IData objects.

#### Inputs:

* `$document` is an `IData` document whose elements are to be
  processed by the given `$service`.
* `$service` is the fully-qualifed name of the service to
  be called to process each element, and should return a
  new key value pair as a result.
* `$pipeline` is an optional input pipeline for providing
  arbitrary input arguments to `$service`.
* `$key.input` is the optional argument name used when
  passing each key to `$service`. Defaults to `$key`.
* `$key.output` is the optional argument name used by `$service`
  when returning the resulting key. Defaults to `$key`.
* `$value.input` is the optional argument name used when
  passing each value to `$service`. Defaults to `$value`.
* `$value.output` is the optional argument name used by `$service`
  when returning the resulting value. Defaults to `$value`.
* `$value.class` is an optional Java class name, which when
  specified restricts the elements processed to only those
  whose value are an instance of the given class. When
  not specified, all elements in the `$document` are processed.
* `$recurse` is an optional boolean indicating if embedded
  `IData` documents and `IData[]` document lists should also
  be processed. Defaults to `false`.

#### Outputs:

* `$document` is the resulting `IData` document created from the
  key value pairs returned by `$service`.

---

### tundra.document:merge

Merges multiple `IData` documents into a single document. The
resulting document contains all keys from all source documents, and
each key is assigned the value associated with the final occurrence
of that key across all source documents.

This service can be useful for combining a document constructed with
default values with a document sourced externally, where the merged
document will contain the key set union of both documents, and will
include default values where no value was present in the externally
sourced document.

#### Inputs:

* `$operands` is an `IData` document in which arbitrary top-level `IData`
  documents and `IData[]` document lists can be provided for merging
  into a single `IData` document.
* `$recurse?` is an optional boolean which when `true` will recursively
  merge child `IData` documents also. Defaults to `false`, where only
  top-level elements are merged.

#### Outputs:

* `$document` is the resulting merged `IData` document.

---

### tundra.document:normalize

Returns a new `IData` document with all fully qualified keys (for example,
'a/b/c' or 'x/y[0]/z[1]') deconstructed into their constituent parts, and
any non-IData objects converted to an `IData` representation wherever
possible.

For example, if the `IData` document contains the following key value pairs
(using [JSON] notation to represent the pipeline):

    {
      "a/b/c": "example 1",
      "a/b/d": "example 2",
      "e": "example 3",
      "f[0]": "example 4",
      "f[1]": "example 5"
    }

This is normalized to the following:

    {
      "a": {
        "b": {
          "c": "example 1",
          "d": "example 2"
        }
      },
      "e": "example 3",
      "f": ["example 4", "example 5"]
    }

Keys using path-style notation, for example `a/b/c`, are
converted to nested `IData` documents with the final key
in the path, `c` in this example, assigned the value of
the original key.

Keys using array- or list-style notation, for example `f[0]`,
are converted to an array or list with the value of the
original key assigned to the indexed item (the zeroth item in
this example).

#### Inputs:

* `$document` is an `IData` document to be normalized.

#### Outputs:

* `$document` is the resulting normalized `IData` document.

---

### tundra.document:parse

Parses or deserializes the given [IData XML] string, byte array, or
input stream to an `IData` document.

#### Inputs:

* `$content` is a string, byte array, or input stream containing a
  serialized `IData` document.
* `$content.class` is an optional choice of the format to use: either
  `xml`, `json`, or `yaml`. When `xml` is chosen, the [IData XML] schema is
  used. Defaults to `xml`.
* `$content.encoding` is an optional character set used to decode the
  `$content` when provided as a byte array or input stream. Defaults
  to [UTF-8].

#### Outputs:

* `$document` is the resulting deserialized `IData` document.

---

### tundra.document:nullify

Converts strings that contain only whitespace characters in the given
`IData` document to null.

#### Inputs:

* `$document` is an `IData` document whose string values are converted to
  null if they contain only whitespace characters.
* `$recurse` is an optional boolean indicating if embedded `IData` documents
  and `IData[]` document lists should also be nullified. Defaults to `false`.

#### Outputs:

* `$document` is the given `IData` document with all string values that
  contained only whitespace characters converted to null.

---

### tundra.document:pivot

Returns an `IData[]` document list of each key value pair in the given IData
document.

#### Inputs:

* `$document` is an `IData` document to be pivoted.
* `$recurse?` is an optional boolean indicating if embedded IData
  documents and `IData[]` document lists should also be pivoted. Note that
  a pivoted `IData[]` document list is returned as a two dimensional IData
  array (IData[][]). Defaults to `false`, if not specified.

#### Outputs:

* `$pivot` is an `IData[]` document list where each item in the list
  represents each key value pair present in the given `$document`.

---

### tundra.document:prefix

Prepends the given prefix to the keys, or values, or both in the
given `IData` document.

#### Inputs:

* `$document` is an `IData` document to be prefixed.
* `$prefix` is the string to be prepended to the keys, or values, or
  both.
* `$recurse?` is an optional boolean indicating if child `IData`
  documents and `IData[]` document lists should also be recursively
  processed. Defaults to `false`.
* `$force?` is an optional boolean indicating if the prefix should be
  always be prepended even if the key or value already starts with
  the given prefix. Defaults to `false`, where the prefix is only
  prepended to keys or values that do not already start with it.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  values.

#### Outputs:

* `$document` is the given `IData` document with the given prefix
  prepended to either the keys, or values, or both.

---

### tundra.document:put

Sets the value associated with the given key in the given `IData` document.

#### Inputs:

* `$document` is an `IData` document in which to insert the given key
  value pair.
* `$key` is the key to be inserted into the given `IData` document, and
  can be simple or fully-qualified, such as `a/b/c[0]/d`. If the key
  already exists, it's value will be overwritten with the given value.
* `$key.literal?` is an optional boolean flag which if `true` will treat
  the given key literally rather than as a potentially fully-qualified
  key. Defaults to `false` if not specified.
* `$value` is the value to be associated with the given key. If not
  specified, a null value will be inserted.

#### Outputs:

* `$document` is the resulting `IData` document containing the new key
  value pair.

---

### tundra.document:remove

Removes either the first or all occurrences of the given pattern
from either the values, the keys, or both the keys and values in the
given `IData` document.

#### Inputs:

* `$document` is an `IData` document to have occurrences of the
  given pattern removed.
* `$pattern` is either a literal or [regular expression pattern] to
  be removed. If not specified, no removal will occur.
* `$pattern.literal?` is a boolean indicating whether the pattern is a
  literal or a [regular expression pattern]. If literal, the pattern
  is converted to a [regular expression pattern] that matches the
  given literal string. Defaults to `false`.
* `$occurrence.first?` is a boolean indicating if only the first
  occurrence of the given pattern should be removed. Defaults to
  `false`, where all occurrences of the pattern are removed.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have occurrences
  of the pattern in their elements replaced. Defaults to `false`.
* `$mode` is an optional choice for what should be removed:
  either the values, the keys, or both the keys and the values.
  Defaults to removing values only.

#### Outputs:

* `$document` is the given `IData` document with the given `$pattern`
  removed.

---

### tundra.document:rename

Renames the value associated with the source key to have the target key
in the given `IData` document.

#### Inputs:

* `$document` is an `IData` document in which to rename the given key.
* `$key.source` is the key to be renamed, and can be simple or fully-
  qualified, such as `a/b/c[0]/d`.
* `$key.target` is the new name that source key will be renamed to,
  and can be simple or fully-qualified, such as `a/b/c[0]/d`. If the
  target key already exists, its value will be overwitten with the
  value that was associated with the source key.
* `$key.literal?` is an optional boolean flag which if `true` will treat
  the given keys literally rather than as potentially fully-qualified
  keys. Defaults to `false` if not specified.

#### Outputs:

* `$document` is the resulting `IData` document, where the source key
  has been renamed to the target key.

---

### tundra.document:replace

Replaces either the first or all occurrences of the given pattern
with the given replacement in either the values, the keys, or both
the keys and values in the given `IData` document.

#### Inputs:

* `$document` is an `IData` document to have occurrences of the
  given pattern replaced.
* `$pattern` is either a literal or [regular expression pattern] to
  be replaced. If not specified, no replacement will occur.
* `$pattern.literal?` is a boolean indicating whether the pattern is a
  literal or a [regular expression pattern]. If literal, the pattern
  is converted to a [regular expression pattern] that matches the
  given literal string. Defaults to `false`.
* `$replacement` is the replacement string to be substituted in the
  given string wherever the given pattern is found. If not
  specified, no replacement will occur.
* `$replacement.literal?` is a boolean indicating if the replacement
  string should be treated as a literal string. If `false`, captured
  groups can be referred to with dollar-sign references, such as `$1`,
  and other special characters may need to be escaped. Defaults to
  `false`.
* `$occurrence.first?` is a boolean indicating if only the first
  occurrence of the given pattern should be replaced. Defaults to
  `false`, where all occurrences of the pattern are replaced.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have occurrences
  of the pattern in their elements replaced. Defaults to `false`.
* `$mode` is an optional choice for what should be replaced:
  either the values, the keys, or both the keys and the values.
  Defaults to replacing values only.

#### Outputs:

* `$document` is the given `IData` document with the given `$pattern`
  replaced with the given `$replacement`.

---

### tundra.document:sort

Sorts the given `IData` document by its keys in natural ascending order.

#### Inputs:

* `$document` is an `IData` document to be sorted.
* `$recurse` is an optional boolean indicating if embedded
  `IData` documents and `IData[]` document lists should also
  be sorted. Defaults to `false`.

#### Outputs:

* `$document` is the resulting `IData` document, where the elements have
  been sorted by their keys into natural ascending order.

---

### tundra.document:split

One-to-many conversion of an `IData` document to an `IData[]` document list.
Calls the given splitting service, passing the document as an input, and
returning the split list of documents as output.

#### Inputs:

* `$document` is an `IData` document to be processed by the given splitting
  service.
* `$service` is the fully-qualified name of the splitting service, which
  accepts a single `IData` document and returns an `IData` document list,
  called to split `$document`.

  It is permissible for the resulting list returned by `$service` to
  contain unlike documents (documents whose formats are different), and
  in this case `$service` is required to return two string lists:
  * `$content.types` specifies a MIME media type per item in the returned
    list of documents.
  * `$schemas` specifies the fully-qualified document reference (for
    XML) or flat file schema (for Flat Files) per item in the returned
    list of documents, which can be used to serialize that item.
* `$pipeline` is an optional `IData` document containing arbitrary
  variables to be included in the input pipeline of the invocation of
  `$service`.
* `$service.input` is an optional variable name to use in the input
  pipeline of the call to `$service` for the `IData` document. Defaults to
  `$document`.
* `$service.output` is an optional variable name used to extract the
  output `IData` document list from the output pipeline of the call to
  `$service`. Defaults to `$documents`.

#### Outputs:

* `$documents` is the resulting list of `IData` documents.
* `$content.types` is the list of MIME media types if the `$documents`
  list contains unlike media types.
* `$schemas` is the list of fully-qualified document reference (for XML)
  or flat file schema (for flat files) names, returned by `$service` for
  when the `$documents` list contains unlike formats.

---

### tundra.document:squeeze

Trims all leading and trailing whitespace from all string values, then
converts empty strings, empty `IData` documents, and empty lists to nulls,
then compacts the `IData` document by removing all null values.

#### Inputs:

* `$document` is an `IData` document to be squeezed.
* `$recurse?` is an optional boolean indicating if embedded
  `IData` documents and `IData[]` document lists should also
  be squeezed. Defaults to `false`.

#### Outputs:

* `$document` is the resulting `IData` document with all string values
  trimmed of leading and trailing whitespace characters, and all
  empty string values, empty `IData` documents, empty lists, and null
  values removed.

---

### tundra.document:stringify

Converts all non-IData document and non-IData[] document list values in the
given `IData` document to strings.

#### Inputs:

* `$document` is an `IData` document whose values are to be converted to
  strings.
* `$recurse` is an optional boolean indicating if embedded `IData` documents
  and `IData[]` document lists should also have their values converted to
  strings. Defaults to `false`.

#### Outputs:

* `$document` is the given `IData` document with all non-IData document and
  non-IData[] document list values converted to strings.

---

### tundra.document:substitute

Attempts variable substitution on each string value in the given
`IData` document by replacing all occurrences of substrings matching
`%key%` with the associated (optionally scoped) value.

#### Inputs:

* `$document` is an `IData` document to perform variable substitution
  on.
* `$substitution.default` is an optional default value to substitute
  in place of keys that resolve to null or missing values. If not
  specified, no substitution will be made for keys that resolve to
  null or missing values.
* `$substitution.mode` is an optional choice of whether the
  substitution scope should be `local` variables only, `global`
  variables only, or `all` variables (local and global variables
  where local variables take precedence). Note that choosing `global`
  on an Integration Server version that does not support global
  variables will result in all values being substituted with the
  default value if one is provided. Defaults to `local`.
* `$pipeline` is an optional scope used to resolve key references. If
  not specified, keys are resolved against the pipeline itself.

#### Outputs:

* `$document` is the resulting `IData` document with all variable
  substitution patterns in all values, such as `%key%`, replaced with
  the value of the key (resolved against either `$pipeline`, if
  specified, or the pipeline itself).

---

### tundra.document:suffix

Appends the given suffix to the keys, or values, or both in the
given `IData` document.

#### Inputs:

* `$document` is an `IData` document to be suffixed.
* `$suffix` is the string to be appended to the keys, or values, or
  both.
* `$recurse?` is an optional boolean indicating if child `IData`
  documents and `IData[]` document lists should also be recursively
  processed. Defaults to `false`.
* `$force?` is an optional boolean indicating if the suffix should be
  always be appended even if the key or value already ends with
  the given suffix. Defaults to `false`, where the suffix is only
  appended to keys or values that do not already end with it.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  values.

#### Outputs:

* `$document` is the given `IData` document with the given suffix
  appended to either the keys, or values, or both.

---

### tundra.document:translate

One-to-one conversion of one `IData` document to another `IData` document.
Calls the given translation service, passing the document as an input, and
returning the translated document as output.

#### Inputs:

* `$document` is an `IData` document to be translated.
* `$service` is the fully-qualified name of the translation service,
  which accepts a single `IData` document and returns a single IData
  document, called to translate the given `$document`.
* `$pipeline` is an optional `IData` document for providing arbitrary
  variables to the invocation of `$service.`
* `$service.input` is an optional variable name to use in the input
  pipeline of the call to `$service` for the given `IData` document.
  Defaults to `$document`.
* `$service.output` is an optional variable name used to extract the
  output `IData` document from the output pipeline of the call to
  `$service`. Defaults to `$translation`.

#### Outputs:

* `$translation` is the translated `IData` document.

---

### tundra.document:trim

Removes leading and trailing whitespace from all string values in the
given `IData` document.

#### Inputs:

* `$document` is an `IData` document whose string values are to be
  trimmed of leading and trailing whitespace characters.
* `$recurse?` is an optional boolean indicating if embedded
  `IData` documents and `IData[]` document lists should also
  have their string values trimmed. Defaults to `false`.
* `$mode` is an optional choice for what should be trimmed:
  either the values, the keys, or both the keys and the values.
  Defaults to trimming values only.

#### Outputs:

* `$document` is the given `IData` document with all string values
  trimmed of leading and trailing whitespace characters.

---

### tundra.document:uncase

Returns a recursive clone with case-insensitive keys of the given
`IData` document.

#### Inputs:

* `$document` is an `IData` document to be cloned to a case-insensitive
  representation.
* `$locale` optionally identifies the case transformation rules to be
  used for a given [Locale]. If not specified, the [default locale]
  is used.

#### Outputs:

* `$document` is the resulting recursive clone with case-insensitive
  keys of the given `IData` document.

---

### tundra.document:unprefix

Removes the given prefix from the keys, or values, or both in the
given `IData` document.

#### Inputs:

* `$document` is an `IData` document to have prefixes removed.
* `$prefix` is the string to be removed from the keys, or values, or
  both.
* `$recurse?` is an optional boolean indicating if child `IData`
  documents and `IData[]` document lists should also be recursively
  processed. Defaults to `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  values.

#### Outputs:

* `$document` is the given `IData` document with the given prefix
  removed from either the keys, or values, or both.

---

### tundra.document:unsuffix

Removes the given suffix from the keys, or values, or both in the
given `IData` document.

#### Inputs:

* `$document` is an `IData` document to have suffixes removed.
* `$suffix` is the string to be removed from the keys, or values, or
  both.
* `$recurse?` is an optional boolean indicating if child `IData`
  documents and `IData[]` document lists should also be recursively
  processed. Defaults to `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  values.

#### Outputs:

* `$document` is the given `IData` document with the given suffix
  removed from either the keys, or values, or both.

---

### tundra.document:uppercase

Converts all string values in the given `IData` document to uppercase.

#### Inputs:

* `$document` is an `IData` document whose string values are to be
  converted to uppercase.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have string
  values converted to uppercase. Defaults to `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  trimming values only.

#### Outputs:

* `$document` is the given `IData` document with all string values
  converted to uppercase.

---

### tundra.document:values

Returns all the top-level values in the given `IData` document.

#### Inputs:

* `$document` is an `IData` document from which all top-level
  values are to be fetched.

#### Outputs:

* `$values` is the list of all top-level values in the given IData
  document.

---

### tundra.document:validate

Validates the given `IData` document against the given document
reference or flat file schema.

#### Inputs:

* `$document` is the `IData` document to be validated.
* `$content.schema` is the fully-qualified name of the document
  reference or flat file schema to be used to validate the given
  `$document`.
* `$raise?` is an optional boolean which when `true` will cause
  this service to throw an exception if the given `$document`
  is invalid. Defaults to `false`.

#### Outputs:

* `$valid?` is `true` when the given `$document` if validated
  succesfully, otherwise `false` when `$document` is invalid.
* `$message` describes the validation errors encountered when
  validating `$document`.

---

### tundra.dns:localhost

Returns the fully-qualifed domain name, host name, and IP address for
the local host.

#### Outputs:

* `$domain` is the fully-qualified domain name of the local host.
* `$host` is the local host name.
* `$ip` is the local IP address.

---

### tundra.dns:resolve

Looks up the given name in the Domain Name System ([DNS]), returning the
fully-qualifed domain name, host name, and IP address, if found.

#### Inputs:

* `$name` is a host name, domain name, or IP address to be resolved
  against [DNS].

#### Outputs:

* `$domain` is the fully-qualified domain name associated with the given
  name.
* `$host` is the host name associated with the given name.
* `$ip` is the IP address associated with the given name.

---

### tundra.duration:add

Adds the given durations, returning the total.

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
* `$pattern.input` is an optional pattern describing the type of
  duration specified by the values specified in `$operands`.
  Defaults to an [ISO8601] XML string.
* `$pattern.output` is an optional desired pattern used to format
  the returned `$duration` string. Defaults to an [ISO8601] XML
  string.

#### Outputs:

* `$duration` is the total duration resulting from adding all the
  given durations.

---

### tundra.duration:compare

Compares one duration to another, returning if the first is less
than, equal to, or greater than the second, or if the comparison
itself is indeterminate.

Indeterminate comparisons occur when, for example, comparing 1 month
with 30 days: as the result could change depending on the month in
question, it is therefore considered indeterminate.

#### Inputs:

* `$duration.first` is a duration string to be compared to
  `$duration.second`.
* `$duration.first.pattern` is an optional pattern describing the type
  of duration specified by `$duration.first`. Defaults to an [ISO8601]
  XML string.
* `$duration.second` is a duration string to be compared to
  `$duration.first`.
* `$duration.second.pattern` is an optional pattern describing the
  type of duration specified by `$duration.second`. Defaults to an
  [ISO8601] XML string.

#### Outputs:

* `$lesser?` is `true` if `$duration.first` is a smaller duration than
  `$duration.second`.
* `$equal?` is `true` if `$duration.first` is equivalent to
  `$duration.second`.
* `$greater?` is `true` if `$duration.first` is larger than
  `$duration.second`.
* `$indeterminate?` is `true` if `$duration.first` and `$duration.second`
  cannot be compared.

---

### tundra.duration:end

Returns the duration between the given monotonic start time and the
current monotonic time as a duration string.

This service is designed to be used in conjunction with
`tundra.duration:start`, and uses [java.lang.System.nanoTime()] for
its implementation:

> This method can only be used to measure elapsed time and is not
> related to any other notion of system or wall-clock time. The
> value returned represents nanoseconds since some fixed but
> arbitrary origin time (perhaps in the future, so values may be
> negative). The same origin is used by all invocations of this
> method in an instance of a Java virtual machine; other virtual
> machine instances are likely to use a different origin.
>
> This method provides nanosecond precision, but not necessarily
> nanosecond resolution (that is, how frequently the value
> changes) - no guarantees are made except that the resolution is
> at least as good as that of currentTimeMillis().
>
> Differences in successive calls that span greater than
> approximately 292 years (263 nanoseconds) will not correctly
> compute elapsed time due to numerical overflow.
>
> The values returned by this method become meaningful only when
> the difference between two such values, obtained within the same
> instance of a Java virtual machine, is computed.

#### Inputs:

* `$datetime.monotonic.start` is the monotonic start time in
  nanoseconds.
* `$duration.pattern` is an optional desired pattern used to format
  the duration strings. Defaults to an [ISO8601] XML string.

#### Outputs:

* `$datetime.monotonic.start` is the monotonic start time in
  nanoseconds.
* `$datetime.monotonic.end` is the monotonic end time in
  nanoseconds.
* `$duration.measured` is the measured duration from the given start
  to the given end time.

[java.lang.System.nanoTime()]: <https://docs.oracle.com/javase/6/docs/api/java/lang/System.html#nanoTime()>


---

### tundra.duration:format

Formats arbitrarily specified duration strings according to the
desired pattern.

A start instant may be required when formatting fields with
indeterminate values, such as converting months to days (because the
number of days in a month varies).

#### Inputs:

* `$duration.input` is an `IData` document containing arbitrarily
  specified duration strings to be formatted.
* `$pattern.input` is an optional pattern describing the type of
  duration specified by the duration strings. Defaults to an
  [ISO8601] XML string.
* `$patterns.input` is an optional list of duration patterns that
  the duration strings might conform to, useful when the exact
  pattern is not known. A parse is attempted for each pattern until
  the first successful parse, or until all patterns have been tried
  in which case an unparseable duration exception will be thrown.
* `$pattern.output `is an optional desired pattern used to format the
  duration strings. Defaults to an [ISO8601] XML string.
* `$datetime` is an optional datetime string used as a starting
  instant to resolve indeterminate values (such as the number of
  days in a month).
* `$datetime.pattern` is an optional datetime pattern that `$datetime`
  conforms to, that will be used to parse the datetime string.
  Defaults to an [ISO8601] XML datetime.

#### Outputs:

* `$duration.output` contains the duration strings formatted according
  to `$pattern.output`.

---

### tundra.duration:multiply

Multiplies the given duration by the given factor.

A start instant may be required when formatting fields with
indeterminate values, such as converting months to days (because the
number of days in a month varies).

#### Inputs:

* `$duration` is an duration string to be multiplied.
* `$pattern.input` is an optional pattern describing the type of
  duration specified by the `$duration` string. Defaults to an
  [ISO8601] XML string.
* `$pattern.output` is an optional desired pattern used to format the
  returned `$duration` string. Defaults to an [ISO8601] XML string.
* `$datetime` is an optional datetime string used as a starting
  instant to resolve indeterminate values (such as the number of
  days in a month).
* `$datetime.pattern` is an optional datetime pattern that `$datetime`
  conforms to, that will be used to parse the datetime string.
  Defaults to an [ISO8601] XML datetime.
* `$factor` is a signed decimal used to multiply the given `$duration`.

#### Outputs:

* `$duration` is the duration string multiplied by the given `$factor`
  and formatted according to `$pattern.output`.

---

### tundra.duration:negate

Reverses the sign of the given duration.

#### Inputs:

* `$duration` is an duration string to be negated.
* `$pattern.input` is an optional pattern describing the type of
  duration specified by the `$duration` string. Defaults to an
  [ISO8601] XML string.
* `$pattern.output` is an optional desired pattern used to format the
  returned `$duration` string. Defaults to an [ISO8601] XML string.

#### Outputs:

* `$duration` is the negated duration string, formatted according to
  `$pattern.output`.

---

### tundra.duration:start

Returns the current value of the running Java Virtual Machine's
high-resolution monotonic time source, in nanoseconds, for use when
measuring elapsed time.

This service is designed to be used in conjunction with
`tundra.duration:end`, and uses [java.lang.System.nanoTime()] for
its implementation:

> This method can only be used to measure elapsed time and is not
> related to any other notion of system or wall-clock time. The
> value returned represents nanoseconds since some fixed but
> arbitrary origin time (perhaps in the future, so values may be
> negative). The same origin is used by all invocations of this
> method in an instance of a Java virtual machine; other virtual
> machine instances are likely to use a different origin.
>
> This method provides nanosecond precision, but not necessarily
> nanosecond resolution (that is, how frequently the value
> changes) - no guarantees are made except that the resolution is
> at least as good as that of currentTimeMillis().
>
> Differences in successive calls that span greater than
> approximately 292 years (263 nanoseconds) will not correctly
> compute elapsed time due to numerical overflow.
>
> The values returned by this method become meaningful only when
> the difference between two such values, obtained within the same
> instance of a Java virtual machine, is computed.

#### Outputs:

* `$datetime.monotonic.start` is the current monotonic time in
  nanoseconds.

---

### tundra.duration:subtract

Subtracts one duration (x) from another (y), returning (x - y).

#### Inputs:

* `$duration.x` is a duration string to subtract `$duration.y` from.
* `$duration.y` is a duration string to be subtracted from
  `$duration.x`.
* `$pattern.input` is an optional pattern describing the type of
  duration specified by the `$duration.x` and `$duration.y` strings.
  Defaults to an [ISO8601] XML string.
* `$pattern.output` is an optional desired pattern used to format the
  returned `$duration` string. Defaults to an [ISO8601] XML string.

#### Outputs:

* `$duration` is a duration string equal to (`$duration.x` - `$duration.y`),
  and formatted according to `$pattern.output`.

---

### tundra.excel:emit

Serializes an `IData` document as a Microsoft Excel spreadsheet.

#### Inputs:

* `$document` is the `IData` document containing one or more `IData[]`
  document lists representing a sheet in the resulting spreadsheet.
  Spreadsheet cells are created using the values provided, with the
  resulting type of cell determined as follows:
  * `number`: value is specified as an instance of `java.lang.Number`,
    or value is specified as a string that is parseable using the
    `java.lang.BigDecimal` grammar.
  * `datetime`: value is specified as an instance of `java.util.Calendar`
    or `java.util.Date`, or value is specified as a string that is
    parseable as an ISO8601 date, time, or datetime.
  * `boolean`: value is specified as an instance of `java.lang.Boolean`,
    or value is specified as either the string `true` or `false`.
  * `formula`: value is specified as a string prefixed with an equals
    character (`=`) followed by an Excel formula.
  * `string`: value is specified as a string, optionally enclosed with
    double-quotes (`"`) if value is parseable as a number, datetime,
    boolean, or formula, but instead should be treated as a literal
    string. Note the optional double-quotes are removed from the
    resulting cell value.
* `$content.extension` is an optional choice of `xlsx` or `xls`, which
  specifies the Microsoft Excel version to be used. Defaults to `xlsx`.
* `$content.mode` is an optional choice of `stream` or `bytes`, which
  specifies the type of object `$content` is returned as. Defaults to
  `stream`.

#### Outputs:

* `$content` is the resulting serialization of the sheets in `$document`
  as a Microsoft Excel spreadsheet.

---

### tundra.excel:parse

Parses a Microsoft Excel spreadsheet as an `IData` document.

#### Inputs:

* `$content` is a byte array or input stream containing a Microsoft
  Excel spreadsheet to be parsed.

#### Outputs:

* `$document` is the resulting `IData` document containing `IData[]`
  document lists representing the sheets from the given spreadsheet.

---

### tundra.exception:raise

Throws the given exception, or a new exception with the given message.

#### Inputs:

* `$message` is an optional error message to use when constructing the
  new `com.wm.app.b2b.server.ServiceException` exception object to be
  thrown. If not specified, an empty message will be used to construct
  the exception object.
* `$exception` is an optional existing [java.lang.Throwable] object to be
  thrown. If specified, `$message` is not used.

---

### tundra.file:copy

Copies the content of the source file to the target file.

#### Inputs:

* `$file.source` is the name of the file to be copied from, specified
  as either a relative or absolute file path or `file:` [URI].
* `$file.target` is the name of the file to be copied to, specified
  as either a relative or absolute file path or `file:` [URI]. If the
  target file already exists, it will be overwritten or appended to,
  depending on the `$file.mode` selected, with the source file
  content.
* `$file.mode` determines what to do when the target file already
  exists. Defaults to `create` if not specified.
  * `append` will append the given content to the file if it already
    exists.
  * `create` will throw an exception if the file already exists.
  * `write` will overwrite the file with the given content if it
    already exists.
* `$file.buffer.size` is an optional size in bytes to use when
  buffering the data being copied in memory.

---

### tundra.file:create

Atomically creates a new empty file if a file with this name does not
yet exist.

The check for the existence of the file and the creation of the file
if it does not exist are a single operation that is atomic with respect
to all other file system activities that might affect the file.

#### Inputs:

* `$file` is the name of the file to be created, specified as either a
  relative or absolute file path or `file:` [URI]. If the file already
  exists, an exception will be thrown. If not specified, a new
  empty temporary file will be created.

#### Outputs:

* `$file` is the name of the file which was created.

---

### tundra.file:digest

Returns a message digest calculated from the given file's contents
using the specified algorithm. A message digest is a secure one-way
fixed-length hash of arbitrary data.

#### Inputs:

* `$file` is the name of the file to use, specified as either a
  relative or absolute file path or `file:` [URI].
* `$digest.algorithm` is the name of the hashing algorithm to use
  when calculating the message digest. Defaults to `SHA-512`.
* `$digest.mode` is a choice of `stream`, `bytes`, `base64`, or
  `hex`, and determines how the calculated digest is returned.
   Defaults to `stream`.

#### Outputs:

* `$digest` is the calculated message digest for the given file's
  contents, returned either as a byte array, input stream,
  base64-encoded or hex-encoded string, depending on the
  `$digest.mode` specified.

---

### tundra.file:equal

Returns whether the given file's equal, by comparing cryptographic
message digests calculated from each file's contents.

#### Inputs:

* `$file.source` is the name of source file to use, specified as
  either a relative or absolute file path or `file:` [URI].
* `$file.target` is the name of target file to use, specified as
  either a relative or absolute file path or `file:` [URI].
* `$raise?` is an optional boolean specifying whether to throw an
  exception if the file's contents are not equal. Defaults to
  `false`.

#### Outputs:

* `$file.equal?` specifies whether the two file's contents are equal.

---

### tundra.file:executable

Returns `true` if Integration Server can execute to the given file.

#### Inputs:

* `$file` is the name of the file to test if Integration Server has
  execution permissions, specified as either a relative or absolute
  file path or `file:` [URI].

#### Outputs:

* `$executable?` is a boolean flag indicating if Integration Server
  has permission to execute the given file. If the file does not
  exist, `false` is returned.

---

### tundra.file:exists

Returns `true` if the given file exists.

#### Inputs:

* `$file` is the name of the file to test existence of, specified as
  either a relative or absolute file path or `file:` [URI].

#### Outputs:

* `$exists?` is a boolean flag indicating if the given file exists.

---

### tundra.file:gzip

Compresses the given file using the gzip algorithm.

#### Inputs:

* `$file` is the name of the file to be read, specified as either a
  relative or absolute file path or `file:` [URI].
* `$file.gzip` is the optional name of the resulting compressed file,
  specified as either a relative or absolute file path or `file:`
  [URI]. Defaults to the source file suffixed with `.gz`.
* `$file.remove?` is an optional boolean flag indicating whether the
  source file should be deleted once it has been compressed.
  Defaults to `false`.

#### Outputs:

* `$file.gzip` is resulting compressed file name specifed as a
  `file:` [URI].

---

### tundra.file:length

Returns the length of the given file in bytes.

#### Inputs:

* `$file` is the name of the file whose length is to be checked,
  specified as either a relative or absolute file path or `file:`
  [URI].

#### Outputs:

* `$length` is the length or size of the given file in bytes. If the
  file does not exist, zero is returned.

---

### tundra.file:match

Returns `true` if the given file name matches the given regular
expression or wildcard pattern.

#### Inputs:

* `$file` is the name of the file to be checked against the given
  `$pattern`, specified as either a relative or absolute file path or
  `file:` [URI].
* `$pattern` is either a regular expression or wildcard pattern,
  depending on the `$pattern.mode` selected, to check the file name
  against.
* `$pattern.mode` is an optional choice of `regular expression` or
  `wildcard`, which determines the type of pattern specified.
  Defaults to `regular expression`.

#### Outputs:

* `$match?` is a boolean flag indicating if the file name matches the
  given `$pattern`. Note that only the file name is considered in the
  match, not the path.

---

### tundra.file:normalize

Returns the canonical `file:` URI that represents the given file.

#### Inputs:

* `$file` is the file name to be normalized to the canonical `file:`
  [URI], specified as either a relative or absolute file path or
  `file:` [URI].

#### Outputs:

* `$file` is the canonical `file:` [URI] that represents the given
  file.

---

### tundra.file:process

Provides a safe way of processing a file as a stream, which is useful
for large files or memory constrained environments, by opening a file
for reading, writing, or appending, calling the given service with
the opened file stream object as an input, and finally closing the
stream when done.

As such, the invoked service does not need to open or close the file
stream itself, it only needs to process the opened file stream. The
stream is guaranteed to be closed automatically, regardless of
whether an exception is encountered by the service.

#### Inputs:

* `$file` is the name of the file to be processed, specified
  as either a relative or absolute file path or `file:` [URI].
* `$file.mode` determines what how the file should be opened, and
  what to do when the file already exists. Defaults to `read` if not
  specified.
    otherwise will create the file and open it for writing.
  * `append` will open the file for appending.
  * `create` will throw an exception if the file already exists,
  * `read` will open the file for reading.
  * `write` will open the file for (over)writing.
* `$service` is the fully-qualified name of the service which
  will be called to process the opened file.
* `$pipeline` is an optional `IData` document for specifying
  arbitrary input parameters to the call to `$service`. If provided,
  the call to `$service` is scoped with this `IData`, and will not
  have access to the global pipeline.
* `$service.input` is an optional input parameter name used when
  adding the opened file stream to the input pipeline of the call to
  `$service`. Defaults to `$stream`.

#### Outputs:

* `$pipeline` is an optional `IData` document representing the output
  pipeline of the call to `$service`. This will only be returned if
  an input `$pipeline` was provided. If no input `$pipeline` was
  provided, any outputs from the call to `$service` will be merged
  directly into the global pipeline.

---

### tundra.file:purge

Deletes all files that match the given filename pattern older than
the given duration.

For example, specifying the following input values will delete all
files older than one day with a `txt` extension from the `C:\Temp`
directory:

    $file = C:\Temp\*.txt
    $duration = P1D
    $filter.type = wildcard

#### Inputs:

* `$file` is the file pattern used to determine which files to delete
  specified as either a relative or absolute file path or `file:`
  [URI], where the filename part is specified as a pattern used to
  match multiple files in the same directory.
* `$duration` is an optional duration of time representing the age of
  files to be deleted. For example, a duration of P1D will delete
  all files that were last modified 24 hours ago or earlier.
* `$duration.pattern` is an optional pattern describing the type of
  duration specified by the `$duration` string. Defaults to an
  [ISO8601] XML string.
* `$filter.type` is an optional choice of either `regular
  expression`, `wildcard`, or `literal`, which determines the type of
  filename pattern provided. Defaults to `regular expression`.

#### Outputs:

* `$count` is the number of files deleted by this service.

---

### tundra.file:read

Reads a file in full, returning the content as either an input
stream, byte array, or string.

As this service reads the entire file into memory, consider using
`tundra.file:process` instead for large files or in memory
constrained environments.

#### Inputs:

* `$file` is the name of the file to be read, specified as either a
  relative or absolute file path or `file:` [URI].
* `$content.mode` is an optional choice of `stream`, `bytes`, or
  `string`, which specifies the type of object `$content` is returned
   as. Defaults to `stream`.
* `$content.encoding` is an optional character set to use when
  reading the file as a string. Defaults to [UTF-8].

#### Outputs:

* `$content` is the file contents returned as either an input stream,
  byte array, or string, depending on the `$content.mode` selected.
  Note that even when returned as an input stream, the entire file
  has been read into memory. This mode is provided as a convenience
  only, and since the file contents are returned as a
  [java.io.ByteArrayInputStream] object the stream does not need to
  be explicitly closed as no system resources (file handles) are
  held.

---

### tundra.file:readable

Returns `true` if Integration Server can read the given file.

#### Inputs:

* `$file` is the name of the file to test if Integration Server has
  read permissions, specified as either a relative or absolute
  file path or `file:` [URI].

#### Outputs:

* `$readable?` is a boolean flag indicating if Integration Server
  has permission to read the given file. If the file does not
  exist, `false` is returned.

---

### tundra.file:reflect

Returns useful details about the given file.

#### Inputs:

* `$file` is the name of the file to return details about, specified
  as either a relative or absolute file path or `file:` [URI].

#### Outputs:

* `$file.properties` is an `IData` document containing the following
  details about the given `$file`:
  * `exists?` is a boolean flag indicating if the file exists.
  * `parent` is the canonical `file:` [URI] that represents the
    parent directory that contains the file.
  * `name` is the name component not including the path of the file.
  * `base` is the name component not including the path or extension
    of the file.
  * `extension` is the part of the name after the final period used
    to determine the type of the file.
  * `type` is the [mime type] associated with the file.
  * `length` is the length or size of the file in bytes. If the file
    does not exist, zero is returned.
  * `modified` is the last modified datetime of the file.
  * `executable?` is a boolean flag indicating if Integration Server
    has permission to execute the file. If the file does not exist,
    `false` is returned.
  * `readable?` is a boolean flag indicating if Integration Server
    has permission to read the file. If the file does not exist,
    `false` is returned.
  * `writable?` is a boolean flag indicating if Integration Server
    has permission to write or append to the file. If the file does
    not exist, `false` is returned.
  * `uri` is the canonical `file:` [URI] that represents the file.

---

### tundra.file:remove

Deletes the given file, if it exists.

#### Inputs:

* `$file` is the name of the file to be deleted, specified as either
  a relative or absolute file path or `file:` [URI]. If the file does
  not exists, this service does nothing (it does not throw an
  exception).

---

### tundra.file:rename

Renames the source file to the target file name.

#### Inputs:

* `$file.source` is the name of the file to be renamed, specified as
  either a relative or absolute file path or `file:` [URI]. If the
  source file does not exist, an exception will be thrown.
* `$file.target` is the new name for the renamed file, specified as
  either a relative or absolute file path or `file:` [URI]. If the
  target file already exists, an exception will be thrown.

---

### tundra.file:touch

Updates the modification time of the given file to a given datetime,
or the current time.

Also optionally creates the file if it does not already exist.

#### Inputs:

* `$file` is the name of the file to be touched, specified as either
  a relative or absolute file path or `file:` [URI]. If the file does
  not exist, it will be created if `$file.create?` is `true`. If the
  file does exist or is created, its modification time will be
  updated to either the `$file.updated` datetime if specified, or the
  current time.
* `$file.create?` is an optional boolean indicating whether to create
  the file if it does not already exist. Defaults to `true`.
* `$file.updated` is an optional datetime to use when updating the
  modification time of the file. Defaults to the current time.

---

### tundra.file:type

Determines the [mime type] for the given file.

Integration Server file extension to [mime type] mappings are defined
in the file `./lib/mime.types`. If the [mime type] cannot be found,
it defaults to the [mime type] for arbitrary binary data:

    application/octet-stream

#### Inputs:

* `$file` is the name of the file whose mime type is to be
  determined, specified as either a relative or absolute file path or
  `file:` [URI]. The file is not required to exist, since the mime
  type is determined purely from the file name itself.

#### Outputs:

* `$type` is the [mime type] of the given file.

---

### tundra.file:writable

Returns `true` if Integration Server can write to the given file.

#### Inputs:

* `$file` is the name of the file to test if Integration Server has
  write permissions, specified as either a relative or absolute
  file path or `file:` [URI].

#### Outputs:

* `$writable?` is a boolean flag indicating if Integration Server
  has permission to write or append to the given file. If the
  file does not exist, `false` is returned.

---

### tundra.file:write

Writes or appends the content, provided as a string, byte array or
input stream, to the given file.

If no file is specified, a new temporary file is created
automatically.

If the path to the file does not exist, this service will attempt to
create the directories that comprise the path before writing the
file.

#### Inputs:

* `$file` is the optional name of the file to which the given content
  is to be written or appended, specified as either a relative or
  absolute file path or `file:` [URI]. If not specified, a new
  temporary file will be created automatically.
* `$file.mode` determines what to do when the file already exists.
  Defaults to `create` if not specified.
  * `append` will append the given content to the file if it already
    exists.
  * `create` will throw an exception if the file already exists.
  * `write` will overwrite the file with the given content if it
    already exists.
* `$file.buffer.size` is an optional size in bytes to use when
  buffering the data being written in memory.
* `$content` is a string, byte array, or input stream containing data
  to be written or appended to the given file.
* `$content.encoding` is an optional character set to use when
  `$content` has been provided as a string. Defaults to [UTF-8].

#### Outputs:

* `$file` is the name of the file that was written or appended to. If
  no input file name was specified, this is the name of the temporary
  file that was created automatically.

---

### tundra.file:zip

Compresses the given file using the zip algorithm.

#### Inputs:

* `$file` is the name of the file to be read, specified as either a
  relative or absolute file path or `file:` [URI].
* `$file.zip` is the optional name of the resulting compressed file,
  specified as either a relative or absolute file path or `file:`
  [URI]. Defaults to the source file suffixed with `.zip`.
* `$file.remove?` is an optional boolean flag indicating whether the
  source file should be deleted once it has been compressed.
  Defaults to `false`.

#### Outputs:

* `$file.zip` is resulting compressed file name specifed as a `file:`
  [URI].

---

### tundra.gzip:compress

Compresses the given content using the [gzip] file compression
format.

#### Inputs:

* `$content` is the data to be compressed, specified as a string,
  byte array, or input stream.
* `$content.encoding` is an optional character set used to encode
  the text data when `$content` is specified as a string. Defaults to
  [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or
  `string`, which determines the type of object `$content.gzip` is
  returned as. Defaults to `stream`.

#### Outputs:

* `$content.gzip` is the resulting compressed data in [gzip] format.

---

### tundra.gzip:decompress

Decompresses the given content using the [gzip] file compression
format.

#### Inputs:

* `$content.gzip` is the [gzip] compressed data to be decompressed,
  specified as a base64-encoded string, byte array, or input stream.
* `$content.encoding` is an optional character set used to decode the
  decompressed data when the chosen `$content.mode` is `string`.
  Defaults to [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or
  `string`, which determines the type of object `$content` is
  returned as. Defaults to `stream`.

#### Outputs:

* `$content` is the resulting decompressed data.

---

### tundra.hjson:emit

Serializes an `IData` document as an [Hjson] formatted string, byte
array, or input stream.

#### Inputs:

* `$document` is the `IData` document to be serialized as an [Hjson]
  string, byte array, or input stream.
  * `recordWithNoID` is an optional array such as an `IData[]` document
    list which if specified will be serialized directly as a [Hjson]
    array.
* `$content.encoding` is an optional character set to use when
  encoding the resulting text data to a byte array or input stream.
  Defaults to [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or `string`
  which specifies the type of object `$content` is returned as.
  Defaults to `stream`.

#### Outputs:

* `$content` is the resulting serialization of `$document` as [Hjson]
  content.

---

### tundra.hjson:parse

Parses [Hjson] content specified as a string, byte array, or input
stream into an `IData` document.

#### Inputs:

* `$content` is a string, byte array, or input stream containing
  [Hjson] content to be parsed.
* `$content.encoding` is an optional character set to use when
  `$content` is provided as a byte array or input stream to decode the
  contained text data. Defaults to [UTF-8].

#### Outputs:

* `$document` is the resulting `IData` document representing the parsed
  `$content`.
  * `recordWithNoID` is an optional array which is returned only when
    the given [Hjson] content represents an array rather than an
    object.

---

### tundra.html:decode

[HTML] decodes the string values in the given `IData` document.

[HTML entities], such as `&lt;` and `&gt;`, are decoded to the
appropriate character representation, such as `<` and `>`.

#### Inputs:

* `$document.encoded` is an optional `IData` document containing
  arbitrarily specified string values to be [HTML] decoded.

#### Outputs:

* `$document.decoded` is an `IData` document containing the arbitrarily
  specified string values with [HTML entities] decoded.

---

### tundra.html:emit

Serializes an `IData` document as an [HTML] fragment suitable for
embedding in an [HTML] page, formatted as a string, byte array,
or input stream.

Child elements that are `IData` documents, `IData[]` document lists,
`Object[]` object lists, or `Object[][]` object tables are
recursively serialized to clean [HTML] table elements.

#### Inputs:

* `$document` is the `IData` document to be serialized as an [HTML]
  string, byte array, or input stream.
  * `recordWithNoID` is an optional `IData[]` document list, which
    can be used to emit a document list rather than a document.
* `$content.orientation` is an optional choice of orientation to use
  when emitting `IData` documents as HTML:
  * `portrait`: will emit an `IData` document with each key value pair
    as an [HTML] table row. This is the default, if not specified.
  * `landscape`: will emit an `IData` document with each key value
    pair represented as an [HTML] table column.
* `$content.encoding` is an optional character set to use when
  encoding the resulting text data to a byte array or input stream.
  Defaults to [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or
  `string`, which specifies the type of object `$content` is returned
  as. Defaults to `stream`.

#### Outputs:

* `$content` is the resulting serialization of `$document` as [HTML]
  content.

---

### tundra.html:encode

[HTML] encodes the string values in the given `IData` document.

Reserved characters in [HTML], such as `<` and `>`, are encoded to the
appropriate [HTML entity], such as `&lt;` and `&gt;`, to ensure the
[HTML] is rendered correctly by web browsers and other [HTML]
rendering software.

#### Inputs:

* `$html.decoded` is an optional `IData` document containing
  arbitrarily specified string values to be [HTML] encoded.
  * `$value` is optional and specified as an input only for developer
    convenience when using this service for a single string value.
  * `$value.list` is optional and specified as an input only for
    developer convenience when using this service for a list of
    string values.
  * `$value.table` is optional and specified as an input only for
    developer convenience when using this service for a table of
    string values.

#### Outputs:

* `$html.encoded` is an `IData` document containing the arbitrarily
  specified string values with [HTML entities] encoded.
  * `$value` is optional and specified as an output only for developer
    convenience when using this service for a single string value.
  * `$value.list` is optional and specified as an output only for
    developer convenience when using this service for a list of
    string values.
  * `$value.table` is optional and specified as an output only for
    developer convenience when using this service for a table of
    string values.

---

### tundra.http:client

Provides an [HTTP] client for issuing requests against [HTTP]
servers.

#### Inputs:

* `$request` is an `IData` document containing the parameters required
  for making an [HTTP] request to an [HTTP] server.
  * `uri` is the [URI] the request will be sent to.
  * `method` is the [HTTP] method for the request, a choice of: `get`,
    `post`, `head`, `put`, `delete`, `options`, or `trace`. Defaults to `get`.
  * `content` is a string, byte array, input stream, or `IData`
    document used as the body of the request. If an `IData` document
    is specified for a get request, the elements are encoded as
    query string parameters in the request [URI]. If an `IData`
    document is specified for a post or put request, the elements
    are encoded as form parameters in the request body.
  * `encoding` is an optional character set used to encode the given
    content if provided as a string.
  * `authority` are optional credentials for the request.
  * `headers` are the [HTTP] headers sent with the request.
  * `query` are optional query string parameters to be added to the
    request [URI], provided separately for convenience.
  * `timeout` is an optional timeout duration for the request
    specified as an XML duration string. Defaults to 60 seconds.
  * `session` is an optional choice of `new` or `reuse`, and determines
    whether a new [HTTP] connection is established for this request
    or whether an existing pooled [HTTP] connection can be used for
    this request respectively. Defaults to `new`.
* `$response.handler` is an optional custom [HTTP] response handler
  service, which implements the `tundra.schema.http.response:handler`
  specification. Defaults to `tundra.http.response:handle`, if not
  specified, which performs the following:
  * checks the [HTTP response code] is < 400, and throws an exception
    when it is not
  * converts the response header keys to be case insensitive
  * converts the response body stream to bytes

#### Outputs:

* `$response` is an `IData` document containing the [HTTP] server
  response for the given request.
  * `uri` is the request [URI] that was used to generate this response,
    provided for convenience.
  * `status` is the response status from the [HTTP] server.
    * `code` is the [HTTP response code] returned by the [HTTP] server
      for this request.
    * `message` is the [HTTP] response message associated with the
      `code`.
  * `headers` are the [HTTP] headers returned by the [HTTP] server in
    its response.
  * `content` is the [HTTP] response body returned.

---

### tundra.http.response:accept

A very accepting [HTTP] response handler: accepts any
[HTTP response code], normalizes the response header keys to be
case-insensitive, and converts the response body stream to bytes.

#### Inputs:

* `$response` is the [HTTP] response to be processed by this service.

#### Outputs:

* `$response` is the processed [HTTP] response, where the response
  header keys are normalized to be case-insensitive, and the
  response body is returned as a byte array.

---

### tundra.http.response:handle

Standard [HTTP] response handler: checks that the [HTTP response code] is
< 400, normalizes the response header keys to lower case, and converts the
response body stream to bytes. If the [HTTP response code] is >= 400, an
exception will be thrown.

#### Inputs:

* `$response` is the [HTTP] response to be processed by this service.

#### Outputs:

* `$response` is the processed [HTTP] response, where the
  [HTTP response code] is guaranteed to be < 400, the response header
  keys are normalized to lower case, and the response body is returned
  as a byte[] array.

---

### tundra.http.response:status

Returns the standard message associated with the given [HTTP status code].

#### Inputs:

* `$code` is the [HTTP status code] to return the associated message for.

#### Outputs:

* `$message` is the standard message associated with the given code.

---

### tundra.http.route:clear

Removes all configured custom HTTP routes from the Integration
Server HTTP request dispatcher. Once this service is invoked,
no custom HTTP routes will be in effect.

---

### tundra.http.route:list

Returns a list of all the custom HTTP routes currently in effect.

#### Outputs:

* `$routes` is an `IData[]` document list containing all the directives
  which have been registered with the Integration Server HTTP
  request dispatcher, and the associated custom routing
  instructions.
  * `directive` is the first directory or folder in a URI path, and
    is used by the Integration Server HTTP request dispatcher for
    dispatching requests to the appropriate handler. Note that
    there are a number of built-in directives in Integration
    Server, which are prohibited from being overridden by custom
    routes:
    * `invoke`
    * `soap`
    * `web`
    * `wm-message`
    * `ws`
  * `routes` is an `IData[]` document list of the custom HTTP routing
    instructions associated with this directive in order of
    precedence.
    * `method` is the [HTTP request method] used to match this HTTP
      routing instruction to incoming HTTP requests, and is one of:
      * `get`
      * `put`
      * `post`
      * `head`
      * `connect`
      * `options`
      * `delete`
      * `trace`
    * `uri` is the [URI template] used to match this HTTP
      routing instruction to incoming HTTP requests. Parameters
      specified in the template will be captured against the
      incoming HTTP request URI, and added to the input pipeline
      of the resulting `service` invocation. For example:

          /messages/{client}/{type}

      Will add the variables `client` and `type` to the input pipeline
      of the resulting `service` invocation, with their values
      derived from the matched request URI. A request URI equal to:

          /messages/foo/bar

      will set the input pipeline variables as follows:

          client = "foo"
          type   = "bar"

    * `target` is either the fully-qualified name of a service that
      is invoked, or a URL for a DSP page or static asset in the
      pub directory of a package that will be returned, when an
      incoming HTTP request matches the associated `method` and `uri`.
    * `description` is an optional description of the the HTTP
      routing instruction.
    * `source` is the HTTP route file name from which this routing
      instruction was configured.
  * `routes.length` is the number of routes associated with this
    directive.
* `$routes.length` is the number of directives registered with the
  Integration Server HTTP dispatcher for custom HTTP routing.

---

### tundra.http.route:refresh

Reloads all configured custom HTTP routes from the server- and
package-specific configuration files.

Custom HTTP routes specify an [HTTP request method] and [URI
template] which, if matched by an Integration Server HTTP request,
will return the associated target (either by invoking the target if
it is a service in the same way as the built-in `/invoke` URIs work,
or by returning a DSP page or static asset if the target is not a
service), and can be used to abstract the API used by HTTP clients
from the implementation (unlike `/invoke` URIs or package DSP pages
URIs, which leak the implementation in the URI's path).

Custom HTTP routes can either be configured in a server-specific
configuration file, or a package-specific configuration file.

To register a server-specific custom HTTP route, create or edit the
following file:

    ./config/http-routes.cnf

To register a package-specific custom HTTP route, create or edit the
following file:

    ./packages/<PackageName>/config/http-routes.cnf

The following file can be used as a template for the above files:

    ./packages/Tundra/config/http-routes.example.cnf

Note that server-specific HTTP routes take precedence over package-
specific routes, and package-specific routes are loaded in lexical
package name order. Routing instructions inside each configuration
file must be specified in order of precedence, and all HTTP routes
are aggregated into a single routing table in Integration Server,
and therefore care must be taken so that routes specified across
the various configuration files do not override each other.

Changes to these HTTP route configuration files do not take effect
until this service is invoked, either manually or by reloading the
Tundra package.

---

### tundra.integer:absolute

Returns the absolute value of the given integer.

#### Inputs:

* `$integer` is a signed integer.

#### Outputs:

* `$integer` is the absolute (unsigned) value of the given
  integer.

---

### tundra.integer:add

Adds the given integers together, returning the result.

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as an integer number.

#### Outputs:

* `$result` is the total sum of the given integers.

---

### tundra.integer:average

Returns the average value of the given integers.

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as an integer number.

#### Outputs:

* `$average` is the average value of the given integers.

---

### tundra.integer:decrement

Decrements the given integer by one. Returns minus one if
no integer is specified, so that this service can be used
in a loop to both initialize and decrement a counter variable.

#### Inputs:

* `$integer` is the integer to be decremented by one.

#### Outputs:

* `$integer` is the given integer minus one, or minus one,
  if no input integer was specified.

---

### tundra.integer:divide

Divides one integer by another, returning the result and
remainder.

#### Inputs:

* `$dividend` is the number to be divided.
* `$divisor` is the number to divide by.

#### Outputs:

* `$result` is the result of dividing the given numbers.
* `$remainder` is the remainder value after from the division.

---

### tundra.integer:emit

Returns an string representation of the given integer object.

#### Inputs:

* `$object` is either a `java.math.BigDecimal`, `java.math.BigInteger`,
  `java.lang.Double`, `java.lang.Float`, `java.lang.Integer`, or
  `java.lang.Long` object.
* `$radix` is an optional [radix] used when encoding the integer as a
  string. Defaults to 10 (decimal), if not specified.

#### Outputs:

* `$string` is the resulting string representation of the integer.

---

### tundra.integer:format

Formats arbitrarily specified number strings that conform to the
input pattern, according to the output pattern.

#### Inputs:

* `$number.input` is an `IData` document containing arbitrarily
  specified string representations of numbers.
* `$pattern.input` is an optional [java.text.DecimalFormat] pattern
  that the given strings conform to, and if specified will be used
  to parse the strings. If not specified, defaults to
  [java.math.BigDecimal grammar].
* `$patterns.input` is an optional list of [java.text.DecimalFormat]
  patterns that the given strings might conform to, which is useful
  when the exact pattern is not known. A parse is attempted for each
  pattern until the first successful parse, or until all patterns
  have been tried in which case an unparseable number exception
  will be thrown.
* `$pattern.output` is an optional [java.text.DecimalFormat] pattern
  that will be used to format the resulting number strings. If not
  specified, defaults to [java.math.BigDecimal grammar].

#### Outputs:

* `$number.output` contains the resulting formatted number strings.

---

### tundra.integer:increment

Increments the given integer by one. Returns one if no integer
was specified, so that this service can be used in a loop to
both initialize and increment a counter variable.

#### Inputs:

* `$integer` is the integer to be incremented by one.

#### Outputs:

* `$integer` is the given integer plus one, or one if no
  input integer was specified.

---

### tundra.integer:maximum

Returns the maximum value from the given integers.

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as an integer number.

#### Outputs:

* `$maximum` is the largest value of the given integers.

---

### tundra.integer:minimum

Returns the minimum value from the given integers.

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as an integer number.

#### Outputs:

* `$minimum` is the smallest value of the given integers.

---

### tundra.integer:multiply

Multiplies the given integers together, returning the result.

#### Inputs:

* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as an integer number.

#### Outputs:

* `$result` is the result of multiplying all given integer
  values together.

---

### tundra.integer:negate

Returns the negative value of the given integer (-x).

#### Inputs:

* `$integer` is an integer to be negated.

#### Outputs:

* `$integer` is the given integer multiplied by minus
  one.

---

### tundra.integer:parse

Returns an integer object by parsing the given string.

#### Inputs:

* `$string` is a string of digits optionally preceded by a plus or minus
  sign to be parsed.
* `$class` is an optional Java class name that determines the type of
  object returned, a choice of either `java.math.BigInteger`,
  `java.math.BigDecimal`, `java.math.Double`, `java.math.Float`,
  `java.lang.Integer`, or `java.lang.Long`. Defaults to `java.math.BigInteger`,
  if not specified.
* `$radix` is an optional [radix] used to interpret the digits in the
  given `$string`. Defaults to 10 (decimal), if not specified.

#### Outputs:

* `$object` is the resulting Java object representing the parsed integer.

---

### tundra.integer:power

Raises the given integer to the power of the given exponent (b^e).

#### Inputs:

* `$base` is an integer value to be raised to the given exponent.
* `$exponent` is the exponent to raise the base by.

#### Outputs:

* `$result` is the result of raising the given base by
  the given exponent (b^e).

---

### tundra.integer:random

Returns a cryptographically strong randomly generated integer using
an instance of the [java.security.SecureRandom] class.

#### Inputs:

* `$integer.random.class` is a choice of either [java.lang.Long],
  [java.lang.Integer], [java.lang.Short], or [java.lang.Byte],
  which determines the class of the output returned. Defaults to
  [java.lang.Long].

#### Outputs:

* `$integer.random` is the randomly generated integer provided as
  an instance of the specified class.

---

### tundra.integer:rebase

Converts a string representation of an integer from one radix or base
to another.

#### Inputs:

* `$string` is a string of digits optionally preceded by a plus or
  minus sign to be parsed.
* `$radix.input` is an optional [radix] used to interpret the digits
  in the given `$string`. Defaults to 10 (decimal), if not specified.
* `$radix.output` is an optional [radix] to convert the given `$string`
  to. Defaults to 10 (decimal), if not specified.

#### Outputs:

* `$string` is the resulting string representation of the integer
  converted from the input radix to the output radix.

---

### tundra.integer:remainder

Returns the remainder from dividing the given integers.

#### Inputs:

* `$dividend` is the number to be divided.
* `$divisor` is the number to divide by.

#### Outputs:

* `$remainder` is the remainder value after from the division.

---

### tundra.integer:shift

Bit shifts the given integer by the given distance, to the left
if distance is positive, and to the right if distance is negative.

#### Inputs:

* `$integer` is a string to be bit shifted.
* `$distance` is the number of bits to be shifted. When positive,
  a left bit shift is performed, when negative a right bit shift
  is performed.

#### Outputs:

* `$integer` is the given integer bit shifted by the given distance.

---

### tundra.integer:subtract

Subtracts one integer from another, returning the result.

#### Inputs:

* `$minuend` is an integer value to subtract from.
* `$subtrahend` is an integer value to be subtracted.

#### Outputs:

* `$result` is the result of subtracting the given values.

---

### tundra.integer:validate

Returns `true` if the given string can be parsed as an integer.

#### Inputs:

* `$integer` is a string to be validated as an integer.
* `$raise?` is an optional boolean indicating whether an exception
  should be thrown if the given integer is invalid. Defaults to
  `false`.

#### Outputs:

* `$valid?` is a boolean indicating if the given string is a valid
  integer.

---

### tundra.json:emit

Serializes an `IData` document as a [JSON] formatted string, byte
array, or input stream.

#### Inputs:

* `$document` is the `IData` document to be serialized as a [JSON]
  string, byte array, or input stream.
  * `recordWithNoID` is an optional array such as an `IData[]` document
    list which if specified will be serialized directly as a [JSON]
    array.
* `$content.minify?` is an optional boolean indicating whether the
  returned [JSON] content should be minified by removing extraneous
  white space. Defaults to `false`, and hence the returned content is
  pretty-printed by default.
* `$content.encoding` is an optional character set to use when
  encoding the resulting text data to a byte array or input stream.
  Defaults to [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or `string`
  which specifies the type of object `$content` is returned as.
  Defaults to `stream`.

#### Outputs:

* `$content` is the resulting serialization of `$document` as [JSON]
  content.

---

### tundra.json:parse

Parses [JSON] content specified as a string, byte array, or input stream
into an `IData` document.

#### Inputs:

* `$content` is a string, byte array, or input stream containing [JSON]
  content to be parsed.
* `$content.encoding` is an optional character set to use when
  `$content` is provided as a byte array or input stream to decode the
  contained text data. Defaults to [UTF-8].

#### Outputs:

* `$document` is the resulting `IData` document representing the parsed
  `$content`.
  * `recordWithNoID` is an optional array which is returned only when
    the given [JSON] content represents an array rather than an
    object.

---

### tundra.list.content:emit

Emits (serializes) each item in the given `IData[]` document list
to a string, byte array, or input stream, resulting in a list of
serialized content.

#### Inputs:

* `$documents` is a list of `IData` documents to be serialized as a
  string, byte array, or input stream.
* `$content.type` is the MIME media type that describes the format
  of all items in the resulting list of serialized content. Use this
  input argument when `$documents` contains like formats (for
  example, when all items adhere to the exact same [XML] MIME type).
* `$content.types` is a list of MIME media types with the same
  number of items as `$documents`, where `$content.types[n]`
  describes the format of the resulting serialized `$document[n]`.
  Use this input argument when `$documents` contains unlike formats
  (for example, a mixture of [XML] and [JSON] MIME types).
* `$content.encoding` is an optional character set to use when the
  `$content.mode` selected is bytes or stream. Defaults to [UTF-8].
* `$content.schema` is an optional input of the document reference
  or flat file schema to use to serialize all items in $documents.
  Use this input argument when $documents contains like formats (for
  example, when all items adhere to the exact same [XML] schema).
* `$content.schemas` is an optional input list with the same number
  of items as `$documents` of document reference or flat file
  schemas, where `$content.schemas[n]` is used to serialize
  `$contents[n]`. Use this input argument when `$documents` contains
  unlike formats (for example, a mixture of Flat File and [XML]
  formats).
* `$content.namespace` is a list of namespace prefixes and the URIs
  they map to, used when emitting [XML] content with elements in one
  or more namespaces. Use this input argument when `$documents`
  contains like formats (for example, when all items adhere to the
  exact same [XML] MIME type using the same namespaces).
* `$content.namespaces` is a list of namespace prefixes and the URIs
  they map to, used when emitting [XML] content with elements in one
  or more namespaces. Use this input argument when `$documents`
  contains unlike formats (for example, a mixture of [XML] formats
  that use different namespaces).
* `$content.validate?` is an optional boolean flag which when `true`
  will validate each `$document[n]` against `$content.schemas[n]` if
  specified, or `$content.schema` if specified, and throw an
  exception if the content is invalid. Defaults to `false`.
* `$content.mode` is an optional choice of stream, bytes, or string
  which determines the type of object the documents are serialized
  to.

#### Outputs:

* `$contents` is a list of strings, byte arrays, or input streams,
  depending on the `$content.mode` selected, where `$contents[n]` is
  the serialized `$document[n]` content.

---

### tundra.list.content:join

Many-to-one (N:1) conversion of content in one format to another
format.

Calls the given joining service, passing the parsed list of contents
as an input, and emitting the joined content as output. The joining
service must accept an `IData[]` document list, and return a single
IData document.

#### Inputs:

* `$contents` is a list of strings, byte arrays, or input streams
  containing content (structured/parseable data) to be joined or
  aggregated together into one item of content.
* `$service` is the fully-qualified joining service name that is
  called to join or aggregate the parsed contents. This service must
  accept an `IData[]` document list, and return a single IData
  document.
* `$pipeline` is an optional `IData` document for specifying arbitrary
  input arguments to the invocation of `$service`.
* `$content.types.input` is a list of MIME media types with the same
  number of items as `$contents`, where `$content.types[n]` describes
  the format of the `$contents[n]`. Use this input argument when
  `$contents` contains unlike formats (for example, a mixture of
  [XML] and [JSON] MIME types).
* `$content.type.input` is the MIME media type that describes the
  format of all items in `$contents`. Use this input argument when
  `$contents` contains like formats (for example, when all items
  adhere to the exact same [XML] MIME type).
* `$content.type.output` is the MIME media type that describes the
  format of the returned `$content`.
* `$namespaces.input` is a list of namespace prefixes and the URIs
  they map to, used when parsing [XML] content with elements in one
  or more namespaces. Use this input argument when `$contents`
  contains unlike formats (for example, a mixture of [XML] formats
  that use different namespaces).
* `$namespace.input` is a list of namespace prefixes and the URIs
  they map to, used when parsing [XML] content with elements in one
  or more namespaces. Use this input argument when `$contents`
  contains like formats (for example, when all items adhere to the
  exact same [XML] MIME type using the same namespaces).
* `$namespace.output` is a list of namespace prefixes and the URIs
  they map to, used when emitting [XML] content with elements in one
  or more namespaces.
* `$schemas.input` is an optional input list with the same number of
  items as `$contents` of document references or flat files schemas,
  where `$schemas[n]` is used to parse `$contents[n]`. Use this
  input argument when `$contents` contains unlike formats (for
  example, a mixture of flat file and xml formats).
* `$schema.input` is an optional input specifying the document
  reference or flat file schema to use to parse all items in
  `$contents`. Use this input argument when `$contents` contains
  like formats (for example, when all items adhere to the exact same
  XML schema).
* `$schema.output` is the document reference or flat file schema to
  use when serializing the document returned by `$service`.
* `$service.input` is an optional name to use for the parsed IData[]
  document list for the input pipeline of the `$service` invocation.
  Defaults to `$documents`.
* `$service.output` is an optional name to use for the output IData
  parameter returned by the `$service` invocation. Defaults to
  `$document`.
* `$encoding.input` is an optional character set to use when the
  `$contents` is provided as a list of input streams or byte arrays.
  Defaults to [UTF-8].
* `$encoding.output` is an optional character set to use when the
  `$mode.output` selected is bytes or stream. Defaults to [UTF-8].
* `$validate.input?` is an optional boolean flag which when `true` will
  validate the input content against the appropriate schema, and throw
  an exception if the content is invalid. Defaults to `false`.
* `$validate.output?` is an optional boolean flag which when `true` will
  validate the output content against the given `$schema.output`, and
  throw an exception if the content is invalid. Defaults to `false`.
* `$mode.output` is an optional choice of stream, bytes, or
  string, which determines the type of `$content` object returned.
  Defaults to stream.

#### Outputs:

* `$content` is a string, byte array, or input stream, depending on
  the `$mode.output` selected, of the serialized `IData` document
  returned by `$service`.

---

### tundra.list.content:parse

Parses a list of content (specified as a list of strings, bytes, or
input streams) into an `IData[]` document list.

#### Inputs:

* `$contents` is a list of strings, byte arrays, or input streams
  containing content (structured/parseable data) to be parsed.
* `$content.types` is a list of MIME media types with the same
  number of items as `$contents`, where `$content.types[n]`
  describes the format of the resulting parsed `$content[n]`. Use
  this input argument when `$contents` contains unlike formats (for
  example, a mixture of [XML] and [JSON] MIME types).
* `$content.type` is the MIME media type that describes the format
  of all items in the resulting list of parsed content. Use this
  input argument when `$contents` contains like formats (for
  example, when all items adhere to the exact same [XML] MIME type).
* `$namespaces` is a list of namespace prefixes and the URIs they
  map to, used when parsing [XML] content with elements in one or
  more namespaces. Use this input argument when `$contents` contains
  unlike formats (for example, a mixture of [XML] formats that use
  different namespaces).
* `$namespace` is a list of namespace prefixes and the URIs they map
  to, used when parsing [XML] content with elements in one or more
  namespaces. Use this input argument when `$contents` contains like
  formats (for example, when all items adhere to the exact same [XML]
  MIME type using the same namespaces).
* `$schemas` is an optional input list with the same number of items
  as `$documents` of document references or flat file schemas, where
  `$schemas[n]` is used to parse `$contents[n]`. Use this input
  argument when `$contents` contains unlike formats (for example, a
  mixture of Flat File and [XML] formats).
* `$schema` is the document reference or flat file schema to use to
  parse all items in `$contents`. Use this input argument when
  `$contents` contains like formats (for example, when all items
  adhere to the exact same [XML] schema).
* `$encoding` is an optional character set to use when the
  `$contents` is provided as a list of input streams or byte arrays.
  Defaults to [UTF-8].
* `$validate?` is an optional boolean flag which when `true` will
  validate each parsed content against the appropriate schema, and
  throw an exception if the content is invalid. Defaults to `false`.

#### Outputs:

* `$documents` is an `IData[]` document list of the parsed `$contents`,
  where `$documents[n]` is the parsed version of `$contents[n]`.

---

### tundra.list.datetime:duration

Returns the durations between a list of datetime pairs.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$datetimes` is a list of datetime pairs for which durations will be
  calculated.
  * `start` is the datetime string representing the starting instant
    for calculating the duration of time.
  * `end` is the datetime string representing the ending instant for
    calculating the duration of time.
* `$datetime.pattern` is an optional datetime pattern that the given
  datetime strings conform to. Defaults to an [ISO8601] XML datetime.
* `$duration.pattern` is an optional duration pattern that the output
  `$durations` will be formatted as. Defaults to an [ISO8601] XML
  duration.

#### Outputs:

* `$durations` is a list of the durations of time between each given
  datetime string pair.

---

### tundra.list.datetime:format

Formats a list of datetime strings that conform to the input
pattern, according to the output pattern.

Supports a handful of well-known named datetime patterns:

Pattern Name  | Description
------------- | --------------------------------------------
datetime      | ISO8601 XML datetime
datetime.db2  | yyyy-MM-dd-HH.mm.ss.SSSSSS
datetime.jdbc | yyyy-MM-dd HH:mm:ss.SSS
date          | ISO8601 XML date
date.jdbc     | yyyy-mm-dd
time          | ISO8601 XML time
time.jdbc     | HH:mm:ss
milliseconds  | Number of milliseconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)
seconds       | Number of seconds since the Epoch, January 1, 1970 00:00:00.000 GMT (Gregorian)

Custom datetime patterns can be specified using
[java.text.SimpleDateFormat] compatible patterns.

#### Inputs:

* `$list` is a list of datetime strings to be formatted.
* `$pattern.input` is an optional datetime pattern that `$list`
  conforms to, that will be used to parse the datetime strings.
  Defaults to an [ISO8601] XML datetime.
* `$patterns.input` is an optional list of datetime patterns that
  `$list` might conform to, useful when the exact pattern is not
  known. A parse is attempted for each pattern until the first
  successful parse, or until all patterns have been tried in which
  case an unparseable datetime exception will be thrown.
* `$pattern.output` is an optional datetime pattern that will be
  used to format the resulting `$list` strings. Defaults to an
  [ISO8601] XML datetime.
* `$timezone.input` is an optional [java.util.TimeZone] ID, or a
  `(+|-)HH:mm` time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or `Z` for UTC, or `local` for the default localhost
  time zone identifying the time zone the input `$list` strings
  will be parsed with. If specified, this time zone will override
  any zone specified in the `$list` strings themselves.
* `$timezone.output` is an optional [java.util.TimeZone] ID, or a
  `(+|-)HH:mm` time zone offset, or an XML duration string
  representing a time zone offset, or a raw millisecond time zone
  offset, or `Z` for UTC, or `local` for the default localhost
  time zone identifying the time zone the returned `$list` strings
  will be formatted with.

#### Outputs:

* `$list` is the resulting list of datetime strings formatted
  according to `$pattern.output` in the given `$timezone.output`.

---

### tundra.list.directory:compact

Deletes all empty child directories recursively from the given
directories, and optionally deletes the given directory itself if
also empty after compacting.

#### Inputs:

* `$directories` is a list of directories specified as a relative or
  absolute path or `file:` [URI] to be compacted.
* `$delete?` is an optional boolean flag which when `true` will delete
  the given `$directory` itself if it is emtpy after compaction.
  Defaults to `false`.
* `$raise?` is an optional boolean flag which when `true` will throw
  an exception if the deletion of any directories fails. Defaults
  to `false`.

---

### tundra.list.directory:purge

Deletes all files older than the given duration, based on the last
modified datetime, from the given directories, and optionally from
their sub-directories.

#### Inputs:

* `$directories` is a list of directories to be purged:
  * `directory` is the directory from which files will be deleted,
    specified as either a relative or absolute file path or `file:`
    [URI].
  * `duration` is the duration of time representing the age of files
    to be deleted. For example, a duration of P1D will delete all
    files that were last modified 24 hours ago or earlier.
  * `duration.pattern` is an optional pattern describing the type of
    duration specified by the `duration` string. Defaults to an
    [ISO8601] XML string.
  * `filter.inclusions` is an optional list of regular expression
    patterns, wildcard file glob patterns, or literals used to
    include files with matching names for deletion.
  * `filter.exclusions` is an optional list of regular expression
    patterns, wildcard file glob patterns, or literals used to
    exclude files with matching names from deletion.
  * `filter.type` is an optional choice if either 'regular
    expression', 'wildcard', or 'literal', which determines the
    type of filter patterns provided. Defaults to 'regular
    expression'.
  * `recurse?` is an optional boolean flag indicating if files in
    sub-directories should also deleted. Defaults to `false`.

#### Outputs:

* `$counts` is a list of the number of files deleted by this service
  for each directory.

---

### tundra.list.directory:remove

Deletes the given directories, and optionally all child files and
directories recursively if desired.

#### Inputs:

* `$directories` is a list of directories specifed as a relative or
  absolute path or `file:` [URI] to be deleted.
* `$recurse?` is a boolean flag indicating that all child files and
  directories should be recursively deleted also. If `false`, and
  a directory is not empty, an exception will be thrown. Defaults
  to `false`.

---

### tundra.list.directory:squeeze

Reduces the total size in bytes of the given directories to the given
size by progressively deleting files in order of least recently
modified.

#### Inputs:

* `$directories` is a list of directories to be squeezed:
  * `directory` is a relative or absolute path, or `file:` [URI], in
    which files will be deleted to reduce the total size of the
    directory to the required size.
  * `size.required` is the required size in bytes of the given
    directory. If the directory's size is less that this size, no
    files will be deleted. If the directory's size is greater than
    this size, files will be progressively deleted in order of least
    recently modified until the directory size is equal to or less
    than the required size.
  * `filter.inclusions` is an optional list of regular expression
    patterns, wildcard file glob patterns, or literals used to
    include files with matching names for deletion.
  * `filter.exclusions` is an optional list of regular expression
    patterns, wildcard file glob patterns, or literals used to
    exclude files with matching names from deletion.
  * `filter.type` is an optional choice if either 'regular
    expression', 'wildcard', or 'literal', which determines the type
    of filter patterns provided. Defaults to 'regular expression'.
  * `recurse?` is an optional boolean flag which when `true` will
    include all child directories in the squeezing process. Defaults
    to `false`.

#### Outputs:

* `$sizes.squeezed` is a list of the resulting sizes in bytes of the
  directories after they have been squeezed.

---

### tundra.list.document:append

Appends a single item to the end of a list, such that appending an item
to a list containing n items results in a new list of n + 1 items.

#### Inputs:

* `$list` is a list to append an item to.
* `$item` is an item to be appended to the given list.

#### Outputs:

* `$list` is the resulting list with the given `$item` appended to the
  end.

---

### tundra.list.document:blankify

Converts all null values in the given `IData[]` document list
to empty strings.

#### Inputs:

* `$list` is an `IData[]` document list to be blankified.
* `$recurse?` is an optional boolean indicating if embedded
  `IData` documents and `IData[]` document lists should also
  be blankified. Defaults to `false`.

#### Outputs:

* `$list` is the resulting `IData[]` document list with all null
  values converted to empty string values.

---

### tundra.list.document:capitalize

Capitalizes all string values in the given `IData[]` document list.

#### Inputs:

* `$list` is an `IData[]` document list whose string values are to be
  capitalized.
* `$capitalize` is an optional choice that determines whether all
  words or only the first word is capitalized. Defaults to
  capitalizing all words, if not specified:
  * `all words`
  * `first word`
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have string
  values capitalized. Defaults to `false`.
* `$mode` is an optional choice for what should be capitalized: either
  the values, the keys, or both the keys and the values. Defaults to
  values only.

#### Outputs:

* `$list` is the given `IData[]` document list with all string values
  capitalized.

---

### tundra.list.document:clear

Removes all elements from all items in the given `IData` document list,
except for any keys specified in the preserve list.

#### Inputs:

* `$list` is an `IData` document list for which to remove the keys in
  each item.
* `$preserve` is list of keys which will not be removed from
  the given `IData` document list. Keys can be simple or fully
  qualified, such as `a/b/c[0]/d`.

#### Outputs:

* `$list` is the given `IData` document list with all keys removed
  from each item, except for those specified in `$preserve`.

---

### tundra.list.document:compact

Removes all null items from the given list, thereby shortening the
length of the list.

#### Inputs:

* `$list` is a list to be compacted.
* `$recurse?` is an optional boolean indicating if embedded
  `IData` documents and `IData[]` document lists should also
  be compacted. Defaults to `false`.

#### Outputs:

* `$list` is the given list with all null items removed.

---

### tundra.list.document:concatenate

Returns a new list containing all the items in the given lists.

#### Inputs:

* `$operands` is an `IData` document under which all the lists to
  be concatenated should be included.

#### Outputs:

* `$list` is a new list containing all the items from the given
  input lists.

---

### tundra.list.document:condense

Replaces runs of one or more whitespace characters (space, tab,
carriage return, line feed) with a single space character, then
trims leading and trailing whitespace.

#### Inputs:

* `$list` is an `IData[]` document list to be condensed.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also be condensed.
  Defaults to `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  values.

#### Outputs:

* `$list` is the resulting `IData[]` document list with `String`,
  `String[]`, and `String[][]` values condensed into single lines.

---

### tundra.list.document:drop

Removes the item stored at a given index in the given list.

#### Inputs:

* `$list` is a list to remove the item from.
* `$index` is an integer identifying which item to be removed from
  the given list. List indexing is zero-based. Supports both
  forward and reverse indexing (where, for example, an index of
  -1 is the last item in the list, and an index of -2 is the
  second last item in the list).

#### Outputs:

* `$list` is the given list with the item identified by the given
  index removed.

---

### tundra.list.document:each

Iterates through the given list, invoking the given service for each
item in the list, passing `$item`, `$index`, `$iteration` and `$length`
variables.

#### Inputs:

* `$list` is a list to be iterated over.
* `$service` is a fully-qualified service name identifying the
  service to be invoked to process each item in the list.
* `$pipeline` is an optional `IData` document containing arbitrary
  input arguments used when invoking `$service`.
* `$item.input` is an optional variable name used when passing each
  item in the list to the invocation of `$service`. Defaults to
  `$item`.

---

### tundra.list.document:equal

Returns `true` if the given lists are equal.

#### Inputs:

* `$operands` is an `IData` document under which all the lists to
  be compared for equality should be included.

#### Outputs:

* `$equal?` is a boolean indicating if the given lists are equal.

---

### tundra.list.document:filter

Filters the given list to only include items where the given
condition evaluates to `true`.

#### Inputs:

* `$list` is the list to be filtered.
* `$condition` is a `tundra.condition:evaluate` compatible conditional
  statement used to filter the given list.

  List items are represented in the evaluation scope by a variable
  named `$item`.

  For example, to filter a list to only include items where surname
  and firstname keys have specific values, use the following
  `$condition`:

      %$item/surname% == "Smith" and %$item/firstname% == "John"

* `$scope` is an optional `IData` document containing the variables
  against which `$condition` will be evaluated. If not specified, the
  `$condition` will be evaluated against the pipeline.

#### Outputs:

* `$list` is the given list filtered to only include the items where
  `$condition` evaluated to `true`.
* `$list.length` is the number of items in the returned `$list`.

---

### tundra.list.document:find

Returns the list of `IData` documents where the values associated with
the give key included at least one occurrence of the given [regular
expression pattern] or literal string pattern, and the list of
remaining `IData` documents.

#### Inputs:

* `$list` is an `IData[]` document list containing values to be searched
  for the given `$pattern`.
* `$key` is the key identifying the value in the given document list to
  be searched for the given `$pattern`, and can be simple or fully-
  qualified, such as `a/b/c[0]/d`.
* `$key.literal?` is an optional boolean flag which if `true` will treat
  the given `$key` literally rather than as a potentially fully-
  qualified key. Defaults to `false` if not specified.
* `$pattern` is the [regular expression pattern] or literal string to
  search for against the given list.
* `$pattern.literal?` is a boolean indicating if the `$pattern` string
  should be treated as a literal string. If `false`, `$pattern` is
  treated as a [regular expression pattern]. If `true`, `$pattern` is
  treated as a literal string. Defaults to `false`, if not specified.

#### Outputs:

* `$found.all?` is a boolean flag which when `true` indicates that all
  items in the given list included the given pattern.
* `$found.any?` is a boolean flag which when `true` indicates that at
  least one item in the given list included the given pattern.
* `$found.none?` is a boolean flag which when `true` indicates that no
  items in the given list included the given pattern.
* `$found` is the list of items which included the given pattern.
* `$found.length` is the number of items in the `$found` list.
* `$unfound` is the list of items which did not include the given
  pattern.
* `$unfound.length` is the number of items in the `$unfound` list.

---

### tundra.list.document:first

Returns the first item from the given list.

#### Inputs:

* `$list` is a list to fetch the first item from.

#### Outputs:

* `$item` is the first item in the given list.

---

### tundra.list.document:flip

Flips the given `IData[]` document list so that for each item the
keys become the values and the values become the keys. Note that
only top-level elements with `String` values are flipped; other
elements are not included.

For example, an `IData[]` document list with the following structure
represented as [JSON]:

    [
        {
            "name": "Ian Curtis",
            "role": "Singer"
        },
        {
            "name": "Bernard Sumner",
            "role": "Guitarist"
        },
        {
            "name": "Peter Hook",
            "role": "Bassist"
        },
        {
            "name": "Stephen Morris",
            "role": "Drummer"
        }
    ]

Would be flipped as follows:

    [
        {
            "Ian Curtis": "name",
            "Singer": "role"
        },
        {
            "Bernard Sumner": "name",
            "Guitarist": "role"
        },
        {
            "Peter Hook": "name",
            "Bassist": "role"
        },
        {
            "Stephen Morris": "name",
            "Drummer": "role"
        }
    ]

#### Inputs:

* `$list` is an `IData[]` document list to be flipped.

#### Outputs:

* `$list` is the given `IData[]` document list flipped so that for each
  item the keys are now the values and the values are now the keys.

---

### tundra.list.document:get

Returns the item stored at a given index in a list. A zero-
based index can be specified using the `$index` input, or
a one-based index can be specified using the `$iteration` input
(which is useful when using this service inside a flow loop).

#### Inputs:

* `$list` is a list to fetch an item from.
* `$index` is an optional zero-based index identifying
  the item to be fetched. Supports forward and reverse
  indexing (where, for example, an index of -1 is the
  last item in the list, and an index of -2 is the
  second last item in the list).
* `$iteration` is an optional one-based index identifying
  the item to be fetched.

#### Outputs:

* `$item` is the item stored at the given index in the given
  list.

---

### tundra.list.document:group

Groups the given `IData[]` document list items by the given criteria.
This service can be used to process an `IData[]` document list in a
way similar to a [SQL GROUP BY] clause, but also supports multi-
level groupings.

#### Inputs:

* `$list` is an `IData[]` document list whose items are to be grouped.
* `$group` specifies the grouping criteria as a recursive hierarchy
  of keys:
  * `by` is a list of keys whose associated values are used to group
    like items in `$list` together:
    * `key` is the fully-qualified key identifying the values in `$list`
      on which to group.
    * `type` is an optional choice that defines type of comparison
      performed between the values associated with the `key`:
      * `string` will compare the values as strings.
      * `integer` will compare the values as integers.
      * `decimal` will compare the values as decimal numbers.
      * `datetime` will compare the values as datetimes.
      * `duration` will compare the values as durations of time.
    * `pattern` is an optional pattern string used when the type of
      comparison is either `datetime` (in which case a pattern
      compatible with `Tundra/tundra.datetime:format` must be
      specified), or `duration` (in which case a pattern compatible
      with `Tundra/tundra.duration:format` must be specified).
  * `then` is an optional next level of grouping criteria, which is
    used to further group like items in `$list` together.
    * ... refer to `$group` structure.
* `$group.sorting` is an optional choice of the type of sorting applied to
  the list of groups, defaults to `ascending` order if not specified:
  * `ascending` will sort the returned groups in ascending compound key order.
  * `descending` will sort the returned groups into descending compound key
    order.
  * `none` will preserve the order of the compound keys in the provided input
    `$list`.

#### Outputs:

* `$list.groups` is an `IData[]` document list containing one item per
  each unique set of grouped value associated:
  * `by` is an `IData` document containing the keys and values that
    defined this group.
  * `items` is an `IData[]` document list containing all the items from
    `$list` whose values are equal to values of the associated group.
  * `items.length` is the number of elements in the `items` `IData[]`
    document list.
  * `then` is an `IData` document list containing the next level of
    grouped items as per the `$group` levels specified.
    * ... refer to `$list.groups` structure.
  * `then.length` is the number of elements in the `then` `IData[]`
    document list.
* `$list.groups.length` is the number of elements in the `$list.groups`
  `IData[]` document list.
* `$list.length` is the number of elements in the input `$list` `IData[]`
  document list.

---

### tundra.list.document:grow

Increases the size of the given list by the given count of items, padded
with the given item (or null if not specified).

#### Inputs:

* `$list` is the `IData[]` list to be grown.
* `$item` is an optional `IData` used to pad the newly grown section of the
  list with.
* `$count` is the number of new items to add to the list.

#### Outputs:

* `$list` is the `IData[]` list grown by the desired `$count` of items,
  with the original items preserved and the new items padded with `$item`
  (or null if not specified).

---

### tundra.list.document:include

Returns `true` if the given item is found in the given list.

#### Inputs:

* `$list` is a list to check whether the given `$item` exists
  in.
* `$item` is the item to be checked against the given `$list`.

#### Outputs:

* `$include?` is a boolean indicating the the given `$item`
  exists as an item in the given `$list`.

---

### tundra.list.document:insert

Returns a new list with the given item inserted at the desired
index in the given list.

#### Inputs:

* `$list` is a list to check whether the given `$item` exists
  in.
* `$item` is the item to be checked against the given `$list`.
* `$index` is the index at which to insert the item. List
  indexing is zero-based. Supports both forward and
  reverse indexing (where, for example, an index of -1
  is the last item in the list, and an index of -2 is the
  second last item in the list).

#### Outputs:

* `$list` is the resulting list with the item inserted at the
  desired index.

---

### tundra.list.document:join

Many-to-one conversion of an `IData` document list to an `IData` document.

Calls the given joining service, passing the `IData` document list
as an input, and returning the resulting `IData` document as output.
The joining service must accept an `IData` document list, and
return a single `IData` document.

#### Inputs:

* `$documents` is an `IData` document list to be joined or
  aggregated together into one `IData` document.
* `$service` is the fully-qualified joining service name that is
  called to join or aggregate the `IData` document list. This
  service must accept an `IData[]` array, and return a single
  `IData` object.
* `$pipeline` is an optional `IData` document for specifying
  arbitrary input arguments to the invocation of `$service`.
* `$service.input` is an optional name to use for the IData
  document list for the input pipeline of the `$service` invocation.
  Defaults to `$documents`.
* `$service.output` is an optional name to use for the output IData
  parameter returned by the `$service` invocation. Defaults to
  `$document`.

#### Outputs:

* `$document` is the resulting `IData` document returned by `$service`.

---

### tundra.list.document:keys

Returns the union set of all top-level keys present in each IData
item in the given `IData[]` document list that match the given regular
expression pattern if specified, or all top-level keys if no pattern
is specified.

#### Inputs:

* `$list` is an `IData[]` document list from which all top-level
  keys are to be fetched.
* `$pattern` is an optional [regular expression pattern] that
  is used to filter the list of keys returned. If not specified,
  all keys are returned.

#### Outputs:

* `$keys` is the union set of all top-level keys in the given IData[]
  document list that match the given regular expression `$pattern` if
  specified, or all top-level keys if no `$pattern` was specified.

---

### tundra.list.document:last

Returns the last item from the given list.

#### Inputs:

* `$list` is a list to fetch the last item from.

#### Outputs:

* `$item` is the last item in the given list.

---

### tundra.list.document:length

Returns the number of items in the given list.

#### Inputs:

* `$list` is a list to count the number of items in.

#### Outputs:

* `$length` is the number of items in the given list.

---

### tundra.list.document:lowercase

Converts all string values in the given `IData[]` document list to
lowercase.

#### Inputs:

* `$list` is an `IData[]` document list whose string values are to be
  converted to lowercase.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have string
  values converted to lowercase. Defaults to `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  trimming values only.

#### Outputs:

* `$list` is the given `IData[]` document list with all string values
  converted to lowercase.

---

### tundra.list.document:map

Returns a new list created by invoking the given service for each
item in the input list, passing `$item`, `$index`, `$iteration` and
`$length` variables, and collecting the values returned by the service
to form the new list.

This is an implementation of a higher-order [map function] for
document lists.

#### Inputs:

* `$list` is a list to be iterated over.
* `$service` is a fully-qualified service name identifying the
  service to be invoked to process each item in the list.
* `$pipeline` is an optional `IData` document containing arbitrary
  input arguments used when invoking `$service`.
* `$item.input` is an optional variable name used when passing each
  item in the list to the invocation of `$service`. Defaults to
  `$item`.
* `$item.output` is an optional variable name used when extracting
  the resulting item from the invocation of `$service`. Defaults to
  `$item`.

#### Outputs:

* `$list` is the newly constructed list containing the returned
  items from invoking `$service` for each input list item.

---

### tundra.list.document:match

Returns the list of `IData` documents where the values associated with
the given key matched the given [regular expression pattern] or
literal string pattern, and the list of remaining `IData` documents.

#### Inputs:

* `$list` is an `IData[]` document list containing values to be matched
  against the given `$pattern`.
* `$key` is the key identifying the value in the given document list to
  be matched against the given `$pattern`, and can be simple or fully-
  qualified, such as `a/b/c[0]/d`.
* `$key.literal?` is an optional boolean flag which if `true` will treat
  the given `$key` literally rather than as a potentially fully-
  qualified key. Defaults to `false` if not specified.
* `$pattern` is the [regular expression pattern] or literal string to
  match against the given list.
* `$pattern.literal?` is a boolean indicating if the `$pattern` string
  should be treated as a literal string. If `false`, `$pattern` is
  treated as a [regular expression pattern]. If `true`, `$pattern` is
  treated as a literal string. Defaults to `false`, if not specified.

#### Outputs:

* `$matched.all?` is a boolean flag which when `true` indicates that all
  items in the given list matched the given pattern.
* `$matched.any?` is a boolean flag which when `true` indicates that at
  least one item in the given list matched the given pattern.
* `$matched.none?` is a boolean flag which when `true` indicates that no
  items in the given list matched the given pattern.
* `$matched` is the list of items which matched the given pattern.
* `$matched.length` is the number of items in the `$matched` list.
* `$unmatched` is the list of items which did not match the given
  pattern.
* `$unmatched.length` is the number of items in the `$unmatched` list.

---

### tundra.list.document:merge

Merges multiple `IData` documents into a single document. The
resulting document contains all keys from all source documents, and
each key is assigned the value associated with the final occurrence
of that key across all source documents.

This service can be useful for combining a document constructed with
default values with a document sourced externally, where the merged
document will contain the key set union of both documents, and will
include default values where no value was present in the externally
sourced document.

#### Inputs:

* `$list` is an `IData[]` document list whose elements will be merged
  into a single `IData` document.
* `$recurse?` is an optional boolean which when `true` will recursively
  merge child `IData` documents also. Defaults to `false`, where only
  top-level elements are merged.

#### Outputs:

* `$document` is the resulting merged `IData` document.

---

### tundra.list.document:normalize

Returns a new `IData[]` document list with all fully qualified keys (for
example, 'a/b/c' or 'x/y[0]/z[1]') deconstructed into their constituent
parts, and any non-IData objects converted to an `IData` representation
wherever possible.

For example, if an `IData` document contains the following key value pairs
(using [JSON] notation to represent the pipeline):

    {
      "a/b/c": "example 1",
      "a/b/d": "example 2",
      "e": "example 3",
      "f[0]": "example 4",
      "f[1]": "example 5"
    }

This is normalized to the following:

    {
      "a": {
        "b": {
          "c": "example 1",
          "d": "example 2"
        }
      },
      "e": "example 3",
      "f": ["example 4", "example 5"]
    }

Keys using path-style notation, for example `a/b/c`, are
converted to nested `IData` documents with the final key
in the path, `c` in this example, assigned the value of
the original key.

Keys using array- or list-style notation, for example `f[0]`,
are converted to an array or list with the value of the
original key assigned to the indexed item (the zeroth item in
this example).


#### Inputs:

* `$list` is an `IData[]` document list to be normalized.

#### Outputs:

* `$list` is the resulting normalized `IData[]` document list.

---

### tundra.list.document:nullify

Converts all string values that contain only whitespace characters
in the given `IData[]` document list to null.

#### Inputs:

* `$list` is an `IData[]` document list to be nullified.
* `$recurse?` is an optional boolean indicating if embedded
  `IData` documents and `IData[]` document lists should also
  be nullified. Defaults to `false`.

#### Outputs:

* `$list` is the resulting `IData[]` document list with all string
  values that contain only whitespace characters converted to
  null.

---

### tundra.list.document:partition

Partitions the given list into a number of lists using the
associated conditions to determine inclusion of an item in the
partitions.

Note that an item can be included in multiple partitions if it
satisfies multiple conditions.

#### Inputs:

* `$list` is the list to be partitioned.
* `$partitions` is the list of partitions to be created from `$list`.
  * `name` is the name used to identify this partition in the returned
    `$results`.
  * `condition` is a `tundra.condition:evaluate` compatible conditional
    statement used to determine if a `$list` item is to be included in
    this partition.

    List items are represented in the evaluation scope by a variable
    named `$item`.

    For example, to partition a list to only include items where
    surname and firstname keys have specific values, you could use
    the following condition:

        %$item/surname% == "Smith" and %$item/firstname% == "John"

  * `scope` is an optional `IData` document containing the variables
    against which condition will be evaluated. If not specified, the
    condition will be evaluated against `$scope` if specifed,
    otherwise it will be evaluated against the pipeline itself.
* `$scope` is an optional `IData` document containing the variables
  against which `$partitions[n]/condition` will be evaluated when no
  `$partitions[n]/scope` is specified. If not specified, a
  `$partitions[n]/condition` without an associated `$partitions[n]/scope`
  will be evaluated against the pipeline.

#### Outputs:

* `$results` contains the resulting partitions.
  * `<$partitions[n]/name>` is a dynamically named list containing the
    items which satisfied the associated `$partitions[n]/condition`.
  * `<$partitions[n]/name>.length` is the number of items in the
    associated dynamically named partition.
  * `remainder` is a list of the remaining items which are not members
    of any named partitions.
  * `remainder.length` is the number of items in the associated
    remainder list.

---

### tundra.list.document:pivot

Returns a given `IData[]` document list pivoted on a given list of
keys.

For example, given an `IData[]` document list that includes the
following two items (represented in [JSON] form):

    $list = [
      { "id": "1", "name": "Bob",  "phone": "1234 5678" },
      { "id": "2", "name": "Jane", "phone": "2345 6789" }
    ]

A pivot on the key `name` would return the following `IData` document,
where each list item is now associated with the value associated
with the pivot key:

    $document = {
      "Bob":  { "id": "1", "name": "Bob",  "phone": "1234 5678" },
      "Jane": { "id": "2", "name": "Jane", "phone": "2345 6789" }
    }

A pivot on the key `phone` would return the following `IData` document:

    $document = {
      "1234 5678": { "id": "1", "name": "Bob",  "phone": "1234 5678" },
      "2345 6789": { "id": "2", "name": "Jane", "phone": "2345 6789" }
    }

A pivot on both the keys `id` and `name` would return the following
`IData` document (note that the pivot keys are nested):

    $document = {
      "1": {
        "Bob":  { "id":"1", "name":"Bob",  "phone":"1234 5678" }
      },
      "2": {
        "Jane": { "id":"2", "name":"Jane", "phone":"2345 6789" }
      }
    }

Alternatively, a pivot on both the keys `id` and `name` with a delimiter
of `-` would construct a compound key using the values associated with
each key from each item joined with the given delimiter, as follows:

    $document = {
      "1-Bob":  { "id":"1", "name":"Bob",  "phone":"1234 5678" },
      "2-Jane": { "id":"2", "name":"Jane", "phone":"2345 6789" }
    }

Although the difference between the `IData[]` document list and `IData`
document representations appear subtle, a pivot of a `IData[]`
document list on a primary key returns an `IData` document that can be
efficiently accessed by key, rather than having to iterate over the
list to find a specific item by key.

#### Inputs:

* `$list` is the `IData[]` document list to be pivoted.
* `$keys` is the list of keys to pivot on, with the following
  caveats:
  * If a key doesn't exist in an item, that item is not included
    in the resulting pivot.
  * If a key is associated with a null value in an item, that item
    is not included in the resulting pivot.
  * If a key is associated with the same value in multiple items,
    only the first item is included in the resulting pivot.
* `$delimiter` is an optional character to use for separating the
  values that form a compound key, when multiple pivot keys are
  specified. If not specified, the pivot keys will be nested in
  the returned `IData` document. The delimiter `/` is reserved,
  and will also result in the pivot keys being nested.

#### Outputs:

* `$document` is the `IData` document pivoted representation of the
  given `$list`.

---

### tundra.list.document:prefix

Prepends the given prefix to the keys, or values, or both in the
given `IData[]` document list.

#### Inputs:

* `$list` is an `IData[]` document list to be prefixed.
* `$prefix` is the string to be prepended to the keys, or values, or
  both.
* `$recurse?` is an optional boolean indicating if child `IData`
  documents and `IData[]` document lists should also be recursively
  processed. Defaults to `false`.
* `$force?` is an optional boolean indicating if the prefix should be
  always be prepended even if the key or value already starts with
  the given prefix. Defaults to `false`, where the prefix is only
  prepended to keys or values that do not already start with it.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  values.

#### Outputs:

* `$list` is the given `IData[]` document list with with the given
  prefix prepended to either the keys, or values, or both.

---

### tundra.list.document:prepend

Prepends a single item to the front of a list, such that prepending
an item to a list containing n items results in a new list of n + 1
items.

#### Inputs:

* `$list` is a list to be prepended to.
* `$item` is the item to prepend to the given list.

#### Outputs:

* `$list` is the resulting list with the given `$item` prepended to the
  start.

---

### tundra.list.document:put

Sets the value of the item at the given index in the given list.

#### Inputs:

* `$list` is a list in which to set the given value.
* `$item` is the item to be set in the given list.
* `$index` is an optional zero-based index identifying
  the item to be set. Supports forward and reverse
  indexing (where, for example, an index of -1 is the
  last item in the list, and an index of -2 is the
  second last item in the list).

#### Outputs:

* `$list` is the resulting list with the item at the given
  index set to the given value.

---

### tundra.list.document:reject

Filters the given list to not include items where the given
condition evaluates to `true`.

#### Inputs:

* `$list` is the list to be filtered.
* `$condition` is a `tundra.condition:evaluate` compatible conditional
  statement used to filter the given list.

  List items are represented in the evaluation scope by a variable
  named `$item`.

  For example, to filter a list to not include items where surname
  and firstname keys have specific values, use the following
  `$condition`:

      %$item/surname% == "Smith" and %$item/firstname% == "John"

* `$scope` is an optional `IData` document containing the variables
  against which `$condition` will be evaluated. If not specified, the
  `$condition` will be evaluated against the pipeline.

#### Outputs:

* `$list` is the given list filtered to not include the items where
  `$condition` evaluated to `true`.
* `$list.length` is the number of items in the returned `$list`.

---

### tundra.list.document:remove

Removes either the first or all occurrences of the given pattern
from either the values, the keys, or both the keys and values in the
given `IData[]` document list.

#### Inputs:

* `$list` is an `IData[]` document list to have occurrences of the
  given pattern removed.
* `$pattern` is either a literal or [regular expression pattern] to
  be removed. If not specified, no removal will occur.
* `$pattern.literal?` is a boolean indicating whether the pattern is a
  literal or a [regular expression pattern]. If literal, the pattern
  is converted to a [regular expression pattern] that matches the
  given literal string. Defaults to `false`.
* `$occurrence.first?` is a boolean indicating if only the first
  occurrence of the given pattern should be removed. Defaults to
  `false`, where all occurrences of the pattern are removed.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have occurrences
  of the pattern in their elements removed. Defaults to `false`.
* `$mode` is an optional choice for what should be replaced:
  either the values, the keys, or both the keys and the values.
  Defaults to replacing values only.

#### Outputs:

* `$list` is the given `IData[]` document list with the given `$pattern`
  removed.

---

### tundra.list.document:replace

Replaces either the first or all occurrences of the given pattern
with the given replacement in either the values, the keys, or both
the keys and values in the given `IData[]` document list.

#### Inputs:

* `$list` is an `IData[]` document list to have occurrences of the
  given pattern replaced.
* `$pattern` is either a literal or [regular expression pattern] to
  be replaced. If not specified, no replacement will occur.
* `$pattern.literal?` is a boolean indicating whether the pattern is a
  literal or a [regular expression pattern]. If literal, the pattern
  is converted to a [regular expression pattern] that matches the
  given literal string. Defaults to `false`.
* `$replacement` is the replacement string to be substituted in the
  given string wherever the given pattern is found. If not
  specified, no replacement will occur.
* `$replacement.literal?` is a boolean indicating if the replacement
  string should be treated as a literal string. If `false`, captured
  groups can be referred to with dollar-sign references, such as `$1`,
  and other special characters may need to be escaped. Defaults to
  `false`.
* `$occurrence.first?` is a boolean indicating if only the first
  occurrence of the given pattern should be replaced. Defaults to
  `false`, where all occurrences of the pattern are replaced.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have occurrences
  of the pattern in their elements replaced. Defaults to `false`.
* `$mode` is an optional choice for what should be replaced:
  either the values, the keys, or both the keys and the values.
  Defaults to replacing values only.

#### Outputs:

* `$list` is the given `IData[]` document list with the given `$pattern`
  replaced with the given `$replacement`.

---

### tundra.list.document:resize

Resizes the given list to the given length, truncated from the end when the
length is decreased, and padded with the given item (or null if not
specified) when the length is increased.

#### Inputs:

* `$list` is the `IData[]` list to be grown.
* `$item` is an optional `IData` used to pad the list if increasing the
  size.
* `$length` is the desired new length of the list if specified as a
  positive integer, or the relative number of items to truncate from the
  list if specified as a negative integer.

#### Outputs:

* `$list` is the `IData[]` list resized to the desired `$length`; if the
  new length is less than the original length, the list is truncated from
  the end; if the new length is greater than the original length, the
  list is padded with `$item` (or null if not specified).

---

### tundra.list.document:reverse

Returns a new list with all items from the given list in
reverse order.

#### Inputs:

* `$list` is the list to be reversed.

#### Outputs:

* `$list` is the given list with item ordering reversed.

---

### tundra.list.document:shrink

Decreases the size of the given list by the given count, truncating items
from the end.

#### Inputs:

* `$list` is the `IData[]` list to be shrunk.
* `$count` is the number of items to truncate from the end of the list.

#### Outputs:

* `$list` is the `IData[]` list shrunk from the end of the list by the
  desired item count by truncating items from the end of the list. If
  the list is smaller than the count, an empty list is returned.

---

### tundra.list.document:slice

Returns a new list which is a subset of the items in the
given list.

#### Inputs:

* `$list` is the list to be sliced.
* `$index` is the zero-based start index from which to
  take the slice.
* `$length` is the number of items to include in the
  slice.

#### Outputs:

* `$list` is the desired subset or slice of the given list.

---

### tundra.list.document:sort

Returns a new `IData[]` document list sorted according to the [natural
ordering] of the values associated with the given keys in each list
item.

#### Inputs:

* `$list` is the `IData[]` document list to be sorted.
* `$criteria` is an `IData[]` document list containing list of sort
  criteria in order of precedence, where the first item in the
  list is the most significant criteria and the last item is the
  least significant criteria:
  * `key` is the fully-qualified key identifying the values in `$list`
    on which to sort.
  * `type` is an optional choice that defines type of comparison
    performed between the values associated with the `key`:
    * `string` will compare the values as strings.
    * `integer` will compare the values as integers.
    * `decimal` will compare the values as decimal numbers.
    * `datetime` will compare the values as datetimes.
    * `duration` will compare the values as durations of time.
  * `pattern` is an optional pattern string used when the type of
    comparison is either datetime (in which case a pattern
    compatible with `Tundra/tundra.datetime:format` must be
    specified), or duration (in which case a pattern compatible
    with `Tundra/tundra.duration:format` must be specified).
  * `descending?` is an optional boolean indicating if the values
    associated with the `key` are to be sorted in descending
    (largest to smallest) order. Defaults to `false`, if not
    specified.

#### Outputs:

* `$list` is the sorted `IData[]` document list.

---

### tundra.list.document:squeeze

Trims all leading and trailing whitespace from all string values,
then converts empty strings, empty `IData` documents, and empty lists
to nulls, then compacts the `IData[]` document list by removing all
null values.

#### Inputs:

* `$list` is an `IData[]` document list to be squeezed.
* `$recurse?` is an optional boolean indicating if embedded
  IData documents and `IData[]` document lists should also
  be squeezed. Defaults to `false`.

#### Outputs:

* `$list` is the resulting `IData[]` document list with all string
  values trimmed of leading and trailing whitespace characters, and
  all empty string values, empty IData documents, empty lists, and
  null values removed.

---

### tundra.list.document:substitute

Attempts variable substitution on each string value in the given
`IData` document list by replacing all occurrences of substrings
matching `%key%` with the associated (optionally scoped) value.

#### Inputs:

* `$list` is an `IData` document list to perform variable
  substitution on.
* `$substitution.default` is an optional default value to substitute
  in place of keys that resolve to null or missing values. If not
  specified, no substitution will be made for keys that resolve to
  null or missing values.
* `$substitution.mode` is an optional choice of whether the
  substitution scope should be `local` variables only, `global`
  variables only, or `all` variables (local and global variables
  where local variables take precedence). Note that choosing `global`
  on an Integration Server version that does not support global
  variables will result in all values being substituted with the
  default value if one is provided. Defaults to `local`.
* `$pipeline` is an optional scope used to resolve key references. If
  not specified, keys are resolved against the pipeline itself.

#### Outputs:

* `$list` is the resulting `IData` document list with all variable
  substitution patterns in all item's values, such as `%key%`,
  replaced with the value of the key (resolved against either
  `$pipeline`, if specified, or the pipeline itself).

---

### tundra.list.document:suffix

Appends the given suffix to the keys, or values, or both in the
given `IData[]` document list.

#### Inputs:

* `$list` is an `IData[]` document list to be suffixed.
* `$suffix` is the string to be appended to the keys, or values, or
  both.
* `$recurse?` is an optional boolean indicating if child `IData`
  documents and `IData[]` document lists should also be recursively
  processed. Defaults to `false`.
* `$force?` is an optional boolean indicating if the suffix should be
  always be appended even if the key or value already starts with
  the given suffix. Defaults to `false`, where the suffix is only
  appended to keys or values that do not already start with it.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  values.

#### Outputs:

* `$list` is the given `IData[]` document list with with the given
  suffix appended to either the keys, or values, or both.

---

### tundra.list.document:take

Returns the given number of items from either the head or the tail
of the given list.

#### Inputs:

* `$list` is a list to fetch the head and tail from.
* `$count` is the number of items to take from either the head when
  specified as a positive number, or the tail when specified as a
  negative number.

#### Outputs:

* `$head` is a list containing the head items from the given `$list`.
* `$tail` is a list containing the tail items from the given `$list`.

---

### tundra.list.document:translate

One-to-one conversion of one `IData` document list to another `IData` document
list.

Calls the given translation service, passing the each `IData` document item
in the list as an input, and building a new `IData` document list from the
translated documents returned by the translation service as output.

#### Inputs:

* `$list` is an `IData` document list containing items to be translated.
* `$service` is the fully-qualified name of the translation service,
  which accepts a single `IData` document and returns a single IData
  document, called to translate the given `$document`.
* `$pipeline` is an optional `IData` document for providing arbitrary
  variables to the invocations of `$service`.
* `$service.input` is an optional variable name to use in the input
  pipeline of the call to `$service` for the given `IData` document.
  Defaults to `$document`.
* `$service.output` is an optional variable name used to extract the
  output `IData` document from the output pipeline of the call to
  `$service`. Defaults to `$translation`.

#### Outputs:

* `$translations` is the resulting `IData` document list containing the
  translated documents.

---

### tundra.list.document:trim

Removes leading and trailing whitespace from all string values in
the given `IData[]` document list.

#### Inputs:

* `$list` is an `IData[]` document list to process.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have their string
  values trimmed. Defaults to `false`.
* `$mode` is an optional choice for what should be trimmed: either the
  values, the keys, or both the keys and the values. Defaults to
  trimming values only.

#### Outputs:

* `$list` is the given `IData[]` document list with all string values
  trimmed of leading and trailing whitespace characters.

---

### tundra.list.document:unique

Returns an `IData[]` document list that only contains the first
of every occurrence of an `IData` document whose values associated
with the given keys are unique.

#### Inputs:

* `$list` is an `IData[]` document list to process.
* `$keys` is a `String[]` containg the keys used to determine
  uniqueness of an `IData` document, and can be simple or fully
  qualified, such as `a/b/c[0]/d`. If not specified, all keys
  will be used to determine uniqueness.

#### Outputs:

* `$list` is the resulting `IData[]` document list that only contains
  the first occurrence of each `IData` document whose values
  associated with the given keys are unique in the list.

---

### tundra.list.document:unprefix

Removes the given prefix from the keys, or values, or both in the
given `IData[]` document list.

#### Inputs:

* `$list` is an `IData[]` document list to have prefixes removed.
* `$prefix` is the string to be removed from the keys, or values, or
  both.
* `$recurse?` is an optional boolean indicating if child `IData`
  documents and `IData[]` document lists should also be recursively
  processed. Defaults to `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  values.

#### Outputs:

* `$list` is the given `IData[]` document list with with the given
  prefix removed from either the keys, or values, or both.

---

### tundra.list.document:unsuffix

Removes the given suffix from the keys, or values, or both in the
given `IData[]` document list.

#### Inputs:

* `$list` is an `IData[]` document list to have suffixes removed.
* `$suffix` is the string to be removed from the keys, or values, or
  both.
* `$recurse?` is an optional boolean indicating if child `IData`
  documents and `IData[]` document lists should also be recursively
  processed. Defaults to `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  values.

#### Outputs:

* `$list` is the given `IData[]` document list with with the given
  suffix removed from either the keys, or values, or both.

---

### tundra.list.document:uppercase

Converts all string values in the given `IData[]` document list to
uppercase.

#### Inputs:

* `$list` is an `IData[]` document list whose string values are to be
  converted to uppercase.
* `$recurse?` is an optional boolean indicating if embedded `IData`
  documents and `IData[]` document lists should also have string
  values converted to uppercase. Defaults to `false`.
* `$mode` is an optional choice for what should be converted: either
  the values, the keys, or both the keys and the values. Defaults to
  trimming values only.

#### Outputs:

* `$list` is the given `IData[]` document list with all string values
  converted to uppercase.

---

### tundra.list.document:values

Returns a list of values associated with the given key from the
respective item in the given `IData[]` document list.

#### Inputs:

* `$list` is an `IData[]` document list.
* `$key` is the key for which the associated value from each item in
  the given `$list` will be returned, and can be simple or fully
  qualified, such as `a/b/c[0]/d`.
* `$default.object` is an optional value to be returned when either
  the given `$key` does not exist in an item or its associated value
  is null.
* `$default.string` is an optional string value, provided for
  convenience when hard-coding a default value, to be returned
  when either the given `$key` does not exist in an item or its
  associated value is null.

#### Outputs:

* `$values` is the list of values associated with `$key` from the
  respective item in `$list`, or the given `$default.object` or
  `$default.string` if specified and the associated value for `$key`
  either did not exist or was null in the respective item.

---

### tundra.list.duration:format

Formats a list of duration strings according to the desired
pattern.

A start instant may be required when formatting fields with
indeterminate values, such as converting months to days
(because the number of days in a month varies).

#### Inputs:

* `$list` is a list of duration strings to be reformatted.
* `$pattern.input` is the duration pattern the given `$list` of
  duration strings adhere to.
* `$pattern.output` is the desired duration pattern the `$list` of
  duration strings will be reformatted according to.
* `$datetime` is an optional datetime string used as a start
  instant for resolving indeterminate durations (such as the
  number of days in a month).
* `$datetime.pattern` is an optional datetime pattern that `$datetime`
  conforms to, that will be used to parse the datetime string.
  Defaults to an [ISO8601] XML datetime.

#### Outputs:

* `$list` is the given list of duration strings formatted according
  to `$pattern.output`.

---

### tundra.list.duration:sum

Returns the sum of all the given durations, returning
(x1 + x2 + ... + xn).

#### Inputs:

* `$list` is a list of [ISO8601] XML duration strings to be added
  together.
* `$pattern.input` is an optional pattern describing the type of
  duration strings in `$list`. Defaults to an [ISO8601] XML string.
* `$pattern.output` is an optional desired pattern used to format
  the returned `$duration` string. Defaults to an [ISO8601] XML
  string.

#### Outputs:

* `$duration` is the sum of the duration strings in the given list
  in [ISO8601] XML format.

---

### tundra.list.exception:raise

Throws a new exception whose message is concatenated from the given
list of exceptions.

#### Inputs:

* `$exceptions` is an optional list of [java.lang.Throwable] objects
  to use to create the message for the resulting thrown exception.
  If not specified, or the list is empty, or the list only contains
  nulls, no exception is thrown.

---

### tundra.list.file:remove

Deletes the given files, if they exist. This service does not throw
an exception when a file does not exist.

#### Inputs:

* `$files` is a list of files to be deleted, specified as either a
  relative or absolute file path or `file:` [URI].

---

### tundra.list.html:decode

[HTML] decodes the given string list.

[HTML entities], such as `&lt;` and `&gt;`, are decoded to the
appropriate character representation, such as `<` and `>`.

#### Inputs:

* `$list` is an optional string list to be [HTML] decoded.

#### Outputs:

* `$list` is the given string list with [HTML entities] decoded
  in each item in the list.

---

### tundra.list.html:emit

Serializes an `IData[]` document list as an [HTML] fragment suitable
for embedding in an [HTML] page, formatted as a string, byte array,
or input stream.

Child elements that are `IData` documents, `IData[]` document lists,
`Object[]` object lists, or `Object[][]` object tables are recursively
serialized to clean [HTML] table elements.

#### Inputs:

* `$document.list` is the `IData[]` document list to be serialized as
  an [HTML] `String`, `byte[]`, or `java.io.InputStream` object.
* `$content.encoding` is an optional character set to use when
  encoding the resulting text data to a `byte[]` or
  `java.io.InputStream` object. Defaults to [UTF-8].
* `$content.mode` determines the type of object `$content` is returned
  as. Defaults to `stream`.
  * `stream` returns a `java.io.InputStream` object.
  * `bytes` returns a `byte[]` object.
  * `string` returns a `String` object.

#### Outputs:

* `$content` is the resulting serialization of `$list` as [HTML]
  content.

---

### tundra.list.html:encode

[HTML] encodes the given string list.

Reserved characters in [HTML], such as `<` and `>`, are encoded to the
appropriate [HTML entity], such as `&lt;` and `&gt;`, to ensure the [HTML]
is rendered correctly by web browsers and other [HTML] rendering
software.

#### Inputs:

* `$list` is an optional string list to be [HTML] encoded.

#### Outputs:

* `$list` is the given string list with special characters [HTML]
  encoded in each item in the list.

---

### tundra.list.integer:add

Returns a new list where each item is equal to the value of the
respective item in the given list added to the given operands.

#### Inputs:

* `$values` is a list of integer strings to be operated on.
* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as an integer number.

#### Outputs:

* `$results` is a new list containing each given value added to the
  given operands.

---

### tundra.list.integer:divide

Returns a new list where each item is equal to the value of the
respective item in the given list divided by the given divisor.

#### Inputs:

* `$dividends` is a list of integer strings to be operated on.
* `$divisor` is the number to divide by.

#### Outputs:

* `$results` is a new list containing each given value divided by the
  given divisor.

---

### tundra.list.integer:multiply

Returns a new list where each item is equal to the value of the
respective item in the given list multiplied by the given operands.

#### Inputs:

* `$values` is a list of integer strings to be operated on.
* `$operands` is an `IData` document containing arbitrary elements
  whose leaf values will be used as operands for this operation.
  Leaf values can be provided as objects with the following types:
  * `java.lang.String`
  * `java.lang.Integer`
  * `java.lang.Long`
  * `java.lang.Float`
  * `java.lang.Double`
  * `java.math.BigDecimal`
  * `java.math.BigInteger`
  Leaf values provided as a `java.lang.String` must be parseable
  as an integer number.

#### Outputs:

* `$results` is a new list containing each given value multiplied by
  the given operands.

---

### tundra.list.integer:remainder

Returns a new list where each item is equal to the remainder from
dividing the value of the respective item in the given list by the
given divisor.

#### Inputs:

* `$dividends` is a list of integer strings to be operated on.
* `$divisor` is the number to divide by.

#### Outputs:

* `$remainders` is a new list containing the remainder from dividing
  each given value by the given divisor.

---

### tundra.list.integer:remainder

Returns a new list where each item is equal to the subtraction of
the given subtrahend from the respective minuend in the given list.

#### Inputs:

* `$minuends` is a list of integer strings to be operated on.
* `$subtrahend` is an integer value to be subtracted.

#### Outputs:

* `$results` is a new list containing the subtraction of the given
  subtrahend from each given minuend.

---

### tundra.list.node:access

Grants the specified permissions on the given namespace nodes.

#### Inputs:

* `$nodes` is a list of Integration Server namespace nodes and
  permissions to be granted.
  * `node` is the Integration Server namespace node to grant
    permissions on, provided as a normal namespace identifer:

        folder(.folder)*(:object)

    Where brackets indicate optional components of the namespace
    identifer, '*' indicates 0 or more occurrences of a
    component, '.' and ':' are literal characters.
  * `permissions` is the list of permissions to be granted on
    the given `node`.
    * `type` identifies the type of permission to be granted, one of:
      * `list` allows members of the given ACL to see the child items
        in the given node.
      * `read` allows members of the given ACL to view the given node.
      * `write` allows members of the given ACL to change the given node.
      * `execute` allows members of the given ACL to execute the given node.
    * `acl` is the name of the ACL object to be granted permission on
      this namespace node.

---

### tundra.list.object:append

Appends a single item to the end of a list, such that appending an item
to a list containing n items results in a new list of n + 1 items.

#### Inputs:

* `$list` is a list to append an item to.
* `$item` is an item to be appended to the given list.
* `$class` is an optional Java class name that the list and item
  to be appended are required to be instances of.

#### Outputs:

* `$list` is the resulting list with the given `$item` appended to the
  end.

---

### tundra.list.object:coalesce

Returns the first object in the given list that is not null, or if
all objects in the list are null, then the given item.

#### Inputs:

* `$list` is an optional list of objects.
* `$default` is an optional object, included for convenience when using
  this service in a map step and providing a default value.
* `$mode` determines what is returned when all arguments are null:
  * `missing`: `$item` is not returned when all arguments are null.
    This is the default, if `$mode` is not specified.
  * `null`: `$item` is returned as null when all arguments are null.

#### Outputs:

* `$item` is the first object in the given `$list` that is not null,
  or the given input `$item` if it is not null, or null if all
  other values were null and `$mode` is 'null'.

---

### tundra.list.object:compact

Removes all null items from the given list, thereby shortening the
length of the list.

#### Inputs:

* `$list` is a list to be compacted.

#### Outputs:

* `$list` is the given list with all null items removed.

---

### tundra.list.object:concatenate

Returns a new list containing all the items in the given lists.

#### Inputs:

* `$operands` is an `IData` document under which all the lists to
  be concatenated should be included.

#### Outputs:

* `$list` is a new list containing all the items from the given
  input lists.

---

### tundra.list.object:create

Returns a newly created empty array with the given length that can
hold items of the class.

#### Inputs:

* `$length` is the desired length of the newly created array. Defaults
  to `0`.
* `$class` is an optional Java class name used to instantiate the
  array. Defaults to `java.lang.Object`.

#### Outputs:

* `$list` is a newly created empty array with the given `$length` that
  can hold items of the given `$class`.

---

### tundra.list.object:difference

Returns a list of only the items in `$list.x` that are not also
present in `$list.y`.

#### Inputs:

* `$list.x` is a list to be checked for differences against
  `$list.y`.
* `$list.y` is a list to be checked against `$list.x` for
  differences.

#### Outputs:

* `$list` is a new list containing only the items in `$list.x`
  that are not also present in `$list.y`.
* `$list.length` is the number of items in the returned `$list`.

---

### tundra.list.object:drop

Removes the item stored at a given index in the given list.

#### Inputs:

* `$list` is a list to remove the item from.
* `$index` is an integer identifying which item to be removed from
  the given list. List indexing is zero-based. Supports both
  forward and reverse indexing (where, for example, an index of
  -1 is the last item in the list, and an index of -2 is the
  second last item in the list).

#### Outputs:

* `$list` is the given list with the item identified by the given
  index removed.

---

### tundra.list.object:each

Iterates through the given list, invoking the given service for each
item in the list, passing `$item`, `$index`, `$iteration` and `$length`
variables.

#### Inputs:

* `$list` is a list to be iterated over.
* `$service` is a fully-qualified service name identifying the
  service to be invoked to process each item in the list.
* `$pipeline` is an optional `IData` document containing arbitrary
  input arguments used when invoking `$service`.
* `$item.input` is an optional variable name used when passing each
  item in the list to the invocation of `$service`. Defaults to
  `$item`.

---

### tundra.list.object:equal

Returns `true` if the given lists are equal.

#### Inputs:

* `$operands` is an `IData` document under which all the lists to
  be compared for equality should be included.

#### Outputs:

* `$equal?` is a boolean indicating if the given lists are equal.

---

### tundra.list.object:filter

Filters the given list to only include items where the given
condition evaluates to `true`.

#### Inputs:

* `$list` is the list to be filtered.
* `$condition` is a `tundra.condition:evaluate` compatible conditional
  statement used to filter the given list.

  List items are represented in the evaluation scope by a variable
  named `$item`.

  For example, to filter a list to only include items equal to 1 or
  2, use the following `$condition`:

      %$item% == 1 or %$item% == 2

* `$scope` is an optional `IData` document containing the variables
  against which `$condition` will be evaluated. If not specified, the
  `$condition` will be evaluated against the pipeline.

#### Outputs:

* `$list` is the given list filtered to only include the items where
  `$condition` evaluated to `true`.
* `$list.length` is the number of items in the returned `$list`.

---

### tundra.list.object:first

Returns the first item from the given list.

#### Inputs:

* `$list` is a list to fetch the first item from.

#### Outputs:

* `$item` is the first item in the given list.

---

### tundra.list.object:get

Returns the item stored at a given index in a list. A zero-
based index can be specified using the `$index` input, or
a one-based index can be specified using the `$iteration` input
(which is useful when using this service inside a flow loop).

#### Inputs:

* `$list` is a list to fetch an item from.
* `$index` is an optional zero-based index identifying
  the item to be fetched. Supports forward and reverse
  indexing (where, for example, an index of -1 is the
  last item in the list, and an index of -2 is the
  second last item in the list).
* `$iteration` is an optional one-based index identifying
  the item to be fetched.

#### Outputs:

* `$item` is the item stored at the given index in the given
  list.

---

### tundra.list.object:grow

Increases the size of the given list by the given count of items, padded
with the given item (or null if not specified).

#### Inputs:

* `$list` is the Object[] list to be grown.
* `$item` is an optional Object used to pad the newly grown section of
  the list with.
* `$count` is the number of new items to add to the list.
* `$class` is an optional Java class name used to instantiate a new list
  if the provided `$list` is null.

#### Outputs:

* `$list` is the Object[] list grown by the desired `$count` of items,
  with the original items preserved and the new items padded with `$item`
  (or null if not specified).

---

### tundra.list.object:include

Returns `true` if the given item is found in the given list.

#### Inputs:

* `$list` is a list to check whether the given `$item` exists
  in.
* `$item` is the item to be checked against the given `$list`.

#### Outputs:

* `$include?` is a boolean indicating the the given `$item`
  exists as an item in the given `$list`.

---

### tundra.list.object:index

Returns a new string list containing index values for each
relative item in the given list.

#### Inputs:

* `$list` is the list to be indexed.
* `$index.start` is the optional starting index to be used for
  the first item in `$list`. If not specified, defaults to 0.
* `$index.step` is the optional value by which each index is
  incremented for each subsequent item in `$list`. If not
  specified, defaults to 1.

#### Outputs:

* `$indexes` is a string list containing index values for each
  item in `$list` calculated as follows:

      $indexes[n] = $index.start + $index.step * n

---

### tundra.list.object:insert

Returns a new list with the given item inserted at the desired
index in the given list.

#### Inputs:

* `$list` is a list to check whether the given `$item` exists
  in.
* `$item` is the item to be checked against the given `$list`.
* `$index` is the index at which to insert the item. List
  indexing is zero-based. Supports both forward and
  reverse indexing (where, for example, an index of -1
  is the last item in the list, and an index of -2 is the
  second last item in the list).
* `$class` is an optional Java class name that the list and
  item to be inserted are required to be instances of.

#### Outputs:

* `$list` is the resulting list with the item inserted at the
  desired index.

---

### tundra.list.object:instance

Returns `true` if the given list is an instance of the given class.

#### Inputs:

* `$list` is a list to check whether it is an instance of
  the given Java class.
* `$class` is the [Java array class name] used to check the list
  against.

#### Outputs:

* `$instance?` is a boolean indicating if the given list is an
  instance of the given class.

---

### tundra.list.object:intersection

Returns a list of only the items that are also present in all
the given lists.

#### Inputs:

* `$operands` is an `IData` document under which all the lists to
  be intersected should be included.

#### Outputs:

* `$list` is a new list containing only the items that are present
  in all input lists (the [set intersection]).

---

### tundra.list.object:join

Returns a string created by converting each list item to a string,
and concatenating the resulting strings together separated by the
given separator.

#### Inputs:

* `$list` is a list to be converted to a string.
* `$separator` is an optional string used to separate each list item
  in the resulting string. Defaults to an empty string.
* `$default` is an optional string which is will be returned in `$result`
  if the given `$list` is null or empty.
* `$sanitization` is an optional choice of whether to remove nulls,
  nulls and blanks (strings that only contain whitespace
  characters), or convert nulls to blanks. If not specified, no
  sanitization is performed prior to the join.

#### Outputs:

* `$result` is a string containing each item in the given `$list`
  converted to a string and separated by the given `$separator`, or
  the given `$default` string if the given `$list` is null or empty.

---

### tundra.list.object:last

Returns the last item from the given list.

#### Inputs:

* `$list` is a list to fetch the last item from.

#### Outputs:

* `$item` is the last item in the given list.

---

### tundra.list.object:length

Returns the number of items in the given list.

#### Inputs:

* `$list` is a list to count the number of items in.

#### Outputs:

* `$length` is the number of items in the given list.

---

### tundra.list.object:map

Returns a new list created by invoking the given service for each
item in the input list, passing `$item`, `$index`, `$iteration` and
`$length` variables, and collecting the values returned by the service
to form the new list.

This is an implementation of a higher-order [map function] for
object lists.

#### Inputs:

* `$list` is a list to be iterated over.
* `$service` is a fully-qualified service name identifying the
  service to be invoked to process each item in the list.
* `$pipeline` is an optional `IData` document containing arbitrary
  input arguments used when invoking `$service`.
* `$item.input` is an optional variable name used when passing each
  item in the list to the invocation of `$service`. Defaults to
  `$item`.
* `$item.output` is an optional variable name used when extracting
  the resulting item from the invocation of `$service`. Defaults to
  `$item`.

#### Outputs:

* `$list` is the newly constructed list containing the returned
  items from invoking `$service` for each input list item.

---

### tundra.list.object:partition

Partitions the given list into a number of lists using the
associated conditions to determine inclusion of an item in the
partitions.

Note that an item can be included in multiple partitions if it
satisfies multiple conditions.

#### Inputs:

* `$list` is the list to be partitioned.
* `$partitions` is the list of partitions to be created from `$list`.
  * `name` is the name used to identify this partition in the returned
    `$results`.
  * `condition` is a `tundra.condition:evaluate` compatible conditional
    statement used to determine if a `$list` item is to be included in
    this partition.

    List items are represented in the evaluation scope by a variable
    named `$item`.

    For example, to partition a list to only include items where
    surname and firstname keys have specific values, you could use
    the following condition:

        %$item/surname% == "Smith" and %$item/firstname% == "John"

  * `scope` is an optional `IData` document containing the variables
    against which condition will be evaluated. If not specified, the
    condition will be evaluated against `$scope` if specifed,
    otherwise it will be evaluated against the pipeline itself.
* `$scope` is an optional `IData` document containing the variables
  against which `$partitions[n]/condition` will be evaluated when no
  `$partitions[n]/scope` is specified. If not specified, a
  `$partitions[n]/condition` without an associated `$partitions[n]/scope`
  will be evaluated against the pipeline.

#### Outputs:

* `$results` contains the resulting partitions.
  * `<$partitions[n]/name>` is a dynamically named list containing the
    items which satisfied the associated `$partitions[n]/condition`.
  * `<$partitions[n]/name>.length` is the number of items in the
    associated dynamically named partition.
  * `remainder` is a list of the remaining items which are not members
    of any named partitions.
  * `remainder.length` is the number of items in the associated
    remainder list.

---

### tundra.list.object:prepend

Prepends a single item to the front of a list, such that prepending
an item to a list containing n items results in a new list of n + 1
items.

#### Inputs:

* `$list` is a list to be prepended to.
* `$item` is the item to prepend to the given list.
* `$class` is an optional Java class name that the list and item
  to be prepended are required to be instances of.

#### Outputs:

* `$list` is the resulting list with the given `$item` prepended to the
  start.

---

### tundra.list.object:put

Sets the value of the item at the given index in the given list.

#### Inputs:

* `$list` is a list in which to set the given value.
* `$item` is the item to be set in the given list.
* `$index` is an optional zero-based index identifying
  the item to be set. Supports forward and reverse
  indexing (where, for example, an index of -1 is the
  last item in the list, and an index of -2 is the
  second last item in the list).
* `$class` is an optional Java class name that the list
  and item are required to be instances of.

#### Outputs:

* `$list` is the resulting list with the item at the given
  index set to the given value.

---

### tundra.list.object:reject

Filters the given list to not include items where the given
condition evaluates to `true`.

#### Inputs:

* `$list` is the list to be filtered.
* `$condition` is a `tundra.condition:evaluate` compatible conditional
  statement used to filter the given list.

  List items are represented in the evaluation scope by a variable
  named `$item`.

  For example, to filter a list to not include items equal to 1 or
  2, use the following `$condition`:

      %$item% == 1 or %$item% == 2

* `$scope` is an optional `IData` document containing the variables
  against which `$condition` will be evaluated. If not specified, the
  `$condition` will be evaluated against the pipeline.

#### Outputs:

* `$list` is the given list filtered to not include the items where
  `$condition` evaluated to `true`.
* `$list.length` is the number of items in the returned `$list`.

---

### tundra.list.object:resize

Resizes the given list to the given length, truncated from the end when the
length is decreased, and padded with the given item (or null if not
specified) when the length is increased.

#### Inputs:

* `$list` is the Object[] list to be grown.
* `$item` is an optional Object used to pad the list if increasing the
  size.
* `$length` is the desired new length of the list if specified as a
  positive integer, or the relative number of items to truncate from the
  list if specified as a negative integer.
* `$class` is an optional Java class name used to instantiate a new list
  if the provided `$list` is null.

#### Outputs:

* `$list` is the Object[] list resized to the desired `$length`; if the
  new length is less than the original length, the list is truncated from
  the end; if the new length is greater than the original length, the
  list is padded with `$item` (or null if not specified).

---

### tundra.list.object:reverse

Returns a new list with all items from the given list in
reverse order.

#### Inputs:

* `$list` is the list to be reversed.

#### Outputs:

* `$list` is the given list with item ordering reversed.

---

### tundra.list.object:shrink

Decreases the size of the given list by the given count, truncating items
from the end.

#### Inputs:

* `$list` is the Object[] list to be shrunk.
* `$count` is the number of items to truncate from the end of the list.

#### Outputs:

* `$list` is the Object[] list shrunk from the end of the list by the
  desired item count by truncating items from the end of the list.

---

### tundra.list.object:slice

Returns a new list which is a subset of the items in the
given list.

#### Inputs:

* `$list` is the list to be sliced.
* `$index` is the zero-based start index from which to
  take the slice.
* `$length` is the number of items to include in the
  slice.

#### Outputs:

* `$list` is the desired subset or slice of the given list.

---

### tundra.list.object:sort

Returns a new list sorted according to the [natural ordering] of
the given list's items.

#### Inputs:

* `$sort.input` is the list to be sorted.
* `$sort.descending?` is an optional boolean which if `true` will sort
  the given list in descending order. Defaults to `false` (ascending
  order).

#### Outputs:

* `$sort.output` is the sorted list.

---

### tundra.list.object:squeeze

Trims all leading and trailing whitespace from all string values,
then converts empty strings to nulls, then compacts the list by
removing all null values.

#### Inputs:

* `$list` is an object list to be squeezed.

#### Outputs:

* `$list` is the resulting object list with all string values
  trimmed of leading and trailing whitespace characters, and
  all empty string values and null values removed.

---

### tundra.list.object:take

Returns the given number of items from either the head or the tail
of the given list.

#### Inputs:

* `$list` is a list to fetch the head and tail from.
* `$count` is the number of items to take from either the head when
  specified as a positive number, or the tail when specified as a
  negative number.

#### Outputs:

* `$head` is a list containing the head items from the given `$list`.
* `$tail` is a list containing the tail items from the given `$list`.

---

### tundra.list.object:unique

Returns a new list with all duplicates from the given list
removed, such that no two items are equal.

#### Inputs:

* `$list` is the list to process.

#### Outputs:

* `$list` is the resulting list with all duplicate items
  removed.

---

### tundra.list.service:chain

Invokes each service in the given list sequentially, sharing the pipeline
across all invokes.

#### Inputs:

* `$services` is a list of fully-qualified service names identifying the
  services to be invoked sequentially.
* `$pipeline` is an optional scoped pipeline to use when invoking the
  services. If not specified, the pipeline itself is used.

#### Outputs:

* `$pipeline` is the output pipeline of the invocations of `$services`. This
  is only returned if `$pipeline` was specified as an input. Otherwise, the
  outputs of these invocations are merged directly with the pipeline
  itself.
* `$duration` is the total execution duration for all services.

---

### tundra.list.service:ensure

Provides a try/catch/finally pattern for chained flow services. Each
service in the given list is invoked sequentially, and the pipeline
is shared across all invokes.

#### Inputs:

* `$services` is a list of fully-qualified service names
  identifying the services to be invoked sequentially in
  the [try block].
* `$catch` is an optional fully-qualified name of a service
  invoked in the [catch block]. This service should
  implement the `Tundra/tundra.schema.exception:handler`
  specification. If no `$catch` service is specified, the
  exception will be rethrown.
* `$finally` is an optional fully-qualified name of a service
  invoked in the [finally block] (always invoked regardless
  of whether an exception is thrown).
* `$pipeline` is an optional scoped pipeline to use when
  invoking the services. If not specified, the pipeline
  itself is used.

#### Outputs:

* `$pipeline` is the output pipeline of the invocations of
  `$services`, `$catch`, and `$finally`. This is only returned if
  `$pipeline` was specified as an input. Otherwise, the outputs
  of these invocations are merged directly with the pipeline
  itself.

---

### tundra.list.service:invoke

Invokes a list of services either synchronously (with an optional
level of concurrency) or asynchronously.

#### Inputs:

* `$invocations` is a list of services and pipelines to be invoked.
  * `service` is the fully-qualified name of the service to be
    invoked.
  * `pipeline` is an optional `IData` document which, if specified,
    contains the input arguments for the invocation of `service`;
    in other words, the invocation is scoped to this `IData` document.
    If not specified, the invocation is unscoped, and hence the
    service will operate directly against the pipeline itself.
* `$mode` is an optional choice of execution mode: either synchronous
  or asynchronous. When synchronous, the invocation of
  `Tundra/tundra.list.service:invoke` blocks until all invocations
  complete. When asynchronous, `Tundra/tundra.list.service:invoke`
  returns immediately and the invocations occur on other threads.
* `$concurrency` is an optional number of threads used when invoking
  synchronously. Defaults to 1.

#### Outputs:

* `$invocations` is the resulting list of services, output pipelines,
  and threads (when invoked in asynchronous mode).
  * `service` is the fully-qualified name of the service invoked.
  * `pipeline` is the output pipeline of the invocation of
    `service`. This is only returned if the `$mode` is synchronous,
    and the invocation was scoped (`$invocations/pipeline` was specified
    as an input). If invocation was synchronous and unscoped, the
    outputs of the invocation are merged directly with the pipeline
    itself. If the invocation was asynchronous, then no outputs
    are returned.
  * `thread` is returned only when the invocation `$mode` is
    asynchronous, and is the thread object which can later be waited on
    to finish using `Tundra/tundra.list.service:join`.

---

### tundra.list.service:join

Waits for each service thread in the given list to finish before
returning each output pipeline.

#### Inputs:

* `$threads` is a list of service thread objects returned by
  `Tundra/tundra.list.service:invoke` when operating in the
  asynchronous invocation mode.

#### Outputs:

* `$pipelines` is a list of all the output pipelines returned
  by the service threads.

---

### tundra.list.string:append

Appends a single item to the end of a list, such that appending an item
to a list containing n items results in a new list of n + 1 items.

#### Inputs:

* `$list` is a list to append an item to.
* `$item` is an item to be appended to the given list.

#### Outputs:

* `$list` is the resulting list with the given `$item` appended to the
  end.

---

### tundra.list.string:blankify

Converts any null items in the given string list to empty strings.

#### Inputs:

* `$list` is an optional string list.

#### Outputs:

* `$list` is the given list with any null items converted to empty
  strings.

---

### tundra.list.string:capitalize

Capitalizes the first character in either the first word or all
words in each of the given strings.

#### Inputs:

* `$list` is the list of strings to be capitalized.
* `$capitalize` is an optional choice that determines whether all
  words or only the first word is capitalized. Defaults to
  capitalizing all words, if not specified:
  * `all words`
  * `first word`

#### Outputs:

* `$list` is the given list of strings capitalized.

---

### tundra.list.string:coalesce

Returns the first string in the given list that is not null, or if
all strings in the list are null then the given item.

#### Inputs:

* `$list` is an optional list of strings.
* `$default` is an optional string, included for convenience when using
  this service in a map step and providing a default value.
* `$mode` determines what is returned when all arguments are null:
  * `missing`: `$item` is not returned when all arguments are null.
    This is the default, if `$mode` is not specified.
  * `null`: `$item` is returned as null when all arguments are null.

#### Outputs:

* `$item` is the first string in the given `$list` that is not null,
  or the given input `$item` if it is not null, or null if all
  other values were null and `$mode` is 'null'.

---

### tundra.list.string:compact

Removes all null items from the given list, thereby shortening the
length of the list.

#### Inputs:

* `$list` is a list to be compacted.

#### Outputs:

* `$list` is the given list with all null items removed.

---

### tundra.list.string:concatenate

Returns a new list containing all the items in the given lists.

#### Inputs:

* `$operands` is an `IData` document under which all the lists to
  be concatenated should be included.

#### Outputs:

* `$list` is a new list containing all the items from the given
  input lists.

---

### tundra.list.string:difference

Returns a list of only the items in `$list.x` that are not also
present in `$list.y`.

#### Inputs:

* `$list.x` is a list to be checked for differences against
  `$list.y`.
* `$list.y` is a list to be checked against `$list.x` for
  differences.

#### Outputs:

* `$list` is a new list containing only the items in `$list.x`
  that are not also present in `$list.y`.
* `$list.length` is the number of items in the returned `$list`.

---

### tundra.list.string:drop

Removes the item stored at a given index in the given list.

#### Inputs:

* `$list` is a list to remove the item from.
* `$index` is an integer identifying which item to be removed from
  the given list. List indexing is zero-based. Supports both
  forward and reverse indexing (where, for example, an index of
  -1 is the last item in the list, and an index of -2 is the
  second last item in the list).

#### Outputs:

* `$list` is the given list with the item identified by the given
  index removed.

---

### tundra.list.string:each

Iterates through the given list, invoking the given service for each
item in the list, passing `$item`, `$index`, `$iteration` and `$length`
variables.

#### Inputs:

* `$list` is a list to be iterated over.
* `$service` is a fully-qualified service name identifying the
  service to be invoked to process each item in the list.
* `$pipeline` is an optional `IData` document containing arbitrary
  input arguments used when invoking `$service`.
* `$item.input` is an optional variable name used when passing each
  item in the list to the invocation of `$service`. Defaults to
  `$item`.

---

### tundra.list.string:equal

Returns `true` if the given lists are equal.

#### Inputs:

* `$operands` is an `IData` document under which all the lists to
  be compared for equality should be included.

#### Outputs:

* `$equal?` is a boolean indicating if the given lists are equal.

---

### tundra.list.string:filter

Filters the given list to only include items where the given
condition evaluates to `true`.

#### Inputs:

* `$list` is the list to be filtered.
* `$condition` is a `tundra.condition:evaluate` compatible conditional
  statement used to filter the given list.

  List items are represented in the evaluation scope by a variable
  named `$item`.

  For example, to filter a list to only include items equal to a
  regular expression pattern, use the following `$condition`:

      %$item% == /\d\d/

* `$scope` is an optional `IData` document containing the variables
  against which `$condition` will be evaluated. If not specified, the
  `$condition` will be evaluated against the pipeline.

#### Outputs:

* `$list` is the given list filtered to only include the items where
  `$condition` evaluated to `true`.
* `$list.length` is the number of items in the returned `$list`.

---

### tundra.list.string:find

Returns the list of items which included at least one occurrence of the
given [regular expression pattern] or literal string pattern, and the list
of items which did not include any occurrences of the pattern.

#### Inputs:

* `$list` is a list to be searched for the given pattern.
* `$pattern` is the [regular expression pattern] or literal string to
  search for against the given list.
* `$pattern.literal?` is a boolean indicating if the `$pattern` string
  should be treated as a literal string. If `false`, `$pattern` is treated
  as a [regular expression pattern]. If `true`, `$pattern` is treated as
  a literal string. Defaults to `false`, if not specified.

#### Outputs:

* `$found.all?` is a boolean flag which when `true` indicates that all
  items in the given list included the given pattern.
* `$found.any?` is a boolean flag which when `true` indicates that at least
  one item in the given list included the given pattern.
* `$found.none?` is a boolean flag which when `true` indicates that no
  items in the given list included the given pattern.
* `$found` is the list of items which included the given pattern.
* `$found.length` is the number of items in the `$found` list.
* `$unfound` is the list of items which did not include the given
  pattern.
* `$unfound.length` is the number of items in the `$unfound` list.

---

### tundra.list.string:first

Returns the first item from the given list.

#### Inputs:

* `$list` is a list to fetch the first item from.

#### Outputs:

* `$item` is the first item in the given list.

---

### tundra.list.string:get

Returns the item stored at a given index in a list. A zero-
based index can be specified using the `$index` input, or
a one-based index can be specified using the `$iteration` input
(which is useful when using this service inside a flow loop).

#### Inputs:

* `$list` is a list to fetch an item from.
* `$index` is an optional zero-based index identifying
  the item to be fetched. Supports forward and reverse
  indexing (where, for example, an index of -1 is the
  last item in the list, and an index of -2 is the
  second last item in the list).
* `$iteration` is an optional one-based index identifying
  the item to be fetched.

#### Outputs:

* `$item` is the item stored at the given index in the given
  list.

---

### tundra.list.string:grow

Increases the size of the given list by the given count of items, padded
with the given item (or null if not specified).

#### Inputs:

* `$list` is the String[] list to be grown.
* `$item` is an optional String used to pad the newly grown section of
  the list with.
* `$count` is the number of new items to add to the list.

#### Outputs:

* `$list` is the String[] list grown by the desired `$count` of items,
  with the original items preserved and the new items padded with `$item`
  (or null if not specified).

---

### tundra.list.string:include

Returns `true` if the given item is found in the given list.

#### Inputs:

* `$list` is a list to check whether the given `$item` exists
  in.
* `$item` is the item to be checked against the given `$list`.

#### Outputs:

* `$include?` is a boolean indicating the the given `$item`
  exists as an item in the given `$list`.

---

### tundra.list.string:insert

Returns a new list with the given item inserted at the desired
index in the given list.

#### Inputs:

* `$list` is a list to check whether the given `$item` exists
  in.
* `$item` is the item to be checked against the given `$list`.
* `$index` is the index at which to insert the item. List
  indexing is zero-based. Supports both forward and
  reverse indexing (where, for example, an index of -1
  is the last item in the list, and an index of -2 is the
  second last item in the list).

#### Outputs:

* `$list` is the resulting list with the item inserted at the
  desired index.

---

### tundra.list.string:intersection

Returns a list of only the items that are also present in all
the given lists.

#### Inputs:

* `$operands` is an `IData` document under which all the lists to
  be intersected should be included.

#### Outputs:

* `$list` is a new list containing only the items that are present
  in all input lists (the [set intersection]).

---

### tundra.list.string:join

Returns a string created by concatenating the list items together,
separated by the given separator.

#### Inputs:

* `$list` is a list to be converted to a string.
* `$separator` is an optional string used to separate each list item
  in the resulting string. Defaults to an empty string.
* `$default` is an optional string which is will be returned in `$result`
  if the given `$list` is null or empty.
* `$sanitization` is an optional choice of whether to remove nulls,
  nulls and blanks (strings that only contain whitespace
  characters), or convert nulls to blanks. If not specified, no
  sanitization is performed prior to the join.

#### Outputs:

* `$result` is a string containing each item in the given `$list`
  separated by the given `$separator`, or the given `$default` string if
  the given `$list` is null or empty.

---

### tundra.list.string:last

Returns the last item from the given list.

#### Inputs:

* `$list` is a list to fetch the last item from.

#### Outputs:

* `$item` is the last item in the given list.

---

### tundra.list.string:length

Returns the number of items in the given list.

#### Inputs:

* `$list` is a list to count the number of items in.

#### Outputs:

* `$length` is the number of items in the given list.

---

### tundra.list.string:lowercase

Converts all strings in the given list to lower case.

#### Inputs:

* `$list` is a list of strings to be converted to lower case.
* `$locale` optionally identifies the case transformation rules
  to be used for a given [Locale]. If not specified, the
  [default locale] is used.

#### Outputs:

* `$list` is the resulting list of lower case strings, where
  all characters have been converted to their lower case
  equivalent according to the transformation rules of the given
  [Locale].

---

### tundra.list.string:map

Returns a new list created by invoking the given service for each
item in the input list, passing `$item`, `$index`, `$iteration` and
`$length` variables, and collecting the values returned by the service
to form the new list.

This is an implementation of a higher-order [map function] for
string lists.

#### Inputs:

* `$list` is a list to be iterated over.
* `$service` is a fully-qualified service name identifying the
  service to be invoked to process each item in the list.
* `$pipeline` is an optional `IData` document containing arbitrary
  input arguments used when invoking `$service`.
* `$item.input` is an optional variable name used when passing each
  item in the list to the invocation of `$service`. Defaults to
  `$item`.
* `$item.output` is an optional variable name used when extracting
  the resulting item from the invocation of `$service`. Defaults to
  `$item`.

#### Outputs:

* `$list` is the newly constructed list containing the returned
  items from invoking `$service` for each input list item.

---

### tundra.list.string:match

Returns the list of items which matched and the list of items which did
not match the given [regular expression pattern] or literal string pattern.

#### Inputs:

* `$list` is a list to be matched against the given pattern.
* `$pattern` is the [regular expression pattern] or literal string to
  match against the given list.
* `$pattern.literal?` is a boolean indicating if the `$pattern` string
  should be treated as a literal string. If `false`, `$pattern` is
  treated as a [regular expression pattern]. If `true`, `$pattern` is
  treated as a literal string. Defaults to `false`, if not specified.

#### Outputs:

* `$matched.all?` is a boolean flag which when `true` indicates that all
  items in the given list matched the given pattern.
* `$matched.any?` is a boolean flag which when `true` indicates that at
  least one item in the given list matched the given pattern.
* `$matched.none?` is a boolean flag which when `true` indicates that no
  items in the given list matched the given pattern.
* `$matched` is the list of items which matched the given pattern.
* `$matched.length` is the number of items in the `$matched` list.
* `$unmatched` is the list of items which did not match the given
   pattern.
* `$unmatched.length` is the number of items in the `$unmatched` list.

---

### tundra.list.string:normalize

Converts a list of `String`, `byte[]` or `java.io.InputStream` objects
to a list of `String` objects.

#### Inputs:

* `$content.list` is a list of `String`, `byte[]`, or
  `java.io.InputStream` objects to be converted to `String` objects.
* `$content.encoding` is an optional character set to use when
  `$content.list` is a list `byte[]` or `java.io.InputStream` objects.
  Defaults to [UTF-8].

#### Outputs:

* `$content.string.list` is the resulting list of `String` objects.

---

### tundra.list.string:nullify

Converts any strings in the given list that contain only whitespace
characters to null.

#### Inputs:

* `$list` is an optional string list.

#### Outputs:

* `$list` is the given list with any items that contained only whitespace
  characters converted to null.

---

### tundra.list.string:pad

Pads each string in the given string list with the given character as
many times as necessary to reach the given length.

#### Inputs:

* `$list` is the list of strings to be padded to the given length. If
  `$list[n].length >= |$length|`, `$list[n]` is returned unmodified.
* `$length` is the minimum desired length for the returned strings. If
  specified as a positive integer, `$list[n]` will be padded from the
  left by prepending it with `(|$length|- $list[n].length)` characters.
  If specified as a negative integer, `$list[n]` will be padded from the
  right by appending it with `(|$length|- $list[n].length)` characters.
* `$character` is the character to use when padding `$list[n]`. If
  `$character.length > 1`, only the first character in `$character` will
  be used. Defaults to ' ' (space character), if not specified.

#### Outputs:

* `$list` is the resulting list of padded strings.

---

### tundra.list.string:partition

Partitions the given list into a number of lists using the
associated conditions to determine inclusion of an item in the
partitions.

Note that an item can be included in multiple partitions if it
satisfies multiple conditions.

#### Inputs:

* `$list` is the list to be partitioned.
* `$partitions` is the list of partitions to be created from `$list`.
  * `name` is the name used to identify this partition in the returned
    `$results`.
  * `condition` is a `tundra.condition:evaluate` compatible conditional
    statement used to determine if a `$list` item is to be included in
    this partition.

    List items are represented in the evaluation scope by a variable
    named `$item`.

    For example, to partition a list to only include items where
    surname and firstname keys have specific values, you could use
    the following condition:

        %$item/surname% == "Smith" and %$item/firstname% == "John"

  * `scope` is an optional `IData` document containing the variables
    against which condition will be evaluated. If not specified, the
    condition will be evaluated against `$scope` if specifed,
    otherwise it will be evaluated against the pipeline itself.
* `$scope` is an optional `IData` document containing the variables
  against which `$partitions[n]/condition` will be evaluated when no
  `$partitions[n]/scope` is specified. If not specified, a
  `$partitions[n]/condition` without an associated `$partitions[n]/scope`
  will be evaluated against the pipeline.

#### Outputs:

* `$results` contains the resulting partitions.
  * `<$partitions[n]/name>` is a dynamically named list containing the
    items which satisfied the associated `$partitions[n]/condition`.
  * `<$partitions[n]/name>.length` is the number of items in the
    associated dynamically named partition.
  * `remainder` is a list of the remaining items which are not members
    of any named partitions.
  * `remainder.length` is the number of items in the associated
    remainder list.

---

### tundra.list.string:prepend

Prepends a single item to the front of a list, such that prepending
an item to a list containing n items results in a new list of n + 1
items.

#### Inputs:

* `$list` is a list to be prepended to.
* `$item` is the item to prepend to the given list.

#### Outputs:

* `$list` is the resulting list with the given `$item` prepended to the
  start.

---

### tundra.list.string:put

Sets the value of the item at the given index in the given list.

#### Inputs:

* `$list` is a list in which to set the given value.
* `$item` is the item to be set in the given list.
* `$index` is an optional zero-based index identifying
  the item to be set. Supports forward and reverse
  indexing (where, for example, an index of -1 is the
  last item in the list, and an index of -2 is the
  second last item in the list).

#### Outputs:

* `$list` is the resulting list with the item at the given
  index set to the given value.

---

### tundra.list.string:quote

Returns a [regular expression pattern] that can be used to match any
of the given strings literally.

#### Inputs:

* `$list` is the list of strings to be converted to a [regular expression
  pattern].

#### Outputs:

* `$pattern` is a [regular expression pattern] that can be used to match
  the any of the given `$list` strings literally.

---

### tundra.list.string:reject

Filters the given list to not include items where the given
condition evaluates to `true`.

#### Inputs:

* `$list` is the list to be filtered.
* `$condition` is a `tundra.condition:evaluate` compatible conditional
  statement used to filter the given list.

  List items are represented in the evaluation scope by a variable
  named `$item`.

  For example, to filter a list to not include items equal to a
  regular expression pattern, use the following `$condition`:

      %$item% == /\d\d/

* `$scope` is an optional `IData` document containing the variables
  against which `$condition` will be evaluated. If not specified, the
  `$condition` will be evaluated against the pipeline.

#### Outputs:

* `$list` is the given list filtered to not include the items where
  `$condition` evaluated to `true`.
* `$list.length` is the number of items in the returned `$list`.

---

### tundra.list.string:remove

Removes either the first or all occurrences of the given
pattern in the given list of strings.

#### Inputs:

* `$list` is a list of strings to have all occurrences of the given
  pattern removed.
* `$pattern` is the pattern to match against the given string. If
  not specified, no removal will occur.
* `$pattern.literal?` is a boolean indicating if the `$pattern` string
  should be treated as a literal string. If `false`, `$pattern` is
  treated as a [regular expression pattern]. If `true`, `$pattern` is
  treated as a literal string. Defaults to `false`, if not specified.
* `$occurrence.first?` is a boolean indicating if only the first
  occurrence of the given pattern should be removed. Defaults to
  `false`, where all occurrences of the pattern are removed.

#### Outputs:

* `$list` is the input list of strings with occurrences of the
  given pattern removed.

---

### tundra.list.string:replace

Replaces either the first or all occurrences of the given
[regular expression pattern] in the given list of strings,
with the replacement string.

#### Inputs:

* `$list` is a list of strings to have all occurrences of the given
  [regular expression pattern] replaced.
* `$pattern` is the [regular expression pattern] to match against
  the given string. If not specified, no replacement will occur.
* `$pattern.literal?` is a boolean indicating whether the pattern is a
  literal or a [regular expression pattern]. If literal, the pattern
  is converted to a [regular expression pattern] that matches the
  given literal string. Defaults to `false`.
* `$replacement` is the replacement string to be substituted in
  the given string wherever the given pattern is found. If not
  specified, no replacement will occur.
* `$replacement.literal?` is a boolean indicating if the replacement
  string should be treated as a literal string. If `false`, captured
  groups can be referred to with dollar-sign references, such as `$1`,
  and other special characters may need to be escaped. Defaults to
  `false`.
* `$occurrence.first?` is a boolean indicating if only the first
  occurrence of the given pattern should be replaced. Defaults to
  `false`, where all occurrences of the pattern are replaced.

#### Outputs:

* `$list` is the input list of strings with all occurrences of the
  given [regular expression pattern] replaced with `$replacement`.

---

### tundra.list.string:resize

Resizes the given list to the given length, truncated from the end when the
length is decreased, and padded with the given item (or null if not
specified) when the length is increased.

#### Inputs:

* `$list` is the String[] list to be grown.
* `$item` is an optional String used to pad the list if increasing the
  size.
* `$length` is the desired new length of the list if specified as a
  positive integer, or the relative number of items to truncate from the
  list if specified as a negative integer.

#### Outputs:

* `$list` is the String[] list resized to the desired `$length`; if the
  new length is less than the original length, the list is truncated from
  the end; if the new length is greater than the original length, the
  list is padded with `$item` (or null if not specified).

---

### tundra.list.string:reverse

Returns a new list with all items from the given list in
reverse order.

#### Inputs:

* `$list` is the list to be reversed.

#### Outputs:

* `$list` is the given list with item ordering reversed.

---

### tundra.list.string:shrink

Decreases the size of the given list by the given count, truncating items
from the end.

#### Inputs:

* `$list` is the String[] list to be shrunk.
* `$count` is the number of items to truncate from the end of the list.

#### Outputs:

* `$list` is the String[] list shrunk from the end of the list by the
  desired item count by truncating items from the end of the list. If
  the list is smaller than the count, an empty list is returned.

---

### tundra.list.string:slice

Returns a new list which is a subset of the items in the
given list.

#### Inputs:

* `$list` is the list to be sliced.
* `$index` is the zero-based start index from which to
  take the slice.
* `$length` is the number of items to include in the
  slice.

#### Outputs:

* `$list` is the desired subset or slice of the given list.

---

### tundra.list.string:sort

Returns a new list sorted according to the [natural ordering] of
the given list's items.

#### Inputs:

* `$sort.input` is the list to be sorted.
* `$sort.descending?` is an optional boolean which if `true` will sort
  the given list in descending order. Defaults to `false` (ascending
  order).

#### Outputs:

* `$sort.output` is the sorted list.

---

### tundra.list.string:substitute

Attempts variable substitution on each string in the given list by
replacing all occurrences of substrings matching `%key%` with the
associated (optionally scoped) value.

#### Inputs:

* `$list` is a list of strings to perform variable substitution on.
* `$substitution.default` is an optional default value to substitute
  in place of null or missing values.
* `$substitution.mode` is an optional choice of whether the
  substitution scope should be `local` variables only, `global`
  variables only, or `all` variables (local and global variables
  where local variables take precedence). Note that choosing `global`
  on an Integration Server version that does not support global
  variables will result in all values being substituted with the
  default value if one is provided. Defaults to `local`.
* `$pipeline` is an optional `IData` document used to scope the
  variable substitution. If not specified, the substitution is
  unscoped (resolved against the pipeline itself).

#### Outputs:

* `$list` is the input list of strings with variable substitution
  patterns, such as `%key%`, replaced with the value of the key
  (resolved against either `$pipeline`, if specified, or the pipeline
  itself).

---

### tundra.list.string:take

Returns the given number of items from either the head or the tail
of the given list.

#### Inputs:

* `$list` is a list to fetch the head and tail from.
* `$count` is the number of items to take from either the head when
  specified as a positive number, or the tail when specified as a
  negative number.

#### Outputs:

* `$head` is a list containing the head items from the given `$list`.
* `$tail` is a list containing the tail items from the given `$list`.

---

### tundra.list.string:trim

Removes leading and trailing whitespace from each string in the
given list.

#### Inputs:

* `$list` is a list of strings to be trimmed of leading and trailing
  whitespace characters.

#### Outputs:

* `$list` is the input list of strings with leading and trailing
  whitespace characters removed.

---

### tundra.list.string:unique

Returns a new list with all duplicates from the given list
removed, such that no two items are equal.

#### Inputs:

* `$list` is the list to process.

#### Outputs:

* `$list` is the resulting list with all duplicate items
  removed.

---

### tundra.list.string:uppercase

Converts all strings in the given list to upper case.

#### Inputs:

* `$list` is a list of strings to be converted to upper case.
* `$locale` optionally identifies the case transformation rules
  to be used for a given [Locale]. If not specified, the
  [default locale] is used.

#### Outputs:

* `$list` is the resulting list of upper case strings, where
  all characters have been converted to their upper case
  equivalent according to the transformation rules of the given
  [Locale].

---

### tundra.measure:convert

Converts a value in one unit of measurement to another, as long as
it is for the same class of measurement such as distance or mass.

#### Inputs:

* `$value` is an integer or decimal value to be converted from one
  unit of measurement to another.
* `$unit.input` is the standard unit abreviation for the unit of
  measurement that `$value` is to be converted from, such as `kg`,
  `km/h`, `mi/h`, or `ft`.
* `$unit.output` is the standard unit abreviation for the unit of
  measurement that `$value` is to be converted to, such as `kg`,
  `km/h`, `mi/h`, or `ft`. This unit must be in the same class of
  measurement as `$unit.input`.
* `$precision` is an optional number of decimal places to be preserved
  in the result.
* `$rounding` is an optional choice of the [rounding algorithm]
  to use when rounding the result to the specified `$precision`.
  If not specifed, defaults to the `HALF_UP` algorithm.
  * `HALF_UP` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case rounds up.
  * `CEILING` rounds towards positive infinity.
  * `DOWN` rounds towards zero.
  * `FLOOR` rounds towards negative infinity.
  * `HALF_DOWN` rounds towards "nearest neighbor" unless both
    neighbors are equidistant, in which case round down.
  * `HALF_EVEN` rounds towards the "nearest neighbor" unless both
    neighbors are equidistant, in which case, round towards the
    even neighbor.
  * `UNNECESSARY` asserts that the requested operation has an exact
    result, and hence no rounding is necessary.
  * `UP` rounds away from zero.

#### Outputs:

* `$result` is the result of converting the given `$value` from the
  given `$unit.input` to `$unit.output` units of measurement.

---

### tundra.message:connect

Designed to be invoked via an [HTTP] request to test whether a client
can connect and authenticate to Integration Server for publishing
messages via the `tundra.message:receive` service.

Returns an [HTTP] response body in [HTML] format describing a successful
connection to the server.

---

### tundra.message.format:clear

Removes all registered message formats from memory.

---

### tundra.message.format:get

Returns the registered message format with the given name.

#### Inputs:

* `$message.format.name` is the name of the message format to be
  returned.

#### Outputs:

* `$message.format` is the registered message format with the given
  name, if one exists.

---

### tundra.message.format:list

Returns all registered message formats which are used by
`tundra.message:receive` and `tundra.message.format:recognize`
to identify the format or type of a received message.

#### Outputs:

* `$message.formats` is the list of registered message formats
  in ascending lexical name order, which is also the order in
  which they are evaluated for recognition.
* `$message.formats.length` is the number of items in the list.

---

### tundra.message.format:recognize

Attempts to recognize the message received by an Integration Server
gateway service by checking the pipeline against each registered
message format's recognition condition. Message formats are
evaluated in ascending lexical name order, and the first format
whose recognition condition evaluates to `true` against the pipeline
is returned.

Message formats are registered by including the following
configuration section in a `tundra.configuration:get` compatible
package configuration file:

    "tundra/message/format": [
        {
            "name": "Example 1",
            "description": "An example message format",
            "type": "text/xml",
            "namespace": {
                "cn": "http://example.com/common",
                "sh": "http://example.com/shared"
            },
            "recognize": {
                "ref": "%node/example/head/id% != $null"
            },
            "parse": {
                "ref": "schema.example:document"
            },
            "validate": {
                "ref": "schema.example:document"
            },
            "route": {
                "type": "publish",
                "ref": "schema.example:document"
            },
            "enabled": true
        }
    ]

Refer to `tundra.schema.message:format` for further details on the
structure of a message format.

The total set of registered message formats is the set of all
formats specified across all package configuration files whose names
are unique. It is expected, but not required, that message formats
are mutually exclusive.

#### Outputs:

* `$message.recognized?` is a boolean which when `true` indicates that a
  message format was recognized against the given pipeline.
* `$message.format` is returned when `$recognized?` is `true` and is the
  first registered message format whose recognize condition matched
  the pipeline.

---

### tundra.message.format:refresh

Reloads all registered message formats from disk. Changes made to
message formats in package configuration files will not take effect
until this service is run.

Message formats are registered by including the following
configuration section in a `tundra.configuration:get` compatible
package configuration file:

    "tundra/message/format": [
        {
            "name": "Example 1",
            "description": "An example message format",
            "type": "text/xml",
            "namespace": {
                "cn": "http://example.com/common",
                "sh": "http://example.com/shared"
            },
            "recognize": {
                "ref": "%node/example/head/id% != $null"
            },
            "parse": {
                "ref": "schema.example:document"
            },
            "validate": {
                "ref": "schema.example:document"
            },
            "route": {
                "type": "publish",
                "ref": "schema.example:document"
            },
            "enabled": true
        }
    ]

Refer to `tundra.schema.message:format` for further details on the
structure of a message format.

The total set of registered message formats is the set of all
formats specified across all package configuration files whose names
are unique. It is expected, but not required, that message formats
are mutually exclusive.

---

### tundra.message:receive

Receives arbitrary content and routes it to either the webMethods 
messaging subsystem via `pub.publish:publish`, a JMS destination, 
or a direct service invocation.

The content can be specified as a string, byte array, `IData` 
document, `java.io.InputStream`, or `org.w3c.dom.Node` object.

This service is either intended to be invoked directly by clients 
via HTTP or FTP, or it can be wrapped by another service which 
specifies appropriate pipeline variables to control the routing of 
the content.

When invoked via HTTP, the service returns a `text/plain` response 
body that is empty on success, or contains a message describing the
errors that occurred on failure, and an appropriate HTTP response 
code according to the following table:

Response                   | Reason
---------------------------|-------------------------------------------------------
202 Accepted               | Received content was routed successfully
400 Bad Request            | Received content was malformed
403 Forbidden              | Sender was denied access to route the received content
406 Not Acceptable         | Received content was not recognized (Unknown)
409 Conflict               | Received content was detected as a duplicate
422 Unprocessable Entity   | Received content failed validation
500 Internal Server Error  | All other errors that occur while processing

When invoked via transports other than HTTP, for example FTP, if the 
content is received successfully the service invocation will succeed 
and an empty response body is returned. If a security or any other 
exception is encountered, the service invocation will fail by 
rethrowing the exception.

When invoked by a wrapping service, any exceptions encountered will 
be thrown to the calling service. It is then the calling service's 
responsibility to set an appropriate response for the transport in 
question.

Refer to `tundra.message.format:recognize` for further details on how 
message formats are recognized by this service.

Note: the successful HTTP response code 202 can be overridden to 
use a different code via the Tundra package configuration setting: 
`feature/message/receive/response/code`.

---

### tundra.message:retrieve

Retrieves arbitrary content from the given `$source` URI, and routes
it to either the webMethods messaging subsystem via
`pub.publish:publish`, a JMS destination, or a direct service
invocation.

Additional retrieval protocols can be implemented by creating a
service named for the URI scheme in the folder
`Tundra/tundra.content.retrieve`.  Services in this folder must
implement the `Tundra/tundra.schema.content.retrieve:handler`
specification.

#### Inputs:

* `$source` is a URI identifying the location from which content is to
  be retrieved. Supports the following retrieval protocols / URI
  schemes:
  * `file:` routes each file matching the given `$source` URI according
    to the routing specified by the recognized message format. The
    file component of the URI can include wildcards or globs (such
    as *.txt or *.j?r) for matching multiple files at once.

    The following example would process all *.txt files in the
    specified directory:

        file:////server:port/directory/*.txt

    To ensure each file processed is not locked or being written to
    by another process, the file is first moved to a working
    directory. The name of this directory can be configured by
    adding a query string parameter called working to the URI, for
    example:

        file:////server:port/directory/*.txt?working=temp

    In this example, files are first moved to a subdirectory named
    temp. If not specified, the working directory defaults to a
    subdirectory named `working`.

    After successful processing, the file is then moved to an archive
    directory. The name of this directory can be configured by
    adding a query string parameter called archive to the URI, for
    example:

        file:////server:port/directory/*.txt?archive=backup

    In this example, files are moved to a subdirectory named backup
    after being successfully processed. If not specified, the
    archive directory defaults to a subdirectory named `archive`.

    Optionally, archived files older than a given age can be cleaned
    up automatically by the retrieve process by specifying a query
    string parameter called purge with an XML duration value
    representing the age an archived file must be before being
    purged, for example:

        file:////server:port/directory/*.txt?purge=P14D

    In this example, any files in the archive directory older than
    14 days will be automatically deleted by the retrieve process.
    If the query string parameter purge is not specified, archived
    files will not be automatically cleaned up.
  * `ftp`: processes each file matching the given `$source` URI with the
    given processing `$service`. The file component of the URI can
    include wildcards or globs (such as `*.txt`) for matching multiple
    files at once.

    The following example would process all `*.txt` files in the
    specified directory using active FTP with an ASCII transfer mode
    and a server timeout of 5 minutes:

        ftp://user:password@server:port/directory/*.txt?active=true&ascii=true&timeout=PT5M

    The following additional settings can be specified using query
    string parameters:
    * `active`: optional boolean which when `true` uses active FTP.
      Defaults to passive FTP.
    * `ascii`: optional boolean which when `true` sets the FTP transfer
      mode to ASCII. Defaults to binary transfer mode.
    * `timeout`: optional XML duration specifying the time to wait for
      a response from the FTP server before timing out and
      terminating the request. Defaults to PT1M (1 minute).
  * `ftps`: refer to `ftp`.
* `$message.format.name` is an optional message format name which if
  specified will skip the recognition step and instead use the
  format with the given name when routing.
* `$limit` is an optional maximum number of content matches to be
  processed in a single execution. Defaults to `1000`.

---

### tundra.message:route

Routes arbitrary content to either the webMethods messaging
subsystem via `pub.publish:publish`, a JMS destination, or a direct
service invocation.

The content can be specified as a string, byte array, input stream,
`org.w3c.dom.Node` object, `com.sap.conn.idoc.IDocDocumentList` object,
or `IData` document.

#### Inputs:

* `$message` is arbitrary content specified as a string, byte array,
  input stream, `IData` document, `com.sap.conn.idoc.IDocDocumentList`,
  or `org.w3c.dom.Node`.
* `$message.format` is an optional message format structure where only
  the `name` element is mandatory.
  * `name` is an optional message format name which if specified will
    skip the recognition step and instead use the format with the
    given name for routing. If the given `$message` is not recognized
    but is provided as an `IData` document, it will default to being
    routed using `pub.publish:publish` with this value as the
    `documentTypeName`.
  * `parse/ref` is an optional document reference to be used when a
    `$message` is specified as a `com.sap.conn.idoc.IDocDocumentList`
    object: before message recognition and routing, `$message` is
    converted to an `IData` document named `$document` using the service
    `WmSAP/pub.sap.idoc:iDocToDocument` with the input argument
    `conformsTo` set to this value.

---

### tundra.message.trigger:recognize

Attempts to recognize the published document in a trigger service's
pipeline, and returns the document with a normalized variable name
and the message format that the document is a parsed instance of.

This service is designed to be invoked as the first step in a
trigger service, and it is expected that the pipeline contains only
one variable: the published document the trigger was invoked to
process.

#### Outputs:

* `$message` is the published document with a normalized and
  predictable variable name.
* `$message.format` is the message format the published document was
  recognized to be a parsed instance of.

---

### tundra.mime.type:classify

Returns a well-known content class for the given MIME media type.

#### Inputs:

* `$content.type` is the MIME media type to be classified.

#### Outputs:

* `$content.class` is the resulting classification of the given MIME
  media type, and is one of the following values or null if it could
  not be classified as one of these values:
  * `csv` - comma-separated values
  * `hjson` - humane javascript object notation
  * `json` - javascript object notation
  * `plain` - plain text
  * `psv` - pipe-separated values
  * `tsv` - tab-separated values
  * `xml` - extensible markup language
  * `yaml` - yaml ain't markup language
  * `unknown` - the content type was not well-known

---

### tundra.mime.type:emit

Emits a Multipurpose Internet Mail Extension ([MIME]) type,
according to [RFC 2045] and [RFC 2046], given its constituent
parts.

Implemented with the [javax.activation.MimeType] class.

#### Inputs:

* `$type` is an optional [mime type] data structure of the
  constituent parts that make a [mime type].

#### Outputs:

* `$string` is resulting [mime type] in its canonical string
  representation.

---

### tundra.mime.type:equal

Returns `true` if the given [mime type] strings are considered equal.

#### Inputs:

* `$string.x` is an optional [mime type] string to be compared
  with `$string.y`.
* `$string.y` is an optional [mime type] string to be compared
  with `$string.x`.

#### Outputs:

* `$equal?` is `true` if the given [mime type] strings are considered
  equal (their base and sub types both match).

---

### tundra.mime.type:extensions

Returns the list of file extensions that are associated with the
given [mime type].

#### Inputs:

* `$mime.type` is a [mime type] whose associated file extensions are
  to be returned.

#### Outputs:

* `$file.extensions` is the list of file extensions associated with
  the given `$mime.type`.

---

### tundra.mime.type:normalize

Normalizes a mime type by removing extraneous whitespace characters,
and listing parameters in alphabetical order.

Implemented with the [javax.activation.MimeType] class.

#### Inputs:

* `$string` is an optional [mime type] string to be normalized.

#### Outputs:

* `$string` is the normalized [mime type] string.

---

### tundra.mime.type:parse

Parses a Multipurpose Internet Mail Extension ([MIME]) type, according
to [RFC 2045] and [RFC 2046], into its constituent parts.

Implemented with the [javax.activation.MimeType] class.

#### Inputs:

* `$string` is an optional [mime type] string to be parsed.

#### Outputs:

* `$type` is an `IData` data structure representing the constituent
  parts of a [mime type] string.

---

### tundra.mime.type:text

Returns whether the given MIME media type describes a text-based type.

#### Inputs:

* `$content.type` is the MIME media type to check.

#### Outputs:

* `$content.type.text?` is `true` if the given `$content.type`
  describes a text-based data type, otherwise `false` if it describes
  a binary data type.

---

### tundra.mime.type:validate

Returns `true` if the given string can be parsed as a valid Multipurpose
Internet Mail Extension ([MIME]) type, according to [RFC 2045] and
[RFC 2046].

Implemented with the [javax.activation.MimeType] class.

#### Inputs:

* `$string` is an optional [mime type] string to be validated.
* `$raise?` is an optional boolean indicating whether to throw an
  exception if the given string is not a valid [mime type] string.
  Defaults to `false`.

#### Outputs:

* `$valid?` is a boolean indicating if the given string is a valid
  [mime type] string.

---

### tundra.node:access

Grants the specified permissions on the given namespace node.

#### Inputs:

* `$node` is the Integration Server namespace node to grant
  permissions on, provided as a normal namespace identifer:

      folder(.folder)*(:object)

  Where brackets indicate optional components of the namespace
  identifer, '*' indicates 0 or more occurrences of a
  component, '.' and ':' are literal characters.
* `$permissions` is the list of permissions to be granted on
  the given `$node`.
  * `type` identifies the type of permission to be granted, one of:
    * `list` allows members of the given ACL to see the child items
      in the given node
    * `read` allows members of the given ACL to view the given node
    * `write` allows members of the given ACL to change the given node
    * `execute` allows members of the given ACL to execute the given node
  * `acl` is the name of the ACL object to be granted permission on
    this namespace node.

---

### tundra.node:exists

Returns `true` if the given namepace node exists.

#### Inputs:

* `$node` is the Integration Server namespace node to check
  existence of, provided as a normal namespace identifer:

      folder(.folder)*(:object)

  Where brackets indicate optional components of the namespace
  identifer, '*' indicates 0 or more occurrences of a
  component, '.' and ':' are literal characters.

#### Outputs:

* `$exists?` is a boolean flag indicating if the given node
  exists on this Integration Server.

---

### tundra.node:list

Lists an interface's child nodes. Optionally filters based on
the given regular expression pattern, and node type.

#### Inputs:

* `$interface` is the Integration Server namespace folder to check
  existence of, provided as a normal namespace interface node:

      folder(.folder)*

  Where brackets indicate optional components of the namespace
  identifer, and '*' indicates 0 or more occurrences of a
  component.
* `$pattern` is an optional regular expression pattern used to
  filter the returned list of nodes (only nodes whose name
  matches the given pattern are returned).
* `$type` is an optional node type used to filter the returned
  list of nodes (only nodes whose type matches are returned).
* `$recurse?` is an optional boolean flag indicating whether to
  recursively list all items in all child folders/interfaces.
  Defaults to `false`.

#### Outputs:

* `$nodes` is the list of (optionally filtered) child nodes that
  exist as items within the given `$interface`.
* `$nodes.length` is the number of items in the `$nodes` list.

---

### tundra.node:type

Returns the type of the given node, such as whether it is an
interface, service, or record.

#### Inputs:

* `$node` is the Integration Server namespace node to check
  existence of, provided as a normal namespace identifer:

      folder(.folder)*(:object)

  Where brackets indicate optional components of the namespace
  identifer, '*' indicates 0 or more occurrences of a
  component, '.' and ':' are literal characters.

#### Outputs:

* `$type` is the type of node that `$node` is, such as interface,
  or service.

---

### tundra.packages:exists

Returns `true` if a package with the given name exists on this
Integration Server.

#### Inputs:

* `$name` is the name of the package to check existence of.

#### Outputs:

* `$exists?` is a boolean indicating whether a package with
  the given name exists on this Integration Server.

---

### tundra.packages:get

Returns information about the package with the given name.

#### Inputs:

* `$name` is the name of the package to return information
  about.

#### Outputs:

* `$package` contains the following information about the package
  with the given name if it exists:
  * `name` is the name of the package.
  * `description` is the optional description of the package.
  * `version` is the version of the package as per its manifest.
  * `enabled?` is a boolean indicating if the package is enabled.
  * `system?` is a boolean indicating if the package is considered
    a system package.
  * `dependencies` is a list of all the packages this package is
    dependent on as per its manifest:
    * `package` is the name of the dependent package.
    * `version` is the version of the dependent package.
  * `dependencies.length` is the number of items in the
    dependencies list.
  * `services` lists all the different type of services in the
    package:
    * `startup` is a list of all the registered startup services in
      the package.
    * `startup.length` is the number of items in the `startup` list.
    * `shutdown` is a list of all the registered shutdown services
      in the package.
    * `shutdown.length` is the number of items in the `shutdown` list.
    * `replication` is a list of all the registered replication
      services in the package.
    * `replication.length` is the number of items in the `replication`
      list.
    * `loaded` is a list of all the loaded services in the package.
    * `loaded.length` is the number of items in the `loaded` list.
  * `directories` provides the location of all the well-known
    directories for the package:
    * `root` is the package's root directory.
    * `ns` is the package's namespace directory.
    * `pub` is the package's public HTML directory.
    * `template` is the package's service template directory.
    * `web` is the package's Java web application directory.
    * `config` is the package's configuration directory.
  * `references` describes the elements the services in this package
    reference.
    * `packages` is the list of referenced packages.
    * `packages.length` is the length of the list of referenced
      packages.
    * `nodes` is the list of resolved references.
      * `package` is the package the reference resides in.
      * `node` is the name of the referenced element.
    * `nodes.length` is the length of the resolved references list.
    * `unresolved` is the list of unresolved references.
    * `unresolved.length` is the length of the unresolved references
      list.
* `$exists?` is a boolean indicating whether a package with
  the given name exists on this Integration Server.

---

### tundra.packages:list

Returns a list of either all known packages or only those enabled on
this Integration Server.

#### Inputs:

* `$enabled?` is an optional boolean indicating whether to return
  only packages which are enabled on this Integration Server.
  Defaults to `false`.

#### Outputs:

* `$packages` is the list of either all known packages or only those
  enabled on this Integration Server:
  * `name` is the name of the package.
  * `description` is the optional description of the package.
  * `version` is the version of the package as per its manifest.
  * `enabled?` is a boolean indicating if the package is enabled.
  * `system?` is a boolean indicating if the package is considered
    a system package.
  * `dependencies` is a list of all the packages this package is
    dependent on as per its manifest:
    * `package` is the name of the dependent package.
    * `version` is the version of the dependent package.
  * `dependencies.length` is the number of items in the
    `dependencies` list.
  * `services` lists all the different type of services in the
    package:
    * `startup` is a list of all the registered startup services in
      the package.
    * `startup.length` is the number of items in the `startup` list.
    * `shutdown` is a list of all the registered shutdown services
      in the package.
    * `shutdown.length` is the number of items in the `shutdown` list.
    * `replication` is a list of all the registered replication
      services in the package.
    * `replication.length` is the number of items in the `replication`
      list.
    * `loaded` is a list of all the loaded services in the package.
    * `loaded.length` is the number of items in the `loaded` list.
  * `directories` provides the location of all the well-known
    directories for the package:
    * `root` is the package's root directory.
    * `ns` is the package's namespace directory.
    * `pub` is the package's public HTML directory.
    * `template` is the package's service template directory.
    * `web` is the package's Java web application directory.
    * `config` is the package's configuration directory.
* `$packages.length` is the number of items in the `$packages` list.

---

### tundra.packages:self

Returns information about the package in which the calling service
is stored.

#### Outputs:

* `$package` contains the following information about the package
  in which the calling service is stored.
  * `name` is the name of the package.
  * `description` is the optional description of the package.
  * `version` is the version of the package as per its manifest.
  * `enabled?` is a boolean indicating if the package is enabled.
  * `system?` is a boolean indicating if the package is considered
    a system package.
  * `dependencies` is a list of all the packages this package is
    dependent on as per its manifest:
    * `package` is the name of the dependent package.
    * `version` is the version of the dependent package.
  * `dependencies.length` is the number of items in the
    dependencies list.
  * `services` lists all the different type of services in the
    package:
    * `startup` is a list of all the registered startup services in
      the package.
    * `startup.length` is the number of items in the startup list.
    * `shutdown` is a list of all the registered shutdown services
      in the package.
    * `shutdown.length` is the number of items in the shutdown list.
    * `replication` is a list of all the registered replication
      services in the package.
    * `replication.length` is the number of items in the replication
      list.
    * `loaded` is a list of all the loaded services in the package.
    * `loaded.length` is the number of items in the loaded list.
  * `directories` provides the location of all the well-known
    directories for the package:
    * `root` is the package's root directory.
    * `ns` is the package's namespace directory.
    * `pub` is the package's public HTML directory.
    * `template` is the package's service template directory.
    * `web` is the package's Java web application directory.
    * `config` is the package's configuration directory.
  * `references` describes the elements the services in this package
    reference.
    * `packages` is the list of referenced packages.
    * `packages.length` is the length of the list of referenced
      packages.
    * `nodes` is the list of resolved references.
      * `package` is the package the reference resides in.
      * `node` is the name of the referenced element.
    * `nodes.length` is the length of the resolved references list.
    * `unresolved` is the list of unresolved references.
    * `unresolved.length` is the length of the unresolved references
      list.

---

### tundra.object:coalesce

Returns the first object argument that is not null.

#### Inputs:

* `$operands` is an `IData` document containing arbitrarily specified
  top-level objects to be coalesced.
* `$mode` determines what is returned when all arguments are null:
  * `missing`: `$object` is not returned when all arguments are null.
    This is the default, if `$mode` is not specified.
  * `null`: `$object` is returned as null when all arguments are null.

#### Outputs:

* `$object` is the first of the given objects whose value is not null,
  or null if all arguments were null and `$mode` is `null`.

---

### tundra.object:equal

Returns `true` if the two given objects are equal.

#### Inputs:

* `$object.x` is an optional object of any class to be compared
  to `$object.y`.
* `$object.y` is an optional object of any class to be compared
  to `$object.x`.

#### Outputs:

* `$equal?` is `true` if the two input objects are considered
  equivalent.

---

### tundra.object:instance

Returns `true` if the given object is an instance of the given class.

#### Inputs:

* `$object` is an optional object of any class.
* `$class` is a Java class name.

#### Outputs:

* `$instance?` is `true` if the give object is an instance of the given
  Java class.

---

### tundra.object:listify

Converts the value or values identified by the given key to a new
list containing the original value or values as its items, unless
the original value is already a list in which case it remains
unmodified.

#### Inputs:

* `$key` is a simple or fully-qualified (such as `a/b/c[0]/d`) key
  identifying the value to be converted to a list.
* `$scope` is an optional `IData` document that, if specified, is used
  to resolve the given `$key` against. If not specified, `$key` is
  resolved against the pipeline.

#### Outputs:

* `$scope` is returned if an input `$scope` was provided, where the value
  identified by `$key` within it has been converted to a list. If
  no input `$scope` was specified, the value identified by `$key` in the
  pipeline is instead converted to a list. If `$key` does not identify
  any value, this service does nothing.

---

### tundra.object:nothing

Returns null.

#### Outputs:

* `$nothing` is returned with a null value.

---

### tundra.object:reflect

Returns whether the given object is an array, a primitive type or an
array of a [primitive type], and its Java class name.

#### Inputs:

* `$object` is an optional object to be reflected on. If neither input
  is specified, this service does nothing and no outputs are returned.
* `$key` is an optional key identifying the value in the pipeline to
  relect on, and can be simple or fully qualified, such as `a/b/c[0]/d`.
  Use `$key` instead of `$object` when reflecting on an object that could
  be a list/array, because mapping a list/array to `$object` will only
  map the first item in the list. If neither input is specified, this
  service does nothing and no outputs are returned.

#### Outputs:

* `$id` is the identity hash code of the given object, as calculated by
  the [java.lang.System.identityHashCode()] method. The identity hash
  code for the null reference is always zero.
* `$class` is the Java class name of the given object.
* `$array?` is a boolean indicating if the given object is an array.
* `$primitive?` is a boolean indicating if the given object is a
  [primitive type], or an array of a [primitive type].

---

### tundra.object:stringify

Converts the given object to its string representation by calling
[Object.toString()].

#### Inputs:

* `$object` is an optional object to be converted to its string
  representation.

#### Outputs:

* `$string` is the given object converted to its string
  representation.

---

### tundra.pipeline:capture

Clones the pipeline and returns it.  This is useful if you want
to use the pipeline itself as a document, which you can then pass
as input when calling a service for example.

#### Outputs:

* `$pipeline` is the captured pipeline as an `IData` document.

---

### tundra.pipeline:clear

Removes all elements from the pipeline, except for any keys
specified in the preserve list.

#### Inputs:

* `$preserve` is a list of keys (simple or fully qualified,
  such as `a/b/c[0]/d`) to not be dropped from the pipeline.
  All other keys not in this list will be dropped.

---

### tundra.pipeline:copy

Copies the value associated with the source key to the target key in the
pipeline.

#### Inputs:

* `$key.source` identifies the value to be copied, and can be simple or
  fully qualified, such as `a/b/c[0]/d`.
* `$key.target` is the key to copy the source value to, and can be simple or
  fully qualified, such as `a/b/c[0]/d`.
* `$key.literal?` is an optional boolean flag which if `true` will treat the
  given keys literally rather than as potentially fully-qualified keys.
  Defaults to `false` if not specified.

---

### tundra.pipeline:denormalize

Replaces all nested child elements in the pipeline with non-nested
elements whose keys are fully-qualified.

For example, if the pipeline contains the following key value pairs
(using [JSON] notation to represent the pipeline):

    {
      "a": {
        "b": {
          "c": "example 1",
          "d": "example 2"
        }
      },
      "e": "example 3",
      "f": ["example 4", "example 5"]
    }

This is denormalized to the following:

    {
      "a/b/c": "example 1",
      "a/b/d": "example 2",
      "e": "example 3",
      "f[0]": "example 4",
      "f[1]": "example 5"
    }

---

### tundra.pipeline:drop

Drops the key value pair associated with the given key from the pipeline.

#### Inputs:

* `$key` identifies the key value pair to be dropped, and can be simple or
  fully qualified, such as `a/b/c[0]/d`.
* `$key.literal?` is an optional boolean flag which if `true` will treat the
  given keys literally rather than as potentially fully-qualified keys.
  Defaults to `false` if not specified.

---

### tundra.pipeline:emit

Emits or serializes the current pipeline as an string, byte array,
or input stream.

#### Inputs:

* `$content.class` is an optional choice of the format to use: either
  `xml`, `json`, or `yaml`. Defaults to `xml`.
* `$content.encoding` is an optional character set to use when
  `$content` is returned as an input stream or byte array. Defaults to
  [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or `string`,
  and determines the type of content object returned. Defaults to
  `stream`.

#### Outputs:

* `$content` is an input stream, byte array, or string (depending on
  the `$mode` selected) representing the encoded pipeline data.

---

### tundra.pipeline:first

Returns the first key value pair from the pipeline.

#### Outputs:

* `$key` is the first key in the pipeline.
* `$value` is the first key's associated value.

---

### tundra.pipeline:flatten

Returns the values associated with the given keys from the pipeline
flattened to a one-dimensional array.

For example, given the following pipeline containing a single `IData`
document `a` (represented in [JSON] form):

    {
      "a": [
        {
          "b": [
            {
              "c": "1"
            },
            {
              "c": "2"
            },
            {
              "c": "3"
            }
          ]
        },
        {
          "b": [
            {
              "c": "4"
            },
            {
              "c": "5"
            }
          ]
        }
      ]
    }

A flattening of `a/b/c` would return the following (represented in
[JSON] form):

    ["1", "2", "3", "4", "5"]

A flattening of `a/b[0]/c` would return the following (represented
in [JSON] form):

    ["1", "2", "3"]

And a flattening of `a/b[0]/c[0]` would return the following
(represented in [JSON] form):

    ["1"]

#### Inputs:

* `$keys` is a list of fully-qualified keys identifying the values in
  the pipeline to be flattened. If the keys index multiple nested
  arrays all associated values in those arrays will be flattened
  into the returned one-dimensional array.
* `$nulls?` is an optional boolean flag which when `true` will include
  missing or null values as nulls in the resulting array. Defaults
  to `false`.

#### Outputs:

* `$values` is a one-dimensional array containing the flattened values
  associated with the given `$keys` in pipeline.

---

### tundra.pipeline:get

Returns the value associated with the given key from the pipeline, or null
if it doesn't exist.

Also supports resolving [XPath] expressions against [org.w3c.dom.Node]
objects in the top level of the pipeline.

#### Inputs:

* `$key` identifies the value to be retrieved from the pipeline, and can be
  simple or fully qualified, such as `a/b/c[0]/d`.
* `$key.literal?` is an optional boolean flag which if `true` will treat the
  given key literally rather than as a potentially fully-qualified key.
  Defaults to `false` if not specified.
* `$default.object` is an optional value to be returned when either the
  given `$key` does not exist or its associated value is null.
* `$default.string` is an optional string value, provided for convenience
  when hard-coding a default value, to be returned when either the given
  `$key` does not exist or its associated value is null.

#### Outputs:

* `$value` is the value associated with the given `$key`.

---

### tundra.pipeline:last

Returns the last key value pair from the pipeline.

#### Outputs:

* `$key` is the last key in the pipeline.
* `$value` is the last key's associated value.

---

### tundra.pipeline:length

Returns the number of top-level elements in the pipeline.

#### Outputs:

* `$length` is the number of top-level elements in the
  pipeline.

---

### tundra.pipeline:listify

Converts the value or values identified by the given key to a new
list containing the original value or values as its items, unless
the original value is already a list in which case it remains
unmodified.

#### Inputs:

* `$key` is a simple or fully-qualified (such as `a/b/c[0]/d`) key
  identifying the value to be converted to a list.

---

### tundra.pipeline:log

Writes the current pipeline to the server log, or a given named log,
as a minified [JSON] string, optionally prefixed with the invoking
user, call stack, and message.

#### Inputs:

* `$log.level` is an optional logging level used when writing the
  pipeline to the server log.
* `$log.message` is an optional message to be written to the server
  log.
* `$log.prefix?` is a boolean indicating whether to prefix the log
  statement with the current user and callstack. Defaults to `true`.
* `$log.name` is the optional logical name of the log to write to.
  The logical log name can be mapped to a physical log target file
  via the Tundra package configuration section `feature/log/target`.
  If the logical log name cannot be found in this configuration
  section, the default physical log target is `./logs/server.log`.
  Defaults to the calling service's package name, if not specified.

---

### tundra.pipeline:merge

Merges the elements in the given `IData` document into the
pipeline.

#### Inputs:

* `$document` is an `IData` document whose top-level elements
  are to be copied directly into the pipeline.

---

### tundra.pipeline:normalize

Iterates over each element in the pipeline, deconstructing
all fully qualified keys into their constituent parts.

For example, if a pipeline contains the following key value
pairs (using [JSON] notation to represent the pipeline):

    {
      "a/b/c": "example 1",
      "a/b/d": "example 2",
      "e": "example 3",
      "f[0]": "example 4",
      "f[1]": "example 5"
    }

This is normalized to the following:

    {
      "a": {
        "b": {
          "c": "example 1",
          "d": "example 2"
        }
      },
      "e": "example 3",
      "f": ["example 4", "example 5"]
    }

Keys using path-style notation, for example "a/b/c", are
converted to nested `IData` documents with the final key
in the path, "c" in this example, assigned the value of
the original key.

Keys using array- or list-style notation, for example "f[0]",
are converted to an array or list with the value of the
original key assigned to the indexed item (the zeroth item in
this example).

---

### tundra.pipeline:parse

Parses or deserializes the given string, byte array, or input stream
and merges it into the pipeline.

#### Inputs:

* `$content` is a string, byte array, or input stream containing
  [IData XML] data to be parsed and merged into the pipeline.
* `$content.class` is an optional choice of the format to use: either
  `xml`, `json`, or `yaml`. Defaults to `xml`.
* `$content.encoding` is an optional character set to use when
  `$content` is provided as a byte array or input stream. Defaults to
  [UTF-8].

---

### tundra.pipeline:put

Sets the value associated with the given key in the pipeline.

#### Inputs:

* `$key` is a simple or fully qualified key (`a/b/c[0]/d`) to associate with
  the given `$value`.
* `$key.literal?` is an optional boolean flag which if `true` will treat the
  given key literally rather than as a potentially fully-qualified key.
  Defaults to `false` if not specified.
* `$value` is the value to be associated with the given `$key`.

---

### tundra.pipeline:rename

Renames the value associated with the source key to have the target key in
the pipeline. After being renamed, the source key will no longer exist in
the pipeline.

#### Inputs:

* `$key.source` is a simple or fully qualified key (`a/b/c[0]/d`) to be
  renamed to the given target key.
* `$key.target` is the new simple or fully qualified key that the source key
  will be renamed to.
* `$key.literal?` is an optional boolean flag which if `true` will treat the
  given key literally rather than as a potentially fully-qualified key.
  Defaults to `false` if not specified.

---

### tundra.pipeline:sanitize

Sanitizes the pipeline by dropping all undeclared inputs or
outputs when compared to either the input or output signature
of the current service respectively.

When sanitizing against the input signature of a flow service,
as the first step of that service, this service protects against
undeclared input variables that exist in the pipeline from the
caller that could inadvertently affect current service's logic,
or could present a security risk. For example, REST services that
accept JSON documents sent by clients that are then parsed by
Integration Server automatically and provided as input variables
can use this service to remove all unexpected inputs from the
pipeline as a security measure when dealing with untrusted clients.

When sanitizing against the output signature of a flow service,
as the last step of that service, this service automatically
removes all undeclared outputs from the pipeline, saving the need
to respecify the outputs such as would be required when using
`WmPublic/pub.flow:clearPipeline` to achieve the same outcome.

#### Inputs:

* `$service.signature.direction` determines whether to sanitize
  the pipeline against the input or output signature of the current
  service.
* `$pipeline.sanitize.recurse?` is an optional boolean which when
  `true` will also recursively sanitize all child `IData` documents
  and `IData[]` document lists. Defaults to `false`.

---

### tundra.pipeline:sort

Sorts the top-level elements in the pipeline by their keys in natural
ascending order.

---

### tundra.pipeline:squeeze

Recursively trims all leading and trailing whitespace from all
string values, then converts empty strings, empty `IData` documents,
and empty lists to nulls, then compacts the pipeline by removing all
null values.

---

### tundra.pipeline:substitute

Attempts variable substitution on every string element in the
pipeline by replacing all occurrences of substrings matching `%key%`
with the associated value.

---

### tundra.pipeline:trim

Removes leading and trailing whitespace from all string values in
the pipeline.

---

### tundra.pipeline:validate

Validates the pipeline against either the input or output signature
of the current service.

This service provides an imperative alternative to the declarative
approach of checking the validate input and output checkboxes on a
service's Input/Output signature.

#### Inputs:

* `$service.signature.direction` determines whether to validate the
  pipeline against the input or output signature of the current
  service.
* `$validation.raise?` is an optional boolean which when `true` will
  throw an exception if validation fails.

#### Outputs:

* `$validation.result?` is `true` if the pipeline was valid, or
  `false` if the pipeline was invalid.
* `$validation.message` is an optional message describing why the
  pipeline was invalid.
* `$validation.errors` is an optional list of errors for each reason
  why the pipeline was invalid.

---

### tundra.sap.idoc:identify

Assigns a `DOCNUM` to each `IDocDocument` object equal to its index in
the given in the given `IDocDocumentList` container.

This service can be used to work around an issue with the hard-coded
9,999 size limit that the SAP Adapter WmSAP package has when it
auto-assigns `DOCNUM`s.

#### Inputs:

* `$idoclist` is an `IDocDocumentList` object containing `IDocDocument`
  objects to assign `DOCNUM`s to.

#### Outputs:

* `$idoclist` is the given `IDocDocumentList` object where each
  `IDocDocument` item has been assigned a `DOCNUM` equal to its
  respective list index.

---

### tundra.sap.idoc:length

Returns the number of `IDocDocument` items in the given
`IDocDocumentList` container.

#### Inputs:

* `$idoclist` is an `IDocDocumentList` object containing zero or more
  `IDocDocument` objects.

#### Outputs:

* `$idoclist.length` is the number of `IDocDocument` items in the given
  `$idoclist`.

---

### tundra.sap.idoc:partition

Partitions the given `IDocDocumentList` into an `IDocDocumentList[]`,
where each array item contains a maximum of the given limit number
of `IDocDocument` objects.

This service can be used to split a batch of `IDocDocument` objects
contained in an `IDocDocumentList` into smaller batches.

#### Inputs:

* `$idoclist` is an `IDocDocumentList` object to be partitioned.
* `$limit` is the maximum number of `IDocDocument` items per
  `IDocDocumentList` container returned.

#### Outputs:

* `$idoclists` is a `IDocDocumentList[]` containing all the `IDocDocument`
  objects in the given `$idoclist`, partitioned in order across
  `(($idoclist.length / $limit) + 1)` `IDocDocumentList` containers.
* `$idoclists.length` is the number of items in the returned
  `$idoclists` `IDocDocumentList[]` array.

---

### tundra.schedule.create:list

Schedules the given list of services for execution once or
periodically.

#### Inputs:

* `$schedule.create.list` is an `IData[]` document list representing
  the tasks to be scheduled. Refer to `Tundra/tundra.schedule:create`
  service comments for further details on input parameters.

#### Outputs:

* `$schedule.id.list` is an `IData[]` document list containing the
  unique identities of the created schedule tasks.

---

### tundra.schedule.create:pivot

Schedules the given list of services for execution once or
periodically.

#### Inputs:

* `$schedule.create.pivot` is an `IData` document containing one child
  `IData` document per scheduled task to be created conforming to the
  `Tundra/tundra.schedule:create` input parameter `$schedule.create`
  data structure.

#### Outputs:

* `$schedule.id.pivot` is an `IData` document containing the unique
  identities of the created schedule tasks.

---

### tundra.schedule:create

Schedules a service for execution once or periodically.

The resulting scheduled task ID is added to the input pipeline
of the scheduled service as a variable named `$schedule.id`,
enabling the service itself to query its owning scheduled task.
This can be useful if, for example, the service implements an
[event loop] and should exit that loop if its owning schedule
is paused or cancelled, which can be determined using the service
`Tundra/tundra.schedule:runnable`.

#### Inputs:

* `$schedule.create` is an `IData` document representing the task to
  be scheduled.
  * `name` is a mandatory unique identity for this scheduled task,
    provided by the creator of the task. This value will be added to
    the input pipeline of the resulting scheduled task as
    `$schedule.name`, and provides easy identification for the creator
    of instances of this task post-creation. Note that only one task
    with the given `name` will ever be scheduled by this service, all
    other instances if they exist will be first removed prior to
    scheduling.
  * `type` describes the type of schedule to be created, and is
    a choice of the following:
    * `once` will schedule the service to execute one time only
      at the given `start` datetime.
    * `repeat` will schedule the service to execute periodically
      at the given `repeat/interval` of time. The default if `type` is
      not specified.
    * `complex` will schedule the service to execute periodically
      on the given minutes, hours, week days, days of month, and
      months of the year.
  * `service` is the fully-qualified name of the service to be
    scheduled for execution.
  * `description` is an optional description of the scheduled task.
  * `target` optionally identifies the server or servers to execute
    the service on. Either specify a specific `hostname:port` to
    execute the service on, or one of the following values:
    * `$any` executes the task on any server in the cluster. Choose
      this option to ensure the service is only executed once at
      any one time, but can be executed by any server in the cluster.
      This is the default when no target is specified.
    * `$all` execute the task on all servers in the cluster. Choose
      this option when the service needs to be executed on every
      cluster node at the same time to perform, for example, clean
      up or house keeping on every server. If this target is
      chosen for a server not in a cluster, it is equivalent to
      choosing `$self`.
    * `$self` executes the task on the same server used to create
      the task.
  * `user` is the optional user account under which the service will
    be executed. Defaults to the built-in `Administrator` user if not
    specified.
  * `start` is the optional datetime from which the scheduled task
    will be in effect or active. If not specified, defaults to the
    current datetime. For `once` only tasks, this is the datetime
    the service will be executed once and only once.
  * `start.delay` is an optional XML duration added to the `start`
    datetime to calculate the actual start datetime of the scheduled
    task. For example, if no `start` datetime is specified, then a
    `start.delay` of `PT5M` will start the task 5 minutes after it
    is scheduled. If not specified, no start delay is applied.
  * `end` is the optional datetime after which the scheduled task
    will no longer be in effect (expires). If not specified, the
    schedule will never expire. Not applicable for `once` scheduled
    tasks.
  * `overlap` is an optional `java.lang.Boolean` determining how to
    handle when one execution of the scheduled task overlaps the next
    scheduled execution, for example when the execution duration
    exceeds the schedule interval. When `true`, the next scheduled
    execution of the service will execute as per the schedule, even if
    the previous schedule is still executing, which can result in
    multiple concurrent executions of the service. When `false`, the
    schedule will never be executed concurrently. For a repeat
    schedule, the next scheduled time is calculated from the end of
    execution of the previous schedule. For a complex schedule, any
    scheduled times that occur while the previous schedule is
    executing are skipped, and the service will execute at the next
    uncontested scheduled time. Defaults to `false`.
  * `lateness` is an optional `IData` document containing arguments
    for how to determine when, and then handle if, a schedule is late.
    * `duration` is an optional duration of time which when lapsed
      passed the scheduled time determines that the task is considered
      late. If not specified, defaults to 0 seconds (in other words a
      task is considered late if it doesn't execute exactly at its
      scheduled time).
    * `action` is an optional choice which determines what happens to
      the task when its determined to be late.
      * `run immediately` - the default if `action` is not specified
      * `skip and run at next scheduled interval`
      * `suspend`
  * `repeat` is an optional `IData` document containing arguments only
    applicable when the schedule type is `repeat`.
    * `interval` is an optional [XML] duration determining how often
      the scheduled task will execute. If not specified, defaults to
      `PT60S` (60 seconds).
  * `complex` is an optional `IData` document containing arguments
    only applicable when the schedule type is `complex`.
    * `months` is an optional list of months of the year, provided as
      an integer between 1 (January) and 12 (December), the schedule
      should execute in. If not specified, the schedule will execute
      in all months of the year.
    * `days` is an optional list of days of the month, provided as an
      integer between 1 and 31, the schedule should execute on. If
      not specified, the schedule will execute on all days in the
      month.
    * `weekdays` is an optional list of days of the week, provided as
      an integer between 1 (Sunday) and 7 (Saturday), the schedule
      should execute on. If not specified, the schedule will execute
      on all days of the week.
    * `hours` is an optional list of hours of the day, provided as an
      integer between 0 and 23, the schedule should execute on. If
      not specified, the schedule will execute every hour.
    * `minutes` is an optional list of minutes of the hour, provided
      as an integer between 0 and 59, the schedule should execute on.
      If not specified, the schedule will execute every minute.
  * `pipeline` is an optional `IData` document containing the input
    arguments used as the input pipeline when executing the service.
  * `enabled` is a mandatory `java.lang.Boolean` which when `true`
    indicates that the scheduled task should be created in an active
    state, and when `false` indicates that the scheduled task should
    be created in a suspended state.

#### Outputs:

* `$schedule.id` is the unique identity of the created scheduled task.

---

### tundra.schedule:exists

Returns `true` if a scheduled task with the given identity or name
exists in the task scheduler on this Integration Server.

#### Inputs:

* `$id` is the internal identity assigned by Integration Server to
  the task when it was created.  When specified, other input
  arguments are ignored.
* `$name` is an optional name given to the scheduled task by the
  creator, which is stored in the input pipeline of the task by
  `tundra.schedule:create` as `$schedule.name`.

#### Outputs:

* `$exists?` is a boolean indicating if a scheduled task identified by
  the given `$id` or `$name` (in order or precedence) exists in the task
  scheduler of this Integration Server.

---

### tundra.schedule:expedite

Expedites the scheduled task with the given identity to run
immediately rather than wait for its next scheduled time.

#### Inputs:

* `$schedule.id` is the identity of a scheduled task to be expedited.

---

### tundra.schedule:get

Returns the details of the scheduled task identified by the given
identity or name.

#### Inputs:

* `$schedule.id` is the internal identity assigned by Integration
  Server to the task when it was created. When specified, other input
  arguments are ignored.
* `$schedule.name` is an optional name given to the scheduled task by
  the creator, which is stored in the input pipeline of the task by
  `Tundra/tundra.schedule:create` as `$schedule.name`.

#### Outputs:

* `$schedule` is an `IData` document containing the details of the
  scheduled task identified by the given `$id`, if it exists.
  * `id` is the identifying string for this scheduled task.
  * `name` is the name assigned to this scheduled task by the creator.
  * `description` is an optional description of the scheduled task.
  * `type` is the type of schedule, a choice of one of the following:
    * `once` is a schedule that executes one time only at the
      specified start datetime.
    * `repeat` is a schedule that executes periodically at the
      specified `repeat/interval` of time.
    * `complex` is a schedule that executes periodically on the given
      minutes, hours, week days, days of month, and months of the
      year.
  * `service` is the fully-qualified name of the service executed by
    the schedule.
  * `package` is the name of the package the scheduled `service` is a
    member of.
  * `target` identifies the server or servers the schedule executes
    the service on, provided either as a specific `hostname:port`, or
    one of the following values:
    * `$any` executes the task on any server in the cluster. This
      option ensures the service is only executed once at any one
      time, but can be executed by any server in the cluster.
    * `$all` executes the task on all servers in the cluster. This
      option executes the service on every cluster node at the
      same time to perform, for example, clean up or house keeping
      on every server.
  * `target.logical` is equal to the value of `target` except when
    `target` is equal to the local scheduler node name in which
    case it is set to the value `$self`.
  * `user` is the user account under which the service is executed.
  * `start` is the optional datetime from which the scheduled task
    is in effect or active. For `once` only tasks, this is the
    datetime the service will be executed once and only once.
  * `end` is the optional datetime after which the scheduled task
    is no longer be in effect (expires). If not specified, the
    schedule never expires.
  * `overlap?` is an boolean determining how to handle when one
    execution of the scheduled task overlaps the next scheduled
    execution, for example when the execution duration exceeds the
    schedule interval.

    When `true`, the next scheduled execution of the service
    executes as per the schedule, even if the previous schedule is
    still executing, which can result in multiple concurrent
    executions of the service.

    When `false`, the schedule is never executed concurrently.
    For a repeat schedule, the next scheduled time is calculated
    from the end of execution of the previous schedule. For a complex
    schedule, any scheduled times that occur while the previous
    schedule is executing are skipped, and the service will execute
    at the next uncontested scheduled time.
  * `lateness` is an optional `IData` document containing arguments
    for how to determine when, and then handle if, a schedule is late.
    * `duration` is the duration of time which when lapsed passed the
      scheduled time determines that the task is considered late.
    * `action` determines what happens to the task when its determined
      to be late, and is a choice of one of the following:
      * `run immediately`
      * `skip and run at next scheduled interval`
      * `suspend`
  * `repeat` is an optional `IData` document containing arguments only
    applicable when the schedule type is `repeat`.
    * `interval` is the duration of time that determines how often
      the scheduled task will execute.
  * `complex` is an optional `IData` document containing arguments
    only applicable when the schedule type is `complex`.
    * `months` is an optional list of months of the year, provided as
      an integer between 1 (January) and 12 (December), the schedule
      executes in. If not specified, the schedule executes in all
      months of the year.
    * `days` is an optional list of days of the month, provided as an
      integer between 1 and 31, the schedule executes on. If not
      specified, the schedule executes on all days in the month.
    * `weekdays` is an optional list of days of the week, provided as
      an integer between 1 (Sunday) and 7 (Saturday), the schedule
      executes on. If not specified, the schedule executes on all
      days of the week.
    * `hours` is an optional list of hours of the day, provided as an
      integer between 0 and 23, the schedule executes on. If not
      specified, the schedule executes every hour.
    * `minutes` is an optional list of minutes of the hour, provided
      as an integer between 0 and 59, the schedule executes on. If
      not specified, the schedule executes every minute.
  * `pipeline` is an optional `IData` document containing the input
    arguments used as the input pipeline when executing the service.
  * `status` is the current status of the scheduled task, and is a
    choice of one of the following:
    * `cancelled`
    * `running`
    * `suspended`
    * `waiting`
  * `next` is the datetime of the next scheduled execution of the
    service, if applicable.

---

### tundra.schedule:invoke

Invokes a given scheduled task's service with the task's configured
input pipeline of the under the invoking user's context.

This service can be used to manually run a scheduled service outside
of its normal schedule, with the convenience of not having to supply
the task's configured inputs.

Note the scheduled task's schedule is unaffected by the use of this
service.

#### Inputs:

* `$schedule.id` is the identity of the scheduled task whose service
  is to be invoked with the input pipeline specified on the task.

---

### tundra.schedule:list

Returns a list of all scheduled tasks that satisfy the given inputs,
or every task if no inputs were specified.

#### Inputs:

* `$schedule.name` is an optional name given to the scheduled task by
  the creator, which is stored in the input pipeline of the task by
  `Tundra/tundra.schedule:create` as `$schedule.name`, which if
  specified will filter the returned list to only be scheduled tasks
  with this name.
* `$schedule.service` is an optional fully-qualified service name
  which, if specified, will filter the returned list to only be
  scheduled tasks that execute this service.
* `$schedule.filter` is an optional `Tundra/tundra.condition:evaluate`
  conditional statement, used to filter the list of scheduled tasks
  returned. The conditional statement is evaluated against the
  pipeline, which includes the task being evaluated as an `IData`
  document named `$schedule` with the same structure as returned by
  `Tundra/tundra.schedule:get`.

  Examples of some filter strings are as follows:

  1. Return a list of only the complex scheduled tasks:

     `%$schedule/type%=="complex"`

  2. Return a list of only the scheduled tasks that execute
     `pub.string:concat`:

     `%$schedule/service%=="pub.string:concat"`

  3. Return a list of only the scheduled tasks that are suspended for
     a service whose name is stored in the pipeline variable `svc`:

     `%$schedule/status%=="suspended" and %$schedule/service%==%svc%`

  Refer to the `Tundra/tundra.condition:evaluate` service
  documentation for the format of conditional statements.

#### Outputs:

* `$schedules` is a `IData[]` document list containing the details of
  all the scheduled tasks that satisfy the given inputs, or every
  scheduled task known to the task scheduler on this Integration
  Server if no inputs were specified.
  * `id` is the identifying string for this scheduled task.
  * `name` is the name assigned to this scheduled task by the creator.
  * `description` is an optional description of the scheduled task.
  * `type` is the type of schedule, a choice of one of the following:
    * `once` is a schedule that executes one time only at the
      specified start datetime.
    * `repeat` is a schedule that executes periodically at the
      specified `repeat/interval` of time.
    * `complex` is a schedule that executes periodically on the given
      minutes, hours, week days, days of month, and months of the
      year.
  * `service` is the fully-qualified name of the service executed by
    the schedule.
  * `package` is the name of the package the scheduled `service` is a
    member of.
  * `target` identifies the server or servers the schedule executes
    the service on, provided either as a specific hostname:port, or
    one of the following values:
    * `$any` executes the task on any server in the cluster. This
      option ensures the service is only executed once at any one
      time, but can be executed by any server in the cluster.
    * `$all` executes the task on all servers in the cluster. This
      option executes the service on every cluster node at the
      same time to perform, for example, clean up or house keeping
      on every server.
  * `target.logical` is equal to the value of `target` except when
    `target` is equal to the local scheduler node name in which
    case it is set to the value `$self`.
  * `user` is the user account under which the service is executed.
  * `start` is the optional datetime from which the scheduled task
    is in effect or active. For `once` only tasks, this is the
    datetime the service will be executed once and only once.
  * `end` is the optional datetime after which the scheduled task
    is no longer be in effect (expires). If not specified, the
    schedule never expires.
  * `overlap?` is an boolean determining how to handle when one
    execution of the scheduled task overlaps the next scheduled
    execution, for example when the execution duration exceeds the
    schedule interval.

    When `true`, the next scheduled execution of the service
    executes as per the schedule, even if the previous schedule is
    still executing, which can result in multiple concurrent
    executions of the service.

    When `false`, the schedule is never executed concurrently.
    For a repeat schedule, the next scheduled time is calculated
    from the end of execution of the previous schedule. For a complex
    schedule, any scheduled times that occur while the previous
    schedule is executing are skipped, and the service will execute
    at the next uncontested scheduled time.
  * `lateness` is an optional `IData` document containing arguments
    for how to determine when, and then handle if, a schedule is late.
    * `duration` is the duration of time which when lapsed passed the
      scheduled time determines that the task is considered late.
    * `action` determines what happens to the task when its determined
      to be late, and is a choice of one of the following:
      * `run immediately`
      * `skip and run at next scheduled interval`
      * `suspend`
  * `repeat` is an optional `IData` document containing arguments only
    applicable when the schedule type is `repeat`.
    * `interval` is the duration of time that determines how often
      the scheduled task will execute.
  * `complex` is an optional `IData` document containing arguments
    only applicable when the schedule type is `complex`.
    * `months` is an optional list of months of the year, provided as
      an integer between 1 (January) and 12 (December), the schedule
      executes in. If not specified, the schedule executes in all
      months of the year.
    * `days` is an optional list of days of the month, provided as an
      integer between 1 and 31, the schedule executes on. If not
      specified, the schedule executes on all days in the month.
    * `weekdays` is an optional list of days of the week, provided as
      an integer between 1 (Sunday) and 7 (Saturday), the schedule
      executes on. If not specified, the schedule executes on all
      days of the week.
    * `hours` is an optional list of hours of the day, provided as an
      integer between 0 and 23, the schedule executes on. If not
      specified, the schedule executes every hour.
    * `minutes` is an optional list of minutes of the hour, provided
      as an integer between 0 and 59, the schedule executes on. If
      not specified, the schedule executes every minute.
  * `pipeline` is an optional `IData` document containing the input
    arguments used as the input pipeline when executing the service.
  * `status` is the current status of the scheduled task, and is a
    choice of one of the following:
    * `cancelled`
    * `running`
    * `suspended`
    * `waiting`
  * `next` is the datetime of the next scheduled execution of the
    service, if applicable.

---

### tundra.schedule:remove

Deletes one or more scheduled tasks identified by the given identity,
or name, service, and filter condition.

#### Inputs:

* `$id` is the internal identity assigned by Integration Server to
  the task when it was created. When specified, other input
  arguments are ignored.
* `$name` is an optional name given to the scheduled task by the
  creator, which is stored in the input pipeline of the task by
  `tundra.schedule:create` as `$schedule.name`.
* `$service` is an optional fully-qualified service name which, if
  specified, will filter the scheduled tasks to be removed to only
  those that execute this service.
* `$filter` is an optional `tundra.condition:evaluate` conditional
  statement. All scheduled tasks which satisfy the given filter
  condition will be deleted. The conditional statement is evaluated
  against the pipeline, which includes the task being evaluated as an
  `IData` document named `$schedule` with the same structure as returned
  by `tundra.schedule:get`.

  Examples of some filter strings are as follows:

  1. Delete all complex scheduled tasks:

     `%$schedule/type% == "complex"`

  2. Delete all scheduled tasks that execute `pub.string:concat`:

     `%$schedule/service% == "pub.string:concat"`

  3. Delete only the scheduled tasks that are suspended for a service
     whose name is stored in the pipeline variable `svc`:

     `%$schedule/status% == "suspended" and %$schedule/service% == %svc%`

  Refer to the `tundra.condition:evaluate` service documentation for the
  format of conditional statements.

---

### tundra.schedule:resume

Resumes the execution of one or more paused scheduled tasks
identified by the given identity, or name, service, and filter
condition.

Suspended tasks do not execute for the period they are suspended.
Previously suspended tasks that are resumed will begin executing
again according to their schedule.

Attempting to resume tasks which are not suspended has no effect on
the task, and results in no exception being thrown.

#### Inputs:

* `$schedule.id` is the internal identity assigned by Integration
  Server to the task when it was created. When specified, other input
  arguments are ignored.
* `$schedule.name` is an optional name given to the scheduled task by
  the creator, which is stored in the input pipeline of the task by
  `Tundra/tundra.schedule:create` as `$schedule.name`.
* `$schedule.service` is an optional fully-qualified service name
  which, if specified, will filter the scheduled tasks to be removed
  to only those that execute this service.
* `$schedule.filter` is an optional `Tundra/tundra.condition:evaluate`
  conditional statement. All scheduled tasks which satisfy the given
  filter condition will be resumed. The conditional statement is
  evaluated against the pipeline, which includes the task being
  evaluated as an `IData` document named `$schedule` with the same
  structure as returned by `Tundra/tundra.schedule:get`.

  Examples of some filter strings are as follows:

  1. Resume all complex scheduled tasks:

     `%$schedule/type% == "complex"`

  2. Resume all scheduled tasks that execute `pub.string:concat`:

     `%$schedule/service% == "pub.string:concat"`

  3. Resume only the scheduled tasks that are suspended for a service
     whose name is stored in the pipeline variable `svc`:

     `%$schedule/status% == "suspended" and %$schedule/service% == %svc%`

  Refer to the `Tundra/tundra.condition:evaluate` service
  documentation for the format of conditional statements.

---

### tundra.schedule:runnable

Returns `true` if a scheduled task with the given identity is runnable
on this server.

Use this service to check if a long-running scheduled task should
run or continue to run, or if it should exit due to the task being
suspended or deleted, or the task scheduler on this server be paused
or stopped.

#### Inputs:

* `$schedule.id` is the optional identity of a scheduled task. If not
  specified, only the current state of the task scheduler on the
  server is taken into account to determine if a task is runnable.

#### Outputs:

* `$schedule.runnable?` is a boolean indicating if this scheduled task
  should run or continue to run on this server. A scheduled task is
  considered runnable if it has a ready or running state, and if the
  task scheduler on this server is running and not paused.

---

### tundra.schedule:suspend

Suspends the execution of one or more scheduled tasks identified by
the given identity, or name, service, and filter condition.

Suspended tasks do not execute for the period they are suspended. To
start executing a suspended task according to its schedule, it
should be resumed using `tundra.schedule:resume`.

Attempting to suspend tasks that are already suspended has no effect
on the task, and results in no exception being thrown.

#### Inputs:

* `$id` is the internal identity assigned by Integration Server to
  the task when it was created. When specified, other input
  arguments are ignored.
* `$name` is an optional name given to the scheduled task by the
  creator, which is stored in the input pipeline of the task by
  `tundra.schedule:create` as `$schedule.name`.
* `$service` is an optional fully-qualified service name which, if
  specified, will filter the scheduled tasks to be removed to only
  those that execute this service.
* `$filter` is an optional `tundra.condition:evaluate` conditional
  statement. All scheduled tasks which satisfy the given filter
  condition will be suspended. The conditional statement is evaluated
  against the pipeline, which includes the task being evaluated as an
  `IData` document named `$schedule` with the same structure as returned
  by `tundra.schedule:get`.

  Examples of some filter strings are as follows:

  1. Suspend all complex scheduled tasks:

     `%$schedule/type% == "complex"`

  2. Suspend all scheduled tasks that execute `pub.string:concat`:

     `%$schedule/service% == "pub.string:concat"`

  3. Suspend only the scheduled tasks that are not already suspended
     for a service whose name is stored in the pipeline variable `svc`:

     `%$schedule/status% != "suspended" and %$schedule/service% == %svc%`

  Refer to the `tundra.condition:evaluate` service documentation for the
  format of conditional statements.

---

### tundra.scheduler:pause

Pauses the Integration Server task scheduler on the server on which
this service is invoked. No user tasks will be executed on this
server by the task scheduler until the scheduler is resumed again.
If already paused, this service has no effect.

On Integration Server 7.1, this service stops rather than pauses
the scheduler, as the pause/resume function is not supported on this
version.

---

### tundra.scheduler:restart

Stops and then starts the Integration Server task scheduler on
the server on which this service is invoked. If already stopped,
this service will start the scheduler. This service can be used
to fix the task scheduler if it gets stuck.

---

### tundra.scheduler:resume

Resumes the Integration Server task scheduler when paused on the
server on which this service is invoked if it was paused. If already
started, this service has no effect.

On Integration Server 7.1, this service starts rather than resumes
the scheduler, as the pause/resume function is not supported on this
version.

---

### tundra.scheduler:self

Returns the Integration Server user task scheduler node name for the
Integration Server on which this service is run.

#### Outputs:

* `$scheduler.self.name` is the node name of this instance of the
  Integration Server user task scheduler. This can be used to target
  a schedule to this specific server.

---

### tundra.scheduler:start

Starts the Integration Server task scheduler on the server on
which this service is invoked. If already started, this service
has no effect.

---

### tundra.scheduler:status

Returns the current status of the Integration Server task scheduler
on the server on which this service is invoked.

#### Outputs:

* $scheduler.self.status is the current status of the scheduler as
  an enumeration with the following possible states: `STARTED`,
  `PAUSED`, `STOPPED`.

---

### tundra.scheduler:stop

Stops the Integration Server task scheduler on the server on
which this service is invoked. No user tasks will be executed
on this server by the task scheduler until the scheduler is
started again. If already stopped, this service has no effect.

---

### tundra.schema.content.deliver:handler

Content delivery protocol handling services used by
`tundra.content:deliver` must implement this specification.

#### Inputs:

* `$content` is a string, byte array, or input stream containing data
  to be delivered to the `$destination` URI.
* `$content.type` is an optional MIME media type describing the type
  content being delivered.
* `$content.encoding` is an optional character set to use when
  `$content` is provided as a string to encode the text data upon
  delivery. Defaults to [UTF-8].
* `$destination` is a parsed URI identifying the location where the
  given `$content` should be delivered.

#### Outputs:

* `$message` is an optional response message, useful for logging,
  that may be returned by specific delivery protocols.
* `$response` is an optional response content returned by the
  delivery (for example, the HTTP response body).
* `$response.type` is an optional MIME media type describing the type
  of `$response` returned.
* `$response.context` is an optional document containing additional
  context for the returned response. For example, an HTTP delivery
  could return the response status and headers to provide additional
  context to the delivery result.

---

### tundra.schema.content.retrieve:handler

Content retrieval protocol handling services used by
`tundra.content:retrieve` must implement this specification.

#### Inputs:

* `$source` is the source URI identifying the location and names of the
  content to be retrieved.
* `$service` is the content processing service that is called for each
  item of content retrieved from `$source`. This service is required to
  implement the `tundra.schema.content.retrieve:processor` specification.
* `$service.input` is the optional variable name for the retrieved
  content in the input pipeline of `$service`. Defaults to `$content` if
  not specified.
* `$limit` is an optional maximum number of content items to be
  retrieved from `$source` per retrieval.

#### Outputs:

* `$message` is an optional diagnostic message describing the results of
  the retrieval.
* `$exceptions` is an optional array of `java.lang.Throwable` exception
  objects that were thrown while processing the retrieved contents.

---

### tundra.schema.content.retrieve:processor

Content processing services used by `tundra.content:retrieve` must
implement this specification.

#### Inputs:

* `$content` is the content to be processed, specified as a
  [java.io.InputStream].
* `$content.name` is the name associated with the content, such as a
  file name.
* `$content.type` is the mime media type of the content to be
  processed.
* `$content.length` is the length in bytes of the content to be
  processed.
* `$content.source` is the source of the content to be processed.
* `$content.archive` is the archive location of the content after
  processing.
* `$content.created` is an optional datetime the content was created.
* `$content.updated` is an optional datetime the content was updated.

---

### tundra.schema.exception:handler

Exception handling `$catch` services called by `tundra.service:ensure` can
implement this specification.

#### Inputs:

* `$exception` is the [java.lang.Throwable] object that was caught by
  this handler to be handled.
* `$exception?` is a boolean flag indicating if an exception was thrown.
* `$exception.class` is the Java class name of the caught exception
  object.
* `$exception.message` is the exception message describing the error
  that has occurred.
* `$exception.service` is the fully qualified name of the service that threw
  the exception.
* `$exception.package` is the package that `$exception.service` resides in,
  specified only if `$exception.service` exists.
* `$exception.info` is the exception information returned by
  `WmPublic/pub.flow:getLastError`.
* `$exception.stack` is the Java call stack describing where the error
  occurred.

---

### tundra.schema.http.response:handler

Specifies the required inputs and outputs for an [HTTP] response handling
service called by `tundra.http:client`.

#### Inputs:

* `$response` is the [HTTP] response to be processed by this service.

#### Outputs:

* `$response` is the processed [HTTP] response. How the response is
  processed is at the discretion of the implementing service. Refer to
  the standard `tundra.http.response:handle` service for a reference
  implementation.

---

### tundra.schema.message:format

Defines the structure required of a message format to be
registered with and evaluated by `tundra.message:receive` via
`tundra.message.format:recognize`.

Message formats are registered by including the following
configuration section in a `tundra.configuration:get` compatible
package configuration file:

    "tundra/message/format": [
        {
            "name": "Example 1",
            "description": "An example message format",
            "type": "text/xml",
            "namespace": {
                "cn": "http://example.com/common",
                "sh": "http://example.com/shared"
            },
            "recognize": {
                "ref": "%node/example/head/id% != $null"
            },
            "parse": {
                "ref": "schema.example:document"
            },
            "validate": {
                "ref": "schema.example:document"
            },
            "route": {
                "type": "publish",
                "ref": "schema.example:document"
            },
            "enabled": true
        }
    ]

#### Structure:

* `name` is the unique name for the message format. Note that message
  formats are evaluated in ascending lexical name order.
* `description` is an optional description of the message format.
* `type` is an optional MIME content type of the message format.
* `namespace` is an optional list of XML namespace prefixes and URIs,
  used when evaluating XPath queries and parsing.
* `recognize`
  * `ref` is a `tundra.condition:evaluate` compatible conditional
    statement which is evaluated against the pipeline to determine
    if the received message is of this format.
* `parse`
  * `ref` is the document reference or flat file schema to be used to
    parse and emit content in this message format.
* `validate`
  * `ref` is the document reference or schema reference to be used to
    validate content in this message format.
* `route`
  * `type` is the type of routing to be performed, and is a choice
    of:
    * `publish` routes the message to the webMethods messaging
      subsystem via `pub.publish:publish` using the `documentTypeName`
      specified in `ref`.
    * `jms` routes the message to a JMS destination specified in `ref`.
    * `service` routes the message via a direct service invocation of
      the service specified in `ref`.
  * `ref` is the logical destination for the type of route specified.
* `enabled` is an optional boolean which when `true` enables this
  message format for recognition by `tundra.message.format:recognize`.
  Defaults to `true`, if not specified.

---

### tundra.security.acl:create

Creates an Integration Server ACL with the given name and group
membership.

#### Inputs:

* `$acl.name` is the name of the ACL to be created.
* `$groups.allowed` is an optional list of group names which will be
  added as members to the ACL's allowed groups.
* `$groups.denied` is an optional list of group names which will be
  added as members to the ACL's denied groups.
* `$force?` is an optional boolean which when `true` will replace the
  existing ACL with the given name with a new ACL with the same name
  and the given group membership. If `false`, and an ACL already
  exists with the given name, no changes to the ACL will be made by
  this service. Defaults to `false`.

---

### tundra.security.content:digest

Returns a message digest for the given content using the given
algorithm. A message digest is a secure one-way fixed-length hash
of arbitrary data.

#### Inputs:

* `$content` is arbitrary data provided as a string, byte array, or
  input stream.
* `$content.encoding` is the character set used to encode the
  character data if `$content` is provided as a string. Defaults to
  `UTF-8`.
* `$digest.algorithm` is the name of the hashing algorithm to use
  when calculating the message digest. Defaults to `SHA-512`.
* `$digest.mode` is a choice of `stream`, `bytes`, `base64`, or
  `hex`, and determines how the calculated digest is returned.
   Defaults to `stream`.

#### Outputs:

* `$content` is the given content which was used to calculate the
  digest, and can now be further processed. The reason this is
  returned is that when an input stream is provided calculating
  the message digest necessarily requires consuming the stream,
  therefore a new input stream is instead returned, positioned at
  the start of the data stream and ready for further consumption.
  This allows a message digest to be calculated in advance of normal
  processing of an input stream.
* `$digest` is the calculated message digest for the given file's
  contents, returned either as a byte array, input stream,
  base64-encoded or hex-encoded string, depending on the
  `$digest.mode` specified.

---

### tundra.security.hmac:authenticate

Authenticates the given [HMAC] authentication code against the given
content, secret key, and algorithm. If the authentication code is
invalid, an exception is thrown indicating the authenticity and
integrity of the given content failed verification and is not trusted.

#### Inputs:

* `$content` is arbitrary data provided as a string, byte array, or
  input stream.
* `$content.encoding` is the character set used to encode the
  character data if `$content` is provided as a string. Defaults to
  `UTF-8`.
* `$content.authentication.key` is the secret key used to calculate
  the authentication code.
* `$content.authentication.algorithm` is the name of the algorithm to
  use when calculating the message digest.
* `$content.authentication.code` is the [HMAC] authentication code to
  be authenticated.

#### Outputs:

* `$content` is the provided content, which is returned because if it
  was provided as an input stream then that stream was necessarily
  consumed to calculate the authentication code, and in that case a
  reset copy of the given stream will be returned so it can be
  consumed again for processing.

---

### tundra.security.hmac:calculate

Calculates the [HMAC] authentication code for the given content.

#### Inputs:

* `$content` is arbitrary data provided as a string, byte array, or
  input stream.
* `$content.encoding` is the character set used to encode the
  character data if `$content` is provided as a string. Defaults to
  `UTF-8`.
* `$content.authentication.key` is the secret key used to calculate
  the authentication code.
* `$content.authentication.algorithm` is the name of the algorithm to
  use when calculating the message digest.
* `$content.authentication.code.mode` is a choice of `stream`,
  `bytes`, or `hex`, and determines how the calculated authentication
  code is returned. Defaults to `stream`.

#### Outputs:

* `$content` is the provided content, which is returned because if it
  was provided as an input stream then that stream was necessarily
  consumed to calculate the authentication code, and in that case a
  reset copy of the given stream will be returned so it can be
  consumed again for processing.
* `$content.authentication.code` is the calculated [HMAC]
  authentication code for the given `$content`.

---

### tundra.security.hmac:verify

Verifies whether the given [HMAC] authentication code is valid for
the given content, secret key, and algorithm.

#### Inputs:

* `$content` is arbitrary data provided as a string, byte array, or
  input stream.
* `$content.encoding` is the character set used to encode the
  character data if `$content` is provided as a string. Defaults to
  `UTF-8`.
* `$content.authentication.key` is the secret key used to calculate
  the authentication code.
* `$content.authentication.algorithm` is the name of the algorithm to
  use when calculating the message digest.
* `$content.authentication.code` is the [HMAC] authentication code to
  be verified.

#### Outputs:

* `$content` is the provided content, which is returned because if it
  was provided as an input stream then that stream was necessarily
  consumed to calculate the authentication code, and in that case a
  reset copy of the given stream will be returned so it can be
  consumed again for processing.
* `$content.authentication.verified?` is `true` if the given
  authentication code was verified as correct, or `false` otherwise.
  If `false`, the authenticity and integrity of the given content
  failed verification and should not be trusted.

---

### tundra.service:benchmark

Benchmarks the performance of the given service by invoking it the
given number of times, and returning the average and standard
deviation execution durations.

#### Inputs:

* `$service` is the fully-qualified name of the service to be
  benchmarked.
* `$pipeline` is an optional `IData` document which, if specified,
  contains the input arguments for the invocation of `$service`; in
  other words, the invocation is scoped to this `IData` document. If
  not specified, the invocation is unscoped, and hence the service
  will operate directly against the pipeline itself.
* `$count` is the number of times `$service` will be invoked, and must
  be greater than or equal to 1. The more times `$service` is
  invoked, the more reliable the resulting statistics will be (in
  other words, the more samples the better).
* `$raise?` is an optional boolean flag which when `true` will halt the
  benchmark immediately upon encountering the first exception thrown
  by `$service`, and rethrows it. Defaults to `false`.

#### Outputs:

* `$benchmark.results` is the results from measuring the execution of
  the given service the given number of times.
  * `description` is a diagnostic message describing the execution
    statistics of the benchmarked service, which can be used for
    logging the results of the benchmark:

        subject = tundra.service:nothing, average = 0.000052816 seconds, standard deviation = 0.000067587 seconds, minimum = 0.000024920 seconds, maximum = 0.000498029 seconds, cumulative = 0.005281598 seconds, successes = 100, failures = 0, total = 100

  * `subject` is the name of the service that was executed.
  * `unit` is the duration unit of measure, which is seconds.
  * `minimum` is the minimum duration sampled when executing the given
    service.
  * `minimum.formatted` is the minimum duration sampled when executing
    the given service formatted as a string.
  * `average` is the calculated mean or average duration of execution
    of the given service.
  * `average.formatted` is the calculated mean or average duration of
    execution of the given service as a string.
  * `deviation.standard` is the calculated standard deviation duration
    of execution of the given service.
  * `deviation.standard.formatted` is the calculated standard
    deviation duration
  * `maximum` is the maximum duration sampled when executing the given
    service.
  * `maximum.formatted`  is the maximum duration sampled when
    executing the given service formatted as a string.
  * `cumulative` is the total duration for all executions of the given
    service.
  * `cumulative.formatted` is the total duration for all executions of
    the given service formatted as a string.
  * `count.successes` is the number of times the given service was
    executed successfully.
  * `count.successes.formatted` is the number of times the given
    service was executed successfully formatted as a string.
  * `count.failures` is the number of times the given service failed
    during execution.
  * `count.failures.formatted` is the number of times the given
    service failed during execution formatted as a string.
  * `count.total` is the total number of times the given service was
    executed
  * `count.total.formatted` is the total number of times the given
    service was executed formatted as a string.
---

### tundra.service:callstack

Returns the current call stack.

#### Outputs:

* `$callstack` is a list of fully-qualified service names in
  invocation order with the top-level service as the first item.
* `$callers` is a string representation of the call stack, which is
  useful for diagnostics/logging.
* `$caller` is the fully-qualified service name of the immediate
  caller of the service which invoked `tundra.service:callstack`,
  which is useful for obtaining the name of the service which called
  the currently executing service.
* `$caller.initiator` is the fully-qualified service name of the
  top-level service in the call stack.

---

### tundra.service:create

Creates a new empty flow service with the given name in an existing
package.

#### Inputs:

* `$package` is the name of the existing package in which to create
  a new empty flow service. If the package does not exist, an
  exception will be thrown.
* `$service` is the fully-qualified name of the service to be
  created, such as `folder1.folder2:service3`. If an element with
  the given name already exists, an exception will be thrown.

---

### tundra.service:defer

Defers execution of the given service using a dedicated single thread. The
service is not executed immediately, and is instead queued to wait to be
executed until the defer thread becomes available.

#### Inputs:

* `$service` is the fully-qualified name of the service to be deferred.
* `$pipeline` is an optional `IData` document which, if specified, contains
  the input arguments for the invocation of `$service`; in other words, the
  invocation is scoped to this `IData` document. If not specified, the
  invocation is unscoped, and hence the service will operate directly
  against the pipeline itself.

---

### tundra.service:ensure

Provides a try/catch/finally pattern for flow services.

#### Inputs:

* `$service` is the fully-qualified name of a service invoked
  in the [try block].
* `$catch` is an optional fully-qualified name of a service
  invoked in the [catch block]. This service should
  implement the `Tundra/tundra.schema.exception:handler`
  specification. If no `$catch` service is specified, the
  exception will be rethrown.
* `$finally` is an optional fully-qualified name of a service
  invoked in the [finally block] (always invoked regardless
  of whether an exception is thrown by `$service` or not).
* `$pipeline` is an optional `IData` document which, if specified,
  contains the input arguments for the invocations of `$service`,
  `$catch`, and `$finally`; in other words, the calls to these
  services are scoped to this `IData` document. If not specified,
  the invocations are not scoped, and hence these services
  operate directly against the pipeline itself.
* `$pipeline.catch` is an optional `IData` document which, if specified,
  contains input arguments which is merged into the pipeline prior
  to the invocation of `$catch`.
* `$pipeline.finally` is an optional `IData` document which, if
  specified, contains input arguments which is merged into the
  pipeline prior to the invocation of `$finally`.
* `$thread.priority` is an optional new priority to set on the current
  [thread], an integer value between 1 and 10, where larger values
  have higher priority and thus are executed in preference to threads
  with lower priority. The current thread's priority is restored to
  its original value after the given service is invoked.

#### Outputs:

* `$pipeline` is the output pipeline of the invocations of
  `$service`, `$catch`, and `$finally`. This is only returned if
  `$pipeline` was specified as an input. Otherwise, the outputs
  of these invocations are merged directly with the pipeline
  itself.

---

### tundra.service:fork

Invokes a service asynchronously, and either scoped or unscoped.

#### Inputs:

* `$service` is the fully-qualified name of the service to be
  invoked.
* `$pipeline` is an optional `IData` document which, if specified,
  contains the input arguments for the invocation of `$service`;
  in other words, the invocation is scoped to this `IData` document.
  If not specified, the invocation is unscoped, and hence the
  service will operate directly against the pipeline itself.

#### Outputs:

* `$thread` is the service thread object which can later be waited
  on to finish using `Tundra/tundra.service:join`.

---

### tundra.service:initiator

Returns `true` if the calling service is the initiating top-level
service for this thread.

#### Outputs:

* `$initiator?` is a boolean indicating if the calling service
  is the top-level service that initiated this thread.

---

### tundra.service:invoke

Invokes a service synchronously, and either scoped or unscoped.

#### Inputs:

* `$service` is the fully-qualified name of the service to be invoked.
* `$pipeline` is an optional `IData` document which, if specified,
  contains the input arguments for the invocation of `$service`; in
  other words, the invocation is scoped to this `IData` document. If
  not specified, the invocation is unscoped, and hence the service
  will operate directly against the pipeline itself.
* `$raise?` is an optional boolean which when `true` will rethrow any
  exceptions thrown by the invoked service, and when `false` will add
  the exception details if an exception was thrown to the pipeline
  or returned scope as appropriate as per the
  `tundra.schema.exception:handler` specification. Defaults to `true`.
* `$thread.priority` is an optional new priority to set on the current
  [thread], an integer value between 1 and 10, where larger values
  have higher priority and thus are executed in preference to threads
  with lower priority. The current thread's priority is restored to
  its original value after the given service is invoked.

#### Outputs:

* `$pipeline` is the output pipeline of the invocation of `$service`.
  This is only returned if the invocation was scoped by specifying
  `$pipeline` was specified as an input. If the invocation was not
  scoped, the outputs of the invocation are merged directly with the
  pipeline itself.
* `$duration` is how long the service took to execute specified as an
  XML duration string.

---

### tundra.service:join

Waits for the given service thread to finish before returning the
service output pipeline.

#### Inputs:

* `$thread` is a service thread object representing an asynchronously
  invoked service.
* `$raise?` is an optional boolean which when `true` will rethrow any
  exceptions thrown by the asynchronously invoked service, and when
  `false` will add the exception details if an exception was thrown to
  the returned `$pipeline` as per the `tundra.schema.exception:handler`
  specification. Defaults to `true`.

#### Outputs:

* `$pipeline` is the output pipeline returned by the service thread.

---

### tundra.service:nothing

This service deliberately does nothing.

---

### tundra.service:redirect

Registers a service redirection, which then transparently redirects
all subsequent invocations of the given source service to the
given target service.

If no target service is specified, the invocations of the source
service are redirected to an empty service which does nothing.

The source service is not required to exist in the current Integration
Server namespace. This allows for the source service to be registered
as a virtual service that is implemented by another existing service.

The source service input and output signature can be translated or
mapped to the target service input and output signature if required.

#### Outputs:

* `$service.redirect` defines a service redirection.
  * `source` is the fully-qualified name of the service to be
    redirected.
  * `target` is an optional fully-qualified name of a service to which
    all subsequent invocation of the given `source` service will be
    redirected.
  * `signature` defines the input and output signature redirections.
    * `input` is a list of source to target input signature parameter
      name redirection. The given `source` service input parameters
      will be renamed to the given `target` service input parameters
      prior to invocation.
      * `source` is a fully-qualified `source` service input signature
        parameter name.
      * `target` is an optional fully-qualified `target` service input
        signature parameter name. If not specified, the `source` input
        signature parameter will be dropped from the input pipeline
        prior to invocation.
    * `output` is a list of target to source output signature
      parameter name redirection. The given `target` service output
      parameters will be renamed to the given `source` service output
      parameters post invocation.
      * `target` is a fully-qualified `target` service output
        signature parameter name.
      * `source` is an optional fully-qualified `source` service
        output signature parameter name. If not specified, the
        `target` output signature parameter will be dropped from the
        output pipeline post invocation.
  * `pipeline` defines optional additional input and output pipeline
    arguments.
    * `input` defines optional additional input pipeline arguments,
    included in the input pipeline when the `target` service is
    invoked.
    * `output` defines optional additional output pipeline arguments,
    merged into the output pipeline returned after the `target`
    service is invoked.

---

### tundra.service:reflect

Returns information about the service with the given name.

#### Inputs:

* `$service` is the name of the service to be reflected on.

#### Outputs:

* `$service.properties`
  * `name` is the name of the service.
  * `type` is the type of service, such as flow or java.
  * `package` is the name of the package the service exists in.
  * `description` is the service comments.
  * `references` describes the elements this service references.
    * `packages` is the list of referenced packages.
    * `packages.length` is the length of the list of referenced
      packages.
    * `nodes` is the list of resolved references.
      * `package` is the package the reference resides in.
      * `node` is the name of the referenced element.
    * `nodes.length` is the length of the resolved references list.
    * `unresolved` is the list of unresolved references.
    * `unresolved.length` is the length of the unresolved references
      list.
  * `dependents` describes the elements which reference this service.
    * `packages` is the list of dependent packages.
    * `packages.length` is the length of the list of dependent
      packages.
    * `nodes` is the list of dependent elements.
      * `package` is the package the dependent element resides in.
      * `node` is the name of the dependent element.
    * `nodes.length` is the length of the resolved references list.

---

### tundra.service:respond

Forces the specified response status and body to be returned by
webMethods Integration Server to the calling process (such as an HTTP
or FTP client). This service only works correctly when invoked by the
top-level initiating service of the current thread, an unfortunate
limitation of the platform.

Unlike `WmPublic/pub.flow:setResponseCode`, which throws an exception
when invoked for transports other than HTTP, this service succeeds
regardless of the invoking transport for response codes that indicate
success.

#### Inputs:

* `$response.code` is the [HTTP status code] for the returned
  response. For example, a `200` status code indicates the request
  was successful. For transports other than HTTP, a status code >=
  `400` will result in an exception being thrown. This is the
  appropriate response, as it will result in the correct action for
  those transports occurring (such as a SOAP fault for a SOAP
  invocation, or a file transfer failure for an FTP invocation, for
  example).
* `$response.message` is an optional message to be associated with
  the given status code. If not specified, the standard status
  message for the code will be used. This is only applicable to HTTP
  transports.
* `$response.headers` is an optional `IData` document containing HTTP
  header keys and values to be added to the response. This is only
  applicable to HTTP transports.
* `$response.content` is an optional string, byte array, or input
  stream containing the response body to be returned. If not
  specified, defaults to an empty string.
* `$response.content.type` is the MIME media type of the given
  response body content. If not specified, defaults to
  `application/octet-stream` (the mime type for arbitrary binary
  data).
* `$response.content.encoding` is an optional character set used to
  encode `$response.content` when it represents text data. Defaults
  to [UTF-8] if the `$response.content.type` represents text data.

---

### tundra.service:restful

Registers the current invocation of the calling service to be treated
as a REST service by Tundra, opting the service in to the features
below.

To provide the following features, this service requires the Tundra
package configuration setting `feature/service/restful/enabled` to be
set to `true`. If this setting is disabled, this service has no
effect.

* Sanitizes the input pipeline to only include top-level arguments
  which are declared in the service's input signature. This guards
  against erroneous or untrusted clients providing undeclared
  arguments that could inadvertently affect the service's logic.
  Implemented with `tundra.pipeline:sanitize`.

* Validates the input pipeline against the service's input signature.
  If the input pipeline is invalid, a validation exception will be
  logged in the server's error log, and the following response status
  and body is automatically returned to the client. Note the response
  body is serialized as JSON or XML or YAML as appropriate based the
  Content-Type negotiated by the client in the request's `Accept`
  header (this example shows XML). Implemented with
  `tundra.pipeline:validate`.

        HTTP/1.1 422 Unprocessable Entity
        Content-Type: application/xml; charset=UTF-8
        Content-Length: nnn

        <error>
            <message>
                Validation against input signature failed:
                `xxx` value does not conform to datatype
            </message>
        </error>

* Executes the service.

* Sanitizes the output pipeline to only include top-level arguments
  which are declared in the service's output signature. This guards
  against erroneous services returning unspecified data to untrusted
  clients. Implemented with `tundra.pipeline:sanitize`.

* Validates the output pipeline against the service's output
  signature. If the output pipeline is invalid, a validation
  exception will be logged in the server's error log, and the
  following response status and body is automatically returned to
  the client. Note the response body is serialized as JSON or XML or
  YAML as appropriate based the Content-Type negotiated by the client
  in the request's `Accept` header (this example shows XML).
  Implemented with `tundra.pipeline:validate`.

        HTTP/1.1 500 Internal Server Error
        Content-Type: application/xml; charset=UTF-8
        Content-Length: nnn

        <error>
            <message>
                Validation against output signature failed:
                `yyy` value does not conform to datatype
            </message>
        </error>

* Serializes the output pipeline automatically as JSON or XML or YAML
  as appropriate based the Content-Type negotiated by the client in
  the request's `Accept` header and returns it as an HTTP 200 OK
  response to the client (the below example shows XML), if no
  response has been explicitly set by the service itself already (by
  calling `tundra.service:respond`, for example).

        HTTP/1.1 200 OK
        Content-Type: application/xml; charset=UTF-8
        Content-Length: nnn

        <example>
            <name>
                John Smith
            </name>
        </example>

* Catches any uncaught exceptions thrown by the service or its
  children, logs the exception in the server's error log, and
  automatically returns the following response status and body to
  the client. Note the response body is serialized as JSON or XML or
  YAML as appropriate based the Content-Type negotiated by the client
  in the request's `Accept` header (this example shows XML).

        HTTP/1.1 500 Internal Server Error
        Content-Type: application/xml; charset=UTF-8
        Content-Length: nnn

        <error>
            <message>
                Exception message describing the error
            </message>
        </error>

* Logs the request duration, request method, request and response
  headers, response status, and input and output pipelines in the
  logical log `permafrost.tundra.server.invoke.RestServiceProcessor`.
  The log level is set via the Tundra package configuration setting
  `feature/service/restful/logging`. Configure this to `OFF` to
  disable logging entirely.

---

### tundra.service:retry

Invokes the given service synchronously, retrying the invocation
if it fails.

#### Inputs:

* `$service` is the fully-qualified name of the service to be
  invoked.
* `$pipeline` is an optional `IData` document which, if specified,
  contains the input arguments for the invocation of `$service`. In
  other words, the invocation is scoped to this `IData` document. If
  not specified, the invocation is unscoped, and hence the service
  will operate directly against the pipeline itself.
* `$retry.limit` is an optional maximum number of retries that will
  be attempted if the service invocation fails, specified as a non-
  negative integer. Defaults to `0`.
* `$retry.wait` is the duration of time to wait between each retry,
  specified as an XML duration string, such as `PT10S` for 10
  seconds. Defaults to `PT0S`.
* `$retry.factor` is an optional factor to use to exponentially
  increase the time to wait between each subsequent retry. The time
  to wait for a retry is calculated as:

        ($retry.factor ^ ($retry.count - 1)) * $retry.wait

* `$thread.priority` is an optional new priority to set on the
  current thread, an integer value between `1` and `10`, where larger
  values have higher priority and thus are executed in preference to
  threads with lower priority. The current thread's priority is
  restored to its original value after the given service is invoked.

#### Outputs:

* `$pipeline` is the output pipeline of the invocation of `$service`.
  This is only returned if the invocation was scoped by specifying
  `$pipeline` was specified as an input. If the invocation was not
  scoped, the outputs of the invocation are merged directly with the
  pipeline itself.
* `$duration` is how long the service took to execute specified as an
  XML duration string.
* `$retry.count` is the number of retries that were attempted. If the
  initial invocation of `$service` succeeded, `0` is returned.

---

### tundra.service:retryable

Registers the current invocation of the calling service to have any
exceptions thrown by it automatically converted to be instances of
`com.wm.app.b2b.server.ISRuntimeException`.

Use this service instead of a `pub.flow:throwExceptionForRetry` try-
catch-throw block for trigger implementation services that should
be retried regardless of the type of exception thrown.

---

### tundra.service:self

Returns the name of the current service, or nothing if invoked
directly.

#### Outputs:

* `$self` is the fully-qualified name of the current service
  (the service which called this service), or nothing if invoked
  directly.

---

### tundra.service:sleep

Sends the currently executing thread to sleep (temporarily cease
execution) for the specified duration, subject to the precision and
accuracy of system timers and schedulers.

This service is an alias for the `tundra.thread:sleep` service.

#### Inputs:

* `$duration` is a duration of time specified as a string for which
  the current thread should sleep.
* `$duration.pattern` is an optional pattern describing the type of
  duration specified by `$duration`. Defaults to an [ISO8601] XML
  string.

---

### tundra.service:synchronize

Performs a thread-synchronized invoke of the given service, such that
multiple parallel invocations of the given service have their
execution serialized.

#### Inputs:

* `$service` is the fully-qualified name of the service to be
  invoked.
* `$pipeline` is an optional `IData` document which, if specified,
  contains the input arguments for the invocation of `$service`, and
  therefore the invocation is scoped to this `IData` document. If
  not specified, the invocation is unscoped, and the service will
  operate directly against the pipeline itself.

#### Outputs:

* `$pipeline` is the output pipeline of the invocation of `$service`.
  This is only returned if the invocation was scoped by specifying
  `$pipeline` was specified as an input. If the invocation was not
  scoped, the outputs of the invocation are merged directly with the
  pipeline itself.
* `$service.duration` is how long the service took to execute
  specified as an XML duration string.

---

### tundra.service:validate

Returns `true` if the given service name exists and is actually
a service.

#### Inputs:

* `$service` is the name to be validated.
* `$raise?` is a boolean flag indicating if an exception should
  be thrown if the given service is not valid. Defaults to
  `false`.

#### Outputs:

* `$valid?` is a boolean flag indicating if the given service
  exists and is actually a service on this Integration Server.

---

### tundra.session:get

Returns information about the current session, including values stored in
session state.

#### Outputs:

* `$session` is an `IData` document containing information about the
  current session.

---

### tundra.session:put

Stores the given key value pair in current session state.

#### Inputs:

* `$key` is the optional key name to use to identify the value
  to be stored in session state. If not specified, this
  service does nothing.
* `$value` is the optional value to be stored in session state.

---

### tundra.session:remove

Removes the given key value pair from current session state.

#### Inputs:

* `$key` is the optional key name to use to identify the key
  value pair to be removed from session state. If not specified,
  this service does nothing, otherwise the key value pair is
  removed from session state.

#### Outputs:

* `$value` was the value associated with the removed `$key`.

---

### tundra.smtp:send

Sends an email message to recipients via the SMTP protocol.

If the transport settings are not provided, will fallback to using the
SMTP server settings configured on the Integration Server Resources
page.

#### Inputs:

* `$email.message` is the message to be sent.
  * `uri` is an optional `mailto:` URI specifying an email to be sent.
  * `to` is an optional list of email addresses to send the email to.
  * `cc` is an optional list of email addresses to send a carbon copy
    of the email to.
  * `bcc` is an optional list of email addresses to send a blind
    carbon copy of the email to.
  * `from` is an optional email address the email will be sent from.
    If not specified, defaults to the system property `tn.mail.from`,
    or the system property `mail.smtp.from` if it exists, otherwise
    defaults to the value `Integration-Server@<hostname>`.
  * `subject` is an optional subject line for the email.
  * `body` is an optional body for the email, and can be specified as
    plain text or HTML.
  * `content.type` is the optional MIME type of the body content. If
    sending an HTML email, then specify `text/html`. Defaults to
    `text/plain` if not specified.
  * `headers` is an optional list of headers specified as key value
    pairs to be set on the email.
  * `attachments` is an optional list of files to be attached to the
    email:
    * `name` is the filename of the attachment.
    * `content` is the content of the attachment, specified as an
      input stream, byte array, or string.
    * `content.type` is the MIME type of the attachment `content`.
    * `compression` is an optional choice to compress the content
      before attaching it to the email:
      * `none`: the content is not compressed and is attached as is,
        this is the default.
      * `gzip`: the content is gzip compressed and attached with the
        given `name` suffixed with the gzip extension `.gz`.
      * `zip`: the content is zip compressed and attached with the
        given `name` suffixed with the zip extension `.zip`.
    * `headers` is an optional list of headers specified as key value
      pairs to be set on the attachment.
  * `transport` contains settings for customising the SMTP transport
    layer:
    * `host` is an optional SMTP host to use to send the email. If not
    specified, defaults to the system setting `watt.server.smtpServer`
    then `mail.smtp.host`.
    * `post` is an optional SMTP port to use to send the email. If not
    specified, defaults to the system setting
    `watt.server.smtpServerPort` then `mail.smtp.port` then the value
    `25` (the default port used for SMTP).
    * `auth` contains the authentication related settings:
      * `type` is a choice of:
        * `Basic`: used for user/password login
        * `Bearer`: used for OAuth login
      * `user` is the username to authenticate with the SMTP server.
      * `password` is the optional password to authenticate with the
        SMTP server when `type` is `Basic`.
      * `token` is the OAuth token to authenticate with the SMTP
        server when sending the email when `type` is `Bearer`.
    * `secure` contains the security related settings:
      * `encryption` is a choice of:
        * `none`: do not use TLS encryption
        * `explicit`: the initial connection to the SMTP server will
          be unencrypted and the client will ask the server to upgrade
          the connection TLS if supported.
        * `implicit`: the initial connection to the SMTP server will
          be encrypted using TLS.
      * `truststore`: is the trust store alias to be used for TLS.

#### Outputs:

* `$email.response` contains the results of sending the email:
  * `message.content` is the content of the email message sent.
  * `message.content.type` is the MIME type of the `message.content`,
    which is set to `message/rfc822`.
  * `transport.uri` is the redacted URI used to connect to the SMTP
    server.
  * `transport.log` is the email session diagnostic logs.
  * `transport.status` is a status message of the email.

---

### tundra.soap.fault:accept

Handles a [SOAP] fault returned by a web service invocation by
returning the fault including a summary message if one of the
Integration Server [SOAP] fault structures exist in the pipeline.

Supports both the Integration Server pre-8.2 SOAP-FAULT and the 8.2
and higher fault structures.

#### Inputs:

* `fault` is an optional [SOAP] fault structure returned by an
  Integration Server 8.2 and higher web service consumer.
* `SOAP-FAULT` is an optional [SOAP] fault structure returned by an
  Integration Server pre-8.2 web service consumer.

#### Outputs:

* `fault` is an optional [SOAP] fault structure specified if one of
  the inputs were provided describing the fault that occurred.
  * `message` is a summary of the [SOAP] fault for display use.

---

### tundra.soap.fault:raise

Handles a [SOAP] fault returned by a web service invocation by
throwing an exception if one of the Integration Server [SOAP] fault
structures exist in the pipeline.

Supports both the Integration Server pre-8.2 `SOAP-FAULT` and the 8.2
and higher `fault` structures.

#### Inputs:

* `fault` is an optional [SOAP] fault structure returned by an
  Integration Server 8.2 and higher web service consumer.
* `SOAP-FAULT` is an optional [SOAP] fault structure returned by an
  Integration Server pre-8.2 web service consumer.

---

### tundra.stream:close

Closes the given input or output stream, and releases any associated
system resources.

#### Inputs:

* `$content.stream` is an optional [java.io.InputStream] or
  [java.io.OutputStream] object to be closed. If specified, the stream
  is closed and any associated system resources are released. If not
  specified, this service does nothing.

---

### tundra.stream:copy

Copies all data from the given [java.io.InputStream] object to the
given [java.io.OutputStream] object, then optionally closes both
stream objects.

#### Inputs:

* `$content.stream.input` is a [java.io.InputStream] object containing
  data to be written to `$content.stream.output`. If not specified,
  this service does nothing.
* `$content.stream.output` is a [java.io.OutputStream] object where
  data read from `$content.stream.input` is to be written. If not
  specified, this service does nothing.
* `$content.stream.buffer.size` is an optional size in bytes to use
  when buffering the data being copied in memory.
* `$content.stream.close?` is an optional boolean indicating whether
  the streams should be closed after the copy is complete. Defaults to
  `true`.

---

### tundra.stream:normalize

Converts a `String`, `byte[]` or `java.io.InputStream` object to a
`java.io.InputStream` object.

#### Inputs:

* `$content` is a `String`, `byte[]`, or `java.io.InputStream` object
  to be converted to a `java.io.InputStream` object. If not specified,
  this service does nothing.
* `$content.encoding` is the character set used to encode character
  data when `$content` is specified as a `String`. Defaults to
  [UTF-8].

#### Outputs:

* `$content.stream` is a `java.io.InputStream` object from which the
  data represented by `$content` can be read.
* `$content.encoding` is the character set used to encode `$content`.

---

### tundra.stream:transcode

Converts the given [java.io.InputStream] containing text data in
from one encoding or character set to another.

#### Inputs:

* `$content.stream` is an [java.io.InputStream] to be transcoded.
* `$content.encoding.input` is the optional character set used to
  decode the given [java.io.InputStream]. Defaults to [UTF-8].
* `$content.encoding.output` is the optional character set used to
  encode the text data decoded from the given [java.io.InputStream].
  Defaults to [UTF-8].

#### Outputs:

* `$content.stream` is a new [java.io.InputStream] which contains the
  transcoded text data.
* `$content.encoding.input` is the character set used to decode the
  input data.
* `$content.encoding.output` is the character set used to encode the
  output data.

---

### tundra.string.base64:decode

[Base64] decodes the given string.

#### Inputs:

* `$base64` is a [Base64] encoded string to be decoded.
* `$content.encoding` is the optional character set used to decode
  the text data. Defaults to [UTF-8].

#### Outputs:

* `$string` is the [Base64] decoded data as a string.

---

### tundra.string.base64:encode

[Base64] encodes the given string.

#### Inputs:

* `$string` is a string containing data to be [Base64] encoded.
* `$content.encoding` is the optional character set used to encode
  the text data. Defaults to [UTF-8].

#### Outputs:

* `$base64` is the [Base64] encoded data as a string.

---

### tundra.string:blankify

Returns an empty string if the given input string is null, otherwise
returns the given input string unchanged.

#### Inputs:

* `$string` is an optional string input value.

#### Outputs:

* `$string` is an empty string if the input `$string` was null,
  otherwise `$string` is returned unchanged.

---

### tundra.string:build

Appends the string values specified as strings, string lists or
string tables in the given `IData` document to the given
[java.lang.StringBuilder] object.

Using a [java.lang.StringBuilder] object to build a string is more
efficient than concatenating strings.

When finished building, use `tundra.object:stringify` to convert
the [java.lang.StringBuilder] object to a string.

Note [java.lang.StringBuilder] objects are not thread-safe so that
they do not incur thread synchronization overhead, and therefore
this service should only be used by one thread per
[java.lang.StringBuilder] object.

#### Inputs:

* `$builder` is an optional [java.lang.StringBuilder] object to append
  the given strings to. A new [java.lang.StringBuilder] object is
  created automatically, if not specified.
* `$operands` is an `IData` document in which arbitrary string, string
  list, and string table values can be specified for concatenation.
* `$separator` is an optional string used to separate each list item
  in the resulting string. Defaults to an empty string.

#### Outputs:

* `$builder` is the [java.lang.StringBuilder] object the given strings
  were appended to.

---

### tundra.string:capitalize

Capitalizes the first character in either the first word or all words
in the given arbitrarily strings.

#### Inputs:

* `$capitalize.operands` is an `IData` document containing arbitrarily
  specified strings to be capitalized.
* `$capitalize.mode` is an optional choice that determines whether all
  words or only the first word is capitalized. Defaults to
  capitalizing all words, if not specified:
  * `all words`
  * `first word`

#### Outputs:

* `$capitalize.results` is an `IData` document containing the
  capitalized strings.

---

### tundra.string:capture

Returns the associated [capturing groups] if the given [regular expression
pattern] is found anywhere in the given string.

Refer to the following excerpt from the [java.util.regex.Pattern]
documentation:

> Groups and capturing
>
> Capturing groups are numbered by counting their opening parentheses from
> left to right. In the expression `((A)(B(C)))`, for example, there are four
> such groups:
>
> 1. `((A)(B(C)))`
> 2. `(A)`
> 3. `(B(C))`
> 4. `(C)`
>
> Group zero always stands for the entire expression.
>
> Capturing groups are so named because, during a match, each subsequence of
> the input sequence that matches such a group is saved. The captured
> subsequence may be used later in the expression, via a back reference, and
> may also be retrieved from the matcher once the match operation is
> complete.
>
> The captured input associated with a group is always the subsequence that
> the group most recently matched. If a group is evaluated a second time
> because of quantification then its previously-captured value, if any, will
> be retained if the second evaluation fails. Matching the string "aba"
> against the expression `(a(b)?)+`, for example, leaves group two set to "b".
> All captured input is discarded at the beginning of each match.
>
> Groups beginning with `(?` are pure, non-capturing groups that do not
> capture text and do not count towards the group total.

#### Inputs:

* `$string` is a string to be searched for the given pattern.
* `$pattern` is the [regular expression pattern] to search for against the
  given string.

#### Outputs:

* `$captured?` is a boolean which when `true` indicates that the [regular
  expression pattern] was found at least once in the given `$string`.
* `$captures` is an `IData[]` document list where each item contains the
  [capturing groups] associated with each time `$pattern` was found in
  `$string`.
  * `groups` is an `IData[]` document list where each item in the list
    represents a single [capturing group]. Note that the first (zeroth)
    item in the list always denotes the entire pattern.
    * `captured?` is a boolean flag which when `true` indicates that this
      [capturing group] was captured in the matched substring. This flag
      will be `false` for optional [capturing groups] that are not found in
      the matched substring.
    * `index` is the zero-based start index of the captured substring for
      this [capturing group], and is only specified when `$captured?` is
      `true`.
    * `length` is the number of captured characters in the captured
      substring for this [capturing group], and is only specified when
      `$captured?` is `true`.
    * `content` is captured substring for this [capturing group], and is
      only specified when `$captured?` is `true`.
  * `groups.length` is the number of items in the `groups` `IData[]` document
    list, which is equal to the number of [capturing groups] + 1 (where
    the additional item is the entire pattern) present in the [regular
    expression pattern].
* `$captures.length` is both the number of times the [regular expression
  pattern] was found in the given `$string`, and the number of items in
  the `$captures` `IData[]` document list.

---

### tundra.string:characters

Returns the given string as a list of characters.

#### Inputs:

* `$string` is a string to be split into characters.

#### Outputs:

* `$characters` is a list of the characters in the
  given `$string`.

---

### tundra.string:coalesce

Returns the first string argument that is not null.

#### Inputs:
* `$coalesce.operands` is an `IData` document containing arbitrarily
  specified strings to be coalesced.
* `$coalesce.mode` determines what is returned when all arguments are
  null:
  * `missing`: `$coalesce.result` is not returned when all arguments
    are null. This is the default, if `$coalesce.mode` is not
    specified.
  * `null`: `$coalesce.result` is returned as null when all arguments
    are null.

#### Outputs:

* `$coalesce.result` is the first of the given strings whose value is
  not null, or null if all arguments were null and the specified
  `$coalesce.mode` was `null`.

---

### tundra.string:compare

Compares two strings indicating their lexicographical position
relative to one another.

Implemented using the java.lang.String [compareTo] and
[compareToIgnoreCase] methods.

#### Inputs:

* `$string.x` is the datetime string to be compared to `$string.y`.
* `$string.y` is the datetime string to be compared to `$string.x`.
* `$insensitive.case?` is a boolean flag indicating if the string
  comparison should ignore case differences. Defaults to `false`.
* `$insensitive.whitespace?` is a boolean flag indicating if the
  string comparison should ignore whitespace differences.
  Defaults to `false`.

#### Outputs:

* `$before?` is a boolean flag indicating if `$string.x` is less than
  `$string.y`.
* `$equal?` is a boolean flag indicating if `$string.x` and `$string.y`
  are equal.
* `$after?` is a boolean flag indicating if `$string.x` is greater
  than `$string.y`.

---

### tundra.string:concatenate

Concatenates the given arbitrarily specified string values.

#### Inputs:

* `$concatenate.operands` is an `IData` document containing
  arbitrarily specified string values to be concatenated.
* `$concatenate.separator` is an optional string used to separate each
  list item in the resulting string. Defaults to an empty string.
* `$concatenate.sanitization` is an optional choice that determines
  how null and blank values should be treated. If not specified, no
  sanitization is performed.
  * `remove nulls` will remove any null values prior to concatenation.
  * `remove nulls and blanks` will remove any null values and blank
    string values prior to concatenation.
  * `convert nulls to blanks` will convert any null values to blank
    values prior to concatenation.

#### Outputs:

* `$concatenate.result` is the result of concatenating all the string
  values provided in the given `IData` document.

---

### tundra.string:condense

Replaces runs of one or more whitespace characters (space, tab,
carriage return, line feed) with a single space character, then trims
leading and trailing whitespace in the given arbitrarily specified
string values.

#### Inputs:

* `$condense.operands` is an `IData` document containing arbitrarily
  specified string values to be condensed.

#### Outputs:

* `$condense.results` is an `IData` document containing the condensed
  string values.

---

### tundra.string:find

Returns whether the given [regular expression pattern] or literal
string is found anywhere in the given string.

#### Inputs:

* `$string` is a string to be searched for the given pattern.
* `$pattern` is the [regular expression pattern] or literal string
  to search for against the given string.
* `$pattern.literal?` is a boolean indicating if the `$pattern` string
  should be treated as a literal string. If `false`, `$pattern` is
  treated as a [regular expression pattern]. If `true`, `$pattern` is
  treated as a literal string. Defaults to `false`, if not specified.

#### Outputs:

* `$found?` is `true` if the given pattern is found anywhere in the
  given string.

---

### tundra.string:format

Returns a formatted string using the given arguments and pattern,
specified as a [java.util.Formatter format string].

#### Inputs:

* `$pattern` is a [java.util.Formatter format string] which can
  contain fixed text, and one or more format specifiers which
  indicate how the given arguments should be formatted in the
  resulting string.
* `$patterns` is a list of [java.util.Formatter format strings]
  which can contain fixed text, and one or more format specifiers
  which  indicate how the given arguments should be formatted in
  the resulting string. If specified, all items in this list are
  concatenated together to create the format string. This list
  can be used to separate the format string into logical units
  for ease of understanding.
* `$pipeline` is an optional `IData` document used to resolve the
  given argument keys against. If not specified, argument keys
  are resolved against the pipeline itself.
* `$list` is an optional `IData[]` document list which if specified
  is iterated over, and each item in the list is used to resolve
  the given argument keys against and the resulting format strings
  for each item are then concatenated together separated by
  `$separator` before being returned. If not specified, argument
  keys are resolved against `$pipeline`.
* `$separator` is an optional string used to separate each format
  string for each item in `$list`. Only used if `$list` is specified.
* `$arguments` is an optional list of arguments referenced by the
  given `$pattern` for inclusion in the resulting formatted string:
  * `key` is an optional simple or fully-qualified key identifying the
    value in the pipeline to be used to format the resulting string.
    Supports absolute keys prefixed with `/`, which will always be
    resolved against `$pipeline`, if it exists, or the pipeline itself,
    if it does not exist. If not specified, then a value should be
    provided directly in the value field. The following two special
    keys are also supported with their values set automatically when
    used:
    * `$index` is the zero-based index of the item in `$list` being
      formatted.
    * `$iteration` is the one-based index of the item in `$list` being
      formatted.
  * `value` is the optional value of the argument to be formatted in
    the resulting string. If not specified, then a `key` should be
    provided to identify which value in the pipeline is to be
    formatted.
  * `type` is the type of value the argument represents, and affects
    the [java.util.Formatter format string] conversions available to
    the argument:
    * `string` will convert the value to a `java.lang.String` prior to
      formatting.
    * `integer` will convert the value to a `java.math.BigInteger` prior
      to formatting.
    * `decimal` will convert the value to a `java.math.BigDecimal` prior
      to formatting.
    * `datetime` will convert the value to a `java.util.Calendar` prior
      to formatting.
  * `pattern` is an optional datetime pattern string which is used to
    parse the given value if the specified type is datetime.
  * `blankify?` is an optional boolean which if `true` will convert
    the argument value if it is null to an empty string for string
    types, and to zero for number types.
* `$locale` optionally identifies the [Locale] to apply during
  formatting. If not specified, no localization is applied.

#### Outputs:

* `$string` is the resulting formatted string.

---

### tundra.string:legalize

Converts the given string to a legal Java identifier by replacing illegal
characters with underscore characters.

#### Inputs:

* `$string` is a string to be converted to a legal Java identifier.

#### Outputs:

* `$string` is the given string with illegal characters replaced with
  underscore characters.

---

### tundra.string:length

Returns the number of characters in the given string.

#### Inputs:

* `$string` is an optional string to return the length of.

#### Outputs:

* `$length` is the number of characters in the given string,
  or zero if no string was specified.

---

### tundra.string:lines

Returns all the lines in the given string as a list.

#### Inputs:

* `$string` is an optional string to split into lines.

#### Outputs:

* `$lines` is a string list containing each line from
  the given string.

---

### tundra.string:listify

Converts the value or values identified by the given key to a new
list containing the original value or values as its items, unless
the original value is already a list in which case it remains
unmodified.

#### Inputs:

* `$key` is a simple or fully-qualified (such as `a/b/c[0]/d`) key
  identifying the value to be converted to a list.
* `$scope` is an optional `IData` document that, if specified, is used
  to resolve the given `$key` against. If not specified, `$key` is
  resolved against the pipeline.

#### Outputs:

* `$scope` is returned if an input `$scope` was provided, where the value
  identified by `$key` within it has been converted to a list. If
  no input `$scope` was specified, the value identified by `$key` in the
  pipeline is instead converted to a list. If `$key` does not identify
  any value, this service does nothing.

---

### tundra.string:lowercase

Returns the given arbitrarily specified strings converted to lower
case.

#### Inputs:

* `$lowercase.operands` is an `IData` document containing arbitrarily
   specified strings to to be converted to lower case.
* `$lowercase.locale` optionally identifies the case transformation
  rules to be used for a given [Locale]. If not specified, the
  [default locale] is used.

#### Outputs:

* `$lowercase.results` is an `IData` document containing the specified
  operands converted to lower case, where all characters have been
  converted to their lower case equivalent according to the
  transformation rules of the given [Locale].

---

### tundra.string:match

Returns whether the given [regular expression pattern] or literal
string matches the given string.

#### Inputs:

* `$string` is a string to be matched against the given pattern.
* `$pattern` is the [regular expression pattern] or literal string
  to match against the given string.
* `$pattern.literal?` is a boolean indicating if the `$pattern` string
  should be treated as a literal string. If `false`, `$pattern` is
  treated as a [regular expression pattern]. If `true`, `$pattern` is
  treated as a literal string. Defaults to `false`, if not specified.

#### Outputs:

* `$match?` is `true` if the given string matches the given pattern.

---

### tundra.string:normalize

Converts a `String`, `byte[]` or `java.io.InputStream` object to a
`String` object.

#### Inputs:

* `$content` is a `String`, `byte[]` or `java.io.InputStream` object
  to be converted to a `String`.
* `$content.encoding` is an optional character set to use when
  `$content` is a `byte[]` or `java.io.InputStream` object. Defaults
  to [UTF-8].

#### Outputs:

* `$content.string` is the given content converted to a `String`
  object.

---

### tundra.string:nullify

Returns null if the given input string contains only whitespace
characters, otherwise returns the given input string unchanged.

#### Inputs:

* `$string` is an optional string input value.

#### Outputs:

* `$string` is null if the given `$string` contained only whitespace
  characters, otherwise `$string` is returned unchanged.

---

### tundra.string:pad

Pads arbitrarily specified strings with the given character as many
times as necessary to reach the desired length.

#### Inputs:

* `$pad.operands` is an `IData` document containing arbitrarily
  specified strings to be padded to the given length. Strings whose
  lengths are greater than or equal to `|$pad.length|` are returned
  unmodified.
* `$pad.length` is the minimum desired length for the returned string.
  If specified as a positive integer, strings will be padded from the
  left. If specified as a negative integer, strings will be padded
  from the right.
* `$pad.character` is the character to use when padding. If more than
  one character is specified, only the first character will be used.
  Defaults to ` ` (space character), if not specified.

#### Outputs:

* `$pad.results` contains the specified strings padded with the given
  character to the desired length.

---

### tundra.string:prefixed

Returns whether the given string starts with the given prefix.

#### Inputs:

* `$string` is the string to be checked.
* `$prefix` is the prefix the given string is expected to start with.

#### Outputs:

* `$prefixed?` is `true` if the given string starts with the given
  prefix.

---

### tundra.string:quote

Returns a [regular expression pattern] that can be used to match the
given string literally. Regular expression metacharacters or escape
sequences in the input sequence are given no special meaning.

#### Inputs:

* `$string` is a string value to be converted to a
  [regular expression pattern].

#### Outputs:

* `$pattern` is a [regular expression pattern] that can be used to match
  the given `$string` literally.

---

### tundra.string:remove

Removes either the first or all of the occurrences of the given
pattern from one or more arbitrarily specified string values.

#### Inputs:

* `$remove.operands` is an `IData` document containing one or more
  arbitrarily specified string values from which to remove occurrences
  of the given pattern.
* `$remove.pattern` is the pattern to match against the given strings.
  If not specified, no removal will occur.
* `$remove.pattern.literal?` is a boolean indicating whether to treat
  `$remove.pattern` as a literal string or a regular expression. If
  not specified, defaults to `false` which treats the pattern as a
  [regular expression pattern].
* `$remove.occurrence.first?` is a boolean indicating whether only the
  first occurrence of the pattern should be removed. Defaults to
  `false`, which removes all occurrences of the pattern.

#### Outputs:

* `$remove.results` are the given string values with occurrences of
  the given pattern removed.

---

### tundra.string:replace

Replaces either the first or all of the occurrences of the given
pattern with the given replacement in one or more arbitrarily
specified string values.

#### Inputs:

* `$replace.operands` is an `IData` document containing one or more
  string values in which to replace occurrences of the given pattern.
* `$replace.pattern` is the pattern to be replaced in the given string
  values. If not specified, no replacement will occur.
* `$replace.pattern.literal?` is a boolean indicating whether the
  pattern is a literal or [regular expression pattern]. If not
  specified, defaults to `false` which treats the pattern as a
  [regular expression pattern].
* `$replace.replacement` is the replacement string to be substituted
  in the given string values wherever the given pattern is found. If
  not specified, no replacement will occur.
* `$replace.replacement.literal?` is a boolean indicating if the
  replacement string should be treated as a literal or regular
  expression replacement. If not specified, defaults to
  `false` where captured groups can be referred to with dollar-
  prefixed references, such as `$1`, and other special characters are
  required to be escaped.
* `$replace.occurrence.first?` is a boolean indicating if only the
  first occurrence of the given pattern should be replaced. Defaults
  to `false`, where all occurrences of the pattern are replaced.

#### Outputs:

* `$replace.results` are the given string values with occurrences of
  the given pattern replaced.

---

### tundra.string:reverse

Reverses the order of characters in one or more arbitrarily specified
string values.

#### Input:

* `$reverse.operands` is an `IData` document containing one or more
  string values to be reversed.

#### Output:

* `$reverse.results` is an `IData` document containing the reversed
  string values.

---

### tundra.string:slice

Takes a slice or subset of the characters in one or more arbitrarily
specified string values. For example:

    slice("hamburger", index =  0, length =  3) => "ham"
    slice("hamburger", index =  2, length = -3) => "ham"
    slice("hamburger", index = -7, length = -3) => "ham"
    slice("hamburger", index =  4, length =  4) => "urge"
    slice("hamburger", index =  7, length = -4) => "urge"
    slice("hamburger", index = -2, length = -4) => "urge"

#### Inputs:

* `$slice.operands` is an `IData` document containing one or more
  arbitrarily specified string values.
* `$slice.index` is an optional zero-based index from which to take
  the slice. Supports forward and reverse indexing, where a positive
  index is a normal zero-based array index from left to right, and a
  negative index is a reverse array index from right to left. For
  example, an index of `-1` is the last item in the list, and an index
  of `-2` is the second last item in the list. If not specified,
  defaults to `0`.
* `$slice.length` is the number of characters to include in the slice.
  Supports positive and negative lengths, where a positive length
  will slice from left to right, and a negative length will slice
  from right to left. If not specified, a left to right slice
  containing all remaining characters after `$slice.index` will be
  returned when `$slice.index` is positive, or a right to left slice
  containing all remaining characters before `$slice.index` will be
  returned when `$slice.index` is negative.

#### Outputs:

* `$slice.results` is an `IData` document containing the sliced string
  values.

---

### tundra.string:split

Splits one or more arbitrarily specified strings around matches of the
given literal or [regular expression pattern].

#### Inputs:

* `$split.operands` is an `IData` document containing one or more
  arbitrarily specified string values to be split.
* `$split.pattern` is the literal or [regular expression pattern] to
  match against the given strings.
* `$split.pattern.literal?` is a boolean indicating if the pattern
  should be treated as a literal or [regular expression pattern].
  Defaults to `false`.

#### Outputs:

* `$split.results` is an `IData` document containing the results of
  splitting each given string value into a list of strings.

---

### tundra.string:substitute

Attempts variable substitution on the given string by replacing all
occurrences of substrings matching `%key%` with the associated
(optionally scoped) value.

#### Inputs:

* `$string` is a string to perform variable substitution on.
* `$substitution.default` is an optional default value to substitute
  in place of null or missing values.
* `$substitution.mode` is an optional choice of whether the
  substitution scope should be `local` variables only, `global`
  variables only, or `all` variables (local and global variables
  where local variables take precedence). Note that choosing `global`
  on an Integration Server version that does not support global
  variables will result in all values being substituted with the
  default value if one is provided. Defaults to `local`.
* `$pipeline` is an optional `IData` document used to scope the
  variable substitution. If not specified, the substitution is
  unscoped (resolved against the pipeline itself).

#### Outputs:

* `$string` is the input string with variable substitution patterns,
  such as `%key%`, replaced with the value of the key (resolved
  against either `$pipeline`, if specified, or the pipeline itself).

---

### tundra.string:suffixed

Returns whether the given string ends with the given suffix.

#### Inputs:

* `$string` is the string to be checked.
* `$suffix` is the suffix the given string is expected to end with.

#### Outputs:

* `$suffixed?` is `true` if the given string ends with the given
  suffix.

---

### tundra.string:translate

Translates strings from one value to another using the specified
translations.

For example, given the following `$translation.operands` containing
arbitrarily specified string values:

    {
        "key1": "a",
        "key2": "b",
        "key3": "1",
        "key4": "2",
        "key5": "z"
    }

And given the following `$translation.values` defining the
translations from the specified keys to the specified values:

    {
        "a": "1",
        "b": "2"
    }


If `$translation.exclude?` is `false`, the resulting translation
returned in `$translation.result` is as follows (note the values with
no translation were included in the results unchanged):

    {
        "key1": "1",
        "key2": "2",
        "key3": "1",
        "key4": "2",
        "key5": "z"
    }

Or if `$translation.exclude?` is `true`, the resulting translation
returned in `$translation.result` is as follows (note the values with
no translation were excluded from the results):

    {
        "key1": "1",
        "key2": "2"
    }

Or if `$translation.exclude?` is `true` and `$translation.reverse?` is
`true`, the `$translation.values` table is reversed, and the resulting
translation returned in `$translation.result` is as follows:

    {
        "key3": "a",
        "key4": "b"
    }

#### Inputs:

* `$translation.operands` is an `IData` document containing
  arbitrarily specified `String`, `String[]`, or `String[][]` values
  to be translated.
* `$translation.values` is an `IData` document containing key value
  pairs which specify the from and to values used for translation. The
  following special keys are also supported to provide a default
  value, and a null replacement value, when required:
  * `$default` - used when a value has no translation
  * `$default.reverse` - used when a value has no translation and
    `$translation.reverse?` is `true`
  * `$null` - used when a value is null
  * `$null.reverse` - used when a value is null and
    `$translation.reverse?` is `true`
* `$translation.reverse?` is an optional boolean which when `true`
  indicates that the `$translation.values` table should be flipped
  such that the keys become the values and vice versa. Defaults to
  `false`.
* `$translation.exclude?` is an optional boolean which when `true`
  will exclude any operands that are missing translation values from
  the returned `$translation.results`. Defaults to `false`.
* `$translation.raise?` is an optional boolean which when `true` will
  throw an exception if the translation value for a given operand is
  missing from `$translation.values`. Defaults to `false`.

#### Outputs:

* `$translation.results` is an `IData` document containing the
  translated values specified using the same keys and structure as was
  specified in `$translation.operands`.

---

### tundra.string:trim

Removes leading and trailing whitespace from one or more arbitrarily
specified strings.

#### Inputs:

* `$trim.operands` is an `IData` document containing one or more
  string values to be trimmed of leading and trailing whitespace
  characters.

#### Outputs:

* `$trim.results` is an `IData` document containing the trimmed string
  values.

---

### tundra.string:truncate

Truncates one or more arbitrarily specified string values to the
desired length.

#### Inputs:

* `$truncate.operands` is an `IData` document containing one or more
  arbitrarily specified string values to be truncated.
* `$truncate.length` is the desired length to truncate to. When
  specified as a positive value, truncation occurs left to right. When
  specified as a negative value, truncation occurs right to left.
* `$truncate.ellipsis?` is an optional boolean indicating whether
  truncated strings should be suffixed (for left to right truncations)
  or prefixed (for right to left truncations) with an ellipsis
  character as an indication that truncation has occurred. Defaults to
  `false`.

#### Outputs:

* `$truncate.results` is an `IData` document containing the truncated
  string values.

---

### tundra.string:uppercase

Returns the given arbitrarily specified strings converted to upper
case.

#### Inputs:

* `$uppercase.operands` is an `IData` document containing arbitrarily
   specified strings to to be converted to upper case.
* `$uppercase.locale` optionally identifies the case transformation
  rules to be used for a given [Locale]. If not specified, the
  [default locale] is used.

#### Outputs:

* `$uppercase.results` is an `IData` document containing the specified
  operands converted to upper case, where all characters have been
  converted to their upper case equivalent according to the
  transformation rules of the given [Locale].

---

### tundra.string:wrap

Wraps the given string by breaking it into lines such that no line
length is greater than the given character length.

#### Inputs:

* `$string` is a string to be wrapped.
* `$length` is the maximum character length allowed per resulting line.

#### Outputs:

* `$string` is the given string wrapped at the given character length.
* `$lines` is a `String[]` containing each line from the resulting
  wrapped string.

---

### tundra.system:reflect

Returns information about Integration Server, such as the software
version, environment settings, Java properties, well-known directory
locations, and memory usage.

#### Inputs:

* `$refresh?` is an optional boolean which when `true` will reload
  the system environment and system properties from the Java virtual
  machine, and the system directories from Integration Server.
  Defaults to `false`.

#### Outputs:

* `$system` is an immutable `IData` document containing the software
  version, environment settings, Java properties, well-known
  directory locations, and memory usage.
  * `version` is the Integration Server software version number for
    the Integration Server on which this service is executed.
  * `localhost` is an `IData` document containing the localhost
    address:
    * `domain` is the localhost's domain name.
    * `host` is the localhost's host name.
    * `ip` is the localhost's IP address.
  * `environment` is an `IData` document containing all the
    environment variables defined on the system on which this
    service is executed.
  * `property` is an `IData` document containing all the Java
    configuration properties defined for the JVM process in which
    this service is executed.
  * `global` is an `IData` document containing all the defined
    Integration Server global variables.
  * `directory` is an `IData` document containing all the well-known
    directory locations defined for the Integration Server on
    which this service is executed.
  * `memory` is an `IData` document containing the memory usage of
    the JVM process in which this service is executed:
    * `used` is the number of bytes of memory allocated to the JVM
      heap that are in use.
    * `free` is the number of bytes of memory allocated to the JVM
      heap that are not in use.
    * `total` is the total number of bytes of memory allocated to
      the JVM heap.
  * `reference` is an `IData` document which provides a unique
    identity and the current datetime, which is useful for common
    variable substitution scenarios.
    * `uuid` is a newly generated random UUID.
    * `datetime` is an `IData` document representing the current
      datetime in either the local or UTC timezone.
      * `local` is a special `IData` document which uses the
        requested key as the datetime pattern to use to format the
        current datetime in the local timezone. For example, if you
        try to map the value for a key named `yyyy/MM/dd`, the value
        returned will be the current datetime in the local timezone
        formatted using the pattern `yyyy/MM/dd`.
      * `utc` is a special `IData` document which uses the
        requested key as the datetime pattern to use to format the
        current datetime in the UTC timezone. For example, if you
        try to map the value for a key named `yyyy/MM/dd`, the value
        returned will be the current datetime in the UTC timezone
        formatted using the pattern `yyyy/MM/dd`.

---

### tundra.thread:current

Returns information about the currently executing thread.

#### Outputs:

* `$thread` is an `IData` document containing information about the
  currently executing thread.
  * `id` is the unique identifier of the thread in the current
    execution context.
  * `name` is the human-readable name that was assigned to the thread
    when it was created.
  * `description` is the thread's built-in string representation of
    itself.
  * `dump` is the Integration Server generated dump for this thread.
  * `state` is the thread's current status, and can be one of the
    following values:
    * `NEW` - a thread that has not yet started is in this state.
    * `RUNNABLE` - a thread executing in the Java virtual machine is
      in this state.
    * `BLOCKED` - a thread that is blocked waiting for a monitor lock
      is in this state.
    * `WAITING` - a thread that is waiting indefinitely for another
      thread to perform a particular action is in this state.
    * `TIMED_WAITING` - a thread that is waiting for another thread
      to perform an action for up to a specified waiting time is in
      this state.
    * `TERMINATED` - a thread that has exited is in this state.
  * `priority` is the thread's priority as an integer. Threads with
    higher priority are executed in preference to threads with lower
    priority.
  * `group` is the name of the thread group this thread belongs to.
  * `alive?` is a boolean indicating if the thread is currently
    alive. A thread is alive if it has been started and has not yet
    died.
  * `daemon?` is a boolean indicating if the thread is a daemon
    thread. Daemon threads do not block the JVM from exiting even if
    they are still running.
  * `interrupted?` is a boolean indicating if the thread has been
    interrupted.
  * `stack` is a document list describing the call stack associated
    with this thread.
    * `description` is a string representation of this stack item.
    * `file` is the name of the source file containing executing
      line.
    * `class` is the fully qualified name of the executing class.
    * `method` is the name of the executing method.
    * `line` is the source file line number of the executing line.
    * `native?` is true if the `method` is a native method.
  * `stack.length` is the number of items in the `stack` list.
  * `thread` is the actual [java.lang.Thread] object itself.

---

### tundra.thread:get

Returns the thread with the given identity.

#### Inputs:

* `$thread.id` is the identity of the thread to be returned.

#### Outputs:

* `$thread.exists?` is a boolean indicating whether a thread with the
  given identity exists in the current execution context.
* `$thread` is an `IData` document containing information about the
  thread with the given identity.
  * `id` is the unique identifier of the thread in the current
    execution context.
  * `name` is the human-readable name that was assigned to the thread
    when it was created.
  * `description` is the thread's built-in string representation of
    itself.
  * `dump` is the Integration Server generated dump for this thread.
  * `state` is the thread's current status, and can be one of the
    following values:
    * `NEW` - a thread that has not yet started is in this state.
    * `RUNNABLE` - a thread executing in the Java virtual machine is
      in this state.
    * `BLOCKED` - a thread that is blocked waiting for a monitor lock
      is in this state.
    * `WAITING` - a thread that is waiting indefinitely for another
      thread to perform a particular action is in this state.
    * `TIMED_WAITING` - a thread that is waiting for another thread
      to perform an action for up to a specified waiting time is in
      this state.
    * `TERMINATED` - a thread that has exited is in this state.
  * `priority` is the thread's priority as an integer. Threads with
    higher priority are executed in preference to threads with lower
    priority.
  * `group` is the name of the thread group this thread belongs to.
  * `alive?` is a boolean indicating if the thread is currently
    alive. A thread is alive if it has been started and has not yet
    died.
  * `daemon?` is a boolean indicating if the thread is a daemon
    thread. Daemon threads do not block the JVM from exiting even if
    they are still running.
  * `interrupted?` is a boolean indicating if the thread has been
    interrupted.
  * `stack` is a document list describing the call stack associated
    with this thread.
    * `description` is a string representation of this stack item.
    * `file` is the name of the source file containing executing
      line.
    * `class` is the fully qualified name of the executing class.
    * `method` is the name of the executing method.
    * `line` is the source file line number of the executing line.
    * `native?` is true if the `method` is a native method.
  * `stack.length` is the number of items in the `stack` list.
  * `thread` is the actual [java.lang.Thread] object itself.
* `$threads.length` is the number of items returned in the `$threads`
  list.

---

### tundra.thread:interrupt

Interrupts the thread with the given identity.

#### Inputs:

* `$thread.id` is the identity of the thread to be interrupted.

---

### tundra.thread:list

Returns a list of all threads known in the current execution context.

#### Outputs:

* `$threads` is an `IData[]` document list containing information
  about all the threads known in the current execution context.
  * `id` is the unique identifier of the thread in the current
    execution context.
  * `name` is the human-readable name that was assigned to the thread
    when it was created.
  * `description` is the thread's built-in string representation of
    itself.
  * `dump` is the Integration Server generated dump for this thread.
  * `state` is the thread's current status, and can be one of the
    following values:
    * `NEW` - a thread that has not yet started is in this state.
    * `RUNNABLE` - a thread executing in the Java virtual machine is
      in this state.
    * `BLOCKED` - a thread that is blocked waiting for a monitor lock
      is in this state.
    * `WAITING` - a thread that is waiting indefinitely for another
      thread to perform a particular action is in this state.
    * `TIMED_WAITING` - a thread that is waiting for another thread
      to perform an action for up to a specified waiting time is in
      this state.
    * `TERMINATED` - a thread that has exited is in this state.
  * `priority` is the thread's priority as an integer. Threads with
    higher priority are executed in preference to threads with lower
    priority.
  * `group` is the name of the thread group this thread belongs to.
  * `alive?` is a boolean indicating if the thread is currently
    alive. A thread is alive if it has been started and has not yet
    died.
  * `daemon?` is a boolean indicating if the thread is a daemon
    thread. Daemon threads do not block the JVM from exiting even if
    they are still running.
  * `interrupted?` is a boolean indicating if the thread has been
    interrupted.
  * `stack` is a document list describing the call stack associated
    with this thread.
    * `description` is a string representation of this stack item.
    * `file` is the name of the source file containing executing
      line.
    * `class` is the fully qualified name of the executing class.
    * `method` is the name of the executing method.
    * `line` is the source file line number of the executing line.
    * `native?` is true if the `method` is a native method.
  * `stack.length` is the number of items in the `stack` list.
  * `thread` is the actual [java.lang.Thread] object itself.
* `$threads.length` is the number of items returned in the `$threads`
  list.

---

### tundra.thread:prioritize

Sets the priority of the current thread:

> Every thread has a priority. Threads with higher priority are
> executed in preference to threads with lower priority.

#### Inputs:

* `$thread.priority` is the new priority to set on the current
  thread, an integer value between 1 and 10. Larger values have
  higher priority.

#### Outputs:

* `$thread.priority.previous` is the previous priority of the current
  thread, which can be used to restore priority to its previous value
  if required.

---

### tundra.thread:sleep

Sends the currently executing thread to sleep (temporarily cease
execution) for the specified duration, subject to the precision and
accuracy of system timers and schedulers.

#### Inputs:

* `$duration` is a duration of time specified as a string for which
  the current thread should sleep.
* `$duration.pattern` is an optional pattern describing the type of
  duration specified by `$duration`. Defaults to an [ISO8601] XML
  string.

---

### tundra.thread:stop

Stops the thread with the given identity.

#### Inputs:

* `$thread.id` is the identity of the thread to be stopped.

---

### tundra.timezone:get

Returns the time zone associated with the given ID.

#### Inputs:

* `$id` is a [java.util.TimeZone] ID identifying the time zone to be
  returned, or a `(+|-)HH:mm` time zone offset, or an XML duration
  string representing a time zone offset, or a raw millisecond
  time zone offset, or `Z` for UTC, or `local` for the default
  localhost time zone. If a time zone offset is specified, the first
  time zone that matches the given offset is returned.
* `$datetime` is an optional datetime string identifying the instant
  in time to be used to determine the Universal Coordinated Time
  (UTC) offset and whether Daylight Savings Time (DST) is applicable.
  If not specified, defaults to the current datetime.
* `$datetime.pattern` is an optional datetime pattern that
  `$datetime` conforms to, and will be used to parse the datetime
  string. Defaults to an [ISO8601] XML datetime.

#### Outputs:

* `$timezone` is an `IData` document describing the time zone
  associated with the given `$id`.
  * `id` is the [java.util.TimeZone] ID associated with the time
    zone.
  * `name` is a short name associated with the time zone.
  * `description` is a long name associated with the time zone.
  * `utc.offset` is the historically correct XML duration added to
    UTC time to get local time in this time zone for the given
    `$datetime` instant. If daylight savings time is in effect for
    the given `$datetime` instant, then this value was adjusted to
    include the daylight savings offset.
  * `dst.used?` is a boolean indicating if daylight savings time is
    used by this time zone.
  * `dst.active?` is a boolean indicating if daylight savings time is
    in effect for the given `$datetime` instant.
  * `dst.offset` is the XML duration added to the UTC offset when
    daylight savings time is in effect.

---

### tundra.timezone:list

Returns all time zones known to the Java Virtual Machine.

#### Inputs:

* `$datetime` is an optional datetime string identifying the instant
  in time to be used to determine the Universal Coordinated Time
  (UTC) offset and whether Daylight Savings Time (DST) is applicable.
  If not specified, defaults to the current datetime.
* `$datetime.pattern` is an optional datetime pattern that
  `$datetime` conforms to, and will be used to parse the datetime
  string. Defaults to an [ISO8601] XML datetime.

#### Outputs:

* `$timezones` is an `IData` document list of time zones known to the
  running instance of the Java Virtual Machine.
  * `id` is the [java.util.TimeZone] ID associated with the time
    zone.
  * `name` is a short name associated with the time zone.
  * `description` is a long name associated with the time zone.
  * `utc.offset` is the historically correct XML duration added to
    UTC time to get local time in this time zone for the given
    `$datetime` instant. If daylight savings time is in effect for
    the given `$datetime` instant, then this value was adjusted to
    include the daylight savings offset.
  * `dst.used?` is a boolean indicating if daylight savings time is
    used by this time zone.
  * `dst.active?` is a boolean indicating if daylight savings time is
    in effect for the given `$datetime` instant.
  * `dst.offset` is the XML duration added to the UTC offset when
    daylight savings time is in effect.

---

### tundra.timezone:self

Returns the default time zone for this host.

#### Inputs:

* `$datetime` is an optional datetime string identifying the instant
  in time to be used to determine the Universal Coordinated Time
  (UTC) offset and whether Daylight Savings Time (DST) is applicable.
  If not specified, defaults to the current datetime.
* `$datetime.pattern` is an optional datetime pattern that
  `$datetime` conforms to, and will be used to parse the datetime
  string. Defaults to an [ISO8601] XML datetime.

#### Outputs:

* `$timezone` is an `IData` document describing the default time
  zone.
  * `id` is the [java.util.TimeZone] ID associated with the default
    time zone.
  * `name` is a short name associated with the default time zone.
  * `description` is a long name associated with the default time
    zone.
  * `utc.offset` is the historically correct XML duration added to
    UTC time to get local time in the default time zone for the given
    `$datetime` instant. If daylight savings time is in effect for
    the given `$datetime` instant, then this value was adjusted to
    include the daylight savings offset.
  * `dst.used?` is a boolean indicating if daylight savings time is
    used by this time zone.
  * `dst.active?` is a boolean indicating if daylight savings time is
    in effect for the given `$datetime` instant.
  * `dst.offset` is the XML duration added to the UTC offset when
    daylight savings time is in effect.

---

### tundra.transport:log

Writes transport information returned by `pub.flow:getTransportInfo`
to the server log, or a given named log, as a minified [JSON] string,
optionally prefixed with the invoking user, call stack, and message.

Note that the value of the `Authorization` HTTP header, if any, will
be redacted before writing to the log.

#### Inputs:

* `$log.level` is an optional logging level used when writing the
  transport information to the server log.
* `$log.message` is an optional message to be written to the server
  log.
* `$log.prefix?` is a boolean indicating whether to prefix the log
  statement with the current user and callstack. Defaults to `true`.
* `$log.name` is the optional logical name of the log to write to.
  The logical log name can be mapped to a physical log target file
  via the Tundra package configuration section `feature/log/target`.
  If the logical log name cannot be found in this configuration
  section, the default physical log target is `./logs/server.log`.
  Defaults to the calling service's package name, if not specified.

---

### tundra.ulid:generate

Returns a newly generated [ULID], a 128-bit universally unique
lexicographically sortable identifier base-32 encoded to a 26
character string.

#### Outputs:

* `$id` is a 26 character string representing a newly generated
  [ULID].

---

### tundra.uri:decode

Decodes arbitrarily specified URI-encoded strings formatted as per
MIME media type application/x-www-form-urlencoded according to
[RFC 2396].

The following rules are applied in the conversion:
* The alphanumeric characters `a` through `z`, `A` through `Z` and
  `0` through `9` remain the same.
* The special characters `.`, `-`, `*`, and `_` remain the same.
* The plus sign `+` is converted into a space character.
* A sequence of the form `%xy` will be treated as representing a byte
  where `xy` is the two-digit hexadecimal representation of the 8
  bits. Then, all substrings that contain one or more of these byte
  sequences consecutively will be replaced by the character(s) whose
  encoding would result in those consecutive bytes.

Implemented with the [java.net.URLDecoder] class.

#### Inputs:

* `$document.encoded` is an `IData` document containing arbitrarily
  specified strings of URI-encoded data to be decoded.
* `$content.encoding` is the character set used to determine what
  characters are represented by any consecutive sequences of the
  form `%xy`. Defaults to [UTF-8].

#### Outputs:

* `$document.decoded` contains the decoded strings.

---

### tundra.uri:emit

Emits a Uniform Resource Identifier ([URI]) string, according to
[RFC 2396], given its constituent parts.

URIs can be categorized as either hierarchical, where the scheme and
body parts are separated by the character sequence `://`, or opaque,
where the scheme and body parts are separated by a `:` character.

Examples of hierarchical URIs:

    http://example.com/
    ftp://example.com/path/file.txt

Examples of opaque URIs:

    mailto:john.doe@example.com
    news:comp.lang.java
    urn:isbn:096139210x

Opaque URIs are constructed according to the following syntax:

    (scheme:)body(?query)(#fragment)

Where brackets (...) delineate optional components and the characters
`:`, `?`, and `#` stand for themselves.

Hierarchical URIs are constructed according to the following syntax:

    (scheme:)(//authority)(/path/)(file)(?query)(#fragment)

Where the characters `:`, `/`, `?`, and `#` stand for themselves. The
authority component, if specified, can be either server-based or
registry-based. If server-based, it is constructed according to the
syntax:

    (user:password@)host(:port)

For example, the following components represented using [JSON]:

    {
      "scheme": "http",
      "authority": {
        "server": {
          "user": "bob",
          "password": "secret",
          "host": "example.com",
          "port": "8080"
        }
      },
      "path": ["x", "y", "z"],
      "file": "index.html",
      "query": {
        "a": "1",
        "a": "2",
        "b": "3",
        "c": ["4", "5"],
        "d": {
          "e": "6"
        }
      },
      "fragment": "footer"
    }

Will be emitted as the following [URI]:

    http://bob:secret@example.com:8080/x/y/z/index.html?a=1&a=2&b=3&c%5B0%5D=4&c%5B1%5D=5&d%2Fe=6#footer

This service was implemented with the [java.net.URI] class.

#### Inputs:

* `$uri` is an `IData` document containing the constituent parts to
  construct a new [URI] string with.
  * `scheme` is the optional scheme for this [URI]; for an
    hierarchical [URI] this is typically a protocol such as http.
  * `body` is the scheme-specific part of an opaque [URI].
  * `authority` is the optional authority part of an hierarchical
    [URI].
    * `registry` is the authority for a registry-based hierarchical
      [URI].
    * `server` is the authority for a server-based heirarchical
      [URI].
      * `user` is the optional user component of the [URI] authority.
      * `password` is the optional password component of the [URI]
        authority.
      * `host` is the host or domain name component of the [URI]
        authority.
      * `port` is the port component of the [URI] authority.
  * `path` is the optional path component of the [URI], provided as
    the list of string tokens that were separated by the `/`
    character.
  * `path.absolute?` is an optional boolean which when `true`
    indicates that the path is absolute, and when `false` indicates
    the path is relative. Defaults to `true` if not specified.
  * `file` is the optional file component of the [URI], provided as
    the string token that follows the final `/` character of the path
    component.
  * `query` is an optional `IData` document whose elements are the
    set of [URI] query string key value parameters. Lists are
    supported in query strings as follows: `?a=1&a=2&a=3` is parsed
    to a `String[] = { "1", "2", "3" }`.
  * `fragment` is the optional fragment component of the [URI].

#### Outputs:

* `$string` is the resulting [URI] input string.

---

### tundra.uri:encode

URI encodes arbitrarily specified strings to be formatted as per
MIME media type application/x-www-form-urlencoded according to
[RFC 2396].

The following rules are applied in the conversion:
* The alphanumeric characters `a` through `z`, `A` through `Z` and
  `0` through `9` remain the same.
* The special characters `.`, `-`, `*`, and `_` remain the same.
* The plus sign `+` is converted into a space character.
* A sequence of the form `%xy` will be treated as representing a byte
  where `xy` is the two-digit hexadecimal representation of the 8
  bits. Then, all substrings that contain one or more of these byte
  sequences consecutively will be replaced by the character(s) whose
  encoding would result in those consecutive bytes.

Implemented with the [java.net.URLEncoder] class.

#### Inputs:

* `$document.decoded` is an `IData` document containing arbitrarily
  specified strings to be URI-encoded.
* `$content.encoding` is the character set used to obtain the bytes
  for unsafe characters. Defaults to [UTF-8].

#### Outputs:

* `$document.encoded` contains the URI-encoded strings.

---

### tundra.uri:normalize

Normalizes a URI string.

Refer to the following excerpt from the [java.net.URI normalize]
method for a description of the normalization process:

> If this URI is opaque, or if its path is already in normal form,
> then this URI is returned. Otherwise a new URI is constructed that
> is identical to this URI except that its path is computed by
> normalizing this URI's path in a manner consistent with RFC 2396,
> section 5.2, step 6, sub-steps c through f; that is:
>
> 1. All "." segments are removed.
>
> 2. If a ".." segment is preceded by a non-".." segment then both of
>    these segments are removed. This step is repeated until it is no
>    longer applicable.
>
> 3. If the path is relative, and if its first segment contains a
>    colon character (':'), then a "." segment is prepended. This
>    prevents a relative URI with a path such as "a:b/c/d" from later
>    being re-parsed as an opaque URI with a scheme of "a" and a
>    scheme-specific part of "b/c/d". (Deviation from RFC 2396.)
>
> A normalized path will begin with one or more ".." segments if
> there were insufficient non-".." segments preceding them to allow
> their removal. A normalized path will begin with a "." segment if
> one was inserted by step 3 above. Otherwise, a normalized path will
> not contain any "." or ".." segments.

#### Inputs:

* `$string` is the [URI] to be normalized.

#### Outputs:

* `$string` is the given [URI] in normalized form.

---

### tundra.uri:parse

Parses a Uniform Resource Identifier ([URI]) string, according to
[RFC 2396], to its constituent parts.

URIs can be categorized as either hierarchical, where the scheme and
body parts are separated by the character sequence `://`, or opaque,
where the scheme and body parts are separated by a `:` character.

Examples of hierarchical URIs:

    http://example.com/
    ftp://example.com/path/file.txt

Examples of opaque URIs:

    mailto:john.doe@example.com
    news:comp.lang.java
    urn:isbn:096139210x

Opaque URIs are constructed according to the following syntax:

    (scheme:)body(?query)(#fragment)

Where brackets (...) delineate optional components and the characters
`:`, `?`, and `#` stand for themselves.

Hierarchical URIs are constructed according to the following syntax:

    (scheme:)(//authority)(/path/)(file)(?query)(#fragment)

Where the characters `:`, `/`, `?`, and `#` stand for themselves. The
authority component, if specified, can be either server-based or
registry-based. If server-based, it is constructed according to the
syntax:

    (user:password@)host(:port)

Where the characters `@` and `:` stand for themselves.

Opaque URI bodies are not subject to further parsing, however, to
accomodate the `mailto:` URI's use of a query string for additional
data (such as `cc`, `bcc`, `subject`, and `body`), this service
checks if the body contains a query string and, if so, parses the
query string also.

For example, the following [URI]:

    http://bob:secret@example.com:8080/x/y/z/index.html?a=1&a=2&b=3&c%5B0%5D=4&c%5B1%5D=5&d%2Fe=6#footer

Will be parsed to the following components, represented using [JSON]:

    {
      "scheme": "http",
      "authority": {
        "server": {
          "user": "bob",
          "password": "secret",
          "host": "example.com",
          "port": "8080"
        }
      },
      "path": ["x", "y", "z"],
      "file": "index.html",
      "query": {
        "a": "1",
        "a": "2",
        "b": "3",
        "c": ["4", "5"],
        "d": {
          "e": "6"
        }
      },
      "fragment": "footer"
    }

This service was implemented with the [java.net.URI] class.

#### Inputs:

* `$string` is the a [URI] string to be parsed.

#### Outputs:

* `$uri` is an `IData` document containing the constituent parts of
  the parsed [URI] string.
  * `scheme` is the optional scheme for this [URI]; for an
    hierarchical [URI] this is typically a protocol such as http.
  * `body` is the scheme-specific part of an opaque [URI].
  * `authority` is the optional authority part of an hierarchical
    [URI].
    * `registry` is the authority for a registry-based hierarchical
      [URI].
    * `server` is the authority for a server-based heirarchical
      [URI].
      * `user` is the optional user component of the [URI] authority.
      * `password` is the optional password component of the [URI]
        authority.
      * `host` is the host or domain name component of the [URI]
        authority.
      * `port` is the port component of the [URI] authority.
  * `path` is the optional path component of the [URI], provided as
    the list of string tokens that were separated by the `/`
    character.
  * `path.absolute?` is a boolean which when `true` indicates that
    the path is absolute, and when `false` indicates the path is
    relative. Defaults to `true` if not specified.
  * `file` is the optional file component of the [URI], provided as
    the string token that follows the final `/` character of the
    path component.
  * `query` is an optional `IData` document whose elements are the
    set of [URI] query string key value parameters. Parameter keys
    are treated as fully-qualified `IData` keys.
  * `fragment` is the optional fragment component of the [URI].
  * `absolute?` is a boolean indicating whether the given `$string`
    was an absolute [URI].
  * `opaque?` is a boolean indicating whether the given `$string` was
    an opaque [URI].

---

### tundra.uri:substitute

Performs variable substitution on the components of a Uniform
Resource Identifier ([URI]) string.

Supports both the use of percent-delimited variable substitution
strings where the percent characters are encoded as %25, and the use
of URI template style variables delimited by curly brackets.

For example, either of the following URI strings:

    http://example.com/%25path%25/%25file%25
    http://example.com/{path}/{file}

With the following given scope:

    {
        "path": "a/b/c",
        "file": "d"
    }

Will result in the following substitution:

    http://example.com/a/b/c/d

#### Inputs:

* `$string` is the [URI] string to perform variable substitution
  against.
* `$scope` is an optional `IData` document against which variables
  are resolved. If not specified, the substitution is unscoped, and
  variables are resolved against the pipeline itself.

#### Outputs:

* `$string` is the resulting [URI] string after variable substitution
  was performed against its parsed components.

---

### tundra.user:current

Returns the current Integration Server user used to invoke this
service.

#### Outputs:

* `$user` is the currently logged on user name that invoked this
  service.

---

### tundra.uuid:generate

Generates an immutable universally unique identifier ([UUID]). A
[UUID] represents a 128-bit value.

The layout of a variant 2 (Leach-Salz) [UUID] is as follows. The most
significant long consists of the following unsigned fields:

  0xFFFFFFFF00000000 time_low
  0x00000000FFFF0000 time_mid
  0x000000000000F000 version
  0x0000000000000FFF time_hi

The least significant long consists of the following unsigned fields:

  0xC000000000000000 variant
  0x3FFF000000000000 clock_seq
  0x0000FFFFFFFFFFFF node

The variant field contains a value which identifies the layout of the
[UUID]. The bit layout described above is valid only for a [UUID]
with a variant value of 2, which indicates the Leach-Salz variant.

The version field holds a value that describes the type of this
[UUID]. There are four different basic types of UUIDs: time-based,
DCE security, name-based, and randomly generated UUIDs. These types
have a version value of 1, 2, 3 and 4, respectively.

For more information including algorithms used to create UUIDs, see
the Internet-Draft UUIDs and GUIDs or the standards body definition
at ISO/IEC 11578:1996.

#### Inputs:

* `$mode` is an optional choice which determines how the [UUID] is
  converted to a string. Defaults to `string`, if not specified:
  * `string` returns the [UUID] in the string representation as per
    [RFC 4122], and is the default.
  * `base64` returns the [UUID] as a [Base64] encoded string.

#### Outputs:

* `$id` is the generated [UUID].

---

### tundra.xml:canonicalize

Canonicalizes the given XML content using the given algorithm.
Implemented with the [Apache Santuario] XML Security library.

#### Inputs:

* `$content` is the XML content to be canonicalized, specified as a
  string, byte array, or input stream.
* `$content.encoding` is an optional character set to use when
  `$content` is provided as a byte array or input stream to decode
  the contained text data. Defaults to [UTF-8].
* `$content.mode` is a choice of `bytes`, `stream`, or `string`, and
  determines the type of object `$content.canonical` is returned as.
  Defaults to `stream`.
* `$algorithm` determines the canonicalization algorithm used, and
  is a choice of:
  * [Canonical XML Version 1.0]
  * [Canonical XML Version 1.0 With Comments]
  * [Canonical XML Version 1.1]
  * [Canonical XML Version 1.1 With Comments]
  * [Exclusive Canonical XML Version 1.0]
  * [Exclusive Canonical XML Version 1.0 With Comments]

#### Outputs:

* `$content.canonical` is the given `$content` converted to canonical
  form by the given `$algorithm`.

---

### tundra.xml:decode

[XML] decodes the string values in the given `IData` document.

[XML entities], such as `&lt;` and `&gt;`, are decoded to the
appropriate character representation, such as `<` and `>`.

#### Inputs:

* `$document.encoded` is an optional `IData` document containing
  arbitrarily specified string values to be [XML] decoded.

#### Outputs:

* `$document.decoded` is an `IData` document containing the
  arbitrarily specified string values with [XML entities] decoded.

---

### tundra.xml:emit

Serializes an [org.w3c.dom.Node] object, known as a node in
webMethods parlance, to an [XML] string, byte array, or input stream.

#### Inputs:

* `$node` is an [org.w3c.dom.Node] object to be serialized.
* `$content.encoding` is the character set used when serializing the
  content. Defaults to [UTF-8].
* `$content.mode` is a choice of `bytes`, `stream`, or `string`, and
  determines the type of object `$content` is returned as. Defaults
  to `stream`.

#### Outputs:

* `$content` is the given [org.w3c.dom.Node] object serialized to
  [XML].
* `$content.encoding` is the character set used to serialize the
  content.

---

### tundra.xml:encode

[XML] encodes the string values in the given `IData` document.

Reserved characters in [XML], such as `<` and `>`, are encoded to the
appropriate [XML entity], such as `&lt;` and `&gt;`, to ensure the
[XML] is rendered correctly by web browsers and other [XML] rendering
software.

#### Inputs:

* `$document.decoded` is an optional `IData` document containing
  arbitrarily specified string values to be [XML] encoded.
* `$attribute.prefix` is an optional custom prefix used to denote
  [XML] attributes in the given `IData` document. Defaults to `@`, if
  not specified.

#### Outputs:

* `$document.encoded` is an `IData` document containing the
  arbitrarily specified string values with [XML entities] encoded.

---

### tundra.xml:minify

Removes all comments and extraneous whitespace from the given [XML]
content.

#### Inputs:

* `$content` is a string, byte array, or input stream containing
  [XML] data.
* `$content.encoding` is the character set used by `$content` if
  provided as a byte array or input stream. Defaults to [UTF-8].
* `$content.mode` is a choice of `bytes`, `stream`, or `string`, and
  determines the type of object `$content.minified` is returned as.
  Defaults to `stream`.

#### Outputs:

* `$content.minified` is the given [XML] content with all comments
  and extraneous whitespace removed.

---

### tundra.xml.namespace:normalize

Normalizes namespace prefixes to use the prefix with the highest
precedence in the given declared namespaces.

#### Inputs:

* `$document.operands` contains arbitrarily specified keys and/or
  values to be normalized.
* `$document.namespace` specifies namespace prefixes mapped to URIs.
* `$document.recurse?` is an optional boolean indicating if child
  `IData` documents and `IData[]` document lists should also be
  recursively processed. Defaults to `false`.
* `$document.mode` is an optional choice for what should be converted:
  either the values, the keys, or both the keys and the values.
  Defaults to values.

#### Outputs:

* `$document.normalized` contains the given keys and/or values with
  namespace prefixes normalized.

---

### tundra.xml:parse

Parses [XML] content into an `IData` document.

This service is not intended as a replacement for
`pub.xml:xmlNodeToDocument`. However, unlike that service, this
service can parse any implementation of the [org.w3c.dom.Node]
interface, not just the standard webMethods implementation class
`com.wm.lang.xml.Node`.

TODO: optionally reference an [XML] schema so that elements with a
`maxOccurs` of `unbounded` can be correctly represented as lists.

#### Inputs:

* `$content` is a string, byte array, input stream, [org.w3c.dom.Node]
  object (node in webMethods parlance), or [org.xml.sax.InputSource]
  object containing [XML] data.
* `$content.encoding` is an optional character set to use when
  `$content` is provided as a byte array or input stream to decode
  the contained text data. Defaults to [UTF-8].
* `$content.namespace` is an optional list of namespace prefixes and
  the URIs they map to, used when parsing [XML] content with elements
  in one or more namespaces.
  * `default` is the [URI] for the default namespace, if applicable.

#### Outputs:

* `$document` is the resulting `IData` document representing the parsed
  `$content`.

---

### tundra.xml:validate

Validates the given content as [XML], and optionally against an [XML]
schema ([XSD]).

Uses the Simple API for XML ([SAX]) algorithm for parsing which, as
it is event-based, is not memory-constrained and can handle
arbitrarily large documents when parsing from an input stream.

#### Inputs:

* `$content` is a string, byte array, or input stream potentially
  containing [XML] data.
* `$content.encoding` is the character set used by `$content` if
  provided as a byte array or input stream. Defaults to [UTF-8].
* `$schema` is a string, byte array, or input stream containing [XSD]
  data.
* `$schema.encoding` is the character set used by `$schema` if
  provided as a byte array or input stream. Defaults to [UTF-8].
* `$raise?` is an optional boolean flag, if `true` and `$content` is
  malformed or invalid an exception will be thrown. If `false`, no
  exception will be thrown. Defaults to `false`.

#### Outputs:

* `$valid?` is `true` if the given `$content` is well-formed [XML]
  and, if a `$schema` was specified, valid when compared to the given
  `$schema`.
* `$errors` is an optional list of the errors detected by the parser
  in the given `$content`, provided when `$valid?` is `false`.

---

### tundra.xpath:exists

Returns `true` if the given [XPath expression] exists the given [XML]
content.

#### Inputs:

* `$content` is a string, byte array, input stream,
  [org.w3c.dom.Node] object (node in webMethods parlance), or
  [org.xml.sax.InputSource] object containing [XML] data.
* `$content.encoding` is the character set used by `$content` if
  provided as a byte array or input stream. Defaults to [UTF-8].
* `$content.namespace` is an optional list of namespace prefixes and
  the URIs they map to, used when parsing [XML] content with elements
  in one or more namespaces.
  * `default` is the URI for the default namespace, if applicable.
* `$expression` is the [XPath expression] to be tested against
  `$content`.

#### Outputs:

* `$exists?` is `true` if the given `$expression` was found to exist
  in the given `$content` (in other words the expression returned one
  or more [org.w3c.dom.Node] objects when evaluated).

---

### tundra.xpath:get

Returns the [XML] nodes associated with the given [XPath expression]
when resolved against the given [XML] content.

#### Inputs:

* `$content` is a string, byte array, input stream,
  [org.w3c.dom.Node] object (node in webMethods parlance), or
  [org.xml.sax.InputSource] object containing [XML] data.
* `$content.encoding` is the character set used by `$content` if
  provided as a byte array or input stream. Defaults to [UTF-8].
* `$content.namespace` is an optional list of namespace prefixes and
  the URIs they map to, used when parsing [XML] content with elements
  in one or more namespaces.
  * `default` is the URI for the default namespace, if applicable.
* `$expression` is the [XPath expression] to be resolved against
  `$content`.
* `$recurse?` is an optional boolean which when `true` will
  recursively return the child elements for the selected nodes.
  Defaults to `false`.

#### Outputs:

* `$nodes` is the list of [XML] nodes assocated with the the given
  `$expression` when resolved against the given `$content`.
  * `node` is a [org.w3c.dom.Node] object.
  * `name.qualified` is the qualified name or [QName] of this node.
  * `name.local` is the optional local part of the qualified name of
    this node, and is only provided when the node type is
    `ATTRIBUTE_NODE` or `ELEMENT_NODE`.
  * `name.prefix` is the optional namespace prefix for this node, and
    is only provided when the node type is `ATTRIBUTE_NODE` or
    `ELEMENT_NODE` and the node is in a namespace other than the
    default.
  * `name.uri` is the optional namespace [URI] for this node, and
    is only provided when the node type is `ATTRIBUTE_NODE` or
    `ELEMENT_NODE` and the node is in a namespace other than the
    default.
  * `type` is the type of this node, and is one of the following
    values:
    * `ATTRIBUTE_NODE`
    * `CDATA_SECTION_NODE`
    * `COMMENT_NODE`
    * `DOCUMENT_FRAGMENT_NODE`
    * `DOCUMENT_NODE`
    * `DOCUMENT_TYPE_NODE`
    * `ELEMENT_NODE`
    * `ENTITY_NODE`
    * `ENTITY_REFERENCE_NODE`
    * `NOTATION_NODE`
    * `PROCESSING_INSTRUCTION_NODE`
    * `TEXT_NODE`
  * `value` is the optional text content associated with the node, if
    any.
  * `attributes` is an optional list of attribute nodes associated
    with this node.
  * `elements` is an optional list of child element nodes associated
    with this node, only provided when `$recurse` is `true`.

---

### tundra.yaml:emit

Serializes an `IData` document as a [YAML] formatted string, byte
array, or input stream.

#### Inputs:

* `$document` is the `IData` document to be serialized as a [YAML]
  string, byte array, or input stream.
  * `recordWithNoID` is an optional object or array, which can be
    used to emit arbitrary objects or arrays at the top-level of the
    resulting [YAML] content.
* `$content.encoding` is an optional character set to use when
  encoding the resulting text data to a byte array or input stream.
  Defaults to [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or
  `string` which specifies the type of object `$content` is returned
  as. Defaults to `stream`.

#### Outputs:

* `$content` is the resulting serialization of `$document` as [YAML]
  content.

---

### tundra.yaml:parse

Parses [YAML] content specified as a string, byte array, or input
stream into an `IData` document.

#### Inputs:

* `$content` is a string, byte array, or input stream containing
  [YAML] content to be parsed.
* `$content.encoding` is an optional character set to use when
  `$content` is provided as a byte array or input stream to decode
  the contained text data. Defaults to [UTF-8].

#### Outputs:

* `$document` is the resulting `IData` document representing the
  parsed `$content`.
  * `recordWithNoID` is an optional object or array, present only
    when the top-level of the given [YAML] content is not a key value
    map.

---

### tundra.zip:compress

Compresses the given contents using the [zip] file compression
format.

#### Inputs:

* `$contents` is an `IData` document list containing the data to be
  compressed.
  * `name` is the file path and name given to this item in the
    resulting zip archive.
  * `content` is the data to be compressed, specified as either a
    string, byte array, or input stream.
  * `encoding` is an optional character set used when `content` is
    specified as a string. Defaults to [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or
  `string`, which determines the type of object `$contents.zip` is
  returned as. Defaults to `stream`.

#### Outputs:

* `$contents.zip` is the resulting compressed data in [zip] format.

---

### tundra.zip:decompress

Decompresses the given content using the [zip] file compression
format.

#### Inputs:

* `$contents.zip` is the [zip] compressed data to be decompressed,
  specified as a base64-encoded string, byte array, or input stream.
* `$content.encoding` is an optional character set used to decode the
  decompressed data when the chosen `$content.mode` is `string`.
  Defaults to [UTF-8].
* `$content.mode` is an optional choice of `stream`, `bytes`, or
  `string`, which determines the type of object `$contents/content`
  is returned as. Defaults to `stream`.

#### Outputs:

* `$contents` is an `IData` document list containing the resulting
  decompressed data.
  * `name` is the file path and name assigned to this item in the
    zip archive.
  * `content` is the decompressed data associated with this item.
  * `length` is the size of `content` in bytes.

[Apache Santuario]: <http://santuario.apache.org/>
[Base64]: <http://en.wikipedia.org/wiki/Base64>
[Canonical XML Version 1.0]: <http://www.w3.org/TR/2001/REC-xml-c14n-20010315>
[Canonical XML Version 1.0 With Comments]: <http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments>
[Canonical XML Version 1.1]: <http://www.w3.org/2006/12/xml-c14n11>
[Canonical XML Version 1.1 With Comments]: <http://www.w3.org/2006/12/xml-c14n11#WithComments>
[capturing group]: <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html#cg>
[capturing groups]: <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html#cg>
[catch block]: <http://docs.oracle.com/javase/tutorial/essential/exceptions/catch.html>
[compareTo]: <http://docs.oracle.com/javase/6/docs/api/java/lang/String.html#compareTo(java.lang.String)>
[compareToIgnoreCase]: <http://docs.oracle.com/javase/6/docs/api/java/lang/String.html#compareToIgnoreCase(java.lang.String)>
[CSV]: <http://en.wikipedia.org/wiki/Comma-separated_values>
[default charset]: <http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html#defaultCharset()>
[default locale]: <http://docs.oracle.com/javase/6/docs/api/java/util/Locale.html#getDefault()>
[DNS]: <http://en.wikipedia.org/wiki/Domain_Name_System>
[event loop]: <http://en.wikipedia.org/wiki/Event_loop>
[Exclusive Canonical XML Version 1.0]: <http://www.w3.org/2001/10/xml-exc-c14n#>
[Exclusive Canonical XML Version 1.0 With Comments]: <http://www.w3.org/2001/10/xml-exc-c14n#WithComments>
[finally block]: <http://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html>
[gzip]: <http://en.wikipedia.org/wiki/Gzip>
[Hjson]: <http://hjson.org/>
[HJSON]: <http://hjson.org/>
[HMAC]: <https://en.wikipedia.org/wiki/HMAC>
[HTML]: <http://en.wikipedia.org/wiki/HTML>
[HTML entities]: <http://www.w3.org/TR/html4/sgml/entities.html>
[HTML entity]: <http://www.w3.org/TR/html4/sgml/entities.html>
[HTTP request method]: <http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html>
[HTTP response code]: <http://httpstatus.es/>
[HTTP status code]: <http://www.iana.org/assignments/http-status-codes/http-status-codes.txt>
[HTTP]: <http://tools.ietf.org/search/rfc2616>
[IData XML]: <http://documentation.softwareag.com/webmethods/wmsuites/wmsuite8-2_sp2/Integration_Server/8-2-SP1_Integration_Server_Java_API_Reference/com/wm/util/coder/IDataXMLCoder.html>
[ISO8601]: <http://en.wikipedia.org/wiki/ISO_8601>
[JAR]: <http://en.wikipedia.org/wiki/JAR_(file_format)>
[java.io.ByteArrayInputStream]: <http://docs.oracle.com/javase/6/docs/api/java/io/ByteArrayInputStream.html>
[java.io.InputStream]: <http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html>
[java.io.OutputStream]: <http://docs.oracle.com/javase/6/docs/api/java/io/OutputStream.html>
[java.lang.Boolean]: <http://docs.oracle.com/javase/6/docs/api/java/lang/Boolean.html>
[java.lang.Byte]: <http://docs.oracle.com/javase/6/docs/api/java/lang/Byte.html>
[java.lang.Double]: <https://docs.oracle.com/javase/6/docs/api/java/lang/Double.html>
[java.lang.Float]: <https://docs.oracle.com/javase/6/docs/api/java/lang/Float.html>
[java.lang.Integer]: <http://docs.oracle.com/javase/6/docs/api/java/lang/Integer.html>
[java.lang.Long]: <http://docs.oracle.com/javase/6/docs/api/java/lang/Long.html>
[java.lang.Number]: <https://docs.oracle.com/javase/6/docs/api/java/lang/Number.html>
[java.lang.Short]: <http://docs.oracle.com/javase/6/docs/api/java/lang/Short.html>
[java.lang.String]: <http://docs.oracle.com/javase/6/docs/api/java/lang/String.html>
[java.lang.StringBuilder]: <https://docs.oracle.com/javase/6/docs/api/java/lang/StringBuilder.html>
[java.lang.System.identityHashCode()]: <http://docs.oracle.com/javase/6/docs/api/java/lang/System.html#identityHashCode(java.lang.Object)>
[java.lang.System.nanoTime()]: <https://docs.oracle.com/javase/6/docs/api/java/lang/System.html#nanoTime()>
[java.lang.Thread]: <https://docs.oracle.com/javase/6/docs/api/java/lang/Thread.html>
[java.lang.Throwable]: <http://docs.oracle.com/javase/6/docs/api/java/lang/Throwable.html>
[java.math.BigDecimal]: <http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html>
[java.math.BigDecimal grammar]: <http://docs.oracle.com/javase/6/docs/api/java/math/BigDecimal.html#BigDecimal(java.lang.String)>
[java.math.BigInteger]: <http://docs.oracle.com/javase/6/docs/api/java/math/BigInteger.html>
[java.net.URI]: <http://docs.oracle.com/javase/6/docs/api/java/net/URI.html>
[java.net.URI normalize]: <http://docs.oracle.com/javase/6/docs/api/java/net/URI.html#normalize()>
[java.net.URLDecoder]: <http://docs.oracle.com/javase/6/docs/api/java/net/URLDecoder.html>
[java.net.URLEncoder]: <http://docs.oracle.com/javase/6/docs/api/java/net/URLEncoder.html>
[java.security.SecureRandom]: <https://docs.oracle.com/javase/6/docs/api/java/security/SecureRandom.html>
[java.text.DecimalFormat]: <http://docs.oracle.com/javase/6/docs/api/java/text/DecimalFormat.html>
[java.text.SimpleDateFormat]: <http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html>
[java.util.Calendar]: <http://docs.oracle.com/javase/6/docs/api/java/util/Calendar.html>
[java.util.Date]: <http://docs.oracle.com/javase/6/docs/api/java/util/Date.html>
[java.util.Formatter format string]: <http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax>
[java.util.Formatter format strings]: <http://docs.oracle.com/javase/6/docs/api/java/util/Formatter.html#syntax>
[java.util.List]: <http://docs.oracle.com/javase/6/docs/api/java/util/List.html>
[java.util.Map]: <https://docs.oracle.com/javase/6/docs/api/java/util/Map.html>
[java.util.TimeZone]: <http://docs.oracle.com/javase/6/docs/api/java/util/TimeZone.html>
[java.util.concurrent.ConcurrentMap]: <https://docs.oracle.com/javase/6/docs/api/java/util/concurrent/ConcurrentMap.html>
[java.util.regex.Pattern]: <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html>
[javax.activation.MimeType]: <http://docs.oracle.com/javase/6/docs/api/javax/activation/MimeType.html>
[javax.jms.BytesMessage]: <http://docs.oracle.com/javaee/1.4/api/javax/jms/BytesMessage.html>
[Java array class name]: <http://docs.oracle.com/javase/tutorial/reflect/special/arrayComponents.html>
[Java array class names]: <http://docs.oracle.com/javase/tutorial/reflect/special/arrayComponents.html>
[JMS]: <http://en.wikipedia.org/wiki/Java_Message_Service>
[JSON]: <http://www.json.org/>
[LICENSE]: <https://raw.githubusercontent.com/Permafrost/Tundra/master/LICENSE.txt>
[Locale]: <http://docs.oracle.com/javase/6/docs/api/java/util/Locale.html>
[map function]: <http://en.wikipedia.org/wiki/Map_(higher-order_function)>
[mime type]: <http://en.wikipedia.org/wiki/Internet_media_type>
[MIME]: <http://en.wikipedia.org/wiki/MIME>
[natural ordering]: <http://docs.oracle.com/javase/6/docs/api/java/lang/Comparable.html>
[Object.toString()]: <http://docs.oracle.com/javase/6/docs/api/java/lang/Object.html#toString()>
[org.w3c.dom.Document]: <http://docs.oracle.com/javase/6/docs/api/org/w3c/dom/Document.html>
[org.w3c.dom.Node]: <http://docs.oracle.com/javase/6/docs/api/org/w3c/dom/Node.html>
[org.xml.sax.InputSource]: <http://docs.oracle.com/javase/6/docs/api/org/xml/sax/InputSource.html>
[primitive type]: <http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html#isPrimitive()>
[QName]: <https://en.wikipedia.org/wiki/QName>
[radix]: <http://en.wikipedia.org/wiki/Radix>
[regular expression pattern]: <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html>
[releases]: <https://github.com/Permafrost/Tundra/releases>
[RFC 2045]: <http://www.ietf.org/rfc/rfc2045.txt>
[RFC 2046]: <http://www.ietf.org/rfc/rfc2046.txt>
[RFC 2396]: <http://www.ietf.org/rfc/rfc2396.txt>
[RFC 4122]: <http://www.ietf.org/rfc/rfc4122.txt>
[rounding algorithm]: <http://docs.oracle.com/javase/6/docs/api/java/math/RoundingMode.html>
[SAX]: <http://en.wikipedia.org/wiki/Simple_API_for_XML>
[set intersection]: <http://en.wikipedia.org/wiki/Intersection_(set_theory)>
[SFTP]: <https://en.wikipedia.org/wiki/SSH_File_Transfer_Protocol>
[SOAP]: <https://en.wikipedia.org/wiki/SOAP>
[SQL GROUP BY]: <http://en.wikipedia.org/wiki/SQL#Queries>
[tar]: <https://en.wikipedia.org/wiki/Tar_(computing)>
[thread]: <https://docs.oracle.com/javase/6/docs/api/java/lang/Thread.html>
[try block]: <http://docs.oracle.com/javase/tutorial/essential/exceptions/try.html>
[TSV]: <http://en.wikipedia.org/wiki/Tab-separated_values>
[Tundra]: <https://github.com/Permafrost/Tundra>
[TundraTest]: <https://github.com/Permafrost/TundraTest>
[TundraTN]: <https://github.com/Permafrost/TundraTN>
[Tundra.java]: <https://github.com/Permafrost/Tundra.java>
[TundraCSV.java]: <https://github.com/Permafrost/TundraCSV.java>
[TundraHTML.java]: <https://github.com/Permafrost/TundraHTML.java>
[TundraHTTP.java]: <https://github.com/Permafrost/TundraHTTP.java>
[TundraJSON.java]: <https://github.com/Permafrost/TundraJSON.java>
[TundraXML.java]: <https://github.com/Permafrost/TundraXML.java>
[TundraYAML.java]: <https://github.com/Permafrost/TundraYAML.java>
[ULID]: <https://github.com/alizain/ulid>
[URI]: <http://www.w3.org/Addressing/>
[URI template]: <https://tools.ietf.org/html/rfc6570>
[UTF-8]: <http://en.wikipedia.org/wiki/UTF-8>
[UUID]: <http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html>
[webMethods Integration Server]: <http://www.softwareag.com/corporate/products/wm/integration/products/ai/overview/default.asp>
[webMethods Trading Networks]: <http://www.softwareag.com/corporate/products/wm/integration/products/b2b/overview/default.asp>
[XML]: <http://www.w3.org/XML/>
[XML entities]: <http://www.w3.org/TR/html4/sgml/entities.html>
[XML entity]: <http://www.w3.org/TR/html4/sgml/entities.html>
[XPath]: <http://www.w3.org/TR/xpath/>
[XPath expression]: <http://www.w3.org/TR/xpath/>
[XSD]: <http://www.w3.org/XML/Schema>
[YAML]: <http://www.yaml.org>
[zip]: <http://en.wikipedia.org/wiki/Zip_(file_format)>
[/dev/null]: <http://en.wikipedia.org/wiki//dev/null>
