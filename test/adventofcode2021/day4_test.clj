(ns adventofcode2021.day4-test
  (:require [clojure.test :refer :all]))

(defn all-drawn [row] (every? (fn [[_ drawn]] drawn) row))

(defn- row-has-won [board]
  (some #(all-drawn %) board))

(defn- col-has-won [board]
  (let [row (mapv first board)]
    (all-drawn row)))

(defn has-won [board]
  (let [board-2d (to-array-2d board)]
    (boolean (or (row-has-won board-2d)
                 (col-has-won board-2d)))))

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
  (testing "column can win"
    (is (= true (has-won [[[1 true] [2 false]]
                          [[3 true] [4 false]]]))))
  )
