(ns adventofcode2021.day11-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.utils :refer [get2d]]))

(defn map2d [fn coll] (mapv #(mapv fn %) coll))

(def NEIGHBOURS [[-1 1] [0 1] [1 1]
                 [-1 0] #____ [1 0]
                 [-1 -1] [0 -1] [1 -1]])

(defn iterate-octopi [grid]
  (map2d #(mod (+ % 1) 10) grid))

(deftest day11
  (testing "octopus simulation"
    (are [start expected]
      (is (= (iterate-octopi start) expected))
      [[0]] [[1]]
      [[1]] [[2]]
      [[9]] [[0]]
      [[1 1] [1 1]] [[2 2] [2 2]]
      [[1 2] [3 4]] [[2 3] [4 5]]
      ;[[1 1] [9 1]] [[3 3] [0 3]]
      )))
