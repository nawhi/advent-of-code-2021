(ns adventofcode2021.day10-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day10 :refer :all]))

(deftest first-illegal-character-test
  (are [string expected]
    (= (first-illegal-character string) expected)
    "(]" \]))
