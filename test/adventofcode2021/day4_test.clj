(ns adventofcode2021.day4-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day4 :refer :all]))



(deftest score-test
  (testing "non winning board has score of nil"
    (is (nil? (score [[1]] [])))
    (is (nil? (score [[1 2] [3 4]] [])))
    )
  (testing "winning board scores sum of unmarked numbers * number just called"
    (is (= 0 (score [[1]] [1])))
    (is (= 0 (score [[5]] [1 2 3 4 5])))
    (is (= 0 (score [[1 0] [1 0]] [1])))
    (is (= 0 (score [[0 1] [0 1]] [1])))
    (is (= 7 (score [[1 2] [3 4]] [2 1])))
    (is (= (* 5 8) (score [[3 7] [2 8]] [7 8])))
    ))
