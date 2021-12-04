(ns adventofcode2021.day2
  (:require [clojure.string :as str]))

(defn- parse-command [[prev-h prev-d] [direction amount]]
  (let [val (Integer/parseInt amount)]
    (case direction
      "forward" [(+ prev-h val) 0]
      "down" [0 (+ prev-d val)])))

(defn final-position
  "Returns tuple of final horizontal position and depth"
  [commands]
  (reduce
    #(parse-command %1 (str/split %2 #" "))
    [0 0]
    commands))
