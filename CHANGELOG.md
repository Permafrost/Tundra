# 0.0.34 (2019-08-06)

* add `tundra.bool:random`, `tundra.bytes:random`, `tundra.decimal:random`, and `tundra.integer:random`, for returning cryptographically strong randomly generated numbers
* add `tundra.collection.map.*:documentify` to convert a `Map` object to an `IData` document
* add `tundra.configuration:clear` and `tundra.configuration:refresh` for clearing the in-memory cache and refreshing the in-memory cache respectively
* add `tundra.document:capitalize` and `tundra.list.document:capitalize` for capitalizing strings in `IData` documents and `IData[]` document lists
* add `tundra.schedule:runnable` to determine if a scheduled task should run or continue to run based on the task's state and the server's task scheduler's state
* add `tundra.soap.fault:accept` to provide a fault handler that does not thow an exception
* add `tundra.string:translate` for translating arbitrarily specified string values using a translation table
* add `tundra.thread:prioritize` for changing the priority of the current thread
* add experimental HTTP logging feature, disabled by default; to enable run `tundra.support.http.log:start` and then HTTP requests will be logged in `./logs/tundra-http.log`, and then to disable run `tundra.support.http.log:stop`
* change `./pub/service/statistics.dsp` to also show the number of successful and failed invocations for each sampled service
* change `tundra.assertion.document:equal` and `tundra.assertion.document:unequal` to ignore key position by default
* change `tundra.assertion.list.document:equal` and `tundra.assertion.list.document:unequal` to ignore key position by default
* change `tundra.configuration:list` to return list and document lengths
* change `tundra.content:emit` to use `tundra.xml:encode` for escaping values prior to XML serialization, and therefore attributes values will now be escaped in a way that preserves whitespace characters
* change `tundra.content:emit` use of `tundra.xml:encode` to support custom XML attribute prefixes
* change `tundra.content:retrieve` server log statement format to be consistent
* change `tundra.csv:emit` and `tundra.csv:parse` to support customizing the escape character, quote character, and quote mode used
* change `tundra.csv:emit` to support serializing a `String[]` string list of values in addition to an `IData[]` document list of records
* change `tundra.csv:parse` to support the string `$null` as a way of disabling quoting completely via the quote character input argument
* change `tundra.document:amend` and dependent services to support `merge`, `create`, `update`, and `delete` actions on key value pairs
* change `tundra.document:condense` and `tundra.list.document:condense` to support condensing keys as well as values
* change `tundra.document:emit` and `tundra.document:parse` input argument names to be consistent, and to support XML, JSON, and YAML formats as per the `tundra.pipeline:emit` and `tundra.pipeline:parse` services respectively
* change `tundra.excel:emit` input argument names to be consistent
* change `tundra.file:write` to support new `create` mode which will throw an exception if the file already exists
* change `tundra.hjson:emit` and `tundra.hjson:parse` input argument names to be consistent
* change `tundra.html:emit` input argument names to be consistent
* change `tundra.http:client` to rename `$service` input to `$response.handler` to not clash with other more generic uses of the `$service` variable name
* change `tundra.http:client` to silently support all `pub.client:http` inputs
* change `tundra.json:emit` and `tundra.json:parse` input argument names to be consistent
* change `tundra.json:emit` and `tundra.json:parse` to improve performance by 1000x or more by using the `org.glassfish.json` implementation classes directly, which avoids the disk access caused by the class loading in the `javax.json.spi.JsonProvider.provider()` method on every invocation
* change `tundra.object:convert` to support `$mode` of `base64`
* change `tundra.pipeline:emit` and `tundra.pipeline:parse` input argument names to be consistent
* change `tundra.service:ensure` and `tundra.service:invoke` to support changing the current thread's priority before service invocation
* change `tundra.string:capitalize` to support capitalizing arbitrarily specified `String`, `String[]`, and `String[][]` values
* change `tundra.string:concatenate` to support sanitizing `IData` operands
* change `tundra.string:condense` to support condensing arbitrarily specified `String`, `String[]`, and `String[][]` values
* change `tundra.string:slice` to support slicing arbitrarily specified `String`, `String[]`, and `String[][]` values
* change `tundra.string:split` to support splitting arbitrarily specified strings
* change `tundra.user:current` to use current invoke state user for implementation
* change `tundra.xml:emit` and `tundra.xml:parse` input argument names to be consistent
* change `tundra.xml:encode` to support custom XML attribute prefixes
* change `tundra.yaml:emit` and `tundra.yaml:parse` input argument names to be consistent
* change `./pub/**/*.dsp` web page styles to give sortable table headings sortable affordances, such as showing which column has been sorted and in which direction, and changing the mouse cursor to be a pointer when hovering over headings that support sorting via a mouse click, and also update to use a grey colour schema to clash less with the new styling used in Integration Server 10.x
* fix `tundra.configuration:get` `NullPointerException` when password cannot be retrieved from password store to be ignored
* fix `tundra.duration:multiply` to correctly format output using specified pattern
* fix `tundra.schedule:create` to not modify `$schedule` input parameter
* fix `tundra.string:concatenate` to not return anything when given all null input arguments
* rename `tundra.soap.fault:handle` to `tundra.soap.fault:raise`

# 0.0.33 (2018-08-30)

* add `tundra.directory:tar` for adding files in a given directory to a new tar archive, optionally compressed with gzip
* add `tundra.directory:zip` for compressing files in a given directory into a zip archive
* add `tundra.mime.type:extensions` for returning the file extensions associated with a given MIME type
* add Tundra > Service Statistics web dashboard for collecting and viewing service execution duration statistics
* change `tundra.content.retrieve:file` to purge the archive directory on a different thread to the main thread which is now used exclusively for processing inbound files, and to purge less frequently
* change `tundra.directory:ls` to return a sorted listing and to also return the length of the listing
* change `tundra.document:join` to support converting nulls to blanks sanitization
* change `tundra.file:write` service comment to reflect that this service attempts to create the directories comprising the file path if the path does not already exist before writing the file
* change `tundra.http:client` to support JSSE and fallback to non-JSSE automatically if TLS 1.1 or higher is not supported by the HTTP server
* change `tundra.list.object:join` to support converting nulls to blanks sanitization
* change `tundra.list.string:join` to support converting nulls to blanks sanitization
* change `tundra:test` to invoke sibling `:setup` and `:teardown` services, if they exist alongside the test case service, before and after the test case executes respectively; also now supports executing tests using multiple threads
* fix `./pub/assets/icons` to be accessible by `Administrators` and `Developers`
* fix `tundra.content.retrieve:ftp` to not include an extraneous `/` character between the path and file components of the `$content.source` argument provided to the content processing service
* fix `tundra.content.retrieve:sftp` to not include an extraneous `/` character between the path and file components of the `$content.source` argument provided to the content processing service
* fix `tundra.system:reflect` to correctly handle non-String properties
* fix `tundra.xml:decode` to correctly decode XML character entities
* fix `tundra.xml:encode` to correctly encode XML character entities; previously this service incorrectly used some HTML character entities which are not supported in XML
* fix the problem reported in Software AG Empower KB article 1780344 "webMethods CloudStreams Server - WmCloudstream package is partially loaded" by removing the dependency on the spring framework to avoid version clashes with other Software AG components that depend on spring, such as webMethods CloudStreams

# 0.0.32 (2018-04-20)

* add `tundra.configuration:peek` for inspecting and verifying an on disk package configuration without affecting the cached configuration
* add `tundra.datetime:timezone` for returning information about the time zone of a given datetime string
* add `tundra.document:empty` which returns a newly created empty `IData` document
* add `tundra.excel:emit` for serializing an `IData` document to a Microsoft Excel spreadsheet
* add `tundra.excel:parse` for deserializing a Microsoft Excel spreadsheet to an `IData` document
* add `tundra.list.document:find` for filtering an `IData[]` document list by values associated with a given key containing a given pattern
* add `tundra.list.document:match` for filtering an `IData[]` document list by values associated with a given key that match a given pattern
* add `tundra.schedule:invoke` for manually invoking a scheduled task's service with it's configured input pipeline
* add `tundra.string:prefixed` to check if a given string starts with a given prefix
* add `tundra.string:suffixed` to check if a given string ends with a given suffix
* add `tundra.string:wrap` for breaking a string into lines of a specified length
* change `tundra.list.document:group` to return list lengths in returned group structure for convenience
* change `tundra.list.string:match` to improve performance by avoiding reallocation of result arrays
* change `tundra.support.http.connect:response` to return HTML doctype in response body
* change `tundra.xml:validate` to throw either a `MalformedException` if the XML is malformed, or a `ValidationException` if a schema is specified and the XML is invalid, when `$raise?` is `true`
* fix `tundra.datetime:add` to correctly handle negative non-XML durations
* fix `tundra.datetime:parse` service comment formatting in Designer
* fix `tundra.datetime:subtract` to correctly handle negative non-XML durations
* fix `tundra.decimal:*` services to accept decimals using scientific notation as input, but to always return results normalized as plain decimal strings
* fix `tundra.directory:*` services to correctly handle `File.listFiles()` returning null
* fix `tundra.list.string:find` input name to be `$pattern.literal?` rather than `$literal?`
* fix `tundra.list.string:match` input name to be `$pattern.literal?` rather than `$literal?`
* fix `tundra.string:squeeze` to not return null when input is null or missing
* fix `tundra.timezone:*` services to correctly handle `GMT+hh:mm` and `GMT-hh:mm` time zone IDs
* fix `tundra.xml:encode` to declare correct input and output variables

# 0.0.31 (2018-01-09)

* add `tundra.document:condense` for replacing runs of multiple whitespace characters with a single space
* add `tundra.duration:start` and `tundra.duration:end` for using monotonic time to reliably measure elapsed durations
* add `tundra.list.document:condense` for replacing runs of multiple whitespace characters with a single space
* add `tundra.scheduler:pause` to pause the scheduled task manager from running any tasks
* add `tundra.scheduler:resume` to resume the scheduled task manager running tasks
* add `tundra.scheduler:status` to return the current status of the scheduled task manager
* add `tundra.string:build` to efficiently build a string using a `java.lang.StringBuilder` object
* add `tundra.string:condense` for replacing runs of multiple whitespace characters with a single space
* add `tundra.list.integer:add` for calculating an addition using each item in a list of integers
* add `tundra.list.integer:divide` for calculating a division using each item in a list of integers
* add `tundra.list.integer:multiply` for calculating a multiplication using each item in a list of integers
* add `tundra.list.integer:remainder` for calculating a division remainder using each item in a list of integers
* add `tundra.list.integer:subtract` for calculating a subtraction using each item in a list of integers
* add `tundra.sap.idoc:identify` to assign a `DOCNUM` to each item in a given `IDocList` equal to its list index
* add `tundra.sap.idoc:length` to return the length of a given `IDocList`
* add `tundra.sap.idoc:partition` to partition a given `IDocList` into a new `IDocList[]` where each item contains at most the given limit
* change `tundra.content.deliver:ftp` to support relative paths, which can be specified with a path starting with the prefix `./`, for example: `ftp://example.com/./relative/path/file`
* change `tundra.content.deliver:sap_idoc` to support partitioning an IDoc batch delivery into multiple TIDs using the limit query string value or `$limit` pipeline argument
* change `tundra.content.deliver:sftp` to default to treating a URI that does not specify a path as being an absolute path to the root of the file system
* change `tundra.content.deliver:sftp` to use the given filename with a `.tmp` suffix when writing the temporary file prior to renaming when the rename option is enabled
* change `tundra.content.retrieve:file` working and archive file names to use both current datetime (`yyyyMMddHHmmssSSS`) to provide the file processing datetime, and a newly-generated UUID to provide uniqueness
* change `tundra.content.retrieve:ftp` to default to treating paths as absolute unless they start with the prefix `./` to indicate that a relative path is being specified
* change `tundra.content.retrieve:ftp` to rename each file to have a `.tmp` suffix prior to retrieval
* change `tundra.content.retrieve:sftp` to default to treating a URI that does not specify a path as being an absolute path to the root of the file system
* change `tundra.content.retrieve:sftp` to rename each file to have a `.tmp` suffix prior to retrieval
* change `tundra.csv:emit` to support CSVs without header rows
* change `tundra.csv:parse` to support CSVs without header rows
* change `tundra.datetime:emit` to support `java.lang.Number` and `java.util.Calendar` objects, in addition to `java.util.Date` objects
* change `tundra.document:keys` to support filtering on associated values, and also return the length of the `$keys` list as `$keys.length`
* change `tundra.duration:*` services to support nanosecond precision
* change `tundra.list.string:find` to support literal patterns and use input arguments consistent with `tundra.document` regular expression services
* change `tundra.list.string:lowercase` implementation to Java for better performance
* change `tundra.list.string:match` to support literal patterns and use input arguments consistent with `tundra.document` regular expression services
* change `tundra.list.string:remove` to support literal patterns and use input arguments consistent with `tundra.document` regular expression services
* change `tundra.list.string:replace` to support literal patterns and use input arguments consistent with `tundra.document` regular expression services
* change `tundra.list.string:split` to support literal patterns and use input arguments consistent with `tundra.document` regular expression services
* change `tundra.list.string:squeeze` implementation to Java for better performance
* change `tundra.list.string:trim` implementation to Java for better performance
* change `tundra.list.string:uppercase` implementation to Java for better performance
* change `tundra.mime.type:classify` implementation to Java for better performance
* change `tundra.string:find` to support literal patterns and use input arguments consistent with `tundra.document` regular expression services
* change `tundra.string:match` to support literal patterns and use input arguments consistent with `tundra.document` regular expression services
* change `tundra.string:remove` to support literal patterns and use input arguments consistent with `tundra.document` regular expression services
* change `tundra.string:replace` to support literal patterns and use input arguments consistent with `tundra.document` regular expression services
* change `tundra.string:split` to support literal patterns and use input arguments consistent with `tundra.document` regular expression services
* deprecate `tundra.string:squeeze`, use `tundra.string:condense` instead
* fix `./pub/service/usage.dsp` rowspan bug for Event Manager threads
* fix `tundra.content.deliver:sap_idoc` to work around the WmSAP hard limit of 9,999 items in a `IDocList` by assigning `DOCNUM` explicitly to be each item's respective list index
* fix `tundra.content.deliver:sftp` when renaming from a temporary file name and therefore overwriting the destination file, to first delete the destination file before attempting the rename so that if the file already exists it will be overwritten
* fix `tundra.string:split` input argument to be correctly named `$pattern.literal?`

# 0.0.30 (2017-11-03)

* add `tundra.integer:format` as an alias for `tundra.decimal:format`
* add `tundra.pipeline:squeeze` for recursively trimming string values, removing empty string values, arrays, and `IData` documents, and removing nulls from the pipeline
* add `tundra.pipeline:trim` for recursively trimming all string values of leading and trailing whitespace in the pipeline
* change `tundra.content.retrieve:file` to process files in ascending filename order
* change `tundra.content:deliver` to support `sftp` delivery on Integration Server versions 9.0 and higher
* change `tundra.content:retrieve` to support `sftp` retrieval on Integration Server versions 9.0 and higher
* change `tundra.datetime:format` to support formatting one or more arbitrarily specified datetime strings in one invocation
* change `tundra.decimal:format` to support formatting one or more arbitrarily specified datetime strings in one invocation
* change `tundra.duration:format` to support formatting one or more arbitrarily specified datetime strings in one invocation
* change `tundra.html:decode` to support decoding one or more arbitrarily specified string values in one invocation
* change `tundra.html:encode` to support encoding one or more arbitrarily specified string values in one invocation
* change `tundra.http.response:accept` to drop null response body from `$response` argument
* change `tundra.pipeline:emit` to support JSON and YAML content in addition to `IData` XML content
* change `tundra.pipeline:parse` to support JSON and YAML content in addition to `IData` XML content
* change `tundra.support.receive:respond` to not return exception class in the HTTP response body when handling exceptions
* change `tundra.uri:decode` to support decoding one or more arbitrarily specified string values in one invocation
* change `tundra.uri:emit` to support unadorned path strings and files
* change `tundra.uri:encode` to support encoding one or more arbitrarily specified string values in one invocation
* change `tundra.uri:parse` to support unadorned path strings and files
* change `tundra.xml:decode` to support decoding one or more arbitrarily specified string values in one invocation
* change `tundra.xml:encode` to support encoding one or more arbitrarily specified string values in one invocation
* fix `tundra.content.deliver:http` to work correctly even if pipeline already contains an `IData` document named `$document`
* fix `tundra.content.retrieve:ftp` to allow arbitrary variables to be passed in the pipeline to the file processing service
* fix `tundra.content:retrieve` to work correctly for the `ftp` protocol when the file retrieve fails
* fix `tundra.http:client` to correctly default `$request/method` to `get` when not specified
* fix `tundra.support:access` to drop unrequired `$nodes` variable
* fix `tundra.uri:emit` to work correctly for opaque URIs with a body but no query string

# 0.0.29 (2017-09-08)

* add `tundra.bytes:transcode` for converting a `byte[]` containing text data from one character set to another
* add `tundra.document:flip` which flips a given `IData` document so that the keys become the values and the values become the keys
* add `tundra.document:legalize` for converting to legal Java identifiers either the keys, values, or both keys and values of a given `IData` document
* add `tundra.document:lowercase` for converting to lowercase either the keys, values, or both in a given `IData` document
* add `tundra.document:prefix` for prepending a given prefix to the keys, or values, or both in a given `IData` document
* add `tundra.document:remove` for removing a pattern from either the values, keys, or both keys and values of a given `IData` document
* add `tundra.document:replace` for replacing a pattern in either the values, keys, or both keys and values of a given `IData` document
* add `tundra.document:suffix` for appending a given suffix to the keys, or values, or both in a given `IData` document
* add `tundra.document:unprefix` for removing a given prefix from the keys, or values, or both in a given `IData` document
* add `tundra.document:unsuffix` for removing a given suffix from the keys, or values, or both in a given `IData` document
* add `tundra.document:uppercase` for converting to uppercase either the keys, values, or both in a given `IData` document
* add `tundra.integer:rebase` for converting an integer string from one radix to another
* add `tundra.list.document:flip` which flips a given `IData[]` document list so that the keys become the values and the values become the keys
* add `tundra.list.document:lowercase` for converting to lowercase either the keys, values, or both in a given `IData[]` document list
* add `tundra.list.document:prefix` for prepending a given prefix to the keys, or values, or both in a given `IData[]` document list
* add `tundra.list.document:remove` for removing a pattern from either the values, keys, or both keys and values of a given `IData[]` document list
* add `tundra.list.document:replace` for replacing a pattern in either the values, keys, or both keys and values of a given `IData[]` document list
* add `tundra.list.document:suffix` for appending a given suffix to the keys, or values, or both in a given `IData[]` document list
* add `tundra.list.document:trim` for trimming leading and trailing whitespace from either the keys, values, or both, in a given `IData[]` document list
* add `tundra.list.document:unprefix` for removing a given prefix from the keys, or values, or both in a given `IData[]` document list
* add `tundra.list.document:unsuffix` for removing a given suffix from the keys, or values, or both in a given `IData[]` document list
* add `tundra.list.document:uppercase` for converting to uppercase either the keys, values, or both in a given `IData[]` document list
* add `tundra.list.exception:raise` for throwing a new exception whose message is the concatenation of the messages from the given list of exceptions
* add `tundra.list.object:create` for creating a new empty array with a given length for the given component class
* add `tundra.message:connect` to allow a client to test connecting to and authenticating with Integration Server
* add `tundra.soap.fault:handle` for handling SOAP faults by throwing an exception
* add `tundra.stream:transcode` for converting a `java.io.InputStream` containing text data from one character set to another
* add `tundra.string:legalize` for converting a given string to a legal Java identifier
* add `tundra.uuid:generate` for generating a new UUID
* change `tundra.bytes:normalize` to return the `$encoding` used
* change `tundra.collection.list.document:append` to not recurse `$items` structure and instead only use the top-level objects instead
* change `tundra.collection.list.document:insert` to not recurse `$items` structure and instead only use the top-level objects instead
* change `tundra.collection.list.document:prepend` to not recurse `$items` structure and instead only use the top-level objects instead
* change `tundra.collection.list.object:append` to not recurse `$items` structure and instead only use the top-level objects instead
* change `tundra.collection.list.object:insert` to not recurse `$items` structure and instead only use the top-level objects instead
* change `tundra.collection.list.object:prepend` to not recurse `$items` structure and instead only use the top-level objects instead
* change `tundra.collection.list.string:append` to not recurse `$items` structure and instead only use the top-level objects instead
* change `tundra.collection.list.string:insert` to not recurse `$items` structure and instead only use the top-level objects instead
* change `tundra.collection.list.string:prepend` to not recurse `$items` structure and instead only use the top-level objects instead
* change `tundra.configuration:get` to support resolving variable subsitution `%key%` substrings against the configuration itself, allowing for configuration values to be built from other configuration values to reduce repetition
* change `tundra.content.retrieve:file` to attempt to move a file that has failed to process back to its original location and name so that it will be retried next time the service runs, and to return any exceptions encountered while processing in the output pipeline
* change `tundra.content.retrieve:file` to include `$content.length` in the input pipeline when calling the file processing service
* change `tundra.content.retrieve:file` to use a newly generated UUID for the suffix used on files while processing
* change `tundra.content:deliver` to drop `$encoding` after call to `tundra.xml:emit`
* change `tundra.content:retrieve` to rethrow any exceptions returned by the handler service
* change `tundra.content:retrieve` to rethrow exceptions encountered during FTP retrievals
* change `tundra.content:retrieve` to support FTP and FTPS file retrieval
* change `tundra.document:get` to replace `$default.object` and `$default.string` inputs with `$value.default`
* change `tundra.document:get` to return `$value.list` when the value associated with the given `$key` is a list
* change `tundra.document:log` to drop unused variables
* change `tundra.duration:format` to support a list of possible duration patterns used to parse the input duration string, which is useful when the exact pattern is not known
* change `tundra.file:process` to support specifying whether to rethrow exceptions encountered while processing via `$raise?` input argument
* change `tundra.http.response:accept` to drop `$encoding` after call to `tundra.bytes:normalize`
* change `tundra.http.route` implementation to return `X-Response-Duration` header in HTTP responses
* change `tundra.list.object:partition` output `$results/remainder` to be optional
* change `tundra.message:receive` to extract the response handling code into `tundra.support.receive:respond`
* change `tundra.message:receive` to use `tundra.service:invoke` instead of `tundra.service:ensure` for a simpler implementation
* change `tundra.message:retrieve` to support FTP and FTPS file retrieval
* change `tundra.object:coalesce` to not recurse `$operands` structure and only work on top-level objects instead
* change `tundra.object:coalesce` to support arbitrarily specified inputs for coalescing
* change `tundra.pipeline:log` to drop unused variables
* change `tundra.schema.content.retrieve:handler` to support handler service optionally returning a list of `java.lang.Throwable` exception objects thrown while processing the retrieved content
* change `tundra.schema.content.retrieve:processor` to include new optional input `$content.length`
* change `tundra.service:benchmark` to exclude their input arguments from the subsequent invocation pipeline
* change `tundra.service:callstack` to return `$caller.initiator`, which is the top-level service in the current call stack
* change `tundra.service:defer` to exclude their input arguments from the subsequent invocation pipeline
* change `tundra.service:ensure` to exclude their input arguments from the subsequent invocation pipeline
* change `tundra.service:ensure` to support catch and finally specific pipelines
* change `tundra.service:fork` to exclude their input arguments from the subsequent invocation pipeline
* change `tundra.service:invoke` to exclude their input arguments from the subsequent invocation pipeline
* change `tundra.service:invoke` to log unthrown exceptions to the error log
* change `tundra.stream:normalize` to return the `$encoding` used
* change `tundra.string:coalesce` to not recurse `$operands` structure and only work on top-level objects instead
* change `tundra.string:coalesce` to support arbitrarily specified inputs for coalescing
* change `tundra.xml:emit` to return the `$encoding` used
* change `tundra:log` to drop unused variables
* deprecate `tundra.document.key:lowercase`, use `tundra.document:lowercase` instead
* deprecate `tundra.document.key:normalize`, use `tundra.document:legalize` instead
* deprecate `tundra.document.key:remove`, use `tundra.document:remove` instead
* deprecate `tundra.document.key:replace`, use `tundra.document:replace` instead
* deprecate `tundra.document.key:trim`, use `tundra.document:trim` instead
* deprecate `tundra.document.key:uppercase`, use `tundra.document:uppercase` instead
* deprecate `tundra.document.value:lowercase`, use `tundra.document:lowercase` instead
* deprecate `tundra.document.value:remove`, use `tundra.document:remove` instead
* deprecate `tundra.document.value:replace`, use `tundra.document:replace` instead
* deprecate `tundra.document.value:trim`, use `tundra.document:trim` instead
* deprecate `tundra.document.value:uppercase`, use `tundra.document:uppercase` instead
* deprecate `tundra.id:generate`, use `tundra.uuid:generate` instead
* deprecate `tundra.id:normalize`, use `tundra.string:legalize` instead
* deprecate `tundra.list.document.key:lowercase`, use `tundra.list.document:lowercase` instead
* deprecate `tundra.list.document.key:remove`, use `tundra.list.document:remove` instead
* deprecate `tundra.list.document.key:replace`, use `tundra.list.document:replace` instead
* deprecate `tundra.list.document.key:trim`, use `tundra.list.document:trim` instead
* deprecate `tundra.list.document.key:uppercase`, use `tundra.list.document:uppercase` instead
* deprecate `tundra.list.document.value:lowercase`, use `tundra.list.document:lowercase` instead
* deprecate `tundra.list.document.value:remove`, use `tundra.list.document:remove` instead
* deprecate `tundra.list.document.value:replace`, use `tundra.list.document:replace` instead
* deprecate `tundra.list.document.value:trim`, use `tundra.list.document:trim` instead
* deprecate `tundra.list.document.value:uppercase`, use `tundra.list.document:uppercase` instead
* fix `tundra.content.deliver:http` to correctly return the HTTP response body in the `$response` output parameter
* fix `tundra.content.retrieve:ftp` to correctly count processed and skipped files
* fix `tundra.datetime:add` `$datetime.pattern` to be a picklist
* fix `tundra.document:translate` to support the service input and output variable names for the input and output `IData` document being the same
* fix `tundra.http.route` to not log spurious Access Denied errors
* fix `tundra.http:client` logged error messages to not include the statement `not found in "com.wm.resources.CoreExcpMsgs"`
* fix `tundra.integer:emit` to correctly use the given `$radix` when emitting the string result
* fix `tundra.list.document:group` service comment typo
* fix `tundra.list.document:partition` to not fail output validation when returning an empty remainder list
* fix `tundra.list.string:partition` to not fail output validation when returning an empty remainder list
* fix `tundra.object:instance` to not throw `ClassNotFoundException` when given class name does not exist, and instead return false
* fix `tundra.service:ensure` to work correctly when no catch and finally pipeline are provided
* fix `tundra.string:format` service comment formatting to display correctly in Designer
* fix `tundra.string:pad` service comment formatting
* fix `tundra.xml:emit` service comment formatting
* fix `tundra.yaml:emit` missing description in SERVICES.md

# 0.0.28 (2017-06-05)

* add `tundra.cache.memory` services which provide in-process on-heap caching of arbitrary key value pairs
* add `tundra.collection.map` services for document and string maps
* add `tundra.decimal:deviate` for calculating the standard deviation and related statistics for the given list of decimal numbers
* add `tundra.directory:gzip` for compressing files in a given directory using the gzip algorithm
* add `tundra.document:log` for logging a given `IData` document to the server log as a minified JSON string
* add `tundra.file:gzip` for compressing a given file with the gzip algorithm
* add `tundra.file:zip` for compressing a given file with the zip algorithm
* add `tundra.list.document:partition` for partitioning a given list into a number of lists using the given conditions
* add `tundra.list.node:access` for granting permissions to a list of nodes
* add `tundra.list.object:partition` for partitioning a given list into a number of lists using the given conditions
* add `tundra.list.string:partition` for partitioning a given list into a number of lists using the given conditions
* add `tundra.mime.type:classify` service which replaces the existing `tundra.<type>.mime.type:check` services with a single service
* add `tundra.support.log:purge` feature, for compressing and/or purging log files daily (currently disabled in the package configuration file `./config/package.hjson`)
* add `tundra.thread:sleep` for sleeping the current thread for the given duration
* change `tundra.collection.map.*.:get` services to return boolean indicating if the given key exists in the given map
* change `tundra.directory:purge` input argument `$duration` to be optional
* change `tundra.document:trim` to include `$mode` argument which allows for either values, keys, or keys and values to be trimmed
* change `tundra.json:emit` to support minifying the returned JSON content by removing all extraneous white space
* change `tundra.list.document:filter` to return the length of the resulting list
* change `tundra.list.document:reject` to return the length of the resulting list
* change `tundra.list.document:take` to support taking items from the tail of the given list by specifying a negative count
* change `tundra.list.object:filter` to return the length of the resulting list
* change `tundra.list.object:reject` to return the length of the resulting list
* change `tundra.list.object:take` to support taking items from the tail of the given list by specifying a negative count
* change `tundra.list.string:filter` to return the length of the resulting list
* change `tundra.list.string:reject` to return the length of the resulting list
* change `tundra.list.string:remove` to support literal patterns in addition to regular expression patterns
* change `tundra.list.string:take` to support taking items from the tail of the given list by specifying a negative count
* change `tundra.message:route` to support `$message` being specified as a `com.sap.conn.idoc.IDocDocumentList` object
* change `tundra.message:route` to support `Content-Encoding` header values of `base64`, `gzip`, and `zip`
* change `tundra.message:route` to support defaulting the routing of an unrecognized message provided as an `IData` document using `pub.publish:publish` with the `$message.format/name` used as the `documentTypeName`
* change `tundra.packages:get` to return the union set of all element references for all services in the given package
* change `tundra.packages:self` to return the union set of all element references for all services in the given package
* change `tundra.pipeline:log` to log the pipeline as a minified JSON string
* change `tundra.service:reflect` to return service references and dependents
* change `tundra.service:sleep` to support multiple duration string formats
* change `tundra.string:remove` to support literal patterns in addition to regular expression patterns
* change `tundra.thread:sleep` to support multiple duration string formats
* delete all `tundra.<type>.mime.type:check` services, now replaced with the `tundra.mime.type:classify` service
* fix `tundra.collection.map.document:get` to work correctly
* fix `tundra.collection.map.string:get` to work correctly
* fix `tundra.configuration:all` to ignore exceptions thrown by individual configurations when they are attempted to be read from disk and parsed; this ensures that one badly formatted file does not break accessing all other configurations (any exceptions encountered are logged to the server error log)
* fix `tundra.configuration:list` to ignore exceptions thrown by individual configurations when they are attempted to be read from disk and parsed; this ensures that one badly formatted file does not break accessing all other configurations (any exceptions encountered are logged to the server error log)
* fix `tundra.file:copy` to not overwite the file when the source and target represent the same canonical path
* fix `tundra.html:emit` to not throw a `java.lang.NullPointerException` when serializing an `IData[]` with null items
* fix `tundra.list.object:join` `$separator` argument to be string (it was previously incorrectly constrained to boolean)
* fix `tundra.schedule:get` to not return null if schedule does not exist; nothing is now returned instead if the schedule does not exist
* fix `tundra.yaml:emit` to correctly handle top-level objects other than key value maps
* fix `tundra.yaml:parse` to correctly handle top-level objects other than key value maps
* upgrade commons-csv library from v1.2 to v1.4
* upgrade hjson library from v1.1.4 to v2.1.1
* upgrade snakeyaml library from v1.15 to v1.18

# 0.0.27 (2017-02-24)

* add `tundra.collection.map` services for manipulating `java.util.Map` objects
* add `tundra.condition:choose` for using the result of evaluating a conditional statement to choose between true and false result arguments
* add `tundra.document:trim` which trims string values in an `IData` of leading and trailing whitespace. This service deprecates the existing service `tundra.document.value:trim`
* add `tundra.list.document:merge` for merging an `IData[]` document list into a single `IData` document
* add `tundra.service:fork` for invoking a service asynchronously
* change `tundra.condition:evaluate` to support XPath queries against `org.w3c.dom.Node` objects that use XML namespaces
* change `tundra.content.deliver:http` to not parameterise credentials specified in the destination URI, as `tundra.http:client` now provides this function
* change `tundra.datetime:concatenate` to support arbitrary datetime patterns and timezones for input and output datetime strings
* change `tundra.document:join`, `tundra.list.object:join`, and `tundra.list.string:join` to support new `$sanitization` input argument for sanitizing the input document or list being joined by either removing nulls or removing nulls and blanks prior to performing the join
* change `tundra.document:merge` input argument to be an `IData` document `$operands` in which top-level `IData` values can be provided arbitrarily for merging. The `$documents` `IData[]` argument continues to be supported silently for backwards compatibility with existing code
* change `tundra.document:split` use of `tundra.document:merge` to use new `$operands` input argument
* change `tundra.document:translate` use of `tundra.document:merge` to use new `$operands` input argument
* change `tundra.http:client` to remove default ports (80 for http, 443 for https) from request URI, and to parameterise credentials specified in the authority component of the request URI by removing the credentials from the request URI and instead providing them as input parameters to the call to `pub.client:http`
* change `tundra.http:client` use of `tundra.document:merge` to use new `$operands` input argument
* change `tundra.list.object:join` and `tundra.list.string:join` to allow for a default value to be specified which will be returned when the given list is null, and to treat empty lists the same as null lists
* change `tundra.message:route` to support specifying publishable document `_env` values in a message format, which is then used when publishing the document for messages with a route type of publish. Values provided under the key `tundra/message/format/route/env` in the message format will first have variable substitution performed on them, before being mapped to the `_env` section of the published document and then published
* change `tundra.message:route` to support specifying publishable document `_properties` values in a message format, which is then used when publishing the document to Universal Messaging for messages with a route type of publish. Values provided under the key `tundra/message/format/route/properties` in the message format will first have variable substitution performed on them, before being mapped to the `_properties` section of the published document and then published
* change `tundra.pipeline:log` to prefix logged pipeline with current user in addition to the current call stack
* change `tundra.schedule:exists`, `tundra.schedule:get`, and `tundra.schedule:list` to use Tundra.java project for their implementations
* change `tundra.service:benchmark`, `tundra.service:validate`, `tundra.service:callstack`, `tundra.service:reflect`, `tundra.service:join`, `tundra.service:ensure` to use Tundra.java project for their implementations
* change `tundra.service:invoke` to use Tundra.java project for its implementation, and deprecate use of this service for asynchronous invocation (`tundra.service:fork` should now be used instead for this)
* change `tundra:log` to prefix logged message with current user in addition to the current call stack
* fix `tundra.content:emit` to drop `_properties` when serializing a publishable document
* fix `tundra.timezone:get` to return a GMT offset timezone when given a timezone ID in the form of an offset; previously it would return the first matching timezone with the same offset given, however if the timezone used daylight savings this could result in a different offset returned than the one specified

# 0.0.26 (2016-10-30)

* add `tundra.collection.map` services for creating and fetching values from `java.util.Map` objects
* add `tundra.content.deliver:message` message protocol handler to route arbitrary content to either the webMethods messaging subsystem via `pub.publish:publish`, a JMS destination, or a direct service invocation, using `tundra.message:route` as its implementation
* add `tundra.document:uncase` for recursively cloning an `IData` document to a new `IData` representation with case-insensitive keys
* add `tundra.hjson:emit` for serializing [HJson](https://hjson.org) formatted content
* add `tundra.hjson:parse` for deserializing [HJson](https://hjson.org) formatted content
* add `tundra.message.format:clear` for removing all message formats from memory
* add `tundra.message.trigger:recognize` for recognizing the type of publishable document a trigger service was invoked with
* add `tundra.service:retryable` for making any service automatically retryable by a trigger by automatically converting any thrown exception to be an instance of `ISRuntimeException`
* add `tundra.string:reverse` to reverse the order of characters in the given string
* add `tundra.ulid:generate` for returning newly generated [ULID](https://github.com/alizain/ulid) identifers
* add `tundra.xml:decode` for decoding XML encoded strings: XML entities, such as `&lt;` and `&gt;`, are decoded to the appropriate character representation, such as `<` and `>`
* add `tundra.xml:encode` for XML encoding strings: reserved characters in XML, such as `<` and `>`, are encoded to the appropriate XML entity, such as `&lt;` and `&gt;`, to ensure the XML is rendered correctly by web browsers and other XML rendering software
* add exception logging feature (currently disabled by default; this feature can be enabled in the package configuration file) **[experimental feature]**
* add support for executing package install/uninstall services automatically (currently disabled by default; this feature can be enabled in the package configuration file) **[experimental feature]**
* add support for logging received content to `./logs/content.log` (currently disabled by default; this feature can be enabled in the package configuration file) **[experimental feature]**
* change `pub/service/usage.dsp` to only show pipeline to a depth of 4
* change `tundra.configuration:get` to always return a configuration `IData` document even when no configuration files are found for a package
* change `tundra.configuration:get` to clean up cached configurations for disabled packages
* change `tundra.configuration:get` to not cache empty configurations in memory
* change `tundra.configuration:get` to return a mutable deep clone of the cached configuration, to ensure multiple threads do not inadvertently mutate each other's configurations
* change `tundra.configuration:get` to return the package name the configuration relates to
* change `tundra.configuration:get` to support [Hjson](https://hjson.org) formatted configuration files
* change `tundra.configuration:get` to support arbitrary XML configuration files
* change `tundra.content.deliver:ftp` to always logout of server even when an error is encountered
* change `tundra.content.deliver:mailto` to use new `$system/property` output from `tundra.system:reflect`, rather than the deprecated `$system/properties`
* change `tundra.content:retrieve` to enable service auditing on error when top-level service
* change `tundra.content:retrieve` to support customising the input variable name for the retrieved content when passed to the processing service
* change `tundra.document:merge` to support recursive merging of nested `IData` documents
* change `tundra.document:normalize` to support normalizing Trading Networks `FixedData` objects such as document types correctly
* change `tundra.html:emit` to support a maximum encoding depth for child `IData` and `IData[]` objects
* change `tundra.http.response:accept` to convert HTTP response header `IData` keys to be case-insensitive rather than lowercase
* change `tundra.http.route:refresh` service comment to use file paths relative to the Integration Server instance root
* change `tundra.message:receive` to accept `format` input argument to specify the message format to be used to route the message
* change `tundra.message:route` input arguments to be named consistently with the other `tundra.message` service arguments
* change `tundra.message:route` to not delay publishing messages until service success, because this does not work when using a thread pool to route messages
* change `tundra.message:route` to support additional pipeline variables specified in route record
* change `tundra.schedule:*` services to support a task name assigned by the task creator, to allow easier identification and hence management of tasks by packages
* change `tundra.schedule:create` to accept `$self` as a logical target identifying the local scheduler node, and to always default a `null` target to `$any`
* change `tundra.schedule:get` to return `$schedule/target.logical` which is set to the value `$self` when target is equal to the local scheduler node name
* change `tundra.schedule:list` to return `$schedule/target.logical` which is set to the value `$self` when target is equal to the local scheduler node name
* change `tundra.system:reflect` to silently support `$system/properties` and `$system/directories` output variables for backwards-compatibility, and to use a case insensitive `IData` implementation for environment variables on Microsoft Windows operating systems
* fix `tundra.configuration:get` to only return configurations for enabled packages
* fix `tundra.content.deliver:ftp` branch with both switch and evaluate labels true error
* fix `tundra.content.deliver:ftp` to work correctly when constructing the initial path to `cd` to prior to the `put` or `append` command
* fix `tundra.content:deliver` service comment to clarify that variables provided in `$pipeline` take precedence and override their corresponding values in the `$destination` URI
* fix `tundra.directory:ls` to throw a meaningful exception when either the directory does not exist, access is denied, or an IO error occurs
* fix `tundra.message:receive` service comment and README entry typo
* fix `tundra.message:route` README typo
* fix `tundra.node:list` to return an empty list when given interface does not exist, and to return the length of the list also
* fix `tundra.pipeline:log` to correctly prefix the logged pipeline with the full call stack
* fix `tundra.schedule:create` service comment formatting to display correctly in Designer
* fix `tundra.schedule:create` service comment to clarify that `$schedule/repeat/interval` is an XML duration
* fix `tundra.schema.message:format` service comment typo
* fix `tundra.service:ensure` to not throw a `java.lang.NullPointerException` when the current invoke state does not contain any error state
* fix `tundra.system:reflect` service comment to reflect that the returned `$system` argument is an immutable `IData`
* fix `tundra.xml:emit` to not throw a `java.lang.reflect.InvocationTargetException` when the Integration Server XSLT implementation does not support `javax.xml.transform.TransformerFactory.setFeature` method
* fix `tundra:log` to correctly prefix the logged message with the full call stack
* fix order of Solution menu items to be sorted lexically

# 0.0.25 (2016-07-05)

* add `tundra.collection.list` services for working with `java.util.List` objects
* add `tundra.configuration:all` service for returning the configurations for all configured packages
* add `tundra.configuration:get` for reading and returning an `IData` document representation of package configuration for package-specific and server-specific configuration files
* add `tundra.configuration:list` services for returning the configurations for all configured packages
* add `tundra.document:denormalize` for flattening nested child documents and arrays
* add `tundra.list.directory:compact` to compact a list of directories
* add `tundra.list.directory:purge` to purge a list of directories
* add `tundra.list.directory:remove` to delete a list of directories
* add `tundra.list.directory:squeeze` to squeeze a list of directories
* add `tundra.list.document:take` for splitting an array into a head with a given count of items and tail with the remaining items
* add `tundra.list.file:remove` for deleting a list of files
* add `tundra.list.object:take` for splitting an array into a head with a given count of items and tail with the remaining items
* add `tundra.list.string:take` for splitting an array into a head with a given count of items and tail with the remaining items
* add `tundra.message:receive` and supporting services for receiving and recognized arbitrary content and routing to the webMethods messaging layer, JMS destination, or service invocation
* add `tundra.message:retrieve` for retrieving arbitrary content from a URI and routing to either the webMethods message subsystem, JMS destination, or service invocation
* add `tundra.pipeline:denormalize` for flattening nested child documents and arrays
* add `tundra.scheduler:restart` for restarting the Integration Server task scheduler
* add `tundra.scheduler:self` for returning the node name of the instance of the IS user task scheduler on which the service is run
* add `tundra.scheduler:start` for starting the Integration Server task scheduler
* add `tundra.scheduler:stop` for stopping the Integration Server task scheduler
* add `tundra.security.acl:create` for creating Integration Server ACLs
* add `tundra.security.content:digest` for calculating a message digest given arbitrary content
* add `tundra.xml:parse` for parsing XML content into an `IData` document
* add `tundra.xpath:get` service for returning the elements associated with an XPath expression
* change `tundra.content.deliver:http` to support reusing sessions
* change `tundra.content:emit` to drop `_env` publishable document section if it exists before serialization
* change `tundra.content:retrieve` to include retrieve duration in log message
* change `tundra.directory:list` to support multiple inclusive and exclusive filter patterns
* change `tundra.directory:purge` to support inclusive and exclusive filename filtering
* change `tundra.directory:squeeze` to support inclusive and exclusive filename filtering
* change `tundra.document:compact` to return empty `IData` document rather than null if all elements get removed
* change `tundra.document:get` to support resolving XPath expressions against node objects
* change `tundra.document:squeeze` to return empty `IData` document rather than null if all elements get removed
* change `tundra.document:substitute` to support resolving variables against either local variables, global variables, or all variables (global then local)
* change `tundra.html:emit` implementation to improve performance
* change `tundra.http:client` to support query string input and additional credential types
* change `tundra.http:client` to support reusing sessions
* change `tundra.json:emit` to return pretty printed JSON text and to trim leading and trailing whitespace before returning output
* change `tundra.list.document:compact` to always return an empty `IData[]` rather than null if all items were removed
* change `tundra.list.document:squeeze` implementation to always return an empty `IData[]` rather than null if all items were removed
* change `tundra.list.document:substitute` to support resolving variables against either local variables, global variables, or all variables (global then local)
* change `tundra.list.string:substitute` to support resolving variables against either local variables, global variables, or all variables (global then local)
* change `tundra.pipeline:get` to support resolving XPath expressions against node objects
* change `tundra.pipeline:substitute` to support resolving variables against either local variables, global variables, or all variables (global then local)
* change `tundra.string:substitute` to support resolving variables against either local variables, global variables, or all variables (global then local)
* change `tundra.system:reflect` to return global variables and use singular names for properties and directories
* change `tundra.uri:emit` to preserve case in host name just to be safe
* change `tundra.uri:emit` to treat query string parameter keys as fully-qualified `IData` keys
* change `tundra.uri:parse` to preserve case in host name just to be safe
* change `tundra.uri:parse` to treat query string parameter keys as fully-qualified `IData` keys
* change `tundra.xml:emit` to support serializing `org.w3c.dom.Node` objects, not just `org.w3c.dom.Document` objects
* change `tundra.xpath:exists` to addtionally support `$content` specified as an `org.w3c.dom.Node` object, or an `org.xml.sax.InputSource` object
* change `tundra:condition:evaluate` to support resolving XPath expressions against node objects
* change `tundra:test` implementation to use new `tundra.collection.list` services for slight performance improvement
* fix `tundra.directory:list` to throw a FileNotFoundException rather than a NullPointerException when an IO error occurs while attempting to list the directory
* fix `tundra.document:leaves` to to work correctly when asked to return leaves that are instances of IData compatible classes by not recursing those classes in this case
* fix `tundra.document:stringify` NullPointerException by checking if the value to be transformed is null before calling `toString()`
* fix `tundra.list.document:compact` to remove nulls from the `IData` items even when `$recurse?` is `false`
* fix `tundra.schedule:create` to correctly handle datetimes in timezones other than local
* fix `tundra.schedule:create` to correctly handle when `$singleton` is true and `$schedule/target` is `$all` or `all` or `host` on Integration Servers that are not in an Integration Server cluster by only removing existing schedules for the service from the node on which this service is run
* fix `tundra.schedule:remove` to work correctly when `$filter` references pipeline variables
* fix `tundra.schedule:resume` to work correctly when `$filter` references pipeline variables
* fix `tundra.schedule:suspend` to work correctly when `$filter` references pipeline variables
* fix `tundra.thread:list` output to declare `$threads.length` argument
* remove all unit tests from package and create new project for these: http://github.com/Permafrost/TundraTest

# 0.0.24 (2016-01-19)

* add `tundra.directory:size` for returning the total size in bytes of all files in a given directory
* add `tundra.directory:squeeze` for reducing the total size in bytes of a given directory by progressively deleting files in order of least recently modified
* add `tundra.document:flatten` for fetching all values associated with a given list of keys from an `IData` document and flattening them to a one-dimensional array
* add `tundra.packages:self` which returns details of the package in which the calling service is stored
* add `tundra.pipeline:flatten` for collecting all the values associated with a given list of keys and flattening them into a one-dimensional array
* add `tundra.string:concatenate` for concatenating arbitrarily specified strings, string lists, and string tables optionally separated by a given separator string
* add `tundra.string:truncate` for truncating all strings in an `IData` document
* add `tundra.uri:substitute` for performing variable substitution against the parsed components of a URI string
* change `tundra.content:deliver` to support `$pipeline/$filename` for specifying the file name for file deliveries, and if provided this value overrides the value provided in the `$destination` URI
* change `tundra.content:emit` to revert the previous change which minifies all serialized XML content, as this legally can change values in the XML, such as collapsing contiguous runs of spaces in an attribute value to a single space, but doing so may cause problems with systems that rely on significant whitespace in values being preserved
* change `tundra.content:reject` to use double-quotes to delimit service name in exception message/response body
* change `tundra.directory:join` to support joining multiple children to a parent path
* change `tundra.http.response:handle` to throw a TransportException that includes the response body content
* change `tundra.list.object:insert` to ignore null `$item` arguments: nulls will no longer be inserted into the given list
* change `tundra.list.object:prepend` to ignore null `$item` arguments: nulls will no longer be inserted into the given list.
* change `tundra.list.object:slice` and dependent service's handling of reverse indexing and reverse lengths to be consistent with `tundra.string:slice`
* fix `tundra.content.deliver:http` to use `$headers/Content-Type` as the content type for the HTTP request if provided
* fix `tundra.list.object:get` and related services to work correctly when using `$iteration` input argument

# 0.0.23 (2015-12-02)

* add `tundra.directory:compact` for recursively deleting empty child directories from a given directory
* add `tundra.document:validate` for validating an `IData` document against a document reference or flat file schema
* add `tundra.list.datetime:duration` for calculating the durations of time for a given list of datetime string pairs
* add `tundra.xml:emit` for serializing an XML node object to a string, byte array or stream
* change `tundra.content.retrieve:file` exception message when `$source` directory does not exist to be more correct by stating that either it does not exist or is unreachable to account for network file shares and problems accessing them across the network
* change `tundra.content:deliver` HTTP deliveries to include HTTP response headers and status serialized as YAML in `$message` output argument
* change `tundra.content:deliver` to support sending emails with no attachments: `$content` is now allowed be null for `mailto:` URIs
* change `tundra.content:emit` to optionally support validating output content
* change `tundra.content:parse` to optionally support validating input content
* change `tundra.content:split` to optionally support validating input and output content
* change `tundra.content:translate` to optionally support validating input and output content
* change `tundra.list.content:emit` to optionally support validating input and output content
* change `tundra.list.content:join` to optionally support validating input and output content
* change `tundra.list.content:parse` to optionally support validating input and output content
* fix `tundra.content.retrieve:file` skipped files message to be less sure of the reason why
* fix `tundra.duration:*` services to not throw exception when parsing decimal values
* fix `tundra.service:benchmark` to correctly measure the performance of services that throw exceptions
* fix `tundra.uri:parse` to not truncate query string parameter values at the first equals symbol encountered

# 0.0.22 (2015-11-04)

* add `tundra.datetime:today` for returning the current date
* add `tundra.datetime:tomorrow` for returning the current date + 1 day
* add `tundra.datetime:yesterday` for returning the current date - 1 day
* add `tundra.document:nullify` for converting string values in an `IData` document that only contain whitespace characters to null
* add `tundra.list.document:blankify` for converting all null values to empty strings in an `IData[]` document list
* add `tundra.list.document:normalize` for normalizing `IData[]` document lists
* add `tundra.list.document:nullify` for converting string values that contain only whitespace characters to null in an `IData[]` document list
* add `tundra.list.object:index` for calculating index values for each item in a given list
* add `tundra.list.string:blankify` for converting null items in a string list to empty strings
* add `tundra.list.string:capitalize` for capitalizing all strings in a string list
* add `tundra.list.string:nullify` for converting strings in a string list that only contain whitespace characters to null
* add `tundra.list.string:pad` for padding each string in a string list
* add `tundra.measure:convert` for converting values from one unit of measurement to another in the same measurement class
* add `tundra.string:format` for formatting strings using a `java.util.Formatter` format string
* add `tundra.string:nullify` for converting strings that only contain whitespace characters to null
* add `tundra.xml:minify` for removing comments and extraneous whitespace from XML content
* add experimental support for HTTP compression to Integration Server (not currently enabled by default). This support can be installed by running the following service at startup: `tundra.support.http.compression:startup`. And it can be uninstalled by running the following service at shutdown: `tundra.support.http.compression:shutdown`.
* change `tundra.content.deliver:http` to gzip `$content` when `$headers/Content-Encoding` is set to `gzip`
* change `tundra.content:emit` to always minify emitted XML content
* change `tundra.datetime:*` services to support new named pattern `datetime.db2` for DB2 timestamp formatted datetimes
* change `tundra.document:drop` to support dropping a key under all items of an `IData[]` document list by specifying the key with no array index
* change `tundra.document:get` to support returning all values associated with a key under an `IData[]` document list when the key is specified with no array index
* change `tundra.document:split` to drop the split service input document argument after invocation
* change `tundra.document:stringify` to treat `java.util.Date` and `java.util.Calendar` objects specially: they are now converted to XML datetime strings
* change `tundra.document:translate` to drop the translation service input document argument after invocation
* change `tundra.duration:add` to support a variable number of arguments to be added
* change `tundra.list.document:group` to support multi-level groupings
* change `tundra.string:compare` to support whitespace insensitive comparisons
* fix `tundra.decimal:validate` to not throw an exception when `$raise?` is `false`
* fix `tundra.document:amend` to amend a copy of the `IData` document, rather than the `IData` document provided itself, so amend conditions are not affected by previous amendments
* fix `tundra.document:put` to correctly handle null `$key` argument
* fix `tundra.uri:parse` to correctly handle opaque URIs containing query strings with values that include URI encoded equal symbols
* upgrade Apache Commons CSV library to v1.2
* upgrade Spring libraries to v4.2.1

# 0.0.21 (2015-09-10)

* add `tundra.document:join` for converting an `IData` document to a human-readable string representation
* change `tundra.packages:exists` to enable input and output validation
* change `tundra.tn.content.retrieve:file` to include the archive location of the content being processed in the processing service's pipeline

# 0.0.20 (2015-08-31)

* add `tundra.document.key:remove` for removing occurrences of a regular expression pattern from the keys in an `IData` document
* add `tundra.document.value:remove` for removing occurrences of a regular expression pattern from the values of an `IData` document
* add `tundra.document:freeze` which wraps an `IData` document in an unmodifiable shell
* add `tundra.document:leaves` for returning all the leaf values from an `IData` document
* add `tundra.integer:shift` for bit shifting integer numbers
* add `tundra.list.document.key:remove` for removing occurrences of regular expression patterns from the keys of an `IData[]` document list
* add `tundra.list.document.value:remove` for removing occurrences of regular expression patterns from the keys of an `IData[]` document list
* add `tundra.list.document:unique` for returning only the IData documents that are unique in an `IData[]` document list
* add `tundra.list.string:remove` to remove occurrences of a regular expression pattern in a list of strings
* add `tundra.packages:exists` for querying the existence of an Integration Server package
* add `tundra.packages:get` for returning information about an Integration Server package
* add `tundra.packages:list` for returning a list of all Integration Server packages
* add `tundra.pipeline:listify` for converting a pipeline value into an array
* add `tundra.string:remove` for removing occurrences of regular expression patterns from a string
* change `./pub/service/usage.dsp` web page to include the current datetime
* change `tundra.document.key:replace` to support `$pattern` and `$replacement` being null
* change `tundra.document.key:replace` to support replacing either the first or all occurrences of the pattern
* change `tundra.document.value:replace` to support replacing either the first or all occurrences of the pattern
* change `tundra.document:copy` to support literal keys
* change `tundra.document:drop` to support literal keys
* change `tundra.document:get` to support literal keys
* change `tundra.document:listify` to handle keys that occur multiple times in an `IData` document by converting all occurrences into a single list
* change `tundra.document:put` to support literal keys
* change `tundra.document:rename` to support literal keys
* change `tundra.exception:raise` to not throw a class cast exception when called with a `$exception` object that is not an instance of `java.lang.Throwable`; instead the `$exception` object will now be ignored if it is not an instance of `java.lang.Throwable`
* change `tundra.list.document.key:replace` to support replacing the first or all occurrences of the pattern
* change `tundra.list.document.value:replace` to support replacing the first or all occurrences of the pattern
* change `tundra.list.string:replace` to support replacing either the first or all occurrences of the pattern
* change `tundra.object:listify` to be an alias of `tundra.document:listify`
* change `tundra.pipeline:copy` to support literal keys
* change `tundra.pipeline:drop` to support literal keys
* change `tundra.pipeline:get` to support literal keys
* change `tundra.pipeline:put` to support literal keys
* change `tundra.pipeline:rename` to support literal keys
* change `tundra.schedule:create` to include a new input parameter `$enabled?` which determines whether the schedule is created in an active or suspended state
* change `tundra.schedule:list` to include a new input argument `$service` which will filter tasks to only those that execute the given service
* change `tundra.schedule:remove` to include a new input argument `$service` which will filter tasks to only those that execute the given service
* change `tundra.schedule:resume` to include a new input argument `$service` which will filter tasks to only those that execute the given service
* change `tundra.schedule:suspend` to include a new input argument `$service` which will filter tasks to only those that execute the given service
* change `tundra.string:replace` to support replacing either the first or all occurrences of the pattern
* change the following service implementations to use [Tundra.java](https://github.com/Permafrost/Tundra.java) classes and methods:
  - `tundra.bool:*`
  - `tundra.bytes:*`
  - `tundra.condition:*`
  - `tundra.csv:*`
  - `tundra.datetime:*`
  - `tundra.decimal:*`
  - `tundra.directory:*`
  - `tundra.dns:*`
  - `tundra.document:*`
  - `tundra.duration:*`
  - `tundra.exception:*`
  - `tundra.file:*`
  - `tundra.gzip:*`
  - `tundra.html:*`
  - `tundra.http:*`
  - `tundra.id:*`
  - `tundra.integer:*`
  - `tundra.json:*`
  - `tundra.list.html:*`
  - `tundra.mime:*`
  - `tundra.node:*`
  - `tundra.session:*`
  - `tundra.stream:*`
  - `tundra.string:*`
  - `tundra.system:*`
  - `tundra.thread:*`
  - `tundra.timezone:*`
  - `tundra.uri:*`
  - `tundra.user:*`
  - `tundra.xml:*`
  - `tundra.xpath:*`
  - `tundra.yaml:*`
  - `tundra.zip:*`
* fix `tundra.document:listify` to clone `$scope` rather than mutating it to prevent inadvertent changes to the `IData` object mapped to `$scope`
* fix `tundra.list.object:map` to return nothing rather than null list when invoked with a null list
* fix `tundra.service:benchmark` to estimate standard deviation correctly
* upgrade [Apache Santuario](http://santuario.apache.org) xmlsec library to v1.5.8

# 0.0.19 (2015-03-19)

* add `tundra.assertion.exception:raised` for asserting that an invoked service throws an exception that meets some given criteria
* add `tundra.bool:parse` for converting a string to a `java.lang.Boolean` object
* add `tundra.content.deliver:ftps` service to add support for the `FTPS` protocol to `tundra.content:deliver`
* add `tundra.html:decode` and `tundra.html:encode` services for HTML-decoding and encoding strings
* add `tundra.html:emit` for serializing an `IData` document to an HTML fragment using clean HTML table elements
* add `tundra.http.response:status` for returning the standard HTTP response message for a given response code
* add `tundra.http.route:*` services for configuring custom HTTP routes for service invocation
* add `tundra.list.document:reject`, `tundra.list.object:reject`, and `tundra.list.string:reject` for filtering lists to not include items where the given condition evaluates to true
* add `tundra.list.html:decode` and `tundra.list.html:encode` services for HTML-decoding and encoding string lists
* add `tundra.list.html:emit` for converting an `IData[]` document list to an HTML fragment using a clean HTML table element
* change `tundra.bool:emit` to allow the `$boolean` input to be specified as either a string or a `java.lang.Boolean` object, and to allow `$value.true` and `$value.false` inputs to be optional
* change `tundra.content:deliver` to normalize the input pipeline prior to invoking the `tundra.content.deliver` delivery protocol implementation service, so that arbitrary input variables can be specified either as complex structures or as flat string variables with 'a/b/c' style keys
* change `tundra.datetime:format` and related services to support specifying the input time zone to use when parsing a datetime
* change `tundra.id:generate` to support returning the generated UUID in either the default string representation or in a base64-encoded representation
* change `tundra.json:parse` to always parse JSON numbers to either `java.lang.Long` or `java.lang.Double` objects depending on whether the number is integral or not
* change `tundra.list.document:pivot` to support pivoting on multiple deeply-nested fully-qualified keys
* change `tundra.list.string:normalize` implementation from flow to java
* change `tundra.schedule:create` to include `$singleton?` input argument, which when true indicates that only one scheduled task should ever exist for this service, and therefore any existing scheduled tasks for this service will be first removed prior to creating a new scheduled task
* change `tundra.service:benchmark` `$message` format to use words instead of mathematical symbols for the average, standard deviation, minimum, and maximum value labels
* change `tundra.service:benchmark` to return the total duration of all executions of the given service
* change `tundra:test` to simplify implementation by using `tundra.string:match` and `tundra.service:ensure`
* upgrade Apache Commons CSV library used for CSV parsing to v1.0.0
* upgrade SnakeYAML library used for YAML parsing to v1.14

# 0.0.18 (2014-11-17)

* add `tundra.list.document:values` for returning a list of values associated with a given `$key` from the respective items in the given `IData[]` document list
* add `tundra.list.string:coalesce` for returning the first non-null item in a given string list
* add `tundra.string.base64:decode` service for convenience when working with base-64 encoded strings in Developer or Designer
* add `tundra.string.base64:encode` service for convenience when working with base-64 encoded strings in Developer or Designer
* add `tundra.string:coalesce` for returning the first non-null string argument
* change `tundra.content:emit` to return `$content.type` and `$encoding`, which is useful for knowing the inferred values when not provided as an input arguments
* change `tundra.document:get` to support optionally specifying a default value to be returned when either the given `$key` doesn't exist or when it is associated with a null value
* change `tundra.duration:*` and `tundra.list.duration:*` services to support specifying different input and output duration patterns
* change `tundra.service:ensure` to include `$exception.service`, `$exception.package`, and the output of `WmPublic/pub.flow:getLastError` as `$exception.info` in the `$catch` service's input pipeline when an exception is thrown by `$service`
* change default character encoding to be UTF-8 rather than the JVM default charset
* fix `tundra.list.document:group` to correctly handle grouping key value tuples where the values are null
* fix `tundra.xml:validate` to not return `$errors` as null when there are no errors

# 0.0.17 (2014-10-29)

* add `tundra.list.document:group` for grouping like items together in an `IData[]` document list similar to a SQL `GROUP BY` clause

# 0.0.16 (2014-10-27)

* add `tundra.document:blankify` for converting all null values in an IData document to empty strings
* add `tundra.list.document:first` for returning the first item in a given list
* add `tundra.list.document:last` for returning the last item from a given list
* add `tundra.list.object:first` for returning the first item from a list
* add `tundra.list.object:last` to return the last item from a given list
* add `tundra.list.string:first` to return the first item in a given list
* add `tundra.list.string:last` to return the last item from a given list
* add `tundra.pipeline:sort` for sorting the pipeline's top-level keys in natural ascending order
* add `tundra.service:create` for creating a new empty flow service in an existing package
* add `tundra.service:defer` for running services asynchronously on a single dedicated thread; the thread pool is created on first use, instead of at package startup, so that if the service is never used, no thread pool will ever get created
* add `tundra.string:blankify` which returns an empty string if given a null string, otherwise returns the given string unchanged
* add `tundra.string:capitalize` for converting the first character in either the first word or all words to upper case
* add `tundra.string:characters` for returning a list of characters from a given string
* add `tundra.string:compare` for lexicographically comparing two strings
* add `tundra.xml:canonicalize` for converting XML content to canonical form
* change `tundra.content.deliver:mailto` to not compute the from email address if not provided, and instead use the `mail.from` property returned by `tundra.system:reflect`
* change `tundra.datetime:earlier` to support optionally specifying the timezone for the returned datetime
* change `tundra.datetime:emit` to support optionally specifying the timezone for the returned datetime
* change `tundra.datetime:format` to support optionally specifying the timezone for the returned datetime
* change `tundra.datetime:later` to support optionally specifying the timezone for the returned datetime
* change `tundra.datetime:now` to support optionally specifying the timezone for the returned datetime
* change `tundra.decimal:add` to include a singleton input decimal to make it easier to use in a transformer with a hard-coded value
* change `tundra.decimal:multiply` to include a singleton `$decimal` input variable to make the service easier to use as a transformer
* change `tundra.integer:add` to include a singleton input integer to make it easier to use in a transformer when adding a hard-coded value
* change `tundra.integer:multiply` to include a singleton input integer to make it easier to use in a transformer when multiplying by a hard-coded value
* change `tundra.json:*` services to use latest version of javax.json library, v1.0.4
* change `tundra.list.datetime:format` to support `$patterns.input` and `$timezone.output` arguments
* change `tundra.list.document:sort` to support sorting integers, decimals, datetimes, and durations, as well as strings; also disallow null `$criteria/key` input
* change `tundra.pipeline:parse` to disable output pipeline validation as its not required, and to include URL for the default charset link in the service comment
* change `tundra.service:benchmark` to return the minimum and maximum durations
* change `tundra.service:self` to return nothing rather than null, if invoked directly; change implementation to be more efficient
* change `tundra.system:reflect` to compute a value for the `mail.from` system property, if it is not already set (value will be computed as "Integration-Server@" + fully-qualified domain name of the server)
* change `tundra.timezone:get` to support a time zone ID of `$default` which represents the default or local host time zone
* change default buffer size used by the `tundra.bytes:*` and `tundra.stream:*` services from 4096 to 8192 bytes
* change unit tests from to use the `tundra.support.test` folder instead of the `tundra.test` folder, as they are not part of the public API of the package
* fix `tundra.document:stringify` to not return a null value when no input `$document` is specified
* fix `tundra.string:normalize` to not return anything when `$object` is null

# 0.0.15 (2014-08-27)

* add `tundra.csv.mime.type:check` which returns true if the given MIME media type is a recognised CSV type
* add `tundra.csv:emit` for serializing CSV content
* add `tundra.csv:parse` for deserializing CSV content
* add `tundra.datetime:earlier` for returning current datetime minus a given duration
* add `tundra.datetime:later` for returning the current datetime plus a given duration
* add `tundra.datetime:maximum` for returning the largest datetime in a given list
* add `tundra.datetime:minimum` for returning the smallest datetime in a given list
* add `tundra.decimal:emit` for converting Java decimal objects to strings
* add `tundra.decimal:parse` for converting strings to Java decimal objects
* add `tundra.directory:purge` for (optionally recursively) deleting files older than a given age from a given directory
* add `tundra.directory:reflect` for returning meta-data about a given directory
* add `tundra.document.key:normalize` for converting all keys in an `IData` document to legal Java identifiers  by replacing illegal characters with underscores
* add `tundra.document:pivot` for iterating over an `IData` document whose structure is not known
* add `tundra.document:stringify` for converting all values in a given `IData` document to strings
* add `tundra.file:reflect` for returning meta-data about a given file
* add `tundra.http.response:accept` which is a response handler that accepts all response codes
* add `tundra.id:normalize` for converting a string to a legal Java identifier by replacing illegal characters with underscores
* add `tundra.integer:emit` for converting Java integer objects to strings
* add `tundra.integer:parse` for converting strings to Java integer objects
* add `tundra.json.content.type:check` which returns true if the given MIME media type is a recognised JSON media type
* add `tundra.json:emit` for serializing JSON content
* add `tundra.json:parse` for deserializing JSON content
* add `tundra.list.document:keys` which returns a union set of all top-level keys for each item in the given `IData[]` document list
* add `tundra.list.document:pivot` for converting an `IData[]` document list to an `IData` document
* add `tundra.list.string:find` for checking if the items in the given list include the given regular expression or literal string pattern
* add `tundra.list.string:quote` for constructing a regular expression that matches any of the literal strings in the given list
* add `tundra.service:reflect` for returning meta-data about a given service
* add `tundra.string:capture` for matching a regular expression pattern against a string and returning the associated capturing groups
* add `tundra.string:find` for partial regular expression and literal pattern matching
* add `tundra.string:pad` for left and right padding of strings
* add `tundra.string:slice` for forward and reverse indexed substrings
* add `tundra.test` unit test services, deprecating separate `TundraTest` package, as version management across `Tundra` and `TundraTest` packages proved too difficult
* add `tundra.thread:current` which returns the currently executing thread
* add `tundra.thread:list` which returns all threads currently scheduled on the JVM
* add `tundra.uri:normalize` for normalizing a URI string
* add `tundra.xml.content.type:check` which returns true if the given MIME media type is a recognised XML media type
* add `tundra.yaml.mime.type:check` which returns true if the given MIME media type is a recognised YAML type
* add `tundra.yaml:emit` for serializing YAML content
* add `tundra.yaml:parse` for deserializing YAML content
* change `tundra.content:deliver` to not attempt delivery when no destination is specified
* change `tundra.content:deliver` to sanitize destination URI scheme when looking for the delivery implementation service by replacing all non-alphanumeric characters with underscores
* change `tundra.content:deliver` to support delivering `IData` documents by first serializing the document prior to delivery
* change `tundra.content:deliver` to support `ftp` delivery scheme
* change `tundra.content:deliver` to support `jms` delivery scheme
* change `tundra.content:deliver` to support `sap+idoc` delivery scheme
* change `tundra.content:deliver` to use charset parameter from `$content.type` if `$encoding` is not specified
* change `tundra.content:discard` to be stateless
* change `tundra.content:emit` to support serializing CSV, JSON, and YAML content
* change `tundra.content:emit`, `tundra.content:parse`, and related services to support XML namespaces
* change `tundra.content:parse` to support deserializing CSV, JSON, and YAML content
* change `tundra.content:reject` to be stateless
* change `tundra.content:retrieve` to append a timestamp to archived file names
* change `tundra.content:retrieve` to optionally clean up archived files older than a given age
* change `tundra.content:retrieve` to use a working directory when processing files
* change `tundra.datetime:format` to accept an array of datetime patterns when the exact pattern is not known
* change `tundra.datetime:parse` to accept an array of datetime patterns when the exact pattern is not known
* change `tundra.decimal:*` services to support optional inputs
* change `tundra.document:duplicate` to return nothing if no input `$document` is given, rather than null
* change `tundra.document:keys` to retain key order
* change `tundra.document:values` to return an array of the nearest ancestor class in common to all values, rather than an `Object[]`
* change `tundra.duration:*` services to return nothing if no input was specified, rather than returning null
* change `tundra.http.response:handle` to use `tundra.http.response:accept` in its implementation
* change `tundra.http:client` `$request/timeout` input to be constrained to an XML duration formatted string **[backwards incompatible]**
* change `tundra.integer:*` services to support optional inputs
* change `tundra.list.document:resize` to support specifying a negative length which results in the given list being truncated by that many items
* change `tundra.list.document:sort` to support sorting by multiple keys in ascending or descending order
* change `tundra.list.object:resize` to support specifying a negative length which results in the given list being truncated by that many items
* change `tundra.list.service:chain` to return total invocation duration
* change `tundra.list.string:match` to return boolean flags indicating if all, any, or none of the items in the given list matched the given pattern
* change `tundra.list.string:match` to support literal patterns as well as regular expression patterns
* change `tundra.list.string:resize` to support specifying a negative length which results in the given list being truncated by that many items
* change `tundra.object:reflect` to return identity hash code of the object, which can be used to compare object identities
* change `tundra.service:benchmark` to first validate the service exists before attempting to benchmark it
* change `tundra.service:invoke` to return invocation duration
* change `tundra.string:match` to support both regular expression and literal pattern matches
* change `tundra.string:quote` input argument to be optional
* change `tundra.timezone:get` services to handle custom datetime patterns
* change `tundra.timezone:get` to return UTC zone when given timezone ID "Z"
* change `tundra.timezone:get` to support retrieving a timezone using either a raw millisecond offset value, an XML duration offset value, or an offset specified in the form (+|-)HH:mm
* change `tundra.timezone:list` services to handle custom datetime patterns
* change `tundra.timezone:self` services to handle custom datetime patterns
* change `tundra.uri:emit` to not include user, password, or fragment in resulting URI string if specified as emtpy strings
* fix `tundra.content:deliver` to correctly support additional HTTP settings in `$pipeline`
* fix `tundra.datetime:add` to work correctly with very large durations (larger than a signed 32-bit integer)
* fix `tundra.datetime:subtract` to work correctly with very large durations (larger than a signed 32-bit integer)
* fix `tundra.decimal:average` to work correctly when given an empty list
* fix `tundra.decimal:maximum` to work correctly when given an empty list
* fix `tundra.decimal:minimum` to work correctly when given an empty list
* fix `tundra.gzip:compress` to work correctly by first closing the byte array output stream before accessing the underlying byte array
* fix `tundra.http:client` to correctly support HTTP PUT method
* fix `tundra.list.document:sort` to copy the input `IData[]` object before sorting, rather than mutating the original `IData[]` object
* fix `tundra.service:benchmark` NaN NumberFormatException thrown when all sample durations were 0 by returning a standard deviation of 0, rather than attempting a division by zero calculation
* fix `tundra.timezone:get` to return nothing when given timezone ID is not found, rather than returning UTC
* fix `tundra.uri:parse` to correctly parse opaque URIs that include variable substitution in the query string

# 0.0.14 (2014-05-28)

* add `tundra.content:deliver` protocol handlers to the Tundra public API in the folder `tundra.content.deliver`
* add `tundra.list.document:filter` to filter a list with a given conditional statement
* add `tundra.list.object:filter` to filter a list with a given conditional statement
* add `tundra.list.string:filter` to filter a list with a given conditional statement
* add `tundra.schedule:*` services for creating, manipulating, and deleting scheduled tasks
* add `tundra.service:benchmark` for measuring the performance of a given service
* add `tundra.string:quote` to escape a string value so that it can be used in a regular expression pattern to match the literal value
* change `tundra.condition:evaluate` to evaluate null and empty conditions to true
* change `tundra.condition:evaluate` to use the exact same implementation of conditional expressions as used by flow branch steps
* change `tundra.datetime:*` services to emit XML dates and times without the time zone offset suffix
* change `tundra.document:compact` to support recursively compacting embedded `com.wm.data.IData`, `com.wm.util.Table`, and `com.wm.data.IData[]` objects
* change `tundra.document:get` to support retrieving values by indexed key from com.wm.util.Table objects
* change `tundra.document:keys` to support filtering the returned list of keys with a given regular expression pattern
* change `tundra.document:squeeze` to remove empty `com.wm.data.IData` and `java.lang.Object[]` objects
* change `tundra.list.document:compact` to support recursively compacting embedded `com.wm.data.IData`, `com.wm.util.Table`, and `com.wm.data.IData[]` objects
* change `tundra.list.document:squeeze` to remove empty `com.wm.data.IData` and `java.lang.Object[]` objects
* change `tundra.object:reflect` to be able to reflect on a pipeline variable identified by key, which is useful when reflecting on objects that may be lists/array (because mapping them to $object will implicitly dereference the array's zeroth element)
* change `tundra.pipeline:get` to support retrieving values by indexed key `com.wm.util.Table` objects
* fix `tundra.uri:parse` to correctly handle paths that include variable substitution strings

# 0.0.13 (2014-02-13)

* change `tundra.content:discard` implementation to use `tundra.service:respond`
* change `tundra.content:reject` implementation to use `tundra.service:respond`

# 0.0.12 (2014-01-31)

* add `tundra.gzip:*` services for compressing and decompressing data using the gzip format
* add `tundra.service:initiator` to check if the calling service is the initiating top-level
  service for this thread
* add `tundra.service:respond` for setting a transport-specific response to be returned to the calling process
* add `tundra.timezone:*` services for inspecting time zone offsets
* add `tundra.zip:*` services for compressing and decompressing data in the zip format
* change `tundra.content:retrieve` to allow the archive directory for file protocol to be configurable via a query string parameter
* change `tundra.duration:format` to correctly parse fractional millisecond values
* change `tundra.mime.type:parse` to fix typo in service comment

# 0.0.11 (2013-12-13)

* add `tundra.file:copy` for copying content from a source file to a target file
* change `tundra.content:deliver` to fix HTTP and HTTPS delivery compatibility with some web servers by not specifying the user/password in the destination URI when calling `pub.client:http`; instead the user and password is provided in the `auth` argument
* change `tundra.list.document:join` to simplify the default settings to use a single map step
* change `tundra.list.document:get` to add `$iteration` input argument, to make the service easier to use from within a loop
* change `tundra.list.object:get` to add `$iteration` input argument, to make the service easier to use from within a loop
* change `tundra.list.string:get` to add `$iteration` input argument, to make the service easier to use from within a loop
* change `tundra.stream:copy` to use default buffer size consistently

# 0.0.10 (2013-11-25)

* add `tundra.list.*:grow`, `:resize` and `:shrink` services for resizing lists
* add `tundra.list.object:difference` and `:intersection`, and `tundra.list.string:difference` and `:intersection` to return the set difference and intersection of two lists respectively
* add `tundra.schema.exception:handler` specification describing the service signature required of a `tundra.service:ensure` `$catch` service
* change tundra.assertion.list.object:unequal to fix typo in service comment
* change `tundra.condition:evaluate` to include the ANTLR4 grammar used to generate the parser in the service comments
* change `tundra.content:amend` and `tundra.document:amend` to add an optional conditional statement to each amendment to control whether the amendment gets applied
* change `tundra.decimal:add` to allow nulls in input list, defaulting them to zero
* change `tundra.integer:average`, `:maximum`, and `:minimum` to disallow nulls in input list

# 0.0.9 (2013-11-05)

* fix `tundra.http:client` variable substitution bug in the resulting exception message when an unexpected HTTP response code is encountered

# 0.0.8 (2013-10-11)

* change `tundra.content:discard` to support being wrapped by another service, and being executed using the `/soap/rpc` handlers
* change `tundra.content:reject` to support being wrapped by another service, and being executed using the `/soap/rpc` handlers

# 0.0.7 (2013-10-01)

* add `tundra.assertion.datetime:equal` and `:unequal` services
* add `tundra.bool:*` services for manipulating boolean values
* add `tundra.content:discard` and `:reject` gateway services for successfully receiving and throwing away and rejecting content respectively
* add `tundra.datetime:emit` and `:parse` services for converting between datetime strings and `java.util.Date` objects
* add `tundra.decimal:average`, `:maximum`, and `:minimum` for calculating the average, maximum, and minimum from a list of decimals
* add `tundra.directory:join` to construct a single file path from a parent directory and child path or file
* add `tundra.directory:ls` as an alternative to tundra.directory:list for when performance is a priority over ease of use
* add `tundra.document:clear` to remove all keys except those specified from the given `IData` document
* add `tundra.document:emit` and `:parse` for encoding/decoding `IData` objects as XML
* add `tundra.document:sort` to sort an `IData` by its keys in natural ascending order
* add `tundra.document:squeeze` to trim all leading and trailing whitespace, then convert empty strings to null, then compact the `IData` document by removing all null values
* add `tundra.file:match` to check if a given file matches a regular expression or wildcard pattern
* add `tundra.integer:average`, `:maximum`, and `:minimum` for calculating the average, maximum, and minimum from a list of integers
* add `tundra.list.document:clear` to remove all keys except for those specified from each `IData` document in the given list
* add `tundra.pipeline:clear` to remove all keys except those specified from the pipeline
* add `tundra.pipeline:emit` and `:parse` for encoding/decoding the pipeline as XML
* change `tundra.content:deliver` to return the delivery response body when applicable
* change `tundra.content:emit` to HTML-encode values by default when emitting XML
* change `tundra.datetime:add`, `:compare`, `:duration`, and `:subtract` to support datetimes other than ISO8601
* change `tundra.datetime:format` so that when the input is null or absent, the output is now guaranteed to be absent rather than null, so that when mapping to an `IData` structure null elements are not created inadvertently
* change `tundra.datetime:format` to support JDBC named patterns, and use `java.sql.Timestamp` to parse and emit JDBC datetime strings
* change `tundra.directory:list` to improve performance, and fix a `NullPointerException` when the directory doesn't exist
* change `tundra.document:rename` to fix bug when old and new keys are the same
* change `tundra.document:split` to support the input and output arguments being the same for the translation service
* change `tundra.document:translate` to fix a variable substitution bug in an error message
* change `tundra.document:translate` to support the input and output arguments being the same for the translation service
* change `tundra.http:client` to have unambiguous and distinct input (`$request`) and output (`$response`) arguments **[backwards incompatible]**
* change `tundra.integer:decrement` input argument to be optional so that you don't need to initialize a counter variable to zero prior to calling this service in a loop
* change `tundra.integer:increment` input argument to be optional so that you don't need to initialize a counter variable to zero prior to calling this service in a loop
* change `tundra.uri:parse` so that malformed URIs now return more meaningful error messages
* delete all `tundra.support.test:*` services from Tundra, and moved them to new TundraTest package to keep Tundra as lightweight as possible, and to avoid inadvertently running the test services in production

# 0.0.6 (2013-07-20)

* add `tundra.content:retrieve` to fetch content from a source URI and process it with the given content processing service
* add `tundra.document:listify` to normalize an `IData` or `IData[]` item in the pipeline to an `IData[]`
* add `tundra.list.string:match` to partition a list of strings into separate lists of matched and unmatched strings by matching each item against the given regular expression
* add `tundra.mime.type:*` services for parsing and emitting mime types
* add `tundra.node:access` to set ACL permissions on namespace nodes
* add `tundra.object:listify` to normalize an `Object` or `Object[]` item in the pipeline to an `Object[]`
* add `tundra.string:listify` to normalize a `String` or `String[]` item in the pipeline to a `String[]`
* add `tundra.system:reflect` to return system settings (Java properties, environment settings) and state (Java heap memory usage)
* add `tundra.user:current` to return the currently logged on user invoking the service
* change `tundra.content:deliver` to correctly allow any additional variables to be passed in `$pipeline`
* change `tundra.content:deliver` to correctly extract the authority component of HTTP URIs
* change `tundra.content:deliver` to support mailto: URI delivery
* change `tundra.directory:list` to support wildcard/glob pattern matching, as well as regular expression matching
* change `tundra.list.*:drop` services to return `$list` to improve their usefulness
* change `tundra.object:instance` now always returns `$instance?`
* change `tundra.support:startup` to set appropriate ACL permissions on tundra nodes
* change `tundra.xml:validate` to return `$valid?` as false when `$content` is null

# 0.0.5 (2013-07-03)

* add `tundra.xpath:exists` to test whether an XPath query exists in an XML document
* add `tundra.list.service:chain` for calling a list of services sequentially with a shared pipeline
* add `tundra.list.service:ensure` to provide a try/catch/finally pattern while invoking a list of services sequentially with a shared pipeline
* add `tundra.document:first` and `tundra.document:last` to return the first and last {key, value} pair in an `IData` document
* add `tundra.pipeline:first` and `tundra.pipeline:last` to return the first and last {key, value} pair in the pipeline
* add `tundra.integer:*` services for working with arbitrary precision integers (uses `java.math.BigInteger` for its implementation)
* add `tundra.decimal:*` services for working with arbitrary precision decimals (uses `java.math.BigDecimal` for its implementation)
* add `tundra.condition:evaluate` for testing conditional predicates against the pipeline or optional scope `IData` document
* add `tundra.service:validate` to test whether a named service exists
* add `tundra.content:amend` and `tundra.document:amend` for making precise inline edits in XML or flat file content, or `IData` documents
* change `tundra.content:split` to support splitting content into disparate schemas

# 0.0.4 (2013-03-30)

* add `tundra.string:squeeze` for replacing runs of whitespace characters with a single space character
* add `tundra.document:duplicate` for making (optionally recursive) copies of `IData` documents
* add support for fully qualified `IData` key names `a/b/c[0]` to relevant `tundra.document` and `tundra.pipeline` services
* add `tundra.uri:*` services for parsing and emitting URI strings
* add `tundra.content:deliver` and `tundra.document:deliver` which provides an extensible framework for delivering arbitrary content to a URI destination
* add `tundra.dns:resolve` to lookup DNS names and IP addresses
* add `tundra.dns:localhost` to return the name and IP address of the localhost Integration Server is running on
* add `tundra.base64:*` services for encoding and decoding base64 strings
* add `tundra.service:self` to return the name of the currently running service
* change `tundra.directory` and `tundra.file` services to handle file:// URIs
* rename `tundra.file:open` to `tundra.file:process` **[backwards incompatible]**

# 0.0.3 (2012-07-28)

* add `tundra.content:*` services for parsing, emitting, and translating or mapping structured textual data (XML, flat files)
* add lots of useful `tundra.document:*` services for manipulating IData documents
* add `tundra.node:*` services for querying Integration Server namespace nodes
* add `tundra.pipeline:drop`, `tundra.pipeline:get`, and `tundra.pipeline:put` for dropping, retrieving, and adding {key, value} pairs from/to the pipeline
* add `tundra.string:lines` to return all the lines in a string
* add `tundra.string:replace` to replace all occurrences of a regular expression pattern in a string
* add `tundra.string:split` to split a string around matches of a regular expression pattern

# 0.0.2 (2012-07-18)

* add `tundra.directory:*` for working with directories
* add `tundra.file:*` for working with files
* add `tundra.list.datetime:format` for formatting a list of datetime strings
* add `tundra.list.duration:format` for formatting a list of time duration strings
* add `tundra.list.service:invoke` for invoking a list of services either synchronously (with an optional level of concurrency) or asynchronously
* add `tundra.list.service:join` for waiting on a list of asynchronously invoked services to finish
* add `tundra.object:stringify` for converting any object to a string
* add `tundra.pipeline:capture` for grabbing a snapshot of the current pipeline as an `IData` document
* add `tundra.pipeline:log` for logging the current contents of the pipeline to the server debug log
* add `tundra.service:ensure` to provide a try/catch/finally pattern for service invocations
* add `tundra.service:join` for waiting on an asynchronously invoked service to finish
* add `tundra.service:sleep` for putting the currently executing thread to sleep
* add `tundra.string:match` for matching strings against regular expression patterns
* add `tundra.string:trim` for removing leading and trailing whitespace from strings

# 0.0.1 (2012-06-23)

* add `tundra.assertion:*` to provide a lightweight unit testing framework
* add `tundra.bytes:*` for working with `byte[]` objects
* add `tundra.datetime:*` for parsing, emitting, and manipulating datetime strings
* add `tundra.document:*` for manipulating `com.wm.data.IData` objects (also known as records or documents in Integration Server)
* add `tundra.duration:*` for parsing, emitting, and manipulating time durations
* add `tundra.exception:*` for throwing exceptions
* add `tundra.id:*` for generating unique identifier strings
* add `tundra.list:*` to provide a comprehensive set of services for enumerating and manipulating lists (`com.wm.data.IData[]`, `java.lang.Object[]`, `java.lang.String[]`)
* add `tundra.object:*` for reflecting on objects
* add `tundra.service:*` for invoking services dynamically
* add `tundra.stream:*` for working with `java.io.InputStream` and `java.io.OutputStream` objects
* add `tundra.string:*` for manipulating strings
