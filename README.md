# cljvalidate

Set of predicate functions to validate strings/symbols for conformance to spec at "http://clojure.org/reader".

## Usage

Three predicate functions that test for compliance:

(valid-symbol-name? "abc") => true
(valid-class-or-namespace-name? "abc") => true
(valid-symbol-fqname? "abc") => true

(valid-symbol-name? "xyz.uvw") => false
(valid-class-or-namespace-name? "xyz.uvw") => true
(valid-symbol-fqname? "xyz.uvw") => true

(valid-symbol-name? "xyz.uvw/abc") => false
(valid-class-or-namespace-name? "xyz.uvw/abc") => false
(valid-symbol-fqname? "xyz.uvw/abc") => true

See the cljvalidate/test/core.clj file for more extensive scenarios.

## Caveats

These validation functions are not official nor endorsed by clojure-core, and will potentially be out of date with the latest and greatest clojure releases...

(arguably/hopefully, some form of validation functions make their way into the clojure.core code-base to provide an easier, officially sanctioned mechanism for compliance testing...)

There is some ambiguity about ":", whether it can be part of a symbol or not (?). (requires too much regex-gymnastics to scan for right now...)

Also, the restriction on class names seems much stricter than Java's, which could "officially" exclude you from importing java classes which identifiers include arbitrary unicode chars and such. 

A simple validation test of the public vars in clojure.core yields:
    user=> (clojure.set/select #(not(nil? %)) (set (map (fn [k] (when-not (valid-symbol-name? k)k))(keys (ns-publics (find-ns 'clojure.core))))))
    #{== not= ->Vec -' ->ArrayChunk ->> .. / +' ->VecNode inc' < ->VecSeq -> = *' > >= dec' <=}

shows that clojure.core does use the following chars that are disallowed for mere mortals: "=", "<", ">", "'"(not at start of identifier), ".." , "/" (as expected).

This kind of static analysis could detect some potential violations, but wouldn't catch those symbols/keywords easily that are generated dynamically from json-keys for example.


## Acknowledgements

Thanks to all those on the clojure-user mailing list who chimed in on this topic.

## License

Copyright (C) 2011 - Frank Siebenlist

Distributed under the Eclipse Public License, the same as Clojure
uses. See the file COPYING.
