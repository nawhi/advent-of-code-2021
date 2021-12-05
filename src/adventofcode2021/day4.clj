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

(defn- map-2d [fn coll]
  (map #(map fn %) coll))

(defn- parse-board [raw-board]
  (let [split (string/split-lines raw-board)
        raw-nums (map #(string/split % #"[^\d]+") split)
        parsed-nums (map-2d identity raw-nums)]
    parsed-nums))

(defn score-winning-board [raw]
  (let [[raw-numbers & raw-boards] (string/split raw #"\n\n")
        numbers (map #(Integer/parseInt %) (string/split raw-numbers #","))
        boards (map #(parse-board %) raw-boards)]
    (println numbers)
    (println (first boards))))
