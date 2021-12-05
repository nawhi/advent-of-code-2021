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

(defn- parse-input [input]
  (let [[raw-numbers & raw-boards] (string/split input #"\n\n")]
    {:numbers (map #(Integer/parseInt %) (string/split raw-numbers #","))
     :boards  (map #(parse-board %) raw-boards)}))

(defn score-winning-board [raw]
  (let [{:keys [numbers boards]} (parse-input raw)]
    (loop [i 1]
      (let [nums (take i numbers)
            scores (->> boards (map #(score % nums)) (filter some?))]
        (if (not-empty scores)
          (first scores)
          (recur (+ i 1)))))))

