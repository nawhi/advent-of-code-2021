#!/usr/bin/env bash

day_num=${1?:pass a day number as first argument}
cat > test/adventofcode2021/day${day_num}_test.clj << _HERE_
(ns adventofcode2021.day${day_num}-test
  (:require [clojure.test :refer :all]
            [adventofcode2021.day${day_num} :refer :all]
            [adventofcode2021.utils :refer [puzzle-input]]))

(deftest day${day_num}_test
  (testing "puzzle 1"
      (are [x expected]
        (is (= (my-func x) expected))
        "abc" nil
        )))
_HERE_

cat > src/adventofcode2021/day${day_num}.clj << _HERE_
(ns adventofcode2021.day${day_num})

(defn my-func [_] nil)
_HERE_
