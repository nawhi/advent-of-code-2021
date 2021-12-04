(ns adventofcode2021.day3-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day3 :refer :all]
            [adventofcode2021.utils :refer :all]))

(deftest epsilon-test
  (testing "epsilon calculation"
    (is (= 0 (epsilon ["0"])))
    (is (= 1 (epsilon ["1"])))
    (is (= 1 (epsilon ["0" "1" "1"])))
    (is (= 0 (epsilon ["0" "0" "1" "0" "1"])))
    (is (= 2r01 (epsilon ["01" "10" "01" "01"])))
    (is (= 2r011 (epsilon ["011" "101" "011" "010"])))
    )
  (testing "gamma calculation"
    (is (= 1 (gamma ["0"])))
    (is (= 0 (gamma ["1"])))
    (is (= 0 (gamma ["0" "1" "1"])))
    (is (= 1 (gamma ["0" "0" "1" "0" "1"])))
    (is (= 2r10 (gamma ["01" "10" "01" "01"])))
    (is (= 2r100 (gamma ["011" "101" "011" "010"]))))

  (testing "epsilon * gamma"
    (let [input ["00100" "11110" "10110" "10111" "10101" "01111" "00111" "11100" "10000" "11001" "00010" "01010"]]
      (is (= 198 (power-consumption input)))))

  (testing "puzzle 1"
    (is (= 3959450 (power-consumption (puzzle-input "3.1"))))))
