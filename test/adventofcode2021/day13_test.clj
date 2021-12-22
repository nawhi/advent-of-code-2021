(ns adventofcode2021.day13-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day13 :refer :all]
            [adventofcode2021.utils :refer [puzzle-input]]
            [clojure.string :as string]))

(deftest day13-test
  (testing "fold a coordinate left in x-axis"
    (are [coord axis expected]
      (is (= (fold-x coord axis) expected))
      [0 0] 1 [0 0]
      [2 1] 1 [0 1]
      [7 8] 4 [1 8]
      ))
  (testing "puzzle 1"
    (let [coords (->> (puzzle-input "13.1")
                      (take-while not-empty)
                      (map #(string/split % #","))
                      (map (fn [c] (map #(Integer/parseInt %) c)))
                      (map #(fold-x % 655))
                      (distinct))]
      (is (= (count coords) 795)))))
