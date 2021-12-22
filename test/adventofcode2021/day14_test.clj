(ns adventofcode2021.day14-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day14 :refer :all]
            [adventofcode2021.utils :refer [puzzle-input]]))

(deftest day14_test
  (testing "puzzle 1"
    (are [x expected]
      (is (= (my-func x) expected))
      "abc" nil
      )))
