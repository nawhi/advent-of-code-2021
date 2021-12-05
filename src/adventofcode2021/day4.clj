(ns adventofcode2021.day4)

(defn- transpose [m] (apply mapv vector m))

(defn- row-win? [board draws]
  (let [[row] board]
    (every? #(contains? draws %) row)))

(defn win? [board draws]
  (or (row-win? board draws) (row-win? (transpose board) draws)))

  (defn score [board draws] (if (win? board (set draws)) 0 nil))
