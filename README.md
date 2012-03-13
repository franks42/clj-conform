# cljvalidate

Set of predicate functions to validate strings/symbols for conformance to spec at "http://clojure.org/reader".

## Usage

Three predicate functions that test for compliance:

(valid-symbol-name? "abc") => true
(valid-symbol-name? 'abc)  => true

(valid-class-or-namespace-name? "xyz.uvw") => true

(valid-symbol-FQName? "xyz.uvw/abc") => true

See the test file for more extensive scenarios.

## Caveats

These validation functions are not official nor endorsed by clojure.core, and will potentially be out of date with the latest and greatest clojure releases...

(arguably/hopefully, some form of validation functions make their way into the clojure.core code-base to provide an easier, officially sanctioned mechanism for compliance testing...)

There is some ambiguity about ":", whether is can be part of a symbol or not (?).

Also, the restriction on class names seems much stricter than Java's, which could "officially" exclude you from importing java classes that include arbitrary unicode chars. 


## Acknowledgements

Thanks to all those on the clojure-user mailing list who chimed in on this topic.

## License

Copyright (C) 2011 - Frank Siebenlist

Distributed under the Eclipse Public License, the same as Clojure
uses. See the file COPYING.
