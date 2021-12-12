(ns adventofcode2021.day7-test
  (:require [clojure.test :refer :all]))

(defn best-align-position [crabs]
  (let [min-pos (min crabs)
        max-pos (max crabs)
        scores (for [crab-pos (range crabs)
                     target-pos (range min-pos (+ max-pos 1))]
                 (Math/abs (- crab-pos target-pos)))]
    scores))

  (deftest day7
    (testing "thingy"
      (are [crabs expected]
        (= (best-align-position crabs) expected)
        [1] 1
        [1 2 2] 2
        )))
