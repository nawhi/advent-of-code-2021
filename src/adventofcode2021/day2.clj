(ns adventofcode2021.day2
  (:require [clojure.string :as str]))

(defn final-position
  "Returns tuple of final horizontal position and depth"
  [commands]
  (let [[h d] [0 0]
        command (first commands)
        [direction amount] (str/split command #" ")]
    [(+ h (Integer/parseInt amount)) 0]))
