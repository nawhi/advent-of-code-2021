(ns adventofcode2021.day5
  (:require [clojure.string :as string]))

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
