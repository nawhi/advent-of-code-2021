(ns adventofcode2021.day7-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.utils :refer :all]
            [adventofcode2021.day7 :refer :all]
            [clojure.string :as string]))


(deftest fuel-for-position-test
  (are [crabs position expected]
    (= (fuel-for-position crabs position) expected)
    [1] 1 [0]
    [2] 1 [1]
    [2] 5 [3]
    [2 2] 5 [3 3]
    [16 1 2 0 4 2 7 1 2 14] 2 [14 1 0 2 2 0 5 1 0 12]))

(deftest best-align-position-test
  (are [crabs expected]
    (= (fuel-for-best-align-position crabs) expected)
    [1] 0                                                   ; position 1
    [1 7 7] 6                                               ; position 7
    [1 1 4 4 4 9 9 9 9] 26                                  ; position 4
    [16 1 2 0 4 2 7 1 2 14] 37                              ; position 2
    ))

(deftest puzzle1
  (let [input (->> (raw-puzzle-input "7.1")
                   (#(string/trim-newline %))
                   (#(string/split % #","))
                   (map #(Integer/parseInt %)))]
    (is (= 344297 (fuel-for-best-align-position input)))))
