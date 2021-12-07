(ns adventofcode2021.day5
  (:require [clojure.test :refer :all]
            [adventofcode2021.utils :refer :all]
            [clojure.string :as string]))

(defn diagonal? [{[from-x from-y] :from [to-x to-y] :to}]
  (= (Math/abs (- from-x to-x)) (Math/abs (- from-y to-y))))

(defn allowed? [{[from-x from-y] :from [to-x to-y] :to :as line}]
  (or (= from-x to-x)
      (= from-y to-y)
      (diagonal? line)))

(defn points-covered [{[from-x from-y] :from [to-x to-y] :to :as line}]
  (let [min-x (min from-x to-x) max-x (max from-x to-x)
        min-y (min from-y to-y) max-y (max from-y to-y)]
    (if (diagonal? line)
      (for [i (range 0 (+ (Math/abs (- from-x to-x)) 1))]
        [(+ min-x i) (+ min-y i)])
      (for [x (range min-x (+ max-x 1)) y (range min-y (+ max-y 1))]
        [x y]))))

(defn debug [x] (println x) x)

(defn overlapping-lines
  "Returns number of positions where at least 2 lines overlap"
  [lines]
  (->> lines
       (filter allowed?)
       (map points-covered)
       (apply concat)
       (frequencies)
       (vals)
       (filter #(> % 1))
       (count)))

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
  (testing "is vertical, horizontal or diagonal"
    (is (true? (allowed? {:from [0 0] :to [0 1]})))
    (is (true? (allowed? {:from [0 0] :to [1 0]})))
    (is (true? (allowed? {:from [1 1] :to [2 2]})))
    (is (true? (allowed? {:from [5 3] :to [2 0]})))
    (is (false? (allowed? {:from [0 0] :to [3 2]})))
    (is (false? (allowed? {:from [0 0] :to [2 1]})))))

(deftest points-covered-test
  (testing "returns points fully covered by line"
    (is (= [[0 0] [1 0]] (points-covered {:from [0 0] :to [1 0]})))
    (is (= [[0 1] [0 2] [0 3]] (points-covered {:from [0 1] :to [0 3]})))
    (is (= [[0 1] [0 2] [0 3]] (points-covered {:from [0 3] :to [0 1]})))
    (is (= [[0 0] [1 1] [2 2]] (points-covered {:from [0 0] :to [2 2]})))
    (is (= [[3 1] [4 2] [5 3] [6 4]] (points-covered {:from [3 1] :to [6 4]})))
    (is (= [[1 1] [2 1] [3 1] [4 1]] (points-covered {:from [1 1] :to [4 1]})))))


(deftest overlap-test
  (testing "0 points when 0 lines"
    (is (= 0 (overlapping-lines []))))
  (testing "0 points when no lines overlap"
    (is (= 0 (overlapping-lines [{:from [0 0] :to [1 0]}
                                 {:from [1 1] :to [2 1]}]))))
  (testing "considers only horizontal & vertical lines"
    (is (= 0 (overlapping-lines [{:from [1 1] :to [4 3]}
                                 {:from [1 1] :to [8 6]}]))))
  (testing "1 point when 2 lines overlap at one position"
    (is (= 1 (overlapping-lines [{:from [1 0] :to [2 0]}
                                 {:from [0 0] :to [1 0]}]))))
  (testing "handles lines going back or down"
    (is (= 1 (overlapping-lines [{:from [4 4] :to [3 4]}
                                 {:from [3 5] :to [3 4]}]))))
  (testing "2 points when 2 lines overlap in 2 different positions")
  (is (= 2 (overlapping-lines [{:from [1 0] :to [3 0]}
                               {:from [2 0] :to [4 0]}]))))
;
;;(deftest puzzle1-test
;;  (testing "puzzle example"
;;    (let [lines (parse-lines (puzzle-input "5.1.example"))]
;;      (is (= 5 (overlapping-lines lines)))))
;;  (testing "puzzle 1"
;;    (let [lines (parse-lines (puzzle-input "5.1"))]
;;      (is (= 4993 (overlapping-lines lines))))))
;
(deftest puzzle2-test
  (testing "with diagonals"
    (let [lines (parse-lines (puzzle-input "5.1.example"))]
      (is (= 12 (overlapping-lines lines))))))
