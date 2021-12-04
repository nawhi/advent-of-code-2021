(ns adventofcode2021.day2
  (:require [clojure.string :as str]))

(defn- parse-command [[x y] [direction amount]]
  (let [val (Integer/parseInt amount)]
    (case direction
      "forward" [(+ x val) y]
      "up" [x (- y val)]
      "down" [x (+ y val)])))

(defn final-position
  "Returns tuple of final horizontal position and depth"
  [commands]
  (reduce #(parse-command %1 (str/split %2 #" ")) [0 0] commands))
