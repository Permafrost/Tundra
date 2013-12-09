# Tundra ❄

A package of useful services for webMethods Integration Server 7.1 or higher.

## Dependencies

Tundra is dependent on the following packages:

* WmFlatFile
* WmPublic
* WmRoot

## Installation

You have two choices for installing Tundra: git or zip. If you are comfortable
using git, I recommend this method as you can then easily switch between
package versions using git checkout and download new versions using git fetch.

### Using Git

To install with this method, first make sure that:
* Git is [installed](http://git-scm.com/downloads) on your Integration Server.
* Your Integration Server has internet access to https://github.com (for
  cloning the repository).
* The dependent packages listed above are installed and enabled on your
  Integration Server.

From your Integration Server installation:

```sh
$ cd ./packages/
$ git clone https://github.com/Permafrost/Tundra.git
$ cd ./Tundra/
$ git checkout v<n.n.n> # where <n.n.n> is the desired version
```

Then activate and enable the Tundra package from the package management web
page on your Integration Server web administration site.

### Using Zip

1.  Download a zip of the desired version of the package from
    https://github.com/Permafrost/Tundra/releases.
2.  Copy the Tundra-n.n.n.zip file to your Integration Server's
    `./replicate/inbound/` directory.
3.  Install and activate the Tundra package release (Tundra-n.n.n.zip) from
    the package management web page on your Integration Server's web
    administration site.

## Upgrading

When upgrading you have to choose the same method used to originally install
the package. Unfortunately, if git wasn't used to install the package then you
can't use git to upgrade it either. However, if you want to switch to using
git to manage the package, delete the installed package and start over using
the git method for installation.

### Using Git

To upgrade with this method, first make sure that:
* Git is [installed](http://git-scm.com/downloads) on your Integration Server.
* Your Integration Server has internet access to https://github.com (for
  fetching updates from the repository).
* The dependent packages listed above are installed and enabled on your
  Integration Server.
* You originally installed Tundra using the git method described above.

From your Integration Server installation:

```sh
$ cd ./packages/Tundra/
$ git fetch
$ git checkout v<n.n.n> # where <n.n.n> is the desired updated version
```

Then reload the Tundra package from the package management web page on your
Integration Server web administration site.

### Using Zip

1.  Download a zip of the desired updated version of the package from
    https://github.com/Permafrost/Tundra/releases.
2.  Copy the Tundra-n.n.n.zip file to your Integration Server's
    `./replicate/inbound/` directory.
3.  Install and activate the updated Tundra package release (Tundra-n.n.n.zip)
    from the package management web page on your Integration Server's web
    administration site.

## Conventions

1.  All input and output pipeline arguments are prefixed with '$' as a poor-
    man's scoping mechanism (typical user-space variables will be unprefixed).
2.  All boolean arguments are suffixed with a '?'.
3.  Single-word argument names are preferred. Where multiple words are
    necessary, words are separated with a '.'.
4.  Service namespace is kept flat. Namespace folders are usually nouns.
    Service names are usually verbs, indicating the action performed on the
    noun (parent folder).
5.  All private elements are kept in the tundra.support folder. All other
    elements comprise the public API of the package. As the private elements
    do not contribute to the public API, they are liable to change at any
    time. Enter at your own risk.
6.  *Almost* all services are written in Java, and are *almost* always
    overloaded by a backing method in the shared source, which provides the
    actual implementation. This way, backing methods can be used by other
    backing methods directly, without needing to deal with the
    IData/IDataCursor/IDataUtil nastiness that a fronting Java service is
    usually required to deal with.

## Tests

*Almost* every service in Tundra has unit tests, located in the [TundraTest]
package. To run the test suite first install the [TundraTest] package, and
then either:

* Run the `Tundra/tundra:test($package = "TundraTest")` service directly, or
* Visit <http://localhost:5555/invoke/tundra/test?$package=TundraTest>
  (substitute your own Integration Server host and port for localhost:5555).

[TundraTest]: <https://github.com/Permafrost/TundraTest>

## Services

Top-level services for the most common tasks.

* #### tundra:log

  Writes a message to the server log, automatically prefixed with the call
  stack.

  * Inputs:
    * `$message` is the message to be written to the server log.
    * `$level` is the logging level of the message, one of {Fatal, Error,
      Warn, Info, Debug, Trace, Off}.

* #### tundra:test

  Runs all *test*:should* services in the given package, returning the test
  results (a test case passes if no exceptions are thrown).

  * Inputs:
    * `$package` is the name of the package which contains the test cases to
      be executed.

### Assertions

Supports unit testing by providing the following bare-bones assertion services.

* #### tundra.assertion.datetime:equal

  Throws an assertion error if the expected and actual datetimes are not equal.

  * Inputs:
    * `$expected` is the expected datetime string value.
    * `$actual` is the actual datetime string value. If this value is not
      equal to the expected value, an assertion error will be thrown.
    * `$pattern` is an optional datetime string pattern the above values
      conform to, which defaults to an ISO8601/XML datetime pattern. Can
      either be a [java.text.SimpleDateFormat](http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html) pattern, or one of the following handful of well-known named patterns:
      * `datetime`       - ISO8601/XML datetime
      * `datetime.jdbc`  - yyyy-MM-dd HH:mm:ss.SSS
      * `date`           - ISO8601/XML date
      * `date.jdbc`      - yyyy-MM-dd
      * `time`           - ISO8601/XML time
      * `time.jdbc`      - HH:mm:ss
      * `milliseconds`   - The number of milliseconds since the Epoch,
                           January 1, 1970 00:00:00.000 GMT (Gregorian)
    * `$message` is an optional custom message to be used as the assertion error message if the assertion fails.

* #### tundra.assertion.datetime:unequal

  Throws an assertion error if the expected and actual datetimes are equal.

  * Inputs:
    * `$expected` is the expected datetime string value.
    * `$actual` is the actual datetime string value. If this value is equal to the expected value, an assertion error will be thrown.
    * `$pattern` is an optional datetime string pattern the above values
      conform to, which defaults to an ISO8601/XML datetime pattern. Can
      either be a [java.text.SimpleDateFormat] pattern, or one of the
      following handful of well-known named patterns:
      * `datetime`       - ISO8601/XML datetime
      * `datetime.jdbc`  - yyyy-MM-dd HH:mm:ss.SSS
      * `date`           - ISO8601/XML date
      * `date.jdbc`      - yyyy-MM-dd
      * `time`           - ISO8601/XML time
      * `time.jdbc`      - HH:mm:ss
      * `milliseconds`   - The number of milliseconds since the Epoch,
                           January 1, 1970 00:00:00.000 GMT (Gregorian)
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.document:equal

  Throws an assertion error if the expected and actual documents are not equal.

  * Inputs:
    * `$expected` is the expected IData document.
    * `$actual` is the actual IData document. If this document is not equal to
      the expected document, an assertion error will be thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.document:unequal

  Throws an assertion error if the expected and actual documents are equal.

   * Inputs:
      * `$expected` is the expected IData document.
      * `$actual` is the actual IData document. If this document is equal to
        the expected document, an assertion error will be thrown.
      * `$message` is an optional custom message to be used as the assertion
        error message if the assertion fails.

* #### tundra.assertion.list.document:equal

  Throws an assertion error if the expected and actual document lists are not
  equal.

  * Inputs:
    * `$expected` is the expected IData document list.
    * `$actual` is the actual IData document list. If this document list is
      not equal to the expected document list, an assertion error will be
      thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.list.document:unequal

  Throws an assertion error if the expected and actual document lists are
  equal.

  * Inputs:
    * `$expected` is the expected IData document list.
    * `$actual` is the actual IData document list. If this document list is
      equal to the expected document list, an assertion error will be thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.list.object:equal

  Throws an assertion error if the expected and actual lists are not equal.

  * Inputs:
    * `$expected` is the expected java.lang.Object list.
    * `$actual` is the actual java.lang.Object list. If this list is not equal
      to the expected list, an assertion error will be thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.list.object:exists

  Throws an assertion error if the given list is null.

  * Inputs:
    * `$list` is the java.lang.Object list to assert existence of.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.list.object:instance

  Throws an assertion error if the given list is not an instance of the given
  class.

  * Inputs:
    * `$list` is the java.lang.Object list to be tested as an instance of the
      specified class.
    * `$class` is the Java array class name the given list is asserted to be
      an instance of. Note that Java array class names are [different and
      distinct][jacn] to normal Java object class names. For example, an array
      of java.lang.String objects has the class name `[Ljava.lang.String;`.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

[jacn]: <http://docs.oracle.com/javase/tutorial/reflect/special/arrayComponents.html>

* #### tundra.assertion.list.object:nothing

  Throws an assertion error if the given list is not null.

  * Inputs:
    * `$list` is the java.lang.Object list expected to be null.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.list.object:unequal

  Throws an assertion error if the expected and actual lists are equal.

  * Inputs:
    * `$expected` is the expected object list.
    * `$actual` is the actual object list. If this list is equal to the
      expected list, an assertion error will be thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.list.string:equal

  Throws an assertion error if the expected and actual string lists are not
  equal.

  * Inputs:
    * `$expected` is the expected string list.
    * `$actual` is the actual string list. If this list is not equal to the
      expected list, an assertion error will be thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.list.string:unequal

  Throws an assertion error if the expected and actual string lists are equal.

  * Inputs:
    * `$expected` is the expected string list.
    * `$actual` is the actual string list. If this list is equal to the
      expected list, an assertion error will be thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.object:equal

  Throws an assertion error if the expected and actual objects are not equal.

  * Inputs:
    * `$expected` is the expected object value.
    * `$actual` is the actual object value. If this value is not equal to the
      expected value, an assertion error will be thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.object:exists

  Throws an assertion error if the given object is null.

  * Inputs:
    * `$object` is the object expected to not be null.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.object:instance

  Throws an assertion error if the given object is not an instance of the
  given class.

  * Inputs:
    * `$object` is the object to be tested as an instance of the specified
      class.
    * `$class` is the Java class name the given object is asserted to be an
      instance of.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.object:nothing

  Throws an assertion error if the given object is not null

  * Inputs:
    * `$object` is the object that is expected to be null.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.object:unequal

  Throws an assertion error if the expected and actual objects are equal.

  * Inputs:
    * `$expected` is the expected object.
    * `$actual` is the actual object. If this object is equal to the expected
      object, an assertion error will be thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.step:unreached

  Throws an assertion error if this service is executed.

  * Inputs:
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.string:equal

  Throws an assertion error if the expected and actual strings are not equal.

  * Inputs:
    * `$expected` is the expected string value.
    * `$actual` is the actual string value. If this value is not equal to the
      expected value, an assertion error will be thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

* #### tundra.assertion.string:unequal

  Throws an assertion error if the expected and actual strings are equal.

  * Inputs:
    * `$expected` is the expected string value.
    * `$actual` is the actual string value. If this value is equal to the
      expected value, an assertion error will be thrown.
    * `$message` is an optional custom message to be used as the assertion
      error message if the assertion fails.

### Base64

Services for encoding and decoding base64 strings.

* #### tundra.base64:decode

  [Base64] decodes the given string, byte array, or input stream.

  * Inputs:
    * `$base64` is either a base-64 encoded string, byte array, or input
      stream to be decoded.
    * `$encoding` is the optional character set used to decode the text data
      when `$base64` is provided as a byte array or input stream. Defaults to
      the Java virtual machine [default charset]. Not used when `$base64` is
      provided as a string.
    * `$mode` is an optional choice of {stream, bytes, string} which
      determines the type of the output `$content` object. Defaults to stream.
  * Outputs:
    * `$content` is the base-64 decoded data as a string, byte array, or input
      stream (depending on the `$mode` chosen).

* #### tundra.base64:encode

  [Base64] encodes the given string, byte array, or input stream.

  * Inputs:
    * `$content` is either a string, byte array, or input stream containing
      data to be base-64 encoded.
    * `$encoding` is the optional character set used to decode the text data
      when `$content` is provided as a byte array or input stream. Defaults to
      the Java virtual machine [default charset]. Not used when `$base64` is
      provided as a string.
    * `$mode` is an optional choice of {stream, bytes, string} which
      determines the type of the output `$base64` object. Defaults to stream.
  * Outputs:
    * `$base64` is the base-64 encoded data as a string, byte array, or input
      stream (depending on the `$mode` chosen).

[Base64]: <http://en.wikipedia.org/wiki/Base64>

### Boolean

Services for manipulating boolean values.

* #### tundra.bool:emit

  Converts the given `$boolean` value to a string using the appropriate string
  values specified for true and false.

  * Inputs:
    * `$boolean` is the value to be converted.
    * `$value.true` is the value returned if `$boolean` is true.
    * `$value.false` is the value returned if `$boolean` is false.
  * Outputs:
    * `$string` is the converted boolean value.

* #### tundra.bool:negate

  Returns the negated canonical string form for the given `$boolean` string:
  either "true" or "false".

  * Inputs:
    * `$boolean` is the value to be negated.
  * Outputs:
    * `$boolean` is the negated input value.

* #### tundra.bool:normalize

  Returns the canonical string form for the given $boolean string: either
  "true" or "false.

  If `$boolean` is null and `$default` is not null, then `$default`'s boolean
  value will be returned.

  If `$boolean` is null and `$default` is null, then "false" will be returned.

  * Inputs:
    * `$boolean` is the value to be normalized.
    * `$default` is the value to use if `$boolean` is null.
  * Outputs:
    * `$boolean` is the normalized input value, guaranteed to be either the
      string "true" or "false".

### Bytes

Services for manipulating byte arrays:

* #### tundra.bytes:length

  Returns the length of the given byte array.

  * Inputs:
    * `$bytes` must be a byte array.
  * Outputs:
    * `$length` is the length of the given byte array.

* #### tundra.bytes:normalize

  Converts a string, byte array or input stream to a byte array.

  * Inputs:
    * `$object` is the string, byte array or input stream to be normalized.
    * `$encoding` is the optional character set to use to encode `$object`
      when it is a string. Defaults to the Java virtual machine [default
      charset].
  * Outputs:
    * `$bytes` is a byte array representation of the input `$object` data.

### Condition

Services for evaluating conditional statements.

* #### tundra.condition:evaluate

  Evaluates the given condition against the pipeline (or optional scope IData
  document).

  * Inputs:
    * `$condition` is the conditional statement to be evaluated. Conditional
      statements have the following form:

          <condition> = <key> == <key>   [and|or <condition>]
                      | <key> != <key>   [and|or <condition>]
                      | <key> == <value> [and|or <condition>]
                      | <key> != <value> [and|or <condition>]

      Where:
      * `<key>` is a fully-qualified percent delimited IData document key,
        such as `%a/b/c[0]%`, and
      * `<value>` is a literal (double- or single-quoted) string, number,
        boolean, (forward slash delimited) regular expression, or null.

      Examples:
      * `%a/b/c[0]% == "xyz"`
      * `%some/thing% != null`
      * `%num% == /\d\d/`
      * `%num% == 10`
      * `%flag% == true`
      * `%total% == %count%`
      * `%inString1% == "abc" and (%inString2% == "123" or %inString3% == "123")`

    * `$scope` is an optional IData document containing the variables against
      which `$condition` will be evaluated. If not specified, the `$condition`
      will be evaluated against the pipeline.
  * Outputs:
    * `$result?` is the boolean result of the evaluation.

### Content

Services for manipulating arbitrary textual content, such as XML or CSV
content.

* #### tundra.content:amend

  Edits the given XML or flat file content with the list of {key, value} pairs
  specified in `$amendments`.

  * Inputs:
    * `$content` is a string, byte array, or input stream containing the XML
      or flat file content to be amended.
    * `$amendments` is an IData document list containing all the edits to be
      made to the given `$content`.
      * `key` is a fully-qualified (for example, `a/b/c[0]`) key identifying
        the value in the parsed `$content` to be edited.
      * `value` is the value to be assigned to the item identified by `key`,
        and can include percent-delimited variable substitution strings which
        will be substituted prior to being inserted into the parsed `$content`.
      * `condition` is an optional `Tundra/tundra.condition:evaluate`
        conditional statement, which is evaluated against the pipeline and
        only if the condition evaluates to true will the associated amended
        `value` be applied. If not specified, the amended `value` will always
        be applied.
    * `$schema` is the fully-qualified name of the document reference (for
      XML) or flat file schema (for flat files) used to parse `$content`.
    * `$encoding.input` is an optional character set used to decode the text
      data if `$content` is provided as a byte array or input stream. Defaults
      to the Java virtual machine [default charset].
    * `$encoding.output` is an optional character set used to encode the
      amended text data if `$mode.output` is a byte array or input stream.
      Defaults to the Java virtual machine [default charset].
    * `$mode.output` is an optional choice of {stream, bytes, string} which
      specifies the type of object `$content` is returned as. Defaults to
      stream.
  * Outputs:
    * `$content` is the resulting edited XML or flat file content.

* #### tundra.content:deliver

  Delivers arbitrary content (string, bytes, or input stream) to the given
  destination URI.

  Additional delivery protocols can be implemented by creating a service named
  for the URI scheme in the folder `Tundra/tundra.support.content.deliver`.
  Services in this folder should implement the
  `Tundra/tundra.support.content.deliver.protocol:handler` specification.

  * Inputs:
    * `$content` is a string, byte array, or input stream containing data to
      be delivered to the `$destination` URI.
    * `$content.type` is an optional MIME media type describing the type
      content being delivered.
    * `$encoding` is an optional character set to use when `$content` is
      provided as a string to encode the text data upon delivery. Defaults to
      the Java virtual machine [default charset].
    * `$destination` is a URI identifying the location where the given
      `$content` should be delivered. Supports the following delivery
      protocols / URI schemes:
      * `file`: writes the given content to the file specified by the
        destination URI. The following additional options can be provided via
        the `$pipeline` document:
        * `$mode`: append / write
      * `http`: transmits the given content to the destination URI. The
        following additional options can be provided via the `$pipeline`
        document:
        * `$method`: get / put / post / delete / head / trace / options
        * `$headers/*`: additional HTTP headers as required
        * `$authority/user`: the username to log on to the remote web server
        * `$authority/password`: the password to log on to the remote web
          server
      * `https`: refer to `http`
      * `mailto`: sends an email with the given content attached. An example
        mailto URI is as follows:
        `mailto:bob@example.com?cc=jane@example.com&subject=Example&body=Example&attachment=message.xml`.

        The following additional override options can be provided via the
        `$pipeline` document:
        * `$attachment`: the attached file's name
        * `$from`: email address to send the email from
        * `$subject`: the subject line text
        * `$body`: the main text of the email
        * `$smtp`: an SMTP URI specifying the SMTP server to use (for example,
          `smtp://user:password@host:port`), defaults to the SMTP server
           configured in the Integration Server setting
           `watt.server.smtpServer`.
    * `$pipeline` is an optional IData document for providing arbitrary
      variables to the delivery implementation service.
  * Outputs:
    * `$message` is an optional response message, useful for logging, that may
      be returned by specific delivery protocols.
    * `$response` is an optional response content returned by the delivery
      (for example, the HTTP response body).
    * `$response.type` is an optional MIME media type describing the type of
      `$response` returned.

* #### tundra.content:discard

  Receives arbitrary (XML or flat file) content and then discards it (does
  nothing with it). This is the Tundra equivalent of Unix's [/dev/null], which
  is useful for successfully receiving messages that do not need to be saved
  or processed.

  This service is intended to be invoked by clients via HTTP or FTP.

  [/dev/null]: <http://en.wikipedia.org/wiki//dev/null>

* #### tundra.content:emit

  Converts an IData document to an XML or flat file string, byte array, or
  input stream.

  * Inputs:
    * `$document` is the IData document to be serialized as a string, byte
      array, or input stream.
    * `$encoding` is an optional character set to use when encoding the
      resulting text data to a byte array or input stream. Defaults to the
      Java virtual machine [default charset].
    * `$schema` is the fully-qualified name of the document reference (for
      XML) or flat file schema (for flat files) used to serialize `$document`.
    * `$mode` is an optional choice of {stream, bytes, string} which specifies
      the type of object `$content` is returned as. Defaults to stream.
  * Outputs:
    * `$content` is the resulting serialization of `$document` as XML or flat
      file content.

* #### tundra.content:parse

  Parses XML and flat file content (specified as a string, byte array, or
  input stream) into an IData document.

  * Inputs:
    * `$content` is a string, byte array, or input stream containing XML or
      flat file content to be parsed.
    * `$encoding` is an optional character set to use when `$content` is
      provided as a byte array or input stream to decode the contained text
      data. Defaults to the Java virtual machine [default charset].
    * `$schema` is the fully-qualified name of the document reference (for
      XML) or flat file schema (for flat files) used to parse `$content`.
  * Outputs:
    * `$document` is the resulting IData document representing the parsed
      `$content`.

* #### tundra.content:reject

  Receives arbitrary (XML or flat file) content and then rejects it by always
  returning an error to the client.

  This service is intended to be invoked by clients via HTTP or FTP.

* #### tundra.content:retrieve

  Retrieves arbitrary content (XML, flat files, binary) from the given
  `$source` URI, and calls the given content processing service to process it.

  Additional retrieval protocols can be implemented by creating a service
  named for the URI scheme in the folder `Tundra/tundra.support.content.retrieve`.
  Services in this folder must implement the `Tundra/tundra.schema.content.retrieve:handler` specification.

  * Inputs:
    * `$source` is a URI identifying the location from which content is to be
      retrieved. Supports the following retrieval protocols / URI schemes:
      * `file`: processes each file matching the given $source URI with the
        given processing $service.

        The file component of the URI can include wildcards or globs (such as *.txt or *.j?r) for matching multiple files at once. For example,
        `file:////server:port/directory/*.txt` would process all *.txt files
        in the specified directory.

        To ensure each file processed is not locked or being written to by
        another process, the file is first moved to a `./archive` directory
        prior to processing.
    * `$service` is the fully-qualified name of the content processing
      service, which implements the `Tundra/tundra.schema.content.retrieve:processor`
      specification, invoked to process each item of content retrieved from
      the `$source` URI.
    * `$limit` is an optional maximum number of content matches to be
      processed in a single execution. Defaults to 1000.

* #### tundra.content:split

  One-to-many conversion of XML or flat file content to another format. Calls
  the given splitting service, passing the parsed `$content` as an input, and
  emitting the split list of `$contents` as output.

  * Inputs:
    * `$content` is a string, byte array, or input stream of XML or flat file
      content to be split.
    * `$service` is the fully-qualified name of the splitting service, which
      accepts a single IData document and returns an IData document list,
      called to split the parsed `$content`.
    * `$pipeline` is an optional IData document containing arbitrary variables
      to be included in the input pipeline of the invocation of `$service`.
    * `$encoding.input` is an optional character set used to decode the text
      data if `$content` is provided as a byte array or input stream. Defaults
      to the Java virtual machine [default charset].
    * `$encoding.output` is an optional character set used to encode the split
      text datum if the specified `$mode.output` is a byte array or stream.
      Defaults to the Java virtual machine [default charset].
    * `$schema.input` is the fully-qualified name of the document reference
      (for XML) or flat file schema (for flat files) used to parse `$content`.
    * `$schema.output` is an optional fully-qualified name of the document
      reference (for XML) or flat file schema (for flat files) used to emit or
      serialize the resulting list of IData documents returned by `$service`
      when all documents in the list are alike.

      Alternatively, it is perfectly permissible for the resulting list
      returned by `$service` to contain unlike documents (documents whose
      formats are different), and in this case `$service` is required to
      return a string list `$schemas`, where each item in `$schemas` is the
      fully-qualified document reference (for XML) or flat file schema (for
      flat files) corresponding to the same indexed item in the returned
      document list to be used to emit/serialize that item.
    * `$service.input` is an optional variable name to use in the input
      pipeline of the call to `$service` for the parsed `$content` IData
      document. Defaults to `$document`.
    * `$service.output` is an optional variable name used to extract the
      output IData document list from the output pipeline of the call to
      `$service`. Defaults to `$documents`.
    * `$mode.output` is an optional choice of {stream, bytes, string} which
      specifies the type of object each item in `$contents` is returned as.
      Defaults to stream.
  * Outputs:
    * `$contents` is the resulting list of split XML or flat file content.
    * `$schemas` is the list of fully-qualified document reference (for XML)
      or flat file schema (for flat files) names, if the `$contents` list
      contains unlike content formats.

* #### tundra.content:translate

  One-to-one conversion of XML or flat file content to another format. Calls
  the given translation service, passing the parsed content as an input, and
  emitting the translated content as output.

  * Inputs:
    * `$content` is a string, byte array or input stream containing XML or
      flat file content to be translated to another format.
    * `$service` is the fully-qualified name of the translation service, which
      accepts a single IData document and returns a single IData document,
      called to translate the parsed `$content`.
    * `$encoding.input` is an optional character set used to decode the text
      data if `$content` is provided as a byte array or input stream. Defaults
      to the Java virtual machine [default charset].
    * `$encoding.output` is an optional character set used to encode the
      translated text data if the specified `$mode.output` is a byte array or
      stream. Defaults to the Java virtual machine [default charset].
    * `$schema.input` is the fully-qualified name of the document reference
      (for XML) or flat file schema (for flat files) used to parse `$content`.
    * `$schema.output` is the fully-qualified name of the document reference
      (for XML) or flat file schema (for flat files) used to emit or serialize
      the resulting IData document returned by `$service`.
    * `$service.input` is an optional variable name to use in the input
      pipeline of the call to `$service` for the parsed `$content` IData
      document. Defaults to `$document`.
    * `$service.output` is an optional variable name used to extract the
      output IData document list from the output pipeline of the call to
      `$service`. Defaults to `$translation`.
    * `$mode.output` is an optional choice of {stream, bytes, string} which
      specifies the type of object `$translation` is returned as. Defaults to
      stream.
  * Outputs:
    * `$translation` is the translated XML or flat file content returned as a
      string, byte array or input stream (depending on the `$mode.output`
      chosen).

### Datetime

Services for manipulating date, time and datetime strings:

```java
// adds a duration of time to the given datetime
tundra.datetime:add($datetime, $pattern, $duration);

// compares two datetime strings, indicating position in time relative to one
// another; returns three booleans: $before?, $equal?, and $after?
tundra.datetime:compare($datetime.x, $datetime.y, $pattern);

// concatenates a date and time into a single datetime
tundra.datetime:concatenate($date, $time);

// returns the duration of time between two datetimes
tundra.datetime:duration($datetime.start, $datetime.end, $pattern);

// returns the given java.util.Date object as a string adhering to the given datetime pattern
tundra.datetime:emit($datetime.object, $pattern);

// formats a datetime that conforms to the input pattern, according to the
// output pattern
tundra.datetime:format($datetime, $pattern.input, $pattern.output);

// returns the current datetime formatted according to the given pattern
tundra.datetime:now($pattern);

// parses the given datetime string according to the given pattern, and returns a corresponding
// java.util.Date object
tundra.datetime:parse($datetime, $pattern);

// subtracts a duration of time from the given datetime
tundra.datetime.subtract($datetime, $pattern, $duration);

// returns true if the given datetime conforms to the given pattern
tundra.datetime.validate($datetime, $pattern);
```

### Decimal

Services for working with arbitrary precision decimals (uses java.math.BigDecimal as its implementation):

```java
// returns the absolute value of the given decimal
tundra.decimal:absolute($decimal);

// returns the average value of the given list of decimals
//
// refer to <http://docs.oracle.com/javase/6/docs/api/java/math/RoundingMode.html> for details on
// each rounding algorithm; if not specified, the default rounding algorithm used is HALF_UP
tundra.decimal:average($decimals[], $precision, $rounding);

// adds the given decimals, returning the result optionally rounded to the given precision (number of
// decimal places) using the given rounding algorithm
//
// refer to <http://docs.oracle.com/javase/6/docs/api/java/math/RoundingMode.html> for details on
// each rounding algorithm; if not specified, the default rounding algorithm used is HALF_UP
tundra.decimal:add($decimals[], $precision, $rounding);

// divides the given decimal x by y, returning the result optionally rounded to the given precision
// (number of decimal places) using the given rounding algorithm
//
// refer to <http://docs.oracle.com/javase/6/docs/api/java/math/RoundingMode.html> for details on
// each rounding algorithm; if not specified, the default rounding algorithm used is HALF_UP
tundra.decimal:divide($decimal.x, $decimal.y, $precision, $rounding);

// returns the maximum value in the given list of decimals
//
// refer to <http://docs.oracle.com/javase/6/docs/api/java/math/RoundingMode.html> for details on
// each rounding algorithm; if not specified, the default rounding algorithm used is HALF_UP
tundra.decimal:maximum($decimals[], $precision, $rounding);

// returns the minimum value in the given list of decimals
//
// refer to <http://docs.oracle.com/javase/6/docs/api/java/math/RoundingMode.html> for details on
// each rounding algorithm; if not specified, the default rounding algorithm used is HALF_UP
tundra.decimal:minimum($decimals[], $precision, $rounding);

// multiplies the given decimals, returning the result optionally rounded to the given precision
// (number of decimal places) using the given rounding algorithm
//
// refer to <http://docs.oracle.com/javase/6/docs/api/java/math/RoundingMode.html> for details on
// each rounding algorithm; if not specified, the default rounding algorithm used is HALF_UP
tundra.decimal:multiply($decimals[], $precision, $rounding);

// returns the negative value of the given decimal (-x)
tundra.decimal:negate($decimal);

// raises the given decimal to the power of the given exponent (d^e), optionally rounded to the
// given precision (number of decimal places) using the given rounding algorithm
//
// refer to <http://docs.oracle.com/javase/6/docs/api/java/math/RoundingMode.html> for details on
// each rounding algorithm; if not specified, the default rounding algorithm used is HALF_UP
tundra.decimal:power($decimal, $exponent, $precision, $rounding);

// rounds the given decimal to given precision (number of decimal places) using the given rounding
// algorithm
//
// refer to <http://docs.oracle.com/javase/6/docs/api/java/math/RoundingMode.html> for details on
// each rounding algorithm; if not specified, the default rounding algorithm used is HALF_UP
tundra.decimal:round($decimal, $precision, $rounding);

// subtracts the given decimal y from x, returning the result optionally rounded to the given precision
// (number of decimal places) using the given rounding algorithm.
//
// refer to <http://docs.oracle.com/javase/6/docs/api/java/math/RoundingMode.html> for details on
// each rounding algorithm; if not specified, the default rounding algorithm used is HALF_UP
tundra.decimal:subtract($decimal.x, $decimal.y, $precision, $rounding);

// returns true if the given string can be parsed as a decimal
tundra.decimal:validate($decimal);
```

### Directory

File system services for working with directories or folders:

```java
// creates a new directory
tundra.directory:create($directory);

// returns true if the directory exists and it is directory
tundra.directory:exists($directory);

// returns a new file path URI, given a $parent path and $child path or file name
tundra.directory:join($parent, $child);

// lists a directory, optionally filtering based on the given pattern, which can be either a
// regular expression (for example, "\w+\.\w+") or a wildcard expression (for example, "*.txt")
// depending on the $mode selected (default is "regex")
tundra.directory:list($directory, $pattern, $mode, $recurse?);

// returns a raw directory listing with no additional processing: useful for when performance
// takes priority over ease of use; for example, when the directory contains hundreds of
// thousands or more files
tundra.directory:ls($directory);

// returns the canonical file: URI that represents the given directory
tundra.directory:normalize($directory);

// deletes the given directory; all child files and directories will be
// deleted if $recurse? is true
tundra.directory:remove($directory, $recurse?);

// renames the source directory to the target directory name
tundra.directory:rename($directory.source, $directory.target);
```

### Document

Services for manipulating com.wm.data.IData objects:

* #### tundra.document:amend

  Edits the given IData `$document` with the list of {key, value} pairs
  specified in `$amendments`.

  * Inputs:
    * `$document` is the IData document to be amended.

    * `$amendments` is an IData document list containing all the edits to be
      made to the given `$document`.
      * `key` is a fully-qualified (for example, `a/b/c[0]`) key identifying
        the value in `$document` to be edited.

      * `value` is the value to be assigned to the item identified by `key`,
        and can include percent-delimited variable substitution strings which
        will be substituted prior to being inserted into the `$document`.

      * `condition` is an optional `Tundra/tundra.condition:evaluate`
        conditional statement, which is evaluated against the pipeline and
        only if the condition evaluates to true will the associated amended
        `value` be applied. If not specified, the amended `value` will always
        be applied.

  * Outputs:
    * `$document` is the resulting edited IData document.

```java
// removes all elements from the given IData $document, except for any keys specified
// in the $preserve list; keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.document:clear($document, $preserve[]);

// removes all null values from the given IData document
tundra.document:compact($document);

// copies the value associated with the source key to the target key in the given IData document
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.document:copy($document, $key.source, $key.target);

// delivers a document to the given destination URI
//
// supports the following delivery protocols / URI schemes:
//  - file: writes the given content to the file specified by the destination URI.  The
//          following additional options can be provided via the $pipeline document:
//            - $mode: append / write
//
// additional delivery protocols can be implemented by creating a service named for the
// URI scheme in the folder tundra.support.content.deliver; services in this folder should
// implement the tundra.support.content.deliver.protocol:handler specification
//
// an optional response stream may be returned by specific delivery protocols, for example, an
// http delivery returns the http response as a stream, which is useful for logging, however a
// file delivery returns no such response as none exists
tundra.document:deliver($document, $encoding, $schema, $destination, $pipeline);

// removes the element with the given key from the given IData document
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.document:drop($document, $key);

// returns either a shallow (top-level elements) or deep (recursive) clone
// of the given IData document
tundra.document:duplicate($document, $recurse?);

// iterates over all elements in the given IData document, invoking the given service for
// each {key, value} pair
tundra.document:each($document, $service, $pipeline, $key.input, $value.input, $value.class, $recurse?);

// returns true if the two IData documents are equal (contain the same keys
// and values)
tundra.document:equal($document.x, $document.y);

// emits (or encodes) the given IData document as an IData XML string, byte array, or input stream
// refer: <http://documentation.softwareag.com/webmethods/wmsuites/wmsuite8-2_sp2/Integration_Server/8-2-SP1_Integration_Server_Java_API_Reference/com/wm/util/coder/IDataXMLCoder.html>
tundra.document:emit($document, $encoding, $mode);

// returns the first {key, value} pair from the given IData document
tundra.document:first($document);

// returns the value associated with the given key from the given IData
// document, or null if the key doesn't exist
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.document:get($document, $key);

// converts all keys in the given IData document to lower case
tundra.document.key:lowercase($document, $recurse?);

// replaces all occurrences of the given regular expression pattern in each key
// in the given IData document with the replacement string
// refer: <http://download.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html>,
//        <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Matcher.html>
tundra.document.key:replace($document, $pattern, $replacement, $literal?, $recurse?);

// removes leading and trailing whitespace from all keys in the given IData document
tundra.document.key:trim($document, $recurse?);

// converts all keys in the given IData document to upper case
tundra.document.key:uppercase($document, $recurse?);

// returns the list of top-level keys in the given IData document
tundra.document:keys($document);

// returns the last {key, value} pair from the given IData document
tundra.document:last($document);

// returns the number of top-level elements in the given IData document
tundra.document:length($document);

// converts the IData value identified by $key in the given $scope IData document (or the pipeline,
// if not specified) to a new list of type IData[] containing the original value as its single item,
// unless the original value was already list; the given key can be simple or fully qualified, such
// as a/b/c[0]/d
tundra.document:listify($key, $scope);

// returns a new IData document created by invoking the given service for each {key, value} pair
// in the given document, and collecting new the {key, value} pair returned
tundra.document:map($document, $service, $pipeline, $key.input, $key.output, $value.input, $value.output, $value.class, $recurse?);

// merges multiple IData documents into a single document; only top-level
// elements are merged, and if duplicate keys exist in the documents being
// merged, the latest wins
tundra.document:merge($documents[]);

// returns a new IData document, with all fully qualified keys (for example,
// 'a/b/c' or 'x/y[0]/z[1]') deconstructed into their constituent parts
tundra.document:normalize($document);

// parses (or decodes) the given IData XML string, byte array, or input stream to an IData document
// refer: <http://documentation.softwareag.com/webmethods/wmsuites/wmsuite8-2_sp2/Integration_Server/8-2-SP1_Integration_Server_Java_API_Reference/com/wm/util/coder/IDataXMLCoder.html>
//        <http://documentation.softwareag.com/webmethods/wmsuites/wmsuite8-2_sp2/Integration_Server/8-2-SP1_Integration_Server_Java_API_Reference/com/wm/util/coder/XMLCoderWrapper.html>
tundra.document:parse($content, $encoding);

// sets the value associated with the given key in the given IData
// document
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.document:put($document, $key, $value);

// renames the value with the source key to have the target key in the given IData document
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.document:rename($document, $key.source, $key.target);

// sorts the given IData document by its keys in natural ascending order
tundra.document:sort($document, $recurse?);

// one-to-many conversion of an IData document to an IData[] document list; calls the given
// splitting service, passing the document as an input, and emitting the split
// list of documents as output; the splitting service must accept a single IData document,
// and return an IData document list; the splitting service may also return a list of $schemas
// that each return document conforms to
tundra.document:split($document, $service, $pipeline, $service.input, $service.output);

// trims all leading and trailing whitespace from all string values, then converts
// empty strings to nulls, then compacts the IData document by removing all null
// values
tundra.document:squeeze($document, $recurse?);

// attempts variable substitution on each string element in the given IData document by
// replacing all occurrences of substrings matching "%key%" with the associated (optionally
// scoped) value
tundra.document:substitute($document, $pipeline);

// one-to-one conversion of an IData document to another IData document; calls the given
// translation service, passing the document as an input, and emitting
// the translated document as output; the translation service must accept a single IData
// document and return a single IData document
tundra.document:translate($document, $service, $pipeline, $service.input, $service.output);

// converts all String elements in the given IData document to lower case
tundra.document.value:lowercase($document, $recurse?);

// replaces all occurrences of the given regular expression pattern in each String value
// in the given IData document with the replacement string
// refer: <http://download.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html>,
//        <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Matcher.html>
tundra.document.value:replace($document, $pattern, $replacement, $literal?, $recurse?);

// removes leading and trailing whitespace from all String elements in the given IData document
tundra.document.value:trim($document, $recurse?);

// converts all String elements in the given IData document to upper case
tundra.document.value:uppercase($document, $recurse?);

// returns the list of top-level values in the given IData document
tundra.document:values($document);
```

### DNS

Services for resolving names in the domain name system (DNS).

```java
// returns the fully-qualifed domain name, host name, and IP address for the
// localhost
tundra.dns:localhost();

// looks up the given name in the Domain Name System (DNS), returning the
// fully-qualifed domain name, host name, and IP address, if found;
// refer: http://en.wikipedia.org/wiki/Domain_Name_System
tundra.dns:resolve($name);
```

### Duration

Services for manipulating durations of time:

```java
// adds two durations together
tundra.duration:add($duration.x, $duration.y);

// compares two durations, returning if the first is less than, equal to, or
// greater than the second duration, or if the comparison is indeterminate
// (such as comparing a 1 month duration with a 30 days duration)
tundra.duration:compare($duration.x, $duration.y);

// formats the given duration string according to the desired pattern (a
// start instant, $datetime, may be required when formatting fields with
// indeterminate values, such as converting months to days, because the
// number of days in a month varies)
tundra.duration:format($duration, $datetime, $pattern.input, $pattern.output);

// multiplies the given duration by the given factor (a start instant, $datetime,
// may be required when multiplying fields with indeterminate values, such as
// months, because the number of days in a month varies)
tundra.duration:multiply($duration, $datetime, $factor);

// reverses the sign of the given duration
tundra.duration:negate($duration)

// subtracts one duration from another
tundra.duration:subtract($duration.x, $duration.y);
```

### Exception

```java
// throws a new com.wm.app.b2b.server.ServiceException with the given message
// or rethrows the given exception
tundra.exception:raise($message, $exception);
```

### File

File system services for working with files:

```java
// atomically creates a new, empty file
tundra.file:create($file);

// returns true if the file can be executed
tundra.file:executable($file);

// returns true if the file exists and is a file
tundra.file:exists($file);

// returns the length of the given file in bytes
tundra.file:length($file);

// returns true if the given filename matches the given regular expression or wildcard
// pattern; note: only the file name is considered in the match, not the path
tundra.file:match($file, $pattern, $mode);

// returns the canonical file: URI that represents the given file
tundra.file:normalize($file);

// opens a file for reading, appending, or writing, calls the given service passing
// the resulting file stream object as an input argument, with the name specified by
// $service.input or '$stream' if not specified, and closes the stream when done
tundra.file:process($file, $mode, $service, $pipeline, $service.input);

// reads a file in full, returning the content as either a byte array or
// string
tundra.file:read($file, $mode, $encoding);

// returns true if the file can be read
tundra.file:readable($file);

// deletes the given file
tundra.file:remove($file);

// renames the source file to the target name
tundra.file:rename($file.source, $file.target);

// updates the modified time, or creates a new file if it doesn't already
// exist
tundra.file:touch($file);

// determines the mime type for the given file name or file URI; Integration
// Server file extension to mime type mappings are defined in the file
// ./lib/mime.types; if the mime type cannot be found, it defaults to the
// type for arbitrary binary data: application/octet-stream
// refer: <http://en.wikipedia.org/wiki/Internet_media_type>
tundra.file:type($file);

// returns true if the file can be written to
tundra.file:writable($file);

// writes or appends data (provided as a string, byte array or input stream)
// to the given file; if $file is null, a new temporary file is automatically
// created
tundra.file:write($file, $mode, $content, $encoding);
```

### HTTP

* #### tundra.http:client

    Provides an [HTTP] client for issuing requests against [HTTP] servers.

    * Inputs:
      * `$request` is an IData document containing the parameters required for
        making an [HTTP] request to an [HTTP] server.
      * `$service` is an optional custom [HTTP] response handler service, which
        implements the `tundra.http.response:handler` specification, and can be 
        specified when the standard `tundra.http.response:handle` service does 
        not suffice. The standard handler does the following:
        * checks the [HTTP response code] is < 400, and throws an exception 
          when it is not
        * normalizes the response header keys to lower case
        * converts the response body stream to bytes

    * Outputs:
      * `$response` is an IData document containing the [HTTP] server response for
        the given request.

* #### tundra.http.response:handle

    Standard [HTTP] response handler: checks that the [HTTP response code] is < 400, 
    normalizes the response header keys to lower case, and converts the response body 
    stream to bytes. If the [HTTP response code] is >= 400, an exception will be
    thrown.

    * Inputs:
      * `$response` is the [HTTP] response to be processed by this service.

    * Outputs:
      * `$response` is the processed [HTTP] response, where the [HTTP response code] is 
        guaranteed to be < 400, the response header keys are normalized to lower case, 
        and the response body is returned as a byte[] array.

* #### tundra.http.response:handler

    Specifies the required inputs and outputs for an [HTTP] response handling service
    called by tundra.http:client.

    * Inputs:
      * `$response` is the [HTTP] response to be processed by this service.

    * Outputs:
      * `$response` is the processed [HTTP] response. How the response is processed is
        at the discretion of the implementing service. Refer to the standard 
        `tundra.http.response:handle` service for a reference implementation.

[HTTP]: <http://tools.ietf.org/search/rfc2616>
[HTTP response code]: <http://httpstatus.es/>

### ID

* #### tundra.id:generate

    Generates an immutable universally unique identifier ([UUID]). A [UUID] 
    represents a 128-bit value.

    There exist different variants of these global identifiers. The methods 
    of this class are for manipulating the Leach-Salz variant, although the
    constructors allow the creation of any variant of [UUID] (described below).

    The layout of a variant 2 (Leach-Salz) [UUID] is as follows: The most 
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
    [UUID]. The bit layout described above is valid only for a [UUID] with a 
    variant value of 2, which indicates the Leach-Salz variant.

    The version field holds a value that describes the type of this [UUID]. 
    There are four different basic types of UUIDs: time-based, DCE 
    security, name-based, and randomly generated UUIDs. These types 
    have a version value of 1, 2, 3 and 4, respectively.

    For more information including algorithms used to create UUIDs, 
    see the Internet-Draft UUIDs and GUIDs or the standards body 
    definition at ISO/IEC 11578:1996. 

    * Outputs:
      * $id is the generated [UUID].

[UUID]: <http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html>

### Integer

Services for working with arbitrary precision integers (uses java.math.BigInteger as its implementation):

```java
// returns the absolute value of the given integer
tundra.integer.absolute($integer);

// adds the given integers together, returning the result
tundra.integer.add($integers[]);

// decrements the given $integer by one; returns minus one if $integer is not specified so that
// this service can be used in a loop to both initialize and decrement a counter variable.
tundra.integer.decrement($integer);

// divides the given integer x by y, returning the result
tundra.integer.divide($integer.x, $integer.y);

// increments the given $integer by one; returns one if $integer is not specified so that
// this service can be used in a loop to both initialize and increment a counter variable
tundra.integer.increment($integer);

// multiplies the given integers together, returning the result
tundra.integer.multiply($integers[]);

// returns the negative value of the given integer (-x)
tundra.integer.negate($integer);

// raises the given integer to the power of the given exponent (i^e)
tundra.integer.power($integer, $exponent);

// returns the remainder from dividing the given integer x by y
tundra.integer.remainder($integer.x, $integer.y);

// subtracts the given integer y from x, returning the result
tundra.integer.subtract($integer.x, $integer.y);

// returns true if the given string can be parsed as an integer
tundra.integer.validate($integer);
```

### Content List

```java
// converts an IData[] document list to a list of XML or flat file strings, bytes, or
// input streams
tundra.list.content:emit($documents[], $encoding, $schema, $mode);

// many-to-one conversion of XML or flat file content to another format; calls the given
// joining service, passing the parsed list of contents as an input, and emitting the joined
// content as output; the splitting service must accept an IData[] document list, and return
// a single IData document
tundra.list.content.join($contents[], $service, $pipeline, $encoding.input, $encoding.output, $schema.input, $schema.output, $service.input, $service.output, $mode.output);

// parses a list of XML and flat file content (specified as a list of strings, bytes, or
// input streams) into an IData[] document list
tundra.list.content:parse($contents[], $encoding, $schema);
```

### Datetime List

```java
// formats a list of datetimes that conform to the input pattern, according
// to the output pattern
tundra.list.datetime:format($list[], $pattern.input, $pattern.output);
```

### Document List

Services for manipulating document (com.wm.data.IData) lists:

```java
// appends a single item to the end of a list, such that appending an item to
// a list containing n items results in a new list of n + 1 items
tundra.list.document:append($list[], $item);

// removes all elements from each IData document in the given list, except for any keys
// specified in the $preserve list; keys can be simple or fully qualified, such as
// a/b/c[0]/d
tundra.list.document.clear($list[], $preserve[]);

// removes all null values from each IData item in the given list, and then
// removes all null items themselves from the given list, thereby shortening
// the length of the list
tundra.list.document:compact($list[]);

// returns a new list containing all the items in the given $list and $items
// input arguments
tundra.list.document:concatenate($list.x[], $list.y[]);

// removes the item with the given index from the given list
tundra.list.document:drop($list, $index);

// iterates through the given list, invoking the given service for each item
// in the list, passing $item, $index, $iteration and $length variables
tundra.list.document:each($list[], $service, $pipeline, $item.input);

// returns true if the two given lists are equal
tundra.list.document:equal($list.x[], $list.y[]);

// returns the item stored at a given index in a list (supports forward and
// reverse indexing)
tundra.list.document:get($list[], $index);

// returns true if the given item is found in the given list
tundra.list.document:include($list[], $item);

// returns a new list with the given item inserted at the desired index in
// the given list
tundra.list.document:insert($list[], $item, $index);

// many-to-one conversion of an IData[] document list to an IData document; calls the given
// joining service, passing the list of documents as an input, and emitting the joined
// document as output; the joining service must accept an IData[] document list, and return
// a single IData document
tundra.list.document.join($documents[], $service, $pipeline, $service.input, $service.output);

// converts all keys in each IData item in the given list to lower case
tundra.list.document.key:lowercase($list[], $recurse?);

// replaces all occurrences of the given regular expression pattern in each key
// in each IData item in the given list with the replacement string
// refer: <http://download.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html>,
//        <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Matcher.html>
tundra.list.document.key:replace($list[], $pattern, $replacement, $literal?, $recurse?);

// removes leading and trailing whitespace from all keys in each IData item in the given list
tundra.list.document.key:trim($list[], $recurse?);

// converts all keys in the each IData item in the given list to upper case
tundra.list.document.key:uppercase($list[], $recurse?);

// returns the number of items in the given list
tundra.list.document:length($list[]);

// returns a new list created by invoking the given service for each item in
// the given list, and collecting new the values returned
tundra.list.document:map($list[], $service, $item.input, $item.output);

// prepends a single item to the front of a list, such that prepending an
// item to a list containing n items results in a new list of n + 1 items
tundra.list.document:prepend($list[], $item);

// sets the value of the item stored at a given index in a list (supports forward and
// reverse indexing)
tundra.list.document:put($list[], $item, $index);

// returns a new list with all items from the given list in reverse order
tundra.list.document:reverse($list[]);

// returns a new list which is a subset of the items in the given list
tundra.list.document:slice($list[], $index, $list);

// returns a new list sorted according to the natural ordering of the given
// list's items
// refer: <http://docs.oracle.com/javase/6/docs/api/java/lang/Comparable.html>
tundra.list.document:sort($list[], $key);

// attempts variable substitution on each string element in each IData document
// in the given list by replacing all occurrences of substrings matching "%key%"
// with the associated (optionally scoped) value
tundra.list.document:substitute($list[], $pipeline);

// one-to-one conversion of a document list (IData[]) to another document list (IData[]);
// calls the given translation service, passing each list item as an input, and collecting
// the translated item as output;
// the translation service must accept a single IData document and return a single
// IData document
tundra.list.document:translate($list[], $service, $pipeline, $service.input, $service.output);

// converts all String elements in each IData item in the given list to lower case
tundra.list.document.value:lowercase($list[], $recurse?);

// replaces all occurrences of the given regular expression pattern in each String value
// in each IData item in the given list with the replacement string
// refer: <http://download.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html>,
//        <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Matcher.html>
tundra.list.document.value:replace($list[], $pattern, $replacement, $literal?, $recurse?);

// removes leading and trailing whitespace from all String elements in each IData item
// in the given list
tundra.list.document.value:trim($list[], $recurse?);

// converts all String elements in each IData item in the given list to upper case
tundra.list.document.value:uppercase($list[], $recurse?);
```

* #### tundra.list.document:grow

  Increases the size of the given list by the given count of items, padded
  with the given item (or null if not specified).

  * Inputs:
    * `$list` is the IData[] list to be grown.
    * `$item` is an optional IData used to pad the newly grown section of the
      list with.
    * `$count` is the number of new items to add to the list.

  * Outputs:
    * `$list` is the IData[] list grown by the desired `$count` of items,
      with the original items preserved and the new items padded with `$item`
      (or null if not specified).

* #### tundra.list.document:resize

  Resizes the given list to the given length, truncated from the end when the
  length is decreased, and padded with the given item (or null if not
  specified) when the length is increased.

  * Inputs:
    * `$list` is the IData[] list to be grown.
    * `$item` is an optional IData used to pad the list if increasing the
      size.
    * `$length` is the desired new length of the list.

  * Outputs:
    * `$list` is the IData[] list resized to the desired `$length`; if the new
      length is less than the original length, the list is truncated from
      the end; if the new length is greater than the original length, the
      list is padded with `$item` (or null if not specified).

* #### tundra.list.document:shrink

  Decreases the size of the given list by the given count, truncating items
  from the end.

  * Inputs:
    * `$list` is the IData[] list to be shrunk.
    * `$count` is the number of items to truncate from the end of the list.

  * Outputs:
    * `$list` is the IData[] list shrunk from the end of the list by the
      desired item count by truncating items from the end of the list. If
      the list is smaller than the count, an empty list is returned.

### Duration List

```java
// formats a list of duration strings according to the desired pattern
// (a start instant, $datetime, may be required when formatting fields with
// indeterminate values, such as converting months to days, because the
// number of days in a month varies)
tundra.list.duration:format($list[], $datetime, $pattern.input, $pattern.output);

// returns the sum of all the given durations, returning (x1 + x2 + ... + xn)
tundra.duration:sum($durations[]);
```

### Object List

Services for manipulating java.lang.Object lists:

```java
// appends a single item to the end of a list, such that appending an item to
// a list containing n items results in a new list of n + 1 items
tundra.list.object:append($list[], $item, $class);

// removes all null items from the given list, thereby shortening the length
// of the list
tundra.list.object:compact($list[]);

// returns a new list containing all the items in the given $list and $items
// input arguments
tundra.list.object:concatenate($list.x[], $list.y[]);

// returns a list of only the items in $list.x that are not also present in
// $list.y
tundra.list.object:difference($list.x[], $list.y[]);

// removes the item with the given index from the given list
tundra.list.object:drop($list, $index);

// iterates through the given list, invoking the given service for each item
// in the list, passing $item, $index, $iteration and $length variables
tundra.list.object:each($list[], $service, $pipeline, $item.input);

// returns true if the two given lists are equal
tundra.list.object:equal($list.x[], $list.y[]);

// returns the item stored at a given index in a list (supports forward and
// reverse indexing)
tundra.list.object:get($list[], $index);

// returns true if the given item is found in the given list
tundra.list.object:include($list[], $item);

// returns a new list with the given item inserted at the desired index in
// the given list
tundra.list.object:insert($list[], $item, $index, $class);

// returns true if the list is an instance of given class
tundra.list.object:instance($list[], $class);

// returns a list of only the items in $list.x that are also present in
// $list.y
tundra.list.object:intersection($list.x[], $list.y[]);

// returns a string created by converting each list item to a string,
// separated by the given separator string
tundra.list.object:join($list[], $separator);

// returns the number of items in the given list
tundra.list.object:length($list[]);

// returns a new list created by invoking the given service for each item in
// the given list, and collecting new the values returned
tundra.list.object:map($list[], $service, $item.input, $item.output);

// prepends a single item to the front of a list, such that prepending an
// item to a list containing n items results in a new list of n + 1 items
tundra.list.object:prepend($list[], $item, $class);

// sets the value of the item stored at a given index in a list (supports forward and
// reverse indexing)
tundra.list.object:put($list[], $item, $index, $class);

// returns a new list with all items from the given list in reverse order
tundra.list.object:reverse($list[]);

// returns a new list which is a subset of the items in the given list
tundra.list.object:slice($list[], $index, $length);

// returns a new list sorted according to the natural ordering of the given
// list's items
// refer: <http://docs.oracle.com/javase/6/docs/api/java/lang/Comparable.html>
tundra.list.object:sort($list[]);

// returns a new list with all duplicates from the given list removed, such
// that no two items are equal
tundra.list.object:unique($list[]);
```

* #### tundra.list.object:grow

  Increases the size of the given list by the given count of items, padded
  with the given item (or null if not specified).

  * Inputs:
    * `$list` is the Object[] list to be grown.
    * `$item` is an optional Object used to pad the newly grown section of the
      list with.
    * `$count` is the number of new items to add to the list.
    * `$class` is an optional Java class name used to instantiate a new list if
      the provided `$list` is null.

  * Outputs:
    * `$list` is the Object[] list grown by the desired `$count` of items,
      with the original items preserved and the new items padded with `$item`
      (or null if not specified).

* #### tundra.list.object:resize

  Resizes the given list to the given length, truncated from the end when the
  length is decreased, and padded with the given item (or null if not
  specified) when the length is increased.

  * Inputs:
    * `$list` is the Object[] list to be grown.
    * `$item` is an optional Object used to pad the list if increasing the
      size.
    * `$length` is the desired new length of the list.
    * `$class` is an optional Java class name used to instantiate a new list if
      the provided `$list` is null.

  * Outputs:
    * `$list` is the Object[] list resized to the desired `$length`; if the new
      length is less than the original length, the list is truncated from
      the end; if the new length is greater than the original length, the
      list is padded with `$item` (or null if not specified).

* #### tundra.list.object:shrink

  Decreases the size of the given list by the given count, truncating items
  from the end.

  * Inputs:
    * `$list` is the Object[] list to be shrunk.
    * `$count` is the number of items to truncate from the end of the list.

  * Outputs:
    * `$list` is the Object[] list shrunk from the end of the list by the
      desired item count by truncating items from the end of the list.

### Service List

```java
// invokes each service in the given list in order, sharing the pipeline across all invokes
tundra.list.service:chain($services[], $pipeline);

// provides a try/catch/finally pattern for chained flow services
//
// If one of the given list of $services throws an exception when invoked, then the arguments
// $exception, $exception?, $exception.class, $exception.message and $exception.stack are added
// to the pipeline.
//
// If specified, the $catch service is invoked to handle the exception, otherwise the exception
// is rethrown.
//
// If specified, the $finally service is always invoked, whether an exception was thrown by one
// of the invoked $services or not.
tundra.list.service:ensure($services[], $catch, $finally, $pipeline);

// invokes a list of services either synchronously (with an optional level of
// concurrency) or asynchronously
tundra.list.service:invoke($invocations[], $mode, $concurrency);

// waits for each service thread in the given list to finish before returning
// each output pipeline
tundra.list.service:join($threads[]);
```

### String List

Services for manipulating string lists:

```java
// appends a single item to the end of a list, such that appending an item to
// a list containing n items results in a new list of n + 1 items
tundra.list.string:append($list[], $item);

// removes all null items from the given list, thereby shortening the length
// of the list
tundra.list.string:compact($list[]);

// returns a new list containing all the items in the given $list and $items
// input arguments
tundra.list.string:concatenate($list.x[], $list.y[]);

// returns a list of only the items in $list.x that are not also present in
// $list.y
tundra.list.string:difference($list.x[], $list.y[]);

// removes the item with the given index from the given list
tundra.list.string:drop($list, $index);

// iterates through the given list, invoking the given service for each item
// in the list, passing $item, $index, $iteration and $length variables
tundra.list.string:each($list[], $service, $pipeline, $item.input);

// returns true if the two given lists are equal
tundra.list.string:equal($list.x[], $list.y[]);

// returns the item stored at a given index in a list (supports forward and
// reverse indexing)
tundra.list.string:get($list[], $index);

// returns true if the given item is found in the given list
tundra.list.string:include($list[], $item);

// returns a new list with the given item inserted at the desired index in
// the given list
tundra.list.string:insert($list[], $item, $index);

// returns a list of only the items in $list.x that are also present in
// $list.y
tundra.list.string:intersection($list.x[], $list.y[]);

// returns a string created by converting each list item to a string,
// separated by the given separator string
tundra.list.string:join($list[], $separator);

// returns the number of items in the given list
tundra.list.string:length($list[]);

// converts each item to lower case
tundra.list.string:lowercase($list[]);

// returns a new list created by invoking the given service for each item in
// the given list, and collecting new the values returned
tundra.list.string:map($list[], $service, $item.input, $item.output);

// returns the list of items which matched, and the list of items which did not match, the given
// regular expression pattern
tundra.list.string:match($list[], $pattern);

// converts a list of string, bytes or input streams to a list of strings
tundra.list.string:normalize($objects[], $encoding);

// prepends a single item to the front of a list, such that prepending an
// item to a list containing n items results in a new list of n + 1 items
tundra.list.string:prepend($list[], $item);

// sets the value of the item stored at a given index in a list (supports forward and
// reverse indexing)
tundra.list.string:put($list[], $item, $index);

// replaces all occurrences of the given regular expression pattern in the each
// item, with the replacement string
// refer: <http://download.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html>,
//        <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Matcher.html>
tundra.list.string:replace($list[], $pattern, $replacement, $literal?);

// returns a new list with all items from the given list in reverse order
tundra.list.string:reverse($list[]);

// returns a new list which is a subset of the items in the given list
tundra.list.string:slice($list[], $index, $length);

// returns a new list sorted according to the natural ordering of the given
// list's items
// refer: <http://docs.oracle.com/javase/6/docs/api/java/lang/Comparable.html>
tundra.list.string:sort($list[]);

// replaces runs of one or more whitespace characters (space, tab, carriage
// return, line feed) with a single space character
tundra.list.string:squeeze($list[]);

// attempts variable substitution on each string in the given list by replacing
// all occurrences of substrings matching "%key%" with the associated (optionally
// scoped) value; optionally replaces null or non-existent values with the given
// default value
tundra.list.string:substitute($list[], $pipeline);

// removes all leading and trailing whitespace
tundra.list.string:trim($list[]);

// returns a new list with all duplicates from the given list removed, such
// that no two items are equal
tundra.list.string:unique($list[]);

// converts each item to upper case
tundra.list.string:uppercase($list[]);
```

* #### tundra.list.string:grow

  Increases the size of the given list by the given count of items, padded
  with the given item (or null if not specified).

  * Inputs:
    * `$list` is the String[] list to be grown.
    * `$item` is an optional String used to pad the newly grown section of the
      list with.
    * `$count` is the number of new items to add to the list.

  * Outputs:
    * `$list` is the String[] list grown by the desired `$count` of items,
      with the original items preserved and the new items padded with `$item`
      (or null if not specified).

* #### tundra.list.string:resize

  Resizes the given list to the given length, truncated from the end when the
  length is decreased, and padded with the given item (or null if not
  specified) when the length is increased.

  * Inputs:
    * `$list` is the String[] list to be grown.
    * `$item` is an optional String used to pad the list if increasing the
      size.
    * `$length` is the desired new length of the list.

  * Outputs:
    * `$list` is the String[] list resized to the desired `$length`; if the new
      length is less than the original length, the list is truncated from
      the end; if the new length is greater than the original length, the
      list is padded with `$item` (or null if not specified).

* #### tundra.list.string:shrink

  Decreases the size of the given list by the given count, truncating items
  from the end.

  * Inputs:
    * `$list` is the String[] list to be shrunk.
    * `$count` is the number of items to truncate from the end of the list.

  * Outputs:
    * `$list` is the String[] list shrunk from the end of the list by the
      desired item count by truncating items from the end of the list. If
      the list is smaller than the count, an empty list is returned.

### MIME

Services related to Multipurpose Internet Mail Extension (MIME):

```java
// Parses a Multipurpose Internet Mail Extension (MIME) type, according to RFC 2045
// <http://www.ietf.org/rfc/rfc2045.txt> and 2046 <http://www.ietf.org/rfc/rfc2046.txt>,
// into its constituent parts.
//
// Refer: <http://docs.oracle.com/javase/6/docs/api/javax/activation/MimeType.html>
tundra.mime.type:equal($string.x, $string.y)

// Emits a Multipurpose Internet Mail Extension (MIME) type, according to RFC 2045
// <http://www.ietf.org/rfc/rfc2045.txt> and 2046 <http://www.ietf.org/rfc/rfc2046.txt>,
// given its constituent parts.
//
// Refer: <http://docs.oracle.com/javase/6/docs/api/javax/activation/MimeType.html>
tundra.mime.type:emit($type);

// Normalizes a Multipurpose Internet Mail Extension (MIME) type by removing extraneous
// whitespace characters, and listing parameters in alphabetical order.
//
// Refer: <http://docs.oracle.com/javase/6/docs/api/javax/activation/MimeType.html>
tundra.mime.type:normalize($string);

// Parses a Multipurpose Internet Mail Extension (MIME) type, according to RFC 2045
// <http://www.ietf.org/rfc/rfc2045.txt> and 2046 <http://www.ietf.org/rfc/rfc2046.txt>,
// into its constituent parts.
//
// Refer: <http://docs.oracle.com/javase/6/docs/api/javax/activation/MimeType.html>
tundra.mime.type:parse($string);

// Returns true if the given string can be parsed as a valid Multipurpose Internet Mail
// Extension (MIME) type, according to RFC 2045 <http://www.ietf.org/rfc/rfc2045.txt>
// and 2046 <http://www.ietf.org/rfc/rfc2046.txt>.
//
// Refer: <http://docs.oracle.com/javase/6/docs/api/javax/activation/MimeType.html>
tundra.mime.type:validate($string);
```

### Node

Services for querying Integration Server namespace nodes:

```java
// grants the specified permissions on the given namespace node
tundra.node:access($node, $permissions[]);

// returns true if a node with the given node exists on this server
tundra.node:exists($node);

// lists an interface's child nodes; optionally filters based on the given
// regular expression pattern, and node type
tundra.node:list($interface, $pattern, $type, $recurse?);

// returns the type of the given node, such as whether it is an interface,
// service, or record
tundra.node:type($node);
```

### Object

Services for manipulating java.lang.Object objects:

```java
// converts a string, byte array, or input stream object to a
// string, byte array, or input stream object
tundra.object:convert($object, $encoding, $mode);

// returns true if the given objects are equal
tundra.object:equal($object.x, $object.y);

// returns true if object is an instance of given class
tundra.object:instance($object, $class);

// converts the value identified by $key in the given $scope IData document (or the pipeline, if not
// specified) to a new list of type Object[] containing the original value as its single item, unless
// the original value is already list; the given key can be simple or fully qualified, such as
// a/b/c[0]/d
tundra.object:listify($key, $scope);

// returns null
tundra.object:nothing();

// returns the given object's class, whether it's an array, and whether it's
// a primitive type, such as an int, or byte
tundra.object:reflect($object);

// converts the given object to a string by calling its toString method
tundra.object:stringify($object);
```

### Pipeline

```java
// returns a clone of the current pipeline as a document: useful if you want
// to pass the pipeline itself as an input to a service
tundra.pipeline:capture();

// removes all elements from the pipeline, except for any keys specified in the $preserve
// list; keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.pipeline:clear($preserve[]);

// copies the value associated with the source key to the target key in the pipeline
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.pipeline:copy($key.source, $key.target);

// removes the element with the given key from the pipeline
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.pipeline:drop($key);

// emits (or encodes) the current pipeline as an IData XML string, byte array, or input stream
// refer: <http://documentation.softwareag.com/webmethods/wmsuites/wmsuite8-2_sp2/Integration_Server/8-2-SP1_Integration_Server_Java_API_Reference/com/wm/util/coder/IDataXMLCoder.html>
tundra.pipeline:emit($encoding, $mode)

// returns the value associated with the given key from the pipeline, or null
// if the key doesn't exist
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.pipeline:get($key);

// returns the first {key, value} pair from the pipeline
tundra.pipeline:first();

// returns the last {key, value} pair from the pipeline
tundra.pipeline:last();

// returns the number of top-level elements in the pipeline
tundra.pipeline:length();

// writes the current pipeline to the server log
tundra.pipeline:log($level);

// merges the elements in the given IData document into the pipeline
tundra.pipeline:merge($document);

// iterates over each element in the pipeline, deconstructing all fully qualified
// keys (for example, 'a/b/c' or 'x/y[0]/z[1]') into their constituent parts
tundra.pipeline:normalize();

// parses (or decodes) the given IData XML string, byte array, or input stream and merges it into the pipeline
// refer: <http://documentation.softwareag.com/webmethods/wmsuites/wmsuite8-2_sp2/Integration_Server/8-2-SP1_Integration_Server_Java_API_Reference/com/wm/util/coder/IDataXMLCoder.html>
//        <http://documentation.softwareag.com/webmethods/wmsuites/wmsuite8-2_sp2/Integration_Server/8-2-SP1_Integration_Server_Java_API_Reference/com/wm/util/coder/XMLCoderWrapper.html>
tundra.pipeline:parse($content, $encoding);

// sets the value associated with the given key in the pipeline
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.pipeline:put($key, $value);

// renames the value with the source key to have the target key
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.pipeline:rename($key.source, $key.target);

// attempts variable substitution on every string element in the pipeline by replacing
// all occurrences of substrings matching "%key%" with the associated (optionally scoped)
// value
tundra.pipeline:substitute();
```

### Schema

Document references and service specifications:

```java
// Content retrieval protocol handling services used by tundra.content:retrieve must implement this
// specification.
tundra.schema.content.retrieve:handler;

// Content processing services used by tundra.content:retrieve must implement this specification. The
// $content is specified as a java.io.InputStream, along with optional meta data about the content,
// such as its mime type ($content.type) and name ($content.name).
tundra.schema.content.retrieve:processor;

// Exception handling $catch services called by tundra.service:ensure can implement this
// specification.
tundra.schema.exception:handler;
```

### Service

```java
// returns the current thread's call stack
tundra.service:callstack();

// provides a try/catch/finally pattern for flow services
//
// if $service throws an exception when invoked, then the arguments $exception,
// $exception?, $exception.class, $exception.message and $exception.stack are
// added to the pipeline
//
// if specified, the $catch service is invoked to handle the exception, otherwise
// the exception is rethrown; the $catch service should implement the
// Tundra/tundra.schema.exception:handler specification
//
// if specified, the $finally service is always invoked, whether an exception was
// thrown by $service or not
tundra.service:ensure($service, $catch, $finally, $pipeline);

// calls the given service dynamically, either synchronously or
// asynchronously; if asynchronous a service thread is returned which can be
// waited on to finish (joined) using tundra.service:join
tundra.service:invoke($service, $pipeline, $mode);

// waits for the given service thread to finish before returning the service
// output pipeline
tundra.service:join($thread);

// this service deliberately does nothing
tundra.service:nothing();

// returns the name of the current service, or null if invoked directly
tundra.service:self();

// sends the currently executing thread to sleep (temporarily cease
// execution) for the specified duration, subject to the precision and
// accuracy of system timers and schedulers
tundra.service:sleep($duration);

// returns true if the given service name exists and is actually a service
tundra.service:validate($service);
```

### Session

Services for storing and retrieving values in session state.

```java
// returns information about the current session, including values stored in state
tundra.session:get();

// stores the given key value pair in the current session's state
tundra.session:put($key, $value);
```

### Stream

Services for manipulating java.io.InputStream and java.io.OutputStream objects:

* #### tundra.stream:close

    Closes the given input stream or output stream, and releases any associated
    system resources.

    * Inputs:
      * `$stream` is an optional [java.io.InputStream] or [java.io.OutputStream] object
        to be closed. If specified, the stream is closed and any associated system
        resources are released. If not specified, this service does nothing.

* #### tundra.stream:copy

    Copies all data from the given input stream (or string or bytes) to the given 
    output stream, then closes the streams.

    * Inputs:
      * `$input` is an optional [java.io.InputStream] object containing data to be
        written to `$output`. If not specified, this service does nothing.
      * `$output` is an optional [java.io.OutputStream] object where data read from
        `$input` is to be written. If not specified, this service does nothing.

* #### tundra.stream:normalize

    Converts a string, bytes or input stream to an input stream.

    * Inputs:
      * `$object` is an optional [java.lang.String], byte[], or [java.io.InputStream] 
        object to be converted to a [java.io.InputStream] object. If not specified, 
        this service does nothing.
      * `$encoding` is the character set used to encode the character data when `$object`
        is specified as a [java.lang.String]. Defaults to the Java virtual machine 
        [default charset].

    * Outputs:
      * `$stream` is an optional [java.io.InputStream] object from which can be read the 
        data represented by `$object`. If `$object` was not specified, no `$stream` is 
        returned.

[java.lang.String]: <http://docs.oracle.com/javase/6/docs/api/java/lang/String.html>
[java.io.InputStream]: <http://docs.oracle.com/javase/6/docs/api/java/io/InputStream.html>
[java.io.OutputStream]: <http://docs.oracle.com/javase/6/docs/api/java/io/OutputStream.html>

### String

Services for manipulating java.lang.String objects:

```java
// returns the number of characters in the given string
tundra.string:length($string);

// returns all the lines in the given string as a list
tundra.string:lines($string);

// converts the String value identified by $key in the given $scope IData document (or the pipeline,
// if not specified) to a new list of type String[] containing the original value as its single item,
// unless the original value is already list; the given key can be simple or fully qualified, such
// as a/b/c[0]/d
tundra.string:listify($key, $scope);

// returns the given string in lower case
tundra.string:lowercase($string, $locale);

// returns whether the given regular expression pattern matches the given
// string
// refer: <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html>
tundra.string:match($string, $pattern);

// converts a string, byte array or input stream to a string
tundra.string:normalize($object, $encoding);

// replaces all occurrences of the given regular expression pattern in the
// given string, with the replacement string
// refer: <http://download.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html>,
//        <http://docs.oracle.com/javase/6/docs/api/java/util/regex/Matcher.html>
tundra.string:replace($string, $pattern, $replacement, $literal?);

// splits the given string around matches of the given regular expression pattern
tundra.string:split($string, $pattern);

// replaces runs of one or more whitespace characters (space, tab, carriage return,
// line feed) with a single space character
tundra.string:squeeze($string);

// attempts variable substitution on the given string by replacing all occurrences of
// substrings matching "%key%" with the associated (optionally scoped) value;
// optionally replaces null or non-existent values with the given default value
tundra.string:substitute($string, $pipeline, $default);

// returns the given string with leading and trailing whitespace removed
tundra.string:trim($string);

// returns the given string in upper case
tundra.string:uppercase($string, $locale);
```

### System

* #### tundra.system:reflect

    Returns information about Integration Server, such as the software version, 
    environment settings, Java properties, well-known directory locations, and
    memory usage.

    * Outputs:
      * $system is a returned IData document containing the software version, 
        environment settings, Java properties, well-known directory locations, 
        and memory usage.

### URI

Services for parsing and emitting Uniform Resource Identifier (URI) strings.

* #### tundra.uri:decode

    Decodes a URI-encoded (application/x-www-form-urlencoded) string, according
    to [RFC 2396].

    The following rules are applied in the conversion:
    - The alphanumeric characters "a" through "z", "A" through "Z" and "0" 
      through "9" remain the same.
    - The special characters ".", "-", "*", and "_" remain the same.
    - The plus sign "+" is converted into a space character " " .
    - A sequence of the form "%xy" will be treated as representing a byte 
      where xy is the two-digit hexadecimal representation of the 8 bits. Then, 
      all substrings that contain one or more of these byte sequences 
      consecutively will be replaced by the character(s) whose encoding would 
      result in those consecutive bytes. The encoding scheme used to decode 
      these characters may be specified, or if unspecified, the default 
      encoding of the platform will be used.

    Implemented with the [java.net.URLDecoder class].

    * Inputs:
      * `$string` is a string containing URI-encoded data to be decoded.
      * `$encoding` is the character set used to determine what characters are 
        represented by any consecutive sequences of the form "%xy". Defaults 
        to the Java virtual machine [default charset].

    * Outputs:
      * `$string` is the decoded input string.

* #### tundra.uri:emit

    Emits a Uniform Resource Identifier ([URI]) string, according to [RFC 2396], 
    given its constituent parts.

    URIs can be categorized as either hierarchical, where the scheme and body 
    parts are separated by the character sequence '://', or opaque, where the 
    scheme and body parts are separated by a ':' character.

    Examples of hierarchical URIs:
    - http://example.com/
    - ftp://example.com/path/file.txt

    Examples of opaque URIs:
    - mailto:john.doe@example.com
    - news:comp.lang.java
    - urn:isbn:096139210x

    Opaque URIs are constructed according to the following syntax:

        (scheme:)body(?query)(#fragment)

    Where brackets (...) delineate optional components and the characters 
    ':', '?', and '#' stand for themselves.

    Hierarchical URIs are constructed according to the following syntax:

        (scheme:)(//authority)(path)(?query)(#fragment)

    Where the characters ':', '/', '?', and '#' stand for themselves. The authority
    component, if specified, can be either server-based or registry-based. If 
    server-based, it is constructed according to the syntax:

        (user:password@)host(:port)

    Implemented with the [java.net.URI class].

    * Inputs:
      * `$uri` is an IData document containing the constituent parts to construct
        a new URI string with.

    * Outputs:
      * `$string` is the resulting URI input string.

* #### tundra.uri:encode

    URI encodes (application/x-www-form-urlencoded) a string, according
    to [RFC 2396].

    The following rules are applied in the conversion:
    - The alphanumeric characters "a" through "z", "A" through "Z" and "0" 
      through "9" remain the same.
    - The special characters ".", "-", "*", and "_" remain the same.
    - The plus sign "+" is converted into a space character " " .
    - A sequence of the form "%xy" will be treated as representing a byte 
      where xy is the two-digit hexadecimal representation of the 8 bits. Then, 
      all substrings that contain one or more of these byte sequences 
      consecutively will be replaced by the character(s) whose encoding would 
      result in those consecutive bytes. The encoding scheme used to decode 
      these characters may be specified, or if unspecified, the default 
      encoding of the platform will be used.

    Implemented with the [java.net.URLEncoder class].

    * Inputs:
      * `$string` is a string containing data to be URI-encoded.
      * `$encoding` is the character set used to obtain the bytes for unsafe 
        characters.. Defaults to the Java virtual machine [default charset].

    * Outputs:
      * `$string` is the URI-encoded input string.

* #### tundra.uri:parse

    Parses a Uniform Resource Identifier ([URI]) string, according to [RFC 2396], 
    to its constituent parts.  

    URIs can be categorized as either hierarchical, where the scheme and body 
    parts are separated by the character sequence '://', or opaque, where the 
    scheme and body parts are separated by a ':' character.

    Examples of hierarchical URIs:
    - http://example.com/
    - ftp://example.com/path/file.txt

    Examples of opaque URIs:
    - mailto:john.doe@example.com
    - news:comp.lang.java
    - urn:isbn:096139210x

    Opaque URIs are constructed according to the following syntax:

        (scheme:)body(?query)(#fragment)

    Where brackets (...) delineate optional components and the characters 
    ':', '?', and '#' stand for themselves.

    Hierarchical URIs are constructed according to the following syntax:

        (scheme:)(//authority)(path)(?query)(#fragment)

    Where the characters ':', '/', '?', and '#' stand for themselves. The authority
    component, if specified, can be either server-based or registry-based. If 
    server-based, it is constructed according to the syntax:

        (user:password@)host(:port)

    Where the characters '@' and ':' stand for themselves.

    Opaque URI bodies are not subject to further parsing, however, to accomodate 
    the mailto: URI's use of a query string for additional data (such as cc, bcc, 
    subject, and body), this service checks if the body contains a query string 
    and, if so, parses the query string also.

    Implemented with the [java.net.URI class].

    * Inputs:
      * `$string` is the a URI string to be parsed.

    * Outputs:
      * `$uri` is an IData document containing the constituent parts of the parsed
        URI string.

[java.net.URLDecoder class]: <http://docs.oracle.com/javase/6/docs/api/java/net/URLDecoder.html>
[java.net.URLEncoder class]: <http://docs.oracle.com/javase/6/docs/api/java/net/URLEncoder.html>
[java.net.URI class]: <http://docs.oracle.com/javase/6/docs/api/java/net/URI.html>
[RFC 2396]: <http://www.ietf.org/rfc/rfc2396.txt>
[URI]: <http://www.w3.org/Addressing/>

### User

```java
// returns the current Integration Server user used to invoke this service
tundra.user:current();
```

### XML

* #### tundra.xml:validate

    Validates the given content as [XML], and optionally against an [XML] 
    schema ([XSD]). Uses the Simple API for XML ([SAX]) algorithm for 
    parsing which, as it is event-based, is not memory-constrained and
    can handle arbitrarily large documents when parsing from an input stream.

    * Inputs:
      * `$content` is a string, byte array, or input stream containing 
        (potentially) [XML] data.
      * `$content.encoding` is the character set used by `$content` if provided as 
        a byte array or input stream. Defaults to the Java virtual machine 
        [default charset].
      * `$schema` is a string, byte array, or input stream containing [XSD] data.
      * `$schema.encoding` is the character set used by `$schema` if provided as 
        a byte array or input stream. Defaults to the Java virtual machine 
        [default charset].
      * `$raise?` is an optional boolean flag, if true and `$content` is malformed
        or invalid an exception will be thrown. If false, no exception will
        be thrown. Defaults to false.

    * Outputs:
      * `$valid?` is true if the given `$content` is well-formed [XML] and, if a
        `$schema` was specified, valid when compared to the given `$schema`.
      * `$errors` is an optional list of the errors detected by the parser in
        the given `$content`, provided when `$valid?` is false.

[SAX]: <http://en.wikipedia.org/wiki/Simple_API_for_XML>
[XML]: <http://www.w3.org/XML/>
[XSD]: <http://www.w3.org/XML/Schema>

### XPath

* #### tundra.xpath:exists

    Returns true if the given XPath expression `$expression` exists the given 
    XML `$content`.

    * Inputs:
      * `$content` is a string, byte array, or input stream containing XML data.
      * `$encoding` is the character set used by `$content` if provided as a byte 
        array or input stream. Defaults to the Java virtual machine [default 
        charset].
      * `$expression` is the [XPath expression] to be tested against `$content`.

    * Outputs:
      * `$exists?` is true if the XPath expression was found to exist in the 
        given `$content`.

[XPath expression]: <http://www.w3.org/TR/xpath/>

[default charset]: <http://docs.oracle.com/javase/6/docs/api/java/nio/charset/Charset.html#defaultCharset()>
[java.text.SimpleDateFormat]: <http://docs.oracle.com/javase/6/docs/api/java/text/SimpleDateFormat.html>

## Contributions

1. Check out the latest master to make sure the feature hasn't been implemented
   or the bug hasn't been fixed yet
2. Check out the issue tracker to make sure someone already hasn't requested it
   and/or contributed it
3. Fork the project
4. Start a feature/bugfix branch
5. Commit and push until you are happy with your contribution
6. Make sure to add tests for it. This is important so it won't break in a future
   version unintentionally

Please try not to mess with the package version, or history. If you want your
own version please isolate it to its own commit, so it can be cherry-picked
around.

## Copyright

Copyright © 2012 Lachlan Dowding. See license.txt for further details.
