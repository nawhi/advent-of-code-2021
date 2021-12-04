(ns adventofcode2021.day2-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day2 :refer :all]))

(deftest day2-test
  (testing "calculates horizontal position and depth"
    (is (= [5 0] (final-position ["forward 5"])))
    ;(is (= [10 0] (final-position ["forward 4" "forward 6"])))
    ))
