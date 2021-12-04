(ns adventofcode2021.day2-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day2 :refer :all]
            [adventofcode2021.utils :refer :all]
            [clojure.string :as str]))

(deftest final-position-test
  (testing "calculates horizontal position and depth"
    (is (= [5 0] (final-position ["forward 5"])))
    (is (= [0 5] (final-position ["down 5"])))
    (is (= [0 10] (final-position ["down 4" "down 6"])))
    (is (= [10 0] (final-position ["forward 4" "forward 6"])))
    (is (= [4 6] (final-position ["forward 4" "down 6"])))
    (is (= [0 2] (final-position ["down 4" "up 2"])))
    (is (= [14 32] (final-position ["forward 4" "down 6" "forward 10" "down 26"])))))

(deftest day2-puzzle1
  (testing "solves day 2 puzzle 1"
    (let [lines (puzzle-input "2.1")
          [h d] (final-position lines)]
      (is (= 1690020 (* h d))))))
