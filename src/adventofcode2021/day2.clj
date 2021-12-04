(ns adventofcode2021.day2
  (:require [clojure.string :as str]))

(defn- parse-command [[prev-h prev-d] [direction amount]]
  (case direction
    "forward" [(+ prev-h (Integer/parseInt amount)) 0]
    "down" [0 (+ prev-h (Integer/parseInt amount))]))

(defn final-position
  "Returns tuple of final horizontal position and depth"
  [commands]
  (parse-command [0 0] (str/split (first commands) #" ")))
