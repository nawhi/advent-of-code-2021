(ns adventofcode2021.day4-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day4 :refer :all]
            [adventofcode2021.utils :refer :all]))



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
    )
  (testing "example in puzzle"
    (is (= 4512 (score [[14 21 17 24 4]
                        [10 16 15 9 19]
                        [18 8 23 26 20]
                        [22 11 13 6 5]
                        [2 0 12 3 7]]
                       [7 4 9 5 11 17 23 2 0 14 21 24])))))

(deftest score-winning-board-test
  (testing "scores the board that would win first"
    (is (= 4512 (score-winning-board (raw-puzzle-input "4.1.example"))))))
