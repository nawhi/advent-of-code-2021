(ns adventofcode2021.day8-test
  (:require [clojure.test :refer :all]
            [clojure.string :as string]
            [adventofcode2021.utils :refer :all]))

(defn debug [x] (println x) x)

(defn frequency-of-1478 [lines]
  "returns number of times a 2, 3, 4 or 7-character word appeared after the bar"
  (println (string/join ", " lines))
  (->> lines
       (map #(last (string/split % #" \| ")))
       (map #(string/split % #" "))
       (flatten)
       (filter #(contains? #{2 3 4 7} (count %)))
       (count)
       ))

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
