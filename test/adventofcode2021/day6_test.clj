(ns adventofcode2021.day6-test
  (:require [clojure.test :refer :all]))

(defn count-fish [[starting-fish] num-days]
  (if (> num-days starting-fish) 2 1))

(deftest count-fish-test
  (testing "single fish lasts days minus age"
    (is (= 1 (count-fish [1] 1)))
    (is (= 1 (count-fish [5] 5)))
    (is (= 1 (count-fish [1] 1))))
  (testing "fish spawns another fish when its age reaches zero"
    (is (= 1 (count-fish [1] 1)))
    (is (= 2 (count-fish [1] 2)))))
