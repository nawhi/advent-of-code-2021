(ns adventofcode2021.day4
  (:require [clojure.string :as string]))

(defn- transpose [m] (apply mapv vector m))

(defn- row-win? [board draws]
  (some (fn [row]
          (every? #(contains? draws %) row)) board))

(defn- win? [board draws]
  (or (row-win? board draws) (row-win? (transpose board) draws)))

(defn- calc-score [board draws last-drawn]
  (->> board
       (flatten)
       (filter #(not (contains? draws %)))
       (reduce +)
       (* last-drawn)))

(defn score [board draws]
  (let [draws-set (set draws)]
    (if (win? board draws-set)
      (calc-score board draws-set (last draws))
      nil)))

(defn- map-2d [fn coll] (map #(map fn %) coll))

(defn- filter-rows [fn coll] (map #(filter fn %) coll))

(defn- parse-board [raw-board]
  (->> (string/split-lines raw-board)
       (map #(string/split % #"[^\d]+"))
       (filter-rows #(not (.isEmpty %)))
       (map-2d #(Integer/parseInt %))))

(defn score-winning-board [raw]
  (let [[raw-numbers & raw-boards] (string/split raw #"\n\n")
        numbers (map #(Integer/parseInt %) (string/split raw-numbers #","))
        boards (map #(parse-board %) raw-boards)]
    (loop [i 1]
      (let [nums (take i numbers)
            winners (filter #(some? (score % nums)) boards)]
        (if (not-empty winners)
          (score (first winners) nums)
          (recur (+ i 1)))))))

