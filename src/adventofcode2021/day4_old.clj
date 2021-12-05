(ns adventofcode2021.day4-old)

(defn- row-has-won [board]
  (some #(every? (fn [[_ drawn]] drawn) %) board))

(defn- transpose [m] (apply mapv vector m))

(defn has-won [board]
  (let [board-2d (to-array-2d board)]
    (boolean (or (row-has-won board-2d)
                 (row-has-won (transpose board-2d))))))
