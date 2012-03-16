# clj-conform

Set of predicate functions to validate conformance of the identifiers used in symbols/keywords to the spec at "http://clojure.org/reader".

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

See the clj_conform/test/core.clj file for more extensive scenarios.

## Caveats

These validation functions are not official nor endorsed by clojure-core, and will potentially be out of date with the next, latest and greatest clojure releases...

(arguably/hopefully, some form of validation functions make their way into the clojure.core code-base to provide an easier, officially sanctioned mechanism for defensive programming and compliance testing...)

There is some ambiguity about ":", whether it can be part of a symbol or not (?). 
Also, the restriction on class names seems much stricter than Java's, which could "officially" exclude you from importing java classes which identifiers include arbitrary unicode chars and such. 

A simple validation test of all identifiers used in all the namespaces in clojure.core yields:

	user=> (clj-conform/ns-non-compliant-fqns)
	("cljsh.utils/cljsh.utils.proxy$java.lang.InheritableThreadLocal$0" "clojure.core/*'" 
	"clojure.core/+'" "clojure.core/-'" "clojure.core/->" "clojure.core/->>" 
	"clojure.core/->ArrayChunk" "clojure.core/->Vec" "clojure.core/->VecNode" 
	"clojure.core/->VecSeq" "clojure.core/.." "clojure.core//" "clojure.core/<"
	"clojure.core/<=" "clojure.core/=" "clojure.core/==" "clojure.core/>" 
	"clojure.core/>0?" "clojure.core/>1?" "clojure.core/>=" "clojure.core/dec'"
	"clojure.core/inc'" "clojure.core/not=" "clojure.core/prim->class" 
	"clojure.java.io/inputstream->reader" "clojure.java.io/outputstream->writer" 
	"clojure.lang.Compiler$CompilerException" "java.lang.Thread$State" 
	"java.lang.Thread$UncaughtExceptionHandler")
	
shows that clojure.core does use the following chars that are disallowed for mere mortals: "=", "<", ">", "'"(not at start of identifier), ".." , "/" (as expected), and $ in classnames.

This kind of static analysis could detect some potential compliance issues, but wouldn't catch those symbols/keywords easily that are generated dynamically from json-keys for example.


## Acknowledgements

Thanks to all those on the clojure-user mailing list who chimed in on this topic.

## License

Copyright (C) 2011 - Frank Siebenlist

Distributed under the Eclipse Public License, the same as Clojure
uses. See the file COPYING.
