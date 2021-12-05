(ns adventofcode2021.day4)

(defn- transpose [m] (apply mapv vector m))

(defn- row-win? [board draws]
  (some (fn [row]
          (every? #(contains? draws %) row)) board))

(defn- win? [board draws]
  (or (row-win? board draws) (row-win? (transpose board) draws)))

(defn- calc-score [board draws last-drawn]
  (let [undrawn (filter #(not (contains? draws %)) (flatten board))]
    (* (reduce + undrawn) last-drawn)))

(defn score [board draws]
  (let [draws-set (set draws)]
    (if (win? board draws-set)
      (calc-score board draws-set (last draws))
      nil)))
