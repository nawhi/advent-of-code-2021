(ns adventofcode2021.day1)

(defn count-measurement-increase
  "Count the number of times a measurement increases from the previous"
  [measurements]
  (let [[m1 m2] measurements]
    (if (< m1 m2) 1 0)))
