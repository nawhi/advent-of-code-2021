(ns adventofcode2021.day13)

(defn fold-x [[x y :as coord] axis]
  (if (> x axis)
    [(- x (* 2 (- x axis))) y]
    coord))
