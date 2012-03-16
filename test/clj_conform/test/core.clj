;; Copyright (c) Frank Siebenlist. All rights reserved.
;; The use and distribution terms for this software are covered by the
;; Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;; which can be found in the file COPYING at the root of this distribution.
;; By using this software in any fashion, you are agreeing to be bound by
;; the terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns clj-conform.test.core
  (:use [clj-conform])
  (:use [clojure.test]))

(deftest valid-symbol-name ;; 
  (is (valid-symbol-name? 'abc))
  (is (valid-symbol-name? "abc"))
  (is (valid-symbol-name? "*abc*"))
  (is (valid-symbol-name? "abc123"))
  (is (valid-symbol-name? "abc-def_123"))
  (is (not (valid-symbol-name? "1abc")))
  (is (not (valid-symbol-name? "abc def")))
  (is (not (valid-symbol-name? "abc/def")))
  (is (not (valid-symbol-name? "abc&def")))
  (is (not (valid-symbol-name? "abc.def")))
  )

(deftest valid-class-or-namespace-name ;; 
  (is (valid-class-or-namespace-name? 'abc))
  (is (valid-class-or-namespace-name? "abc"))
  (is (valid-class-or-namespace-name? "*abc*"))
  (is (valid-class-or-namespace-name? "abc123"))
  (is (valid-class-or-namespace-name? "abc-def_123"))
  (is (valid-class-or-namespace-name? "abc-def_123"))
  (is (valid-class-or-namespace-name? "abc.def.123"))
  (is (not (valid-class-or-namespace-name? "1abc")))
  (is (not (valid-class-or-namespace-name? "abc def")))
  (is (not (valid-class-or-namespace-name? "abc/def")))
  (is (not (valid-class-or-namespace-name? "abc&def")))
  (is (not (valid-class-or-namespace-name? "abc..def")))
  (is (not (valid-class-or-namespace-name? "abc.def.")))
  (is (not (valid-class-or-namespace-name? ".abc.def")))
  )
  
(deftest valid-symbol-fqname ;; 
  (is (valid-symbol-fqname? 'abc))
  (is (valid-symbol-fqname? "abc"))
  (is (valid-symbol-fqname? "*abc*"))
  (is (valid-symbol-fqname? "*abc*.def"))
  (is (valid-symbol-fqname? "abc123"))
  (is (valid-symbol-fqname? "abc-def_123"))
  (is (valid-symbol-fqname? "abc-def_123"))
  (is (valid-symbol-fqname? "abc/def"))
  (is (valid-symbol-fqname? "abc.ghi/def"))
  (is (valid-symbol-fqname? 'abc.ghi/def))
  (is (not (valid-symbol-fqname? "1abc")))
  (is (not (valid-symbol-fqname? "abc def")))
  (is (not (valid-symbol-fqname? "abc/def/")))
  (is (not (valid-symbol-fqname? "abc/def/ghi")))
  (is (not (valid-symbol-fqname? 'abc/def/ghi)))
  (is (not (valid-symbol-fqname? "abc..def/ghi")))
  (is (not (valid-symbol-fqname? "/abc.def")))
  (is (not (valid-symbol-fqname? "abc.def//")))
  )
