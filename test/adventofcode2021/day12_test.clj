(ns adventofcode2021.day12-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day12 :refer :all]
            [adventofcode2021.utils :refer [puzzle-input]]))

(deftest day12-test
  (testing "parse cave system from string"
    (are [input expected]
      (is (= (parse-cave input) expected))
      ["a-b"] {"a" #{"b"}}
      ["a-b" "a-c"] {"a" #{"b" "c"}}
      ["a-b" "a-c" "a-d"] {"a" #{"b" "c" "d"}}
      ["a-b" "a-c" "a-d" "b-d" "b-e"] {"a" #{"b" "c" "d"} "b" #{"d" "e"}}
      (puzzle-input "12.1") {"DG"    #{"dc"}
                             "MN"    #{"ah" "dc" "fi" "start" "wn"}
                             "XB"    #{"fi"}
                             "dc"    #{"XB" "ah"}
                             "end"   #{"XB" "yw"}
                             "fi"    #{"end" "yw"}
                             "start" #{"ah" "dc"}
                             "th"    #{"fi"}
                             "wn"    #{"DG" "XB" "ah" "yw"}
                             "yw"    #{"DG" "MN" "XN"}}
      ))
  (testing "find all routes through a cave system"
    (are [graph expected]
      (is (= (routes graph) expected))
      {"start" #{"end"}} #{["start" "end"]}
      ))
  )
