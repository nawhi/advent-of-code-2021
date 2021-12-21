(ns adventofcode2021.day12-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day12 :refer :all]
            [adventofcode2021.utils :refer [puzzle-input]]))


(deftest day12-test
  (testing "parse cave system from string"
    (are [input expected]
      (is (= (parse-cave input) expected))
      ["a-b"] {"a" #{"b"} "b" #{"a"}}
      ["a-b" "a-c"] {"a" #{"b" "c"} "b" #{"a"} "c" #{"a"}}
      ["a-b" "a-c" "a-d"] {"a" #{"b" "c" "d"} "b" #{"a"} "c" #{"a"} "d" #{"a"}}
      ["a-b" "a-c" "a-d" "b-d" "b-e"] {"a" #{"b" "c" "d"}
                                       "b" #{"a" "d" "e"}
                                       "c" #{"a"}
                                       "d" #{"a" "b"}
                                       "e" #{"b"}}
      ["start-x" "x-end"] {"start" #{"x"} "x" #{"start" "end"} "end" #{"x"}}
      ))
  (testing "find all routes through a cave system"
    (are [input expected]
      (is (= (routes input) expected))
      ["start-end"] #{"start,end"}
      ["start-x" "x-end"] #{"start,x,end"}
      ["start-x" "x-y" "y-end"] #{"start,x,y,end"}
      ["start-x" "start-y" "x-end" "y-end"] #{"start,x,end" "start,y,end"}
      ))
  (testing "larger examples"
    (let [example1 ["start-A" "start-b" "A-c" "A-b" "b-d" "A-end" "b-end"]
          example2 ["dc-end" "HN-start" "start-kj" "dc-start" "dc-HN" "LN-dc" "HN-end" "kj-sa" "kj-HN" "kj-dc"]
          example3 ["fs-end" "he-DX" "fs-he" "start-DX" "pj-DX" "end-zg" "zg-sl" "zg-pj" "pj-he" "RW-he" "fs-DX" "pj-RW" "zg-RW" "start-pj" "he-WI" "zg-he" "pj-fs" "start-RW"]]
      (is (= (count (routes example1)) 10))
      (is (= (count (routes example2)) 19))
      (is (= (count (routes example3)) 226))
      ))
  (testing "puzzle 1"
    (is (= (count (routes (puzzle-input "12.1"))) 12345)))
  )
