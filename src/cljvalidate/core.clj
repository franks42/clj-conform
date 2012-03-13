;; Copyright (c) Frank Siebenlist. All rights reserved.
;; The use and distribution terms for this software are covered by the
;; Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file COPYING at the root of this distribution.
;; By using this software in any fashion, you are agreeing to be bound by
;; the terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns cljvalidate.core
  "Set of functions to check/validate symbol-names for compliance with 
  \"official\" spec at \"http://clojure.org/reader\", which reads:
  
  Symbols begin with a non-numeric character and can contain 
  alphanumeric characters and *, +, !, -, _, and ? (other characters
  will be allowed eventually, but not all macro characters have been 
  determined). '/' has special meaning, it can be used once in the
  middle of a symbol to separate the namespace from the name, e.g. 
  my-namespace/foo. '/' by itself names the division function. '.' 
  has special meaning - it can be used one or more times in the middle
  of a symbol to designate a fully-qualified class name, 
  e.g. java.util.BitSet, or in namespace names. Symbols beginning or 
  ending with '.' are reserved by Clojure. Symbols containing / or . 
  are said to be 'qualified'. Symbols beginning or ending with ':' 
  are reserved by Clojure. A symbol can contain one or more 
  non-repeating ':'s.
  
  ")

(defn valid-symbol-name?
  "A symbol string, begins with a non-numeric character 
  and can contain alphanumeric characters and *, +, !, -, _, and ?.
  (see \"http://clojure.org/reader\" for details)."
  [n]
  (when-let [name-str (cond (string? n) n
                      (symbol? n) (str n))]
    (re-matches #"[a-zA-z*+!\-_?][a-zA-z0-9*+!\-_?]*" name-str)))


(defn valid-class-or-namespace-name?
  "A class or namespace, begins with a non-numeric character 
  and can contain alphanumeric characters and *, +, !, -, _, and ?.
  '.' has special meaning - it can be used one or more times in the 
  middle of a symbol to designate a fully-qualified class name
  , e.g. java.util.BitSet, or in namespace names. 
  (see \"http://clojure.org/reader\" for details)."
  [n]
  (when-let [name-str (cond (string? n) n
                     (symbol? n) (str n))]
    (re-matches #"[a-zA-z*+!\-_?][a-zA-z0-9*+!\-_?]*(\.[a-zA-z0-9*+!\-_?]+)*" name-str)))


(defn valid-symbol-FQName?
  "A symbol string, begins with a non-numeric character 
  and can contain alphanumeric characters and *, +, !, -, _, and ?.
   '/' has special meaning, it can be used once in the middle of a 
   symbol to separate the namespace from the name, e.g. my-namespace/foo.
   So a FQN can either be a namespace, class or ns/name.
  (see \"http://clojure.org/reader\" for details)."
  [n]
  (when-let [name-symb (cond (string? n) (symbol n)
                       (symbol? n) n)]
    (if-let [ns-name-str (namespace name-symb)]
      ;; FQN with ns/name
      (and (valid-symbol-name? (name name-symb))
           (valid-class-or-namespace-name?  ns-name-str))
      ;; either unqualified name or qualified class/namespace
      (or (valid-symbol-name? name-symb)
          (valid-class-or-namespace-name? name-symb)))))

