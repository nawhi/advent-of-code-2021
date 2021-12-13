(ns adventofcode2021.day8-test
  (:require [clojure.test :refer :all]))

(defn local-minima [mx]
  (let [arr (to-array-2d mx)]
    #{(aget mx 0 0)}))

(deftest local-minimum
  (are [mx expected]
    (is (= (local-minima mx) expected))
    [[0 1]] #{0}))
