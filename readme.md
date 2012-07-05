# Tundra ❄

A package of useful services for webMethods Integration Server 7.1 or higher.

## Installation

From your Integration Server installation:

    $ cd ./packages
    $ git clone https://github.com/Permafrost/Tundra.git

Then activate and enable the package from the package management web page on the Integration Server web administration site.

## Conventions

1. All input and output pipeline arguments are prefixed with '$' as a poor-man's scoping mechanism (typical
   user-space variables will be unprefixed)
2. All boolean arguments are suffixed with a '?'
3. Single-word argument names are preferred. Where multiple words are necessary, words are separated with a
   '.'
4. Service namespace is kept flat.  Namespace folders are usually nouns. Service names are usually verbs,
   indicating the action performed on the noun (parent folder)
5. All private elements are kept in the tundra.support folder. All other elements are public and form the public API
   of the package. As the private elements do not contribute to the public API, they are liable to change at any time.
   Enter at your own risk

## Tests

*Almost* every service in Tundra has unit tests, located in the tundra.support.test folder.

To run the test suite, either run `tundra:test($package = "Tundra")` service directly, or visit
<http://localhost:5555/invoke/tundra/test?$package=Tundra> (substituting  your own
Integration Server host and port for localhost:5555).

## Services

### Tundra

Top-level services for the most common tasks:

    # writes a message to the server log, automatically prefixed with the call stack
    tundra:log($message, $level)

    # runs all *test*:should* services in the given package, returning the test results
    # (a test case passes if no exceptions are thrown)
    tundra:test($package)

#### Assertion

Supports unit testing by providing the following bare-bones assertion services:

    # throws an assertion error if the expected and actual documents are not equal
    tundra.assertion.document:equal($expected, $actual, $message)

    # throws an assertion error if the expected and actual documents are equal
    tundra.assertion.document:unequal($expected, $actual, $message)

    # throws an assertion error if the expected and actual document lists are not equal
    tundra.assertion.list.document:equal($expected[], $actual[], $message)

    # throws an assertion error if the expected and actual document lists are equal
    tundra.assertion.list.document:unequal($expected[], $actual[], $message)

    # throws an assertion error if the expected and actual lists are not equal
    tundra.assertion.list.object:equal($expected[], $actual[], $message)

    # throws an assertion error if the given list is null
    tundra.assertion.list.object:exists($list[], $message)

    # throws an assertion error if the given list is not an instance of the given class
    tundra.assertion.list.object:instance($list[], $class, $message)

    # throws an assertion error if the given list is not null
    tundra.assertion.list.object:nothing($list[], $message)

    # throws an assertion error if the expected and actual lists are equal
    tundra.assertion.list.object:unequal($expected[], $actual[], $message)

    # throws an assertion error if the expected and actual string lists are not equal
    tundra.assertion.list.string:equal($expected[], $actual[], $message)

    # throws an assertion error if the expected and actual string lists are equal
    tundra.assertion.list.string:unequal($expected[], $actual[], $message)

    # throws an assertion error if the expected and actual objects are not equal
    tundra.assertion.object:equal($expected, $actual, $message)

    # throws an assertion error if the given object is null
    tundra.assertion.object:exists($object, $message)

    # throws an assertion error if the given object is not an instance of the given class
    tundra.assertion.object:instance($object, $class, $message)

    # throws an assertion error if the given object is not null
    tundra.assertion.object:nothing($object, $message)

    # throws an assertion error if the expected and actual objects are equal
    tundra.assertion.object:unequal($expected, $actual, $message)

    # throws an assertion error if this service is executed
    tundra.assertion.step:unreached($message)

    # throws an assertion error if the expected and actual strings are not equal
    tundra.assertion.string:equal($expected, $actual, $message)

    # throws an assertion error if the expected and actual strings are equal
    tundra.assertion.string:unequal($expected, $actual, $message)

#### Bytes

Services for manipulating byte arrays:

    # converts a string, byte array or input stream to a byte array
    tundra.bytes:normalize($object, $encoding)

#### Datetime

Services for manipulating datetimes:

    # adds a duration of time to the given datetime
    tundra.datetime:add($datetime, $duration)

    # compares two datetime strings, indicating position in time relative to one another
    # returns three booleans: $before?, $equal?, and $after?
    tundra.datetime:compare($datetime.x, $datetime.y)

    # concatenates a date and time into a single datetime
    tundra.datetime:concatenate($date, $time)

    # returns the duration of time between two datetimes
    tundra.datetime:duration($datetime.start, $datetime.end)

    # formats a datetime that conforms to the input pattern, according to the output pattern
    tundra.datetime:format($datetime, $pattern.input, $pattern.output)

    # returns the current datetime
    tundra.datetime:now

    # subtracts a duration of time from the given datetime
    tundra.datetime.subtract($datetime, $duration)

    # returns true if the given datetime conforms to the given pattern.
    tundra.datetime.validate($datetime, $pattern)

#### Document

Services for manipulating com.wm.data.IData objects:

    # returns true if the two IData documents are equal (contain the same keys and values)
    tundra.document:equal($document.x, $document.y)

#### Duration

    # adds two durations together
    tundra.duration:add($duration.x, $duration.y)

    # compares two durations, returning if the first is less than, equal to, or greater than
    # the second duration, or if the comparison is indeterminate (such as comparing a 1 month
    # duration with a 30 days duration)
    tundra.duration:compare($duration.x, $duration.y)

    # formats the given duration string according to the desired pattern (a start instant, $datetime,
    # may be required when formatting fields with indeterminate values, such as converting months to
    # days, because the number of days in a month varies)
    tundra.duration:format($duration, $datetime, $pattern.input, $pattern.output)

    # subtracts one duration from another
    tundra.duration:subtract($duration.x, $duration.y)

    # returns the sum of all the given durations, returning (x1 + x2 + ... + xn)
    tundra.duration:sum($durations[])

#### Exception

    # throws a new com.wm.app.b2b.server.ServiceException with the given message
    # or rethrows the given exception
    tundra.exception:raise($message, $exception)

#### ID

    # generates an immutable 128-bit universally unique identifier
    # refer: <http://docs.oracle.com/javase/6/docs/api/java/util/UUID.html>
    tundra.id:generate

#### List

Services for manipulating lists:

    # formats a list of datetimes that conform to the input pattern, according
    # to the output pattern
    tundra.list.datetime:format($list[], $pattern.input, $pattern.output)

    # appends a single item to the end of a list, such that appending an item to a
    # list containing n items results in a new list of n + 1 items
    tundra.list.document:append($list[], $item)

    # removes all null items from the given list, thereby shortening the length of the list
    tundra.list.document:compact($list[])

    # returns a new list containing all the items in the given $list and $items input arguments
    tundra.list.document:concatenate($list.x[], $list.y[])

    # iterates through the given list, invoking the given service for each item in the list,
    # passing $item, $index, $iteration and $length variables
    tundra.list.document:each($list[], $service, $pipeline, $item.input)

    # returns true if the two given lists are equal
    tundra.list.document:equal($list.x[], $list.y[])

    # returns true if the given item is found in the given list
    tundra.list.document:include($list[], $item)

    # returns a new list with the given item inserted at the desired index in the given list
    tundra.list.document:insert($list[], $item, $index)

    # returns the item stored at a given index in a list (supports forward and reverse indexing)
    tundra.list.document:item($list[], $index)

    # returns the number of items in the given list
    tundra.list.document:length($list[])

    # returns a new list created by invoking the given service for each item in the given list,
    # and collecting new the values returned
    tundra.list.document:map($list[], $service, $item.input, $item.output)

    # prepends a single item to the front of a list, such that prepending an item to a list containing
    # n items results in a new list of n + 1 items
    tundra.list.document:prepend($list[], $item)

    # returns a new list with all items from the given list in reverse order
    tundra.list.document:reverse($list[])

    # returns a new list which is a subset of the items in the given list
    tundra.list.document:slice($list[], $index, $list)

    # returns a new list sorted according to the natural ordering of the given list's items
    # refer: <http://docs.oracle.com/javase/6/docs/api/java/lang/Comparable.html>
    tundra.list.document:sort($list[], $key)

    # formats a list of duration strings according to the desired pattern
    # (a start instant, $datetime, may be required when formatting fields with
    # indeterminate values, such as converting months to days, because the number
    # of days in a month varies)
    tundra.list.duration:format($list[], $datetime, $pattern.input, $pattern.output)

    # appends a single item to the end of a list, such that appending an item to a list
    # containing n items results in a new list of n + 1 items
    tundra.list.object:append($list[], $item)

    # removes all null items from the given list, thereby shortening the length of the list.
    tundra.list.object:compact($list[])

    # returns a new list containing all the items in the given $list and $items input arguments
    tundra.list.object:concatenate($list.x[], $list.y[])

    # iterates through the given list, invoking the given service for each item in the list,
    # passing $item, $index, $iteration and $length variables
    tundra.list.object:each($list[], $service, $pipeline, $item.input)

    # returns true if the two given lists are equal
    tundra.list.object:equal($list.x[], $list.y[])

    # returns true if the given item is found in the given list
    tundra.list.object:include($list[], $item)

    # returns a new list with the given item inserted at the desired index in the given list
    tundra.list.object:insert($list[], $item, $index)

    # returns the item stored at a given index in a list (supports forward and reverse indexing)
    tundra.list.object:item($list[], $index)

    # returns a string created by converting each list item to a string, separated by the given
    # separator string
    tundra.list.object:join($list[], $separator)

    # returns the number of items in the given list
    tundra.list.object:length($list[])

    # returns a new list created by invoking the given service for each item in the given list,
    # and collecting new the values returned
    tundra.list.object:map($list[], $service, $item.input, $item.output)

    # prepends a single item to the front of a list, such that prepending an item to a list
    # containing n items results in a new list of n + 1 items
    tundra.list.object:prepend($list[], $item)

    # returns a new list with all items from the given list in reverse order
    tundra.list.object:reverse($list[])

    # returns a new list which is a subset of the items in the given list
    tundra.list.object:slice($list[], $index, $length)

    # returns a new list sorted according to the natural ordering of the given list's items
    # refer: <http://docs.oracle.com/javase/6/docs/api/java/lang/Comparable.html>
    tundra.list.object:sort($list[])

    # returns a new list with all duplicates from the given list removed, such that no two
    # items are equal
    tundra.list.object:unique($list[])

    # appends a single item to the end of a list, such that appending an item to a list
    # containing n items results in a new list of n + 1 items
    tundra.list.string:append($list[], $item)

    # removes all null items from the given list, thereby shortening the length of the list.
    tundra.list.string:compact($list[])

    # returns a new list containing all the items in the given $list and $items input arguments
    tundra.list.string:concatenate($list.x[], $list.y[])

    # iterates through the given list, invoking the given service for each item in the list,
    # passing $item, $index, $iteration and $length variables
    tundra.list.string:each($list[], $service, $pipeline, $item.input)

    # returns true if the two given lists are equal
    tundra.list.string:equal($list.x[], $list.y[])

    # returns true if the given item is found in the given list
    tundra.list.string:include($list[], $item)

    # returns a new list with the given item inserted at the desired index in the given list
    tundra.list.string:insert($list[], $item, $index)

    # returns the item stored at a given index in a list (supports forward and reverse indexing)
    tundra.list.string:item($list[], $index)

    # returns a string created by converting each list item to a string, separated by the given
    # separator string
    tundra.list.string:join($list[], $separator)

    # returns the number of items in the given list
    tundra.list.string:length($list[])

    # returns a new list created by invoking the given service for each item in the given list,
    # and collecting new the values returned
    tundra.list.string:map($list[], $service, $item.input, $item.output)

    # prepends a single item to the front of a list, such that prepending an item to a list
    # containing n items results in a new list of n + 1 items
    tundra.list.string:prepend($list[], $item)

    # returns a new list with all items from the given list in reverse order
    tundra.list.string:reverse($list[])

    # returns a new list which is a subset of the items in the given list
    tundra.list.string:slice($list[], $index, $length)

    # returns a new list sorted according to the natural ordering of the given list's items
    # refer: <http://docs.oracle.com/javase/6/docs/api/java/lang/Comparable.html>
    tundra.list.string:sort($list[])

    # returns a new list with all duplicates from the given list removed, such that no two
    # items are equal
    tundra.list.string:unique($list[])

#### Object

Services for manipulating java.lang.Object objects:

    # returns true if object is an instance of given class
    tundra.object:instance($object, $class)

    # returns null
    tundra.object:nothing

    # returns the given object's class, whether it's an array, and whether it's a
    # primitive type, such as an int, or byte
    tundra.object:reflect($object)

    # converts the given object to a string by calling its toString method
    tundra.object:stringify($object)

#### Pipeline

    # returns a clone of the current pipeline as a document: useful if you want to
    # pass the pipeline itself as an input to a service
    tundra.pipeline:capture

    # writes the current pipeline to the server log
    tundra.pipeline:log($level)

#### Service

    # returns the current thread's call stack
    tundra.service:callstack

    # provides a try/catch/finally pattern for flow services: if $service throws an exception
    # when invoked, then the $catch service is invoked (with $exception, $exception.class,
    # $exception.message and $exception.stack arguments added to pipeline); the $finally service
    # is then invoked, whether an exception was thrown by $service or not
    tundra.service:ensure($service, $catch, $finally, $pipeline)

    # calls the given service dynamically, either synchronously or asynchronously; if asynchronous
    # a service thread is returned which can be waited on to finish (joined) using tundra.service:join
    tundra.service:invoke($service, $pipeline, $mode)

    # waits for the given service thread to finish before returning the service output pipeline
    tundra.service:join($thread)

    # sends the currently executing thread to sleep (temporarily cease execution) for the specified
    # duration, subject to the precision and accuracy of system timers and schedulers
    tundra.service:sleep($duration)

#### Stream

Services for manipulating java.io.InputStream and java.io.OutputStream objects:

    # closes the given input or output stream
    tundra.stream:close($stream)

    # copies all data from the given input stream (or string or bytes) to the given
    # output stream, then closes the streams
    tundra.stream:copy($input, $output)

    # converts a string, byte array or input stream to an input stream
    tundra.stream:normalize($object, $encoding)

#### String

Services for manipulating java.lang.String objects:

    # returns the given string in lower case
    tundra.string:lowercase($string, $locale)

    # converts a string, byte array or input stream to a string
    tundra.string:normalize($object, $encoding)

    # returns the given string in upper case
    tundra.string:uppercase($string, $locale)

## Contributions

1. Check out the latest master to make sure the feature hasn’t been implemented or the bug hasn’t been fixed yet
2. Check out the issue tracker to make sure someone already hasn’t requested it and/or contributed it
3. Fork the project
4. Start a feature/bugfix branch
5. Commit and push until you are happy with your contribution
6. Make sure to add tests for it. This is important so it won't in a future version unintentionally

Please try not to mess with the package version, or history. If you want your own version please isolate it to its own commit, so it can be cherry-picked around.

## Copyright

Copyright © 2012 Lachlan Dowding. See license.txt for further details.
