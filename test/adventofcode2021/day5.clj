(ns adventofcode2021.day5
  (:require [clojure.test :refer :all]
            [adventofcode2021.utils :refer :all]
            [clojure.string :as string]))

(defn is-vertical-or-horizontal [{[from-x from-y] :from [to-x to-y] :to}]
  (or (= from-x to-x) (= from-y to-y)))

(defn points-covered [{[from-x from-y] :from [to-x to-y] :to}]
  (for [x (range from-x (+ to-x 1)) y (range from-y (+ to-y 1))]
    [x y]))

(defn debug [x] (println x) x)

(defn overlapping-lines
  "Returns number of positions where at least 2 lines overlap"
  [lines]
  (->> lines
       (filter is-vertical-or-horizontal)
       (map points-covered)
       (apply concat)
       (frequencies)
       (vals)
       (filter #(> % 1))
       (count)))

(defn- map-inside [fn coll] (map #(map fn %) coll))

(defn parse-coord [raw]
  (let [[x y] (string/split raw #",")]
    [(Integer/parseInt x) (Integer/parseInt y)]))

(defn parse-lines [raw-lines]
  (map #(let [[from to] (map parse-coord (string/split % #" -> "))]
          {:from from :to to})
       raw-lines))

(deftest parse-lines-test
  (testing "parses a line in x1,y1 -> x2,y2 format"
    (is (= [{:from [25 48] :to [37 98]}] (parse-lines ["25,48 -> 37,98"])))))

(deftest is-vertical-or-horizontal-test
  (testing "is vertical or horizontal")
  (is (true? (is-vertical-or-horizontal {:from [0 0] :to [0 1]})))
  (is (true? (is-vertical-or-horizontal {:from [0 0] :to [1 0]})))
  (is (false? (is-vertical-or-horizontal {:from [0 0] :to [3 2]})))
  (is (false? (is-vertical-or-horizontal {:from [0 0] :to [1 1]}))))

(deftest points-covered-test
  (testing "returns 2 points covered by line"
    (is (= [[0 0] [1 0]] (points-covered {:from [0 0] :to [1 0]})))))
(is (= [[0 1] [0 2] [0 3]] (points-covered {:from [0 1] :to [0 3]})))
(is (= [[1 1] [2 1] [3 1] [4 1]] (points-covered {:from [1 1] :to [4 1]})))


(deftest overlap-test
  (testing "0 points when 0 lines"
    (is (= 0 (overlapping-lines []))))
  (testing "0 points when no lines overlap"
    (is (= 0 (overlapping-lines [{:from [0 0] :to [1 0]}
                                 {:from [1 1] :to [2 1]}]))))
  (testing "considers only horizontal & vertical lines"
    (is (= 0 (overlapping-lines [{:from [1 1] :to [2 2]}
                                 {:from [1 1] :to [2 2]}]))))
  (testing "1 point when 2 lines overlap at one position"
    (is (= 1 (overlapping-lines [{:from [1 0] :to [2 0]}
                                 {:from [0 0] :to [1 0]}]))))
  (testing "2 points when 2 lines overlap in 2 different positions")
  (is (= 2 (overlapping-lines [{:from [1 0] :to [3 0]}
                               {:from [2 0] :to [4 0]}]))))

(deftest puzzle1-test
  (testing "puzzle example"
    (let [lines (parse-lines (puzzle-input "5.1.example"))]
      (is (= 5 (overlapping-lines lines))))))
