(ns adventofcode2021.day1)

(defn- is-increase [[m1 m2]] (if (< m1 m2) 1 0))

(defn count-measurement-increase
  "Count the number of times a measurement increases from the previous"
  [measurements]
  (reduce #(+ %1 (is-increase %2)) 0 (partition 2 1 measurements)))

