(ns adventofcode2021.day6-test
  (:require [clojure.test :refer :all]
            [clojure.core.matrix :as mx]))

(def STATE-TRANSITION-MX
  "foo"
  (mx/matrix [[0 1 0 0 0 0 0 0 0]
              [0 0 1 0 0 0 0 0 0]
              [0 0 0 1 0 0 0 0 0]
              [0 0 0 0 1 0 0 0 0]
              [0 0 0 0 0 1 0 0 0]
              [0 0 0 0 0 0 1 0 0]
              [1 0 0 0 0 0 0 1 0]
              [0 0 0 0 0 0 0 0 1]
              [1 0 0 0 0 0 0 0 0]]))


(defn calc-agev [fish] (for [x (range 0 9)] (count (filter #(= % x) fish))))

(defn calc-end-agev [fish-vector num-days]
  (let [transition-mx (mx/pow STATE-TRANSITION-MX num-days)]
    (map int (mx/dot fish-vector transition-mx))))

(defn count-fish [initial-ages num-days]
  "Takes a list of fish represented by a number for time until next reproduction.
  Returns the number of fish after num-days days"
  (reduce + (calc-end-agev (calc-agev initial-ages) num-days)))

(deftest calc-agev-test
  (are [fish expected]
    (= (calc-agev fish) expected)
    [] [0 0 0 0 0 0 0 0 0]
    [0] [1 0 0 0 0 0 0 0 0]
    [0 0] [2 0 0 0 0 0 0 0 0]
    [0 0 0 0] [4 0 0 0 0 0 0 0 0]
    [0 0 0 0] [4 0 0 0 0 0 0 0 0]
    [0 1 2 3 4 5 6 7 8] [1 1 1 1 1 1 1 1 1]
    [4 2 3 2 7 4 6 5 2 4 3 8 5 6 3] [0 0 3 3 3 2 2 1 1]
    [6 0 6 4 5 6 0 1 1 2 6 0 1 1 1 2 2 3 3 4 6 7 8 8 8 8] [3 5 3 2 2 1 5 1 4]
    ))

(deftest calc-end-agev-test
  (are [fish-vector iterations expected]
    (= (calc-end-agev fish-vector iterations) expected)
    [0 0 0 0 0 0 0 0 0] 0 [0 0 0 0 0 0 0 0 0]
    [0 1 0 0 0 0 0 0 0] 1 [1 0 0 0 0 0 0 0 0]
    [1 0 0 0 0 0 0 0 0] 1 [0 0 0 0 0 0 1 0 1]
    ))

;(deftest count-fish-test
;  (are [fish iterations expected]
;    (= expected (count-fish fish iterations))
;    [1] 1 1
;    ;[5] 1 1
;    ;[8] 1 1
;    ;[0] 1 2
;    ;[6] 6 1
;    ;[6] 7 2
;    ;[3 4 3 1 2] 18 5934
;    ))
