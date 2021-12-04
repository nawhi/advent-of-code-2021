(ns adventofcode2021.day4-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day4 :refer :all]))

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
