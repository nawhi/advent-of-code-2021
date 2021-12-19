(ns adventofcode2021.day9-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day9 :refer :all]
            [adventofcode2021.utils :refer :all]
            [clojure.string :as string]))

(deftest get2d-test
  (let [mx [["00" "10" "20"]
            ["01" "11" "12"]
            ["02" "12" "22"]]]
    (are
      [arr x y expected]
      (is (= (get2d arr x y) expected))
      [[]] 0 0 nil
      [[0]] 0 0 0
      mx 1 1 "11"
      mx 2 0 "20"
      mx 0 2 "02"
      mx 5 3 nil
      )))

(deftest local-minima-test
  (are [heightmap expected]
    (= (find-local-minima heightmap) expected)
    [[1]] [1]
    [[1 1 1]
     [1 0 1]
     [1 1 1]] [0]
    [[1 1 1]
     [1 0 1]] [0]
    [[2 1 9 9 9 4 3 2 1 0]
     [3 9 8 7 8 9 4 9 2 1]
     [9 8 5 6 7 8 9 8 9 2]
     [8 7 6 7 8 9 6 7 8 9]
     [9 8 9 9 9 6 5 6 7 8]] [0 1 5 5]
    ))

(deftest risk-level-test
  (are [minima expected]
    (= (risk-level minima) expected)
    [0] 1
    [1] 2
    [0 0] 2
    [5 6 7] 21
    [0 1 5 5] 15))

(deftest puzzle1-test
  (let [input (->> (puzzle-input "9.1")
                   (map #(string/split % #""))
                   (map (fn [row] (map #(Integer/parseInt %) row))))
        minima (find-local-minima input)
        risk-level (risk-level minima)]
    (is (= 458 risk-level))))
