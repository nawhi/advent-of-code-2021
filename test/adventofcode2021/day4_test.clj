(ns adventofcode2021.day4-test
  (:require [clojure.test :refer :all]))

(defn- row-has-won [board]
  (some #(every? (fn [[_ drawn]] drawn) %) board))

(defn- transpose [m] (apply mapv vector m))

(defn has-won [board]
  (let [board-2d (to-array-2d board)]
    (boolean (or (row-has-won board-2d)
                 (row-has-won (transpose board-2d))))))

(deftest has-board-won
  (testing "1 entry"
    (is (= false (has-won [[[1 false]]])))
    (is (= true (has-won [[[1 true]]])))
    )
  (testing "diagonal is not a win"
    (is (= false (has-won [[[1 true] [2 false]]
                           [[3 false] [4 true]]])))
    )
  (testing "second row can win"
    (is (= true (has-won [[[1 false] [2 false]]
                          [[3 true] [4 true]]]))))
  (testing "first column can win"
    (is (= true (has-won [[[1 true] [2 false]]
                          [[3 true] [4 false]]]))))
  (testing "second column can win"
    (is (= true (has-won [[[1 false] [2 true]]
                          [[3 false] [4 true]]])))
    )
  )
