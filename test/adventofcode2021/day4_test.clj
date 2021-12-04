(ns adventofcode2021.day4-test
  (:require [clojure.test :refer :all]))

(defn has-won [board]
  (let [[row] board]
    (every? (fn [[_ drawn]] drawn) row)))

(deftest has-board-won
  (testing "1 entry"
    (is (= false (has-won [[[1 false]]])))
    (is (= true (has-won [[[1 true]]])))
    )
  (testing "2 entries 1 row"
    (is (= true (has-won [[[1 true] [2 true]]])))
    (is (= false (has-won [[[1 true] [2 false]]])))
    )
  ;(testing "diagonal is not a win"
  ;  (is (= false (has-won [[[1 true] [2 false]]
  ;                         [[3 false] [4 true]]])))
  ;  )
  )
