(ns adventofcode2021.day12-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day12 :refer :all]))

(deftest day12-test
  (testing "parse cave system from string"
    (are [input expected]
      (is (= (parse-cave input) expected))
      ["a-b"] {"a" #{"b"}}
      ["a-b" "a-c"] {"a" #{"b" "c"}}
      ["a-b" "a-c" "a-d"] {"a" #{"b" "c" "d"}}
      ["a-b" "a-c" "a-d" "b-d" "b-e"] {"a" #{"b" "c" "d"} "b" #{"d" "e"}}
      )))
