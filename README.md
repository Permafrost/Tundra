# Tundra ❄

A package of useful services for webMethods Integration Server 7.1 or higher.

## Dependencies

Tundra is dependent on the following packages:

* WmFlatFile
* WmPublic
* WmRoot

## Installation

You have two choices for installing Tundra: git or zip. If you are comfortable using git,
I recommend this method as you can then easily switch between package versions using git
checkout and download new versions using git fetch.

### Using Git

To install with this method, first make sure that:
* Git is [installed](http://git-scm.com/downloads) on your Integration Server
* Your Integration Server has internet access to https://github.com (for cloning the repository)
* The dependent packages listed above are installed and enabled on your Integration Server

From your Integration Server installation:

```sh
$ cd ./packages/
$ git clone https://github.com/Permafrost/Tundra.git
$ cd ./Tundra/
$ git checkout v<n.n.n> # where <n.n.n> is the desired version
```

Then activate and enable the Tundra package from the package management web page on your Integration Server
web administration site.

### Using Zip

1. Download a zip of the desired version of the package from https://github.com/Permafrost/Tundra/tags
2. Extract the downloaded Tundra-n.n.n.zip file to your Integration Server's ./packages/ folder
3. Rename the ./packages/Tundra-n.n.n/ folder to ./packages/Tundra/
4. Activate and enable Tundra from the package management web page on your Integration Server web
administration site

## Upgrading

When upgrading you have to choose the same method used to originally install the package. Unfortunately, if git
wasn't used to install the package then you can't use git to upgrade it either. However, if you want to switch
to using git to manage the package, delete the installed package and start over using the git method for
installation.

### Using Git

To upgrade with this method, first make sure that:
* Git is [installed](http://git-scm.com/downloads) on your Integration Server
* Your Integration Server has internet access to https://github.com (for fetching updates from the repository)
* The dependent packages listed above are installed and enabled on your Integration Server
* You originally installed Tundra using the git method described above

From your Integration Server installation:

```sh
$ cd ./packages/Tundra/
$ git fetch
$ git checkout v<n.n.n> # where <n.n.n> is the desired updated version
```

Then reload the Tundra package from the package management web page on your Integration Server web administration
site.

### Using Zip

1. Delete your existing version of the Tundra package using the package management web page on your Integration
Server web administration site
2. Download a zip of the desired updated version of the package from https://github.com/Permafrost/Tundra/tags
3. Extract the downloaded Tundra-n.n.n.zip file to your Integration Server's ./packages/ folder
3. Rename the ./packages/Tundra-n.n.n/ folder to ./packages/Tundra/
4. Activate and enable Tundra from the package management web page on your Integration Server web administration
site

## Conventions

1. All input and output pipeline arguments are prefixed with '$' as a poor-man's
   scoping mechanism (typical user-space variables will be unprefixed)
2. All boolean arguments are suffixed with a '?'
3. Single-word argument names are preferred. Where multiple words are necessary,
   words are separated with a '.'
4. Service namespace is kept flat. Namespace folders are usually nouns. Service
   names are usually verbs, indicating the action performed on the noun (parent
   folder)
5. All private elements are kept in the tundra.support folder. All other
   elements comprise the public API of the package. As the private
   elements do not contribute to the public API, they are liable to change at
   any time. Enter at your own risk
6. *Almost* all services are written in Java, and are *almost* always overloaded
   by a backing method in the shared source, which provides the actual
   implementation. This way, backing methods can be used by other backing
   methods directly, without needing to deal with the IData/IDataCursor/
   IDataUtil nastiness that a fronting Java service is required to

## Tests

*Almost* every service in Tundra has unit tests, located in the
tundra.support.test folder.

To run the test suite, either:
* run tundra:test($package = "Tundra") service directly
* visit <http://localhost:5555/invoke/tundra/test?$package=Tundra>
  (substitute your own Integration Server host and port for localhost:5555)

## Services

Top-level services for the most common tasks:

```java
// writes a message to the server log, automatically prefixed with the call
// stack
tundra:log($message, $level);

// runs all *test*:should* services in the given package, returning the test
// results (a test case passes if no exceptions are thrown)
tundra:test($package);
```

### Assertion

Supports unit testing by providing the following bare-bones assertion services:

```java
// throws an assertion error if the expected and actual documents are not
// equal
tundra.assertion.document:equal($expected, $actual, $message);

// throws an assertion error if the expected and actual documents are equal
tundra.assertion.document:unequal($expected, $actual, $message);

// throws an assertion error if the expected and actual document lists are
// not equal
tundra.assertion.list.document:equal($expected[], $actual[], $message);

// throws an assertion error if the expected and actual document lists are
// equal
tundra.assertion.list.document:unequal($expected[], $actual[], $message);

// throws an assertion error if the expected and actual lists are not equal
tundra.assertion.list.object:equal($expected[], $actual[], $message);

// throws an assertion error if the given list is null
tundra.assertion.list.object:exists($list[], $message);

// throws an assertion error if the given list is not an instance of the
// given class
tundra.assertion.list.object:instance($list[], $class, $message);

// throws an assertion error if the given list is not null
tundra.assertion.list.object:nothing($list[], $message);

// throws an assertion error if the expected and actual lists are equal
tundra.assertion.list.object:unequal($expected[], $actual[], $message);

// throws an assertion error if the expected and actual string lists are not
// equal
tundra.assertion.list.string:equal($expected[], $actual[], $message);

// throws an assertion error if the expected and actual string lists are
// equal
tundra.assertion.list.string:unequal($expected[], $actual[], $message);

// throws an assertion error if the expected and actual objects are not equal
tundra.assertion.object:equal($expected, $actual, $message);

// throws an assertion error if the given object is null
tundra.assertion.object:exists($object, $message);

// throws an assertion error if the given object is not an instance of the
// given class
tundra.assertion.object:instance($object, $class, $message);

// throws an assertion error if the given object is not null
tundra.assertion.object:nothing($object, $message);

// throws an assertion error if the expected and actual objects are equal
tundra.assertion.object:unequal($expected, $actual, $message);

// throws an assertion error if this service is executed
tundra.assertion.step:unreached($message);

// throws an assertion error if the expected and actual strings are not equal
tundra.assertion.string:equal($expected, $actual, $message);

// throws an assertion error if the expected and actual strings are equal
tundra.assertion.string:unequal($expected, $actual, $message);
```

### Base64

Services for encoding and decoding base64 strings.

```java
// base64 decodes the given string, byte array, or input stream
// refer: <http://en.wikipedia.org/wiki/Base64>
tundra.base64:decode($base64, $encoding, $mode);

// base64 encodes the given string, byte array, or input stream
// refer: <http://en.wikipedia.org/wiki/Base64>
tundra.base64:encode($content, $encoding, $mode);
```

### Bytes

Services for manipulating byte arrays:

```java
// converts a string, byte array or input stream to a byte array
tundra.bytes:normalize($object, $encoding);

// returns the length of the given byte array
tundra.bytes:length($bytes);
```

### Content

Services for manipulating arbitrary textual content, such as XML or CSV content:

```java
// Delivers arbitrary content (string, bytes, or input stream) to the given destination URI.
//
// Supports the following delivery protocols / URI schemes:
//   - file: writes the given content to the file specified by the destination URI.  The
//           following additional options can be provided via the $pipeline document:
//           - $mode: append / write
//   - http: transmits the given content to the destination URI. The following adttional
//           options can be provided via the $pipeline document:
//           - $method: get / put / post / delete / head / trace / options
//           - $headers/*: additional HTTP headers as required
//           - $authority/user: the username to log on to the remote web server with
//           - $authority/password: the password to log on to the remote web server with
//   - https: refer to http
//
// Additional delivery protocols can be implemented by creating a service named for the
// URI scheme in the folder tundra.support.content.deliver.  Services in this folder should
// implement the tundra.support.content.deliver.protocol:handler specification.
//
// An optional response message, useful for logging, may be returned by specific delivery protocols.
tundra.content:deliver($content, $encoding, $destination, $pipeline);

// converts an IData document to an XML or flat file string, byte array, or
// input stream
tundra.content:emit($document, $encoding, $schema, $mode);

// parses XML and flat file content (specified as a string, byte array, or
// input stream) into an IData document
tundra.content:parse($content, $encoding, $schema);

// one-to-many conversion of XML or flat file content to another format; calls the given
// splitting service, passing the parsed content as an input, and emitting the split
// list of contents as output; the splitting service must accept a single IData document,
// and return an IData document list
tundra.content:split($content, $service, $pipeline, $encoding.input, $encoding.output, $schema.input, $schema.output, $service.input, $service.output, $mode.output);

// one-to-one conversion of XML or flat file content to another format; calls the given
// translation service, passing the parsed content as an input, and emitting
// the translated content as output; the translation service must accept a single IData
// document and return a single IData document
tundra.content:translate($content, $service, $encoding.input, $encoding.output, $schema.input, $schema.output, $service.input, $service.output, $mode.output);
```

### Datetime

Services for manipulating date, time and datetime strings:

```java
// adds a duration of time to the given datetime
tundra.datetime:add($datetime, $duration);

// compares two datetime strings, indicating position in time relative to one
// another; returns three booleans: $before?, $equal?, and $after?
tundra.datetime:compare($datetime.x, $datetime.y);

// concatenates a date and time into a single datetime
tundra.datetime:concatenate($date, $time);

// returns the duration of time between two datetimes
tundra.datetime:duration($datetime.start, $datetime.end);

// formats a datetime that conforms to the input pattern, according to the
// output pattern
tundra.datetime:format($datetime, $pattern.input, $pattern.output);

// returns the current datetime
tundra.datetime:now();

// subtracts a duration of time from the given datetime
tundra.datetime.subtract($datetime, $duration);

// returns true if the given datetime conforms to the given pattern
tundra.datetime.validate($datetime, $pattern);
```

### Decimal

Services for working with arbitrary precision decimals (uses java.math.BigDecimal as its implementation):

```java
// returns the absolute value of the given decimal
tundra.decimal:absolute($decimal);

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

// lists a directory, optionally filtering based on the given regular
// expression pattern
tundra.directory:list($directory, $pattern, $recurse?);

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

```java
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

// sets the value associated with the given key in the given IData
// document
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.document:put($document, $key, $value);

// renames the value with the source key to have the target key in the given IData document
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.document:rename($document, $key.source, $key.target);

// one-to-many conversion of an IData document to an IData[] document list; calls the given
// splitting service, passing the document as an input, and emitting the split
// list of documents as output; the splitting service must accept a single IData document,
// and return an IData document list; the splitting service may also return a list of $schemas
// that each return document conforms to
tundra.document:split($document, $service, $pipeline, $service.input, $service.output);

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

```java
// Provides an HTTP client for interacting with HTTP servers. A custom
// response handler can be specified ($service), when the standard handler
// (tundra.support.http.client.response:handle) does not suffice.
tundra.http:client($uri, $method, $content, $encoding, $authority, $headers, $timeout, $service);
```

### ID

```java
// generates an immutable 128-bit universally unique identifier
// refer: <http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html>
tundra.id:generate();
```

### Integer

Services for working with arbitrary precision integers (uses java.math.BigInteger as its implementation):

```java
// returns the absolute value of the given integer
tundra.integer.absolute($integer);

// adds the given integers together, returning the result
tundra.integer.add($integers[]);

// decrements the given integer by one
tundra.integer.decrement($integer);

// divides the given integer x by y, returning the result
tundra.integer.divide($integer.x, $integer.y);

// increments the given integer by one
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

### List

Services for manipulating lists:

#### Content

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

#### Datetime List

```java
// formats a list of datetimes that conform to the input pattern, according
// to the output pattern
tundra.list.datetime:format($list[], $pattern.input, $pattern.output);
```

#### Document List

Services for manipulating document (com.wm.data.IData) lists:

```java
// appends a single item to the end of a list, such that appending an item to
// a list containing n items results in a new list of n + 1 items
tundra.list.document:append($list[], $item);

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
// document as output; the splitting service must accept an IData[] document list, and return
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

#### Duration List

```java
// formats a list of duration strings according to the desired pattern
// (a start instant, $datetime, may be required when formatting fields with
// indeterminate values, such as converting months to days, because the
// number of days in a month varies)
tundra.list.duration:format($list[], $datetime, $pattern.input, $pattern.output);

// returns the sum of all the given durations, returning (x1 + x2 + ... + xn)
tundra.duration:sum($durations[]);
```

#### Object List

Services for manipulating java.lang.Object lists:

```java
// appends a single item to the end of a list, such that appending an item to
// a list containing n items results in a new list of n + 1 items
tundra.list.object:append($list[], $item);

// removes all null items from the given list, thereby shortening the length
// of the list
tundra.list.object:compact($list[]);

// returns a new list containing all the items in the given $list and $items
// input arguments
tundra.list.object:concatenate($list.x[], $list.y[]);

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
tundra.list.object:insert($list[], $item, $index);

// returns true if the list is an instance of given class
tundra.list.object:instance($list[], $class);

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
tundra.list.object:prepend($list[], $item);

// sets the value of the item stored at a given index in a list (supports forward and
// reverse indexing)
tundra.list.object:put($list[], $item, $index);

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

#### Service List

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

#### String List

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
// scoped) value
tundra.list.string:substitute($list[], $pipeline);

// removes all leading and trailing whitespace
tundra.list.string:trim($list[]);

// returns a new list with all duplicates from the given list removed, such
// that no two items are equal
tundra.list.string:unique($list[]);

// converts each item to upper case
tundra.list.string:uppercase($list[]);
```

### Node

Services for querying Integration Server namespace nodes:

```java
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

// copies the value associated with the source key to the target key in the pipeline
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.pipeline:copy($key.source, $key.target);

// removes the element with the given key from the pipeline
// keys can be simple or fully qualified, such as a/b/c[0]/d
tundra.pipeline:drop($key);

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
// the exception is rethrown
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

```java
// closes the given input or output stream
tundra.stream:close($stream);

// copies all data from the given input stream (or string or bytes) to the
// given output stream, then closes the streams
tundra.stream:copy($input, $output);

// converts a string, byte array or input stream to an input stream
tundra.stream:normalize($object, $encoding);
```

### String

Services for manipulating java.lang.String objects:

```java
// returns the number of characters in the given string
tundra.string:length($string);

// returns all the lines in the given string as a list
tundra.string:lines($string);

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
// substrings matching "%key%" with the associated (optionally scoped) value
tundra.string:substitute($string, $pipeline);

// returns the given string with leading and trailing whitespace removed
tundra.string:trim($string);

// returns the given string in upper case
tundra.string:uppercase($string, $locale);
```

### URI

Services for parsing and emitting Uniform Resource Identifier (URI) strings.

```java
// Decodes a URL-encoded (application/x-www-form-urlencoded) string.
//
// The following rules are applied in the conversion:
//  - The alphanumeric characters "a" through "z", "A" through "Z" and "0"
//    through "9" remain the same.
//  - The special characters ".", "-", "*", and "_" remain the same
//  - The plus sign "+" is converted into a space character " ".
//  - A sequence of the form "%xy" will be treated as representing a byte
//    where xy is the two-digit hexadecimal representation of the 8 bits. Then,
//    all substrings that contain one or more of these byte sequences.
//    consecutively will be replaced by the character(s) whose encoding would
//    result in those consecutive bytes. The encoding scheme used to decode
//    these characters may be specified, or if unspecified, the default
//    encoding of the platform will be used.
//
// Refer: <http://docs.oracle.com/javase/6/docs/api/java/net/URLDecoder.html>.
tundra.uri:decode($string);

// Emits a Uniform Resource Identifier (URI) string, according to RFC 2396
// <http://www.ietf.org/rfc/rfc2396.txt>, given its constituent parts.
//
// URIs can be categorized as either hierarchical, where the scheme and body
// parts are separated by the character sequence '://', or opaque, where the scheme
// and body parts are separated by a ':' character.
//
// Examples of hierarchical URIs:
//   - http://example.com/
//   - ftp://example.com/path/file.txt
//
// Examples of opaque URIs:
//   - mailto:john.doe@example.com
//   - news:comp.lang.java
//   - urn:isbn:096139210x
//
// Opaque URIs are constructed according to the following syntax:
//
//   [scheme:]body[?query][#fragment]
//
// Where square brackets [...] delineate optional components and the characters ':',
// '?', and '#' stand for themselves.
//
// Hierarchical URIs are constructed according to the following syntax:
//
//   [scheme:][//authority][path][?query][#fragment]
//
// Where the characters ':', '/', '?', and '#' stand for themselves. The authority
// component, if specified, can be either server-based or registry-based. If server-
// based, it is constructed according to the syntax:
//
//   [user:password@]host[:port]
//
// refer: <http://docs.oracle.com/javase/6/docs/api/java/net/URI.html>.
tundra.uri:emit($uri);

// URL encodes (application/x-www-form-urlencoded) a string.
//
// The following rules are applied in the conversion:
//   - The alphanumeric characters "a" through "z", "A" through "Z" and "0"
//     through "9" remain the same.
//   - The special characters ".", "-", "*", and "_" remain the same.
//   - All other characters are unsafe and are first converted into one or
//     more bytes using some encoding scheme. Then each byte is represented
//     by the 3-character string "%xy", where xy is the two-digit hexadecimal
//     representation of the byte. The recommended encoding scheme to use is
//     UTF-8. However, for compatibility reasons, if an encoding is not
//     specified, then the default encoding of the platform is used.
//
// Refer: <http://docs.oracle.com/javase/6/docs/api/java/net/URLEncoder.html>.
tundra.uri:encode($string);

// Parses a Uniform Resource Identifier (URI) string, according to RFC 2396
// <http://www.ietf.org/rfc/rfc2396.txt>, to its constituent parts.
//
// URIs can be categorized as either hierarchical, where the scheme and body
// parts are separated by the character sequence '://', or opaque, where the scheme
// and body parts are separated by a ':' character.
//
// Examples of hierarchical URIs:
//   - http://example.com/
//   - ftp://example.com/path/file.txt
//
// Examples of opaque URIs:
//   - mailto:john.doe@example.com
//   - news:comp.lang.java
//   - urn:isbn:096139210x
//
// URIs are constructed from the following parts:
//
//   [scheme:]body[?query][#fragment]
//
// Where square brackets [...] delineate optional components and the characters ':'
// and '#' stand for themselves.
//
// Hierarchical URI bodies are subject to further parsing according to the following
// syntax:
//
//   [scheme:][//authority][path][file][?query][#fragment]
//
// Where the characters ':', '/', '?', and '#' stand for themselves. The authority
// component, if specified, can be either server-based or registry-based. If server-
// based, it can be further parsed according to the syntax:
//
//   [user:password@]host[:port]
//
// Where the characters '@' and ':' stand for themselves.
//
// Opaque URI bodies are not subject to further parsing, however, to accomodate
// the mailto: URI's use of a query string for additional data (such as cc, bcc,
// subject, and body), this service checks if the body contains a query string and,
// if so, parses the query string also.
//
// Refer: <http://docs.oracle.com/javase/6/docs/api/java/net/URI.html>
tundra.uri.parse($string);
````

### XML

```java
// validates the given string, byte array, or input stream as XML, optionally against
// an XML schema (XSD) also specified as a string, byte array or input stream; uses
// the Simple API for XML (SAX) algorithm for parsing which, as it is event-based, is
// not memory-constrained and can handle arbitrarily large documents when parsing from
// an input stream
tundra.xml:validate($content, $content.encoding, $schema, $schema.encoding, $raise?);
```

### XPath

```java
// returns true if the given XPath expression $expression exists in the given XML $content
tundra.xpath:exists($content, $encoding, $expression, $namespace);
```

## Contributions

1. Check out the latest master to make sure the feature hasn't been implemented
   or the bug hasn't been fixed yet
2. Check out the issue tracker to make sure someone already hasn't requested it
   and/or contributed it
3. Fork the project
4. Start a feature/bugfix branch
5. Commit and push until you are happy with your contribution
6. Make sure to add tests for it. This is important so it won't in a future
   version unintentionally

Please try not to mess with the package version, or history. If you want your
own version please isolate it to its own commit, so it can be cherry-picked
around.

## Copyright

Copyright © 2012 Lachlan Dowding. See license.txt for further details.
