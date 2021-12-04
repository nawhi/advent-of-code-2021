(ns adventofcode2021.day3-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day3 :refer :all]))

(deftest epsilon-test
  (testing "epsilon calculation"
    (is (= "0" (epsilon ["0"])))
    (is (= "1" (epsilon ["1"])))
    (is (= "1" (epsilon ["0" "1" "1"])))
    (is (= "0" (epsilon ["0" "0" "1" "0" "1"])))
    (is (= "01" (epsilon ["01" "10" "01" "01"])))
    ))
