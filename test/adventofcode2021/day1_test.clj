(ns adventofcode2021.day1-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day1 :refer :all]))

(deftest test
  (testing "How many measurements are larger than the previous"
    (is (= 1 (count-measurement-increase [1 2])))
    (is (= 5 (count-measurement-increase [1 2 3 4 6 8])))
    (is (= 3 (count-measurement-increase [10 15 10 15 10 15])))
    (is (= 2 (count-measurement-increase [1 2 3])))))

(deftest puzzle1
  (testing "puzzle 1"
    (let [raw-input (slurp "resources/1.1.txt")
          split-input (clojure.string/split raw-input #"\n" )
          numeric-input (map #(Integer/parseInt %) split-input)
          result (count-measurement-increase numeric-input)]
      (is (= 1226 result)))))
