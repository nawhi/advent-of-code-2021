(ns adventofcode2021.day14-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day14 :refer :all]
            [adventofcode2021.utils :refer [puzzle-input]]))

(def EXAMPLE-RULES ["CH -> B" "HH -> N" "CB -> H" "NH -> C"
                    "HB -> C" "HC -> B" "HN -> C" "NN -> C"
                    "BH -> H" "NC -> B" "NB -> B" "BN -> B"
                    "BB -> N" "BC -> B" "CC -> N" "CN -> C"])

(deftest day14_test
  (testing "polymer insertion simulation"
    (are [template rules expected]
      (is (= (simulate-pair-insertion rules template) expected))
      "AB" ["CD -> E"] "AB"
      "AB" ["AB -> E"] "AEB"
      "ABC" ["AB -> E" "BC -> F"] "AEBFC"
      "NNCB" EXAMPLE-RULES "NCNBCHB"
      "NCNBCHB" EXAMPLE-RULES "NBCCNBBBCBHCB"
      "NBCCNBBBCBHCB" EXAMPLE-RULES "NBBBCNCCNBBNBNBBCHBHHBCHB"
      "NBBBCNCCNBBNBNBBCHBHHBCHB" EXAMPLE-RULES "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"
      ))
  (testing "scoring polymer"
    (are [polymer score]
      (is (= (score-polymer polymer) score))
      "HBB" 1
      "HHHHBB" 2
      "ABCDEFGHJJJJJJJJJJK" 9
      ))
  (testing "puzzle 1 example"
    (let [input (concat ["NNCB" ""] EXAMPLE-RULES)]
      (is (= (run-simulation input) 1588))
      ))
  (testing "puzzle 1"
    (is (= 2745 (run-simulation (puzzle-input "14.1"))))))
