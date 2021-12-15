(ns adventofcode2021.day8-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day8 :refer :all]
            [adventofcode2021.utils :refer :all]))

(deftest day8
  (are [input expected]
    (is (= (frequency-of-1478 input) expected))
    ["ba abce eg | abcde"] 0
    ["ba abce eg | abcde afcba"] 0
    ["ba abce eg fbaedbc | ab"] 1
    ["ba abce eg fbaedbc | ab abc abcd abcdefg"] 4
    ["ba abce eg fbaedbc | ab abc abcd abcde abcdefg"
     "ba abce eg fbaedbc | abcdefg abc abcd abcde ab ab ab"] 10
    (puzzle-input "8.1.example") 26
    (puzzle-input "8.1") 449
    ))
