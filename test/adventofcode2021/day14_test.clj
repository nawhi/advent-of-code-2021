(ns adventofcode2021.day14-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day14 :refer :all]
            [adventofcode2021.utils :refer [puzzle-input]]))

(def EXAMPLE-RULES ["CH -> B" "HH -> N" "CB -> H" "NH -> C"
                    "HB -> C" "HC -> B" "HN -> C" "NN -> C"
                    "BH -> H" "NC -> B" "NB -> B" "BN -> B"
                    "BB -> N" "BC -> B" "CC -> N" "CN -> C"])

(deftest day14_test
  (testing "puzzle 1"
    (are [template rules expected]
      (is (= (simulate-pair-insertion template rules) expected))
      "AB" ["CD -> E"] "AB"
      "AB" ["AB -> E"] "AEB"
      "ABC" ["AB -> E" "BC -> F"] "AEBFC"
      "NNCB" EXAMPLE-RULES "NCNBCHB"
      "NCNBCHB" EXAMPLE-RULES "NBCCNBBBCBHCB"
      "NBCCNBBBCBHCB" EXAMPLE-RULES "NBBBCNCCNBBNBNBBCHBHHBCHB"
      "NBBBCNCCNBBNBNBBCHBHHBCHB" EXAMPLE-RULES "NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"
      )))
