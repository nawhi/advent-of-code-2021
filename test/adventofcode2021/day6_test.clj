(ns adventofcode2021.day6-test
  (:require [clojure.test :refer :all]))

(def STATE-TRANSITION-MX
  "foo"
  [[0 0 0 0 0 0 0 0 1]
   [1 0 0 0 0 0 0 0 0]
   [0 1 0 0 0 0 0 0 1]
   [0 0 1 0 0 0 0 0 0]
   [0 0 0 1 0 0 0 0 0]
   [0 0 0 0 1 0 0 0 0]
   [0 0 0 0 0 1 0 0 0]
   [0 0 0 0 0 0 1 0 0]
   [0 0 0 0 0 0 0 1 0]])

(defn count-fish [[age] iterations]
  (if (>= (- age iterations) 0)
    1
    (let [offset (mod age 6)])
    ))

(deftest count-fish-test
  (are [expected fish iterations]
    (= expected (count-fish fish iterations))
    1 [1] 1
    1 [6] 6
    2 [0] 1
    2 [6] 7))
