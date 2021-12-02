(ns adventofcode2021.day1-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day1 :refer :all]))

(deftest a-test
  (testing "How many measurements are larger than the previous"
    (is (= 1 (count-measurement-increase [1 2])))))
